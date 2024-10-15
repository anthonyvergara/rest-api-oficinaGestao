package com.api.oficina.service;

import java.util.List;

import com.api.oficina.model.OrdemServico;
import com.api.oficina.model.Parcela;

public interface ParcelaService {
	public List<Parcela> save(Long idOrdemServico, int numeroParcelas);
	public List<Parcela> update(Long idOrdemServico, int numeroParcelas);
	
	public List<Parcela> listAll();
	public List<Parcela> findByIdOrdemServico(Long idOrdemServico);
}
