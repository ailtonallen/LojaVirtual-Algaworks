package com.farmacia.lojavirtual.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Categoria implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private String descricao;

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
		}

	public void setId(Long id) {
		this.id = id;
		}

	@Column(length = 30, nullable = false)
	public String getDescricao() {
		return descricao;
		}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
		}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		return result = prime * result + ((id == null) ? 0 : id.hashCode());

	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if ((obj == null) || (getClass() != obj.getClass()))
			return false;
		Categoria other = (Categoria) obj;
			if (id == null) {
				if (other.id != null)
				return false;
			} else if (!id.equals(other.id))
					return false; return true; }

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	}