package com.salaoAPI.config;

import java.time.Instant;
import java.util.Arrays;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.salaoAPI.Entidades.Categoria;
import com.salaoAPI.Entidades.Cliente;
import com.salaoAPI.Entidades.InicioAtendimento;
import com.salaoAPI.Entidades.OrderItem;
import com.salaoAPI.Entidades.Pagamento;
import com.salaoAPI.Entidades.Produto;
import com.salaoAPI.Entidades.enums.OrderStatus;
import com.salaoAPI.repositories.CategoriaRepository;
import com.salaoAPI.repositories.ClienteRepository;
import com.salaoAPI.repositories.OrderItemRepository;
import com.salaoAPI.repositories.InicioAtendimentoRepository;
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

	}

}
