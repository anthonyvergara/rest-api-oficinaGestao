package com.api.oficina.dto;

import java.util.List;
import java.util.Objects;

import com.api.oficina.model.OrdemServico;

public class OficinaInfoDTO {

	// Contadores de ordens por status
	private Long quantidadeOrdensPago;
	private Long quantidadeOrdensAgendado;
	private Long quantidadeOrdensAtrasado;

	// Contadores de ordens por período
	private Long quantidadeOrdensUltimaHora;
	private Long quantidadeOrdensUltimoDia;
	private Long quantidadeOrdensUltimaSemana;
	private Long quantidadeOrdensUltimoMes;
	private Long quantidadeOrdensNesteAno;
	private Long quantidadeOrdensMesEspecifico;

	// Contadores de clientes
	private Long quantidadeTotalClientes;
	private Long quantidadeClientesUltimoDia;
	private Long quantidadeClientesUltimaSemana;
	private Long quantidadeClientesUltimoMes;
	private Long quantidadeClientesNesteAno;
	private Long quantidadeClientesMesEspecifico;

	// Lista das últimas ordens
	private List<OrdemServico> ultimasOrdens;

	public OficinaInfoDTO() {
	}

	public OficinaInfoDTO(Long quantidadeOrdensPago, Long quantidadeOrdensAgendado, Long quantidadeOrdensAtrasado, Long quantidadeOrdensUltimaHora, Long quantidadeOrdensUltimoDia, Long quantidadeOrdensUltimaSemana, Long quantidadeOrdensUltimoMes, Long quantidadeOrdensNesteAno, Long quantidadeOrdensMesEspecifico, Long quantidadeTotalClientes, Long quantidadeClientesUltimoDia, Long quantidadeClientesUltimaSemana, Long quantidadeClientesUltimoMes, Long quantidadeClientesNesteAno, Long quantidadeClientesMesEspecifico, List<OrdemServico> ultimasOrdens) {
		this.quantidadeOrdensPago = quantidadeOrdensPago;
		this.quantidadeOrdensAgendado = quantidadeOrdensAgendado;
		this.quantidadeOrdensAtrasado = quantidadeOrdensAtrasado;
		this.quantidadeOrdensUltimaHora = quantidadeOrdensUltimaHora;
		this.quantidadeOrdensUltimoDia = quantidadeOrdensUltimoDia;
		this.quantidadeOrdensUltimaSemana = quantidadeOrdensUltimaSemana;
		this.quantidadeOrdensUltimoMes = quantidadeOrdensUltimoMes;
		this.quantidadeOrdensNesteAno = quantidadeOrdensNesteAno;
		this.quantidadeOrdensMesEspecifico = quantidadeOrdensMesEspecifico;
		this.quantidadeTotalClientes = quantidadeTotalClientes;
		this.quantidadeClientesUltimoDia = quantidadeClientesUltimoDia;
		this.quantidadeClientesUltimaSemana = quantidadeClientesUltimaSemana;
		this.quantidadeClientesUltimoMes = quantidadeClientesUltimoMes;
		this.quantidadeClientesNesteAno = quantidadeClientesNesteAno;
		this.quantidadeClientesMesEspecifico = quantidadeClientesMesEspecifico;
		this.ultimasOrdens = ultimasOrdens;
	}

	public static Builder builder() {
		return new Builder();
	}

	public Long getQuantidadeOrdensPago() {
		return quantidadeOrdensPago;
	}

	public void setQuantidadeOrdensPago(Long quantidadeOrdensPago) {
		this.quantidadeOrdensPago = quantidadeOrdensPago;
	}

	public Long getQuantidadeOrdensAgendado() {
		return quantidadeOrdensAgendado;
	}

	public void setQuantidadeOrdensAgendado(Long quantidadeOrdensAgendado) {
		this.quantidadeOrdensAgendado = quantidadeOrdensAgendado;
	}

	public Long getQuantidadeOrdensAtrasado() {
		return quantidadeOrdensAtrasado;
	}

	public void setQuantidadeOrdensAtrasado(Long quantidadeOrdensAtrasado) {
		this.quantidadeOrdensAtrasado = quantidadeOrdensAtrasado;
	}

	public Long getQuantidadeOrdensUltimaHora() {
		return quantidadeOrdensUltimaHora;
	}

	public void setQuantidadeOrdensUltimaHora(Long quantidadeOrdensUltimaHora) {
		this.quantidadeOrdensUltimaHora = quantidadeOrdensUltimaHora;
	}

	public Long getQuantidadeOrdensUltimoDia() {
		return quantidadeOrdensUltimoDia;
	}

