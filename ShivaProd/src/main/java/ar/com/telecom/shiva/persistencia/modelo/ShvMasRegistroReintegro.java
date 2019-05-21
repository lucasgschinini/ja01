package ar.com.telecom.shiva.persistencia.modelo;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import ar.com.telecom.shiva.base.enumeradores.EstadoAcuerdoFacturacionEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoOrigenEnum;
import ar.com.telecom.shiva.base.enumeradores.MarcaCyQEnum;
import ar.com.telecom.shiva.base.enumeradores.MarcaReclamoEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoClaseComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoFacturaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoOrigenEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoReintegroEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoRemanenteEnum;

@Entity
@Table(name = "SHV_MAS_REGISTRO_REINTEGRO")
@PrimaryKeyJoinColumn(name="ID_REGISTRO")
@Inheritance(strategy=InheritanceType.JOINED)
public class ShvMasRegistroReintegro extends ShvMasRegistro {

	private static final long serialVersionUID = 1L;
	
	@Column(name="CUENTA") 			
	private Long cuenta;
	
	@Enumerated (EnumType.STRING)
	@Column(name="TIPO_REMANENTE") 			
	private TipoOrigenEnum tipoRemanente;
	
	@Column(name="NUMERO_REFERENCIA_NC")
	private Long numeroReferenciaNC;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "CLASE_COMPROBANTE", nullable=true)
	private TipoClaseComprobanteEnum claseComprobante;

	@Column(name = "SUCURSAL_COMPROBANTE")
	private String sucursalComprobante;

	@Column(name = "NUMERO_COMPROBANTE")
	private String numeroComprobante;

	@Enumerated (EnumType.STRING)
	@Column(name="CREDITO_MIGRADO")
	private SiNoEnum creditoMigrado;
	
	@Column(name="TRAMITE_REINTEGRO") 			
	private Long tramiteReintegro;

	@Column(name="FECHA_ALTA_TRAM_REINTEGRO") 			
	private Date fechaAltaTramiteReintegro;
	
	@Enumerated (EnumType.STRING)
	@Column(name="TIPO_REINTEGRO") 			
	private TipoReintegroEnum tipoReintegro;
	
	@Column(name="ACUERDO_FACTURACION_DESTINO")
	private Long acuerdoFacturacionDestino;
	
	@Column(name="LINEA_DESTINO")
	private Long lineaDestino;
	
	@Enumerated (EnumType.STRING)
	@Column(name="REINTEGRA_CON_INTERES")
	private SiNoEnum reintegraConInteres;

	/******************************************************************************/
	// Datos de cobranza generacion de cargo
	@Column(name="COB_CAR_INT_TRASLA_REGU")
	private BigDecimal cobranzaCargoInteresesTrasladadosRegulados;
	@Column(name="COB_CAR_INT_TRASLA_N_REGU")
	private BigDecimal cobranzaCargoInteresesTrasladadosNoRegulados;
	@Column(name="COB_CAR_INT_BONIF_REGU")
	private BigDecimal cobranzaCargoInteresesBonificadosRegulados;
	@Column(name="COB_CAR_INT_BONIF_N_REGU")
	private BigDecimal cobranzaCargoInteresesBonificadosNoRegulados;
	
	// Datos del credito aplicado: cliente
	@Column(name="CRE_CODIGO_CLIENTE")
	private Long creditoCodigoCliente;
	
	// Datos del credito aplicado: medio de pago (foto de los datos previos a la imputacion)
	@Column(name="CRED_MED_PAGO_CUENTA")
	private Long creditoMedioPagoCuenta;
	@Enumerated (EnumType.STRING)
	@Column(name="CRED_MED_PAGO_TIPO_CRED")
	private TipoComprobanteEnum creditoMedioPagoTipoCredito;
	
	// Datos del credito aplicado: Nota de credito (foto de los datos previos a la imputacion)
	@Enumerated (EnumType.STRING)
	@Column(name="CRED_MED_PAGO_TIPO_COMPR")
	private TipoComprobanteEnum creditoMedioPagoTipoComprobante;
	@Enumerated (EnumType.STRING)
	@Column(name="CRED_MED_PAGO_CLASE_COMPR")
	private TipoClaseComprobanteEnum creditoMedioPagoClaseComprobante;
	@Column(name="CRED_MED_PAGO_SUC_COMPR")
	private String creditoMedioPagoSucursalComprobante;
	@Column(name="CRED_MED_PAGO_NUM_COMPR")
	private String creditoMedioPagoNumeroComprobante;
	@Column(name="CRED_MED_PAGO_NRO_REF_MIC")
	private Long creditoMedioPagoNroReferenciaMic;
	
	// Datos del credito aplicado: Remanente (foto de los datos previos a la imputacion)
	@Enumerated (EnumType.STRING)
	@Column(name="CODIGO_TIPO_REMANENTE")
	private TipoRemanenteEnum remanenteTipoRemanente; //-----------------------------------------------------------------------------------
	
	// Datos del credito aplicado: datos generales (foto de los datos previos a la imputacion)
	@Column(name="CRED_GRAL_IMP")
	private BigDecimal creditoGralImporte;
	@Column(name="CRED_GRAL_SALDO")
	private BigDecimal creditoGralSaldo;
	@Column(name="CRED_GRAL_FECHA_ALTA")
	private Date creditoGralFechaAlta;
	@Column(name="CRED_GRAL_FECHA_EMISION")
	private Date creditoGralFechaEmision;
	@Column(name="CRED_GRAL_FECHA_VENC")
	private Date creditoGralFechaVencimiento;
	@Column(name="CRED_GRAL_FECHA_ULT_MOV")
	private Date creditoGralFechaUltimoMovimiento;
	@Enumerated (EnumType.STRING)
	@Column(name="CRED_GRAL_MARCA_RECLAMO")
	private MarcaReclamoEnum creditoGralMarcaReclamo;
	@Enumerated (EnumType.STRING)
	@Column(name="CRED_MARCA_CYQ")
	private MarcaCyQEnum creditoGralMarcaCyq;
	@Enumerated (EnumType.STRING)
	@Column(name="ESTADO_ORIGEN")
	private EstadoOrigenEnum estadoOrigen;
	
	// Datos del credito aplicado: Tagetik  (foto de los datos previos a la imputacion)
	@Column(name="CRED_TAG_RAZ_SOC_CLI")
	private String creditoTagetikRazonSocialCliente;
	@Column(name="CRED_TAG_TIPO_CLI")
	private String creditoTagetikTipoCliente;
	@Column(name="CRED_TAG_CUIT")
	private String creditoTagetikCuit;
	@Column(name="CRED_TAG_CICLO_FACT")
	private Integer creditoTagetikCicloFacturacion;
	@Column(name="CRED_MARKET_CUSTOM_GROUP")
	private String creditoTagetikMarketingCustomerGroup;
	@Enumerated (EnumType.STRING)
	@Column(name="CRED_TIPO_FACTURA")
	private TipoFacturaEnum creditoTagetikTipoFactura;
	@Column(name="CRED_TAG_IMP_ORIG")
	private BigDecimal creditoTagetikImporteOriginal;
	
	// Datos del credito aplicado: Dacota (foto de los datos previos a la imputacion)
	@Column(name="CRED_FECHA_VENCIMIENTO_MORA")
	private Date creditoDakotaFechaVencimientoMora;
	@Column(name="CRED_INDICADOR_PETICION_CORTE")
	private String creditoDakotaIndicadorPeticionCorte;
	@Column(name="CRED_CODIGO_TARIFA")
	private String creditoDakotaCodigoTarifa;
	
	// Datos del acuerdo de intereses por mora / acuerdo del reintegro: datos generales (foto de los datos previos a la imputacion)
	@Column(name="DATOS_CLIENTE_CODIGO")
	private Long acuerdoDatosClientecodigoCliente;
	
	// Datos del acuerdo de intereses por mora / acuerdo del reintegro: datos del cliente
	@Column(name="ACUER_COD_CLI")
	private Long acuerdoGralCodigoCliente;
	@Column(name="ACUERDO_FACTURACION")
	private Long acuerdoGralAcuerdoFacturacion;
	@Column(name="ACUERDO_GRAL_LINEA")
	private Long acuerdoGralLinea;
	@Enumerated (EnumType.STRING)
	@Column(name="ESTADO_ACUERDO_FACT")
	private EstadoAcuerdoFacturacionEnum acuerdoGralEstadoAcuerdoFacturacion;
	//	
	/******************************************************************************/
	public Long getCuenta() {
		return cuenta;
	}

	public void setCuenta(Long cuenta) {
		this.cuenta = cuenta;
	}

	public TipoOrigenEnum getTipoRemanente() {
		return tipoRemanente;
	}

	public void setTipoRemanente(TipoOrigenEnum tipoRemanente) {
		this.tipoRemanente = tipoRemanente;
	}

	public Long getNumeroReferenciaNC() {
		return numeroReferenciaNC;
	}

	public void setNumeroReferenciaNC(Long numeroReferenciaNC) {
		this.numeroReferenciaNC = numeroReferenciaNC;
	}

	public SiNoEnum getCreditoMigrado() {
		return creditoMigrado;
	}

	public void setCreditoMigrado(SiNoEnum creditoMigrado) {
		this.creditoMigrado = creditoMigrado;
	}

	public Long getTramiteReintegro() {
		return tramiteReintegro;
	}

	public void setTramiteReintegro(Long tramiteReintegro) {
		this.tramiteReintegro = tramiteReintegro;
	}

	public Date getFechaAltaTramiteReintegro() {
		return fechaAltaTramiteReintegro;
	}

	public void setFechaAltaTramiteReintegro(Date fechaAltaTramiteReintegro) {
		this.fechaAltaTramiteReintegro = fechaAltaTramiteReintegro;
	}

	public TipoReintegroEnum getTipoReintegro() {
		return tipoReintegro;
	}

	public void setTipoReintegro(TipoReintegroEnum tipoReintegro) {
		this.tipoReintegro = tipoReintegro;
	}

	public Long getAcuerdoFacturacionDestino() {
		return acuerdoFacturacionDestino;
	}

	public void setAcuerdoFacturacionDestino(Long acuerdoFacturacionDestino) {
		this.acuerdoFacturacionDestino = acuerdoFacturacionDestino;
	}

	public Long getLineaDestino() {
		return lineaDestino;
	}

	public void setLineaDestino(Long lineaDestino) {
		this.lineaDestino = lineaDestino;
	}

	public SiNoEnum getReintegraConInteres() {
		return reintegraConInteres;
	}

	public void setReintegraConInteres(SiNoEnum reintegraConInteres) {
		this.reintegraConInteres = reintegraConInteres;
	}

	public BigDecimal getCobranzaCargoInteresesTrasladadosRegulados() {
		return cobranzaCargoInteresesTrasladadosRegulados;
	}

	public void setCobranzaCargoInteresesTrasladadosRegulados(
			BigDecimal cobranzaCargoInteresesTrasladadosRegulados) {
		this.cobranzaCargoInteresesTrasladadosRegulados = cobranzaCargoInteresesTrasladadosRegulados;
	}

	public BigDecimal getCobranzaCargoInteresesTrasladadosNoRegulados() {
		return cobranzaCargoInteresesTrasladadosNoRegulados;
	}

	public void setCobranzaCargoInteresesTrasladadosNoRegulados(
			BigDecimal cobranzaCargoInteresesTrasladadosNoRegulados) {
		this.cobranzaCargoInteresesTrasladadosNoRegulados = cobranzaCargoInteresesTrasladadosNoRegulados;
	}

	public BigDecimal getCobranzaCargoInteresesBonificadosRegulados() {
		return cobranzaCargoInteresesBonificadosRegulados;
	}

	public void setCobranzaCargoInteresesBonificadosRegulados(
			BigDecimal cobranzaCargoInteresesBonificadosRegulados) {
		this.cobranzaCargoInteresesBonificadosRegulados = cobranzaCargoInteresesBonificadosRegulados;
	}

	public BigDecimal getCobranzaCargoInteresesBonificadosNoRegulados() {
		return cobranzaCargoInteresesBonificadosNoRegulados;
	}

	public void setCobranzaCargoInteresesBonificadosNoRegulados(
			BigDecimal cobranzaCargoInteresesBonificadosNoRegulados) {
		this.cobranzaCargoInteresesBonificadosNoRegulados = cobranzaCargoInteresesBonificadosNoRegulados;
	}

	public Long getCreditoCodigoCliente() {
		return creditoCodigoCliente;
	}

	public void setCreditoCodigoCliente(Long creditoCodigoCliente) {
		this.creditoCodigoCliente = creditoCodigoCliente;
	}

	public Long getCreditoMedioPagoCuenta() {
		return creditoMedioPagoCuenta;
	}

	public void setCreditoMedioPagoCuenta(Long creditoMedioPagoCuenta) {
		this.creditoMedioPagoCuenta = creditoMedioPagoCuenta;
	}

	public TipoComprobanteEnum getCreditoMedioPagoTipoCredito() {
		return creditoMedioPagoTipoCredito;
	}

	public void setCreditoMedioPagoTipoCredito(
			TipoComprobanteEnum creditoMedioPagoTipoCredito) {
		this.creditoMedioPagoTipoCredito = creditoMedioPagoTipoCredito;
	}

	public TipoComprobanteEnum getCreditoMedioPagoTipoComprobante() {
		return creditoMedioPagoTipoComprobante;
	}

	public void setCreditoMedioPagoTipoComprobante(
			TipoComprobanteEnum creditoMedioPagoTipoComprobante) {
		this.creditoMedioPagoTipoComprobante = creditoMedioPagoTipoComprobante;
	}

	public TipoClaseComprobanteEnum getCreditoMedioPagoClaseComprobante() {
		return creditoMedioPagoClaseComprobante;
	}

	public void setCreditoMedioPagoClaseComprobante(
			TipoClaseComprobanteEnum creditoMedioPagoClaseComprobante) {
		this.creditoMedioPagoClaseComprobante = creditoMedioPagoClaseComprobante;
	}

	public String getCreditoMedioPagoSucursalComprobante() {
		return creditoMedioPagoSucursalComprobante;
	}

	public void setCreditoMedioPagoSucursalComprobante(
			String creditoMedioPagoSucursalComprobante) {
		this.creditoMedioPagoSucursalComprobante = creditoMedioPagoSucursalComprobante;
	}

	public String getCreditoMedioPagoNumeroComprobante() {
		return creditoMedioPagoNumeroComprobante;
	}

	public void setCreditoMedioPagoNumeroComprobante(
			String creditoMedioPagoNumeroComprobante) {
		this.creditoMedioPagoNumeroComprobante = creditoMedioPagoNumeroComprobante;
	}

	public Long getCreditoMedioPagoNroReferenciaMic() {
		return creditoMedioPagoNroReferenciaMic;
	}

	public void setCreditoMedioPagoNroReferenciaMic(
			Long creditoMedioPagoNroReferenciaMic) {
		this.creditoMedioPagoNroReferenciaMic = creditoMedioPagoNroReferenciaMic;
	}

	public TipoRemanenteEnum getRemanenteTipoRemanente() {
		return remanenteTipoRemanente;
	}

	public void setRemanenteTipoRemanente(TipoRemanenteEnum remanenteTipoRemanente) {
		this.remanenteTipoRemanente = remanenteTipoRemanente;
	}

	public BigDecimal getCreditoGralImporte() {
		return creditoGralImporte;
	}

	public void setCreditoGralImporte(BigDecimal creditoGralImporte) {
		this.creditoGralImporte = creditoGralImporte;
	}

	public BigDecimal getCreditoGralSaldo() {
		return creditoGralSaldo;
	}

	public void setCreditoGralSaldo(BigDecimal creditoGralSaldo) {
		this.creditoGralSaldo = creditoGralSaldo;
	}

	public Date getCreditoGralFechaAlta() {
		return creditoGralFechaAlta;
	}

	public void setCreditoGralFechaAlta(Date creditoGralFechaAlta) {
		this.creditoGralFechaAlta = creditoGralFechaAlta;
	}

	public Date getCreditoGralFechaEmision() {
		return creditoGralFechaEmision;
	}

	public void setCreditoGralFechaEmision(Date creditoGralFechaEmision) {
		this.creditoGralFechaEmision = creditoGralFechaEmision;
	}

	public Date getCreditoGralFechaUltimoMovimiento() {
		return creditoGralFechaUltimoMovimiento;
	}

	public void setCreditoGralFechaUltimoMovimiento(
			Date creditoGralFechaUltimoMovimiento) {
		this.creditoGralFechaUltimoMovimiento = creditoGralFechaUltimoMovimiento;
	}

	public MarcaReclamoEnum getCreditoGralMarcaReclamo() {
		return creditoGralMarcaReclamo;
	}

	public void setCreditoGralMarcaReclamo(MarcaReclamoEnum creditoGralMarcaReclamo) {
		this.creditoGralMarcaReclamo = creditoGralMarcaReclamo;
	}

	public MarcaCyQEnum getCreditoGralMarcaCyq() {
		return creditoGralMarcaCyq;
	}

	public void setCreditoGralMarcaCyq(MarcaCyQEnum creditoGralMarcaCyq) {
		this.creditoGralMarcaCyq = creditoGralMarcaCyq;
	}

	public EstadoOrigenEnum getEstadoOrigen() {
		return estadoOrigen;
	}

	public void setEstadoOrigen(EstadoOrigenEnum estadoOrigen) {
		this.estadoOrigen = estadoOrigen;
	}

	public String getCreditoTagetikRazonSocialCliente() {
		return creditoTagetikRazonSocialCliente;
	}

	public void setCreditoTagetikRazonSocialCliente(
			String creditoTagetikRazonSocialCliente) {
		this.creditoTagetikRazonSocialCliente = creditoTagetikRazonSocialCliente;
	}

	public String getCreditoTagetikTipoCliente() {
		return creditoTagetikTipoCliente;
	}

	public void setCreditoTagetikTipoCliente(String creditoTagetikTipoCliente) {
		this.creditoTagetikTipoCliente = creditoTagetikTipoCliente;
	}

	public String getCreditoTagetikCuit() {
		return creditoTagetikCuit;
	}

	public void setCreditoTagetikCuit(String creditoTagetikCuit) {
		this.creditoTagetikCuit = creditoTagetikCuit;
	}

	public Integer getCreditoTagetikCicloFacturacion() {
		return creditoTagetikCicloFacturacion;
	}

	public void setCreditoTagetikCicloFacturacion(
			Integer creditoTagetikCicloFacturacion) {
		this.creditoTagetikCicloFacturacion = creditoTagetikCicloFacturacion;
	}

	public String getCreditoTagetikMarketingCustomerGroup() {
		return creditoTagetikMarketingCustomerGroup;
	}

	public void setCreditoTagetikMarketingCustomerGroup(
			String creditoTagetikMarketingCustomerGroup) {
		this.creditoTagetikMarketingCustomerGroup = creditoTagetikMarketingCustomerGroup;
	}

	public TipoFacturaEnum getCreditoTagetikTipoFactura() {
		return creditoTagetikTipoFactura;
	}

	public void setCreditoTagetikTipoFactura(
			TipoFacturaEnum creditoTagetikTipoFactura) {
		this.creditoTagetikTipoFactura = creditoTagetikTipoFactura;
	}

	public BigDecimal getCreditoTagetikImporteOriginal() {
		return creditoTagetikImporteOriginal;
	}

	public void setCreditoTagetikImporteOriginal(
			BigDecimal creditoTagetikImporteOriginal) {
		this.creditoTagetikImporteOriginal = creditoTagetikImporteOriginal;
	}

	public Date getCreditoDakotaFechaVencimientoMora() {
		return creditoDakotaFechaVencimientoMora;
	}

	public void setCreditoDakotaFechaVencimientoMora(
			Date creditoDakotaFechaVencimientoMora) {
		this.creditoDakotaFechaVencimientoMora = creditoDakotaFechaVencimientoMora;
	}

	public String getCreditoDakotaIndicadorPeticionCorte() {
		return creditoDakotaIndicadorPeticionCorte;
	}

	public void setCreditoDakotaIndicadorPeticionCorte(
			String creditoDakotaIndicadorPeticionCorte) {
		this.creditoDakotaIndicadorPeticionCorte = creditoDakotaIndicadorPeticionCorte;
	}

	public String getCreditoDakotaCodigoTarifa() {
		return creditoDakotaCodigoTarifa;
	}

	public void setCreditoDakotaCodigoTarifa(String creditoDakotaCodigoTarifa) {
		this.creditoDakotaCodigoTarifa = creditoDakotaCodigoTarifa;
	}

	public Long getAcuerdoDatosClientecodigoCliente() {
		return acuerdoDatosClientecodigoCliente;
	}

	public void setAcuerdoDatosClientecodigoCliente(
			Long acuerdoDatosClientecodigoCliente) {
		this.acuerdoDatosClientecodigoCliente = acuerdoDatosClientecodigoCliente;
	}

	public Long getAcuerdoGralCodigoCliente() {
		return acuerdoGralCodigoCliente;
	}

	public void setAcuerdoGralCodigoCliente(Long acuerdoGralCodigoCliente) {
		this.acuerdoGralCodigoCliente = acuerdoGralCodigoCliente;
	}

	public Long getAcuerdoGralAcuerdoFacturacion() {
		return acuerdoGralAcuerdoFacturacion;
	}

	public void setAcuerdoGralAcuerdoFacturacion(Long acuerdoGralAcuerdoFacturacion) {
		this.acuerdoGralAcuerdoFacturacion = acuerdoGralAcuerdoFacturacion;
	}

	public Long getAcuerdoGralLinea() {
		return acuerdoGralLinea;
	}

	public void setAcuerdoGralLinea(Long acuerdoGralLinea) {
		this.acuerdoGralLinea = acuerdoGralLinea;
	}

	public EstadoAcuerdoFacturacionEnum getAcuerdoGralEstadoAcuerdoFacturacion() {
		return acuerdoGralEstadoAcuerdoFacturacion;
	}

	public void setAcuerdoGralEstadoAcuerdoFacturacion(
			EstadoAcuerdoFacturacionEnum acuerdoGralEstadoAcuerdoFacturacion) {
		this.acuerdoGralEstadoAcuerdoFacturacion = acuerdoGralEstadoAcuerdoFacturacion;
	}

	public Date getCreditoGralFechaVencimiento() {
		return creditoGralFechaVencimiento;
	}

	public void setCreditoGralFechaVencimiento(
			Date creditoGralFechaVencimiento) {
		this.creditoGralFechaVencimiento = creditoGralFechaVencimiento;
	}

	public TipoClaseComprobanteEnum getClaseComprobante() {
		return claseComprobante;
	}

	public void setClaseComprobante(TipoClaseComprobanteEnum claseComprobante) {
		this.claseComprobante = claseComprobante;
	}

	public String getSucursalComprobante() {
		return sucursalComprobante;
	}

	public void setSucursalComprobante(String sucursalComprobante) {
		this.sucursalComprobante = sucursalComprobante;
	}

	public String getNumeroComprobante() {
		return numeroComprobante;
	}

	public void setNumeroComprobante(String numeroComprobante) {
		this.numeroComprobante = numeroComprobante;
	}
	
}
