package ar.com.telecom.shiva.base.mapeadores;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;

public abstract class MapeadorWS {

	/**
	 * DTO --> Object ws
	 * @param modelo
	 * @return
	 */
	public abstract Object map(DTO dto) throws NegocioExcepcion;
	
	
	/**
	 * Object ws --> DTO 
	 * @param object
	 * @return
	 */
	public abstract DTO map(Object obj) throws NegocioExcepcion;
		
	/**
	 * Para pasar colecciones (DTO a ws) or (ws a DTO)
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
			if (obj instanceof Object) {
				DTO DTO = map((Object) obj);
				newCol.add(DTO);
			} else if (obj instanceof DTO) {
				Object dto = map((DTO) obj);
				newCol.add(dto);
			}
		}
		
		return newCol;
	}
	
}
