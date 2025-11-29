package com.api.oficina.serviceImpl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import com.api.oficina.dto.ClienteDTO;
import com.api.oficina.dto.Dto;
import com.api.oficina.model.Cliente;
import com.api.oficina.model.Endereco;
import com.api.oficina.model.Oficina;
import com.api.oficina.model.Telefone;
import com.api.oficina.infrastructure.repository.ClienteRepository;
import com.api.oficina.infrastructure.repository.OficinaRepository;
import com.api.oficina.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService{
	
	@PersistenceContext
	private EntityManager entityManager;

	private final OficinaRepository OFICINA_REPOSITORY;
	private final ClienteRepository CLIENTE_REPOSITORY;
	private final Dto DTO;
	
	public ClienteServiceImpl(OficinaRepository oficina, ClienteRepository clienteRepository, ClienteDTO clienteDTO) {
		this.OFICINA_REPOSITORY = oficina;
		this.CLIENTE_REPOSITORY = clienteRepository;
		this.DTO = clienteDTO;
	}
	
	@Override
	public List<ClienteDTO> listAll() {
		List<Cliente> cliente = (List<Cliente>) this.CLIENTE_REPOSITORY.findAll();
		Collections.sort(cliente);
		return DTO.listToDto(cliente);
	}
	
	@Override
	public List<ClienteDTO> findByNameContains(String name){
		List<Cliente> cliente = this.CLIENTE_REPOSITORY.findByNomeContainingIgnoreCase(name);
		Collections.sort(cliente);
		return DTO.listToDto(cliente);
	}

	@Transactional
	@Override
	public ClienteDTO save(Cliente cliente, Long idOficina) {

		Oficina findOficina = this.OFICINA_REPOSITORY.findById(idOficina)
				.orElseThrow(()-> new IllegalArgumentException("Oficina não existe!"));
		
		cliente.setOficina(findOficina);

		for(int i = 0; i< cliente.getEndereco().size(); i++) {
			Endereco endereco = cliente.getEndereco().get(i);
			endereco.setPessoa(cliente);

			if(endereco.getId_endereco() != null) {
				cliente.getEndereco().set(i, entityManager.merge(endereco));
			}
		}

		for(int i = 0; i< cliente.getTelefone().size(); i++) {
			Telefone telefone = cliente.getTelefone().get(i);
			telefone.setPessoa(cliente);

			if(telefone.getId_telefone() != null) {
				cliente.getTelefone().set(i, entityManager.merge(telefone));
			}
		}

		this.CLIENTE_REPOSITORY.save(cliente);
		return (ClienteDTO) DTO.convertToDto(cliente);
	}

	@Override
	public ClienteDTO findById(Long id) {
		
		Optional<Cliente> find = this.CLIENTE_REPOSITORY.findById(id);
		if(find.isEmpty()) {
			throw new RuntimeException();
		}

		return (ClienteDTO) DTO.convertToDto(find.get());
	}


	@Transactional
	@Override
	public ClienteDTO update(Cliente cliente, Long idOficina) {

		Oficina findOficina = this.OFICINA_REPOSITORY.findById(idOficina)
				.orElseThrow(() -> new IllegalArgumentException("Oficina não existe!"));

		cliente.setOficina(findOficina);
		
		// Processar telefones - merge se existentes, ou criar novos
		for(int i = 0; i<cliente.getTelefone().size(); i++) {
			Telefone telefone = cliente.getTelefone().get(i);
			telefone.setPessoa(cliente);

			// Se o telefone tem ID, fazer merge para reattach
			if(telefone.getId_telefone() != null) {
				cliente.getTelefone().set(i, entityManager.merge(telefone));
			}
		}

		// Processar endereços - merge se existentes, ou criar novos
		for(int i = 0; i<cliente.getEndereco().size(); i++) {
			Endereco endereco = cliente.getEndereco().get(i);
			endereco.setPessoa(cliente);

			// Se o endereço tem ID, fazer merge para reattach
			if(endereco.getId_endereco() != null) {
				cliente.getEndereco().set(i, entityManager.merge(endereco));
			}
		}

		this.CLIENTE_REPOSITORY.save(cliente);
		
		return (ClienteDTO) DTO.convertToDto(cliente);
	}
	
	@Override
	public void deleteById(Long id) {
		
		Optional<Cliente> findCliente = this.CLIENTE_REPOSITORY.findById(id);
		if(findCliente.isEmpty()) {
			throw new RuntimeException();
		}else {
			this.CLIENTE_REPOSITORY.deleteById(id);
		}
		
	}

	@Override
	public ClienteDTO findByIdOrdemServico(Long idOrdemServico) {
		Cliente cliente = this.CLIENTE_REPOSITORY.findByIdOrdemServico(idOrdemServico);
		return (ClienteDTO) DTO.convertToDto(cliente);
	}

}
