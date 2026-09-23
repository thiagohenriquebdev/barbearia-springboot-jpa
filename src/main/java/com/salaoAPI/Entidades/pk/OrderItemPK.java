package com.salaoAPI.entidades.pk;

import java.io.Serializable;
import java.util.Objects;

import com.salaoAPI.entidades.InicioAtendimento;
import com.salaoAPI.entidades.Produto;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class OrderItemPK implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn (name = "order_id")
	private InicioAtendimento order;
	@ManyToOne
	@JoinColumn (name = "produto_id")
	private Produto produto;
	
	
	public InicioAtendimento getOrder() {
		return order;
	}
	public void setOrder(InicioAtendimento order) {
		this.order = order;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	@Override
	public int hashCode() {
		return Objects.hash(order, produto);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItemPK other = (OrderItemPK) obj;
		return Objects.equals(order, other.order) && Objects.equals(produto, other.produto);
	}
	
	
	
	

}
