package com.salaoAPI.Entidades;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.salaoAPI.Entidades.enums.OrderStatus;
import com.salaoAPI.Entidades.enums.StatusPagamento;
import com.salaoAPI.Entidades.enums.StatusRecebimento;

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
	private String nome; // entrada de dados do cliente (Dados Unicos , Nao Pode Ser Repetido)
	
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "HH:mm:ss",timezone = "America/Sao_Paulo")
	private Instant dataChegada;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "HH:mm:ss",timezone = "America/Sao_Paulo")
	private Instant dataFinalizacao;
	
	@Enumerated (EnumType.STRING)
	private OrderStatus statusAtendimento;
	
	@Enumerated (EnumType.STRING)
	private StatusPagamento statusPagamento;
	
	@Enumerated (EnumType.STRING)
	private StatusRecebimento statusRecebimento;
	
	@JsonIgnore
	@OneToMany(mappedBy = "cliente")
	List<InicioAtendimento>servicos = new ArrayList<>();
	
	
	public Cliente() {
	}
	
	public Cliente(Long id, String nome,OrderStatus statusAtendimento,StatusPagamento statusPagamento, Instant dataChegada,Instant dataFinalizacao,StatusRecebimento statusRecebimento) {
		this.id = id;
		this.nome = nome;
		this.statusAtendimento=statusAtendimento;
		this.statusPagamento=statusPagamento;
		this.statusRecebimento=statusRecebimento;
		this.dataChegada = dataChegada;
		this.dataFinalizacao=dataFinalizacao;
	}

	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public Instant getDataChegada() {
		return dataChegada;
	}
	
	public OrderStatus getStatusAtendimento() {
		return statusAtendimento;
	}

	public void setStatusAtendimento(OrderStatus statusAtendimento) {
		this.statusAtendimento = statusAtendimento;
	}

	public StatusPagamento getStatusPagamento() {
		return statusPagamento;
	}

	public void setStatusPagamento(StatusPagamento statusPagamento) {
		this.statusPagamento = statusPagamento;
	}

	public StatusRecebimento getStatusRecebimento() {
		return statusRecebimento;
	}

	public void setStatusRecebimento(StatusRecebimento statusRecebimento) {
		this.statusRecebimento = statusRecebimento;
	}

	public void setDataChegada(Instant dataChegada) {
		this.dataChegada = dataChegada;
	}
	
	public Instant getDataFinalizacao() {
		return dataFinalizacao;
	}

	public void setDataFinalizacao(Instant dataFinalizacao) {
		this.dataFinalizacao = dataFinalizacao;
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
