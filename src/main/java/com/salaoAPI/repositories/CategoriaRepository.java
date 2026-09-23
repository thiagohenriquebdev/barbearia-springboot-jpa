package com.salaoAPI.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salaoAPI.entidades.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

}
