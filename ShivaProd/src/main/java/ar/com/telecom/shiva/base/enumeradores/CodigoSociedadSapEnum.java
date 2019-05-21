package ar.com.telecom.shiva.base.enumeradores;

public enum CodigoSociedadSapEnum {
	M650("M650", "Telecom Argentina"),
	_3200("3200", "CV"),
	M660("M660", "TP");

	private String codigo = "";
	private String descripcion = "";

	
	private CodigoSociedadSapEnum(String codigo, String descripcion) {
		this.descripcion = descripcion;
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public String getCodigo() {
		return codigo;
	}

	public static CodigoSociedadSapEnum getEnumByName(String name) {
		for (CodigoSociedadSapEnum enumerador : CodigoSociedadSapEnum.values()) {
			if (enumerador.name().equalsIgnoreCase(name)) {
				return enumerador;
			}
		}
		return null; 
	}
}
