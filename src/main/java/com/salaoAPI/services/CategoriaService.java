package com.salaoAPI.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.salaoAPI.entidades.Categoria;
import com.salaoAPI.repositories.CategoriaRepository;
import com.salaoAPI.services.exceptions.DataBaseException;
import com.salaoAPI.services.exceptions.ResourceNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;
	
	
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
	
	//alterar alguma categoria
	public Categoria update (Long id ,Categoria obj) {
		Categoria entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		updateData(entity,obj);
		return repository.save(entity);
	}
	
	private void updateData(Categoria entity,Categoria obj) {
		entity.setName(obj.getName());
	}
	
	
	//deletar uma categoria
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
