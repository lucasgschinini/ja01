package ar.com.telecom.shiva.base.ws.cliente.datos.entrada.agrupador.calipso;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.ws.cliente.datos.IdDocumento;

public class DetalleCTAoNotaCreditoRespuesta {
	
	protected BigDecimal importe;
	protected IdDocumento ctaResultante;
//	Sprint 5
	protected BigInteger idDocumentoCuentasCobranza;
	protected Date fechaAltaOriginalCTA;
	
	//Dolares
	protected MonedaEnum moneda;
	
	public BigDecimal getImporte() {
		return importe;
	}
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}
	public IdDocumento getCtaResultante() {
		return ctaResultante;
	}
	public void setCtaResultante(IdDocumento ctaResultante) {
		this.ctaResultante = ctaResultante;
	}
	public Date getFechaAltaOriginalCTA() {
		return fechaAltaOriginalCTA;
	}
	public void setFechaAltaOriginalCTA(Date fechaAltaOriginalCTA) {
		this.fechaAltaOriginalCTA = fechaAltaOriginalCTA;
	}
	public BigInteger getIdDocumentoCuentasCobranza() {
		return idDocumentoCuentasCobranza;
	}
	public void setIdDocumentoCuentasCobranza(BigInteger idDocumentoCuentasCobranza) {
		this.idDocumentoCuentasCobranza = idDocumentoCuentasCobranza;
	}
	public MonedaEnum getMoneda() {
		return moneda;
	}
	public void setMoneda(MonedaEnum moneda) {
		this.moneda = moneda;
	}
	
}