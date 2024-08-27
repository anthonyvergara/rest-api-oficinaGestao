package com.api.oficina.service;

import java.util.List;

import com.api.oficina.model.Cliente;

public interface ClienteService {
	
	public List<Cliente> listAll();
	public Cliente findById(Long id);
	public Cliente save(Cliente cliente);
	public Cliente update(Cliente cliente);
	public void deleteById(Long id);
}
