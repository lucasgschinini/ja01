package ar.com.telecom.shiva.persistencia.dao;

import java.util.List;

import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.SociedadEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTareaEnum;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfTarea;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvWfTareaSimplificado;
import ar.com.telecom.shiva.presentacion.bean.filtro.TareaFiltro;

public interface ITareaDao {

	ShvWfTarea insertarTarea(ShvWfTarea tarea) throws PersistenciaExcepcion;

	ShvWfTarea actualizarTarea(ShvWfTarea tarea) throws PersistenciaExcepcion;
	
	public void eliminarTarea(ShvWfTarea tarea) throws PersistenciaExcepcion;
	
	ShvWfTarea buscarTarea(Long id) throws PersistenciaExcepcion;
	
//	ShvWfTarea buscarTareaPendiente(TipoTareaEnum tipoTarea, Integer idWorkflow) throws PersistenciaExcepcion;
	
	List<ShvWfTarea> buscarTareasPendientes(Integer idWorkflow) throws PersistenciaExcepcion;

	List<ShvWfTarea> listarTareas(TareaFiltro filtro) throws PersistenciaExcepcion;

	ShvWfTarea buscarTareaPendiente(TipoTareaEnum tipoTarea, Integer idWorkflow) throws PersistenciaExcepcion;

	ShvWfTarea buscarTareaPendiente(TipoTareaEnum tipoTarea, SociedadEnum sociedad, SistemaEnum sistema, Integer idWorkflow) throws PersistenciaExcepcion;

	ShvWfTarea buscarTareaPendienteConfManual(TipoTareaEnum tipoTarea, SociedadEnum sociedad, SistemaEnum sistema, Integer idWorkflow) throws PersistenciaExcepcion;

	/**
	 * 
	 * @param filtro
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public List<ShvWfTareaSimplificado> listarTareasParaEstadoFinalDeCobro(TareaFiltro filtro) throws PersistenciaExcepcion;

	/**
	 * 
	 * @param idWorkflow
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public List<ShvWfTarea> buscarTareaAPLManualParaObservaciones(Integer idWorkflow) throws PersistenciaExcepcion;
	
}
