package com.api.oficina.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.api.oficina.component.Parcelas;
import com.api.oficina.model.OrdemServico;
import com.api.oficina.model.Pagamento;
import com.api.oficina.model.Parcela;
import com.api.oficina.model.StatusOrdemServico;
import com.api.oficina.repository.OrdemServicoRepository;
import com.api.oficina.repository.PagamentoRepository;
import com.api.oficina.service.PagamentoService;
import com.api.oficina.util.parcela.CalculoParcelamentoSemJuros;

import jakarta.transaction.Transactional;

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
	
	@Transactional
	@Override
	public List<Pagamento> save(Long idOrdemServico, List<Pagamento> pagamentos) {
		Optional<OrdemServico> ordemServico = Optional.of(this.ORDEM_SERVICO_REPOSITORY.findById(idOrdemServico)
				.orElseThrow(()-> new IllegalArgumentException("OrdemServico não existe!")));
		
		pagamentos.removeIf(valor -> valor.getValorPago() == 0);
		
		double saldoDevedor = ordemServico.get().getStatusOrdemServico().getSaldoDevedor();
		double valorTotalPagamentos = pagamentos.stream().mapToDouble(Pagamento::getValorPago).sum();
		
		if(valorTotalPagamentos > saldoDevedor) {
			throw new IllegalArgumentException("O valor total pago não pode ser maior que o saldo devedor!");
		}
		
		pagamentos.forEach(pagamento -> {
			pagamento.setDataPagamento(LocalDateTime.now());
			pagamento.setOrdemServico(ordemServico.get());
			this.PAGAMENTO_REPOSITORY.save(pagamento);
			ordemServico.get().getPagamento().add(pagamento);
		});
		
		if(!ordemServico.get().getParcela().isEmpty()) {
			ordemServico.get().setParcela(Parcelas.debitarValorDaParcela(ordemServico.get(), pagamentos, new CalculoParcelamentoSemJuros(0)));
		}
		this.STATUS_ORDEM_SERVICO.update(ordemServico.get().getStatusOrdemServico());
		
		return pagamentos;
	}
	
}
