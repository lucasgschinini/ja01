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

@Entity
@Table(name = "SHV_MAS_REGISTRO_DESESTIMIENTO")
@PrimaryKeyJoinColumn(name="ID_REGISTRO")
@Inheritance(strategy=InheritanceType.JOINED)
public class ShvMasRegistroDesistimiento extends ShvMasRegistro {

	private static final long serialVersionUID = 1L;
	
	@Column(name="NUMERO_REFERENCIA_FACTURA")
	private Long numeroReferenciaFactura;
	
	@Enumerated(EnumType.STRING)
	@Column(name="DEUDA_MIGRADA") 			
	private SiNoEnum deudaMigrada;

	@Column(name="NUMERO_ACTA_DESISTIMIENTO")
	private String numeroActaDesistimiento;
	
	@Column(name="FECHA_ACTA_DESISTIMIENTO")
	private Date fechaActaDesistimiento;

	/*************************************************************************/
	
// Datos del debito imputado: cliente
	
	@Column(name="DEB_IMP_COD_CLI")
	private Long debitoImputadoCodigoCliente;
	
// Datos del debito imputado: datos generales (foto de los datos previos a la imputacion)
	@Column(name="DEB_IMP_GRAL_CUEN_DEB")
	private Long debitoImputadoGralCuentaDebito;
	
	@Enumerated (EnumType.STRING)
	@Column(name="DEB_IMPUTADO_GRAL_TIPO_DOC")
	private TipoComprobanteEnum debitoImputadoGralTipoDocumento;
	@Enumerated(EnumType.STRING)
	@Column(name="DEB_IMP_GRAL_TIPO_DUC")
	private TipoDUCEnum debitoImputadoGralTipoDuc;
		//	Descripcion Tipo de DUC	Alfanumérico	20
	@Enumerated(EnumType.STRING)
	@Column(name="DEB_IMP_GRAL_ESTADO_DUC")
	private EstadoDUCEnum debitoImputadoGralEstadoDuc;
		//	Descripción Estado del DUC	Alfanumérico	18
	@Column(name="DEB_IMP_GRAL_ACUERDO")
	private String debitoImputadoGralAcuerdo;
	@Enumerated(EnumType.STRING)
	@Column(name="DEB_IMP_GRAL_CLASE_COMPR")
	private TipoClaseComprobanteEnum debitoImputadoGralClaseComprobante;
	@Column(name="DEB_IMP_GRAL_SUC_COMPR")
	private String debitoImputadoGralSucursalComprobante;
	@Column(name="DEB_IMP_GRAL_NUM_COMPR")
	private String debitoImputadoGralNumeroComprobante;
	@Column(name="DEB_IMP_GRAL_NUM_REF_MIC")
	private Long debitoImputadoGralNumeroReferenciaMic;
	@Column(name="DEB_IMP_GRAL_FECHA_VENC")
	private Date debitoImputadoGralFechaVencimiento;
	@Column(name="DEB_IMPU_GRAL_IMP_PRIM_VENC")
	private BigDecimal debitoImputadoGralImportePrimerVencimiento;
	@Column(name="DEB_IMPU_GRAL_IMP_SEG_VENC")
	private BigDecimal debitoImputadoGralImporteSegundoVencimiento;
	@Column(name="DEB_IMPU_GRAL_SAL_PRI_VENC")
	private BigDecimal debitoImputadoGralSaldoPrimerVencimiento;
	@Enumerated(EnumType.STRING)
	@Column(name="DEB_IMPU_GRAL_EST_ACUER_FACT")
	private EstadoAcuerdoFacturacionEnum debitoImputadoGralEstadoAcuerdoFacturacion;
		//	Codigo Estado acuerdo Facturación	Numérico	2
		//	Descripcion Estado acuerdo Factuación	Alfanumérico	10
	//TODO Ver si no es el enum EstadoConceptoTercero
	@Enumerated(EnumType.STRING)
	@Column(name="DEB_IMPU_GRAL_EST_CONC_TER")
	private EstadoConceptoTercerosEnum debitoImputadoGralEstadoConceptoTerceros; //	Estado Conceptos de Terceros	Alfanumérico	1
	@Enumerated(EnumType.STRING)
	@Column(name="DEB_IMPU_GRAL_POS_DEST")
	private SiNoEnum debitoImputadoGralPosibilidadDestransferencia;
	@Column(name="DEB_IMPU_GRAL_IMP_TER_TRANS")
	private BigDecimal debitoImputadoGralImporteTercerosTransferibles;
	@Column(name="DEB_IMPU_IMP_PRI_VENC_C_TER")
	private BigDecimal debitoImputadoGralImportePrimerVencimientoConTerceros;
	@Column(name="DEB_IMPU_IMP_SEG_VENC_c_TER")
	private BigDecimal debitoImputadoGralImporteSegundoVencimientoConTerceros;
	@Enumerated(EnumType.STRING)
	@Column(name="DEB_IMPU_GRAL_COD_EST_FACT")
	private EstadoOrigenEnum debitoImputadoGralCodigoEstadoFactura;
		//	Descripcion Estado Factura	Alfanumérico	20
	@Enumerated(EnumType.STRING)
	@Column(name="DEB_IMPU_GRAL_MARC_RECL")
	private MarcaReclamoEnum debitoImputadoGralMarcaReclamo;
	//	Codigo Marca Reclamo	Alfanumérico	1
	//	Descripcion Marca Reclamo	Alfanumérico	10
	@Enumerated(EnumType.STRING)
	@Column(name="DEB_IMPU_GRAL_MARCA_CYQ")
	private MarcaCyQEnum debitoImputadoGralMarcaCyq;
		//	Codigo Marca C&Q	Alfanumérico	1
		//	Descripcion Marca C&Q	Alfanumérico	8
	@Column(name="DEB_IMPU_GRAL_FECHA_EMIS")
	private Date debitoImputadoGralFechaEmision;
	@Column(name="DEB_IMPU_GRAL_NUM_CONV")
	private Long debitoImputadoGralNumeroConvenio;
	@Column(name="DEB_IMPU_GRAL_CUOTA")
	private Long debitoImputadoGralCuota;
	@Column(name="DEB_IMP_FECHA_ULT_PAGO_PARC")
	private Date debitoImputadoGralFechaUltimoPagoParcial;
	@Column(name="DEB_IMP_FECHA_ULT_PUES_COB")
	private Date debitoImputadoGralFechaPuestaCobro;
	// Datos del debito imputado: tagetik  (foto de los datos previos a la imputacion)
	@Column(name="DEB_TAG_RAZ_SOC_CLI")
	private String debitoTagetikRazonSocialCliente;
	@Enumerated(EnumType.STRING)
	@Column(name="DEB_TAG_TIPO_CLI")
	private TipoClienteTagetikEnum debitoTagetikTipoCliente;
	//	Tipo de Cliente	Alfanumérico	2
	@Column(name="DEB_TAG_CUIT")
	private String debitoTagetikCuit;
	@Column(name="DEB_TAG_CICLO_FACT")
	private Integer debitoTagetikCicloFacturacion;
	@Column(name="DEB_TAG_MARK_CUSTOM_GROUP")
	private String debitoTagetikMarketingCustomerGroup;
	@Enumerated(EnumType.STRING)
	@Column(name="DEB_TAG_TIPO_FACT")
	private TipoFacturaEnum debitoTagetikTipoFactura;
	// Datos del debito imputado: Dacota  (foto de los datos previos a la imputacion)
	@Column(name="DEB_DAK_FECHA_VENC_MORA")
	private Date debitoDakotaFechaVencimientoMora;
	@Column(name="DEB_DAK_INDIC_PETIC_CORTE")
	private String debitoDakotaIndicadorPeticionCorte;
	@Column(name="DEB_DAK_COD_TARIFA")
	private String debitoDakotaCodigoTarifa;
	// Datos del debito imputado: Saldos de terceros  (foto de los datos previos a la imputacion)
	@Column(name="DEB_SALD_TER_FIN_N_TRANSF")
	private BigDecimal debitoSaldosTercerosSaldoTerceroFinanciableNOTransferible;
	@Column(name="DEB_SALD_TER_FIN_TRANSF")
	private BigDecimal debitoSaldosTercerosSaldoTerceroFinanciableTransferible;
	@Column(name="DEB_SALD_TER_NO_FIN_TRANSF")
	private BigDecimal debitoSaldosTercerosSaldoTerceroNOFinanciableTransferible;
	// Datos del acuerdo de intereses por mora / acuerdo del reintegro: datos del cliente
	@Column(name="ACUER_DAT_CLI_COD_CLI")
	private Long acuerdoDatosClientecodigoCliente;
	
