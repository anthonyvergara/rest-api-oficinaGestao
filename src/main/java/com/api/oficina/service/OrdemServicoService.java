package com.api.oficina.service;

import java.util.List;

import com.api.oficina.model.OrdemServico;

public interface OrdemServicoService {
	
	public List<OrdemServico> listAll();
	public List<OrdemServico> listAllByIdOficina(Long idOficina);
	public OrdemServico listById(Long id);
	public OrdemServico save(OrdemServico ordemServico, Long idCliente, Long idOficina);
	public OrdemServico update(OrdemServico ordemServico);
	public OrdemServico updateFields(OrdemServico ordemServico, Long idCliente, Long idOficina);
	public void delete(Long id);
	
}
