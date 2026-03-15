package com.api.oficina.infrastructure.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.api.oficina.model.Parcela;

public interface ParcelaRepository extends CrudRepository<Parcela, Long>{

	@Query(value = "select p from OrdemServico o join o.parcela p where o.id  = :id")
	public Optional<List<Parcela>> listByIdOrdemServico(@Param("id") Long id);
	
	
}
