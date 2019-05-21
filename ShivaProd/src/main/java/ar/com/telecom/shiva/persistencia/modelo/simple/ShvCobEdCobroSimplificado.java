package ar.com.telecom.shiva.persistencia.modelo.simple;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import ar.com.telecom.shiva.persistencia.modelo.Modelo;


@Entity
@Table(name = "SHV_COB_ED_COBRO")
public class ShvCobEdCobroSimplificado extends Modelo {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_COBRO", updatable = false)
	private Long idCobro;
	
//	@ManyToOne(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}, fetch=FetchType.EAGER)
//	@JoinColumn(name="ID_WORKFLOW", referencedColumnName="ID_WORKFLOW")
//	private ShvWfWorkflow workflow;
	
	@Column(name="ID_OPERACION", updatable = false)
	private Long idOperacion;
	
//	@OneToMany(fetch = FetchType.EAGER, cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST})
//	@JoinColumn(name="ID_COBRO", referencedColumnName="ID_COBRO")
//	@NotFound(action=NotFoundAction.IGNORE)
//	private Set<ShvCobEdDebito> debitos = new HashSet<ShvCobEdDebito>(0);
//	
//	@OneToMany(fetch = FetchType.EAGER, cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST})
//	@JoinColumn(name="ID_COBRO", referencedColumnName="ID_COBRO")
//	@NotFound(action=NotFoundAction.IGNORE)
//	private Set<ShvCobEdCredito> creditos = new HashSet<ShvCobEdCredito>(0);
//	
//	@OneToMany(fetch = FetchType.EAGER, cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST})
//	@JoinColumn(name="ID_COBRO", referencedColumnName="ID_COBRO")
//	@NotFound(action=NotFoundAction.IGNORE)
//	private Set<ShvCobEdCliente> clientes = new HashSet<ShvCobEdCliente>(0);
//	
//	@OneToMany(fetch = FetchType.EAGER, cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST})
//	@JoinColumn(name="ID_COBRO", referencedColumnName="ID_COBRO")
//	@NotFound(action=NotFoundAction.IGNORE)
//	private Set<ShvCobEdOtrosMedioPago> mediosPagos = new HashSet<ShvCobEdOtrosMedioPago>(0);
//
//	@OneToOne(fetch = FetchType.EAGER, cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
//	@PrimaryKeyJoinColumn(name="ID_COBRO", referencedColumnName="ID_COBRO")
//	@NotFound(action=NotFoundAction.IGNORE)
//	private ShvCobEdTratamientoDiferencia tratamientoDiferencia;
//	
//	@ManyToOne(cascade = CascadeType.DETACH, fetch=FetchType.EAGER)
//	@JoinColumn(name="ID_EMPRESA") 
//	@NotFound(action=NotFoundAction.IGNORE)
//	private ShvParamEmpresa empresa;
	@Column(name="ID_EMPRESA" , updatable = false) 
	private String idEmpresa;

//	@ManyToOne(cascade = CascadeType.DETACH, fetch=FetchType.EAGER)
//	@JoinColumn(name="ID_SEGMENTO")
//	@NotFound(action=NotFoundAction.IGNORE)
//	private ShvParamSegmento segmento;
	@Column(name="ID_SEGMENTO" , updatable = false) 
	private String idSegmento;

//	@Column(name="IMPORTE_TOTAL") 			
//	private BigDecimal importeTotal;
//	
//	@Column(name="ID_ANALISTA")
//	private String idAnalista;
//
//	@Column(name="ANALISTA")
//	private String nombreApellidoAnalista;
//
//	@Column(name="ID_COPROPIETARIO")
//	private String idCopropietario;
//	
//	@Column(name="COPROPIETARIO")
//	private String nombreApellidoCopropietario;
//
//	@Column(name="ID_ANALISTA_TEAM_COMERCIAL")
//	private String idAnalistaTeamComercial;
//
//	@Column(name="ANALISTA_TEAM_COMERCIAL")
//	private String nombreApellidoAnalistaTeamComercial;
//
//	@ManyToOne(cascade = CascadeType.DETACH, fetch=FetchType.EAGER)
//	@JoinColumn(name="ID_MOTIVO_COBRO")
//	@NotFound(action=NotFoundAction.IGNORE)
//	private ShvParamMotivoCobro motivo;
//
//	@Column(name="FECHA_ALTA")
//	private Date fechaAlta;
//
//	@Column(name="USUARIO_ALTA")
//	private String usuarioAlta;
//	
//	@Column(name="FECHA_DERIVACION")
//	private Date fechaDerivacion;
//
//	@Column(name="USUARIO_DERIVACION")
//	private String usuarioDerivacion;
//	
//	@Column(name="FECHA_APROB_SUPER_COB")
//	private Date fechaAprobacionSupervisorCobranza;
//
//	@Column(name="USUARIO_APROB_SUPER_COB")
//	private String usuarioAprobacionSupervisorCobranza;
//	
//	@Column(name="FECHA_APROB_REFER_COB")
//	private Date fechaAprobacionReferenteCobranza;
//
//	@Column(name="USUARIO_APROB_REFER_COB")
//	private String usuarioAprobacionReferenteCobranza;
	
