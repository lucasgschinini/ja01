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
import ar.com.telecom.shiva.base.enumeradores.EstadoConceptoTercerosEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoDUCEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoOrigenEnum;
import ar.com.telecom.shiva.base.enumeradores.MarcaCyQEnum;
import ar.com.telecom.shiva.base.enumeradores.MarcaReclamoEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoClaseComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoClienteTagetikEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoDUCEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoFacturaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoOrigenEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoPagoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoRemanenteEnum;
import ar.com.telecom.shiva.base.enumeradores.TratamientoInteresesEnum;

@Entity
@Table(name = "SHV_MAS_REGISTRO_APLICAR_DEUDA")
@PrimaryKeyJoinColumn(name="ID_REGISTRO")
@Inheritance(strategy=InheritanceType.JOINED)
public class ShvMasRegistroAplicarDeuda extends ShvMasRegistro {

	private static final long serialVersionUID = 1L;
	
	@Enumerated (EnumType.STRING)
	@Column(name="TIPO_OPERACION")
	private TipoPagoEnum tipoOperacion;
	
	@Column(name="CUENTA")
	private Long cuenta;
	
	@Enumerated (EnumType.STRING)
	@Column(name="TIPO_REMANENTE")
	private TipoOrigenEnum tipoRemanente;
	
	@Enumerated(EnumType.STRING)
	@Column(name="DEUDA_MIGRADA") 
	private SiNoEnum deudaMigrada;
	
	@Column(name="NUMERO_REFERENCIA_FACTURA")
	private Long numeroReferenciaFactura;
	
	@Enumerated(EnumType.STRING)
	@Column(name="DESTRANSFERIR_TERCEROS")
	private SiNoEnum destransferirTerceros;
	
	@Column(name="NUMERO_REFERENCIA_NC")
	private String numeroReferenciaNC;
	
	@Enumerated(EnumType.STRING)
	@Column(name="CREDITO_MIGRADO")
	private SiNoEnum creditoMigrado;
	
	@Enumerated(EnumType.STRING)
	@Column(name="ACCION_SOBRE_INTERESES")
	private TratamientoInteresesEnum accionSobreIntereses;
	
	@Column(name="PORCENTAJE_BONIF_INTERESES")
	private Long porcentajeBonificacionIntereses;
	
	@Column(name="IMPORTE_BONIFICACION_INTERESES")
	private BigDecimal importeBonificacionIntereses;
	
