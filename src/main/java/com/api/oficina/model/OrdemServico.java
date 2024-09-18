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

@Entity
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
	
	@OneToMany(mappedBy = "ordemServico", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Pagamento> pagamento = new ArrayList<Pagamento>();
	
	@OneToOne(mappedBy = "ordemServico")
	private StatusOrdemServico statusOrdemServico;
	
	@OneToMany(mappedBy = "ordemServico", cascade = CascadeType.ALL)
	private List<Parcelamento> parcelamento = new ArrayList<Parcelamento>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(Long invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public int getVat() {
		return vat;
	}

	public void setVat(int vat) {
		this.vat = vat;
	}

	public LocalDateTime getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDateTime dataInicio) {
		this.dataInicio = dataInicio;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public TipoPagamento getTipoPagamento() {
		return TipoPagamento.valueOf(tipoPagamento);
	}

	public void setTipoPagamento(TipoPagamento tipoPagamento) {
		if(tipoPagamento != null) {
			this.tipoPagamento = tipoPagamento.getCode();
		}
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public int getQuantidadeParcelas() {
		return quantidadeParcelas;
	}

	public void setQuantidadeParcelas(int quantidadeParcelas) {
		this.quantidadeParcelas = quantidadeParcelas;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public Oficina getOficina() {
		return oficina;
	}

	public void setOficina(Oficina oficina) {
		this.oficina = oficina;
	}

	public StatusOrdemServico getStatusOrdemServico() {
		return statusOrdemServico;
	}

	public void setStatusOrdemServico(StatusOrdemServico statusOrdemServico) {
		this.statusOrdemServico = statusOrdemServico;
	}
	
	public List<DetalheServico> getDetalheServico() {
		return detalheServico;
	}

	public void setDetalheServico(List<DetalheServico> detalheServico) {
		this.detalheServico = detalheServico;
	}

	public List<Pagamento> getPagamento() {
		return pagamento;
	}

	public void setPagamento(List<Pagamento> pagamento) {
		this.pagamento = pagamento;
	}
	
	public List<Parcelamento> getParcelamento() {
		return parcelamento;
	}

	public void setParcelamento(List<Parcelamento> parcelamento) {
		this.parcelamento = parcelamento;
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
		OrdemServico other = (OrdemServico) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
}
