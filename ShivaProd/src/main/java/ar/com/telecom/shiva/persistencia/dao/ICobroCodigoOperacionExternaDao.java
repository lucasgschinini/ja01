package ar.com.telecom.shiva.persistencia.dao;

import java.util.List;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdCodigoOperacionExterna;

public interface ICobroCodigoOperacionExternaDao {

	/**
	 * 
	 * @param idCobro
	 * @return
	 * @throws NegocioExcepcion
	 */
	List<ShvCobEdCodigoOperacionExterna> listarCodigosOperacionesExternasPorIdCobro(Long idCobro) throws NegocioExcepcion;
	/**
	 * 
	 * @param codigoOperacionExterna
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	ShvCobEdCodigoOperacionExterna insertarCodigoOperacioneExterna(ShvCobEdCodigoOperacionExterna codigoOperacionExterna) throws PersistenciaExcepcion;
	/**
	 * 
	 * @param idCobro
	 * @param numGrupo
	 * @return
	 * @throws NegocioExcepcion
	 */
	public List<ShvCobEdCodigoOperacionExterna> listarCodigosOperacionesExternas(Long idCobro, Long numGrupo) throws NegocioExcepcion;
}
