package com.api.oficina.service;

import java.util.List;

import com.api.oficina.model.DetalheServico;
import com.api.oficina.model.OrdemServico;

public interface DetalheServicoService {

	public List<DetalheServico> save(Long idOrdemServico, List<DetalheServico> servicos);
	public List<DetalheServico> listByPlaca(String placa, Long idOrdemServico);
	public List<DetalheServico> listByIdOrdemServico(Long idOrdemServico);
	public List<DetalheServico> update(Long idOrdemServico, List<DetalheServico> servicos);
	
}
