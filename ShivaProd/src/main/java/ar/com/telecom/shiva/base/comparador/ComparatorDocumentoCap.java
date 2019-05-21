package ar.com.telecom.shiva.base.comparador;

import java.util.Comparator;

import ar.com.telecom.shiva.presentacion.bean.dto.DocumentoCapDto;

public class ComparatorDocumentoCap implements Comparator<DocumentoCapDto> {

	@Override
	public int compare(DocumentoCapDto arg0, DocumentoCapDto arg1) {
		int salida = 0;
	
		salida = arg0.getIdPantalla().compareTo(arg1.getIdPantalla()); 
		if (salida == 0) {
			if (arg0.obtenerSemaforoPrioridad() < 0 || arg1.obtenerSemaforoPrioridad() < 0) {
				if (arg0.obtenerSemaforoPrioridad() < 0 && arg1.obtenerSemaforoPrioridad() > -1) {
					salida = arg0.getIdPantallaRenglon().compareTo(arg1.getIdPantallaRenglon());
				} else if (arg0.obtenerSemaforoPrioridad() > -1 && arg1.obtenerSemaforoPrioridad() < 0) {
					salida = arg0.getIdPantallaRenglon().compareTo(arg1.getIdPantallaRenglon());
				}
			
			} else if (arg0.obtenerSemaforoPrioridad() > arg1.obtenerSemaforoPrioridad()) {
				salida = 1;
			} else if (arg0.obtenerSemaforoPrioridad() < arg1.obtenerSemaforoPrioridad()) {
				salida = -1;	
			} else {
				salida = arg0.getIdPantallaRenglon().compareTo(arg1.getIdPantallaRenglon());
			}	
		} else {
			return salida;
		}
		return salida;
		
	}
}
