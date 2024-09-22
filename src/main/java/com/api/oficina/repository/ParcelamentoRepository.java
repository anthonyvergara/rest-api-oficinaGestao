package com.api.oficina.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.api.oficina.model.Parcelamento;

public interface ParcelamentoRepository extends CrudRepository<Parcelamento, Long>{

	@Query(value = "select p from OrdemServico o join o.parcelamento p where o.id  = :id")
	public List<Parcelamento> listByIdOrdemServico(@Param("id") Long id);
	
	
}
