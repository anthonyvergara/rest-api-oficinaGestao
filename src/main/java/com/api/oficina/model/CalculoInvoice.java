package com.api.oficina.model;

import java.util.List;

public class CalculoInvoice {

	public static double calcularSaldoDevedor(OrdemServico ordemServico) {
		
		double valorTotal = CalculoInvoice.calcularServicos(ordemServico.getDetalheServico()) - CalculoInvoice.calcularPagamento(ordemServico.getPagamento());
		
		return valorTotal;
	}
	
	public static double calcularPagamento(List<Pagamento> pagamento){
		
		double valorTotal = 0;
		
		for(Pagamento p : pagamento) {
			
			valorTotal = valorTotal + p.getValorPago();
			
		}
		
		
		return valorTotal;
	}
	
	public static double calcularServicos(List<DetalheServico> servicos) {
		
		double valorTotal = 0;
		
		for(DetalheServico d : servicos) {
			valorTotal = valorTotal + d.getValor();
		}
		
		return valorTotal;
	}
	
	public static double proximaParcela(OrdemServico ordemServico) {
		
		
		
		return 0;
	}
}
