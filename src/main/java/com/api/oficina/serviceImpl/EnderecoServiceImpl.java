package com.api.oficina.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.oficina.model.Endereco;
import com.api.oficina.model.Pessoa;
import com.api.oficina.repository.EnderecoRepository;
import com.api.oficina.service.EnderecoService;

@Service
public class EnderecoServiceImpl implements EnderecoService{
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	
	@Override
	public List<Endereco> listAll() {
		List<Endereco> lista = (List<Endereco>) enderecoRepository.findAll();
		return lista;
	}

	@Override
	public List<Endereco> listById(Long id) {
		List<Endereco> lista = enderecoRepository.listById(id);
		if(lista.isEmpty()) {
			throw new RuntimeException();
		}
		return lista;
	}

	@Override
	public Endereco save(Endereco endereco, Long idPessoa) {
		Pessoa pessoa = this.enderecoRepository.findPessoaId(idPessoa);
		if(pessoa == null) {
			throw new RuntimeException();
		}else {
			endereco.setPessoa(pessoa);
			return this.enderecoRepository.save(endereco);
		}
	}

	@Override
	public Endereco update(Endereco endereco) {
		Optional<Endereco> end = this.enderecoRepository.findById(endereco.getId_endereco());
		if(end.isEmpty()) {
			throw new RuntimeException();
		}else {
			endereco.setPessoa(end.get().getPessoa());
			return this.enderecoRepository.save(endereco);
		}
	}

	@Override
	public void deleteById(Long id) {
		Optional<Endereco> endereco = this.enderecoRepository.findById(id);
		if(endereco.isEmpty()) {
			throw new RuntimeException();
		}
		enderecoRepository.deleteById(id);
	}

}
