package com.api.oficina.serviceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.api.oficina.dto.Dto;
import com.api.oficina.dto.OficinaDTO;
import com.api.oficina.dto.OficinaInfoDTO;
import com.api.oficina.dto.OficinaResponseDTO;
import com.api.oficina.infrastructure.repository.ClienteRepository;
import com.api.oficina.infrastructure.repository.OrdemServicoRepository;
import com.api.oficina.mapper.OficinaMapper;
import com.api.oficina.model.DonoOficina;
import com.api.oficina.model.Oficina;
import com.api.oficina.model.OrdemServico;
import com.api.oficina.modelEnum.Status;
import com.api.oficina.infrastructure.repository.OficinaRepository;
import com.api.oficina.service.OficinaService;

@Service
public class OficinaServiceImpl implements OficinaService{

	private final OficinaRepository OFICINA_REPOSITORY;
	private final Dto DTO;
	private final OficinaMapper OFICINA_MAPPER;
	private final OrdemServicoRepository ORDEM_SERVICO_REPOSITORY;
	private final ClienteRepository CLIENTE_REPOSITORY;

	public OficinaServiceImpl(OficinaRepository oficinaRepository, OficinaDTO oficinaDTO, OficinaMapper oficinaMapper,
							  OrdemServicoRepository ordemServicoRepository, ClienteRepository clienteRepository) {
		this.OFICINA_REPOSITORY = oficinaRepository;
		this.DTO = oficinaDTO;
		this.OFICINA_MAPPER = oficinaMapper;
		this.ORDEM_SERVICO_REPOSITORY = ordemServicoRepository;
		this.CLIENTE_REPOSITORY = clienteRepository;
	}

	@Override
	public List<OficinaDTO> listAll() {
		List<Oficina> lista = (List<Oficina>) this.OFICINA_REPOSITORY.findAll();

		return this.DTO.listToDto(lista);
	}

	@Override
	public OficinaDTO save(Oficina oficina, Long idDonoOficina) {

		Optional<DonoOficina> dono = Optional.of(this.OFICINA_REPOSITORY.findDonoOficinaById(idDonoOficina)
				.orElseThrow(()-> new IllegalArgumentException("Oficina não existe!")));

		if(dono.isEmpty() || oficina.getNomeOficina().isBlank()) {
			throw new RuntimeException();
		}else {
			List<DonoOficina> listaDono = new ArrayList<DonoOficina>();
			listaDono.add(dono.get());
			oficina.setDonoOficina(listaDono);
			this.OFICINA_REPOSITORY.save(oficina);

			return (OficinaDTO)this.DTO.convertToDto(oficina);
		}
	}

	@Override
	public OficinaDTO update(Oficina oficina) {
		if(oficina.getNomeOficina().isBlank()) {
			throw new RuntimeException();
		}else {
			if(oficina.getEndereco() != null) {
				oficina.getEndereco().setOficina(oficina);
			}

			this.OFICINA_REPOSITORY.save(oficina);
			return (OficinaDTO)this.DTO.convertToDto(oficina);
		}
	}

	@Override
	public void deleteDonoFromOficina(Long idOficina, Long idDono) {
		Optional<Oficina> oficina = this.OFICINA_REPOSITORY.findById(idOficina);
		Optional<DonoOficina> dono = this.OFICINA_REPOSITORY.findDonoOficinaById(idDono);

		if(oficina.isEmpty() || dono.isEmpty()) {
			throw new RuntimeException();
		}else {
			for(DonoOficina d : oficina.get().getDonoOficina()) {
				if(d.getId().longValue() == idDono) {
					oficina.get().getDonoOficina().remove(d);
				}
			}
			this.OFICINA_REPOSITORY.save(oficina.get());
		}

	}

	@Override
	public OficinaResponseDTO getById(Long id) {
		Oficina oficina = this.OFICINA_REPOSITORY.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("Oficina não encontrada com ID: " + id));

