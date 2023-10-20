package com.farmacia.lojavirtual.controller;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.farmacia.lojavirtual.model.TipoPagamento;
import com.farmacia.lojavirtual.service.NegocioException;
import com.farmacia.lojavirtual.service.TipoPagamentoService;
import com.farmacia.lojavirtual.util.jsf.FacesUtil;

@Named
@ViewScoped
public class TipoPagamentoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private TipoPagamento tipoPagamento;

	public TipoPagamento getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(TipoPagamento tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

	public TipoPagamentoService getTipoPagamentoService() {
		return tipoPagamentoService;
	}

	public void setTipoPagamentoService(
			TipoPagamentoService tipoPagamentoService) {
		this.tipoPagamentoService = tipoPagamentoService;
	}

	@Inject
	private TipoPagamentoService tipoPagamentoService;

	public TipoPagamentoBean() {
		limpar();
	}

	public void inicializar() {
		if (this.tipoPagamento == null) {

			limpar();
		}
	}

	private void limpar() {
		tipoPagamento = new TipoPagamento();
	}

	public void salvar() {
		try {
			this.tipoPagamento = tipoPagamentoService
					.salvar(this.tipoPagamento);
			limpar();
			FacesUtil.addInfoMessage("Tipo de pagamnto salvo com sucesso!");

		} catch (NegocioException ne) {
			FacesUtil.addErrorMessage(ne.getMessage());
		}
	}
}
