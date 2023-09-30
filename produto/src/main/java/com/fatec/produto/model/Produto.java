package com.fatec.produto.model;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Produto {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String descricao;
	private String categoria;
	private double custo;
	private int quantidadeNoEstoque;

	public Produto(String descricao, String categoria, double custo, int quantidadeNoEstoque) {
		setDescricao(descricao);
		setCategoria(categoria);
		setCusto(custo);
		setQuantidadeNoEstoque(quantidadeNoEstoque);
	}

	public Produto() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		if (descricao == null) {
			throw new IllegalArgumentException("A descrição não deve estar em branco");
		} else {
			if (descricao.isBlank() ) {
				throw new IllegalArgumentException("A descrição não deve estar em branco");
			}
			else {
				this.descricao = descricao;
			}
		}
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		if (categoria == null || categoria.isBlank()) {
			throw new IllegalArgumentException("A categoria não deve estar em branco");
		} else {
			this.categoria = categoria;
		}
	}

	public double getCusto() {
		return custo;
	}

	public void setCusto(double custo) {
		try {
			if (custo <= 0) {
				throw new IllegalArgumentException("O custo deve ser maior que zero");
			} else {
				this.custo = custo;
			}
		} catch (Exception e) {
			throw new IllegalArgumentException("O custo deve ser maior que zero");
		}

	}

	public int getQuantidadeNoEstoque() {
		return quantidadeNoEstoque;
	}

	public void setQuantidadeNoEstoque(int quantidadeNoEstoque) {
		try {
			if (quantidadeNoEstoque <= 0) {
				throw new IllegalArgumentException("A quantidade deve ser maior que zero");
			} else {
				this.quantidadeNoEstoque = quantidadeNoEstoque;
			}
		} catch (Exception e) {
			throw new IllegalArgumentException("A quantidade deve ser maior que zero");
		}

	}

	@Override
	public int hashCode() {
		return Objects.hash(categoria, custo, descricao, quantidadeNoEstoque);
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
		return Objects.equals(categoria, other.categoria)
				&& Double.doubleToLongBits(custo) == Double.doubleToLongBits(other.custo)
				&& Objects.equals(descricao, other.descricao) && quantidadeNoEstoque == other.quantidadeNoEstoque;
	}

}
