package com.api.oficina.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.oficina.model.DonoOficina;
import com.api.oficina.model.Oficina;
import com.api.oficina.repository.OficinaRepository;
import com.api.oficina.service.OficinaService;

@Service
public class OficinaServiceImpl implements OficinaService{
	
	@Autowired
	private OficinaRepository oficinaRepository;

	@Override
	public List<Oficina> listAll() {
		List<Oficina> lista = (List<Oficina>) this.oficinaRepository.findAll();
		return lista;
	}

	@Override
	public Oficina save(Oficina oficina, Long idDonoOficina) {
		
		Optional<DonoOficina> dono = this.oficinaRepository.findDonoOficinaById(idDonoOficina);
		
		if(dono.isEmpty() || oficina.getNomeOficina().isBlank()) {
			throw new RuntimeException();
		}else {
			List<DonoOficina> listaDono = new ArrayList<DonoOficina>();
			listaDono.add(dono.get());
			oficina.setDonoOficina(listaDono);
			return this.oficinaRepository.save(oficina);
		}
	}

	@Override
	public Oficina update(Oficina oficina) {
		
		if(oficina.getNomeOficina().isBlank()) {
			throw new RuntimeException();
		}else {
			return this.oficinaRepository.save(oficina);
		}
	}

	@Override
	public void deleteDonoFromOficina(Long idOficina, Long idDono) {
		Optional<Oficina> oficina = this.oficinaRepository.findById(idOficina);
		Optional<DonoOficina> dono = this.oficinaRepository.findDonoOficinaById(idDono);
		
		if(oficina.isEmpty() || dono.isEmpty()) {
			throw new RuntimeException();
		}else {
			for(DonoOficina d : oficina.get().getDonoOficina()) {
				if(d.getId().longValue() == idDono) {
					oficina.get().getDonoOficina().remove(d);
				}
			}
			this.oficinaRepository.save(oficina.get());
		}
		
	}

}
