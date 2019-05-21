package ar.com.telecom.shiva.base.enumeradores;

/**
 * 
 * @author u564030
 */
public enum TipoDocumentoGeneradoEnum {
	
	RECIBO("Recibo", "RC"),
	NOTA_REEMBOLSO("Nota de Reembolso", "NR");

	String descripcion;
	String tipoDocumentoGeneradoScard;

	/**
	 * 
	 * @param descripcion
	 * @param tipoDocumentoGeneradoScard
	 */
	private TipoDocumentoGeneradoEnum(String descripcion, String tipoDocumentoGeneradoScard) {
	    this.descripcion = descripcion;
	    this.tipoDocumentoGeneradoScard = tipoDocumentoGeneradoScard;
	}
	
	/**
	 * 
	 * @param cadena
	 * @return
	 */
	public static TipoDocumentoGeneradoEnum getEnumByName(String cadena){
		for (TipoDocumentoGeneradoEnum en : TipoDocumentoGeneradoEnum.values()) {
			if (en.name().equalsIgnoreCase(cadena)){
				return en;
			}
		}
		return null;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @return the tipoDocumentoGeneradoScard
	 */
	public String getTipoDocumentoGeneradoScard() {
		return tipoDocumentoGeneradoScard;
	}
}
