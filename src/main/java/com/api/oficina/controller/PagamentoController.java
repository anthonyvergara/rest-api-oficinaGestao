package com.api.oficina.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

	@GetMapping(value = "/ordemServico/{idOrdemServico}")
	public ResponseEntity<List<Pagamento>> findByIdOrdemServico(@PathVariable(value = "idOrdemServico") Long idOrdemServico){
		return new ResponseEntity<List<Pagamento>>(this.PAGAMENTO_SERVICE.findByIdOrdemServico(idOrdemServico),HttpStatus.OK);
	}

}