	@Column(name="ACUERDO_FACTURACION_DESTINO")
	private Long acuerdoFacturacionDestino;

/******************************************************************************/
// Datos de cobranza apropiacion de deuda
	@Column(name="COBR_APROP_INTER_TRAS_REGU")
	private BigDecimal cobranzaApropiacionInteresesTrasladadosRegulados;
	@Column(name="COBR_APROP_INTER_TRAS_N_REGU")
	private BigDecimal cobranzaApropiacionInteresesTrasladadosNoRegulados;
	@Column(name="COBR_APROP_INTER_BONIF_REGU")
	private BigDecimal cobranzaApropiacionInteresesBonificadosRegulados;
	@Column(name="COBR_APROP_INTER_BONIF_N_REGU")
	private BigDecimal cobranzaApropiacionInteresesBonificadosNoRegulados;
// Datos del debito imputado: cliente
	@Column(name="DEB_IMPUTADO_COD_CLIENTE")
	private Long debitoImputadoCodigoCliente;
// Datos del debito imputado: datos generales (foto de los datos previos a la imputacion)
	@Column(name="DEB_IMPUTADO_GRAL_CUENTA_DEB")
	private Long debitoImputadoGralCuentaDebito;
	@Enumerated (EnumType.STRING)
	@Column(name="DEB_IMPUTADO_GRAL_TIPO_DOC")
	private TipoComprobanteEnum debitoImputadoGralTipoDocumento;
	@Enumerated (EnumType.STRING)
	@Column(name="DEB_IMPUTADO_GRAL_TIPO_DUC")
	private TipoDUCEnum debitoImputadoGralTipoDuc;
		//	Descripcion Tipo de DUC	Alfanumérico	
	@Enumerated (EnumType.STRING)
	@Column(name="DEB_IMPUTADO_GRAL_ESTADO_DUC")
	private EstadoDUCEnum debitoImputadoGralEstadoDuc;
		//	Descripción Estado del DUC	Alfanumérico	
	@Column(name="DEB_IMPUTADO_GRAL_ACUERDO")
	private String debitoImputadoGralAcuerdo;
	@Enumerated (EnumType.STRING)
	@Column(name="DEB_IMPUTADO_GRAL_CLASE_COMP")
	private TipoClaseComprobanteEnum debitoImputadoGralClaseComprobante;
	@Column(name="DEB_IMPUTADO_GRAL_SUC_COMP")
	private String debitoImputadoGralSucursalComprobante;
	@Column(name="DEB_IMPUTADO_GRAL_NUM_COMP")
	private String debitoImputadoGralNumeroComprobante;
	@Column(name="DEB_IMPUTADO_GRAL_NUM_REF_MIC")
	private String debitoImputadoGralNumeroReferenciaMic;
	@Column(name="DEB_IMPU_GRAL_FECHA_VENC") 	
	private Date debitoImputadoGralFechaVencimiento;
	@Column(name="DEB_IMPU_GRAL_IMPOR_PRIM_VENC") 
	private BigDecimal debitoImputadoGralImportePrimerVencimiento;
	@Column(name="DEB_IMPU_GRAL_IMPOR_SEG_VENC") 
	private BigDecimal debitoImputadoGralImporteSegundoVencimiento;
	@Column(name="DEB_IMPU_GRAL_SALDO_PRIM_VENC")
	private BigDecimal debitoImputadoGralSaldoPrimerVencimiento;
	@Enumerated (EnumType.STRING)
	@Column(name="DEB_IMPU_GRAL_ESTAD_ACUER_FACT")
	private EstadoAcuerdoFacturacionEnum debitoImputadoGralEstadoAcuerdoFacturacion;
		//	Codigo Estado acuerdo Facturación	Numérico	2
		//	Descripcion Estado acuerdo Factuación	Alfanumérico	10
//	//TODO Ver si no es el enum EstadoConceptoTercero
	@Enumerated (EnumType.STRING)
	@Column(name="DEB_IMPU_GRAL_ESTAD_CONCEP_TER")
	private EstadoConceptoTercerosEnum debitoImputadoGralEstadoConceptoTerceros; //	Estado Conceptos de Terceros	Alfanumérico	1
	@Enumerated (EnumType.STRING)
	@Column(name="DEB_IMPU_GRAL_POSIB_DESTRANF")
	private SiNoEnum debitoImputadoGralPosibilidadDestransferencia;
	@Column(name="DEB_IMPU_GRAL_IMP_TER_TRANSF")
	private BigDecimal debitoImputadoGralImporteTercerosTransferibles;
	@Column(name="DEB_IMPU_IMP_SEG_VEN_C_TER")
	private BigDecimal debitoImputadoGralImportePrimerVencimientoConTerceros;
	@Column(name="DEB_IMPU_IMP_PRIM_VEN_C_TER")
	private BigDecimal debitoImputadoGralImporteSegundoVencimientoConTerceros;
	
	
	@Enumerated (EnumType.STRING)
	@Column(name="DEB_IMPU_GRAL_COD_EST_FAC")
	private EstadoOrigenEnum debitoImputadoGralCodigoEstadoFactura;

	@Enumerated (EnumType.STRING)
	@Column(name="DEB_IMPU_GRAL_MARCA_RECLAMO")
	private MarcaReclamoEnum debitoImputadoGralMarcaReclamo;

	@Enumerated (EnumType.STRING)
	@Column(name="DEB_IMPU_GRAL_MARCA_CYQ")
	private MarcaCyQEnum debitoImputadoGralMarcaCyq;
	
