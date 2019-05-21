package ar.com.telecom.shiva.persistencia.modelo;

import java.math.BigDecimal;
import java.util.Date;

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
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import ar.com.telecom.shiva.base.enumeradores.EstadoBloqueoSapEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoDocSapEnum;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoDocumentoCapEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoRenglonSapEnum;

@Entity
@Table(name="SHV_COB_ED_DOCUMENTO_CAP")
public class ShvCobEdDocumentoCap extends Modelo{
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="SEQ_SHV_COB_ED_DOCUMENTO_CAP", sequenceName="SEQ_SHV_COB_ED_DOCUMENTO_CAP", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_SHV_COB_ED_DOCUMENTO_CAP")
	@Column(name = "ID_DOCUMENTO_CAP")
	private Long idDocumentoCap;
	
	@ManyToOne(cascade = { CascadeType.DETACH}, fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "ID_MEDIO_PAGO", referencedColumnName = "ID_MEDIO_PAGO"),
		@JoinColumn(name = "ID_COBRO", referencedColumnName = "ID_COBRO")
	})
	private ShvCobEdOtrosMedioPago otrosMedioPago;
	
	@Column(name = "ID_CLIENTE_LEGADO")
	private Long idClienteLegado;

	@Column(name = "ID_CAP_REFERENCIA")
	private String idCapReferencia;		

	@Enumerated(EnumType.STRING)
	@Column(name = "TIPO_RENGLON")
	private TipoRenglonSapEnum tipoRenglon;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "SISTEMA_ORIGEN", nullable=true)
	private SistemaEnum sistemaOrigen;
	
	@Column(name = "IMP_PESIFICADO_DOC_ASOCIADO")
	private BigDecimal importePesificadoDocAsociadoBd;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "MONEDA_IMPORTE") 
	private MonedaEnum monedaImporte;

	@Column(name = "SALDO_PESIFICADO_FECHA_SHIVA")
	private BigDecimal saldoPesificadoTotalAFechaShiva;
	
	@Column(name = "SALDO_PESIFICADO_FECHA_EMISION")
	private BigDecimal saldoPesificadoTotalAFechaEmision;
	
	@Column(name = "IMP_GESTIONABLE_FECHA_SHIVA")
	private BigDecimal  ImporteGestionableAFechaShivaTotal;  
	
	@Column(name = "IMP_GESTIONABLE_FECHA_EMISION")
	private BigDecimal ImporteGestionableAFechaEmisionTotal;

	@Column(name = "IMP_GESTIONABLE")
	private BigDecimal ImporteGestionable;

	@Column(name = "SALDO_MONEDA_ORIGEN")
	private BigDecimal saldoMonedaOrigen;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "CHECK_SIN_DIFERENCIA_CAMBIO", nullable=true)
	private SiNoEnum checkSinDiferenciaCambio;
	
	// Datos de la partida de sap tal cual viene para los registros linea
	@Column(name = "ID_SOCIEDAD")
	private String idSociedad;
	
	@Column(name = "ID_PROVEEDOR")
	private String idProveedor;
	
	@Column(name = "ASIGNACION")
	private String asignacion;
	
	@Column(name = "EJERCICIO_FISCAL_DOC_SAP") 
	private Integer ejercicioFiscalDocSAP;//TOOD MAL
	
	@Column(name = "NUMERO_DOC_SAP") 
	private String numeroDocSAP;
	
	@Column(name = "POSICION_DOC_SAP") 
	private Integer posicionDocSAP;
	
	@Column(name = "FECHA_CONTABLE_DOC_SAP") 
	private Date fechaContableDocSAP;
	
	@Column(name = "FECHA_DOCUMENTO_PROVEEDOR") 
	private Date fechaDocProveedor;
	
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
	@Column(name = "CLASE_DOC_SAP") 
	private TipoDocumentoCapEnum claseDocSAP;
	
	@Column(name = "MES_FISCAL_DOC_SAP") 
	private Integer mesFiscalDocSap;
	
	@Column(name = "CLAVE_CONTABILIZACION_SAP") 
	private String claveContabilizacionSAP;
	
	@Column(name = "INDICADOR")
	private String indicador;
	
	@Column(name = "DIVISION")
	private String division;
	
	@Column(name = "IMPORTE_DOC")
	private BigDecimal importeDoc;
	
	@Column(name = "IMPORTE_DOC_MONEDA_DOC")
	private BigDecimal importeDocMonedaDoc;
	
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
	
	@Column(name = "IMP_DOC_PES")
	private BigDecimal impDocPES;
	
	@Column(name = "IMP_DOC_USD")
	private BigDecimal impDocUSD;
	
	@Column(name = "IMP_TOTAL_DOC_MONEDA_DOC")
	private BigDecimal impTotalDocMonedaDoc;
	
	@Column(name = "IMP_TOTAL_DOC_MONEDA_LOCAL")
	private BigDecimal impTotalDocMonedaLocal;
	
	@Column(name = "IMP_TOTAL_DOC_PES") 
	private BigDecimal impTotalDocPES;
	
	@Column(name = "IMP_TOTAL_DOC_USD")
	private BigDecimal impTotalDocUSD;

	@Column(name = "TIPO_CAMBIO_A_FECH_TIPO_CAMBIO")
	private BigDecimal tipoDeCambioAFechaTipoCambio;

	@Column(name = "IMPORTE_REGLON_DOLAR_FEC_EMIS")
	private BigDecimal importeRenglonMonedaDolarAFechaEmision;
	
	// Atributo que acumula el importe total en moneda documento de estados bloqueado PESOS '$'
	@Column(name = "IMP_TOT_MON_ORI_DOC_BLO_PESOS")
	private BigDecimal importeTotalMonedaOrigenDocumentosBloqueoPesos;

	// Atributo que acumula el importe total en monde emision de los estados bloqueo !Pesos '$'
	@Column(name = "SALDO_PES_EMI_DOC_NO_PESOS")
	private BigDecimal saldoPesificadoEmisionDocumentosBloqueoNoPesos;

	// Atributo que acumula el importe total en monde cobro de los estados bloqueo !Pesos '$'
	@Column(name = "SALDO_PES_COBRO_DOC_NO_PESOS")
	private BigDecimal saldoPesificadoCobroDocumentosBloqueoNoPesos;
	
	// Atributos que no hacen la documento
	@Column(name = "V_SEMAFORO")	//TODO VARCHAR2 8
	private String vSemaforo;
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
	public Date getFechaDocProveedor() {
		return fechaDocProveedor;
	}

	/**
	 * @param fechaDocProveedor the fechaEmision to set
	 */
	public void setFechaDocProveedor(Date fechaDocProveedor) {
		this.fechaDocProveedor = fechaDocProveedor;
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
	 * @return the claseDocSAP
	 */
	public TipoDocumentoCapEnum getClaseDocSAP() {
		return claseDocSAP;
	}

	/**
	 * @param claseDocSAP the claseDocSAP to set
	 */
	public void setClaseDocSAP(TipoDocumentoCapEnum claseDocSAP) {
		this.claseDocSAP = claseDocSAP;
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
	public BigDecimal getImporteDoc() {
		return importeDoc;
	}

	/**
	 * @param importeDoc the importeRenglonPesosAFechaEmision to set
	 */
	public void setImporteDoc(BigDecimal importeDoc) {
		this.importeDoc = importeDoc;
	}

	/**
	 * @return the importeRenglonMonedaOrigenAFechaEmision
	 */
	public BigDecimal getImporteDocMonedaDoc() {
		return importeDocMonedaDoc;
	}

	/**
	 * @param importeDocMonedaDoc the importeRenglonMonedaOrigenAFechaEmision to set
	 */
	public void setImporteDocMonedaDoc(BigDecimal importeDocMonedaDoc) {
		this.importeDocMonedaDoc = importeDocMonedaDoc;
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
	public BigDecimal getImpDocPES() {
		return impDocPES;
	}

	/**
	 * @param impDocPES the importeRenglonPesosAFechaShiva to set
	 */
	public void setImpDocPES(BigDecimal impDocPES) {
		this.impDocPES = impDocPES;
	}

	/**
	 * @return the importeRenglonMonedaDolarAFechaShiva
	 */
	public BigDecimal getImpDocUSD() {
		return impDocUSD;
	}

	/**
	 * @param impDocUSD the importeRenglonMonedaDolarAFechaShiva to set
	 */
	public void setImpDocUSD(BigDecimal impDocUSD) {
		this.impDocUSD = impDocUSD;
	}

	/**
	 * @return the importeTotalDocumentoMonedaOrigenAFechaEmision
	 */
	public BigDecimal getImpTotalDocMonedaDoc() {
		return impTotalDocMonedaDoc;
	}

	/**
	 * @param impTotalDocMonedaDoc the importeTotalDocumentoMonedaOrigenAFechaEmision to set
	 */
	public void setImpTotalDocMonedaDoc(BigDecimal impTotalDocMonedaDoc) {
		this.impTotalDocMonedaDoc = impTotalDocMonedaDoc;
	}

	/**
	 * @return the importeTotalDocumentoPesosAFechaEmision
	 */
	public BigDecimal getImpTotalDocMonedaLocal() {
		return impTotalDocMonedaLocal;
	}

	/**
	 * @param impTotalDocMonedaLocal the importeTotalDocumentoPesosAFechaEmision to set
	 */
	public void setImpTotalDocMonedaLocal(BigDecimal impTotalDocMonedaLocal) {
		this.impTotalDocMonedaLocal = impTotalDocMonedaLocal;
	}

	/**
	 * @return the importeTotalDocumentoPesosAFechaShiva
	 */
	public BigDecimal getImpTotalDocPES() {
		return impTotalDocPES;
	}

	/**
	 * @param importeTotalDocumentoPesosAFechaShiva the importeTotalDocumentoPesosAFechaShiva to set
	 */
	public void setImpTotalDocPES(BigDecimal impTotalDocPES) {
		this.impTotalDocPES = impTotalDocPES;
	}

	/**
	 * @return the importeTotalDocumentoMonedaDolarAFechaShiva
	 */
	public BigDecimal getImpTotalDocUSD() {
		return impTotalDocUSD;
	}

	/**
	 * @param impTotalDocUSD the importeTotalDocumentoMonedaDolarAFechaShiva to set
	 */
	public void setImpTotalDocUSD(BigDecimal impTotalDocUSD) {
		this.impTotalDocUSD = impTotalDocUSD;
	}

	/**
	 * @return the idCapReferencia
	 */
	public String getIdCapReferencia() {
		return idCapReferencia;
	}

	/**
	 * @param idCapReferencia the idCapReferencia to set
	 */
	public void setIdCapReferencia(String idCapReferencia) {
		this.idCapReferencia = idCapReferencia;
	}

	/**
	 * @return the tipoRenglon
	 */
	public TipoRenglonSapEnum getTipoRenglon() {
		return tipoRenglon;
	}

	/**
	 * @param tipoRenglon the tipoRenglon to set
	 */
	public void setTipoRenglon(TipoRenglonSapEnum tipoRenglon) {
		this.tipoRenglon = tipoRenglon;
	}

	/**
	 * @return the monedaImporte
	 */
	public MonedaEnum getMonedaImporte() {
		return monedaImporte;
	}

	/**
	 * @param monedaImporte the monedaImporte to set
	 */
	public void setMonedaImporte(MonedaEnum monedaImporte) {
		this.monedaImporte = monedaImporte;
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
	 * @return the otrosMedioPago
	 */
	public ShvCobEdOtrosMedioPago getOtrosMedioPago() {
		return otrosMedioPago;
	}

	/**
	 * @param otrosMedioPago the otrosMedioPago to set
	 */
	public void setOtrosMedioPago(ShvCobEdOtrosMedioPago otrosMedioPago) {
		this.otrosMedioPago = otrosMedioPago;
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
	// Atributos de controls

	/**
	 * @return the vSemaforo
	 */
	public String getvSemaforo() {
		return vSemaforo;
	}

	/**
	 * @param vSemaforo the vSemaforo to set
	 */
	public void setvSemaforo(String vSemaforo) {
		this.vSemaforo = vSemaforo;
	}

	/**
	 * @return the importeGestionable
	 */
	public BigDecimal getImporteGestionable() {
		return ImporteGestionable;
	}

	/**
	 * @param importeGestionable the importeGestionable to set
	 */
	public void setImporteGestionable(BigDecimal importeGestionable) {
		ImporteGestionable = importeGestionable;
	}

	/**
	 * @return the importePesificadoDocAsociadoBd
	 */
	public BigDecimal getImportePesificadoDocAsociadoBd() {
		return importePesificadoDocAsociadoBd;
	}

	/**
	 * @param importePesificadoDocAsociadoBd the importePesificadoDocAsociadoBd to set
	 */
	public void setImportePesificadoDocAsociadoBd(
			BigDecimal importePesificadoDocAsociadoBd) {
		this.importePesificadoDocAsociadoBd = importePesificadoDocAsociadoBd;
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
	 * @return the importeGestionableAFechaShivaTotal
	 */
	public BigDecimal getImporteGestionableAFechaShivaTotal() {
		return ImporteGestionableAFechaShivaTotal;
	}

	/**
	 * @param importeGestionableAFechaShivaTotal the importeGestionableAFechaShivaTotal to set
	 */
	public void setImporteGestionableAFechaShivaTotal(
			BigDecimal importeGestionableAFechaShivaTotal) {
		ImporteGestionableAFechaShivaTotal = importeGestionableAFechaShivaTotal;
	}

	/**
	 * @return the importeGestionableAFechaEmisionTotal
	 */
	public BigDecimal getImporteGestionableAFechaEmisionTotal() {
		return ImporteGestionableAFechaEmisionTotal;
	}

	/**
	 * @param importeGestionableAFechaEmisionTotal the importeGestionableAFechaEmisionTotal to set
	 */
	public void setImporteGestionableAFechaEmisionTotal(
			BigDecimal importeGestionableAFechaEmisionTotal) {
		ImporteGestionableAFechaEmisionTotal = importeGestionableAFechaEmisionTotal;
	}

	/**
	 * @return the saldoPesificadoTotalAFechaShiva
	 */
	public BigDecimal getSaldoPesificadoTotalAFechaShiva() {
		return saldoPesificadoTotalAFechaShiva;
	}

	/**
	 * @param saldoPesificadoTotalAFechaShiva the saldoPesificadoTotalAFechaShiva to set
	 */
	public void setSaldoPesificadoTotalAFechaShiva(
			BigDecimal saldoPesificadoTotalAFechaShiva) {
		this.saldoPesificadoTotalAFechaShiva = saldoPesificadoTotalAFechaShiva;
	}

	/**
	 * @return the saldoPesificadoTotalAFechaEmision
	 */
	public BigDecimal getSaldoPesificadoTotalAFechaEmision() {
		return saldoPesificadoTotalAFechaEmision;
	}

	/**
	 * @param saldoPesificadoTotalAFechaEmision the saldoPesificadoTotalAFechaEmision to set
	 */
	public void setSaldoPesificadoTotalAFechaEmision(
			BigDecimal saldoPesificadoTotalAFechaEmision) {
		this.saldoPesificadoTotalAFechaEmision = saldoPesificadoTotalAFechaEmision;
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
	 * @return the idDocumentoCap
	 */
	public Long getIdDocumentoCap() {
		return idDocumentoCap;
	}

	/**
	 * @param idDocumentoCap the idDocumentoCap to set
	 */
	public void setIdDocumentoCap(Long idDocumentoCap) {
		this.idDocumentoCap = idDocumentoCap;
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

