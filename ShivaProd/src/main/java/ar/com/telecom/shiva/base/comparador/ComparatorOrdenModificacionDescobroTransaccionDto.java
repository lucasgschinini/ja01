package ar.com.telecom.shiva.base.comparador;

import java.util.Comparator;

import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.presentacion.bean.dto.DescobroTransaccionDto;

public class ComparatorOrdenModificacionDescobroTransaccionDto implements Comparator<DescobroTransaccionDto> {

	
	/**
	 * El orden de comparacion se arma de la siguiente manera:
	 * 		- Se compara el numero de transaccion y si coinciden se pregunta si tiene idFactura o idTratamiento diferencia, que pesa mas que
	 * 		el idMedioPago, entonces la factura tiene que salir primero
	 */
	@Override
	public int compare(DescobroTransaccionDto o1, DescobroTransaccionDto o2) {
		if(new Integer(o1.getNumeroGrupo()).compareTo(new Integer(o2.getNumeroGrupo())) == 0){
			if(new Integer(o1.getNumeroTransaccion()).compareTo(new Integer(o2.getNumeroTransaccion())) == 0){
				if(!Validaciones.isNullEmptyOrDash(o1.getIdFactura()) || !Validaciones.isNullEmptyOrDash(o1.getIdTratamientoDiferencia())){
					return -1;
				} else {
					if(!Validaciones.isNullEmptyOrDash(o1.getIdMedioPago()) && !Validaciones.isNullEmptyOrDash(o2.getIdMedioPago())){
						return new Long(o1.getIdMedioPago()).compareTo(new Long(o2.getIdMedioPago()));
					} else if(!Validaciones.isNullEmptyOrDash(o1.getIdMedioPago()) && Validaciones.isNullEmptyOrDash(o2.getIdMedioPago())){
						return 1;
					} else if(Validaciones.isNullEmptyOrDash(o1.getIdMedioPago()) && !Validaciones.isNullEmptyOrDash(o2.getIdMedioPago())){
						return -1;
					} else {
						return 0;
					}				
				}
			} else {
				return new Integer(o1.getNumeroTransaccion()).compareTo(new Integer(o2.getNumeroTransaccion()));
			}
		} else {
			return new Integer(o1.getNumeroGrupo()).compareTo(new Integer(o2.getNumeroGrupo()));
		}
	}
}
