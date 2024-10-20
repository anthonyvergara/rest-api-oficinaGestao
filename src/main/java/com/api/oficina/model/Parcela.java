package com.api.oficina.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import org.hibernate.annotations.ForeignKey;

import com.api.oficina.modelEnum.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class Parcela implements Serializable{

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Status getStatusParcela() {
		return Status.valueOf(this.statusParcela);
	}

	public void setStatusParcela(Status statusParcela) {
		if(statusParcela != null) {
			this.statusParcela = statusParcela.getCode();
		}
	}
}
