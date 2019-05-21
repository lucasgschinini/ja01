
package ar.com.telecom.shiva.persistencia.dao.impl;

import java.util.List;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.persistencia.dao.Dao;
import ar.com.telecom.shiva.persistencia.dao.IOperacionMasivaDao;
import ar.com.telecom.shiva.persistencia.dao.util.QueryParametrosUtil;
import ar.com.telecom.shiva.persistencia.modelo.ShvMasOperacionMasiva;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvMasOperacionMasivaSimplificado;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvMasOperacionMasivaSimplificadoWorkFlow;
import ar.com.telecom.shiva.persistencia.repository.OperacionMasivaRepositorio;
import ar.com.telecom.shiva.persistencia.repository.OperacionMasivaSimplificadoRepositorio;

public class OperacionMasivaDaoImpl extends Dao implements IOperacionMasivaDao {

	@Autowired
	OperacionMasivaRepositorio repositorio;
	@Autowired
	OperacionMasivaSimplificadoRepositorio repositorioSimplificado;

	@Override
	public ShvMasOperacionMasiva crear(ShvMasOperacionMasiva operacionMasiva) throws PersistenciaExcepcion {
		try {
			ShvMasOperacionMasiva masiva = repositorio.save(operacionMasiva);
			repositorio.flush();
			return masiva;
		} catch (Throwable t) {
			throw new PersistenceException(t.getMessage(), t);
		}
	}

	@Override
	public ShvMasOperacionMasiva modificar(ShvMasOperacionMasiva operacionMasiva) throws PersistenciaExcepcion {
		try {
			ShvMasOperacionMasiva masiva = repositorio.save(operacionMasiva);
			repositorio.flush();
			return masiva;
		} catch (Throwable t) {
			throw new PersistenceException(t.getMessage(), t);
		}
	}

