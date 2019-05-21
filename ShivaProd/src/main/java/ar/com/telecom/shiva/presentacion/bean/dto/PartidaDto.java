package ar.com.telecom.shiva.presentacion.bean.dto;

import java.math.BigDecimal;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import ar.com.telecom.shiva.base.enumeradores.EstadoBloqueoSapEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoDocSapEnum;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoDocumentoCapEnum;


@JsonIgnoreProperties (ignoreUnknown=true)
public class PartidaDto extends DocumentoGestionableDTO {
	private static final long serialVersionUID = 20161215L;

	private String codigoSociedad;
	private String idNumeroProveedor;
	private String asignacion;
	private Integer ejercicioFiscalDocSAP;
	private String nroDocumentoSap;
	private Integer posicionDocSAP;
	private Date fechaContableDocSAP;
	private Date fechaDocProveedor;
	private Date fechaRegistroSAP;
	private MonedaEnum monedaDocProveedor;
	private MonedaEnum monedaLocal;
	private BigDecimal tipoCambioRegistradoSAP;
	private String numeroLegalDocProveedor;
	private TipoDocumentoCapEnum claseDocSAP;	//               <DOC_TYPE>KB</DOC_TYPE>
	private Integer mesFiscalDocSap;
	private String claveContabilizacionSAP;
	private String indicador;
	private String division;
	private BigDecimal importeDoc;
	private BigDecimal importeDocMonedaDoc;
	private String textoPosicion;
	private Date fechaBase;
	private String condPago;
	private String viaPago;
	private EstadoBloqueoSapEnum bloqueoPago;
	private String descripcionBloqueo;
	private String numeroDocSAPVinculado;
	private Integer ejercicioFiscalDocSAPVinculado;
	private Integer posicionDocSAPVinculado;
	private String claveRef;
	private String claveRef2;
	private Date fechaTipoCambio;
	protected BigDecimal tipoCambioAFechaTipoCambio;
	private EstadoDocSapEnum estadoDocSAP;
	private BigDecimal impDocPES;
	private BigDecimal impDocUSD;
	private BigDecimal impTotalDocMonedaDoc;
	private BigDecimal impTotalDocMonedaLocal;
	private BigDecimal impTotalDocPES;
	private BigDecimal impTotalDocUSD;

	protected BigDecimal importeRenglonMonedaDolarAFechaEmision;
	
	
	
	// TODO dato calculao par persistir
	private BigDecimal importeTotalMonedaOrigenDocumentosBloqueoPesos;
	private BigDecimal saldoPesificadoEmisionDocumentosBloqueoNoPesos;
	private BigDecimal saldoPesificadoCobroDocumentosBloqueoNoPesos;

	public PartidaDto() {}
//	public PartidaDto(String codigoSociedad, String idNumeroProveedor, String ejercicioFiscalDocSAP, String nroDocumentoSap, Date fechaEmision,
//			MonedaEnum monedaDocProveedor, BigDecimal tipoCambioRegistradoSAP, String numeroLegalDocProveedor, TipoDocumentoCapEnum claseDocSAP, BigDecimal importeTotalDocumentoMonedaOrigenAFechaEmision,
//			BigDecimal importeTotalDocumentoPesosAFechaShiva) {
	public PartidaDto(PartidaDto dto) {
		this.codigoSociedad = dto.codigoSociedad;
		this.idNumeroProveedor = dto.idNumeroProveedor;
		this.ejercicioFiscalDocSAP = dto.ejercicioFiscalDocSAP;
		this.nroDocumentoSap = dto.nroDocumentoSap;
		this.fechaDocProveedor = dto.fechaDocProveedor;
		this.monedaDocProveedor = dto.monedaDocProveedor;
		this.tipoCambioRegistradoSAP = dto.tipoCambioRegistradoSAP;
		this.numeroLegalDocProveedor = dto.numeroLegalDocProveedor;
		//this.claseDocSAP
		this.impTotalDocMonedaDoc = dto.impTotalDocMonedaDoc;
		//this.importeDoc = dto.getImporteDoc();
		this.impTotalDocPES = dto.impTotalDocPES;
		this.claseDocSAP = dto.getClaseDocSAP();
		//this.impDocPES = dto.getImpDocPES();
		this.tipoCambioAFechaTipoCambio = dto.tipoCambioAFechaTipoCambio;
	}

	/**
	 * @return the idSociedad
	 */
	public String getCodigoSociedad() {
		return codigoSociedad;
	}

	/**
	 * @param idSociedad the idSociedad to set
	 */
	public void setCodigoSociedad(String idSociedad) {
		this.codigoSociedad = idSociedad;
	}

	/**
	 * @return the idProveedor
	 */
	public String getIdNumeroProveedor() {
		return idNumeroProveedor;
	}

	/**
	 * @param idProveedor the idProveedor to set
	 */
	public void setIdNumeroProveedor(String idProveedor) {
		this.idNumeroProveedor = idProveedor;
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
	public String getNroDocumentoSap() {
		return nroDocumentoSap;
	}

	/**
	 * @param numeroDocSAP the numeroDocSAP to set
	 */
	public void setNroDocumentoSap(String numeroDocSAP) {
		this.nroDocumentoSap = numeroDocSAP;
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
	 * @return the impoteDocMonedaDoc
	 */
	public BigDecimal getImporteDocMonedaDoc() {
		return importeDocMonedaDoc;
	}

	/**
	 * @param impoteDocMonedaDoc the impoteDocMonedaDoc to set
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
	 * @param impTotalDocPES the importeTotalDocumentoPesosAFechaShiva to set
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
	 * @return the monedaDocProveedor
	 */
	public MonedaEnum getMonedaDocProveedor() {
		return monedaDocProveedor;
	}
	public String getMonedaDocProveedorSigno2() {
		return monedaDocProveedor.getSigno2();
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
	 * @return the tipoCambioAFechaTipoCambio
	 */
	public BigDecimal getTipoCambioAFechaTipoCambio() {
		return tipoCambioAFechaTipoCambio;
	}
	/**
	 * @param tipoCambioAFechaTipoCambio the tipoCambioAFechaTipoCambio to set
	 */
	public void setTipoCambioAFechaTipoCambio(BigDecimal tipoCambioAFechaTipoCambio) {
		this.tipoCambioAFechaTipoCambio = tipoCambioAFechaTipoCambio;
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
