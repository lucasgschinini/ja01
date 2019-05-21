package ar.com.telecom.shiva.persistencia.modelo.simple;

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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.negocio.workflow.definicion.TipoWorkflow;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;

@Entity
@Table(name = "SHV_WF_WORKFLOW")
public class ShvWfWorkflowSimplificado extends Modelo {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SHV_WF_WORKFLOW")
	@SequenceGenerator(name = "SEQ_SHV_WF_WORKFLOW", sequenceName = "SEQ_SHV_WF_WORKFLOW", allocationSize = 1)
	@Column(name = "ID_WORKFLOW", nullable = false)
	private Integer idWorkflow;

	@Enumerated(EnumType.STRING)
	@Column(name = "TIPO_WORKFLOW", nullable = false)
	private TipoWorkflow tipoWorkflow;

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}, mappedBy = "workflow")
	private Set<ShvWfWorkflowEstadoSimplificado> shvWfWorkflowEstado = new HashSet<ShvWfWorkflowEstadoSimplificado>(0);

	@Transient
	private String usuarioModificacion;

	@Transient
	private String datosModificados;

	/**
	 * Devuelve la ultima fecha del ultimo cambio
	 * 
	 * @return
	 */
	public Date getFechaUltimaModificacion() {
		return shvWfWorkflowEstado.iterator().next().getFechaModificacion();
	}

	/**
	 * Devuelve el ultimo estado del workflow como enum
	 * 
	 * @return
	 */
	public Estado getEstado() {
		return shvWfWorkflowEstado.iterator().next().getEstado();
	}
	
	/**
	 * Devuelve el ultimo estado del workflow como objeto workflowEstado
	 * 
	 * @return
	 */
	public ShvWfWorkflowEstadoSimplificado getWorkflowEstado() {
		return shvWfWorkflowEstado.iterator().next();
	}

	/**
	 * @return the idWorkflow
	 */
	public Integer getIdWorkflow() {
		return idWorkflow;
	}

	/**
	 * @param idWorkflow the idWorkflow to set
	 */
	public void setIdWorkflow(Integer idWorkflow) {
		this.idWorkflow = idWorkflow;
	}

	/**
	 * @return the tipoWorkflow
	 */
	public TipoWorkflow getTipoWorkflow() {
		return tipoWorkflow;
	}

	/**
	 * @param tipoWorkflow the tipoWorkflow to set
	 */
	public void setTipoWorkflow(TipoWorkflow tipoWorkflow) {
		this.tipoWorkflow = tipoWorkflow;
	}

	/**
	 * @return the shvWfWorkflowEstado
	 */
	public Set<ShvWfWorkflowEstadoSimplificado> getShvWfWorkflowEstado() {
		return shvWfWorkflowEstado;
	}

	/**
	 * @param shvWfWorkflowEstado the shvWfWorkflowEstado to set
	 */
	public void setShvWfWorkflowEstado(
			Set<ShvWfWorkflowEstadoSimplificado> shvWfWorkflowEstado) {
		this.shvWfWorkflowEstado = shvWfWorkflowEstado;
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
