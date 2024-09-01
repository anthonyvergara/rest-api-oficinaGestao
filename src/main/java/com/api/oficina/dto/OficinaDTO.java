package com.api.oficina.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.api.oficina.model.Oficina;

public class OficinaDTO {
	
	private String nomeOficina;
	private Long companyNumber;
	private Long vatNumber;

	public static OficinaDTO convert(Oficina oficina) {
		OficinaDTO dto = new OficinaDTO();
		BeanUtils.copyProperties(oficina, dto);
		return dto;
	}
	
	public static List<OficinaDTO> convertList(List<Oficina> oficina){
		List<OficinaDTO> dto = new ArrayList<OficinaDTO>();
		oficina.forEach(value ->{
			dto.add(OficinaDTO.convert(value));
		});
		return dto;
	}

	public String getNomeOficina() {
		return nomeOficina;
	}

	public void setNomeOficina(String nomeOficina) {
		this.nomeOficina = nomeOficina;
	}

	public Long getCompanyNumber() {
		return companyNumber;
	}

	public void setCompanyNumber(Long companyNumber) {
		this.companyNumber = companyNumber;
	}

	public Long getVatNumber() {
		return vatNumber;
	}

	public void setVatNumber(Long vatNumber) {
		this.vatNumber = vatNumber;
	}
	
}
