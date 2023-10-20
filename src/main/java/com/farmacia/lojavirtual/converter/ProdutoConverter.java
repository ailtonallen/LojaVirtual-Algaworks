package com.farmacia.lojavirtual.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.farmacia.lojavirtual.model.Produto;
import com.farmacia.lojavirtual.repository.Produtos;
import com.farmacia.lojavirtual.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Produto.class)
public class ProdutoConverter implements Converter {

	//@Inject
	private Produtos produtos;

	public ProdutoConverter() {
		this.produtos = CDIServiceLocator.getBean(Produtos.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		Produto retorno = null;

		if (value != null) {
			retorno = this.produtos.porId(new Long(value));

		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value != null) {
			Produto produto = (Produto) value;
			return produto.getId() == null ? null : produto.getId().toString();
		}

		return "";
	}

}