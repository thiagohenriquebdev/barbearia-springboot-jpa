package com.salaoAPI.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.salaoAPI.Entidades.Cliente;
import com.salaoAPI.Entidades.InicioAtendimento;
import com.salaoAPI.Entidades.enums.OrderStatus;
import com.salaoAPI.Entidades.enums.StatusPagamento;
import com.salaoAPI.Entidades.enums.StatusRecebimento;
import com.salaoAPI.dto.ClienteAtendimentoResponse;
import com.salaoAPI.repositories.ClienteRepository;
import com.salaoAPI.repositories.InicioAtendimentoRepository;
import com.salaoAPI.services.exceptions.DataBaseException;
import com.salaoAPI.services.exceptions.ResourceNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private InicioAtendimentoRepository inicioRepository;
	
	@Autowired
	private ClienteRepository repository;
	
	public ClienteService(ClienteRepository repository) {
		this.repository=repository;
	}
	
	public Cliente entrarNaFila(String nome) {
		Cliente cliente = repository.findByNome(nome).orElseGet(() -> {Cliente novo = new Cliente();
		novo.setNome(nome);
		return novo;
		});
		cliente.setDataChegada(Instant.now());
		cliente.setStatusAtendimento(OrderStatus.AGUARDANDO_FILA);
		cliente.setStatusPagamento(StatusPagamento.PENDENTE);
		
		return repository.save(cliente);
	}
		
	public ClienteAtendimentoResponse chamarProximo() {
		Cliente proximo = repository.findFirstByStatusAtendimentoOrderByDataChegadaAsc(OrderStatus.AGUARDANDO_FILA)
				.orElseThrow(() -> new ResponseStatusException (HttpStatus.NOT_FOUND, "Fila Vazia"));
		
		proximo.setStatusAtendimento(OrderStatus.ATENDIMENTO);
		
		InicioAtendimento inicioAtendimento = new InicioAtendimento();
		inicioAtendimento.setInicioAtendimento(Instant.now());
		inicioAtendimento.setCliente(proximo);
		
		inicioRepository.save(inicioAtendimento);		
		repository.save(proximo);
		
		return new ClienteAtendimentoResponse(proximo.getNome() , inicioAtendimento.getInicioAtendimento());
	}
	
	public List <Cliente> listarFila () {
		return repository.findByStatusAtendimentoOrderByDataChegadaAsc(OrderStatus.AGUARDANDO_FILA);
	}
	
	
	public Cliente finalizarAtendimento (Long id , StatusRecebimento statusRecebimento) {
		Cliente cliente = repository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND , "Cliente Nao Encontrado"));
		
		if (cliente.getStatusAtendimento()!=OrderStatus.ATENDIMENTO) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST , "Cliente Nao Esta Em Atendimento ") ;
		}
	
		cliente.setStatusAtendimento(OrderStatus.FINALIZADO);
		cliente.setStatusPagamento(StatusPagamento.PAGO);
		cliente.setStatusRecebimento(statusRecebimento);
		cliente.setDataFinalizacao(Instant.now());
		return repository.save(cliente);
	}
	
	
	public List <Cliente> findAll() {
		return repository.findAll();
	}
	
	public Cliente FindById(Long id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Cliente insert (Cliente cliente) {
		return repository.save(cliente);
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
		entity.setNome(obj.getNome());
		entity.setDataChegada(obj.getDataChegada());		
	}
	
}
