package com.api.oficina.service;

import java.util.List;

import com.api.oficina.model.DonoOficina;

public interface DonoOficinaService {
	
	public List<DonoOficina> listAll();
	
	public DonoOficina updateDados(DonoOficina dono);
	
	public DonoOficina save(DonoOficina dono);
	
	public void deleteById(Long id);
	
}
