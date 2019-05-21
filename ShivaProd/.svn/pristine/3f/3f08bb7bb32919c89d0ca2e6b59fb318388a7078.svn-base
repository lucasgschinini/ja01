package ar.com.telecom.shiva.base.ws.servidor.datos.valoresDisponibles;

import java.util.ArrayList;
import java.util.Collection;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "listaInterdepositos")
public class ListaInterdepositos {
	
	@XmlElement(name = "interdeposito", required = true)
	private Collection<ValoresInterdeposito>  listaInterdepositos  = new ArrayList<ValoresInterdeposito>();

	public Collection<ValoresInterdeposito> getListaInterdepositos() {
		return listaInterdepositos;
	}

	public void setListaInterdepositos(
			Collection<ValoresInterdeposito> listaInterdepositos) {
		this.listaInterdepositos = listaInterdepositos;
	}
}
