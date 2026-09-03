package com.salaoAPI.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.salaoAPI.Entidades.Categoria;
import com.salaoAPI.Entidades.Cliente;
import com.salaoAPI.Entidades.Order;
import com.salaoAPI.Entidades.enums.OrderStatus;
import com.salaoAPI.repositories.CategoriaRepository;
import com.salaoAPI.repositories.ClienteRepository;
import com.salaoAPI.repositories.OrderRepository;

@Configuration
@Profile ("test")
public class TesteConfig implements CommandLineRunner {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	@Override
	public void run(String... args) throws Exception {
		
		Categoria c1 = new Categoria(null,"Item_Cabelo");
		Categoria c2 = new Categoria(null,"Item_Outros");
		
		categoriaRepository.saveAll(Arrays.asList(c1,c2));
		
		Cliente u1 = new Cliente(null, "Maria",Instant.now());
		Cliente u2 = new Cliente(null, "Ana",Instant.now());
		Cliente u3 = new Cliente(null, "Joao",Instant.now());
		
		clienteRepository.saveAll(Arrays.asList(u1,u2,u3));
		
		Order o1 = new Order(null ,OrderStatus.AGUARDANDO_FILA,u1);
		Order o2 = new Order(null ,OrderStatus.ATENDIMENTO,u1);
		Order o3 = new Order(null ,OrderStatus.FINALIZADO,u2);

		orderRepository.saveAll(Arrays.asList(o1,o2,o3));
	}

}
