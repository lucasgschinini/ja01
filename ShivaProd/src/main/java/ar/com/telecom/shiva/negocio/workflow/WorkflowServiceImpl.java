package ar.com.telecom.shiva.negocio.workflow;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.constantes.Mensajes;
import ar.com.telecom.shiva.base.enumeradores.MarcaEnum;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.WorkflowExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.negocio.workflow.definicion.TipoWorkflow;
import ar.com.telecom.shiva.negocio.workflow.definicion.Transicion;
import ar.com.telecom.shiva.negocio.workflow.definicion.Workflow;
import ar.com.telecom.shiva.persistencia.dao.IWorkflowDao;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfHistorialObservacion;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfHistorialObservacionHist;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflowEstado;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflowEstadoHist;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflowMarca;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflowMarcaHist;

public class WorkflowServiceImpl implements IWorkflowService {
	
	@Autowired 
	private IWorkflowDao dao;
	@Autowired 
	private MotorWorkflow motorWorkflow;
	
	/*******************************************************************
	 * Motor Workflow
	 *******************************************************************/
	@Override
	public boolean validarConfiguracionWorkflow() throws WorkflowExcepcion {
		
		Collection<Workflow> workflows = motorWorkflow.getWorkflows();
		//Verifico que si la aplicacion tiene workflows
		if (!Validaciones.isCollectionNotEmpty(workflows)) {
			throw new WorkflowExcepcion(Mensajes.WORKFLOW_VACIO);
		}
		
		// Verifico que si cada workflow tiene por lo menos 1 transicion
		Iterator<Workflow> it = workflows.iterator();
	    while (it.hasNext()) {
	    	Workflow o = it.next();
	    	Collection<Transicion> transiciones = o.getTransiciones();
	    	if (!Validaciones.isCollectionNotEmpty(transiciones)) {
	    		throw new WorkflowExcepcion(
	    				Utilidad.reemplazarMensajes(
	    						Mensajes.WORKFLOW_SIN_TRANSICION, 
	    							o.getNombre()));
	    	}
	    }
		
	    //TODO: Verifico que si los nombres de las transiciones no se encuentren repetidas
	    
	    //TODO: Verifico que si los nombres de los estados pertenezcan al workflow correspondiente
	    
	    //TODO: Verifico que si los eventos y sus estados estan definidos en el workflow
	    
	    //TODO: Verifico que si los caminos estàn bien definidos (evento = origen --> destino)
	    // Ese es mas dificil de implementar
	    
		return true;
	}
	
	@Override
	public Collection<Transicion> obtenerTransiciones(Workflow workflow)
			throws WorkflowExcepcion {
		
		Iterator<Workflow> it = this.obtenerWorkflows().iterator();
	    while (it.hasNext()) {
	    	Workflow o = it.next();
	    	if (workflow.getNombre().equalsIgnoreCase(o.getNombre())) {
	    		Collection<Transicion> transiciones = o.getTransiciones();
	    		return transiciones;
	    	}
	    }
		
	    throw new WorkflowExcepcion(Mensajes.WORKFLOW_VACIO);
	}

	/**
	 * Retorna una lista de workflows definidos en el Motor
	 * @return
	 */
	public Collection<Workflow> obtenerWorkflows() throws WorkflowExcepcion {
		return motorWorkflow.getWorkflows();
	}
	
	/**
	 * Retorna el workflow especifico según el tipo del workflow
	 * @return
	 */
	private Workflow buscarWorflow(TipoWorkflow tipoWorkflow)
			throws WorkflowExcepcion {
		
		Iterator<Workflow> it = this.obtenerWorkflows().iterator();
	    while (it.hasNext()) {
	    	Workflow o = it.next();
	    	if (tipoWorkflow.name().equalsIgnoreCase(o.getNombre())) {
	    		return o;
	    	}
	    }
		
	    throw new WorkflowExcepcion(Utilidad.reemplazarMensajes(
				Mensajes.WORKFLOW_NO_ENCONTRADO, tipoWorkflow.name()));
	}
	
