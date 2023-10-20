package com.farmacia.lojavirtual.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.farmacia.lojavirtual.model.Grupo;
import com.farmacia.lojavirtual.model.Usuario;
import com.farmacia.lojavirtual.repository.Grupos;
import com.farmacia.lojavirtual.service.CadastroUsuarioService;
import com.farmacia.lojavirtual.service.NegocioException;
import com.farmacia.lojavirtual.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroUsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;


	private Usuario usuario;
	private Grupo grupoSelecionado;

	private List<Grupo> grupos;

	@Inject
	private CadastroUsuarioService cadastroUsuarioService;

	@Inject
	private Grupos repositoryGrupos;

	public CadastroUsuarioBean() {
		usuario = new Usuario();
		grupos = new ArrayList<>();
	}

	public void inicializar() {
		if (FacesUtil.isNotPostback()) {

			grupos = repositoryGrupos.previlegios();

			if (this.grupos != null) {
				this.getGrupos();
			}
		}
	}

	public void salvar() {
		try {
			this.usuario = cadastroUsuarioService.salvar(this.usuario);
			limpar();
			FacesUtil.addInfoMessage("Usuário salvo com sucesso!");
		} catch (NegocioException ne) {
			FacesUtil.addErrorMessage(ne.getMessage());
		}
	}

	public void adicionaGrupo() {
		this.usuario.getGrupos().add(this.grupoSelecionado);
		FacesUtil.addInfoMessage("Grupo adicionado com sucesso");

	}

	public void limpar() {
		grupos = new ArrayList<>();
		usuario = new Usuario();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;

		if (this.usuario != null) {
			this.grupos = this.usuario.getGrupos();
		}
	}

	public Grupo getGrupoSelecionado() {
		return grupoSelecionado;

	}

	public void setGrupoSelecionado(Grupo grupoSelecionado) {
		this.grupoSelecionado = grupoSelecionado;
	}

	public List<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}

	public boolean isEditando() {
		return this.usuario.getId() != null;
	}

}