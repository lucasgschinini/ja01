package ar.com.telecom.shiva.base.enumeradores;

public enum TipoResultadoSimulacionEnum {

	OK("", "OK", "OK", "OK", "OK", "S"),
	NOK("", "", "NOK", "NOK", "", "A"),
	ERROR("Error", "ER", "ERR", "ERR", "ERROR", "E"),
	WARNING("Warning", "WR", "WRN", "", "", "W"),
	ERROR_SERVICIO("Error de servicio", "", "", "", "", "X");
	
	private String descripcion;
	private String codigoMic;
	private String codigoCalipso;
	private String codigoDeimos;
	private String codigoShiva;
	private String codigoSap;
	
	/**
	 * 
	 * @param codigoMic
	 * @param codigoCalipso
	 */
	private TipoResultadoSimulacionEnum(
			String descripcion, String codigoMic, String codigoCalipso, String codigoDeimos, String codigoShiva, String codigoSap) {
		
		this.descripcion = descripcion;
		this.codigoMic = codigoMic;
		this.codigoCalipso = codigoCalipso;
		this.codigoDeimos = codigoDeimos;
		this.codigoShiva = codigoShiva;
		this.codigoSap = codigoSap;
	}

	/**
	 * 
	 * @param resultadoInvocacion
	 * @return
	 */
	public static TipoResultadoSimulacionEnum getEnumByCodigoMic(String resultadoInvocacion){
		for (TipoResultadoSimulacionEnum enumerador : TipoResultadoSimulacionEnum.values()) {
			if (enumerador.codigoMic.equalsIgnoreCase(resultadoInvocacion)) {
				return enumerador;
			}
		}
		        
		return null; 
	}
	
	/**
	 * 
	 * @param resultadoInvocacion
	 * @return
	 */
	public static TipoResultadoSimulacionEnum getEnumByCodigoCalipso(String resultadoInvocacion){
		for (TipoResultadoSimulacionEnum enumerador : TipoResultadoSimulacionEnum.values()) {
			if (enumerador.codigoCalipso.equalsIgnoreCase(resultadoInvocacion)) {
				return enumerador;
			}
		}
		        
		return null; 
	}
	
	/**
	 * 
	 * @param resultadoInvocacion
	 * @return
	 */
	public static TipoResultadoSimulacionEnum getEnumByCodigoDeimos(String resultadoInvocacion){
		for (TipoResultadoSimulacionEnum enumerador : TipoResultadoSimulacionEnum.values()) {
			if (enumerador.codigoDeimos.equalsIgnoreCase(resultadoInvocacion)) {
				return enumerador;
			}
		}
		        
		return null; 
	}
	
	/**
	 * 
	 * @param resultadoInvocacion
	 * @return
	 */
	public static TipoResultadoSimulacionEnum getEnumByCodigoShiva(String resultadoInvocacion){
		for (TipoResultadoSimulacionEnum enumerador : TipoResultadoSimulacionEnum.values()) {
			if (enumerador.codigoShiva.equalsIgnoreCase(resultadoInvocacion)) {
				return enumerador;
			}
		}
		        
		return null; 
	}

	/**
	 * 
	 * @param resultadoInvocacion
	 * @return
	 */
	public static TipoResultadoSimulacionEnum getEnumByCodigoSap(String resultadoInvocacion){
		for (TipoResultadoSimulacionEnum enumerador : TipoResultadoSimulacionEnum.values()) {
			if (enumerador.codigoSap.equalsIgnoreCase(resultadoInvocacion)) {
				return enumerador;
			}
		}
		        
		return null; 
	}

	/**
	 * @return the descripcion
	 */
	public String descripcion() {
		return descripcion;
	}

	public String codigoCalipso() {
		return codigoCalipso;
	}
	
	public String codigoMic() {
		return codigoMic;
	}

	public String codigoDeimos() {
		return codigoDeimos;
	}
	
	public String codigoShiva() {
		return codigoShiva;
	}
	
	public String codigoSap() {
		return codigoSap;
	}

}
