package ar.com.telecom.shiva.base.enumeradores;

public enum TipoTareaEstadoEnum {
	/**
    15 caracteres 
    123456789012345  
     */
	PENDIENTE("Pendiente"),
	FINALIZADA("Finalizada");
	
	String descripcion;
	
	private TipoTareaEstadoEnum(String descripcion) {
	    this.descripcion = descripcion;
	}
	
	public String descripcion() {
	    return this.descripcion;
	}
	
	/**
	 * Devuelve el enumerador segun el nombre
	 * @param name
	 * @return
	 */
	public static TipoTareaEstadoEnum getEnumByName(String name){
		
		for (TipoTareaEstadoEnum item : TipoTareaEstadoEnum.values()) {
			if (item.name().equals(name)) {
				return item;
			}
		}
		return null; 
	}
}
