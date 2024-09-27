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
import com.api.oficina.model.Parcelamento;
import com.api.oficina.modelEnum.StatusParcela;
import com.api.oficina.modelEnum.TipoPagamento;
import com.api.oficina.service.CalculoParcelamento;

@Component
public class Parcelas {
	
	public List<Double> calcularParcela(OrdemServico ordemServico, CalculoParcelamento calculoParcelamento){
		
		List<Double> valorParcelas = calculoParcelamento.calcularParcelamento(ordemServico);
		
		return valorParcelas;
	}
	
	public List<LocalDate> calcularDatas (LocalDate dataPrimeiraParcela, TipoPagamento tipoPagamento, int quantidadeParcelas){
		
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

}
