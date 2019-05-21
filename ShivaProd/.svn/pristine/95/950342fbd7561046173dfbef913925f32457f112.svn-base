package ar.com.telecom.shiva.base.ws.servidor.datos.imputacionCobros.mediospago;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "compensacion")
public class Compensacion extends MedioDePago implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@XmlElement(required=true)
	private Long numeroActa;
	@XmlElement(required=true)
	private long fechaFirmaActa;
	@XmlElement(required=true)
	private Long idClienteLegado;
	
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
	/** devuelve el valor utilizado para ordenar por fecha */
	public Date getFechaMedioPago(){
		return new Date(0);
	}
	
	/**
	 * @return the numeroActa
	 */
	public Long getNumeroActa() {
		return numeroActa;
	}
	/**
	 * @param numeroActa the numeroActa to set
	 */
	public void setNumeroActa(Long numeroActa) {
		this.numeroActa = numeroActa;
	}
	/**
	 * @return the fechaFirmaActa
	 */
	public long getFechaFirmaActa() {
		return fechaFirmaActa;
	}
	/**
	 * @param fechaFirmaActa the fechaFirmaActa to set
	 */
	public void setFechaFirmaActa(long fechaFirmaActa) {
		this.fechaFirmaActa = fechaFirmaActa;
	}
	
}
