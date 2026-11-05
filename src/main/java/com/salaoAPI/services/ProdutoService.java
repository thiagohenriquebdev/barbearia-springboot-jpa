package com.salaoAPI.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.salaoAPI.dto.CategoriaRequest;
import com.salaoAPI.entidades.Categoria;
import com.salaoAPI.entidades.Produto;
import com.salaoAPI.repositories.CategoriaRepository;
import com.salaoAPI.repositories.ProdutoRepository;
import com.salaoAPI.services.exceptions.ResourceNotFoundException;

@Service
public class ProdutoService {

	private final ProdutoRepository produtoRepository;
	private final CategoriaRepository categoriaRepository;
	
	public ProdutoService(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository) {
		this.produtoRepository = produtoRepository;
		this.categoriaRepository=categoriaRepository;
	}

	//Lista de Produtos 
	public List <Produto> findAll() {
		return produtoRepository.findAll();
	}
	
	//Buscar Produtos 
	public Produto findById(Long id) {
		return produtoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	//Adicionar Produtos  
	public Produto adicionarProduto(Produto obj) {
		
		Long categoriaID =obj.getCategoria()
				.iterator().next().getId();
		
		Categoria categoria = categoriaRepository.findById(categoriaID)
				.orElseThrow(() -> new RuntimeException("Id Invalido!"));
		
		Produto produto = new Produto();
		
		produto.setNome(obj.getNome());
		produto.setPreco(obj.getPreco());
		produto.getCategoria().add(categoria);
		
		
		return produtoRepository.save(produto);
	}
	
	//Alterar Um Produto
	public Produto update (Long id ,Produto obj) {
		Produto entity = produtoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id));

		entity.setNome(obj.getNome());
		entity.setPreco(obj.getPreco());
		entity.setQuantidade(obj.getQuantidade());
		entity.setControlarEstoque(obj.getControlarEstoque());

		return produtoRepository.save(entity);
	}

	public void delete(Long id) {
		produtoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));

		produtoRepository.deleteById(id);
	}
}
	
	

