package com.api.oficina.service;

import com.api.oficina.model.StatusOrdemServico;

public interface StatusOrdemServicoService {
	
	public StatusOrdemServico criarStatusOS(Long idOrdemServico);
	public StatusOrdemServico atualizarStatusOS(StatusOrdemServico statusOS);
	
}
