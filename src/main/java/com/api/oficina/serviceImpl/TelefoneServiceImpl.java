package com.api.oficina.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.oficina.model.Pessoa;
import com.api.oficina.model.Telefone;
import com.api.oficina.repository.TelefoneRepository;
import com.api.oficina.service.TelefoneService;

@Service
public class TelefoneServiceImpl implements TelefoneService {
	
	@Autowired
	private TelefoneRepository telefoneRepository;

	@Override
	public List<Telefone> listAll() {
		List<Telefone>lista = (List<Telefone>) this.telefoneRepository.findAll();
		return lista;
	}

	@Override
	public List<Telefone> listById(Long id) {
		List<Telefone>lista = this.telefoneRepository.listById(id);
		if(lista.isEmpty()) {
			throw new RuntimeException();
		}else {
			return lista;
		}
	}

	@Override
	public Telefone save(Telefone telefone, Long idPessoa) {
		Pessoa pessoa = this.telefoneRepository.findPessoaId(idPessoa);
		if(pessoa == null) {
			throw new RuntimeException();
		}else {
			telefone.setPessoa(pessoa);
			return this.telefoneRepository.save(telefone);
		}
	}

	@Override
	public Telefone update(Telefone telefone) {
		Optional<Telefone> tel = this.telefoneRepository.findById(telefone.getId_telefone());
		if(tel.isEmpty()) {
			throw new RuntimeException();
		}else {
			telefone.setPessoa(tel.get().getPessoa());
			return this.telefoneRepository.save(telefone);
		}
	}

	@Override
	public void deleteById(Long id) {
		Optional<Telefone>findId = this.telefoneRepository.findById(id);
		if(findId.isEmpty()) {
			throw new RuntimeException();
		}else {
			this.telefoneRepository.deleteById(id);
		}

	}

}
