package ar.com.telecom.shiva.base.enumeradores;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum SistemaEnum {
	
	CALIPSO("Calipso", "CLP"),
	MIC("Mic", "MIC"),
	DEIMOS("Deimos","DEI"),
	SHIVA("Shiva","SHV"),
	USUARIO("Usuario", "USU"),
	OTRO("Otro", "OTR"),
	SAP("SAP", "SAP"),
	NEGOCIO_NET("Negocio.Net","NET"),
	CABLEVISION("Cablevisión", "CV"),
	FIBERTEL("Fibertel", "FT"),
	NEXTEL("Nextel", "NX"),
	ICE("Ice","ICE"),
	OPEN("Open", "OP"),
	NEXUS("Nexus", "NXS"),
	HUAWEI("Huawei", "HW");
	

	String descripcion;
	String descripcionCorta;
	
	private SistemaEnum(String descripcion, String descripcionCorta) {
	    this.descripcion = descripcion;
	    this.descripcionCorta = descripcionCorta;
	}
	
	public String getDescripcion() {
	    return this.descripcion;
	}

	public String getDescripcionCorta() {
		return descripcionCorta;
	}
	
	/**
	 * 
	 * @param descripcionCorta
	 * @return
	 */
	public static SistemaEnum getEnumByDescripcionCorta(String descripcionCorta){
		
		for (SistemaEnum item : SistemaEnum.values()) {
			if (item.getDescripcionCorta().equals(descripcionCorta)) {
				return item;
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @param descripcionCorta
	 * @return
	 */
	public static SistemaEnum getEnumByDescripcion(String descripcion){
		
		for (SistemaEnum item : SistemaEnum.values()) {
			if (item.getDescripcion().equals(descripcion)) {
				return item;
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public static SistemaEnum getEnumByName(String name){
		for (SistemaEnum item : SistemaEnum.values()) {
			if (item.name().equals(name)) {
				return item;
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @return
	 */
	public static List<SistemaEnum> getEnumMICyCLP(){
		List<SistemaEnum> lista = new ArrayList<SistemaEnum>();
		lista.add(CALIPSO);
		lista.add(MIC);
		return lista;
	}
	
	/**
	 * Para el combo sistema creditos
	 * @return
	 */
	public static List<SistemaEnum> getEnumComboCreditoSistema(){
		List<SistemaEnum> lista = new ArrayList<SistemaEnum>();
		lista.add(CALIPSO);
		lista.add(MIC);
		lista.add(SHIVA);
		return lista;
	}
	
	public static Map<String, Map<String, Object>> convertirAMap() {
		Map<String, Map<String, Object>> map = new HashMap<String, Map<String,Object>>();

		for (SistemaEnum item : SistemaEnum.values()) {
			Map<String, Object> des = new HashMap<String, Object>();
			des.put("descripcionCorta", item.getDescripcionCorta());
			des.put("descripcion", item.getDescripcion());
			map.put(item.name(), des);
		}
		return map;
	}
	
	public static List<SistemaEnum> getEnumAplicacionManual () {
		List<SistemaEnum> lista = new ArrayList<SistemaEnum>();
		lista.add(NEGOCIO_NET);
		lista.add(SAP);
		lista.add(CABLEVISION);
		lista.add(NEXTEL);
		return lista;
	}
	public static List<SistemaEnum> getEnumOtrosDebito (){
		List<SistemaEnum> lista = new ArrayList<SistemaEnum>();
		lista.add(NEGOCIO_NET);
		lista.add(SAP);
		lista.add(OPEN);
		lista.add(NEXUS);
		lista.add(HUAWEI);
		return lista;
	}
	/**
	 * Excluyo NEXUS y HUAWEI
	 * @param sistema
	 * @return
	 */
	public static List<SistemaEnum> obtenerListaDeSistemas(String sistema) {
		
		List<SistemaEnum> lista = new ArrayList<SistemaEnum>();
		String[] sistemasString = sistema.split(";");
		
		for (String sistemaString : sistemasString) {
			for (SistemaEnum sistemaEnum : SistemaEnum.values()) {
				if (sistemaEnum.getDescripcionCorta().equalsIgnoreCase(sistemaString)) {
					lista.add(sistemaEnum);
					break;
				}
			}
		}
		
		return lista;
	}
	public static Map<String, Map<String, Object>> convertirAMapOtrosDebito() {
		Map<String, Map<String, Object>> map = new HashMap<String, Map<String,Object>>();

		for (SistemaEnum item : getEnumOtrosDebito()) {
			Map<String, Object> des = new HashMap<String, Object>();
			des.put("descripcionCorta", item.getDescripcionCorta());
			des.put("descripcion", item.getDescripcion());
			map.put(item.name(), des);
		}
		return map;
	}
}
