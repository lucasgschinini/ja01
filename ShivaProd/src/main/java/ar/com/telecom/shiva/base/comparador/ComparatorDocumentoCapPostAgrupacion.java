package ar.com.telecom.shiva.base.comparador;

import java.util.Comparator;

import ar.com.telecom.shiva.presentacion.bean.dto.DocumentoCapDto;

public class ComparatorDocumentoCapPostAgrupacion implements Comparator<DocumentoCapDto> {

	@Override
	public int compare(DocumentoCapDto arg0, DocumentoCapDto arg1) {
		int salida = 0;
	
		try {
			salida = arg0.getFechaEmisionOrdenacion().compareTo(arg1.getFechaEmisionOrdenacion());
		} catch(Exception e) {
			System.out.println("222");
		}
		
		if (salida == 0) {
			salida = arg0.getIdPantalla().compareTo(arg1.getIdPantalla()); 
			if (salida == 0) {
				salida = arg0.getPosicionEnDocumento().compareTo(arg1.getPosicionEnDocumento());
			}	
		}
		return salida;
		
	}
}
