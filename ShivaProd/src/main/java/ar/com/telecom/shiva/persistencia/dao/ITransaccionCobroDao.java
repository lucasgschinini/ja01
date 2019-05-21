package ar.com.telecom.shiva.persistencia.dao;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvCobTransaccionSimple;

public interface ITransaccionCobroDao {

	ShvCobTransaccionSimple buscarTransaccionCobroPorTransaccion(Integer idTransaccion) throws PersistenciaExcepcion;
	
	ShvCobTransaccionSimple buscarTransaccionCobroPorOperacionyNumero(Long idOperacion, Integer numeroTransaccion) throws PersistenciaExcepcion;

	ShvCobTransaccionSimple buscarTransaccionCobroPorOperacionyTransaccion(Long idOperacion, Integer idTransaccion) throws PersistenciaExcepcion;
	
	public void borrarTransaccionPorIdTransaccion(Integer idTransaccion) throws PersistenciaExcepcion;

	Integer verificarNroTransaccionFicticio(Long idOperacion, Integer numeroTransaccionFicticio) throws PersistenciaExcepcion;

	Integer buscarNroTransaccionFicticio(Long idOperacion, Integer idTransaccion);
	
}
