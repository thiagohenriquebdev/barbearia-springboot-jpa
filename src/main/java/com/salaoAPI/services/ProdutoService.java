package com.salaoAPI.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salaoAPI.entidades.Cliente;
import com.salaoAPI.entidades.Produto;
import com.salaoAPI.entidades.enums.StatusAtendimento;
import com.salaoAPI.entidades.enums.StatusPagamento;
import com.salaoAPI.repositories.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	public List <Produto> findAll() {
		return produtoRepository.findAll();
	}
	
	public Produto FindById(Long id) {
		Optional<Produto> obj = produtoRepository.findById(id);
		return obj.get();
	}
	
	public void consumirProduto(long produtoId , Integer quantidade) {
		Produto produto = produtoRepository.findById(produtoId).orElseThrow(() -> new RuntimeException("Produto Nao Encontrado"));
		
		if (produto.getQuantidade() < quantidade ) {
			throw new RuntimeException("Quantidade Insuficiente");
		}
		
		produto.setQuantidade(produto.getQuantidade() - quantidade);
		produtoRepository.save(produto);
	}
}
