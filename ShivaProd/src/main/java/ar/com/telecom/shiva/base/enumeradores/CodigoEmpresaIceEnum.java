package ar.com.telecom.shiva.base.enumeradores;

public enum CodigoEmpresaIceEnum {
	$0004("0004", "Telecom Argentina"),
	$0350("0350", "Personal Argentina");

	private String codigo = "";
	private String descripcion = "";

	
	private CodigoEmpresaIceEnum(String codigo, String descripcion) {
		this.descripcion = descripcion;
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public String getCodigo() {
		return codigo;
	}

	public static CodigoEmpresaIceEnum getEnumByName(String name) {
		for (CodigoEmpresaIceEnum enumerador : CodigoEmpresaIceEnum.values()) {
			if (enumerador.name().equalsIgnoreCase(name)) {
				return enumerador;
			}
		}
		return null; 
	}


}
