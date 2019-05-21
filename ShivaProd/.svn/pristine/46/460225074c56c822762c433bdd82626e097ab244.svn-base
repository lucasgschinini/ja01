package ar.com.telecom.shiva.persistencia.modelo.simple;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import ar.com.telecom.shiva.persistencia.modelo.Modelo;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamEmpresa;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamGestor;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamSegmento;

/**
 * @author u564030 Pablo M. Ibarrola
 * 
 * Req 70868 Mejora de Performance en Talonarios.
 * Se crean objetos mas livianos para hacer mas performance la gestion de recibos y talonarios 
 */
@Entity
@Table(name = "SHV_TAL_TALONARIO")
public class ShvTalTalonarioSimplificado extends Modelo{
	
	private static final long serialVersionUID = 1L;

	@Id
 	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_SHV_TAL_TALONARIO")
 	@SequenceGenerator(name="SEQ_SHV_TAL_TALONARIO", sequenceName="SEQ_SHV_TAL_TALONARIO", allocationSize = 1)
	@Column(name="ID_TALONARIO")
	private Integer idTalonario;
	
	@ManyToOne(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_WORKFLOW", referencedColumnName="ID_WORKFLOW")
	private ShvWfWorkflowSimplificado workflow;
	
	@OneToMany(fetch = FetchType.EAGER, cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}, mappedBy="talonario")
	@NotFound(action=NotFoundAction.IGNORE)
	private Set<ShvTalReciboPreImpresoSimplificado> shvTalReciboPreImpreso = new HashSet<ShvTalReciboPreImpresoSimplificado>(0);
	 
