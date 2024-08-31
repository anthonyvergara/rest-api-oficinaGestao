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
	
	@Autowired
	private OficinaEnderecoRepo enderecoRepository;

	@Override
	public OficinaEndereco getInfoEndereco(Long id) {
		Collection<OficinaEndereco> end = new HashSet<>();
		this.enderecoRepository.findAll().forEach(value -> end.add(value));
		
		for(OficinaEndereco e : end) {
			if(e.getOficina().getId().longValue() == id) {
				return e;
			}
		}
		throw new RuntimeException();
	}

	@Override
	public OficinaEndereco save(OficinaEndereco endereco, Long idOficina) {
		Optional<Oficina> findOficina = this.enderecoRepository.findOficina(idOficina);
		
		if(findOficina.isEmpty()) {
			throw new RuntimeException();
		}else {
			endereco.setOficina(findOficina.get());
			return this.enderecoRepository.save(endereco);
		}
	}

	@Override
	public void deleteById(Long id) {
		Optional<OficinaEndereco> findEndereco = this.enderecoRepository.findById(id);
		if(findEndereco.isEmpty()) {
			throw new RuntimeException();
		}else {
			this.enderecoRepository.deleteById(id);
		}
		
	}

	@Override
	public List<OficinaEndereco> listAll() {
		List<OficinaEndereco> lista = (List<OficinaEndereco>) this.enderecoRepository.findAll();
		return lista;
	}

	@Override
	public OficinaEndereco update(OficinaEndereco endereco) {
		return this.enderecoRepository.save(endereco);
	}

}
