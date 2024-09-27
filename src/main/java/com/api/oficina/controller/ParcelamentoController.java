package com.api.oficina.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.oficina.model.Parcelamento;
import com.api.oficina.serviceImpl.ParcelamentoServiceImpl;

@RestController
@RequestMapping(value = "/oficina/parcelamento")
public class ParcelamentoController {
	
	private final ParcelamentoServiceImpl  PARCELAMENTO_SERVICE;
	
	public ParcelamentoController(ParcelamentoServiceImpl parcelamentoServiceImpl) {
		this.PARCELAMENTO_SERVICE = parcelamentoServiceImpl;
	}
	
}
