package ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import ar.com.telecom.shiva.persistencia.modelo.Modelo;


@Entity
@Table(name="SHV_COB_MED_PAG_CLIENTE")
public class ShvCobMedioPagoClienteSinOperacion extends Modelo  {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SEQ_SHV_COB_MED_PAG_CLIENTE_SIN", sequenceName="SEQ_SHV_COB_MED_PAG_CLIENTE", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_SHV_COB_MED_PAG_CLIENTE_SIN")
	@Column(name="ID_MEDIO_PAGO_CLIENTE")
	private Integer idMedioPagoCliente;

	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_MEDIO_PAGO", referencedColumnName = "ID_MEDIO_PAGO")
	private ShvCobMedioPagoUsuarioSinOperacion medioPagoUsuario;
	
	@Column(name = "ID_CLIENTE_LEGADO")
	private String idClienteLegado;

	/**
	 * @return the idMedioPagoCliente
	 */
	public Integer getIdMedioPagoCliente() {
		return idMedioPagoCliente;
	}

	/**
	 * @param idMedioPagoCliente the idMedioPagoCliente to set
	 */
	public void setIdMedioPagoCliente(Integer idMedioPagoCliente) {
		this.idMedioPagoCliente = idMedioPagoCliente;
	}

	/**
	 * @return the medioPagoUsuario
	 */
	public ShvCobMedioPagoUsuarioSinOperacion getMedioPagoUsuario() {
		return medioPagoUsuario;
	}

	/**
	 * @param medioPagoUsuario the medioPagoUsuario to set
	 */
	public void setMedioPagoUsuario(ShvCobMedioPagoUsuarioSinOperacion medioPagoUsuario) {
		this.medioPagoUsuario = medioPagoUsuario;
	}

	/**
	 * @return the idClienteLegado
	 */
	public String getIdClienteLegado() {
		return idClienteLegado;
	}

	/**
	 * @param idClienteLegado the idClienteLegado to set
	 */
	public void setIdClienteLegado(String idClienteLegado) {
		this.idClienteLegado = idClienteLegado;
	}
}