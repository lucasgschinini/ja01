package ar.com.telecom.shiva.persistencia.modelo.simple;
import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import ar.com.telecom.shiva.persistencia.modelo.ShvValConstanciaRecepcion;

/**
 * @author u564030 Pablo M. Ibarrola
 * 
 * Req 70868 Mejora de Performance en Talonarios.
 * Se ha detectado que al levantar datos de constancia (y solo de constancia) se está levantando 
 * el objeto valor asociado a la constancia de manera innecesaria.
 * Se crea una nueva PK sin el valor asociado.
 */
@Embeddable
public class ShvValConstanciaRecepcionValorSimplificadoPk implements Serializable{

	private static final long serialVersionUID = -1L;

	@OneToOne(cascade = CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_CONSTANCIA_RECEPCION", referencedColumnName="ID_CONSTANCIA_RECEPCION") 
	private ShvValConstanciaRecepcion constanciaRecepcion;

	@Column(name = "ID_VALOR", nullable = false)
	private Long idValor;

	/**
	 * @return the constanciaRecepcion
	 */
	public ShvValConstanciaRecepcion getConstanciaRecepcion() {
		return constanciaRecepcion;
	}

	/**
	 * @param constanciaRecepcion the constanciaRecepcion to set
	 */
	public void setConstanciaRecepcion(ShvValConstanciaRecepcion constanciaRecepcion) {
		this.constanciaRecepcion = constanciaRecepcion;
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
