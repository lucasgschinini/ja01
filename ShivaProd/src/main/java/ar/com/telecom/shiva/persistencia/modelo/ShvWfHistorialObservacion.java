package ar.com.telecom.shiva.persistencia.modelo;

import java.util.Date;

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

@Entity
@Table(name = "SHV_WF_HISTORIAL_OBS")
public class ShvWfHistorialObservacion extends Modelo {

	private static final long serialVersionUID = 20170705L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SHV_WF_HIST_OBS")
	@SequenceGenerator(name = "SEQ_SHV_WF_HIST_OBS", sequenceName = "SEQ_SHV_WF_HIST_OBS", allocationSize = 1)
	@Column(name = "ID_HISTORIAL_OBSERVACION", nullable = false)
	private Long idHistorialObservacion;

	@Column(name="USUARIO")
	private String usuario;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_WORKFLOW_ESTADO", nullable = false)
	private ShvWfWorkflowEstado workflowEstado;
    
	@Column(name="FECHA_CREACION")
	private Date fechaCreacion;
    
	@Column(name="OBSERVACION")
	private String observacion;

	public Long getIdHistorialObservacion() {
		return idHistorialObservacion;
	}

	public void setIdHistorialObservacion(Long idHistorialObservacion) {
		this.idHistorialObservacion = idHistorialObservacion;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public ShvWfWorkflowEstado getWorkflowEstado() {
		return workflowEstado;
	}

	public void setWorkflowEstado(ShvWfWorkflowEstado workflowEstado) {
		this.workflowEstado = workflowEstado;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ShvWfHistorialObservacion [idHistorialObservacion="
				+ idHistorialObservacion + ", usuario=" + usuario
				+ ", workflowEstado=" + "/*workflowEstado*/" + ", fechaCreacion="
				+ fechaCreacion + ", observacion=" + observacion + "]";
	}
	
}
