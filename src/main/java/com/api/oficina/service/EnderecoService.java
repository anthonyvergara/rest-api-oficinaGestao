package com.api.oficina.service;

import java.util.List;

import com.api.oficina.model.Endereco;

public interface EnderecoService {
	
	public List<Endereco> listAll();
	public List<Endereco> listById(Long id);
	public Endereco save(Endereco endereco, Long idPessoa);
	public Endereco update(Endereco endereco);
	public void deleteById(Long id);
	
}
