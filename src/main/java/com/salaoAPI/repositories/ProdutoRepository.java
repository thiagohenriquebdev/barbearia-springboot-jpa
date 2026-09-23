package com.salaoAPI.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salaoAPI.entidades.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
