package com.api.oficina.os.servico;

import java.util.List;

import com.api.oficina.model.DetalheServico;
import com.api.oficina.model.OrdemServico;
import com.api.oficina.service.CalculoServico;

public class CalculoServicoPadrao implements CalculoServico{
	
	private int vat;
	
	public CalculoServicoPadrao(int vat) {
		this.vat = vat;
	}
	
	@Override
	public double calcular(List<DetalheServico> detalheServico) {
		
		double valorTotal  = detalheServico.stream()
		.mapToDouble(value -> value.getValor())
		.sum();
		
		valorTotal = ((valorTotal * vat)/100) + valorTotal;
		
		return valorTotal;
	}

}
