/**
 * 
 */
package ar.com.telecom.shiva.base.comparador;

import java.util.Comparator;

import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.persistencia.modelo.ShvLgjChequeRechazadoDetalleCobro;

/**
 * @author Pablo M. Ibarrola
 * 
 */
public class ComparatorShvLgjChequeRechazadoDetalleCobro implements Comparator<ShvLgjChequeRechazadoDetalleCobro> {

	/**
	 * El orden de comparacion se arma de la siguiente manera:
	 * 		- Se compara unicamente el numero de transaccion
	 */
	@Override
	public int compare(ShvLgjChequeRechazadoDetalleCobro o1, ShvLgjChequeRechazadoDetalleCobro o2) {
		int retornoComparacion = 1;

		if (
			!Validaciones.isObjectNull(o1.getIdChequeRechazadoCobro()) &&
			!Validaciones.isObjectNull(o2.getIdChequeRechazadoCobro())
		) {
			retornoComparacion = o1.getIdChequeRechazadoCobro().compareTo(o2.getIdChequeRechazadoCobro());
		}
		return retornoComparacion;
	}
	

}
