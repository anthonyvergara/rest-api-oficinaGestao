package com.api.oficina.serviceImpl;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.api.oficina.model.OrdemServico;
import com.api.oficina.model.StatusOrdemServico;
import com.api.oficina.modelEnum.StatusOS;
import com.api.oficina.modelEnum.StatusParcela;
import com.api.oficina.repository.OrdemServicoRepository;
import com.api.oficina.repository.StatusOrdemServicoRepository;
import com.api.oficina.service.StatusOrdemServicoService;

@Service
public class StatusOrdemServicoImpl implements StatusOrdemServicoService{

	private final StatusOrdemServicoRepository STATUS_SERVICO_REPOSITORY;
	private final OrdemServicoRepository ORDEM_SERVICO_REPOSITORY;
	
	
	public StatusOrdemServicoImpl(StatusOrdemServicoRepository statusServicoRepository, OrdemServicoRepository ordemServicoRepository) {
		this.STATUS_SERVICO_REPOSITORY = statusServicoRepository;
		this.ORDEM_SERVICO_REPOSITORY = ordemServicoRepository;
	}
	
	@Override
	public StatusOrdemServico save(Long idOrdemServico) {
		return null;
	}

}
