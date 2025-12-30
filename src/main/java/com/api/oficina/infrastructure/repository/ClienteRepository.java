package com.api.oficina.infrastructure.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.api.oficina.model.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Long>{
	
	@Query("SELECT o.cliente FROM OrdemServico o WHERE o.id = :idOrdem")
	Cliente findByIdOrdemServico(@Param("idOrdem") Long idOrdem);

	@Query("SELECT c FROM Cliente c WHERE " +
			"LOWER(c.nome) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
			"LOWER(c.sobrenome) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
			"LOWER(CONCAT(c.nome, ' ', c.sobrenome)) LIKE LOWER(CONCAT('%', :search, '%'))")
	List<Cliente> findByNomeContainingIgnoreCase(@Param("search") String search);

	List<Cliente> findByOficinaId(Long idOficina);
}
