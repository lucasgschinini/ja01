/**
 * 
 */
package ar.com.telecom.shiva.base.comparador;

import java.util.Comparator;

import ar.com.telecom.shiva.presentacion.bean.dto.ClienteDto;

/**
 * @author u573005, fabio.giaquinta.ruiz
 * 
 */
public class ComparatorOrdenCobroClienteDto implements Comparator<ClienteDto> {

	@Override
	public int compare(ClienteDto o1, ClienteDto o2) {
		return new Long(o1.getIdClienteLegado()).compareTo(new Long(o2.getIdClienteLegado()));
	}

}