	public void setQuantidadeOrdensUltimoDia(Long quantidadeOrdensUltimoDia) {
		this.quantidadeOrdensUltimoDia = quantidadeOrdensUltimoDia;
	}

	public Long getQuantidadeOrdensUltimaSemana() {
		return quantidadeOrdensUltimaSemana;
	}

	public void setQuantidadeOrdensUltimaSemana(Long quantidadeOrdensUltimaSemana) {
		this.quantidadeOrdensUltimaSemana = quantidadeOrdensUltimaSemana;
	}

	public Long getQuantidadeOrdensUltimoMes() {
		return quantidadeOrdensUltimoMes;
	}

	public void setQuantidadeOrdensUltimoMes(Long quantidadeOrdensUltimoMes) {
		this.quantidadeOrdensUltimoMes = quantidadeOrdensUltimoMes;
	}

	public Long getQuantidadeOrdensNesteAno() {
		return quantidadeOrdensNesteAno;
	}

	public void setQuantidadeOrdensNesteAno(Long quantidadeOrdensNesteAno) {
		this.quantidadeOrdensNesteAno = quantidadeOrdensNesteAno;
	}

	public Long getQuantidadeOrdensMesEspecifico() {
		return quantidadeOrdensMesEspecifico;
	}

	public void setQuantidadeOrdensMesEspecifico(Long quantidadeOrdensMesEspecifico) {
		this.quantidadeOrdensMesEspecifico = quantidadeOrdensMesEspecifico;
	}

	public Long getQuantidadeTotalClientes() {
		return quantidadeTotalClientes;
	}

	public void setQuantidadeTotalClientes(Long quantidadeTotalClientes) {
		this.quantidadeTotalClientes = quantidadeTotalClientes;
	}

	public Long getQuantidadeClientesUltimoDia() {
		return quantidadeClientesUltimoDia;
	}

	public void setQuantidadeClientesUltimoDia(Long quantidadeClientesUltimoDia) {
		this.quantidadeClientesUltimoDia = quantidadeClientesUltimoDia;
	}

	public Long getQuantidadeClientesUltimaSemana() {
		return quantidadeClientesUltimaSemana;
	}

	public void setQuantidadeClientesUltimaSemana(Long quantidadeClientesUltimaSemana) {
		this.quantidadeClientesUltimaSemana = quantidadeClientesUltimaSemana;
	}

	public Long getQuantidadeClientesUltimoMes() {
		return quantidadeClientesUltimoMes;
	}

	public void setQuantidadeClientesUltimoMes(Long quantidadeClientesUltimoMes) {
		this.quantidadeClientesUltimoMes = quantidadeClientesUltimoMes;
	}

	public Long getQuantidadeClientesNesteAno() {
		return quantidadeClientesNesteAno;
	}

	public void setQuantidadeClientesNesteAno(Long quantidadeClientesNesteAno) {
		this.quantidadeClientesNesteAno = quantidadeClientesNesteAno;
	}

	public Long getQuantidadeClientesMesEspecifico() {
		return quantidadeClientesMesEspecifico;
	}

	public void setQuantidadeClientesMesEspecifico(Long quantidadeClientesMesEspecifico) {
		this.quantidadeClientesMesEspecifico = quantidadeClientesMesEspecifico;
	}

	public List<OrdemServico> getUltimasOrdens() {
		return ultimasOrdens;
	}

	public void setUltimasOrdens(List<OrdemServico> ultimasOrdens) {
		this.ultimasOrdens = ultimasOrdens;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		OficinaInfoDTO that = (OficinaInfoDTO) o;
		return Objects.equals(quantidadeOrdensPago, that.quantidadeOrdensPago) &&
				Objects.equals(quantidadeOrdensAgendado, that.quantidadeOrdensAgendado) &&
				Objects.equals(quantidadeOrdensAtrasado, that.quantidadeOrdensAtrasado) &&
				Objects.equals(quantidadeOrdensUltimaHora, that.quantidadeOrdensUltimaHora) &&
				Objects.equals(quantidadeOrdensUltimoDia, that.quantidadeOrdensUltimoDia) &&
				Objects.equals(quantidadeOrdensUltimaSemana, that.quantidadeOrdensUltimaSemana) &&
				Objects.equals(quantidadeOrdensUltimoMes, that.quantidadeOrdensUltimoMes) &&
				Objects.equals(quantidadeOrdensNesteAno, that.quantidadeOrdensNesteAno) &&
				Objects.equals(quantidadeOrdensMesEspecifico, that.quantidadeOrdensMesEspecifico) &&
				Objects.equals(quantidadeTotalClientes, that.quantidadeTotalClientes) &&
				Objects.equals(quantidadeClientesUltimoDia, that.quantidadeClientesUltimoDia) &&
				Objects.equals(quantidadeClientesUltimaSemana, that.quantidadeClientesUltimaSemana) &&
				Objects.equals(quantidadeClientesUltimoMes, that.quantidadeClientesUltimoMes) &&
				Objects.equals(quantidadeClientesNesteAno, that.quantidadeClientesNesteAno) &&
				Objects.equals(quantidadeClientesMesEspecifico, that.quantidadeClientesMesEspecifico) &&
				Objects.equals(ultimasOrdens, that.ultimasOrdens);
	}

