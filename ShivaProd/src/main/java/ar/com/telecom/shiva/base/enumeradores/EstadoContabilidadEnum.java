package ar.com.telecom.shiva.base.enumeradores;

/**
 * 
 * @author u564030
 *
 */
public enum EstadoContabilidadEnum {
	
	PROCESADO_CON_ERROR("PROC. ERROR"), 		// Utilizado cuando hay un error en un registro puntual
	PROCESADO("PROCESADO"),
	EN_PROCESO("EN PROCESO"),
	PENDIENTE("PENDIENTE"),
	PENDIENTE_SIN_ACREDITACION("P. NO ACRED."),
	PENDIENTE_ACREDITACION("PEND. ACRED.");

	String codigo;
	
	private EstadoContabilidadEnum(String codigo) {
		this.codigo = codigo;
	}
	
	public String codigo(){
		return this.codigo;
	}
	
	/**
	 * Devuelve el enumerador segun el nombre
	 * @param name
	 * @return
	 */
	public static EstadoContabilidadEnum getEnumByName(String name){
		
		for (EstadoContabilidadEnum enumAbuscar : EstadoContabilidadEnum.values()) {
			if (enumAbuscar.name().equals(name)) {
				return enumAbuscar;
			}
		}
		return null; 
	}	
}