	/**
	 * @author u572487 Guido.Settecerze
	 * Busca una operacion masiva en la base.
	 */
	public ShvMasOperacionMasiva buscarOperacionMasiva(Long id) throws PersistenciaExcepcion {
		try {
			return repositorio.findOne(id);
			
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	/**
	 * Busca todas las Operaciones Masivas segund un estado del workflow
	 */

	@SuppressWarnings("unchecked")
	public List<ShvMasOperacionMasiva> buscarOperacionesMasivasByEstado(Estado estado) throws PersistenciaExcepcion {
		List<ShvMasOperacionMasiva> registros;
		try {
			StringBuffer query = new StringBuffer();
			query.append("select mas from ShvMasOperacionMasiva as mas left join mas.workFlow ");
			query.append("left join mas.workFlow.shvWfWorkflowEstado workflowEstado where workflowEstado.estado in (?) ");

			QueryParametrosUtil qp = new QueryParametrosUtil(query.toString());
			qp.addParametro(estado);
			
			registros = (List<ShvMasOperacionMasiva>) buscarUsandoQueryConParametros(qp);
			return registros;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<ShvMasOperacionMasivaSimplificadoWorkFlow> buscarOperacionesMasivasSinRegistroEnProcesoByEstado(List<Estado> estados) throws PersistenciaExcepcion {
		List<ShvMasOperacionMasivaSimplificadoWorkFlow> registros;
		try {
			StringBuffer strFiltroEstadoOperacionMasiva = new StringBuffer("(");
			for (int index = 0; index < estados.size(); index++) {
				if (strFiltroEstadoOperacionMasiva.length() > 1) {
					strFiltroEstadoOperacionMasiva.append(", ?");
				} else {
					strFiltroEstadoOperacionMasiva.append("?");
				}
			}
			strFiltroEstadoOperacionMasiva.append(")");
			StringBuffer query = new StringBuffer();
			query.append("select mas from ShvMasOperacionMasivaSimplificadoWorkFlow as mas left join mas.workFlow ");
			query.append("left join mas.workFlow.shvWfWorkflowEstado workflowEstado where workflowEstado.estado in ");
			query.append(strFiltroEstadoOperacionMasiva.toString());
			query.append(" and mas.cantRegistrosEnProceso = 0 and mas.cantRegistros = mas.cantRegistrosError + mas.cantRegistrosProcesados ");
			QueryParametrosUtil qp = new QueryParametrosUtil(query.toString());
			for (Estado estado : estados) {
				qp.addParametro(estado);
			}
			
			registros = (List<ShvMasOperacionMasivaSimplificadoWorkFlow>) buscarUsandoQueryConParametros(qp);
			return registros;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	@Override
	@SuppressWarnings("unchecked")
	public List<ShvMasOperacionMasivaSimplificado> calcularCantidadesDeRegistros(List<Estado> estados) throws PersistenciaExcepcion {
		List<ShvMasOperacionMasivaSimplificado> registros;
		try {
			StringBuffer strFiltroEstadoOperacionMasiva = new StringBuffer("(");
			
			for (int index = 0; index < estados.size(); index++) {
				if (strFiltroEstadoOperacionMasiva.length() > 1) {
					strFiltroEstadoOperacionMasiva.append(", ?");
				} else {
					strFiltroEstadoOperacionMasiva.append("?");
				}
			}
			strFiltroEstadoOperacionMasiva.append(")");
			String queryCantRegistrosProcesados = "select COUNT(*) from ShvMasRegistro as reg left join reg.operacionMasiva as rmas Where rmas.idOperacionMasiva = mas.idOperacionMasiva AND reg.estado IN ('PROCESADO') AND reg.estado != 'ERROR'";
			String queryCantRegistrosError = "select COUNT(*) from ShvMasRegistro as reg left join reg.operacionMasiva as rmas Where rmas.idOperacionMasiva = mas.idOperacionMasiva AND reg.estado IN ('ERROR_MIC', 'ERROR_SIEBEL', 'ERROR_SHIVA') AND reg.estado != 'ERROR'";
			String queryCantRegistros = "select COUNT(*) from ShvMasRegistro as reg left join reg.operacionMasiva as rmas Where rmas.idOperacionMasiva = mas.idOperacionMasiva AND reg.estado != 'ERROR'";
			String queryCantRegistrosEnProceso = "select COUNT(*) from ShvMasRegistro as reg left join reg.operacionMasiva as rmas Where rmas.idOperacionMasiva = mas.idOperacionMasiva AND reg.estado IN ('PROCESO_IMPUTACION') AND reg.estado != 'ERROR'"; 
			
			StringBuffer query = new StringBuffer();
			query.append("select new ar.com.telecom.shiva.persistencia.modelo.simple.ShvMasOperacionMasivaSimplificado(mas.idOperacionMasiva,(");
			query.append(queryCantRegistrosProcesados);
			query.append("), (");
			query.append(queryCantRegistrosError);
			query.append("), (");
			query.append(queryCantRegistros);
			query.append("), (");
			query.append(queryCantRegistrosEnProceso);
			query.append(") ) from ShvMasOperacionMasiva as mas left join mas.workFlow ");
			query.append("left join mas.workFlow.shvWfWorkflowEstado workflowEstado where workflowEstado.estado in ");
			query.append(strFiltroEstadoOperacionMasiva.toString());  

			QueryParametrosUtil qp = new QueryParametrosUtil(query.toString());
			for (Estado estado : estados) {
				qp.addParametro(estado);
			}
			
			registros = (List<ShvMasOperacionMasivaSimplificado>) buscarUsandoQueryConParametros(qp);
			return registros;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	@Override
	public ShvMasOperacionMasivaSimplificado modificar(ShvMasOperacionMasivaSimplificado operacionMasiva) throws PersistenciaExcepcion {
		try {
			ShvMasOperacionMasivaSimplificado masiva = repositorioSimplificado.save(operacionMasiva);
			repositorio.flush();
			return masiva;
		} catch (Throwable t) {
			throw new PersistenceException(t.getMessage(), t);
		}
	}
	@Override
	public List<ShvMasOperacionMasivaSimplificado> modificar(List<ShvMasOperacionMasivaSimplificado> list) throws PersistenciaExcepcion {
		try {
			List<ShvMasOperacionMasivaSimplificado> masiva = repositorioSimplificado.save(list);
			repositorio.flush();
			return masiva;
		} catch (Throwable t) {
			throw new PersistenceException(t.getMessage(), t);
		}
	}
	
}
