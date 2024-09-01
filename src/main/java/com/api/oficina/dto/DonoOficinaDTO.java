package com.api.oficina.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.api.oficina.model.DonoOficina;
import com.fasterxml.jackson.annotation.JsonFormat;

public class DonoOficinaDTO {
	
	private String nome;
	private String sobrenome;
	private String usuario;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;
	private String email;

	public static DonoOficinaDTO convert(DonoOficina dono) {
		DonoOficinaDTO dto = new DonoOficinaDTO();
		
		BeanUtils.copyProperties(dono, dto,"senha","oficina");
		return dto;
	}
	
	public static List<DonoOficinaDTO> convertList(List<DonoOficina> dono){
		List<DonoOficinaDTO> listDTO = new ArrayList<DonoOficinaDTO>();
		
		dono.forEach(value ->{
			listDTO.add(DonoOficinaDTO.convert(value));
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
