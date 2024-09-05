package com.api.oficina.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.api.oficina.model.DocumentoImg;

public interface DocumentoImgService {
	
	public List<DocumentoImg> listAll();
	public List<String> save(List<MultipartFile> file, Long id);
	
}
