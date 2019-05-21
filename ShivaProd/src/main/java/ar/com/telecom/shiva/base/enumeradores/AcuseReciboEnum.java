package ar.com.telecom.shiva.base.enumeradores;

import java.util.ArrayList;
import java.util.List;

public enum AcuseReciboEnum {
	

	CERRADO_AUSENTE(1,"Cerrado/Ausente"),
	DIRECCION_INEXISTENTE(2,"Dirección Inexistente"),
	DIRECCION_INSUFICIENTE(3,"Dirección Insuficiente"),
	ENTREGADO(4,"Entregado"),
	RECHAZADO(5,"Rechazado");
	
	int indice;
	String descripcion;

	public String getDescripcion() {
		return descripcion;
	}
	
	public int getIndice() {
		return indice;
	}
	
	private AcuseReciboEnum(int indice, String descripcion) {
	    this.indice = indice;
		this.descripcion = descripcion;
	}
	
	public static AcuseReciboEnum getEnumByDescripcion(String descripcion) {
		
		for (AcuseReciboEnum acuseReciboEnum : AcuseReciboEnum.values()) {
			if (acuseReciboEnum.descripcion.equalsIgnoreCase(descripcion)){
				return acuseReciboEnum;
			}
		}
		return null; 
	}
	
	public static AcuseReciboEnum getEnumByIndice(int indice) {
		
		for (AcuseReciboEnum acuseReciboEnum : AcuseReciboEnum.values()) {
			if (acuseReciboEnum.indice == indice){
				return acuseReciboEnum;
			}
		}
		return null; 
	}
	
	public static AcuseReciboEnum getEnumByName(String name) {
		
		for (AcuseReciboEnum acuseReciboEnum : AcuseReciboEnum.values()) {
			if (acuseReciboEnum.name().equalsIgnoreCase(name)) {
				return acuseReciboEnum;
			}
		}
		        
		return null; 
	}
	
	public static List<AcuseReciboEnum> getEnums() {
		List<AcuseReciboEnum> lista = new ArrayList<AcuseReciboEnum>();
		for (AcuseReciboEnum enumerado : AcuseReciboEnum.values()) {
			lista.add(enumerado);
		}
		return lista;
	}
}
