package com.api.oficina.model;

import java.io.Serializable;

import com.api.oficina.modelEnum.Permission;
import com.api.oficina.modelEnum.Recurso;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "usuario_recurso_permissao", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"usuario_id", "recurso", "permission"})
})
public class UsuarioRecursoPermissao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Recurso recurso;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Permission permission;

    // Constructors
    public UsuarioRecursoPermissao() {
    }

    public UsuarioRecursoPermissao(Long id, Usuario usuario, Recurso recurso, Permission permission) {
        this.id = id;
        this.usuario = usuario;
        this.recurso = recurso;
        this.permission = permission;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Recurso getRecurso() {
        return recurso;
    }

    public void setRecurso(Recurso recurso) {
        this.recurso = recurso;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }
}
