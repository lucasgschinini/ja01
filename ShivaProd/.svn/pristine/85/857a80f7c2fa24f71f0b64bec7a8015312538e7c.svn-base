package ar.com.telecom.shiva.base.mapeadores;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.dto.SOA;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;

public abstract class MapeadorSOA {

	/**
	 * SOA (JMS/WS) --> DTO
	 * @param SOA
	 * @return
	 */
	public abstract DTO map(SOA vo) throws NegocioExcepcion;
	
	
	/**
	 * DTO --> SOA (JMS/WS) 
	 * @param DTO
	 * @return
	 */
	public abstract SOA map(DTO dto) throws NegocioExcepcion;
		
	/**
	 * Para pasar colecciones (SOA a DTO) or (DTO a SOA)
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
			if (obj instanceof DTO) {
				SOA modelo = map((DTO) obj);
				newCol.add(modelo);
			} else if (obj instanceof SOA) {
				DTO dto = map((SOA) obj);
				newCol.add(dto);
			}
		}
		
		return newCol;
	}
}
