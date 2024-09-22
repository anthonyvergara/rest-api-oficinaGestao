package com.api.oficina.serviceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.api.oficina.component.Parcelas;
import com.api.oficina.model.OrdemServico;
import com.api.oficina.model.Parcelamento;
import com.api.oficina.modelEnum.StatusParcela;
import com.api.oficina.os.pagamento.CalculoParcelamentoSemJuros;
import com.api.oficina.repository.OrdemServicoRepository;
import com.api.oficina.repository.ParcelamentoRepository;
import com.api.oficina.service.ParcelamentoService;

@Service
public class ParcelamentoServiceImpl implements ParcelamentoService{
	
	public final ParcelamentoRepository PARCELAMENTO_REPOSITORY;
	public final OrdemServicoRepository ORDEM_SERVICO_REPOSITORY;
	public final Parcelas PARCELAS;

	public ParcelamentoServiceImpl(ParcelamentoRepository parcelamentoRepository, OrdemServicoRepository ordemServicoRepository, Parcelas parcelas) {
		this.PARCELAMENTO_REPOSITORY = parcelamentoRepository;
		this.ORDEM_SERVICO_REPOSITORY =  ordemServicoRepository;
		this.PARCELAS = parcelas;
	}

	@Override
	public List<Parcelamento> save(Long idOrdemServico, int numeroParcelas) {
		
		Optional<OrdemServico> ordemServico = this.ORDEM_SERVICO_REPOSITORY.findById(idOrdemServico);
		
		List<Parcelamento> parcelamento = this.PARCELAMENTO_REPOSITORY.listByIdOrdemServico(idOrdemServico);
		
		Parcelamento parcela;
		
		List<Double> valorParcelas = this.PARCELAS.calcularParcela(ordemServico.get(), new CalculoParcelamentoSemJuros(numeroParcelas));
		List<LocalDate> datasParcelas = this.PARCELAS.calcularDatas(LocalDate.now(), ordemServico.get().getTipoPagamento(), numeroParcelas);
		
		ordemServico.get().getStatusOrdemServico().setProximoVencimento(datasParcelas.get(0));
		ordemServico.get().getStatusOrdemServico().setValorProximaParcela(valorParcelas.get(0));
		
		if(parcelamento.size() > 0) {
			parcelamento.stream()
					.filter(value -> value.getStatusParcela() != StatusParcela.PAGO)
					.forEach(parcelas -> this.PARCELAMENTO_REPOSITORY.deleteById(parcelas.getId()));
		}
		
		for(int i = 0; i<numeroParcelas; i++) {
			parcela = new Parcelamento();
			parcela.setDataVencimento(datasParcelas.get(i));
			parcela.setValorParcela(valorParcelas.get(i));
			parcela.setStatusParcela(StatusParcela.PENDENTE);
			parcela.setOrdemServico(ordemServico.get());
			parcela = this.PARCELAMENTO_REPOSITORY.save(parcela);
			
			parcelamento.add(parcela);
		}
		
		return parcelamento;
	}
	
	
}
