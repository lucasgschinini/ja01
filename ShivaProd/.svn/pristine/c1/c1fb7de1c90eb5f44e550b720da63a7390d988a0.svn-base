package ar.com.telecom.shiva.base.enumeradores;


/**
 * @author u578936 M.A.Uehara
 *
 */
public enum TipoCobroEnum {
	SHIVA("Shiva"),
	MIXTO("Mixto"),
	APLICACION_MANUAL("Externo");

	String descripcion;

	private TipoCobroEnum(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getDescripcion() {
		return this.descripcion;
	}

	public static TipoCobroEnum getEnumByDescripcion(String descripcion){
		for (TipoCobroEnum item : TipoCobroEnum.values()) {
			if (item.getDescripcion().equals(descripcion)) {
				return item;
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public static TipoCobroEnum getEnumByName(String name){
		for (TipoCobroEnum item : TipoCobroEnum.values()) {
			if (item.name().equals(name)) {
				return item;
			}
		}
		return null;
	}

}
