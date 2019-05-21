package ar.com.telecom.shiva.base.enumeradores;

public enum MotivoSuspensionEnum {
    /**
     */
	CAMBIO_CLIENTE_SOL(1, "Cambio de cliente solicitado"),
	CHEQUE_RECHAZADO(2, "Cheque rechazado"),
	OTROS(4, "Otros");

	Integer codigo;
	String descripcion;
	
	private MotivoSuspensionEnum(Integer codigo, String descripcion) {
		this.codigo = codigo;
		this.descripcion = descripcion;
	}
	
	public Integer codigo(){
		return this.codigo;
	}
	
	public String descripcion() {
	    return this.descripcion;
	}
	
	/**
	 * Devuelve el enumerador segun el nombre
	 * @param name
	 * @return
	 */
	public static MotivoSuspensionEnum getEnumByName(String name){
		
		for (MotivoSuspensionEnum enumAbuscar : MotivoSuspensionEnum.values()) {
			if (enumAbuscar.name().equals(name)) {
				return enumAbuscar;
			}
		}
		return null; 
	}
	
	/**
	 * Devuelve el enumerador segun el nombre
	 * @param name
	 * @return
	 */
	public static MotivoSuspensionEnum getEnumByCodigo(Integer name){
		
		for (MotivoSuspensionEnum enumAbuscar : MotivoSuspensionEnum.values()) {
			if (enumAbuscar.codigo().equals(name)) {
				return enumAbuscar;
			}
		}
		return null; 
	}
	
	/**
	 * Devuelve el enumerador segun el nombre
	 * @param name
	 * @return
	 */
	public static MotivoSuspensionEnum getEnumByCodigo(String name){
		
		for (MotivoSuspensionEnum enumAbuscar : MotivoSuspensionEnum.values()) {
			if (enumAbuscar.codigo().toString().equals(name)) {
				return enumAbuscar;
			}
		}
		return null; 
	}	
	
}
