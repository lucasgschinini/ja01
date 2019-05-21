package ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "SHV_COB_MED_PAG_SHIVA")
@PrimaryKeyJoinColumn(name="ID_MEDIO_PAGO")
public class ShvCobMedioPagoShivaSinOperacion extends ShvCobMedioPagoSinOperacion {
	
	private static final long serialVersionUID = 1L;

	@Column(name = "ID_VALOR", nullable = false)
	private Long idValor;
	
	@Column(name="ID_CLIENTE_LEGADO")	 	
	private Long idClienteLegado;

	@Column(name="FECHA_VALOR")	 	
	private Date fechaValor;
	
	@Column(name="REFERENCIA_VALOR")	 	
	private String referencia;
	
	@Column(name="ID_TIPO_VALOR")	 	
	private Long idTipoValor;

	@Column(name="ID_TIPO_RETENCION_IMPUESTO")	 	
	private Long idTipoRetencionImpuesto;


	@Override
	public String getIdBusquedaRespuestaCobrador() {
		return this.getIdMedioPago().toString();
	}
	
	/**
	 * @return the idValor
	 */
	public Long getIdValor() {
		return idValor;
	}

	/**
	 * @param idValor the idValor to set
	 */
	public void setIdValor(Long idValor) {
		this.idValor = idValor;
	}

	/**
	 * @return the idClienteLegado
	 */
	public Long getIdClienteLegado() {
		return idClienteLegado;
	}

	/**
	 * @param idClienteLegado the idClienteLegado to set
	 */
	public void setIdClienteLegado(Long idClienteLegado) {
		this.idClienteLegado = idClienteLegado;
	}

	/**
	 * @return the fechaValor
	 */
	public Date getFechaValor() {
		return fechaValor;
	}

	/**
	 * @param fechaValor the fechaValor to set
	 */
	public void setFechaValor(Date fechaValor) {
		this.fechaValor = fechaValor;
	}

	/**
	 * @return the referenciaValor
	 */
	public String getReferencia() {
		return referencia;
	}

	/**
	 * @param referenciaValor the referenciaValor to set
	 */
	public void setReferencia(String referenciaValor) {
		this.referencia = referenciaValor;
	}

	/**
	 * @return the idTipoValor
	 */
	public Long getIdTipoValor() {
		return idTipoValor;
	}

	/**
	 * @param idTipoValor the idTipoValor to set
	 */
	public void setIdTipoValor(Long idTipoValor) {
		this.idTipoValor = idTipoValor;
	}

	/**
	 * @return the idTipoRetencionImpuesto
	 */
	public Long getIdTipoRetencionImpuesto() {
		return idTipoRetencionImpuesto;
	}

	/**
	 * @param idTipoRetencionImpuesto the idTipoRetencionImpuesto to set
	 */
	public void setIdTipoRetencionImpuesto(Long idTipoRetencionImpuesto) {
		this.idTipoRetencionImpuesto = idTipoRetencionImpuesto;
	}
}
