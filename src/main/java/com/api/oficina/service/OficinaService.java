package com.api.oficina.service;

import java.util.List;

import com.api.oficina.model.Oficina;

public interface OficinaService {
	
	public List<Oficina> listAll();
	public Oficina save(Oficina oficina, Long idDonoOficina);
	public Oficina update(Long idOficina, Long idDonoOficina);

}