package ar.com.telecom.shiva.base.ws.servidor.datos.imputacionCobros.mediospago;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "remanente")
public class Remanente extends MedioDePago implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@XmlElement(required=true)
	private Long cuenta;
	@XmlElement(required=true)
	private int tipo;
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
	/**
	 * @return the cuenta
	 */
	public Long getCuenta() {
		return cuenta;
	}
	/**
	 * @param cuenta the cuenta to set
	 */
	public void setCuenta(Long cuenta) {
		this.cuenta = cuenta;
	}
	/**
	 * @return the tipo
	 */
	public int getTipo() {
		return tipo;
	}
	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	
}
