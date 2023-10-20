package com.farmacia.lojavirtual.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.farmacia.lojavirtual.model.Entrega;
import com.farmacia.lojavirtual.repository.Entregas;
import com.farmacia.lojavirtual.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Entrega.class)
public class EntregaConverter implements Converter{
	// @Inject
		private Entregas entregas;

		public EntregaConverter() {
			this.entregas = CDIServiceLocator
					.getBean(Entregas.class);
		}

		@Override
		public Object getAsObject(FacesContext context, UIComponent component,
				String value) {
			Entrega retorno = null;

			if (value != null) {
				Long id = new Long(value);
				retorno = entregas.porId(id);
			}

			return retorno;
		}

		@Override
		public String getAsString(FacesContext context, UIComponent component,
				Object value) {

			if (value != null) {
				Entrega entrega = (Entrega) value;
				return entrega.getId() == null ? null : entrega.getId()
						.toString();
			}

			return "";
		}


}
