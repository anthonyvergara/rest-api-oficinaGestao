package com.api.oficina.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.api.oficina.model.DetalheServico;

public interface DetalheServicoRepository extends CrudRepository<DetalheServico, Long>{

	@Query(value = "select d from DetalheServico d where d.placa = :placa and d.ordemServico.id = :idOrdemServico")
	public List<DetalheServico>listByPlaca(@Param("placa") String placa, @Param("idOrdemServico") Long idOrdemServico);
	
	@Query(value = "select d from DetalheServico d where d.ordemServico.id = :idOrdemServico")
	public List<DetalheServico> listByIdOrdemServico(@Param("idOrdemServico") Long id);
	
}
