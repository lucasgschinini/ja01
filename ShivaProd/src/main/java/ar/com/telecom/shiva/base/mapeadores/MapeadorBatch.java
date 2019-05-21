package ar.com.telecom.shiva.base.mapeadores;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import ar.com.telecom.shiva.base.dto.Batch;
import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;


public abstract class MapeadorBatch {

	/**
	 * DTO --> Batch
	 * @param DTO
	 * @return
	 */
	public abstract Batch map(DTO vo) throws NegocioExcepcion;
	
	
	/**
	 * Batch --> DTO 
	 * @param Batch
	 * @return
	 */
	public abstract DTO map(Batch dto) throws NegocioExcepcion;
		
	/**
	 * Para pasar colecciones (Batch a DTO) or (DTO a Dto)
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
			if (obj instanceof Batch) {
				DTO dto = map((Batch) obj);
				newCol.add(dto);
			} else if (obj instanceof DTO) {
				Batch batch = map((DTO) obj);
				newCol.add(batch);
			}
		}
		
		return newCol;
	}
}
