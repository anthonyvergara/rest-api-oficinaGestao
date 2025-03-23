package com.api.oficina.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.api.oficina.component.Invoice;
import com.api.oficina.model.DetalheServico;
import com.api.oficina.model.OrdemServico;
import com.api.oficina.modelEnum.Status;
import com.api.oficina.repository.DetalheServicoRepository;
import com.api.oficina.repository.OrdemServicoRepository;
import com.api.oficina.service.DetalheServicoService;
import com.api.oficina.util.servico.CalculoServicoPadrao;

import jakarta.transaction.Transactional;

@Service
public class DetalheServicoImpl implements DetalheServicoService{
	
	private final DetalheServicoRepository DETALHE_SERVICO_REPOSITORY;
	
	private final OrdemServicoRepository ORDEM_SERVICO_REPOSITORY;
	private final StatusOrdemServicoImpl STATUS_ORDEM_SERVICO;
	private final ParcelaServiceImpl PARCELAMENTO_SERVICE;
	
	
	public DetalheServicoImpl(DetalheServicoRepository servicoRepository, OrdemServicoRepository ordemServicoRepository, 
			ParcelaServiceImpl parcelamentoService, StatusOrdemServicoImpl statusOrdemServico) {
		this.DETALHE_SERVICO_REPOSITORY = servicoRepository;
		this.ORDEM_SERVICO_REPOSITORY = ordemServicoRepository;
		this.PARCELAMENTO_SERVICE = parcelamentoService;
		this.STATUS_ORDEM_SERVICO = statusOrdemServico;
	}
	
	@Transactional
	@Override
	public List<DetalheServico> save(Long idOrdemServico, List<DetalheServico> servicos){
		Optional<OrdemServico> ordemServico  = Optional.of(this.ORDEM_SERVICO_REPOSITORY.findById(idOrdemServico)
				.orElseThrow(() -> new IllegalArgumentException("OrdemServico não existe!")));
		
		double valorTotal = ordemServico.get().getValorTotal();
		double valorTotalAtualizado = /*valorTotal */ Invoice.calcularServico(servicos, new CalculoServicoPadrao(ordemServico.get().getVat()));
		ordemServico.get().setValorTotal(valorTotalAtualizado);
		
		for(DetalheServico servico : servicos) {
			servico.setOrdemServico(ordemServico.get());
			this.DETALHE_SERVICO_REPOSITORY.save(servico);
		}
		
		verificarParcelamento(ordemServico.get());
		
		return servicos;
	}
	
	@Transactional
	@Override
	public List<DetalheServico> update(Long idOrdemServico, List<DetalheServico> servicos){
		Optional<OrdemServico> ordemServico  = Optional.of(this.ORDEM_SERVICO_REPOSITORY.findById(idOrdemServico)
				.orElseThrow(() -> new IllegalArgumentException("OrdemServico não existe!")));
		
		double valorTotal = ordemServico.get().getValorTotal();
		double valorTotalAtualizado = /*valorTotal */ Invoice.calcularServico(servicos, new CalculoServicoPadrao(ordemServico.get().getVat()));
		ordemServico.get().setValorTotal(valorTotalAtualizado);
		
		int i = 0;
		for(DetalheServico servico : servicos) {
			servico.setOrdemServico(ordemServico.get());
			servicos.set(i, this.DETALHE_SERVICO_REPOSITORY.save(servico));
			i++;
		}
		
		List<DetalheServico> detalheServico = this.DETALHE_SERVICO_REPOSITORY.listByIdOrdemServico(idOrdemServico);
		
		for(DetalheServico servico : detalheServico) {
			
			if(!servicos.contains(servico)) {
				this.DETALHE_SERVICO_REPOSITORY.delete(servico);
			}
			
		}
		
		verificarParcelamento(ordemServico.get());
		
		return servicos;
	}
	
	private void verificarParcelamento(OrdemServico ordemServico) {
		
		if(! ordemServico.getParcela().isEmpty() && ordemServico.getStatusOrdemServico().getTipoStatus() != Status.PAGO) {
			this.PARCELAMENTO_SERVICE.update(ordemServico.getId(), 0);
		}else {
			this.STATUS_ORDEM_SERVICO.update(ordemServico.getStatusOrdemServico());
		}
		/*else if(ordemServico.getParcelamento().isEmpty() && ordemServico.getQuantidadeParcelas() > 0) {
			this.PARCELAMENTO_SERVICE.criarParcela(ordemServico.getId(), ordemServico.getQuantidadeParcelas());
		}*/
		
	}

	@Override
	public List<DetalheServico> listByPlaca(String placa, Long idOrdemServico) {
		List<DetalheServico> listaServicos = this.DETALHE_SERVICO_REPOSITORY.listByPlaca(placa,idOrdemServico);
		return listaServicos;
	}

	@Override
	public List<DetalheServico> listByIdOrdemServico(Long idOrdemServico) {
		List<DetalheServico> listaServicos = this.DETALHE_SERVICO_REPOSITORY.listByIdOrdemServico(idOrdemServico);
		return listaServicos;
	}

}
