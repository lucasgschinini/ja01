package ar.com.telecom.shiva.persistencia.modelo;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import ar.com.telecom.shiva.base.enumeradores.EstadoConceptoTercerosEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoDUCEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoDebitoImportadoEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoOrigenEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoTramiteDeimosEnum;
import ar.com.telecom.shiva.base.enumeradores.MarcaCyQEnum;
import ar.com.telecom.shiva.base.enumeradores.MarcaReclamoEnum;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.OrigenDebitoEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.SociedadEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoClaseComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoDUCEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoFacturaEnum;

@Entity
@Table(name = "SHV_COB_ED_DEBITO")
public class ShvCobEdDebito extends Modelo {
	private static final long serialVersionUID = 1L;

	@Id
	private ShvCobEdDebitoPk pk;

	@Column(name = "ID_DEBITO_REFERENCIA")
	private String idDebitoReferencia;
		
	@Column(name = "ID_CLIENTE_LEGADO")
	private Long idClienteLegado;

	@Enumerated(EnumType.STRING)
	@Column(name = "TIPO_COMPROBANTE", nullable=true)
	private TipoComprobanteEnum tipoComprobante;

	@Enumerated(EnumType.STRING)
	@Column(name = "CLASE_COMPROBANTE", nullable=true)
	private TipoClaseComprobanteEnum claseComprobante;

	@Column(name = "SUCURSAL_COMPROBANTE")
	private String sucursalComprobante;

	@Column(name = "NUMERO_COMPROBANTE")
	private String numeroComprobante;

	@Enumerated(EnumType.STRING)
	@Column(name = "SISTEMA_ORIGEN", nullable=true)
	private SistemaEnum sistemaOrigen;

	@Enumerated(EnumType.STRING)
	@Column(name = "CHECK_COBRAR_SEG_VENCIMIENTO", nullable=true)
	private SiNoEnum checkCobrarSegVencimiento;

	@Enumerated(EnumType.STRING)
	@Column(name = "CHECK_DESTRANFERIR_TERCEROS", nullable=true)
	private SiNoEnum checkDestranferirTerceros;

	@Enumerated(EnumType.STRING)
	@Column(name = "CHECK_SIN_DIFERENCIA_CAMBIO", nullable=true)
	private SiNoEnum checkSinDiferenciaCambio;

	@Column(name = "ACUERDO_FACTURACION")
	private String acuerdoFacturacion;

