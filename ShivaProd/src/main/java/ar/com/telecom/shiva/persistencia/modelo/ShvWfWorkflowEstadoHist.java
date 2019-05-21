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
@Table(name = "SHV_WF_WORKFLOW_ESTADO_HIST")
public class ShvWfWorkflowEstadoHist extends Modelo {
	
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_SHV_WF_WF_ESTADO_HIST")
    @SequenceGenerator(name="SEQ_SHV_WF_WF_ESTADO_HIST", sequenceName="SEQ_SHV_WF_WF_ESTADO_HIST",allocationSize=1)
	@Column(name="ID_WORKFLOW_ESTADO_HIST")
	private Integer idWorkflowEstadoHist;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_WORKFLOW", nullable = false)
	private ShvWfWorkflow workflow;
	
	@Enumerated(EnumType.STRING)
	@Column(name="ESTADO")
	private Estado estado;
	
	@Column(name="USUARIO_MODIFICACION")
	private String usuarioModificacion;
	
	@Column(name="FECHA_MODIFICACION")
	private Date fechaModificacion;
	
	@Column(name="DATOS_MODIFICADOS")
	private String datosModificados;

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}, mappedBy = "workflowEstadoHist")
	private Set<ShvWfWorkflowMarcaHist> shvWfWorkflowMarcaHist = new HashSet<ShvWfWorkflowMarcaHist>();
	
	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}, mappedBy = "workflowEstadoHist")
	private Set<ShvWfHistorialObservacionHist> shvWfHistoriaHist = new HashSet<ShvWfHistorialObservacionHist>();

	public Integer getIdWorkflowEstadoHist() {
		super.setId(idWorkflowEstadoHist);
		return idWorkflowEstadoHist;
	}

	public void setIdWorkflowEstadoHist(Integer idWorkflowEstadoHist) {
		this.idWorkflowEstadoHist = idWorkflowEstadoHist;
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
	 * @return the shvWfWorkflowMarcaHist
	 */
	public Set<ShvWfWorkflowMarcaHist> getShvWfWorkflowMarcaHist() {
		return shvWfWorkflowMarcaHist;
	}

	/**
	 * @param shvWfWorkflowMarcaHist the shvWfWorkflowMarcaHist to set
	 */
	public void setShvWfWorkflowMarcaHist(
			Set<ShvWfWorkflowMarcaHist> shvWfWorkflowMarcaHist) {
		this.shvWfWorkflowMarcaHist = shvWfWorkflowMarcaHist;
	}

	public Set<ShvWfHistorialObservacionHist> getShvWfHistoriaHist() {
		return shvWfHistoriaHist;
	}

	public void setShvWfHistoriaHist(
			Set<ShvWfHistorialObservacionHist> shvWfHistoriaHist) {
		this.shvWfHistoriaHist = shvWfHistoriaHist;
	}
}
