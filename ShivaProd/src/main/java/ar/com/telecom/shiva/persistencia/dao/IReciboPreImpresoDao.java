package ar.com.telecom.shiva.persistencia.dao;

import java.util.Set;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.modelo.ShvTalReciboPreImpreso;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvTalCompensacionSimplificado;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvTalReciboPreImpresoCompensacionSimplificado;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvTalReciboPreImpresoSimplificado;

/**
 * 
 *
 */
public interface IReciboPreImpresoDao {
	
	/**
	 * 
	 * @param idRecibo
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	ShvTalReciboPreImpreso buscarRecibo(Integer idRecibo) throws PersistenciaExcepcion;

	/**
	 * 
	 * @param reciboModelo
	 * @throws PersistenciaExcepcion
	 */
	void actualizarCompensaciones(ShvTalReciboPreImpreso reciboModelo) throws PersistenciaExcepcion;

	/**
	 * 
	 * @param reciboModelo
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	ShvTalReciboPreImpreso actualizarRecibo(ShvTalReciboPreImpreso reciboModelo) throws PersistenciaExcepcion;

	/**
	 * 
	 * @param numeroRecibo
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	ShvTalReciboPreImpreso buscarPorNumeroRecibo(String numeroRecibo) throws PersistenciaExcepcion;

	/**
	 * 
	 * @param numeroRecibo
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	ShvTalReciboPreImpresoSimplificado buscarSimplificadoPorNumeroRecibo(String numeroRecibo) throws PersistenciaExcepcion;
	
	/**
	 * 
	 * @param numeroRecibo
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	ShvTalReciboPreImpresoCompensacionSimplificado buscarSimplificadoConCompensacionPorNumeroRecibo(String numeroRecibo) throws PersistenciaExcepcion;
	
	 /**
	  *
	  * @param numeroRecibo
	  * @return
	  * @throws PersistenciaExcepcion
	  */
	public ShvTalReciboPreImpresoCompensacionSimplificado buscarReciboCompensacionSimplificado(Integer idRecibo) throws PersistenciaExcepcion;
	
	 /**
	  *
	  * @param listaCompensacionesSimplificadas
	  * @return
	  * @throws PersistenciaExcepcion
	  */
	public void actualizarCompensaciones(Set<ShvTalCompensacionSimplificado> listaCompensacionesSimplificadas) throws PersistenciaExcepcion;
	
	 /**
	  *
	  * @param reciboPreImpresoCompensacionSimplificado
	  * @return
	  * @throws PersistenciaExcepcion
	  */
	public ShvTalReciboPreImpresoCompensacionSimplificado actualizarReciboSimplificado(ShvTalReciboPreImpresoCompensacionSimplificado reciboModeloSimplificado) throws PersistenciaExcepcion;
	
}
	
