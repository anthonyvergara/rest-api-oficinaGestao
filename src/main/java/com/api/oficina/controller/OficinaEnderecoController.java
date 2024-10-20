package com.api.oficina.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.oficina.model.OficinaEndereco;
import com.api.oficina.serviceImpl.OficinaEnderecoImpl;

@RestController
@RequestMapping(value = "/oficina/endereco")
public class OficinaEnderecoController {
	
	private final OficinaEnderecoImpl OFICINA_ENDERECO_SERVICE;
	
	public OficinaEnderecoController(OficinaEnderecoImpl oficinaEndereco) {
		this.OFICINA_ENDERECO_SERVICE = oficinaEndereco;
	}
	
	@GetMapping
	public ResponseEntity<List<OficinaEndereco>> listAll(){
		return new ResponseEntity<List<OficinaEndereco>>(this.OFICINA_ENDERECO_SERVICE.listAll(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/{idOficina}")
	public ResponseEntity<OficinaEndereco> findById(@PathVariable(value = "idOficina") Long id){
		return new ResponseEntity<OficinaEndereco>(this.OFICINA_ENDERECO_SERVICE.getInfoEndereco(id),HttpStatus.OK);
	}
	
	@PutMapping(value = "")
	public ResponseEntity<OficinaEndereco> update(@RequestBody OficinaEndereco endereco){
		return new ResponseEntity<OficinaEndereco>(this.OFICINA_ENDERECO_SERVICE.update(endereco), HttpStatus.OK);
	}
	
	@PostMapping(value = "/{idOficina}")
	public ResponseEntity<OficinaEndereco> save(@RequestBody OficinaEndereco endereco,
												@PathVariable(value = "idOficina") Long id ){
		return new ResponseEntity<OficinaEndereco>(this.OFICINA_ENDERECO_SERVICE.save(endereco, id), HttpStatus.OK);
	}

}
