package com.api.oficina.serviceImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.api.oficina.model.OrdemServico;
import com.api.oficina.model.Pagamento;
import com.api.oficina.model.Parcela;
import com.api.oficina.model.StatusOrdemServico;
import com.api.oficina.modelEnum.Status;
import com.api.oficina.repository.OrdemServicoRepository;
import com.api.oficina.repository.PagamentoRepository;
import com.api.oficina.repository.StatusOrdemServicoRepository;
import com.api.oficina.service.StatusOrdemServicoService;
import com.fasterxml.jackson.annotation.JacksonInject.Value;

@Service
public class StatusOrdemServicoImpl implements StatusOrdemServicoService{

	private final StatusOrdemServicoRepository STATUS_SERVICO_REPOSITORY;
	private final OrdemServicoRepository ORDEM_SERVICO_REPOSITORY;
	private final PagamentoRepository PAGAMENTO_REPOSITORY;
	
	
	
	public StatusOrdemServicoImpl(StatusOrdemServicoRepository statusServicoRepository, OrdemServicoRepository ordemServicoRepository, PagamentoRepository pagamentoRepository) {
		this.STATUS_SERVICO_REPOSITORY = statusServicoRepository;
		this.ORDEM_SERVICO_REPOSITORY = ordemServicoRepository;
		this.PAGAMENTO_REPOSITORY = pagamentoRepository;
	}
	
	@Override
	public StatusOrdemServico save(Long idOrdemServico) {
		
		Optional<OrdemServico> ordemServico = Optional.of(this.ORDEM_SERVICO_REPOSITORY.findById(idOrdemServico)
				.orElseThrow(()-> new IllegalArgumentException("Ordem Servico não existe!")));
		
		StatusOrdemServico statusOS = new StatusOrdemServico();
		
		statusOS.setOrdemServico(ordemServico.get());
		
		statusOS = this.update(statusOS);
		
		return statusOS;
	}
	
	@Override
	public StatusOrdemServico update(StatusOrdemServico statusOS) {
		OrdemServico ordemServico = statusOS.getOrdemServico();
		
		boolean parcelaAtrasada = false;
		
		// VERIFICA SE EXISTE PARCELAMENTOS PARA ATUALIZAR O STATUS
		if(! ordemServico.getParcela().isEmpty())	{
			Optional<LocalDate> proximoVencimento = ordemServico.getParcela().stream()
					.filter(parcela -> parcela.getStatusParcela() != Status.PAGO)
					.map(Parcela::getDataVencimento)
					.min(Comparator.naturalOrder());
			
			statusOS.setProximoVencimento(proximoVencimento.get());
			
			Optional<Double> valorProximaParcela = ordemServico.getParcela().stream()
					.filter(parcela -> parcela.getStatusParcela() != Status.PAGO)
					.map(Parcela::getValorParcela)
					.min(Comparator.naturalOrder());
			
			statusOS.setValorProximaParcela(valorProximaParcela.get());
			
			
			parcelaAtrasada = ordemServico.getParcela().stream()
			.anyMatch(parcela -> parcela.getStatusParcela() == Status.ATRASADO);
		}
		
		double valorTotalOrdemServico = ordemServico.getValorTotal();
		double valorTotalPagamento = 0;
		
		// VERIFICA SE EXISTE PAGAMENTOS PARA ATUALIZAR O ULTIMO PAGAMENTO E SALDO DEVEDOR
		if(! ordemServico.getPagamento().isEmpty()) {
			Optional<LocalDateTime> ultimoPagamento = ordemServico.getPagamento().stream()
					.map(Pagamento::getDataPagamento)
					.min(Comparator.naturalOrder());
			
			statusOS.setUltimoPagamento(ultimoPagamento.get());
			
			valorTotalPagamento = ordemServico.getPagamento().stream()
					.mapToDouble(valor -> valor.getValorPago())
					.sum();
		}
		double saldoDevedor = valorTotalOrdemServico - valorTotalPagamento;
		
		// VERIFICA CONDIÇOES PARA DEFINITIR O TIPO DE STATUS
		
		Status status = saldoDevedor == 0 ? Status.PAGO : parcelaAtrasada == true? Status.ATRASADO : Status.AGENDADO;
		
		statusOS.setSaldoDevedor(saldoDevedor);
		statusOS.setTipoStatus(status.getCode());
		statusOS.setOrdemServico(ordemServico);
		
		this.STATUS_SERVICO_REPOSITORY.save(statusOS);
				
		return statusOS;
	}
	
}
