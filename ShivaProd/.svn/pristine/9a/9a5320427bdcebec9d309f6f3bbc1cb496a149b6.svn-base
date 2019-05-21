package ar.com.telecom.shiva.persistencia.modelo;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Embeddable
public class ShvAvcRegistroAvcValorPK implements Serializable{

	private static final long serialVersionUID = -1L;

	@OneToOne(cascade = CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_REGISTRO_AVC", referencedColumnName="ID_REGISTRO_AVC") 
	private ShvAvcRegistroAvc registroAvc;

	@OneToOne(cascade = CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_VALOR", referencedColumnName="ID_VALOR") 
	private ShvValValor valor;

	public ShvAvcRegistroAvc getRegistroAvc() {
		return registroAvc;
	}

	public void setRegistroAvc(ShvAvcRegistroAvc registroAvc) {
		this.registroAvc = registroAvc;
	}

	public ShvValValor getValor() {
		return valor;
	}

	public void setValor(ShvValValor valor) {
		this.valor = valor;
	}

}
