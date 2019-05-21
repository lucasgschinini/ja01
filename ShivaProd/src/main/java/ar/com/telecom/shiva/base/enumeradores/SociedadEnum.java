package ar.com.telecom.shiva.base.enumeradores;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author u578936 M.A.Uehara
 *
 */
public enum SociedadEnum {
	TELECOM("Telecom", "TA"),
	PERSONAL("Personal", "TP"),
	FIBERCORP("Fibercorp", "FC"),
	NEXTEL("Nextel", "NX"),
	PUBLICIDAD("Publicidad y Wholesale", "PYW");
	
	private String descripcion;
	private String apocope;
	
	private SociedadEnum(String descripcion, String apocope) {
		this.descripcion = descripcion;
		this.apocope = apocope;
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
	 * @return the apocope
	 */
	public String getApocope() {
		return apocope;
	}
	
	/**
	 * @param apocope the apocope to set
	 */
	public void setApocope(String apocope) {
		this.apocope = apocope;
	}
	
	public static SociedadEnum getEnumByName(String name) {
		for (SociedadEnum enumerado : SociedadEnum.values()) {
			if (enumerado.name().equalsIgnoreCase(name)) {
				return enumerado;
			}
		}
		return null;
	}
	
	public static SociedadEnum getEnumByDescripcion(String descripcion) {
		for (SociedadEnum enumerado : SociedadEnum.values()) {
			if (enumerado.getDescripcion().equalsIgnoreCase(descripcion)) {
				return enumerado;
			}
		}
		return null;
	}
	
	public static Map<String, Map<String, Object>> convertirAMap() {
		Map<String, Map<String, Object>> map = new HashMap<String, Map<String,Object>>();

		for (SociedadEnum item : SociedadEnum.values()) {
			Map<String, Object> des = new HashMap<String, Object>();
			des.put("descripcion", item.getDescripcion());
			map.put(item.name(), des);
		}
		return map;
	}
	public static List<Object> listarName() {
		List<Object> lista = new ArrayList<Object>();

		for (SociedadEnum item : SociedadEnum.values()) {
			lista.add(item.name());
		}
		return lista;
	}
	
	public static SociedadEnum getEnumByApocope(String apocope) {
		for (SociedadEnum enumerado : SociedadEnum.values()) {
			if (enumerado.getApocope().equalsIgnoreCase(apocope)) {
				return enumerado;
			}
		}
		return null;
	}
}
