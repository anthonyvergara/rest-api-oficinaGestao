package com.api.oficina.component;

import java.util.List;

import org.springframework.stereotype.Component;

import com.api.oficina.model.DetalheServico;
import com.api.oficina.service.CalculoServico;

public class Invoice {
	
	public static double calcularServico(List<DetalheServico> detalheServico, CalculoServico calculoServico) {
		
		double valorTotal = calculoServico.calcular(detalheServico);
		
		return valorTotal;
	}
}
