package ar.com.telecom.shiva.presentacion.bean.dto;

import java.math.BigDecimal;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;

@JsonIgnoreProperties(ignoreUnknown=true)
public class DescobroDocumentoRelacionadoDto extends DTO {

	private static final long serialVersionUID = 1L;

	private String idDocumentoRelacionado;
	private String idDescobro;
	private String idOperacion;
	private String nroTransaccion;
	private String nroTransaccionFicticio; 
	private String idCobranzaGenerada;
	private String fechaImputacion;
	private String importeGenerado;
	private String importeCapitalGenereado;
	private String importeImpuestosGenerado;
	private String tipoComprobanteGenerado;
	private String claseComprobanteGenerado;
	private String sucursalComprobanteGenerado;
	private String nroComprobanteGenerado;
	private String idDocumentoCuentasCobranzaGenerado;
	private String fechaVencGenerada;
	private String importeAplicadoGenerado;
	private String origenDocumentoGenerado;
	private String origenDocumentoGeneradoDesc;
	private String tipoComprobanteOriginal;
	private String claseComprobanteOriginal;
	private String sucursalComprobanteOriginal;
	private String nroComprobanteOriginal;
	private String idDocumentoCuentasCobranzaOriginal;
	private String importeAplicadoOriginal;
	private String origenDocumentoOriginal;
	private String origenDocumentoOriginalDesc;
	private String sistemaOrigen;
	
	
	
	/**********************************************/
	/***** GETTERS AND SETTERS 			 **********/
	/**********************************************/
	
	
	/**
	 * @return the idDocumentoRelacionado
	 */
	public String getIdDocumentoRelacionado() {
		return idDocumentoRelacionado;
	}
	/**
	 * @param idDocumentoRelacionado the idDocumentoRelacionado to set
	 */
	public void setIdDocumentoRelacionado(String idDocumentoRelacionado) {
		this.idDocumentoRelacionado = idDocumentoRelacionado;
	}
	/**
	 * @return the idDescobro
	 */
	public String getIdDescobro() {
		return idDescobro;
	}
	/**
	 * @param idDescobro the idDescobro to set
	 */
	public void setIdDescobro(String idDescobro) {
		this.idDescobro = idDescobro;
	}
	/**
	 * @return the idOperacion
	 */
	public String getIdOperacion() {
		return idOperacion;
	}
	/**
	 * @param idOperacion the idOperacion to set
	 */
	public void setIdOperacion(String idOperacion) {
		this.idOperacion = idOperacion;
	}
	/**
	 * @return the nroTransaccion
	 */
	public String getNroTransaccion() {
		return nroTransaccion;
	}
	/**
	 * @param nroTransaccion the nroTransaccion to set
	 */
	public void setNroTransaccion(String nroTransaccion) {
		this.nroTransaccion = nroTransaccion;
	}
	/**
	 * @return the idCobranzaGenerada
	 */
	public String getIdCobranzaGenerada() {
		return idCobranzaGenerada;
	}
	/**
	 * @param idCobranzaGenerada the idCobranzaGenerada to set
	 */
	public void setIdCobranzaGenerada(String idCobranzaGenerada) {
		this.idCobranzaGenerada = idCobranzaGenerada;
	}
	/**
	 * @return the importeGenerado
	 */
	public String getImporteGenerado() {
		return importeGenerado;
	}
	/**
	 * @param importeGenerado the importeGenerado to set
	 */
	public void setImporteGenerado(String importeGenerado) {
		this.importeGenerado = importeGenerado;
	}
	/**
	 * @return the importeCapitalGenereado
	 */
	public String getImporteCapitalGenereado() {
		return importeCapitalGenereado;
	}
	/**
	 * @param importeCapitalGenereado the importeCapitalGenereado to set
	 */
	public void setImporteCapitalGenereado(String importeCapitalGenereado) {
		this.importeCapitalGenereado = importeCapitalGenereado;
	}
	/**
	 * @return the importeImpuestosGenerado
	 */
	public String getImporteImpuestosGenerado() {
		return importeImpuestosGenerado;
	}
	/**
	 * @param importeImpuestosGenerado the importeImpuestosGenerado to set
	 */
	public void setImporteImpuestosGenerado(String importeImpuestosGenerado) {
		this.importeImpuestosGenerado = importeImpuestosGenerado;
	}
	/**
	 * @return the tipoComprobanteGenerado
	 */
	public String getTipoComprobanteGenerado() {
		return tipoComprobanteGenerado;
	}
	/**
	 * @param tipoComprobanteGenerado the tipoComprobanteGenerado to set
	 */
	public void setTipoComprobanteGenerado(String tipoComprobanteGenerado) {
		this.tipoComprobanteGenerado = tipoComprobanteGenerado;
	}
	/**
	 * @return the claseComprobanteGenerado
	 */
	public String getClaseComprobanteGenerado() {
		return claseComprobanteGenerado;
	}
	/**
	 * @param claseComprobanteGenerado the claseComprobanteGenerado to set
	 */
	public void setClaseComprobanteGenerado(String claseComprobanteGenerado) {
		this.claseComprobanteGenerado = claseComprobanteGenerado;
	}
	/**
	 * @return the sucursalComprobanteGenerado
	 */
	public String getSucursalComprobanteGenerado() {
		return sucursalComprobanteGenerado;
	}
	/**
	 * @param sucursalComprobanteGenerado the sucursalComprobanteGenerado to set
	 */
	public void setSucursalComprobanteGenerado(String sucursalComprobanteGenerado) {
		this.sucursalComprobanteGenerado = sucursalComprobanteGenerado;
	}
	/**
	 * @return the nroComprobanteGenerado
	 */
	public String getNroComprobanteGenerado() {
		return nroComprobanteGenerado;
	}
	/**
	 * @param nroComprobanteGenerado the nroComprobanteGenerado to set
	 */
	public void setNroComprobanteGenerado(String nroComprobanteGenerado) {
		this.nroComprobanteGenerado = nroComprobanteGenerado;
	}
	/**
	 * @return the idDocumentoCuentasCobranzaGenerado
	 */
	public String getIdDocumentoCuentasCobranzaGenerado() {
		return idDocumentoCuentasCobranzaGenerado;
	}
	/**
	 * @param idDocumentoCuentasCobranzaGenerado the idDocumentoCuentasCobranzaGenerado to set
	 */
	public void setIdDocumentoCuentasCobranzaGenerado(
			String idDocumentoCuentasCobranzaGenerado) {
		this.idDocumentoCuentasCobranzaGenerado = idDocumentoCuentasCobranzaGenerado;
	}
	/**
	 * @return the fechaVencGenerada
	 */
	public String getFechaVencGenerada() {
		return fechaVencGenerada;
	}
	/**
	 * @param fechaVencGenerada the fechaVencGenerada to set
	 */
	public void setFechaVencGenerada(String fechaVencGenerada) {
		this.fechaVencGenerada = fechaVencGenerada;
	}
	/**
	 * @return the importeAplicadoGenerado
	 */
	public String getImporteAplicadoGenerado() {
		return importeAplicadoGenerado;
	}
	/**
	 * @param importeAplicadoGenerado the importeAplicadoGenerado to set
	 */
	public void setImporteAplicadoGenerado(String importeAplicadoGenerado) {
		this.importeAplicadoGenerado = importeAplicadoGenerado;
	}
	
