package ar.com.telecom.shiva.base.enumeradores;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum MonedaEnum {
    PES("Pesos", "$", "ARS", "$", "Peso"),
    DOL("Dolares", "U$D", "USD", "U$S", "Dolar"),
    EUR("Euros", "€", "EUR", "€", "Euro"),
    TOD("Todos", "TODOS", "TODOS", "TODOS", "TODOS");
    
    String descripcion;
    String signo;
    String sigla;
    String signo2;
    String descripcionSingular; 
    
	/**
	 * 
	 * @param descripcion
	 * @param signo
	 * @param sigla
	 * @param signo2
	 */
    private MonedaEnum(String descripcion, String signo, String sigla, String signo2, String descripcionSingular) {
	    this.descripcion = descripcion;
	    this.signo = signo;
	    this.sigla = sigla;
	    this.signo2 = signo2;
	    this.descripcionSingular = descripcionSingular;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getDescripcion() {
	    return this.descripcion;
	}
	public String getDescripcionSingular() {
	    return this.descripcionSingular;
	}
	/**
	 * 
	 * @return
	 */
	public String getSigla() {
	    return this.sigla;
	}

	/**
	 * 
	 * @return
	 */
	public String getSigno() {
		return signo;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getSigno2() {
		return signo2;
	}
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public static MonedaEnum getEnumByName(String name) {
		for (MonedaEnum moneda : MonedaEnum.values()) {
			if (moneda.name().equalsIgnoreCase(name)) {
				return moneda;
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @param sigla
	 * @return
	 */
	public static MonedaEnum getEnumBySigla(String sigla) {
		for (MonedaEnum moneda : MonedaEnum.values()) {
			if (moneda.getSigla().equalsIgnoreCase(sigla)) {
				return moneda;
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @param moneda
	 * @return
	 */
	public static MonedaEnum getEnumByString(String moneda){
		for (MonedaEnum mon : MonedaEnum.values()) {
            if(mon.name().equalsIgnoreCase(moneda))
                return mon;
            if(mon.getDescripcion().equalsIgnoreCase(moneda))
                return mon;
            if(mon.getSigno().equalsIgnoreCase(moneda))
                return mon;
            if(mon.getSigla().equalsIgnoreCase(moneda))
                return mon;
        }
        return null;
    }

	/**
	 * 
	 * @param moneda
	 * @return
	 */
	public static MonedaEnum getEnumBySigno(String moneda){
		for (MonedaEnum mon : MonedaEnum.values()) {
            if(mon.getSigno().equalsIgnoreCase(moneda))
                return mon;
        }
        return null;
    }

	/**
	 * 
	 * @param moneda
	 * @return
	 */
	public static MonedaEnum getEnumBySigno2(String moneda){
		for (MonedaEnum mon : MonedaEnum.values()) {
            if(mon.getSigno2().equalsIgnoreCase(moneda))
                return mon;
        }
        return null;
    }
	
	/**
	 * 
	 */
	public static List<MonedaEnum> getEnum$yU$S(){
		List<MonedaEnum> lista = new ArrayList<MonedaEnum>();
		lista.add(PES);
		lista.add(DOL);
		return lista; 
	}

	/**
	 * 
	 */
	public static List<MonedaEnum> getEnumU$SyEuros(){
		List<MonedaEnum> lista = new ArrayList<MonedaEnum>();
		lista.add(DOL);
		lista.add(EUR);
		return lista; 
	}
	
	public static List<MonedaEnum> getEnums() {
		List<MonedaEnum> lista = new ArrayList<MonedaEnum>();
		for (MonedaEnum enumerado : MonedaEnum.values()) {
			lista.add(enumerado);
		}
		return lista;
	}
	
	public static List<MonedaEnum> getEnum$yU$SEuros(){
		List<MonedaEnum> lista = new ArrayList<MonedaEnum>();
		lista.add(PES);
		lista.add(DOL);
		lista.add(EUR);
		
		return lista; 
	}
	
	public static Map<String, Map<String, Object>> convertirAMap() {
		Map<String, Map<String, Object>> map = new HashMap<String, Map<String,Object>>();

		for (MonedaEnum item : MonedaEnum.values()) {
			Map<String, Object> des = new HashMap<String, Object>();
			des.put("descripcion", item.getDescripcion());
			des.put("signo2", item.getSigno2());
			map.put(item.name(), des);
		}
		return map;
	}
	
	public static MonedaEnum getEnumByDescripcionSingular(String descripcion) {
		for (MonedaEnum moneda : MonedaEnum.values()) {
			if (moneda.descripcionSingular.equalsIgnoreCase(descripcion)) {
				return moneda;
			}
		}
		return null;
	}
}
