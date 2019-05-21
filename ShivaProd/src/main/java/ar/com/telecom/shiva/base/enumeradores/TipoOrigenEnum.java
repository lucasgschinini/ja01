package ar.com.telecom.shiva.base.enumeradores;

public enum TipoOrigenEnum {

	RT1("RT1", 1),
	RT2("RT2", 2),
	RT3("RT3", 3);
	
	String descripcion;
	long codigo;
	
	private TipoOrigenEnum(String descripcion, long codigo) {
		this.descripcion = descripcion;
		this.codigo = codigo;
	}
	
	public String getDescripcion() {
		return this.descripcion;
	}
	public long getCodigo() {
		return this.codigo;
	}
	
	public static TipoOrigenEnum getEnumByName(String name) {
		for (TipoOrigenEnum tipoOrigen : TipoOrigenEnum.values()) {
			if (tipoOrigen.name().equalsIgnoreCase(name)) {
				return tipoOrigen;
			}
		}
		return null;
	}
	
	public static TipoOrigenEnum getEnumByDescripcion(String descripcion) {
		for (TipoOrigenEnum tipoOrigen : TipoOrigenEnum.values()) {
			if (tipoOrigen.getDescripcion().equalsIgnoreCase(descripcion)) {
				return tipoOrigen;
			}
		}
		return null;
	}
	
	public static TipoOrigenEnum getEnumByName1y2(String name) {
		for (TipoOrigenEnum tipoOrigen : TipoOrigenEnum.values()) {
			if (RT1.name().equalsIgnoreCase(name) ) {
				return tipoOrigen;
			}
			if (RT2.name().equalsIgnoreCase(name) ) {
				return tipoOrigen;
			}
		}
		return null;
	}
	
}
