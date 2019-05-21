package ar.com.telecom.shiva.persistencia.modelo;

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

import ar.com.telecom.shiva.base.enumeradores.EstadoDebitoImportadoEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoOrigenEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoTramiteDeimosEnum;
import ar.com.telecom.shiva.base.enumeradores.MarcaCyQEnum;
import ar.com.telecom.shiva.base.enumeradores.MarcaReclamoEnum;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.MotivoShivaEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoClaseComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoCreditoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoRetencionEnum;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamTipoMedioPago;


@Entity
@Table(name="SHV_COB_ED_CREDITO")
public class ShvCobEdCredito extends Modelo  {
	private static final long serialVersionUID = 1L;

	@Id
	private ShvCobEdCreditoPk pk;

	@Column(name = "ID_CREDITO_REFERENCIA")
	private String idCreditoReferencia;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "SISTEMA_ORIGEN")
	private SistemaEnum sistemaOrigen;
	
	@Column(name="ID_CLIENTE_LEGADO")
	private Long  idClienteLegado;
	
	//Solo se hace la FK en la bd, no en el modelo, porque se va a usar la vista
	@Column(name="ID_VALOR")
	private Long  idValor; 
	
	@Enumerated(EnumType.STRING)
	@Column(name="TIPO_COMPROBANTE")
	private TipoComprobanteEnum tipoComprobante;
	
	@Enumerated(EnumType.STRING)
	@Column(name="CLASE_COMPROBANTE")
	private TipoClaseComprobanteEnum claseComprobante;
	
	@Column(name="SUCURSAL_COMPROBANTE")
	private String  sucursalComprobante;
	
	@Column(name="NUMERO_COMPROBANTE")
	private String  numeroComprobante;

	@Column(name="CICLO_FACTURACION")
	private Integer cicloFacturacion;

	@Enumerated(EnumType.STRING)
	@Column(name = "MARCA_CYQ")
	private MarcaCyQEnum marcaCyq;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "MARCA_RECLAMO")
	private MarcaReclamoEnum marcaReclamo;// MIC - CALIPSO
	
	@Column(name="CODIGO_TARIFA")
	private String codigoTarifa;

	@Column(name="CUENTA")
	private Long cuenta;

	@Column(name="CUIT")
	private String cuit;

	@Column(name="CODIGO_TIPO_REMANENTE")
	private Long codigoTipoRemamente;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "ESTADO_ORIGEN")
	private EstadoOrigenEnum estadoOrigen;
	
	@Column(name="FECHA_ALTA")
	private Date fechaAlta;

	@Column(name="FECHA_EMISION")
	private Date fechaEmision;

	@Column(name="FECHA_ULTIMO_MOVIMIENTO")
	private Date fechaUltimoMovimiento;

	@Column(name="FECHA_VALOR")
	private Date fechaValor;

	@Column(name="FECHA_VENCIMIENTO")
	private Date fechaVencimiento;

	@Column(name="FECHA_VENCIMIENTO_MORA")
	private Date fechaVencimientoMora;
	
	@Column(name="IMPORTE_A_UTILIZAR")
	private BigDecimal importeAUtilizar;

	@Column(name="IMPORTE_MONEDA_ORIGEN")
	private BigDecimal importeMonedaOrigen;

	@Column(name="IMPORTE_PESIFICADO")
	private BigDecimal importePesificado;
	
	@Column(name="SALDO_MONEDA_ORIGEN")
	private BigDecimal saldoMonedaOrigen;

	@Column(name="SALDO_PESIFICADO")
	private BigDecimal saldoPesificado;

	@Column(name="INDICADOR_PETICION_CORTE")
	private String indicadorPeticionCorte;

	@Enumerated(EnumType.STRING)
	@Column(name = "MARCA_MIGRADO_DEIMOS")
	private SiNoEnum marcaMigradoDeimos;

	@Column(name="MARKETING_CUSTOMER_GROUP")
	private String marketingCustomerGroup;

	@Enumerated(EnumType.STRING)
	@Column(name = "MONEDA")
	private MonedaEnum moneda;

	@Column(name="NRO_REFERENCIA_MIC")
	private Long nroReferenciaMic;

	@Column(name="NUMERO_ACUERDO")
	private Long numeroAcuerdo;

	@Column(name="HOLDING")
	private String holding;

	@Column(name="ID_DOCUMENTO_CUENTA_COBRANZA")
	private Long idDocumentoCuentaCobranza;

	@Column(name="PROVINCIA")
	private String provincia;

	@Column(name="RAZON_SOCIAL_CLIENTE")
	private String razonSocialCliente;
	
	@Column(name="SUBTIPO")
	private String subtipo;

	@Column(name="TIPO_CLIENTE")
	private String tipoCliente;

	@Enumerated(EnumType.STRING)
	@Column(name="TIPO_CREDITO") 
	private TipoCreditoEnum tipoCredito;
	
	@ManyToOne(cascade = CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_TIPO_MEDIO_PAGO") 
	@NotFound(action=NotFoundAction.IGNORE)
	private ShvParamTipoMedioPago tipoMedioPago;
	
	@Column(name="TIPO_DE_CAMBIO")
	private BigDecimal tipoDeCambio;

	@Column(name="UNIDAD_OPERATIVA")
	private String unidadOperativa;

	/************ VALORES SHIVA **************/

	@Column(name = "REFERENCIA_VALOR")
	private String referenciaValor;//Nro. Documento
	
	@Enumerated(EnumType.STRING)
	@Column(name = "TIPO_RETENCION")
	private TipoRetencionEnum tipoRetencion;
	
	@Column(name = "FECHA_INGRESO_RECIBO")
	private Date fechaIngresoRecibo;
	
	@Column(name = "FECHA_DEPOSITO")
	private Date fechaDeposito;
	
	@Column(name = "FECHA_TRANSFERENCIA")
	private Date fechaTransferencia;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "MOTIVO")
	private MotivoShivaEnum motivo;
	
	/************ DATOS ADICIONALES PARA DEIMOS ***************/

	@Enumerated(EnumType.STRING)
	@Column(name = "DMOS_APROPIADO_EN_DEIMOS")
	private SiNoEnum dmosApropiacionEnDeimos;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "DMOS_ESTADO_TRAMITE")
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
	@Column(name="MONEDA_IMPORTE_A_UTILIZAR")
	private MonedaEnum  monedaImporteAUtilizar ;
	
	/**
	 * 
	 */
	public ShvCobEdCredito() {
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
	 * @return the idValor
	 */
	public Long getIdValor() {
		return idValor;
	}

	/**
	 * @param idValor the idValor to set
	 */
	public void setIdValor(Long idValor) {
		this.idValor = idValor;
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
	 * @return the cuenta
	 */
	public Long getCuenta() {
		return cuenta;
	}

	/**
	 * @param cuenta the cuenta to set
	 */
	public void setCuenta(Long cuenta) {
		this.cuenta = cuenta;
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
	 * @return the fechaAlta
	 */
	public Date getFechaAlta() {
		return fechaAlta;
	}

	/**
	 * @param fechaAlta the fechaAlta to set
	 */
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
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
	 * @return the fechaUltimoMovimiento
	 */
	public Date getFechaUltimoMovimiento() {
		return fechaUltimoMovimiento;
	}

	/**
	 * @param fechaUltimoMovimiento the fechaUltimoMovimiento to set
	 */
	public void setFechaUltimoMovimiento(Date fechaUltimoMovimiento) {
		this.fechaUltimoMovimiento = fechaUltimoMovimiento;
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
	 * @return the importeAUtilizar
	 */
	public BigDecimal getImporteAUtilizar() {
		return importeAUtilizar;
	}

	/**
	 * @param importeAUtilizar the importeAUtilizar to set
	 */
	public void setImporteAUtilizar(BigDecimal importeAUtilizar) {
		this.importeAUtilizar = importeAUtilizar;
	}

	/**
	 * @return the importeMonedaOrigen
	 */
	public BigDecimal getImporteMonedaOrigen() {
		return importeMonedaOrigen;
	}

	/**
	 * @param importeMonedaOrigen the importeMonedaOrigen to set
	 */
	public void setImporteMonedaOrigen(BigDecimal importeMonedaOrigen) {
		this.importeMonedaOrigen = importeMonedaOrigen;
	}

	/**
	 * @return the importePesificado
	 */
	public BigDecimal getImportePesificado() {
		return importePesificado;
	}

	/**
	 * @param importePesificado the importePesificado to set
	 */
	public void setImportePesificado(BigDecimal importePesificado) {
		this.importePesificado = importePesificado;
	}

	/**
	 * @return the saldoMonedaOrigen
	 */
	public BigDecimal getSaldoMonedaOrigen() {
		return saldoMonedaOrigen;
	}

	/**
	 * @param saldoMonedaOrigen the saldoMonedaOrigen to set
	 */
	public void setSaldoMonedaOrigen(BigDecimal saldoMonedaOrigen) {
		this.saldoMonedaOrigen = saldoMonedaOrigen;
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
	 * @return the nroReferenciaMic
	 */
	public Long getNroReferenciaMic() {
		return nroReferenciaMic;
	}

	/**
	 * @param nroReferenciaMic the nroReferenciaMic to set
	 */
	public void setNroReferenciaMic(Long nroReferenciaMic) {
		this.nroReferenciaMic = nroReferenciaMic;
	}

	/**
	 * @return the numeroAcuerdo
	 */
	public Long getNumeroAcuerdo() {
		return numeroAcuerdo;
	}

	/**
	 * @param numeroAcuerdo the numeroAcuerdo to set
	 */
	public void setNumeroAcuerdo(Long numeroAcuerdo) {
		this.numeroAcuerdo = numeroAcuerdo;
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
	 * @return the idDocumentoCuentaCobranza
	 */
	public Long getIdDocumentoCuentaCobranza() {
		return idDocumentoCuentaCobranza;
	}

	/**
	 * @param idDocumentoCuentaCobranza the idDocumentoCuentaCobranza to set
	 */
	public void setIdDocumentoCuentaCobranza(Long idDocumentoCuentaCobranza) {
		this.idDocumentoCuentaCobranza = idDocumentoCuentaCobranza;
	}

	/**
	 * @return the provincia
	 */
	public String getProvincia() {
		return provincia;
	}

	/**
	 * @param provincia the provincia to set
	 */
	public void setProvincia(String provincia) {
		this.provincia = provincia;
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
	 * @return the tipoCredito
	 */
	public TipoCreditoEnum getTipoCredito() {
		return tipoCredito;
	}

	/**
	 * @param tipoCredito the tipoCredito to set
	 */
	public void setTipoCredito(TipoCreditoEnum tipoCredito) {
		this.tipoCredito = tipoCredito;
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
	 * @return the referenciaValor
	 */
	public String getReferenciaValor() {
		return referenciaValor;
	}

	/**
	 * @param referenciaValor the referenciaValor to set
	 */
	public void setReferenciaValor(String referenciaValor) {
		this.referenciaValor = referenciaValor;
	}

	/**
	 * @return the descripcionTipoRetencion
	 */
	public TipoRetencionEnum getTipoRetencion() {
		return tipoRetencion;
	}

	/**
	 * @param descripcionTipoRetencion the descripcionTipoRetencion to set
	 */
	public void setTipoRetencion(TipoRetencionEnum tipoRetencion) {
		this.tipoRetencion = tipoRetencion;
	}

	/**
	 * @return the fechaIngresoRecibo
	 */
	public Date getFechaIngresoRecibo() {
		return fechaIngresoRecibo;
	}

	/**
	 * @param fechaIngresoRecibo the fechaIngresoRecibo to set
	 */
	public void setFechaIngresoRecibo(Date fechaIngresoRecibo) {
		this.fechaIngresoRecibo = fechaIngresoRecibo;
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
	 * @return the fechaTransferencia
	 */
	public Date getFechaTransferencia() {
		return fechaTransferencia;
	}

	/**
	 * @param fechaTransferencia the fechaTransferencia to set
	 */
	public void setFechaTransferencia(Date fechaTransferencia) {
		this.fechaTransferencia = fechaTransferencia;
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
	public void setResultadoValidacionDescripcionError(String resultadoValidacionDescripcionError) {
		this.resultadoValidacionDescripcionError = resultadoValidacionDescripcionError;
	}

	public SistemaEnum getSistemaOrigen() {
		return sistemaOrigen;
	}

	public void setSistemaOrigen(SistemaEnum sistemaOrigen) {
		this.sistemaOrigen = sistemaOrigen;
	}

	public ShvCobEdCreditoPk getPk() {
		return pk;
	}

	public void setPk(ShvCobEdCreditoPk pk) {
		this.pk = pk;
	}

	public Long getCodigoTipoRemamente() {
		return codigoTipoRemamente;
	}

	public void setCodigoTipoRemamente(Long codigoTipoRemamente) {
		this.codigoTipoRemamente = codigoTipoRemamente;
	}

	public EstadoOrigenEnum getEstadoOrigen() {
		return estadoOrigen;
	}

	public void setEstadoOrigen(EstadoOrigenEnum estadoOrigen) {
		this.estadoOrigen = estadoOrigen;
	}

	public MotivoShivaEnum getMotivo() {
		return motivo;
	}

	public void setMotivo(MotivoShivaEnum motivo) {
		this.motivo = motivo;
	}

	public SiNoEnum getDmosApropiacionEnDeimos() {
		return dmosApropiacionEnDeimos;
	}

	public void setDmosApropiacionEnDeimos(SiNoEnum dmosApropiacionEnDeimos) {
		this.dmosApropiacionEnDeimos = dmosApropiacionEnDeimos;
	}

	public EstadoTramiteDeimosEnum getDmosEstadoTramite() {
		return dmosEstadoTramite;
	}

	public void setDmosEstadoTramite(EstadoTramiteDeimosEnum dmosEstadoTramite) {
		this.dmosEstadoTramite = dmosEstadoTramite;
	}

	public Long getDmosCantidadDeCuotas() {
		return dmosCantidadDeCuotas;
	}

	public void setDmosCantidadDeCuotas(Long dmosCantidadDeCuotas) {
		this.dmosCantidadDeCuotas = dmosCantidadDeCuotas;
	}

	public Long getDmosCantidadDeCuotasPagas() {
		return dmosCantidadDeCuotasPagas;
	}

	public void setDmosCantidadDeCuotasPagas(Long dmosCantidadDeCuotasPagas) {
		this.dmosCantidadDeCuotasPagas = dmosCantidadDeCuotasPagas;
	}

	public Long getDmosNumeroConvenio() {
		return dmosNumeroConvenio;
	}

	public void setDmosNumeroConvenio(Long dmosNumeroConvenio) {
		this.dmosNumeroConvenio = dmosNumeroConvenio;
	}

	public ShvParamTipoMedioPago getTipoMedioPago() {
		return tipoMedioPago;
	}

	public void setTipoMedioPago(ShvParamTipoMedioPago tipoMedioPago) {
		this.tipoMedioPago = tipoMedioPago;
	}

	public String getIdCreditoReferencia() {
		return idCreditoReferencia;
	}

	public void setIdCreditoReferencia(String idCreditoReferencia) {
		this.idCreditoReferencia = idCreditoReferencia;
	}

	public MonedaEnum getMonedaImporteAUtilizar() {
		return monedaImporteAUtilizar;
	}

	public void setMonedaImporteAUtilizar(MonedaEnum monedaImporteAUtilizar) {
		this.monedaImporteAUtilizar = monedaImporteAUtilizar;
	}

}