	@ManyToOne(cascade = CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_EMPRESA") 
	@NotFound(action=NotFoundAction.IGNORE)
	private ShvParamEmpresa empresa;
	
	@ManyToOne(cascade = CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_SEGMENTO")
	@NotFound(action=NotFoundAction.IGNORE)
	private ShvParamSegmento segmento;
	
	@Column(name="NUMERO_SERIE")
	private Integer nroSerie;

	@Column(name="RANGO_DESDE")	 			
	private	Integer	rangoDesde;
	
	@Column(name="RANGO_HASTA")	 			
	private	Integer	rangoHasta;
	
	@ManyToOne(cascade={CascadeType.DETACH}, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_GESTOR", referencedColumnName="ID_GESTOR")
	@NotFound(action=NotFoundAction.IGNORE)
	private	ShvParamGestor usuarioGestor;
	
	@Column(name="FECHA_ALTA")	 			
	private Date fechaAlta;

	@Column(name="USUARIO_ALTA")	 			
	private String usuarioAlta;
	
	@Column(name="FECHA_ASIGNACION")	 			
	private Date fechaAsignacion;
	
	@Column(name="USUARIO_ASIGNACION")	 			
	private String usuarioAsignacion;
	
	@Column(name="FECHA_RENDICION")	 			
	private Date fechaRendicion;
	
	@Column(name="USUARIO_RENDICION")	 			
	private String usuarioRendicion;
	
	@Column(name="FECHA_APROB_RENDICION")	 			
	private Date fechaAprobacionRendicion;
	
	@Column(name="USUARIO_APROB_RENDICION")	 			
	private String usuarioAprobacionRendicion;

	/**
	 * @return the idTalonario
	 */
	public Integer getIdTalonario() {
		return idTalonario;
	}

	/**
	 * @param idTalonario the idTalonario to set
	 */
	public void setIdTalonario(Integer idTalonario) {
		this.idTalonario = idTalonario;
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
	 * @return the empresa
	 */
	public ShvParamEmpresa getEmpresa() {
		return empresa;
	}

	/**
	 * @param empresa the empresa to set
	 */
	public void setEmpresa(ShvParamEmpresa empresa) {
		this.empresa = empresa;
	}

	/**
	 * @return the segmento
	 */
	public ShvParamSegmento getSegmento() {
		return segmento;
	}

	/**
	 * @param segmento the segmento to set
	 */
	public void setSegmento(ShvParamSegmento segmento) {
		this.segmento = segmento;
	}

	/**
	 * @return the nroSerie
	 */
	public Integer getNroSerie() {
		return nroSerie;
	}

	/**
	 * @param nroSerie the nroSerie to set
	 */
	public void setNroSerie(Integer nroSerie) {
		this.nroSerie = nroSerie;
	}

	/**
	 * @return the rangoDesde
	 */
	public Integer getRangoDesde() {
		return rangoDesde;
	}

	/**
	 * @param rangoDesde the rangoDesde to set
	 */
	public void setRangoDesde(Integer rangoDesde) {
		this.rangoDesde = rangoDesde;
	}

	/**
	 * @return the rangoHasta
	 */
	public Integer getRangoHasta() {
		return rangoHasta;
	}

	/**
	 * @param rangoHasta the rangoHasta to set
	 */
	public void setRangoHasta(Integer rangoHasta) {
		this.rangoHasta = rangoHasta;
	}

	/**
	 * @return the usuarioGestor
	 */
	public ShvParamGestor getUsuarioGestor() {
		return usuarioGestor;
	}

	/**
	 * @param usuarioGestor the usuarioGestor to set
	 */
	public void setUsuarioGestor(ShvParamGestor usuarioGestor) {
		this.usuarioGestor = usuarioGestor;
	}

	/**
	 * @return the fechaAlta
	 */
	public Date getFechaAlta() {
		return fechaAlta;
	}

	/**
	 * @param fechaAlta the fechaAlta to set
	 */
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	/**
	 * @return the usuarioAlta
	 */
	public String getUsuarioAlta() {
		return usuarioAlta;
	}

	/**
	 * @param usuarioAlta the usuarioAlta to set
	 */
	public void setUsuarioAlta(String usuarioAlta) {
		this.usuarioAlta = usuarioAlta;
	}

	/**
	 * @return the fechaAsignacion
	 */
	public Date getFechaAsignacion() {
		return fechaAsignacion;
	}

	/**
	 * @param fechaAsignacion the fechaAsignacion to set
	 */
	public void setFechaAsignacion(Date fechaAsignacion) {
		this.fechaAsignacion = fechaAsignacion;
	}

	/**
	 * @return the usuarioAsignacion
	 */
	public String getUsuarioAsignacion() {
		return usuarioAsignacion;
	}

	/**
	 * @param usuarioAsignacion the usuarioAsignacion to set
	 */
	public void setUsuarioAsignacion(String usuarioAsignacion) {
		this.usuarioAsignacion = usuarioAsignacion;
	}

	/**
	 * @return the fechaRendicion
	 */
	public Date getFechaRendicion() {
		return fechaRendicion;
	}

	/**
	 * @param fechaRendicion the fechaRendicion to set
	 */
	public void setFechaRendicion(Date fechaRendicion) {
		this.fechaRendicion = fechaRendicion;
	}

	/**
	 * @return the usuarioRendicion
	 */
	public String getUsuarioRendicion() {
		return usuarioRendicion;
	}

	/**
	 * @param usuarioRendicion the usuarioRendicion to set
	 */
	public void setUsuarioRendicion(String usuarioRendicion) {
		this.usuarioRendicion = usuarioRendicion;
	}

	/**
	 * @return the fechaAprobacionRendicion
	 */
	public Date getFechaAprobacionRendicion() {
		return fechaAprobacionRendicion;
	}

	/**
	 * @param fechaAprobacionRendicion the fechaAprobacionRendicion to set
	 */
	public void setFechaAprobacionRendicion(Date fechaAprobacionRendicion) {
		this.fechaAprobacionRendicion = fechaAprobacionRendicion;
	}

	/**
	 * @return the usuarioAprobacionRendicion
	 */
	public String getUsuarioAprobacionRendicion() {
		return usuarioAprobacionRendicion;
	}

	/**
	 * @param usuarioAprobacionRendicion the usuarioAprobacionRendicion to set
	 */
	public void setUsuarioAprobacionRendicion(String usuarioAprobacionRendicion) {
		this.usuarioAprobacionRendicion = usuarioAprobacionRendicion;
	}
}
