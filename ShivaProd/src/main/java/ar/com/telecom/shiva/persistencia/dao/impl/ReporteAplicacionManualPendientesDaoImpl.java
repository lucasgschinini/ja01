package ar.com.telecom.shiva.persistencia.dao.impl;

import java.math.BigDecimal;
import java.sql.Types;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteAplicacionManualPendientes;
import ar.com.telecom.shiva.persistencia.dao.Dao;
import ar.com.telecom.shiva.persistencia.dao.IReporteAplicacionManualPendienteDao;
import ar.com.telecom.shiva.persistencia.dao.util.QueryParametrosUtil;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteReporteAplicacionManualPendientesFiltro;

/**
 * 
 * @author U587496 Guido.Settecerze
 *
 */

public class ReporteAplicacionManualPendientesDaoImpl extends Dao implements IReporteAplicacionManualPendienteDao {
	
	@Override
	public List<VistaSoporteAplicacionManualPendientes> buscarCobros(VistaSoporteReporteAplicacionManualPendientesFiltro filtro) throws PersistenciaExcepcion {
		try {
			
			QueryParametrosUtil qp = obtenerTareasPendienteConfirmacion(filtro);
			List<Map<String, Object>> listaResultadoQuery = buscarUsandoSQL(qp);
			
			List<VistaSoporteAplicacionManualPendientes> listaResultado = new ArrayList<VistaSoporteAplicacionManualPendientes>();
			
			if(Validaciones.isCollectionNotEmpty(listaResultadoQuery)){
				
				for (Map<String, Object> registro : listaResultadoQuery) {
					
					VistaSoporteAplicacionManualPendientes resultado = new VistaSoporteAplicacionManualPendientes();
					
					if (!Validaciones.isObjectNull(registro.get("FECHA_MODIFICACION"))){
						
						resultado.setFechaCreacionTareaAprobacion((Date) registro.get("FECHA_MODIFICACION"));
						
					}
					
					if (!Validaciones.isObjectNull(registro.get("NOMBRE_ARCHIVO"))){
						
						resultado.setNombreArchivo(registro.get("NOMBRE_ARCHIVO").toString());
						
					}
					
					if (!Validaciones.isObjectNull(registro.get("CUIT"))){
						
						resultado.setCuit(registro.get("CUIT").toString());
						
					}
					
					if (!Validaciones.isObjectNull(registro.get("TIPO_OPERACION"))){
						
						resultado.setTipoOperacion(registro.get("TIPO_OPERACION").toString());
						
					}
					
					if (!Validaciones.isObjectNull(registro.get("MONEDA"))){
						
						resultado.setMoneda(registro.get("MONEDA").toString());
						
					}
					
					if (!Validaciones.isObjectNull(registro.get("MONTO_IMPUTAR_MONEDA_ORIGEN"))){
						
						resultado.setMontoImputarEnMonedaOrigen((BigDecimal) registro.get("MONTO_IMPUTAR_MONEDA_ORIGEN"));
						
					}

					if (!Validaciones.isObjectNull(registro.get("TIPO_CAMBIO"))){
						
						resultado.setTipoDeCambio((BigDecimal) registro.get("TIPO_CAMBIO"));
						
					}
					
					if (!Validaciones.isObjectNull(registro.get("MONTO_IMPUTAR_PESOS"))){
						
						resultado.setMontoImputarEnPesos((BigDecimal) registro.get("MONTO_IMPUTAR_PESOS"));
						
					}
					
					if (!Validaciones.isObjectNull(registro.get("ID_TRANSACCION_COBRO"))){
						
						resultado.setIdTransaccionCobro(registro.get("ID_TRANSACCION_COBRO").toString());
						
					}
					
					listaResultado.add(resultado);
					
				}
			}
			
			return listaResultado;
			
		} catch (Exception e) {
			throw new PersistenciaExcepcion(e.getMessage(), e);
		}

	}	
	
