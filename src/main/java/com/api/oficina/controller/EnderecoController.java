package com.api.oficina.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.oficina.model.Endereco;
import com.api.oficina.serviceImpl.EnderecoServiceImpl;

@RestController
@RequestMapping(value = "/oficina/pessoa/endereco")
public class EnderecoController {
	
	private final EnderecoServiceImpl ENDERECO_SERVICE;
	
	public EnderecoController(EnderecoServiceImpl enderecoService) {
		this.ENDERECO_SERVICE = enderecoService;
	}
	
	@GetMapping(value = "")
	public ResponseEntity<List<Endereco>> listAll(){
		
		return new ResponseEntity<List<Endereco>>(ENDERECO_SERVICE.listAll(), HttpStatus.OK);
		
	}
	
	@GetMapping(value = "/{idPessoa}")
	public ResponseEntity<List<Endereco>> listById(@PathVariable (value = "idPessoa") Long id){
		
		return new ResponseEntity<List<Endereco>>(ENDERECO_SERVICE.listById(id), HttpStatus.OK);
	}
	
	@PostMapping(value = "/pessoa/{idPessoa}")
	public ResponseEntity<Endereco> addEndereco(@RequestBody Endereco endereco, @PathVariable(value = "idPessoa") Long id){

		return new ResponseEntity<Endereco>(this.ENDERECO_SERVICE.save(endereco, id),HttpStatus.OK);
	}
	
	@PutMapping(value = "")
	public ResponseEntity<Endereco> update(@RequestBody Endereco endereco){
		
		return new ResponseEntity<Endereco>(this.ENDERECO_SERVICE.update(endereco),HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> delete(@PathVariable(value = "id") Long id){
		ENDERECO_SERVICE.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