	/**
	 * Estado valida si el camino seleccionado para avanzar es correcto.
	 * Si lo es, retorna el estado siguiente correspondiente para el avance de la transicion
	 * Si no lo es, sale como null 
	 */
	private Transicion validarAvanceTransicion(Workflow workflow, String metodo, Estado actual)
			throws WorkflowExcepcion {
		
		Collection<Transicion> transiciones = workflow.getTransiciones();
		if (!Validaciones.isCollectionNotEmpty(transiciones)) {
			throw new WorkflowExcepcion(
					Utilidad.reemplazarMensajes(
							Mensajes.WORKFLOW_SIN_TRANSICION, 
							workflow.getNombre()));
		}
		
		// Recorro toda las transiciones del workflow ingresado
		Iterator<Transicion> it = transiciones.iterator();
		while (it.hasNext()) {
	    	Transicion tr = it.next();
	    	
	    	// Busco el metodo en la lista de transiciones del workflow ingresado
	    	
	    	if (metodo.equalsIgnoreCase(tr.getEvento())) 
	    	{
	    		// Verifico que si el estado actual corresponde al origen del workflow
	    		// sino digo que este workflow no es valido 
	    		if (!actual.descripcion().equalsIgnoreCase(tr.getOrigen().descripcion())) {
	    			throw new WorkflowExcepcion(
	    					Utilidad.reemplazarMensajes(
	    							Mensajes.WORKFLOW_TRANSICION_ORIGEN_INVALIDO, 
	    							actual.descripcion(),metodo, workflow.getNombre()));
	    		}
	    		
	    		// Encontre la transicion valida
	    		return tr;
	    	}
	    }
		
	    //Digo que no encontré la transicion de acuerdo al evento
		throw new WorkflowExcepcion(
				Utilidad.reemplazarMensajes(
						Mensajes.WORKFLOW_TRANSICION_NO_ENCONTRADA, 
						metodo, workflow.getNombre()));
	}
	
	/**
	 * Avanza por la transicion seleccionada y valida, 
	 * pudiendo actualizar los datos en la tabla por el nuevo
	 */
	private Transicion avanzarTransicion(TipoWorkflow tipoWorkflow, String metodo, Estado estadoActual)
			throws WorkflowExcepcion {
		
		Workflow wf = this.buscarWorflow(tipoWorkflow);
		Transicion transicionAAvanzar = this.validarAvanceTransicion(wf, metodo, estadoActual);
		return transicionAAvanzar;
	}
	
	/*******************************************************************
	 * Base de Datos
	 *******************************************************************/
	@Override
	public ShvWfWorkflow buscarWorkflow(Integer id)
			throws WorkflowExcepcion {
		try {
			
			return dao.buscarWorkflow(id);
		
		} catch (PersistenciaExcepcion e) {
			throw new WorkflowExcepcion(e);
		}
	}
	
	@Override
	public Collection<ShvWfWorkflow> listarWorkflowTest() throws WorkflowExcepcion {
		try {
			return dao.listarWorkflow();
		} catch (PersistenciaExcepcion e) {
			throw new WorkflowExcepcion(e);
		}
	}
	
	@Override
	/**
	 * Nota: Este metodo tiene que estar en la capa principal de servicios
	 * @Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class})  
	 */
	public ShvWfWorkflow crearWorkflow(ShvWfWorkflow wf) 
			throws WorkflowExcepcion {
		
		return this.crearWorkflow(wf, "crearWorkflow");
	}
	
	@Override
	/**
	 * Nota: Este metodo tiene que estar en la capa principal de servicios
	 * @Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class})  
	 */
	public ShvWfWorkflow crearWorkflow(ShvWfWorkflow wf, String metodo) throws WorkflowExcepcion {
		
		try {
			//Motor Workflow
			Transicion transicion = this.avanzarTransicion(wf.getTipoWorkflow(), metodo, Estado.INICIO);
			
			String datosModificadosEstado= Utilidad.generarDatosModificadosEstado(null, transicion.getDestino().descripcion());
			String datosModificados = datosModificadosEstado + wf.getDatosModificados(); 
			
			//BBDD Workflow
		    ShvWfWorkflowEstado nuevoEstado = new ShvWfWorkflowEstado();
	        nuevoEstado.setEstado(transicion.getDestino());
	        nuevoEstado.setFechaModificacion(new Date());
	        nuevoEstado.setUsuarioModificacion(wf.getUsuarioModificacion());
	        nuevoEstado.setDatosModificados(datosModificados);
	        nuevoEstado.setWorkflow(wf);
	        
	        wf.getShvWfWorkflowEstado().add(nuevoEstado);
	        wf = dao.insertarWorkflow(wf);
	        
	        return wf;
		} catch (PersistenciaExcepcion e) {
			throw new WorkflowExcepcion(e);
		}
	}
	
