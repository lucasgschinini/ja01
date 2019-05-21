package ar.com.telecom.shiva.base.enumeradores;

public enum MotivoAdjuntoEnum {
	
	APLICACION_MANUAL("Detalle de la Aplicación Manual"),
	OTROS_DEBITO("Adjunto Otros Debito"),
	APLI_MANUAL_CONF("Detalle de la Aplicación Manual Confirmación"),
	COMPROBANTE_COBRO(""),
	COMPROBANTE_DESCOBRO("");
	
	String descripcion;

	public String getDescripcion() {
		return descripcion;
	}

	private MotivoAdjuntoEnum(String descripcion) {
	    this.descripcion = descripcion;
	}
	
	public static MotivoAdjuntoEnum getEnumByName(String name) {
		
		for (MotivoAdjuntoEnum motivoAdjunto : MotivoAdjuntoEnum.values()) {
			if (motivoAdjunto.name().equalsIgnoreCase(name)) {
				return motivoAdjunto;
			}
		}
		        
		return null; 
	}
	
	

}
