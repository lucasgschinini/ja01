package ar.com.telecom.shiva.base.ws.servidor.datos.valoresDisponibles;

import java.util.ArrayList;
import java.util.Collection;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "listaRetencionesSegSoc")
public class ListaRetencionesSegSoc {
	
	@XmlElement(name = "retencionSegSoc", required = true)
	private Collection<ValoresRetencionSeguridadSocial>  listaRetencionesSegSoc  = new ArrayList<ValoresRetencionSeguridadSocial>();

	public Collection<ValoresRetencionSeguridadSocial> getListaRetencionesSegSoc() {
		return listaRetencionesSegSoc;
	}

	public void setListaRetencionesSegSoc(
			Collection<ValoresRetencionSeguridadSocial> listaRetencionesSegSoc) {
		this.listaRetencionesSegSoc = listaRetencionesSegSoc;
	}
	
}
