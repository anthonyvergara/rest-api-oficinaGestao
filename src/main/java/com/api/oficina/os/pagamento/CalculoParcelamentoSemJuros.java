package com.api.oficina.os.pagamento;

import java.util.ArrayList;
import java.util.List;

import com.api.oficina.model.OrdemServico;
import com.api.oficina.model.Parcelamento;
import com.api.oficina.service.CalculoParcelamento;

public class CalculoParcelamentoSemJuros implements CalculoParcelamento{

	private List<Double> listaParcelas = new ArrayList<Double>();
	
	private final int NUMERO_PARCELAS;
	
	public CalculoParcelamentoSemJuros(int numeroParcelas) {
		this.NUMERO_PARCELAS = numeroParcelas;
	}
	
	@Override
	public List<Double> calcularParcelamento(OrdemServico ordemServico) {
		
		double valorTotal = ordemServico.getValorTotal();
		double valoresPagos = ordemServico.getPagamento().stream()
				.mapToDouble(value -> value.getValorPago())
				.sum();
		
		for(int i = 0; i< NUMERO_PARCELAS; i++) {
			double valorParcela = (valorTotal - valoresPagos)/NUMERO_PARCELAS;
			this.listaParcelas.add(valorParcela);
		}
		
		return listaParcelas;
	}
	
	
	
}
