package ar.com.telecom.shiva.negocio.servicios;

import java.util.List;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.SociedadEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoInvocacionEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobDescobro;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow;
import ar.com.telecom.shiva.presentacion.bean.UsuarioSesion;

public interface IDescobroImputacionServicio extends IServicio {
	
	/**
	 * Me devuelve la lista de cobros pendientes de imputacion
	 */
	public List<Long> listarDescobrosPendientesImputacion() throws NegocioExcepcion;
	
	/**
	 * 
	 * @return
	 * @throws NegocioExcepcion
	 */
	public List<Long> listarDescobrosPendientesProcesarMIC() throws NegocioExcepcion;
	
	/**
	 * 
	 * @param idOperacion
	 * @return
	 * @throws NegocioExcepcion
	 */
	ShvCobDescobro buscarDescobroPorIdOperacion(Long idOperacion) throws NegocioExcepcion;
	
	/**
	 * Me permite buscar un cobro
	 */
	ShvCobDescobro buscarDescobro(Long idDescobro) throws NegocioExcepcion;
	
	/**
	 * Comienzo el proceso de imputacion 
	 */
	ShvCobDescobro imputarDescobro(Long idCobro, int count) throws NegocioExcepcion;
	
	/**
	 * Una vez finalizada la imputacion del cobro, envia un mail con el resultado de la imputacion
	 * y en caso de error genera una tarea
	 */
	public void enviarMailyGenerarTarea(ShvCobDescobro cobro) throws NegocioExcepcion;
	
	/**
	 * Proceso la respuesta con datos del descobro
	 */
	public void micRecepcionDescobro(DTO respuesta) throws NegocioExcepcion, PersistenciaExcepcion;
	
	/**
	 * Proceso la respuesta con datos del descobro contracargos
	 * @param respuesta
	 * @throws NegocioExcepcion
	 */
	public void micRecepcionContraCargo(DTO respuesta) throws NegocioExcepcion;
	
	/**
	 * Obtiene el idOperacion del descobro a partir del idOperacion del cobro
	 * @param idOperacion
	 * @return
	 * @throws NegocioExcepcion
	 */
	public Long buscarIdDescobroPorIdOperacionCobro(Long idOperacion) throws NegocioExcepcion;
	
	/**
	 * Pasa el workflow de pendiente mic a pendiente procesar mic
	 * @param idOperacion
	 * @param tipoInvocacion
	 * @param idOperacionTransaccion
	 * @param usuarioBatch
	 * @throws NegocioExcepcion
	 */
	public void pasarWorkflowPendienteMICAPendienteProcesarMIC(Long idOperacion, TipoInvocacionEnum tipoInvocacion, String idOperacionTransaccion,  String usuarioBatch) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param idOperacion
	 * @param tipoInvocacion
	 * @param idOperacionTransaccion
	 * @param usuarioBatch
	 * @throws NegocioExcepcion
	 */
	public ShvWfWorkflow pasarWorkflowProcesoAPendienteMIC(Long idOperacion, String usuarioBatch, TipoInvocacionEnum tipoInvocacion, String idOperacionTransaccion, ShvWfWorkflow wf) throws NegocioExcepcion;

	/**
	 * 
	 * @param descobro
	 * @throws NegocioExcepcion
	 */
	public void enviarMailyFinalizarTareaAplicacionManual(ShvCobDescobro descobro, SociedadEnum sociedad, SistemaEnum sistema, UsuarioSesion userSesion) throws NegocioExcepcion;
		
}
