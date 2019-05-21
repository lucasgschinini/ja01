package ar.com.telecom.shiva.base.enumeradores;

/**
 * @author fabio.giaquinta.ruiz
 * 23/09/2014
 */
public enum OrigenDocumentoEnum {

	DC("Diferencia de Cambio"),
	CDC("Contradocumento Diferencia de Cambio");
	
	String descripcion;
	
	private OrigenDocumentoEnum(String descripcion) {
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
	public static OrigenDocumentoEnum getEnumByName(String name){
		
		for (OrigenDocumentoEnum enumAbuscar : OrigenDocumentoEnum.values()) {
			if (enumAbuscar.name().equals(name)) {
				return enumAbuscar;
			}
		}
		return null; 
	}	
}
