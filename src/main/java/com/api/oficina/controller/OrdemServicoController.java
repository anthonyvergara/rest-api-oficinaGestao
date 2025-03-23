package com.api.oficina.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.oficina.model.OrdemServico;
import com.api.oficina.serviceImpl.OrdemServicoImpl;

import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping(value = "/oficina/ordemServico")
public class OrdemServicoController {

	private final OrdemServicoImpl ORDEM_SERVICO;
	
	public OrdemServicoController(OrdemServicoImpl ordemServico) {
		this.ORDEM_SERVICO = ordemServico;
	}
	
	@GetMapping("")
	public ResponseEntity<List<OrdemServico>> listAll(){
		return new ResponseEntity<List<OrdemServico>>(this.ORDEM_SERVICO.listAll(),HttpStatus.OK);
	}
	
	@GetMapping("/oficina/{idOficina}")
	public ResponseEntity<List<OrdemServico>> listAll(@PathVariable(value ="idOficina") Long idOficina){
		return new ResponseEntity<List<OrdemServico>>(this.ORDEM_SERVICO.listAllByIdOficina(idOficina),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<OrdemServico> listById(@PathVariable(value="id") Long id){
		return new ResponseEntity<OrdemServico>(this.ORDEM_SERVICO.listById(id),HttpStatus.OK);
	}
	
	@GetMapping("/cliente/{idCliente}")
	public ResponseEntity<List<OrdemServico>> findByIdCliente(@PathVariable(value ="idCliente") Long idCliente){
		return new ResponseEntity<List<OrdemServico>>(this.ORDEM_SERVICO.listByIdCliente(idCliente),HttpStatus.OK);
	}
	
	@PostMapping(value = "/cliente/{idCliente}/oficina/{idOficina}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<OrdemServico> save(@RequestBody @Valid OrdemServico ordemServico, 
											@PathVariable(value = "idCliente") Long idCliente,
											@PathVariable(value = "idOficina") Long idOficina) throws Exception{
		
		return new ResponseEntity<OrdemServico>(this.ORDEM_SERVICO.save(ordemServico, idCliente, idOficina),HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> delete(@PathVariable(value = "id") Long id){
		this.ORDEM_SERVICO.delete(id);
		return ResponseEntity.ok().build();
	}
	
//	@DeleteMapping
//	public ResponseEntity<String> deleteAll(){
//		this.ORDEM_SERVICO.deleteAll();
//		return ResponseEntity.ok().build();
//	}
	
}
