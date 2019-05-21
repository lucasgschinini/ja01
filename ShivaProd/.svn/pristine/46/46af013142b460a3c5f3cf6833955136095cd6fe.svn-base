package ar.com.telecom.shiva.persistencia.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
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

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import ar.com.telecom.shiva.base.comparador.ShvWfWorkflowEstadoHistCompPorFecha;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.negocio.workflow.definicion.TipoWorkflow;

@Entity
@Table(name = "SHV_WF_WORKFLOW")
public class ShvWfWorkflow extends Modelo {

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
	private Set<ShvWfWorkflowEstado> shvWfWorkflowEstado = new HashSet<ShvWfWorkflowEstado>(0);

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST }, mappedBy = "workflow")
	@NotFound(action = NotFoundAction.IGNORE)
	private Set<ShvWfWorkflowEstadoHist> shvWfWorkflowEstadoHist = new HashSet<ShvWfWorkflowEstadoHist>(0);

	@Transient
	private String usuarioModificacion;

	@Transient
	private String datosModificados;

	/********************************************************************************************
	 * Funciones especiales
	 ********************************************************************************************/

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
	public ShvWfWorkflowEstado getWorkflowEstado() {
		return shvWfWorkflowEstado.iterator().next();
	}
	
	/**
	 * Devuelve la fecha que genero el alta
	 * 
	 * @return
	 */
	public Date getFechaAlta() {
		Date primeraFechaAlta = shvWfWorkflowEstado.iterator().next()
				.getFechaModificacion();
		if (Validaciones.isCollectionNotEmpty(shvWfWorkflowEstadoHist)) {
			primeraFechaAlta = getListaWorkflowEstadoHistOrdenadaPorFecha()
					.iterator().next().getFechaModificacion();
		}

		return primeraFechaAlta;
	}

	/**
	 * Devuelve el usuario que genero el alta (El primer paso del workflow)
	 * 
	 * @return
	 */
	public String getUsuarioAlta() {
		String primerUsuarioAlta = shvWfWorkflowEstado.iterator().next()
				.getUsuarioModificacion();
		if (Validaciones.isCollectionNotEmpty(shvWfWorkflowEstadoHist)) {
			primerUsuarioAlta = getListaWorkflowEstadoHistOrdenadaPorFecha()
					.iterator().next().getUsuarioModificacion();
		}
		return primerUsuarioAlta;
	}

	/**
	 * Devuelve la lista workflow historial ordenada por fecha
	 */
	public List<ShvWfWorkflowEstadoHist> getListaWorkflowEstadoHistOrdenadaPorFecha() {
		List<ShvWfWorkflowEstadoHist> listHistorial = new ArrayList<ShvWfWorkflowEstadoHist>();
		if (Validaciones.isCollectionNotEmpty(shvWfWorkflowEstadoHist)) {
			for (ShvWfWorkflowEstadoHist hist : shvWfWorkflowEstadoHist) {
				listHistorial.add(hist);
			}
			Collections.sort(listHistorial,
					new ShvWfWorkflowEstadoHistCompPorFecha());
		}
		return listHistorial;
	}

	/********************************************************************************************
	 * Setter & Getter
	 ********************************************************************************************/
	public Integer getIdWorkflow() {
		//super.setId(idWorkflow);
		return idWorkflow;
	}

	public void setIdWorkflow(Integer idWorkflow) {
		this.idWorkflow = idWorkflow;
	}

	public TipoWorkflow getTipoWorkflow() {
		return tipoWorkflow;
	}

	public void setTipoWorkflow(TipoWorkflow tipoWorkflow) {
		this.tipoWorkflow = tipoWorkflow;
	}

	public String getUsuarioModificacion() {
		return usuarioModificacion;
	}

	public void setUsuarioModificacion(String usuarioModificacion) {
		this.usuarioModificacion = usuarioModificacion;
	}

	public String getDatosModificados() {
		return datosModificados;
	}

	public void setDatosModificados(String datosModificados) {
		this.datosModificados = datosModificados;
	}

	public Set<ShvWfWorkflowEstado> getShvWfWorkflowEstado() {
		return shvWfWorkflowEstado;
	}

	public void setShvWfWorkflowEstado(
			Set<ShvWfWorkflowEstado> shvWfWorkflowEstado) {
		this.shvWfWorkflowEstado = shvWfWorkflowEstado;
	}

	public Set<ShvWfWorkflowEstadoHist> getShvWfWorkflowEstadoHist() {
		return shvWfWorkflowEstadoHist;
	}

	public void setShvWfWorkflowEstadoHist(
			Set<ShvWfWorkflowEstadoHist> shvWfWorkflowEstadoHist) {
		this.shvWfWorkflowEstadoHist = shvWfWorkflowEstadoHist;
	}

}
