package ar.com.telecom.shiva.base.ws.servidor.datos.valoresDisponibles;

import java.util.ArrayList;
import java.util.Collection;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "listaDepositosEfectivo")
public class ListaDepositosEfectivo {
	
	@XmlElement(name = "efectivo", required = true)
	private Collection<ValoresEfectivo>  listaDepositosEfectivo  = new ArrayList<ValoresEfectivo>();

	public Collection<ValoresEfectivo> getListaDepositosEfectivo() {
		return listaDepositosEfectivo;
	}

	public void setListaDepositosEfectivo(
			Collection<ValoresEfectivo> listaDepositosEfectivo) {
		this.listaDepositosEfectivo = listaDepositosEfectivo;
	}
}
