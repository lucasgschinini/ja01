package ar.com.telecom.shiva.base.enumeradores;



public enum TipoOperacionEnum {
	
	COBRO("Cobro", "RC","COB", "COB"),
	DESCOBRO("Descobro", "NR","REV", "RCO"),
	REINTEGRO("Reintegro","","REI", ""),
	GANANCIA("Ganancia","","GAN", ""),
	SALDO_A_PAGAR("Saldo a Pagar", "", "SAP", ""),
	REVERSION_REINTEGRO("Reversión de Reintegro","","RRE", ""),
	REVERSION_GANANCIA("Reversión de Ganacia","","RGA", "");

	String descripcion;
	String tipoScard;
	String tipoSubdiario;
	String tipoOperacionesExternas;

	private TipoOperacionEnum(String descripcion, String tipoScard,String tipoSubdiario, String tipoOperacionesExternas) {
	    this.descripcion = descripcion;
	    this.tipoScard = tipoScard;
	    this.tipoSubdiario = tipoSubdiario;
	    this.tipoOperacionesExternas = tipoOperacionesExternas;
	}
	
	public String descripcion() {
	    return this.descripcion;
	}
	
	public String tipoSubdiario() {
		return tipoSubdiario;
	}

	public String tipoScard() {
		return tipoScard;
	}
	public String tipoOperacionesExternas() {
		return this.tipoOperacionesExternas;
	}
	public static TipoOperacionEnum getEnumByName(String cadena){
		for (TipoOperacionEnum en : TipoOperacionEnum.values()) {
			if (en.name().equalsIgnoreCase(cadena)){
				return en;
			}
		}
		return null;
	}
	public static TipoOperacionEnum getEnumByTipoOperacionesExternas(String cadena){
		for (TipoOperacionEnum en : TipoOperacionEnum.values()) {
			if (en.tipoOperacionesExternas().equalsIgnoreCase(cadena)){
				return en;
			}
		}
		return null;
	}
}
