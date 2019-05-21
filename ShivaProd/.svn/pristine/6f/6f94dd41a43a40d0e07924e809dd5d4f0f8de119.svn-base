package ar.com.telecom.shiva.persistencia.modelo;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import ar.com.telecom.shiva.base.enumeradores.ExcepcionMorosidadFechaEjecucionEnum;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoExcepcionEnum;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;

@Entity
@Table(name = "SHV_SOP_EXCEPCION_MOROSIDAD")
public class ShvSopExcepcionMorosidad extends Modelo {
	private static final long serialVersionUID = 20170418L;

	@Id
	@Column(name="ID_MOROSIDAD")
	private String idMorosidad;
	@Column(name="NUMERO_TRANSACCION_FORMATEADO")
	private String numeroTransaccionFormateado;
	@Column(name="id_cobro")
	private Long idCobro;
	@Column(name="id_operacion")
	private Long idOperacion;
	@Column(name="ID_FACTURA")
	private Long idFactura;
	@Column(name="ID_MEDIO_PAGO")
	private Long idMedioPago;
	@Column(name="ID_TRANSACCION")
	private Long idTransaccion;
	@Enumerated(EnumType.STRING)
	@Column(name="ESTADO_COBRO")
	private Estado estadoCobro;
	@Column(name="FECHA_MODIFICACION")
	@Type(type="timestamp")
	private Date fechaModificacionUltimoEstado;
	@Column(name="TIPO_MEDIO_PAGO")
	private String tipoMedioPago;
	@Column(name="ID_CLIENTE_LEGADO")
	private String cliente;
	@Column(name="CUENTA")
	private String cuenta;

//	// Atributos para notificar
	@Column(name="ACUERDO_FACTURACION")
	private String acuerdoFacturacion;
	@Enumerated(EnumType.STRING)
	@Column(name="SISTEMA_ORIGEN")
	private SistemaEnum sistemaOrigen;
	@Column(name="TIPO_DOCUMENTO")
	private String tipoDocumento;
	@Column(name="NUMERO_LEGAL_COMPROBANTE")
	private String numeroLegalComprobante;
	@Column(name="NUMERO_REFERENCIA")
	private String numeroReferencia;
	@Column(name="TIPO_REMANENTE")
	private Long tipoRemanente;
	@Column(name="FECHA_REMANENTE")
	private Date fechaRemanente;
	@Column(name="ID_VALOR")
	private String idValor;
	@Column(name="SOCIEDAD")
	private String sociedad;
	@Column(name="PROVEEDOR")
	private String proveedor;
	@Column(name="CUIT")
	private String cuit;
	@Column(name="EJERCICIO_FISCAL_SAP")
	private Integer ejercicioFiscalSAP;
	@Column(name="NUMERO_DOCUMENTO_SAP")
	private String numeroDocumentoSAP;
	@Column(name="POSICION_DOCUMENTO_SAP")
	private Integer posicionDocumentoSAP;
	@Enumerated(EnumType.STRING)
	@Column(name="MONEDA_ORIGEN_DOCUMENTO")
	private MonedaEnum monedaOrigenDocumento;
	@Column(name="IMP_EXCEP_MONEDA_ORIGEN_DOC")
	private BigDecimal impExcepMonedaOrigenDoc;
	@Column(name="REFERENCIA_EXCEPCION")
	private Long Referencia_excepcion;
	@Column(name="ID_ANALISTA")
	private String idAnalista;
	@Enumerated(EnumType.STRING)
	@Column(name="TIPO_EXCEPCION")
	private TipoExcepcionEnum tipoExcepcion;

	@Column(name="FECHA_CREACION")
	private Date fechaCreacion;

