package ar.com.telecom.shiva.base.enumeradores;

public enum TipoArchivoOperacionMasivaEnum {

	DEUDA("Aplicar Deuda","Deuda", "01"),
	DSIST("Desistimiento","Desistimiento", "03"),
	GNCIA("Ganancias","Ganancia", "02"),
	REINT("Reintegro","Reintegro", "04");
	//CONVENIO("Archivo de Convenio","Convenio", "");
	
	String descripcion;
	String descripcionCorta;
	String codigo;
	
	private TipoArchivoOperacionMasivaEnum(String descripcion,String descripcionCorta, String codigo) {
		this.descripcion = descripcion;
		this.descripcionCorta = descripcionCorta;
		this.codigo = codigo;
	}
	
	public String getCodigo() {
		return this.codigo;
	}
	
	public String getDescripcion() {
		return this.descripcion;
	}
	
	public String getDescripcionCorta() {
		return this.descripcionCorta;
	}
	
	public String getName() {
		return this.name();
	}
	public static TipoArchivoOperacionMasivaEnum getEnumByName(String name) {
		for (TipoArchivoOperacionMasivaEnum tipoArchivo : TipoArchivoOperacionMasivaEnum.values()) {
			if (tipoArchivo.name().equalsIgnoreCase(name)) {
				return tipoArchivo;
			}
		}
		return null;
	}
	public static TipoArchivoOperacionMasivaEnum getEnumByCodigo(String codigo) {
		for (TipoArchivoOperacionMasivaEnum tipoArchivo : TipoArchivoOperacionMasivaEnum.values()) {
			if (tipoArchivo.getCodigo().equalsIgnoreCase(codigo)) {
				return tipoArchivo;
			}
		}
		return null;
	}
}
