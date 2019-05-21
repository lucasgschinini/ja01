package ar.com.telecom.shiva.persistencia.dao;

import java.util.List;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobDescobroCodigoOperacionExterna;

public interface IDescobroCodigoOperacionExternaDao {

	List<ShvCobDescobroCodigoOperacionExterna> listarCodigosOperacionesExternasPorIdDescobro(Long idDescobro) throws NegocioExcepcion;
	
	ShvCobDescobroCodigoOperacionExterna insertarCodigoOperacioneExterna(ShvCobDescobroCodigoOperacionExterna codigoOperacionExterna) throws PersistenciaExcepcion;
	
}
