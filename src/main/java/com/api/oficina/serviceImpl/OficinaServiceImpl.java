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
		
		if(dono.isEmpty()) {
			throw new RuntimeException();
		}else {
			List<DonoOficina> listaDono = new ArrayList<DonoOficina>();
			listaDono.add(dono.get());
			oficina.setDonoOficina(listaDono);
			return this.oficinaRepository.save(oficina);
		}
	}

	@Override
	public Oficina update(Long idOficina, Long idDonoOficina) {
		Optional<Oficina> oficina = this.oficinaRepository.findById(idOficina);
		Optional<DonoOficina> donoOficina = this.oficinaRepository.findDonoOficinaById(idDonoOficina);
		
		if(oficina.isEmpty() || donoOficina.isEmpty()) {
			throw new RuntimeException();
		}else {
			List<DonoOficina> addDono = new ArrayList<DonoOficina>();
			List<DonoOficina> listaDonosCadastrados = oficina.get().getDonoOficina();
			for(DonoOficina donos : listaDonosCadastrados) {
				if(donos.equals(donoOficina.get())) {
					throw new RuntimeException();
				}
				addDono.add(donos);
			}
			addDono.add(donoOficina.get());
			
			oficina.get().setDonoOficina(addDono);
			return this.oficinaRepository.save(oficina.get());
		}
	}

}
