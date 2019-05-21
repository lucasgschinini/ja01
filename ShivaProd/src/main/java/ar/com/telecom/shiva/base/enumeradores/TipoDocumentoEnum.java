package ar.com.telecom.shiva.base.enumeradores;

public enum TipoDocumentoEnum {

	FACTURA_NOTA_DEBITO("F","Factura/Nota de Debito"),  
	DUC("D","DUC"), 
	CONVENIO("C","Convenio"); 

	String codigo;
	String descripcion;

	private TipoDocumentoEnum(String codigo, String descripcion) {
		this.codigo = codigo;
		this.descripcion = descripcion;
	}

	public String codigo(){
		return this.codigo;
	}

	public String descripcion() {
		return this.descripcion;
	}

	public static TipoDocumentoEnum getEnumByName(String name) {
		for (TipoDocumentoEnum enumerador : TipoDocumentoEnum.values()) {
			if (enumerador.name().equalsIgnoreCase(name)) {
				return enumerador;
			}
		}
		return null;
	}

	public static TipoDocumentoEnum getEnumByCodigo(String codigo) {
		for (TipoDocumentoEnum enumerador : TipoDocumentoEnum.values()) {
			if (enumerador.codigo().equalsIgnoreCase(codigo)) {
				return enumerador;
			}
		}
		return null;
	}

	public static TipoDocumentoEnum getEnumByDescripcion(String descripcion) {
		for (TipoDocumentoEnum enumerador : TipoDocumentoEnum.values()) {
			if (enumerador.descripcion().equalsIgnoreCase(descripcion)) {
				return enumerador;
			}
		}
		return null;
	}
}