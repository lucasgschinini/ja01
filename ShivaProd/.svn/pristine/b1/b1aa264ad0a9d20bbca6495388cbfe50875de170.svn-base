package ar.com.telecom.shiva.base.jms.util.definicion;


public enum TipoDatoEnum {
	AGRUPADOR,
	NUMERICO,
	ALFANUMERICO,
	//SpringBatch
	FECHA,
	NUMERO,
	TEXTO,
	DECIMAL;
	
	public static TipoDatoEnum getEnumByName(String name) {
		
		for (TipoDatoEnum campo : TipoDatoEnum.values()) {
			if (campo.name().equalsIgnoreCase(name)) {
				return campo;
			}
		}
		        
		return null; 
	}
	
	
}
