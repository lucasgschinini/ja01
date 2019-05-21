/**
 * 
 */
package ar.com.telecom.shiva.base.comparador;

import java.util.Comparator;

import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdOtrosMedioPago;

/**
 * @author Pablo M. Ibarrola
 * 
 */
public class ComparatorOrdenGuardadoShvCobEdOtrosMedioPago implements Comparator<ShvCobEdOtrosMedioPago> {

	@Override
	public int compare(ShvCobEdOtrosMedioPago o1, ShvCobEdOtrosMedioPago o2) {

		int retornoComparacion = 0;
		
		String idOtrosMedioPagoReferencia1 = 
				String.valueOf(o1.getTipoMedioPago().getIdTipoMedioPago()) + 
				String.valueOf(o1.getImporte()) + 
				String.valueOf(o1.getNroActa()) + 
				String.valueOf(o1.getFecha());

		String idOtrosMedioPagoReferencia2 =  
				String.valueOf(o2.getTipoMedioPago().getIdTipoMedioPago()) + 
				String.valueOf(o2.getImporte()) + 
				String.valueOf(o2.getNroActa()) + 
				String.valueOf(o2.getFecha());
		
		// Realizo una comparacion y ordeno por id credito referencia
		retornoComparacion = idOtrosMedioPagoReferencia1.compareTo(idOtrosMedioPagoReferencia2);
		
		return retornoComparacion;
	}
	

}
