package com.api.oficina.model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter @Getter
public class Oficina implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	
	@NotBlank
	private String nomeOficina;
	
	private Long companyNumber;
	
	private Long vatNumber;
	
	@ManyToMany
	@JoinTable(
			name = "oficina_dono",
			joinColumns = @JoinColumn(name = "oficina_id"),
			inverseJoinColumns = @JoinColumn(name = "donoOficina_id")
	)
	private List<DonoOficina> donoOficina = new ArrayList<DonoOficina>();
	
	@OneToOne(mappedBy = "oficina", cascade = CascadeType.ALL)
	private OficinaEndereco endereco;
	
	@OneToMany(mappedBy = "oficina", cascade = CascadeType.ALL)
	private Set<Cliente> cliente = new HashSet<Cliente>();
	
	@OneToMany(mappedBy = "oficina")
	private Set<OrdemServico> ordemServico = new HashSet<OrdemServico>();

	@Override
	public int hashCode() {
		return Objects.hash(Id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Oficina other = (Oficina) obj;
		return Objects.equals(Id, other.Id);
	}

	
}
