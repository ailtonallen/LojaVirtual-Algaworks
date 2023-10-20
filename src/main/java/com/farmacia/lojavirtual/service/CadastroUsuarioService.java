package com.farmacia.lojavirtual.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.farmacia.lojavirtual.model.Usuario;
import com.farmacia.lojavirtual.repository.Usuarios;
import com.farmacia.lojavirtual.util.jpa.Transactional;

public class CadastroUsuarioService implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private Usuarios usuarios;

	@Transactional
	public Usuario salvar(Usuario usuario){
		Usuario usuarioExistente = usuarios.porEmail(usuario.getEmail());

		if(usuarioExistente != null && usuarioExistente.equals(usuario)){
			throw new NegocioException("Já existe um usuário com o e-mail informado");
		}
		return usuarios.guardar(usuario);
	}
}
