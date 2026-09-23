package com.salaoAPI.entidades;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.salaoAPI.entidades.pk.OrderItemPK;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table (name = "tb_order_item")
public class OrderItem implements Serializable{
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private OrderItemPK id = new OrderItemPK();
	private Integer quantidade;
	private double price;
	
	public OrderItem () {
	}
	
	public OrderItem(InicioAtendimento inicioAtendimento , Produto produto ,Integer quantidade, double price) {
		super();
		id.setOrder(inicioAtendimento);
		id.setProduto(produto);
		this.quantidade = quantidade;
		this.price = price;
	}
	
	
	public Produto getProduto() {
		return id.getProduto();
	}
	
	public void setProduto(Produto produto) {
		id.setProduto(produto);
	}
	
	public InicioAtendimento getInicioAtendimento() {
		return id.getOrder();
	}
	
	@JsonIgnore
	public void setInicioAtendimento(InicioAtendimento inicioAtendimento) {
		id.setOrder(inicioAtendimento);
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public Double getSubTotal() {
		return getPrice() * getQuantidade();
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
		OrderItem other = (OrderItem) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
	
	
}
