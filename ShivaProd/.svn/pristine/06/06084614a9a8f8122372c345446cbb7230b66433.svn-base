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
@Table(name = "SHV_WF_HISTORIAL_OBS_HIST")
public class ShvWfHistorialObservacionHist extends Modelo {
	
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_SHV_WF_HIST_OBS_HIST")
    @SequenceGenerator(name="SEQ_SHV_WF_HIST_OBS_HIST", sequenceName="SEQ_SHV_WF_HIST_OBS_HIST",allocationSize=1)
	@Column(name="ID_HISTORIAL_OBSERVACION_HIST")
	private Long idHistorialObservacionHist;

	@Column(name="USUARIO")
	private String usuario;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_WORKFLOW_ESTADO_HIST", nullable = false)
	private ShvWfWorkflowEstadoHist workflowEstadoHist;
    
	@Column(name="FECHA_CREACION")
	private Date fechaCreacion;
    
	@Column(name="OBSERVACION")
	private String observacion;

	public Long getIdHistorialObservacionHist() {
		return idHistorialObservacionHist;
	}

	public void setIdHistorialObservacionHist(Long idHistorialObservacionHist) {
		this.idHistorialObservacionHist = idHistorialObservacionHist;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
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

	public ShvWfWorkflowEstadoHist getWorkflowEstadoHist() {
		return workflowEstadoHist;
	}

	public void setWorkflowEstadoHist(ShvWfWorkflowEstadoHist workflowEstadoHist) {
		this.workflowEstadoHist = workflowEstadoHist;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ShvWfHistorialObservacionHist [idHistorialObservacionHist="
				+ idHistorialObservacionHist + ", usuario=" + usuario
				+ ", fechaCreacion=" + fechaCreacion + ", observacion="
				+ observacion + "]";
	}
    
}
