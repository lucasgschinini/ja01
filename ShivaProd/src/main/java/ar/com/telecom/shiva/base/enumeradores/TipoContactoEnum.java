package ar.com.telecom.shiva.base.enumeradores;

import java.util.ArrayList;
import java.util.List;

public enum TipoContactoEnum {

	CARTA(1,"Carta"),
	EMAIL(2,"Email"),
	TELEFONICO(3,"Telefónico"),
	PRESENCIAL(4,"Presencial");
	
	int indice;
	String descripcion;

	public String getDescripcion() {
		return descripcion;
	}
	
	public int getIndice() {
		return indice;
	}
	
	private TipoContactoEnum(int indice, String descripcion) {
	    this.indice = indice;
		this.descripcion = descripcion;
	}
	
	public static TipoContactoEnum getEnumByDescripcion(String descripcion) {
		
		for (TipoContactoEnum tipoContactoEnum : TipoContactoEnum.values()) {
			if (tipoContactoEnum.descripcion.equalsIgnoreCase(descripcion)){
				return tipoContactoEnum;
			}
		}
		return null; 
	}
	
	public static TipoContactoEnum getEnumByIndice(int indice) {
		
		for (TipoContactoEnum tipoContactoEnum : TipoContactoEnum.values()) {
			if (tipoContactoEnum.indice == indice){
				return tipoContactoEnum;
			}
		}
		return null; 
	}
	
	public static TipoContactoEnum getEnumByName(String name) {
		
		for (TipoContactoEnum tipoContactoEnum : TipoContactoEnum.values()) {
			if (tipoContactoEnum.name().equalsIgnoreCase(name)) {
				return tipoContactoEnum;
			}
		}
		        
		return null; 
	}
	
	public static List<TipoContactoEnum> getEnums() {
		List<TipoContactoEnum> lista = new ArrayList<TipoContactoEnum>();
		for (TipoContactoEnum enumerado : TipoContactoEnum.values()) {
			lista.add(enumerado);
		}
		return lista;
	}
}
