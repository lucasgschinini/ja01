/**
 * 
 */
package ar.com.telecom.shiva.base.comparador;

import java.util.Comparator;

import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroTransaccionDto;

/**
 * @author u573005, fabio.giaquinta.ruiz
 * 
 */
public class ComparatorOrdenModificacionCobroTransaccionDto implements Comparator<CobroTransaccionDto> {

	/**
	 * El orden de comparacion se arma de la siguiente manera:
	 * 		- Se compara el numero de transaccion y si coinciden se pregunta si tiene idFactura o idTratamiento diferencia, que pesa mas que
	 * 		el idMedioPago, entonces la factura tiene que salir primero
	 */
	@Override
	public int compare(CobroTransaccionDto o1, CobroTransaccionDto o2) {
		if(new Integer(o1.getNumeroTransaccion()).compareTo(new Integer(o2.getNumeroTransaccion())) == 0){
			if(new Integer(o1.getNumeroTransaccionOriginal()).compareTo(new Integer(o2.getNumeroTransaccionOriginal())) == 0){
				if(!Validaciones.isNullEmptyOrDash(o1.getIdFactura()) || !Validaciones.isNullEmptyOrDash(o1.getIdTratamientoDiferencia())){
					return -1;
				} else {
					return 1;
				}
			} else {
				return new Integer(o1.getNumeroTransaccionOriginal()).compareTo(new Integer(o2.getNumeroTransaccionOriginal()));
			}
		} else {
			return new Integer(o1.getNumeroTransaccion()).compareTo(new Integer(o2.getNumeroTransaccion()));
		}
	}

}