	@Column(name="DEB_IMPU_GRAL_FECHA_EMISION") 	
	private Date debitoImputadoGralFechaEmision;
	@Column(name="DEB_IMPU_GRAL_NUMERO_CONVENIO")
	private Long debitoImputadoGralNumeroConvenio;
	@Column(name="DEB_IMPU_GRAL_CUOTA")
	private Long debitoImputadoGralCuota;
	@Column(name="DEB_IMPU_FECHA_ULT_PAGO_PARC")
	private Date debitoImputadoGralFechaUltimoPagoParcial;
	@Column(name="DEB_IMPU_FECHA_ULT_PUESTA_COB")
	private Date debitoImputadoGralFechaPuestaCobro;
	
// Datos del debito imputado: tagetik  (foto de los datos previos a la imputacion)
	@Column(name="DEB_TAG_RAZON_SOC_CLI")
	private String debitoTagetikRazonSocialCliente;
	@Enumerated (EnumType.STRING)
	@Column(name="DEB_TAG_TIPO_CLIENTE")
	private TipoClienteTagetikEnum debitoTagetikTipoCliente;
	@Column(name="DEB_TAG_CUIT")
	private String debitoTagetikCuit;
	@Column(name="DEB_TAG_CICLO_FACTURACION")
	private Integer debitoTagetikCicloFacturacion;
	@Column(name="DEB_TAG_MARKET_CUSTOM_GROUP")
	private String debitoTagetikMarketingCustomerGroup;
	@Enumerated (EnumType.STRING)
	@Column(name="DEB_TAG_TIPO_FACTURA")
	private TipoFacturaEnum debitoTagetikTipoFactura;
// Datos del debito imputado: Dacota  (foto de los datos previos a la imputacion)
	@Column(name="DEB_DAK_FECHA_VENC_MORA")
	private Date debitoDakotaFechaVencimientoMora;
	@Column(name="DEB_DAK_INDIC_PETIC_CORTE")
	private String debitoDakotaIndicadorPeticionCorte;
	@Column(name="DEB_DAK_CODIGO_TARIFA")
	private String debitoDakotaCodigoTarifa;
// Datos del debito imputado: Saldos de terceros  (foto de los datos previos a la imputacion)
	@Column(name="DEB_SALD_TER_FINANC_NO_TRANSF")
	private BigDecimal debitoSaldosTercerosSaldoTerceroFinanciableNOTransferible;
	@Column(name="DEB_SALD_TER_FINANC_TRANSF")
	private BigDecimal debitoSaldosTercerosSaldoTerceroFinanciableTransferible;
	@Column(name="DEB_SALD_TER_N_FINANC_TRANSF")
	private BigDecimal debitoSaldosTercerosSaldoTerceroNOFinanciableTransferible;
//	
//	
//	/*************************************************************************/
//	// CRedio
///******************************************************************************/
	
	
	
	// Datos del credito aplicado: medio de pago (foto de los datos previos a la imputacion)
	
	// Datos del credito aplicado: cliente
	@Column(name="CRED_MED_PAG_COD_CLI")
	private Long creditoCodigoCliente;
	
	@Column(name="CRED_MED_PAG_CUEN")
	private Long creditoMedioPagoCuenta;

	@Enumerated (EnumType.STRING)
	@Column(name="CRED_MED_PAG_TIPO_CRED")
	private TipoComprobanteEnum creditoMedioPagoTipoCredito;
	// Datos del credito aplicado: Nota de credito (foto de los datos previos a la imputacion)
	@Enumerated (EnumType.STRING)
	@Column(name="CRED_MED_PAG_TIPO_COMPR")
	private TipoComprobanteEnum creditoMedioPagoTipoComprobante;
	@Enumerated (EnumType.STRING)
	@Column(name="CRED_MED_PAG_CLASE_COMPR")
	private TipoClaseComprobanteEnum creditoMedioPagoClaseComprobante;
	@Column(name="CRED_MED_PAG_SUC_COMPR")
	private String creditoMedioPagoSucursalComprobante;
	@Column(name="CRED_MED_PAG_NUM_COMPR")
	private String creditoMedioPagoNumeroComprobante;
	@Column(name="CRED_MED_PAGO_NRO_REF_MIC")
	private Long creditoMedioPagoNroReferenciaMic;
	// Datos del credito aplicado: Remanente (foto de los datos previos a la imputacion)
	@Enumerated (EnumType.STRING)
	@Column(name="CRED_TIPO_REMANENTE")
	private TipoRemanenteEnum remanenteTipoRemanente;

