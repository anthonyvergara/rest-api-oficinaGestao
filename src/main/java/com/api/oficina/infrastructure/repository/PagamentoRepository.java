package com.api.oficina.infrastructure.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.api.oficina.model.Pagamento;

public interface PagamentoRepository extends CrudRepository<Pagamento, Long>{
	
	@Query(value ="select o from Pagamento p join p.ordemServico o where p.id = :id")
	public List<Pagamento> getAllByIdOrdemServico(@Param("id") Long id);

	@Query(value = "select p from Pagamento p where p.ordemServico.id = :idOrdemServico")
	public List<Pagamento> findByIdOrdemServico(@Param("idOrdemServico") Long idOrdemServico);
	
}