		return OFICINA_MAPPER.toResponseDTO(oficina);
	}

	@Override
	public OficinaInfoDTO getInfo(Long oficinaId, String periodo, Integer mes, Integer ano, Integer quantidadeUltimasOrdens) {
		// Verificar se a oficina existe
		this.OFICINA_REPOSITORY.findById(oficinaId)
			.orElseThrow(() -> new IllegalArgumentException("Oficina não encontrada com ID: " + oficinaId));

		OficinaInfoDTO info = new OficinaInfoDTO();

		// Contadores de ordens por status
		info.setQuantidadeOrdensPago(ORDEM_SERVICO_REPOSITORY.countByOficinaIdAndStatus(oficinaId, Status.PAGO.getCode()));
		info.setQuantidadeOrdensAgendado(ORDEM_SERVICO_REPOSITORY.countByOficinaIdAndStatus(oficinaId, Status.AGENDADO.getCode()));
		info.setQuantidadeOrdensAtrasado(ORDEM_SERVICO_REPOSITORY.countByOficinaIdAndStatus(oficinaId, Status.ATRASADO.getCode()));

		// Calcular contadores de ordens por período
		LocalDateTime now = LocalDateTime.now();

		if (periodo != null) {
			switch (periodo.toLowerCase()) {
				case "ultima_hora":
					info.setQuantidadeOrdensUltimaHora(
						ORDEM_SERVICO_REPOSITORY.countByOficinaIdAndCreatedAtAfter(oficinaId, now.minusHours(1)));
					break;
				case "ultimo_dia":
					info.setQuantidadeOrdensUltimoDia(
						ORDEM_SERVICO_REPOSITORY.countByOficinaIdAndCreatedAtAfter(oficinaId, now.minusDays(1)));
					break;
				case "ultima_semana":
					info.setQuantidadeOrdensUltimaSemana(
						ORDEM_SERVICO_REPOSITORY.countByOficinaIdAndCreatedAtAfter(oficinaId, now.minusWeeks(1)));
					break;
				case "ultimo_mes":
					info.setQuantidadeOrdensUltimoMes(
						ORDEM_SERVICO_REPOSITORY.countByOficinaIdAndCreatedAtAfter(oficinaId, now.minusMonths(1)));
					break;
				case "este_ano":
					info.setQuantidadeOrdensNesteAno(
						ORDEM_SERVICO_REPOSITORY.countByOficinaIdAndCreatedAtAfter(oficinaId,
							LocalDateTime.of(now.getYear(), 1, 1, 0, 0)));
					break;
			}
		}

		// Contar ordens por mês específico
		if (mes != null && ano != null) {
			info.setQuantidadeOrdensMesEspecifico(
				ORDEM_SERVICO_REPOSITORY.countByOficinaIdAndYearAndMonth(oficinaId, ano, mes));
		}

		// Contadores de clientes
		info.setQuantidadeTotalClientes(CLIENTE_REPOSITORY.countByOficinaId(oficinaId));

		if (periodo != null) {
			switch (periodo.toLowerCase()) {
				case "ultimo_dia":
					info.setQuantidadeClientesUltimoDia(
						CLIENTE_REPOSITORY.countByOficinaIdAndCreatedAtAfter(oficinaId, now.minusDays(1)));
					break;
				case "ultima_semana":
					info.setQuantidadeClientesUltimaSemana(
						CLIENTE_REPOSITORY.countByOficinaIdAndCreatedAtAfter(oficinaId, now.minusWeeks(1)));
					break;
				case "ultimo_mes":
					info.setQuantidadeClientesUltimoMes(
						CLIENTE_REPOSITORY.countByOficinaIdAndCreatedAtAfter(oficinaId, now.minusMonths(1)));
					break;
				case "este_ano":
					info.setQuantidadeClientesNesteAno(
						CLIENTE_REPOSITORY.countByOficinaIdAndCreatedAtAfter(oficinaId,
							LocalDateTime.of(now.getYear(), 1, 1, 0, 0)));
					break;
			}
		}

		// Contar clientes por mês específico
		if (mes != null && ano != null) {
			info.setQuantidadeClientesMesEspecifico(
				CLIENTE_REPOSITORY.countByOficinaIdAndYearAndMonth(oficinaId, ano, mes));
		}

		// Buscar últimas ordens
		if (quantidadeUltimasOrdens != null && quantidadeUltimasOrdens > 0) {
			List<OrdemServico> ultimasOrdens = ORDEM_SERVICO_REPOSITORY.findTopNByOficinaIdOrderByCreatedAtDesc(
				oficinaId, PageRequest.of(0, quantidadeUltimasOrdens));
			info.setUltimasOrdens(ultimasOrdens);
		}

		return info;
	}
}
