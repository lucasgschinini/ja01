package ar.com.telecom.shiva.base.enumeradores;


public enum TipoRegistroEnum {
	
	C("Cabecera", "00"),
	P("Pie", "02"),
	D("Detalle", "01");
	
	String descripcion;
	String codigo;
	
	private TipoRegistroEnum(String descripcion, String codigo) {
		this.descripcion = descripcion;
		this.codigo = codigo;
	}

	public String descripcion() {
		return this.descripcion;
	}
	public String codigo() {
		return this.codigo;
	}
		
	public static TipoRegistroEnum getEnumByName(String name){
		for (TipoRegistroEnum enumerador : TipoRegistroEnum.values()) {
			if (enumerador.name().equals(name)) {
				return enumerador;
			}
		}
		return null;
	}
	public static TipoRegistroEnum getEnumByCodigo(String codigo){
		for (TipoRegistroEnum enumerador : TipoRegistroEnum.values()) {
			if (enumerador.codigo().equals(codigo)) {
				return enumerador;
			}
		}
		return null;
	}
}
