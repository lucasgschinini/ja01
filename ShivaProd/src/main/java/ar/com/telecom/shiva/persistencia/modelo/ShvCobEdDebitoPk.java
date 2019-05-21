package ar.com.telecom.shiva.persistencia.modelo;
import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class ShvCobEdDebitoPk implements Serializable{
	private static final long serialVersionUID = -1L;

	@Column(name = "ID_DEBITO")
	private Long idDebito;
	
	
	@ManyToOne(cascade = {CascadeType.DETACH}, fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_COBRO", referencedColumnName = "ID_COBRO")
	private ShvCobEdCobro cobro;


	public Long getIdDebito() {
		return idDebito;
	}
	public void setIdDebito(Long idDebito) {
		this.idDebito = idDebito;
	}


	public ShvCobEdCobro getCobro() {
		return cobro;
	}
	public void setCobro(ShvCobEdCobro cobro) {
		this.cobro = cobro;
	}
}
