package com.farmacia.lojavirtual.service;

import java.io.IOException;
import java.io.Serializable;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import com.farmacia.lojavirtual.model.Produto;
import com.farmacia.lojavirtual.model.Usuario;
import com.farmacia.lojavirtual.repository.Produtos;
import com.farmacia.lojavirtual.repository.Usuarios;
import com.farmacia.lojavirtual.util.jpa.Transactional;

	public class CadastroProdutoService implements Serializable{

		private static final long serialVersionUID = 1L;

		@Inject
		private Produtos produtos;

		@Inject
		private Usuarios usuarios;

		@Inject
		private FotoService fotoService;

		@Transactional
		public Produto salvar(Produto produto){
			Produto produtoExistente = produtos.porNome(produto.getNome());

			if(produtoExistente != null && produtoExistente.equals(produto)){
				throw new NegocioException("Já existe um produto com o nome informado");
				}

			if (produtoExistente != null && StringUtils.isNotEmpty(produtoExistente.getFoto())
					&& !produtoExistente.getFoto().equals(produto.getFoto())) {
				try {
					fotoService.deletar(produtoExistente.getFoto());
				} catch (IOException e) {
					throw new NegocioException("Problemas ao tentar remover foto antiga.", e);
				}
			}

			try {
				fotoService.recuperarFotoTemporaria(produto.getFoto());
			} catch (IOException e) {
				throw new NegocioException("Não foi possível recuperar foto temporária.", e);
			}

			return produtos.guardar(produto);
		}

		public Usuario salvar(Usuario usuario) {
Usuario usuarioExistente = usuarios.porId(usuario.getId());


			return null;
		}
	}
