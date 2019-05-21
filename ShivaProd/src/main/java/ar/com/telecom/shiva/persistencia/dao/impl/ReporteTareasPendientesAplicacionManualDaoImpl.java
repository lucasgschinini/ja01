package ar.com.telecom.shiva.persistencia.dao.impl;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.enumeradores.EstadoTransaccionEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.SociedadEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTareaEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteTareasPendientesAplicacionManual;
import ar.com.telecom.shiva.persistencia.dao.Dao;
import ar.com.telecom.shiva.persistencia.dao.IReporteTareasPendientesAplicacionManualDao;
import ar.com.telecom.shiva.persistencia.dao.util.QueryParametrosUtil;
import ar.com.telecom.shiva.persistencia.repository.CobroAplicacionManualBatchDetalleRepositorio;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteReporteTareasPendientesAplicacionManualFiltro;

public class ReporteTareasPendientesAplicacionManualDaoImpl extends Dao implements IReporteTareasPendientesAplicacionManualDao {

		@Autowired
		CobroAplicacionManualBatchDetalleRepositorio cobroAplicacionManualBatchDetalleRepositorio;

		@Override
		public List<VistaSoporteTareasPendientesAplicacionManual> buscarTareasPendientesAplicacionManual(VistaSoporteReporteTareasPendientesAplicacionManualFiltro filtro) throws PersistenciaExcepcion {
			try{
				
				QueryParametrosUtil qp = obtenerTareasPendienteConfirmacion(filtro);
				List<Map<String, Object>> listaResultadoQuery = buscarUsandoSQL(qp);
				
				List<VistaSoporteTareasPendientesAplicacionManual> listaResultado = new ArrayList<VistaSoporteTareasPendientesAplicacionManual>();
				
				if(Validaciones.isCollectionNotEmpty(listaResultadoQuery)){
					for (Map<String, Object> registro : listaResultadoQuery) {
						
						VistaSoporteTareasPendientesAplicacionManual resultado = new VistaSoporteTareasPendientesAplicacionManual();
						String traza = "Se encontro el siguiente registro para agregar al archivo de la sociedad "+filtro.getSociedad()+" del sistema " +filtro.getSistema()+ ": ";
						
						if (!Validaciones.isObjectNull(registro.get("TIPO_OPERACION"))){
							resultado.setTipoOperacion(registro.get("TIPO_OPERACION").toString());
							traza += " TIPO_OPERACION: " + resultado.getTipoOperacion();
						}
						if (!Validaciones.isObjectNull(registro.get("CUIT"))){
							resultado.setCuit(registro.get("CUIT").toString());
							traza += " CUIT: " + resultado.getCuit();
						}
						if (!Validaciones.isObjectNull(registro.get("MONEDA"))){
							resultado.setMoneda(registro.get("MONEDA").toString());
							traza += " MONEDA: " + resultado.getMoneda();
						}
						
						if(Constantes.MONEDA_DOL.equals(resultado.getMoneda())){
							if (!Validaciones.isObjectNull(registro.get("MONTO_IMPORTE_MONEDA_ORIGEN"))){
								resultado.setMontoImputarEnMonedaOrigen((BigDecimal) registro.get("MONTO_IMPORTE_MONEDA_ORIGEN"));
								traza += " MONTO_IMPORTE_MONEDA_ORIGEN: " + resultado.getMontoImputarEnMonedaOrigen();
							}
							if (!Validaciones.isObjectNull(registro.get("TIPO_CAMBIO"))){
								resultado.setTipoCambio((BigDecimal) registro.get("TIPO_CAMBIO"));
								traza += " TIPO_CAMBIO: " + resultado.getTipoCambio();
							}
						}
						
						if (!Validaciones.isObjectNull(registro.get("MONTO_IMPORTE_PESOS"))){
							resultado.setMontoImputarEnPesos((BigDecimal) registro.get("MONTO_IMPORTE_PESOS"));
							traza += " MONTO_IMPORTE_PESOS: " + resultado.getMontoImputarEnPesos();
						}
						if (!Validaciones.isObjectNull(registro.get("ID_TRANSACCION_COBRO"))){
							resultado.setIdTransaccionShivaDeCobro(registro.get("ID_TRANSACCION_COBRO").toString());
							traza += " ID_TRANSACCION_COBRO: " + resultado.getIdTransaccionShivaDeCobro();
						}
						if (!Validaciones.isObjectNull(registro.get("NUMERO_TRANSACCION_FICTICIO"))){
							resultado.setNumeroTransaccionFicticio(registro.get("NUMERO_TRANSACCION_FICTICIO").toString());
							traza += " NUMERO_TRANSACCION_FICTICIO: " + resultado.getNumeroTransaccionFicticio();
						}
						if (!Validaciones.isObjectNull(registro.get("ID_TRANSACCION"))){
							resultado.setIdTransaccion((registro.get("ID_TRANSACCION")).toString());
							traza += " ID_TRANSACCION: " + resultado.getIdTransaccion();
						}
						if (!Validaciones.isObjectNull(registro.get("FECHA_TAREA"))){
							resultado.setFechaCreacionTarea((Date) registro.get("FECHA_TAREA"));
							traza += " FECHA_TAREA: " + resultado.getFechaCreacionTarea();
						}
						
						if (!Validaciones.isObjectNull(registro.get("ID_TRANSACCION_DESCOBRO"))){
							resultado.setIdTransaccionDescobro(registro.get("ID_TRANSACCION_DESCOBRO").toString());
							traza += " ID_TRANSACCION_DESCOBRO: " + resultado.getIdTransaccionDescobro();
						}
						
						if (!Validaciones.isObjectNull(registro.get("ID_COBRO"))) {
							resultado.setIdCobro(registro.get("ID_COBRO").toString());
							traza += " ID_COBRO: " + resultado.getIdCobro();
						}
						
						if (!Validaciones.isObjectNull(registro.get("ID_DESCOBRO"))) {
							resultado.setIdDescobro(registro.get("ID_DESCOBRO").toString());
							traza += " ID_DESCOBRO: " + resultado.getIdDescobro();
						}
						
						if (!Validaciones.isObjectNull(registro.get("ID_OPERACION_COBRO"))) {
							resultado.setIdOperacion(registro.get("ID_OPERACION_COBRO").toString());
							traza += " ID_OPERACION: " + resultado.getIdOperacion();
						}
						
						if (!Validaciones.isObjectNull(registro.get("ID_OPERACION_DESCOBRO"))) {
							resultado.setIdOperacionDescobro(registro.get("ID_OPERACION_DESCOBRO").toString());
							traza += " ID_OPERACION_DESCOBRO: " + resultado.getIdOperacionDescobro();
						}
						if(!Validaciones.isObjectNull(registro.get("SISTEMA"))){
							resultado.setSistema((SistemaEnum.getEnumByName(registro.get("SISTEMA").toString())).getDescripcion());
						}
						if(!Validaciones.isObjectNull(registro.get("SOCIEDAD"))){
							resultado.setSociedad((SociedadEnum.getEnumByName(registro.get("SOCIEDAD").toString())).getDescripcion());
						}
						
						listaResultado.add(resultado);
						Traza.advertencia(getClass(), traza);
					}
				}
				
				return listaResultado;
				
			} catch (Exception e) {
				throw new PersistenciaExcepcion(e.getMessage(), e);
			}

		}
		
