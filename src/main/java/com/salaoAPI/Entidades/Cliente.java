package com.salaoAPI.Entidades;

import java.io.Serializable;
import java.time.Instant;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
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
	private String name;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'",timezone = "GMT")
	private Instant dataChegada;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'",timezone = "GMT")
	private Instant dataSaida;
	
	@JsonIgnore
	@OneToMany(mappedBy = "cliente")
	List<Order>servicos = new ArrayList<>();
	
	
	public Cliente() {
	}
	
	public Cliente(Long id, String name, Instant dataChegada) {
		this.id = id;
		this.name = name;
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
	
	public Instant getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(Instant dataSaida) {
		this.dataSaida = dataSaida;
	}


	public void setDataChegada(Instant dataChegada) {
		this.dataChegada = dataChegada;
	}
	
	public List<Order> getServicos() {
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
