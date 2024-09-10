package com.api.oficina.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import org.hibernate.annotations.ForeignKey;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class StatusOrdemServico implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime ultimoPagamento;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate proximoVencimento;
	private double saldoDevedor;
	private double valorProximaParcela;
	
	@JsonIgnore
	@ForeignKey(name = "id_ordemServico")
	@OneToOne
	private OrdemServico ordemServico;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getUltimoPagamento() {
		return ultimoPagamento;
	}

	public void setUltimoPagamento(LocalDateTime ultimoPagamento) {
		this.ultimoPagamento = ultimoPagamento;
	}

	public LocalDate getProximoVencimento() {
		return proximoVencimento;
	}

	public void setProximoVencimento(LocalDate proximoVencimento) {
		this.proximoVencimento = proximoVencimento;
	}

	public double getSaldoDevedor() {
		return saldoDevedor;
	}

	public void setSaldoDevedor(double saldoDevedor) {
		this.saldoDevedor = saldoDevedor;
	}

	public double getValorProximaParcela() {
		return valorProximaParcela;
	}

	public void setValorProximaParcela(double valorProximaParcela) {
		this.valorProximaParcela = valorProximaParcela;
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
		StatusOrdemServico other = (StatusOrdemServico) obj;
		return Objects.equals(id, other.id);
	}
	
}
