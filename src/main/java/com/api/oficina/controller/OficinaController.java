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

import com.api.oficina.model.Oficina;
import com.api.oficina.serviceImpl.OficinaServiceImpl;

@RestController
@RequestMapping(value = "/oficina")
public class OficinaController {
	
	@Autowired
	private OficinaServiceImpl oficinaService;
	
	
	@GetMapping(value = "")
	public ResponseEntity<List<Oficina>> listAll(){
		return new ResponseEntity<List<Oficina>>(this.oficinaService.listAll(),HttpStatus.OK);
	}
	
	@PostMapping(value = "/donoOficina/{idDonoOficina}")
	public ResponseEntity<Oficina> save(@RequestBody Oficina oficina, @PathVariable(value = "idDonoOficina") Long id){
		return new ResponseEntity<Oficina>(this.oficinaService.save(oficina, id),HttpStatus.OK);
	}
	
	@PutMapping(value = "{id}/donoOficina/{idDonoOficina}")
	public ResponseEntity<Oficina> update(@PathVariable(value = "id") Long id,
											@PathVariable(value = "idDonoOficina") Long idDonoOficina){
		
		return new ResponseEntity<Oficina>(this.oficinaService.update(id, idDonoOficina),HttpStatus.OK);
	}

}
