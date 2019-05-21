package ar.com.telecom.shiva.persistencia.dao;

import java.util.List;

import ar.com.telecom.shiva.base.enumeradores.MotivoCobroUsoEnum;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamMotivoCobro;

public interface IMotivoCobroDao {

	/**
	 * Permite la busqueda de un motivo de cobro puntual
	 * 
	 * @param idMotivoCobro
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	ShvParamMotivoCobro buscarMotivoCobro(String idMotivoCobro) throws PersistenciaExcepcion;
	
	/**
	 * Permite obtener una lista de motivos de Cobro filtrando por el campo "Uso"
	 * 
	 * @param uso
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	List<ShvParamMotivoCobro> listarMotivosCobrosPorUso(MotivoCobroUsoEnum uso) throws PersistenciaExcepcion;
}
