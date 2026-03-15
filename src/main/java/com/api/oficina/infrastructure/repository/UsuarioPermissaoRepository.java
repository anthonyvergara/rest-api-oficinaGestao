package com.api.oficina.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.oficina.model.UsuarioPermissao;

@Repository
public interface UsuarioPermissaoRepository extends JpaRepository<UsuarioPermissao, Long> {
}

