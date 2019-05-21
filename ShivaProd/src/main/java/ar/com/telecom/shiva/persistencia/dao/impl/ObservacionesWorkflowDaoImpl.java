package ar.com.telecom.shiva.persistencia.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.persistencia.dao.Dao;
import ar.com.telecom.shiva.persistencia.dao.IObservacionesWorkflowDao;
import ar.com.telecom.shiva.persistencia.dao.util.QueryParametrosUtil;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfHistorialObservacion;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfHistorialObservacionHist;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflowEstado;
import ar.com.telecom.shiva.persistencia.repository.HistorialObservacionHistorialRepositorio;
import ar.com.telecom.shiva.persistencia.repository.HistorialObservacionRepositorio;

public class ObservacionesWorkflowDaoImpl extends Dao implements IObservacionesWorkflowDao {
	
	@Autowired HistorialObservacionRepositorio historialObservacionRepositorio;
	@Autowired HistorialObservacionHistorialRepositorio historialObservacionHistorialRepositorio;

	@Override
	public ShvWfHistorialObservacion obtenerObservacionCorrienteBy(ShvWfWorkflowEstado workflowEstado) throws PersistenciaExcepcion {
		try {
			List<ShvWfHistorialObservacion> listaShvWfHistorialObservacion = historialObservacionRepositorio.obtenerObservacionesEstadoCorrienteBy(workflowEstado);

			if (listaShvWfHistorialObservacion.isEmpty()) {
				return null;
			}

			return listaShvWfHistorialObservacion.get(0);
		} catch(Exception e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	@Override
	public void borrarObservacionCorrienteBy(ShvWfWorkflowEstado workflowEstado) throws PersistenciaExcepcion {
		try {
			ShvWfHistorialObservacion shvWfHistorialObservacion = obtenerObservacionCorrienteBy(workflowEstado);
			if (!Validaciones.isObjectNull(shvWfHistorialObservacion)) {
				historialObservacionRepositorio.borrarObservacionCorrienteBy(shvWfHistorialObservacion.getIdHistorialObservacion());
				historialObservacionRepositorio.flush();
			}
			return;
		} catch(Exception e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	@Override
	public ShvWfHistorialObservacion insertarHistorialObservacion(ShvWfHistorialObservacion historialObservacion) throws PersistenciaExcepcion {
		try{
			ShvWfHistorialObservacion archivoAVCBD = historialObservacionRepositorio.save(historialObservacion);
			historialObservacionRepositorio.flush();
			return archivoAVCBD;
		} catch(Exception e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<ShvWfHistorialObservacion> listarObservacionesActualesByIdWorkflowOrdenadoPorFecha(Integer idWorkflow) throws PersistenciaExcepcion {
		try {
			String query = "from ShvWfHistorialObservacion as his "
					+ " where his.workflowEstado.workflow.idWorkflow = ? "
					+ " order by his.fechaCreacion";
			
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addParametro(idWorkflow);
			
			List<ShvWfHistorialObservacion> resultado = (List<ShvWfHistorialObservacion>) buscarUsandoQueryConParametros(qp);
			return resultado;
			
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<ShvWfHistorialObservacionHist> listarObservacionesHistoricasOrdenadoPorFecha(Integer idWorkflow) throws PersistenciaExcepcion {
		try {
			String query = "from ShvWfHistorialObservacionHist as his "
					+ " where his.workflowEstadoHist.workflow.idWorkflow = ? "
					+ " order by his.fechaCreacion";
			
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addParametro(idWorkflow);
			
			List<ShvWfHistorialObservacionHist> resultado = (List<ShvWfHistorialObservacionHist>) buscarUsandoQueryConParametros(qp);
			return resultado;
			
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	/************************************************************************************
	 * Getters & Setters
	 ************************************************************************************/

	public HistorialObservacionRepositorio getHistorialObservacionRepositorio() {
		return historialObservacionRepositorio;
	}

	public void setHistorialObservacionRepositorio(
			HistorialObservacionRepositorio historialObservacionRepositorio) {
		this.historialObservacionRepositorio = historialObservacionRepositorio;
	}

	public HistorialObservacionHistorialRepositorio getHistorialObservacionHistorialRepositorio() {
		return historialObservacionHistorialRepositorio;
	}

	public void setHistorialObservacionHistorialRepositorio(
			HistorialObservacionHistorialRepositorio historialObservacionHistorialRepositorio) {
		this.historialObservacionHistorialRepositorio = historialObservacionHistorialRepositorio;
	}

}
