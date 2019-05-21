package ar.com.telecom.shiva.base.enumeradores;

import java.util.HashMap;
import java.util.Map;

/**
 * @author u578936 M.A.Uehara
 *
 */
public enum ConfFormObligatoriedadEnum {
	OBLIGATORIO("Obligatorio"),
	SELECTOR("Selector"),
	INFOCLIENTE("InfoCliente");
	
	private String descripcion;
	
	private ConfFormObligatoriedadEnum(String descripcion) {
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

	public static Map<String, Map<String, Object>> convertirAMap() {
		Map<String, Map<String, Object>> map = new HashMap<String, Map<String,Object>>();

		for (ConfFormObligatoriedadEnum item : ConfFormObligatoriedadEnum.values()) {
			Map<String, Object> des = new HashMap<String, Object>();
			des.put("descripcion", item.getDescripcion());
			map.put(item.name(), des);
		}
		return map;
	}
	public static ConfFormObligatoriedadEnum getEnumByName(String name){
		for (ConfFormObligatoriedadEnum item : ConfFormObligatoriedadEnum.values()) {
			if (item.name().equals(name)) {
				return item;
			}
		}
		return null;
	}
	
}
