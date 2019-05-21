package ar.com.telecom.shiva.persistencia.dao;

import java.util.Date;
import java.util.List;

import ar.com.telecom.shiva.base.enumeradores.EstadoNotificacionEnum;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.modelo.ShvLgjNotificacion;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvLgjNotificacionSimplificado;

/**
 * @author u578936 M.A.Uehara
 *
 */
public interface ILegajoChequeRechazadoNotificacionDao {
	/**
	 * 
	 * @param shvLgjNotificacion
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public ShvLgjNotificacion guardar(ShvLgjNotificacion shvLgjNotificacion) throws PersistenciaExcepcion;
	/**
	 * 
	 * @param shvLgjNotificacion
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public ShvLgjNotificacionSimplificado guardar(ShvLgjNotificacionSimplificado shvLgjNotificacion) throws PersistenciaExcepcion;
	/**
	 * 
	 * @param idNotificacion
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public ShvLgjNotificacion buscar(Long idNotificacion) throws PersistenciaExcepcion;

	/**
	 * 
	 * @param idLegajoChequerechazado
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public List<ShvLgjNotificacionSimplificado> lista(Long idLegajoChequerechazado) throws PersistenciaExcepcion;

	/**
	 * 
	 * @param idLegajoChequerechazado
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public int cambiarEstado(Long idNotificacion, Date fechaModificacion, EstadoNotificacionEnum estado) throws PersistenciaExcepcion;
}
