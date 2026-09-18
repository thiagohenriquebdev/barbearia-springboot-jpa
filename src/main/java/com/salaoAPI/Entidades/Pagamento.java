package com.salaoAPI.Entidades;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table (name ="tb_pagamento")
public class Pagamento implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long Id;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "HH:mm:ss",timezone = "america/Sao_Paulo")
	private Instant dataFinalizado;
	
	@JsonIgnore
	@OneToOne
	@MapsId
	private InicioAtendimento order;
	
	
	public Pagamento () {
	}
	
	public Pagamento(Long id, Instant dataFinalizado, InicioAtendimento order) {
		super();
		Id = id;
		this.dataFinalizado = dataFinalizado;
		this.order = order;
	}

	public Long getId() {
		return Id;
	}


	public void setId(Long id) {
		Id = id;
	}


	public Instant getdataFinalizado() {
		return dataFinalizado;
	}


	public void setdataFinalizado(Instant dataFinalizado) {
		this.dataFinalizado = dataFinalizado;
	}


	public InicioAtendimento getOrder() {
		return order;
	}


	public void setOrder(InicioAtendimento order) {
		this.order = order;
	}


	@Override
	public int hashCode() {
		return Objects.hash(Id);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pagamento other = (Pagamento) obj;
		return Objects.equals(Id, other.Id);
	}
	
	
	
	
}
