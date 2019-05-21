package ar.com.telecom.shiva.base.enumeradores;

public enum TipoMensajeEstadoEnum {

	OK("Ok"),
	WRN("Warning"),
	ERR("Error");
	
	String descripcion;
	
	/**
	 * 
	 * @param descripcion
	 */
	private TipoMensajeEstadoEnum(String descripcion) {
		this.descripcion = descripcion;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getDescripcion() {
		return this.descripcion;
	}
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public static TipoMensajeEstadoEnum getEnumByName(String name) {
		for (TipoMensajeEstadoEnum tipoOrigen : TipoMensajeEstadoEnum.values()) {
			if (tipoOrigen.name().equalsIgnoreCase(name)) {
				return tipoOrigen;
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @param descripcion
	 * @return
	 */
	public static TipoMensajeEstadoEnum getEnumByDescripcion(String descripcion) {
		for (TipoMensajeEstadoEnum tipoOrigen : TipoMensajeEstadoEnum.values()) {
			if (tipoOrigen.getDescripcion().equalsIgnoreCase(descripcion)) {
				return tipoOrigen;
			}
		}
		return null;
	}
}