	// Datos del credito aplicado: datos generales (foto de los datos previos a la imputacion)
	@Column(name="CRED_GRAL_IMPORTE")
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
	@Enumerated(EnumType.STRING)
	@Column(name="CRED_GRAL_MARCA_RECLAMO")
	private MarcaReclamoEnum creditoGralMarcaReclamo;
	@Enumerated(EnumType.STRING)
	@Column(name="CRED_GRAL_MARCA_CYQ")
	private MarcaCyQEnum creditoGralMarcaCyq;
	@Enumerated(EnumType.STRING)
	@Column(name="ESTADO_ORIGEN")
	private EstadoOrigenEnum estadoOrigen;
	// Datos del credito aplicado: Tagetik  (foto de los datos previos a la imputacion)
	@Column(name="CRED_TAG_RAZ_SOC_CLI")
	private String creditoTagetikRazonSocialCliente;
	@Column(name="CRED_TAG_TIPO_CLI")
	private String creditoTagetikTipoCliente;
	@Column(name="CRED_TAG_CUIT")
	private String creditoTagetikCuit;
	@Column(name="CRED_TAG_CICLO_FAC")
	private Integer creditoTagetikCicloFacturacion;
	@Column(name="CRED_TAG_MARKET_CUSTOM_GROUP")
	private String creditoTagetikMarketingCustomerGroup;
	@Enumerated(EnumType.STRING)
	@Column(name="CRED_TAG_TIPO_FAC")
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
	// Datos del acuerdo de intereses por mora / acuerdo del reintegro: datos del cliente
	@Column(name="ACUER_DATOS_CLI_COD_CLI")
	private Long acuerdoDatosClientecodigoCliente;
	// Datos del acuerdo de intereses por mora / acuerdo del reintegro: datos generales (foto de los datos previos a la imputacion)
	@Column(name="ACUER_GRAL_COD_CLI")
	private Long acuerdoGralCodigoCliente;
	@Column(name="ACUER_GRAL_ACUER_FAC")
	private Long acuerdoGralAcuerdoFacturacion;
	@Column(name="ACUERDO_GRAL_LINEA")
	private Long acuerdoGralLinea;
	@Enumerated (EnumType.STRING)
	@Column(name="ACUER_GRAL_EST_ACUER_FACT")
	private EstadoAcuerdoFacturacionEnum acuerdoGralEstadoAcuerdoFacturacion;
	
	public TipoPagoEnum getTipoOperacion() {
		return tipoOperacion;
	}

	public void setTipoOperacion(TipoPagoEnum tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}

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

	public SiNoEnum getDeudaMigrada() {
		return deudaMigrada;
	}

	public void setDeudaMigrada(SiNoEnum deudaMigrada) {
		this.deudaMigrada = deudaMigrada;
	}

	public Long getNumeroReferenciaFactura() {
		return numeroReferenciaFactura;
	}

	public void setNumeroReferenciaFactura(Long numeroReferenciaFactura) {
		this.numeroReferenciaFactura = numeroReferenciaFactura;
	}

	public SiNoEnum getDestransferirTerceros() {
		return destransferirTerceros;
	}

	public void setDestransferirTerceros(SiNoEnum destransferirTerceros) {
		this.destransferirTerceros = destransferirTerceros;
	}

	public String getNumeroReferenciaNC() {
		return numeroReferenciaNC;
	}

	public void setNumeroReferenciaNC(String numeroReferenciaNC) {
		this.numeroReferenciaNC = numeroReferenciaNC;
	}

	public SiNoEnum getCreditoMigrado() {
		return creditoMigrado;
	}

	public void setCreditoMigrado(SiNoEnum creditoMigrado) {
		this.creditoMigrado = creditoMigrado;
	}

	public TratamientoInteresesEnum getAccionSobreIntereses() {
		return accionSobreIntereses;
	}

	public void setAccionSobreIntereses(
			TratamientoInteresesEnum accionSobreIntereses) {
		this.accionSobreIntereses = accionSobreIntereses;
	}

	public Long getPorcentajeBonificacionIntereses() {
		return porcentajeBonificacionIntereses;
	}

	public void setPorcentajeBonificacionIntereses(
			Long porcentajeBonificacionIntereses) {
		this.porcentajeBonificacionIntereses = porcentajeBonificacionIntereses;
	}

	public BigDecimal getImporteBonificacionIntereses() {
		return importeBonificacionIntereses;
	}

	public void setImporteBonificacionIntereses(
			BigDecimal importeBonificacionIntereses) {
		this.importeBonificacionIntereses = importeBonificacionIntereses;
	}

	public Long getAcuerdoFacturacionDestino() {
		return acuerdoFacturacionDestino;
	}

	public void setAcuerdoFacturacionDestino(Long acuerdoFacturacionDestino) {
		this.acuerdoFacturacionDestino = acuerdoFacturacionDestino;
	}

	public BigDecimal getCobranzaApropiacionInteresesTrasladadosRegulados() {
		return cobranzaApropiacionInteresesTrasladadosRegulados;
	}

	public void setCobranzaApropiacionInteresesTrasladadosRegulados(
			BigDecimal cobranzaApropiacionInteresesTrasladadosRegulados) {
		this.cobranzaApropiacionInteresesTrasladadosRegulados = cobranzaApropiacionInteresesTrasladadosRegulados;
	}

