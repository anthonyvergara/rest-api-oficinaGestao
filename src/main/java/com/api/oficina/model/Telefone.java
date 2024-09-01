package com.api.oficina.model;

import java.io.Serializable;
import java.util.Objects;

import org.hibernate.annotations.ForeignKey;

import com.api.oficina.modelEnum.TipoTelefone;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Telefone implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public Telefone() {
		this.setDdd(44);
		this.setCountry("UK");
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id_telefone;
	
	private Integer tipo;
	
	private String country;
	
	@Column(nullable = false)
	private int ddd;
	
	@Column(nullable = false)
	private Long numero;
	
	@JsonIgnore
	@ForeignKey(name = "id_pessoa")
	@ManyToOne(optional = false)
	private Pessoa pessoa;
	

	public Long getId_telefone() {
		return id_telefone;
	}

	public void setId_telefone(Long id_telefone) {
		this.id_telefone = id_telefone;
	}

	public TipoTelefone getTipo() {
		return TipoTelefone.valueOf(tipo);
	}

	public void setTipo(TipoTelefone tipo) {
		if(tipo != null) {
			this.tipo = tipo.getCode();
		}
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getDdd() {
		return ddd;
	}

	public void setDdd(int ddd) {
		this.ddd = ddd;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id_telefone);
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Telefone other = (Telefone) obj;
		return Objects.equals(id_telefone, other.id_telefone);
	}
	
	
	
}
