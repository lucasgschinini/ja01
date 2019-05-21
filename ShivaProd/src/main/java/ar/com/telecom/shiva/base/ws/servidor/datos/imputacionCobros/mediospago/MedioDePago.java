package ar.com.telecom.shiva.base.ws.servidor.datos.imputacionCobros.mediospago;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;

@XmlAccessorType(XmlAccessType.FIELD)
public class MedioDePago implements Serializable, Comparable<MedioDePago>  {

	private static final long serialVersionUID = 1L;
	
	@XmlElement(required=true)
	private BigDecimal importe;
	
	/** devuelve el valor utilizado para ordenar por fecha 
	 * @throws NegocioExcepcion */
	public Date getFechaMedioPago(){
		return  null;
	}
	/**
	 * @return the importe
	 */
	public BigDecimal getImporte() {
		return importe;
	}
	/**
	 * @param importe the importe to set
	 */
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}
	
	@Override
	public int compareTo(MedioDePago o) {
		return this.getFechaMedioPago().compareTo(o.getFechaMedioPago());
	}
}
