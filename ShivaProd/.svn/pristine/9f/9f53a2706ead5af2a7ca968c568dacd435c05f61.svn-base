package ar.com.telecom.shiva.base.enumeradores;

public enum EstadoCargoGeneradoEnum {

	PENDIENTE_FACTURAR("01","PENDIENTE DE FACTURAR","Pendiente de Facturar", SiNoEnum.NO),
	FACTURADO("02","FACTURADO", "Facturado", SiNoEnum.SI);

	String codigo;
	String nameConEspacios;
	String descripcion;
	SiNoEnum facturado;
	
	private EstadoCargoGeneradoEnum(String codigo, String nameConEspacios, String descripcion, SiNoEnum facturado) {
		this.codigo = codigo;
		this.nameConEspacios = nameConEspacios;
		this.descripcion = descripcion;
		this.facturado = facturado;
	}
	
	public String codigo(){
		return this.codigo;
	}
	
	public String nameConEspacios(){
		return this.nameConEspacios;
	}
	
	public String descripcion() {
	    return this.descripcion;
	}
	
	public SiNoEnum facturado() {
	    return this.facturado;
	}
	
	public static EstadoCargoGeneradoEnum getEnumByName(String name) {
		
		for (EstadoCargoGeneradoEnum estadoAcuerdo : EstadoCargoGeneradoEnum.values()) {
			if (estadoAcuerdo.name().equalsIgnoreCase(name)) {
				return estadoAcuerdo;
			}
		}
		        
		return null; 
	}
	
	
	public static EstadoCargoGeneradoEnum getEnumByNameConEspacios(String nameConEspacios) {
		
		for (EstadoCargoGeneradoEnum estadoAcuerdo : EstadoCargoGeneradoEnum.values()) {
			if (estadoAcuerdo.nameConEspacios().equalsIgnoreCase(nameConEspacios)) {
				return estadoAcuerdo;
			}
		}
		        
		return null; 
	}
	
	public static EstadoCargoGeneradoEnum getEnumByCodigo(String codigo) {
		
		for (EstadoCargoGeneradoEnum estadoAcuerdo : EstadoCargoGeneradoEnum.values()) {
			if (estadoAcuerdo.codigo().equalsIgnoreCase(codigo)) {
				return estadoAcuerdo;
			}
		}
		        
		return null; 
	}
	
	public static EstadoCargoGeneradoEnum getEnumByFacturado(SiNoEnum facturado) {
		
		for (EstadoCargoGeneradoEnum estadoAcuerdo : EstadoCargoGeneradoEnum.values()) {
			if (estadoAcuerdo.facturado().equals(facturado)) {
				return estadoAcuerdo;
			}
		}
		        
		return null; 
	}
	
	public static EstadoCargoGeneradoEnum getEnumByDescripcion(String descripcion) {
		
		for (EstadoCargoGeneradoEnum estadoAcuerdo : EstadoCargoGeneradoEnum.values()) {
			if (estadoAcuerdo.descripcion().equals(descripcion)) {
				return estadoAcuerdo;
			}
		}
		        
		return null; 
	}
}
