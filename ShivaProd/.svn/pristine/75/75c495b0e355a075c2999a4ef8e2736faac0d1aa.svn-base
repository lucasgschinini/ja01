package ar.com.telecom.shiva.base.enumeradores;

public enum TipoAcuerdoEnum {

	ACUERDO_28("28"),
	ACUERDO_29("29"),
	ACUERDO_33("33"),
	ACUERDO_34("34"),
	TRANSFERENCIA("125"),
	INTERDEPOSITO_6("6"),
	INTERDEPOSITO_87("87"),
	INTERDEPOSITO_94("94");

	String descripcion;
	
	private TipoAcuerdoEnum(String descripcion) {
	    this.descripcion = descripcion;
	}
	
	public String descripcion() {
	    return this.descripcion;
	}
	
	public static TipoAcuerdoEnum getEnumByDescripcionTipoAcuerdo(String tipoAcuerdoDesc) {
		for (TipoAcuerdoEnum tipoAcuerdo : TipoAcuerdoEnum.values()) {
			if (tipoAcuerdo.descripcion.equalsIgnoreCase(tipoAcuerdoDesc)){
				return tipoAcuerdo;
			}
		}
		return null; 
	}
	
	public static TipoAcuerdoEnum getEnumByName(String name) {
		
		for (TipoAcuerdoEnum tipoDoc : TipoAcuerdoEnum.values()) {
			if (tipoDoc.name().equalsIgnoreCase(name)) {
				return tipoDoc;
			}
		}
		        
		return null; 
	}
}