	public BigDecimal getCobranzaApropiacionInteresesTrasladadosNoRegulados() {
		return cobranzaApropiacionInteresesTrasladadosNoRegulados;
	}

	public void setCobranzaApropiacionInteresesTrasladadosNoRegulados(
			BigDecimal cobranzaApropiacionInteresesTrasladadosNoRegulados) {
		this.cobranzaApropiacionInteresesTrasladadosNoRegulados = cobranzaApropiacionInteresesTrasladadosNoRegulados;
	}

	public BigDecimal getCobranzaApropiacionInteresesBonificadosRegulados() {
		return cobranzaApropiacionInteresesBonificadosRegulados;
	}

	public void setCobranzaApropiacionInteresesBonificadosRegulados(
			BigDecimal cobranzaApropiacionInteresesBonificadosRegulados) {
		this.cobranzaApropiacionInteresesBonificadosRegulados = cobranzaApropiacionInteresesBonificadosRegulados;
	}

	public BigDecimal getCobranzaApropiacionInteresesBonificadosNoRegulados() {
		return cobranzaApropiacionInteresesBonificadosNoRegulados;
	}

	public void setCobranzaApropiacionInteresesBonificadosNoRegulados(
			BigDecimal cobranzaApropiacionInteresesBonificadosNoRegulados) {
		this.cobranzaApropiacionInteresesBonificadosNoRegulados = cobranzaApropiacionInteresesBonificadosNoRegulados;
	}

	public Long getDebitoImputadoCodigoCliente() {
		return debitoImputadoCodigoCliente;
	}

	public void setDebitoImputadoCodigoCliente(Long debitoImputadoCodigoCliente) {
		this.debitoImputadoCodigoCliente = debitoImputadoCodigoCliente;
	}

	public Long getDebitoImputadoGralCuentaDebito() {
		return debitoImputadoGralCuentaDebito;
	}

	public void setDebitoImputadoGralCuentaDebito(
			Long debitoImputadoGralCuentaDebito) {
		this.debitoImputadoGralCuentaDebito = debitoImputadoGralCuentaDebito;
	}

	public TipoComprobanteEnum getDebitoImputadoGralTipoDocumento() {
		return debitoImputadoGralTipoDocumento;
	}

	public void setDebitoImputadoGralTipoDocumento(
			TipoComprobanteEnum debitoImputadoGralTipoDocumento) {
		this.debitoImputadoGralTipoDocumento = debitoImputadoGralTipoDocumento;
	}

	public TipoDUCEnum getDebitoImputadoGralTipoDuc() {
		return debitoImputadoGralTipoDuc;
	}

	public void setDebitoImputadoGralTipoDuc(TipoDUCEnum debitoImputadoGralTipoDuc) {
		this.debitoImputadoGralTipoDuc = debitoImputadoGralTipoDuc;
	}

	public EstadoDUCEnum getDebitoImputadoGralEstadoDuc() {
		return debitoImputadoGralEstadoDuc;
	}

	public void setDebitoImputadoGralEstadoDuc(
			EstadoDUCEnum debitoImputadoGralEstadoDuc) {
		this.debitoImputadoGralEstadoDuc = debitoImputadoGralEstadoDuc;
	}

	public String getDebitoImputadoGralAcuerdo() {
		return debitoImputadoGralAcuerdo;
	}

	public void setDebitoImputadoGralAcuerdo(String debitoImputadoGralAcuerdo) {
		this.debitoImputadoGralAcuerdo = debitoImputadoGralAcuerdo;
	}

	public TipoClaseComprobanteEnum getDebitoImputadoGralClaseComprobante() {
		return debitoImputadoGralClaseComprobante;
	}

	public void setDebitoImputadoGralClaseComprobante(
			TipoClaseComprobanteEnum debitoImputadoGralClaseComprobante) {
		this.debitoImputadoGralClaseComprobante = debitoImputadoGralClaseComprobante;
	}

	public String getDebitoImputadoGralSucursalComprobante() {
		return debitoImputadoGralSucursalComprobante;
	}

	public void setDebitoImputadoGralSucursalComprobante(
			String debitoImputadoGralSucursalComprobante) {
		this.debitoImputadoGralSucursalComprobante = debitoImputadoGralSucursalComprobante;
	}

