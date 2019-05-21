package ar.com.telecom.shiva.persistencia.modelo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="SHV_COB_ED_MEDIO_PAGO_CLIENTE")
public class ShvCobEdMedioPagoCliente extends Modelo  {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SEQ_SHV_COB_ED_MED_PAG_CLIENTE", sequenceName="SEQ_SHV_COB_ED_MED_PAG_CLIENTE", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_SHV_COB_ED_MED_PAG_CLIENTE")
	@Column(name="ID_MEDIO_PAGO_CLIENTE")
	private Long idMedioPagoCliente;

	@ManyToOne(cascade = { CascadeType.DETACH}, fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "ID_MEDIO_PAGO", referencedColumnName = "ID_MEDIO_PAGO"),
		@JoinColumn(name = "ID_COBRO", referencedColumnName = "ID_COBRO")
	})
	private ShvCobEdOtrosMedioPago otrosMedioPago;
	
	@Column(name = "ID_CLIENTE_LEGADO")
	private String idClienteLegado;
	
	
	public ShvCobEdMedioPagoCliente() {
	}

	public Long getIdMedioPagoCliente() {
		return idMedioPagoCliente;
	}

	public void setIdMedioPagoCliente(Long idMedioPagoCliente) {
		this.idMedioPagoCliente = idMedioPagoCliente;
	}

	public String getIdClienteLegado() {
		return idClienteLegado;
	}

	public void setIdClienteLegado(String idClienteLegado) {
		this.idClienteLegado = idClienteLegado;
	}

	public ShvCobEdOtrosMedioPago getOtrosMedioPago() {
		return otrosMedioPago;
	}

	public void setOtrosMedioPago(ShvCobEdOtrosMedioPago otrosMedioPago) {
		this.otrosMedioPago = otrosMedioPago;
	}

}