	@Enumerated(EnumType.STRING)
	@Column(name = "ESTADO_ORIGEN", nullable=true)
	private EstadoOrigenEnum estadoOrigen;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "MARCA_CYQ", nullable=true)
	private MarcaCyQEnum marcaCyq;

	@Enumerated(EnumType.STRING)
	@Column(name = "MARCA_RECLAMO", nullable=true)
	private MarcaReclamoEnum marcaReclamo;// MIC - CALIPSO

	@Column(name = "CUENTA")
	private Long cuenta;

	@Column(name = "CUOTA")
	private Long cuota;

	@Column(name = "ESTADO_ACUERDO_FACTURACION", nullable=true)
	private String estadoAcuerdoFacturacion;

	@Enumerated(EnumType.STRING)
	@Column(name = "ESTADO_CONCEPTO_TERCEROS", nullable=true)
	private EstadoConceptoTercerosEnum estadoConceptoTerceros;

	@Enumerated(EnumType.STRING)
	@Column(name = "ESTADO_DUC", nullable=true)
	private EstadoDUCEnum estadoDuc;

	@Column(name = "FECHA_EMISION")
	private Date fechaEmision;

	@Column(name = "FECHA_PUESTA_COBRO")
	private Date fechaPuestaCobro;

	@Column(name = "FECHA_ULTIMO_PAGO_PARCIAL")
	private Date fechaUltimoPagoParcial;

	@Column(name = "FECHA_VENCIMIENTO")
	private Date fechaVencimiento;

	@Column(name = "ID_DOC_CUENTA_COBRANZA")
	private BigDecimal idDocCuentaCobranza;

	@Column(name = "IMPORTE_A_COBRAR")
	private BigDecimal importeACobrar;

	@Column(name = "IMPORTE_PRI_VENC_MONEDA_ORIGEN")
	private BigDecimal importePriVencMonedaOrigen;

	@Column(name = "IMPORTE_PRI_VENC_PESIFICADO")
	private BigDecimal importePriVencPesificado;

	@Column(name = "IMPORTE_PRI_VENC_TERCEROS")
	private BigDecimal importePriVencTerceros;

	@Column(name = "IMPORTE_SEG_VENC")
	private BigDecimal importeSegVenc;

	@Column(name = "IMPORTE_SEG_VENC_TERCEROS")
	private BigDecimal importeSegVencTerceros;

	@Column(name = "IMPORTE_TERCEROS_TRANSFERIDOS")
	private BigDecimal importeTercerosTransferidos;

	@Enumerated(EnumType.STRING)
	@Column(name = "MARCA_MIGRADO_DEIMOS", nullable=true)
	private SiNoEnum marcaMigradoDeimos; //CALIPSO

	@Enumerated(EnumType.STRING)
	@Column(name = "MONEDA")
	private MonedaEnum moneda;

	@Column(name = "NUMERO_CONVENIO")
	private Long numeroConvenio;  //MIC

	@Column(name = "NUMERO_REFERENCIA_MIC")
	private Long numeroReferenciaMic;

	@Column(name = "NUMERO_REFERENCIA_DUC")
	private String numeroReferenciaDuc;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "POSIBILIDAD_DESTRANSFERENCIA", nullable=true)
	private SiNoEnum posibilidadDestransferencia; //MIC

	@Column(name = "SALDO_PESIFICADO")
	private BigDecimal saldoPesificado;

	@Column(name = "SALDO_PRI_VENC_MONEDA_ORIGEN")
	private BigDecimal saldoPriVencMonedaOrigen;

	@Column(name = "TIPO_DE_CAMBIO")
	private BigDecimal tipoDeCambio;

	@Enumerated(EnumType.STRING)
	@Column(name = "TIPO_DUC", nullable=true)
	private TipoDUCEnum tipoDuc;

	@Enumerated(EnumType.STRING)
	@Column(name = "MARCA_APROPIADO_EN_DEIMOS", nullable=true)
	private SiNoEnum marcaApropiarDeimos;//MIC

	@Column(name = "ORIGEN")
	private OrigenDebitoEnum origen;
	
	@Column(name = "SUBTIPO_DOCUMENTO")
	private String subtipo;
	
	/************ DATOS ADICIONALES PARA TAGETIK ***************/

	@Column(name = "RAZON_SOCIAL_CLIENTE")
	private String razonSocialCliente;
	
	@Column(name = "TIPO_CLIENTE")
	private String tipoCliente;
	
	@Column(name = "CUIT")
	private String cuit;
	
	@Column(name = "CICLO_FACTURACION")
	private Integer cicloFacturacion;

	@Column(name = "MARKETING_CUSTOMER_GROUP")
	private String marketingCustomerGroup;

	@Enumerated(EnumType.STRING)
	@Column(name = "TIPO_FACTURA", nullable=true)
	private TipoFacturaEnum tipoFactura;
	
	/************ DATOS SALDOS ***************/
	@Column(name = "SALDO_TER_FINANCIA_TRANSF")
	private BigDecimal saldoTerceroFinanciableTransferible;
	
	@Column(name = "SALDO_TER_FINANCIA_NO_TRANSF")
	private BigDecimal saldoTerceroFinanciableNOTransferible;
	
	@Column(name = "SALDO_TER_NO_FINANCIA_TRANSF")
	private BigDecimal saldoTerceroNOFinanciableTransferible;
	
	/************ DATOS ADICIONALES PARA DAKOTA ***************/
	@Column(name = "UNIDAD_OPERATIVA")
	private String unidadOperativa;
	
	@Column(name = "SUBTIPO")
	private String subtipoDakota;
	
	@Column(name = "HOLDING")
	private String holding;
	
	@Column(name = "FECHA_VENCIMIENTO_MORA")
	private Date fechaVencimientoMora;

	@Column(name = "INDICADOR_PETICION_CORTE")
	private String indicadorPeticionCorte;

	@Column(name = "CODIGO_TARIFA")
	private String codigoTarifa;
	
	/************ DATOS ADICIONALES PARA DEIMOS ***************/

	@Enumerated(EnumType.STRING)
	@Column(name = "DMOS_APROPIADO_EN_DEIMOS", nullable=true)
	private SiNoEnum dmosApropiacionEnDeimos;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "DMOS_ESTADO_TRAMITE", nullable=true)
	private EstadoTramiteDeimosEnum dmosEstadoTramite;
	
	@Column(name = "DMOS_CANTIDAD_DE_CUOTAS")
	private Long dmosCantidadDeCuotas;
	
	@Column(name = "DMOS_CANTIDAD_DE_CUOTAS_PAGAS")
	private Long dmosCantidadDeCuotasPagas;

	@Column(name = "DMOS_NUMERO_CONVENIO")
	private Long dmosNumeroConvenio;  
	
	/************ RESULTADOS DE VALIDACION ***************/

	@Enumerated(EnumType.STRING)
	@Column(name = "RESULTADO_VALIDACION_ESTADO")
	private EstadoDebitoImportadoEnum resultadoValidacionEstado;
	
	@Column(name = "RESULTADO_VALIDACION_COD_ERR")
	private String resultadoValidacionCodigoError;
	
	@Column(name = "RESULTADO_VALIDACION_DES_ERR")
	private String resultadoValidacionDescripcionError;
	
	@Enumerated(EnumType.STRING)
	@Column(name="MONEDA_IMPORTE_A_COBRAR")
	private MonedaEnum monedaImporteACobrar;
	
	@Enumerated(EnumType.STRING)
	@Column(name="HABILITAR_EDICION_SIN_DIF_CBIO")
	private SiNoEnum habilitarEdicionSinDifCambio;
	
	@Column(name = "FECHA_TIPO_CAMBIO")
	private Date fechaTipoCambio;
	
	@Column(name = "IMP_1ER_VENC_PESIF_FECH_COTI") 
	private BigDecimal importe1erVencimientoPesificadoFechaCotizacion;
	
	@Column(name = "SALDO_PESIF_FECHA_COTIZACION")
	private BigDecimal SaldoPesificadoFechaCotizacion;
	
	@Column(name = "TIPO_CAMBIO_FECHA_COTIZACION")
	private BigDecimal tipoCambioFechaCotizacion;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "SOCIEDAD", nullable=false)
	private SociedadEnum sociedad;
	
	/**
	 * 
	 */
	public ShvCobEdDebito() {
	}

	/**
	 * @return the idClienteLegado
	 */
	public Long getIdClienteLegado() {
		return idClienteLegado;
	}

	/**
	 * @param idClienteLegado the idClienteLegado to set
	 */
	public void setIdClienteLegado(Long idClienteLegado) {
		this.idClienteLegado = idClienteLegado;
	}

	/**
	 * @return the tipoComprobante
	 */
	public TipoComprobanteEnum getTipoComprobante() {
		return tipoComprobante;
	}

	/**
	 * @param tipoComprobante the tipoComprobante to set
	 */
	public void setTipoComprobante(TipoComprobanteEnum tipoComprobante) {
		this.tipoComprobante = tipoComprobante;
	}

	/**
	 * @return the claseComprobante
	 */
	public TipoClaseComprobanteEnum getClaseComprobante() {
		return claseComprobante;
	}

	/**
	 * @param claseComprobante the claseComprobante to set
	 */
	public void setClaseComprobante(TipoClaseComprobanteEnum claseComprobante) {
		this.claseComprobante = claseComprobante;
	}

	/**
	 * @return the sucursalComprobante
	 */
	public String getSucursalComprobante() {
		return sucursalComprobante;
	}

	/**
	 * @param sucursalComprobante the sucursalComprobante to set
	 */
	public void setSucursalComprobante(String sucursalComprobante) {
		this.sucursalComprobante = sucursalComprobante;
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
	 * @return the checkCobrarSegVencimiento
	 */
	public SiNoEnum getCheckCobrarSegVencimiento() {
		return checkCobrarSegVencimiento;
	}

	/**
	 * @param checkCobrarSegVencimiento the checkCobrarSegVencimiento to set
	 */
	public void setCheckCobrarSegVencimiento(SiNoEnum checkCobrarSegVencimiento) {
		this.checkCobrarSegVencimiento = checkCobrarSegVencimiento;
	}

	/**
	 * @return the checkDestranferirTerceros
	 */
	public SiNoEnum getCheckDestranferirTerceros() {
		return checkDestranferirTerceros;
	}

	/**
	 * @param checkDestranferirTerceros the checkDestranferirTerceros to set
	 */
	public void setCheckDestranferirTerceros(SiNoEnum checkDestranferirTerceros) {
		this.checkDestranferirTerceros = checkDestranferirTerceros;
	}

	/**
	 * @return the checkSinDiferenciaCambio
	 */
	public SiNoEnum getCheckSinDiferenciaCambio() {
		return checkSinDiferenciaCambio;
	}

	/**
	 * @param checkSinDiferenciaCambio the checkSinDiferenciaCambio to set
	 */
	public void setCheckSinDiferenciaCambio(SiNoEnum checkSinDiferenciaCambio) {
		this.checkSinDiferenciaCambio = checkSinDiferenciaCambio;
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
	 * @return the marcaCyq
	 */
	public MarcaCyQEnum getMarcaCyq() {
		return marcaCyq;
	}

	/**
	 * @param marcaCyq the marcaCyq to set
	 */
	public void setMarcaCyq(MarcaCyQEnum marcaCyq) {
		this.marcaCyq = marcaCyq;
	}

	/**
	 * @return the marcaReclamo
	 */
	public MarcaReclamoEnum getMarcaReclamo() {
		return marcaReclamo;
	}

	/**
	 * @param marcaReclamo the marcaReclamo to set
	 */
	public void setMarcaReclamo(MarcaReclamoEnum marcaReclamo) {
		this.marcaReclamo = marcaReclamo;
	}

	/**
	 * @return the estadoAcuerdoFacturacion
	 */
	public String getEstadoAcuerdoFacturacion() {
		return estadoAcuerdoFacturacion;
	}

	/**
	 * @param estadoAcuerdoFacturacion the estadoAcuerdoFacturacion to set
	 */
	public void setEstadoAcuerdoFacturacion(
			String estadoAcuerdoFacturacion) {
		this.estadoAcuerdoFacturacion = estadoAcuerdoFacturacion;
	}

	/**
	 * @return the estadoConceptoTerceros
	 */
	public EstadoConceptoTercerosEnum getEstadoConceptoTerceros() {
		return estadoConceptoTerceros;
	}

	/**
	 * @param estadoConceptoTerceros the estadoConceptoTerceros to set
	 */
	public void setEstadoConceptoTerceros(EstadoConceptoTercerosEnum estadoConceptoTerceros) {
		this.estadoConceptoTerceros = estadoConceptoTerceros;
	}

	/**
	 * @return the estadoDuc
	 */
	public EstadoDUCEnum getEstadoDuc() {
		return estadoDuc;
	}

	/**
	 * @param estadoDuc the estadoDuc to set
	 */
	public void setEstadoDuc(EstadoDUCEnum estadoDuc) {
		this.estadoDuc = estadoDuc;
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

	/**
	 * @return the fechaPuestaCobro
	 */
	public Date getFechaPuestaCobro() {
		return fechaPuestaCobro;
	}

	/**
	 * @param fechaPuestaCobro the fechaPuestaCobro to set
	 */
	public void setFechaPuestaCobro(Date fechaPuestaCobro) {
		this.fechaPuestaCobro = fechaPuestaCobro;
	}

	/**
	 * @return the fechaUltimoPagoParcial
	 */
	public Date getFechaUltimoPagoParcial() {
		return fechaUltimoPagoParcial;
	}

	/**
	 * @param fechaUltimoPagoParcial the fechaUltimoPagoParcial to set
	 */
	public void setFechaUltimoPagoParcial(Date fechaUltimoPagoParcial) {
		this.fechaUltimoPagoParcial = fechaUltimoPagoParcial;
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
	 * @return the idDocCuentaCobranza
	 */
	public BigDecimal getIdDocCuentaCobranza() {
		return idDocCuentaCobranza;
	}

	/**
	 * @param idDocCuentaCobranza the idDocCuentaCobranza to set
	 */
	public void setIdDocCuentaCobranza(BigDecimal idDocCuentaCobranza) {
		this.idDocCuentaCobranza = idDocCuentaCobranza;
	}

	/**
	 * @return the importeACobrar
	 */
	public BigDecimal getImporteACobrar() {
		return importeACobrar;
	}

	/**
	 * @param importeACobrar the importeACobrar to set
	 */
	public void setImporteACobrar(BigDecimal importeACobrar) {
		this.importeACobrar = importeACobrar;
	}

	/**
	 * @return the importePriVencMonedaOrigen
	 */
	public BigDecimal getImportePriVencMonedaOrigen() {
		return importePriVencMonedaOrigen;
	}

	/**
	 * @param importePriVencMonedaOrigen the importePriVencMonedaOrigen to set
	 */
	public void setImportePriVencMonedaOrigen(BigDecimal importePriVencMonedaOrigen) {
		this.importePriVencMonedaOrigen = importePriVencMonedaOrigen;
	}

	/**
	 * @return the importePriVencPesificado
	 */
	public BigDecimal getImportePriVencPesificado() {
		return importePriVencPesificado;
	}

	/**
	 * @param importePriVencPesificado the importePriVencPesificado to set
	 */
	public void setImportePriVencPesificado(BigDecimal importePriVencPesificado) {
		this.importePriVencPesificado = importePriVencPesificado;
	}

	/**
	 * @return the importePriVencTerceros
	 */
	public BigDecimal getImportePriVencTerceros() {
		return importePriVencTerceros;
	}

	/**
	 * @param importePriVencTerceros the importePriVencTerceros to set
	 */
	public void setImportePriVencTerceros(BigDecimal importePriVencTerceros) {
		this.importePriVencTerceros = importePriVencTerceros;
	}

	/**
	 * @return the importeSegVenc
	 */
	public BigDecimal getImporteSegVenc() {
		return importeSegVenc;
	}

	/**
	 * @param importeSegVenc the importeSegVenc to set
	 */
	public void setImporteSegVenc(BigDecimal importeSegVenc) {
		this.importeSegVenc = importeSegVenc;
	}

	/**
	 * @return the importeSegVencTerceros
	 */
	public BigDecimal getImporteSegVencTerceros() {
		return importeSegVencTerceros;
	}

	/**
	 * @param importeSegVencTerceros the importeSegVencTerceros to set
	 */
	public void setImporteSegVencTerceros(BigDecimal importeSegVencTerceros) {
		this.importeSegVencTerceros = importeSegVencTerceros;
	}

	/**
	 * @return the importeTercerosTransferidos
	 */
	public BigDecimal getImporteTercerosTransferidos() {
		return importeTercerosTransferidos;
	}

	/**
	 * @param importeTercerosTransferidos the importeTercerosTransferidos to set
	 */
	public void setImporteTercerosTransferidos(
			BigDecimal importeTercerosTransferidos) {
		this.importeTercerosTransferidos = importeTercerosTransferidos;
	}

	/**
	 * @return the marcaMigradoDeimos
	 */
	public SiNoEnum getMarcaMigradoDeimos() {
		return marcaMigradoDeimos;
	}

	/**
	 * @param marcaMigradoDeimos the marcaMigradoDeimos to set
	 */
	public void setMarcaMigradoDeimos(SiNoEnum marcaMigradoDeimos) {
		this.marcaMigradoDeimos = marcaMigradoDeimos;
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
	 * @return the numeroConvenio
	 */
	public Long getNumeroConvenio() {
		return numeroConvenio;
	}

	/**
	 * @param numeroConvenio the numeroConvenio to set
	 */
	public void setNumeroConvenio(Long numeroConvenio) {
		this.numeroConvenio = numeroConvenio;
	}

	/**
	 * @return the numeroReferenciaMic
	 */
	public Long getNumeroReferenciaMic() {
		return numeroReferenciaMic;
	}

	/**
	 * @param numeroReferenciaMic the numeroReferenciaMic to set
	 */
	public void setNumeroReferenciaMic(Long numeroReferenciaMic) {
		this.numeroReferenciaMic = numeroReferenciaMic;
	}

	/**
	 * @return the posibilidadDestransferencia
	 */
	public SiNoEnum getPosibilidadDestransferencia() {
		return posibilidadDestransferencia;
	}

	/**
	 * @param posibilidadDestransferencia the posibilidadDestransferencia to set
	 */
	public void setPosibilidadDestransferencia(SiNoEnum posibilidadDestransferencia) {
		this.posibilidadDestransferencia = posibilidadDestransferencia;
	}

	/**
	 * @return the saldoPesificado
	 */
	public BigDecimal getSaldoPesificado() {
		return saldoPesificado;
	}

	/**
	 * @param saldoPesificado the saldoPesificado to set
	 */
	public void setSaldoPesificado(BigDecimal saldoPesificado) {
		this.saldoPesificado = saldoPesificado;
	}

	/**
	 * @return the saldoPriVencMonedaOrigen
	 */
	public BigDecimal getSaldoPriVencMonedaOrigen() {
		return saldoPriVencMonedaOrigen;
	}

	/**
	 * @param saldoPriVencMonedaOrigen the saldoPriVencMonedaOrigen to set
	 */
	public void setSaldoPriVencMonedaOrigen(BigDecimal saldoPriVencMonedaOrigen) {
		this.saldoPriVencMonedaOrigen = saldoPriVencMonedaOrigen;
	}

	/**
	 * @return the tipoDeCambio
	 */
	public BigDecimal getTipoDeCambio() {
		return tipoDeCambio;
	}

	/**
	 * @param tipoDeCambio the tipoDeCambio to set
	 */
	public void setTipoDeCambio(BigDecimal tipoDeCambio) {
		this.tipoDeCambio = tipoDeCambio;
	}

	/**
	 * @return the tipoDuc
	 */
	public TipoDUCEnum getTipoDuc() {
		return tipoDuc;
	}

	/**
	 * @param tipoDuc the tipoDuc to set
	 */
	public void setTipoDuc(TipoDUCEnum tipoDuc) {
		this.tipoDuc = tipoDuc;
	}

	/**
	 * @return the marcaApropiarDeimos
	 */
	public SiNoEnum getMarcaApropiarDeimos() {
		return marcaApropiarDeimos;
	}

	/**
	 * @param marcaApropiarDeimos the marcaApropiarDeimos to set
	 */
	public void setMarcaApropiarDeimos(SiNoEnum marcaApropiarDeimos) {
		this.marcaApropiarDeimos = marcaApropiarDeimos;
	}

	/**
	 * @return the origen
	 */
	public OrigenDebitoEnum getOrigen() {
		return origen;
	}

	/**
	 * @param origen the origen to set
	 */
	public void setOrigen(OrigenDebitoEnum origen) {
		this.origen = origen;
	}

	/**
	 * @return the razonSocialCliente
	 */
	public String getRazonSocialCliente() {
		return razonSocialCliente;
	}

	/**
	 * @param razonSocialCliente the razonSocialCliente to set
	 */
	public void setRazonSocialCliente(String razonSocialCliente) {
		this.razonSocialCliente = razonSocialCliente;
	}

	/**
	 * @return the tipoCliente
	 */
	public String getTipoCliente() {
		return tipoCliente;
	}

	/**
	 * @param tipoCliente the tipoCliente to set
	 */
	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
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
	 * @return the cicloFacturacion
	 */
	public Integer getCicloFacturacion() {
		return cicloFacturacion;
	}

	/**
	 * @param cicloFacturacion the cicloFacturacion to set
	 */
	public void setCicloFacturacion(Integer cicloFacturacion) {
		this.cicloFacturacion = cicloFacturacion;
	}

	/**
	 * @return the marketingCustomerGroup
	 */
	public String getMarketingCustomerGroup() {
		return marketingCustomerGroup;
	}

	/**
	 * @param marketingCustomerGroup the marketingCustomerGroup to set
	 */
	public void setMarketingCustomerGroup(String marketingCustomerGroup) {
		this.marketingCustomerGroup = marketingCustomerGroup;
	}

	/**
	 * @return the tipoFactura
	 */
	public TipoFacturaEnum getTipoFactura() {
		return tipoFactura;
	}

	/**
	 * @param tipoFactura the tipoFactura to set
	 */
	public void setTipoFactura(TipoFacturaEnum tipoFactura) {
		this.tipoFactura = tipoFactura;
	}

	/**
	 * @return the unidadOperativa
	 */
	public String getUnidadOperativa() {
		return unidadOperativa;
	}

	/**
	 * @param unidadOperativa the unidadOperativa to set
	 */
	public void setUnidadOperativa(String unidadOperativa) {
		this.unidadOperativa = unidadOperativa;
	}

	/**
	 * @return the subtipo
	 */
	public String getSubtipo() {
		return subtipo;
	}

	/**
	 * @param subtipo the subtipo to set
	 */
	public void setSubtipo(String subtipo) {
		this.subtipo = subtipo;
	}

	/**
	 * @return the holding
	 */
	public String getHolding() {
		return holding;
	}

	/**
	 * @param holding the holding to set
	 */
	public void setHolding(String holding) {
		this.holding = holding;
	}

	/**
	 * @return the fechaVencimientoMora
	 */
	public Date getFechaVencimientoMora() {
		return fechaVencimientoMora;
	}

	/**
	 * @param fechaVencimientoMora the fechaVencimientoMora to set
	 */
	public void setFechaVencimientoMora(Date fechaVencimientoMora) {
		this.fechaVencimientoMora = fechaVencimientoMora;
	}

	/**
	 * @return the indicadorPeticionCorte
	 */
	public String getIndicadorPeticionCorte() {
		return indicadorPeticionCorte;
	}

	/**
	 * @param indicadorPeticionCorte the indicadorPeticionCorte to set
	 */
	public void setIndicadorPeticionCorte(String indicadorPeticionCorte) {
		this.indicadorPeticionCorte = indicadorPeticionCorte;
	}

	/**
	 * @return the codigoTarifa
	 */
	public String getCodigoTarifa() {
		return codigoTarifa;
	}

	/**
	 * @param codigoTarifa the codigoTarifa to set
	 */
	public void setCodigoTarifa(String codigoTarifa) {
		this.codigoTarifa = codigoTarifa;
	}

	/**
	 * @return the dmosApropiacionEnDeimos
	 */
	public SiNoEnum getDmosApropiacionEnDeimos() {
		return dmosApropiacionEnDeimos;
	}

	/**
	 * @param dmosApropiacionEnDeimos the dmosApropiacionEnDeimos to set
	 */
	public void setDmosApropiacionEnDeimos(SiNoEnum dmosApropiacionEnDeimos) {
		this.dmosApropiacionEnDeimos = dmosApropiacionEnDeimos;
	}

	/**
	 * @return the dmosEstadoTramite
	 */
	public EstadoTramiteDeimosEnum getDmosEstadoTramite() {
		return dmosEstadoTramite;
	}

	/**
	 * @param dmosEstadoTramite the dmosEstadoTramite to set
	 */
	public void setDmosEstadoTramite(EstadoTramiteDeimosEnum dmosEstadoTramite) {
		this.dmosEstadoTramite = dmosEstadoTramite;
	}

	/**
	 * @return the dmosCantidadDeCuotas
	 */
	public Long getDmosCantidadDeCuotas() {
		return dmosCantidadDeCuotas;
	}

	/**
	 * @param dmosCantidadDeCuotas the dmosCantidadDeCuotas to set
	 */
	public void setDmosCantidadDeCuotas(Long dmosCantidadDeCuotas) {
		this.dmosCantidadDeCuotas = dmosCantidadDeCuotas;
	}

	/**
	 * @return the dmosCantidadDeCuotasPagas
	 */
	public Long getDmosCantidadDeCuotasPagas() {
		return dmosCantidadDeCuotasPagas;
	}

	/**
	 * @param dmosCantidadDeCuotasPagas the dmosCantidadDeCuotasPagas to set
	 */
	public void setDmosCantidadDeCuotasPagas(Long dmosCantidadDeCuotasPagas) {
		this.dmosCantidadDeCuotasPagas = dmosCantidadDeCuotasPagas;
	}

	/**
	 * @return the dmosNumeroConvenio
	 */
	public Long getDmosNumeroConvenio() {
		return dmosNumeroConvenio;
	}

	/**
	 * @param dmosNumeroConvenio the dmosNumeroConvenio to set
	 */
	public void setDmosNumeroConvenio(Long dmosNumeroConvenio) {
		this.dmosNumeroConvenio = dmosNumeroConvenio;
	}

	/**
	 * @return the resultadoValidacionEstado
	 */
	public EstadoDebitoImportadoEnum getResultadoValidacionEstado() {
		return resultadoValidacionEstado;
	}

	/**
	 * @param resultadoValidacionEstado the resultadoValidacionEstado to set
	 */
	public void setResultadoValidacionEstado(EstadoDebitoImportadoEnum resultadoValidacionEstado) {
		this.resultadoValidacionEstado = resultadoValidacionEstado;
	}

	/**
	 * @return the resultadoValidacionCodigoError
	 */
	public String getResultadoValidacionCodigoError() {
		return resultadoValidacionCodigoError;
	}

	/**
	 * @param resultadoValidacionCodigoError the resultadoValidacionCodigoError to set
	 */
	public void setResultadoValidacionCodigoError(
			String resultadoValidacionCodigoError) {
		this.resultadoValidacionCodigoError = resultadoValidacionCodigoError;
	}

	/**
	 * @return the resultadoValidacionDescripcionError
	 */
	public String getResultadoValidacionDescripcionError() {
		return resultadoValidacionDescripcionError;
	}

	/**
	 * @param resultadoValidacionDescripcionError the resultadoValidacionDescripcionError to set
	 */
	public void setResultadoValidacionDescripcionError(
			String resultadoValidacionDescripcionError) {
		this.resultadoValidacionDescripcionError = resultadoValidacionDescripcionError;
	}

	public SistemaEnum getSistemaOrigen() {
		return sistemaOrigen;
	}

	public void setSistemaOrigen(SistemaEnum sistemaOrigen) {
		this.sistemaOrigen = sistemaOrigen;
	}

	public ShvCobEdDebitoPk getPk() {
		return pk;
	}

	public void setPk(ShvCobEdDebitoPk pk) {
		this.pk = pk;
	}

	public Long getCuenta() {
		return cuenta;
	}

	public void setCuenta(Long cuenta) {
		this.cuenta = cuenta;
	}

	public Long getCuota() {
		return cuota;
	}

	public void setCuota(Long cuota) {
		this.cuota = cuota;
	}

	public EstadoOrigenEnum getEstadoOrigen() {
		return estadoOrigen;
	}

	public void setEstadoOrigen(EstadoOrigenEnum estadoOrigen) {
		this.estadoOrigen = estadoOrigen;
	}

	public String getNumeroReferenciaDuc() {
		return numeroReferenciaDuc;
	}

	public void setNumeroReferenciaDuc(String numeroReferenciaDuc) {
		this.numeroReferenciaDuc = numeroReferenciaDuc;
	}

	public BigDecimal getSaldoTerceroFinanciableTransferible() {
		return saldoTerceroFinanciableTransferible;
	}

	public void setSaldoTerceroFinanciableTransferible(
			BigDecimal saldoTerceroFinanciableTransferible) {
		this.saldoTerceroFinanciableTransferible = saldoTerceroFinanciableTransferible;
	}

	public BigDecimal getSaldoTerceroFinanciableNOTransferible() {
		return saldoTerceroFinanciableNOTransferible;
	}

	public void setSaldoTerceroFinanciableNOTransferible(
			BigDecimal saldoTerceroFinanciableNOTransferible) {
		this.saldoTerceroFinanciableNOTransferible = saldoTerceroFinanciableNOTransferible;
	}

	public BigDecimal getSaldoTerceroNOFinanciableTransferible() {
		return saldoTerceroNOFinanciableTransferible;
	}

	public void setSaldoTerceroNOFinanciableTransferible(
			BigDecimal saldoTerceroNOFinanciableTransferible) {
		this.saldoTerceroNOFinanciableTransferible = saldoTerceroNOFinanciableTransferible;
	}

	public String getSubtipoDakota() {
		return subtipoDakota;
	}

	public void setSubtipoDakota(String subtipoDakota) {
		this.subtipoDakota = subtipoDakota;
	}

	public String getIdDebitoReferencia() {
		return idDebitoReferencia;
	}

	public void setIdDebitoReferencia(String idDebitoReferencia) {
		this.idDebitoReferencia = idDebitoReferencia;
	}

	public SiNoEnum getHabilitarEdicionSinDifCambio() {
		return habilitarEdicionSinDifCambio;
	}

	public void setHabilitarEdicionSinDifCambio(
			SiNoEnum habilitarEdicionSinDifCambio) {
		this.habilitarEdicionSinDifCambio = habilitarEdicionSinDifCambio;
	}

	public MonedaEnum getMonedaImporteACobrar() {
		return monedaImporteACobrar;
	}

	public void setMonedaImporteACobrar(MonedaEnum monedaImporteACobrar) {
		this.monedaImporteACobrar = monedaImporteACobrar;
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
	 * @return the importe1erVencimientoPesificadoFechaCotizacion
	 */
	public BigDecimal getImporte1erVencimientoPesificadoFechaCotizacion() {
		return importe1erVencimientoPesificadoFechaCotizacion;
	}

	/**
	 * @param importe1erVencimientoPesificadoFechaCotizacion the importe1erVencimientoPesificadoFechaCotizacion to set
	 */
	public void setImporte1erVencimientoPesificadoFechaCotizacion(
			BigDecimal importe1erVencimientoPesificadoFechaCotizacion) {
		this.importe1erVencimientoPesificadoFechaCotizacion = importe1erVencimientoPesificadoFechaCotizacion;
	}

	/**
	 * @return the saldoPesificadoFechaCotizacion
	 */
	public BigDecimal getSaldoPesificadoFechaCotizacion() {
		return SaldoPesificadoFechaCotizacion;
	}

	/**
	 * @param saldoPesificadoFechaCotizacion the saldoPesificadoFechaCotizacion to set
	 */
	public void setSaldoPesificadoFechaCotizacion(
			BigDecimal saldoPesificadoFechaCotizacion) {
		SaldoPesificadoFechaCotizacion = saldoPesificadoFechaCotizacion;
	}

	/**
	 * @return the tipoCambioFechaCotizacion
	 */
	public BigDecimal getTipoCambioFechaCotizacion() {
		return tipoCambioFechaCotizacion;
	}

	/**
	 * @param tipoCambioFechaCotizacion the tipoCambioFechaCotizacion to set
	 */
	public void setTipoCambioFechaCotizacion(BigDecimal tipoCambioFechaCotizacion) {
		this.tipoCambioFechaCotizacion = tipoCambioFechaCotizacion;
	}

	/**
	 * @return the sociedad
	 */
	public SociedadEnum getSociedad() {
		return sociedad;
	}

	/**
	 * @param sociedad the sociedad to set
	 */
	public void setSociedad(SociedadEnum sociedad) {
		this.sociedad = sociedad;
	}

}