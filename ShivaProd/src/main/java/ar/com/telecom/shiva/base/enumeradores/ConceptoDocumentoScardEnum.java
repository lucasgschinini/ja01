package ar.com.telecom.shiva.base.enumeradores;

/**
 * Este enum posee como 'claves' la familia de operaciones que pueden generarse al armar un cobro.
 * Cada clave esta formada adem�s, por la leyenda o concepto que debe figurar en cada documento Scard generado, correspondiente a dicha operaci�n.
 *  * 
 * @author u564030 Pablo M. Ibarrola
 *  
 */
public enum ConceptoDocumentoScardEnum {
	
	// Para notas de recibo
	COBRO_DE_FACTURA("PAGO DE FACTURAS (C�DIGO DE OPERACI�N {0})"),
	COBRO_DE_DUC("PAGO DE DOCUMENTOS (C�DIGO DE OPERACI�N {0})"), 
	REINTEGRO("REINTEGRO (C�DIGO DE OPERACI�N {0})"),
	ENVIO_A_GANANCIA("REINTEGRO (C�DIGO DE OPERACI�N {0})"),
	SALIDA_APLICACION_MANUAL("APLICACI�N MANUAL (C�DIGO DE OPERACI�N {0})"),
	SALIDA_APLICACION_MANUAL_CONJUNTO_DE_DEBITOS("APLICACI�N MANUAL (C�DIGO DE OPERACI�N {0})"),
	
	// Para notas de reembolso
	REVERSION_DE_COBRO_DE_FACTURA("REEMBOLSO DE FACTURAS (C�DIGO DE OPERACI�N {0})"),
	REVERSION_DE_COBRO_DE_DUC("PAGO DE DOCUMENTOS (C�DIGO DE OPERACI�N {0})"), 
	REVERSION_DE_REINTEGRO("REVERSI�N DE REINTEGRO (C�DIGO DE OPERACI�N {0})"),
	REVERSION_DE_ENVIO_A_GANANCIA("REVERSI�N DE REINTEGRO (C�DIGO DE OPERACI�N {0})"),
	REVERSION_DE_SALIDA_APLICACION_MANUAL("REVERSI�N APLICACI�N MANUAL (C�DIGO DE OPERACI�N {0})"),
	REVERSION_DE_SALIDA_APLICACION_MANUAL_CONJUNTO_DE_DEBITOS("REVERSI�N APLICACI�N MANUAL (C�DIGO DE OPERACI�N {0})");
	
	String leyenda;
	
	/**
	 * 
	 * @param descripcion
	 */
	private ConceptoDocumentoScardEnum(String descripcion) {
	    this.leyenda = descripcion;
	}
	
	/**
	 * 
	 * @return
	 */
	public String descripcion() {
	    return this.leyenda;
	}

	/**
	 * 
	 * @param name
	 * @return
	 */
	public static ConceptoDocumentoScardEnum getEnumByName(String name) {
		
		for (ConceptoDocumentoScardEnum estado : ConceptoDocumentoScardEnum.values()) {
			if (estado.name().equals(name)) {
				return estado;
			}
		}
		return null; 
	}
}
