package com.farmacia.lojavirtual.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.farmacia.lojavirtual.model.Entrega;

public class Entregas implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Entrega porId(Long id) {
		return manager.find(Entrega.class, id);
	}

	public List<Entrega> lista() {
		return manager.createQuery("from Entrega", Entrega.class)
				.getResultList();

	}

	public Entrega guardar(Entrega entrega) {
		return manager.merge(entrega);
	}

}
