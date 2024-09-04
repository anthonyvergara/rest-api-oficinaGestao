package com.api.oficina.dto;

import java.util.List;

import com.api.oficina.model.Cliente;
import com.api.oficina.model.Pessoa;

public interface Dto<T, E> {

	public T convertToDto(E object);
	
	public List<T> listToDto(List<E> object);
	
}
