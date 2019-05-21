package ar.com.telecom.shiva.base.enumeradores;

// SHV_PARAM_ORIGEN
public enum TipoShivaEnum {

	AVISO_DE_PAGO("Aviso de pago", "6"),
	REVERSION("Reversión", "7"),
	CONCILIACION("Conciliación", "5"),
	BOLETA_OFICINA_REC_PRE("Boleta Oficina", "3"),
	BOLETA_OFICINA_CONST_AUT("Boleta Oficina", "4"),
	BOLETA_CLIENTE("Boleta Cliente", "2"),
	BOLETA_CAJERO_PAGADOR("Boleta Cajero Pagador", "1");

	String descripcion;
	String idOrigen;

	private TipoShivaEnum(String descripcion, String idOrigen) {
		this.descripcion = descripcion;
		this.idOrigen = idOrigen;
	}

	public String descripcion() {
		return this.descripcion;
	}
	public String idOrigen() {
		return idOrigen;
	}
	public static TipoShivaEnum getEnumByName(String name) {

		for (TipoShivaEnum enumerador : TipoShivaEnum.values()) {
			if (enumerador.name().equalsIgnoreCase(name)) {
				return enumerador;
			}
		}
		return null;
	}
	public static TipoShivaEnum getEnumByDescripcion(String descripcion) {
		for (TipoShivaEnum enumerador : TipoShivaEnum.values()) {
			if (enumerador.descripcion().equalsIgnoreCase(descripcion)) {
				return enumerador;
			}
		}
		return null;
	}
	public static TipoShivaEnum getEnumByIdOrigen(String idOrigen) {
		for (TipoShivaEnum enumerador : TipoShivaEnum.values()) {
			if (enumerador.idOrigen().equalsIgnoreCase(idOrigen)) {
				return enumerador;
			}
		}
		return null;
	}
}