	// Parametrica de Parametrica de Exceo
	//private SiNoEnum 
	@Enumerated(EnumType.STRING)
	@Column(name="FECHA_DESDE_ENUM")
	private ExcepcionMorosidadFechaEjecucionEnum fechaDesdeEnum;
	@Column(name="FECHA_DERIVACION")
	private Date fechaFerivacion;
	@Column(name="FECHA_VENCIMIENTO")
	private Date fechaVencimiento;
	@Column(name="DIAS_DURACION_EXCEPCION")
	private Integer diasDuracionExcepcion;
	@Enumerated(EnumType.STRING)
	@Column(name="DIAS_CORRIDOS_SIN_FERIADOS")
	private SiNoEnum diasCorridosSinFeriados;
	
	
	
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
	 * @return the idFactura
	 */
	public Long getIdFactura() {
		return idFactura;
	}
	/**
	 * @param idFactura the idFactura to set
	 */
	public void setIdFactura(Long idFactura) {
		this.idFactura = idFactura;
	}
	/**
	 * @return the idMedioPago
	 */
	public Long getIdMedioPago() {
		return idMedioPago;
	}
	/**
	 * @param idMedioPago the idMedioPago to set
	 */
	public void setIdMedioPago(Long idMedioPago) {
		this.idMedioPago = idMedioPago;
	}
	/**
	 * @return the idTransaccion
	 */
	public Long getIdTransaccion() {
		return idTransaccion;
	}
	/**
	 * @param idTransaccion the idTransaccion to set
	 */
	public void setIdTransaccion(Long idTransaccion) {
		this.idTransaccion = idTransaccion;
	}
	/**
	 * @return the estadoCobro
	 */
	public Estado getEstadoCobro() {
		return estadoCobro;
	}
	/**
	 * @param estadoCobro the estadoCobro to set
	 */
	public void setEstadoCobro(Estado estadoCobro) {
		this.estadoCobro = estadoCobro;
	}
	/**
	 * @return the fechaModificacionUltimoEstado
	 */
	public Date getFechaModificacionUltimoEstado() {
		return fechaModificacionUltimoEstado;
	}
	/**
	 * @param fechaModificacionUltimoEstado the fechaModificacionUltimoEstado to set
	 */
	public void setFechaModificacionUltimoEstado(Date fechaModificacionUltimoEstado) {
		this.fechaModificacionUltimoEstado = fechaModificacionUltimoEstado;
	}
	/**
	 * @return the tipoMedioPago
	 */
	public String getTipoMedioPago() {
		return tipoMedioPago;
	}
	/**
	 * @param tipoMedioPago the tipoMedioPago to set
	 */
	public void setTipoMedioPago(String tipoMedioPago) {
		this.tipoMedioPago = tipoMedioPago;
	}
	/**
	 * @return the cliente
	 */
	public String getCliente() {
		return cliente;
	}
	/**
	 * @param cliente the cliente to set
	 */
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	/**
	 * @return the cuenta
	 */
	public String getCuenta() {
		return cuenta;
	}
	/**
	 * @param cuenta the cuenta to set
	 */
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}
	/**
	 * @return the acuerdoFacturacion
	 */
	public String getAcuerdoFacturacion() {
		return acuerdoFacturacion;
	}
	/**
	 * @param acuerdoFacturacion the acuerdoFacturacion to set
	 */
	public void setAcuerdoFacturacion(String acuerdoFacturacion) {
		this.acuerdoFacturacion = acuerdoFacturacion;
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
	 * @return the tipoDocumento
	 */
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	/**
	 * @param tipoDocumento the tipoDocumento to set
	 */
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	/**
	 * @return the numeroLegalComprobante
	 */
	public String getNumeroLegalComprobante() {
		return numeroLegalComprobante;
	}
	/**
	 * @param numeroLegalComprobante the numeroLegalComprobante to set
	 */
	public void setNumeroLegalComprobante(String numeroLegalComprobante) {
		this.numeroLegalComprobante = numeroLegalComprobante;
	}
	/**
	 * @return the numeroReferencia
	 */
	public String getNumeroReferencia() {
		return numeroReferencia;
	}
	/**
	 * @param numeroReferencia the numeroReferencia to set
	 */
	public void setNumeroReferencia(String numeroReferencia) {
		this.numeroReferencia = numeroReferencia;
	}
	/**
	 * @return the tipoRemanente
	 */
	public Long getTipoRemanente() {
		return tipoRemanente;
	}
	/**
	 * @param tipoRemanente the tipoRemanente to set
	 */
	public void setTipoRemanente(Long tipoRemanente) {
		this.tipoRemanente = tipoRemanente;
	}
	/**
	 * @return the fechaRemanente
	 */
	public Date getFechaRemanente() {
		return fechaRemanente;
	}
	/**
	 * @param fechaRemanente the fechaRemanente to set
	 */
	public void setFechaRemanente(Date fechaRemanente) {
		this.fechaRemanente = fechaRemanente;
	}
	/**
	 * @return the idValor
	 */
	public String getIdValor() {
		return idValor;
	}
	/**
	 * @param idValor the idValor to set
	 */
	public void setIdValor(String idValor) {
		this.idValor = idValor;
	}
	/**
	 * @return the sociedad
	 */
	public String getSociedad() {
		return sociedad;
	}
	/**
	 * @param sociedad the sociedad to set
	 */
	public void setSociedad(String sociedad) {
		this.sociedad = sociedad;
	}
	/**
	 * @return the proveedor
	 */
	public String getProveedor() {
		return proveedor;
	}
	/**
	 * @param proveedor the proveedor to set
	 */
	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
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
	 * @return the ejercicioFiscalSAP
	 */
	public Integer getEjercicioFiscalSAP() {
		return ejercicioFiscalSAP;
	}
	/**
	 * @param ejercicioFiscalSAP the ejercicioFiscalSAP to set
	 */
	public void setEjercicioFiscalSAP(Integer ejercicioFiscalSAP) {
		this.ejercicioFiscalSAP = ejercicioFiscalSAP;
	}
	/**
	 * @return the numeroDocumentoSAP
	 */
	public String getNumeroDocumentoSAP() {
		return numeroDocumentoSAP;
	}
	/**
	 * @param numeroDocumentoSAP the numeroDocumentoSAP to set
	 */
	public void setNumeroDocumentoSAP(String numeroDocumentoSAP) {
		this.numeroDocumentoSAP = numeroDocumentoSAP;
	}
	/**
	 * @return the posicionDocumentoSAP
	 */
	public Integer getPosicionDocumentoSAP() {
		return posicionDocumentoSAP;
	}
	/**
	 * @param posicionDocumentoSAP the posicionDocumentoSAP to set
	 */
	public void setPosicionDocumentoSAP(Integer posicionDocumentoSAP) {
		this.posicionDocumentoSAP = posicionDocumentoSAP;
	}
	/**
	 * @return the monedaOrigenDocumento
	 */
	public MonedaEnum getMonedaOrigenDocumento() {
		return monedaOrigenDocumento;
	}
	/**
	 * @param monedaOrigenDocumento the monedaOrigenDocumento to set
	 */
	public void setMonedaOrigenDocumento(MonedaEnum monedaOrigenDocumento) {
		this.monedaOrigenDocumento = monedaOrigenDocumento;
	}
	/**
	 * @return the impExcepMonedaOrigenDoc
	 */
	public BigDecimal getImpExcepMonedaOrigenDoc() {
		return impExcepMonedaOrigenDoc;
	}
	/**
	 * @param impExcepMonedaOrigenDoc the impExcepMonedaOrigenDoc to set
	 */
	public void setImpExcepMonedaOrigenDoc(BigDecimal impExcepMonedaOrigenDoc) {
		this.impExcepMonedaOrigenDoc = impExcepMonedaOrigenDoc;
	}
	/**
	 * @return the referencia_excepcion
	 */
	public Long getReferencia_excepcion() {
		return Referencia_excepcion;
	}
	/**
	 * @param referencia_excepcion the referencia_excepcion to set
	 */
	public void setReferencia_excepcion(Long referencia_excepcion) {
		Referencia_excepcion = referencia_excepcion;
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
	 * @return the tipoExcepcion
	 */
	public TipoExcepcionEnum getTipoExcepcion() {
		return tipoExcepcion;
	}
	/**
	 * @param tipoExcepcion the tipoExcepcion to set
	 */
	public void setTipoExcepcion(TipoExcepcionEnum tipoExcepcion) {
		this.tipoExcepcion = tipoExcepcion;
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
	 * @return the fechaDesdeEnum
	 */
	public ExcepcionMorosidadFechaEjecucionEnum getFechaDesdeEnum() {
		return fechaDesdeEnum;
	}
	/**
	 * @param fechaDesdeEnum the fechaDesdeEnum to set
	 */
	public void setFechaDesdeEnum(
			ExcepcionMorosidadFechaEjecucionEnum fechaDesdeEnum) {
		this.fechaDesdeEnum = fechaDesdeEnum;
	}
	/**
	 * @return the fechaFerivacion
	 */
	public Date getFechaFerivacion() {
		return fechaFerivacion;
	}
	/**
	 * @param fechaFerivacion the fechaFerivacion to set
	 */
	public void setFechaFerivacion(Date fechaFerivacion) {
		this.fechaFerivacion = fechaFerivacion;
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
	 * @return the diasDuracionExcepcion
	 */
	public Integer getDiasDuracionExcepcion() {
		return diasDuracionExcepcion;
	}
	/**
	 * @param diasDuracionExcepcion the diasDuracionExcepcion to set
	 */
	public void setDiasDuracionExcepcion(Integer diasDuracionExcepcion) {
		this.diasDuracionExcepcion = diasDuracionExcepcion;
	}
	/**
	 * @return the diasCorridosSinFeriados
	 */
	public SiNoEnum getDiasCorridosSinFeriados() {
		return diasCorridosSinFeriados;
	}
	/**
	 * @param diasCorridosSinFeriados the diasCorridosSinFeriados to set
	 */
	public void setDiasCorridosSinFeriados(SiNoEnum diasCorridosSinFeriados) {
		this.diasCorridosSinFeriados = diasCorridosSinFeriados;
	}
	/**
	 * @return the numeroTransaccionFormateado
	 */
	public String getNumeroTransaccionFormateado() {
		return numeroTransaccionFormateado;
	}
	/**
	 * @param numeroTransaccionFormateado the numeroTransaccionFormateado to set
	 */
	public void setNumeroTransaccionFormateado(String numeroTransaccionFormateado) {
		this.numeroTransaccionFormateado = numeroTransaccionFormateado;
	}
	
}