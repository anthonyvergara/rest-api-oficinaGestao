package com.api.oficina.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.api.oficina.model.OrdemServico;

public interface OrdemServicoRepository extends CrudRepository<OrdemServico, Long>{
	
	@Query(value = "select p from OrdemServico p where p.invoiceNumber = :invoiceNumber")
	public Optional<OrdemServico> findByInvoice(@Param("invoiceNumber") Long invoiceNumber);

}
