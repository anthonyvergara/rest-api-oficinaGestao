package com.api.oficina.component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.api.oficina.model.OrdemServico;
import com.api.oficina.modelEnum.TipoPagamento;
import com.api.oficina.service.CalculoParcelamento;

@Component
public class Parcelas {
	
	private Map<LocalDate,Double> parcelas = new HashMap<>();
	
	public Map<LocalDate,Double> calcularParcelas(OrdemServico ordemServico, CalculoParcelamento calculoParcelamento){
		
		List<Double> valorParcelas = calculoParcelamento.calcularParcelamento(ordemServico);
		List<LocalDate> datas = this.calcularDatas(null, ordemServico.getTipoPagamento(), ordemServico.getQuantidadeParcelas());
		
		for(int i = 0; i<valorParcelas.size(); i++) {
			parcelas.put(datas.get(i), valorParcelas.get(i));
		}
		
		return parcelas;
	}
	
	public List<LocalDate> calcularDatas (LocalDate dataParcela, TipoPagamento tipoPagamento, int quantidadeParcelas){
		
		List<LocalDate> datas = new ArrayList<>();
		
		int valorSomaData = tipoPagamento == TipoPagamento.SEMANAL ? 7 : 30;
		 
		if(dataParcela == null) {
			dataParcela = dataParcela.now().plusDays(valorSomaData);
		}else {
			
			for(int i = 0; i<quantidadeParcelas; i++) {
				
				dataParcela = dataParcela.getDayOfWeek() == DayOfWeek.SUNDAY ? dataParcela.plusDays(1) : dataParcela.plusDays(valorSomaData);
				datas.add(dataParcela);
			}
			
		}
		return datas;
	}

}
