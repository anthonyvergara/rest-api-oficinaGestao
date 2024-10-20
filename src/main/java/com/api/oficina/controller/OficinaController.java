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

import com.api.oficina.dto.OficinaDTO;
import com.api.oficina.model.Oficina;
import com.api.oficina.serviceImpl.OficinaServiceImpl;

@RestController
@RequestMapping(value = "/oficina")
public class OficinaController {
	
	private final OficinaServiceImpl OFICINA_SERVICE;
	
	public OficinaController(OficinaServiceImpl oficinaService) {
		this.OFICINA_SERVICE = oficinaService;
	}
	
	
	@GetMapping(value = "")
	public ResponseEntity<List<OficinaDTO>> listAll(){
		return new ResponseEntity<List<OficinaDTO>>(this.OFICINA_SERVICE.listAll(),HttpStatus.OK);
	}
	
	@PostMapping(value = "/donoOficina/{idDonoOficina}")
	public ResponseEntity<OficinaDTO> save(@RequestBody Oficina oficina, @PathVariable(value = "idDonoOficina") Long id){
		return new ResponseEntity<OficinaDTO>(this.OFICINA_SERVICE.save(oficina, id),HttpStatus.OK);
	}
	
	@PutMapping(value = "")
	public ResponseEntity<OficinaDTO> update(@RequestBody Oficina oficina){
		
		return new ResponseEntity<OficinaDTO>(this.OFICINA_SERVICE.update(oficina),HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{idOficina}/dono/{idDono}")
	public ResponseEntity<String> deleteDonoFromOficina(@PathVariable(value = "idOficina") Long idOficina,
														@PathVariable(value = "idDono") Long idDono){
		this.OFICINA_SERVICE.deleteDonoFromOficina(idOficina, idDono);
		return ResponseEntity.ok().build();
	}
}
