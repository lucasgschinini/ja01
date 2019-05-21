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
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamBanco;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamMotivoLegajo;


@Entity
@Table(name = "SHV_LGJ_LEGAJO_CHEQUE_RCHAZADO")
public class ShvLgjLegajoChequeRechazadoSimplificadoNotificacion extends Modelo {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_LEGAJO_CHEQUE_RECHAZADO", updatable = false)
	private Long idLegajoChequeRechazado;
	
	@ManyToOne(cascade = CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_MOTIVO_LEGAJO")
	private ShvParamMotivoLegajo motivoLegajo;

	@ManyToOne(cascade = CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_BANCO_ORIGEN")
	private ShvParamBanco bancoOrigen;
	
	@Column(name="NUMERO_CHEQUE")
	private Long numeroCheque;

	@Column(name="IMPORTE")
	private BigDecimal importe;

	@ManyToOne(cascade = { CascadeType.DETACH }, fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_WORKFLOW", referencedColumnName = "ID_WORKFLOW", nullable = false)
	private ShvWfWorkflow workflow;
	
	@Column(name="FECHA_EMISION")
	private Date fechaEmision;

	public ShvLgjLegajoChequeRechazadoSimplificadoNotificacion(){
	}

	/**
	 * @return the motivoLegajo
	 */
	public ShvParamMotivoLegajo getMotivoLegajo() {
		return motivoLegajo;
	}

	/**
	 * @param motivoLegajo the motivoLegajo to set
	 */
	public void setMotivoLegajo(ShvParamMotivoLegajo motivoLegajo) {
		this.motivoLegajo = motivoLegajo;
	}

	/**
	 * @return the bancoOrigen
	 */
	public ShvParamBanco getBancoOrigen() {
		return bancoOrigen;
	}

	/**
	 * @param bancoOrigen the bancoOrigen to set
	 */
	public void setBancoOrigen(ShvParamBanco bancoOrigen) {
		this.bancoOrigen = bancoOrigen;
	}

	/**
	 * @return the numeroCheque
	 */
	public Long getNumeroCheque() {
		return numeroCheque;
	}

	/**
	 * @param numeroCheque the numeroCheque to set
	 */
	public void setNumeroCheque(Long numeroCheque) {
		this.numeroCheque = numeroCheque;
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
	 * @return the idLegajoChequeRechazado
	 */
	public Long getIdLegajoChequeRechazado() {
		return idLegajoChequeRechazado;
	}

	/**
	 * @param idLegajoChequeRechazado the idLegajoChequeRechazado to set
	 */
	public void setIdLegajoChequeRechazado(Long idLegajoChequeRechazado) {
		this.idLegajoChequeRechazado = idLegajoChequeRechazado;
	}

	/**
	 * @return the workflow
	 */
	public ShvWfWorkflow getWorkflow() {
		return workflow;
	}

	/**
	 * @param workflow the workflow to set
	 */
	public void setWorkflow(ShvWfWorkflow workflow) {
		this.workflow = workflow;
	}

	/**
	 * @return the fechaEmision
	 */
	public Date getFechaEmision() {
		return fechaEmision;
	}

	/**
	 * @param fechaEmision the fechaEmision to set
	 */
	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}	
}