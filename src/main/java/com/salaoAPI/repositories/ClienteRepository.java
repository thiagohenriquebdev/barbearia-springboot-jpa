package com.salaoAPI.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salaoAPI.entidades.Cliente;
import com.salaoAPI.entidades.enums.StatusAtendimento;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
	Optional<Cliente> findFirstByStatusAtendimentoOrderByDataChegadaAsc(StatusAtendimento statusAtendimento);
	
	List<Cliente> findByStatusAtendimentoOrderByDataChegadaAsc(StatusAtendimento statusAtendimento);
	
	Optional<Cliente> findByNome (String nome);
 
}
