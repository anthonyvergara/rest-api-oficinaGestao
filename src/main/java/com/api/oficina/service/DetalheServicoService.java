package com.api.oficina.service;

import java.util.List;

import com.api.oficina.model.DetalheServico;
import com.api.oficina.model.OrdemServico;

public interface DetalheServicoService {

	public List<DetalheServico> save(Long idOrdemServico, List<DetalheServico> servicos);
	
	
	
}
