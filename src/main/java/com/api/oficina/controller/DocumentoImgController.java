package com.api.oficina.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.api.oficina.serviceImpl.DocumentoImgImpl;

@RestController
@RequestMapping(value = "/documento")
public class DocumentoImgController {
	
	@Autowired
	private DocumentoImgImpl documentoImg;
	
	@PostMapping(value = "/cliente/{idCliente}")
	public ResponseEntity<List<String>> save(@RequestParam("file") List<MultipartFile> file, @PathVariable(value = "idCliente")Long id){
		return new ResponseEntity<List<String>>(documentoImg.save(file,id),HttpStatus.OK);
	}
	
}
