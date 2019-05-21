package ar.com.telecom.shiva.persistencia.modelo;
import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class ShvCobEdOtrosMedioPagoPk implements Serializable{
	private static final long serialVersionUID = -1L;

	//@SequenceGenerator(name="SEQ_SHV_COB_ED_OTRO_MEDIO_PAGO", sequenceName="SEQ_SHV_COB_ED_OTRO_MEDIO_PAGO", allocationSize = 1)
	//@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_SHV_COB_ED_OTRO_MEDIO_PAGO")
	@Column(name="ID_MEDIO_PAGO")
	private Long idMedioPago;

	@ManyToOne(cascade={CascadeType.DETACH}, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_COBRO", referencedColumnName="ID_COBRO")
	private ShvCobEdCobro cobro;

	public Long getIdMedioPago() {
		return idMedioPago;
	}

	public void setIdMedioPago(Long idMedioPago) {
		this.idMedioPago = idMedioPago;
	}

	public ShvCobEdCobro getCobro() {
		return cobro;
	}

	public void setCobro(ShvCobEdCobro cobro) {
		this.cobro = cobro;
	}

}
