package ar.com.telecom.shiva.persistencia.modelo;
import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class ShvCobEdClientePk implements Serializable{
	private static final long serialVersionUID = -1L;

    @Column(name="ID_CLIENTE_COBRO")	
	private Long idClienteCobro;
	
	@ManyToOne(cascade={CascadeType.DETACH}, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_COBRO", referencedColumnName="ID_COBRO")
	private ShvCobEdCobro cobro;

	public Long getIdClienteCobro() {
		return idClienteCobro;
	}
	public void setIdClienteCobro(Long idClienteCobro) {
		this.idClienteCobro = idClienteCobro;
	}

	public ShvCobEdCobro getCobro() {
		return cobro;
	}
	public void setCobro(ShvCobEdCobro cobro) {
		this.cobro = cobro;
	}

}
