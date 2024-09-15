package com.api.oficina.component;

import org.springframework.stereotype.Service;

import com.api.oficina.model.DetalheServico;
import com.api.oficina.model.OrdemServico;

@Service
public class CalculoServicos {

	
	public OrdemServico calcularServicos(OrdemServico ordemServico) {
		
		double valorTotal = ordemServico.getValorTotal();
		
		for(DetalheServico d : ordemServico.getDetalheServico()) {
			
			valorTotal = valorTotal + d.getValor();
			
		}
		
		ordemServico.setValorTotal(valorTotal);
		//provavelmente preciso de um repository ordemservico para salvar
		
		return ordemServico;
	}
	
}
