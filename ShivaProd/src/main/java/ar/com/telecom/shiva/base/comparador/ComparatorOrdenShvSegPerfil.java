/**
 * 
 */
package ar.com.telecom.shiva.base.comparador;

import java.util.Comparator;

import ar.com.telecom.shiva.persistencia.modelo.ShvSegPerfil;


/**
 * @author Agustina Streule
 * 
 */
public class ComparatorOrdenShvSegPerfil implements Comparator<ShvSegPerfil> {

	@Override
	public int compare(ShvSegPerfil o1, ShvSegPerfil o2) {

		return o1.getIdPerfil().compareTo(o2.getIdPerfil());
	}
	

}