	@Override
	public int hashCode() {
		return Objects.hash(quantidadeOrdensPago, quantidadeOrdensAgendado, quantidadeOrdensAtrasado, quantidadeOrdensUltimaHora, quantidadeOrdensUltimoDia, quantidadeOrdensUltimaSemana, quantidadeOrdensUltimoMes, quantidadeOrdensNesteAno, quantidadeOrdensMesEspecifico, quantidadeTotalClientes, quantidadeClientesUltimoDia, quantidadeClientesUltimaSemana, quantidadeClientesUltimoMes, quantidadeClientesNesteAno, quantidadeClientesMesEspecifico, ultimasOrdens);
	}

	@Override
	public String toString() {
		return "OficinaInfoDTO{" +
				"quantidadeOrdensPago=" + quantidadeOrdensPago +
				", quantidadeOrdensAgendado=" + quantidadeOrdensAgendado +
				", quantidadeOrdensAtrasado=" + quantidadeOrdensAtrasado +
				", quantidadeOrdensUltimaHora=" + quantidadeOrdensUltimaHora +
				", quantidadeOrdensUltimoDia=" + quantidadeOrdensUltimoDia +
				", quantidadeOrdensUltimaSemana=" + quantidadeOrdensUltimaSemana +
				", quantidadeOrdensUltimoMes=" + quantidadeOrdensUltimoMes +
				", quantidadeOrdensNesteAno=" + quantidadeOrdensNesteAno +
				", quantidadeOrdensMesEspecifico=" + quantidadeOrdensMesEspecifico +
				", quantidadeTotalClientes=" + quantidadeTotalClientes +
				", quantidadeClientesUltimoDia=" + quantidadeClientesUltimoDia +
				", quantidadeClientesUltimaSemana=" + quantidadeClientesUltimaSemana +
				", quantidadeClientesUltimoMes=" + quantidadeClientesUltimoMes +
				", quantidadeClientesNesteAno=" + quantidadeClientesNesteAno +
				", quantidadeClientesMesEspecifico=" + quantidadeClientesMesEspecifico +
				", ultimasOrdens=" + ultimasOrdens +
				'}';
	}

	public static class Builder {
		private Long quantidadeOrdensPago;
		private Long quantidadeOrdensAgendado;
		private Long quantidadeOrdensAtrasado;
		private Long quantidadeOrdensUltimaHora;
		private Long quantidadeOrdensUltimoDia;
		private Long quantidadeOrdensUltimaSemana;
		private Long quantidadeOrdensUltimoMes;
		private Long quantidadeOrdensNesteAno;
		private Long quantidadeOrdensMesEspecifico;
		private Long quantidadeTotalClientes;
		private Long quantidadeClientesUltimoDia;
		private Long quantidadeClientesUltimaSemana;
		private Long quantidadeClientesUltimoMes;
		private Long quantidadeClientesNesteAno;
		private Long quantidadeClientesMesEspecifico;
		private List<OrdemServico> ultimasOrdens;

		Builder() {
		}

		public Builder quantidadeOrdensPago(Long quantidadeOrdensPago) {
			this.quantidadeOrdensPago = quantidadeOrdensPago;
			return this;
		}

		public Builder quantidadeOrdensAgendado(Long quantidadeOrdensAgendado) {
			this.quantidadeOrdensAgendado = quantidadeOrdensAgendado;
			return this;
		}

		public Builder quantidadeOrdensAtrasado(Long quantidadeOrdensAtrasado) {
			this.quantidadeOrdensAtrasado = quantidadeOrdensAtrasado;
			return this;
		}

		public Builder quantidadeOrdensUltimaHora(Long quantidadeOrdensUltimaHora) {
			this.quantidadeOrdensUltimaHora = quantidadeOrdensUltimaHora;
			return this;
		}

		public Builder quantidadeOrdensUltimoDia(Long quantidadeOrdensUltimoDia) {
			this.quantidadeOrdensUltimoDia = quantidadeOrdensUltimoDia;
			return this;
		}

