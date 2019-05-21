package ar.com.telecom.shiva.persistencia.dao.impl;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteAutomatizacionConfirmacionAplicacionManual;
import ar.com.telecom.shiva.persistencia.dao.Dao;
import ar.com.telecom.shiva.persistencia.dao.IGenerarSalidaDetalleAplicacionesManualesDao;
import ar.com.telecom.shiva.persistencia.dao.util.QueryParametrosUtil;
import ar.com.telecom.shiva.persistencia.modelo.ShvArcArchivo;
import ar.com.telecom.shiva.persistencia.repository.CobroAplicacionManualBatchDetalleRepositorio;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteAutomatizacionConfirmacionAplicacionManualFiltro;

public class GenerarSalidaDetalleAplicacionesManualesDaoImpl extends Dao implements IGenerarSalidaDetalleAplicacionesManualesDao {

	@Autowired
	CobroAplicacionManualBatchDetalleRepositorio cobroAplicacionManualBatchDetalleRepositorio;

	@Override
	public List<VistaSoporteAutomatizacionConfirmacionAplicacionManual> buscarCobrosPendientesConfirmacionParaInformar(VistaSoporteAutomatizacionConfirmacionAplicacionManualFiltro filtro) throws PersistenciaExcepcion {
		try{
			
			QueryParametrosUtil qp = obtenerTareasPendienteConfirmacion(filtro);
			List<Map<String, Object>> listaResultadoQuery = buscarUsandoSQL(qp);
			
			List<VistaSoporteAutomatizacionConfirmacionAplicacionManual> listaResultado = new ArrayList<VistaSoporteAutomatizacionConfirmacionAplicacionManual>();
			
			if(Validaciones.isCollectionNotEmpty(listaResultadoQuery)){
				for (Map<String, Object> registro : listaResultadoQuery) {
					
					VistaSoporteAutomatizacionConfirmacionAplicacionManual resultado = new VistaSoporteAutomatizacionConfirmacionAplicacionManual();
					String traza = "Se encontro el siguiente registro para agregar al archivo del sistema " +filtro.getSistema()+": ";
					
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
					if (!Validaciones.isObjectNull(registro.get("ID_TRANSACCION"))){
						resultado.setIdTransaccion((registro.get("ID_TRANSACCION")).toString());
						traza += " ID_TRANSACCION: " + resultado.getIdTransaccion();
					}
					if (!Validaciones.isObjectNull(registro.get("ID_TRATAMIENTO_DIFERENCIA"))){
						resultado.setIdTratamientoDiferencia((BigDecimal) (registro.get("ID_TRATAMIENTO_DIFERENCIA")));
						traza += " ID_TRATAMIENTO_DIFERENCIA: " + resultado.getIdTratamientoDiferencia();
					}
					if (!Validaciones.isObjectNull(registro.get("FECHA_REAL_PAGO"))){
						resultado.setFechaRealDePago((Date) registro.get("FECHA_REAL_PAGO"));
						traza += " FECHA_REAL_PAGO: " + resultado.getFechaRealDePago();
					}
					if (!Validaciones.isObjectNull(registro.get("TIPO_CREDITO"))){
						resultado.setTipoDeCredito(registro.get("TIPO_CREDITO").toString());
						traza += " TIPO_CREDITO: " + resultado.getTipoDeCredito();
					}
					
					if (!Validaciones.isObjectNull(registro.get("REFERENCIA_MEDIO_PAGO"))){
						resultado.setReferenciaMedioDePago(registro.get("REFERENCIA_MEDIO_PAGO").toString());
						traza += " REFERENCIA_MEDIO_PAGO: " + resultado.getReferenciaMedioDePago();
					}
					
					if (!Validaciones.isObjectNull(registro.get("POSEE_ADJUNTO"))){
						resultado.setPoseeAdjunto(registro.get("POSEE_ADJUNTO").toString());
						traza += " POSEE_ADJUNTO: " + resultado.getPoseeAdjunto();
					}
					
					if (!Validaciones.isObjectNull(registro.get("SISTEMA_ORIGEN_MEDIO_PAGO"))){						
						resultado.setSistemaOrigenMedioPago(registro.get("SISTEMA_ORIGEN_MEDIO_PAGO").toString());
						traza += " SISTEMA_ORIGEN_MEDIO_PAGO: " + resultado.getSistemaOrigenMedioPago();
					}
					if (!Validaciones.isObjectNull(registro.get("LISTA_OPERACIONES_EXTERNAS"))){
						
						String[] listaOperacionesExternas = registro.get("LISTA_OPERACIONES_EXTERNAS").toString().split("\\|");
						
						int indice = 0;
						
						while (indice < listaOperacionesExternas.length - 1) {
							
							if (listaOperacionesExternas[indice+3].equals("0")){
								listaOperacionesExternas[indice+3]="";
							} else {
								listaOperacionesExternas[indice+3]=Utilidad.formatCurrency(listaOperacionesExternas[indice+3], false, true);
							}
							

							indice += 4;
							
						}
						
						resultado.setListaOperacionesExternas(StringUtils.join(listaOperacionesExternas,Constantes.SEPARADOR_PIPE));
						traza += " LISTA_OPERACIONES_EXTERNAS: " + resultado.getListaOperacionesExternas();
					}
					
					if (!Validaciones.isObjectNull(registro.get("REFERENCIA_VALOR"))){
						resultado.setReferenciaDelValor(registro.get("REFERENCIA_VALOR").toString());
						traza += " REFERENCIA_VALOR: " + resultado.getReferenciaDelValor();
					}
					
					if (!Validaciones.isObjectNull(registro.get("ID_TRANSACCION_DESCOBRO"))){
						resultado.setIdTransaccionDescobro(registro.get("ID_TRANSACCION_DESCOBRO").toString());
						traza += " ID_TRANSACCION_DESCOBRO: " + resultado.getIdTransaccionDescobro();
					}
					
					listaResultado.add(resultado);
					Traza.advertencia(getClass(), traza);
					
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
				}
			}
			
			return listaResultado;
			
		} catch (Exception e) {
			throw new PersistenciaExcepcion(e.getMessage(), e);
		}

	}
	
private QueryParametrosUtil obtenerTareasPendienteConfirmacion(VistaSoporteAutomatizacionConfirmacionAplicacionManualFiltro filtro) throws NegocioExcepcion {
		
		StringBuffer consulta = new StringBuffer("");
		QueryParametrosUtil qp = new QueryParametrosUtil();
		// INICIO - COBRO - MOTIVO_ADJUNTO APLICACION MANUAL
		// INICIO - COBRO - VALORES SHIVA
		consulta.append(" ((select 'COB' AS TIPO_OPERACION ");
		consulta.append(" , cliente.cuit AS CUIT ");
		consulta.append(" , grilla.id_cobro AS ID_COBRO");
		consulta.append(" , grilla.id_operacion AS ID_OPERACION_COBRO");
		consulta.append(" , NULL AS ID_DESCOBRO ");
		consulta.append(" , NULL AS ID_OPERACION_DESCOBRO ");
		consulta.append(" , grilla.moneda_medio_pago AS MONEDA ");
		consulta.append(" , grilla.importe_apl_fec_emi_mon_ori_mp AS MONTO_IMPORTE_MONEDA_ORIGEN ");
		consulta.append(" , grilla.tipo_cambio_fecha_emision_mp AS TIPO_CAMBIO  ");
		consulta.append(" , grilla.importe_medio_pago AS MONTO_IMPORTE_PESOS ");
		consulta.append(" , grilla.numero_transaccion_formateado AS ID_TRANSACCION_COBRO ");
		consulta.append(" , grilla.id_transaccion AS ID_TRANSACCION ");
		consulta.append(" , trata.id_tratamiento_diferencia AS ID_TRATAMIENTO_DIFERENCIA ");
		consulta.append(" , grilla.fecha_medio_pago AS FECHA_REAL_PAGO ");
		consulta.append(" , grilla.tipo_medio_pago AS TIPO_CREDITO ");
		consulta.append(" , grilla.REFERENCIA_MEDIO_PAGO AS REFERENCIA_MEDIO_PAGO ");
		consulta.append(" , 'SI' AS POSEE_ADJUNTO ");
		consulta.append(" , grilla.sistema_origen_medio_pago AS SISTEMA_ORIGEN_MEDIO_PAGO");
		consulta.append(" , NULL AS LISTA_OPERACIONES_EXTERNAS ");

		consulta.append(" , valor.numero_valor AS REFERENCIA_VALOR ");
		consulta.append(" , NULL AS ID_TRANSACCION_DESCOBRO ");
		consulta.append(" from shv_sop_cobros_grilla_transac grilla ");
		consulta.append(" , shv_cob_cobro cob ");
		consulta.append(" , shv_cob_ed_cliente cliente ");
		consulta.append(" , shv_wf_workflow_estado wf ");
		consulta.append(" , shv_cob_tratamiento_diferencia trata ");
		consulta.append(" , shv_cob_med_pag_shiva credito ");
		consulta.append(" , shv_val_valor valor ");
		consulta.append(" , shv_cob_ed_adjunto adjunto ");
		consulta.append(" where grilla.id_cobro=cob.id_cobro and cob.id_workflow=wf.id_workflow ");
		consulta.append(" and valor.id_cliente_legado = cliente.id_cliente_legado and cob.id_cobro=cliente.id_cobro and grilla.id_transaccion=trata.id_transaccion ");
		consulta.append(" and grilla.id_medio_pago=credito.id_medio_pago and credito.id_valor=valor.id_valor ");
		consulta.append(" and wf.estado='COB_PENDIENTE_CONFIRMACION_MANUAL' ");
		consulta.append(" and trata.tipo_tratamiento_diferencia like 'APLICACION_MANUAL%' ");
		consulta.append(" and grilla.id_transaccion not in (select ID_TRANSACCION from SHV_COB_APLIC_MANUAL_BATCH_DET where id_transaccion is not null) ");
		consulta.append(" and adjunto.id_cobro = cob.id_cobro and adjunto.motivo_adjunto = 'APLICACION_MANUAL' ");
		consulta.append(" and trata.sistema_origen = ?) ");
		// FIN - COBRO - VALORES SHIVA
		consulta.append(" UNION ");
		// INICIO - COBRO - VALORES MIC/CLP
		consulta.append(" (select 'COB' AS TIPO_OPERACION ");
		consulta.append(" , cliente.cuit AS CUIT ");
		consulta.append(" , grilla.id_cobro AS ID_COBRO ");
		consulta.append(" , grilla.id_operacion AS ID_OPERACION_COBRO ");
		consulta.append(" , NULL AS ID_DESCOBRO ");
		consulta.append(" , NULL AS ID_OPERACION_DESCOBRO ");
		consulta.append(" , grilla.moneda_medio_pago AS MONEDA ");
		consulta.append(" , grilla.importe_apl_fec_emi_mon_ori_mp AS MONTO_IMPORTE_MONEDA_ORIGEN ");
		consulta.append(" , grilla.tipo_cambio_fecha_emision_mp AS TIPO_CAMBIO ");
		consulta.append(" , grilla.importe_medio_pago AS MONTO_IMPORTE_PESOS ");
		consulta.append(" , grilla.numero_transaccion_formateado AS ID_TRANSACCION_COBRO ");
		consulta.append(" , grilla.id_transaccion AS ID_TRANSACCION ");
		consulta.append(" , trata.id_tratamiento_diferencia AS ID_TRATAMIENTO_DIFERENCIA ");
		consulta.append(" , grilla.fecha_medio_pago AS FECHA_REAL_PAGO ");
		consulta.append(" , grilla.tipo_medio_pago AS TIPO_CREDITO ");
		consulta.append(" , grilla.REFERENCIA_MEDIO_PAGO AS REFERENCIA_MEDIO_PAGO ");
		consulta.append(" , 'SI' AS POSEE_ADJUNTO ");
		consulta.append(" , grilla.sistema_origen_medio_pago AS SISTEMA_ORIGEN_MEDIO_PAGO");
		consulta.append(" , NULL AS LISTA_OPERACIONES_EXTERNAS ");
		consulta.append(" , NULL AS REFERENCIA_VALOR ");
		consulta.append(" , NULL AS ID_TRANSACCION_DESCOBRO ");
		consulta.append(" from shv_sop_cobros_grilla_transac grilla ");
		consulta.append(" ,shv_cob_cobro cob ");
		consulta.append(" , shv_cob_ed_cliente cliente ");
		consulta.append(" , shv_wf_workflow_estado wf ");
		consulta.append(" , shv_cob_tratamiento_diferencia trata ");
		consulta.append(" , shv_cob_med_pago pago ");
		consulta.append(" , shv_cob_ed_adjunto adjunto ");
		consulta.append(" where grilla.id_cobro=cob.id_cobro and cob.id_workflow=wf.id_workflow ");
		consulta.append(" and cob.id_cobro=cliente.id_cobro and grilla.id_transaccion=trata.id_transaccion ");
		consulta.append(" and trata.id_transaccion = pago.id_transaccion and pago.id_cliente_legado = cliente.id_cliente_legado ");
		consulta.append(" and wf.estado='COB_PENDIENTE_CONFIRMACION_MANUAL' ");
		consulta.append(" and trata.tipo_tratamiento_diferencia like 'APLICACION_MANUAL%' ");
		consulta.append(" and grilla.sistema_origen_documento is null ");
		consulta.append(" and grilla.id_transaccion not in (select ID_TRANSACCION from SHV_COB_APLIC_MANUAL_BATCH_DET where id_transaccion is not null) ");
		consulta.append(" and grilla.sistema_origen_medio_pago <> 'SHIVA' ");
		consulta.append(" and adjunto.id_cobro = cob.id_cobro and adjunto.motivo_adjunto = 'APLICACION_MANUAL' ");
		consulta.append(" and trata.sistema_origen = ?) ");
		// FIN - COBRO - VALORES MIC/CLP
		// FIN - COBRO - MOTIVO_ADJUNTO APLICACION MANUAL
		consulta.append(" UNION ");
		// INICIO - COBRO - MOTIVO_ADJUNTO DISTINTO APLICACION MANUAL
		// INICIO - COBRO - VALORES SHIVA
		consulta.append(" (select 'COB' AS TIPO_OPERACION ");
		consulta.append(" , cliente.cuit AS CUIT ");
		consulta.append(" , grilla.id_cobro AS ID_COBRO ");
		consulta.append(" , grilla.id_operacion AS ID_OPERACION_COBRO ");
		consulta.append(" , NULL AS ID_DESCOBRO ");
		consulta.append(" , NULL AS ID_OPERACION_DESCOBRO ");
		consulta.append(" , grilla.moneda_medio_pago AS MONEDA ");
		consulta.append(" , grilla.importe_apl_fec_emi_mon_ori_mp AS MONTO_IMPORTE_MONEDA_ORIGEN ");
		consulta.append(" , grilla.tipo_cambio_fecha_emision_mp AS TIPO_CAMBIO  ");
		consulta.append(" , grilla.importe_medio_pago AS MONTO_IMPORTE_PESOS ");
		consulta.append(" , grilla.numero_transaccion_formateado AS ID_TRANSACCION_COBRO ");
		consulta.append(" , grilla.id_transaccion AS ID_TRANSACCION ");
		consulta.append(" , trata.id_tratamiento_diferencia AS ID_TRATAMIENTO_DIFERENCIA ");
		consulta.append(" , grilla.fecha_medio_pago AS FECHA_REAL_PAGO ");
		consulta.append(" , grilla.tipo_medio_pago AS TIPO_CREDITO ");
		consulta.append(" , grilla.REFERENCIA_MEDIO_PAGO AS REFERENCIA_MEDIO_PAGO ");
		consulta.append(" , 'NO' AS POSEE_ADJUNTO ");
		consulta.append(" , grilla.sistema_origen_medio_pago AS SISTEMA_ORIGEN_MEDIO_PAGO");
		consulta.append(" , NULL AS LISTA_OPERACIONES_EXTERNAS ");
		consulta.append(" , valor.numero_valor AS REFERENCIA_VALOR ");
		consulta.append(" , NULL AS ID_TRANSACCION_DESCOBRO ");
		consulta.append(" from shv_sop_cobros_grilla_transac grilla ");
		consulta.append(" , shv_cob_cobro cob ");
		consulta.append(" , shv_cob_ed_cliente cliente ");
		consulta.append(" , shv_wf_workflow_estado wf ");
		consulta.append(" , shv_cob_tratamiento_diferencia trata ");
		consulta.append(" , shv_cob_med_pag_shiva credito ");
		consulta.append(" , shv_val_valor valor ");
		consulta.append(" where grilla.id_cobro=cob.id_cobro and cob.id_workflow=wf.id_workflow ");
		consulta.append(" and valor.id_cliente_legado = cliente.id_cliente_legado and cob.id_cobro=cliente.id_cobro and grilla.id_transaccion=trata.id_transaccion ");
		consulta.append(" and grilla.id_medio_pago=credito.id_medio_pago and credito.id_valor=valor.id_valor ");
		consulta.append(" and wf.estado='COB_PENDIENTE_CONFIRMACION_MANUAL' ");
		consulta.append(" and trata.tipo_tratamiento_diferencia like 'APLICACION_MANUAL%' ");
		consulta.append(" and grilla.id_transaccion not in (select ID_TRANSACCION from SHV_COB_APLIC_MANUAL_BATCH_DET where id_transaccion is not null) ");
		consulta.append(" and not exists (select * from shv_cob_ed_adjunto adjunto where adjunto.id_cobro = cob.id_cobro and adjunto.motivo_adjunto = 'APLICACION_MANUAL') ");
		consulta.append(" and trata.sistema_origen = ?) ");
		// FIN - COBRO - VALORES SHIVA
		consulta.append(" UNION ");
		// INICIO - COBRO - VALORES MIC/CLP
		consulta.append(" (select 'COB' AS TIPO_OPERACION ");
		consulta.append(" , cliente.cuit AS CUIT ");
		consulta.append(" , grilla.id_cobro AS ID_COBRO ");
		consulta.append(" , grilla.id_operacion AS ID_OPERACION_COBRO ");
		consulta.append(" , NULL AS ID_DESCOBRO ");
		consulta.append(" , NULL AS ID_OPERACION_DESCOBRO ");
		consulta.append(" , grilla.moneda_medio_pago AS MONEDA ");
		consulta.append(" , grilla.importe_apl_fec_emi_mon_ori_mp AS MONTO_IMPORTE_MONEDA_ORIGEN ");
		consulta.append(" , grilla.tipo_cambio_fecha_emision_mp AS TIPO_CAMBIO ");
		consulta.append(" , grilla.importe_medio_pago AS MONTO_IMPORTE_PESOS ");
		consulta.append(" , grilla.numero_transaccion_formateado AS ID_TRANSACCION_COBRO ");
		consulta.append(" , grilla.id_transaccion AS ID_TRANSACCION ");
		consulta.append(" , trata.id_tratamiento_diferencia AS ID_TRATAMIENTO_DIFERENCIA ");
		consulta.append(" , grilla.fecha_medio_pago AS FECHA_REAL_PAGO ");
		consulta.append(" , grilla.tipo_medio_pago AS TIPO_CREDITO ");
		consulta.append(" , grilla.REFERENCIA_MEDIO_PAGO AS REFERENCIA_MEDIO_PAGO ");
		consulta.append(" , 'NO' AS POSEE_ADJUNTO ");
		consulta.append(" , grilla.sistema_origen_medio_pago AS SISTEMA_ORIGEN_MEDIO_PAGO");
		consulta.append(" , NULL AS LISTA_OPERACIONES_EXTERNAS ");
		consulta.append(" , NULL AS REFERENCIA_VALOR ");
		consulta.append(" , NULL AS ID_TRANSACCION_DESCOBRO ");
		consulta.append(" from shv_sop_cobros_grilla_transac grilla ");
		consulta.append(" ,shv_cob_cobro cob ");
		consulta.append(" , shv_cob_ed_cliente cliente ");
		consulta.append(" , shv_wf_workflow_estado wf ");
		consulta.append(" , shv_cob_tratamiento_diferencia trata ");
		consulta.append(" , shv_cob_med_pago pago ");
		consulta.append(" where grilla.id_cobro=cob.id_cobro and cob.id_workflow=wf.id_workflow ");
		consulta.append(" and cob.id_cobro=cliente.id_cobro and grilla.id_transaccion=trata.id_transaccion ");
		consulta.append(" and trata.id_transaccion = pago.id_transaccion and pago.id_cliente_legado = cliente.id_cliente_legado ");
		consulta.append(" and wf.estado='COB_PENDIENTE_CONFIRMACION_MANUAL' ");
		consulta.append(" and trata.tipo_tratamiento_diferencia like 'APLICACION_MANUAL%' ");
		consulta.append(" and grilla.sistema_origen_documento is null ");
		consulta.append(" and grilla.id_transaccion not in (select ID_TRANSACCION from SHV_COB_APLIC_MANUAL_BATCH_DET where id_transaccion is not null) ");
		consulta.append(" and grilla.sistema_origen_medio_pago <> 'SHIVA' ");
		consulta.append(" and not exists (select * from shv_cob_ed_adjunto adjunto where adjunto.id_cobro = cob.id_cobro and adjunto.motivo_adjunto = 'APLICACION_MANUAL') ");
		consulta.append(" and trata.sistema_origen = ?) ");
		// FIN - COBRO - VALORES MIC/CLP
		// FIN - COBRO - MOTIVO_ADJUNTO DISTINTO APLICACION MANUAL
		consulta.append(" UNION ");
		// INICIO - DESCOBRO - MOTIVO_ADJUNTO APLICACION MANUAL
		// INICIO - DESCOBRO - VALORES SHIVA
		consulta.append(" (select 'RCO' AS TIPO_OPERACION ");
		consulta.append(" , cliente.cuit AS CUIT ");
		consulta.append(" , NULL AS ID_COBRO ");
		consulta.append(" , NULL AS ID_OPERACION_COBRO ");
		consulta.append(" , grilla.id_descobro AS ID_DESCOBRO");
		consulta.append(" , grilla.id_operacion AS ID_OPERACION_DESCOBRO ");
		consulta.append(" , grilla.moneda_medio_pago AS MONEDA ");
		consulta.append(" , grilla.importe_apl_fec_emi_mon_ori_mp AS MONTO_IMPORTE_MONEDA_ORIGEN ");
		consulta.append(" , grilla.tipo_cambio_fecha_emision_mp AS TIPO_CAMBIO ");
		consulta.append(" , grilla.importe_medio_pago AS MONTO_IMPORTE_PESOS ");
		consulta.append(" , grillaCobro.numero_transaccion_formateado AS ID_TRANSACCION_COBRO ");
		consulta.append(" , grilla.id_transaccion AS ID_TRANSACCION ");
		consulta.append(" , trata.id_tratamiento_diferencia AS ID_TRATAMIENTO_DIFERENCIA ");
		consulta.append(" , grilla.fecha_medio_pago AS FECHA_REAL_PAGO ");
		consulta.append(" , grilla.tipo_medio_pago AS TIPO_CREDITO ");
		consulta.append(" , grilla.REFERENCIA_MEDIO_PAGO AS REFERENCIA_MEDIO_PAGO ");
		consulta.append(" , 'SI' AS POSEE_ADJUNTO ");
		consulta.append(" , grilla.sistema_origen_medio_pago AS SISTEMA_ORIGEN_MEDIO_PAGO");
		consulta.append(" , (SELECT LISTAGG(COE.SISTEMA || '|' || COE.REFERENTE || '|' ");
		consulta.append("   || COE.CODIGO_OPERACION_EXTERNA || '|' || DECODE(COE.IMPORTE,NULL,'0',COE.IMPORTE), '|')");
		consulta.append("    WITHIN GROUP(ORDER BY COE.CODIGO_OPERACION_EXTERNA) ");
		consulta.append("    FROM SHV_COB_ED_COD_OPER_EXT COE WHERE COE.ID_COBRO = descob.id_cobro ");
		consulta.append(" )  AS LISTA_OPERACIONES_EXTERNAS ");
		consulta.append(" , valor.numero_valor AS REFERENCIA_VALOR ");
		consulta.append(" , grilla.numero_transaccion_formateado AS ID_TRANSACCION_DESCOBRO ");
		consulta.append(" from shv_sop_descobros_grilla_trans grilla ");
		consulta.append(" , shv_cob_descobro descob ");
		consulta.append(" , shv_cob_ed_cliente cliente ");
		consulta.append(" , shv_wf_workflow_estado wf ");
		consulta.append(" , shv_cob_tratamiento_diferencia trata ");
		consulta.append(" , shv_cob_med_pag_shiva credito ");
		consulta.append(" , shv_val_valor valor ");
		consulta.append(" , shv_cob_descobro_adjunto adjunto ");
		consulta.append(" , shv_sop_cobros_grilla_transac grillaCobro ");
		consulta.append(" where grilla.id_descobro=descob.id_descobro and descob.id_workflow=wf.id_workflow ");
		consulta.append(" and valor.id_cliente_legado = cliente.id_cliente_legado and descob.id_cobro=cliente.id_cobro and grilla.id_transaccion=trata.id_transaccion ");
		consulta.append(" and grilla.id_medio_pago=credito.id_medio_pago and credito.id_valor=valor.id_valor ");
		consulta.append(" and wf.estado='DES_PENDIENTE_CONFIRMACION_MANUAL' ");
		consulta.append(" and trata.tipo_tratamiento_diferencia like 'APLICACION_MANUAL%' ");
		consulta.append(" and grilla.id_transaccion not in (select ID_TRANSACCION from SHV_COB_APLIC_MANUAL_BATCH_DET where id_transaccion is not null) ");
		consulta.append(" and descob.id_cobro = grillaCobro.id_cobro and grillaCobro.referencia_medio_pago = grilla.referencia_medio_pago ");
		consulta.append(" and adjunto.id_descobro = descob.id_descobro and adjunto.motivo_adjunto='APLICACION_MANUAL' ");
		consulta.append(" and trata.sistema_origen = ?) ");
		// FIN - DESCOBRO - VALORES SHIVA
		consulta.append(" UNION ");
		// INICIO - DESCOBRO - VALORES MIC/CLP
		consulta.append(" (select 'RCO' AS TIPO_OPERACION ");
		consulta.append(" , cliente.cuit AS CUIT ");
		consulta.append(" , NULL AS ID_COBRO ");
		consulta.append(" , NULL AS ID_OPERACION_COBRO ");
		consulta.append(" , grilla.id_descobro AS ID_DESCOBRO");
		consulta.append(" , grilla.id_operacion AS ID_OPERACION_DESCOBRO ");
		consulta.append(" , grilla.moneda_medio_pago AS MONEDA ");
		consulta.append(" , grilla.importe_apl_fec_emi_mon_ori_mp AS MONTO_IMPORTE_MONEDA_ORIGEN ");
		consulta.append(" , grilla.tipo_cambio_fecha_emision_mp AS TIPO_CAMBIO ");
		consulta.append(" , grilla.importe_medio_pago AS MONTO_IMPORTE_PESOS ");
		consulta.append(" , grillaCobro.numero_transaccion_formateado AS ID_TRANSACCION_COBRO ");
		consulta.append(" , grilla.id_transaccion AS ID_TRANSACCION ");
		consulta.append(" , trata.id_tratamiento_diferencia AS ID_TRATAMIENTO_DIFERENCIA ");
		consulta.append(" , grilla.fecha_medio_pago AS FECHA_REAL_PAGO ");
		consulta.append(" , grilla.tipo_medio_pago AS TIPO_CREDITO ");
		consulta.append(" , grilla.REFERENCIA_MEDIO_PAGO AS REFERENCIA_MEDIO_PAGO ");
		consulta.append(" , 'SI' AS POSEE_ADJUNTO ");
		consulta.append(" , grilla.sistema_origen_medio_pago AS SISTEMA_ORIGEN_MEDIO_PAGO");
		consulta.append(" , (SELECT LISTAGG(COE.SISTEMA || '|' || COE.REFERENTE || '|' ");
		consulta.append("   || COE.CODIGO_OPERACION_EXTERNA || '|' || DECODE(COE.IMPORTE,NULL,'0',COE.IMPORTE), '|')");
		consulta.append("    WITHIN GROUP(ORDER BY COE.CODIGO_OPERACION_EXTERNA) ");
		consulta.append("    FROM SHV_COB_ED_COD_OPER_EXT COE WHERE COE.ID_COBRO = descob.id_cobro ");
		consulta.append(" )  AS LISTA_OPERACIONES_EXTERNAS ");
		consulta.append(" , NULL AS REFERENCIA_VALOR ");
		consulta.append(" , grilla.numero_transaccion_formateado AS ID_TRANSACCION_DESCOBRO ");
		consulta.append(" from shv_sop_descobros_grilla_trans grilla ");
		consulta.append(" , shv_cob_descobro descob ");
		consulta.append(" , shv_cob_ed_cliente cliente ");
		consulta.append(" , shv_wf_workflow_estado wf ");
		consulta.append(" , shv_cob_tratamiento_diferencia trata ");
		consulta.append(" , shv_sop_cobros_grilla_transac grillaCobro ");
		consulta.append(" , shv_cob_med_pago pago ");
		consulta.append(" , shv_cob_descobro_adjunto adjunto ");
		consulta.append(" where grilla.id_descobro=descob.id_descobro and descob.id_workflow=wf.id_workflow ");
		consulta.append(" and descob.id_cobro=cliente.id_cobro and grilla.id_transaccion=trata.id_transaccion ");
		consulta.append(" and trata.id_transaccion = pago.id_transaccion and pago.id_cliente_legado = cliente.id_cliente_legado ");
		consulta.append(" and wf.estado='DES_PENDIENTE_CONFIRMACION_MANUAL' ");
		consulta.append(" and trata.tipo_tratamiento_diferencia like 'APLICACION_MANUAL%' ");
		consulta.append(" and grilla.sistema_origen_documento is null ");
		consulta.append(" and grilla.id_transaccion not in (select ID_TRANSACCION from SHV_COB_APLIC_MANUAL_BATCH_DET where id_transaccion is not null) ");
		consulta.append(" and grilla.sistema_origen_medio_pago <> 'SHIVA' ");
		consulta.append(" and descob.id_cobro = grillaCobro.id_cobro and grillaCobro.referencia_medio_pago = grilla.referencia_medio_pago ");
		consulta.append(" and adjunto.id_descobro = descob.id_descobro and adjunto.motivo_adjunto='APLICACION_MANUAL' ");
		consulta.append(" and trata.sistema_origen = ?) ");
		// FIN - DESCOBRO - VALORES MIC/CLP
		// FIN - DESCOBRO - MOTIVO_ADJUNTO  APLICACION MANUAL
		consulta.append(" UNION ");
		// INICIO - DESCOBRO - MOTIVO_ADJUNTO DISTINTO APLICACION MANUAL
		// INICIO - DESCOBRO - VALORES SHIVA
		consulta.append(" (select 'RCO' AS TIPO_OPERACION ");
		consulta.append(" , cliente.cuit AS CUIT ");
		consulta.append(" , NULL AS ID_COBRO ");
		consulta.append(" , NULL AS ID_OPERACION_COBRO ");
		consulta.append(" , grilla.id_descobro AS ID_DESCOBRO");
		consulta.append(" , grilla.id_operacion AS ID_OPERACION_DESCOBRO");
		consulta.append(" , grilla.moneda_medio_pago AS MONEDA ");
		consulta.append(" , grilla.importe_apl_fec_emi_mon_ori_mp AS MONTO_IMPORTE_MONEDA_ORIGEN ");
		consulta.append(" , grilla.tipo_cambio_fecha_emision_mp AS TIPO_CAMBIO ");
		consulta.append(" , grilla.importe_medio_pago AS MONTO_IMPORTE_PESOS ");
		consulta.append(" , grillaCobro.numero_transaccion_formateado AS ID_TRANSACCION_COBRO ");
		consulta.append(" , grilla.id_transaccion AS ID_TRANSACCION ");
		consulta.append(" , trata.id_tratamiento_diferencia AS ID_TRATAMIENTO_DIFERENCIA ");
		consulta.append(" , grilla.fecha_medio_pago AS FECHA_REAL_PAGO ");
		consulta.append(" , grilla.tipo_medio_pago AS TIPO_CREDITO ");
		consulta.append(" , grilla.REFERENCIA_MEDIO_PAGO AS REFERENCIA_MEDIO_PAGO ");
		consulta.append(" , 'NO' AS POSEE_ADJUNTO ");
		consulta.append(" , grilla.sistema_origen_medio_pago AS SISTEMA_ORIGEN_MEDIO_PAGO");
		consulta.append(" , (SELECT LISTAGG(COE.SISTEMA || '|' || COE.REFERENTE || '|' ");
		consulta.append("   || COE.CODIGO_OPERACION_EXTERNA || '|' || DECODE(COE.IMPORTE,NULL,'0',COE.IMPORTE), '|')");
		consulta.append("    WITHIN GROUP(ORDER BY COE.CODIGO_OPERACION_EXTERNA) ");
		consulta.append("    FROM SHV_COB_ED_COD_OPER_EXT COE WHERE COE.ID_COBRO = descob.id_cobro ");
		consulta.append(" )  AS LISTA_OPERACIONES_EXTERNAS ");
		consulta.append(" , valor.numero_valor AS REFERENCIA_VALOR ");
		consulta.append(" , grilla.numero_transaccion_formateado AS ID_TRANSACCION_DESCOBRO ");
		consulta.append(" from shv_sop_descobros_grilla_trans grilla ");
		consulta.append(" , shv_cob_descobro descob ");
		consulta.append(" , shv_cob_ed_cliente cliente ");
		consulta.append(" , shv_wf_workflow_estado wf ");
		consulta.append(" , shv_cob_tratamiento_diferencia trata ");
		consulta.append(" , shv_cob_med_pag_shiva credito ");
		consulta.append(" , shv_val_valor valor ");
		consulta.append(" , shv_sop_cobros_grilla_transac grillaCobro ");
		consulta.append(" where grilla.id_descobro=descob.id_descobro and descob.id_workflow=wf.id_workflow ");
		consulta.append(" and valor.id_cliente_legado = cliente.id_cliente_legado and descob.id_cobro=cliente.id_cobro and grilla.id_transaccion=trata.id_transaccion ");
		consulta.append(" and grilla.id_medio_pago=credito.id_medio_pago and credito.id_valor=valor.id_valor ");
		consulta.append(" and wf.estado='DES_PENDIENTE_CONFIRMACION_MANUAL' ");
		consulta.append(" and trata.tipo_tratamiento_diferencia like 'APLICACION_MANUAL%' ");
		consulta.append(" and grilla.id_transaccion not in (select ID_TRANSACCION from SHV_COB_APLIC_MANUAL_BATCH_DET where id_transaccion is not null) ");
		consulta.append(" and descob.id_cobro = grillaCobro.id_cobro and grillaCobro.referencia_medio_pago = grilla.referencia_medio_pago ");
		consulta.append(" and not exists (select * from shv_cob_descobro_adjunto adjunto where adjunto.id_descobro = descob.id_descobro and adjunto.motivo_adjunto = 'APLICACION_MANUAL') ");
		consulta.append(" and trata.sistema_origen = ?) ");
		// FIN - DESCOBRO - VALORES SHIVA
		consulta.append(" UNION ");
		// INICIO - DESCOBRO - VALORES MIC/CLP
		consulta.append(" (select 'RCO' AS TIPO_OPERACION ");
		consulta.append(" , cliente.cuit AS CUIT ");
		consulta.append(" , NULL AS ID_COBRO ");
		consulta.append(" , NULL AS ID_OPERACION_COBRO ");
		consulta.append(" , grilla.id_descobro AS ID_DESCOBRO");
		consulta.append(" , grilla.id_operacion AS ID_OPERACION_DESCOBRO");
		consulta.append(" , grilla.moneda_medio_pago AS MONEDA ");
		consulta.append(" , grilla.importe_apl_fec_emi_mon_ori_mp AS MONTO_IMPORTE_MONEDA_ORIGEN ");
		consulta.append(" , grilla.tipo_cambio_fecha_emision_mp AS TIPO_CAMBIO ");
		consulta.append(" , grilla.importe_medio_pago AS MONTO_IMPORTE_PESOS ");
		consulta.append(" , grillaCobro.numero_transaccion_formateado AS ID_TRANSACCION_COBRO ");
		consulta.append(" , grilla.id_transaccion AS ID_TRANSACCION ");
		consulta.append(" , trata.id_tratamiento_diferencia AS ID_TRATAMIENTO_DIFERENCIA ");
		consulta.append(" , grilla.fecha_medio_pago AS FECHA_REAL_PAGO ");
		consulta.append(" , grilla.tipo_medio_pago AS TIPO_CREDITO ");
		consulta.append(" , grilla.REFERENCIA_MEDIO_PAGO AS REFERENCIA_MEDIO_PAGO ");
		consulta.append(" , 'NO' AS POSEE_ADJUNTO ");
		consulta.append(" , grilla.sistema_origen_medio_pago AS SISTEMA_ORIGEN_MEDIO_PAGO");
		consulta.append(" , (SELECT LISTAGG(COE.SISTEMA || '|' || COE.REFERENTE || '|' ");
		consulta.append("   || COE.CODIGO_OPERACION_EXTERNA || '|' || DECODE(COE.IMPORTE,NULL,'0',COE.IMPORTE), '|')");
		consulta.append("    WITHIN GROUP(ORDER BY COE.CODIGO_OPERACION_EXTERNA) ");
		consulta.append("    FROM SHV_COB_ED_COD_OPER_EXT COE WHERE COE.ID_COBRO = descob.id_cobro ");
		consulta.append(" )  AS LISTA_OPERACIONES_EXTERNAS ");
		consulta.append(" , NULL AS REFERENCIA_VALOR ");
		consulta.append(" , grilla.numero_transaccion_formateado AS ID_TRANSACCION_DESCOBRO ");
		consulta.append(" from shv_sop_descobros_grilla_trans grilla ");
		consulta.append(" , shv_cob_descobro descob ");
		consulta.append(" , shv_cob_ed_cliente cliente ");
		consulta.append(" , shv_wf_workflow_estado wf ");
		consulta.append(" , shv_cob_tratamiento_diferencia trata ");
		consulta.append(" , shv_sop_cobros_grilla_transac grillaCobro ");
		consulta.append(" , shv_cob_med_pago pago ");
		consulta.append(" where grilla.id_descobro=descob.id_descobro and descob.id_workflow=wf.id_workflow ");
		consulta.append(" and descob.id_cobro=cliente.id_cobro and grilla.id_transaccion=trata.id_transaccion ");
		consulta.append(" and trata.id_transaccion = pago.id_transaccion and pago.id_cliente_legado = cliente.id_cliente_legado ");
		consulta.append(" and wf.estado='DES_PENDIENTE_CONFIRMACION_MANUAL' ");
		consulta.append(" and trata.tipo_tratamiento_diferencia like 'APLICACION_MANUAL%' ");
		consulta.append(" and grilla.sistema_origen_documento is null ");
		consulta.append(" and grilla.id_transaccion not in (select ID_TRANSACCION from SHV_COB_APLIC_MANUAL_BATCH_DET where id_transaccion is not null) ");
		consulta.append(" and grilla.sistema_origen_medio_pago <> 'SHIVA' ");
		consulta.append(" and descob.id_cobro = grillaCobro.id_cobro and grillaCobro.referencia_medio_pago = grilla.referencia_medio_pago ");
		consulta.append(" and not exists (select * from shv_cob_descobro_adjunto adjunto where adjunto.id_descobro = descob.id_descobro and adjunto.motivo_adjunto = 'APLICACION_MANUAL') ");
		consulta.append(" and trata.sistema_origen = ?)) ");
		consulta.append(" order by tipo_operacion,id_transaccion ");
		// FIN - DESCOBRO - VALORES MIC/CLP
		// FIN - DESCOBRO - MOTIVO_ADJUNTO DISTINTO APLICACION MANUAL
		
		qp.addCampoAlQuery(filtro.getSistema(), Types.VARCHAR);
		qp.addCampoAlQuery(filtro.getSistema(), Types.VARCHAR);
		qp.addCampoAlQuery(filtro.getSistema(), Types.VARCHAR);
		qp.addCampoAlQuery(filtro.getSistema(), Types.VARCHAR);
		qp.addCampoAlQuery(filtro.getSistema(), Types.VARCHAR);
		qp.addCampoAlQuery(filtro.getSistema(), Types.VARCHAR);
		qp.addCampoAlQuery(filtro.getSistema(), Types.VARCHAR);
		qp.addCampoAlQuery(filtro.getSistema(), Types.VARCHAR);
		
		qp.setSql(consulta.toString());

		return qp;
	}

	@Override
	public ShvArcArchivo insertarArcArchivo(ShvArcArchivo archivo) throws PersistenciaExcepcion {
		try{
			ShvArcArchivo modeloNuevo = cobroAplicacionManualBatchDetalleRepositorio.save(archivo);
			cobroAplicacionManualBatchDetalleRepositorio.flush();
			return modeloNuevo;
			
		}catch(Exception e){
			throw new PersistenciaExcepcion(e);
		}
		
	}

	/************************************************************************************
	 * Getters & Setters
	 ************************************************************************************/

	public CobroAplicacionManualBatchDetalleRepositorio getCobroAplicacionManualBatchDetalleRepositorio() {
		return cobroAplicacionManualBatchDetalleRepositorio;
	}

	public void setCobroAplicacionManualBatchDetalleRepositorio(
			CobroAplicacionManualBatchDetalleRepositorio cobroAplicacionManualBatchDetalleRepositorio) {
		this.cobroAplicacionManualBatchDetalleRepositorio = cobroAplicacionManualBatchDetalleRepositorio;
	}

	
}