	@Override
	public ShvWfWorkflow actualizarWorkflow(ShvWfWorkflow wf, String metodo)
			throws WorkflowExcepcion {
		
		try {
			ShvWfWorkflowEstado wfEstadoActual = wf.getShvWfWorkflowEstado().iterator().next();
					
			//Para el Motor Workflow
			Estado estadoActual = wfEstadoActual.getEstado();
			TipoWorkflow tipoWorkflow = wf.getTipoWorkflow();
			Transicion transicionAAvanzar = this.avanzarTransicion(tipoWorkflow, metodo, estadoActual);
			
			String datosModificadosEstado= Utilidad.generarDatosModificadosEstado(estadoActual.descripcion(), transicionAAvanzar.getDestino().descripcion());
			String datosModificados = datosModificadosEstado + (wf.getDatosModificados() != null? wf.getDatosModificados():""); 
			
			//BBDD Workflow
			ShvWfWorkflowEstadoHist nuevoEstadoHist = new ShvWfWorkflowEstadoHist();
	        nuevoEstadoHist.setEstado(wfEstadoActual.getEstado());
	        nuevoEstadoHist.setFechaModificacion(wfEstadoActual.getFechaModificacion());
	        nuevoEstadoHist.setUsuarioModificacion(wfEstadoActual.getUsuarioModificacion());
	        nuevoEstadoHist.setDatosModificados(wfEstadoActual.getDatosModificados());

	        //Mover Marcas a historico
	        for (ShvWfWorkflowMarca wfMarca : wfEstadoActual.getWorkflowMarcas()) {
	        
	        	ShvWfWorkflowMarcaHist wfMarcaHist = new ShvWfWorkflowMarcaHist();
	        	wfMarcaHist.setFechaCreacion(wfMarca.getFechaCreacion());
	        	wfMarcaHist.setUsuarioCreacion(wfMarca.getUsuarioCreacion());
	        	wfMarcaHist.setMarca(wfMarca.getMarca());
	        	wfMarcaHist.setWorkflowEstadoHist(nuevoEstadoHist);
	        	wfMarcaHist.setObservacion(wfMarca.getObservacion());
	        	
	        	
	        	nuevoEstadoHist.getShvWfWorkflowMarcaHist().add(wfMarcaHist);
	        }
	        
	        //Mover observaciones a historico
	        for (ShvWfHistorialObservacion observacion : wfEstadoActual.getHistorialObservaciones()) {
	        
	        	ShvWfHistorialObservacionHist observacionHist = new ShvWfHistorialObservacionHist();
	        	observacionHist.setFechaCreacion(observacion.getFechaCreacion());
	        	observacionHist.setUsuario(observacion.getUsuario());
	        	observacionHist.setObservacion(observacion.getObservacion());
	        	observacionHist.setWorkflowEstadoHist(nuevoEstadoHist);
	        	
	        	nuevoEstadoHist.getShvWfHistoriaHist().add(observacionHist);
	        }
	        
	        nuevoEstadoHist.setWorkflow(wf);
	        wf.getShvWfWorkflowEstadoHist().add(nuevoEstadoHist);
	        
			wfEstadoActual.setEstado(transicionAAvanzar.getDestino());
			wfEstadoActual.setFechaModificacion(new Date());
			wfEstadoActual.setUsuarioModificacion(wf.getUsuarioModificacion());
			wfEstadoActual.setDatosModificados(datosModificados);
			//eliminar marcas del estado actual dado que no corresponden al estado actual
			wfEstadoActual.getWorkflowMarcas().clear();
			//eliminar observaciones del estado actual dado que no corresponden al estado actual
			wfEstadoActual.getHistorialObservaciones().clear();
	        
	        wf = dao.actualizarWorkflow(wf);
	        
	        return wf;
		} catch (PersistenciaExcepcion e) {
			throw new WorkflowExcepcion(e);
		}
		
	}
	
