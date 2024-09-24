package com.api.oficina.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.api.oficina.dto.ClienteDTO;
import com.api.oficina.dto.Dto;
import com.api.oficina.model.Cliente;
import com.api.oficina.model.Oficina;
import com.api.oficina.repository.ClienteRepository;
import com.api.oficina.repository.OficinaRepository;
import com.api.oficina.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService{
	
	private final OficinaRepository oficinaRepository;
	private final ClienteRepository clienteRepository;
	private final Dto dto;
	
	public ClienteServiceImpl(OficinaRepository oficina, ClienteRepository clienteRepository, ClienteDTO clienteDTO) {
		this.oficinaRepository = oficina;
		this.clienteRepository = clienteRepository;
		this.dto = clienteDTO;
	}
	
	@Override
	public List<ClienteDTO> listAll() {
		List<Cliente> cliente = (List<Cliente>) this.clienteRepository.findAll();
		
		return dto.listToDto(cliente);
	}

	@Override
	public ClienteDTO save(Cliente cliente, Long idOficina) {
		
		Optional<Oficina> findOficina = this.oficinaRepository.findById(idOficina);
		
		if(findOficina.isEmpty()) {
			throw new RuntimeException();
		}else {
			cliente.setOficina(findOficina.get());
			
			for(int i = 0; i< cliente.getEndereco().size(); i++) {
				cliente.getEndereco().get(i).setPessoa(cliente);
			}
			for(int i = 0; i< cliente.getTelefone().size(); i++) {
				cliente.getTelefone().get(i).setPessoa(cliente);
			}
		}
		
		this.clienteRepository.save(cliente);
		return (ClienteDTO) dto.convertToDto(cliente);
	}

	@Override
	public ClienteDTO findById(Long id) {
		
		Optional<Cliente> find = this.clienteRepository.findById(id);
		if(find.isEmpty()) {
			throw new RuntimeException();
		}else {
			return (ClienteDTO) dto.convertToDto(find.get());
		}
		
	}

	@Override
	public ClienteDTO update(Cliente cliente) {
		
		for(int i = 0; i<cliente.getTelefone().size(); i++) {
			cliente.getTelefone().get(i).setPessoa(cliente);
		}
		for(int i = 0; i<cliente.getEndereco().size(); i++) {
			cliente.getEndereco().get(i).setPessoa(cliente);
		}
		this.clienteRepository.save(cliente);
		
		return (ClienteDTO) dto.convertToDto(cliente);
	}

	@Override
	public void deleteById(Long id) {
		
		Optional<Cliente> findCliente = this.clienteRepository.findById(id);
		if(findCliente.isEmpty()) {
			throw new RuntimeException();
		}else {
			this.clienteRepository.deleteById(id);
		}
		
	}

}
