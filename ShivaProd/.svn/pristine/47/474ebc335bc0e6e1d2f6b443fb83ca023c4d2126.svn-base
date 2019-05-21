package ar.com.telecom.shiva.persistencia.dao.impl;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;





import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteHistoricoDeValores;
import ar.com.telecom.shiva.persistencia.dao.Dao;
import ar.com.telecom.shiva.persistencia.dao.IReporteValoresPorEmpresaDao;
import ar.com.telecom.shiva.persistencia.dao.util.QueryParametrosUtil;

/*
 * autor: Fernando Nicolas Quispe
 * u591368
 * 
 * */

public class ReporteValoresPorEmpresaDaoImpl extends Dao implements IReporteValoresPorEmpresaDao {
	

	@Override
	public List<VistaSoporteHistoricoDeValores> buscarValores(Long registrosXPagina , Long pagina) throws PersistenciaExcepcion {

		//
		// Busqueda de Valores
		//
		List<Map<String, Object>> listaResultadoQuery;
		

		try {
			QueryParametrosUtil qp = Paginado(registrosXPagina, pagina, obtenerSQLValores().getSql());
			listaResultadoQuery = buscarUsandoSQL(qp);
			
			List<VistaSoporteHistoricoDeValores> resultado = new ArrayList<VistaSoporteHistoricoDeValores>();
			
			for (Map<String, Object> archivo : listaResultadoQuery) {
				
				
				VistaSoporteHistoricoDeValores reg = new VistaSoporteHistoricoDeValores();
				
				if (!Validaciones.isObjectNull(archivo.get("ID_VALOR"))) {
					reg.setIdValor(((BigDecimal) archivo.get("ID_VALOR")).longValue());
				}
				if (!Validaciones.isObjectNull(archivo.get("EMPRESA"))) {
					reg.setEmpresa(archivo.get("EMPRESA").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("SEGMENTO"))) {
					reg.setSegmento(archivo.get("SEGMENTO").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("ID_CLIENTE_LEGADO"))) {
					reg.setIdClienteLegado(archivo.get("ID_CLIENTE_LEGADO").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("RAZON_SOCIAL_CLIENTE_PERFIL"))) {
					reg.setRazonSocialClienteAgrupador(archivo.get("RAZON_SOCIAL_CLIENTE_PERFIL").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("TIPO_VALOR"))) {
					reg.setTipoValor(archivo.get("TIPO_VALOR").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("ESTADO_VALOR"))) {
					reg.setEstadoValor(archivo.get("ESTADO_VALOR").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("ORIGEN"))) {
					reg.setOrigen(archivo.get("ORIGEN").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("ACUERDO"))) {
					reg.setIdAcuerdo(archivo.get("ACUERDO").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("IMPORTE"))) {
					reg.setImporte((BigDecimal) archivo.get("IMPORTE"));
				}
				if (!Validaciones.isObjectNull(archivo.get("BD_IMPRESA"))) {
					reg.setBdImpresa(archivo.get("BD_IMPRESA").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("BD_ENVIADA_MAIL"))) {
					reg.setBdEnviadaMail(archivo.get("BD_ENVIADA_MAIL").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("FECHA_ALTA"))) {
					reg.setFechaAlta(String.valueOf(archivo.get("FECHA_ALTA")));
				}
				if (!Validaciones.isObjectNull(archivo.get("ID_ANALISTA"))) {
					reg.setIdAnalista(archivo.get("ID_ANALISTA").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("NRO_RECIBO"))) {
					reg.setNroRecibo(archivo.get("NRO_RECIBO").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("ID_CONSTANCIA_RECEPCION"))) {
					reg.setIdConstanciaRecepcion(archivo.get("ID_CONSTANCIA_RECEPCION").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("OPERACION_ASOCIADA"))) {
					reg.setOperacionAsociada(archivo.get("OPERACION_ASOCIADA").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("FACTURA_RELACIONADA"))) {
					reg.setFacturaRelacionada(archivo.get("FACTURA_RELACIONADA").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("DOCUMENTACION_ORIG_RECIBIDA"))) {
					reg.setDocumentacionOrigRecibida(archivo.get("DOCUMENTACION_ORIG_RECIBIDA").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("MOTIVO"))) {
					reg.setMotivo(archivo.get("MOTIVO").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("BANCO_DEPOSITO"))) {
					reg.setBancoDeposito(archivo.get("BANCO_DEPOSITO").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("ID_COPROPIETARIO"))) {
					reg.setIdCopropietario(archivo.get("ID_COPROPIETARIO").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("USUARIO_AUTORIZACION"))) {
					reg.setUsuarioAutorizacion(archivo.get("USUARIO_AUTORIZACION").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("NUMERO_DOCUMENTO_CONTABLE"))) {
					reg.setNumeroDocumentoContable(archivo.get("NUMERO_DOCUMENTO_CONTABLE").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("MOTIVO_SUSPENSION"))) {
					reg.setMotivoSuspension(archivo.get("MOTIVO_SUSPENSION").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("ID_LEGAJO_CHEQUE_RECHAZADO"))) {
					reg.setIdLegajoChequeRechazado(archivo.get("ID_LEGAJO_CHEQUE_RECHAZADO").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("FECHA_NOTIFICACION_RECHAZO"))) {
					reg.setFechaNotificacionRechazo((Date)archivo.get("FECHA_NOTIFICACION_RECHAZO"));
				}
				if (!Validaciones.isObjectNull(archivo.get("FECHA_RECHAZO"))) {
					reg.setFechaRechazo((Date)archivo.get("FECHA_RECHAZO"));
				}
				if (!Validaciones.isObjectNull(archivo.get("EJECUTIVO"))) {
					reg.setEjecutivo(archivo.get("EJECUTIVO").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("ASISTENTE"))) {
					reg.setAsistente(archivo.get("ASISTENTE").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("SALDO_DISPONIBLE"))) {
					reg.setSaldoDisponible((BigDecimal) archivo.get("SALDO_DISPONIBLE"));
				}
				if (!Validaciones.isObjectNull(archivo.get("FECHA_INGRESO_RECIBO"))) {
					reg.setFechaIngresoRecibo((Date) archivo.get("FECHA_INGRESO_RECIBO"));
				}
				// Consulta servidor plnx0267 / plnx0268 / plnx0270 - Shiva
//				if (!Validaciones.isObjectNull(archivo.get("FECHA_EMISION"))) {
//					reg.setFechaEmision(archivo.get("FECHA_EMISION").toString());
//				}
				if (!Validaciones.isObjectNull(archivo.get("FECHA_EMISION"))) {
					reg.setFechaEmision(Utilidad.formatDatePicker(
						(Date) archivo.get("FECHA_EMISION")
					));
				}
				if (!Validaciones.isObjectNull(archivo.get("FECHA_VENCIMIENTO"))) {
					reg.setFechaVencimiento((Date) archivo.get("FECHA_VENCIMIENTO"));
				}
				if (!Validaciones.isObjectNull(archivo.get("FECHA_TRANSFERENCIA"))) {
					reg.setFechaTransferencia((Date) archivo.get("FECHA_TRANSFERENCIA"));
				}
				if (!Validaciones.isObjectNull(archivo.get("FECHA_DEPOSITO"))) {
					reg.setFechaDeposito((Date) archivo.get("FECHA_DEPOSITO"));
				}
				if (!Validaciones.isObjectNull(archivo.get("FECHA_ULTIMO_ESTADO"))) {
					reg.setFechaUltimoEstado((Date) archivo.get("FECHA_ULTIMO_ESTADO"));
				}
				if (!Validaciones.isObjectNull(archivo.get("FECHA_DISPONIBLE"))) {
					reg.setFechaDisponible((Date) archivo.get("FECHA_DISPONIBLE"));
				}
				if (!Validaciones.isObjectNull(archivo.get("FECHA_VALOR"))) {
					reg.setFechaValor((Date) archivo.get("FECHA_VALOR"));
				}
				if (!Validaciones.isObjectNull(archivo.get("DESCRIPCION_TIPO_RETENCION"))) {
					reg.setDescripcionTipoRetencion(archivo.get("DESCRIPCION_TIPO_RETENCION").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("NRO_BOLETA"))) {
					reg.setNroBoleta(archivo.get("NRO_BOLETA").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("DESCRIPCION_BANCO_ORIGEN"))) {
					reg.setDescripcionBancoOrigen(archivo.get("DESCRIPCION_BANCO_ORIGEN").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("NRO_CUIT_RETENCION"))) {
					reg.setNroCuitRetencion(archivo.get("NRO_CUIT_RETENCION").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("PROVINCIA_RETENCION"))) {
					reg.setProvinciaRetencion(archivo.get("PROVINCIA_RETENCION").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("CODIGO_ORGANISMO"))) {
					reg.setCodigoOrganismo(archivo.get("CODIGO_ORGANISMO").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("REFERENCIA_VALOR"))) {
					reg.setReferenciaValor(archivo.get("REFERENCIA_VALOR").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("ID_ANALISTA_TEAM_COMERCIAL"))) {
					reg.setIdAnalistaTeamComercial(archivo.get("ID_ANALISTA_TEAM_COMERCIAL").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("ID_SUPERVISOR_TEAM_COMERCIAL"))) {
					reg.setIdSupervisorTeamComercial(archivo.get("ID_SUPERVISOR_TEAM_COMERCIAL").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("ID_GERENTE_REG_TEAM_COMERCIAL"))) {
					reg.setIdGerenteRegionalTeamComercial(archivo.get("ID_GERENTE_REG_TEAM_COMERCIAL").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("OBSERVACIONES"))) {
					reg.setObservaciones(archivo.get("OBSERVACIONES").toString());
				}
				// ShvParamCliente
				if (!Validaciones.isObjectNull(archivo.get("ID_EMPRESA"))) {
					reg.setIdEmpresa(archivo.get("ID_EMPRESA").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("CUIT_CLIENTE"))) {
					reg.setCuit(archivo.get("CUIT_CLIENTE").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("HOLDING"))) {
					reg.setHolding(((BigDecimal) archivo.get("HOLDING")).longValue());
				}
				if (!Validaciones.isObjectNull(archivo.get("DESCRIPCION_HOLDING"))) {
					reg.setDescripcionHolding(archivo.get("DESCRIPCION_HOLDING").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("ID_AGENCIA_NEGOCIO"))) {
					reg.setIdAgenciaNegocio(((BigDecimal) archivo.get("ID_AGENCIA_NEGOCIO")).longValue());
				}
				if (!Validaciones.isObjectNull(archivo.get("DESCRIPCION_AGENCIA_NEGOCIO"))) {
					reg.setDescripcionAgenciaNegocio(archivo.get("DESCRIPCION_AGENCIA_NEGOCIO").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("ID_UNIDAD_NEGOCIO"))) {
					reg.setIdUnidadNegocio(archivo.get("ID_UNIDAD_NEGOCIO").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("DESCRIPCION_UNIDAD_NEGOCIO"))) {
					reg.setDescripcionUnidadNegocio(archivo.get("DESCRIPCION_UNIDAD_NEGOCIO").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("PERMITE_USO_TA"))) {
					reg.setAplicaTA((SiNoEnum.getEnumByName(archivo.get("PERMITE_USO_TA").toString())).getDescripcion());
				}
				if (!Validaciones.isObjectNull(archivo.get("PERMITE_USO_TP"))) {
					reg.setAplicaTP((SiNoEnum.getEnumByName(archivo.get("PERMITE_USO_TP").toString())).getDescripcion());
				}
				if (!Validaciones.isObjectNull(archivo.get("PERMITE_USO_CV"))) {
					reg.setAplicaCV((SiNoEnum.getEnumByName(archivo.get("PERMITE_USO_CV").toString())).getDescripcion());
				}
				if (!Validaciones.isObjectNull(archivo.get("PERMITE_USO_FT"))) {
					reg.setAplicaFT((SiNoEnum.getEnumByName(archivo.get("PERMITE_USO_FT").toString())).getDescripcion());
				}
				if (!Validaciones.isObjectNull(archivo.get("PERMITE_USO_NX"))) {
					reg.setAplicaNX((SiNoEnum.getEnumByName(archivo.get("PERMITE_USO_NX").toString())).getDescripcion());
				}
				// Fin ShvParamCliente		
				
				resultado.add(reg);
			}
			
			return resultado;
		} catch (Exception e) {
			throw new PersistenciaExcepcion(e.getMessage(), e);
		}

	}	
	
