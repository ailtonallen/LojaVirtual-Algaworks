package com.farmacia.lojavirtual.controller;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Messages;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import com.farmacia.lojavirtual.model.Categoria;
import com.farmacia.lojavirtual.model.Produto;
import com.farmacia.lojavirtual.model.Usuario;
import com.farmacia.lojavirtual.repository.Categorias;
import com.farmacia.lojavirtual.repository.Usuarios;
import com.farmacia.lojavirtual.service.CadastroProdutoService;
import com.farmacia.lojavirtual.service.NegocioException;

@Named
@ViewScoped
public class CadastroProdutoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Produto produto;
	private String categoriaSelecionada;
	private List<Categoria> categoriaLista;
	private Usuario usuario;
	private List<Usuario> usuarioLista;

	@Inject
	private CadastroProdutoService cadastroProdutoService;

	@Inject
	private Categorias categorias;

	@Inject
	private Usuarios usuarios;

	public CadastroProdutoBean() {
		limpar();
	}

	public void inicializar() {
		if (this.produto == null) {

			limpar();
		}
		categoriaLista = categorias.lista();
		setUsuarioLista(usuarios.lista());
	}

	private void limpar() {
		produto = new Produto();
		setUsuario(new Usuario());
		categoriaSelecionada = null;
		categoriaLista = new ArrayList<>();

	}

	public void salvar() {
		try {
			this.produto = cadastroProdutoService.salvar(this.produto);
			Path origem = Paths.get(produto.getCaminho());
			Path destino = Paths.get("C:/Desenvolvimento2/Uploads/" + produto.getId() + ".JPG");
			Files.copy(origem, destino, StandardCopyOption.REPLACE_EXISTING);
			limpar();
			Messages.addGlobalInfo("Produto salvo com sucesso!");


		} catch (NegocioException | IOException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar o produto");
			erro.printStackTrace();
		}
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;

		if (this.produto != null) {
			this.categoriaSelecionada = this.produto.getCategoria().getNome();

		}
	}

	public String getCategoriaSelecionada() {
		return categoriaSelecionada;
	}

	public void setCategoriaSelecionada(String categoriaSelecionada) {
		this.categoriaSelecionada = categoriaSelecionada;
	}

	public List<Categoria> getCategoriaLista() {
		return categoriaLista;
	}

	public void setCategoriaLista(List<Categoria> categoriaLista) {
		this.categoriaLista = categoriaLista;
	}

	public boolean isEditando() {
		return this.produto.getId() != null;
	}

	public void upload(FileUploadEvent evento) {
		try {
			UploadedFile arquivoUpload = evento.getFile();
			Path arquivoTemp = Files.createTempFile(null, null);
			Files.copy(arquivoUpload.getInputstream(), arquivoTemp,
					StandardCopyOption.REPLACE_EXISTING);
			produto.setCaminho(arquivoTemp.toString());
		} catch (IOException erro) {
			Messages.addGlobalInfo("Ocorreu um erro ao tentar realizar o upload da foto");
			erro.printStackTrace();
		}
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarioLista() {
		return usuarioLista;
	}

	public void setUsuarioLista(List<Usuario> usuarioLista) {
		this.usuarioLista = usuarioLista;
	}

}