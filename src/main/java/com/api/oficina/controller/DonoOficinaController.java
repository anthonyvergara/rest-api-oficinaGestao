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

import com.api.oficina.dto.DonoOficinaDTO;
import com.api.oficina.model.DonoOficina;
import com.api.oficina.serviceImpl.DonoOficinaImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/dono")
public class DonoOficinaController {
	
	@Autowired
	private DonoOficinaImpl donoOficinaService;

	
	@GetMapping(value = "", produces = "application/json")
	public ResponseEntity<List<DonoOficinaDTO>> listAll(){
		return new ResponseEntity<>(this.donoOficinaService.listAll(), HttpStatus.OK);
	}
	
	@PutMapping(value = "")
	public ResponseEntity<DonoOficinaDTO> updateDados(@RequestBody @Valid DonoOficina dono){
		
		return ResponseEntity.ok().body(donoOficinaService.updateDados(dono));
	}
	
	@PostMapping(value = "")
	public ResponseEntity<DonoOficinaDTO> saveDados(@RequestBody @Valid DonoOficina dono){
		
		return ResponseEntity.ok().body(donoOficinaService.save(dono));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") Long id){
		this.donoOficinaService.deleteById(id);
		return ResponseEntity.ok().build();
	}

}
