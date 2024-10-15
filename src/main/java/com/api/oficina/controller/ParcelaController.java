package com.api.oficina.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
	
}
