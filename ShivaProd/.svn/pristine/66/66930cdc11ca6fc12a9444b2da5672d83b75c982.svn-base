package ar.com.telecom.shiva.base.enumeradores;

import java.util.ArrayList;
import java.util.List;

public enum TratamientoInteresesEnum {

	TV("Traslado vencimiento con 100% de bonificacion", "TV", "", "PROXIMO_PERIODO", "TV", "SIMULAR"),
	TC("Traslado vencimiento con XX% de bonificacion", "TC", "", "PROXIMO_PERIODO", "TV", "SIMULAR"),
	C("Cobro con bonificacion con XX% de bonificacion", "C", "", "COBRO", "", ""),
	TM("Traslado mora", "TM" , "T", "PROXIMO_PERIODO", "TM", ""),
	BV("Bonificacion vencimiento","BV", "", "BONIFICA_VTO", "TV", "SIMULAR"),
	BM("Bonificacion mora","BM", "B", "BONIFICA_VTO", "TM", ""),
	SC("Sin calculo","SC", "S", "", "SC", "");
	

	String descripcion;
	String codigoMicApropiacion;
	String codigoMicCargo;
	String codigoCalipsoApropiacion;
	String codigoMicSimulacion;
	String codigoCalipsoSimulacion;
	
	private TratamientoInteresesEnum(
			String descripcion, String codigoMic, String codigoMicCargo, String codigoCalipso, String codigoMicSimulacion, String codigoCalipsoSimulacion) {

		this.descripcion = descripcion;
	    this.codigoMicApropiacion = codigoMic;
	    this.codigoMicCargo = codigoMicCargo;
		this.codigoCalipsoApropiacion = codigoCalipso;
		this.codigoMicSimulacion = codigoMicSimulacion;
		this.codigoCalipsoSimulacion = codigoCalipsoSimulacion;
	}

	public static TratamientoInteresesEnum getEnumByName(String name) {
		for (TratamientoInteresesEnum tipo : TratamientoInteresesEnum.values()) {
			if (tipo.name().equalsIgnoreCase(name)) {
				return tipo;
			}
		}
		return null; 
	}
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public static TratamientoInteresesEnum getEnumByNameAccionIntereses(String name) {
		for (TratamientoInteresesEnum tipo : TratamientoInteresesEnum.values()) {
			if (TratamientoInteresesEnum.BM.equals(tipo)
					|| TratamientoInteresesEnum.BV.equals(tipo)
					|| TratamientoInteresesEnum.TM.equals(tipo)
					|| TratamientoInteresesEnum.TV.equals(tipo)) {
			
				if (tipo.name().equalsIgnoreCase(name)) {
					return tipo;
				}
			}
		}
		return null; 
	}
	
	/**
	 * 
	 * @return
	 */
	public static List<TratamientoInteresesEnum> getValuesAccionIntereses() {
		List<TratamientoInteresesEnum> lista = new ArrayList<TratamientoInteresesEnum>();
		lista.add(BM);
		lista.add(BV);
		lista.add(TM);
		lista.add(TV);
		return lista;
	}
		

	public String getDescripcion() {
	    return this.descripcion;
	}

	public String getCodigoMicApropiacion() {
		return codigoMicApropiacion;
	}

	public String getCodigoCalipsoApropiacion() {
		return codigoCalipsoApropiacion;
	}

	/**
	 * @return the codigoMicCargo
	 */
	public String getCodigoMicCargo() {
		return codigoMicCargo;
	}

	/**
	 * @return the codigoMicSimulacion
	 */
	public String getCodigoMicSimulacion() {
		return codigoMicSimulacion;
	}

	/**
	 * @return the codigoCalipsoSimulacion
	 */
	public String getCodigoCalipsoSimulacion() {
		return codigoCalipsoSimulacion;
	}
}

