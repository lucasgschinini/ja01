package ar.com.telecom.shiva.persistencia.modelo;

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

import ar.com.telecom.shiva.base.enumeradores.MarcaEnum;

@Entity
@Table(name = "SHV_WF_WORKFLOW_MARCA")
public class ShvWfWorkflowMarca extends Modelo {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SHV_WF_WF_MARCA")
	@SequenceGenerator(name = "SEQ_SHV_WF_WF_MARCA", sequenceName = "SEQ_SHV_WF_WF_MARCA", allocationSize = 1)
	@Column(name = "ID_WORKFLOW_MARCA", nullable = false)
	private Integer idWorkflowMarca;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_WORKFLOW_ESTADO", nullable = false)
	private ShvWfWorkflowEstado workflowEstado;

	@Enumerated(EnumType.STRING)
	@Column(name = "ID_MARCA", nullable = false)
	private MarcaEnum marca;

	@Column(name = "USUARIO_CREACION", nullable = false)
	private String usuarioCreacion;

	@Column(name = "FECHA_CREACION", nullable = false)
	private Date fechaCreacion;

	@Column(name = "OBSERVACION")
	private String observacion;

	/**
	 * @return the idWorkflowMarca
	 */
	public Integer getIdWorkflowMarca() {
		return idWorkflowMarca;
	}

	/**
	 * @param idWorkflowMarca the idWorkflowMarca to set
	 */
	public void setIdWorkflowMarca(Integer idWorkflowMarca) {
		this.idWorkflowMarca = idWorkflowMarca;
	}

	/**
	 * @return the workflowEstado
	 */
	public ShvWfWorkflowEstado getWorkflowEstado() {
		return workflowEstado;
	}

	/**
	 * @param workflowEstado the workflowEstado to set
	 */
	public void setWorkflowEstado(ShvWfWorkflowEstado workflowEstado) {
		this.workflowEstado = workflowEstado;
	}

	/**
	 * @return the marca
	 */
	public MarcaEnum getMarca() {
		return marca;
	}

	/**
	 * @param marca the marca to set
	 */
	public void setMarca(MarcaEnum marca) {
		this.marca = marca;
	}

	/**
	 * @return the usuarioCreacion
	 */
	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}

	/**
	 * @param usuarioCreacion the usuarioCreacion to set
	 */
	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	/**
	 * @return the fechaCreacion
	 */
	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	/**
	 * @param fechaCreacion the fechaCreacion to set
	 */
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	/**
	 * @return the observacion
	 */
	public String getObservacion() {
		return observacion;
	}

	/**
	 * @param observacion the observacion to set
	 */
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
}
