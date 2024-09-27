package com.api.oficina.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.oficina.component.Invoice;
import com.api.oficina.model.DetalheServico;
import com.api.oficina.model.OrdemServico;
import com.api.oficina.model.Parcelamento;
import com.api.oficina.repository.DetalheServicoRepository;
import com.api.oficina.repository.OrdemServicoRepository;
import com.api.oficina.service.DetalheServicoService;
import com.api.oficina.util.servico.CalculoServicoPadrao;

import jakarta.transaction.Transactional;

@Service
public class DetalheServicoImpl implements DetalheServicoService{
	
	private final DetalheServicoRepository DETALHE_SERVICO_REPOSITORY;
	
	private final Invoice INVOICE;
	
	private final OrdemServicoRepository ORDEM_SERVICO_REPOSITORY;
	
	private final ParcelamentoServiceImpl PARCELAMENTO_SERVICE;
	
	
	public DetalheServicoImpl(DetalheServicoRepository servicosRepository, OrdemServicoRepository ordemServicoRepository, Invoice invoice, 
			ParcelamentoServiceImpl parcelamentoService) {
		this.DETALHE_SERVICO_REPOSITORY = servicosRepository;
		this.ORDEM_SERVICO_REPOSITORY = ordemServicoRepository;
		this.INVOICE = invoice;
		this.PARCELAMENTO_SERVICE = parcelamentoService;
	}

	@Override
	@Transactional
	public List<DetalheServico> save(Long idOrdemServico, List<DetalheServico> servicos) {
		
		Optional<OrdemServico> ordemServico  = this.ORDEM_SERVICO_REPOSITORY.findById(idOrdemServico);
		
		double valorTotal = ordemServico.get().getValorTotal();
		double valorTotalAtualizado = valorTotal +  this.INVOICE.calcularServico(servicos, new CalculoServicoPadrao(ordemServico.get().getVat()));
		ordemServico.get().setValorTotal(valorTotalAtualizado);
		
		for(DetalheServico servico : servicos) {
			servico.setOrdemServico(ordemServico.get());
			this.DETALHE_SERVICO_REPOSITORY.save(servico);
		}
		
		// PARCELAS
		if (ordemServico.get().getQuantidadeParcelas() > 0){
			this.PARCELAMENTO_SERVICE.save(ordemServico.get().getId(), 4);
		}
		
		
		//
		
		return servicos;
	}

}
