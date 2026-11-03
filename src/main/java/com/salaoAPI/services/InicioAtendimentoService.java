package com.salaoAPI.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.salaoAPI.entidades.InicioAtendimento;
import com.salaoAPI.repositories.InicioAtendimentoRepository;
import com.salaoAPI.services.exceptions.ResourceNotFoundException;

@Service
public class InicioAtendimentoService {

	
	private final InicioAtendimentoRepository repository;
	
	
	public InicioAtendimentoService (InicioAtendimentoRepository repository) {
		this.repository=repository;
	}
	
	
	//Mostra Os Clientes Na FiLA
	public List <InicioAtendimento> findAll() {
		return repository.buscarTodosComItens();
	}
	
	//Chamada do proximo Cliente Baseado nas Regras De FIFO , Chamando Pelo ID.
	public InicioAtendimento findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
	}
}
