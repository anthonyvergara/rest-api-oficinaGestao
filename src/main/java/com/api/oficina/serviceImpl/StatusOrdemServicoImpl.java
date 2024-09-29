package com.api.oficina.serviceImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.api.oficina.model.OrdemServico;
import com.api.oficina.model.Pagamento;
import com.api.oficina.model.Parcelamento;
import com.api.oficina.model.StatusOrdemServico;
import com.api.oficina.modelEnum.StatusOS;
import com.api.oficina.modelEnum.StatusParcela;
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
	public StatusOrdemServico criarStatusOS(Long idOrdemServico) {
		
		Optional<OrdemServico> ordemServico = this.ORDEM_SERVICO_REPOSITORY.findById(idOrdemServico);
		
		StatusOrdemServico statusOS = new StatusOrdemServico();
		statusOS.setOrdemServico(ordemServico.get());
		statusOS.setTipoStatus(StatusOS.AGENDADO.getCode());
		this.STATUS_SERVICO_REPOSITORY.save(statusOS);
		
		return statusOS;
	}

	@Override
	public StatusOrdemServico atualizarStatusOS(StatusOrdemServico statusOS) {
		
		OrdemServico ordemServico = statusOS.getOrdemServico();
		
		List<Pagamento> pagamentos = ordemServico.getPagamento() != null ? ordemServico.getPagamento() : null;
		
		int parcelaAtrasada = 0;
		
		if(!pagamentos.isEmpty()) {
			Optional<LocalDateTime> ultimoPagamento = pagamentos.stream()
					.map(Pagamento::getDataPagamento)
					.min(Comparator.naturalOrder());
			
			statusOS.setUltimoPagamento(ultimoPagamento.get());
		}
		//statusOS.setProximoVencimento(null);
		
		double valoresPagos = ordemServico.getPagamento().stream().mapToDouble(valor -> valor.getValorPago()).sum();
		double valorTotal = ordemServico.getValorTotal();
		double saldoDevedor = valorTotal - valoresPagos;
		
		statusOS.setSaldoDevedor(saldoDevedor);
		
		if(! ordemServico.getParcelamento().isEmpty()) {
			StatusOS status = checarStatus(statusOS, ordemServico.getParcelamento());
			statusOS.setTipoStatus(status.getCode());
		}
		
		//statusOS.setTipoStatus(null);
		//statusOS.setValorProximaParcela(0);
		
		this.STATUS_SERVICO_REPOSITORY.save(statusOS);
		
		return statusOS;
	}
	
	private StatusOS checarStatus(StatusOrdemServico statusOS, List<Parcelamento> parcelamento) {
		StatusOS status = null;
		
		parcelamento = parcelamento.stream()
				.filter(parcela -> parcela.getStatusParcela() != StatusParcela.PAGO)
				.toList();
		
		int parcelasAtrasadas = 0;
		parcelasAtrasadas = (int)parcelamento.stream()
				.filter(parcela -> parcela.getDataVencimento().isBefore(LocalDate.now()))
				.count();
		
		if(statusOS.getSaldoDevedor() == 0) {
			status = StatusOS.PAGO;
		}else if(parcelasAtrasadas > 0) {
			status = StatusOS.ATRASADO;
		}else {
			status = StatusOS.AGENDADO;
		}
		
		return status;
	}
	
}
