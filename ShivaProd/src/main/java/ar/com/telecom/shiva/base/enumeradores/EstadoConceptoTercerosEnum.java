package ar.com.telecom.shiva.base.enumeradores;

public enum EstadoConceptoTercerosEnum {

	NO_TRANSFERIDOS("N"),
	PARCIALMENTE_TRANSFERIDOS("S"),
	TOTALMENTE_TRANSFERIDOS("T");

	private String codigoMic;
	
	EstadoConceptoTercerosEnum(String codigoMic){
		this.codigoMic = codigoMic;
	}

	/**
	 * 
	 * @param codigoMic
	 * @return
	 */
	public static EstadoConceptoTercerosEnum getEnumByCodigoMic(String codigoMic) {
		for (EstadoConceptoTercerosEnum enumerador : EstadoConceptoTercerosEnum.values()) {
			if (enumerador.codigoMic.equalsIgnoreCase(codigoMic)){
				return enumerador;
			}
		}
		return null; 
	}

	/**
	 * @return the codigoMic
	 */
	public String getCodigoMic() {
		return codigoMic;
	}
}

