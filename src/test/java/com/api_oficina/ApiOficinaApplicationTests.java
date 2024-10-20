package com.api_oficina;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.api.oficina.model.Cliente;

@SpringBootTest
class ApiOficinaApplicationTests {
	
	@Test
	void contextLoads() {
	}
	
	@Test
	void criarCliente() {
		Cliente c = new Cliente();
		c.setNome("Anthony");
		Assertions.assertEquals("a", c.getNome(), "verdade!");
	}

}
