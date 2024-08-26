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

import com.api.oficina.model.Endereco;
import com.api.oficina.repository.EnderecoRepository;

@RestController
@RequestMapping(value = "/endereco")
public class EnderecoController {
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@GetMapping(value = "/")
	public ResponseEntity<List<Endereco>> listAll(){
		
		List<Endereco> lista = (List<Endereco>) enderecoRepository.findAll();
		
		return new ResponseEntity<List<Endereco>>(lista, HttpStatus.OK);
		
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<List<Endereco>> listById(@PathVariable (value = "id") Long id){
		
		List<Endereco> lista = enderecoRepository.listById(id);
		
		return new ResponseEntity<List<Endereco>>(lista, HttpStatus.OK);
	}
	
	@PostMapping(value = "/dono/{id}")
	public ResponseEntity<Endereco> addEndereco(@RequestBody Endereco endereco, @PathVariable(value = "id") Long id){

		List<Endereco> listaNomePessoa = enderecoRepository.listById(id);
		if(listaNomePessoa.isEmpty()) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}else {
			for(Endereco e : listaNomePessoa) {
				endereco.setPessoa(e.getPessoa());
			}
		}
		
		return ResponseEntity.ok().body(enderecoRepository.save(endereco));
	}
}
