package com.farmacia.lojavirtual.controller;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.farmacia.lojavirtual.model.Entrega;
import com.farmacia.lojavirtual.service.EntregaService;
import com.farmacia.lojavirtual.service.NegocioException;
import com.farmacia.lojavirtual.util.jsf.FacesUtil;

@Named
@ViewScoped
public class EntregaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Entrega entrega;

	public Entrega getEntrega() {
		return entrega;
	}

	public void setEntrega(Entrega entrega) {
		this.entrega = entrega;
	}

	public EntregaService getEntregaService() {
		return entregaService;
	}

	public void setEntregaService(EntregaService entregaService) {
		this.entregaService = entregaService;
	}

	@Inject
	private EntregaService entregaService;

	public EntregaBean() {
		limpar();
	}

	public void inicializar() {
		if (this.entrega == null) {

			limpar();
		}
	}

	private void limpar() {
		entrega = new Entrega();
	}

	public void salvar() {
		try {
			this.entrega = entregaService.salvar(this.entrega);
			limpar();
			FacesUtil.addInfoMessage("Local de entrega salvo com sucesso!");

		} catch (NegocioException ne) {
			FacesUtil.addErrorMessage(ne.getMessage());
		}
	}
}
