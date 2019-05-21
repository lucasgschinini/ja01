package ar.com.telecom.shiva.base.ws.servidor.datos.imputacionCobros.mediospago;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "desistimiento")
public class Desistimiento extends MedioDePago implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@XmlElement(required=true)
	private Long numeroActa;
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
}
