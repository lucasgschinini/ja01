package ar.com.telecom.shiva.base.comparador;

import java.io.Serializable;
import java.util.Comparator;

import ar.com.telecom.shiva.negocio.simulacionCoherencia.ShvCobEdOtroDebitoWrapper;

public class ComparatorShvCobEdOtroDebitoWrapper implements Comparator<ShvCobEdOtroDebitoWrapper>, Serializable {
	private static final long serialVersionUID = -8021216859809932681L;

	/**
	 *	El atributo de comparacion es el idPantalla es el mismo usado en la vista.
	 *
	 */
	@Override
	public int compare(ShvCobEdOtroDebitoWrapper o1, ShvCobEdOtroDebitoWrapper o2) {
		return o1.getIdPantalla().compareTo(o2.getIdPantalla());
	}
}
