package com.api.oficina.dto;

import java.time.LocalDate;
import java.util.List;

import com.api.oficina.model.Endereco;
import com.api.oficina.model.Telefone;
import com.api.oficina.modelEnum.Role;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UsuarioDTO {

    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    private String sobrenome;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email inválido")
    private String email;

    @NotBlank(message = "Username é obrigatório")
    private String username;

    private String password;

    @NotNull(message = "Role é obrigatória")
    private Role role;

    private Long oficinaId;

    // Sistema de permissões por recurso
    private List<RecursoPermissaoDTO> recursoPermissoes;

    private Boolean ativo;

    private List<Telefone> telefones;
    private List<Endereco> enderecos;

    // Constructors
    public UsuarioDTO() {
    }

    public UsuarioDTO(Long id, String nome, String sobrenome, LocalDate dataNascimento, String email, String username, String password, Role role,
                      Long oficinaId, List<RecursoPermissaoDTO> recursoPermissoes, Boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.dataNascimento = dataNascimento;
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

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
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

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }
}