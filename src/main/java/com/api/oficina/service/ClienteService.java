package com.api.oficina.service;

import java.util.List;

import com.api.oficina.dto.ClienteDTO;
import com.api.oficina.model.Cliente;

public interface ClienteService {
	
	public List<ClienteDTO> listAll();
	public ClienteDTO findById(Long id);
	public ClienteDTO save(Cliente cliente, Long idOficina);
	public ClienteDTO update(Cliente cliente);
	public void deleteById(Long id);
}
