package ar.com.telecom.shiva.base.enumeradores;

public enum EstadoTramiteDeimosEnum {

	NA("NA","No Asignado"),
	EGSA("EGSA","En Gestion Sin Acuerdo"),
	EGCA("EGCA","En Gestion Con Acuerdo"),
	CO("CO","Cobrado"),
	IC("IC","Incobrable"),
	CC("CC","Convenio Caido"),
	NED("NED", "No Existe Documento");
	
	String codigo;
	String descripcion;

	private EstadoTramiteDeimosEnum(String codigo, String descripcion) {
		this.codigo = codigo;
		this.descripcion = descripcion;
	}

	public String codigo(){
		return this.codigo;
	}

	public String descripcion() {
		return this.descripcion;
	}

	public static EstadoTramiteDeimosEnum getEnumtByName(String name) {
		for (EstadoTramiteDeimosEnum enumerador : EstadoTramiteDeimosEnum.values()) {
			if (enumerador.name().equals(name)) {
				return enumerador;
			}
		}
		return null;
	}

	public static EstadoTramiteDeimosEnum getEnumtByCodigo(String codigo) {
		for (EstadoTramiteDeimosEnum enumerador : EstadoTramiteDeimosEnum.values()) {
			if (enumerador.codigo().equals(codigo)) {
				return enumerador;
			}
		}
		return null;
	}

	public static EstadoTramiteDeimosEnum getEnumtByDescripcion(String descripcion) {
		for (EstadoTramiteDeimosEnum enumerador : EstadoTramiteDeimosEnum.values()) {
			if (enumerador.descripcion().equals(descripcion)) {
				return enumerador;
			}
		}
		return null;
	}
}
