package ar.com.telecom.shiva.persistencia.modelo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import ar.com.telecom.shiva.base.comparador.ComparatorOrdenGuardadoShvCobEdCredito;
import ar.com.telecom.shiva.base.comparador.ComparatorOrdenGuardadoShvCobEdDebito;
import ar.com.telecom.shiva.base.comparador.ComparatorOrdenGuardadoShvCobEdOtrosDebito;
import ar.com.telecom.shiva.base.comparador.ComparatorOrdenGuardadoShvCobEdOtrosMedioPago;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.MotivoCobroUsoEnum;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamEmpresa;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamMotivoCobro;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamSegmento;


@Entity
@Table(name = "SHV_COB_ED_COBRO")
public class ShvCobEdCobro extends Modelo {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_COBRO", updatable = false)
	private Long idCobro;
	
	@ManyToOne(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_WORKFLOW", referencedColumnName="ID_WORKFLOW")
	private ShvWfWorkflow workflow;
	
	@Column(name="ID_OPERACION", updatable = false)
	private Long idOperacion;
	
	@OneToMany(fetch = FetchType.EAGER, cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name="ID_COBRO", referencedColumnName="ID_COBRO")
	@NotFound(action=NotFoundAction.IGNORE)
	private Set<ShvCobEdDebito> debitos = new HashSet<ShvCobEdDebito>(0);
	
	@OneToMany(fetch = FetchType.EAGER, cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name="ID_COBRO", referencedColumnName="ID_COBRO")
	@NotFound(action=NotFoundAction.IGNORE)
	private Set<ShvCobEdOtrosDebito> otrosDebitos = new HashSet<ShvCobEdOtrosDebito>(0);
	
	@OneToMany(fetch = FetchType.EAGER, cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name="ID_COBRO", referencedColumnName="ID_COBRO")
	@NotFound(action=NotFoundAction.IGNORE)
	private Set<ShvCobEdCredito> creditos = new HashSet<ShvCobEdCredito>(0);
	
	@OneToMany(fetch = FetchType.EAGER, cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name="ID_COBRO", referencedColumnName="ID_COBRO")
	@NotFound(action=NotFoundAction.IGNORE)
	private Set<ShvCobEdCliente> clientes = new HashSet<ShvCobEdCliente>(0);
	
	@OneToMany(fetch = FetchType.EAGER, cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name="ID_COBRO", referencedColumnName="ID_COBRO")
	@NotFound(action=NotFoundAction.IGNORE)
	private Set<ShvCobEdOtrosMedioPago> mediosPagos = new HashSet<ShvCobEdOtrosMedioPago>(0);

	@OneToOne(fetch = FetchType.EAGER, cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
	@PrimaryKeyJoinColumn(name="ID_COBRO", referencedColumnName="ID_COBRO")
	@NotFound(action=NotFoundAction.IGNORE)
	private ShvCobEdTratamientoDiferencia tratamientoDiferencia;
	
	@ManyToOne(cascade = CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_EMPRESA") 
	@NotFound(action=NotFoundAction.IGNORE)
	private ShvParamEmpresa empresa;
	
	@ManyToOne(cascade = CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_SEGMENTO")
	@NotFound(action=NotFoundAction.IGNORE)
	private ShvParamSegmento segmento;
	
	@Column(name="ID_OPERACION_MASIVA")
	private Long idOperacionMasiva;
	
	@Column(name="IMPORTE_TOTAL") 			
	private BigDecimal importeTotal;
	
	@Column(name="ID_ANALISTA")
	private String idAnalista;

	@Column(name="ANALISTA")
	private String nombreApellidoAnalista;

	@Column(name="ID_COPROPIETARIO")
	private String idCopropietario;
	
	@Column(name="COPROPIETARIO")
	private String nombreApellidoCopropietario;

	@Column(name="ID_ANALISTA_TEAM_COMERCIAL")
	private String idAnalistaTeamComercial;

	@Column(name="ANALISTA_TEAM_COMERCIAL")
	private String nombreApellidoAnalistaTeamComercial;

	@ManyToOne(cascade = CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_MOTIVO_COBRO")
	@NotFound(action=NotFoundAction.IGNORE)
	private ShvParamMotivoCobro motivo;

	@Column(name="FECHA_ALTA")
	private Date fechaAlta;

	@Column(name="USUARIO_ALTA")
	private String usuarioAlta;
	
	@Column(name="FECHA_DERIVACION")
	private Date fechaDerivacion;

	@Column(name="USUARIO_DERIVACION")
	private String usuarioDerivacion;
	
	@Column(name="FECHA_APROB_SUPER_COB")
	private Date fechaAprobacionSupervisorCobranza;

	@Column(name="USUARIO_APROB_SUPER_COB")
	private String usuarioAprobacionSupervisorCobranza;
	
	@Column(name="FECHA_APROB_REFER_COB")
	private Date fechaAprobacionReferenteCobranza;

	@Column(name="USUARIO_APROB_REFER_COB")
	private String usuarioAprobacionReferenteCobranza;
	
	@Column(name="FECHA_IMPUTACION")
	private Date fechaImputacion;

	@Column(name="USUARIO_IMPUTACION")
	private String usuarioImputacion;
	
	@Column(name="FECHA_ULTIMA_MODIFICACION")
	private Date fechaUltimaModificacion;

	@Column(name="USUARIO_ULTIMA_MODIFICACION")
	private String usuarioUltimaModificacion;

	@Column(name="USUARIO_APROB_SUPER_OPER_ESPE")
	private String usuarioAprobadorSuperOperacionesEspeciales;

	
	@Column(name="OBSERVACION")
	private String observacion;

	@Column(name="ID_COBRO_PADRE")
	private Long idCobroPadre;
	
	@Column(name="CHECKSUM_AL_SIMULAR")
	private String checkSumAlSimular;
	
	@Column(name="REFERENTE_APROBADOR")
	private String nombreApellidoReferenteAprobador;
	
	@Column(name="SUPERVISOR_APROBADOR")
	private String nombreApellidoSupervisorAprobador;
	
	@Column(name="APROBADOR_SUPER_OPER_ESPE")
	private String nombreAprobadorSuperOperacionesEspeciales;

	@Enumerated(EnumType.STRING)
	@Column(name="MONEDA_OPERACION")
	private MonedaEnum  monedaOperacion ;

	//COTIZACION
	@Column(name = "TIPO_CAMBIO")
	private String tipoCambio;

	@Column(name = "FECHA_TIPO_CAMBIO")
	private Date fechaTipoCambio;

	@Column(name="FECHA_APROB_OPER_ESPE")
	private Date fechaAprobacionOperacionesEspeciales;
	
	@Column(name="FECHA_RECHAZA_APLIC_MANUAL")
	private Date fechaRechazoAplicacionManual;

	@Column(name="USUARIO_RECHAZA_APLIC_MANUAL")
	private String usuarioRechazoAplicacionManual;
	
	@OneToMany(fetch = FetchType.EAGER, cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}, mappedBy = "cobro")
	private Set<ShvCobEdCodigoOperacionExterna> codigosOperacionesExternas = new HashSet<ShvCobEdCodigoOperacionExterna>(0);
	
	@Column(name="REFERENTE_CAJA")
	private String nombreApellidoReferenteCaja;
	
	@Column(name="ID_REFERENTE_CAJA")
	private String idReferenteCaja;
	
	@Column(name="FECHA_REFERENTE_CAJA")
	private Date fechaReferenteCaja;
	
	@Column(name="REFERENTE_OPER_EXTERNA")
	private String nombreApellidoReferenteOperacionExterna;
	
	@Column(name="ID_REFERENTE_OPER_EXTERNA")
	private String idReferenteoperacionexterna;
	
	@Column(name="FECHA_REFERENTE_OPER_EXTERNA")
	private Date fechaReferenteOperacionExterna;
	
	@Transient
	private Set<ShvCobEdCobroAdjunto> comprobantes = new HashSet<ShvCobEdCobroAdjunto>(0);
	
	@Column(name="IMPORTE_PARCIAL") 			
	private BigDecimal importeParcial;

	public BigDecimal getImporteParcial() {
		return importeParcial;
	}


	public void setImporteParcial(BigDecimal importeParcial) {
		this.importeParcial = importeParcial;
	}


	public ShvCobEdCobro() {
	}


	public String getIdAnalista() {
		return this.idAnalista;
	}

	public void setIdAnalista(String idAnalista) {
		this.idAnalista = idAnalista;
	}

	public String getIdCopropietario() {
		return this.idCopropietario;
	}

	public void setIdCopropietario(String idCopropietario) {
		this.idCopropietario = idCopropietario;
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

	public ShvParamMotivoCobro getMotivo() {
		return motivo;
	}

	public void setMotivo(ShvParamMotivoCobro motivo) {
		this.motivo = motivo;
	}

	public void setIdCobro(Long idCobro) {
		this.idCobro = idCobro;
	}

	/**
	 * Se retorna una lista ordenada.
	 * 
	 * Es necesario que esta lista se encuentre ordenada a fin de que, cada vez que se guarde el cobro, los elementos 
	 * se guarden de la misma manera que se almacenaron de manera original
	 * 
	 * @return
	 */
	public Set<ShvCobEdCredito> getCreditos() {
		Set<ShvCobEdCredito> creditosOrdenados = new TreeSet<ShvCobEdCredito>(new ComparatorOrdenGuardadoShvCobEdCredito());
		creditosOrdenados.addAll(creditos);
		return creditosOrdenados;
	}

	public void setCreditos(Set<ShvCobEdCredito> creditos) {
		this.creditos = creditos;
	}

	public Set<ShvCobEdCliente> getClientes() {
		return clientes;
	}

	public void setClientes(Set<ShvCobEdCliente> clientes) {
		this.clientes = clientes;
	}

	/**
	 * Se retorna una lista ordenada.
	 * 
	 * Es necesario que esta lista se encuentre ordenada a fin de que, cada vez que se guarde el cobro, los elementos 
	 * se guarden de la misma manera que se almacenaron de manera original
	 * 
	 * @return
	 */
	public Set<ShvCobEdOtrosMedioPago> getMediosPagos() {
		Set<ShvCobEdOtrosMedioPago> otrosMediosPagosOrdenados = new TreeSet<ShvCobEdOtrosMedioPago>(new ComparatorOrdenGuardadoShvCobEdOtrosMedioPago());
		otrosMediosPagosOrdenados.addAll(mediosPagos);
		return otrosMediosPagosOrdenados;
	}

	public void setMediosPagos(Set<ShvCobEdOtrosMedioPago> mediosPagos) {
		this.mediosPagos = mediosPagos;
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

	public Date getFechaDerivacion() {
		return fechaDerivacion;
	}

	public void setFechaDerivacion(Date fechaDerivacion) {
		this.fechaDerivacion = fechaDerivacion;
	}

	public String getUsuarioDerivacion() {
		return usuarioDerivacion;
	}

	public void setUsuarioDerivacion(String usuarioDerivacion) {
		this.usuarioDerivacion = usuarioDerivacion;
	}

	public Date getFechaAprobacionSupervisorCobranza() {
		return fechaAprobacionSupervisorCobranza;
	}

	public void setFechaAprobacionSupervisorCobranza(
			Date fechaAprobacionSupervisorCobranza) {
		this.fechaAprobacionSupervisorCobranza = fechaAprobacionSupervisorCobranza;
	}

	public String getUsuarioAprobacionSupervisorCobranza() {
		return usuarioAprobacionSupervisorCobranza;
	}

	public void setUsuarioAprobacionSupervisorCobranza(
			String usuarioAprobacionSupervisorCobranza) {
		this.usuarioAprobacionSupervisorCobranza = usuarioAprobacionSupervisorCobranza;
	}

	public Date getFechaAprobacionReferenteCobranza() {
		return fechaAprobacionReferenteCobranza;
	}

	public void setFechaAprobacionReferenteCobranza(
			Date fechaAprobacionReferenteCobranza) {
		this.fechaAprobacionReferenteCobranza = fechaAprobacionReferenteCobranza;
	}

	public String getUsuarioAprobacionReferenteCobranza() {
		return usuarioAprobacionReferenteCobranza;
	}

	public void setUsuarioAprobacionReferenteCobranza(
			String usuarioAprobacionReferenteCobranza) {
		this.usuarioAprobacionReferenteCobranza = usuarioAprobacionReferenteCobranza;
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

	public ShvCobEdTratamientoDiferencia getTratamientoDiferencia() {
		return tratamientoDiferencia;
	}

	public void setTratamientoDiferencia(
			ShvCobEdTratamientoDiferencia tratamientoDiferencia) {
		this.tratamientoDiferencia = tratamientoDiferencia;
	}

	public Long getIdCobro() {
		return idCobro;
	}

	/**
	 * Se retorna una lista ordenada.
	 * 
	 * Es necesario que esta lista se encuentre ordenada a fin de que, cada vez que se guarde el cobro, los elementos 
	 * se guarden de la misma manera que se almacenaron de manera original
	 * 
	 * @return
	 */
	public Set<ShvCobEdDebito> getDebitos() {
		Set<ShvCobEdDebito> debitosOrdenados = new TreeSet<ShvCobEdDebito>(new ComparatorOrdenGuardadoShvCobEdDebito());
		debitosOrdenados.addAll(debitos);
		return debitosOrdenados;
	}

	public void setDebitos(Set<ShvCobEdDebito> debitos) {
		this.debitos = debitos;
	}
	
	/**
	 * Se retorna una lista ordenada.
	 * 
	 * Es necesario que esta lista se encuentre ordenada a fin de que, cada vez que se guarde el cobro, los elementos 
	 * se guarden de la misma manera que se almacenaron de manera original
	 * 
	 * @return
	 */
	public Set<ShvCobEdOtrosDebito> getOtrosDebitos() {
		Set<ShvCobEdOtrosDebito> otrosDebitosOrdenados = new TreeSet<ShvCobEdOtrosDebito>(new ComparatorOrdenGuardadoShvCobEdOtrosDebito());
		otrosDebitosOrdenados.addAll(otrosDebitos);
		return otrosDebitosOrdenados;
	}

	public void setOtrosDebitos(Set<ShvCobEdOtrosDebito> otrosDebitos) {
		this.otrosDebitos = otrosDebitos;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	/**
	 * @return the nombreApellidoAnalista
	 */
	public String getNombreApellidoAnalista() {
		return nombreApellidoAnalista;
	}

	/**
	 * @param nombreApellidoAnalista the nombreApellidoAnalista to set
	 */
	public void setNombreApellidoAnalista(String nombreApellidoAnalista) {
		this.nombreApellidoAnalista = nombreApellidoAnalista;
	}

	/**
	 * @return the nombreApellidoCopropietario
	 */
	public String getNombreApellidoCopropietario() {
		return nombreApellidoCopropietario;
	}

	/**
	 * @param nombreApellidoCopropietario the nombreApellidoCopropietario to set
	 */
	public void setNombreApellidoCopropietario(String nombreApellidoCopropietario) {
		this.nombreApellidoCopropietario = nombreApellidoCopropietario;
	}

	/**
	 * @return the idAnalistaTeamComercial
	 */
	public String getIdAnalistaTeamComercial() {
		return idAnalistaTeamComercial;
	}

	/**
	 * @param idAnalistaTeamComercial the idAnalistaTeamComercial to set
	 */
	public void setIdAnalistaTeamComercial(String idAnalistaTeamComercial) {
		this.idAnalistaTeamComercial = idAnalistaTeamComercial;
	}

	/**
	 * @return the nombreApellidoAnalistaTeamComercial
	 */
	public String getNombreApellidoAnalistaTeamComercial() {
		return nombreApellidoAnalistaTeamComercial;
	}

	/**
	 * @param nombreApellidoAnalistaTeamComercial the nombreApellidoAnalistaTeamComercial to set
	 */
	public void setNombreApellidoAnalistaTeamComercial(String nombreApellidoAnalistaTeamComercial) {
		this.nombreApellidoAnalistaTeamComercial = nombreApellidoAnalistaTeamComercial;
	}

	/**
	 * @return the importeTotal
	 */
	public BigDecimal getImporteTotal() {
		return importeTotal;
	}

	/**
	 * @param importeTotal the importeTotal to set
	 */
	public void setImporteTotal(BigDecimal importeTotal) {
		this.importeTotal = importeTotal;
	}

	/**
	 * @return the idCobroPadre
	 */
	public Long getIdCobroPadre() {
		return idCobroPadre;
	}

	/**
	 * @param idCobroPadre the idCobroPadre to set
	 */
	public void setIdCobroPadre(Long idCobroPadre) {
		this.idCobroPadre = idCobroPadre;
	}

	/**
	 * @return the checkSumAlSimular
	 */
	public String getCheckSumAlSimular() {
		return checkSumAlSimular;
	}

	/**
	 * @param checkSumAlSimular the checkSumAlSimular to set
	 */
	public void setCheckSumAlSimular(String checkSumAlSimular) {
		this.checkSumAlSimular = checkSumAlSimular;
	}


	/**
	 * @return the nombreApellidoReferenteAprobador
	 */
	public String getNombreApellidoReferenteAprobador() {
		return nombreApellidoReferenteAprobador;
	}


	/**
	 * @param nombreApellidoReferenteAprobador the nombreApellidoReferenteAprobador to set
	 */
	public void setNombreApellidoReferenteAprobador(
			String nombreApellidoReferenteAprobador) {
		this.nombreApellidoReferenteAprobador = nombreApellidoReferenteAprobador;
	}


	/**
	 * @return the nombreApellidoSupervisorAprobador
	 */
	public String getNombreApellidoSupervisorAprobador() {
		return nombreApellidoSupervisorAprobador;
	}


	/**
	 * @param nombreApellidoSupervisorAprobador the nombreApellidoSupervisorAprobador to set
	 */
	public void setNombreApellidoSupervisorAprobador(
			String nombreApellidoSupervisorAprobador) {
		this.nombreApellidoSupervisorAprobador = nombreApellidoSupervisorAprobador;
	}


	/**
	 * @return the idOperacionMasiva
	 */
	public Long getIdOperacionMasiva() {
		return idOperacionMasiva;
	}


	/**
	 * @param idOperacionMasiva the idOperacionMasiva to set
	 */
	public void setIdOperacionMasiva(Long idOperacionMasiva) {
		this.idOperacionMasiva = idOperacionMasiva;
	}


	public MonedaEnum getMonedaOperacion() {
		return monedaOperacion;
	}


	public void setMonedaOperacion(MonedaEnum monedaOperacion) {
		this.monedaOperacion = monedaOperacion;
	}

	/**
	 * @return the tipoCambio
	 */
	public String getTipoCambio() {
		return tipoCambio;
	}

	/**
	 * @param tipoCambio the tipoCambio to set
	 */
	public void setTipoCambio(String tipoCambio) {
		this.tipoCambio = tipoCambio;
	}

	/**
	 * @return the fechaTipoCambio
	 */
	public Date getFechaTipoCambio() {
		return fechaTipoCambio;
	}

	/**
	 * @param fechaTipoCambio the fechaTipoCambio to set
	 */
	public void setFechaTipoCambio(Date fechaTipoCambio) {
		this.fechaTipoCambio = fechaTipoCambio;
	}


	/**
	 * @return the usuarioVerificador
	 */
	public String getUsuarioAprobadorSuperOperacionesEspeciales() {
		return usuarioAprobadorSuperOperacionesEspeciales;
	}


	/**
	 * @param usuarioVerificador the usuarioVerificador to set
	 */
	public void setUsuarioAprobadorSuperOperacionesEspeciales(String usuarioVerificador) {
		this.usuarioAprobadorSuperOperacionesEspeciales = usuarioVerificador;
	}


	/**
	 * @return the fechaAprobacionOperacionesEspeciales
	 */
	public Date getFechaAprobacionOperacionesEspeciales() {
		return fechaAprobacionOperacionesEspeciales;
	}


	/**
	 * @param fechaAprobacionOperacionesEspeciales the fechaAprobacionOperacionesEspeciales to set
	 */
	public void setFechaAprobacionOperacionesEspeciales(
			Date fechaAprobacionOperacionesEspeciales) {
		this.fechaAprobacionOperacionesEspeciales = fechaAprobacionOperacionesEspeciales;
	}

	public Date getFechaRechazoAplicacionManual() {
		return fechaRechazoAplicacionManual;
	}


	public void setFechaRechazoAplicacionManual(Date fechaRechazoAplicacionManual) {
		this.fechaRechazoAplicacionManual = fechaRechazoAplicacionManual;
	}


	public String getUsuarioRechazoAplicacionManual() {
		return usuarioRechazoAplicacionManual;
	}


	public void setUsuarioRechazoAplicacionManual(
			String usuarioRechazoAplicacionManual) {
		this.usuarioRechazoAplicacionManual = usuarioRechazoAplicacionManual;
	}


	public Set<ShvCobEdCodigoOperacionExterna> getCodigosOperacionesExternas() {
		return codigosOperacionesExternas;
	}


	public void setCodigosOperacionesExternas(
			Set<ShvCobEdCodigoOperacionExterna> codigosOperacionesExternas) {
		this.codigosOperacionesExternas = codigosOperacionesExternas;
	}
	
	public String getNombreApellidoReferenteCaja() {
		return nombreApellidoReferenteCaja;
	}


	public void setNombreApellidoReferenteCaja(String nombreApellidoReferenteCaja) {
		this.nombreApellidoReferenteCaja = nombreApellidoReferenteCaja;
	}


	public String getIdReferenteCaja() {
		return idReferenteCaja;
	}


	public void setIdReferenteCaja(String idReferenteCaja) {
		this.idReferenteCaja = idReferenteCaja;
	}


	public Date getFechaReferenteCaja() {
		return fechaReferenteCaja;
	}


	public void setFechaReferenteCaja(Date fechaReferenteCaja) {
		this.fechaReferenteCaja = fechaReferenteCaja;
	}


	public String getNombreApellidoReferenteOperacionExterna() {
		return nombreApellidoReferenteOperacionExterna;
	}


	public void setNombreApellidoReferenteOperacionExterna(
			String nombreApellidoReferenteOperacionExterna) {
		this.nombreApellidoReferenteOperacionExterna = nombreApellidoReferenteOperacionExterna;
	}


	public String getIdReferenteoperacionexterna() {
		return idReferenteoperacionexterna;
	}


	public void setIdReferenteoperacionexterna(String idReferenteoperacionexterna) {
		this.idReferenteoperacionexterna = idReferenteoperacionexterna;
	}


	public Date getFechaReferenteOperacionExterna() {
		return fechaReferenteOperacionExterna;
	}


	public void setFechaReferenteOperacionExterna(
			Date fechaReferenteOperacionExterna) {
		this.fechaReferenteOperacionExterna = fechaReferenteOperacionExterna;
	}


	/**
	 * @return the comprobantes
	 */
	public Set<ShvCobEdCobroAdjunto> getComprobantes() {
		return comprobantes;
	}


	/**
	 * @param comprobantes the comprobantes to set
	 */
	public void setComprobantes(Set<ShvCobEdCobroAdjunto> comprobantes) {
		this.comprobantes = comprobantes;
	}
	

	public boolean esCobroAutomaticoValores() {
		
		if (!Validaciones.isObjectNull(getMotivo())){
			if (MotivoCobroUsoEnum.COBRO_SALIDA_AUTOMATICA_VALOR.equals(this.getMotivo().getIdMotivoCobro())){
				return true;
			}
		} 
		return false;
	}


	/**
	 * @return the nombreAprobadorSuperOperacionesEspeciales
	 */
	public String getNombreAprobadorSuperOperacionesEspeciales() {
		return nombreAprobadorSuperOperacionesEspeciales;
	}


	/**
	 * @param nombreAprobadorSuperOperacionesEspeciales the nombreAprobadorSuperOperacionesEspeciales to set
	 */
	public void setNombreAprobadorSuperOperacionesEspeciales(
			String nombreAprobadorSuperOperacionesEspeciales) {
		this.nombreAprobadorSuperOperacionesEspeciales = nombreAprobadorSuperOperacionesEspeciales;
	}

}