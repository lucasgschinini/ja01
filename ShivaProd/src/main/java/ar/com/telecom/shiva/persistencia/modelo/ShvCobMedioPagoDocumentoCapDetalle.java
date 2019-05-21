package ar.com.telecom.shiva.persistencia.modelo;

import java.math.BigDecimal;
import java.util.Date;

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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import ar.com.telecom.shiva.base.enumeradores.EstadoBloqueoSapEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoDocSapEnum;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoDocumentoCapEnum;

@Entity
@Table(name="SHV_COB_MED_PAG_DOC_CAP_DETALL")
public class ShvCobMedioPagoDocumentoCapDetalle extends Modelo {
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="SEQ_SHV_COB_MP_DOC_CAP_DETALL", sequenceName="SEQ_SHV_COB_MP_DOC_CAP_DETALL", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_SHV_COB_MP_DOC_CAP_DETALL")
	@Column(name = "ID_MEDIO_PAGO_DOC_CAP_DETALL")
	private Long idMedioPagoDocumentoCapDetalle;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_MEDIO_PAGO_DOC_CAP", referencedColumnName = "ID_MEDIO_PAGO_DOC_CAP")
	private ShvCobMedioPagoDocumentoCap documentoCap;
	
	@Column(name = "ID_CLIENTE_LEGADO")
	private Long idClienteLegado;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "SISTEMA_ORIGEN", nullable=true)
	private SistemaEnum sistemaOrigen;
	
	@Column(name = "ID_SOCIEDAD")
	private String idSociedad;
	
	@Column(name = "ID_PROVEEDOR")
	private String idProveedor;
