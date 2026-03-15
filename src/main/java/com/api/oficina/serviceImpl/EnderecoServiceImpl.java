package com.api.oficina.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.api.oficina.model.Endereco;
import com.api.oficina.model.Pessoa;
import com.api.oficina.infrastructure.repository.EnderecoRepository;
import com.api.oficina.service.EnderecoService;

@Service
public class EnderecoServiceImpl implements EnderecoService{
	
	private final EnderecoRepository ENDERECO_REPOSITORY;
	
	public EnderecoServiceImpl(EnderecoRepository enderecoRepository) {
		this.ENDERECO_REPOSITORY = enderecoRepository;
	}
	
	
	@Override
	public List<Endereco> listAll() {
		List<Endereco> lista = (List<Endereco>) ENDERECO_REPOSITORY.findAll();
		return lista;
	}

	@Override
	public List<Endereco> listById(Long id) {
		List<Endereco> lista = ENDERECO_REPOSITORY.listById(id);
		if(lista.isEmpty()) {
			throw new RuntimeException();
		}
		return lista;
	}

	@Override
	public Endereco save(Endereco endereco, Long idPessoa) {
		Pessoa pessoa = this.ENDERECO_REPOSITORY.findPessoaId(idPessoa);
		if(pessoa == null) {
			throw new RuntimeException();
		}else {
			endereco.setPessoa(pessoa);
			return this.ENDERECO_REPOSITORY.save(endereco);
		}
	}

	@Override
	public Endereco update(Endereco endereco) {
		Optional<Endereco> end = this.ENDERECO_REPOSITORY.findById(endereco.getId_endereco());
		if(end.isEmpty()) {
			throw new RuntimeException();
		}else {
			endereco.setPessoa(end.get().getPessoa());
			return this.ENDERECO_REPOSITORY.save(endereco);
		}
	}

	@Override
	public void deleteById(Long id) {
		Optional<Endereco> endereco = this.ENDERECO_REPOSITORY.findById(id);
		if(endereco.isEmpty()) {
			throw new RuntimeException();
		}
		ENDERECO_REPOSITORY.deleteById(id);
	}

}
