/**
 * 
 */
package ar.com.telecom.shiva.base.comparador;

import java.util.Comparator;

import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.persistencia.modelo.ShvLgjChequeRechazadoDetalleCliente;

/**
 * @author Pablo M. Ibarrola
 * 
 */
public class ComparatorShvLgjChequeRechazadoDetalleCliente implements Comparator<ShvLgjChequeRechazadoDetalleCliente> {

	/**
	 * El orden de comparacion se arma de la siguiente manera:
	 * 		- Se compara unicamente el numero de id cliente legado
	 */
	@Override
	public int compare(ShvLgjChequeRechazadoDetalleCliente o1, ShvLgjChequeRechazadoDetalleCliente o2) {

		int retornoComparacion = 1;
		
		if (!Validaciones.isObjectNull(o1.getIdClienteLegado()) && !Validaciones.isObjectNull(o2.getIdClienteLegado())) {
			retornoComparacion = o1.getIdClienteLegado().compareTo(o2.getIdClienteLegado());
		}

		return retornoComparacion;
	}
	

}
