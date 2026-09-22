package com.salaoAPI.Entidades;

import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table (name ="tb_inicioAtendimento")
public class InicioAtendimento {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "HH:mm:ss",timezone = "America/Sao_Paulo")
	@Column (nullable = false)
	private Instant inicioAtendimento;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	@OneToMany (mappedBy = "id.order")
	private Set<OrderItem> items = new HashSet<>();
	
	@OneToOne (mappedBy = "inicioAtendimento" , cascade = CascadeType.ALL)
	private Pagamento pagamento;
	
	public InicioAtendimento () {
	}

	public InicioAtendimento(Long id,Instant inicioAtendimento, Cliente cliente) {
		super();
		this.id = id;
		this.inicioAtendimento=inicioAtendimento;
		this.cliente = cliente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getInicioAtendimento() {
		return inicioAtendimento;
	}

	public void setInicioAtendimento(Instant inicioAtendimento) {
		this.inicioAtendimento = inicioAtendimento;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Set<OrderItem> getItems() {
		return items;
	}
	
	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}
	
	public Double getTotal() {
		double soma = 0.0;
		for (OrderItem x : items) {
			soma+=x.getSubTotal();
		}
		return soma;
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
		InicioAtendimento other = (InicioAtendimento) obj;
		return Objects.equals(id, other.id);
	}

}
