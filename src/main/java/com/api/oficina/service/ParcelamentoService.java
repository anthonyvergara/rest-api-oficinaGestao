package com.api.oficina.service;

import java.util.List;

import com.api.oficina.model.OrdemServico;
import com.api.oficina.model.Parcelamento;

public interface ParcelamentoService {
	public List<Parcelamento> save(Long idOrdemServico, int numeroParcelas);
}
