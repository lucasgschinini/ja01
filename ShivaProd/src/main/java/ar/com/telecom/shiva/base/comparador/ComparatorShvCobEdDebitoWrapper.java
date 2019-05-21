package ar.com.telecom.shiva.base.comparador;

import java.io.Serializable;
import java.util.Comparator;
import ar.com.telecom.shiva.negocio.simulacionCoherencia.ShvCobEdDebitoWrapper;

/**
 * 
 * @author u578936 Max Uehara
 */
public class ComparatorShvCobEdDebitoWrapper implements Comparator<ShvCobEdDebitoWrapper>, Serializable {
	private static final long serialVersionUID = -8021216859809932681L;

	/**
	 *	El atributo de comparacion es el idPantalla es el mismo usado en la vista.
	 *
	 */
	@Override
	public int compare(ShvCobEdDebitoWrapper o1, ShvCobEdDebitoWrapper o2) {
		return o1.getIdPantalla().compareTo(o2.getIdPantalla());
	}
}
