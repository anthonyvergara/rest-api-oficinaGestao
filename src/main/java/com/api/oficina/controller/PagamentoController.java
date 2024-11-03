package com.api.oficina.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.oficina.model.Pagamento;
import com.api.oficina.service.PagamentoService;


@RestController
@RequestMapping(value = "/oficina/pagamento")
public class PagamentoController {
	
	private final PagamentoService PAGAMENTO_SERVICE;
	
	public PagamentoController(PagamentoService pagamentoService) {
		this.PAGAMENTO_SERVICE = pagamentoService;
	}
	
	@PostMapping(value = "/ordemServico/{idOrdemServico}")
	public ResponseEntity<List<Pagamento>> save(@RequestBody List<Pagamento> pagamento, @PathVariable(value = "idOrdemServico") Long idOrdemServico){
		return new ResponseEntity<List<Pagamento>>(this.PAGAMENTO_SERVICE.save(idOrdemServico, pagamento),HttpStatus.OK);
	}

}
