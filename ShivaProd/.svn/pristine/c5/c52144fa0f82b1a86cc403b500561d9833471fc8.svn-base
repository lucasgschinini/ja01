package ar.com.telecom.shiva.persistencia.modelo;
import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class ShvCobEdCreditoPk implements Serializable{
	private static final long serialVersionUID = -1L;

	//@SequenceGenerator(name="SEQ_SHV_COB_ED_CREDITO", sequenceName="SEQ_SHV_COB_ED_CREDITO", allocationSize = 1)
	//@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_SHV_COB_ED_CREDITO")
	@Column(name="ID_CREDITO")
	private Long idCredito;
	
	@ManyToOne(cascade={CascadeType.DETACH}, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_COBRO", referencedColumnName="ID_COBRO")
	private ShvCobEdCobro cobro;

	public Long getIdCredito() {
		return idCredito;
	}

	public void setIdCredito(Long idCredito) {
		this.idCredito = idCredito;
	}

	public ShvCobEdCobro getCobro() {
		return cobro;
	}

	public void setCobro(ShvCobEdCobro cobro) {
		this.cobro = cobro;
	}
}
