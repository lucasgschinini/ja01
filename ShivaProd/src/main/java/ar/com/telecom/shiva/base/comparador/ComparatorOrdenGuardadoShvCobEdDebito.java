/**
 * 
 */
package ar.com.telecom.shiva.base.comparador;

import java.util.Comparator;

import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdDebito;

/**
 * @author Pablo M. Ibarrola
 * 
 */
public class ComparatorOrdenGuardadoShvCobEdDebito implements Comparator<ShvCobEdDebito> {

	@Override
	public int compare(ShvCobEdDebito o1, ShvCobEdDebito o2) {

		int retornoComparacion = 0;

		// Realizo una comparacion y ordeno por id debito referencia
		if (!Validaciones.isObjectNull(o1.getIdDebitoReferencia()) && !Validaciones.isObjectNull(o2.getIdDebitoReferencia())) {
			retornoComparacion = o1.getIdDebitoReferencia().compareTo(o2.getIdDebitoReferencia());
			if (retornoComparacion == 0) {
				retornoComparacion = -1;
			}
		} else {
			retornoComparacion = -1;
		}

		return retornoComparacion;
	}
	

}
