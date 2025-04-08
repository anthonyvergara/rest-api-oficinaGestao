package com.api.oficina.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.api.oficina.model.Cliente;
import com.api.oficina.model.Endereco;
import com.api.oficina.model.Telefone;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
public class ClienteDTO implements Dto<ClienteDTO,Cliente>{
	
	private Long id;
	private String nome;
	private String sobrenome;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;
	private String email;
	private Long numeroDrive;
	private Long numeroPassaporte;
	private Long numeroRg;
	private List<Telefone> telefone;
	private List<Endereco> endereco;
	
	public ClienteDTO() {
		
	}
	
	public ClienteDTO(Long id, String nome, String sobrenome, LocalDate dataNascimento, String email,
            Long numeroDrive, Long numeroPassaporte, Long numeroRg,
            List<Telefone> telefone, List<Endereco> endereco) {
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.dataNascimento = dataNascimento;
		this.email = email;
		this.numeroDrive = numeroDrive;
		this.numeroPassaporte = numeroPassaporte;
		this.numeroRg = numeroRg;
		this.telefone = telefone;
		this.endereco = endereco;
	}

	
	@Override
	public ClienteDTO convertToDto(Cliente cliente) {
		BeanUtils.copyProperties(cliente, this);
		
		return this;
	}
	
	@Override
	public List<ClienteDTO> listToDto(List<Cliente> cliente) {
		List<ClienteDTO> lista = new ArrayList<>();
		for(int i = 0; i<cliente.size(); i++) {
			ClienteDTO clienteDTO = new ClienteDTO();
			lista.add(clienteDTO.convertToDto(cliente.get(i)));
		}
		
		return lista;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public List<Telefone> getTelefone() {
		return telefone;
	}

	public void setTelefone(List<Telefone> telefone) {
		this.telefone = telefone;
	}

	public List<Endereco> getEndereco() {
		return endereco;
	}

	public void setEndereco(List<Endereco> endereco) {
		this.endereco = endereco;
	}
	
	

}