	@Override
	public ShvWfWorkflow actualizarWorkflow(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion)
			throws WorkflowExcepcion {
		
		try {
			ShvWfWorkflowEstado wfEstadoActual = wf.getShvWfWorkflowEstado().iterator().next();
			
			//Estado historico
			ShvWfWorkflowEstadoHist nuevoEstadoHist = new ShvWfWorkflowEstadoHist();
	        nuevoEstadoHist.setEstado(wfEstadoActual.getEstado());
	        nuevoEstadoHist.setFechaModificacion(wfEstadoActual.getFechaModificacion());
	        nuevoEstadoHist.setUsuarioModificacion(wfEstadoActual.getUsuarioModificacion());
	        nuevoEstadoHist.setDatosModificados(wfEstadoActual.getDatosModificados());

	        for (ShvWfWorkflowMarca wfMarca : wfEstadoActual.getWorkflowMarcas()) {
		        
	        	ShvWfWorkflowMarcaHist wfMarcaHist = new ShvWfWorkflowMarcaHist();
	        	wfMarcaHist.setFechaCreacion(wfMarca.getFechaCreacion());
	        	wfMarcaHist.setUsuarioCreacion(wfMarca.getUsuarioCreacion());
	        	wfMarcaHist.setMarca(wfMarca.getMarca());
	        	wfMarcaHist.setWorkflowEstadoHist(nuevoEstadoHist);
	        	
	        	nuevoEstadoHist.getShvWfWorkflowMarcaHist().add(wfMarcaHist);
	        }
	        
	        nuevoEstadoHist.setWorkflow(wf);
	        wf.getShvWfWorkflowEstadoHist().add(nuevoEstadoHist);
	        
	        //Estado actual sin cambio de estado
	        wfEstadoActual.setFechaModificacion(new Date());
	        wfEstadoActual.setUsuarioModificacion(usuarioModificacion);
	        wfEstadoActual.setDatosModificados(datosModificados);
			wfEstadoActual.getWorkflowMarcas().clear();

	        wf = dao.actualizarWorkflow(wf);
	        
	        return wf;
		} catch (PersistenciaExcepcion e) {
			throw new WorkflowExcepcion(e);
		}
	}
	
	/**
	 * @author u572487 Guido.Settecerze
	 */
	@Override
	public ShvWfWorkflow actualizarWorkflowSinGuardar(ShvWfWorkflow wf, String datosModificados, String usuarioModificacion)
			throws WorkflowExcepcion {
		
		ShvWfWorkflowEstado wfEstadoActual = wf.getShvWfWorkflowEstado().iterator().next();
		
		//Estado historico
		ShvWfWorkflowEstadoHist nuevoEstadoHist = new ShvWfWorkflowEstadoHist();
		nuevoEstadoHist.setEstado(wfEstadoActual.getEstado());
		nuevoEstadoHist.setFechaModificacion(wfEstadoActual.getFechaModificacion());
		nuevoEstadoHist.setUsuarioModificacion(wfEstadoActual.getUsuarioModificacion());
		nuevoEstadoHist.setDatosModificados(wfEstadoActual.getDatosModificados());

		for (ShvWfWorkflowMarca wfMarca : wfEstadoActual.getWorkflowMarcas()) {
		    
			ShvWfWorkflowMarcaHist wfMarcaHist = new ShvWfWorkflowMarcaHist();
			wfMarcaHist.setFechaCreacion(wfMarca.getFechaCreacion());
			wfMarcaHist.setUsuarioCreacion(wfMarca.getUsuarioCreacion());
			wfMarcaHist.setMarca(wfMarca.getMarca());
			wfMarcaHist.setWorkflowEstadoHist(nuevoEstadoHist);
			
			nuevoEstadoHist.getShvWfWorkflowMarcaHist().add(wfMarcaHist);
		}
		
		nuevoEstadoHist.setWorkflow(wf);
		wf.getShvWfWorkflowEstadoHist().add(nuevoEstadoHist);
		
		//Estado actual sin cambio de estado
		wfEstadoActual.setFechaModificacion(new Date());
		wfEstadoActual.setUsuarioModificacion(usuarioModificacion);
		wfEstadoActual.setDatosModificados(datosModificados);
		wfEstadoActual.getWorkflowMarcas().clear();

		return wf;
	}
	
