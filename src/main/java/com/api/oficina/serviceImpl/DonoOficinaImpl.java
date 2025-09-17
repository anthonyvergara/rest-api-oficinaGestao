package com.api.oficina.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.api.oficina.dto.DonoOficinaDTO;
import com.api.oficina.dto.Dto;
import com.api.oficina.model.DonoOficina;
import com.api.oficina.infrastructure.repository.DonoOficinaRepository;
import com.api.oficina.service.DonoOficinaService;

@Service
public class DonoOficinaImpl implements DonoOficinaService{

	private final DonoOficinaRepository DONO_OFICINA;
	private final Dto DTO;
	
	public DonoOficinaImpl(DonoOficinaRepository donoOficina, DonoOficinaDTO donoOficinaDTO) {
		this.DONO_OFICINA = donoOficina;
		this.DTO = donoOficinaDTO;
	}
	
	@Override
	public List<DonoOficinaDTO> listAll() {
		
		List<DonoOficina> listDTO = (List<DonoOficina>) this.DONO_OFICINA.findAll();
		
		return this.DTO.listToDto(listDTO);
	}

	@Override
	public DonoOficinaDTO updateDados(DonoOficina dono) {
		
		for(int i = 0; i < dono.getTelefone().size(); i++){
			dono.getTelefone().get(i).setPessoa(dono);
		}
		for(int i = 0; i < dono.getEndereco().size(); i++){
			dono.getEndereco().get(i).setPessoa(dono);
		}
		DONO_OFICINA.save(dono);
		
		return (DonoOficinaDTO) this.DTO.convertToDto(dono);
	}

	@Override
	public DonoOficinaDTO save(DonoOficina dono) {
		for(int i = 0; i < dono.getTelefone().size(); i++){
			dono.getTelefone().get(i).setPessoa(dono);
		}
		for(int i = 0; i < dono.getEndereco().size(); i++){
			dono.getEndereco().get(i).setPessoa(dono);
		}
		
		this.DONO_OFICINA.save(dono);
		return (DonoOficinaDTO) this.DTO.convertToDto(dono);
	}

	@Override
	public void deleteById(Long id) {
		
		Optional<DonoOficina> dono = this.DONO_OFICINA.findById(id);
		if(dono.isEmpty()) {
			throw new RuntimeException();
		}else {
			this.DONO_OFICINA.deleteById(id);
		}
		
	}

}
