package ar.com.telecom.shiva.negocio.workflow;

import java.util.Collection;
import java.util.Set;

import ar.com.telecom.shiva.base.enumeradores.MarcaEnum;
import ar.com.telecom.shiva.base.excepciones.otros.WorkflowExcepcion;
import ar.com.telecom.shiva.negocio.workflow.definicion.Transicion;
import ar.com.telecom.shiva.negocio.workflow.definicion.Workflow;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflowMarca;

public interface IWorkflowService {

	/*******************************************************************
	 * Motor Workflow
	 *******************************************************************/
	/**
	 * Verifico que si los workflows están correctamente configurados
	 * @return
	 */
	public boolean validarConfiguracionWorkflow() throws WorkflowExcepcion;
	
	/**
	 * Retorna una lista de transicion para el workflow
	 * Tester
	 * @return
	 */
	public Collection<Transicion> obtenerTransiciones(Workflow workflow) throws WorkflowExcepcion;
	
	/*******************************************************************
	 * Base de Datos
	 *******************************************************************/
	/**
	 * Hago una lista completa de los workflows desde la BD
	 * Sòlo para Tester
	 */
	public Collection<ShvWfWorkflow> listarWorkflowTest() throws WorkflowExcepcion;
	
	/**
	 * Busco el workflow
	 */
	public ShvWfWorkflow buscarWorkflow(Integer id) throws WorkflowExcepcion;
	
	/**
	 * Crea el circuito workflow en la Base de datos
	 */
	public ShvWfWorkflow crearWorkflow(ShvWfWorkflow shvWfWorkflow) throws WorkflowExcepcion;
	
	/**
	 * Crea el circuito workflow en la Base de datos
	 */
	public ShvWfWorkflow crearWorkflow(ShvWfWorkflow shvWfWorkflow, String metodo) throws WorkflowExcepcion;
	
	/**
	 * Actualiza el circuito con cada cambio de estado, generando registros en la tabla
	 * historica y dejando el ultimo estado en la tabla actual
	 */
	public ShvWfWorkflow actualizarWorkflow(ShvWfWorkflow shvWfWorkflow, String metodo) throws WorkflowExcepcion;
	
	/**
	 * Actualiza el circuito sin cambiar el estado, generando registros en la tabla
	 * historica y dejando el ultimo estado en la tabla actual
	 */
	public ShvWfWorkflow actualizarWorkflow(ShvWfWorkflow shvWfWorkflow, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;
	
	/**
	 * Actualiza el circuito sin cambiar el estado, adicionando una marca al estado actual
	 * 
	 * @param wf
	 * @param usuarioModificacion
	 * @param marca
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow agregarWorkflowMarca(ShvWfWorkflow wf, String usuarioModificacion, MarcaEnum marca) throws WorkflowExcepcion;
	/**
	 * Actualiza el circuito sin cambiar el estado, adicionando una marca al estado actual
	 * 
	 * @param wf
	 * @param usuarioModificacion
	 * @param marca
	 * @param observaciones
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow agregarWorkflowMarcaConObservaciones(ShvWfWorkflow wf, String usuarioModificacion, MarcaEnum marca, String observaciones) throws WorkflowExcepcion;
	/**
	 * Actualiza el circuito sin cambiar el estado, adicionando una marca al estado actual. la marca es hererada del estado anterior
	 * 
	 * @param wf
	 * @param usuarioModificacion
	 * @param marca
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow agregarWorkflowMarca(ShvWfWorkflow wf, Set<ShvWfWorkflowMarca> marcas) throws WorkflowExcepcion;
	/**
	 * Actualiza el circuito sin cambiar el estado, eliminando una marca al estado actual
	 * 
	 * @param wf
	 * @param usuarioModificacion
	 * @param marca
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow eliminarWorkflowMarca(ShvWfWorkflow wf, String usuarioModificacion, MarcaEnum marca) throws WorkflowExcepcion;
	
	public ShvWfWorkflow actualizarWorkflowSinGuardar(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion;
}