//	
	@Column(name = "ASIGNACION")
	private String asignacion;
	
	@Column(name = "EJERCICIO_FISCAL_DOC_SAP") 
	private Integer ejercicioFiscalDocSAP;
	
	@Column(name = "NUMERO_DOC_SAP") 
	private String numeroDocSAP;
	
	@Column(name = "POSICION_DOC_SAP") 
	private Integer posicionDocSAP;
	
	@Column(name = "FECHA_CONTABLE_DOC_SAP") 
	private Date fechaContableDocSAP;
	
	@Column(name = "FECHA_EMISION") 
	private Date fechaEmision;
	
	@Column(name = "FECHA_REGISTRO_SAP") 
	private Date fechaRegistroSAP;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "MONEDA_DOC_PROVEEDOR") 
	private MonedaEnum monedaDocProveedor;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "MONEDA_LOCAL") 
	private MonedaEnum monedaLocal;
	
	@Column(name = "TIPO_CAMBIO_REGISTRADO_SAP") 
	private BigDecimal tipoCambioRegistradoSAP;
	
	@Column(name = "NUMERO_LEGAL_DOC_PROVEEDOR") 
	private String numeroLegalDocProveedor;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "TIPO_DOCUMENTO") 
	private TipoDocumentoCapEnum tipoDocumento;
	
	@Column(name = "MES_FISCAL_DOC_SAP") 
	private Integer mesFiscalDocSap;
	
	@Column(name = "CLAVE_CONTABILIZACION_SAP") 
	private String claveContabilizacionSAP;
	
	@Column(name = "INDICADOR")
	private String indicador;
	
	@Column(name = "DIVISION")
	private String division;
	
	@Column(name = "IMPORTE_REGLON_PESOS_FEC_EMIS") 
	private BigDecimal importeRenglonPesosAFechaEmision;
	
	@Column(name = "IMPORTE_REGLON_MON_ORIGEN_EMIS") 
	private BigDecimal importeRenglonMonedaOrigenAFechaEmision;
	
	@Column(name = "TEXTO_POSICION")
	private String textoPosicion;
	
	@Column(name = "FECHA_BASE")
	private Date fechaBase;
	
	@Column(name = "COND_PAGO") 
	private String condPago;
	
	@Column(name = "VIA_PAGO")
	private String viaPago;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "BLOQUEO_PAGO") 
	private EstadoBloqueoSapEnum bloqueoPago;
	
	@Column(name = "DESCRIPCION_BLOQUEO")
	private String descripcionBloqueo;
	
	@Column(name = "NUMERO_DOC_SAP_VINCULADO")
	private String numeroDocSAPVinculado;
	
	@Column(name = "EJER_FISCAL_DOC_SAP_VINCULADO")
	private Integer ejercicioFiscalDocSAPVinculado; 
	
	@Column(name = "POSICION_DOC_SAP_VINCULADO") 
	private Integer posicionDocSAPVinculado;
	
	@Column(name = "CLAVE_REF")
	private String claveRef;
	
	@Column(name = "CLAVE_REF_2")
	private String claveRef2;
	
	@Column(name = "FECHA_TIPO_CAMBIO")
	private Date fechaTipoCambio;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "ESTADO_DOC_SAP")
	private EstadoDocSapEnum estadoDocSAP;
	
	@Column(name = "IMPORTE_REGLON_PESOS_FEC_SHIVA") 
	private BigDecimal importeRenglonPesosAFechaShiva;
	
	@Column(name = "IMPORTE_REGLON_DOLAR_FEC_EMIS")
	private BigDecimal importeRenglonMonedaDolarAFechaEmision;

	@Column(name = "IMPORTE_REGLON_DOLAR_FEC_SHIVA")
	private BigDecimal importeRenglonMonedaDolarAFechaShiva;
	
	@Column(name = "IMPORTE_TOTAL_MON_ORIGEN_EMIS") 
	private BigDecimal importeTotalDocumentoMonedaOrigenAFechaEmision;
	
	@Column(name = "IMPORTE_TOTAL_PESOS_FEC_EMIS") 
	private BigDecimal importeTotalDocumentoPesosAFechaEmision;
	
	@Column(name = "IMPORTE_TOTAL_PESOS_FEC_SHIVA") 
	private BigDecimal importeTotalDocumentoPesosAFechaShiva;
	
	@Column(name = "IMPORTE_TOTAL_DOLAR_FEC_SHIVA") 
	private BigDecimal importeTotalDocumentoMonedaDolarAFechaShiva;

	@Column(name = "TIPO_CAMBIO_A_FECH_TIPO_CAMBIO")
	private BigDecimal tipoDeCambioAFechaTipoCambio;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "CHECK_SIN_DIFERENCIA_CAMBIO", nullable=true)
	private SiNoEnum checkSinDiferenciaCambio;
	
	@Column(name = "IMPORTE_APLICADO_MONEDA_PESOS")
	private BigDecimal importeAplicadoMonedaPesos;

	@Column(name = "IMPORTE_APLICADO_MONEDA_DOLAR")
	private BigDecimal importeAplicadoMonedaDolar;

	// Atributo que acumula el importe total en moneda documento de estados bloqueado PESOS '$'
	@Column(name = "IMP_TOT_MON_ORI_DOC_BLO_PESOS")
	private BigDecimal importeTotalMonedaOrigenDocumentosBloqueoPesos;

	// Atributo que acumula el importe total en monde emision de los estados bloqueo !Pesos '$'
	@Column(name = "SALDO_PES_EMI_DOC_NO_PESOS")
	
	private BigDecimal saldoPesificadoEmisionDocumentosBloqueoNoPesos;

	// Atributo que acumula el importe total en monde cobro de los estados bloqueo !Pesos '$'
	@Column(name = "SALDO_PES_COBRO_DOC_NO_PESOS")
	private BigDecimal saldoPesificadoCobroDocumentosBloqueoNoPesos;

	/**
	 * Método que retorna el importe a utilizar del renglon, teniendo en cuenta
	 * si se encuentra marcado el check 'sin diferencia de cambio' o no
	 * 
	 * @return
	 */
	public BigDecimal getImporteRenglonPesosEvaluandoCheckSinDiferenciaCambio() {
		return SiNoEnum.NO.equals(checkSinDiferenciaCambio) ? importeRenglonPesosAFechaShiva : importeRenglonPesosAFechaEmision;
	}

	/**
	 * Método que retorna el importe a total del documento, teniendo en cuenta
	 * si se encuentra marcado el check 'sin diferencia de cambio' o no
	 * 
	 * @return
	 */
	public BigDecimal getImporteTotalPesosEvaluandoCheckSinDiferenciaCambio() {
		return SiNoEnum.NO.equals(checkSinDiferenciaCambio) ? importeTotalDocumentoPesosAFechaShiva : importeTotalDocumentoPesosAFechaEmision;
	}
	
	
	/**
	 * @return the idMedioPagoDocumentoCapDetalle
	 */
	public Long getIdMedioPagoDocumentoCapDetalle() {
		return idMedioPagoDocumentoCapDetalle;
	}

	/**
	 * @param idMedioPagoDocumentoCapDetalle the idMedioPagoDocumentoCapDetalle to set
	 */
	public void setIdMedioPagoDocumentoCapDetalle(
			Long idMedioPagoDocumentoCapDetalle) {
		this.idMedioPagoDocumentoCapDetalle = idMedioPagoDocumentoCapDetalle;
	}

	/**
	 * @return the documentoCap
	 */
	public ShvCobMedioPagoDocumentoCap getDocumentoCap() {
		return documentoCap;
	}

	/**
	 * @param documentoCap the documentoCap to set
	 */
	public void setDocumentoCap(
			ShvCobMedioPagoDocumentoCap medioPagoDocumentoCap) {
		this.documentoCap = medioPagoDocumentoCap;
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
	 * @return the idSociedad
	 */
	public String getIdSociedad() {
		return idSociedad;
	}

	/**
	 * @param idSociedad the idSociedad to set
	 */
	public void setIdSociedad(String idSociedad) {
		this.idSociedad = idSociedad;
	}

	/**
	 * @return the idProveedor
	 */
	public String getIdProveedor() {
		return idProveedor;
	}

	/**
	 * @param idProveedor the idProveedor to set
	 */
	public void setIdProveedor(String idProveedor) {
		this.idProveedor = idProveedor;
	}

	/**
	 * @return the asignacion
	 */
	public String getAsignacion() {
		return asignacion;
	}

	/**
	 * @param asignacion the asignacion to set
	 */
	public void setAsignacion(String asignacion) {
		this.asignacion = asignacion;
	}

	/**
	 * @return the ejercicioFiscalDocSAP
	 */
	public Integer getEjercicioFiscalDocSAP() {
		return ejercicioFiscalDocSAP;
	}

	/**
	 * @param ejercicioFiscalDocSAP the ejercicioFiscalDocSAP to set
	 */
	public void setEjercicioFiscalDocSAP(Integer ejercicioFiscalDocSAP) {
		this.ejercicioFiscalDocSAP = ejercicioFiscalDocSAP;
	}

	/**
	 * @return the numeroDocSAP
	 */
	public String getNumeroDocSAP() {
		return numeroDocSAP;
	}

	/**
	 * @param numeroDocSAP the numeroDocSAP to set
	 */
	public void setNumeroDocSAP(String numeroDocSAP) {
		this.numeroDocSAP = numeroDocSAP;
	}

	/**
	 * @return the posicionDocSAP
	 */
	public Integer getPosicionDocSAP() {
		return posicionDocSAP;
	}

	/**
	 * @param posicionDocSAP the posicionDocSAP to set
	 */
	public void setPosicionDocSAP(Integer posicionDocSAP) {
		this.posicionDocSAP = posicionDocSAP;
	}

	/**
	 * @return the fechaContableDocSAP
	 */
	public Date getFechaContableDocSAP() {
		return fechaContableDocSAP;
	}

	/**
	 * @param fechaContableDocSAP the fechaContableDocSAP to set
	 */
	public void setFechaContableDocSAP(Date fechaContableDocSAP) {
		this.fechaContableDocSAP = fechaContableDocSAP;
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
	public void setFechaEmision(Date fechaDocProveedor) {
		this.fechaEmision = fechaDocProveedor;
	}

	/**
	 * @return the fechaRegistroSAP
	 */
	public Date getFechaRegistroSAP() {
		return fechaRegistroSAP;
	}

	/**
	 * @param fechaRegistroSAP the fechaRegistroSAP to set
	 */
	public void setFechaRegistroSAP(Date fechaRegistroSAP) {
		this.fechaRegistroSAP = fechaRegistroSAP;
	}

	/**
	 * @return the monedaDocProveedor
	 */
	public MonedaEnum getMonedaDocProveedor() {
		return monedaDocProveedor;
	}

	/**
	 * @param monedaDocProveedor the monedaDocProveedor to set
	 */
	public void setMonedaDocProveedor(MonedaEnum monedaDocProveedor) {
		this.monedaDocProveedor = monedaDocProveedor;
	}

	/**
	 * @return the monedaLocal
	 */
	public MonedaEnum getMonedaLocal() {
		return monedaLocal;
	}

	/**
	 * @param monedaLocal the monedaLocal to set
	 */
	public void setMonedaLocal(MonedaEnum monedaLocal) {
		this.monedaLocal = monedaLocal;
	}

	/**
	 * @return the tipoCambioRegistradoSAP
	 */
	public BigDecimal getTipoCambioRegistradoSAP() {
		return tipoCambioRegistradoSAP;
	}

	/**
	 * @param tipoCambioRegistradoSAP the tipoCambioRegistradoSAP to set
	 */
	public void setTipoCambioRegistradoSAP(BigDecimal tipoCambioRegistradoSAP) {
		this.tipoCambioRegistradoSAP = tipoCambioRegistradoSAP;
	}

	/**
	 * @return the numeroLegalDocProveedor
	 */
	public String getNumeroLegalDocProveedor() {
		return numeroLegalDocProveedor;
	}

	/**
	 * @param numeroLegalDocProveedor the numeroLegalDocProveedor to set
	 */
	public void setNumeroLegalDocProveedor(String numeroLegalDocProveedor) {
		this.numeroLegalDocProveedor = numeroLegalDocProveedor;
	}

	/**
	 * @return the tipoDocumento
	 */
	public TipoDocumentoCapEnum getTipoDocumento() {
		return tipoDocumento;
	}

	/**
	 * @param tipoDocumento the tipoDocumento to set
	 */
	public void setTipoDocumento(TipoDocumentoCapEnum claseDocSAP) {
		this.tipoDocumento = claseDocSAP;
	}

	/**
	 * @return the mesFiscalDocSap
	 */
	public Integer getMesFiscalDocSap() {
		return mesFiscalDocSap;
	}

	/**
	 * @param mesFiscalDocSap the mesFiscalDocSap to set
	 */
	public void setMesFiscalDocSap(Integer mesFiscalDocSap) {
		this.mesFiscalDocSap = mesFiscalDocSap;
	}

	/**
	 * @return the claveContabilizacionSAP
	 */
	public String getClaveContabilizacionSAP() {
		return claveContabilizacionSAP;
	}

	/**
	 * @param claveContabilizacionSAP the claveContabilizacionSAP to set
	 */
	public void setClaveContabilizacionSAP(String claveContabilizacionSAP) {
		this.claveContabilizacionSAP = claveContabilizacionSAP;
	}

	/**
	 * @return the indicador
	 */
	public String getIndicador() {
		return indicador;
	}

	/**
	 * @param indicador the indicador to set
	 */
	public void setIndicador(String indicador) {
		this.indicador = indicador;
	}

	/**
	 * @return the division
	 */
	public String getDivision() {
		return division;
	}

	/**
	 * @param division the division to set
	 */
	public void setDivision(String division) {
		this.division = division;
	}

	/**
	 * @return the importeRenglonPesosAFechaEmision
	 */
	public BigDecimal getImporteRenglonPesosAFechaEmision() {
		return importeRenglonPesosAFechaEmision;
	}

	/**
	 * @param importeRenglonPesosAFechaEmision the importeRenglonPesosAFechaEmision to set
	 */
	public void setImporteRenglonPesosAFechaEmision(BigDecimal importeDoc) {
		this.importeRenglonPesosAFechaEmision = importeDoc;
	}

	/**
	 * @return the importeRenglonMonedaOrigenAFechaEmision
	 */
	public BigDecimal getImporteRenglonMonedaOrigenAFechaEmision() {
		return importeRenglonMonedaOrigenAFechaEmision;
	}

	/**
	 * @param importeRenglonMonedaOrigenAFechaEmision the importeRenglonMonedaOrigenAFechaEmision to set
	 */
	public void setImporteRenglonMonedaOrigenAFechaEmision(BigDecimal importeDocMonedaDoc) {
		this.importeRenglonMonedaOrigenAFechaEmision = importeDocMonedaDoc;
	}

	/**
	 * @return the textoPosicion
	 */
	public String getTextoPosicion() {
		return textoPosicion;
	}

	/**
	 * @param textoPosicion the textoPosicion to set
	 */
	public void setTextoPosicion(String textoPosicion) {
		this.textoPosicion = textoPosicion;
	}

	/**
	 * @return the fechaBase
	 */
	public Date getFechaBase() {
		return fechaBase;
	}

	/**
	 * @param fechaBase the fechaBase to set
	 */
	public void setFechaBase(Date fechaBase) {
		this.fechaBase = fechaBase;
	}

	/**
	 * @return the condPago
	 */
	public String getCondPago() {
		return condPago;
	}

	/**
	 * @param condPago the condPago to set
	 */
	public void setCondPago(String condPago) {
		this.condPago = condPago;
	}

	/**
	 * @return the viaPago
	 */
	public String getViaPago() {
		return viaPago;
	}

	/**
	 * @param viaPago the viaPago to set
	 */
	public void setViaPago(String viaPago) {
		this.viaPago = viaPago;
	}

	/**
	 * @return the bloqueoPago
	 */
	public EstadoBloqueoSapEnum getBloqueoPago() {
		return bloqueoPago;
	}

	/**
	 * @param bloqueoPago the bloqueoPago to set
	 */
	public void setBloqueoPago(EstadoBloqueoSapEnum bloqueoPago) {
		this.bloqueoPago = bloqueoPago;
	}

	/**
	 * @return the descripcionBloqueo
	 */
	public String getDescripcionBloqueo() {
		return descripcionBloqueo;
	}

	/**
	 * @param descripcionBloqueo the descripcionBloqueo to set
	 */
	public void setDescripcionBloqueo(String descripcionBloqueo) {
		this.descripcionBloqueo = descripcionBloqueo;
	}

	/**
	 * @return the numeroDocSAPVinculado
	 */
	public String getNumeroDocSAPVinculado() {
		return numeroDocSAPVinculado;
	}

	/**
	 * @param numeroDocSAPVinculado the numeroDocSAPVinculado to set
	 */
	public void setNumeroDocSAPVinculado(String numeroDocSAPVinculado) {
		this.numeroDocSAPVinculado = numeroDocSAPVinculado;
	}

	/**
	 * @return the ejercicioFiscalDocSAPVinculado
	 */
	public Integer getEjercicioFiscalDocSAPVinculado() {
		return ejercicioFiscalDocSAPVinculado;
	}

	/**
	 * @param ejercicioFiscalDocSAPVinculado the ejercicioFiscalDocSAPVinculado to set
	 */
	public void setEjercicioFiscalDocSAPVinculado(
			Integer ejercicioFiscalDocSAPVinculado) {
		this.ejercicioFiscalDocSAPVinculado = ejercicioFiscalDocSAPVinculado;
	}

	/**
	 * @return the posicionDocSAPVinculado
	 */
	public Integer getPosicionDocSAPVinculado() {
		return posicionDocSAPVinculado;
	}

	/**
	 * @param posicionDocSAPVinculado the posicionDocSAPVinculado to set
	 */
	public void setPosicionDocSAPVinculado(Integer posicionDocSAPVinculado) {
		this.posicionDocSAPVinculado = posicionDocSAPVinculado;
	}

	/**
	 * @return the claveRef
	 */
	public String getClaveRef() {
		return claveRef;
	}

	/**
	 * @param claveRef the claveRef to set
	 */
	public void setClaveRef(String claveRef) {
		this.claveRef = claveRef;
	}

	/**
	 * @return the claveRef2
	 */
	public String getClaveRef2() {
		return claveRef2;
	}

	/**
	 * @param claveRef2 the claveRef2 to set
	 */
	public void setClaveRef2(String claveRef2) {
		this.claveRef2 = claveRef2;
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
	 * @return the estadoDocSAP
	 */
	public EstadoDocSapEnum getEstadoDocSAP() {
		return estadoDocSAP;
	}

	/**
	 * @param estadoDocSAP the estadoDocSAP to set
	 */
	public void setEstadoDocSAP(EstadoDocSapEnum estadoDocSAP) {
		this.estadoDocSAP = estadoDocSAP;
	}

	/**
	 * @return the importeRenglonPesosAFechaShiva
	 */
	public BigDecimal getImporteRenglonPesosAFechaShiva() {
		return importeRenglonPesosAFechaShiva;
	}

	/**
	 * @param importeRenglonPesosAFechaShiva the importeRenglonPesosAFechaShiva to set
	 */
	public void setImporteRenglonPesosAFechaShiva(BigDecimal impDocPES) {
		this.importeRenglonPesosAFechaShiva = impDocPES;
	}

	/**
	 * @return the importeRenglonMonedaDolarAFechaShiva
	 */
	public BigDecimal getImporteRenglonMonedaDolarAFechaShiva() {
		return importeRenglonMonedaDolarAFechaShiva;
	}

	/**
	 * @param importeRenglonMonedaDolarAFechaShiva the importeRenglonMonedaDolarAFechaShiva to set
	 */
	public void setImporteRenglonMonedaDolarAFechaShiva(BigDecimal importeRenglonMonedaDolarAFechaShiva) {
		this.importeRenglonMonedaDolarAFechaShiva = importeRenglonMonedaDolarAFechaShiva;
	}

	/**
	 * @return the importeTotalDocumentoMonedaOrigenAFechaEmision
	 */
	public BigDecimal getImporteTotalDocumentoMonedaOrigenAFechaEmision() {
		return importeTotalDocumentoMonedaOrigenAFechaEmision;
	}

	/**
	 * @param importeTotalDocumentoMonedaOrigenAFechaEmision the importeTotalDocumentoMonedaOrigenAFechaEmision to set
	 */
	public void setImporteTotalDocumentoMonedaOrigenAFechaEmision(BigDecimal impTotalDocMonedaDoc) {
		this.importeTotalDocumentoMonedaOrigenAFechaEmision = impTotalDocMonedaDoc;
	}

	/**
	 * @return the importeTotalDocumentoPesosAFechaEmision
	 */
	public BigDecimal getImporteTotalDocumentoPesosAFechaEmision() {
		return importeTotalDocumentoPesosAFechaEmision;
	}

	/**
	 * @param importeTotalDocumentoPesosAFechaEmision the importeTotalDocumentoPesosAFechaEmision to set
	 */
	public void setImporteTotalDocumentoPesosAFechaEmision(BigDecimal impTotalDocMonedaLocal) {
		this.importeTotalDocumentoPesosAFechaEmision = impTotalDocMonedaLocal;
	}

	/**
	 * @return the importeTotalDocumentoPesosAFechaShiva
	 */
	public BigDecimal getImporteTotalDocumentoPesosAFechaShiva() {
		return importeTotalDocumentoPesosAFechaShiva;
	}

	/**
	 * @param importeTotalDocumentoPesosAFechaShiva the importeTotalDocumentoPesosAFechaShiva to set
	 */
	public void setImporteTotalDocumentoPesosAFechaShiva(BigDecimal impTotalDocPES) {
		this.importeTotalDocumentoPesosAFechaShiva = impTotalDocPES;
	}

	/**
	 * @return the importeTotalDocumentoMonedaDolarAFechaShiva
	 */
	public BigDecimal getImporteTotalDocumentoMonedaDolarAFechaShiva() {
		return importeTotalDocumentoMonedaDolarAFechaShiva;
	}

	/**
	 * @param importeTotalDocumentoMonedaDolarAFechaShiva the importeTotalDocumentoMonedaDolarAFechaShiva to set
	 */
	public void setImporteTotalDocumentoMonedaDolarAFechaShiva(BigDecimal impTotalDocMonedaOrigen) {
		this.importeTotalDocumentoMonedaDolarAFechaShiva = impTotalDocMonedaOrigen;
	}

	/**
	 * @return the tipoDeCambioAFechaTipoCambio
	 */
	public BigDecimal getTipoDeCambioAFechaTipoCambio() {
		return tipoDeCambioAFechaTipoCambio;
	}

	/**
	 * @param tipoDeCambioAFechaTipoCambio the tipoDeCambioAFechaTipoCambio to set
	 */
	public void setTipoDeCambioAFechaTipoCambio(
			BigDecimal tipoDeCambioAFechaTipoCambio) {
		this.tipoDeCambioAFechaTipoCambio = tipoDeCambioAFechaTipoCambio;
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
	 * @return the importeAplicadoMonedaPesos
	 */
	public BigDecimal getImporteAplicadoMonedaPesos() {
		return importeAplicadoMonedaPesos;
	}

	/**
	 * @param importeAplicadoMonedaPesos the importeAplicadoMonedaPesos to set
	 */
	public void setImporteAplicadoMonedaPesos(BigDecimal importeAplicadoMonedaPesos) {
		this.importeAplicadoMonedaPesos = importeAplicadoMonedaPesos;
	}

	/**
	 * @return the importeAplicadoMonedaDolar
	 */
	public BigDecimal getImporteAplicadoMonedaDolar() {
		return importeAplicadoMonedaDolar;
	}

	/**
	 * @param importeAplicadoMonedaDolar the importeAplicadoMonedaDolar to set
	 */
	public void setImporteAplicadoMonedaDolar(BigDecimal importeAplicadoMonedaDolar) {
		this.importeAplicadoMonedaDolar = importeAplicadoMonedaDolar;
	}

	/**
	 * @return the importeRenglonMonedaDolarAFechaEmision
	 */
	public BigDecimal getImporteRenglonMonedaDolarAFechaEmision() {
		return importeRenglonMonedaDolarAFechaEmision;
	}

	/**
	 * @param importeRenglonMonedaDolarAFechaEmision the importeRenglonMonedaDolarAFechaEmision to set
	 */
	public void setImporteRenglonMonedaDolarAFechaEmision(
			BigDecimal importeRenglonMonedaDolarAFechaEmision) {
		this.importeRenglonMonedaDolarAFechaEmision = importeRenglonMonedaDolarAFechaEmision;
	}

	/**
	 * @return the importeTotalMonedaOrigenDocumentosBloqueoPesos
	 */
	public BigDecimal getImporteTotalMonedaOrigenDocumentosBloqueoPesos() {
		return importeTotalMonedaOrigenDocumentosBloqueoPesos;
	}

	/**
	 * @param importeTotalMonedaOrigenDocumentosBloqueoPesos the importeTotalMonedaOrigenDocumentosBloqueoPesos to set
	 */
	public void setImporteTotalMonedaOrigenDocumentosBloqueoPesos(
			BigDecimal importeTotalMonedaOrigenDocumentosBloqueoPesos) {
		this.importeTotalMonedaOrigenDocumentosBloqueoPesos = importeTotalMonedaOrigenDocumentosBloqueoPesos;
	}

	/**
	 * @return the saldoPesificadoEmisionDocumentosBloqueoNoPesos
	 */
	public BigDecimal getSaldoPesificadoEmisionDocumentosBloqueoNoPesos() {
		return saldoPesificadoEmisionDocumentosBloqueoNoPesos;
	}

	/**
	 * @param saldoPesificadoEmisionDocumentosBloqueoNoPesos the saldoPesificadoEmisionDocumentosBloqueoNoPesos to set
	 */
	public void setSaldoPesificadoEmisionDocumentosBloqueoNoPesos(
			BigDecimal saldoPesificadoEmisionDocumentosBloqueoNoPesos) {
		this.saldoPesificadoEmisionDocumentosBloqueoNoPesos = saldoPesificadoEmisionDocumentosBloqueoNoPesos;
	}

	/**
	 * @return the saldoPesificadoCobroDocumentosBloqueoNoPesos
	 */
	public BigDecimal getSaldoPesificadoCobroDocumentosBloqueoNoPesos() {
		return saldoPesificadoCobroDocumentosBloqueoNoPesos;
	}

	/**
	 * @param saldoPesificadoCobroDocumentosBloqueoNoPesos the saldoPesificadoCobroDocumentosBloqueoNoPesos to set
	 */
	public void setSaldoPesificadoCobroDocumentosBloqueoNoPesos(
			BigDecimal saldoPesificadoCobroDocumentosBloqueoNoPesos) {
		this.saldoPesificadoCobroDocumentosBloqueoNoPesos = saldoPesificadoCobroDocumentosBloqueoNoPesos;
	}
	
}

