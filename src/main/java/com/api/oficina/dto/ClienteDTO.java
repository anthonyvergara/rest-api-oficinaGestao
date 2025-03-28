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

@Component @Setter @Getter @NoArgsConstructor @AllArgsConstructor
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

}
