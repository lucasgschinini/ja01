/**
 * 
 */
package ar.com.telecom.shiva.base.comparador;

import java.util.Comparator;

import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPago;

/**
 * @author Pablo M. Ibarrola
 * 
 */
public class ComparatorOrdenSimulacionReintegroShvCobMedioPago implements Comparator<ShvCobMedioPago> {

	/**
	 * El orden de aplicaci�n de los medios de pago sobre reintegros ser� el siguiente:
	 * - Si dentro de la operaci�n existe un reintegro de dinero al cliente, antes de aplicar 
	 *   sobre deuda se debe descontar este monto del medio de pago con fecha valor m�s nueva habilitado para este caso.		
	 *
	 */
	@Override
	public int compare(ShvCobMedioPago o1, ShvCobMedioPago o2) {

		int retornoComparacion = 0;
		retornoComparacion = o2.getFechaValor().compareTo(o1.getFechaValor());
		return retornoComparacion;	
	}
}
