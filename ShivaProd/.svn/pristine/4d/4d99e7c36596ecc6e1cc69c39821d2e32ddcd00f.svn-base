package ar.com.telecom.shiva.persistencia.dao;

import java.util.List;

import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamMotivoDescobro;

public interface IMotivoDescobroDao {

	/**
	 * Permite la busqueda de un motivo de descobro puntual
	 * 
	 * @param idMotivoDescobro
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	ShvParamMotivoDescobro buscarMotivoDescobro(String idMotivoDescobro) throws PersistenciaExcepcion;
	
	/**
	 * Permite obtener una lista de motivos de Descobro filtrando por el campo "Uso Operacion Masiva"
	 * 
	 * @param usoOperacionMasiva
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	List<ShvParamMotivoDescobro> listarMotivosDescobrosPorUsoOperacionMasiva(SiNoEnum usoOperacionMasiva) throws PersistenciaExcepcion;
}
