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
	
	public PagamentoServiceImpl(OrdemServicoRepository ordemServicoRepository, PagamentoRepository pagamentoRepository) {
		this.PAGAMENTO_REPOSITORY = pagamentoRepository;
		this.ORDEM_SERVICO_REPOSITORY = ordemServicoRepository;
	}

	@Override
	public List<Pagamento> salvar(List<Pagamento> pagamentos, Long idOrdemServico) {
		
		Optional<OrdemServico> ordemServico = this.ORDEM_SERVICO_REPOSITORY.findById(idOrdemServico);
		
		double valorTotal = ordemServico.get().getValorTotal();
		double valorPagamento = pagamentos.stream().mapToDouble(valor -> valor.getValorPago()).sum();
		ordemServico.get().setValorTotal(valorTotal - valorPagamento);
		
		if(pagamentos.get(0).getValorPago() > 0) {
			//ordemServico.get().getStatusOrdemServico().setUltimoPagamento(LocalDateTime.now());
		}
		
		pagamentos.forEach(pagamento -> {
			pagamento.setOrdemServico(ordemServico.get());
			this.PAGAMENTO_REPOSITORY.save(pagamento);
		});
		
		return pagamentos;
	}
	
}
