package ar.com.telecom.shiva.persistencia.bean;

import java.math.BigDecimal;
import java.util.Date;

public class VistaSoporteAutomatizacionConfirmacionAplicacionManual extends VistaSoporteResultadoBusqueda{

	private String tipoOperacion;
	private String cuit;
	private String moneda;
	private BigDecimal montoImputarEnMonedaOrigen;
	private BigDecimal montoImputarEnPesos;
	private String idTransaccionShivaDeCobro;
	private Date fechaRealDePago;
	private String tipoDeCredito;
	private String referenciaDelValor;
	private BigDecimal tipoCambio;
	private BigDecimal idTratamientoDiferencia;
	private String IdTransaccion;
	private String sistemaOrigenMedioPago;
	private String poseeAdjunto;
	private String listaOperacionesExternas;
	private String idTransaccionDescobro;
	private String referenciaMedioDePago;
	private String idCobro;
	private String idOperacion;
	private String idDescobro;
	private String idOperacionDescobro;
	
	public String getTipoOperacion() {
		return tipoOperacion;
	}
	public void setTipoOperacion(String tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}
	public String getCuit() {
		return cuit;
	}
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}
	public String getMoneda() {
		return moneda;
	}
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}
	public BigDecimal getMontoImputarEnMonedaOrigen() {
		return montoImputarEnMonedaOrigen;
	}
	public void setMontoImputarEnMonedaOrigen(BigDecimal montoImputarEnMonedaOrigen) {
		this.montoImputarEnMonedaOrigen = montoImputarEnMonedaOrigen;
	}
	public BigDecimal getMontoImputarEnPesos() {
		return montoImputarEnPesos;
	}
	public void setMontoImputarEnPesos(BigDecimal montoImputarEnPesos) {
		this.montoImputarEnPesos = montoImputarEnPesos;
	}
	public String getIdTransaccionShivaDeCobro() {
		return idTransaccionShivaDeCobro;
	}
	public void setIdTransaccionShivaDeCobro(String idTransaccionShiva) {
		this.idTransaccionShivaDeCobro = idTransaccionShiva;
	}
	public Date getFechaRealDePago() {
		return fechaRealDePago;
	}
	public void setFechaRealDePago(Date fechaRealDePago) {
		this.fechaRealDePago = fechaRealDePago;
	}
	public String getTipoDeCredito() {
		return tipoDeCredito;
	}
	public void setTipoDeCredito(String tipoDeCredito) {
		this.tipoDeCredito = tipoDeCredito;
	}
	public String getReferenciaDelValor() {
		return referenciaDelValor;
	}
	public void setReferenciaDelValor(String referenciaDelValor) {
		this.referenciaDelValor = referenciaDelValor;
	}
	public BigDecimal getTipoCambio() {
		return tipoCambio;
	}
	public void setTipoCambio(BigDecimal tipoCambio) {
		this.tipoCambio = tipoCambio;
	}
	public BigDecimal getIdTratamientoDiferencia() {
		return idTratamientoDiferencia;
	}
	public void setIdTratamientoDiferencia(BigDecimal idTratamientoDiferencia) {
		this.idTratamientoDiferencia = idTratamientoDiferencia;
	}
	public String getIdTransaccion() {
		return IdTransaccion;
	}
	public void setIdTransaccion(String idTransaccion) {
		IdTransaccion = idTransaccion;
	}
	public String getSistemaOrigenMedioPago() {
		return sistemaOrigenMedioPago;
	}
	public void setSistemaOrigenMedioPago(String sistemaOrigenMedioPago) {
		this.sistemaOrigenMedioPago = sistemaOrigenMedioPago;
	}
	public String getPoseeAdjunto() {
		return poseeAdjunto;
	}
	public void setPoseeAdjunto(String poseeAdjunto) {
		this.poseeAdjunto = poseeAdjunto;
	}
	public String getListaOperacionesExternas() {
		return listaOperacionesExternas;
	}
	public void setListaOperacionesExternas(String listaOperacionesExternas) {
		this.listaOperacionesExternas = listaOperacionesExternas;
	}

	public void calcularImporteEnPesos() {

		BigDecimal importeEnMonedaOrigenAcu = new BigDecimal(0);
		
		importeEnMonedaOrigenAcu = importeEnMonedaOrigenAcu.add(this.montoImputarEnMonedaOrigen.multiply(tipoCambio));
		this.montoImputarEnPesos = importeEnMonedaOrigenAcu;

	}
	public String getIdTransaccionDescobro() {
		return idTransaccionDescobro;
	}
	public void setIdTransaccionDescobro(String idTransaccionDescobro) {
		this.idTransaccionDescobro = idTransaccionDescobro;
	}
	/**
	 * @return the referenciaMedioDePago
	 */
	public String getReferenciaMedioDePago() {
		return referenciaMedioDePago;
	}
	/**
	 * @param referenciaMedioDePago the referenciaMedioDePago to set
	 */
	public void setReferenciaMedioDePago(String referenciaMedioDePago) {
		this.referenciaMedioDePago = referenciaMedioDePago;
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
	
	
	
}
