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
@RequestMapping(value = "/endereco")
public class EnderecoController {
	
	@Autowired
	private EnderecoServiceImpl enderecoService;
	
	@GetMapping(value = "")
	public ResponseEntity<List<Endereco>> listAll(){
		
		return new ResponseEntity<List<Endereco>>(enderecoService.listAll(), HttpStatus.OK);
		
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<List<Endereco>> listById(@PathVariable (value = "id") Long id){
		
		return new ResponseEntity<List<Endereco>>(enderecoService.listById(id), HttpStatus.OK);
	}
	
	@PostMapping(value = "/dono/{id}")
	public ResponseEntity<Endereco> addEndereco(@RequestBody Endereco endereco, @PathVariable(value = "id") Long id){

		return new ResponseEntity<Endereco>(this.enderecoService.save(endereco, id),HttpStatus.OK);
	}
	
	@PutMapping(value = "")
	public ResponseEntity<Endereco> update(@RequestBody Endereco endereco){
		
		return new ResponseEntity<Endereco>(this.enderecoService.update(endereco),HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> delete(@PathVariable(value = "id") Long id){
		enderecoService.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
