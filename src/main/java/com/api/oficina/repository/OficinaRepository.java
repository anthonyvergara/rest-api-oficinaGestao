package com.api.oficina.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.api.oficina.model.DonoOficina;
import com.api.oficina.model.Oficina;

public interface OficinaRepository extends CrudRepository<Oficina, Long>{

	@Query(value = "select p from DonoOficina p where p.id = :id")
	public Optional<DonoOficina> findDonoOficinaById(@Param("id") Long id);
}
