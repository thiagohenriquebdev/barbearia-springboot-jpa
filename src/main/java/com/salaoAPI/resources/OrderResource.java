package com.salaoAPI.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.salaoAPI.Entidades.InicioAtendimento;
import com.salaoAPI.services.OrderService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping (value = "/orders")
public class OrderResource {
	
	@Autowired
	private OrderService service;
	
	@GetMapping
	public ResponseEntity<List<InicioAtendimento>> findall() {
		List <InicioAtendimento> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping (value = "/{id}")
	public ResponseEntity<InicioAtendimento> FindById(@PathVariable Long id) {
		InicioAtendimento obj =service.FindById(id);
		return ResponseEntity.ok().body(obj);
		
	}
	
	

}
