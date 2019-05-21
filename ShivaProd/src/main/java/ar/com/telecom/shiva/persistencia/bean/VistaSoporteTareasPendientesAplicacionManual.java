package ar.com.telecom.shiva.persistencia.bean;

import java.math.BigDecimal;
import java.util.Date;

public class VistaSoporteTareasPendientesAplicacionManual extends VistaSoporteResultadoBusqueda{

	private String tipoOperacion;
	private String cuit;
	private String moneda;
	private BigDecimal montoImputarEnMonedaOrigen;
	private BigDecimal montoImputarEnPesos;
	private String idTransaccionShivaDeCobro;
	private Date fechaCreacionTarea;
	private BigDecimal tipoCambio;
	private String IdTransaccion;
	private String idTransaccionDescobro;
	private String idCobro;
	private String idOperacion;
	private String idDescobro;
	private String idOperacionDescobro;
	private String sociedad;
	private String sistema;
	private String numeroTransaccionFicticio;
	
	/**
	 * @return the tipoOperacion
	 */
	public String getTipoOperacion() {
		return tipoOperacion;
	}
	/**
	 * @param tipoOperacion the tipoOperacion to set
	 */
	public void setTipoOperacion(String tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
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
	 * @return the moneda
	 */
	public String getMoneda() {
		return moneda;
	}
	/**
	 * @param moneda the moneda to set
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}
	/**
	 * @return the montoImputarEnMonedaOrigen
	 */
	public BigDecimal getMontoImputarEnMonedaOrigen() {
		return montoImputarEnMonedaOrigen;
	}
	/**
	 * @param montoImputarEnMonedaOrigen the montoImputarEnMonedaOrigen to set
	 */
	public void setMontoImputarEnMonedaOrigen(BigDecimal montoImputarEnMonedaOrigen) {
		this.montoImputarEnMonedaOrigen = montoImputarEnMonedaOrigen;
	}
	/**
	 * @return the montoImputarEnPesos
	 */
	public BigDecimal getMontoImputarEnPesos() {
		return montoImputarEnPesos;
	}
	/**
	 * @param montoImputarEnPesos the montoImputarEnPesos to set
	 */
	public void setMontoImputarEnPesos(BigDecimal montoImputarEnPesos) {
		this.montoImputarEnPesos = montoImputarEnPesos;
	}
	/**
	 * @return the idTransaccionShivaDeCobro
	 */
	public String getIdTransaccionShivaDeCobro() {
		return idTransaccionShivaDeCobro;
	}
	/**
	 * @param idTransaccionShivaDeCobro the idTransaccionShivaDeCobro to set
	 */
	public void setIdTransaccionShivaDeCobro(String idTransaccionShivaDeCobro) {
		this.idTransaccionShivaDeCobro = idTransaccionShivaDeCobro;
	}
	/**
	 * @return the fechaCreacionTarea
	 */
	public Date getFechaCreacionTarea() {
		return fechaCreacionTarea;
	}
	/**
	 * @param fechaCreacionTarea the fechaCreacionTarea to set
	 */
	public void setFechaCreacionTarea(Date fechaCreacionTarea) {
		this.fechaCreacionTarea = fechaCreacionTarea;
	}
	/**
	 * @return the tipoCambio
	 */
	public BigDecimal getTipoCambio() {
		return tipoCambio;
	}
	/**
	 * @param tipoCambio the tipoCambio to set
	 */
	public void setTipoCambio(BigDecimal tipoCambio) {
		this.tipoCambio = tipoCambio;
	}
	/**
	 * @return the idTransaccion
	 */
	public String getIdTransaccion() {
		return IdTransaccion;
	}
	/**
	 * @param idTransaccion the idTransaccion to set
	 */
	public void setIdTransaccion(String idTransaccion) {
		IdTransaccion = idTransaccion;
	}
	/**
	 * @return the idTransaccionDescobro
	 */
	public String getIdTransaccionDescobro() {
		return idTransaccionDescobro;
	}
	/**
	 * @param idTransaccionDescobro the idTransaccionDescobro to set
	 */
	public void setIdTransaccionDescobro(String idTransaccionDescobro) {
		this.idTransaccionDescobro = idTransaccionDescobro;
	}
	/**
	 * @return the idCobro
	 */
	public String getIdCobro() {
		return idCobro;
	}
	/**
	 * @param idCobro the idCobro to set
	 */
	public void setIdCobro(String idCobro) {
		this.idCobro = idCobro;
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
	 * @return the idOperacionDescobro
	 */
	public String getIdOperacionDescobro() {
		return idOperacionDescobro;
	}
	/**
	 * @param idOperacionDescobro the idOperacionDescobro to set
	 */
	public void setIdOperacionDescobro(String idOperacionDescobro) {
		this.idOperacionDescobro = idOperacionDescobro;
	}
	
	/**
	 * @return the sociedad
	 */
	public String getSociedad() {
		return sociedad;
	}
	/**
	 * @param sociedad the sociedad to set
	 */
	public void setSociedad(String sociedad) {
		this.sociedad = sociedad;
	}
	/**
	 * @return the sistema
	 */
	public String getSistema() {
		return sistema;
	}
	/**
	 * @param sistema the sistema to set
	 */
	public void setSistema(String sistema) {
		this.sistema = sistema;
	}
	public void calcularImporteEnPesos() {

		BigDecimal importeEnMonedaOrigenAcu = new BigDecimal(0);
		
		importeEnMonedaOrigenAcu = importeEnMonedaOrigenAcu.add(this.montoImputarEnMonedaOrigen.multiply(tipoCambio));
		this.montoImputarEnPesos = importeEnMonedaOrigenAcu;
	}
	/**
	 * @return the numeroTransaccionFicticio
	 */
	public String getNumeroTransaccionFicticio() {
		return numeroTransaccionFicticio;
	}
	/**
	 * @param numeroTransaccionFicticio the numeroTransaccionFicticio to set
	 */
	public void setNumeroTransaccionFicticio(String numeroTransaccionFicticio) {
		this.numeroTransaccionFicticio = numeroTransaccionFicticio;
	}
	
}
