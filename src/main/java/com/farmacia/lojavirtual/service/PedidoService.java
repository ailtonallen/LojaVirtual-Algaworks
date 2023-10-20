package com.farmacia.lojavirtual.service;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;

import com.farmacia.lojavirtual.model.Entrega;
import com.farmacia.lojavirtual.model.Pedido;
import com.farmacia.lojavirtual.model.Produto;
import com.farmacia.lojavirtual.repository.Pedidos;
import com.farmacia.lojavirtual.security.UsuarioLogado;
import com.farmacia.lojavirtual.util.jpa.Transactional;

public class PedidoService implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Produto produto;
	private Entrega entregas;
	@Inject
	Pedidos pedidos;

	UsuarioLogado usuario;

	@Transactional
	public Pedido salvar(Pedido pedido) {
		pedido.setDataCriacao(new Date());
		pedido.setDataEntrega(new Date());
		pedido.setQuantidade(new Short("1"));
		pedido.setProduto(produto);
		pedido.getTipo();
		pedido.getUsuario();
		return pedidos.guardar(pedido);
	}

	// return pedidos.mer(pedido);
}