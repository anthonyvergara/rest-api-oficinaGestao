package com.api.oficina.infrastructure.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.api.oficina.model.OrdemServico;

public interface OrdemServicoRepository extends CrudRepository<OrdemServico, Long>{
	
	@Query(value = "select p from OrdemServico p where p.invoiceNumber = :invoiceNumber")
	public Optional<OrdemServico> findByInvoice(@Param("invoiceNumber") Long invoiceNumber);
	
	public List<OrdemServico> findAllByOficina_IdAndDeletedAtIsNull(Long oficinaId);

	public List<OrdemServico> findAllByDeletedAtIsNull();

	public Optional<OrdemServico> findByIdAndDeletedAtIsNull(Long id);

	@Query(value =""" 
			select * from ordem_servico os
			WHERE os.cliente_id = :idCliente
			AND os.deleted_at IS NULL
			""", nativeQuery = true)
	public List<OrdemServico> findOrdemServicoByIdCliente(@Param("idCliente") Long idCliente);
	
	public List<OrdemServico> findAllByOficina_Id(Long oficinaId);

	// Contar ordens por status
	@Query("SELECT COUNT(os) FROM OrdemServico os WHERE os.oficina.Id = :oficinaId AND os.statusOrdemServico.tipoStatus = :status AND os.deletedAt IS NULL")
	Long countByOficinaIdAndStatus(@Param("oficinaId") Long oficinaId, @Param("status") Integer status);

	// Contar ordens por período
	@Query("SELECT COUNT(os) FROM OrdemServico os WHERE os.oficina.Id = :oficinaId AND os.createdAt >= :startDate AND os.deletedAt IS NULL")
	Long countByOficinaIdAndCreatedAtAfter(@Param("oficinaId") Long oficinaId, @Param("startDate") LocalDateTime startDate);

	// Contar ordens por mês específico
	@Query("SELECT COUNT(os) FROM OrdemServico os WHERE os.oficina.Id = :oficinaId AND YEAR(os.createdAt) = :year AND MONTH(os.createdAt) = :month AND os.deletedAt IS NULL")
	Long countByOficinaIdAndYearAndMonth(@Param("oficinaId") Long oficinaId, @Param("year") int year, @Param("month") int month);

	// Buscar últimas ordens
	@Query("SELECT os FROM OrdemServico os WHERE os.oficina.Id = :oficinaId AND os.deletedAt IS NULL ORDER BY os.createdAt DESC")
	List<OrdemServico> findTopNByOficinaIdOrderByCreatedAtDesc(@Param("oficinaId") Long oficinaId, Pageable pageable);
}
