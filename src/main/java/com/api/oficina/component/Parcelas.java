package com.api.oficina.component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.util.comparator.Comparators;

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
		
		List<Double> valorParcelaAtualizado = calculoParcelamento.debitarValorDaParcela(ordemServico, pagamento);
		
		List<Parcela> parcelasPagas = ordemServico.getParcela().stream()
				.filter(parcela -> parcela.getStatusParcela() == Status.PAGO)
				.collect(Collectors.toCollection(ArrayList::new));
		
		List<Parcela> parcelasNaoPagas = ordemServico.getParcela().stream()
				.filter(parcela -> parcela.getStatusParcela() != Status.PAGO)
				.collect(Collectors.toCollection(ArrayList::new));
		
		List<Parcela> parcelasAtualizadas = parcelasPagas;
		
		for(int i = 0; i<valorParcelaAtualizado.size(); i++) {
			
			if(valorParcelaAtualizado.get(i) != 0.0) {
				parcelasNaoPagas.get(i).setValorParcela(valorParcelaAtualizado.get(i));
			}else {
				parcelasNaoPagas.get(i).setStatusParcela(Status.PAGO);
			}
			parcelasAtualizadas.add(parcelasNaoPagas.get(i));
		}
		
		return parcelasAtualizadas;
	}

}