	@Override
	public ShvWfWorkflow agregarWorkflowMarca(ShvWfWorkflow wf, 
			String usuarioModificacion, MarcaEnum marca) throws WorkflowExcepcion {
		
		try {
			ShvWfWorkflowEstado wfEstadoActual = wf.getShvWfWorkflowEstado().iterator().next();
			
			// Marca
			ShvWfWorkflowMarca wfMarca = new ShvWfWorkflowMarca();
			wfMarca.setFechaCreacion(new Date());
			wfMarca.setUsuarioCreacion(usuarioModificacion);
			wfMarca.setWorkflowEstado(wfEstadoActual);
			wfMarca.setMarca(marca);
	        //Estado actual sin cambio de estado
	        wfEstadoActual.getWorkflowMarcas().add(wfMarca);
	        
	        wf = dao.actualizarWorkflow(wf);
	        
	        return wf;
		} catch (PersistenciaExcepcion e) {
			throw new WorkflowExcepcion(e);
		}
	}
	
	@Override
	public ShvWfWorkflow agregarWorkflowMarcaConObservaciones(ShvWfWorkflow wf, 
			String usuarioModificacion, MarcaEnum marca, String observaciones) throws WorkflowExcepcion {
		
		try {
			ShvWfWorkflowEstado wfEstadoActual = wf.getShvWfWorkflowEstado().iterator().next();
			
			// Marca
			ShvWfWorkflowMarca wfMarca = new ShvWfWorkflowMarca();
			wfMarca.setFechaCreacion(new Date());
			wfMarca.setUsuarioCreacion(usuarioModificacion);
			wfMarca.setWorkflowEstado(wfEstadoActual);
			wfMarca.setMarca(marca);
			wfMarca.setObservacion(observaciones);
			//Estado actual sin cambio de estado
			wfEstadoActual.getWorkflowMarcas().add(wfMarca);
			
			wf = dao.actualizarWorkflow(wf);
			
			return wf;
		} catch (PersistenciaExcepcion e) {
			throw new WorkflowExcepcion(e);
		}
	}
	@Override
	public ShvWfWorkflow agregarWorkflowMarca(ShvWfWorkflow wf, Set<ShvWfWorkflowMarca> marcas) throws WorkflowExcepcion {
		
		try {
			ShvWfWorkflowEstado wfEstadoActual = wf.getShvWfWorkflowEstado().iterator().next();

			// Marca
			for (ShvWfWorkflowMarca marca : marcas) {
				marca.setWorkflowEstado(wfEstadoActual);
				//Estado actual sin cambio de estado
				wfEstadoActual.getWorkflowMarcas().add(marca);
			}
			wf = dao.actualizarWorkflow(wf);
			return wf;
		} catch (PersistenciaExcepcion e) {
			throw new WorkflowExcepcion(e);
		}
	}
	@Override
	public ShvWfWorkflow eliminarWorkflowMarca(ShvWfWorkflow wf, 
			String usuarioModificacion, MarcaEnum marca) throws WorkflowExcepcion {
		
		try {
			ShvWfWorkflowEstado wfEstadoActual = wf.getShvWfWorkflowEstado().iterator().next();
			
			for (ShvWfWorkflowMarca wfMarca : wfEstadoActual.getWorkflowMarcas()) {
				if (marca.equals(wfMarca.getMarca())) {
			        wfEstadoActual.getWorkflowMarcas().remove(wfMarca);
				}
			}
	        
	        wf = dao.actualizarWorkflow(wf);
	        
	        return wf;
		} catch (PersistenciaExcepcion e) {
			throw new WorkflowExcepcion(e);
		}
	}
	
	
	/***************************************************************************
	 * GETTERS & SETTERS
	 **************************************************************************/

	public IWorkflowDao getDao() {
		return dao;
	}

	public void setDao(IWorkflowDao dao) {
		this.dao = dao;
	}

	public MotorWorkflow getMotorWorkflow() {
		return motorWorkflow;
	}

	public void setMotorWorkflow(MotorWorkflow motorWorkflow) {
		this.motorWorkflow = motorWorkflow;
	}
}
