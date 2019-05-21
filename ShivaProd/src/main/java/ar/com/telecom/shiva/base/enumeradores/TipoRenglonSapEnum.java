package ar.com.telecom.shiva.base.enumeradores;

public enum TipoRenglonSapEnum {
	AGRUPADOR("RENGLON_AGRUPADOR", "Renglon donde se visualizan lo montos"),
	CAP("RENGLON_CAP", ""),
	REF("RENGLON_REF", ""),
	FIN("RENGLON FIN", ""),
	CAP_FIN("RENGLON FIN CAP", ""),
	REF_FIN("RENGLON FIN CAP", "");

	private String codigo = "";
	private String descripcion = "";

	
	private TipoRenglonSapEnum(String codigo, String descripcion) {
		this.descripcion = descripcion;
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public String getCodigo() {
		return codigo;
	}

	public static TipoRenglonSapEnum getEnumByName(String name) {
		for (TipoRenglonSapEnum enumerador : TipoRenglonSapEnum.values()) {
			if (enumerador.name().equalsIgnoreCase(name)) {
				return enumerador;
			}
		}
		return null; 
	}
}
