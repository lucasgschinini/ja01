package ar.com.telecom.shiva.base.enumeradores;

public enum ClienteOrigenEnum {
	
	DELTA("DELTA","DELTA"),
	USUARIO("USUARIO","USUARIO"),
	INTERNO ("INT", "INTERNO"),
	SBL ("SBL", "SIEBEL"),
	FAN ("FAN", "FAN");
	
	private String codigo = "";
	private String descripcion = "";

	
	private ClienteOrigenEnum(String codigo, String descripcion) {
		this.descripcion = descripcion;
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public String getCodigo() {
		return codigo;
	}

	public static ClienteOrigenEnum getEnumByName(String name) {
		for (ClienteOrigenEnum enumerador : ClienteOrigenEnum.values()) {
			if (enumerador.name().equalsIgnoreCase(name)) {
				return enumerador;
			}
		}
		return null; 
	}
	
	public static ClienteOrigenEnum getEnumByCodigo(String codigo) {
		for (ClienteOrigenEnum enumerador : ClienteOrigenEnum.values()) {
			if (enumerador.getCodigo().equalsIgnoreCase(codigo)) {
				return enumerador;
			}
		}
		return null; 
	}

	
}
