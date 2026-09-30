package com.salaoAPI.entidades;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "id_produto")
public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String descricao;
	private String imgURL;
	
	@Column
	@PositiveOrZero
	private Double valorDeVenda;
	
	@Column (name = "valor_compra")
	@PositiveOrZero
	private Double valorDeCompra;
	private Integer quantidade;
	
	@Column (nullable=false)
	private Boolean controlarEstoque=true;

	@ManyToMany
	@JoinTable(name = "tb_produto_categoria",
	    joinColumns = @JoinColumn(name = "produto_id"),
	    inverseJoinColumns = @JoinColumn(name = "categoria_id"))
	private Set<Categoria> categorias = new HashSet<>();

	@OneToMany (mappedBy = "id.produto")
	private Set<OrderItem> items = new HashSet<>();
	
	public Produto() {
	}

	public Produto(Long id, String nome, String descricao, String imgURL, Double valorDeVenda,Double valorDeCompra,Integer quantidade) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.imgURL = imgURL;
		this.valorDeVenda = valorDeVenda;
		this.valorDeCompra=valorDeCompra;
		this.quantidade=quantidade;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getImgURL() {
		return imgURL;
	}

	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}
	
	public Double getValorDeVenda() {
		return valorDeVenda;
	}

	public void setValorDeVenda(Double valorDeVenda) {
		this.valorDeVenda = valorDeVenda;
	}

	public Double getValorDeCompra() {
		return valorDeCompra;
	}

	public void setValorDeCompra(Double valorDeCompra) {
		this.valorDeCompra = valorDeCompra;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Set<Categoria> getCategorias() {
		return categorias;
	}
	
	public Boolean getControlarEstoque() {
		return controlarEstoque;
	}

	public void setControlarEstoque(Boolean controlarEstoque) {
		this.controlarEstoque = controlarEstoque;
	}

	@JsonIgnore
	public Set <InicioAtendimento> getOrders() {
		Set <InicioAtendimento> set  = new HashSet<>();
		for (OrderItem x : items) {
			set.add(x.getInicioAtendimento());
		}
		return set;
	}
	
	public boolean temEstoquePara(int produto) {
		return !controlarEstoque || (quantidade !=null && quantidade >= produto);
	}
	
	public void baixarEstoque (int produto) {
		if (controlarEstoque) quantidade -=produto;
	}

	public void devolverEstoque(int produto) {
		if (controlarEstoque) quantidade = (quantidade ==null ? 0 : quantidade) + produto;
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
		Produto other = (Produto) obj;
		return Objects.equals(id, other.id);
	}

}
