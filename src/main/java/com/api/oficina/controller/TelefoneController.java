package com.api.oficina.controller;

import java.util.List;
import java.util.Optional;

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

import com.api.oficina.model.Telefone;
import com.api.oficina.repository.TelefoneRepository;
import com.api.oficina.service.TelefoneService;

@RestController
@RequestMapping(value = "/oficina/pessoa/telefone")
public class TelefoneController {
	
	@Autowired
	private TelefoneService telefoneService;
	
	@GetMapping(value = "")
	public ResponseEntity<List<Telefone>> listAll(){
		return new ResponseEntity<List<Telefone>>(this.telefoneService.listAll(),HttpStatus.OK);
	}
	
	@GetMapping(value = "{idPessoa}")
	public ResponseEntity<List<Telefone>> listById(@PathVariable(value = "idPessoa") Long idPessoa){
		return new ResponseEntity<List<Telefone>>(this.telefoneService.listById(idPessoa),HttpStatus.OK);
	}

	@PostMapping(value = "/pessoa/{idPessoa}")
	public ResponseEntity<Telefone> save(@RequestBody Telefone Telefone, @PathVariable(value = "idPessoa") Long idPessoa){
		return new ResponseEntity<Telefone>(this.telefoneService.save(Telefone, idPessoa), HttpStatus.OK);
	}
	
	@PutMapping(value = "")
	public ResponseEntity<Telefone> update(@RequestBody Telefone telefone){
		return new ResponseEntity<Telefone>(this.telefoneService.update(telefone),HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> delete(@PathVariable(value = "id") Long id){
		this.telefoneService.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
}
