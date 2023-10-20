package com.farmacia.lojavirtual.repository.filter;

import java.io.Serializable;


public class ProdutoFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private String fabricante;
	private String nome;


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

}