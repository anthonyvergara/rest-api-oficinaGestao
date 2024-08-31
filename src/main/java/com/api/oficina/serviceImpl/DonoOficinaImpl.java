package com.api.oficina.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.oficina.model.DonoOficina;
import com.api.oficina.repository.DonoOficinaRepository;
import com.api.oficina.service.DonoOficinaService;

@Service
public class DonoOficinaImpl implements DonoOficinaService{

	@Autowired
	private DonoOficinaRepository donoOficina;
	
	@Override
	public List<DonoOficina> listAll() {
		
		List<DonoOficina> list = (List<DonoOficina>) donoOficina.findAll();
		
		return list;
	}

	@Override
	public DonoOficina updateDados(DonoOficina dono) {
		
		for(int i = 0; i < dono.getTelefone().size(); i++){
			dono.getTelefone().get(i).setPessoa(dono);
		}
		for(int i = 0; i < dono.getEndereco().size(); i++){
			dono.getEndereco().get(i).setPessoa(dono);
		}
		
		return donoOficina.save(dono);
	}

	@Override
	public DonoOficina save(DonoOficina dono) {
		for(int i = 0; i < dono.getTelefone().size(); i++){
			dono.getTelefone().get(i).setPessoa(dono);
		}
		for(int i = 0; i < dono.getEndereco().size(); i++){
			dono.getEndereco().get(i).setPessoa(dono);
		}
		
		this.donoOficina.save(dono);
		return dono;
	}

	@Override
	public void deleteById(Long id) {
		
		Optional<DonoOficina> dono = this.donoOficina.findById(id);
		if(dono.isEmpty()) {
			throw new RuntimeException();
		}else {
			this.donoOficina.deleteById(id);
		}
		
	}

}
