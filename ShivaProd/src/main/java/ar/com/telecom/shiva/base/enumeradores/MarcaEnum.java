package ar.com.telecom.shiva.base.enumeradores;


public enum MarcaEnum {
	
	// Marca Resultado de Simulacion
	
	SIMULACION_BATCH_FINALIZADA_CON_EXITO("Simulación batch finalizada con exito"),
	SIMULACION_ONLINE_FINALIZADA_CON_EXITO("Simulación online finalizada con exito"),
	SIMULACION_BATCH_EN_PROCESO("Simulacion batch en proceso"),
	SIMULACION_BATCH_FINALIZADA_CON_ERROR("Simulación batch finalizada con error"),
	SIMULACION_ONLINE_FINALIZADA_CON_ERROR("Simulación online finalizada con error"),
	SIMULACION_VACIA(""),
	// Marca Motivos de Rechazos

	RECHAZADO_POR_SUPERVISOR("Rechazado por Supervisor"),
	RECHAZADO_POR_SUPERVISOR_OP_ESPECIALES("Rechazado por Supervisor Operaciones Especiales"),
	RECHAZADO_POR_REFERENTE("Rechazado por Referente"),
	RECHAZADO_POR_REFERENTE_CAJA("Rechazado por Referente de Caja"),
	RECHAZADO_POR_REFERENTE_OPERACIONES_EXTERNAS("Rechazado por Referente de Operaciones Externas"),
	RECHAZADO_POR_TIEMPO_BOLETA_PEND_CONCILIAR("Rechazado por tiempo por boleta pendiente de conciliación"), 
	RECHAZADO_POR_TIEMPO_NOTA_CRED_PEND_EMITIR("Rechazado por tiempo por NC pendiente de emitir"), 
	CONFIRMADO_POR_REFERENTE_OPERACIONES_EXTERNAS("Confirmado por Referente de Operaciones Externas"), 
	CONFIRMADO_POR_REFERENTE_CAJA("Confirmado por Referente de Caja");

	// Marca simulacion en proceso
	

	String descripcion;
	
	/**
	 * 
	 * @param descripcion
	 */
	private MarcaEnum(String descripcion) {
	    this.descripcion = descripcion;
	}
	
	/**
	 * 
	 * @return
	 */
	public String descripcion() {
	    return this.descripcion;
	}

	/**
	 * 
	 * @param name
	 * @return
	 */
	public static MarcaEnum getEnumByName(String name) {
		
		for (MarcaEnum marca : MarcaEnum.values()) {
			if (marca.name().equals(name)) {
				return marca;
			}
		}
		        
		return null; 
	}

	/**
	 * 
	 * @param descripcion
	 * @return
	 */
	public static MarcaEnum getEnumByDescripcion(String descripcion) {
		
		for (MarcaEnum marca : MarcaEnum.values()) {
			if (marca.descripcion().equals(descripcion)) {
				return marca;
			}
		}
		        
		return null; 
	}
}
