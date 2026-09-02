package com.salaoAPI.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salaoAPI.Entidades.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
