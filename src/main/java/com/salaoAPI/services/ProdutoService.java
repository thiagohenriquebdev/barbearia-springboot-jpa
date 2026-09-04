package com.salaoAPI.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salaoAPI.Entidades.Produto;
import com.salaoAPI.repositories.ProdutoRepository;

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
}
