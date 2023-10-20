package com.farmacia.lojavirtual.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import com.farmacia.lojavirtual.model.Pedido;
import com.farmacia.lojavirtual.repository.Pedidos1;

@FacesConverter(forClass = Pedido.class)
public class PedidoConverter implements Converter {

	@Inject
	private Pedidos1 pedidos;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		Pedido retorno = null;

		if (StringUtils.isNotEmpty(value)) {
			Long id = new Long(value);
			retorno = pedidos.porId(id);
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value != null) {
			Pedido pedido = (Pedido) value;
			return pedido.getId() == null ? null : pedido.getId().toString();
		}

		return "";
	}

}
