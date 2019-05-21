package ar.com.telecom.shiva.persistencia.modelo.simple;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ar.com.telecom.shiva.persistencia.modelo.Modelo;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamMotivo;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamMotivoSuspension;

@Entity
@Table(name = "SHV_VAL_VALOR")
public class ShvValValorSimplificado extends Modelo {


	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_VALOR", nullable = false)
	private Long idValor;
	
	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_WORKFLOW", referencedColumnName = "ID_WORKFLOW", nullable = false)
	private ShvWfWorkflow workFlow;
	
	@Column(name = "IMPORTE", nullable = false)
	private BigDecimal importe;

	@Column(name = "SALDO_DISPONIBLE")
	private BigDecimal saldoDisponible;
	
	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_MOTIVO", referencedColumnName = "ID_MOTIVO")
	private ShvParamMotivo motivo;
	
	@Column(name = "FECHA_VALOR")
	private Date fechaValor;

	@Column(name = "FECHA_ULTIMO_ESTADO")
	private Date fechaUltimoEstado;

	@Column(name = "FECHA_DISPONIBLE")
	private Date fechaDisponible;

	@Column(name = "ID_TIPO_VALOR")
	private Integer idTipoValor;
	
	@Column(name = "ID_ANALISTA", nullable = false)
	private String idAnalista;

	@Column(name = "ID_COPROPIETARIO")
	private String idCopropietario;
	
	@Column(name = "ID_CLIENTE_LEGADO", nullable = false)
	private Long idClienteLegado;

	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_MOTIVO_SUSPENSION", referencedColumnName = "ID_MOTIVO_SUSPENSION")
	private ShvParamMotivoSuspension motivoSuspension;

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
	 * @return the workFlow
	 */
	public ShvWfWorkflow getWorkFlow() {
		return workFlow;
	}

	/**
	 * @param workFlow the workFlow to set
	 */
	public void setWorkFlow(ShvWfWorkflow workFlow) {
		this.workFlow = workFlow;
	}

	/**
	 * @return the importe
	 */
	public BigDecimal getImporte() {
		return importe;
	}

	/**
	 * @param importe the importe to set
	 */
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	/**
	 * @return the saldoDisponible
	 */
	public BigDecimal getSaldoDisponible() {
		return saldoDisponible;
	}

	/**
	 * @param saldoDisponible the saldoDisponible to set
	 */
	public void setSaldoDisponible(BigDecimal saldoDisponible) {
		this.saldoDisponible = saldoDisponible;
	}

	/**
	 * @return the motivo
	 */
	public ShvParamMotivo getMotivo() {
		return motivo;
	}

	/**
	 * @param motivo the motivo to set
	 */
	public void setMotivo(ShvParamMotivo motivo) {
		this.motivo = motivo;
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
	 * @return the fechaUltimoEstado
	 */
	public Date getFechaUltimoEstado() {
		return fechaUltimoEstado;
	}

	/**
	 * @param fechaUltimoEstado the fechaUltimoEstado to set
	 */
	public void setFechaUltimoEstado(Date fechaUltimoEstado) {
		this.fechaUltimoEstado = fechaUltimoEstado;
	}

	/**
	 * @return the fechaDisponible
	 */
	public Date getFechaDisponible() {
		return fechaDisponible;
	}

	/**
	 * @param fechaDisponible the fechaDisponible to set
	 */
	public void setFechaDisponible(Date fechaDisponible) {
		this.fechaDisponible = fechaDisponible;
	}

	/**
	 * @return the idTipoValor
	 */
	public Integer getIdTipoValor() {
		return idTipoValor;
	}

	/**
	 * @param idTipoValor the idTipoValor to set
	 */
	public void setIdTipoValor(Integer idTipoValor) {
		this.idTipoValor = idTipoValor;
	}

	/**
	 * @return the motivoSuspension
	 */
	public ShvParamMotivoSuspension getMotivoSuspension() {
		return motivoSuspension;
	}

	/**
	 * @param motivoSuspension the motivoSuspension to set
	 */
	public void setMotivoSuspension(ShvParamMotivoSuspension motivoSuspension) {
		this.motivoSuspension = motivoSuspension;
	}

	/**
	 * @return the idAnalista
	 */
	public String getIdAnalista() {
		return idAnalista;
	}

	/**
	 * @param idAnalista the idAnalista to set
	 */
	public void setIdAnalista(String idAnalista) {
		this.idAnalista = idAnalista;
	}

	/**
	 * @return the idCopropietario
	 */
	public String getIdCopropietario() {
		return idCopropietario;
	}

	/**
	 * @param idCopropietario the idCopropietario to set
	 */
	public void setIdCopropietario(String idCopropietario) {
		this.idCopropietario = idCopropietario;
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
}
