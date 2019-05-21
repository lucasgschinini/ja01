package ar.com.telecom.shiva.base.enumeradores;

public enum EstadoChequeRechazadoDetalleCobroEnum {

	
	COBRADO("Cobrado"),
	PEND_ENVIAR_ICE("Pendiente de enviar a ICE"),
	ENVIADO_ICE("Enviado a ICE"),
	REVERTIDO("Revertido");

	String descripcion;
	
	private EstadoChequeRechazadoDetalleCobroEnum(String descripcion) {
		this.descripcion = descripcion; 
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	public static EstadoChequeRechazadoDetalleCobroEnum getEnumByDescripcion(String descripcion) {
		
		for (EstadoChequeRechazadoDetalleCobroEnum estadoCheque : EstadoChequeRechazadoDetalleCobroEnum.values()) {
			if (estadoCheque.getDescripcion().equals(descripcion)) {
				return estadoCheque;
			}
		}
		        
		return null; 
	}
	
	public static EstadoChequeRechazadoDetalleCobroEnum getEnumByName(String name) {
		
		for (EstadoChequeRechazadoDetalleCobroEnum estadoCheque : EstadoChequeRechazadoDetalleCobroEnum.values()) {
			if (estadoCheque.name().equalsIgnoreCase(name)) {
				return estadoCheque;
			}
		}
		        
		return null; 
	}
}
