package com.farmacia.lojavirtual.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.farmacia.lojavirtual.model.Categoria;

public class Categorias implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Categoria porId(Long id) {
		return manager.find(Categoria.class, id);
	}

	public List<Categoria> lista() {
		return manager.createQuery("from Categoria", Categoria.class)
				.getResultList();

	}
}
