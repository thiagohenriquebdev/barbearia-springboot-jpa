package com.salaoAPI.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salaoAPI.Entidades.InicioAtendimento;
import com.salaoAPI.repositories.InicioAtendimentoRepository;

@Service
public class OrderService {

	@Autowired
	private InicioAtendimentoRepository repository;
	
	public List <InicioAtendimento> findAll() {
		return repository.findAll();
	}
	
	public InicioAtendimento FindById(Long id) {
		Optional<InicioAtendimento> obj = repository.findById(id);
		return obj.get();
	}
}
