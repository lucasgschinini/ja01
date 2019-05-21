package ar.com.telecom.shiva.persistencia.dao;

import java.util.Collection;

import javax.persistence.PersistenceException;

import ar.com.telecom.shiva.base.enumeradores.TipoMedioPagoEnum;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamTipoMedioPago;

public interface ITipoMedioPagoDao extends IDao {

	/**
	 * 
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	Collection<ShvParamTipoMedioPago> listarMediosPago() throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @param tipo
	 * @return
	 * @throws PersistenceException
	 */
	ShvParamTipoMedioPago buscarTipo(String tipo) throws PersistenceException;
	
	/**
	 * 
	 * @param tipoMedioPago
	 * @return
	 * @throws PersistenceException
	 */
	ShvParamTipoMedioPago buscarMedioPago(TipoMedioPagoEnum tipoMedioPago) throws PersistenceException;
}
