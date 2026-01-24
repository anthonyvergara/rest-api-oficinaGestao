package com.api.oficina.dto;

import java.util.List;

import com.api.oficina.model.OrdemServico;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
}

