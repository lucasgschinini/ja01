package ar.com.telecom.shiva.negocio.workflow.servicios.util.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.negocio.servicios.impl.Servicio;
import ar.com.telecom.shiva.negocio.workflow.servicios.util.IObservacionesWorkflowServicio;
import ar.com.telecom.shiva.persistencia.dao.IObservacionesWorkflowDao;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfHistorialObservacion;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfHistorialObservacionHist;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflowEstado;

public class ObservacionesWorkflowServicioImpl extends Servicio implements IObservacionesWorkflowServicio{
	@Autowired 
	IObservacionesWorkflowDao observacionesWorkflowDao;

	@Override
	public ShvWfHistorialObservacion obtenerObservacionCorrienteBy(ShvWfWorkflowEstado workflowEstado) throws NegocioExcepcion {
		try {
			return observacionesWorkflowDao.obtenerObservacionCorrienteBy(workflowEstado);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e);
		}
	}
	
	@Override
	public ShvWfHistorialObservacion insertarHistorialObservacion(ShvWfWorkflowEstado workflowEstado, String observacion) throws NegocioExcepcion {
		try {
			ShvWfHistorialObservacion historialObservacion = new ShvWfHistorialObservacion();
			historialObservacion.setFechaCreacion(new Date());
			historialObservacion.setObservacion(observacion);
			historialObservacion.setUsuario(workflowEstado.getUsuarioModificacion());
			historialObservacion.setWorkflowEstado(workflowEstado);

			return observacionesWorkflowDao.insertarHistorialObservacion(historialObservacion);
		} catch (Throwable e) {
			throw new NegocioExcepcion(e);
		}
	}

	@Override
	public ShvWfHistorialObservacion modificarObservacionCorrienteBy(ShvWfHistorialObservacion historialObservacion) throws NegocioExcepcion {
		try{
			return observacionesWorkflowDao.insertarHistorialObservacion(historialObservacion);
		} catch (Throwable e){
			throw new NegocioExcepcion(e);
		}
	}

	@Override
	public void borrarObservacionCorrienteBy(ShvWfWorkflowEstado workflowEstado) throws NegocioExcepcion {
		try {
			observacionesWorkflowDao.borrarObservacionCorrienteBy(workflowEstado);	
		} catch (Throwable e) {
			throw new NegocioExcepcion(e);
		}
		
	}

	@Override
	public List<ShvWfHistorialObservacion> listarObservacionesActualesByIdWorkflowOrdenadoPorFecha(Integer idWorkflow) throws NegocioExcepcion {
		try{
			return observacionesWorkflowDao.listarObservacionesActualesByIdWorkflowOrdenadoPorFecha(idWorkflow);
		} catch (Throwable e){
			throw new NegocioExcepcion(e);
		}
	}
	

	
	private List<ShvWfHistorialObservacionHist> listarObservacionesHistoricasOrdenadoPorFecha(Integer idWorkflow) throws NegocioExcepcion {
		try{
			return observacionesWorkflowDao.listarObservacionesHistoricasOrdenadoPorFecha(idWorkflow);
		} catch (Throwable e){
			throw new NegocioExcepcion(e);
		}
	}

	@Override
	public Map<String, String> listarObservacionesHistoricasYActuales(Integer idWorkflow, boolean currienteEditable) throws NegocioExcepcion {
		try{
			List<Modelo> resultado = new ArrayList<Modelo>();
			Map<String, String> map = new HashMap<String, String>();
	
			resultado.addAll(this.listarObservacionesActualesByIdWorkflowOrdenadoPorFecha(idWorkflow));
			if (!resultado.isEmpty() && currienteEditable) {
				map.put("current", (((ShvWfHistorialObservacion)resultado.get(0)).getObservacion()));
				resultado.remove(0);
			} else {
				map.put("current", null);
			}
			resultado.addAll(this.listarObservacionesHistoricasOrdenadoPorFecha(idWorkflow));
			
			StringBuffer str = new StringBuffer("");
			if (!resultado.isEmpty()) {
				String obse = null; 

				for (Modelo shvWf : resultado) {
					if (shvWf instanceof ShvWfHistorialObservacion) {
						obse = ((ShvWfHistorialObservacion) shvWf).getObservacion();
					} else if (shvWf instanceof ShvWfHistorialObservacionHist) {
						obse = ((ShvWfHistorialObservacionHist) shvWf).getObservacion();
					}
					if (str.length() > 0) {
						str.append("\n");
					} else {
						str.append(obse);
					}
				}
			}
			map.put("historicos", str.toString());
			return map;
		} catch (Throwable e) {
			throw new NegocioExcepcion(e);
		}
	}
	@Override
	public Map<String, String> listarObservacionesHistoricasYActuales(Integer idWorkflow) throws NegocioExcepcion {
		return this.listarObservacionesHistoricasYActuales(idWorkflow, false);
	}
	/***************************************************************************
	 * GETTERS & SETTERS
	 * ************************************************************************/
	public IObservacionesWorkflowDao getObservacionesWorkflowDao() {
		return observacionesWorkflowDao;
	}

	public void setObservacionesWorkflowDao(
			IObservacionesWorkflowDao observacionesWorkflowDao) {
		this.observacionesWorkflowDao = observacionesWorkflowDao;
	}


}
