package ar.com.telecom.shiva.base.comparador;

import java.util.Comparator;

import ar.com.telecom.shiva.presentacion.bean.dto.DescobroDocumentoRelacionadoDto;

/**
 * @author u573005, fabio.giaquinta.ruiz
 * 
 */
public class ComparatorOrdenDescobroDocumentosRelacionadosDto implements Comparator<DescobroDocumentoRelacionadoDto> {
	
	@Override
	public int compare(DescobroDocumentoRelacionadoDto o1, DescobroDocumentoRelacionadoDto o2) {
		return new Long(o1.getNroTransaccion()).compareTo(new Long(o2.getNroTransaccion()));
	}

}
