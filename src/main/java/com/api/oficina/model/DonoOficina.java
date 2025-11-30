package com.api.oficina.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;

@Entity
public class DonoOficina extends Pessoa implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	private String usuario;
	
	@NotBlank
	private String senha;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "donoOficina")
	private List<Oficina> oficina;

	// Constructors
	public DonoOficina() {
	}

	public DonoOficina(Long id, String usuario, String senha, List<Oficina> oficina) {
		this.id = id;
		this.usuario = usuario;
		this.senha = senha;
		this.oficina = oficina;
	}

	// Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Oficina> getOficina() {
		return oficina;
	}

	public void setOficina(List<Oficina> oficina) {
		this.oficina = oficina;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DonoOficina other = (DonoOficina) obj;
		return Objects.equals(id, other.id);
	}
}
