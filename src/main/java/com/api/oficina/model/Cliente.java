package com.api.oficina.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.hibernate.annotations.ForeignKey;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter @Getter
public class Cliente extends Pessoa implements Serializable, Comparable<Cliente>{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Long numeroDrive;
	
	private Long numeroPassaporte;
	
	private Long numeroRg;
	
	@JsonIgnore
	@ForeignKey(name = "id_oficina")
	@ManyToOne(optional = false)
	private Oficina oficina;
	
	@OneToMany(mappedBy = "cliente")
	private List<DocumentoImg> documentos = new ArrayList<>();
	
	@OneToMany(mappedBy = "cliente")
	private Set<OrdemServico> ordemServico = new HashSet<OrdemServico>();
	
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

	@Override
	public int compareTo(Cliente o) {
		
		return this.getNome().compareTo(o.getNome());
	}

}
