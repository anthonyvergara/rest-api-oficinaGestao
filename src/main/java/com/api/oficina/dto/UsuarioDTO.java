package com.api.oficina.dto;

import java.util.List;

import com.api.oficina.modelEnum.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UsuarioDTO {

    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email inválido")
    private String email;

    @NotBlank(message = "Username é obrigatório")
    private String username;

    @NotBlank(message = "Senha é obrigatória")
    private String password;

    @NotNull(message = "Role é obrigatória")
    private Role role;

    private Long oficinaId;

    // Sistema de permissões por recurso
    private List<RecursoPermissaoDTO> recursoPermissoes;

    private Boolean ativo;

    // Constructors
    public UsuarioDTO() {
    }

    public UsuarioDTO(Long id, String nome, String email, String username, String password, Role role,
                      Long oficinaId, List<RecursoPermissaoDTO> recursoPermissoes, Boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
        this.oficinaId = oficinaId;
        this.recursoPermissoes = recursoPermissoes;
        this.ativo = ativo;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Long getOficinaId() {
        return oficinaId;
    }

    public void setOficinaId(Long oficinaId) {
        this.oficinaId = oficinaId;
    }

    public List<RecursoPermissaoDTO> getRecursoPermissoes() {
        return recursoPermissoes;
    }

    public void setRecursoPermissoes(List<RecursoPermissaoDTO> recursoPermissoes) {
        this.recursoPermissoes = recursoPermissoes;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}
