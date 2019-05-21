package ar.com.telecom.shiva.persistencia.modelo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;

@Entity
@Table(name = "SHV_WF_WORKFLOW_ESTADO")
public class ShvWfWorkflowEstado extends Modelo {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SHV_WF_WF_ESTADO")
	@SequenceGenerator(name = "SEQ_SHV_WF_WF_ESTADO", sequenceName = "SEQ_SHV_WF_WF_ESTADO", allocationSize = 1)
	@Column(name = "ID_WORKFLOW_ESTADO", nullable = false)
	private Integer idWorkflowEstado;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_WORKFLOW", nullable = false)
	private ShvWfWorkflow workflow;

	@Enumerated(EnumType.STRING)
	@Column(name = "ESTADO", nullable = false)
	private Estado estado;

	@Column(name = "USUARIO_MODIFICACION", nullable = false)
	private String usuarioModificacion;

	@Column(name = "FECHA_MODIFICACION", nullable = false)
	private Date fechaModificacion;

	@Column(name = "DATOS_MODIFICADOS", nullable = false)
	private String datosModificados;

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}, mappedBy = "workflowEstado", orphanRemoval=true)
	private Set<ShvWfWorkflowMarca> workflowMarcas = new HashSet<ShvWfWorkflowMarca>();
	
	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}, mappedBy = "workflowEstado", orphanRemoval=true)
	private Set<ShvWfHistorialObservacion> historialObservaciones = new HashSet<ShvWfHistorialObservacion>();

	
	public Integer getIdWorkflowEstado() {
		super.setId(idWorkflowEstado);
		return idWorkflowEstado;
	}

	public void setIdWorkflowEstado(Integer idWorkflowEstado) {
		this.idWorkflowEstado = idWorkflowEstado;
	}

	public ShvWfWorkflow getWorkflow() {
		return workflow;
	}

	public void setWorkflow(ShvWfWorkflow workflow) {
		this.workflow = workflow;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public String getUsuarioModificacion() {
		return usuarioModificacion;
	}

	public void setUsuarioModificacion(String usuarioModificacion) {
		this.usuarioModificacion = usuarioModificacion;
	}

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public String getDatosModificados() {
		return datosModificados;
	}

	public void setDatosModificados(String datosModificados) {
		this.datosModificados = datosModificados;
	}

	/**
	 * @return the shvWfWorkflowMarca
	 */
	public Set<ShvWfWorkflowMarca> getWorkflowMarcas() {
		return workflowMarcas;
	}

	/**
	 * @param shvWfWorkflowMarca the shvWfWorkflowMarca to set
	 */
	public void setWorkflowMarcas(Set<ShvWfWorkflowMarca> shvWfWorkflowMarca) {
		this.workflowMarcas = shvWfWorkflowMarca;
	}

	public Set<ShvWfHistorialObservacion> getHistorialObservaciones() {
		return historialObservaciones;
	}

	public void setHistorialObservaciones(Set<ShvWfHistorialObservacion> historialObservaciones) {
		this.historialObservaciones = historialObservaciones;
	}
}
