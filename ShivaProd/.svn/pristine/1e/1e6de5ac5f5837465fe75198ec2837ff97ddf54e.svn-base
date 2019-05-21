package ar.com.telecom.shiva.base.ws.servidor.datos.valoresDisponibles;

import java.util.ArrayList;
import java.util.Collection;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "listaCheques")
public class ListaCheques {
	
	@XmlElement(name = "cheque", required = true)
	private Collection<ValoresCheque>  listaCheques  = new ArrayList<ValoresCheque>();

	public Collection<ValoresCheque> getListaCheques() {
		return listaCheques;
	}

	public void setListaCheques(Collection<ValoresCheque> listaCheques) {
		this.listaCheques = listaCheques;
	}
}
