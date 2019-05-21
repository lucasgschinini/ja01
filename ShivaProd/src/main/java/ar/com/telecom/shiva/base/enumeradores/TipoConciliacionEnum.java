package ar.com.telecom.shiva.base.enumeradores;


public enum TipoConciliacionEnum {
	
	TOTAL("TOTAL"),
	SUGERIDA("SUGERIDA");

	String descripcion;
	
	private TipoConciliacionEnum(String descripcion) {
	    this.descripcion = descripcion;
	}
	
	public String getDescripcion() {
	    return this.descripcion;
	}
	
	public static TipoConciliacionEnum getEnumByDescripcionTipoConciliacion(String tipoConciliacionDescripion) {
		
		for (TipoConciliacionEnum tipoConciliacion : TipoConciliacionEnum.values()) {
			if (tipoConciliacion.getDescripcion().equalsIgnoreCase(tipoConciliacionDescripion)) {
				return tipoConciliacion;
			}
		}
		        
		return null; 
	}
}
