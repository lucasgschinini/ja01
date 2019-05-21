/**
 * 
 */
package ar.com.telecom.shiva.persistencia.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.dao.ICobroCodigoOperacionExternaSimplificadoDao;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvCobEdCodigoOperacionExternaSimplificado;
import ar.com.telecom.shiva.persistencia.repository.CobroCodigoOperacionExternaSimplificadoRepositorio;

/**
 * @author u564030 Pablo M. Ibarrola
 *
 */
public class CobroCodigoOperacionExternaSimplificadoDaoImpl implements ICobroCodigoOperacionExternaSimplificadoDao {

	@Autowired CobroCodigoOperacionExternaSimplificadoRepositorio cobroCodigoOperacionExternaSimplificadoRepositorio;
	
	@Override
	public List<ShvCobEdCodigoOperacionExternaSimplificado> listarPorIdCobro(Long idCobro) throws PersistenciaExcepcion {

		try {
			return cobroCodigoOperacionExternaSimplificadoRepositorio.listarPorIdCobro(idCobro);
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
}
