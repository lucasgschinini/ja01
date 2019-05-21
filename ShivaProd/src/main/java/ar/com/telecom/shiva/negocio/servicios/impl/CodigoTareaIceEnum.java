package ar.com.telecom.shiva.negocio.servicios.impl;


public enum CodigoTareaIceEnum {
	
	COBRANZA_INTEGRADA("23","Cobranza Integrada");
	
	String codigo;
	String descripcion;
	
	private CodigoTareaIceEnum(String codigo, String descripcion) {
		
		this.codigo = codigo;
		this.descripcion = descripcion;
	}
	
	public String descripcion() {
		return this.descripcion;
	}
	
	public String codigo() {
		return this.codigo;
	}


	public static CodigoTareaIceEnum getEnumByName(String name) {
		for (CodigoTareaIceEnum enumerador : CodigoTareaIceEnum.values()) {
			if (enumerador.name().equalsIgnoreCase(name)) {
				return enumerador;
			}
		}
		return null;
	}

	public static CodigoTareaIceEnum getEnumByCodigo(String codigo) {
		for (CodigoTareaIceEnum enumerador : CodigoTareaIceEnum.values()) {
			if (enumerador.codigo().equals(codigo)) {
				return enumerador;
			}
		}
		return null;
	}
	
	public static boolean esCobranzaIntegrada(String codigo) {

		if(COBRANZA_INTEGRADA.codigo().equalsIgnoreCase(codigo)) {
			return true;
		}
		
		return false;
	}
	
	

}
