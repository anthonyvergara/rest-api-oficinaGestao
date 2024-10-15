package com.api.oficina.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import org.hibernate.annotations.ForeignKey;

import com.api.oficina.modelEnum.StatusOS;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter @Getter @EqualsAndHashCode
public class StatusOrdemServico implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column
	private Integer tipoStatus;
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

	public StatusOS getTipoStatus() {
		return StatusOS.valueOf(tipoStatus);
	}

	public void setTipoStatus(Integer tipoStatus) {
		if(tipoStatus != null) {
			this.tipoStatus = tipoStatus;
		}
	}
}
