package ar.com.telecom.shiva.persistencia.modelo;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class ShvCajCajaCajeroPk implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(cascade = CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_CAJA")
	private ShvCajCaja caja;
	
	@Column (name = "USUARIO")
	private String usuario;

	/**
	 * @return the idCaja
	 */
	public ShvCajCaja getCaja() {
		return caja;
	}

	/**
	 * @param idCaja the idCaja to set
	 */
	public void setCaja(ShvCajCaja idCaja) {
		this.caja = idCaja;
	}

	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
}
