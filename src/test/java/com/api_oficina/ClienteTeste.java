package com.api_oficina;

import static org.mockito.Mockito.doNothing;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mockito;

import com.api.oficina.dto.ClienteDTO;
import com.api.oficina.model.Cliente;
import com.api.oficina.repository.ClienteRepository;
import com.api.oficina.repository.OficinaRepository;
import com.api.oficina.serviceImpl.ClienteServiceImpl;

public class ClienteTeste {
	
	private ClienteRepository clienteRepository;
	private OficinaRepository oficinaRepo;
	private ClienteDTO dto;
	private ClienteServiceImpl clienteService;
	
	private static Cliente cliente;
	private static ClienteDTO clienteDTO;
	
	@BeforeAll
	static void configurarObetoClienteAndDTO() {
		cliente = new Cliente();
		cliente.setId(1L);
		cliente.setNome("Anthony");
		cliente.setEmail("anthonyverg@icloud.com");
		
		clienteDTO = new ClienteDTO(cliente.getId(),cliente.getNome(),null,null,cliente.getEmail(),null,null,null);
	}
	
	@BeforeEach
	void configuracao() {
		clienteRepository = Mockito.mock(ClienteRepository.class);
		oficinaRepo = Mockito.mock(OficinaRepository.class);
		dto = Mockito.mock(ClienteDTO.class);
		clienteService = new ClienteServiceImpl(oficinaRepo, clienteRepository, dto);
	}
	
	@Test
	void deveBuscaDeCliente() {
	
		Mockito.when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
		
		Mockito.when(dto.convertToDto(cliente)).thenReturn(clienteDTO);
		
		ClienteDTO dt = clienteService.findById(1L);
		Assertions.assertEquals(cliente.getNome(), dt.getNome());
	}
	
	@Test
	void deveRemoverCliente() {
		
		Mockito.when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
		
		doNothing().when(clienteRepository).deleteById(1L);
		
		Mockito.when(clienteRepository.existsById(1L)).thenReturn(false);
		
		clienteRepository.deleteById(1L);
		
		Mockito.when(clienteRepository.findById(1L)).thenReturn(Optional.empty());
		
		Assertions.assertTrue(clienteRepository.findById(1L).isEmpty());
	}

}
