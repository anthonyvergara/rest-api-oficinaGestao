package com.api.oficina.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.oficina.model.Telefone;
import com.api.oficina.repository.TelefoneRepository;

@RestController
@RequestMapping(value = "telefone")
public class TelefoneController {
	
	@Autowired
	private TelefoneRepository telefoneRepository;
	
	@GetMapping(value = "/")
	public ResponseEntity<List<Telefone>> listTelefones(){
		
		List<Telefone> lista = (List<Telefone>) telefoneRepository.findAll();
		
		return new ResponseEntity<List<Telefone>>(lista, HttpStatus.OK);
		
	}
	
	
	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<List<Telefone>> listById(@PathVariable (value = "id") Long id){
		
		List<Telefone> lista = telefoneRepository.listName(id);
		
		return new ResponseEntity<List<Telefone>>(lista, HttpStatus.OK);
	}
	
}
