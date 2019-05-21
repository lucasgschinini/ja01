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

import ar.com.telecom.shiva.base.enumeradores.EstadoOrigenEnum;
import ar.com.telecom.shiva.base.enumeradores.MarcaCyQEnum;
import ar.com.telecom.shiva.base.enumeradores.MarcaReclamoEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoClaseComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoFacturaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoOrigenEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoRemanenteEnum;

@Entity
@Table(name = "SHV_MAS_REGISTRO_GANANCIAS")
@PrimaryKeyJoinColumn(name="ID_REGISTRO")
@Inheritance(strategy=InheritanceType.JOINED)
public class ShvMasRegistroGanancias extends ShvMasRegistro {

	private static final long serialVersionUID = 1L;
	
	@Column(name="CUENTA_ORIGEN") 			
	private Long cuentaOrigen;
	
	@Enumerated (EnumType.STRING)
	@Column(name="TIPO_REMANENTE")
	private TipoOrigenEnum tipoRemanente;
	
	@Column(name="NRO_REFERENCIA_NC")
	private Long numeroReferenciaNC;
	
	@Enumerated (EnumType.STRING)
	@Column(name="CREDITO_MIGRADO")
	private SiNoEnum creditoMigrado;
/*******************************************************************************/
	// Datos del credito aplicado: cliente
	@Column(name="CRED_CODIGO_CLIENTE")
	private Long creditoCodigoCliente;
	
	// Datos del credito aplicado: medio de pago (foto de los datos previos a la imputacion)
	@Column(name="CRED_MEDIO_PAGO_CUENTA")
	private Long creditoMedioPagoCuenta;
	//Top
	@Enumerated (EnumType.STRING)
	@Column(name="CRED_MED_PAG_TIPO_CRED")
	private TipoComprobanteEnum creditoMedioPagoTipoCredito;
	
	// Datos del credito aplicado: Nota de credito (foto de los datos previos a la imputacion)
	@Enumerated (EnumType.STRING)
	@Column(name="CRED_MED_PAG_TIPO_COMPR")
	private TipoComprobanteEnum creditoMedioPagoTipoComprobante;
	
	@Enumerated (EnumType.STRING)
	@Column(name="CRED_MED_PAGO_CLASE_COMPR")
	private TipoClaseComprobanteEnum creditoMedioPagoClaseComprobante;
	
	@Column(name="CRED_PAG_SUC_COMPR")
	private String creditoMedioPagoSucursalComprobante;
	
	@Column(name="CRED_MED_PAGO_NUM_COMPR")
	private String creditoMedioPagoNumeroComprobante;

	@Column(name="CRED_MED_PAGO_NRO_REF_MIC")
	private Long creditoMedioPagoNroReferenciaMic;
	
	// Datos del credito aplicado: Remanente (foto de los datos previos a la imputacion)
	@Enumerated (EnumType.STRING)
	@Column(name="REMANENTE_TIPO_REMANENTE")
	private TipoRemanenteEnum remanenteTipoRemanente;
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
	@Column(name="CRED_GRAL_MARCA_CYQ")
	private MarcaCyQEnum creditoGralMarcaCyq;
	
	@Enumerated (EnumType.STRING)
	@Column(name="ESTADO_ORIGEN")
	private EstadoOrigenEnum estadoOrigen;
	// Datos del credito aplicado: Tagetik  (foto de los datos previos a la imputacion)
	
	@Column(name="CRED_TA_RAZ_SOC_CLI")
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
	@Column(name="CRED_TAG_TIPO_FACT")
	private TipoFacturaEnum creditoTagetikTipoFactura;
	
	@Column(name="CRED_TAG_IMP_ORIG")	
	private BigDecimal creditoTagetikImporteOriginal;
	
	// Datos del credito aplicado: Dacota (foto de los datos previos a la imputacion)
	@Column(name="CRED_DAK_FECHA_VENC_MORA")
	private Date creditoDakotaFechaVencimientoMora;
	
	@Column(name="CRED_DAK_INDIC_PETIC_CORTE")
	private String creditoDakotaIndicadorPeticionCorte;
	
	@Column(name="CRED_DAK_COD_TARIFA")
	private String creditoDakotaCodigoTarifa;
	
	public Long getCuentaOrigen() {
		return cuentaOrigen;
	}

	public void setCuentaOrigen(Long cuentaOrigen) {
		this.cuentaOrigen = cuentaOrigen;
	}

	public SiNoEnum getCreditoMigrado() {
		return creditoMigrado;
	}

	public void setCreditoMigrado(SiNoEnum creditoMigrado) {
		this.creditoMigrado = creditoMigrado;
	}

	public Long getNumeroReferenciaNC() {
		return numeroReferenciaNC;
	}

	public void setNumeroReferenciaNC(Long numeroReferenciaNC) {
		this.numeroReferenciaNC = numeroReferenciaNC;
	}

	public TipoOrigenEnum getTipoRemanente() {
		return tipoRemanente;
	}

	public void setTipoRemanente(TipoOrigenEnum tipoRemanente) {
		this.tipoRemanente = tipoRemanente;
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

	public Date getCreditoGralFechaVencimiento() {
		return creditoGralFechaVencimiento;
	}

	public void setCreditoGralFechaVencimiento(
			Date creditoGralFechaVencimiento) {
		this.creditoGralFechaVencimiento = creditoGralFechaVencimiento;
	}
	
	
}
