package com.api.oficina.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.oficina.model.Cliente;
import com.api.oficina.repository.ClienteRepository;
import com.api.oficina.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService{
	
	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public List<Cliente> listAll() {
		List<Cliente> cliente = (List<Cliente>) this.clienteRepository.findAll();
		return cliente;
	}

	@Override
	public Cliente save(Cliente cliente) {
		for(int i = 0; i< cliente.getEndereco().size(); i++) {
			cliente.getEndereco().get(i).setPessoa(cliente);
		}
		for(int i = 0; i< cliente.getTelefone().size(); i++) {
			cliente.getTelefone().get(i).setPessoa(cliente);
		}
		
		return this.clienteRepository.save(cliente);
	}

	@Override
	public Cliente findById(Long id) {
		
		Optional<Cliente> find = this.clienteRepository.findById(id);
		if(find.isEmpty()) {
			throw new RuntimeException();
		}
		return find.get();
	}

	@Override
	public Cliente update(Cliente cliente) {
		
		for(int i = 0; i<cliente.getTelefone().size(); i++) {
			cliente.getTelefone().get(i).setPessoa(cliente);
		}
		for(int i = 0; i<cliente.getEndereco().size(); i++) {
			cliente.getEndereco().get(i).setPessoa(cliente);
		}
		
		return this.clienteRepository.save(cliente);
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
