package com.api.oficina.infrastructure.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.oficina.model.UsuarioRecursoPermissao;
import com.api.oficina.modelEnum.Permission;
import com.api.oficina.modelEnum.Recurso;

@Repository
public interface UsuarioRecursoPermissaoRepository extends JpaRepository<UsuarioRecursoPermissao, Long> {

    List<UsuarioRecursoPermissao> findByUsuarioId(Long usuarioId);

    @Query("SELECT urp FROM UsuarioRecursoPermissao urp WHERE urp.usuario.id = :usuarioId AND urp.recurso = :recurso AND urp.permission = :permission")
    UsuarioRecursoPermissao findByUsuarioIdAndRecursoAndPermission(
        @Param("usuarioId") Long usuarioId,
        @Param("recurso") Recurso recurso,
        @Param("permission") Permission permission
    );

    void deleteByUsuarioId(Long usuarioId);
}

