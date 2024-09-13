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
	
	private final CalculoServicos calculoServicos;
	
	public DetalheServicoImpl(DetalheServicoRepository servicosRepository, OrdemServicoRepository ordemServicoRepository, OrdemServicoImpl ordemServicoImpl,
			CalculoServicos calculoServicos) {
		this.servicoRepository = servicosRepository;
		this.ordemServicoRepository = ordemServicoRepository;
		this.ordemServicoImpl = ordemServicoImpl;
		this.calculoServicos = calculoServicos;
	}

	@Override
	public List<DetalheServico> save(Long idOrdemServico, List<DetalheServico> servicos) {
		
		Optional<OrdemServico> ordemServico  = this.ordemServicoRepository.findById(idOrdemServico);
		ordemServico.get().setDetalheServico(servicos);

		for(DetalheServico d : servicos) {
			d.setOrdemServico(ordemServico.get());
			this.servicoRepository.save(d);
		}
		
		OrdemServico newOrdem = ordemServico.get();
		newOrdem = this.calculoServicos.calcularServicos(ordemServico.get());
		
		this.ordemServicoImpl.update(newOrdem);
		
		return servicos;
	}

}
