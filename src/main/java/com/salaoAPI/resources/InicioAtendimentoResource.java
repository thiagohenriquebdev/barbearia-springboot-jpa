package com.salaoAPI.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.salaoAPI.entidades.InicioAtendimento;
import com.salaoAPI.services.InicioAtendimentoService;

@RestController
@RequestMapping (value = "/orders")
public class InicioAtendimentoResource {
	
	@Autowired
	private InicioAtendimentoService service;
	
	@GetMapping
	public ResponseEntity<List<InicioAtendimento>> findAll() {
		List <InicioAtendimento> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping (value = "/{id}")
	public ResponseEntity<InicioAtendimento> findById(@PathVariable Long id) {
		InicioAtendimento obj =service.findById(id);
		return ResponseEntity.ok().body(obj);
		
	}
	
	

}