	/**
	 * @author U587496 Guido.Settecerze
	 * @return
	 * @throws ParseException 
	 */
	private QueryParametrosUtil obtenerTareasPendienteConfirmacion(VistaSoporteReporteAplicacionManualPendientesFiltro filtro) throws ParseException {
		
		StringBuffer consulta = new StringBuffer("");
		QueryParametrosUtil qp = new QueryParametrosUtil();
		
		consulta.append("(select wf.fecha_modificacion ");
		consulta.append("	  , archivo.nombre_archivo ");
		consulta.append("	  , detalle.cuit ");
		consulta.append("	  , detalle.tipo_operacion ");
		consulta.append("	  , detalle.moneda ");
		consulta.append("	  , detalle.monto_imputar_moneda_origen ");
		consulta.append("	  , detalle.tipo_cambio ");
		consulta.append("	  , detalle.monto_imputar_pesos ");
		consulta.append("	  , detalle.id_transaccion_cobro ");
		consulta.append("	  , detalle.sistema ");
		consulta.append(" from shv_cob_aplic_manual_batch_det detalle, ");
		consulta.append("      shv_arc_archivo archivo, ");
		consulta.append("	  shv_sop_cobros_grilla_transac grilla, ");
		consulta.append("	  shv_cob_cobro cob, ");
		consulta.append("	  shv_wf_workflow_estado wf ");
		consulta.append(" where detalle.id_archivo = archivo.id_archivo ");
		consulta.append("  and detalle.id_transaccion = grilla.id_transaccion ");
		consulta.append("  and grilla.id_cobro=cob.id_cobro ");
		consulta.append("  and cob.id_workflow=wf.id_workflow ");
		consulta.append("  and wf.estado='COB_PENDIENTE_CONFIRMACION_MANUAL' ");
		consulta.append("  and sistema_origen_documento is not null ");
		consulta.append("  and wf.fecha_modificacion <= to_date( ? , ? )  ");
		consulta.append("  and detalle.estado_tarea = ? ");
		consulta.append("  and detalle.sistema = ?) ");
		consulta.append("union ");
		consulta.append("(select wf.fecha_modificacion ");
		consulta.append(" 	  , archivo.nombre_archivo ");
		consulta.append("	  , detalle.cuit ");
		consulta.append("	  , detalle.tipo_operacion ");
		consulta.append("	  , detalle.moneda ");
		consulta.append("	  , detalle.monto_imputar_moneda_origen ");
		consulta.append("	  , detalle.tipo_cambio ");
		consulta.append("	  , detalle.monto_imputar_pesos ");
		consulta.append("	  , detalle.id_transaccion_cobro ");
		consulta.append("	  , detalle.sistema ");
		consulta.append(" from shv_cob_aplic_manual_batch_det detalle, ");
		consulta.append("      shv_arc_archivo archivo, ");
		consulta.append("	  shv_sop_descobros_grilla_trans grilla, ");
		consulta.append("	  shv_cob_descobro descob, ");
		consulta.append("	  shv_wf_workflow_estado wf ");
		consulta.append(" where detalle.id_archivo = archivo.id_archivo ");
		consulta.append("  and detalle.id_transaccion = grilla.id_transaccion ");
		consulta.append("  and grilla.id_descobro=descob.id_descobro ");
		consulta.append("  and descob.id_workflow=wf.id_workflow ");
		consulta.append("  and wf.estado='DES_PENDIENTE_CONFIRMACION_MANUAL' ");
		consulta.append("  and sistema_origen_documento is not null ");
		consulta.append("  and wf.fecha_modificacion <= to_date( ? , ? )  ");
		consulta.append("  and detalle.estado_tarea = ? ");
		consulta.append("  and detalle.sistema = ?) ");
		consulta.append("order by fecha_modificacion, nombre_archivo, id_transaccion_cobro ");
		
		
		

		
		qp.addCampoAlQuery((filtro.getFecha() + " 23:59:59"), Types.VARCHAR);
		qp.addCampoAlQuery(Utilidad.ORACLE_DATE_TIME_FULL_FORMAT, Types.VARCHAR);
		qp.addCampoAlQuery(Constantes.ESTADO_PENDIENTE, Types.VARCHAR);
		qp.addCampoAlQuery(filtro.getSistema(), Types.VARCHAR);
		qp.addCampoAlQuery((filtro.getFecha() + " 23:59:59"), Types.VARCHAR);
		qp.addCampoAlQuery(Utilidad.ORACLE_DATE_TIME_FULL_FORMAT, Types.VARCHAR);
		qp.addCampoAlQuery(Constantes.ESTADO_PENDIENTE, Types.VARCHAR);
		qp.addCampoAlQuery(filtro.getSistema(), Types.VARCHAR);
		
		qp.setSql(consulta.toString());

		return qp;
		
	}
	
}