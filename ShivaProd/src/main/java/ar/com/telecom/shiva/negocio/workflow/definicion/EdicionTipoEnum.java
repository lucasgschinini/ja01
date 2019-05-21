package ar.com.telecom.shiva.negocio.workflow.definicion;

import java.util.HashMap;
import java.util.Map;

/**
 * @author u578936 M.A.Uehara
 *
 */
public enum EdicionTipoEnum {
	EDICION_PARCIAL_1("EDICION_PARCIAL_1"),
	EDICION_PARCIAL_2("EDICION_PARCIAL_2"),
	EDICION_PARCIAL_3("EDICION_PARCIAL_3"),
	EDICION_PARCIAL_4("EDICION_PARCIAL_4"),
	EDICION_PARCIAL_4_1("EDICION_PARCIAL_4_1"),
	EDICION_PARCIAL_5("EDICION_PARCIAL_5"),
	EDICION_PARCIAL_5_1("EDICION_PARCIAL_5_1"),
	EDICION_PARCIAL_6("EDICION_PARCIAL_6"),
	EDICION_PARCIAL_7("EDICION_PARCIAL_7"),
	EDICION_PARCIAL_8("EDICION_PARCIAL_8"),
	EDICION_PARCIAL_9("EDICION_PARCIAL_9"),
	SIN_EDICION("SIN_EDICION"),
	EDICION_SUPERVICION("EDICION_SUPERVICION"),
	EDICION_TOTAL("EDICION_TOTAL");
	
	String descripcion;
	
	
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


	private EdicionTipoEnum(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	public static Map<String, Map<String, Object>> convertirAMap() {
		Map<String, Map<String, Object>> map = new HashMap<String, Map<String,Object>>();

		for (EdicionTipoEnum item : EdicionTipoEnum.values()) {
			Map<String, Object> des = new HashMap<String, Object>();
			des.put("descripcion", item.getDescripcion());
			des.put("name", item.name());
			map.put(item.name(), des);
		}
		return map;
	}
}
