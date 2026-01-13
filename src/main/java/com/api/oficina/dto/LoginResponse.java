package com.api.oficina.dto;

import java.util.List;

import com.api.oficina.modelEnum.Role;

public class LoginResponse {

    private String token;

    private String type = "Bearer";

    private Long id;

    private String username;

    private String email;

    private String nome;

    private Role role;

    private List<RecursoPermissaoDTO> recursoPermissoes;

    private Long oficinaId;

    // Constructors
    public LoginResponse() {
    }

    public LoginResponse(String token, String type, Long id, String username, String email, String nome,
                        Role role, List<RecursoPermissaoDTO> recursoPermissoes, Long oficinaId) {
        this.token = token;
        this.type = type;
        this.id = id;
        this.username = username;
        this.email = email;
        this.nome = nome;
        this.role = role;
        this.recursoPermissoes = recursoPermissoes;
        this.oficinaId = oficinaId;
    }

    // Getters and Setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<RecursoPermissaoDTO> getRecursoPermissoes() {
        return recursoPermissoes;
    }

    public void setRecursoPermissoes(List<RecursoPermissaoDTO> recursoPermissoes) {
        this.recursoPermissoes = recursoPermissoes;
    }

    public Long getOficinaId() {
        return oficinaId;
    }

    public void setOficinaId(Long oficinaId) {
        this.oficinaId = oficinaId;
    }
}
