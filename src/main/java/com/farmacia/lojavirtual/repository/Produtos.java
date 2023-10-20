package com.farmacia.lojavirtual.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import com.farmacia.lojavirtual.model.Categoria;
import com.farmacia.lojavirtual.model.Produto;
import com.farmacia.lojavirtual.repository.filter.ProdutoFilter;
import com.farmacia.lojavirtual.service.NegocioException;
import com.farmacia.lojavirtual.util.jpa.Transactional;

public class Produtos implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Produto guardar(Produto produto) {
		return manager.merge(produto);
	}

	@Transactional
	public void remover(Produto produto) throws NegocioException {
		try {
			produto = porId(produto.getId());
			manager.remove(produto);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Produto não pode ser excluído.");
		}
	}

	public Produto porNome(String nome) {
		try {
			return manager
					.createQuery("from Produto where upper(nome) = :nome",
							Produto.class)
					.setParameter("nome", nome.toUpperCase()).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<Produto> filtrados(ProdutoFilter filtro) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Produto> criteriaQuery = builder
				.createQuery(Produto.class);
		List<Predicate> predicates = new ArrayList<>();

		Root<Produto> produtoRoot = criteriaQuery.from(Produto.class);
		Fetch<Produto, Categoria> categoriaJoin = produtoRoot.fetch("categoria",
				JoinType.INNER);
		categoriaJoin.fetch("categoria", JoinType.INNER);

		if (StringUtils.isNotBlank(filtro.getNome())) {
			predicates.add(builder.equal(produtoRoot.get("nome"),
					filtro.getNome()));
		}

		if (StringUtils.isNotBlank(filtro.getNome())) {
			predicates.add(builder.like(builder.lower(produtoRoot.get("nome")),
					"%" + filtro.getNome().toLowerCase() + "%"));
		}

		criteriaQuery.select(produtoRoot);
		criteriaQuery.where(predicates.toArray(new Predicate[0]));
		criteriaQuery.orderBy(builder.asc(produtoRoot.get("nome")));

		TypedQuery<Produto> query = manager.createQuery(criteriaQuery);
		return query.getResultList();
	}

	public Produto porId(Long id) {
		return manager.find(Produto.class, id);
	}

	public List<Produto> porFabricante(String fabricante) {
		return this.manager
				.createQuery(
						"from Produto where upper(fabricante) like :fabricante",
						Produto.class)
				.setParameter("fabricante", fabricante.toUpperCase() + "%")
				.getResultList();
	}

	public List<Produto> porProduto(String nome) {

		return manager
				.createQuery("From Produto where upper(nome) like :nome",
						Produto.class)
				.setParameter("nome", nome.toLowerCase() + "%").getResultList();
	}

}