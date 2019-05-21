package ar.com.telecom.shiva.base.enumeradores;

public enum EstadoAcuerdoFacturacionEnum {

	POTENCIAL("00","Potencial","0"),
	ACTIVO("01","Activo","1"),
	INCOMUNICADO("02","Incomunicado","2"),
	BAJA_DEFINITIVA("06","Baja Definitiva","6"),
	FACTURABLE("", "Facturable", ""),
	NO_FACTURABLE("", "No Facturable", "");

	String codigo;
	String descripcion;
	String codigoSinCero;
	
	private EstadoAcuerdoFacturacionEnum(String codigo, String descripcion, String codigoSinCero) {
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.codigoSinCero = codigoSinCero;
	}
	
	public String codigo(){
		return this.codigo;
	}
	
	public String descripcion() {
	    return this.descripcion;
	}
	
	public String codigoSinCero() {
	    return this.codigoSinCero;
	}
	
	public static EstadoAcuerdoFacturacionEnum getEnumByName(String name) {
		
		for (EstadoAcuerdoFacturacionEnum estadoAcuerdo : EstadoAcuerdoFacturacionEnum.values()) {
			if (estadoAcuerdo.name().equalsIgnoreCase(name)) {
				return estadoAcuerdo;
			}
		}
		        
		return null; 
	}
	
	
	public static EstadoAcuerdoFacturacionEnum getEnumByCodigo(String codigo) {
		
		for (EstadoAcuerdoFacturacionEnum estadoAcuerdo : EstadoAcuerdoFacturacionEnum.values()) {
			if (estadoAcuerdo.codigo().equalsIgnoreCase(codigo)) {
				return estadoAcuerdo;
			}
		}
		        
		return null; 
	}
	
	public static EstadoAcuerdoFacturacionEnum getEnumByCodigoSinCero(String codigoSinCero) {
		
		for (EstadoAcuerdoFacturacionEnum estadoAcuerdo : EstadoAcuerdoFacturacionEnum.values()) {
			if (estadoAcuerdo.codigoSinCero().equalsIgnoreCase(codigoSinCero)) {
				return estadoAcuerdo;
			}
		}
		        
		return null; 
	}
	
	public static EstadoAcuerdoFacturacionEnum getEnumByDescripcion(String descripcion) {
		
		for (EstadoAcuerdoFacturacionEnum estadoAcuerdo : EstadoAcuerdoFacturacionEnum.values()) {
			if (estadoAcuerdo.descripcion().equals(descripcion)) {
				return estadoAcuerdo;
			}
		}
		        
		return null; 
	}
}
