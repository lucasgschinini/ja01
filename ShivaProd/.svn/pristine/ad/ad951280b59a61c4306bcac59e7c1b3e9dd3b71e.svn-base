/**
 * 
 */
package ar.com.telecom.shiva.base.comparador;

import java.util.Comparator;

import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamAcuerdo;


public class ComparatorShvParamAcuerdo implements Comparator<ShvParamAcuerdo> {

	@Override
	public int compare(ShvParamAcuerdo o1, ShvParamAcuerdo o2) {
		int retornoComparacion = 1;

		if (
			!Validaciones.isObjectNull(o1.getIdAcuerdo()) &&
			!Validaciones.isObjectNull(o2.getIdAcuerdo())
		) {
			retornoComparacion = o1.getIdAcuerdo().compareTo(o2.getIdAcuerdo());
		}
		return retornoComparacion;
	}
	

}
