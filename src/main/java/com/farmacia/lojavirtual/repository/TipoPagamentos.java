package com.farmacia.lojavirtual.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.farmacia.lojavirtual.model.TipoPagamento;

public class TipoPagamentos implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public TipoPagamento porId(Long id) {
		return manager.find(TipoPagamento.class, id);
	}

	public List<TipoPagamento> lista() {
		return manager.createQuery("from TipoPagamento", TipoPagamento.class)
				.getResultList();

	}

	public TipoPagamento guardar(TipoPagamento tipoPagamento) {
		return manager.merge(tipoPagamento);
	}
}
