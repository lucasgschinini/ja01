package ar.com.telecom.shiva.base.ws.servidor.datos.valoresDisponibles;

import java.util.ArrayList;
import java.util.Collection;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "listaTransferencias")
public class ListaTransferencias {
	
	@XmlElement(name = "transferencia", required = true)
	private Collection<ValoresTransferencia>  listaTransferencias  = new ArrayList<ValoresTransferencia>();

	public Collection<ValoresTransferencia> getListaTransferencias() {
		return listaTransferencias;
	}

	public void setListaTransferencias(
			Collection<ValoresTransferencia> listaTransferencias) {
		this.listaTransferencias = listaTransferencias;
	}
}
