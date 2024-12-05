package com.api.oficina.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.api.oficina.model.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Long>{
	
	@Query("SELECT o.cliente FROM OrdemServico o WHERE o.id = :idOrdem")
	Cliente findByIdOrdemServico(@Param("idOrdem") Long idOrdem);

	
}
