package ar.com.telecom.shiva.base.ws.servidor.datos.imputacionCobros.mediospago;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.ws.servidor.datos.imputacionCobros.facturas.InformacionAdicionalTagetikNotaCreditoMIC;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "notaCreditoMIC")
public class NotaCreditoMIC extends MedioDePago implements Serializable  {

	private static final long serialVersionUID = 1L;
	
	@XmlElement(required=true)
	private String  numeroNotaCredito;
	
	@XmlElement(required=true)
	private long 	fechaEmision;
	
	@XmlElement(required=true)
	private InformacionAdicionalTagetikNotaCreditoMIC  informacionAdicionalTageticNotaCreditoMic;
	@XmlElement(required=true)
	private Long idClienteLegado;
	
	public Date getFechaMedioPago() {
		try {
			return Utilidad.parseDateWSFormat(String.valueOf(this.fechaEmision));
		} catch (ParseException e) {
			return null;
		}
	}
	
	public InformacionAdicionalTagetikNotaCreditoMIC getInformacionAdicionalTageticNotaCreditoMic() {
		return informacionAdicionalTageticNotaCreditoMic;
	}
	public void setInformacionAdicionalTageticNotaCreditoMic(InformacionAdicionalTagetikNotaCreditoMIC informacionAdicionalTageticMic) {
		this.informacionAdicionalTageticNotaCreditoMic = informacionAdicionalTageticMic;
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
	
	public long getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(long fechaEmision) {
		this.fechaEmision = fechaEmision;
	}


	/**
	 * @return the numeroNotaCredito
	 */
	public String getNumeroNotaCredito() {
		return numeroNotaCredito;
	}

	/**
	 * @param numeroNotaCredito the numeroNotaCredito to set
	 */
	public void setNumeroNotaCredito(String numeroNotaCredito) {
		this.numeroNotaCredito = numeroNotaCredito;
	}
	
}
