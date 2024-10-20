package com.api.oficina.serviceImpl;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.oficina.model.Oficina;
import com.api.oficina.model.OficinaEndereco;
import com.api.oficina.repository.OficinaEnderecoRepo;
import com.api.oficina.service.OficinaEnderecoService;

@Service
public class OficinaEnderecoImpl implements OficinaEnderecoService{
	
	private OficinaEnderecoRepo ENDERECO_REPOSITORY;
	
	public OficinaEnderecoImpl(OficinaEnderecoRepo enderecoRepository) {
		this.ENDERECO_REPOSITORY = enderecoRepository;
	}

	@Override
	public OficinaEndereco getInfoEndereco(Long id) {
		Collection<OficinaEndereco> end = new HashSet<>();
		this.ENDERECO_REPOSITORY.findAll().forEach(value -> end.add(value));
		
		for(OficinaEndereco e : end) {
			if(e.getOficina().getId().longValue() == id) {
				return e;
			}
		}
		throw new RuntimeException();
	}

	@Override
	public OficinaEndereco save(OficinaEndereco endereco, Long idOficina) {
		Optional<Oficina> findOficina = this.ENDERECO_REPOSITORY.findOficina(idOficina);
		
		if(findOficina.isEmpty()) {
			throw new RuntimeException();
		}else {
			endereco.setOficina(findOficina.get());
			return this.ENDERECO_REPOSITORY.save(endereco);
		}
	}

	@Override
	public void deleteById(Long id) {
		Optional<OficinaEndereco> findEndereco = this.ENDERECO_REPOSITORY.findById(id);
		if(findEndereco.isEmpty()) {
			throw new RuntimeException();
		}else {
			this.ENDERECO_REPOSITORY.deleteById(id);
		}
		
	}

	@Override
	public List<OficinaEndereco> listAll() {
		List<OficinaEndereco> lista = (List<OficinaEndereco>) this.ENDERECO_REPOSITORY.findAll();
		return lista;
	}

	@Override
	public OficinaEndereco update(OficinaEndereco endereco) {
		return this.ENDERECO_REPOSITORY.save(endereco);
	}

}
