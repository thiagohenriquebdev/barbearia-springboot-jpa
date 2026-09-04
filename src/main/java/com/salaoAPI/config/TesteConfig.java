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
import com.salaoAPI.Entidades.OrderItem;
import com.salaoAPI.Entidades.Produto;
import com.salaoAPI.Entidades.enums.OrderStatus;
import com.salaoAPI.repositories.CategoriaRepository;
import com.salaoAPI.repositories.ClienteRepository;
import com.salaoAPI.repositories.OrderItemRepository;
import com.salaoAPI.repositories.OrderRepository;
import com.salaoAPI.repositories.ProdutoRepository;

@Configuration
@Profile ("test")
public class TesteConfig implements CommandLineRunner {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;

	@Override
	public void run(String... args) throws Exception {
		
		Categoria c1 = new Categoria(null,"Item_Cabelo");
		Categoria c2 = new Categoria(null,"Item_Outros");
		
		Produto p1 = new Produto(null,"Cabelo","Corte de Cabelo", null,25.00);
		Produto p2 = new Produto(null,"Cabelo","Alinhamento de Barba", null,25.00);
		Produto p3 = new Produto(null,"Coca Lata 350ML","Lata de Refrigerante Coca 350ml", null,25.00);
		
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		categoriaRepository.saveAll(Arrays.asList(c1,c2));
		
		p1.getCategorias().add(c1);
		p2.getCategorias().add(c1);
		p3.getCategorias().add(c2);
		
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		Cliente u1 = new Cliente(null, "Maria",Instant.now());
		Cliente u2 = new Cliente(null, "Ana",Instant.now());
		Cliente u3 = new Cliente(null, "Joao",Instant.now());
		
		clienteRepository.saveAll(Arrays.asList(u1,u2,u3));
		
		Order o1 = new Order(null ,OrderStatus.AGUARDANDO_FILA,u1);
		Order o2 = new Order(null ,OrderStatus.ATENDIMENTO,u1);
		Order o3 = new Order(null ,OrderStatus.FINALIZADO,u2);

		orderRepository.saveAll(Arrays.asList(o1,o2,o3));
		
		OrderItem oi1 = new OrderItem(o1, p3, 2, p1.getValor());
		OrderItem oi2 = new OrderItem(o1, p2, 1, p1.getValor());
		OrderItem oi3 = new OrderItem(o3, p1, 1, p1.getValor());
		
		orderItemRepository.saveAll(Arrays.asList(oi1,oi2,oi3));
	}

}
