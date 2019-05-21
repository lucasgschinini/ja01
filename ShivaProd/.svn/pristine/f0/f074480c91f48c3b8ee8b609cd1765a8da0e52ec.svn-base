package ar.com.telecom.shiva.base.comparador;

import java.util.Comparator;

import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaDescobroTransaccion;

/**
 * @author u573005, fabio.giaquinta.ruiz
 * 
 */
public class ComparatorOrdenVistaTransaccionInvertido implements Comparator<VistaSoporteResultadoBusquedaDescobroTransaccion> {

	/**
	 * El orden de comparacion se arma de la siguiente manera:
	 * 		- Se compara el numero de transaccion y si coinciden se pregunta si tiene idFactura o idTratamiento diferencia, que pesa mas que
	 * 		el idMedioPago, entonces la factura tiene que salir despues ya que el orden es invertido al normal
	 */
	@Override
	public int compare(VistaSoporteResultadoBusquedaDescobroTransaccion o1, VistaSoporteResultadoBusquedaDescobroTransaccion o2) {
		if(o1.getNumeroTransaccion().compareTo(o2.getNumeroTransaccion()) == 0){
			
			if(o1.getNumeroTransaccionOriginal().compareTo(o2.getNumeroTransaccionOriginal()) == 0){
				
				if(!Validaciones.isObjectNull(o1.getIdFactura()) || !Validaciones.isObjectNull(o1.getIdTratamientoDiferencia())){
					return -1;
				} else {
					if(!Validaciones.isObjectNull(o1.getIdMedioPago()) && !Validaciones.isObjectNull(o2.getIdMedioPago())){
						return o1.getIdMedioPago().compareTo(o2.getIdMedioPago());
					} else if(!Validaciones.isObjectNull(o1.getIdMedioPago()) && Validaciones.isObjectNull(o2.getIdMedioPago())){
						return 1;
					} else if(Validaciones.isObjectNull(o1.getIdMedioPago()) && !Validaciones.isObjectNull(o2.getIdMedioPago())){
						return -1;
					} else {
						return 0;
					}					
				}
			} else {
				return o1.getNumeroTransaccionOriginal().compareTo(o2.getNumeroTransaccionOriginal());
			}
		} else {
			return o1.getNumeroTransaccion().compareTo(o2.getNumeroTransaccion()) * -1;
		}
	}

}
