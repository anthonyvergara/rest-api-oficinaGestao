package com.api.oficina.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.api.oficina.model.Pessoa;
import com.api.oficina.model.Telefone;
import com.api.oficina.infrastructure.repository.TelefoneRepository;
import com.api.oficina.service.TelefoneService;

@Service
public class TelefoneServiceImpl implements TelefoneService {
	
	private TelefoneRepository TELEFONE_REPOSITORY;
	
	public TelefoneServiceImpl(TelefoneRepository telefoneRepository) {
		this.TELEFONE_REPOSITORY = telefoneRepository;
	}

	@Override
	public List<Telefone> listAll() {
		List<Telefone>lista = (List<Telefone>) this.TELEFONE_REPOSITORY.findAll();
		return lista;
	}

	@Override
	public List<Telefone> listById(Long id) {
		List<Telefone>lista = this.TELEFONE_REPOSITORY.listById(id);
		if(lista.isEmpty()) {
			throw new RuntimeException();
		}else {
			return lista;
		}
	}

	@Override
	public Telefone save(Telefone telefone, Long idPessoa) {
		Pessoa pessoa = this.TELEFONE_REPOSITORY.findPessoaId(idPessoa);
		if(pessoa == null) {
			throw new RuntimeException();
		}else {
			telefone.setPessoa(pessoa);
			return this.TELEFONE_REPOSITORY.save(telefone);
		}
	}

	@Override
	public Telefone update(Telefone telefone) {
		Optional<Telefone> tel = this.TELEFONE_REPOSITORY.findById(telefone.getId_telefone());
		if(tel.isEmpty()) {
			throw new RuntimeException();
		}else {
			telefone.setPessoa(tel.get().getPessoa());
			return this.TELEFONE_REPOSITORY.save(telefone);
		}
	}

	@Override
	public void deleteById(Long id) {
		Optional<Telefone>findId = this.TELEFONE_REPOSITORY.findById(id);
		if(findId.isEmpty()) {
			throw new RuntimeException();
		}else {
			this.TELEFONE_REPOSITORY.deleteById(id);
		}

	}

}