	/*************************************************************************/
	
	public Long getNumeroReferenciaFactura() {
		return numeroReferenciaFactura;
	}

	public void setNumeroReferenciaFactura(Long numeroReferenciaFactura) {
		this.numeroReferenciaFactura = numeroReferenciaFactura;
	}

	public SiNoEnum getDeudaMigrada() {
		return deudaMigrada;
	}

	public void setDeudaMigrada(SiNoEnum deudaMigrada) {
		this.deudaMigrada = deudaMigrada;
	}

	public String getNumeroActaDesistimiento() {
		return numeroActaDesistimiento;
	}

	public void setNumeroActaDesistimiento(String numeroActaDesistimiento) {
		this.numeroActaDesistimiento = numeroActaDesistimiento;
	}

	public Date getFechaActaDesistimiento() {
		return fechaActaDesistimiento;
	}

	public void setFechaActaDesistimiento(Date fechaActaDesistimiento) {
		this.fechaActaDesistimiento = fechaActaDesistimiento;
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

	public Long getDebitoImputadoGralNumeroReferenciaMic() {
		return debitoImputadoGralNumeroReferenciaMic;
	}

	public void setDebitoImputadoGralNumeroReferenciaMic(
			Long debitoImputadoGralNumeroReferenciaMic) {
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

	public Long getAcuerdoDatosClientecodigoCliente() {
		return acuerdoDatosClientecodigoCliente;
	}

	public void setAcuerdoDatosClientecodigoCliente(
			Long acuerdoDatosClientecodigoCliente) {
		this.acuerdoDatosClientecodigoCliente = acuerdoDatosClientecodigoCliente;
	}
	
}
