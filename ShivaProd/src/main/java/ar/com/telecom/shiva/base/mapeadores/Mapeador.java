package ar.com.telecom.shiva.base.mapeadores;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;

public abstract class Mapeador {

	/**
	 * Modelo --> DTO
	 * @param modelo
	 * @return
	 */
	public abstract DTO map(Modelo vo) throws NegocioExcepcion;
	
	
	/**
	 * Modelo --> DTO 
	 * @param WSDTO
	 * @return
	 */
	public abstract Modelo map(DTO dto, Modelo vo) throws NegocioExcepcion;
		
	/**
	 * Para pasar colecciones (Modelo a DTO) or (DTO a Modelo)
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
				Modelo modelo = map((DTO) obj, null);
				newCol.add(modelo);
			} else if (obj instanceof Modelo) {
				DTO dto = map((Modelo) obj);
				newCol.add(dto);
			}
		}
		
		return newCol;
	}
	
	/**
	 * Para pasar colecciones del DTO al Modelo
	 * @param dtoList
	 * @param modeloList
	 * @return
	 */
	public Collection<Modelo> map(Collection<DTO> dtoLista, Collection<Modelo> modeloLista) 
			throws NegocioExcepcion {
		Collection<Modelo> savedCollection;
		if (modeloLista instanceof Set) {
			savedCollection = new HashSet<Modelo>();
		} else {
			savedCollection = new ArrayList<Modelo>();
		}
		modeloLista.clear();
		
		for (Modelo savedModelo: savedCollection) {

			for (DTO dto: dtoLista) {
				if (dto.getId()!=null
						&& dto.getId().equals(savedModelo.getId())) {
					Modelo modelo = map(dto, savedModelo);
					modeloLista.add(modelo);
					break;
				}
			}
		}
		
		for (DTO dto: dtoLista) {
			if (dto.getId() != null) {
				Modelo newModelo = map(dto,null);
				modeloLista.add(newModelo);
			}
		}
		
		return modeloLista;
	}
}
