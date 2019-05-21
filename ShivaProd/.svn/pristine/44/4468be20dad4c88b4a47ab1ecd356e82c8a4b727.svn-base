/**
 * 
 */
package ar.com.telecom.shiva.base.comparador;

import java.util.Comparator;

import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobFactura;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobTransaccion;

/**
 * @author u573005, fabio.giaquinta.ruiz
 * 
 */
public class ComparatorOrdenModificacionShvCobTransaccionInvertido implements Comparator<ShvCobTransaccion> {

	/**
	 * El orden de comparacion se arma de la siguiente manera:
	 * 		- Se compara el numero de transaccion y si coinciden se pregunta si tiene idFactura o idTratamiento diferencia, que pesa mas que
	 * 		el idMedioPago, entonces la factura tiene que salir despues ya que el orden es invertido al normal
	 */
	@Override
	public int compare(ShvCobTransaccion o1, ShvCobTransaccion o2) {
		if(new Integer(o1.getNumeroTransaccion()).compareTo(new Integer(o2.getNumeroTransaccion())) == 0){
			ShvCobFactura factura = o1.getFactura();
			if((!Validaciones.isObjectNull(factura) && !Validaciones.isObjectNull(factura.getIdFactura())) || !Validaciones.isObjectNull(o1.getTratamientoDiferencia().getIdTratamientoDiferencia())){
				return 1;
			} else {
				return -1;
			}
		} else {
			return new Integer(o1.getNumeroTransaccion()).compareTo(new Integer(o2.getNumeroTransaccion())) * -1;
		}
	}

}
