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
	public StatusOrdemServico listById(Long idOrdemServico) {
		
		Optional<StatusOrdemServico> statusOrdemServico = Optional.ofNullable(this.STATUS_SERVICO_REPOSITORY.findByIdOrdemServico(idOrdemServico));

		if (statusOrdemServico == null || !statusOrdemServico.isPresent()) {
		    throw new IllegalArgumentException("Status de Ordem de Serviço não encontrado");
		}

		return statusOrdemServico.get();
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
		
		// VERIFICA SE EXISTEM PARCELAMENTOS PARA ATUALIZAR O STATUS
		if(! ordemServico.getParcela().isEmpty())	{
			
			ordemServico.setStatusOrdemServico(this.atualizaProximoVencimento(ordemServico));
			
			ordemServico.setStatusOrdemServico(this.atualizaValorProximaParcela(ordemServico));
			
			ordemServico.setParcela(this.atualizarStatusParcelas(ordemServico));
			
			parcelaAtrasada = ordemServico.getParcela().stream()
			.anyMatch(parcela -> parcela.getStatusParcela() == Status.ATRASADO);
			// PRECISO INCLUR CONDICIONAL PARA VERIFICAR PARCELA ATRASADA E MUDAR SEU STATUS ANTES DE PASSAR POR ESTE STREAM
			
		}
		
		double valorTotalOrdemServico = ordemServico.getValorTotal();
		double valorTotalPagamento = 0;
		
		// VERIFICA SE EXISTEm PAGAMENTOS PARA ATUALIZAR O ULTIMO PAGAMENTO E SALDO DEVEDOR
		if(! ordemServico.getPagamento().isEmpty()) {
			Optional<LocalDateTime> ultimoPagamento = ordemServico.getPagamento().stream()
					.filter(valores -> valores.getValorPago() > 0)
					.map(Pagamento::getDataPagamento)
					.max(Comparator.naturalOrder());
			
			if(ultimoPagamento.isPresent()) {
				statusOS.setUltimoPagamento(ultimoPagamento.get());
			}
			
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
	
	private StatusOrdemServico atualizaProximoVencimento(OrdemServico ordemServico) {
		StatusOrdemServico status = ordemServico.getStatusOrdemServico();
		
		Optional<LocalDate> proximoVencimento = ordemServico.getParcela().stream()
				.filter(parcela -> parcela.getStatusParcela() == Status.AGENDADO)
				.map(Parcela::getDataVencimento)
				.min(Comparator.naturalOrder());
		
		if(proximoVencimento.isPresent()) {
			status.setProximoVencimento(proximoVencimento.get());
		}else {
			status.setProximoVencimento(null);
		}
			
		return status;
	}
	
	private StatusOrdemServico atualizaValorProximaParcela (OrdemServico ordemServico) {
		StatusOrdemServico status = ordemServico.getStatusOrdemServico();
		
		Optional<Double> valorProximaParcela = ordemServico.getParcela().stream() // LISTA A PROXIMA PARCELA IGNORANDO AS PAGAS E ATRASADAS
				.filter(parcela -> parcela.getStatusParcela() == Status.AGENDADO)
				.map(Parcela::getValorParcela)
				.min(Comparator.naturalOrder());
		
		if(valorProximaParcela.isPresent()) {
			status.setValorProximaParcela(valorProximaParcela.get());
		}else {
			status.setValorProximaParcela(0.0);
		}
		
		return status;
	}
	
	private List<Parcela> atualizarStatusParcelas(OrdemServico ordemServico) {
		List<Parcela> parcelas = ordemServico.getParcela();
		
		LocalDate dataAtual = LocalDate.now();
		
		parcelas.forEach(parcela -> {
			if(parcela.getDataVencimento().isBefore(dataAtual) && parcela.getStatusParcela() != Status.PAGO) {
				parcela.setStatusParcela(Status.ATRASADO);
			}
		});
		
		return parcelas;
	}
	
}
