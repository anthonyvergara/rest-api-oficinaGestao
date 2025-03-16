package com.api.oficina.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.oficina.model.DetalheServico;
import com.api.oficina.serviceImpl.DetalheServicoImpl;

@RestController
@RequestMapping(value = "/oficina/detalheServico")
public class DetalheServicoController {

	private final DetalheServicoImpl DETALHE_SERVICO_SERVICE;
	
	public DetalheServicoController(DetalheServicoImpl detalheServicoService) {
		this.DETALHE_SERVICO_SERVICE = detalheServicoService;
	}
	
	@PostMapping(value = "/{idOrdemServico}")
	public ResponseEntity<List<DetalheServico>> save(@RequestBody List<DetalheServico> servicos, @PathVariable(value = "idOrdemServico") Long id){
		return new ResponseEntity<List<DetalheServico>>(this.DETALHE_SERVICO_SERVICE.save(id ,servicos),HttpStatus.OK);
	}
	
	@GetMapping(value = "/placa/{placa}/ordemServico/{idOrdemservico}")
	public ResponseEntity<List<DetalheServico>> listByPlaca(@PathVariable(value = "placa") String placa, @PathVariable(value = "idOrdemservico") Long idOrdemservico){
		return new ResponseEntity<List<DetalheServico>>(this.DETALHE_SERVICO_SERVICE.listByPlaca(placa,idOrdemservico),HttpStatus.OK);
	}
	
	@GetMapping(value = "/ordemServico/{idOrdemServico}")
	public ResponseEntity<List<DetalheServico>> listByIdOrdemServico(@PathVariable(value = "idOrdemServico")Long idOrdemServico){
		return new ResponseEntity<List<DetalheServico>>(this.DETALHE_SERVICO_SERVICE.listByIdOrdemServico(idOrdemServico),HttpStatus.OK);
	}
	
	@PutMapping(value = "/{idOrdemServico}")
	public ResponseEntity<List<DetalheServico>> update(@RequestBody List<DetalheServico> servicos, @PathVariable(value = "idOrdemServico") Long id){
		return new ResponseEntity<List<DetalheServico>>(this.DETALHE_SERVICO_SERVICE.save(id, servicos), HttpStatus.OK);
	}
}