	/**
	 * 
	 * @return
	 */
	private QueryParametrosUtil obtenerSQLValores() {
				
		StringBuffer consulta = new StringBuffer("");
		
		consulta.append("				select valor.id_valor ");
		consulta.append("					 , valor.empresa ");
		consulta.append("					 , valor.segmento ");
		consulta.append("					 , valor.id_cliente_legado ");
		consulta.append("					 , valor.razon_social_cliente_perfil ");
		consulta.append("					 , valor.tipo_valor ");
		consulta.append("					 , valor.estado_valor ");
		consulta.append("					 , valor.origen ");
		consulta.append("					 , valor.acuerdo ");
		consulta.append("					 , valor.importe ");
		consulta.append("					 , valor.bd_impresa ");
		consulta.append("					 , valor.bd_enviada_mail ");
		consulta.append("					 , valor.fecha_alta ");
		consulta.append("					 , valor.id_analista ");
		consulta.append("					 , valor.nro_recibo ");
		consulta.append("					 , valor.id_constancia_recepcion ");
		consulta.append("					 , valor.operacion_asociada ");
		consulta.append("					 , valor.factura_relacionada ");
		consulta.append("					 , valor.documentacion_orig_recibida ");
		consulta.append("					 , valor.motivo ");
		consulta.append("					 , valor.banco_deposito ");
		consulta.append("					 , valor.id_copropietario ");
		consulta.append("					 , valor.usuario_autorizacion ");
		consulta.append("					 , valor.numero_documento_contable ");
		consulta.append("					 , valor.motivo_suspension ");
		consulta.append("					 , valor.id_legajo_cheque_rechazado ");
		consulta.append("					 , valor.fecha_notificacion_rechazo ");
		consulta.append("					 , valor.fecha_rechazo ");
		consulta.append("					 , valor.ejecutivo ");
		consulta.append("					 , valor.asistente ");
		consulta.append("					 , valor.saldo_disponible ");
		consulta.append("					 , valor.fecha_ingreso_recibo ");
		consulta.append("					 , valor.fecha_emision ");
		consulta.append("					 , valor.fecha_transferencia ");
		consulta.append("					 , valor.fecha_deposito ");
		consulta.append("					 , valor.fecha_ultimo_estado ");
		consulta.append("					 , valor.fecha_disponible ");
		consulta.append("					 , valor.nro_boleta ");
		consulta.append("					 , valor.descripcion_banco_origen ");
		consulta.append("					 , valor.fecha_vencimiento ");
		consulta.append("					 , valor.descripcion_tipo_retencion ");
		consulta.append("					 , valor.nro_cuit_retencion ");
		consulta.append("					 , valor.provincia_retencion ");
		consulta.append("					 , valor.referencia_valor ");
		consulta.append("					 , valor.id_analista_team_comercial ");
		consulta.append("					 , valor.id_supervisor_team_comercial ");
		consulta.append("					 , valor.id_gerente_reg_team_comercial ");
		consulta.append("					 , valor.codigo_organismo ");
		consulta.append("					 , valor.observaciones ");
		consulta.append("					 , valor.fecha_valor ");
		consulta.append("					 , decode(cliente.id_empresa,null,'TA',cliente.id_empresa) as id_empresa");
		consulta.append("					 , valor.CUIT_cliente ");
		consulta.append("					 , valor.holding ");
		consulta.append("					 , valor.descripcion_holding ");
		consulta.append("					 , valor.id_agencia_negocio ");
		consulta.append("					 , valor.descripcion_agencia_negocio ");
		consulta.append("					 , cliente.id_unidad_negocio ");
		consulta.append("					 , cliente.descripcion_unidad_negocio ");
		consulta.append("					 , valor.permite_uso_ta ");
		consulta.append("					 , valor.permite_uso_tp ");
		consulta.append("					 , valor.permite_uso_cv ");
		consulta.append("					 , valor.permite_uso_ft ");
		consulta.append("					 , valor.permite_uso_nx ");
		consulta.append("				 from shv_sop_busqueda_valores valor ");
		consulta.append("				 full outer join shv_param_cliente cliente ");
		consulta.append("				 on cliente.id_cliente_legado = valor.id_cliente_legado  ");
		consulta.append("				 where valor.id_valor is not null ");
		
		QueryParametrosUtil qp = new QueryParametrosUtil(consulta.toString());		

		return qp;
	}
	

