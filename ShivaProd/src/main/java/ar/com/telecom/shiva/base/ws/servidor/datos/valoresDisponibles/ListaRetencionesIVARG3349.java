package ar.com.telecom.shiva.base.ws.servidor.datos.valoresDisponibles;

import java.util.ArrayList;
import java.util.Collection;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "listaRetencionesIVARG3349")
public class ListaRetencionesIVARG3349 {
	
	@XmlElement(name = "retencionIVARG3349", required = true)
	private Collection<ValoresRetencionIVARG3349>  listaRetencionesIVARG3349  = new ArrayList<ValoresRetencionIVARG3349>();

	public Collection<ValoresRetencionIVARG3349> getListaRetencionesIVARG3349() {
		return listaRetencionesIVARG3349;
	}

	public void setListaRetencionesIVARG3349(
			Collection<ValoresRetencionIVARG3349> listaRetencionesIVARG3349) {
		this.listaRetencionesIVARG3349 = listaRetencionesIVARG3349;
	}
	
}
