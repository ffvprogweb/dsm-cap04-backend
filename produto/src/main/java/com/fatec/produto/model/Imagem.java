package com.fatec.produto.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Imagem {
	@Id
	Long id;
	private String nome;
	private String caminho;
	@Column (name="arquivo", length=28672)
	private byte[] arquivo;
	public Imagem(String nome, String caminho, byte[] arquivo) {
		this.nome = nome;
		this.caminho = caminho;
		this.arquivo = arquivo;
	}
	public Imagem() {
		
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
	public String getCaminho() {
		return caminho;
	}
	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}
	public byte[] getArquivo() {
		return arquivo;
	}
	public void setArquivo(byte[] arquivo) {
		this.arquivo = arquivo;
	}
	
	

}
