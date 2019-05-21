package ar.com.telecom.shiva.base.ws.servidor.datos.imputacionCobros.facturas;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "facturaCalipso")
public class FacturaCalipso extends Factura implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@XmlElement(required=true)
	private Long idDocumentoCuentaCobranza;
	
	@XmlElement(required=true)
    	private InformacionAdicionalTagetikCalipso 	informacionAdicionalTagetikCalipso;

	/**
	 * @return the informacionAdicionalTagetikCalipso
	 */
	public InformacionAdicionalTagetikCalipso getInformacionAdicionalTagetikCalipso() {
		return informacionAdicionalTagetikCalipso;
	}

	/**
	 * @param informacionAdicionalTagetikCalipso the informacionAdicionalTagetikCalipso to set
	 */
	public void setInformacionAdicionalTagetikCalipso(InformacionAdicionalTagetikCalipso informacionAdicionalTagetikCalipso) {
		this.informacionAdicionalTagetikCalipso = informacionAdicionalTagetikCalipso;
	}

	public Long getIdDocumentoCuentaCobranza() {
		return idDocumentoCuentaCobranza;
	}

	public void setIdDocumentoCuentaCobranza(Long idDocumentoCuentaCobranza) {
		this.idDocumentoCuentaCobranza = idDocumentoCuentaCobranza;
	}
	
}
