package com.api.oficina.mapper;

import org.springframework.stereotype.Component;

import com.api.oficina.dto.OficinaResponseDTO;
import com.api.oficina.model.Oficina;
import com.api.oficina.model.OficinaEndereco;

@Component
public class OficinaMapper {

	public OficinaResponseDTO toResponseDTO(Oficina oficina) {
		if (oficina == null) {
			return null;
		}

		OficinaResponseDTO dto = new OficinaResponseDTO();
		dto.setId(oficina.getId());
		dto.setNomeOficina(oficina.getNomeOficina());
		dto.setCompanyNumber(oficina.getCompanyNumber());
		dto.setVatNumber(oficina.getVatNumber());
		dto.setEmail(oficina.getEmail());
		dto.setTelefone(oficina.getTelefone());

		// Converter endereço se existir
		if (oficina.getEndereco() != null) {
			OficinaEndereco enderecoEntity = oficina.getEndereco();
			OficinaResponseDTO.Endereco enderecoDTO = new OficinaResponseDTO.Endereco(
				enderecoEntity.getId(),
				enderecoEntity.getRua(),
				enderecoEntity.getNumero(),
				enderecoEntity.getCidade(),
				enderecoEntity.getPostcode()
			);
			dto.setEndereco(enderecoDTO);
		}

		return dto;
	}
}

