package com.api.oficina.infrastructure.repository;

import java.time.LocalDateTime;
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

	// Contar total de clientes
	@Query("SELECT COUNT(c) FROM Cliente c WHERE c.oficina.Id = :oficinaId AND c.deletedAt IS NULL")
	Long countByOficinaId(@Param("oficinaId") Long oficinaId);

	// Contar clientes por período
	@Query("SELECT COUNT(c) FROM Cliente c WHERE c.oficina.Id = :oficinaId AND c.createdAt >= :startDate AND c.deletedAt IS NULL")
	Long countByOficinaIdAndCreatedAtAfter(@Param("oficinaId") Long oficinaId, @Param("startDate") LocalDateTime startDate);

	// Contar clientes por mês específico
	@Query("SELECT COUNT(c) FROM Cliente c WHERE c.oficina.Id = :oficinaId AND YEAR(c.createdAt) = :year AND MONTH(c.createdAt) = :month AND c.deletedAt IS NULL")
	Long countByOficinaIdAndYearAndMonth(@Param("oficinaId") Long oficinaId, @Param("year") int year, @Param("month") int month);
}
