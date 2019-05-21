package ar.com.telecom.shiva.base.enumeradores;

public enum TipoMoraEnum {
	PROXIMO_PERIODO,
	BONIFICA_VTO,
	COBRO,
	SIMULAR;
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public static TipoMoraEnum getEnumByName(String name) {
		for (TipoMoraEnum enumerador : TipoMoraEnum.values()) {
			if (enumerador.name().equalsIgnoreCase(name)) {
				return enumerador;
			}
		}
		return null;
	}
}
