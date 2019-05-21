package ar.com.telecom.shiva.base.enumeradores;

public enum TipoImputacionEnum {

	AUTOMATICA("Automática","Autom"),
	MANUAL("Manual","Man");
	
	String descripcion;
    String apocope;
    
    
	private TipoImputacionEnum(String descripcion, String apocope) {
		this.descripcion = descripcion;
		this.apocope = apocope;
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


	/**
	 * @return the apocope
	 */
	public String getApocope() {
		return apocope;
	}


	/**
	 * @param apocope the apocope to set
	 */
	public void setApocope(String apocope) {
		this.apocope = apocope;
	}
    
	public static TipoImputacionEnum getEnumByName(String name) {
		for (TipoImputacionEnum tipoAplicacion : TipoImputacionEnum.values()) {
			if (tipoAplicacion.name().equalsIgnoreCase(name)) {
				return tipoAplicacion;
			}
		}
		return null;
	}
	public static TipoImputacionEnum getEnumByApocope(String apocope) {
		for (TipoImputacionEnum tipoAplicacion : TipoImputacionEnum.values()) {
			if (tipoAplicacion.getApocope().equalsIgnoreCase(apocope)) {
				return tipoAplicacion;
			}
		}
		return null;
	}
}
