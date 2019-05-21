package ar.com.telecom.shiva.base.enumeradores;

public enum MotivoCobroUsoEnum {
	
	COBRO("Cobro","1"),
	OPERACION_MASIVA("Operacion Masiva","2"),
	COBRO_SALIDA_AUTOMATICA_VALOR("Cobro Salida Automatica Valor","3");
	
	
	String descripcion;
	String codigo;
	
	private MotivoCobroUsoEnum(String descripcion, String codigo) {
		this.descripcion = descripcion;
		this.codigo = codigo;
	}
	
	public String descripcion() {
		return this.descripcion;
	}

	public String codigo() {
		return this.codigo;
	}
	
	
	public static MotivoCobroUsoEnum getEnumByName(String name) {
		for (MotivoCobroUsoEnum enumerador : MotivoCobroUsoEnum.values()) {
			if (enumerador.name().equals(name)) {
				return enumerador;
			}
		}
		return null;
	}

	public static MotivoCobroUsoEnum getEnumByDescripcion(String descripcion) {
		for (MotivoCobroUsoEnum enumerador : MotivoCobroUsoEnum.values()) {
			if (enumerador.descripcion().equals(descripcion)) {
				return enumerador;
			}
		}
		return null;
	}

	public static MotivoCobroUsoEnum getEnumByIdMotivo(String codigo) {
		for (MotivoCobroUsoEnum enumerador : MotivoCobroUsoEnum.values()) {
			if (enumerador.codigo.equals(codigo)) {
				return enumerador;
			}
		}
		return null;
	}

}
