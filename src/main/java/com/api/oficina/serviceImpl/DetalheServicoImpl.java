package com.api.oficina.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.api.oficina.component.CalculoServicoPadrao;
import com.api.oficina.component.CalculoServicos;
import com.api.oficina.component.Invoice;
import com.api.oficina.model.DetalheServico;
import com.api.oficina.model.OrdemServico;
import com.api.oficina.repository.DetalheServicoRepository;
import com.api.oficina.repository.OrdemServicoRepository;
import com.api.oficina.service.CalculoServico;
import com.api.oficina.service.DetalheServicoService;

@Service
public class DetalheServicoImpl implements DetalheServicoService{
	
	private final DetalheServicoRepository servicoRepository;
	
	private final Invoice invoice;
	
	private final OrdemServicoRepository ordemServicoRepository;
	
	
	public DetalheServicoImpl(DetalheServicoRepository servicosRepository, OrdemServicoRepository ordemServicoRepository, Invoice invoice) {
		this.servicoRepository = servicosRepository;
		this.ordemServicoRepository = ordemServicoRepository;
		this.invoice = invoice;
	}

	@Override
	public List<DetalheServico> save(Long idOrdemServico, List<DetalheServico> servicos) {
		
		Optional<OrdemServico> ordemServico  = this.ordemServicoRepository.findById(idOrdemServico);
		
		double valorTotalAtual = ordemServico.get().getValorTotal();
		double newValorTotal = valorTotalAtual + this.invoice.calcularServico(servicos, new CalculoServicoPadrao(ordemServico.get().getVat()));
		
		ordemServico.get().setValorTotal(newValorTotal);
		
		for(DetalheServico d : servicos) {
			d.setOrdemServico(ordemServico.get());
			this.servicoRepository.save(d);
		}
		
		
		return servicos;
	}

}
