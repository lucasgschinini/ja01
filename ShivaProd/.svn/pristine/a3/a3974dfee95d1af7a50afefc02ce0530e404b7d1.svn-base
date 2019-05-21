package ar.com.telecom.shiva.persistencia.modelo;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.SociedadEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoAccionEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTareaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTareaEstadoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTareaGestionaEnum;

@Entity
@Table(name = "SHV_WF_TAREA")
public class ShvWfTarea extends Modelo {

	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SHV_WF_TAREA")
	@SequenceGenerator(name = "SEQ_SHV_WF_TAREA", sequenceName = "SEQ_SHV_WF_TAREA", allocationSize = 1)
	@Column(name = "ID_TAREA", nullable = false)
	private Long idTarea;
	
	@Column(name = "ID_WORKFLOW", nullable = false)
	private Integer idWorkflow;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "TIPO_TAREA", nullable = false)
	private TipoTareaEnum tipoTarea;
	
	@Column(name = "FECHA_CREACION", nullable = false)
	private Date fechaCreacion;
	
	@Column(name = "USUARIO_CREACION", nullable = false)
	private String usuarioCreacion;

	@Column(name = "PERFIL_ASIGNACION", nullable = false)
	private String perfilAsignacion;
	
	@Column(name = "USUARIO_ASIGNACION", nullable = true)
	private String usuarioAsignacion;
	
	@Column(name = "FECHA_EJECUCION", nullable = false)
	private Date fechaEjecucion;
	
	@Column(name = "USUARIO_EJECUCION", nullable = false)
	private String usuarioEjecucion;
	
	@Column(name = "INFORMACION_ADICIONAL", nullable = false)
	private String informacionAdicional;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "GESTIONA_SOBRE", nullable = false)
	private TipoTareaGestionaEnum gestionaSobre;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "ESTADO", nullable = false)
	private TipoTareaEstadoEnum estado;
	
	@Column(name = "FECHA_ULTIMA_MODIFICACION")
	private Date fechaUltimaModificacion;

	@OneToOne(cascade = { CascadeType.ALL }, mappedBy = "tarea")
	private ShvWfTareaValor tareaValor;
	
	@OneToOne(cascade = { CascadeType.ALL }, mappedBy = "tarea")
	private ShvWfTareaTalonario tareaTalonario;
	
	@OneToOne(cascade = { CascadeType.ALL }, mappedBy = "tarea")
	private ShvWfTareaRegistroAVC tareaRegistroAVC;
	
	@OneToOne(cascade = { CascadeType.ALL }, mappedBy = "tarea")
	private ShvWfTareaValorReversion tareaValorPorReversion;
	
	@OneToOne(cascade = { CascadeType.ALL }, mappedBy = "tarea")
	private ShvWfTareaOperacionMasiva tareaOperacionMasiva;
	
	@OneToOne(cascade = { CascadeType.ALL }, mappedBy = "tarea")
	private ShvWfTareaCobro tareaCobro;
	
	@OneToOne(cascade = { CascadeType.ALL }, mappedBy = "tarea")
	private ShvWfTareaDescobro tareaDescobro;
	
	@Enumerated(EnumType.STRING)
	@Column(name="SISTEMA")
	private SistemaEnum  sistema;
	
	@Enumerated(EnumType.STRING)
	@Column(name="SOCIEDAD")
	private SociedadEnum  sociedad;
	
	@Enumerated(EnumType.STRING)
	@Column(name="TIPO_ACCION")
	private TipoAccionEnum tipoAccion;
	
	@Column(name="OBSERVACIONES")
	private String observaciones;

	@Column(name="NOMBRE_USUARIO_EJECUCION")
	private String nombreUsuarioEjecucion;
	

	/**
	 * @return the tipoAccion
	 */
	public TipoAccionEnum getTipoAccion() {
		return tipoAccion;
	}

	/**
	 * @param tipoAccion the tipoAccion to set
	 */
	public void setTipoAccion(TipoAccionEnum tipoAccion) {
		this.tipoAccion = tipoAccion;
	}

	/**
	 * @return the observaciones
	 */
	public String getObservaciones() {
		return observaciones;
	}

	/**
	 * @param observaciones the observaciones to set
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	/**
	 * @return the nombreUsuarioEjecucion
	 */
	public String getNombreUsuarioEjecucion() {
		return nombreUsuarioEjecucion;
	}

	/**
	 * @param nombreUsuarioEjecucion the nombreUsuarioEjecucion to set
	 */
	public void setNombreUsuarioEjecucion(String nombreUsuarioEjecucion) {
		this.nombreUsuarioEjecucion = nombreUsuarioEjecucion;
	}

	public SociedadEnum getSociedad() {
		return sociedad;
	}

	public void setSociedad(SociedadEnum sociedad) {
		this.sociedad = sociedad;
	}

	public SistemaEnum getSistema() {
		return sistema;
	}

	public void setSistema(SistemaEnum sistema) {
		this.sistema = sistema;
	}

	public ShvWfTareaDescobro getTareaDescobro() {
		return tareaDescobro;
	}

	public void setTareaDescobro(ShvWfTareaDescobro tareaDescobro) {
		this.tareaDescobro = tareaDescobro;
	}

	public Long getIdTarea() {
		return idTarea;
	}

	public void setIdTarea(Long idTarea) {
		this.idTarea = idTarea;
	}

	public Date getFechaEjecucion() {
		return fechaEjecucion;
	}

	public void setFechaEjecucion(Date fechaEjecucion) {
		this.fechaEjecucion = fechaEjecucion;
	}

	public String getUsuarioEjecucion() {
		return usuarioEjecucion;
	}

	public void setUsuarioEjecucion(String usuarioEjecucion) {
		this.usuarioEjecucion = usuarioEjecucion;
	}

	public String getInformacionAdicional() {
		return informacionAdicional;
	}

	public void setInformacionAdicional(String informacionAdicional) {
		this.informacionAdicional = informacionAdicional;
	}

	public TipoTareaGestionaEnum getGestionaSobre() {
		return gestionaSobre;
	}

	public void setGestionaSobre(TipoTareaGestionaEnum gestionaSobre) {
		this.gestionaSobre = gestionaSobre;
	}

	public TipoTareaEstadoEnum getEstado() {
		return estado;
	}

	public void setEstado(TipoTareaEstadoEnum estado) {
		this.estado = estado;
	}

	public ShvWfTareaValor getTareaValor() {
		return tareaValor;
	}

	public void setTareaValor(ShvWfTareaValor tareaValor) {
		this.tareaValor = tareaValor;
	}

	public ShvWfTareaTalonario getTareaTalonario() {
		return tareaTalonario;
	}

	public void setTareaTalonario(ShvWfTareaTalonario tareaTalonario) {
		this.tareaTalonario = tareaTalonario;
	}

	public ShvWfTareaRegistroAVC getTareaRegistroAVC() {
		return tareaRegistroAVC;
	}

	public void setTareaRegistroAVC(ShvWfTareaRegistroAVC tareaRegistroAVC) {
		this.tareaRegistroAVC = tareaRegistroAVC;
	}

	public Integer getIdWorkflow() {
		return idWorkflow;
	}

	public void setIdWorkflow(Integer idWorkflow) {
		this.idWorkflow = idWorkflow;
	}

	public TipoTareaEnum getTipoTarea() {
		return tipoTarea;
	}

	public void setTipoTarea(TipoTareaEnum tipoTarea) {
		this.tipoTarea = tipoTarea;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public String getPerfilAsignacion() {
		return perfilAsignacion;
	}

	public void setPerfilAsignacion(String perfilAsignacion) {
		this.perfilAsignacion = perfilAsignacion;
	}

	public ShvWfTareaValorReversion getTareaValorPorReversion() {
		return tareaValorPorReversion;
	}

	public void setTareaValorPorReversion(ShvWfTareaValorReversion valorPorReversion) {
		this.tareaValorPorReversion = valorPorReversion;
	}

	public String getUsuarioAsignacion() {
		return usuarioAsignacion;
	}

	public void setUsuarioAsignacion(String usuarioAsignacion) {
		this.usuarioAsignacion = usuarioAsignacion;
	}

	public ShvWfTareaCobro getTareaCobro() {
		return tareaCobro;
	}

	public void setTareaCobro(ShvWfTareaCobro tareaCobro) {
		this.tareaCobro = tareaCobro;
	}

	public ShvWfTareaOperacionMasiva getTareaOperacionMasiva() {
		return tareaOperacionMasiva;
	}

	public void setTareaOperacionMasiva(ShvWfTareaOperacionMasiva tareaOperacionMasiva) {
		this.tareaOperacionMasiva = tareaOperacionMasiva;
	}

	/**
	 * @return the fechaAlta
	 */
	public Date getFechaUltimaModificacion() {
		return fechaUltimaModificacion;
	}

	/**
	 * @param fechaAlta the fechaAlta to set
	 */
	public void setFechaUltimaModificacion(Date fechaUltimaModificacion) {
		this.fechaUltimaModificacion = fechaUltimaModificacion;
	}
	
	
	
}
