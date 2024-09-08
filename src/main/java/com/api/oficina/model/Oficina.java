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

@Entity
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
	
	@OneToMany(mappedBy = "id_oficina")
	private Set<OrdemServico> ordemServico = new HashSet<OrdemServico>();

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getNomeOficina() {
		return nomeOficina;
	}

	public void setNomeOficina(String nomeOficina) {
		this.nomeOficina = nomeOficina;
	}

	public Long getCompanyNumber() {
		return companyNumber;
	}

	public void setCompanyNumber(Long companyNumber) {
		this.companyNumber = companyNumber;
	}

	public Long getVatNumber() {
		return vatNumber;
	}

	public void setVatNumber(Long vatNumber) {
		this.vatNumber = vatNumber;
	}

	public List<DonoOficina> getDonoOficina() {
		return donoOficina;
	}

	public void setDonoOficina(List<DonoOficina> donoOficina) {
		this.donoOficina = donoOficina;
	}
	
	public OficinaEndereco getEndereco() {
		return endereco;
	}

	public void setEndereco(OficinaEndereco endereco) {
		this.endereco = endereco;
	}

	public Set<Cliente> getCliente() {
		return cliente;
	}

	public void setCliente(Set<Cliente> cliente) {
		this.cliente = cliente;
	}
	
	public Set<OrdemServico> getOrdemServico() {
		return ordemServico;
	}

	public void setOrdemServico(Set<OrdemServico> ordemServico) {
		this.ordemServico = ordemServico;
	}

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
