package com.farmacia.lojavirtual.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.farmacia.lojavirtual.model.Usuario;
import com.farmacia.lojavirtual.repository.Usuarios;
import com.farmacia.lojavirtual.repository.filter.UsuarioFilter;
import com.farmacia.lojavirtual.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaUsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Usuarios usuarios;

	private UsuarioFilter filtro;

	private List<Usuario> usuariosFiltrados;

	private Usuario usuarioSelecionado;

	public PesquisaUsuarioBean() {
		filtro = new UsuarioFilter();
	}

	public void pesquisar() {
		usuariosFiltrados = usuarios.filtrados(filtro);
	}

	public void excluir() {
		usuarios.remover(usuarioSelecionado);
		usuariosFiltrados.remove(usuarioSelecionado);

		FacesUtil.addInfoMessage("Usuario" + usuarioSelecionado.getNome()
				+ "excluido com sucesso.");
	}

	public List<Usuario> getUsuariosFiltrados() {
		return usuariosFiltrados;
	}

	public UsuarioFilter getFiltro() {
		return filtro;
	}

	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}

}
