package com.api.oficina.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.api.oficina.serviceImpl.ClienteServiceImpl;

@RestController
@RequestMapping(value = "/oficina/cliente")
@CrossOrigin(origins = "http://localhost:4200")
public class ClienteController {
	
	private final ClienteServiceImpl CLIENTE_SERVICE;
	
	public ClienteController(ClienteServiceImpl clienteService) {
		this.CLIENTE_SERVICE = clienteService;
	}
	
	@GetMapping(value = "")
	public ResponseEntity<List<ClienteDTO>> listAll(){
		return new ResponseEntity<List<ClienteDTO>>(this.CLIENTE_SERVICE.listAll(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO>findById(@PathVariable(value = "id") Long id){
		
		return new ResponseEntity<ClienteDTO>(this.CLIENTE_SERVICE.findById(id),HttpStatus.OK);
	}
	
	@PostMapping(value = "/{idOficina}")
	public ResponseEntity<ClienteDTO> save(@RequestBody Cliente cliente, @PathVariable(value = "idOficina") Long id){
		return new ResponseEntity<ClienteDTO>(this.CLIENTE_SERVICE.save(cliente, id),HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> delete(@PathVariable(value = "id") Long id){
		this.CLIENTE_SERVICE.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping(value = "")
	public ResponseEntity<ClienteDTO> update(@RequestBody Cliente cliente){
		return new ResponseEntity<ClienteDTO>(this.CLIENTE_SERVICE.update(cliente),HttpStatus.OK);
	}
}
