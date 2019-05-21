package ar.com.telecom.shiva.persistencia.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.dao.Dao;
import ar.com.telecom.shiva.persistencia.dao.IDescobroCodigoOperacionExternaDao;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobDescobroCodigoOperacionExterna;
import ar.com.telecom.shiva.persistencia.repository.CodigoOperacionExternaRepositorio;


public class DescobroCodigoOperacionExternaDaoImpl extends Dao implements IDescobroCodigoOperacionExternaDao {
	@Autowired
	CodigoOperacionExternaRepositorio codigoOperacionExternaRepositorio;

	@Override
	public List<ShvCobDescobroCodigoOperacionExterna> listarCodigosOperacionesExternasPorIdDescobro(Long idDescobro) throws NegocioExcepcion {
		
		return null;
	}

	@Override
	public ShvCobDescobroCodigoOperacionExterna insertarCodigoOperacioneExterna(ShvCobDescobroCodigoOperacionExterna codigoOperacionExterna)
			throws PersistenciaExcepcion {
		try{
			ShvCobDescobroCodigoOperacionExterna codigoOperacionExternaBD = codigoOperacionExternaRepositorio.save(codigoOperacionExterna);
			codigoOperacionExternaRepositorio.flush();
			return codigoOperacionExternaBD;
		} catch(Exception e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	

	
	
}
