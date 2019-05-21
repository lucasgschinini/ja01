/**
 * 
 */
package ar.com.telecom.shiva.base.comparador;

import java.util.Comparator;

import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamBanco;


public class ComparatorShvParamBanco implements Comparator<ShvParamBanco> {

	@Override
	public int compare(ShvParamBanco o1, ShvParamBanco o2) {
		int retornoComparacion = 1;

		if (
			!Validaciones.isObjectNull(o1.getIdBanco()) &&
			!Validaciones.isObjectNull(o2.getIdBanco())
		) {
			retornoComparacion = o1.getIdBanco().compareTo(o2.getIdBanco());
		}
		return retornoComparacion;
	}
	

}
