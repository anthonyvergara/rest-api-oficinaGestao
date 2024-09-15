package com.api.oficina.service;

import java.util.List;

import com.api.oficina.model.DetalheServico;

public interface CalculoServico {
	public double calcular(List<DetalheServico> detalheServico);
}
