package com.salaoAPI.services;

import java.time.Instant;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.salaoAPI.dto.ClienteAtendimentoResponse;
import com.salaoAPI.entidades.Cliente;
import com.salaoAPI.entidades.InicioAtendimento;
import com.salaoAPI.entidades.Pagamento;
import com.salaoAPI.entidades.enums.FormaPagamento;
import com.salaoAPI.entidades.enums.StatusAtendimento;
import com.salaoAPI.entidades.enums.StatusPagamento;
import com.salaoAPI.repositories.ClienteRepository;
import com.salaoAPI.repositories.InicioAtendimentoRepository;
import com.salaoAPI.services.exceptions.DataBaseException;
import com.salaoAPI.services.exceptions.ResourceNotFoundException;

@Service
public class ClienteService {

	
	private final InicioAtendimentoRepository inicioRepository;
	private final ClienteRepository clienteRepository;
	
	public ClienteService(InicioAtendimentoRepository inicioRepository,ClienteRepository clienteRepository) {
		this.clienteRepository=clienteRepository;
		this.inicioRepository=inicioRepository;
	}
	
	public Cliente entrarNaFila(String nome) {
		Cliente cliente = clienteRepository.findByNome(nome).orElseGet(Cliente :: new );
		cliente.setNome(nome);
		cliente.setDataChegada(Instant.now());
		cliente.setStatusAtendimento(StatusAtendimento.AGUARDANDO_FILA);
		return clienteRepository.save(cliente);
	}
		
	public ClienteAtendimentoResponse chamarProximo() {
		Cliente proximo = clienteRepository.findFirstByStatusAtendimentoOrderByDataChegadaAsc(StatusAtendimento.AGUARDANDO_FILA)
				.orElseThrow(() -> new ResponseStatusException (HttpStatus.NOT_FOUND, "Fila Vazia"));
		
		proximo.setStatusAtendimento(StatusAtendimento.ATENDIMENTO);
		
		InicioAtendimento inicioAtendimento = new InicioAtendimento();
		inicioAtendimento.setInicioAtendimento(Instant.now());
		inicioAtendimento.setCliente(proximo);
		
		Pagamento pagamento = new Pagamento();
		pagamento.setStatusPagamento(StatusPagamento.PENDENTE);
		pagamento.setInicioAtendimento(inicioAtendimento);
		inicioAtendimento.setPagamento(pagamento);
		
		inicioRepository.save(inicioAtendimento);		
		clienteRepository.save(proximo);
		
		return new ClienteAtendimentoResponse(proximo.getId() ,proximo.getNome(),inicioAtendimento.getInicioAtendimento());
	}
	
	public List <Cliente> listarFila () {
		return clienteRepository.findByStatusAtendimentoOrderByDataChegadaAsc(StatusAtendimento.AGUARDANDO_FILA);
	}
	
	
	public Cliente finalizarAtendimento (Long clienteId, FormaPagamento formaPagamento) {
		Cliente cliente = clienteRepository.findById(clienteId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND , "Cliente Nao Encontrado"));
		
		if (cliente.getStatusAtendimento()!=StatusAtendimento.ATENDIMENTO) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST , "Cliente Nao Esta Em Atendimento ") ;
		}
	
		InicioAtendimento atendimentoAtual = inicioRepository.findFirstByClienteOrderByIdDesc(cliente).orElseThrow(() 
				-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Atendimento Nao Encontrado"));
				
		Pagamento pagamento =atendimentoAtual.getPagamento();
		pagamento.setStatusPagamento(StatusPagamento.PAGO);
		pagamento.setFormaPagamento(formaPagamento);
		pagamento.setValorPago(atendimentoAtual.getTotal());
				
		
		cliente.setStatusAtendimento(StatusAtendimento.FINALIZADO);
		cliente.setDataFinalizacao(Instant.now());
		return clienteRepository.save(cliente);
	}
	
	
	public List <Cliente> findAll() {
		return clienteRepository.findAll();
	}
	
	public Cliente findById(Long id) {
		return clienteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Cliente insert (Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	public void delete (Long id) {
		clienteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		try {
		clienteRepository.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataBaseException(e.getMessage());
		}
	}
	
	public Cliente update (Long id ,Cliente obj) {
		Cliente entity =clienteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		updateData(entity,obj);
		return clienteRepository.save(entity);
	}

	private void updateData(Cliente entity, Cliente obj) {
		entity.setNome(obj.getNome());
		entity.setDataChegada(obj.getDataChegada());		
	}
	
}
