/**
 * 
 */
package ar.com.telecom.shiva.base.mapeadores;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusqueda;

/**
 * @author pablo.m.ibarrola
 *
 */
public abstract class MapeadorResultadoBusqueda {

	/**
	 * 
	 * @param dto
	 * @param vistaSoporteResultadoBusqueda
	 * @return
	 * @throws NegocioExcepcion
	 * @throws ParseException 
	 */
	public abstract DTO map (VistaSoporteResultadoBusqueda vistaSoporteResultadoBusqueda) throws NegocioExcepcion;
	
	
	/**
	 * Para pasar colecciones (vistaSoporteResultadoBusqueda a DTO)
	 * @param sourceList
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Collection map(Collection sourceLista) 
			throws NegocioExcepcion {
		Collection newCol;
		
		if (sourceLista instanceof Set) {
			newCol = new HashSet();
		} else {
			newCol = new ArrayList();
		}
		
		for (Object obj: sourceLista) {
			DTO dto = map((VistaSoporteResultadoBusqueda) obj);
			newCol.add(dto);
		}
		
		return newCol;
	}
}