	public String getDebitoImputadoGralNumeroComprobante() {
		return debitoImputadoGralNumeroComprobante;
	}

	public void setDebitoImputadoGralNumeroComprobante(
			String debitoImputadoGralNumeroComprobante) {
		this.debitoImputadoGralNumeroComprobante = debitoImputadoGralNumeroComprobante;
	}

	public String getDebitoImputadoGralNumeroReferenciaMic() {
		return debitoImputadoGralNumeroReferenciaMic;
	}

	public void setDebitoImputadoGralNumeroReferenciaMic(
			String debitoImputadoGralNumeroReferenciaMic) {
		this.debitoImputadoGralNumeroReferenciaMic = debitoImputadoGralNumeroReferenciaMic;
	}

	public Date getDebitoImputadoGralFechaVencimiento() {
		return debitoImputadoGralFechaVencimiento;
	}

	public void setDebitoImputadoGralFechaVencimiento(
			Date debitoImputadoGralFechaVencimiento) {
		this.debitoImputadoGralFechaVencimiento = debitoImputadoGralFechaVencimiento;
	}

	public BigDecimal getDebitoImputadoGralImportePrimerVencimiento() {
		return debitoImputadoGralImportePrimerVencimiento;
	}

	public void setDebitoImputadoGralImportePrimerVencimiento(
			BigDecimal debitoImputadoGralImportePrimerVencimiento) {
		this.debitoImputadoGralImportePrimerVencimiento = debitoImputadoGralImportePrimerVencimiento;
	}

	public BigDecimal getDebitoImputadoGralImporteSegundoVencimiento() {
		return debitoImputadoGralImporteSegundoVencimiento;
	}

	public void setDebitoImputadoGralImporteSegundoVencimiento(
			BigDecimal debitoImputadoGralImporteSegundoVencimiento) {
		this.debitoImputadoGralImporteSegundoVencimiento = debitoImputadoGralImporteSegundoVencimiento;
	}

	public BigDecimal getDebitoImputadoGralSaldoPrimerVencimiento() {
		return debitoImputadoGralSaldoPrimerVencimiento;
	}

	public void setDebitoImputadoGralSaldoPrimerVencimiento(
			BigDecimal debitoImputadoGralSaldoPrimerVencimiento) {
		this.debitoImputadoGralSaldoPrimerVencimiento = debitoImputadoGralSaldoPrimerVencimiento;
	}

	public EstadoAcuerdoFacturacionEnum getDebitoImputadoGralEstadoAcuerdoFacturacion() {
		return debitoImputadoGralEstadoAcuerdoFacturacion;
	}

	public void setDebitoImputadoGralEstadoAcuerdoFacturacion(
			EstadoAcuerdoFacturacionEnum debitoImputadoGralEstadoAcuerdoFacturacion) {
		this.debitoImputadoGralEstadoAcuerdoFacturacion = debitoImputadoGralEstadoAcuerdoFacturacion;
	}

	public EstadoConceptoTercerosEnum getDebitoImputadoGralEstadoConceptoTerceros() {
		return debitoImputadoGralEstadoConceptoTerceros;
	}

	public void setDebitoImputadoGralEstadoConceptoTerceros(
			EstadoConceptoTercerosEnum debitoImputadoGralEstadoConceptoTerceros) {
		this.debitoImputadoGralEstadoConceptoTerceros = debitoImputadoGralEstadoConceptoTerceros;
	}

	public SiNoEnum getDebitoImputadoGralPosibilidadDestransferencia() {
		return debitoImputadoGralPosibilidadDestransferencia;
	}

	public void setDebitoImputadoGralPosibilidadDestransferencia(
			SiNoEnum debitoImputadoGralPosibilidadDestransferencia) {
		this.debitoImputadoGralPosibilidadDestransferencia = debitoImputadoGralPosibilidadDestransferencia;
	}

	public BigDecimal getDebitoImputadoGralImporteTercerosTransferibles() {
		return debitoImputadoGralImporteTercerosTransferibles;
	}

	public void setDebitoImputadoGralImporteTercerosTransferibles(
			BigDecimal debitoImputadoGralImporteTercerosTransferibles) {
		this.debitoImputadoGralImporteTercerosTransferibles = debitoImputadoGralImporteTercerosTransferibles;
	}

	public BigDecimal getDebitoImputadoGralImportePrimerVencimientoConTerceros() {
		return debitoImputadoGralImportePrimerVencimientoConTerceros;
	}

