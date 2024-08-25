package com.api.oficina.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.oficina.model.Telefone;

import jakarta.transaction.Transactional;

@Repository
public interface TelefoneRepository extends CrudRepository<Telefone, Long>{
	
	@Modifying
	@Transactional
	@Query(value = "select p from Telefone p where p.idPessoa = : id")
	public List<Telefone> listById(@Param("id") Long id);
}
