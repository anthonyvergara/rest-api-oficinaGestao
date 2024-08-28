package com.api.oficina.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.oficina.model.Pessoa;
import com.api.oficina.model.Telefone;


@Repository
public interface TelefoneRepository extends CrudRepository<Telefone, Long>{
	
	@Query (value = "select tel from Pessoa p join p.telefone tel where p.id = :id")
	public List<Telefone> listById(@Param("id") Long id);
	
	@Query (value = "select p from Pessoa p where p.id = :id")
	public Pessoa findPessoaId(@Param("id") Long id);
	
}
