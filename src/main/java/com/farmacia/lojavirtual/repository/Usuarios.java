package com.farmacia.lojavirtual.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.farmacia.lojavirtual.model.Usuario;
import com.farmacia.lojavirtual.repository.filter.UsuarioFilter;
import com.farmacia.lojavirtual.service.NegocioException;
import com.farmacia.lojavirtual.util.jpa.Transactional;

public class Usuarios implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Usuario getUsuario(String email, String senha) {
		try {
			Usuario usuario = (Usuario) manager
					.createQuery(
							"SELECT u from Usuario u where u.email = :name and u.senha = :senha")
					.setParameter("name", email).setParameter("senha", senha)
					.getSingleResult();
			return usuario;
		} catch (NoResultException e) {
			return null;
		}

	}

	public Usuario porId(Long id) {
		return this.manager.find(Usuario.class, id);
	}

	public List<Usuario> Loja() {
		// filtra apenas os funcionarios por um grupo especifico
		return this.manager.createQuery("from Usuario", Usuario.class)
				.getResultList();
	}

	public Usuario porEmail(String email) {
		Usuario usuario = null;

		try {
			usuario = this.manager
					.createQuery("from Usuario where lower(email) = :email",
							Usuario.class)
					.setParameter("email", email.toLowerCase())
					.getSingleResult();
		} catch (NoResultException e) {
			// nenhum usuario encontrado com o email informado
		}
		return usuario;
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> filtrados(UsuarioFilter filtro) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Usuario.class);

		if (StringUtils.isNotBlank(filtro.getNome())) {
			criteria.add(Restrictions.ilike("nome", filtro.getNome(),
					MatchMode.ANYWHERE));

		}
		if (StringUtils.isNotBlank(filtro.getEmail())) {
			criteria.add(Restrictions.ilike("email", filtro.getEmail(),
					MatchMode.ANYWHERE));
		}
		return criteria.addOrder(Order.asc("id")).list();
	}

	public Usuario guardar(Usuario usuario) {

		return usuario = manager.merge(usuario);
	}

	@Transactional
	public void remover(Usuario usuario) {
		try {
			usuario = porId(usuario.getId());
			manager.remove(usuario);

			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Usuario nao pode ser excluido");
		}
	}

	public List<Usuario> lista() {
		return manager.createQuery("from Usuario", Usuario.class)
				.getResultList();
	}

	public Usuario porNome(String nome) {
		// TODO Auto-generated method stub
		return null;
	}
}
