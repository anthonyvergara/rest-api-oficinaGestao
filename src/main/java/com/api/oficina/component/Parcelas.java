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
	
	private Map<LocalDate,Double> parcelas = new HashMap<LocalDate, Double>();
	
	private List<Parcelamento> listaParcelas = new ArrayList<Parcelamento>();
	
	public Map<LocalDate,Double> calcularParcelas(OrdemServico ordemServico, CalculoParcelamento calculoParcelamento){
		
		List<Double> valorParcelas = calculoParcelamento.calcularParcelamento(ordemServico);
		List<LocalDate> datas = this.calcularDatas(LocalDate.now(), ordemServico.getTipoPagamento(), ordemServico.getQuantidadeParcelas());
		
		for(int i = 0; i<valorParcelas.size(); i++) {
			parcelas.put(datas.get(i), valorParcelas.get(i));
		}
		
		return parcelas;
	}
	
	public List<Parcelamento> listarParcelas(Map<LocalDate,Double> parcelasCalculadas){
		
		
		List<Double> valorParcelas = parcelasCalculadas.values().stream()
				.sorted()
				.toList();
		List<LocalDate> dataParcelas = parcelasCalculadas.keySet().stream()
				.sorted()
				.toList();
	 	
	 	for(int i = 0; i<parcelasCalculadas.size(); i++) {
	 		Parcelamento parcelas = new Parcelamento(StatusParcela.PENDENTE, valorParcelas.get(i), dataParcelas.get(i));
	 		this.listaParcelas.add(parcelas);
	 	}
		
		return this.listaParcelas;
	}
	
	public List<LocalDate> calcularDatas (LocalDate dataParcela, TipoPagamento tipoPagamento, int quantidadeParcelas){
		
		List<LocalDate> datas = new ArrayList<LocalDate>();
		
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
