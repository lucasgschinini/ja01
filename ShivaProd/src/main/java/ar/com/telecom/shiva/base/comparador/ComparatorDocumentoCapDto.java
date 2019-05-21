package ar.com.telecom.shiva.base.comparador;

import java.util.Comparator;

import ar.com.telecom.shiva.presentacion.bean.dto.DocumentoCapDto;

public class ComparatorDocumentoCapDto implements Comparator<DocumentoCapDto> {

	@Override
	public int compare(DocumentoCapDto arg0, DocumentoCapDto arg1) {
		int salida = 0;
		
		salida = arg0.getIdPantalla().compareTo(arg1.getIdPantalla());
		if (salida == 0) {
			if (arg0.obtenerSemaforoPrioridad() > arg1.obtenerSemaforoPrioridad()) {
				salida = -1;
			} else if (arg0.obtenerSemaforoPrioridad() > arg1.obtenerSemaforoPrioridad()) {
				salida = 2;
			} else {
				salida = arg0.getIdPantallaRenglon().compareTo(arg1.getIdPantallaRenglon());
				if (salida == 0) {
					salida = arg0.getPosicionSap().compareTo(arg1.getPosicionSap());
					if (salida == 0) {
						salida = arg0.obtenerTipoRenglon().compareTo(arg1.obtenerTipoRenglon());	
					}
				}
			}
		} else {
			return salida;
		}
		return salida;
	}
}
