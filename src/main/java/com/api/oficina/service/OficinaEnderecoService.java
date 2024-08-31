package com.api.oficina.service;

import java.util.List;

import com.api.oficina.model.OficinaEndereco;

public interface OficinaEnderecoService {

	public List<OficinaEndereco> listAll();
	public OficinaEndereco getInfoEndereco(Long id);
	public OficinaEndereco save(OficinaEndereco endereco, Long idOficina);
	public OficinaEndereco update(OficinaEndereco endereco);
	public void deleteById(Long id);
}
