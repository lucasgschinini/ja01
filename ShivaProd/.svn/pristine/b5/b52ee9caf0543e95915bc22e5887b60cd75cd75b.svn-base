package ar.com.telecom.shiva.base.enumeradores;


public enum CriterioBusquedaClienteEnum {
    
	BUSQUEDA_POR_CLIENTE_LEGADO("BUSQUEDA_POR_CLIENTE_LEGADO", "Cliente Legado"),
	BUSQUEDA_POR_AGENCIA_NEGOCIO("BUSQUEDA_POR_AGENCIA_NEGOCIO", "Agencia de Negocio"),
	BUSQUEDA_POR_HOLDING("BUSQUEDA_POR_HOLDING", "Holding"),
	BUSQUEDA_POR_CUIT("BUSQUEDA_POR_CUIT", "CUIT");
	
    String nombre;
    String descripcion;
    
	private CriterioBusquedaClienteEnum(String nombre, String descripcion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
	}
	
	public String getDescripcion() {
	    return this.descripcion;
	}
	
	public String getNombre() {
	    return this.nombre;
	}
	
	public static CriterioBusquedaClienteEnum getEnumByName(String name) {
		for (CriterioBusquedaClienteEnum criterioBusquedaClienteEnum : CriterioBusquedaClienteEnum.values()) {
			if (criterioBusquedaClienteEnum.name().equalsIgnoreCase(name)) {
				return criterioBusquedaClienteEnum;
			}
		}
		return null;
	}
	
	public static CriterioBusquedaClienteEnum getEnumByNombre(String nombre) {
		for (CriterioBusquedaClienteEnum criterioBusquedaClienteEnum : CriterioBusquedaClienteEnum.values()) {
			if (criterioBusquedaClienteEnum.getNombre().equalsIgnoreCase(nombre)) {
				return criterioBusquedaClienteEnum;
			}
		}
		return null;
	}
	
	public static CriterioBusquedaClienteEnum getEnumByString(String moneda){
		for (CriterioBusquedaClienteEnum criterioBusquedaClienteEnum : CriterioBusquedaClienteEnum.values()) {
            if(criterioBusquedaClienteEnum.name().equalsIgnoreCase(moneda))
                return criterioBusquedaClienteEnum;
            if(criterioBusquedaClienteEnum.getDescripcion().equalsIgnoreCase(moneda))
                return criterioBusquedaClienteEnum;
        }
        return null;
    }

}
