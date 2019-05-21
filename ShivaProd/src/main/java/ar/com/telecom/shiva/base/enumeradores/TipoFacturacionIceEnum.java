package ar.com.telecom.shiva.base.enumeradores;

public enum TipoFacturacionIceEnum {

	FACTURACION_GENESIS ("00","Facturación Génesis", "MIC"),
	FACTURACION_PERSONAL_TRAFICO ("16","Facturación Personal Tráfico" , "Negocio"),
	RAPIDUCS_GENESIS ("75","RAPIDUCs Génesis", "MIC"),
	DUCS_GENESIS ("76","DUCs Génesis", "MIC"),
	FACTURACION_PERSEUS ("77","Facturación Perseus", "Calipso"),
	MAYORISTAS_CALIPSO ("96","Mayoristas Calipso", "Calipso"),
	DATOS_CALIPSO ("97","Datos Calipso", "Calipso"),
	CUOTAS_DEIMOS ("98","Cuotas DEIMOs", "DEIMOs");

	String codigo;
	String descripcion;
	String sistema;

	private TipoFacturacionIceEnum(String codigo, String descripcion, String sistema) {
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.sistema = sistema;
	}

	public String sistema(){
		return this.sistema;
	}

	public String descripcion() {
		return this.descripcion;
	}
	
	public String codigo() {
		return this.codigo;
	}

	public static TipoFacturacionIceEnum getEnumByName(String name) {
		for (TipoFacturacionIceEnum enumerador : TipoFacturacionIceEnum.values()) {
			if (enumerador.name().equalsIgnoreCase(name)) {
				return enumerador;
			}
		}
		return null;
	}

	public static TipoFacturacionIceEnum getEnumByCodigo(String codigo) {
		for (TipoFacturacionIceEnum enumerador : TipoFacturacionIceEnum.values()) {
			if (enumerador.codigo().equals(codigo)) {
				return enumerador;
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @param codigo
	 * @return
	 */
	public static boolean esTipoFacturacionCalipso(String codigo) {

		TipoFacturacionIceEnum tipoFacturacion = TipoFacturacionIceEnum.getEnumByCodigo(codigo);
		
		if (TipoFacturacionIceEnum.FACTURACION_PERSEUS.equals(tipoFacturacion)
				|| TipoFacturacionIceEnum.MAYORISTAS_CALIPSO.equals(tipoFacturacion)
				|| TipoFacturacionIceEnum.DATOS_CALIPSO.equals(tipoFacturacion)) {
		
			return true;

		} else {
			return false;
		}
	}

	/**
	 * 
	 * @param codigo
	 * @return
	 */
	public static boolean esTipoFacturacionMic(String codigo) {
	
		TipoFacturacionIceEnum tipoFacturacion = TipoFacturacionIceEnum.getEnumByCodigo(codigo);
		
		if (TipoFacturacionIceEnum.FACTURACION_GENESIS.equals(tipoFacturacion)		//MIC
				|| TipoFacturacionIceEnum.RAPIDUCS_GENESIS.equals(tipoFacturacion)	//MIC
				|| TipoFacturacionIceEnum.DUCS_GENESIS.equals(tipoFacturacion)) {
		
			return true;

		} else {
			return false;
		}
	}
	

	/**
	 * 
	 * @param codigo
	 * @return
	 */
	public static boolean esTipoFacturacionDeimos(String codigo) {

		TipoFacturacionIceEnum tipoFacturacion = TipoFacturacionIceEnum.getEnumByCodigo(codigo);
		
		if (TipoFacturacionIceEnum.CUOTAS_DEIMOS.equals(tipoFacturacion)) {
			return true;

		} else {
			return false;
		}
	}
}