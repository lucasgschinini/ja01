package ar.com.telecom.shiva.base.enumeradores;

import java.util.ArrayList;
import java.util.List;



public enum TipoParametroEntradaPerfilesAplicativoEnum {
	
	SHIVA_DEFAULT_USER("SHIVA_DEFAULT_USER.txt"),
	SHIVA_USER_ACTION("SHIVA_USER_ACTION.txt"),
	SHIVA_ROLE("SHIVA_ROLE.txt"),
	SHIVA_ACTIONS("SHIVA_ACTIONS.txt"),
	SHIVA_PROFILE("SHIVA_PROFILE.txt");
	
	String descripcion;
	
	private TipoParametroEntradaPerfilesAplicativoEnum(String descripcion) {
	    this.descripcion = descripcion;
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
	 * 
	 * @param name
	 * @return
	 */
	public static TipoParametroEntradaPerfilesAplicativoEnum getEnumByName(String name) {
		
		for (TipoParametroEntradaPerfilesAplicativoEnum tipoParametroEntrada : TipoParametroEntradaPerfilesAplicativoEnum.values()) {
			if (tipoParametroEntrada.name().equals(name)) {
				return tipoParametroEntrada;
			}
		}
		        
		return null; 
	}
	
	public static List<TipoParametroEntradaPerfilesAplicativoEnum> listarEstados (){
		List<TipoParametroEntradaPerfilesAplicativoEnum> resultado = new ArrayList<TipoParametroEntradaPerfilesAplicativoEnum>();
		for (TipoParametroEntradaPerfilesAplicativoEnum tipoParametro: TipoParametroEntradaPerfilesAplicativoEnum.values()){
				resultado.add(tipoParametro);
		}
		return resultado;
	}
}
