/**
 * 
 */
package ar.com.telecom.shiva.base.comparador;

import java.util.Comparator;

import ar.com.telecom.shiva.persistencia.modelo.ShvSegPerfilAplicativo;


/**
 * @author Agustina Streule
 * 
 */
public class ComparatorOrdenShvSegPerfilAplicativo implements Comparator<ShvSegPerfilAplicativo> {

	@Override
	public int compare(ShvSegPerfilAplicativo o1, ShvSegPerfilAplicativo o2) {

		return new Long(o1.getIdPerfilAplicativo()).compareTo(new Long(o2.getIdPerfilAplicativo()));
	}

}
