package com.salaoAPI.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salaoAPI.entidades.InicioAtendimento;

public interface InicioAtendimentoRepository extends JpaRepository<InicioAtendimento, Long>{
	
	@Query("SELECT DISTINCT i FROM InicioAtendimento i " +
		       "LEFT JOIN FETCH i.items it " +
		       "LEFT JOIN FETCH it.id.produto " +
		       "LEFT JOIN FETCH i.pagamento")
		List<InicioAtendimento> buscarTodosComItens();
}