	public void setDebitoImputadoGralImportePrimerVencimientoConTerceros(
			BigDecimal debitoImputadoGralImportePrimerVencimientoConTerceros) {
		this.debitoImputadoGralImportePrimerVencimientoConTerceros = debitoImputadoGralImportePrimerVencimientoConTerceros;
	}

	public BigDecimal getDebitoImputadoGralImporteSegundoVencimientoConTerceros() {
		return debitoImputadoGralImporteSegundoVencimientoConTerceros;
	}

	public void setDebitoImputadoGralImporteSegundoVencimientoConTerceros(
			BigDecimal debitoImputadoGralImporteSegundoVencimientoConTerceros) {
		this.debitoImputadoGralImporteSegundoVencimientoConTerceros = debitoImputadoGralImporteSegundoVencimientoConTerceros;
	}

	public EstadoOrigenEnum getDebitoImputadoGralCodigoEstadoFactura() {
		return debitoImputadoGralCodigoEstadoFactura;
	}

	public void setDebitoImputadoGralCodigoEstadoFactura(
			EstadoOrigenEnum debitoImputadoGralCodigoEstadoFactura) {
		this.debitoImputadoGralCodigoEstadoFactura = debitoImputadoGralCodigoEstadoFactura;
	}

	public MarcaReclamoEnum getDebitoImputadoGralMarcaReclamo() {
		return debitoImputadoGralMarcaReclamo;
	}

	public void setDebitoImputadoGralMarcaReclamo(
			MarcaReclamoEnum debitoImputadoGralMarcaReclamo) {
		this.debitoImputadoGralMarcaReclamo = debitoImputadoGralMarcaReclamo;
	}

	public MarcaCyQEnum getDebitoImputadoGralMarcaCyq() {
		return debitoImputadoGralMarcaCyq;
	}

	public void setDebitoImputadoGralMarcaCyq(
			MarcaCyQEnum debitoImputadoGralMarcaCyq) {
		this.debitoImputadoGralMarcaCyq = debitoImputadoGralMarcaCyq;
	}

	public Date getDebitoImputadoGralFechaEmision() {
		return debitoImputadoGralFechaEmision;
	}

	public void setDebitoImputadoGralFechaEmision(
			Date debitoImputadoGralFechaEmision) {
		this.debitoImputadoGralFechaEmision = debitoImputadoGralFechaEmision;
	}

	public Long getDebitoImputadoGralNumeroConvenio() {
		return debitoImputadoGralNumeroConvenio;
	}

	public void setDebitoImputadoGralNumeroConvenio(
			Long debitoImputadoGralNumeroConvenio) {
		this.debitoImputadoGralNumeroConvenio = debitoImputadoGralNumeroConvenio;
	}

	public Long getDebitoImputadoGralCuota() {
		return debitoImputadoGralCuota;
	}

	public void setDebitoImputadoGralCuota(Long debitoImputadoGralCuota) {
		this.debitoImputadoGralCuota = debitoImputadoGralCuota;
	}

	public Date getDebitoImputadoGralFechaUltimoPagoParcial() {
		return debitoImputadoGralFechaUltimoPagoParcial;
	}

	public void setDebitoImputadoGralFechaUltimoPagoParcial(
			Date debitoImputadoGralFechaUltimoPagoParcial) {
		this.debitoImputadoGralFechaUltimoPagoParcial = debitoImputadoGralFechaUltimoPagoParcial;
	}

	public Date getDebitoImputadoGralFechaPuestaCobro() {
		return debitoImputadoGralFechaPuestaCobro;
	}

	public void setDebitoImputadoGralFechaPuestaCobro(
			Date debitoImputadoGralFechaPuestaCobro) {
		this.debitoImputadoGralFechaPuestaCobro = debitoImputadoGralFechaPuestaCobro;
	}

	public String getDebitoTagetikRazonSocialCliente() {
		return debitoTagetikRazonSocialCliente;
	}

	public void setDebitoTagetikRazonSocialCliente(
			String debitoTagetikRazonSocialCliente) {
		this.debitoTagetikRazonSocialCliente = debitoTagetikRazonSocialCliente;
	}

	public TipoClienteTagetikEnum getDebitoTagetikTipoCliente() {
		return debitoTagetikTipoCliente;
	}

	public void setDebitoTagetikTipoCliente(
			TipoClienteTagetikEnum debitoTagetikTipoCliente) {
		this.debitoTagetikTipoCliente = debitoTagetikTipoCliente;
	}

