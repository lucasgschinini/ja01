package ar.com.telecom.shiva.persistencia.modelo;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Embeddable
public class ShvValReversionValorPK implements Serializable{

	private static final long serialVersionUID = -1L;

	@OneToOne(cascade = CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_VALOR_POR_REVERSION", referencedColumnName="ID_VALOR_POR_REVERSION") 
	private ShvValValorPorReversion valorPorReversion;

	@OneToOne(cascade = CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_VALOR", referencedColumnName="ID_VALOR") 
	private ShvValValor valor;


	public ShvValValorPorReversion getValorPorReversion() {
		return valorPorReversion;
	}

	public void setValorPorReversion(ShvValValorPorReversion valorPorReversion) {
		this.valorPorReversion = valorPorReversion;
	}

	public ShvValValor getValor() {
		return valor;
	}

	public void setValor(ShvValValor valor) {
		this.valor = valor;
	}

}
