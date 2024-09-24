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

import com.api.oficina.dto.ClienteDTO;
import com.api.oficina.model.Cliente;
import com.api.oficina.service.ClienteService;

@RestController
@RequestMapping(value = "/oficina/cliente")
public class ClienteController {
	
	private ClienteService clienteService;
	
	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}
	
	@GetMapping(value = "")
	public ResponseEntity<List<ClienteDTO>> listAll(){
		return new ResponseEntity<List<ClienteDTO>>(this.clienteService.listAll(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO>findById(@PathVariable(value = "id") Long id){
		
		return new ResponseEntity<ClienteDTO>(this.clienteService.findById(id),HttpStatus.OK);
	}
	
	@PostMapping(value = "/{idOficina}")
	public ResponseEntity<ClienteDTO> save(@RequestBody Cliente cliente, @PathVariable(value = "idOficina") Long id){
		return new ResponseEntity<ClienteDTO>(this.clienteService.save(cliente, id),HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> delete(@PathVariable(value = "id") Long id){
		this.clienteService.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping(value = "")
	public ResponseEntity<ClienteDTO> update(@RequestBody Cliente cliente){
		return new ResponseEntity<ClienteDTO>(this.clienteService.update(cliente),HttpStatus.OK);
	}
}
