package ar.com.telecom.shiva.negocio.simulacionCoherencia;

import java.io.Serializable;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdDocumentoCap;

/**
 * 
 * @author u578936 Max.Uehara
 */
public class ShvCobEdDocumentoCapWrapper implements Serializable {
	
	private static final long serialVersionUID = 20170102l;
	private ShvCobEdDocumentoCap documento = null;
	private String idPantalla = "";
	
	public ShvCobEdDocumentoCapWrapper() {
	}
	
	public ShvCobEdDocumentoCapWrapper(ShvCobEdDocumentoCap documento, String idPantalla) throws NegocioExcepcion {
		super();
		try {
			this.documento = documento;
		} catch (Exception e) {
			throw new NegocioExcepcion(e.getMessage());
		}
		this.documento.setOtrosMedioPago(null);
		this.idPantalla = idPantalla;
		this.documento.setIdDocumentoCap(null);
	}

	/**
	 * @return the documento
	 */
	public ShvCobEdDocumentoCap getDocumento() {
		return documento;
	}

	/**
	 * @param documento the documento to set
	 */
	public void setDocumento(ShvCobEdDocumentoCap documento) {
		this.documento = documento;
	}

	/**
	 * @return the idPantalla
	 */
	public String getIdPantalla() {
		return idPantalla;
	}

	/**
	 * @param idPantalla the idPantalla to set
	 */
	public void setIdPantalla(String idPantalla) {
		this.idPantalla = idPantalla;
	}	
}
