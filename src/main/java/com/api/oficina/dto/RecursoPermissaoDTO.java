package com.api.oficina.dto;

import java.util.Set;

import com.api.oficina.modelEnum.Permission;
import com.api.oficina.modelEnum.Recurso;

public class RecursoPermissaoDTO {

    private Recurso recurso;
    private Set<Permission> permissoes;

    public RecursoPermissaoDTO() {
    }

    public RecursoPermissaoDTO(Recurso recurso, Set<Permission> permissoes) {
        this.recurso = recurso;
        this.permissoes = permissoes;
    }

    public Recurso getRecurso() {
        return recurso;
    }

    public void setRecurso(Recurso recurso) {
        this.recurso = recurso;
    }

    public Set<Permission> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(Set<Permission> permissoes) {
        this.permissoes = permissoes;
    }
}

