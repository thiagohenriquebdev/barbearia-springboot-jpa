package com.salaoAPI.entidades;

import java.io.Serializable;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.salaoAPI.entidades.enums.FormaPagamento;
import com.salaoAPI.entidades.enums.StatusPagamento;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
	private Long id;
	
	private double valorPago;

	@JsonIgnore
	@OneToOne
	@MapsId
	private InicioAtendimento inicioAtendimento;
	
	@Enumerated (EnumType.STRING)
	private StatusPagamento statusPagamento;
	
	@Enumerated (EnumType.STRING)
	private FormaPagamento formaPagamento;

	public Pagamento() {
	}

	public Pagamento(Long id, InicioAtendimento inicioAtendimento) {
		super();
		this.id = id;
		this.inicioAtendimento = inicioAtendimento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public StatusPagamento getStatusPagamento() {
		return statusPagamento;
	}

	public void setStatusPagamento(StatusPagamento statusPagamento) {
		this.statusPagamento = statusPagamento;
	}

	public InicioAtendimento getInicioAtendimento() {
		return inicioAtendimento;
	}

	public void setInicioAtendimento(InicioAtendimento inicioAtendimento) {
		this.inicioAtendimento = inicioAtendimento;
	}

	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public double getValorPago() {
		return valorPago;
	}

	public void setValorPago(double valorPago) {
		this.valorPago = valorPago;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
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
		return Objects.equals(id, other.id);
	}
}
