package com.api.oficina.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.api.oficina.component.Invoice;
import com.api.oficina.model.DetalheServico;
import com.api.oficina.model.OrdemServico;
import com.api.oficina.os.servico.CalculoServicoPadrao;
import com.api.oficina.repository.DetalheServicoRepository;
import com.api.oficina.repository.OrdemServicoRepository;
import com.api.oficina.service.CalculoServico;
import com.api.oficina.service.DetalheServicoService;

@Service
public class DetalheServicoImpl implements DetalheServicoService{
	
	private final DetalheServicoRepository DETALHE_SERVICO_REPOSITORY;
	
	private final Invoice INVOICE;
	
	private final OrdemServicoRepository ORDEM_SERVICO_REPOSITORY;
	
	
	public DetalheServicoImpl(DetalheServicoRepository servicosRepository, OrdemServicoRepository ordemServicoRepository, Invoice invoice) {
		this.DETALHE_SERVICO_REPOSITORY = servicosRepository;
		this.ORDEM_SERVICO_REPOSITORY = ordemServicoRepository;
		this.INVOICE = invoice;
	}

	@Override
	public List<DetalheServico> save(Long idOrdemServico, List<DetalheServico> servicos) {
		
		Optional<OrdemServico> ordemServico  = this.ORDEM_SERVICO_REPOSITORY.findById(idOrdemServico);
		
		double valorTotalAtual = ordemServico.get().getValorTotal();
		double newValorTotal = valorTotalAtual + this.INVOICE.calcularServico(servicos, new CalculoServicoPadrao(ordemServico.get().getVat()));
		
		ordemServico.get().setValorTotal(newValorTotal);
		
		for(DetalheServico d : servicos) {
			d.setOrdemServico(ordemServico.get());
			this.DETALHE_SERVICO_REPOSITORY.save(d);
		}
		
		
		return servicos;
	}

}
