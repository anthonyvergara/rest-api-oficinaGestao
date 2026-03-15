package com.api.oficina.util.servico;

import java.text.DecimalFormat;
import java.util.List;

import com.api.oficina.model.DetalheServico;
import com.api.oficina.model.OrdemServico;
import com.api.oficina.service.CalculoServico;
import com.api.oficina.util.FormataNumero;

public class CalculoServicoPadrao implements CalculoServico{
	
	private int vat;
	
	public CalculoServicoPadrao(int vat) {
		this.vat = vat;
	}
	
	@Override
	public double calcular(List<DetalheServico> detalheServico) {
		
		double valorTotal  = detalheServico.stream()
		.mapToDouble(value -> value.getValor() * value.getQuantidade())
		.sum();
		
		valorTotal = ((valorTotal * vat)/100) + valorTotal;
		
		valorTotal = Double.parseDouble(new FormataNumero().format(valorTotal));
		
		return valorTotal;
	}

}
