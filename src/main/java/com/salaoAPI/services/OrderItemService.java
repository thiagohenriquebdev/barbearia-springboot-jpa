package com.salaoAPI.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salaoAPI.entidades.InicioAtendimento;
import com.salaoAPI.entidades.OrderItem;
import com.salaoAPI.entidades.Produto;
import com.salaoAPI.entidades.pk.OrderItemPK;
import com.salaoAPI.repositories.ClienteRepository;
import com.salaoAPI.repositories.InicioAtendimentoRepository;
import com.salaoAPI.repositories.OrderItemRepository;
import com.salaoAPI.repositories.ProdutoRepository;
import com.salaoAPI.services.exceptions.DataBaseException;
import com.salaoAPI.services.exceptions.ResourceNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class OrderItemService {
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private InicioAtendimentoRepository inicioAtendimentoRepository;
	
	@Transactional
	public OrderItem registraConsumo(long atendimentoId , long produtoId ,Integer quantidade) {
		InicioAtendimento  atendimento =inicioAtendimentoRepository.findById(atendimentoId).orElseThrow(() -> new ResourceNotFoundException(atendimentoId));
		
		Produto produto = produtoRepository.findById(produtoId).orElseThrow(() -> new ResourceNotFoundException(produtoId));
		
		if (produto.getQuantidade() < quantidade) {
			throw new DataBaseException("Estoque Insuficiente do Produto" + produto.getNome());
		}
		
		produto.setQuantidade(produto.getQuantidade() - quantidade);
		produtoRepository.save(produto);
		
		OrderItemPK pk = new OrderItemPK();
		
		pk.setOrder(atendimento);
		pk.setProduto(produto);
		
		Optional<OrderItem> existente = orderItemRepository.findById(pk);
		
		if (existente.isPresent()) {
			OrderItem item = existente.get();
			item.setQuantidade(item.getQuantidade() + quantidade);
			return orderItemRepository.save(item);
		}
		
		OrderItem novoItem = new OrderItem(atendimento, produto, quantidade, produto.getValor());
		return orderItemRepository.save(novoItem);			
	}
	
	@Transactional
	public void cancelar (long atendimentoId , long produtoId , Integer quantidade) {
		OrderItemPK pk = new OrderItemPK();
		
		InicioAtendimento  atendimento =inicioAtendimentoRepository.findById(atendimentoId).orElseThrow(() -> new ResourceNotFoundException(atendimentoId));
		
		Produto produto = produtoRepository.findById(produtoId).orElseThrow(() -> new ResourceNotFoundException(produtoId));
		
		pk.setOrder(atendimento);
		pk.setProduto(produto);
		
		OrderItem item = orderItemRepository.findById(pk).orElseThrow(() -> new ResourceNotFoundException("Produto Nao Encontrado"));
		
		//Devolvendo Produto Pro Estoque
		produto.setQuantidade(produto.getQuantidade() + quantidade);
		produtoRepository.save(produto);
		
		if (item.getQuantidade() > quantidade) {
			item.setQuantidade(item.getQuantidade() - quantidade);
			orderItemRepository.save(item);
		}
		else {
			orderItemRepository.delete(item);
		}
		
	}

}
