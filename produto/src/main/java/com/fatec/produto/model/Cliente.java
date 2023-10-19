package com.fatec.produto.model;

import java.util.Objects;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(unique = true) // nao funciona com @Valid tem que tratar na camada de persistencia
	@CPF
	private String cpf;
	@NotBlank (message="O atributo nome não deve estar vazio.")// nao eh nulo ou vazio
	private String nome;
	@Size(min = 8, max = 8, message = "CEP deve ter 8 caracteres")
	private String cep;
	private String endereco;
	@NotBlank
	private String dataUltimaTransacao;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getDataUltimaTransacao() {
		return dataUltimaTransacao;
	}
	public void setDataUltimaTransacao(String dataUltimaTransacao) {
		this.dataUltimaTransacao = dataUltimaTransacao;
	}
	@Override
	public int hashCode() {
		return Objects.hash(cep, cpf, dataUltimaTransacao, endereco, nome);
	}
	@Override
	public boolean equals(Object obj) {
		
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		
		return Objects.equals(cep, other.cep) && Objects.equals(cpf, other.cpf)
				&& Objects.equals(dataUltimaTransacao, other.dataUltimaTransacao)
				&& Objects.equals(endereco, other.endereco) && Objects.equals(nome, other.nome);
	}
	

}

