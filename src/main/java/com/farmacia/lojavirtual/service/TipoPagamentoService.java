package com.farmacia.lojavirtual.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.farmacia.lojavirtual.model.TipoPagamento;
import com.farmacia.lojavirtual.repository.TipoPagamentos;
import com.farmacia.lojavirtual.util.jpa.Transactional;

public class TipoPagamentoService implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private TipoPagamentos tipoPagamentos;

	@Transactional
	public TipoPagamento salvar(TipoPagamento tipoPagamento) {
		TipoPagamento tipoPagamentoExistente = tipoPagamentos
				.porId(tipoPagamento.getId());

		if (tipoPagamentoExistente != null
				&& tipoPagamentoExistente.equals(tipoPagamento)) {
			throw new NegocioException(
					"Já existe um tipo de pagamento com o nome informado");
		}
		return tipoPagamentos.guardar(tipoPagamento);
	}
}
