package com.salaoAPI.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salaoAPI.Entidades.Cliente;
import com.salaoAPI.Entidades.enums.OrderStatus;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
	Optional<Cliente> findFirstByStatusOrderByDataChegadaAsc (OrderStatus status);
	
	List<Cliente> findByStatusOrderByDataChegadaAsc(OrderStatus status);
	
	Optional<Cliente> findByName (String name);
 
}
