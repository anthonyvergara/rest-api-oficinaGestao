package com.api.oficina.serviceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.api.oficina.component.Parcelas;
import com.api.oficina.model.OrdemServico;
import com.api.oficina.model.Parcelamento;
import com.api.oficina.modelEnum.StatusParcela;
import com.api.oficina.repository.OrdemServicoRepository;
import com.api.oficina.repository.ParcelamentoRepository;
import com.api.oficina.service.ParcelamentoService;
import com.api.oficina.util.pagamento.CalculoParcelamentoSemJuros;


@Service
public class ParcelamentoServiceImpl implements ParcelamentoService{
	
	public final ParcelamentoRepository PARCELAMENTO_REPOSITORY;
	private final OrdemServicoRepository ORDEM_SERVICO_REPOSITORY;
	private final StatusOrdemServicoImpl STATUS_ORDEM_SERVICO;
	private final Parcelas PARCELAS;

	public ParcelamentoServiceImpl(ParcelamentoRepository parcelamentoRepository, OrdemServicoRepository ordemServicoRepository, Parcelas parcelas, 
			StatusOrdemServicoImpl statusOrdemServico) {
		this.PARCELAMENTO_REPOSITORY = parcelamentoRepository;
		this.ORDEM_SERVICO_REPOSITORY =  ordemServicoRepository;
		this.PARCELAS = parcelas;
		this.STATUS_ORDEM_SERVICO = statusOrdemServico;
	}
	
	@Override
	public List<Parcelamento> listAll(){
		return (List<Parcelamento>) this.PARCELAMENTO_REPOSITORY.findAll();
	}
	
	@Override
	public List<Parcelamento> findByIdOrdemServico(Long idOrdemServico) {
		List<Parcelamento> listaParcelamento = this.PARCELAMENTO_REPOSITORY.listByIdOrdemServico(idOrdemServico);
		
		return listaParcelamento;
	}

	@Override
	public List<Parcelamento> criarParcelamento(Long idOrdemServico, int numeroParcelas) {
		
		Optional<OrdemServico> ordemServico = this.ORDEM_SERVICO_REPOSITORY.findById(idOrdemServico);
		
		List<Parcelamento> listaParcelas = new ArrayList<Parcelamento>();
		
		if(! ordemServico.isEmpty()) {
			List<Parcelamento> parcelasExistentes = this.PARCELAMENTO_REPOSITORY.listByIdOrdemServico(idOrdemServico);
			
			
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
	
	@Override
	public List<Parcelamento> atualizarParcelamento(Long idOrdemServico, int quantidadeParcelas) {
		
		List<Parcelamento> parcelasExistentes = this.PARCELAMENTO_REPOSITORY.listByIdOrdemServico(idOrdemServico);
		
		List<Parcelamento> novaListaParcelas = new ArrayList<Parcelamento>();
		
		if(! parcelasExistentes.isEmpty()) {
			OrdemServico ordemServico = parcelasExistentes.get(0).getOrdemServico();
			
			int quantidadeParcelasPendentes = (int) parcelasExistentes.stream().filter(parcela -> parcela.getStatusParcela() != StatusParcela.PAGO).count();
			
			quantidadeParcelas =  quantidadeParcelas == 0 ? quantidadeParcelasPendentes : quantidadeParcelas;
			
			if (quantidadeParcelas == 0){
				return null;
			}
			System.out.println("passou sem parcelas");
			
			parcelasExistentes.stream()
				.filter(parcela -> parcela.getStatusParcela() != StatusParcela.PAGO)
				.forEach(this.PARCELAMENTO_REPOSITORY::delete);
			
			novaListaParcelas = calcularParcelamento(ordemServico, quantidadeParcelas);
		}else {
			throw new IllegalArgumentException();
		}
		
		return novaListaParcelas;
	}
	
	private List<Parcelamento> calcularParcelamento(OrdemServico ordemServico, int numeroParcelas){
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
		
		this.STATUS_ORDEM_SERVICO.atualizarStatusOS(ordemServico.getStatusOrdemServico());
		
		return listaParcelas;
	}
}