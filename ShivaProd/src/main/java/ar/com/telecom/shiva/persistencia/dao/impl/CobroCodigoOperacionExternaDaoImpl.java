package ar.com.telecom.shiva.persistencia.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.dao.Dao;
import ar.com.telecom.shiva.persistencia.dao.ICobroCodigoOperacionExternaDao;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdCodigoOperacionExterna;
import ar.com.telecom.shiva.persistencia.repository.CobroOnlineCodigoOperacionExternaRepositorio;
/**
 * @author u578936 M.A.Uehara
 *
 */
public class CobroCodigoOperacionExternaDaoImpl extends Dao implements ICobroCodigoOperacionExternaDao {
	@Autowired
	CobroOnlineCodigoOperacionExternaRepositorio cobroOnlineCodigoOperacionExternaRepositorio;

	@Override
	public List<ShvCobEdCodigoOperacionExterna> listarCodigosOperacionesExternasPorIdCobro(Long idCobro) throws NegocioExcepcion {
		
		return cobroOnlineCodigoOperacionExternaRepositorio.lista(idCobro);
	}

	@Override
	public ShvCobEdCodigoOperacionExterna insertarCodigoOperacioneExterna(ShvCobEdCodigoOperacionExterna codigoOperacionExterna)
			throws PersistenciaExcepcion {
		try{
			ShvCobEdCodigoOperacionExterna codigoOperacionExternaBD = cobroOnlineCodigoOperacionExternaRepositorio.save(codigoOperacionExterna);
			cobroOnlineCodigoOperacionExternaRepositorio.flush();
			return codigoOperacionExternaBD;
		} catch(Exception e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	@Override
	public List<ShvCobEdCodigoOperacionExterna> listarCodigosOperacionesExternas(Long idCobro, Long numGrupo) throws NegocioExcepcion {
		
		return cobroOnlineCodigoOperacionExternaRepositorio.lista(idCobro, String.valueOf(numGrupo));
	}
}
