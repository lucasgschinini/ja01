package ar.com.telecom.shiva.base.jms.datos.entrada.agrupador;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

public class MicInformacionPaginadoCredito implements Serializable {

	private static final long serialVersionUID = 1L;

	protected Integer		cantidadRegistrosARetornar;
	
	//Remanente
	protected BigInteger	ultimoCodigoClienteRemanente;
	protected Integer		ultimaCuentaRemanente;
	protected Integer		ultimoCodigoTipoRemanente;
	protected Date			ultimaFechaRemanente;
	
	//Nota Credito
	protected BigInteger	ultimoNumeroReferenciaMicNC;
	protected Date			ultimaFechaNotaCredito;
	protected BigInteger 	ultimoClienteNotaCredito;
	protected Integer		ultimaCuentaNotaCredito;
	
	public Integer getCantidadRegistrosARetornar() {
		return cantidadRegistrosARetornar;
	}
	public void setCantidadRegistrosARetornar(Integer cantidadRegistrosARetornar) {
		this.cantidadRegistrosARetornar = cantidadRegistrosARetornar;
	}
	
	public BigInteger getUltimoCodigoClienteRemanente() {
		return ultimoCodigoClienteRemanente;
	}
	public void setUltimoCodigoClienteRemanente(
			BigInteger ultimoCodigoClienteRemanente) {
		this.ultimoCodigoClienteRemanente = ultimoCodigoClienteRemanente;
	}
	public Integer getUltimaCuentaRemanente() {
		return ultimaCuentaRemanente;
	}
	public void setUltimaCuentaRemanente(Integer ultimaCuentaRemanente) {
		this.ultimaCuentaRemanente = ultimaCuentaRemanente;
	}
	public Integer getUltimoCodigoTipoRemanente() {
		return ultimoCodigoTipoRemanente;
	}
	public void setUltimoCodigoTipoRemanente(Integer ultimoCodigoTipoRemanente) {
		this.ultimoCodigoTipoRemanente = ultimoCodigoTipoRemanente;
	}
	public Date getUltimaFechaRemanente() {
		return ultimaFechaRemanente;
	}
	public void setUltimaFechaRemanente(Date ultimaFechaRemanente) {
		this.ultimaFechaRemanente = ultimaFechaRemanente;
	}
	public BigInteger getUltimoNumeroReferenciaMicNC() {
		return ultimoNumeroReferenciaMicNC;
	}
	public void setUltimoNumeroReferenciaMicNC(
			BigInteger ultimoNumeroReferenciaMicNC) {
		this.ultimoNumeroReferenciaMicNC = ultimoNumeroReferenciaMicNC;
	}
	public Date getUltimaFechaNotaCredito() {
		return ultimaFechaNotaCredito;
	}
	public void setUltimaFechaNotaCredito(Date ultimaFechaNotaCredito) {
		this.ultimaFechaNotaCredito = ultimaFechaNotaCredito;
	}
	public BigInteger getUltimoClienteNotaCredito() {
		return ultimoClienteNotaCredito;
	}
	public void setUltimoClienteNotaCredito(BigInteger ultimoClienteNotaCredito) {
		this.ultimoClienteNotaCredito = ultimoClienteNotaCredito;
	}
	public Integer getUltimaCuentaNotaCredito() {
		return ultimaCuentaNotaCredito;
	}
	public void setUltimaCuentaNotaCredito(Integer ultimaCuentaNotaCredito) {
		this.ultimaCuentaNotaCredito = ultimaCuentaNotaCredito;
	}
	
	
	
	
}
