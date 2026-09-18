package com.salaoAPI.Entidades;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.salaoAPI.Entidades.enums.OrderStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Cliente implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column (nullable =false , unique = true)
	private String name; // entrada de dados do cliente (Dados Unicos , Nao Pode Ser Repetido)
	
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "HH:mm:ss",timezone = "America/Sao_Paulo")
	private Instant dataChegada;
	
	@Enumerated (EnumType.STRING)
	private OrderStatus status;
	
	@JsonIgnore
	@OneToMany(mappedBy = "cliente")
	List<InicioAtendimento>servicos = new ArrayList<>();
	
	
	public Cliente() {
	}
	
	public Cliente(Long id, String name,OrderStatus status, Instant dataChegada) {
		this.id = id;
		this.name = name;
		this.status=status;
		this.dataChegada = dataChegada;
	}

	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Instant getDataChegada() {
		return dataChegada;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public void setDataChegada(Instant dataChegada) {
		this.dataChegada = dataChegada;
	}
	
	public List<InicioAtendimento> getServicos() {
		return servicos;
	}

	@Override
	public int hashCode() {
		return Objects.hash(Long.valueOf(id));
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return id == other.id;
	}


	
	
}
