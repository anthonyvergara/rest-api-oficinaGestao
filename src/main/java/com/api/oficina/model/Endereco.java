package com.api.oficina.model;

import java.io.Serializable;
import java.util.Objects;

import org.hibernate.annotations.ForeignKey;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter @Getter @EqualsAndHashCode
public class Endereco implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public Endereco() {
		this.setCidade("London");
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_endereco;
	
	private String rua;
	
	private int numero;
	
	@NotBlank
	@Column(nullable = false)
	private String postcode;
	
	private String cidade;
	
	@JsonIgnore
	@ForeignKey(name = "id_pessoa")
	@ManyToOne(optional = false)
	private Pessoa pessoa;
	
}
