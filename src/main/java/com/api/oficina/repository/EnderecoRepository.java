package com.api.oficina.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.api.oficina.model.Endereco;

public interface EnderecoRepository extends CrudRepository<Endereco, Long>{

	@Query(value = "select e from Pessoa p join p.endereco e where p.id = :id")
	public List<Endereco> listById(@Param("id") Long id);
	
	@Query(value = "select e from Pessoa p join p.endereco e where p.id = :id")
	public List<Endereco> findUnique(@Param("id") Long id);
}
