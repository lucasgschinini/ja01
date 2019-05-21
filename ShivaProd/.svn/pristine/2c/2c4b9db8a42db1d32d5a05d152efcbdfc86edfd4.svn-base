package ar.com.telecom.shiva.persistencia.dao;

import java.util.List;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamMotivoLegajo;

public interface IMotivoLegajoDao {

	/**
	 * Permite la busqueda de un motivo de cobro puntual
	 * 
	 * @param idMotivoCobro
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	ShvParamMotivoLegajo buscarMotivoLegajo(String idMotivoLegajo) throws PersistenciaExcepcion;
	
	/**
	 * Permite obtener una lista de motivos de Legajos de cheques rechazados
	 * 
	 * @param usoOperacionMasiva
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	List<ShvParamMotivoLegajo> listarMotivosLegajos() throws PersistenciaExcepcion;
	
}