	public String getImporteAplicadoGeneradoFormateado() {
		if(!Validaciones.isNullEmptyOrDash(importeAplicadoGenerado)){
			return Utilidad.formatCurrency(new BigDecimal(importeAplicadoGenerado),2);
		} else {
			return "-";
		}
	}
	/**
	 * @return the origenDocumentoGenerado
	 */
	public String getOrigenDocumentoGenerado() {
		return origenDocumentoGenerado;
	}
	/**
	 * @param origenDocumentoGenerado the origenDocumentoGenerado to set
	 */
	public void setOrigenDocumentoGenerado(String origenDocumentoGenerado) {
		this.origenDocumentoGenerado = origenDocumentoGenerado;
	}
	
	public String getOrigenDocumentoGeneradoDesc() {
		return origenDocumentoGeneradoDesc;
	}
	public void setOrigenDocumentoGeneradoDesc(String origenDocumentoGeneradoDesc) {
		this.origenDocumentoGeneradoDesc = origenDocumentoGeneradoDesc;
	}
	/**
	 * @return the tipoComprobanteOriginal
	 */
	public String getTipoComprobanteOriginal() {
		return tipoComprobanteOriginal;
	}
	/**
	 * @param tipoComprobanteOriginal the tipoComprobanteOriginal to set
	 */
	public void setTipoComprobanteOriginal(String tipoComprobanteOriginal) {
		this.tipoComprobanteOriginal = tipoComprobanteOriginal;
	}
	/**
	 * @return the claseComprobanteOriginal
	 */
	public String getClaseComprobanteOriginal() {
		return claseComprobanteOriginal;
	}
	/**
	 * @param claseComprobanteOriginal the claseComprobanteOriginal to set
	 */
	public void setClaseComprobanteOriginal(String claseComprobanteOriginal) {
		this.claseComprobanteOriginal = claseComprobanteOriginal;
	}
	/**
	 * @return the sucursalComprobanteOriginal
	 */
	public String getSucursalComprobanteOriginal() {
		return sucursalComprobanteOriginal;
	}
	/**
	 * @param sucursalComprobanteOriginal the sucursalComprobanteOriginal to set
	 */
	public void setSucursalComprobanteOriginal(String sucursalComprobanteOriginal) {
		this.sucursalComprobanteOriginal = sucursalComprobanteOriginal;
	}
	/**
	 * @return the nroComprobanteOriginal
	 */
	public String getNroComprobanteOriginal() {
		return nroComprobanteOriginal;
	}
	/**
	 * @param nroComprobanteOriginal the nroComprobanteOriginal to set
	 */
	public void setNroComprobanteOriginal(String nroComprobanteOriginal) {
		this.nroComprobanteOriginal = nroComprobanteOriginal;
	}
	/**
	 * @return the idDocumentoCuentasCobranzaOriginal
	 */
	public String getIdDocumentoCuentasCobranzaOriginal() {
		return idDocumentoCuentasCobranzaOriginal;
	}
	/**
	 * @param idDocumentoCuentasCobranzaOriginal the idDocumentoCuentasCobranzaOriginal to set
	 */
	public void setIdDocumentoCuentasCobranzaOriginal(
			String idDocumentoCuentasCobranzaOriginal) {
		this.idDocumentoCuentasCobranzaOriginal = idDocumentoCuentasCobranzaOriginal;
	}
	/**
	 * @return the fechaImputacion
	 */
	public String getFechaImputacion() {
		return fechaImputacion;
	}
	/**
	 * @param fechaImputacion the fechaImputacion to set
	 */
	public void setFechaImputacion(String fechaImputacion) {
		this.fechaImputacion = fechaImputacion;
	}

