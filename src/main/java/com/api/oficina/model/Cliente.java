package com.api.oficina.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Cliente extends Pessoa implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Long numeroDrive;
	
	private Long numeroPassaporte;
	
	private Long numeroRg;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getNumeroDrive() {
		return numeroDrive;
	}

	public void setNumeroDrive(Long numeroDrive) {
		this.numeroDrive = numeroDrive;
	}

	public Long getNumeroPassaporte() {
		return numeroPassaporte;
	}

	public void setNumeroPassaporte(Long numeroPassaporte) {
		this.numeroPassaporte = numeroPassaporte;
	}

	public Long getNumeroRg() {
		return numeroRg;
	}

	public void setNumeroRg(Long numeroRg) {
		this.numeroRg = numeroRg;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(id);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(id, other.id);
	}
	
	

}
