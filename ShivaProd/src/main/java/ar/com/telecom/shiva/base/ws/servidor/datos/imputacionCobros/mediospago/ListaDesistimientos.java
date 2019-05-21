package ar.com.telecom.shiva.base.ws.servidor.datos.imputacionCobros.mediospago;

import java.util.ArrayList;
import java.util.Collection;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "listaDesistimientos")
public class ListaDesistimientos {

	@XmlElement(name = "desistimiento")
	private Collection<Desistimiento> desistimientos = new ArrayList<Desistimiento>();

	public Collection<Desistimiento> getDesistimientos() {
		return desistimientos;
	}

	public void setDesistimientos(Collection<Desistimiento> desistimientos) {
		this.desistimientos = desistimientos;
	}

	
}
