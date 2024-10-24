package com.api_oficina;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.api.oficina.dto.ClienteDTO;
import com.api.oficina.model.Cliente;
import com.api.oficina.model.Oficina;
import com.api.oficina.model.Telefone;
import com.api.oficina.repository.ClienteRepository;
import com.api.oficina.repository.OficinaRepository;
import com.api.oficina.serviceImpl.ClienteServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ClienteTeste {
	
	@Mock
	private ClienteRepository clienteRepository;
	@Mock
	private OficinaRepository oficinaRepository;
	@Mock
	private ClienteDTO dto;
	
	@Spy
	@InjectMocks
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
	
	@Test
	void deveCriarUmNovoCliente() {
		Oficina oficina = new Oficina();
		oficina.setId(1L);
		oficina.setNomeOficina("Careca Motors");
		when(oficinaRepository.findById(1L)).thenReturn(Optional.of(oficina));
		
		when(clienteRepository.save(cliente)).thenReturn(cliente);
		
		when(dto.convertToDto(cliente)).thenReturn(clienteDTO);
		
		ClienteDTO dt = clienteService.save(cliente, 1L);
		Assertions.assertEquals(cliente.getNome(), dt.getNome());
		
	}
	
	@Test
	void deveFazerBuscaDeCliente() {
	
		Mockito.when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
		
		Mockito.when(dto.convertToDto(cliente)).thenReturn(clienteDTO);
		
		ClienteDTO dt = clienteService.findById(1L);
		
		Assertions.assertEquals(cliente.getNome(), dt.getNome());
	}
	
	@Test 
	void deveRetornarTodosOsClientes(){
		List<Cliente> clientes = new ArrayList<>();
		clientes.add(cliente);
		when(clienteRepository.findAll()).thenReturn(clientes);
		List<ClienteDTO> clientesDTO = new ArrayList<>();
		clientesDTO.add(clienteDTO);
		when(dto.listToDto(clientes)).thenReturn(clientesDTO);
		
		Assertions.assertNotNull(clienteService.listAll());
	}
	
	@Test
	void deveRemoverCliente() {
		
		Mockito.when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
		
		doNothing().when(clienteRepository).deleteById(1L);
		
		clienteRepository.deleteById(1L);
		
		Mockito.when(clienteRepository.findById(1L)).thenReturn(Optional.empty());
		
		Assertions.assertTrue(clienteRepository.findById(1L).isEmpty());
	}
	
	@Test
	void deveAtualizarCliente() {
		
		verifyNoInteractions(clienteService);
		Mockito.when(clienteRepository.save(cliente)).thenReturn(cliente);
		when(dto.convertToDto(cliente)).thenReturn(clienteDTO);
		
		ClienteDTO clientedt = clienteService.update(cliente);
		Assertions.assertEquals(clientedt.getNome(), cliente.getNome());
		
		verify(clienteRepository).save(cliente);
		
	}

}
