package ar.com.telecom.shiva.persistencia.modelo.simple;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;

@Entity
@Table(name = "SHV_WF_WORKFLOW_ESTADO")
public class ShvWfWorkflowEstadoSimplificado extends Modelo {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SHV_WF_WF_ESTADO")
	@SequenceGenerator(name = "SEQ_SHV_WF_WF_ESTADO", sequenceName = "SEQ_SHV_WF_WF_ESTADO", allocationSize = 1)
	@Column(name = "ID_WORKFLOW_ESTADO", nullable = false)
	private Integer idWorkflowEstado;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_WORKFLOW", nullable = false)
	private ShvWfWorkflowSimplificado workflow;

	@Enumerated(EnumType.STRING)
	@Column(name = "ESTADO", nullable = false)
	private Estado estado;

	@Column(name = "USUARIO_MODIFICACION", nullable = false)
	private String usuarioModificacion;

	@Column(name = "FECHA_MODIFICACION", nullable = false)
	private Date fechaModificacion;

	@Column(name = "DATOS_MODIFICADOS", nullable = false)
	private String datosModificados;

	/**
	 * @return the idWorkflowEstado
	 */
	public Integer getIdWorkflowEstado() {
		return idWorkflowEstado;
	}

	/**
	 * @param idWorkflowEstado the idWorkflowEstado to set
	 */
	public void setIdWorkflowEstado(Integer idWorkflowEstado) {
		this.idWorkflowEstado = idWorkflowEstado;
	}

	/**
	 * @return the workflow
	 */
	public ShvWfWorkflowSimplificado getWorkflow() {
		return workflow;
	}

	/**
	 * @param workflow the workflow to set
	 */
	public void setWorkflow(ShvWfWorkflowSimplificado workflow) {
		this.workflow = workflow;
	}

	/**
	 * @return the estado
	 */
	public Estado getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	/**
	 * @return the usuarioModificacion
	 */
	public String getUsuarioModificacion() {
		return usuarioModificacion;
	}

	/**
	 * @param usuarioModificacion the usuarioModificacion to set
	 */
	public void setUsuarioModificacion(String usuarioModificacion) {
		this.usuarioModificacion = usuarioModificacion;
	}

	/**
	 * @return the fechaModificacion
	 */
	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	/**
	 * @param fechaModificacion the fechaModificacion to set
	 */
	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	/**
	 * @return the datosModificados
	 */
	public String getDatosModificados() {
		return datosModificados;
	}

	/**
	 * @param datosModificados the datosModificados to set
	 */
	public void setDatosModificados(String datosModificados) {
		this.datosModificados = datosModificados;
	}
}
