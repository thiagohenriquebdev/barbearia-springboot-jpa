package com.salaoAPI.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.salaoAPI.entidades.Categoria;
import com.salaoAPI.entidades.Produto;
import com.salaoAPI.repositories.CategoriaRepository;
import com.salaoAPI.repositories.ClienteRepository;
import com.salaoAPI.repositories.InicioAtendimentoRepository;
import com.salaoAPI.repositories.OrderItemRepository;
import com.salaoAPI.repositories.ProdutoRepository;
import com.salaoAPI.resources.ProdutoResource;

@Configuration
@Profile ("test")
public class TesteConfig implements CommandLineRunner {
	
	private final ProdutoResource produtoResource;

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

	TesteConfig(ProdutoResource produtoResource) {
		this.produtoResource = produtoResource;
	}

	@Override
	public void run(String... args) throws Exception {
		
		List <Categoria> categoria = new ArrayList<>();
		
		Categoria c1 = new Categoria(null,"Produtos Cabelo");
		Categoria c2 = new Categoria(null,"Produtos Utilitarios");
		
		categoria.addAll((List.of(c1,c2)));
		categoriaRepository.saveAll(categoria);
		
		List<Produto> produto = new ArrayList<>();
		
		Produto p1 = new Produto(null,"Coca","Lata Coca Cola 250ml", null,5.50,10);
		Produto p2 = new Produto(null,"Cerveja","Lata Cerveja  500ml", null,8.50,10);
		Produto p3 = new Produto(null,"Corte Cabelo","Corte", null,25.00,null);
		Produto p4 = new Produto(null,"Barba","Alinhamento de Barba", null,15.00,null);
		
		produto.addAll(List.of(p1,p2,p3,p4));
		produtoRepository.saveAll(produto);
		
		
		
		
	}

}
