package com.salaoAPI.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.salaoAPI.entidades.Produto;
import com.salaoAPI.repositories.ProdutoRepository;
import com.salaoAPI.services.exceptions.DataBaseException;
import com.salaoAPI.services.exceptions.ResourceNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repository;
	
	public List <Produto> findAll() {
		return repository.findAll();
	}
	
	public Produto findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Produto insert(Produto insert) {
		return repository.save(insert);
	}
	
	public Produto update (Long id ,Produto obj) {
		Produto entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		updateData(entity,obj);
		return repository.save(entity);
	}
	
	private void updateData(Produto entity,Produto obj) {
		entity.setNome(obj.getNome());
		entity.setValorDeVenda(obj.getValorDeVenda());
		entity.setValorDeCompra(obj.getValorDeCompra());
		entity.setQuantidade(obj.getQuantidade());
		entity.setDescricao(obj.getDescricao());
		entity.setImgURL(obj.getImgURL());
		entity.setControlarEstoque(obj.getControlarEstoque());
	}
	
	public void delete(Long id) {
		repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		try {
		repository.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataBaseException(e.getMessage());
		}
	}
}
	
	

