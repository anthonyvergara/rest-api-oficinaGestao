package com.api.oficina.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.hibernate.annotations.ForeignKey;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Cliente extends Pessoa implements Serializable, Comparable<Cliente>{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Long numeroDrive;
	
	private Long numeroPassaporte;
	
	private Long numeroRg;
	
	@Column(name = "created_at", updatable = false)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime updatedAt;

	@Column(name = "deleted_at")
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime deletedAt;

	@JsonIgnore
	@ForeignKey(name = "id_oficina")
	@ManyToOne(optional = false)
	private Oficina oficina;
	
	@OneToMany(mappedBy = "cliente")
	private List<DocumentoImg> documentos = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "cliente")
	private Set<OrdemServico> ordemServico = new HashSet<OrdemServico>();
	
	@PrePersist
	public void prePersist() {
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
	}

	@PreUpdate
	public void preUpdate() {
		this.updatedAt = LocalDateTime.now();
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

	@Override
	public int compareTo(Cliente o) {
		
		return this.getNome().compareTo(o.getNome());
	}

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

	public Oficina getOficina() {
		return oficina;
	}

	public void setOficina(Oficina oficina) {
		this.oficina = oficina;
	}

	public List<DocumentoImg> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(List<DocumentoImg> documentos) {
		this.documentos = documentos;
	}

	public Set<OrdemServico> getOrdemServico() {
		return ordemServico;
	}

	public void setOrdemServico(Set<OrdemServico> ordemServico) {
		this.ordemServico = ordemServico;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public LocalDateTime getDeletedAt() {
		return deletedAt;
	}

	public void setDeletedAt(LocalDateTime deletedAt) {
		this.deletedAt = deletedAt;
	}



}
