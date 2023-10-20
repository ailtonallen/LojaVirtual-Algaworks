package com.farmacia.lojavirtual.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import com.farmacia.lojavirtual.model.Entrega;
import com.farmacia.lojavirtual.model.Pedido;
import com.farmacia.lojavirtual.model.Produto;
import com.farmacia.lojavirtual.repository.filter.PedidoFilter;
import com.farmacia.lojavirtual.service.NegocioException;
import com.farmacia.lojavirtual.util.jpa.Transactional;

public class Pedidos1 implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Pedido guardar(Pedido pedido) {
		return pedido = manager.merge(pedido);
	}

	@Transactional
	public void remover(Pedido pedido) throws NegocioException {
		try {
			pedido = porId(pedido.getId());
			manager.remove(pedido);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Produto não pode ser excluído.");
		}
	}

	public List<Pedido> filtrados(PedidoFilter filtro) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Pedido> criteriaQuery = builder.createQuery(Pedido.class);
		List<Predicate> predicates = new ArrayList<>();

		Root<Pedido> pedidoRoot = criteriaQuery.from(Pedido.class);
		Fetch<Pedido, Entrega> entregaJoin = pedidoRoot.fetch("entrega",
				JoinType.INNER);


		if (StringUtils.isNotBlank(filtro.getNomeUsuario())) {
			predicates.add(builder.equal(pedidoRoot.get("nome"),
					filtro.getNomeUsuario()));
		}

		if (StringUtils.isNotBlank(filtro.getNomeUsuario())) {
			predicates.add(builder.like(
					builder.lower(pedidoRoot.get("pedido")), "%"
							+ filtro.getNomeUsuario().toLowerCase() + "%"));
		}

		criteriaQuery.select(pedidoRoot);
		criteriaQuery.where(predicates.toArray(new Predicate[0]));
		criteriaQuery.orderBy(builder.asc(pedidoRoot.get("pedido")));

		TypedQuery<Pedido> query = manager.createQuery(criteriaQuery);
		return query.getResultList();
	}

	public Pedido porId(Long id) {
		return manager.find(Pedido.class, id);
	}

	public List<Pedido> lista() {
		return manager.createQuery("from Pedido", Pedido.class).getResultList();
	}

	public List<Pedido> porProduto(String nome) {

		return manager
				.createQuery("From Produto where upper(nome) like :nome",
						Pedido.class)
				.setParameter("nome", nome.toLowerCase() + "%").getResultList();
	}

	public Pedido porProduto(Produto produto) {
		// TODO Auto-generated method stub
		return null;
	}

}
