package com.api.oficina.infrastructure.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.oficina.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByUsername(String username);

    @Query("SELECT u FROM Usuario u LEFT JOIN FETCH u.recursoPermissoes WHERE u.username = :username")
    Optional<Usuario> findByUsernameWithPermissions(@Param("username") String username);

    Optional<Usuario> findByEmail(String email);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
    
    Boolean existsByEmailAndIdNot(String email, Long id);
}