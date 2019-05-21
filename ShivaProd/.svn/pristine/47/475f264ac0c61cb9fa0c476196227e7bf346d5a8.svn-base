package ar.com.telecom.shiva.base.enumeradores;

/**
 * Este enum posee como 'claves' la familia de operaciones que pueden generarse al armar un cobro.
 * Cada clave esta formada además, por la leyenda o concepto que debe figurar en cada documento Scard generado, correspondiente a dicha operación.
 *  * 
 * @author u564030 Pablo M. Ibarrola
 *  
 */
public enum ConceptoDocumentoScardEnum {
	
	// Para notas de recibo
	COBRO_DE_FACTURA("PAGO DE FACTURAS (CÓDIGO DE OPERACIÓN {0})"),
	COBRO_DE_DUC("PAGO DE DOCUMENTOS (CÓDIGO DE OPERACIÓN {0})"), 
	REINTEGRO("REINTEGRO (CÓDIGO DE OPERACIÓN {0})"),
	ENVIO_A_GANANCIA("REINTEGRO (CÓDIGO DE OPERACIÓN {0})"),
	SALIDA_APLICACION_MANUAL("APLICACIÓN MANUAL (CÓDIGO DE OPERACIÓN {0})"),
	SALIDA_APLICACION_MANUAL_CONJUNTO_DE_DEBITOS("APLICACIÓN MANUAL (CÓDIGO DE OPERACIÓN {0})"),
	
	// Para notas de reembolso
	REVERSION_DE_COBRO_DE_FACTURA("REEMBOLSO DE FACTURAS (CÓDIGO DE OPERACIÓN {0})"),
	REVERSION_DE_COBRO_DE_DUC("PAGO DE DOCUMENTOS (CÓDIGO DE OPERACIÓN {0})"), 
	REVERSION_DE_REINTEGRO("REVERSIÓN DE REINTEGRO (CÓDIGO DE OPERACIÓN {0})"),
	REVERSION_DE_ENVIO_A_GANANCIA("REVERSIÓN DE REINTEGRO (CÓDIGO DE OPERACIÓN {0})"),
	REVERSION_DE_SALIDA_APLICACION_MANUAL("REVERSIÓN APLICACIÓN MANUAL (CÓDIGO DE OPERACIÓN {0})"),
	REVERSION_DE_SALIDA_APLICACION_MANUAL_CONJUNTO_DE_DEBITOS("REVERSIÓN APLICACIÓN MANUAL (CÓDIGO DE OPERACIÓN {0})");
	
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
