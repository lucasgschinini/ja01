package ar.com.telecom.shiva.base.enumeradores;

public enum TipoCotizacionSapEnum {
	B("Conversión normal según T/C vendedor"),
	G("Conversión normal según T/C comprador"),
	I("Tipo de cotización IntraStat"),
	M("Conversión normal según T/C estándar"),
	P("Conversión estándar para planif.costes");
	
	String descripcion;
	
	TipoCotizacionSapEnum(String descripcion){
		this.descripcion = descripcion;
	}
	
	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public static TipoCotizacionSapEnum getEnumByName(String name) {
		for (TipoCotizacionSapEnum enumerador : TipoCotizacionSapEnum.values()) {
			if (enumerador.name().equalsIgnoreCase(name)) {
				return enumerador;
			}
		}
		return null; 
	}
}
