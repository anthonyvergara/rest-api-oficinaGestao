package com.api.oficina.model;

import java.io.Serializable;
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
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter @Getter @EqualsAndHashCode
public class Pagamento implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@PositiveOrZero(message = "O valor pago não pode ser negativo!")
	private double valorPago;
	
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime dataPagamento;
	
	@JsonIgnore
	@ForeignKey(name = "id_ordemServico")
	@ManyToOne
	private OrdemServico ordemServico;
	
	public Pagamento() {
		this.dataPagamento = LocalDateTime.now();
	}
}
