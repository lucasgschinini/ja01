package ar.com.telecom.shiva.persistencia.modelo.simple;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamEmpresa;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamSegmento;

/**
 * 
 * @author u564030
 *
 */
@Entity
@Table(name = "SHV_COB_ED_COBRO")
public class ShvCobEdCobroSimplificadoConWorkflow extends Modelo {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_COBRO", updatable = false)
	private Long idCobro;
	
	@Column(name = "ID_WORKFLOW", nullable = false)
	private Integer idWorkflow;
	
	@Column(name="ID_OPERACION", updatable = false)
	private Long idOperacion;
	
	@ManyToOne(cascade = CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_EMPRESA") 
	@NotFound(action=NotFoundAction.IGNORE)
	private ShvParamEmpresa empresa;

	@ManyToOne(cascade = CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_SEGMENTO")
	@NotFound(action=NotFoundAction.IGNORE)
	private ShvParamSegmento segmento;

	@Enumerated(EnumType.STRING)
	@Column(name="MONEDA_OPERACION")
	private MonedaEnum  monedaOperacion ;

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

	
	@Column(name="FECHA_ULTIMA_MODIFICACION")
	private Date fechaUltimaModificacion;

	@Column(name="USUARIO_ULTIMA_MODIFICACION")
	private String usuarioUltimaModificacion;
	
	// Aprobaciones Supervisor de Cobros
	
	@Column(name="FECHA_APROB_SUPER_COB")
	private Date fechaAprobacionSupervisorCobranza;

	@Column(name="USUARIO_APROB_SUPER_COB")
	private String usuarioAprobacionSupervisorCobranza;
	
	// Aprobaciones Referente de Cobranza

	@Column(name="FECHA_APROB_REFER_COB")
	private Date fechaAprobacionReferenteCobranza;

	@Column(name="USUARIO_APROB_REFER_COB")
	private String usuarioAprobacionReferenteCobranza;
	

	// Rechazo de aplicación manual
	
	@Column(name="FECHA_RECHAZA_APLIC_MANUAL")
	private Date fechaRechazoAplicacionManual;

	@Column(name="USUARIO_RECHAZA_APLIC_MANUAL")
	private String usuarioRechazoAplicacionManual;
	
	@Column(name="REFERENTE_RECHAZA_APLIC_MANUAL")
	private String referenteRechazoAplicacionManual;

	
	// Aprobaciones Referente de Caja

	@Column(name="REFERENTE_CAJA")
	private String nombreApellidoReferenteCaja;
	
	@Column(name="ID_REFERENTE_CAJA")
	private String idReferenteCaja;
	
	@Column(name="FECHA_REFERENTE_CAJA")
	private Date fechaReferenteCaja;
	
	// Aprobaciones Referente de Operaciones Externas
	
	@Column(name="REFERENTE_OPER_EXTERNA")
	private String nombreApellidoReferenteOperacionExterna;
	
	@Column(name="ID_REFERENTE_OPER_EXTERNA")
	private String idReferenteoperacionexterna;
	
	@Column(name="FECHA_REFERENTE_OPER_EXTERNA")
	private Date fechaReferenteOperacionExterna;

	@Column(name="OBSERVACIONES_APLIC_MANUAL")
	private String observacionesAplicacionesManual;
	
	/**
	 * @return the idCobro
	 */
	public Long getIdCobro() {
		return idCobro;
	}

	/**
	 * @param idCobro the idCobro to set
	 */
	public void setIdCobro(Long idCobro) {
		this.idCobro = idCobro;
	}

//	/**
//	 * @return the workflow
//	 */
//	public ShvWfWorkflow getWorkflow() {
//		return workflow;
//	}
//
//	/**
//	 * @param workflow the workflow to set
//	 */
//	public void setWorkflow(ShvWfWorkflow workflow) {
//		this.workflow = workflow;
//	}

	/**
	 * @return the idOperacion
	 */
	public Long getIdOperacion() {
		return idOperacion;
	}

	/**
	 * @param idOperacion the idOperacion to set
	 */
	public void setIdOperacion(Long idOperacion) {
		this.idOperacion = idOperacion;
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
	 * @return the monedaOperacion
	 */
	public MonedaEnum getMonedaOperacion() {
		return monedaOperacion;
	}

	/**
	 * @param monedaOperacion the monedaOperacion to set
	 */
	public void setMonedaOperacion(MonedaEnum monedaOperacion) {
		this.monedaOperacion = monedaOperacion;
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
	 * @return the fechaUltimaModificacion
	 */
	public Date getFechaUltimaModificacion() {
		return fechaUltimaModificacion;
	}

	/**
	 * @param fechaUltimaModificacion the fechaUltimaModificacion to set
	 */
	public void setFechaUltimaModificacion(Date fechaUltimaModificacion) {
		this.fechaUltimaModificacion = fechaUltimaModificacion;
	}

	/**
	 * @return the usuarioUltimaModificacion
	 */
	public String getUsuarioUltimaModificacion() {
		return usuarioUltimaModificacion;
	}

	/**
	 * @param usuarioUltimaModificacion the usuarioUltimaModificacion to set
	 */
	public void setUsuarioUltimaModificacion(String usuarioUltimaModificacion) {
		this.usuarioUltimaModificacion = usuarioUltimaModificacion;
	}

	/**
	 * @return the fechaAprobacionSupervisorCobranza
	 */
	public Date getFechaAprobacionSupervisorCobranza() {
		return fechaAprobacionSupervisorCobranza;
	}

	/**
	 * @param fechaAprobacionSupervisorCobranza the fechaAprobacionSupervisorCobranza to set
	 */
	public void setFechaAprobacionSupervisorCobranza(
			Date fechaAprobacionSupervisorCobranza) {
		this.fechaAprobacionSupervisorCobranza = fechaAprobacionSupervisorCobranza;
	}

	/**
	 * @return the usuarioAprobacionSupervisorCobranza
	 */
	public String getUsuarioAprobacionSupervisorCobranza() {
		return usuarioAprobacionSupervisorCobranza;
	}

	/**
	 * @param usuarioAprobacionSupervisorCobranza the usuarioAprobacionSupervisorCobranza to set
	 */
	public void setUsuarioAprobacionSupervisorCobranza(
			String usuarioAprobacionSupervisorCobranza) {
		this.usuarioAprobacionSupervisorCobranza = usuarioAprobacionSupervisorCobranza;
	}

	/**
	 * @return the fechaAprobacionReferenteCobranza
	 */
	public Date getFechaAprobacionReferenteCobranza() {
		return fechaAprobacionReferenteCobranza;
	}

	/**
	 * @param fechaAprobacionReferenteCobranza the fechaAprobacionReferenteCobranza to set
	 */
	public void setFechaAprobacionReferenteCobranza(
			Date fechaAprobacionReferenteCobranza) {
		this.fechaAprobacionReferenteCobranza = fechaAprobacionReferenteCobranza;
	}

	/**
	 * @return the usuarioAprobacionReferenteCobranza
	 */
	public String getUsuarioAprobacionReferenteCobranza() {
		return usuarioAprobacionReferenteCobranza;
	}

	/**
	 * @param usuarioAprobacionReferenteCobranza the usuarioAprobacionReferenteCobranza to set
	 */
	public void setUsuarioAprobacionReferenteCobranza(
			String usuarioAprobacionReferenteCobranza) {
		this.usuarioAprobacionReferenteCobranza = usuarioAprobacionReferenteCobranza;
	}

	/**
	 * @return the fechaRechazoAplicacionManual
	 */
	public Date getFechaRechazoAplicacionManual() {
		return fechaRechazoAplicacionManual;
	}

	/**
	 * @param fechaRechazoAplicacionManual the fechaRechazoAplicacionManual to set
	 */
	public void setFechaRechazoAplicacionManual(Date fechaRechazoAplicacionManual) {
		this.fechaRechazoAplicacionManual = fechaRechazoAplicacionManual;
	}

	/**
	 * @return the usuarioRechazoAplicacionManual
	 */
	public String getUsuarioRechazoAplicacionManual() {
		return usuarioRechazoAplicacionManual;
	}

	/**
	 * @param usuarioRechazoAplicacionManual the usuarioRechazoAplicacionManual to set
	 */
	public void setUsuarioRechazoAplicacionManual(
			String usuarioRechazoAplicacionManual) {
		this.usuarioRechazoAplicacionManual = usuarioRechazoAplicacionManual;
	}

	/**
	 * @return the nombreApellidoReferenteCaja
	 */
	public String getNombreApellidoReferenteCaja() {
		return nombreApellidoReferenteCaja;
	}

	/**
	 * @param nombreApellidoReferenteCaja the nombreApellidoReferenteCaja to set
	 */
	public void setNombreApellidoReferenteCaja(String nombreApellidoReferenteCaja) {
		this.nombreApellidoReferenteCaja = nombreApellidoReferenteCaja;
	}

	/**
	 * @return the idReferenteCaja
	 */
	public String getIdReferenteCaja() {
		return idReferenteCaja;
	}

	/**
	 * @param idReferenteCaja the idReferenteCaja to set
	 */
	public void setIdReferenteCaja(String idReferenteCaja) {
		this.idReferenteCaja = idReferenteCaja;
	}

	/**
	 * @return the fechaReferenteCaja
	 */
	public Date getFechaReferenteCaja() {
		return fechaReferenteCaja;
	}

	/**
	 * @param fechaReferenteCaja the fechaReferenteCaja to set
	 */
	public void setFechaReferenteCaja(Date fechaReferenteCaja) {
		this.fechaReferenteCaja = fechaReferenteCaja;
	}

	/**
	 * @return the nombreApellidoReferenteOperacionExterna
	 */
	public String getNombreApellidoReferenteOperacionExterna() {
		return nombreApellidoReferenteOperacionExterna;
	}

	/**
	 * @param nombreApellidoReferenteOperacionExterna the nombreApellidoReferenteOperacionExterna to set
	 */
	public void setNombreApellidoReferenteOperacionExterna(
			String nombreApellidoReferenteOperacionExterna) {
		this.nombreApellidoReferenteOperacionExterna = nombreApellidoReferenteOperacionExterna;
	}

	/**
	 * @return the idReferenteoperacionexterna
	 */
	public String getIdReferenteoperacionexterna() {
		return idReferenteoperacionexterna;
	}

	/**
	 * @param idReferenteoperacionexterna the idReferenteoperacionexterna to set
	 */
	public void setIdReferenteoperacionexterna(String idReferenteoperacionexterna) {
		this.idReferenteoperacionexterna = idReferenteoperacionexterna;
	}

	/**
	 * @return the fechaReferenteOperacionExterna
	 */
	public Date getFechaReferenteOperacionExterna() {
		return fechaReferenteOperacionExterna;
	}

	/**
	 * @param fechaReferenteOperacionExterna the fechaReferenteOperacionExterna to set
	 */
	public void setFechaReferenteOperacionExterna(
			Date fechaReferenteOperacionExterna) {
		this.fechaReferenteOperacionExterna = fechaReferenteOperacionExterna;
	}

	/**
	 * @return the idAnalista
	 */
	public String getIdAnalista() {
		return idAnalista;
	}

	/**
	 * @param idAnalista the idAnalista to set
	 */
	public void setIdAnalista(String idAnalista) {
		this.idAnalista = idAnalista;
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
	 * @return the idCopropietario
	 */
	public String getIdCopropietario() {
		return idCopropietario;
	}

	/**
	 * @param idCopropietario the idCopropietario to set
	 */
	public void setIdCopropietario(String idCopropietario) {
		this.idCopropietario = idCopropietario;
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
	public void setNombreApellidoAnalistaTeamComercial(
			String nombreApellidoAnalistaTeamComercial) {
		this.nombreApellidoAnalistaTeamComercial = nombreApellidoAnalistaTeamComercial;
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

	public String getObservacionesAplicacionesManual() {
		return observacionesAplicacionesManual;
	}

	public void setObservacionesAplicacionesManual(
			String observacionesAplicacionesManual) {
		this.observacionesAplicacionesManual = observacionesAplicacionesManual;
	}

	public String getReferenteRechazoAplicacionManual() {
		return referenteRechazoAplicacionManual;
	}

	public void setReferenteRechazoAplicacionManual(
			String referenteRechazoAplicacionManual) {
		this.referenteRechazoAplicacionManual = referenteRechazoAplicacionManual;
	}
}