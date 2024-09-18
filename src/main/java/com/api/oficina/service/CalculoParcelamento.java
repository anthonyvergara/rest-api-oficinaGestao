package com.api.oficina.service;

import java.util.List;

import com.api.oficina.model.OrdemServico;

public interface CalculoParcelamento {
	public List<Double> calcularParcelamento(OrdemServico ordemServico);
}
