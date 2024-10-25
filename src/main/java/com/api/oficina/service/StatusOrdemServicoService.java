package com.api.oficina.service;

import com.api.oficina.model.OrdemServico;
import com.api.oficina.model.StatusOrdemServico;

public interface StatusOrdemServicoService {
	
	public StatusOrdemServico save(Long idOrdemServico);
	public StatusOrdemServico update(StatusOrdemServico statusOS);
	
}
