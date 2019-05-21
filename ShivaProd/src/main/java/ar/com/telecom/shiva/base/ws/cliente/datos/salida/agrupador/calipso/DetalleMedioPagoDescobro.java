package ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.calipso;

import java.math.BigInteger;

import ar.com.telecom.shiva.base.ws.cliente.datos.IdDocumento;

public class DetalleMedioPagoDescobro {
	
	protected IdDocumento idMedioPago;
	protected BigInteger idDocCtasCob;
	protected Resultado resultadoDescobroMedioPago;
	
	/*************************************************
	 * Getters & Setters
	 *************************************************/

	public IdDocumento getIdMedioPago() {
		return idMedioPago;
	}
	public void setIdMedioPago(IdDocumento idMedioPago) {
		this.idMedioPago = idMedioPago;
	}
	public BigInteger getIdDocCtasCob() {
		return idDocCtasCob;
	}
	public void setIdDocCtasCob(BigInteger idDocCtasCob) {
		this.idDocCtasCob = idDocCtasCob;
	}
	public Resultado getResultadoDescobroMedioPago() {
		return resultadoDescobroMedioPago;
	}
	public void setResultadoDescobroMedioPago(Resultado resultadoDescobroMedioPago) {
		this.resultadoDescobroMedioPago = resultadoDescobroMedioPago;
	}
	
}