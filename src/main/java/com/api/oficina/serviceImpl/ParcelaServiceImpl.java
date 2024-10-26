package com.api.oficina.serviceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.api.oficina.component.Parcelas;
import com.api.oficina.model.OrdemServico;
import com.api.oficina.model.Parcela;
import com.api.oficina.modelEnum.Status;
import com.api.oficina.repository.OrdemServicoRepository;
import com.api.oficina.repository.ParcelaRepository;
import com.api.oficina.service.ParcelaService;
import com.api.oficina.util.parcela.CalculoParcelamentoSemJuros;


@Service
public class ParcelaServiceImpl implements ParcelaService{
	
	public final ParcelaRepository PARCELAMENTO_REPOSITORY;
	private final OrdemServicoRepository ORDEM_SERVICO_REPOSITORY;
	private final StatusOrdemServicoImpl STATUS_ORDEM_SERVICO;

	public ParcelaServiceImpl(ParcelaRepository parcelamentoRepository, OrdemServicoRepository ordemServicoRepository,
			StatusOrdemServicoImpl statusOrdemServico) {
		this.PARCELAMENTO_REPOSITORY = parcelamentoRepository;
		this.ORDEM_SERVICO_REPOSITORY =  ordemServicoRepository;
		this.STATUS_ORDEM_SERVICO = statusOrdemServico;
	}
	
	@Override
	public List<Parcela> listAll(){
		return (List<Parcela>) this.PARCELAMENTO_REPOSITORY.findAll();
	}
	
	@Override
	public List<Parcela> findByIdOrdemServico(Long idOrdemServico) {
		Optional<List<Parcela>> listaParcelamento = Optional.of(this.PARCELAMENTO_REPOSITORY.listByIdOrdemServico(idOrdemServico)
				.orElseThrow(() -> new IllegalArgumentException("OrdemServico não existe!")));
		
		return listaParcelamento.get();
	}
	
	public List<Parcela> save(Long idOrdemServico, int numeroParcelas){
		
		Optional<OrdemServico> ordemServico = Optional.of(this.ORDEM_SERVICO_REPOSITORY.findById(idOrdemServico)
				.orElseThrow(()-> new IllegalArgumentException("OrdemServico não existe!")));
		
		List<Parcela> listaParcelas = null;
		
		if(! ordemServico.get().getParcela().isEmpty()) {
			throw new RuntimeException("Já existem parcelas cadastradas nesta ordem de servico!");
		}else {
		
			listaParcelas = this.gerarParcelamento(ordemServico.get(), numeroParcelas);
		}
		
		return listaParcelas;
	}
	
	@Override
	public List<Parcela> update(Long idOrdemServico, int quantidadeParcelas) {
		
		Optional<List<Parcela>> parcelasExistentes = Optional.of(this.PARCELAMENTO_REPOSITORY.listByIdOrdemServico(idOrdemServico)
				.orElseThrow(() -> new IllegalArgumentException("Não existem parcelas para serem atualizadas!")));
		
		List<Parcela> novaListaParcelas = new ArrayList<Parcela>();
		
		OrdemServico ordemServico = parcelasExistentes.get().get(0).getOrdemServico();
		
		int quantidadeParcelasPendentes = (int) parcelasExistentes.get().stream().filter(parcela -> parcela.getStatusParcela() != Status.PAGO).count();
		
		quantidadeParcelas =  quantidadeParcelas == 0 ? quantidadeParcelasPendentes : quantidadeParcelas;
		
		parcelasExistentes.get().stream()
			.filter(parcela -> parcela.getStatusParcela() != Status.PAGO)
			.forEach(this.PARCELAMENTO_REPOSITORY::delete);
		
		novaListaParcelas = gerarParcelamento(ordemServico, quantidadeParcelas);
		
		return novaListaParcelas;
	}
	
	private List<Parcela> gerarParcelamento(OrdemServico ordemServico, int numeroParcelas){
		List<Double> valorParcelas = Parcelas.calcularParcela(ordemServico, new CalculoParcelamentoSemJuros(numeroParcelas));
		List<LocalDate> datasParcelas = Parcelas.calcularDatas(LocalDate.now(), ordemServico.getTipoPagamento(), numeroParcelas);
		
		List<Parcela> listaParcelas = new ArrayList<Parcela>();
		
		for(int i = 0; i<numeroParcelas; i++) {
			Parcela parcela = new Parcela();
			parcela.setDataVencimento(datasParcelas.get(i));
			parcela.setValorParcela(valorParcelas.get(i));
			parcela.setStatusParcela(Status.AGENDADO);
			parcela.setOrdemServico(ordemServico);
			this.PARCELAMENTO_REPOSITORY.save(parcela);
			listaParcelas.add(parcela);
		}
		ordemServico.setParcela(listaParcelas);
		
		this.STATUS_ORDEM_SERVICO.update(ordemServico.getStatusOrdemServico());
		
		return listaParcelas;
	}
}