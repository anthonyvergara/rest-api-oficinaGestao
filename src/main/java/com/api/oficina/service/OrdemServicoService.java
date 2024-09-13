package com.api.oficina.service;

import java.util.List;

import com.api.oficina.model.OrdemServico;

public interface OrdemServicoService {
	
	public List<OrdemServico> listAll();
	public OrdemServico save(OrdemServico ordemServico, Long idCliente, Long idOficina);
	public OrdemServico update(OrdemServico ordemServico);
	public void delete(Long id);
	
}
