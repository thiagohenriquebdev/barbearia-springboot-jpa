package com.salaoAPI.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salaoAPI.entidades.Cliente;
import com.salaoAPI.entidades.InicioAtendimento;

public interface InicioAtendimentoRepository extends JpaRepository<InicioAtendimento,Long>{
	
	
	Optional<InicioAtendimento> findFirstByClienteOrderByIdDesc(Cliente cliente);
	
	
	@Query("SELECT DISTINCT i FROM InicioAtendimento i " +
		       "LEFT JOIN FETCH i.items it " +
		       "LEFT JOIN FETCH it.id.produto " +
		       "LEFT JOIN FETCH i.pagamento")
		List<InicioAtendimento> buscarTodosComItens();
}
