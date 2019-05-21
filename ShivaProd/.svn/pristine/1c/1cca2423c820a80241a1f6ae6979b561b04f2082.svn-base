/**
 * 
 */
package ar.com.telecom.shiva.persistencia.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.dao.IDescobroCodigoOperacionExternaSimplificadoDao;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvCobDescobroCodigoOperacionExternaSimplificado;
import ar.com.telecom.shiva.persistencia.repository.DescobroCodigoOperacionExternaSimplificadoRepositorio;

/**
 * @author u564030 Pablo M. Ibarrola
 *
 */
public class DescobroCodigoOperacionExternaSimplificadoDaoImpl implements IDescobroCodigoOperacionExternaSimplificadoDao {

	@Autowired DescobroCodigoOperacionExternaSimplificadoRepositorio descobroCodigoOperacionExternaSimplificadoRepositorio;
	
	@Override
	public List<ShvCobDescobroCodigoOperacionExternaSimplificado> listarPorIdDescobro(Long idDescobro) throws PersistenciaExcepcion {

		try {
			return descobroCodigoOperacionExternaSimplificadoRepositorio.listarPorIdDescobro(idDescobro);
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
}
