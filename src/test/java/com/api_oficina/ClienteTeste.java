package com.api_oficina;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.api.oficina.model.Cliente;

public class ClienteTeste {
	
	@Test
	void criarCliente() {
		Cliente c = new Cliente();
		c.setNome("Anthony");
		
	}

}
