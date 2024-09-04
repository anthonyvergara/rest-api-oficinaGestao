package com.api.oficina.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.api.oficina.model.Cliente;
import com.api.oficina.model.Oficina;
import com.fasterxml.jackson.annotation.JsonFormat;

@Component
public class ClienteDTO implements Dto<ClienteDTO,Cliente>{
	
	private String nome;
	private String sobrenome;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;
	private String email;
	private Long numeroDrive;
	private Long numeroPassaporte;
	private Long numeroRg;
	private Oficina oficina;
	
	private String nomeOficina;
	
	@Override
	public ClienteDTO convertToDto(Cliente cliente) {
		
		BeanUtils.copyProperties(cliente, this);
		
		return this;
	}
	
	@Override
	public List<ClienteDTO> listToDto(List<Cliente> cliente) {
		List<ClienteDTO> lista = new ArrayList<ClienteDTO>();
		
		for(Cliente c : cliente) {
			lista.add(this.convertToDto(c));
		}
		
		return lista;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getNumeroDrive() {
		return numeroDrive;
	}

	public void setNumeroDrive(Long numeroDrive) {
		this.numeroDrive = numeroDrive;
	}

	public Long getNumeroPassaporte() {
		return numeroPassaporte;
	}

	public void setNumeroPassaporte(Long numeroPassaporte) {
		this.numeroPassaporte = numeroPassaporte;
	}

	public Long getNumeroRg() {
		return numeroRg;
	}

	public void setNumeroRg(Long numeroRg) {
		this.numeroRg = numeroRg;
	}

	public String getNomeOficina() {
		return nomeOficina;
	}

	public void setOficina(Oficina oficina) {
		
		this.nomeOficina = oficina.getNomeOficina();
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
}
