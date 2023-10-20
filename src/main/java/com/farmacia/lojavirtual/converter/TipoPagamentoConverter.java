package com.farmacia.lojavirtual.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.farmacia.lojavirtual.model.TipoPagamento;
import com.farmacia.lojavirtual.repository.TipoPagamentos;
import com.farmacia.lojavirtual.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = TipoPagamento.class)
public class TipoPagamentoConverter implements Converter {
	// @Inject
	private TipoPagamentos tipoPagamentos;

	public TipoPagamentoConverter() {
		this.tipoPagamentos = CDIServiceLocator
				.getBean(TipoPagamentos.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		TipoPagamento retorno = null;

		if (value != null) {
			Long id = new Long(value);
			retorno = tipoPagamentos.porId(id);
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {

		if (value != null) {
			TipoPagamento tipoPagamento = (TipoPagamento) value;
			return tipoPagamento.getId() == null ? null : tipoPagamento.getId()
					.toString();
		}

		return "";
	}

}
