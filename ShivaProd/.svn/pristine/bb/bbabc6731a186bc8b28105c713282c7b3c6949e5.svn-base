/**
 * 
 */
package ar.com.telecom.shiva.base.comparador;

import java.util.Comparator;

import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamBancoCuenta;


public class ComparatorShvParamBancoCuenta implements Comparator<ShvParamBancoCuenta> {

	@Override
	public int compare(ShvParamBancoCuenta o1, ShvParamBancoCuenta o2) {
		int retornoComparacion = 1;

		if (
			!Validaciones.isObjectNull(o1.getCuentaBancaria()) &&
			!Validaciones.isObjectNull(o2.getCuentaBancaria())
		) {
			retornoComparacion = o1.getCuentaBancaria().compareTo(o2.getCuentaBancaria());
		}
		return retornoComparacion;
	}
	

}
