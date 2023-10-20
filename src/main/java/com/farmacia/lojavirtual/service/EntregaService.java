package com.farmacia.lojavirtual.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.farmacia.lojavirtual.model.Entrega;
import com.farmacia.lojavirtual.repository.Entregas;
import com.farmacia.lojavirtual.util.jpa.Transactional;

public class EntregaService implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private Entregas entregas;

	@Transactional
	public Entrega salvar(Entrega entrega) {
		Entrega entregaExistente = entregas.porId(entrega.getId());

		if (entregaExistente != null && entregaExistente.equals(entrega)) {
			throw new NegocioException(
					"Já existe um local de entrega com o nome informado");
		}
		return entregas.guardar(entrega);
	}
}
