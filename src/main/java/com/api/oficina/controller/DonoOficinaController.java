package com.api.oficina.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.oficina.model.DonoOficina;
import com.api.oficina.repository.DonoOficinaRepository;
import com.oficina.model.Usuario;

@RestController
@RequestMapping(value = "/dono")
public class DonoOficinaController {
	
	@Autowired
	private DonoOficinaRepository donoOficinaRepo;

	
	@GetMapping(value = "/", produces = "application/json")
	public ResponseEntity<List<DonoOficina>> listarUsuarios(){
		
		List<DonoOficina> user = (List<DonoOficina>) donoOficinaRepo.findAll();
		
		return new ResponseEntity(user, HttpStatus.OK);
	}
	
	@PostMapping(value = "/")
	public ResponseEntity<DonoOficina> inserir(@RequestBody DonoOficina dono){
		
		for (int i = 0; i < dono.getTelefone().size(); i++) {
			dono.getTelefone().get(i).setPessoa(dono);
		}
		
		return ResponseEntity.ok().body(donoOficinaRepo.save(dono));
		
	}

}
