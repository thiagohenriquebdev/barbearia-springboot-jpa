package com.salaoAPI.config;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.salaoAPI.Entidades.Cliente;
import com.salaoAPI.Entidades.enums.OrderStatus;
import com.salaoAPI.repositories.CategoriaRepository;
import com.salaoAPI.repositories.ClienteRepository;
import com.salaoAPI.repositories.InicioAtendimentoRepository;
import com.salaoAPI.repositories.OrderItemRepository;
import com.salaoAPI.repositories.ProdutoRepository;

@Configuration
@Profile ("test")
public class TesteConfig implements CommandLineRunner {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private InicioAtendimentoRepository orderRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;

	@Override
	public void run(String... args) throws Exception {
		
		List <Cliente> cliente = new ArrayList<>();
		Cliente obj = new Cliente(null,"Thiago", OrderStatus.AGUARDANDO_FILA, null,Instant.now(), null, null);
		Cliente obj2 = new Cliente(null,"diego", OrderStatus.AGUARDANDO_FILA, null, null, null, null);
		Cliente obj3 = new Cliente(null,"douglas", OrderStatus.AGUARDANDO_FILA, null, null, null, null);
		Cliente obj4 = new Cliente(null,"rafael", OrderStatus.AGUARDANDO_FILA, null, null, null, null);
		
		cliente.addAll(List.of(obj,obj2,obj3,obj4));
		
		clienteRepository.saveAll(cliente);
		
	}

}
