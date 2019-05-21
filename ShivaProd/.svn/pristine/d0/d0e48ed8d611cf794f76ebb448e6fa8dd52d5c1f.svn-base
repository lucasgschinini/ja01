package ar.com.telecom.shiva.base.registros.util.definicion;


public enum TipoDatoEnum {
	AGRUPADOR,
	NUMERICO,
	ALFANUMERICO;
	
	public static TipoDatoEnum getEnumByName(String name) {
		
		for (TipoDatoEnum campo : TipoDatoEnum.values()) {
			if (campo.name().equalsIgnoreCase(name)) {
				return campo;
			}
		}
		        
		return null; 
	}
	
	
}
