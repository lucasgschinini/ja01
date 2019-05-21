package ar.com.telecom.shiva.base.enumeradores;

public enum EstadoFacturaMedioPagoEnum {
	
	PENDIENTE("Pendiente"),
	APROPIADA("Apropiada"),
	ERROR("Error"),
	CONFIRMADA("Confirmada"),
	DESAPROPIADA("Desapropiada"),
	EN_PROCESO_DESAPROPIACION("En Proceso de Desapropiacion"),
	EN_PROCESO_DESAPROPIACION_MANUAL("En Proceso Desapropiacion Manual"),
	
	// Debitos y creditos generados como aplicacion de documentos en Dolares 
	NUEVO_POR_DIFERENCIA_DE_CAMBIO("Nuevo por Diferencia de Cambio"),

	// Estados utilizados para el proceso de descobro
	PENDIENTE_DESCOBRO("Pendiente de descobro"),
	EN_PROCESO_DESCOBRO("En proceso de descobro"),
	EN_PROCESO_CONTRACARGO("En proceso de contracargo en descobro"),
	EN_PROCESO_CONTRACARGO_CONFIRMACION("En proceso de confirmación de contracargo"),
	DESCOBRO("Descobrado"),
	ERROR_DESCOBRO("Error en descobro"),
	ERROR_CONTRACARGO("Error en contracargo en descobro"),
	ERROR_CONTRACARGO_CONFIRMACION("Error en confirmación de contracargo"),
	//Estado para el nuevo CTA creado por la aplicacion de un CTA Parcial
	NUEVO("Nuevo"),

	NO_APLICA("No aplica");

	String descripcion;
	
	/**
	 * 
	 * @param descripcion
	 */
	private EstadoFacturaMedioPagoEnum(String descripcion) {
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
	public static EstadoFacturaMedioPagoEnum getEnumByName(String name) {
		for (EstadoFacturaMedioPagoEnum estado : EstadoFacturaMedioPagoEnum.values()) {
			if (estado.name().equalsIgnoreCase(name)) {
				return estado;
			}
		}
		return null;
	}	
}
