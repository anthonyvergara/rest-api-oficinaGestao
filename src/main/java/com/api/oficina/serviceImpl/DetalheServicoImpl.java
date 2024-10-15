package com.api.oficina.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.api.oficina.component.Invoice;
import com.api.oficina.model.DetalheServico;
import com.api.oficina.model.OrdemServico;
import com.api.oficina.modelEnum.StatusOS;
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
	private final StatusOrdemServicoImpl STATUS_ORDEM_SERVICO;
	private final ParcelaServiceImpl PARCELAMENTO_SERVICE;
	
	
	public DetalheServicoImpl(DetalheServicoRepository servicosRepository, OrdemServicoRepository ordemServicoRepository, Invoice invoice, 
			ParcelaServiceImpl parcelamentoService, StatusOrdemServicoImpl statusOrdemServico) {
		this.DETALHE_SERVICO_REPOSITORY = servicosRepository;
		this.ORDEM_SERVICO_REPOSITORY = ordemServicoRepository;
		this.INVOICE = invoice;
		this.PARCELAMENTO_SERVICE = parcelamentoService;
		this.STATUS_ORDEM_SERVICO = statusOrdemServico;
	}
	
	@Transactional
	@Override
	public List<DetalheServico> save(Long idOrdemServico, List<DetalheServico> servicos){
		Optional<OrdemServico> ordemServico  = Optional.of(this.ORDEM_SERVICO_REPOSITORY.findById(idOrdemServico)
				.orElseThrow(() -> new IllegalArgumentException("OrdemServico não existe!")));
		
		double valorTotal = ordemServico.get().getValorTotal();
		double valorTotalAtualizado = valorTotal +  this.INVOICE.calcularServico(servicos, new CalculoServicoPadrao(ordemServico.get().getVat()));
		ordemServico.get().setValorTotal(valorTotalAtualizado);
		
		for(DetalheServico servico : servicos) {
			servico.setOrdemServico(ordemServico.get());
			this.DETALHE_SERVICO_REPOSITORY.save(servico);
		}
		
		verificarParcelamento(ordemServico.get());
		
		return servicos;
	}
	
	private void verificarParcelamento(OrdemServico ordemServico) {
		
		if(! ordemServico.getParcela().isEmpty() && ordemServico.getStatusOrdemServico().getTipoStatus() != StatusOS.PAGO) {
			this.PARCELAMENTO_SERVICE.update(ordemServico.getId(), 0);
		}else {
			this.STATUS_ORDEM_SERVICO.atualizarStatus(ordemServico, ordemServico.getStatusOrdemServico());
		}
		/*else if(ordemServico.getParcelamento().isEmpty() && ordemServico.getQuantidadeParcelas() > 0) {
			this.PARCELAMENTO_SERVICE.criarParcela(ordemServico.getId(), ordemServico.getQuantidadeParcelas());
		}*/
		
	}

}
