package ar.com.telecom.shiva.negocio.servicios;

import java.util.List;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.negocio.bean.ArchivoByteArray;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.persistencia.modelo.ShvLgjNotificacion;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvLgjNotificacionSimplificado;

/**
 * @author u578936 M.A.Uehara
 *
 */
public interface ILegajoChequeRechazadoNotificacionServicio {
	/**
	 * 
	 * @param shvLgjNotificacionSimplificado
	 * @return
	 * @throws NegocioExcepcion
	 */
	public ShvLgjNotificacionSimplificado guardar(ShvLgjNotificacionSimplificado shvLgjNotificacionSimplificado) throws NegocioExcepcion;

	/**
	 * 
	 * @param idNotificacion
	 * @throws NegocioExcepcion
	 */
	public void anular(Long idNotificacion) throws NegocioExcepcion;

	/**
	 * 
	 * @param idLegajoChequeRechazado
	 * @return
	 * @throws NegocioExcepcion
	 */
	public ShvLgjNotificacion buscar(Long idLegajoChequeRechazado) throws NegocioExcepcion;

	/**
	 * 
	 * @param id
	 * @return
	 * @throws NegocioExcepcion
	 */
	public ArchivoByteArray generarCartaLegajoPdf(Long id) throws NegocioExcepcion;

	/**
	 * 
	 * @param idLegajoChequeRechazado
	 * @return
	 * @throws NegocioExcepcion
	 */
	public List<ShvLgjNotificacionSimplificado> listar(Long idLegajoChequeRechazado) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param shvLgjNotificacionSimplificado
	 * @param estadoWorkFlowLegajo
	 * @return
	 * @throws NegocioExcepcion
	 */
	public ShvLgjNotificacionSimplificado crear(ShvLgjNotificacionSimplificado shvLgjNotificacionSimplificado, Estado estadoWorkFlowLegajo) throws NegocioExcepcion;
}

