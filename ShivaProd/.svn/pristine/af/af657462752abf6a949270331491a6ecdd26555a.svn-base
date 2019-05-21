package ar.com.telecom.shiva.base.enumeradores;


public enum TipoPagoEnum {
	
	PAGO_TOTAL("Pago Total", "T"),
	PAGO_PARCIAL("Pago Parcial", "P"),
	PAGO_PARCIAL_CANCELATORIO("Pago Parcial Cancelatorio", "P"),
	NO_APLICA("No aplica", "N");
	
	String descripcion;
	String descripcionCorta;
	
	private TipoPagoEnum(String descripcion, String descripcionCorta) {
	    this.descripcion = descripcion;
	    this.descripcionCorta = descripcionCorta;
	}
	
	public String getDescripcionCorta() {
		return descripcionCorta;
	}

	public String descripcion() {
	    return this.descripcion;
	}
	
	/**
	 * 
	 * @param descripcion
	 * @return
	 */
	public static TipoPagoEnum getEnumByDescripcion(String descripcion) {
		for (TipoPagoEnum tipoCob : TipoPagoEnum.values()) {
			if (tipoCob.descripcion().equalsIgnoreCase(descripcion)) {
				return tipoCob;
			}
		}
		        
		return null; 
	}
	
	/**
	 * 
	 * @param descripcionCorta
	 * @return
	 */
	public static TipoPagoEnum getEnumByDescripcionCorta(String descripcionCorta) {
		for (TipoPagoEnum tipoPag : TipoPagoEnum.values()) {
			if (tipoPag.getDescripcionCorta().equalsIgnoreCase(descripcionCorta)) {
				return tipoPag;
			}
		}
		        
		return null; 
	}
	
	public static TipoPagoEnum getEnumByName(String name) {
        for (TipoPagoEnum tipoPag : TipoPagoEnum.values()) {
            if(tipoPag.name().equalsIgnoreCase(name)){
                return tipoPag;
            }
        }
        return null;
    }

}
