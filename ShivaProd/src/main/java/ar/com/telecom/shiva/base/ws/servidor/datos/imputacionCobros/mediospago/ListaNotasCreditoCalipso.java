package ar.com.telecom.shiva.base.ws.servidor.datos.imputacionCobros.mediospago;

import java.util.ArrayList;
import java.util.Collection;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "listaNotasCreditoCalipso")
public class ListaNotasCreditoCalipso {

	@XmlElement(name = "notaCreditoCalipso")
	private Collection<NotaCreditoCalipso> notasCreditoCalipso	= new ArrayList<NotaCreditoCalipso>();

	public Collection<NotaCreditoCalipso> getNotasCreditoCalipso() {
		return notasCreditoCalipso;
	}

	public void setNotasCreditoCalipso(
			Collection<NotaCreditoCalipso> notasCreditoCalipso) {
		this.notasCreditoCalipso = notasCreditoCalipso;
	}

}
