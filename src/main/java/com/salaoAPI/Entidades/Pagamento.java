package com.salaoAPI.entidades;

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
@Table(name = "tb_pagamento")
public class Pagamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	@JsonIgnore
	@OneToOne
	@MapsId
	private InicioAtendimento inicioAtendimento;

	public Pagamento() {
	}

	public Pagamento(Long id, InicioAtendimento inicioAtendimento) {
		super();
		Id = id;
		this.inicioAtendimento = inicioAtendimento;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public InicioAtendimento getinicioAtendimento() {
		return inicioAtendimento;
	}

	public void setinicioAtendimento(InicioAtendimento inicioAtendimento) {
		this.inicioAtendimento = inicioAtendimento;
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
