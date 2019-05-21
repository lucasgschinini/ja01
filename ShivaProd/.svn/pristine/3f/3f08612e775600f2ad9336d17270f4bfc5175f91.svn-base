package ar.com.telecom.shiva.base.comparador;

import java.util.Comparator;

import ar.com.telecom.shiva.presentacion.bean.dto.CodigoOperacionExternaDto;

/**
 * @author u578936 M.A.Uehara
 *
 */
public class ComparatorCodigoOperacionExternaDto implements Comparator<CodigoOperacionExternaDto> {

	@Override
	public int compare(CodigoOperacionExternaDto o1, CodigoOperacionExternaDto o2) {
		
		String idCodigoOperacionExterna1 = 
				String.valueOf(o1.getNroTransaccion()) + 
				String.valueOf(o1.getSistema()) + 
				String.valueOf(o1.getCodigoOperacionExterna()) + 
				String.valueOf(o1.getReferencia());
		
		String idCodigoOperacionExterna2 = 
				String.valueOf(o2.getNroTransaccion()) + 
				String.valueOf(o2.getSistema()) + 
				String.valueOf(o2.getCodigoOperacionExterna()) + 
				String.valueOf(o2.getReferencia());
		
		// Realizo una comparacion y ordeno por numero de transaccion, sistema, codigoOperacionExterna y referencia
		return idCodigoOperacionExterna1.compareTo(idCodigoOperacionExterna2);
	}

}
