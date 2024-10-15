package com.api.oficina.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import org.hibernate.annotations.ForeignKey;

import com.api.oficina.modelEnum.TipoPagamento;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter @Getter @EqualsAndHashCode
public class OrdemServico implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public OrdemServico() {
		this.setDataInicio(dataInicio.now());
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Long invoiceNumber;
	
	private int vat;
	
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime dataInicio;
	
	private double valorTotal;
	
	private Integer tipoPagamento;
	
	private String observacao;
	
	private int quantidadeParcelas;
	
	@JsonIgnore
	@ForeignKey(name = "id_cliente")
	@ManyToOne
	private Cliente cliente;
	
	@JsonIgnore
	@ForeignKey(name = "id_oficina")
	@ManyToOne
	private Oficina oficina;
	
	@OneToMany(mappedBy = "ordemServico", cascade = CascadeType.ALL)
	private List<DetalheServico> detalheServico = new ArrayList<DetalheServico>();
	
	@OneToMany(mappedBy = "ordemServico", cascade = CascadeType.ALL)
	private List<Pagamento> pagamento = new ArrayList<Pagamento>();
	
	@OneToOne(mappedBy = "ordemServico", cascade = CascadeType.ALL)
	private StatusOrdemServico statusOrdemServico;
	
	@OneToMany(mappedBy = "ordemServico", cascade = CascadeType.ALL)
	private List<Parcela> parcela = new ArrayList<Parcela>();

	public TipoPagamento getTipoPagamento() {
		return TipoPagamento.valueOf(tipoPagamento);
	}

	public void setTipoPagamento(TipoPagamento tipoPagamento) {
		if(tipoPagamento != null) {
			this.tipoPagamento = tipoPagamento.getCode();
		}
	}
}
