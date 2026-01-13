package com.api.oficina.service;

import java.util.List;
import java.util.Optional;

import com.api.oficina.dto.DonoOficinaDTO;
import com.api.oficina.model.DonoOficina;

public interface DonoOficinaService {
	
	public List<DonoOficinaDTO> listAll();
	
	public DonoOficinaDTO updateDados(DonoOficina dono);
	
	public DonoOficinaDTO save(DonoOficina dono);
	
	public void deleteById(Long id);

    Optional<DonoOficina> findById(Long id);
}
