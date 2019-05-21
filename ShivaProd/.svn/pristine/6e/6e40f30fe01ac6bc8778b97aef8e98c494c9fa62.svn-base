package ar.com.telecom.shiva.base.enumeradores;

public enum MotivoDescobroEnum {
	
	CHEQUE_RECHAZADO("Cheque Rechazado","1"),
	VALOR_DUPLICADO("Valor Duplicado","2"),
	ERR_IMPORTE_APLICADO("Error En Importe Aplicado","3"),
	ERR_IMPUTACION("Error En Imputacion","4"),
	REVERSION_RELACIONADA("Reversion Relacionada","5");
	
	String descripcion;
	String codigo;
	
	private MotivoDescobroEnum(String descripcion, String codigo) {
		this.descripcion = descripcion;
		this.codigo = codigo;
	}
	
	public String descripcion() {
		return this.descripcion;
	}

	public String codigo() {
		return this.codigo;
	}
	
	
	public static MotivoDescobroEnum getEnumByName(String name) {
		for (MotivoDescobroEnum enumerador : MotivoDescobroEnum.values()) {
			if (enumerador.name().equals(name)) {
				return enumerador;
			}
		}
		return null;
	}

	public static MotivoDescobroEnum getEnumByDescripcion(String descripcion) {
		for (MotivoDescobroEnum enumerador : MotivoDescobroEnum.values()) {
			if (enumerador.descripcion().equals(descripcion)) {
				return enumerador;
			}
		}
		return null;
	}

	public static MotivoDescobroEnum getEnumByIdMotivo(String codigo) {
		for (MotivoDescobroEnum enumerador : MotivoDescobroEnum.values()) {
			if (enumerador.codigo.equals(codigo)) {
				return enumerador;
			}
		}
		return null;
	}
}
