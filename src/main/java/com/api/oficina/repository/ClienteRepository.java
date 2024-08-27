package com.api.oficina.repository;

import org.springframework.data.repository.CrudRepository;

import com.api.oficina.model.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Long>{
	
}
