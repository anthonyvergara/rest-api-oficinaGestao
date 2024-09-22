package com.api.oficina.service;

import java.util.List;

import com.api.oficina.model.Pagamento;

public interface PagamentoService {
	
	public List<Pagamento> salvar(List<Pagamento> pagamentos, Long idOrdemServico);

}
