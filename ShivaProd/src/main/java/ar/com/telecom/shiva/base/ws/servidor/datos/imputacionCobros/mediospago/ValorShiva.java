package ar.com.telecom.shiva.base.ws.servidor.datos.imputacionCobros.mediospago;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "valorShiva")
public class ValorShiva extends MedioDePago implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@XmlElement(required=true)
	private Long idValor;
	@XmlElement(required=true)
	private Long idClienteLegado;
	@XmlTransient
	private Date fechaMedioPago;
	
	@Override
	public Date getFechaMedioPago(){
		return  fechaMedioPago;
	}
	
	public void setFechaMedioPago(Date fechaValor) {
		this.fechaMedioPago = fechaValor;
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
	 * @return the idValor
	 */
	public Long getIdValor() {
		return idValor;
	}

	/**
	 * @param idValor the idValor to set
	 */
	public void setIdValor(Long idValor) {
		this.idValor = idValor;
	}
}
