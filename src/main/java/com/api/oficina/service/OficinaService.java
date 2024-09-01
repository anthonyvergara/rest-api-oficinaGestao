package com.api.oficina.service;

import java.util.List;

import com.api.oficina.dto.OficinaDTO;
import com.api.oficina.model.Oficina;

public interface OficinaService {
	
	public List<OficinaDTO> listAll();
	public OficinaDTO save(Oficina oficina, Long idDonoOficina);
	public OficinaDTO update(Oficina oficina);
	public void deleteDonoFromOficina(Long idOficina, Long idDono);
}
