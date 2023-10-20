package com.farmacia.lojavirtual.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.farmacia.lojavirtual.model.Pedido;
import com.farmacia.lojavirtual.model.Produto;
import com.farmacia.lojavirtual.model.Usuario;
import com.farmacia.lojavirtual.repository.Pedidos;
import com.farmacia.lojavirtual.repository.Usuarios;
import com.farmacia.lojavirtual.repository.filter.PedidoFilter;

@Named
@ViewScoped
public class PesquisaPedidosBean implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private Pedidos pedidos;
	private String pedido;
	private PedidoFilter filtro;
	private List<Usuario> usuarioLista;
	private List<Pedido> pedidosFiltrados;
	private Pedido pedidoSelecionado;
	private Produto produtoSelecionado;
	private Usuarios usuarios;
	private String usuarioSelecionado;

	public PesquisaPedidosBean() {
		filtro = new PedidoFilter();
	}

	public void pesquisar() {
		pedidosFiltrados = pedidos.filtrados(filtro);
	}

	/*
	 * public void excluir() { pedidos.remover(pedidoSelecionado);
	 * pedidosFiltrados.remove(pedidoSelecionado);
	 *
	 * FacesUtil.addInfoMessage("Pedido" + pedidoSelecionado.getId() +
	 * "excluido com sucesso."); }
	 */
	public Pedidos getPedidos() {
		return pedidos;
	}

	public void setPedidos(Pedidos pedidos) {
		this.pedidos = pedidos;
	}

	public String getPedido() {
		return pedido;
	}

	public void setPedido(String pedido) {
		this.pedido = pedido;
	}

	public PedidoFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(PedidoFilter filtro) {
		this.filtro = filtro;
	}

	public List<Usuario> getUsuarioLista() {
		return usuarioLista;
	}

	public void setUsuarioLista(List<Usuario> usuarioLista) {
		this.usuarioLista = usuarioLista;
	}

	public List<Pedido> getPedidosFiltrados() {
		return pedidosFiltrados;
	}

	public void setPedidosFiltrados(List<Pedido> pedidosFiltrados) {
		this.pedidosFiltrados = pedidosFiltrados;
	}

	public Produto getProdutoSelecionado() {
		return produtoSelecionado;
	}

	public void setProdutoSelecionado(Produto produtoSelecionado) {
		this.produtoSelecionado = produtoSelecionado;
	}

	public Usuarios getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Usuarios usuarios) {
		this.usuarios = usuarios;
	}

	public String getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(String usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}
}
