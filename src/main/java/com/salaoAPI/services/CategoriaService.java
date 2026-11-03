package com.salaoAPI.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.salaoAPI.entidades.Categoria;
import com.salaoAPI.repositories.CategoriaRepository;
import com.salaoAPI.services.exceptions.ResourceNotFoundException;

@Service
public class CategoriaService {

	private final CategoriaRepository repository;
	
	public CategoriaService (CategoriaRepository repository) {
		this.repository=repository;
		
	}
	//listar todas categorias
	public List <Categoria> findAll() {
		return repository.findAll();
	}
	
	//buscar categoria 
	public Categoria findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	//Insert de categoria
	public Categoria insert(Categoria insert) {
		return repository.save(insert);
	}
	
	//Atualiza o nome de uma Categoria
	public Categoria update(Long id ,Categoria obj) {
		Categoria entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		
		entity.setName(obj.getName());	
		return repository.save(entity);
	}
	
	
	//deletar uma categoria
	public void delete(Long id) {
		repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		repository.deleteById(id);
	}
}
