package ar.com.telecom.shiva.persistencia.modelo;
import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Embeddable
public class ShvAvcRegistroAvcBoletaPk implements Serializable {

 
	private static final long serialVersionUID = -1L;

	@OneToOne(cascade = CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_REGISTRO_AVC", referencedColumnName="ID_REGISTRO_AVC") 
	private ShvAvcRegistroAvc registroAvc;

	@OneToOne(cascade = CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_BOLETA", referencedColumnName="ID_BOLETA") 
	private ShvBolBoleta boleta;

	public ShvAvcRegistroAvc getRegistroAvc() {
		return registroAvc;
	}

	public void setRegistroAvc(ShvAvcRegistroAvc registroAvc) {
		this.registroAvc = registroAvc;
	}

	public ShvBolBoleta getBoleta() {
		return boleta;
	}

	public void setBoleta(ShvBolBoleta boleta) {
		this.boleta = boleta;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((boleta == null) ? 0 : boleta.hashCode());
		result = prime * result
				+ ((registroAvc == null) ? 0 : registroAvc.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShvAvcRegistroAvcBoletaPk other = (ShvAvcRegistroAvcBoletaPk) obj;
		if (boleta == null) {
			if (other.boleta != null)
				return false;
		} else if (!boleta.equals(other.boleta))
			return false;
		if (registroAvc == null) {
			if (other.registroAvc != null)
				return false;
		} else if (!registroAvc.equals(other.registroAvc))
			return false;
		return true;
	}



}
