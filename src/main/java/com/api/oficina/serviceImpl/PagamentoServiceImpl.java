package com.api.oficina.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.api.oficina.model.OrdemServico;
import com.api.oficina.model.Pagamento;
import com.api.oficina.model.StatusOrdemServico;
import com.api.oficina.repository.OrdemServicoRepository;
import com.api.oficina.repository.PagamentoRepository;
import com.api.oficina.service.PagamentoService;

@Service
public class PagamentoServiceImpl implements PagamentoService{
	
	
	private final PagamentoRepository PAGAMENTO_REPOSITORY;
	private final OrdemServicoRepository ORDEM_SERVICO_REPOSITORY;
	private final StatusOrdemServicoImpl STATUS_ORDEM_SERVICO;
	
	public PagamentoServiceImpl(OrdemServicoRepository ordemServicoRepository, PagamentoRepository pagamentoRepository,
			StatusOrdemServicoImpl statusOrdemServico) {
		this.PAGAMENTO_REPOSITORY = pagamentoRepository;
		this.ORDEM_SERVICO_REPOSITORY = ordemServicoRepository;
		this.STATUS_ORDEM_SERVICO = statusOrdemServico;
	}

	@Override
	public List<Pagamento> salvar(List<Pagamento> pagamentos, Long idOrdemServico) {
		
		Optional<OrdemServico> ordemServico = this.ORDEM_SERVICO_REPOSITORY.findById(idOrdemServico);
		
		pagamentos.forEach(pagamento -> {
			pagamento.setDataPagamento(LocalDateTime.now());
			pagamento.setOrdemServico(ordemServico.get());
			this.PAGAMENTO_REPOSITORY.save(pagamento);
		});
		
		this.STATUS_ORDEM_SERVICO.atualizarStatusOS(ordemServico.get().getStatusOrdemServico());
		
		return pagamentos;
	}
	
}
