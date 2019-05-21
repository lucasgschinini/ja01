package ar.com.telecom.shiva.persistencia.modelo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "SHV_LGJ_CHEQUE_RECHA_DET_CLIEN")
public class ShvLgjChequeRechazadoDetalleCliente extends Modelo {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SHV_LGJ_CHEQUE_RECHA_CLIEN")
	@SequenceGenerator(name = "SEQ_SHV_LGJ_CHEQUE_RECHA_CLIEN", sequenceName = "SEQ_SHV_LGJ_CHEQUE_RECHA_CLIEN", allocationSize = 1)
	@Column (name="ID_CHEQUE_RECHAZADO_CLIENTE", updatable = false)
	private Long idChequeRechazadoCliente;
	
	@ManyToOne(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name="ID_CHEQUE_RECHAZADO", referencedColumnName="ID_CHEQUE_RECHAZADO") 	
	private ShvLgjChequeRechazado chequeRechazado;
	
	@Column(name="ID_CLIENTE_LEGADO")
	private Long idClienteLegado;
	
	@Column(name="RAZON_SOCIAL")
	private String razonSocial;

	@Column(name="CUIT")
	private String cuit;

	public ShvLgjChequeRechazadoDetalleCliente() {


	}

	public Long getIdChequeRechazadoCliente() {
		return idChequeRechazadoCliente;
	}

	public void setIdChequeRechazadoCliente(Long idChequeRechazadoCliente) {
		this.idChequeRechazadoCliente = idChequeRechazadoCliente;
	}

	public Long getIdClienteLegado() {
		return idClienteLegado;
	}

	public void setIdClienteLegado(Long idClienteLegado) {
		this.idClienteLegado = idClienteLegado;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public ShvLgjChequeRechazado getChequeRechazado() {
		return chequeRechazado;
	}
	public void setChequeRechazado(ShvLgjChequeRechazado chequeRechazado) {
		this.chequeRechazado = chequeRechazado;
	}

	/**
	 * @return the cuit
	 */
	public String getCuit() {
		return cuit;
	}

	/**
	 * @param cuit the cuit to set
	 */
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}
	
}
