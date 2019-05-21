/**
 * 
 */
package ar.com.telecom.shiva.base.comparador;

import java.util.Comparator;

import ar.com.telecom.shiva.persistencia.bean.VistaSoporteConsultaCapPdfCap;

/**
 * @author u586743
 *
 */
public class ComparatorVistaSoporteConsultaCapPdfCap implements Comparator<VistaSoporteConsultaCapPdfCap>{

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(VistaSoporteConsultaCapPdfCap o1,VistaSoporteConsultaCapPdfCap o2) {
		
		if (o1.getFechaEmision().compareTo(o2.getFechaEmision()) != 0) {
			return o2.getFechaEmision().compareTo(o1.getFechaEmision());
		}else {

			return	o1.getNumeroDocumentoSap().compareToIgnoreCase(o2.getNumeroDocumentoSap());
		}
		
	
	}

}
