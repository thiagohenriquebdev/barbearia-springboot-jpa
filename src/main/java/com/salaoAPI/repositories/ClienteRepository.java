package com.salaoAPI.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salaoAPI.Entidades.Cliente;
import com.salaoAPI.Entidades.enums.OrderStatus;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
	Optional<Cliente> findFirstByStatusAtendimentoOrderByDataChegadaAsc(OrderStatus statusAtendimento);
	
	List<Cliente> findByStatusAtendimentoOrderByDataChegadaAsc(OrderStatus statusAtendimento);
	
	Optional<Cliente> findByNome (String nome);
 
}
