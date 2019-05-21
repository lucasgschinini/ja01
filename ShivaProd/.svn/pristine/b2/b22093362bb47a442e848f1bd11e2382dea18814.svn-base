/**
 * 
 */
package ar.com.telecom.shiva.base.comparador;

import java.text.ParseException;
import java.util.Comparator;
import java.util.Date;

import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.presentacion.bean.dto.DescobroOperacionesRelacionadasDto;

/**
 * @author u573005, fabio.giaquinta.ruiz
 * 
 * Se pasan las fechas de String a Date para facilitar la manipulacion,despues se usar el compareTo para ir acomodando la lista en orden descendente.
 * 
 * 
 */
public class ComparatorOrdenDescobroOperacionesRelacionadasDto implements Comparator<DescobroOperacionesRelacionadasDto> {

	
//	@Override
//	public int compare(DescobroTransaccionDto o1, DescobroTransaccionDto o2) {
//		if(new Long(o1.getNumeroTransaccion()).compareTo(new Long(o2.getNumeroTransaccion())) == 0){
//			
//			if(!Validaciones.isObjectNull(o1.getIdFactura()) || !Validaciones.isObjectNull(o1.getIdTratamientoDiferencia())){
//				return -1;
//			} else {
//				return 1;
//			}
//		} else {
//			return new Long(o1.getNumeroTransaccion()).compareTo(new Long(o2.getNumeroTransaccion())) * -1;
//		}
//	}

	@Override
	public int compare(DescobroOperacionesRelacionadasDto o1,
			DescobroOperacionesRelacionadasDto o2) {
			
		Date fechaPrimera = new Date();
		Date fechaSegunda = new Date();
		
		
			try {
				fechaPrimera = Utilidad.parseDateAndTimeFullString(o1.getFechaImputacion());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				fechaSegunda= Utilidad.parseDateAndTimeFullString(o2.getFechaImputacion());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		if(fechaPrimera==null && fechaSegunda!=null){
			return 1;
		}
		if(fechaSegunda==null && fechaPrimera!=null)
		{
			return -1;
		}
		if(fechaPrimera==null && fechaSegunda==null){
			return 0;
		}
		
		
		if(fechaPrimera.compareTo(fechaSegunda)==-1)
		{
			return 1;
		}
		
		if(fechaPrimera.compareTo(fechaSegunda)==1)
		{
			return -1;
		}
		return 0;
	
		
		
	}

}
