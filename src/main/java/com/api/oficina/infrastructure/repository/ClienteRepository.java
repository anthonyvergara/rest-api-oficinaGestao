package com.api.oficina.infrastructure.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.api.oficina.model.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Long>{
	
	@Query("SELECT o.cliente FROM OrdemServico o WHERE o.id = :idOrdem")
	Cliente findByIdOrdemServico(@Param("idOrdem") Long idOrdem);

	List<Cliente> findByNomeContainingIgnoreCase(String nome);
}
