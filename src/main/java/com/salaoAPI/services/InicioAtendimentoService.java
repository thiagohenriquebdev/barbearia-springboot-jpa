package com.salaoAPI.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salaoAPI.entidades.InicioAtendimento;
import com.salaoAPI.repositories.InicioAtendimentoRepository;
import com.salaoAPI.services.exceptions.ResourceNotFoundException;

@Service
public class InicioAtendimentoService {

	@Autowired
	private InicioAtendimentoRepository repository;
	
	public List <InicioAtendimento> findAll() {
		return repository.buscarTodosComItens();
	}
	
	public InicioAtendimento findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
	}
}
