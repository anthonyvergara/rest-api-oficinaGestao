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
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter @Getter @EqualsAndHashCode
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
	
	public TipoTelefone getTipo() {
		return TipoTelefone.valueOf(tipo);
	}

	public void setTipo(TipoTelefone tipo) {
		if(tipo != null) {
			this.tipo = tipo.getCode();
		}
	}
	
}
