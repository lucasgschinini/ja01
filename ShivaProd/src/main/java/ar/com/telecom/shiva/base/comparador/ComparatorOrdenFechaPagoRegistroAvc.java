/**
 * 
 */
package ar.com.telecom.shiva.base.comparador;

import java.util.Comparator;

import ar.com.telecom.shiva.persistencia.bean.VistaSoporteMotorConciliacion;


/**
 * @author Pablo M. Ibarrola
 * 
 */
public class ComparatorOrdenFechaPagoRegistroAvc implements Comparator<VistaSoporteMotorConciliacion> {

	@Override
	public int compare(VistaSoporteMotorConciliacion o1, VistaSoporteMotorConciliacion o2) {

		return o1.getRavcFechaDePago().compareTo(o2.getRavcFechaDePago());
	}
	

}
