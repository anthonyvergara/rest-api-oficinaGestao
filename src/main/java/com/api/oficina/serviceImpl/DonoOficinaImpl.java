package com.api.oficina.serviceImpl;

import java.util.List;

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

}
