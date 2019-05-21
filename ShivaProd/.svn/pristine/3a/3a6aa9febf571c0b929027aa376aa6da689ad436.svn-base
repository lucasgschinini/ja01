package ar.com.telecom.shiva.base.comparador;

import java.util.Calendar;
import java.util.Comparator;
import java.util.GregorianCalendar;

import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.negocio.batch.bean.TagetikBatch;


public class ComparatorTagetikBatch implements Comparator<TagetikBatch> {
	public ComparatorTagetikBatch() {
	}

	@Override
	public int compare(TagetikBatch o1, TagetikBatch o2) {
		int comparacion = 0;

		comparacion = o1.getIdOperacion().compareTo(o2.getIdOperacion());
		if (comparacion == 0) {
			if (
				Validaciones.isNullEmptyOrDash(o1.getIdCuentaCobPadre()) &&
				Validaciones.isNullEmptyOrDash(o2.getIdCuentaCobPadre())
			) {
				comparacion = this.comparar(o1, o2);
			} else if (
				!Validaciones.isNullEmptyOrDash(o1.getIdCuentaCobPadre()) &&
				!Validaciones.isNullEmptyOrDash(o2.getIdCuentaCobPadre())
			) {
				comparacion = o1.getIdCuentaCobPadre().compareTo(o2.getIdCuentaCobPadre());
				if (comparacion == 0) {
					comparacion = this.comparar(o1, o2);
				}
			} else if (!Validaciones.isNullEmptyOrDash(o1.getIdCuentaCobPadre())) {
				comparacion = 1;
			} else {
				comparacion = -1;
			}
		}
		return comparacion;
	}
	
	private int comparar(TagetikBatch o1, TagetikBatch o2) {
		int comparacion = 0;
		comparacion = o1.getIdCuentaCob().compareTo(o2.getIdCuentaCob());
		if (comparacion == 0) {
			comparacion = o1.getTipoComprobante().compareTo(o2.getTipoComprobante());
			if (comparacion == 0) {
				comparacion = this.compararFecha(o1, o2);
				if (comparacion == 0) {
					comparacion = o1.getMoneda().compareTo(o2.getMoneda());	
				}
			}
		}
		return comparacion;
	}
	private int compararFecha(TagetikBatch o1, TagetikBatch o2) {
		Calendar date1 = GregorianCalendar.getInstance();
		Calendar date2 = GregorianCalendar.getInstance();

		date1.setTimeInMillis(o1.getFechaValor().getTime());
		date2.setTimeInMillis(o2.getFechaValor().getTime());

		date1.set(Calendar.HOUR, 0);
		date2.set(Calendar.HOUR, 0);
		date1.set(Calendar.MINUTE, 0);
		date2.set(Calendar.MINUTE, 0);
		date1.set(Calendar.SECOND, 0);
		date2.set(Calendar.SECOND, 0);
		date1.set(Calendar.MILLISECOND, 0);
		date2.set(Calendar.MILLISECOND, 0);
		
		return date1.compareTo(date2);
		
	}
//	private int compararNumeroLegal(TagetikBatch o1, TagetikBatch o2) {
//		int comparacion = 0;
//	
//		comparacion = o1.getClaseComprobante().compareTo(o2.getClaseComprobante());
//		if (comparacion == 0) {
//			comparacion = o1.getSucComprobante().compareTo(o2.getSucComprobante());
//			if (comparacion == 0) {
//				comparacion = o1.getNumComprobante().compareTo(o2.getNumComprobante());
//			}
//		}
//		return comparacion;
//	}

}