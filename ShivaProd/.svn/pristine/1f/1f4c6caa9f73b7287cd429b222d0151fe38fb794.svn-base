package ar.com.telecom.shiva.base.ws.servidor.datos.imputacionCobros.mediospago;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.ws.servidor.datos.imputacionCobros.IdDocumento;
import ar.com.telecom.shiva.base.ws.servidor.datos.imputacionCobros.facturas.InformacionAdicionalTagetikCalipso;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "notaCreditoCalipso")
public class NotaCreditoCalipso extends MedioDePago implements Serializable  {

	private static final long serialVersionUID = 1L;
	
	@XmlElement(required=true)
	private IdDocumento idDocumento;
	@XmlElement(required=true)
	private Long idDocumentoCuentaCobranza;
	@XmlElement(required=true)
	private long fechaEmision;
	@XmlElement(required=true)
	private InformacionAdicionalTagetikCalipso  informacionAdicionalTageticCalipso;
	
	@XmlElement(required=true)
	private Long idClienteLegado;
	
	
	
	public Date getFechaMedioPago() {
		try {
			return Utilidad.parseDateWSFormat(String.valueOf(this.fechaEmision));
		} catch (ParseException e) {
			return null;
		}
	}
	/**
	 * @return the idClienteLegado
	 */
	public Long getIdClienteLegado() {
		return idClienteLegado;
	}
	/**
	 * @param idClienteLegado the idClienteLegado to set
	 */
	public void setIdClienteLegado(Long idClienteLegado) {
		this.idClienteLegado = idClienteLegado;
	}
	
	/**
	 * @return the idDocumento
	 */
	public IdDocumento getIdDocumento() {
		return idDocumento;
	}

	/**
	 * @param idDocumento the idDocumento to set
	 */
	public void setIdDocumento(IdDocumento idDocumento) {
		this.idDocumento = idDocumento;
	}

	/**
	 * @return the fechaEmision
	 */
	public long getFechaEmision() {
		return fechaEmision;
	}

	/**
	 * @param fechaEmision the fechaEmision to set
	 */
	public void setFechaEmision(long fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	public Long getIdDocumentoCuentaCobranza() {
		return idDocumentoCuentaCobranza;
	}
	public void setIdDocumentoCuentaCobranza(Long idDocumentoCuentaCobranza) {
		this.idDocumentoCuentaCobranza = idDocumentoCuentaCobranza;
	}
	public InformacionAdicionalTagetikCalipso getInformacionAdicionalTageticCalipso() {
		return informacionAdicionalTageticCalipso;
	}
	public void setInformacionAdicionalTageticCalipso(
			InformacionAdicionalTagetikCalipso informacionAdicionalTageticCalipso) {
		this.informacionAdicionalTageticCalipso = informacionAdicionalTageticCalipso;
	}
	
}
