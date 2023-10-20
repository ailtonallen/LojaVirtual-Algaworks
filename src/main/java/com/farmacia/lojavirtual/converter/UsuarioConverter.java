package com.farmacia.lojavirtual.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.farmacia.lojavirtual.model.Usuario;
import com.farmacia.lojavirtual.repository.Usuarios;
import com.farmacia.lojavirtual.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Usuario.class)
public class UsuarioConverter implements Converter {

	// @Inject
	private Usuarios usuarios;

	public UsuarioConverter() {
		this.usuarios = CDIServiceLocator.getBean(Usuarios.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		Usuario retorno = null;

		if (value != null) {
			retorno = this.usuarios.porId(new Long(value));
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value != null) {
			Usuario usuario = (Usuario) value;
			return usuario.getId() == null ? null : usuario.getId().toString();
			// return ((Usuario) value).getId().toString();
		}
		return "";
	}

}
