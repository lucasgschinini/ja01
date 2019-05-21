/**
 * 
 */
package ar.com.telecom.shiva.base.comparador;

import java.util.Comparator;

import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdOtrosDebito;

/**
 * 
 * @author U587496 Guido.Settecerze
 *
 */
public class ComparatorOrdenGuardadoShvCobEdOtrosDebito implements Comparator<ShvCobEdOtrosDebito> {
	@Override
	public int compare(ShvCobEdOtrosDebito o1, ShvCobEdOtrosDebito o2) {
		int retornoComparacion = 0;

		// Realizo una comparacion y ordeno por id debito referencia
		if (!Validaciones.isObjectNull(o1.getIdOtrosDebitosReferencia()) && !Validaciones.isObjectNull(o2.getIdOtrosDebitosReferencia())) {
			retornoComparacion = o1.getIdOtrosDebitosReferencia().compareTo(o2.getIdOtrosDebitosReferencia());
			if (retornoComparacion == 0) {
				retornoComparacion = -1;
			}
		} else {
			retornoComparacion = -1;
		}

		return retornoComparacion;
	}
}
