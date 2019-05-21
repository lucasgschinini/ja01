package ar.com.telecom.shiva.base.enumeradores;

import java.util.ArrayList;
import java.util.List;

public enum TipoNotificacionEnum {
	
	NOTIFICACION_RECHAZO(1,"Notificación del Rechazo"),
	NOTIFICACION_REEMBOLSO(2,"Notificación del Reembolso"),
	ENTREGA_CHEQUE(3,"Entrega de Cheque");
	
	int indice;
	String descripcion;

	public String getDescripcion() {
		return descripcion;
	}
	

	public int getIndice() {
		return indice;
	}
	
	private TipoNotificacionEnum(int indice, String descripcion) {
	    this.indice =indice;
		this.descripcion = descripcion;
	}
	
	public static TipoNotificacionEnum getEnumByDescripcion(String descripcion) {
		
		for (TipoNotificacionEnum tipoNotificacion : TipoNotificacionEnum.values()) {
			if (tipoNotificacion.descripcion.equalsIgnoreCase(descripcion)){
				return tipoNotificacion;
			}
		}
		return null; 
	}
	
	public static TipoNotificacionEnum getEnumByIndice(int indice) {
		
		for (TipoNotificacionEnum tipoNotificacionEnum : TipoNotificacionEnum.values()) {
			if (tipoNotificacionEnum.indice == indice){
				return tipoNotificacionEnum;
			}
		}
		return null; 
	}
	
	public static TipoNotificacionEnum getEnumByName(String name) {
		
		for (TipoNotificacionEnum tipoNotificacion : TipoNotificacionEnum.values()) {
			if (tipoNotificacion.name().equalsIgnoreCase(name)) {
				return tipoNotificacion;
			}
		}
		        
		return null; 
	}
	
	
	public static List<TipoNotificacionEnum> getEnums() {
		List<TipoNotificacionEnum> lista = new ArrayList<TipoNotificacionEnum>();
		for (TipoNotificacionEnum enumerado : TipoNotificacionEnum.values()) {
			lista.add(enumerado);
		}
		return lista;
	}

}
