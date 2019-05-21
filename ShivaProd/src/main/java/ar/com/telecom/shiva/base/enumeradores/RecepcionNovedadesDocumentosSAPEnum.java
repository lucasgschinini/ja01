package ar.com.telecom.shiva.base.enumeradores;

public enum RecepcionNovedadesDocumentosSAPEnum {
	
	ANULACION_DOC_K$("Anulación de documento K$");
	
	String descripcion;
	
	private RecepcionNovedadesDocumentosSAPEnum(String descripcion) {
		this.descripcion = descripcion;
	}

	
	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	
	

}
