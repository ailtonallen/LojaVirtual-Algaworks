package com.farmacia.lojavirtual.controller;

import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Messages;

import com.farmacia.lojavirtual.model.Pedido;
import com.farmacia.lojavirtual.model.Produto;
import com.farmacia.lojavirtual.model.Usuario;
import com.farmacia.lojavirtual.repository.Produtos;
import com.farmacia.lojavirtual.repository.Usuarios;
import com.farmacia.lojavirtual.repository.filter.ProdutoFilter;

@Named
@ViewScoped
public class PesquisaProdutosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Produtos produtos;

	private String produto;
	private ProdutoFilter filtro;
	private List<Usuario> usuarioLista;
	private List<Produto> produtosFiltrados;
	private Produto produtoSelecionado;
	private Usuarios usuarios;
	private String usuarioSelecionado;
	private List<Pedido> itens;

	public PesquisaProdutosBean() {
		filtro = new ProdutoFilter();

	}
	public void inicializar() {
		if (this.produto == null);

		}


	public void excluir() {
		try {
			produtos.remover(produtoSelecionado);
			produtosFiltrados.remove(produtoSelecionado);

			Path arquivo = Paths.get("C:/Desenvolvimento2/Uploads/" + produtoSelecionado.getId() + ".JPG" );
			Files.deleteIfExists(arquivo);

			Messages.addGlobalInfo("Produto " + produtoSelecionado.getNome()
					+ " excluído com sucesso.");
		} catch (Exception e) {
			Messages.addFlashGlobalError("Ocorreu um erro ao excluir o " + produtoSelecionado.getNome());
			e.printStackTrace();
		}
	}

	public void pesquisar() {
		if (produto != null) {
			produtosFiltrados = produtos.porProduto(produto);
			 itens = new ArrayList<>();
		} else {
			if (filtro.getNome() != null) {
				produtosFiltrados = produtos.porProduto(filtro.getNome());
			}if(filtro.getFabricante() != null) {
				produtosFiltrados = produtos.porFabricante(filtro.getFabricante());
				usuarioLista = new ArrayList<>();
			}

		}

	}

	public List<Produto> getProdutosFiltrados() {
		return produtosFiltrados;
	}

	public ProdutoFilter getFiltro() {
		return filtro;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {

		this.produto = produto;
		if (this.produto != null) {
//		this.usuarioSelecionado = this.produto.getUsuario().getNome();
		}
	}

	public void setFiltro(ProdutoFilter filtro) {
		this.filtro = filtro;
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

	public List<Usuario> getUsuarioLista() {
		return usuarioLista;
	}

	public void setUsuarioLista(List<Usuario> usuarioLista) {
		this.usuarioLista = usuarioLista;
	}

}