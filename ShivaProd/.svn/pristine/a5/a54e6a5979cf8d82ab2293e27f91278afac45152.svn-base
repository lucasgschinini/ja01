package ar.com.telecom.shiva.persistencia.modelo;
import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author U587496 Guido.Settecerze
 */

@Embeddable
public class ShvCobEdOtrosDebitoPk implements Serializable{
	
	private static final long serialVersionUID = -1L;

	@Column(name = "ID_OTROS_DEBITO")
	private Long idOtrosDebito;
	
	
	@ManyToOne(cascade = {CascadeType.DETACH}, fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_COBRO", referencedColumnName = "ID_COBRO")
	private ShvCobEdCobro cobro;


	/**
	 * @return the idOtrosDebito
	 */
	public Long getIdOtrosDebito() {
		return idOtrosDebito;
	}


	/**
	 * @param idOtrosDebito the idOtrosDebito to set
	 */
	public void setIdOtrosDebito(Long idOtrosDebito) {
		this.idOtrosDebito = idOtrosDebito;
	}


	/**
	 * @return the cobro
	 */
	public ShvCobEdCobro getCobro() {
		return cobro;
	}


	/**
	 * @param cobro the cobro to set
	 */
	public void setCobro(ShvCobEdCobro cobro) {
		this.cobro = cobro;
	}

	
}