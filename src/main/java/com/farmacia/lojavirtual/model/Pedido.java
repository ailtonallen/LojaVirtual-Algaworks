package com.farmacia.lojavirtual.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table
public class Pedido implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private Usuario usuario;
	private TipoPagamento tipo;
	private StatusPedido status;
	private short quantidade;
	private Date dataCriacao;
	private Date dataEntrega;
	private BigDecimal valorTransporte = BigDecimal.ZERO;
	private BigDecimal valorTotal = BigDecimal.ZERO;
	private BigDecimal valor = BigDecimal.ZERO;
	private Entrega Entrega;
	private Pedido pedido;
	private Produto Produto;
	private List<Produto> itens = new ArrayList<>();

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@OneToOne
	@JoinColumn(name = "tipo_id")
	public TipoPagamento getTipo() {
		return tipo;
	}

	public void setTipo(TipoPagamento tipo) {
		this.tipo = tipo;
	}

	@NotNull
	@Column(name = "dataCriacao", nullable = false)
	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date date) {
		this.dataCriacao = date;
	}

	@NotNull
	@Column(name = "dataEntrega", nullable = false)
	public Date getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(Date date) {
		this.dataEntrega = date;
	}

	@NotNull
	@Column(name = "valorTransporte", precision = 10, scale = 2, nullable = false)
	public BigDecimal getValorTransporte() {
		return valorTransporte;
	}

	public void setValorTransporte(BigDecimal valorTransporte) {
		this.valorTransporte = valorTransporte;
	}

	@NotNull
	@Column(name = "valorTotal", precision = 10, scale = 2, nullable = false)
	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;

	}

	@OneToOne
	@JoinColumn(name = "Entrega_id")
	public Entrega getEntrega() {
		return Entrega;
	}

	public void setEntrega(Entrega Entrega) {
		this.Entrega = Entrega;
	}

	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	public List<Produto> getItens() {
		return itens;
	}

	public void setItens(List<Produto> itens) {
		this.itens = itens;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		return result = prime * result + ((id == null) ? 0 : id.hashCode());

	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if ((obj == null) || (getClass() != obj.getClass()))
			return false;
		Pedido other = (Pedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;

	}

	@NotNull
	@Column(name = "valor", precision = 10, scale = 2, nullable = false)
	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public short getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(short quantidade) {
		this.quantidade = quantidade;
	}

	@ManyToOne
	@JoinColumn(name = "produto_id")
	public Produto getProduto() {
		return Produto;
	}

	public void setProduto(Produto produto) {
		Produto = produto;
	}

	public void setStatus(String nome) {

	}

	public StatusPedido getStatus() {
		return status;
	}

	public void setStatus(StatusPedido status) {
		this.status = status;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
}