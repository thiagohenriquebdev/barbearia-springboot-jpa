package com.salaoAPI.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.salaoAPI.entidades.Categoria;
import com.salaoAPI.entidades.Produto;
import com.salaoAPI.services.CategoriaService;
import com.salaoAPI.services.exceptions.DataBaseException;
import com.salaoAPI.services.exceptions.ResourceNotFoundException;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping (value = "/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service;
	
	@GetMapping
	public ResponseEntity<List<Categoria>> findall() {
		return ResponseEntity.ok().body(service.findAll());
	}
	
	@GetMapping (value = "/{id}")
	public ResponseEntity<Categoria> FindById(@PathVariable Long id) {
		Categoria obj =service.FindById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<Categoria> insert(@RequestBody Categoria obj) {
		obj = service.insert(obj);
		URI uri =ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@PutMapping (value = "/{id}")
	public ResponseEntity<Categoria> update(@PathVariable Long id ,@RequestBody Categoria obj) {
		obj=service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
	
	@DeleteMapping (value = "/{id}")
	public ResponseEntity<Void> delete (@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	


}
