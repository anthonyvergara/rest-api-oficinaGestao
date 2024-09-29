package com.api.oficina.serviceImpl;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.api.oficina.model.OrdemServico;
import com.api.oficina.model.Pagamento;
import com.api.oficina.model.StatusOrdemServico;
import com.api.oficina.modelEnum.StatusOS;
import com.api.oficina.modelEnum.StatusParcela;
import com.api.oficina.repository.OrdemServicoRepository;
import com.api.oficina.repository.PagamentoRepository;
import com.api.oficina.repository.StatusOrdemServicoRepository;
import com.api.oficina.service.StatusOrdemServicoService;

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
		
		int quantidadeParcelaAtrasada = 0;
		
		statusOS.setSaldoDevedor(saldoDevedor);
		
		if(! ordemServico.getParcelamento().isEmpty()) {
			quantidadeParcelaAtrasada = (int) ordemServico.getParcelamento().stream()
				.filter(parcela -> parcela.getStatusParcela() == StatusParcela.ATRASADO)
				.count();
		}
		
		if((valorTotal - valoresPagos) == 0) {
			ordemServico.getStatusOrdemServico().setTipoStatus(StatusOS.PAGO.getCode());
		}else if(quantidadeParcelaAtrasada > 0) {
			ordemServico.getStatusOrdemServico().setTipoStatus(StatusOS.ATRASADO.getCode());
		}else {
			ordemServico.getStatusOrdemServico().setTipoStatus(StatusOS.AGENDADO.getCode());
		}
		
		//statusOS.setTipoStatus(null);
		//statusOS.setValorProximaParcela(0);
		
		return null;
	}
	
	
	
}
