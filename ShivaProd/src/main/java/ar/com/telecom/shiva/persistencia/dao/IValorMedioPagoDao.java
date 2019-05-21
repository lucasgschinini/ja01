package ar.com.telecom.shiva.persistencia.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.PersistenceException;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamValorMedioPago;

public interface IValorMedioPagoDao {

	Collection<ShvParamValorMedioPago> listarValorMedioPago() throws PersistenciaExcepcion;
	Collection<ShvParamValorMedioPago> buscarPorTipoMedioPago(String tipo) throws PersistenceException;
	List<ShvParamValorMedioPago> buscarPorTipoValor(String tipoValor) throws PersistenceException;
}
