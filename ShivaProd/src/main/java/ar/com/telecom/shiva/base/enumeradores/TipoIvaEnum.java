package ar.com.telecom.shiva.base.enumeradores;

public enum TipoIvaEnum {
	CF,
	NC,
	NR,
	EX,
	RI,
	IC,
	RN,
	RM,
	CN,
	RS;

	
	private TipoIvaEnum() {}
	
	
	public static TipoIvaEnum getEnumByName(String name) {
		
		for (TipoIvaEnum tipoIva : TipoIvaEnum.values()) {
			if (tipoIva.name().equals(name)) {
				return tipoIva;
			}
		}
		        
		return null; 
	}

}
