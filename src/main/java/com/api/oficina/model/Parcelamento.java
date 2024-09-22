package com.api.oficina.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import org.hibernate.annotations.ForeignKey;

import com.api.oficina.modelEnum.StatusParcela;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Parcelamento implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Integer statusParcela;
	
	private double valorParcela;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataVencimento;
	
	@JsonIgnore
	@ForeignKey(name = "id_ordemServico")
	@ManyToOne
	private OrdemServico ordemServico;
	
	public Parcelamento() {
		
	}
	
	public Parcelamento(StatusParcela status, double valorParcela, LocalDate dataVencimento) {
		this.statusParcela = status.code;
		this.valorParcela = valorParcela;
		this.dataVencimento = dataVencimento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public StatusParcela getStatusParcela() {
		return StatusParcela.valueOf(this.statusParcela);
	}

	public void setStatusParcela(StatusParcela statusParcela) {
		if(statusParcela != null) {
			this.statusParcela = statusParcela.getCode();
		}
	}

	public double getValorParcela() {
		return valorParcela;
	}

	public void setValorParcela(double valorParcela) {
		this.valorParcela = valorParcela;
	}

	public LocalDate getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(LocalDate dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public OrdemServico getOrdemServico() {
		return ordemServico;
	}

	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
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
		Parcelamento other = (Parcelamento) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
}