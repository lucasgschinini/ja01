package ar.com.telecom.shiva.base.enumeradores;

public enum EstadoDocSapEnum {
	BLANCO("BLANCO", "Doc. en firme"),
	V("V", "Doc. preliminar");

	private String descripcion;
	private String codigo;
	
	private EstadoDocSapEnum(String codigo, String descripcion) {
		this.descripcion = descripcion;
		this.codigo = codigo;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public static EstadoDocSapEnum getEnumByName(String name) {
		for (EstadoDocSapEnum estado : EstadoDocSapEnum.values()) {
			if (estado.name().equalsIgnoreCase(name)) {
				return estado;
			}
		}
		return null;
	}
	public static EstadoDocSapEnum getEnumByCodigo(String codigo) {
		for (EstadoDocSapEnum estado : EstadoDocSapEnum.values()) {
			if (estado.getCodigo().equalsIgnoreCase(codigo)) {
				return estado;
			}
		}
		return null;
	}
}
