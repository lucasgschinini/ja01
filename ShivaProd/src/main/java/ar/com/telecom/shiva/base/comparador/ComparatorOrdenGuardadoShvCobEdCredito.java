/**
 * 
 */
package ar.com.telecom.shiva.base.comparador;

import java.util.Comparator;

import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdCredito;

/**
 * @author Pablo M. Ibarrola
 * 
 */
public class ComparatorOrdenGuardadoShvCobEdCredito implements Comparator<ShvCobEdCredito> {

	@Override
	public int compare(ShvCobEdCredito o1, ShvCobEdCredito o2) {

		int retornoComparacion = 0;
		
		// Realizo una comparacion y ordeno por id credito referencia
		if (!Validaciones.isObjectNull(o1.getIdCreditoReferencia()) && !Validaciones.isObjectNull(o2.getIdCreditoReferencia())) {
			retornoComparacion = o1.getIdCreditoReferencia().compareTo(o2.getIdCreditoReferencia());
			if (retornoComparacion == 0) {
				retornoComparacion = -1;
			}
		} else {
			retornoComparacion = -1;
		}
		
		return retornoComparacion;
	}
	

}
