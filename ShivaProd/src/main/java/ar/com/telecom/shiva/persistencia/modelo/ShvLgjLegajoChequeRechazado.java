package ar.com.telecom.shiva.persistencia.modelo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import ar.com.telecom.shiva.base.comparador.ComparatorShvLgjNotificacion;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoChequeEnum;
import ar.com.telecom.shiva.base.enumeradores.UbicacionChequeEnum;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamAcuerdo;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamBanco;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamBancoCuenta;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamEmpresa;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamMotivoLegajo;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamSegmento;


@Entity
@Table(name = "SHV_LGJ_LEGAJO_CHEQUE_RCHAZADO")
public class ShvLgjLegajoChequeRechazado extends Modelo {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SHV_LGJ_LEG_CHEQ_RCHAZADO")
	@SequenceGenerator(name = "SEQ_SHV_LGJ_LEG_CHEQ_RCHAZADO", sequenceName = "SEQ_SHV_LGJ_LEG_CHEQ_RCHAZADO", allocationSize = 1)
	@Column(name="ID_LEGAJO_CHEQUE_RECHAZADO", updatable = false)
	private Long idLegajoChequeRechazado;
	
	@OneToOne(fetch = FetchType.EAGER, cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
	@JoinColumn(name="ID_CHEQUE_RECHAZADO", referencedColumnName="ID_CHEQUE_RECHAZADO")
	private ShvLgjChequeRechazado chequeRechazado;
	
	@ManyToOne(cascade = CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_EMPRESA") 
	private ShvParamEmpresa empresa;
	
	@ManyToOne(cascade = CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_SEGMENTO")
	private ShvParamSegmento segmento;

	@Column(name="ID_ANALISTA")
	private String idAnalista;
	
	@Column(name="ANALISTA")
	private String analista;

	@Column(name="ID_COPROPIETARIO")
	private String idCopropietario;

	@Column(name="COPROPIETARIO")
	private String copropietario;

	@Column(name="ID_ANALISTA_TEAM_COMERCIAL")
	private String idAnalistaTeamComercial;
	
	@Column(name="ANALISTA_TEAM_COMERCIAL")
	private String analistaTeamComercial;

	@Column(name="FECHA_NOTIFICACION")
	private Date fechaNotificacion;
	
	@ManyToOne(cascade = CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_MOTIVO_LEGAJO")
	private ShvParamMotivoLegajo motivoLegajo;

	@Column(name="OBSERVACIONES")
	private String observaciones;

	@Enumerated(EnumType.STRING)
	@Column(name="UBICACION")
	private UbicacionChequeEnum ubicacion;

	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE }, fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_WORKFLOW", referencedColumnName = "ID_WORKFLOW", nullable = false)
	private ShvWfWorkflow workflow;

	@Column(name="FECHA_RECHAZO")
	private Date fechaRechazo;
	
	@ManyToOne(cascade = CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_BANCO_ORIGEN")
	private ShvParamBanco bancoOrigen;
	
	@Enumerated(EnumType.STRING)
	@Column(name="TIPO_CHEQUE")
	private TipoChequeEnum tipoCheque;
	
	@Column(name="NUMERO_CHEQUE")
	private Long numeroCheque;
	
	@Column(name="FECHA_VENCIMIENTO")	
	private Date fechaVencimiento;

	@Enumerated(EnumType.STRING)
	@Column(name="MONEDA")  
	private MonedaEnum moneda;

	@Column(name="IMPORTE")
	private BigDecimal importe;

	@ManyToOne(cascade = CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_ACUERDO") 
	private ShvParamAcuerdo acuerdo;
	
	@Column(name="FECHA_DEPOSITO")
	private Date fechaDeposito;

	@Enumerated(EnumType.STRING)
	@Column(name="SISTEMA_ORIGEN")
	private SistemaEnum sistemaOrigen;

	@ManyToOne(cascade = CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_BANCO_DEPOSITO") 
	private ShvParamBanco bancoDeposito;
	
	@ManyToOne(cascade = CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_BANCO_CUENTA") 
	private ShvParamBancoCuenta cuentaDeposito;
	
	@Column(name="FECHA_ALTA")
	private Date fechaAlta;

	@OneToMany(cascade={CascadeType.DETACH, CascadeType.MERGE}, fetch=FetchType.EAGER, mappedBy = "legajoChequeRechazado")
	@NotFound(action=NotFoundAction.IGNORE)
	private Set<ShvLgjNotificacion> notificaciones = new TreeSet<ShvLgjNotificacion>(new ComparatorShvLgjNotificacion());
	
	@Column(name="FECHA_EMISION")
	private Date fechaEmision;
	
	@Column(name="FECHA_CIERRE")
	private Date fechaCierre;
	
	public ShvLgjLegajoChequeRechazado(){
	}

	/**
	 * @return the idLegajoChequeRechazado
	 */
	public Long getIdLegajoChequeRechazado() {
		return idLegajoChequeRechazado;
	}

	/**
	 * @param idLegajoChequeRechazado the idLegajoChequeRechazado to set
	 */
	public void setIdLegajoChequeRechazado(Long idLegajoChequeRechazado) {
		this.idLegajoChequeRechazado = idLegajoChequeRechazado;
	}

	/**
	 * @return the chequeRechazado
	 */
	public ShvLgjChequeRechazado getChequeRechazado() {
		return chequeRechazado;
	}

	/**
	 * @param chequeRechazado the chequeRechazado to set
	 */
	public void setChequeRechazado(ShvLgjChequeRechazado chequeRechazado) {
		this.chequeRechazado = chequeRechazado;
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
	 * @return the analista
	 */
	public String getAnalista() {
		return analista;
	}

	/**
	 * @param analista the analista to set
	 */
	public void setAnalista(String analista) {
		this.analista = analista;
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
	 * @return the copropietario
	 */
	public String getCopropietario() {
		return copropietario;
	}

	/**
	 * @param copropietario the copropietario to set
	 */
	public void setCopropietario(String copropietario) {
		this.copropietario = copropietario;
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
	 * @return the analistaTeamComercial
	 */
	public String getAnalistaTeamComercial() {
		return analistaTeamComercial;
	}

	/**
	 * @param analistaTeamComercial the analistaTeamComercial to set
	 */
	public void setAnalistaTeamComercial(String analistaTeamComercial) {
		this.analistaTeamComercial = analistaTeamComercial;
	}

	/**
	 * @return the fechaNotificacion
	 */
	public Date getFechaNotificacion() {
		return fechaNotificacion;
	}

	/**
	 * @param fechaNotificacion the fechaNotificacion to set
	 */
	public void setFechaNotificacion(Date fechaNotificacion) {
		this.fechaNotificacion = fechaNotificacion;
	}

	/**
	 * @return the motivoLegajo
	 */
	public ShvParamMotivoLegajo getMotivoLegajo() {
		return motivoLegajo;
	}

	/**
	 * @param motivoLegajo the motivoLegajo to set
	 */
	public void setMotivoLegajo(ShvParamMotivoLegajo motivoLegajo) {
		this.motivoLegajo = motivoLegajo;
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
	 * @return the ubicacion
	 */
	public UbicacionChequeEnum getUbicacion() {
		return ubicacion;
	}

	/**
	 * @param ubicacion the ubicacion to set
	 */
	public void setUbicacion(UbicacionChequeEnum ubicacion) {
		this.ubicacion = ubicacion;
	}

	/**
	 * @return the workflow
	 */
	public ShvWfWorkflow getWorkflow() {
		return workflow;
	}

	/**
	 * @param workflow the workflow to set
	 */
	public void setWorkflow(ShvWfWorkflow workflow) {
		this.workflow = workflow;
	}

	/**
	 * @return the fechaRechazo
	 */
	public Date getFechaRechazo() {
		return fechaRechazo;
	}

	/**
	 * @param fechaRechazo the fechaRechazo to set
	 */
	public void setFechaRechazo(Date fechaRechazo) {
		this.fechaRechazo = fechaRechazo;
	}

	/**
	 * @return the bancoOrigen
	 */
	public ShvParamBanco getBancoOrigen() {
		return bancoOrigen;
	}

	/**
	 * @param bancoOrigen the bancoOrigen to set
	 */
	public void setBancoOrigen(ShvParamBanco bancoOrigen) {
		this.bancoOrigen = bancoOrigen;
	}

	/**
	 * @return the tipoCheque
	 */
	public TipoChequeEnum getTipoCheque() {
		return tipoCheque;
	}

	/**
	 * @param tipoCheque the tipoCheque to set
	 */
	public void setTipoCheque(TipoChequeEnum tipoCheque) {
		this.tipoCheque = tipoCheque;
	}

	/**
	 * @return the numeroCheque
	 */
	public Long getNumeroCheque() {
		return numeroCheque;
	}

	/**
	 * @param numeroCheque the numeroCheque to set
	 */
	public void setNumeroCheque(Long numeroCheque) {
		this.numeroCheque = numeroCheque;
	}

	/**
	 * @return the fechaVencimiento
	 */
	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	/**
	 * @param fechaVencimiento the fechaVencimiento to set
	 */
	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	/**
	 * @return the moneda
	 */
	public MonedaEnum getMoneda() {
		return moneda;
	}

	/**
	 * @param moneda the moneda to set
	 */
	public void setMoneda(MonedaEnum moneda) {
		this.moneda = moneda;
	}

	/**
	 * @return the importe
	 */
	public BigDecimal getImporte() {
		return importe;
	}

	/**
	 * @param importe the importe to set
	 */
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	/**
	 * @return the idAcuerdo
	 */
	public ShvParamAcuerdo getAcuerdo() {
		return acuerdo;
	}

	/**
	 * @param idAcuerdo the idAcuerdo to set
	 */
	public void setAcuerdo(ShvParamAcuerdo acuerdo) {
		this.acuerdo = acuerdo;
	}

	/**
	 * @return the fechaDeposito
	 */
	public Date getFechaDeposito() {
		return fechaDeposito;
	}

	/**
	 * @param fechaDeposito the fechaDeposito to set
	 */
	public void setFechaDeposito(Date fechaDeposito) {
		this.fechaDeposito = fechaDeposito;
	}

	/**
	 * @return the sistemaOrigen
	 */
	public SistemaEnum getSistemaOrigen() {
		return sistemaOrigen;
	}

	/**
	 * @param sistemaOrigen the sistemaOrigen to set
	 */
	public void setSistemaOrigen(SistemaEnum sistemaOrigen) {
		this.sistemaOrigen = sistemaOrigen;
	}

	/**
	 * @return the bancoDeposito
	 */
	public ShvParamBanco getBancoDeposito() {
		return bancoDeposito;
	}

	/**
	 * @param bancoDeposito the bancoDeposito to set
	 */
	public void setBancoDeposito(ShvParamBanco bancoDeposito) {
		this.bancoDeposito = bancoDeposito;
	}

	/**
	 * @return the cuentaDeposito
	 */
	public ShvParamBancoCuenta getCuentaDeposito() {
		return cuentaDeposito;
	}

	/**
	 * @param cuentaDeposito the cuentaDeposito to set
	 */
	public void setCuentaDeposito(ShvParamBancoCuenta cuentaDeposito) {
		this.cuentaDeposito = cuentaDeposito;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	/**
	 * @return the notificaciones
	 */
	public Set<ShvLgjNotificacion> getNotificaciones() {
		return notificaciones;
	}

	/**
	 * @param notificaciones the notificaciones to set
	 */
	public void setNotificaciones(Set<ShvLgjNotificacion> notificaciones) {
		this.notificaciones = notificaciones;
	}

	/**
	 * @return the fechaEmision
	 */
	public Date getFechaEmision() {
		return fechaEmision;
	}

	/**
	 * @param fechaEmision the fechaEmision to set
	 */
	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public Date getFechaCierre() {
		return fechaCierre;
	}

	public void setFechaCierre(Date fechaCierre) {
		this.fechaCierre = fechaCierre;
	}
	
	
}