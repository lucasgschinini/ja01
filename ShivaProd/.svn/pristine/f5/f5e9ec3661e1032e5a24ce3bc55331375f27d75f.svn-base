package ar.com.telecom.shiva.base.enumeradores;

import java.util.ArrayList;
import java.util.List;

public enum UbicacionChequeEnum {

	
	ANALISTA_CHEQUES_RECH(1,"Analista de Cheques Rechazados"),
	ANALISTA_COBRANZA(2,"Analista de Cobranza"),
	CLIENTE(3,"Cliente"),
	LEGALES(4,"Legales"),
	TESORERIA(5,"Tesorería");
	
	int indice;
	String descripcion;
	
	private UbicacionChequeEnum(int indice,String descripcion) {
		this.indice = indice;
	    this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getIndice() {
		return indice;
	}

	public void setIndice(int indice) {
		this.indice = indice;
	}
	
	public static UbicacionChequeEnum getEnumByIndice(int indice) {
		
		for (UbicacionChequeEnum ubicacion : UbicacionChequeEnum.values()) {
			if (ubicacion.getIndice()==indice) {
				return ubicacion;
			}
		}
		        
		return null; 
	}
	
	public static UbicacionChequeEnum getEnumByDescripcion(String descripcion) {
		
		for (UbicacionChequeEnum ubicacion : UbicacionChequeEnum.values()) {
			if (ubicacion.getDescripcion().equals(descripcion)) {
				return ubicacion;
			}
		}
		        
		return null; 
	}
	
	public static List<UbicacionChequeEnum> listaSinClienteNiLegales() {
		List<UbicacionChequeEnum> ubicacion = new ArrayList<UbicacionChequeEnum>();
		ubicacion.add(ANALISTA_CHEQUES_RECH);
		ubicacion.add(ANALISTA_COBRANZA);
		ubicacion.add(TESORERIA);
		return ubicacion;
	}

	public static List<UbicacionChequeEnum> listaUbicaciones() {
		List<UbicacionChequeEnum> ubicacion = new ArrayList<UbicacionChequeEnum>();
		ubicacion.add(ANALISTA_CHEQUES_RECH);
		ubicacion.add(ANALISTA_COBRANZA);
		ubicacion.add(CLIENTE);
		ubicacion.add(LEGALES);
		ubicacion.add(TESORERIA);
		return ubicacion;
	}

}
