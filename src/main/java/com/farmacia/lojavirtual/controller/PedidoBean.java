package com.farmacia.lojavirtual.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import com.farmacia.lojavirtual.model.Entrega;
import com.farmacia.lojavirtual.model.Pedido;
import com.farmacia.lojavirtual.model.Produto;
import com.farmacia.lojavirtual.model.TipoPagamento;
import com.farmacia.lojavirtual.repository.Entregas;
import com.farmacia.lojavirtual.repository.TipoPagamentos;
import com.farmacia.lojavirtual.security.UsuarioLogado;
import com.farmacia.lojavirtual.service.NegocioException;
import com.farmacia.lojavirtual.service.PedidoService;
import com.farmacia.lojavirtual.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PedidoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Pedido pedido;
	private String entregaSelecionada;
	private String tipoSelecionado;
	private List<Pedido> itens = new ArrayList<>();
	private List<Produto> produtos;
	private List<Entrega> entregasLista;
	private List<TipoPagamento> tipoPagamentoLista;
	private Produto produto;
	private UsuarioLogado usuario;

	public List<TipoPagamento> getTipoPagamentoLista() {
		return tipoPagamentoLista;
	}

	public void setTipoPagamentoLista(List<TipoPagamento> tipoPagamentoLista) {
		this.tipoPagamentoLista = tipoPagamentoLista;
	}

	@Inject
	PedidoService pedidoService;

	@Inject
	private TipoPagamentos tipoPagamentos;

	@Inject
	private Entregas entregas;

	public PedidoBean() {
		limpar();
	}

	public void inicializar() {
		if (this.pedido == null) {
			limpar();

		}
		entregasLista = entregas.lista();
		tipoPagamentoLista = tipoPagamentos.lista();
	}

	private void limpar() {
		pedido = new Pedido();
		pedido.setValorTotal(new BigDecimal("0.00"));
		tipoPagamentoLista = new ArrayList<>();
		entregasLista = new ArrayList<>();

	}

	public void adicionar(ActionEvent evento) {
		produto = (Produto) evento.getComponent().getAttributes()
				.get("produtoSelecionado");

		int achou = -1;
		for (int posicao = 0; posicao < itens.size(); posicao++) {
			if (itens.get(posicao).getProduto().equals(produto)) {
				achou = posicao;
			}
		}
		if (achou < 0) {
			Entrega entregas = new Entrega();

			Pedido pedidos = new Pedido();
			pedidos.setValorTransporte(entregas.getValor());
			pedidos.setValor(produto.getPrecoUnitario());
			pedidos.setProduto(produto);
			pedidos.setQuantidade(new Short("1"));

			itens.add(pedidos);
		} else {
			Pedido pedidos = itens.get(achou);
			pedidos.setQuantidade(new Short(pedidos.getQuantidade() + 1 + ""));
			pedidos.setValor(produto.getPrecoUnitario().multiply(
					new BigDecimal(pedidos.getQuantidade())));
		}
		calcular();
	}

	public void remover(ActionEvent evento) {
		Pedido pedidos = (Pedido) evento.getComponent().getAttributes()
				.get("itemSelecionado");
		int achou = -1;
		for (int posicao = 0; posicao < itens.size(); posicao++) {
			if (itens.get(posicao).getProduto().equals(pedidos.getProduto())) {
				achou = posicao;
			}
		}
		if (achou > -1) {
			itens.remove(achou);
		}
		calcular();
	}

	public void calcular() {
		pedido.setValorTotal(new BigDecimal("0.00"));
		for (Pedido pedidos : itens) {

			pedido.setValorTotal(pedido.getValorTotal().add(
					pedidos.getValor()));
		}
	}

	public void salvar() {
		try {
			this.pedido = this.pedidoService.salvar(this.pedido);
			FacesUtil.addInfoMessage("Pedido feito com sucesso");
		} catch (NegocioException ne) {
			FacesUtil.addErrorMessage(ne.getMessage());
		}
	}

	// try {
	// if (pedido.getValorTotal().signum() == 0) {
	// Messages.addGlobalError("Selecione pelo menos um item para finalizar a compra");
	// return;
	// }
	// Pedidos pedidos = new Pedidos();
	// pedidos.guardar(pedido, itens);
	// pedido= new Pedido();
	// pedido.setValorTotal(new BigDecimal("0.00"));
	//
	// Produtos produtos = new Produtos();
	// produtos = produtos.porProduto(nome);
	//
	// itens = new ArrayList<>();
	//
	// Messages.addGlobalInfo("Venda realizada com sucesso");
	//
	// } catch (RuntimeException erro) {
	// Messages.addGlobalError("Ocorreu um erro ao tentar efetuar a compra");
	// erro.printStackTrace();
	// }
	// }

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
		if (this.pedido != null) {
			this.entregaSelecionada = this.pedido.getEntrega().getLocal();
		}
		if (this.pedido != null) {
			this.tipoSelecionado = this.pedido.getTipo().getNome();
		}
	}

	public List<Pedido> getItens() {
		return itens;
	}

	public void setItens(List<Pedido> itens) {
		this.itens = itens;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Entrega> getEntregasLista() {
		return entregasLista;
	}

	public void setEntregasLista(List<Entrega> entregasLista) {
		this.entregasLista = entregasLista;
	}

	public String getEntregaSelecionada() {
		return entregaSelecionada;
	}

	public void setEntregaSelecionada(String entregaSelecionada) {
		this.entregaSelecionada = entregaSelecionada;
	}

	public String getTipoSelecionado() {
		return tipoSelecionado;
	}

	public void setTipoSelecionado(String tipoSelecionado) {
		this.tipoSelecionado = tipoSelecionado;
	}

	public TipoPagamentos getTipoPagamentos() {
		return tipoPagamentos;
	}

	public void setTipoPagamentos(TipoPagamentos tipoPagamentos) {
		this.tipoPagamentos = tipoPagamentos;
	}

	public Entregas getEntregas() {
		return entregas;
	}

	public void setEntregas(Entregas entregas) {
		this.entregas = entregas;
	}

}