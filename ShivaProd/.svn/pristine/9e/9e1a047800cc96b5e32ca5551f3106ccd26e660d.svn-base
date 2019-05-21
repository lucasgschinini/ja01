package ar.com.telecom.shiva.persistencia.modelo.simple;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ar.com.telecom.shiva.base.enumeradores.UbicacionChequeEnum;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow;
/**
 * @author u578936 M.A.Uehara
 *
 */
@Entity
@Table(name = "SHV_LGJ_LEGAJO_CHEQUE_RCHAZADO")
public class ShvLgjLegajoChequeRechazadoSimplificadoWorkFlow extends Modelo {
	private static final long serialVersionUID = 20170804L;

	@Id
	@Column(name="ID_LEGAJO_CHEQUE_RECHAZADO", updatable = false)
	private Long idLegajoChequeRechazado;
	
	@Enumerated(EnumType.STRING)
	@Column(name="UBICACION")
	private UbicacionChequeEnum ubicacion;
	
	@Column(name="ID_ANALISTA")
	private String idAnalista;
	
	@Column(name="ID_COPROPIETARIO")
	private String idCopropietario;

	@Column(name="COPROPIETARIO")
	private String copropietario;
	
	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE }, fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_WORKFLOW", referencedColumnName = "ID_WORKFLOW", nullable = false)
	private ShvWfWorkflow workflow;
	
	/**
	 * @return the ubicacion
	 */
	public UbicacionChequeEnum getUbicacion() {
		return ubicacion;
	}
	/**
	 * @param ubicacion the ubicacion to set
	 */
	public void setUbicacion(UbicacionChequeEnum ubicacion) {
		this.ubicacion = ubicacion;
	}
	public String getIdCopropietario() {
		return idCopropietario;
	}
	public void setIdCopropietario(String idCopropietario) {
		this.idCopropietario = idCopropietario;
	}
	public String getCopropietario() {
		return copropietario;
	}
	public void setCopropietario(String copropietario) {
		this.copropietario = copropietario;
	}
	public Long getIdLegajoChequeRechazado() {
		return idLegajoChequeRechazado;
	}
	public void setIdLegajoChequeRechazado(Long idLegajoChequeRechazado) {
		this.idLegajoChequeRechazado = idLegajoChequeRechazado;
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


}
