package com.api.oficina.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.oficina.dto.Dto;
import com.api.oficina.dto.OficinaDTO;
import com.api.oficina.model.DonoOficina;
import com.api.oficina.model.Oficina;
import com.api.oficina.repository.OficinaRepository;
import com.api.oficina.service.OficinaService;

@Service
public class OficinaServiceImpl implements OficinaService{
	
	private final OficinaRepository OFICINA_REPOSITORY;
	private final Dto DTO;
	
	public OficinaServiceImpl(OficinaRepository oficinaRepository, OficinaDTO oficinaDTO) {
		this.OFICINA_REPOSITORY = oficinaRepository;
		this.DTO = oficinaDTO;
	}

	@Override
	public List<OficinaDTO> listAll() {
		List<Oficina> lista = (List<Oficina>) this.OFICINA_REPOSITORY.findAll();
		
		return this.DTO.listToDto(lista);
	}

	@Override
	public OficinaDTO save(Oficina oficina, Long idDonoOficina) {
		
		Optional<DonoOficina> dono = Optional.of(this.OFICINA_REPOSITORY.findDonoOficinaById(idDonoOficina)
				.orElseThrow(()-> new IllegalArgumentException("Oficina não existe!")));
		
		if(dono.isEmpty() || oficina.getNomeOficina().isBlank()) {
			throw new RuntimeException();
		}else {
			List<DonoOficina> listaDono = new ArrayList<DonoOficina>();
			listaDono.add(dono.get());
			oficina.setDonoOficina(listaDono);
			this.OFICINA_REPOSITORY.save(oficina);
			
			return (OficinaDTO)this.DTO.convertToDto(oficina);
		}
	}

	@Override
	public OficinaDTO update(Oficina oficina) {
		
		if(oficina.getNomeOficina().isBlank()) {
			throw new RuntimeException();
		}else {
			this.OFICINA_REPOSITORY.save(oficina);
			return (OficinaDTO)this.DTO.convertToDto(oficina);
		}
	}

	@Override
	public void deleteDonoFromOficina(Long idOficina, Long idDono) {
		Optional<Oficina> oficina = this.OFICINA_REPOSITORY.findById(idOficina);
		Optional<DonoOficina> dono = this.OFICINA_REPOSITORY.findDonoOficinaById(idDono);
		
		if(oficina.isEmpty() || dono.isEmpty()) {
			throw new RuntimeException();
		}else {
			for(DonoOficina d : oficina.get().getDonoOficina()) {
				if(d.getId().longValue() == idDono) {
					oficina.get().getDonoOficina().remove(d);
				}
			}
			this.OFICINA_REPOSITORY.save(oficina.get());
		}
		
	}

}
