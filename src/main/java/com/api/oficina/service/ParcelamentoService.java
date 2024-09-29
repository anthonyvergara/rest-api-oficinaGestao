package com.api.oficina.service;

import java.util.List;

import com.api.oficina.model.OrdemServico;
import com.api.oficina.model.Parcelamento;

public interface ParcelamentoService {
	public List<Parcelamento> criarParcelamento(Long idOrdemServico, int numeroParcelas);
	public List<Parcelamento> atualizarParcelamento(Long idOrdemServico, int numeroParcelas);
	
	public List<Parcelamento> listAll();
	public List<Parcelamento> findByIdOrdemServico(Long idOrdemServico);
}
