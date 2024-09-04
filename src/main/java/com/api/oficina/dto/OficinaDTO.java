package com.api.oficina.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.api.oficina.model.Oficina;

@Component
public class OficinaDTO implements Dto<OficinaDTO, Oficina>{
	
	private String nomeOficina;
	private Long companyNumber;
	private Long vatNumber;


	@Override
	public OficinaDTO convertToDto(Oficina oficina) {
		BeanUtils.copyProperties(oficina, this);
		return this;
	}

	@Override
	public List<OficinaDTO> listToDto(List<Oficina> oficina) {
		List<OficinaDTO> lista = new ArrayList<OficinaDTO>();
		
		oficina.forEach(value ->{
			lista.add(this.convertToDto(value));
		});
		return lista;
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
