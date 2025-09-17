package com.api.oficina.infrastructure.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.api.oficina.model.Endereco;
import com.api.oficina.model.Pessoa;

public interface EnderecoRepository extends CrudRepository<Endereco, Long>{

	@Query(value = "select e from Pessoa p join p.endereco e where p.id = :id")
	public List<Endereco> listById(@Param("id") Long id);
	
	@Query(value = "select p from Pessoa p where p.id = :id")
	public Pessoa findPessoaId(@Param("id") Long id);
	
}
