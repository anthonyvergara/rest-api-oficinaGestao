package com.api.oficina.infrastructure.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.api.oficina.model.Oficina;
import com.api.oficina.model.OficinaEndereco;

public interface OficinaEnderecoRepo extends CrudRepository<OficinaEndereco, Long>{

	@Query(value = "select p from Oficina p where p.id = :id")
	public Optional<Oficina> findOficina(@Param("id") Long id);
	
}
