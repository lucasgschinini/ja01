package ar.com.telecom.shiva.persistencia.modelo;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamAcuerdo;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamBanco;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamTipoCliente;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamTipoMedioPago;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamTipoOrigenContable;

@Entity
@Table(name="SHV_CNT_CONTABILIDAD_DETALLE")
public class ShvCntContabilidadDetalle {


	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_SHV_CNT_CONTABILIDAD_DETAL")
	@SequenceGenerator(name="SEQ_SHV_CNT_CONTABILIDAD_DETAL", sequenceName="SEQ_SHV_CNT_CONTABILIDAD_DETAL", allocationSize = 1)
	@Column (name="ID_CONTABILIDAD_DETALLE")
	private	Long idContabilidadDetalle;
	
	@ManyToOne(cascade = CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_TIPO_ORIGEN_CONTABLE")
	private ShvParamTipoOrigenContable idTipoOrigenContable; 
	
	@ManyToOne
	@JoinColumn(name="ID_TIPO_MEDIO_PAGO", referencedColumnName="ID_TIPO_MEDIO_PAGO", updatable=false)
	@NotFound(action=NotFoundAction.IGNORE)
	private ShvParamTipoMedioPago idTipoMedioPago;
	
	@ManyToOne
	@JoinColumn(name="ID_TIPO_CLIENTE", referencedColumnName="ID_TIPO_CLIENTE", updatable=false)
	@NotFound(action=NotFoundAction.IGNORE)
	private ShvParamTipoCliente idTipoCliente;
	
	@Column (name="ID_JURISDICCION")
	@NotFound(action=NotFoundAction.IGNORE)
	private String idJurisdiccion;
	
	@Column (name="CODIGO_CLIENTE_LEGADO")
	private Long codigoClienteLegado; 
	
	@ManyToOne(cascade = CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_BANCO")
	@NotFound(action=NotFoundAction.IGNORE)
	private ShvParamBanco idBanco;
	
	@ManyToOne(cascade = CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_ACUERDO")
	@NotFound(action=NotFoundAction.IGNORE)
	private ShvParamAcuerdo idAcuerdo;
	
	@Column (name="ID_ANALISTA")
	private String idAnalista;
	
	@Column (name="IMPORTE")
	private BigDecimal importe;
	
	@Column (name="MONEDA")
	private String moneda;
	
	@Column (name="FECHA_VALOR")
	@NotFound(action=NotFoundAction.IGNORE)
	private Date fechaValor;
	
	@Column (name="FECHA_VENCIMIENTO")
	@NotFound(action=NotFoundAction.IGNORE)
	private Date fechaVencimiento;
	
	@Column (name="TRANSACCION")
	private String transaccion;
	
	@Column (name="NUMERO_BOLETA")
	@NotFound(action=NotFoundAction.IGNORE)
	private Long numeroBoleta;
	
	@Column (name="NUMERO_COMPROBANTE")
	@NotFound(action=NotFoundAction.IGNORE)
	private String numeroComprobante;
	
	//U562163 - IM0552654 - se elimina la FK
//	@ManyToOne(cascade = CascadeType.DETACH, fetch=FetchType.EAGER)
	@Column(name="ID_ORGANISMO")
	@NotFound(action=NotFoundAction.IGNORE)
	private String idOrganismo;
	
	@Column (name="TIPO_DE_CAMBIO")
	@NotFound(action=NotFoundAction.IGNORE)
	private String tipoDeCambio;
	
	@Column (name="CUIT")
	@NotFound(action=NotFoundAction.IGNORE)
	private String cuit;
	
	@Column (name="NUMERO_DOCUMENTO_CONTABLE")
	@NotFound(action=NotFoundAction.IGNORE)
	private Long numeroDocumentoContable;
	
	@Column (name="ID_CAJA")
	@NotFound(action=NotFoundAction.IGNORE)
	private String idCaja;
	
	@Column (name="ESTADO")
	private String estado;
	
	@Column (name="FECHA_CREACION")
	private Date fechaCreacion;
	
	@Column (name="FECHA_PROCESAMIENTO")
	private Date fechaProcesamiento;
	
	@Column (name="ID_VALOR")
	private Long idValor;
	
	@Column (name="ID_TRANSACCION")
	private Long idTransaccion;
	
	@Column (name="ID_COBRO")
	private Long IdCobro;
	
	@Column (name="ESTADO_INACTIVIDAD")
	private String estadoInactividad;
	
	@ManyToOne(cascade = CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_CONTABILIDAD_CABECERA")
	@NotFound(action=NotFoundAction.IGNORE)
	private ShvCntContabilidadCabecera idContabilidadCabecera;
	
	@Transient
	private boolean acreditado = true;
	
	/**
	 * @return the idContabilidadDetalle
	 */
	public Long getIdContabilidadDetalle() {
		return idContabilidadDetalle;
	}
	/**
	 * @param idContabilidadDetalle the idContabilidadDetalle to set
	 */
	public void setIdContabilidadDetalle(Long idContabilidadDetalle) {
		this.idContabilidadDetalle = idContabilidadDetalle;
	}
	
	/**
	 * @return the idTipoOrigenContable
	 */
	public ShvParamTipoOrigenContable getIdTipoOrigenContable() {
		return idTipoOrigenContable;
	}
	/**
	 * @param idTipoOrigenContable the idTipoOrigenContable to set
	 */
	public void setIdTipoOrigenContable(
			ShvParamTipoOrigenContable idTipoOrigenContable) {
		this.idTipoOrigenContable = idTipoOrigenContable;
	}
	
	/**
	 * @return the idTipoMedioPago
	 */
	public ShvParamTipoMedioPago getIdTipoMedioPago() {
		return idTipoMedioPago;
	}
	/**
	 * @param idTipoMedioPago the idTipoMedioPago to set
	 */
	public void setIdTipoMedioPago(ShvParamTipoMedioPago idTipoMedioPago) {
		this.idTipoMedioPago = idTipoMedioPago;
	}
	
	
	/**
	 * @return the idTipoCliente
	 */
	public ShvParamTipoCliente getIdTipoCliente() {
		return idTipoCliente;
	}
	/**
	 * @param idTipoCliente the idTipoCliente to set
	 */
	public void setIdTipoCliente(ShvParamTipoCliente idTipoCliente) {
		this.idTipoCliente = idTipoCliente;
	}

	/**
	 * @return the idJurisdiccion
	 */
	public String getIdJurisdiccion() {
		return idJurisdiccion;
	}
	/**
	 * @param idJurisdiccion the idJurisdiccion to set
	 */
	public void setIdJurisdiccion(String idJurisdiccion) {
		this.idJurisdiccion = idJurisdiccion;
	}
	/**
	 * @return the codigoClienteLegado
	 */
	public Long getCodigoClienteLegado() {
		return codigoClienteLegado;
	}
	/**
	 * @param codigoClienteLegado the codigoClienteLegado to set
	 */
	public void setCodigoClienteLegado(Long codigoClienteLegado) {
		this.codigoClienteLegado = codigoClienteLegado;
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
	 * @return the moneda
	 */
	public String getMoneda() {
		return moneda;
	}
	/**
	 * @param moneda the moneda to set
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}
	/**
	 * @return the fechaValor
	 */
	public Date getFechaValor() {
		return fechaValor;
	}
	/**
	 * @param fechaValor the fechaValor to set
	 */
	public void setFechaValor(Date fechaValor) {
		this.fechaValor = fechaValor;
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
	 * @return the transaccion
	 */
	public String getTransaccion() {
		return transaccion;
	}
	/**
	 * @param transaccion the transaccion to set
	 */
	public void setTransaccion(String transaccion) {
		this.transaccion = transaccion;
	}
	/**
	 * @return the numeroBoleta
	 */
	public Long getNumeroBoleta() {
		return numeroBoleta;
	}
	/**
	 * @param numeroBoleta the numeroBoleta to set
	 */
	public void setNumeroBoleta(Long numeroBoleta) {
		this.numeroBoleta = numeroBoleta;
	}
	/**
	 * @return the numeroComprobante
	 */
	public String getNumeroComprobante() {
		return numeroComprobante;
	}
	/**
	 * @param numeroComprobante the numeroComprobante to set
	 */
	public void setNumeroComprobante(String numeroComprobante) {
		this.numeroComprobante = numeroComprobante;
	}
	
	/**
	 * @return the idOrganismo
	 */
	public String getIdOrganismo() {
		return idOrganismo;
	}
	/**
	 * @param idOrganismo the idOrganismo to set
	 */
	public void setIdOrganismo(String idOrganismo) {
		this.idOrganismo = idOrganismo;
	}
	/**
	 * @return the tipoDeCambio
	 */
	public String getTipoDeCambio() {
		return tipoDeCambio;
	}
	/**
	 * @param tipoDeCambio the tipoDeCambio to set
	 */
	public void setTipoDeCambio(String tipoDeCambio) {
		this.tipoDeCambio = tipoDeCambio;
	}
	/**
	 * @return the cuit
	 */
	public String getCuit() {
		return cuit;
	}
	/**
	 * @param cuit the cuit to set
	 */
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}
	/**
	 * @return the numeroDocumenoContable
	 */
	public Long getNumeroDocumentoContable() {
		return numeroDocumentoContable;
	}
	/**
	 * @param numeroDocumentoContable the numeroDocumenoContable to set
	 */
	public void setNumeroDocumentoContable(Long numeroDocumentoContable) {
		this.numeroDocumentoContable = numeroDocumentoContable;
	}
	/**
	 * @return the idCaja
	 */
	public String getIdCaja() {
		return idCaja;
	}
	/**
	 * @param idCaja the idCaja to set
	 */
	public void setIdCaja(String idCaja) {
		this.idCaja = idCaja;
	}
	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}
	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
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
	 * @return the fechaProcesamiento
	 */
	public Date getFechaProcesamiento() {
		return fechaProcesamiento;
	}
	/**
	 * @param fechaProcesamiento the fechaProcesamiento to set
	 */
	public void setFechaProcesamiento(Date fechaProcesamiento) {
		this.fechaProcesamiento = fechaProcesamiento;
	}
	
	/**
	 * @return the idContabilidadCabecera
	 */
	public ShvCntContabilidadCabecera getIdContabilidadCabecera() {
		return idContabilidadCabecera;
	}
	/**
	 * @param idContabilidadCabecera the idContabilidadCabecera to set
	 */
	public void setIdContabilidadCabecera(
			ShvCntContabilidadCabecera idContabilidadCabecera) {
		this.idContabilidadCabecera = idContabilidadCabecera;
	}
	/**
	 * @return the idBanco
	 */
	public ShvParamBanco getIdBanco() {
		return idBanco;
	}
	/**
	 * @param idBanco the idBanco to set
	 */
	public void setIdBanco(ShvParamBanco idBanco) {
		this.idBanco = idBanco;
	}
	/**
	 * @return the idAcuerdo
	 */
	public ShvParamAcuerdo getIdAcuerdo() {
		return idAcuerdo;
	}
	/**
	 * @param idAcuerdo the idAcuerdo to set
	 */
	public void setIdAcuerdo(ShvParamAcuerdo idAcuerdo) {
		this.idAcuerdo = idAcuerdo;
	}
	public boolean isAcreditado() {
		return acreditado;
	}
	public void setAcreditado(boolean acreditado) {
		this.acreditado = acreditado;
	}
	public Long getIdValor() {
		return idValor;
	}
	public void setIdValor(Long idValor) {
		this.idValor = idValor;
	}
	public Long getIdTransaccion() {
		return idTransaccion;
	}
	public void setIdTransaccion(Long idTransaccion) {
		this.idTransaccion = idTransaccion;
	}
	public Long getIdCobro() {
		return IdCobro;
	}
	public void setIdCobro(Long idCobro) {
		IdCobro = idCobro;
	}
	public String getEstadoInactividad() {
		return estadoInactividad;
	}
	public void setEstadoInactividad(String estadoInactividad) {
		this.estadoInactividad = estadoInactividad;
	}

	
}
