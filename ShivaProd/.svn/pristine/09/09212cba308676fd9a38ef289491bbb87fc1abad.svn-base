package ar.com.telecom.shiva.persistencia.modelo;
import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Embeddable
public class ShvValConstanciaRecepcionValorPk implements Serializable{

 
	private static final long serialVersionUID = -1L;

	@OneToOne(cascade = CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_CONSTANCIA_RECEPCION", referencedColumnName="ID_CONSTANCIA_RECEPCION") 
	private ShvValConstanciaRecepcion constanciaRecepcion;

	@OneToOne(cascade = CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_VALOR", referencedColumnName="ID_VALOR") 
	private ShvValValor valor;

	public ShvValConstanciaRecepcion getConstanciaRecepcion() {
		return constanciaRecepcion;
	}

	public void setConstanciaRecepcion(ShvValConstanciaRecepcion constanciaRecepcion) {
		this.constanciaRecepcion = constanciaRecepcion;
	}

	public ShvValValor getValor() {
		return valor;
	}

	public void setValor(ShvValValor valor) {
		this.valor = valor;
	}



}
