package ar.com.telecom.shiva.base.comparador;

import java.io.Serializable;
import java.util.Comparator;
import ar.com.telecom.shiva.negocio.simulacionCoherencia.ShvCobEdCreditoWrapper;
/**
 * 
 * @author u578936 Max Uehara
 */
public class ComparatorShvCobEdCreditoWrapper implements Comparator<ShvCobEdCreditoWrapper>, Serializable {
	private static final long serialVersionUID = 8398371578249576177L;

	/**
	 *	El atributo de comparacion es el idPantalla es el mismo usado en la vista.
	 *
	 */
	@Override
	public int compare(ShvCobEdCreditoWrapper o1, ShvCobEdCreditoWrapper o2) {
		return o1.getIdPantalla().compareTo(o2.getIdPantalla());
	}
}
