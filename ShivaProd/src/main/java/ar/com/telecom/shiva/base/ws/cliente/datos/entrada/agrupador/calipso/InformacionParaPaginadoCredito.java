package ar.com.telecom.shiva.base.ws.cliente.datos.entrada.agrupador.calipso;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

public class InformacionParaPaginadoCredito implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	protected BigInteger 	cantidadDeRegistrosARetornar;
	protected BigInteger 	ultimoIdDocCuentasCobranzaCTA;
	protected Date 			ultimaFechaCTA;
	protected BigInteger 	ultimoClienteCTA;
	
	protected BigInteger 	ultimoIdDocCuentasCobranzaNC;
	protected Date 			ultimaFechaNC;
	protected BigInteger 	ultimoClienteNC;
	
	public BigInteger getCantidadDeRegistrosARetornar() {
		return cantidadDeRegistrosARetornar;
	}
	public void setCantidadDeRegistrosARetornar(BigInteger cantidadDeRegistrosARetornar) {
		this.cantidadDeRegistrosARetornar = cantidadDeRegistrosARetornar;
	}
	public BigInteger getUltimoIdDocCuentasCobranzaCTA() {
		return ultimoIdDocCuentasCobranzaCTA;
	}
	public void setUltimoIdDocCuentasCobranzaCTA(
			BigInteger ultimoIdDocCuentasCobranzaCTA) {
		this.ultimoIdDocCuentasCobranzaCTA = ultimoIdDocCuentasCobranzaCTA;
	}
	public Date getUltimaFechaCTA() {
		return ultimaFechaCTA;
	}
	public void setUltimaFechaCTA(Date ultimaFechaCTA) {
		this.ultimaFechaCTA = ultimaFechaCTA;
	}
	public BigInteger getUltimoClienteCTA() {
		return ultimoClienteCTA;
	}
	public void setUltimoClienteCTA(BigInteger ultimoClienteCTA) {
		this.ultimoClienteCTA = ultimoClienteCTA;
	}
	public BigInteger getUltimoIdDocCuentasCobranzaNC() {
		return ultimoIdDocCuentasCobranzaNC;
	}
	public void setUltimoIdDocCuentasCobranzaNC(
			BigInteger ultimoIdDocCuentasCobranzaNC) {
		this.ultimoIdDocCuentasCobranzaNC = ultimoIdDocCuentasCobranzaNC;
	}
	public Date getUltimaFechaNC() {
		return ultimaFechaNC;
	}
	public void setUltimaFechaNC(Date ultimaFechaNC) {
		this.ultimaFechaNC = ultimaFechaNC;
	}
	public BigInteger getUltimoClienteNC() {
		return ultimoClienteNC;
	}
	public void setUltimoClienteNC(BigInteger ultimoClienteNC) {
		this.ultimoClienteNC = ultimoClienteNC;
	}
	
}
