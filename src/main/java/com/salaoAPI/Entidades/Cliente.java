package com.salaoAPI.Entidades;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
	private long id;
	private String name;
	private LocalTime dataChegada;
	
	@OneToMany(mappedBy = "cliente")
	List<Servico>servicos = new ArrayList<>();
	
	
	public Cliente() {
	}
	
	public Cliente(long id, String name, LocalTime dataChegada) {
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


	public LocalTime getDataChegada() {
		return dataChegada;
	}


	public void setDataChegada(LocalTime dataChegada) {
		this.dataChegada = dataChegada;
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
