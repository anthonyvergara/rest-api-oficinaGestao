package com.api.oficina.infrastructure.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.api.oficina.model.StatusOrdemServico;

public interface StatusOrdemServicoRepository extends CrudRepository<StatusOrdemServico, Long>{
	
	@Query(value = "select s from OrdemServico o join o.statusOrdemServico s where o.id = :id")
	public StatusOrdemServico findByIdOrdemServico(@Param("id") Long id);

}