	@Column(name="FECHA_IMPUTACION")
	private Date fechaImputacion;

	@Column(name="USUARIO_IMPUTACION")
	private String usuarioImputacion;
	
	@Column(name="FECHA_ULTIMA_MODIFICACION")
	private Date fechaUltimaModificacion;

	@Column(name="USUARIO_ULTIMA_MODIFICACION")
	private String usuarioUltimaModificacion;
	
//	@Column(name="OBSERVACION")
//	private String observacion;
//
//	@Column(name="ID_COBRO_PADRE")
//	private Long idCobroPadre;
//	
//	@Column(name="CHECKSUM_AL_SIMULAR")
//	private String checkSumAlSimular;
//	
//	@Column(name="REFERENTE_APROBADOR")
//	private String nombreApellidoReferenteAprobador;
//	
	@Column(name="USUARIO_APROB_SUPER_OPER_ESPE")
	private String usuarioAprobadorSupervisorOperacEspeciales;
	
	
	@Column(name="IMPORTE_PARCIAL") 			
	private BigDecimal importeParcial;

	public BigDecimal getImporteParcial() {
		return importeParcial;
	}


	public void setImporteParcial(BigDecimal importeParcial) {
		this.importeParcial = importeParcial;
	}
	
	public ShvCobEdCobroSimplificado() {
	}


	

	public void setIdCobro(Long idCobro) {
		this.idCobro = idCobro;
	}

	

	public Date getFechaUltimaModificacion() {
		return fechaUltimaModificacion;
	}

	public void setFechaUltimaModificacion(Date fechaUltimaModificacion) {
		this.fechaUltimaModificacion = fechaUltimaModificacion;
	}

	public String getUsuarioUltimaModificacion() {
		return usuarioUltimaModificacion;
	}

	public void setUsuarioUltimaModificacion(String usuarioUltimaModificacion) {
		this.usuarioUltimaModificacion = usuarioUltimaModificacion;
	}

	public Long getIdOperacion() {
		return idOperacion;
	}

	public void setIdOperacion(Long idOperacion) {
		this.idOperacion = idOperacion;
	}

	public Long getIdCobro() {
		return idCobro;
	}




	public Date getFechaImputacion() {
		return fechaImputacion;
	}




	public void setFechaImputacion(Date fechaImputacion) {
		this.fechaImputacion = fechaImputacion;
	}




	public String getUsuarioImputacion() {
		return usuarioImputacion;
	}




	public void setUsuarioImputacion(String usuarioImputacion) {
		this.usuarioImputacion = usuarioImputacion;
	}




	/**
	 * @return the idEmpresa
	 */
	public String getIdEmpresa() {
		return idEmpresa;
	}




	/**
	 * @param idEmpresa the idEmpresa to set
	 */
	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}



	/**
	 * @return the idSegmento
	 */
	public String getIdSegmento() {
		return idSegmento;
	}



	/**
	 * @param idSegmento the idSegmento to set
	 */
	public void setIdSegmento(String idSegmento) {
		this.idSegmento = idSegmento;
	}



	public String getUsuarioAprobadorSupervisorOperacEspeciales() {
		return usuarioAprobadorSupervisorOperacEspeciales;
	}




	public void setUsuarioAprobadorSupervisorOperacEspeciales(
			String usuarioAprobadorSupervisorOperacEspeciales) {
		this.usuarioAprobadorSupervisorOperacEspeciales = usuarioAprobadorSupervisorOperacEspeciales;
	}

}