	private QueryParametrosUtil obtenerTareasPendienteConfirmacion(VistaSoporteReporteTareasPendientesAplicacionManualFiltro filtro) throws NegocioExcepcion {
			
			StringBuffer consulta = new StringBuffer("");
			QueryParametrosUtil qp = new QueryParametrosUtil();
			consulta.append("((SELECT   ");
			consulta.append("  'COB'                                  AS TIPO_OPERACION,");
			consulta.append("  cliente.cuit                           AS CUIT,");
			consulta.append("  grilla.id_cobro                        AS ID_COBRO,");
			consulta.append("  grilla.id_operacion                    AS ID_OPERACION_COBRO,");
			consulta.append("  NULL                                   AS ID_DESCOBRO,");
			consulta.append("  NULL                                   AS ID_OPERACION_DESCOBRO,");
			consulta.append("  grilla.moneda                          AS MONEDA,");
			consulta.append("  grilla.importe_aplic_fec_emis_mon_ori  AS MONTO_IMPORTE_MONEDA_ORIGEN,");
			consulta.append("  grilla.tipo_de_cambio_fecha_cobro      AS TIPO_CAMBIO,");
			consulta.append("  grilla.importe_a_cobrar                AS MONTO_IMPORTE_PESOS,");
			consulta.append("  grilla.numero_transaccion_formateado   AS ID_TRANSACCION_COBRO,");
			consulta.append("  grilla.nro_trans_ficticio_formateado AS NUMERO_TRANSACCION_FICTICIO,");
			consulta.append("  grilla.id_transaccion                  AS ID_TRANSACCION,");
			consulta.append("  TAREA.fecha_CREACION                   AS FECHA_TAREA,");
			consulta.append("  grilla.id_sociedad                     AS SOCIEDAD,");
			consulta.append("  grilla.sistema                         AS SISTEMA,");
			consulta.append("  NULL                                   AS ID_TRANSACCION_DESCOBRO");
			consulta.append("  FROM");
			consulta.append("  SHV_SOP_COBROS_GRILLA_TRANSAC grilla,");
			consulta.append("  SHV_WF_TAREA tarea,");
			consulta.append("  SHV_COB_ED_CLIENTE cliente,");
			consulta.append("  shv_cob_cobro cob ");
			consulta.append("  WHERE grilla.ID_COBRO=cliente.ID_COBRO");
			consulta.append("  AND grilla.id_cobro=cob.id_cobro");
			consulta.append("  AND grilla.ID_CLIENTE_LEGADO_DOCUMENTO=cliente.ID_CLIENTE_LEGADO");
			consulta.append("  AND grilla.id_sociedad=?");  
			consulta.append("  AND grilla.sistema=?");
			consulta.append("  AND grilla.ESTADO_TRANSACCION=?");
			consulta.append("  AND TAREA.SOCIEDAD=grilla.id_sociedad");
			consulta.append("  AND tarea.sistema=grilla.sistema");
			consulta.append("  AND tarea.id_workflow=cob.id_workflow");
			consulta.append("  AND tarea.tipo_tarea=?");
			consulta.append("  AND grilla.id_factura is not null)");
			consulta.append("  UNION");
			consulta.append("  (SELECT");
			consulta.append("  'RCO'                                 	 AS TIPO_OPERACION,");
			consulta.append("  cliente.cuit                          	 AS CUIT,");
			consulta.append("  NULL                                  	 AS ID_COBRO,");
			consulta.append("  NULL                                  	 AS ID_OPERACION_COBRO,");
			consulta.append("  grilla.id_descobro                    	 AS ID_DESCOBRO,");
			consulta.append("  grilla.id_operacion                   	 AS ID_OPERACION_DESCOBRO,");
			consulta.append("  grilla.moneda       		              	 AS MONEDA,");
			consulta.append("  grilla.importe_apl_fec_emi_mon_ori_mp 	 AS MONTO_IMPORTE_MONEDA_ORIGEN,");
			consulta.append("  grilla.tipo_de_cambio_fecha_cobro	     AS TIPO_CAMBIO,");
			consulta.append("  grilla.importe_a_cobrar                   AS MONTO_IMPORTE_PESOS,");
			consulta.append("  grilla.numero_transaccion_formateado      AS ID_TRANSACCION_COBRO,"); 
			consulta.append("  grilla.nro_trans_ficticio_formateado AS NUMERO_TRANSACCION_FICTICIO,");    
			consulta.append("  grilla.id_transaccion                     AS ID_TRANSACCION,");  
			consulta.append("  TAREA.fecha_CREACION                      AS FECHA_TAREA,");
			consulta.append("  grilla.id_sociedad                        AS SOCIEDAD,");
			consulta.append("  grilla.sistema                            AS SISTEMA,");
			consulta.append("  grilla.numero_transaccion_formateado      AS ID_TRANSACCION_DESCOBRO");
			consulta.append("  FROM");
			consulta.append("  shv_sop_descobros_grilla_trans grilla,");
			consulta.append("  shv_cob_descobro descob,");
			consulta.append("  shv_cob_ed_cliente cliente,");
			consulta.append("  SHV_WF_TAREA tarea");
			consulta.append("  WHERE");
			consulta.append("  grilla.id_descobro=descob.id_descobro");
			consulta.append("  AND descob.id_cobro=cliente.id_cobro");
			consulta.append("  AND grilla.id_sociedad=?");  
			consulta.append("  AND grilla.sistema=?");
			consulta.append("  AND grilla.estado_transaccion=?");
			consulta.append("  AND TAREA.SOCIEDAD=grilla.id_sociedad");
			consulta.append("  AND tarea.sistema=grilla.sistema");
			consulta.append("  AND tarea.id_workflow=descob.id_workflow");
			consulta.append("  AND tarea.tipo_tarea=?");
			consulta.append("  AND grilla.id_factura is not null)");
			consulta.append("  UNION");
			consulta.append("  (SELECT");
			consulta.append("  'COB'                                  AS TIPO_OPERACION,");
			consulta.append("  NULL								      AS CUIT,");
			consulta.append("  grilla.id_cobro                        AS ID_COBRO,");
			consulta.append("  grilla.id_operacion                    AS ID_OPERACION_COBRO,");
			consulta.append("  NULL                                   AS ID_DESCOBRO,");
			consulta.append("  NULL                                   AS ID_OPERACION_DESCOBRO,");
			consulta.append("  grilla.moneda                          AS MONEDA,");
			consulta.append("  grilla.importe_aplic_fec_emis_mon_ori  AS MONTO_IMPORTE_MONEDA_ORIGEN,");
			consulta.append("  grilla.tipo_de_cambio_fecha_cobro      AS TIPO_CAMBIO,");
			consulta.append("  grilla.importe_a_cobrar                AS MONTO_IMPORTE_PESOS,");
			consulta.append("  grilla.numero_transaccion_formateado   AS ID_TRANSACCION_COBRO,"); 
			consulta.append("  grilla.nro_trans_ficticio_formateado AS NUMERO_TRANSACCION_FICTICIO,"); 
			consulta.append("  grilla.id_transaccion                  AS ID_TRANSACCION,");
			consulta.append("  TAREA.fecha_CREACION                   AS FECHA_TAREA,");
			consulta.append("  grilla.id_sociedad                     AS SOCIEDAD,");
			consulta.append("  grilla.sistema                         AS SISTEMA,");
			consulta.append("  NULL                                   AS ID_TRANSACCION_DESCOBRO");
			consulta.append("  FROM");
			consulta.append("  SHV_SOP_COBROS_GRILLA_TRANSAC grilla,");
			consulta.append("  SHV_WF_TAREA tarea,");
			consulta.append("  shv_cob_cobro cob");
			consulta.append("  WHERE grilla.id_cobro=cob.id_cobro");
			consulta.append("  AND grilla.id_sociedad=?");
			consulta.append("  AND grilla.sistema=?");
			consulta.append("  AND grilla.ESTADO_TRANSACCION=?");
			consulta.append("  AND TAREA.SOCIEDAD=grilla.id_sociedad");
			consulta.append("  AND tarea.sistema=grilla.sistema");
			consulta.append("  AND tarea.id_workflow=cob.id_workflow");
			consulta.append("  AND tarea.tipo_tarea=?");
			consulta.append("  AND grilla.id_factura is not null");
			consulta.append("  and grilla.id_cliente_legado_documento is null)");
			consulta.append("  UNION");
			consulta.append("  (SELECT");
			consulta.append("  'RCO'                                 	AS TIPO_OPERACION,");
			consulta.append("  NULL		                              	AS CUIT,");
			consulta.append("  NULL                                  	AS ID_COBRO,");
			consulta.append("  NULL                                  	AS ID_OPERACION_COBRO,");
			consulta.append("  grilla.id_descobro                    	AS ID_DESCOBRO,");
			consulta.append("  grilla.id_operacion                   	AS ID_OPERACION_DESCOBRO,");
			consulta.append("  grilla.moneda       		              	AS MONEDA,");
			consulta.append("  grilla.importe_apl_fec_emi_mon_ori_mp 	AS MONTO_IMPORTE_MONEDA_ORIGEN,");
			consulta.append("  grilla.tipo_de_cambio_fecha_cobro	    AS TIPO_CAMBIO,");
			consulta.append("  grilla.importe_a_cobrar                  AS MONTO_IMPORTE_PESOS,");
			consulta.append("  grilla.numero_transaccion_formateado     AS ID_TRANSACCION_COBRO,"); 
			consulta.append("  grilla.nro_trans_ficticio_formateado AS NUMERO_TRANSACCION_FICTICIO,");    
			consulta.append("  grilla.id_transaccion                    AS ID_TRANSACCION,");  
			consulta.append("  TAREA.fecha_CREACION                     AS FECHA_TAREA,");
			consulta.append("  grilla.id_sociedad                       AS SOCIEDAD,");
			consulta.append("  grilla.sistema                           AS SISTEMA,");
			consulta.append("  grilla.numero_transaccion_formateado     AS ID_TRANSACCION_DESCOBRO");
			consulta.append("  FROM");
			consulta.append("  shv_sop_descobros_grilla_trans grilla,");
			consulta.append("  shv_cob_descobro descob,");
			consulta.append("  SHV_WF_TAREA tarea");
			consulta.append("  WHERE grilla.id_descobro=descob.id_descobro");
			consulta.append("  AND grilla.id_sociedad=?");
			consulta.append("  AND grilla.sistema=?");
			consulta.append("  AND grilla.ESTADO_TRANSACCION=?");
			consulta.append("  AND TAREA.SOCIEDAD=grilla.id_sociedad");
			consulta.append("  AND tarea.sistema=grilla.sistema");
			consulta.append("  AND tarea.id_workflow=descob.id_workflow");
			consulta.append("  AND tarea.tipo_tarea= ?");
			consulta.append("  AND grilla.id_factura is not null");
			consulta.append("  and grilla.id_cliente_legado_documento is null))");
			consulta.append("  order by fecha_tarea, tipo_operacion,id_transaccion");


			
			qp.addCampoAlQuery(filtro.getSociedad().name(), Types.VARCHAR);
			qp.addCampoAlQuery(filtro.getSistema().name(), Types.VARCHAR);
			qp.addCampoAlQuery(EstadoTransaccionEnum.PENDIENTE_CONFIRMAR_APL_MANUAL, Types.VARCHAR);
			qp.addCampoAlQuery(TipoTareaEnum.COB_CONF_APLIC_MANUAL, Types.VARCHAR);
			
			qp.addCampoAlQuery(filtro.getSociedad().name(), Types.VARCHAR);
			qp.addCampoAlQuery(filtro.getSistema().name(), Types.VARCHAR);
			qp.addCampoAlQuery(EstadoTransaccionEnum.PENDIENTE_CONFIRMAR_APL_MANUAL, Types.VARCHAR);
			qp.addCampoAlQuery(TipoTareaEnum.DES_CONFIRMA_APL_MAN, Types.VARCHAR);
			
			qp.addCampoAlQuery(filtro.getSociedad().name(), Types.VARCHAR);
			qp.addCampoAlQuery(filtro.getSistema().name(), Types.VARCHAR);
			qp.addCampoAlQuery(EstadoTransaccionEnum.PENDIENTE_CONFIRMAR_APL_MANUAL, Types.VARCHAR);
			qp.addCampoAlQuery(TipoTareaEnum.COB_CONF_APLIC_MANUAL, Types.VARCHAR);
			
			qp.addCampoAlQuery(filtro.getSociedad().name(), Types.VARCHAR);
			qp.addCampoAlQuery(filtro.getSistema().name(), Types.VARCHAR);
			qp.addCampoAlQuery(EstadoTransaccionEnum.PENDIENTE_CONFIRMAR_APL_MANUAL, Types.VARCHAR);
			qp.addCampoAlQuery(TipoTareaEnum.DES_CONFIRMA_APL_MAN, Types.VARCHAR);
			qp.setSql(consulta.toString());

			return qp;
		}

}
