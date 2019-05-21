/**
 * 
 */
package ar.com.telecom.shiva.base.comparador;

import java.util.Comparator;

import ar.com.telecom.shiva.persistencia.modelo.ShvCobTransaccion;

/**
 * @author Pablo M. Ibarrola
 * 
 */
public class ComparatorOrdenSimulacionShvCobTransaccion implements Comparator<ShvCobTransaccion> {

	/**
	 * El orden de comparacion se arma de la siguiente manera:
	 * 		- Se compara el grupo primero y luego el numero de transaccion
	 */
	@Override
	public int compare(ShvCobTransaccion o1, ShvCobTransaccion o2) {
		int retornoComparacion = o1.getGrupo().compareTo(o2.getGrupo());
		if(retornoComparacion==0){
			 retornoComparacion = o1.getNumeroTransaccion().compareTo(o2.getNumeroTransaccion());
		}
		return retornoComparacion;
	}
	

}
