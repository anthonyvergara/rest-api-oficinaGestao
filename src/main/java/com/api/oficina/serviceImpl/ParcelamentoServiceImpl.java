package com.api.oficina.serviceImpl;

import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.oficina.component.Parcelas;
import com.api.oficina.model.OrdemServico;
import com.api.oficina.model.Parcelamento;
import com.api.oficina.modelEnum.StatusParcela;
import com.api.oficina.repository.OrdemServicoRepository;
import com.api.oficina.repository.ParcelamentoRepository;
import com.api.oficina.service.ParcelamentoService;
import com.api.oficina.util.pagamento.CalculoParcelamentoSemJuros;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Service
public class ParcelamentoServiceImpl implements ParcelamentoService{
	
	public final ParcelamentoRepository PARCELAMENTO_REPOSITORY;
	private final OrdemServicoRepository ORDEM_SERVICO_REPOSITORY;
	private final Parcelas PARCELAS;

	public ParcelamentoServiceImpl(ParcelamentoRepository parcelamentoRepository, OrdemServicoRepository ordemServicoRepository, Parcelas parcelas) {
		this.PARCELAMENTO_REPOSITORY = parcelamentoRepository;
		this.ORDEM_SERVICO_REPOSITORY =  ordemServicoRepository;
		this.PARCELAS = parcelas;
	}

	@Override
	public List<Parcelamento> save(Long idOrdemServico, int numeroParcelas) {
		
		Optional<OrdemServico> ordemServico = this.ORDEM_SERVICO_REPOSITORY.findById(idOrdemServico);
		List<Parcelamento> listaParcelas = new ArrayList<Parcelamento>();
		
		List<Parcelamento> parcelasExistentes = this.PARCELAMENTO_REPOSITORY.listByIdOrdemServico(idOrdemServico);
		
		if(! ordemServico.isEmpty()) {
			if(parcelasExistentes.isEmpty()) {
				listaParcelas = this.calcularParcelamento(ordemServico.get(), numeroParcelas);
			}else {
				listaParcelas = this.atualizarParcelamento(idOrdemServico, numeroParcelas);
			}
		}else {
			throw new IllegalArgumentException();
		}
		
		
		return listaParcelas;
	}
	
	public List<Parcelamento> atualizarParcelamento(Long idOrdemServico, int quantidadeParcelas) {
		
		List<Parcelamento> parcelasExistentes = this.PARCELAMENTO_REPOSITORY.listByIdOrdemServico(idOrdemServico);
		
		OrdemServico ordemServico = parcelasExistentes.get(0).getOrdemServico();
				
		List<Parcelamento> novaListaParcelas = new ArrayList<Parcelamento>();
		
		int quantidadeParcelasPendentes = (int) parcelasExistentes.stream().filter(parcela -> parcela.getStatusParcela() != StatusParcela.PAGO).count();
		
		quantidadeParcelas =  quantidadeParcelas == 0 ? quantidadeParcelasPendentes : quantidadeParcelas;
		
		parcelasExistentes.stream()
			.filter(parcela -> parcela.getStatusParcela() != StatusParcela.PAGO)
			.forEach(this.PARCELAMENTO_REPOSITORY::delete);
		
		novaListaParcelas = calcularParcelamento(ordemServico, quantidadeParcelas);
		
		return novaListaParcelas;
	}
	
	public List<Parcelamento> calcularParcelamento(OrdemServico ordemServico, int numeroParcelas){
		List<Double> valorParcelas = this.PARCELAS.calcularParcela(ordemServico, new CalculoParcelamentoSemJuros(numeroParcelas));
		List<LocalDate> datasParcelas = this.PARCELAS.calcularDatas(LocalDate.now(), ordemServico.getTipoPagamento(), numeroParcelas);
		
		List<Parcelamento> listaParcelas = new ArrayList<Parcelamento>();
		
		for(int i = 0; i<numeroParcelas; i++) {
			Parcelamento parcela = new Parcelamento();
			parcela.setDataVencimento(datasParcelas.get(i));
			parcela.setValorParcela(valorParcelas.get(i));
			parcela.setStatusParcela(StatusParcela.PENDENTE);
			parcela.setOrdemServico(ordemServico);
			this.PARCELAMENTO_REPOSITORY.save(parcela);
			listaParcelas.add(parcela);
		}
		return listaParcelas;
	}
}