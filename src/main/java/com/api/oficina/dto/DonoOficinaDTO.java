package com.api.oficina.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.api.oficina.model.DonoOficina;
import com.fasterxml.jackson.annotation.JsonFormat;

@Component
public class DonoOficinaDTO implements Dto<DonoOficinaDTO,DonoOficina>{
	
	private String nome;
	private String sobrenome;
	private String usuario;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;
	private String email;
	

	@Override
	public DonoOficinaDTO convertToDto(DonoOficina dono) {
		BeanUtils.copyProperties(dono, this,"senha");
		return this;
	}

	@Override
	public List<DonoOficinaDTO> listToDto(List<DonoOficina> dono) {
		List<DonoOficinaDTO> listDTO = new ArrayList<DonoOficinaDTO>();
		
		dono.forEach(value ->{
			DonoOficinaDTO donoDTO = new DonoOficinaDTO();
			listDTO.add(donoDTO.convertToDto(value));
		});
		return listDTO;
	}

	public String getNome() {
		return nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}
	
	public String getUsuario() {
		return usuario;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
