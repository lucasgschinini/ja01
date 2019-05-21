package ar.com.telecom.shiva.base.enumeradores;

public enum TipoClienteEnum {

	GOBIERNO("01","Gobierno"),
	NO_GOBIERNO("02","No es gobierno");

	String codigo;
	String descripcion;
	
	private TipoClienteEnum(String codigo, String descripcion) {
	    this.descripcion = descripcion;
	    this.codigo = codigo;
	}
	
	public String descripcion() {
	    return this.descripcion;
	}
	public String codigo() {
	    return this.codigo;
	}
	
	public static TipoClienteEnum getEnumByName(String name) {
		
		for (TipoClienteEnum tipoDoc : TipoClienteEnum.values()) {
			if (tipoDoc.name().equalsIgnoreCase(name)) {
				return tipoDoc;
			}
		}
		        
		return null; 
	}
	
	public static TipoClienteEnum getEnumByCodigo(String codigo) {
		
		for (TipoClienteEnum tipoAcuerdo : TipoClienteEnum.values()) {
			if (tipoAcuerdo.codigo.equalsIgnoreCase(codigo)){
				return tipoAcuerdo;
			}
		}
		return null; 
	}
	
	public static TipoClienteEnum getEnumByDescripcion(String descripcion) {
		
		for (TipoClienteEnum tipoAcuerdo : TipoClienteEnum.values()) {
			if (tipoAcuerdo.descripcion.equalsIgnoreCase(descripcion)){
				return tipoAcuerdo;
			}
		}
		return null; 
	}
}
