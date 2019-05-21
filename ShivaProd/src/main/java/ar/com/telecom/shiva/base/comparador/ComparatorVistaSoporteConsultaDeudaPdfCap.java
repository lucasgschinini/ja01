package ar.com.telecom.shiva.base.comparador;

import java.util.Comparator;

import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteConsultaDeudaPdfCap;

/**
 * @author u586743
 * @param <VistaSoporteConsultaDeudaPdfCap>
 * @param <VistaSoporteConsultaPdfCap>
 *
 */
public class ComparatorVistaSoporteConsultaDeudaPdfCap implements Comparator<VistaSoporteConsultaDeudaPdfCap> {

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(VistaSoporteConsultaDeudaPdfCap o1, VistaSoporteConsultaDeudaPdfCap o2) {

		if (!o1.getSistema().getDescripcionCorta().equals(o2.getSistema().getDescripcionCorta())) {
			if (o1.getSistema().getDescripcionCorta().equals(SistemaEnum.CALIPSO.getDescripcionCorta())) {
				return 1;
			}else {
				return -1;
			}
		}else {
			if(Validaciones.isObjectNull(o1.getFechaDateVto()) || Validaciones.isObjectNull(o2.getFechaDateVto())){
				return 0;
			}			
		else if(!Validaciones.isObjectNull(o1.getFechaDateVto())){
				return o1.getFechaDateVto().compareTo(o2.getFechaDateVto());
			}else if(Validaciones.isObjectNull(o1.getFechaDateVto()) && !Validaciones.isObjectNull(o2.getFechaDateVto())){
				return -1;
		}else {
			if(!Validaciones.isObjectNull(o1.getNroDeComprobante())){
				return o1.getNroDeComprobante().compareTo(o2.getNroDeComprobante());
			}else if(Validaciones.isObjectNull(o1.getNroDeComprobante()) && !Validaciones.isObjectNull(o2.getNroDeComprobante())){
					return -1;
//				if (0 != o1.getNroDeComprobante().compareToIgnoreCase(o2.getNroDeComprobante())){
//					return o1.getNroDeComprobante().compareToIgnoreCase(o2.getNroDeComprobante());
			}else {
					return  o2.getSaldo().compareTo(o1.getSaldo());
				}
			}

		}
		
	}





}
