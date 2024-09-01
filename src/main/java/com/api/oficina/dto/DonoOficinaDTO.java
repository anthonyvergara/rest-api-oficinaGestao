package com.api.oficina.dto;

import java.util.ArrayList;
import java.util.List;

import com.api.oficina.model.DonoOficina;
import com.api.oficina.model.Oficina;

public class DonoOficinaDTO {
	
	private Long id;
	private String usuario;
	private String senha;
	private List<DonoOficinaDTO> oficina = new ArrayList<DonoOficinaDTO>();
	private String nomeOficina;
	
	public DonoOficinaDTO() {}
	
	public DonoOficinaDTO(DonoOficinaDTO oficina) {
		this.oficina.add(oficina);
	}
	
	public DonoOficinaDTO(Long id, String usuario, List<DonoOficinaDTO> oficina) {
		this.id = id;
		this.usuario = usuario;
		this.oficina = oficina;
	}
	
	public static List<DonoOficinaDTO> convertObject(List<DonoOficina> dono) {
		List<DonoOficinaDTO> lista = new ArrayList<DonoOficinaDTO>();
		List<DonoOficinaDTO> oficinas = new ArrayList<DonoOficinaDTO>();
		for(DonoOficina d : dono) {
			
			for(Oficina o : d.getOficina()) {
				if(o != null) {
					DonoOficinaDTO dd = new DonoOficinaDTO();
					dd.nomeOficina = o.getNomeOficina();
					oficinas.add(dd);
				}
			}
			
			lista.add(new DonoOficinaDTO(d.getId(), d.getUsuario(), oficinas));
		}
		return lista;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsuario() {
		return usuario;
	}
	public String getSenha() {
		return senha;
	}
	public List<DonoOficinaDTO> getOficina() {
		return oficina;
	}
	
	
}
