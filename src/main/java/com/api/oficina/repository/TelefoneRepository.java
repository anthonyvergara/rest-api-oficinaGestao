package com.api.oficina.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.oficina.model.Telefone;


@Repository
public interface TelefoneRepository extends CrudRepository<Telefone, Long>{
	
	@Query (value = "select tel from Pessoa p join p.telefone tel where p.id = :id")
	public List<Telefone> listName(@Param("id") Long id);
	
}
