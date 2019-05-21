package ar.com.telecom.shiva.persistencia.modelo;

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

import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamEmpresa;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamGestor;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamSegmento;

@Entity
@Table(name = "SHV_TAL_TALONARIO")
public class ShvTalTalonario extends Modelo{
	
	private static final long serialVersionUID = 1L;

	@Id
 	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_SHV_TAL_TALONARIO")
 	@SequenceGenerator(name="SEQ_SHV_TAL_TALONARIO", sequenceName="SEQ_SHV_TAL_TALONARIO", allocationSize = 1)
	@Column(name="ID_TALONARIO")
	private 	Integer idTalonario;
	
	@ManyToOne(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_WORKFLOW", referencedColumnName="ID_WORKFLOW")
	private ShvWfWorkflow workflow;
	 
	@OneToMany(fetch = FetchType.EAGER, cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}, mappedBy="talonario")
	@NotFound(action=NotFoundAction.IGNORE)
	private Set<ShvTalReciboPreImpreso> shvTalReciboPreImpreso = new HashSet<ShvTalReciboPreImpreso>(0);
	
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
	
	
	
	
	
	public ShvTalTalonario(){
		
	}
	
	public Set<ShvTalReciboPreImpreso> getShvTalReciboPreImpreso() {
		return shvTalReciboPreImpreso;
	}

	public void setShvTalReciboPreImpreso(
			Set<ShvTalReciboPreImpreso> shvTalReciboPreImpreso) {
		this.shvTalReciboPreImpreso = shvTalReciboPreImpreso;
	}

	public ShvTalTalonario(Integer id) {
		super.setId(id);
	}
	
	
	public Integer getIdTalonario() {
		return idTalonario;
	}
	public void setIdTalonario(Integer idTalonario) {
		this.idTalonario = idTalonario;
	}
 
	public ShvWfWorkflow getWorkflow() {
		return workflow;
	}
	public void setWorkflow(ShvWfWorkflow workflow) {
		this.workflow = workflow;
	}
	
	public ShvParamEmpresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(ShvParamEmpresa empresa) {
		this.empresa = empresa;
	}
	public ShvParamSegmento getSegmento() {
		return segmento;
	}
	public void setSegmento(ShvParamSegmento segmento) {
		this.segmento = segmento;
	}
	public Integer getRangoDesde() {
		return rangoDesde;
	}
	public void setRangoDesde(Integer rangoDesde) {
		this.rangoDesde = rangoDesde;
	}
	public Integer getRangoHasta() {
		return rangoHasta;
	}
	public void setRangoHasta(Integer rangoHasta) {
		this.rangoHasta = rangoHasta;
	}

	public ShvParamGestor getUsuarioGestor() {
		return usuarioGestor;
	}

	public void setUsuarioGestor(ShvParamGestor usuarioGestor) {
		this.usuarioGestor = usuarioGestor;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public String getUsuarioAlta() {
		return usuarioAlta;
	}

	public void setUsuarioAlta(String usuarioAlta) {
		this.usuarioAlta = usuarioAlta;
	}

	public Date getFechaAsignacion() {
		return fechaAsignacion;
	}

	public void setFechaAsignacion(Date fechaAsignacion) {
		this.fechaAsignacion = fechaAsignacion;
	}

	public String getUsuarioAsignacion() {
		return usuarioAsignacion;
	}

	public void setUsuarioAsignacion(String usuarioAsignacion) {
		this.usuarioAsignacion = usuarioAsignacion;
	}


	public Date getFechaRendicion() {
		return fechaRendicion;
	}

	public void setFechaRendicion(Date fechaRendicion) {
		this.fechaRendicion = fechaRendicion;
	}

	public String getUsuarioRendicion() {
		return usuarioRendicion;
	}

	public void setUsuarioRendicion(String usuarioRendicion) {
		this.usuarioRendicion = usuarioRendicion;
	}

	public Date getFechaAprobacionRendicion() {
		return fechaAprobacionRendicion;
	}

	public void setFechaAprobacionRendicion(Date fechaAprobacionRendicion) {
		this.fechaAprobacionRendicion = fechaAprobacionRendicion;
	}

	public String getUsuarioAprobacionRendicion() {
		return usuarioAprobacionRendicion;
	}

	public void setUsuarioAprobacionRendicion(String usuarioAprobacionRendicion) {
		this.usuarioAprobacionRendicion = usuarioAprobacionRendicion;
	}

	public Integer getNroSerie() {
		return nroSerie;
	}

	public void setNroSerie(Integer nroSerie) {
		this.nroSerie = nroSerie;
	}

	
	
}