	/**
	 * @return the sistemaOrigen
	 */
	public String getSistemaOrigen(){
		return sistemaOrigen;
	}
	/**
	 * @param sistemaOrigen the sistemaOrigen to set
	 */
	public void setSistemaOrigen(String sistemaOrigen) {
		this.sistemaOrigen = sistemaOrigen;
	}
	
	public String getImporteAplicadoOriginal() {
		return importeAplicadoOriginal;
	}
	public void setImporteAplicadoOriginal(String importeAplicadoOriginal) {
		this.importeAplicadoOriginal = importeAplicadoOriginal;
	}
	public String getImporteAplicadoOriginalFormateado() {
		if(!Validaciones.isNullEmptyOrDash(importeAplicadoOriginal)){
			return Utilidad.formatCurrency(new BigDecimal(importeAplicadoOriginal),2);
		} else {
			return "-";
		}
	}
	
	public String getOrigenDocumentoOriginal() {
		return origenDocumentoOriginal;
	}
	public void setOrigenDocumentoOriginal(String origenDocumentoOriginal) {
		this.origenDocumentoOriginal = origenDocumentoOriginal;
	}
	public String getNroTransaccionFormateado() {
		if(!Validaciones.isNullEmptyOrDash(idOperacion)
				&& !Validaciones.isNullEmptyOrDash(nroTransaccion)){
			return Utilidad.rellenarCerosIzquierda(idOperacion, 7) + "." + Utilidad.rellenarCerosIzquierda(nroTransaccion, 5);
		}
		return "";
	}
	public String getNroTransaccionFicticioFormateado() {
		if(!Validaciones.isNullEmptyOrDash(idOperacion)
				&& !Validaciones.isNullEmptyOrDash(nroTransaccionFicticio)){
			return Utilidad.rellenarCerosIzquierda(idOperacion, 7) + "." + Utilidad.rellenarCerosIzquierda(nroTransaccionFicticio, 5);
		}
		return "";
	}
	public String getNroDocumentoGeneradoFormateado() {
	
		if(!Validaciones.isNullEmptyOrDash(claseComprobanteGenerado)){
			return claseComprobanteGenerado + "-" + sucursalComprobanteGenerado + "-" + nroComprobanteGenerado;
		} else {
			return sucursalComprobanteGenerado + "-" + nroComprobanteGenerado;
		}
	}
	
	public String getNroDocumentoOriginalFormateado() {
		
		if(!Validaciones.isNullEmptyOrDash(claseComprobanteOriginal)){
			return claseComprobanteOriginal + "-" + sucursalComprobanteOriginal + "-" + nroComprobanteOriginal;
		} else {
			return sucursalComprobanteOriginal + "-" + nroComprobanteOriginal;
		}
	}
	public String getOrigenDocumentoOriginalDesc() {
		return origenDocumentoOriginalDesc;
	}
	public void setOrigenDocumentoOriginalDesc(String origenDocumentoOriginalDesc) {
		this.origenDocumentoOriginalDesc = origenDocumentoOriginalDesc;
	}
	/**
	 * @return the nroTransaccionFicticio
	 */
	public String getNroTransaccionFicticio() {
		return nroTransaccionFicticio;
	}
	/**
	 * @param nroTransaccionFicticio the nroTransaccionFicticio to set
	 */
	public void setNroTransaccionFicticio(String nroTransaccionFicticio) {
		this.nroTransaccionFicticio = nroTransaccionFicticio;
	}
		
}
