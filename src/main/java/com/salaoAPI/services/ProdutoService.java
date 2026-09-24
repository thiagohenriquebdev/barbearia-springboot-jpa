package com.salaoAPI.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.salaoAPI.entidades.Cliente;
import com.salaoAPI.entidades.Produto;
import com.salaoAPI.repositories.ProdutoRepository;
import com.salaoAPI.services.exceptions.DataBaseException;
import com.salaoAPI.services.exceptions.ResourceNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repository;
	
	public List <Produto> findAll() {
		return repository.findAll();
	}
	
	public Produto FindById(Long id) {
		Optional<Produto> obj = repository.findById(id);
		return obj.get();
	}
	
	public void consumirProduto(long produtoId , Integer quantidade) {
		Produto produto = repository.findById(produtoId).orElseThrow(() -> new RuntimeException("Produto Nao Encontrado"));
		
		if (produto.getQuantidade() < quantidade ) {
			throw new RuntimeException("Quantidade Insuficiente");
		}
		
		produto.setQuantidade(produto.getQuantidade() - quantidade);
		repository.save(produto);
	}
	
	public Produto insert(Produto insert) {
		return repository.save(insert);
	}
	
	public Produto update (Long id ,Produto obj) {
		Produto entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		updateData(entity,obj);
		return repository.save(entity);
	}
	
	private void updateData(Produto entity,Produto obj) {
		entity.setNome(obj.getNome());
		entity.setValor(obj.getValor());
		entity.setQuantidade(obj.getQuantidade());
	}
	
	public void delete(Long id) {
		repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		try {
		repository.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataBaseException(e.getMessage());
		}
	}
}
	
	