		public Builder quantidadeOrdensUltimaSemana(Long quantidadeOrdensUltimaSemana) {
			this.quantidadeOrdensUltimaSemana = quantidadeOrdensUltimaSemana;
			return this;
		}

		public Builder quantidadeOrdensUltimoMes(Long quantidadeOrdensUltimoMes) {
			this.quantidadeOrdensUltimoMes = quantidadeOrdensUltimoMes;
			return this;
		}

		public Builder quantidadeOrdensNesteAno(Long quantidadeOrdensNesteAno) {
			this.quantidadeOrdensNesteAno = quantidadeOrdensNesteAno;
			return this;
		}

		public Builder quantidadeOrdensMesEspecifico(Long quantidadeOrdensMesEspecifico) {
			this.quantidadeOrdensMesEspecifico = quantidadeOrdensMesEspecifico;
			return this;
		}

		public Builder quantidadeTotalClientes(Long quantidadeTotalClientes) {
			this.quantidadeTotalClientes = quantidadeTotalClientes;
			return this;
		}

		public Builder quantidadeClientesUltimoDia(Long quantidadeClientesUltimoDia) {
			this.quantidadeClientesUltimoDia = quantidadeClientesUltimoDia;
			return this;
		}

		public Builder quantidadeClientesUltimaSemana(Long quantidadeClientesUltimaSemana) {
			this.quantidadeClientesUltimaSemana = quantidadeClientesUltimaSemana;
			return this;
		}

		public Builder quantidadeClientesUltimoMes(Long quantidadeClientesUltimoMes) {
			this.quantidadeClientesUltimoMes = quantidadeClientesUltimoMes;
			return this;
		}

		public Builder quantidadeClientesNesteAno(Long quantidadeClientesNesteAno) {
			this.quantidadeClientesNesteAno = quantidadeClientesNesteAno;
			return this;
		}

		public Builder quantidadeClientesMesEspecifico(Long quantidadeClientesMesEspecifico) {
			this.quantidadeClientesMesEspecifico = quantidadeClientesMesEspecifico;
			return this;
		}

		public Builder ultimasOrdens(List<OrdemServico> ultimasOrdens) {
			this.ultimasOrdens = ultimasOrdens;
			return this;
		}

		public OficinaInfoDTO build() {
			return new OficinaInfoDTO(
					quantidadeOrdensPago,
					quantidadeOrdensAgendado,
					quantidadeOrdensAtrasado,
					quantidadeOrdensUltimaHora,
					quantidadeOrdensUltimoDia,
					quantidadeOrdensUltimaSemana,
					quantidadeOrdensUltimoMes,
					quantidadeOrdensNesteAno,
					quantidadeOrdensMesEspecifico,
					quantidadeTotalClientes,
					quantidadeClientesUltimoDia,
					quantidadeClientesUltimaSemana,
					quantidadeClientesUltimoMes,
					quantidadeClientesNesteAno,
					quantidadeClientesMesEspecifico,
					ultimasOrdens
			);
		}

		public String toString() {
			return "OficinaInfoDTO.Builder(quantidadeOrdensPago=" + this.quantidadeOrdensPago + ", quantidadeOrdensAgendado=" + this.quantidadeOrdensAgendado + ", quantidadeOrdensAtrasado=" + this.quantidadeOrdensAtrasado + ", quantidadeOrdensUltimaHora=" + this.quantidadeOrdensUltimaHora + ", quantidadeOrdensUltimoDia=" + this.quantidadeOrdensUltimoDia + ", quantidadeOrdensUltimaSemana=" + this.quantidadeOrdensUltimaSemana + ", quantidadeOrdensUltimoMes=" + this.quantidadeOrdensUltimoMes + ", quantidadeOrdensNesteAno=" + this.quantidadeOrdensNesteAno + ", quantidadeOrdensMesEspecifico=" + this.quantidadeOrdensMesEspecifico + ", quantidadeTotalClientes=" + this.quantidadeTotalClientes + ", quantidadeClientesUltimoDia=" + this.quantidadeClientesUltimoDia + ", quantidadeClientesUltimaSemana=" + this.quantidadeClientesUltimaSemana + ", quantidadeClientesUltimoMes=" + this.quantidadeClientesUltimoMes + ", quantidadeClientesNesteAno=" + this.quantidadeClientesNesteAno + ", quantidadeClientesMesEspecifico=" + this.quantidadeClientesMesEspecifico + ", ultimasOrdens=" + this.ultimasOrdens + ")";
		}
	}
}
