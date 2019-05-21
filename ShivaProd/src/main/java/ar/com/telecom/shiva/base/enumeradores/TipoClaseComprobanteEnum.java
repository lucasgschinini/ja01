package ar.com.telecom.shiva.base.enumeradores;

public enum TipoClaseComprobanteEnum {
	A,
	B,
	C,
	E,
	D,
	M,
	S,
	X,
	U;
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public static TipoClaseComprobanteEnum getEnumByName(String name) {
		
		for (TipoClaseComprobanteEnum tipo : TipoClaseComprobanteEnum.values()) {
			if (tipo.name().equalsIgnoreCase(name)) {
				return tipo;
			}
		}
		        
		return null; 
	}
}
