package ar.com.telecom.shiva.base.enumeradores;

import ar.com.telecom.shiva.base.constantes.Propiedades;

public enum LeyendaPiePaginaDocumentoScardEnum {

	DOCUMENTO_GENERADO_POR_DIF_CAMBIO("scard.notas.leyenda.documentoGeneradoDiferenciaCambio"),
	EXISTEN_INTERESES_TRASLADAS_PROXIMA_FACTURA("scard.notas.leyenda.interesTrasladarProximaFactura");
	
	private String codigoMensajesProperties;
	
	private LeyendaPiePaginaDocumentoScardEnum(String descripcion) {
		this.codigoMensajesProperties = descripcion;
	}
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public static LeyendaPiePaginaDocumentoScardEnum getEnumByName(String name) {
		for (LeyendaPiePaginaDocumentoScardEnum enumerador : LeyendaPiePaginaDocumentoScardEnum.values()) {
			if (enumerador.name().equalsIgnoreCase(name)) {
				return enumerador;
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getLeyendaPiePagina() {
		return Propiedades.MENSAJES_PROPIEDADES.getString(codigoMensajesProperties);

	}
}