	public String getDebitoTagetikCuit() {
		return debitoTagetikCuit;
	}

	public void setDebitoTagetikCuit(String debitoTagetikCuit) {
		this.debitoTagetikCuit = debitoTagetikCuit;
	}

	public Integer getDebitoTagetikCicloFacturacion() {
		return debitoTagetikCicloFacturacion;
	}

	public void setDebitoTagetikCicloFacturacion(
			Integer debitoTagetikCicloFacturacion) {
		this.debitoTagetikCicloFacturacion = debitoTagetikCicloFacturacion;
	}

	public String getDebitoTagetikMarketingCustomerGroup() {
		return debitoTagetikMarketingCustomerGroup;
	}

	public void setDebitoTagetikMarketingCustomerGroup(
			String debitoTagetikMarketingCustomerGroup) {
		this.debitoTagetikMarketingCustomerGroup = debitoTagetikMarketingCustomerGroup;
	}

	public TipoFacturaEnum getDebitoTagetikTipoFactura() {
		return debitoTagetikTipoFactura;
	}

	public void setDebitoTagetikTipoFactura(TipoFacturaEnum debitoTagetikTipoFactura) {
		this.debitoTagetikTipoFactura = debitoTagetikTipoFactura;
	}

	public Date getDebitoDakotaFechaVencimientoMora() {
		return debitoDakotaFechaVencimientoMora;
	}

	public void setDebitoDakotaFechaVencimientoMora(
			Date debitoDakotaFechaVencimientoMora) {
		this.debitoDakotaFechaVencimientoMora = debitoDakotaFechaVencimientoMora;
	}

	public String getDebitoDakotaIndicadorPeticionCorte() {
		return debitoDakotaIndicadorPeticionCorte;
	}

	public void setDebitoDakotaIndicadorPeticionCorte(
			String debitoDakotaIndicadorPeticionCorte) {
		this.debitoDakotaIndicadorPeticionCorte = debitoDakotaIndicadorPeticionCorte;
	}

	public String getDebitoDakotaCodigoTarifa() {
		return debitoDakotaCodigoTarifa;
	}

	public void setDebitoDakotaCodigoTarifa(String debitoDakotaCodigoTarifa) {
		this.debitoDakotaCodigoTarifa = debitoDakotaCodigoTarifa;
	}

	public BigDecimal getDebitoSaldosTercerosSaldoTerceroFinanciableNOTransferible() {
		return debitoSaldosTercerosSaldoTerceroFinanciableNOTransferible;
	}

	public void setDebitoSaldosTercerosSaldoTerceroFinanciableNOTransferible(
			BigDecimal debitoSaldosTercerosSaldoTerceroFinanciableNOTransferible) {
		this.debitoSaldosTercerosSaldoTerceroFinanciableNOTransferible = debitoSaldosTercerosSaldoTerceroFinanciableNOTransferible;
	}

	public BigDecimal getDebitoSaldosTercerosSaldoTerceroFinanciableTransferible() {
		return debitoSaldosTercerosSaldoTerceroFinanciableTransferible;
	}

	public void setDebitoSaldosTercerosSaldoTerceroFinanciableTransferible(
			BigDecimal debitoSaldosTercerosSaldoTerceroFinanciableTransferible) {
		this.debitoSaldosTercerosSaldoTerceroFinanciableTransferible = debitoSaldosTercerosSaldoTerceroFinanciableTransferible;
	}

	public BigDecimal getDebitoSaldosTercerosSaldoTerceroNOFinanciableTransferible() {
		return debitoSaldosTercerosSaldoTerceroNOFinanciableTransferible;
	}

	public void setDebitoSaldosTercerosSaldoTerceroNOFinanciableTransferible(
			BigDecimal debitoSaldosTercerosSaldoTerceroNOFinanciableTransferible) {
		this.debitoSaldosTercerosSaldoTerceroNOFinanciableTransferible = debitoSaldosTercerosSaldoTerceroNOFinanciableTransferible;
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

	/**
	 * @return the creditoCodigoCliente
	 */
	public Long getCreditoCodigoCliente() {
		return creditoCodigoCliente;
	}

	/**
	 * @param creditoCodigoCliente the creditoCodigoCliente to set
	 */
	public void setCreditoCodigoCliente(Long creditoCodigoCliente) {
		this.creditoCodigoCliente = creditoCodigoCliente;
	}
}
