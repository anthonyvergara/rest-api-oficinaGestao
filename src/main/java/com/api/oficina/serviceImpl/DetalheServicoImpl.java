package com.api.oficina.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.api.oficina.model.DetalheServico;
import com.api.oficina.model.OrdemServico;
import com.api.oficina.repository.DetalheServicoRepository;
import com.api.oficina.repository.OrdemServicoRepository;
import com.api.oficina.service.DetalheServicoService;

@Service
public class DetalheServicoImpl implements DetalheServicoService{
	
	private final DetalheServicoRepository servicoRepository;
	
	private final OrdemServicoImpl ordemServicoImpl;
	
	private final OrdemServicoRepository ordemServicoRepository;
	
	public DetalheServicoImpl(DetalheServicoRepository servicosRepository, OrdemServicoRepository ordemServicoRepository, OrdemServicoImpl ordemServicoImpl) {
		this.servicoRepository = servicosRepository;
		this.ordemServicoRepository = ordemServicoRepository;
		this.ordemServicoImpl = ordemServicoImpl;
	}
	
	@Override
	public List<DetalheServico> calcularServicos(Long ordemServicoID, List<DetalheServico> servicos) {
		
		Optional<OrdemServico> ordemServico = this.ordemServicoRepository.findById(ordemServicoID);
		
		double valorTotal = ordemServico.get().getValorTotal();
		
		if(ordemServico.isPresent()) {
			for(DetalheServico servico : servicos) {
				valorTotal = valorTotal + servico.getValor();
				servico.setOrdemServico(ordemServico.get());
				this.servicoRepository.save(servico);
			}
		}
		ordemServico.get().setValorTotal(valorTotal);
		this.ordemServicoImpl.update(ordemServico.get());
		
		return servicos;
	}

}
