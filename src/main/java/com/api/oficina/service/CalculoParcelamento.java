package com.api.oficina.service;

import java.util.List;

import com.api.oficina.model.OrdemServico;
import com.api.oficina.model.Pagamento;

public interface CalculoParcelamento {
	public List<Double> calcularParcelamento(OrdemServico ordemServico);
	public List<Double> debitarValorDaParcela(OrdemServico ordemServico, List<Pagamento> pagamento);
}
