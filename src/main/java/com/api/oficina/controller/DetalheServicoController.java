package com.api.oficina.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.oficina.model.DetalheServico;
import com.api.oficina.serviceImpl.DetalheServicoImpl;

@RestController
@RequestMapping(value = "/detalheservico")
public class DetalheServicoController {

	private final DetalheServicoImpl detalheServicoService;
	
	public DetalheServicoController(DetalheServicoImpl detalheServicoService) {
		this.detalheServicoService = detalheServicoService;
	}
	
	@PostMapping(value = "/{idOrdemServico}")
	public ResponseEntity<List<DetalheServico>> save(@RequestBody List<DetalheServico> servicos, @PathVariable(value = "idOrdemServico") Long id){
		return new ResponseEntity<List<DetalheServico>>(this.detalheServicoService.save(id, servicos),HttpStatus.OK);
	}
}