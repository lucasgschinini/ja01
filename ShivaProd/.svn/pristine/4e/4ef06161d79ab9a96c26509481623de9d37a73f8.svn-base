package ar.com.telecom.shiva.persistencia.dao.impl;

import java.util.List;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.persistencia.dao.Dao;
import ar.com.telecom.shiva.persistencia.dao.IOperacionTruncaDao;
import ar.com.telecom.shiva.persistencia.dao.util.QueryParametrosUtil;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobCobro;
import ar.com.telecom.shiva.presentacion.bean.filtro.OperacionTruncaFiltro;

public class OperacionesTruncasDaoImpl extends Dao implements IOperacionTruncaDao {

//	TODO se comenta para volver a la version anterior
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<ShvCobCobro> buscarOperacionesTruncas(
//			OperacionTruncaFiltro opeTruncaFiltro) throws PersistenciaExcepcion {
//		try {
//			
//			QueryParametrosUtil qp = new QueryParametrosUtil(
//						"select scc from ShvCobCobro scc join scc.descobro.workflow.shvWfWorkflowEstado as swfe where swfe.estado in "
//						+ "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) "
//						+ " and swfe.fechaModificacion <= TO_TIMESTAMP( ? , ? ) "
//						+ " order by scc.operacion.idOperacion");
//			
//			qp.addParametro(Estado.DES_AMBOS_COBRADORES);
//			qp.addParametro(Estado.DES_DOCUMENTOS_COBRADORES_MEDIOS_MIC);
//			qp.addParametro(Estado.DES_DOCUMENTOS_COBRADORES_MEDIOS_CALIPSO);
//			qp.addParametro(Estado.DES_DOCUMENTO_MIC_MEDIOS_COBRADORES);
//			qp.addParametro(Estado.DES_DOCUMENTO_CALIPSO_MEDIOS_COBRADORES);
//			qp.addParametro(Estado.DES_DOCUMENTO_COBRADORES);
//			qp.addParametro(Estado.DES_MEDIOS_COBRADORES);
//			qp.addParametro(Estado.DES_AMBOS_CALIPSO);
//			qp.addParametro(Estado.DES_DOCUMENTO_CALIPSO);
//			qp.addParametro(Estado.DES_MEDIOS_CALIPSO);
//			qp.addParametro(Estado.DES_AMBOS_MIC);
//			qp.addParametro(Estado.DES_MEDIOS_MIC);
//			qp.addParametro(Estado.DES_DOCUMENTO_MIC);
//			qp.addParametro(new String(opeTruncaFiltro.getFechaHasta() + " 23:59:59"));
//			qp.addParametro(new String(Utilidad.ORACLE_DATE_TIME_FULL_FORMAT));
//			
//			List<ShvCobCobro> lista = (List<ShvCobCobro>) buscarUsandoQueryConParametros(qp);
//			
//			qp = new QueryParametrosUtil(
//					"select scc from ShvCobCobro scc join scc.descobro.workflow.shvWfWorkflowEstadoHist as swfe"
//					+ " where swfe.estado in(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) "
//					+ " and swfe.fechaModificacion <= TO_TIMESTAMP( ? , ? ) "
//					+ " and swfe.workflow not in( "
//						+ "select swfen.workflow from ShvCobCobro sccn join sccn.descobro.workflow.shvWfWorkflowEstado as swfen where swfen.estado in "
//						+ "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) "
//						+ " and swfen.fechaModificacion <= TO_TIMESTAMP( ? , ? ) "
//					+ " )"
//					+ " and swfe.idWorkflowEstadoHist in("
//						+ " select max(swfeh.idWorkflowEstadoHist) "
//						+ " from ShvCobCobro scch "
//						+ " join scch.descobro.workflow.shvWfWorkflowEstadoHist as swfeh "
//						+ " where swfeh.estado in (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) "
//						+ " and swfeh.fechaModificacion <= TO_TIMESTAMP( ? , ? ) "
//						+ " and swfeh.workflow = swfe.workflow"
//					+ ")"
//					+ " order by scc.operacion.idOperacion");
//		
//			qp.addParametro(Estado.DES_AMBOS_COBRADORES);
//			qp.addParametro(Estado.DES_DOCUMENTOS_COBRADORES_MEDIOS_MIC);
//			qp.addParametro(Estado.DES_DOCUMENTOS_COBRADORES_MEDIOS_CALIPSO);
//			qp.addParametro(Estado.DES_DOCUMENTO_MIC_MEDIOS_COBRADORES);
//			qp.addParametro(Estado.DES_DOCUMENTO_CALIPSO_MEDIOS_COBRADORES);
//			qp.addParametro(Estado.DES_DOCUMENTO_COBRADORES);
//			qp.addParametro(Estado.DES_MEDIOS_COBRADORES);
//			qp.addParametro(Estado.DES_AMBOS_CALIPSO);
//			qp.addParametro(Estado.DES_DOCUMENTO_CALIPSO);
//			qp.addParametro(Estado.DES_MEDIOS_CALIPSO);
//			qp.addParametro(Estado.DES_AMBOS_MIC);
//			qp.addParametro(Estado.DES_MEDIOS_MIC);
//			qp.addParametro(Estado.DES_DOCUMENTO_MIC);
//			qp.addParametro(new String(opeTruncaFiltro.getFechaHasta() + " 23:59:59"));
//			qp.addParametro(new String(Utilidad.ORACLE_DATE_TIME_FULL_FORMAT));
//			qp.addParametro(Estado.DES_AMBOS_COBRADORES);
//			qp.addParametro(Estado.DES_DOCUMENTOS_COBRADORES_MEDIOS_MIC);
//			qp.addParametro(Estado.DES_DOCUMENTOS_COBRADORES_MEDIOS_CALIPSO);
//			qp.addParametro(Estado.DES_DOCUMENTO_MIC_MEDIOS_COBRADORES);
//			qp.addParametro(Estado.DES_DOCUMENTO_CALIPSO_MEDIOS_COBRADORES);
//			qp.addParametro(Estado.DES_DOCUMENTO_COBRADORES);
//			qp.addParametro(Estado.DES_MEDIOS_COBRADORES);
//			qp.addParametro(Estado.DES_AMBOS_CALIPSO);
//			qp.addParametro(Estado.DES_DOCUMENTO_CALIPSO);
//			qp.addParametro(Estado.DES_MEDIOS_CALIPSO);
//			qp.addParametro(Estado.DES_AMBOS_MIC);
//			qp.addParametro(Estado.DES_MEDIOS_MIC);
//			qp.addParametro(Estado.DES_DOCUMENTO_MIC);
//			qp.addParametro(new String(opeTruncaFiltro.getFechaHasta() + " 23:59:59"));
//			qp.addParametro(new String(Utilidad.ORACLE_DATE_TIME_FULL_FORMAT));
//			qp.addParametro(Estado.DES_AMBOS_COBRADORES);
//			qp.addParametro(Estado.DES_DOCUMENTOS_COBRADORES_MEDIOS_MIC);
//			qp.addParametro(Estado.DES_DOCUMENTOS_COBRADORES_MEDIOS_CALIPSO);
//			qp.addParametro(Estado.DES_DOCUMENTO_MIC_MEDIOS_COBRADORES);
//			qp.addParametro(Estado.DES_DOCUMENTO_CALIPSO_MEDIOS_COBRADORES);
//			qp.addParametro(Estado.DES_DOCUMENTO_COBRADORES);
//			qp.addParametro(Estado.DES_MEDIOS_COBRADORES);
//			qp.addParametro(Estado.DES_AMBOS_CALIPSO);
//			qp.addParametro(Estado.DES_DOCUMENTO_CALIPSO);
//			qp.addParametro(Estado.DES_MEDIOS_CALIPSO);
//			qp.addParametro(Estado.DES_AMBOS_MIC);
//			qp.addParametro(Estado.DES_MEDIOS_MIC);
//			qp.addParametro(Estado.DES_DOCUMENTO_MIC);
//			qp.addParametro(new String(opeTruncaFiltro.getFechaHasta() + " 23:59:59"));
//			qp.addParametro(new String(Utilidad.ORACLE_DATE_TIME_FULL_FORMAT));
//
//			lista.addAll((List<ShvCobCobro>) buscarUsandoQueryConParametros(qp));
//			
//			return lista;
//
//		} catch (Throwable e) {
//			throw new PersistenciaExcepcion(e);
//		}
//	}
	
//	TODO se activa para volver atras
	@SuppressWarnings("unchecked")
	@Override
	public List<ShvCobCobro> buscarOperacionesTruncas(
			OperacionTruncaFiltro opeTruncaFiltro) throws PersistenciaExcepcion {
		try {
			
			QueryParametrosUtil qp = new QueryParametrosUtil(
						"select scc from ShvCobDescobro as descobro "
						+ "join descobro.workflow.shvWfWorkflowEstado as we, "
						+ "ShvCobCobro as scc "
						+ "where we.estado in (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) "
						+ "and descobro.idCobro=scc.idCobro "
						+ "order by scc.operacion.idOperacion");
			
			qp.addParametro(Estado.DES_AMBOS_COBRADORES);
			qp.addParametro(Estado.DES_DOCUMENTOS_COBRADORES_MEDIOS_MIC);
			qp.addParametro(Estado.DES_DOCUMENTOS_COBRADORES_MEDIOS_CALIPSO);
			qp.addParametro(Estado.DES_DOCUMENTO_MIC_MEDIOS_COBRADORES);
			qp.addParametro(Estado.DES_DOCUMENTO_CALIPSO_MEDIOS_COBRADORES);
			qp.addParametro(Estado.DES_DOCUMENTO_COBRADORES);
			qp.addParametro(Estado.DES_MEDIOS_COBRADORES);
			qp.addParametro(Estado.DES_AMBOS_CALIPSO);
			qp.addParametro(Estado.DES_DOCUMENTO_CALIPSO);
			qp.addParametro(Estado.DES_MEDIOS_CALIPSO);
			qp.addParametro(Estado.DES_AMBOS_MIC);
			qp.addParametro(Estado.DES_MEDIOS_MIC);
			qp.addParametro(Estado.DES_DOCUMENTO_MIC);

			List<ShvCobCobro> lista = (List<ShvCobCobro>) buscarUsandoQueryConParametros(qp);
			
			return lista;

		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

}
