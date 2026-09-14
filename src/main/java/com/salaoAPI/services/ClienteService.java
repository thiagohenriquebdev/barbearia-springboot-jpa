package com.salaoAPI.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.salaoAPI.Entidades.Cliente;
import com.salaoAPI.repositories.ClienteRepository;
import com.salaoAPI.services.exceptions.DataBaseException;
import com.salaoAPI.services.exceptions.ResourceNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;
	
	public List <Cliente> findAll() {
		return repository.findAll();
	}
	
	public Cliente FindById(Long id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Cliente insert (Cliente obj) {
		return repository.save(obj);
	}
	
	public void delete (Long id) {
		repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		try {
		repository.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataBaseException(e.getMessage());
		}
	}
	
	public Cliente update (Long id ,Cliente obj) {
		repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		Cliente entity = repository.getReferenceById(id);
		updateData(entity,obj);
		return repository.save(entity);
	}

	private void updateData(Cliente entity, Cliente obj) {
		entity.setName(obj.getName());
		entity.setDataChegada(obj.getDataChegada());		
	}
	
}
