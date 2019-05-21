package ar.com.telecom.shiva.base.enumeradores;

public enum TipoInvocacionIceEnum {

	$01("1", "Consulta de cheques en ICE"),
	$02("2", "Consulta de Cobros de un cheque en ICE");

	private String codigo = "";
	private String descripcion = "";

	/**
	 * 
	 * @param codigo
	 * @param descripcion
	 */
	private TipoInvocacionIceEnum(String codigo, String descripcion) {
		this.descripcion = descripcion;
		this.codigo = codigo;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * 
	 * @return
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * 
	 * @param name
	 * @return
	 */
	public static TipoInvocacionIceEnum getEnumByName(String name) {
		for (TipoInvocacionIceEnum enumerador : TipoInvocacionIceEnum.values()) {
			if (enumerador.name().equalsIgnoreCase(name)) {
				return enumerador;
			}
		}
		return null; 
	}
}
