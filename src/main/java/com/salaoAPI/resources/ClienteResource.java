package com.salaoAPI.resources;

import java.time.LocalTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.salaoAPI.Entidades.Cliente;

@RestController
@RequestMapping (value = "/clientes")
public class ClienteResource {
	
	@GetMapping
	public ResponseEntity<Cliente> findall() {
		Cliente u = new Cliente(1L, "thiago", LocalTime.now());
		return ResponseEntity.ok().body(u);
	}

}
