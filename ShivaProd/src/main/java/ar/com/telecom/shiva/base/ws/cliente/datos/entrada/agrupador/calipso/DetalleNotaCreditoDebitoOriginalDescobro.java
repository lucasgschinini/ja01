package ar.com.telecom.shiva.base.ws.cliente.datos.entrada.agrupador.calipso;

import java.math.BigInteger;

import ar.com.telecom.shiva.base.ws.cliente.datos.IdDocumento;

public class DetalleNotaCreditoDebitoOriginalDescobro {
	
	protected IdDocumento documentoGenerado;
	protected BigInteger idDocumentoCuentasCobranza;
	
	/*************************************************
	 * Getters & Setters
	 *************************************************/
	
	public IdDocumento getDocumentoGenerado() {
		return documentoGenerado;
	}
	public void setDocumentoGenerado(IdDocumento documentoGenerado) {
		this.documentoGenerado = documentoGenerado;
	}
	public BigInteger getIdDocumentoCuentasCobranza() {
		return idDocumentoCuentasCobranza;
	}
	public void setIdDocumentoCuentasCobranza(BigInteger idDocumentoCuentasCobranza) {
		this.idDocumentoCuentasCobranza = idDocumentoCuentasCobranza;
	}
	
}