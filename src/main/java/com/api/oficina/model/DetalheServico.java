package com.api.oficina.model;

import java.io.Serializable;
import java.time.LocalDateTime;

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
import jakarta.persistence.PrePersist;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter @Getter @EqualsAndHashCode
public class DetalheServico implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public DetalheServico() {
		this.setData(LocalDateTime.now());
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String placa;
	
	private String descricao;
	
	private int quantidade;
	
	private Long milhagem;
	
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime data;
	
	private double valor;
	
	@JsonIgnore
	@ForeignKey(name = "id_ordemServico")
	@ManyToOne
	private OrdemServico ordemServico;
	
	@PrePersist
    public void prePersist() {
        if (this.data == null) {
            this.data = LocalDateTime.now();
        }
    }
	
}
