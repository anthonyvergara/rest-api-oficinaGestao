package com.api.oficina.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.oficina.model.StatusOrdemServico;
import com.api.oficina.service.StatusOrdemServicoService;

@RestController
@RequestMapping(value = "/oficina/status")
@CrossOrigin(origins = "http://localhost:4200")
public class StatusOrdemServicoController {
	
	private StatusOrdemServicoService STATUS_ORDEM_SERVICO_SERVICE;
	
	public StatusOrdemServicoController(StatusOrdemServicoService statusOrdemServico) {
		this.STATUS_ORDEM_SERVICO_SERVICE = statusOrdemServico;
	}
	
	@GetMapping(value = "/{idOrdemServico}")
	public ResponseEntity<StatusOrdemServico> listById(@PathVariable(value = "idOrdemServico") Long id){
		
		return new ResponseEntity<StatusOrdemServico>(this.STATUS_ORDEM_SERVICO_SERVICE.listById(id), HttpStatus.OK);
	}

}
