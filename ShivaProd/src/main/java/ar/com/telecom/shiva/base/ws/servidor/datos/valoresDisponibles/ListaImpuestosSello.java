package ar.com.telecom.shiva.base.ws.servidor.datos.valoresDisponibles;

import java.util.ArrayList;
import java.util.Collection;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "listaImpuestosSello")
public class ListaImpuestosSello {
	
	@XmlElement(name = "impuestoSello", required = true)
	private Collection<ValoresImpuestoSello>  listaImpuestosSello  = new ArrayList<ValoresImpuestoSello>();

	public Collection<ValoresImpuestoSello> getListaImpuestosSello() {
		return listaImpuestosSello;
	}

	public void setListaImpuestosSello(
			Collection<ValoresImpuestoSello> listaImpuestosSello) {
		this.listaImpuestosSello = listaImpuestosSello;
	}

	
	
}
