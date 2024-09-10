package com.api.oficina.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.oficina.model.OrdemServico;
import com.api.oficina.serviceImpl.OrdemServicoImpl;

@RestController
@RequestMapping(value = "/ordemservico")
public class OrdemServicoController {

	private final OrdemServicoImpl ordemServico;
	
	public OrdemServicoController(OrdemServicoImpl ordemServico) {
		this.ordemServico = ordemServico;
	}
	
	@GetMapping
	public ResponseEntity<List<OrdemServico>> listAll(){
		return new ResponseEntity<List<OrdemServico>>(this.ordemServico.listAll(),HttpStatus.OK);
	}
	
	@PostMapping(value = "/cliente/{idCliente}/oficina/{idOficina}")
	public ResponseEntity<OrdemServico> save(@RequestBody OrdemServico ordemServico, 
											@PathVariable(value = "idCliente") Long idCliente,
											@PathVariable(value = "idOficina") Long idOficina){
		
		return new ResponseEntity<OrdemServico>(this.ordemServico.save(ordemServico, idCliente, idOficina),HttpStatus.OK);
	}
	
}
