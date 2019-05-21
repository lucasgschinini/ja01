package ar.com.telecom.shiva.base.enumeradores;

/**
 * Este enum se utiliza para indicar si el debito fue cargado por el usuario
 * de manera online (grilla de busqueda de debitos) o mediante la importacion de un archivo
 * 
 * @author u564030
 */
public enum OrigenDebitoEnum {
	
	ONLINE("Online"),
	IMPORTACION("Importación");

	String descripcion;
	
	/**
	 * 
	 * @param descripcion
	 */
	private OrigenDebitoEnum(String descripcion) {
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
	 * @param descripcion
	 * @return
	 */
	public static OrigenDebitoEnum getEnumByDescripcion(String descripcion){
		
		for (OrigenDebitoEnum item : OrigenDebitoEnum.values()) {
			if (item.getDescripcion().equals(descripcion)) {
				return item;
			}
		}
		return null;
	}	

	/**
	 * 
	 * @param name
	 * @return
	 */
	public static OrigenDebitoEnum getEnumByName(String name){
		for (OrigenDebitoEnum item : OrigenDebitoEnum.values()) {
			if (item.name().equals(name)) {
				return item;
			}
		}
		return null;
	}
}
