package com.api.oficina.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.api.oficina.model.DocumentoImg;
import com.api.oficina.serviceImpl.DocumentoImgImpl;


@RestController
@RequestMapping(value = "/documento")
public class DocumentoImgController {
	
	@Autowired
	private DocumentoImgImpl documentoService;
	
	@PostMapping(value = "/cliente/{idCliente}")
	public ResponseEntity<List<String>> save(@RequestParam("file") List<MultipartFile> file, @PathVariable(value = "idCliente")Long id){
		return new ResponseEntity<List<String>>(documentoService.save(file,id),HttpStatus.OK);
	}
	
	@GetMapping(value = "")
	public ResponseEntity<List<DocumentoImg>>listAll(){
		return new ResponseEntity<List<DocumentoImg>>(this.documentoService.listAll(),HttpStatus.OK);
	}
	
	@GetMapping(value = "/")
	public ResponseEntity<Resource> listObject(){
		
		byte[] file = this.documentoService.downloadFile();
		
		ByteArrayResource resource = new ByteArrayResource(file);
		
		String contentType = ".png";
		
		String fileName = "81b026b1-b5f6-430b-89cd-98a1a185c5c2";
		
		return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName+contentType + "\"")
                .header(HttpHeaders.CONTENT_TYPE, contentType == "png" ? contentType : "application/octet-stream")
                .body(resource);
    }
	
	
}
