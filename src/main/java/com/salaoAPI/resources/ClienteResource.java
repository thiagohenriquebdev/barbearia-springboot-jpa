package com.salaoAPI.resources;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.salaoAPI.dto.ClienteAtendimentoResponse;
import com.salaoAPI.dto.EntrarFilaRequest;
import com.salaoAPI.dto.PagamentoRecebido;
import com.salaoAPI.entidades.Cliente;
import com.salaoAPI.services.ClienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping (value = "/clientes")
public class ClienteResource {
	
	private final ClienteService service;
	
	public ClienteResource (ClienteService service) {
		this.service=service;
	}
	
	@PostMapping ("/entrar")
	public Cliente entrar(@RequestBody EntrarFilaRequest request) {
		return service.entrarNaFila(request.nome());
	}
	
	@PostMapping("/proximo")
	public ClienteAtendimentoResponse proximo() {
		return service.chamarProximo();
	}
	
	@PostMapping ("/finalizar/{id}")
	public ResponseEntity<Cliente> finalizar (@PathVariable Long id ,
			@RequestBody @Valid PagamentoRecebido pagamento) {
		Cliente cliente = service.finalizarAtendimento(id , pagamento.formaPagamento());
		return ResponseEntity.ok().body(cliente);
	}
	
	@GetMapping
	public List<Cliente> listar (){
		return service.listarFila();
	}
	
	@GetMapping ("/all")
	public ResponseEntity<List<Cliente>> findAll() {
		List <Cliente> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping (value = "/{id}")
	public ResponseEntity<Cliente> findById(@PathVariable Long id) {
		Cliente obj =service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@DeleteMapping (value = "/{id}")
	public ResponseEntity<Void> delete (@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping (value = "/{id}")
	public ResponseEntity<Cliente> update(@PathVariable Long id ,@RequestBody Cliente obj) {
		obj=service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
	
	

}
