package com.api.oficina.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.oficina.serviceImpl.DocumentoImgImpl;

@RestController
@RequestMapping(value = "/documento")
public class DocumentoImgController {
	
	@Autowired
	private DocumentoImgImpl documentoImg;
	
	@PostMapping(value = "")
	public ResponseEntity<String> save(){
		return new ResponseEntity<String>(documentoImg.save(),HttpStatus.OK);
	}
	
}
