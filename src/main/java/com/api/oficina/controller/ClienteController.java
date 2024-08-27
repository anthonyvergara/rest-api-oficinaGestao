package com.api.oficina.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.oficina.model.Cliente;
import com.api.oficina.service.ClienteService;

@RestController
@RequestMapping(value = "/cliente")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping(value = "")
	public ResponseEntity<List<Cliente>> listAll(){
		return new ResponseEntity<List<Cliente>>(this.clienteService.listAll(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Cliente>findById(@PathVariable(value = "id") Long id){
		
		return new ResponseEntity<Cliente>(this.clienteService.findById(id),HttpStatus.OK);
	}
	
	@PostMapping(value = "")
	public ResponseEntity<Cliente> save(@RequestBody Cliente cliente){
		return new ResponseEntity<Cliente>(this.clienteService.save(cliente),HttpStatus.OK);
	}
}
