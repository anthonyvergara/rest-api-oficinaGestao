package com.api.oficina.service;

import java.util.List;

import com.api.oficina.model.Telefone;

public interface TelefoneService {
	
	public List<Telefone>listAll();
	public List<Telefone>listById(Long id);
	
	public Telefone save(Telefone telefone, Long id);
	public Telefone update(Telefone telefone);
	public void deleteById(Long id);
	
}
