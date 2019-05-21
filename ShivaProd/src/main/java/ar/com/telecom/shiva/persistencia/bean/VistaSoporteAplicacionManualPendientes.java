package ar.com.telecom.shiva.persistencia.bean;

import java.math.BigDecimal;
import java.util.Date;

import ar.com.telecom.shiva.persistencia.modelo.Modelo;

/**
 * 
 * @author U587496 Guido.Settecerze
 *
 */
public class VistaSoporteAplicacionManualPendientes extends Modelo {

	private static final long serialVersionUID = 1L;
	private Date fechaCreacionTareaAprobacion;
	private String nombreArchivo;
	private String cuit;
	private String tipoOperacion;
	private String moneda;
	private BigDecimal montoImputarEnMonedaOrigen;
	private BigDecimal tipoDeCambio;
	private BigDecimal montoImputarEnPesos;
	private String idTransaccionCobro;
	
	public Date getFechaCreacionTareaAprobacion() {
		return fechaCreacionTareaAprobacion;
	}
	public void setFechaCreacionTareaAprobacion(Date fechaCreacionTareaAprobacion) {
		this.fechaCreacionTareaAprobacion = fechaCreacionTareaAprobacion;
	}
	public String getNombreArchivo() {
		return nombreArchivo;
	}
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
	public String getCuit() {
		return cuit;
	}
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}
	public String getTipoOperacion() {
		return tipoOperacion;
	}
	public void setTipoOperacion(String tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
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
	public BigDecimal getTipoDeCambio() {
		return tipoDeCambio;
	}
	public void setTipoDeCambio(BigDecimal tipoDeCambio) {
		this.tipoDeCambio = tipoDeCambio;
	}
	public BigDecimal getMontoImputarEnPesos() {
		return montoImputarEnPesos;
	}
	public void setMontoImputarEnPesos(BigDecimal montoImputarEnPesos) {
		this.montoImputarEnPesos = montoImputarEnPesos;
	}
	public String getIdTransaccionCobro() {
		return idTransaccionCobro;
	}
	public void setIdTransaccionCobro(String idTransaccionCobro) {
		this.idTransaccionCobro = idTransaccionCobro;
	}
		
}