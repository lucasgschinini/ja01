package ar.com.telecom.shiva.base.ws.servidor.datos.imputacionCobros.mediospago;

import java.util.ArrayList;
import java.util.Collection;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "listaRemanentes")
public class ListaRemanentes {

	@XmlElement(name = "remanente")
	private Collection<Remanente> remanentes = new ArrayList<Remanente>();

	public Collection<Remanente> getRemanentes() {
		return remanentes;
	}

	public void setRemanentes(Collection<Remanente> remanentes) {
		this.remanentes = remanentes;
	}

}