	public QueryParametrosUtil  Paginado(Long registrosXPagina , Long pagina , String query){
		
		QueryParametrosUtil qp ;
		
		
		if (Validaciones.isObjectNull(pagina) || Validaciones.isObjectNull(registrosXPagina) || pagina <= 0 || registrosXPagina <= 0){
			 qp = new QueryParametrosUtil(query);
			return qp;
		}
		Long min = (registrosXPagina * (pagina-1)+1);
		Long max = registrosXPagina * (pagina); 
		
		
		StringBuffer consulta = new StringBuffer("");
		
		consulta.append(query);
		consulta.append("  and valor.id_valor between ? and ?  ");
		
		qp = new QueryParametrosUtil(consulta.toString());		
		qp.addCampoAlQuery(min, Types.NUMERIC);
		qp.addCampoAlQuery(max, Types.NUMERIC);

		return qp;
	}
	
	
	public Long cantidadRegistros() throws PersistenciaExcepcion{
		String query = this.obtenerSQLValores().getSql();
		StringBuffer consulta = new StringBuffer("");
		consulta.append("  select count(*) as cantidad from (  ");
		consulta.append(query);
		consulta.append("  )  ");
		
		
		Map<String, Object> resultadoQuery;
		Long cantidad = 0l;
		try {
			resultadoQuery = buscarUsandoSQL(new  QueryParametrosUtil(consulta.toString())).get(0);
			if (!Validaciones.isObjectNull(resultadoQuery.get("CANTIDAD"))) {
				cantidad =  (((BigDecimal) resultadoQuery.get("CANTIDAD")).longValue());
			}
		} catch (Exception e) {
			throw new  PersistenciaExcepcion(e);
		}
		return cantidad;
				
		
	}
	
	
}