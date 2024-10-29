package com.api.oficina.component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.api.oficina.model.OrdemServico;
import com.api.oficina.model.Pagamento;
import com.api.oficina.model.Parcela;
import com.api.oficina.modelEnum.Status;
import com.api.oficina.modelEnum.TipoPagamento;
import com.api.oficina.service.CalculoParcelamento;

public class Parcelas {
	
	public static List<Double> calcularParcela(OrdemServico ordemServico, CalculoParcelamento calculoParcelamento){
		
		List<Double> valorParcelas = calculoParcelamento.calcularParcelamento(ordemServico);
		
		return valorParcelas;
	}
	
	public static List<LocalDate> calcularDatas (LocalDate dataPrimeiraParcela, TipoPagamento tipoPagamento, int quantidadeParcelas){
		
		List<LocalDate> datas = new ArrayList<LocalDate>();
		
		int valorSomaData = tipoPagamento == TipoPagamento.SEMANAL ? 7 : 30;
		 
		if(dataPrimeiraParcela == null) {
			dataPrimeiraParcela = dataPrimeiraParcela.now().plusDays(valorSomaData);
		}else {
			
			for(int i = 0; i<quantidadeParcelas; i++) {
				
				dataPrimeiraParcela = dataPrimeiraParcela.getDayOfWeek() == DayOfWeek.SUNDAY ? dataPrimeiraParcela.plusDays(1) : dataPrimeiraParcela.plusDays(valorSomaData);
				datas.add(dataPrimeiraParcela);
			}
			
		}
		return datas;
	}
	
	public static List<Parcela> debitarValorDaParcela(OrdemServico ordemServico, List<Pagamento> pagamento ,CalculoParcelamento calculoParcelamento){
		List<Double> valorNovasParcelas = calculoParcelamento.debitarValorDaParcela(ordemServico, pagamento);
		
		List<Parcela>parcelasAtuais = ordemServico.getParcela();
		
		List<Parcela>novasParcelas = ordemServico.getParcela();
		
		for(int i = 0; i<valorNovasParcelas.size(); i++) {
			if(parcelasAtuais.get(i).getStatusParcela() != Status.PAGO) {
				if(valorNovasParcelas.get(i) == 0) {
					novasParcelas.get(i).setStatusParcela(Status.PAGO);
					continue;
				}
				if(valorNovasParcelas.get(i) < parcelasAtuais.get(i).getValorParcela()) {
					novasParcelas.get(i).setValorParcela(valorNovasParcelas.get(i));
				}
			}
		}
		
		return novasParcelas;
	}

}
