package com.api.oficina.service;

import java.util.List;

import com.api.oficina.model.DocumentoImg;

public interface DocumentoImgService {
	
	public List<DocumentoImg> listAll();
	public String save();
	
}
