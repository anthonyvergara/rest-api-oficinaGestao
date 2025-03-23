package com.api.oficina.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.oficina.model.Parcela;
import com.api.oficina.serviceImpl.ParcelaServiceImpl;

@RestController
@RequestMapping(value = "/oficina/parcelamento")
public class ParcelaController {
	
	private final ParcelaServiceImpl  PARCELAMENTO_SERVICE;
	
	public ParcelaController(ParcelaServiceImpl parcelamentoServiceImpl) {
		this.PARCELAMENTO_SERVICE = parcelamentoServiceImpl;
	}
	
	@PutMapping(value = "/ordemServico/{idOrdemServico}/parcela/{numParcelas}")
	public ResponseEntity<List<Parcela>> updateParcelas(@PathVariable(value = "idOrdemServico") Long id, @PathVariable(value = "numParcelas") int numParcelas){
		return new ResponseEntity<List<Parcela>>(this.PARCELAMENTO_SERVICE.update(id, numParcelas), HttpStatus.OK);
	}
	
}
