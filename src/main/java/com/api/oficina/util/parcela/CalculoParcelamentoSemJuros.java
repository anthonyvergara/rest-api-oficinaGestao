package com.api.oficina.util.parcela;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.api.oficina.model.OrdemServico;
import com.api.oficina.model.Pagamento;
import com.api.oficina.model.Parcela;
import com.api.oficina.modelEnum.Status;
import com.api.oficina.service.CalculoParcelamento;
import com.api.oficina.util.FormataNumero;

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
			
			valorParcela = Double.parseDouble(new FormataNumero().format(valorParcela));
			
			this.listaParcelas.add(valorParcela);
		}
		
		return listaParcelas;
	}
	
	public List<Double> debitarValorDaParcela(OrdemServico ordemServico, List<Pagamento> pagamento){
		ordemServico.getParcela().sort(null);
		
		 List<Double> parcelasNaoPagas = ordemServico.getParcela().stream()
				.filter(parcela -> parcela.getStatusParcela() != Status.PAGO)
				.map(Parcela::getValorParcela)
				.collect(Collectors.toCollection(ArrayList::new));
		 
		 double restoPagamento = pagamento.stream().mapToDouble(Pagamento::getValorPago).sum();
		 int i = 0;
		 
		 while(restoPagamento > 0) {
			 //Verifica se valor da parcela é menor que o valor pago.
			 if(parcelasNaoPagas.size() == i) {
				 break;
			 }
			 if(parcelasNaoPagas.get(i) < restoPagamento) {
				 restoPagamento = restoPagamento - parcelasNaoPagas.get(i);
				 parcelasNaoPagas.set(i, 0.0);
				 i++;
				 continue;
			 }
			 //Verifica se o valor da parcela é igual ao valor pago.
			 if(parcelasNaoPagas.get(i) == restoPagamento) {
				 restoPagamento = restoPagamento - parcelasNaoPagas.get(i);
				 parcelasNaoPagas.set(i, 0.0);
				 i++;
				 continue;
			 }
			 //Verifica se o valor da parcela é maior que o valor pago.
			 if(parcelasNaoPagas.get(i) > restoPagamento) {
				 parcelasNaoPagas.set(i, parcelasNaoPagas.get(i) - restoPagamento);
				 restoPagamento = 0;
				 break;
			 }
			 i++;
		 }
		 
		 return parcelasNaoPagas;
	}
	
}
