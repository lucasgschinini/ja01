/**
 * 
 */
package ar.com.telecom.shiva.base.comparador;

import java.util.Comparator;

import ar.com.telecom.shiva.persistencia.modelo.ShvLgjNotificacion;

/**
 * @author u578936 M.A.Uehara
 *
 */
public class ComparatorShvLgjNotificacion implements Comparator<ShvLgjNotificacion> {
	@Override
	public int compare(ShvLgjNotificacion o1, ShvLgjNotificacion o2) {
		int retornoComparacion = 0;

		retornoComparacion = o1.getFechaContacto().compareTo(o2.getFechaContacto());
		if (retornoComparacion == 0) {
			retornoComparacion = o1.getIdNotificacion().compareTo(o2.getIdNotificacion());
			
		}
		if (retornoComparacion != 0) {
			retornoComparacion = retornoComparacion * (-1);
		}
		return retornoComparacion;
	}
}
