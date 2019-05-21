package ar.com.telecom.shiva.base.enumeradores;

import java.util.ArrayList;
import java.util.List;

public enum MarcaCyQEnum {

	VACIO(" ", ""),
	C("C", "CONCURSO"),
	Q("Q", "QUIEBRA");

	String codigo;
	String descripcion;
	
	private MarcaCyQEnum(String codigo, String descripcion) {
		this.codigo = codigo;
		this.descripcion = descripcion;
	}
	
	public String codigo(){
		return this.codigo;
	}
	
	public String descripcion() {
	    return this.descripcion;
	}
	
	
	public static MarcaCyQEnum getEnumByCodigo(String name) {
		
		for (MarcaCyQEnum marcaCYQ : MarcaCyQEnum.values()) {
			if (marcaCYQ.codigo().equalsIgnoreCase(name)) {
				return marcaCYQ;
			}
		}
		        
		return null; 
	}

	public static MarcaCyQEnum getEnumByName(String name) {
		
		for (MarcaCyQEnum marcaCYQ : MarcaCyQEnum.values()) {
			if (marcaCYQ.name().equalsIgnoreCase(name)) {
				return marcaCYQ;
			}
		}
		        
		return null; 
	}
	public static List<String> getNames() {
		List<String> names = new ArrayList<String>();
		for (MarcaCyQEnum marcaCYQ : MarcaCyQEnum.values()) {
			names.add(marcaCYQ.name());
		}
		return names;
	}
	public static List<String> getDesc() {
		List<String> names = new ArrayList<String>();
		for (MarcaCyQEnum marcaCYQ : MarcaCyQEnum.values()) {
			names.add(marcaCYQ.descripcion());
		}
		return names;
	}
	public static String getLeyenda() {
		StringBuffer str = new StringBuffer();
		for (MarcaCyQEnum marcaCYQ : MarcaCyQEnum.values()) {
			str.append(marcaCYQ.name() + "(" + marcaCYQ.codigo + ")");
		}
		return str.toString();
	}
}
