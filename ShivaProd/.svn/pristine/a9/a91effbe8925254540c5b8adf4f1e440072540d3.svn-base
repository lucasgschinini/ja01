package ar.com.telecom.shiva.persistencia.dao.impl;

import java.math.BigDecimal;
import java.sql.Types;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.enumeradores.EmpresaEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoAcuerdoFacturacionEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoCargoGeneradoEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoFacturaMedioPagoEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoOperacionMasivaEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoTransaccionEnum;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.OrigenDocumentoEnum;
import ar.com.telecom.shiva.base.enumeradores.QueryMarcaEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.SociedadEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoCobroEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoCreditoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoDUCEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoFacturaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoFacturacionIceEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoMedioPagoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoMensajeEstadoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoRemanenteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoRetencionEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTratamientoDiferenciaEnum;
import ar.com.telecom.shiva.base.enumeradores.TratamientoInteresesEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.servicios.ICombosServicio;
import ar.com.telecom.shiva.negocio.servicios.bean.Bean;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteCobrosOnline;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteConsultaCapPdfCap;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteConsultaDeudaPdfCap;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteMotorConciliacion;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteOperacionesMasivas;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteRegistrosAvcSinConciliar;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaCobroHistorial;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaCobroTransaccion;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaDescobro;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaDescobroHistorial;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaDescobroOperacionesRelacionadas;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaDescobroTransaccion;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaLegajoChequeRechazado;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaLegajoCobrosRelacionado;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaLegajoCobrosRelacionadoSimplificado;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaLegajoDetalleDocumento;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaOperacionMasivaHistorial;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaReciboPreimpreso;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaTalonarios;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaTareaPendiente;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaValor;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaValorDisponible;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoCobroCreditoDebito;
import ar.com.telecom.shiva.persistencia.dao.Dao;
import ar.com.telecom.shiva.persistencia.dao.IParamRespWfTareaDao;
import ar.com.telecom.shiva.persistencia.dao.IVistaSoporteDao;
import ar.com.telecom.shiva.persistencia.dao.util.QueryParametrosUtil;
import ar.com.telecom.shiva.persistencia.modelo.ShvSopExcepcionMorosidad;
import ar.com.telecom.shiva.persistencia.modelo.ShvTalCompensacion;
import ar.com.telecom.shiva.persistencia.repository.LegajoChequeRechazadoRepositorio;
import ar.com.telecom.shiva.presentacion.bean.dto.ClienteDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ValorDto;
import ar.com.telecom.shiva.presentacion.bean.filtro.ExcepcionMorosidadFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.TalonarioFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteBusquedaCobroHistorialFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteBusquedaDescobroHistorialFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteBusquedaReciboPreImpresoFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteBusquedaValoresFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteCobroOnlineFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteCobroTransaccionFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteDescobroFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteDescobroOperacionRelacionadaFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteDescobroTransaccionFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteLegajoChequeRechazadoCobroRelacionadoFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteLegajoChequeRechazadoFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteOperacionMasivaFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteRegistrosAvcSinConciliarFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteTareasPendientesFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteValoresDisponiblesFiltro;

public class VistaSoporteDaoImpl extends Dao implements IVistaSoporteDao {
	
	@Autowired
	protected ICombosServicio combosServicio;
	
	@Autowired LegajoChequeRechazadoRepositorio legajoChequeRechazadoRepositorio;

	@Autowired IParamRespWfTareaDao paramRespWfTareaDao;
	
	@Override
	public List<VistaSoporteResultadoBusquedaValorDisponible> consultarValoresDisponibles(
			VistaSoporteValoresDisponiblesFiltro filtro) throws PersistenciaExcepcion {

		List<Map<String, Object>> listaResultadoQuery;

		try {
			QueryParametrosUtil qp = obtenerSQLConsultaValoresDisponibles(filtro);
			listaResultadoQuery = buscarUsandoSQL(qp);
	
			List<VistaSoporteResultadoBusquedaValorDisponible> resultado = new ArrayList<VistaSoporteResultadoBusquedaValorDisponible>();
		
			for (Map<String, Object> archivo : listaResultadoQuery) {
				
				 VistaSoporteResultadoBusquedaValorDisponible reg = new VistaSoporteResultadoBusquedaValorDisponible();
				 
				 reg.setIdValor(archivo.get("ID_VALOR").toString());
				 reg.setIdTipoValor(archivo.get("ID_TIPO_VALOR").toString());
				 reg.setIdTipoMedioPago(archivo.get("ID_TIPO_MEDIO_PAGO").toString());
				 reg.setDescripcionTipoMedioPago(archivo.get("DESCRIPCION_TIPO_MEDIO_PAGO").toString());
				 reg.setImporte(archivo.get("IMPORTE").toString());
				 reg.setSaldoDisponible(archivo.get("SALDO_DISPONIBLE").toString());

				 if (!Validaciones.isObjectNull(archivo.get("FECHA_VALOR"))) {
					 reg.setFechaValor(archivo.get("FECHA_VALOR").toString());
				 } 
				 
				 if (!Validaciones.isObjectNull(archivo.get("DESCRIPCION_MOTIVO"))) {
					 reg.setDescripcionMotivo(archivo.get("DESCRIPCION_MOTIVO").toString());
				 }
	
				 if (!Validaciones.isObjectNull(archivo.get("OPERACION_ASOCIADA"))) {
					 reg.setOperacionAsociada(archivo.get("OPERACION_ASOCIADA").toString());
				 }
				 
				 reg.setIdClienteLegado(archivo.get("ID_CLIENTE_LEGADO").toString());
				 if (!Validaciones.isObjectNull(archivo.get("ID_ACUERDO"))) {
					 reg.setIdAcuerdo(archivo.get("ID_ACUERDO").toString());
				 }
				 
				 if (!Validaciones.isObjectNull(archivo.get("DESCRIPCION_ORIGEN"))) {
					 reg.setDescripcionOrigen(archivo.get("DESCRIPCION_ORIGEN").toString());
				 }
				 
				 if (!Validaciones.isObjectNull(archivo.get("RETENCION_ID_TIPO_RET_IMPUESTO"))) {
					 reg.setRetencionIdTipoRetencionImpuesto(archivo.get("RETENCION_ID_TIPO_RET_IMPUESTO").toString());
				 }
				 if (!Validaciones.isObjectNull(archivo.get("RETENCION_NUMERO_CONSTANCIA"))) {
					 reg.setRetencionNumeroConstancia(archivo.get("RETENCION_NUMERO_CONSTANCIA").toString());
				 }
				 if (!Validaciones.isObjectNull(archivo.get("RETENCION_FECHA_EMISION"))) {
					 reg.setRetencionFechaEmision(archivo.get("RETENCION_FECHA_EMISION").toString());
				 }
				 
				 if (!Validaciones.isObjectNull(archivo.get("RETENCION_CUIT"))) {
					 reg.setRetencionCUIT(archivo.get("RETENCION_CUIT").toString());
				 }
				 if (!Validaciones.isObjectNull(archivo.get("RETENCION_DESCRIPCION_JURISDIC"))) {
					 reg.setRetencionDescripcionJurisdiccion(archivo.get("RETENCION_DESCRIPCION_JURISDIC").toString());
				 }
				 if (!Validaciones.isObjectNull(archivo.get("RETENCION_TIPO_COMPROBANTE"))) {
					 reg.setRetencionTipoComprobante(archivo.get("RETENCION_TIPO_COMPROBANTE").toString());
				 }
				 if (!Validaciones.isObjectNull(archivo.get("RETENCION_TIPO_LETRA_COMP"))) {
					 reg.setRetencionTipoLetraComprobante(archivo.get("RETENCION_TIPO_LETRA_COMP").toString());
				 }
				 if (!Validaciones.isObjectNull(archivo.get("RETENCION_NUMERO_COMPROBANTE"))) {
					 reg.setRetencionNumeroComprobante(archivo.get("RETENCION_NUMERO_COMPROBANTE").toString());
				 }
				 
				 if (!Validaciones.isObjectNull(archivo.get("BANCO_ORIGEN"))) {
					 reg.setBancoOrigen(archivo.get("BANCO_ORIGEN").toString());
				 }
				 if (!Validaciones.isObjectNull(archivo.get("NUMERO_BOLETA"))) {
					 reg.setNumeroBoleta(archivo.get("NUMERO_BOLETA").toString());
				 }
				 if (!Validaciones.isObjectNull(archivo.get("CHEQUE_NUMERO_CHEQUE"))) {
					 reg.setChequeNumeroCheque(archivo.get("CHEQUE_NUMERO_CHEQUE").toString());
				 }
		
				 if (!Validaciones.isObjectNull(archivo.get("TRANSF_NUMERO_REFERENCIA"))) {
					 reg.setTransferenciaNumeroReferencia(archivo.get("TRANSF_NUMERO_REFERENCIA").toString());
				 }
				 if (!Validaciones.isObjectNull(archivo.get("TRANSF_CUIT"))) {
					 reg.setTransferenciaCUIT(archivo.get("TRANSF_CUIT").toString());
				 }
				 
				 if (!Validaciones.isObjectNull(archivo.get("INTERDEP_NUMERO_INTERDEPOSITO"))) {
					 reg.setInterdepositoNumeroInterdeposito(archivo.get("INTERDEP_NUMERO_INTERDEPOSITO").toString());
				 }
				 if (!Validaciones.isObjectNull(archivo.get("INTERDEP_CODIGO_ORGANISMO"))) {
					 reg.setInterdepositoCodigoOrganismo(archivo.get("INTERDEP_CODIGO_ORGANISMO").toString());
				 }
				 
				 resultado.add(reg);
			}
			return resultado;
		} catch (Exception e) {
			throw new PersistenciaExcepcion(e.getMessage(), e);
		}

	}	


	/**
	 * 
	 * @param filtro
	 * @return
	 */
	private QueryParametrosUtil obtenerSQLConsultaValoresDisponibles(VistaSoporteValoresDisponiblesFiltro filtro) {
	
		StringBuffer consulta = new StringBuffer();
		QueryParametrosUtil parametros = new QueryParametrosUtil();
		consulta.append("select * "
					  + "from shv_sop_ws_valores_disponibles "
		              + "where id_cliente_legado = ? ");
		parametros.addCampoAlQuery(filtro.getIdClienteLegado(), Types.NUMERIC);
				
		if (!Validaciones.isNullOrEmpty(filtro.getIdTipoMedioPago())) {
			consulta.append(" and id_tipo_medio_pago = ? ");
			parametros.addCampoAlQuery(filtro.getIdTipoMedioPago(), Types.VARCHAR);
		}
		parametros.setSql(consulta.toString());
		return parametros;
	}	
	
	@Override
	public List<VistaSoporteResultadoBusquedaTareaPendiente> consultarTareasPendientes(
			VistaSoporteTareasPendientesFiltro filtro) throws PersistenciaExcepcion {

		List<Map<String, Object>> listaResultadoQuery;
		try {
			QueryParametrosUtil qp = obtenerSQLConsultaTareasPendientes(filtro);
			listaResultadoQuery = buscarUsandoSQL(qp);
	
			List<VistaSoporteResultadoBusquedaTareaPendiente> resultado = new ArrayList<VistaSoporteResultadoBusquedaTareaPendiente>();
		
			for (Map<String, Object> archivo : listaResultadoQuery) {
				
				VistaSoporteResultadoBusquedaTareaPendiente reg = new VistaSoporteResultadoBusquedaTareaPendiente();
				 
				reg.setIdTarea(archivo.get("ID_TAREA").toString());
				reg.setTipoTarea(archivo.get("TIPO_TAREA").toString());
				reg.setEstadoTarea(archivo.get("ESTADO_TAREA").toString());
				reg.setFechaCreacion(archivo.get("FECHA_CREACION").toString());
				reg.setUsuarioCreacion(archivo.get("USUARIO_CREACION").toString());
	
				if (!Validaciones.isObjectNull(archivo.get("FECHA_EJECUCION"))) {
					reg.setFechaEjecucion(archivo.get("FECHA_EJECUCION").toString());
				}
	
				if (!Validaciones.isObjectNull(archivo.get("USUARIO_EJECUCION"))) {
					reg.setUsuarioEjecucion(archivo.get("USUARIO_EJECUCION").toString());
				}
	
				if (!Validaciones.isObjectNull(archivo.get("INFORMACION_ADICIONAL"))) {
					reg.setInformacionAdicional(archivo.get("INFORMACION_ADICIONAL").toString());
				}
	
				reg.setGestionaSobre(archivo.get("GESTIONA_SOBRE").toString());
				if(archivo.get("PERFIL_ASIGNACION")!=null)
				reg.setPerfilAsignacion(archivo.get("PERFIL_ASIGNACION").toString());
				
				Object usuarioAsignacion = archivo.get("USUARIO_ASIGNACION");
				reg.setUsuarioAsignacion((!Validaciones.isObjectNull(usuarioAsignacion)) ? usuarioAsignacion.toString() : null);
				
				reg.setIdWorkflowAsociado(archivo.get("ID_WORKFLOW_ASOCIADO").toString());
				reg.setEstadoWorkflowAsociado(archivo.get("ESTADO_WORKFLOW_ASOCIADO").toString());
				
				reg.setNroCliente((!Validaciones.isObjectNull(archivo.get("NUMERO_CLIENTE"))) ? archivo.get("NUMERO_CLIENTE").toString() : "");
				reg.setRazonSocial((!Validaciones.isObjectNull(archivo.get("RAZON_SOCIAL"))) ? archivo.get("RAZON_SOCIAL").toString() : "");
				reg.setImporte((!Validaciones.isObjectNull(archivo.get("IMPORTE"))) ? (BigDecimal) archivo.get("IMPORTE") : null);
				
				if (!Validaciones.isObjectNull(archivo.get("ID_REGISTRO_AVC"))) {
					reg.setIdRegistroAvc(archivo.get("ID_REGISTRO_AVC").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("ID_VALOR_POR_REVERSION"))) {
					reg.setIdValorPorReversion(archivo.get("ID_VALOR_POR_REVERSION").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("ID_VALOR"))) {
					reg.setIdValor(archivo.get("ID_VALOR").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("ID_TALONARIO"))) {
					reg.setIdTalonario(archivo.get("ID_TALONARIO").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("ID_OPERACION_MASIVA"))) {
					reg.setIdOperacionMasiva(archivo.get("ID_OPERACION_MASIVA").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("ID_COBRO"))) {
					reg.setIdCobro(archivo.get("ID_COBRO").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("ID_OPERACION"))) {
					reg.setIdOperacion(archivo.get("ID_OPERACION").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("ID_DESCOBRO"))) {
					reg.setIdDescobro(archivo.get("ID_DESCOBRO").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("MONEDA_IMPORTE"))) {
					reg.setMonedaImporte(archivo.get("MONEDA_IMPORTE").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("ID_EMPRESA"))) {
					reg.setIdEmpresa(archivo.get("ID_EMPRESA").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("DESCRIPCION_EMPRESA"))) {
					reg.setDescripcionEmpresa(archivo.get("DESCRIPCION_EMPRESA").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("ID_SEGMENTO"))) {
					reg.setIdSegmento(archivo.get("ID_SEGMENTO").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("DESCRIPCION_SEGMENTO"))) {
					reg.setDescripcionSegmento(archivo.get("DESCRIPCION_SEGMENTO").toString());			 
				}
				
				
				//
				// INFORMACION ADICIONAL - CAMPOS SEPARADOS
				//
				if (!Validaciones.isObjectNull(archivo.get("NUMERO_BOLETA"))) {
					reg.setNroBoleta(archivo.get("NUMERO_BOLETA").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("DESCRIPCION_BANCO"))) {
					reg.setDescBanco(archivo.get("DESCRIPCION_BANCO").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("NUMERO_CHEQUE"))) {
					reg.setNroCheque(archivo.get("NUMERO_CHEQUE").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("FECHA_VENCIMIENTO"))) {
					reg.setVencimiento(archivo.get("FECHA_VENCIMIENTO").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("CODIGO_INTERDEPOSITO"))) {
					reg.setCodInterdeposito(archivo.get("CODIGO_INTERDEPOSITO").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("REFERENCIA"))) {
					reg.setReferencia(archivo.get("REFERENCIA").toString());
				}
				
				
				if (!Validaciones.isObjectNull(archivo.get("TIPO"))) {
					reg.setTipo(archivo.get("TIPO").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("NRO_CONSTANCIA"))) {
					reg.setNroConstancia(archivo.get("NRO_CONSTANCIA").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("CUIT"))) {
					reg.setCuit(archivo.get("CUIT").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("PROVINCIA"))) {
					reg.setProvincia(archivo.get("PROVINCIA").toString());
				}
				
				
				if (!Validaciones.isObjectNull(archivo.get("COD_ORGANISMO"))) {
					reg.setCodOrganismo(archivo.get("COD_ORGANISMO").toString());
				}
				
				
				if (!Validaciones.isObjectNull(archivo.get("RANGO"))) {
					reg.setRango(archivo.get("RANGO").toString());
				}
				
				//
				// FIN INFORMACION ADICIONAL - CAMPOS SEPARADOS
				//
				
				if (!Validaciones.isObjectNull(archivo.get("SOCIEDAD"))) {
					reg.setSociedad(SociedadEnum.getEnumByName(archivo.get("SOCIEDAD").toString()));
				}
				
				if (!Validaciones.isObjectNull(archivo.get("SISTEMA"))) {
					reg.setSistema(SistemaEnum.getEnumByName(archivo.get("SISTEMA").toString()));
				}
				
				
				resultado.add(reg);
			}
			return resultado;
		} catch (PersistenciaExcepcion e) {
			throw new PersistenciaExcepcion(e.getMessage(), e);
		}
	}	

	
	private QueryParametrosUtil obtenerSQLConsultaTareasPendientes(VistaSoporteTareasPendientesFiltro filtro) {
		QueryParametrosUtil parametros = new QueryParametrosUtil();
		String consulta = ""; 
		
		if (Validaciones.isNullOrEmpty(filtro.getIdFiltroSeleccionado())){
			filtro.setIdFiltroSeleccionado(Constantes.BANDEJA_ENTRADA_ID_FILTRO_TODOS);
		}
		
		// FILTRO TODAS LAS TAREAS Ó TAREAS DERIVADAS
		if (filtro.getIdFiltroSeleccionado().equals(Constantes.BANDEJA_ENTRADA_ID_FILTRO_TODOS) 
				|| filtro.getIdFiltroSeleccionado().equals(Constantes.BANDEJA_ENTRADA_ID_FILTRO_DERIVADAS)) {
			consulta += " where upper(usuario_creacion) = ? ";
			parametros.addCampoAlQuery(filtro.getUsuarioLogeado().getUsuario().toUpperCase(), Types.VARCHAR);
		}
		
		// FILTRO TODAS LAS TAREAS Ó MIS TAREAS
		if (filtro.getIdFiltroSeleccionado().equals(Constantes.BANDEJA_ENTRADA_ID_FILTRO_TODOS) 
				|| filtro.getIdFiltroSeleccionado().equals(Constantes.BANDEJA_ENTRADA_ID_FILTRO_MIS_TAREAS)) {
			consulta += (Validaciones.isNullOrEmpty(consulta)) ? " where " : " or "; 
			consulta += "(upper(perfil_asignacion) in (";
			String perfiles = "";
			for (String perfil: filtro.getUsuarioLogeado().getPerfilesCompletos()) {
				perfiles += "?, ";
				parametros.addCampoAlQuery(perfil, Types.VARCHAR);
			}
			consulta += perfiles.substring(0, perfiles.length()-2) + ") and usuario_asignacion is null) ";
			consulta += " or upper(usuario_asignacion) = ? ";
			parametros.addCampoAlQuery(filtro.getUsuarioLogeado().getUsuario().toUpperCase(), Types.VARCHAR);
		}
		consulta = "select * from shv_sop_bandeja_de_entrada " + consulta;
		parametros.setSql(consulta);
		return parametros;
	}

	
	private QueryParametrosUtil obtenerSQLBuscarValoresPorCobro(VistaSoporteBusquedaValoresFiltro filtro) {

		StringBuffer consulta = new StringBuffer();  
	
		QueryParametrosUtil parametros = new QueryParametrosUtil();

		consulta.append("select ");
		consulta.append("  distinct scmps.id_valor ");
		consulta.append("from ");
		consulta.append("  shv_cob_cobro scc, "); 
		consulta.append("  shv_cob_transaccion sct, "); 
		consulta.append("  shv_cob_med_pago scmp, ");
		consulta.append("  shv_cob_med_pag_shiva scmps, ");
		consulta.append("  shv_wf_workflow_estado swwe ");
		consulta.append("where "); 
		consulta.append("  sct.id_operacion = scc.id_operacion ");
		consulta.append("  and scmp.id_transaccion = sct.id_transaccion ");
		consulta.append("  and scmp.id_medio_pago = scmps.id_medio_pago ");
		consulta.append("  and swwe.id_workflow = scc.id_workflow ");
		consulta.append("  and sct.estado in(?, ?, ?, ?, ?, ?, ?, ?) ");
		consulta.append("  and scc.id_operacion = ? ");  
		
		parametros.addCampoAlQuery(EstadoTransaccionEnum.PENDIENTE.name(), Types.VARCHAR);
		parametros.addCampoAlQuery(EstadoTransaccionEnum.APROPIADA.name(), Types.VARCHAR);
		parametros.addCampoAlQuery(EstadoTransaccionEnum.ERROR_CONFIRMACION.name(), Types.VARCHAR);
		parametros.addCampoAlQuery(EstadoTransaccionEnum.ERROR_DESAPROPIACION.name(), Types.VARCHAR);
		parametros.addCampoAlQuery(EstadoTransaccionEnum.EN_PROCESO_APROPIACION.name(), Types.VARCHAR);
		parametros.addCampoAlQuery(EstadoTransaccionEnum.EN_PROCESO_CONFIRMACION.name(), Types.VARCHAR);
		parametros.addCampoAlQuery(EstadoTransaccionEnum.EN_PROCESO_DESAPROPIACION.name(), Types.VARCHAR);
		parametros.addCampoAlQuery(EstadoTransaccionEnum.CONFIRMADA.name(), Types.VARCHAR);
	
		parametros.addCampoAlQuery(filtro.getIdCobroShivaFiltro(), Types.NUMERIC);
		
		parametros.setSql(consulta.toString());
		return parametros;
	}
	
	
	/** @author u573005 Sprint3, defecto 133
	 * Se agrego el campo ID_BOLETA para consultar los datos del valor
	 * en conciliacion sugerida
	 **/
	@Override
	public List<VistaSoporteResultadoBusquedaValor> buscarValores(
			VistaSoporteBusquedaValoresFiltro filtro) throws PersistenciaExcepcion {

		//
		// Busqueda de id de valores para un cobro dado
		//
		if (!Validaciones.isNullOrEmpty(filtro.getIdCobroShivaFiltro())) {
			
			List<Map<String, Object>> listaResultadoQueryIdValoresPorCobro;
			
			QueryParametrosUtil qp = obtenerSQLBuscarValoresPorCobro(filtro);
			listaResultadoQueryIdValoresPorCobro = buscarUsandoSQL(qp);
			
			List<Long> resultado = new ArrayList<>();
			
			for (Map<String, Object> registro : listaResultadoQueryIdValoresPorCobro) {
				if (!Validaciones.isObjectNull(registro.get("ID_VALOR"))) {
					resultado.add(new Long(registro.get("ID_VALOR").toString()));
				}
			}
			
			filtro.setIdValorDeBusqueraPorFiltroCobro(resultado);
		}
		
		//
		// Busqueda de Valores
		//
		List<Map<String, Object>> listaResultadoQuery;

		try {
			QueryParametrosUtil qp = obtenerSQLBuscarValoresParametros(filtro);
			listaResultadoQuery = buscarUsandoSQL(qp);
			
			List<VistaSoporteResultadoBusquedaValor> resultado = new ArrayList<>();
			for (Map<String, Object> archivo : listaResultadoQuery) {
				
				VistaSoporteResultadoBusquedaValor reg = new VistaSoporteResultadoBusquedaValor();

				if (!Validaciones.isObjectNull(archivo.get("EMPRESA"))) {
					reg.setEmpresa(archivo.get("EMPRESA").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("EMPRESAS_ASOCIADAS"))) {
					reg.setEmpresasAsociadas(archivo.get("EMPRESAS_ASOCIADAS").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("FECHA_NOTIF_DISPON_RETIRO_VAL"))) {
					reg.setFechaNtifDisponRetiroVal((Date)archivo.get("FECHA_NOTIF_DISPON_RETIRO_VAL"));
				}
				if (!Validaciones.isObjectNull(archivo.get("SEGMENTO"))) {
					reg.setSegmento(archivo.get("SEGMENTO").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("CUIT_CLIENTE"))) {
					reg.setCuitCliente(archivo.get("CUIT_CLIENTE").toString());
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
				if (!Validaciones.isObjectNull(archivo.get("NRO_VALOR"))) {
					reg.setNroValor(archivo.get("NRO_VALOR").toString());
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
				if (!Validaciones.isObjectNull(archivo.get("NOMBRE_APELLIDO_ANALISTA"))) {
					reg.setNombreApellidoAnalista(archivo.get("NOMBRE_APELLIDO_ANALISTA").toString());
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
				if (!Validaciones.isObjectNull(archivo.get("ID_MOTIVO"))) {
					reg.setMotivo(archivo.get("ID_MOTIVO").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("BANCO_DEPOSITO"))) {
					reg.setBancoDeposito(archivo.get("BANCO_DEPOSITO").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("ID_BANCO_DEPOSITO"))) {
					reg.setIdBancoDeposito(archivo.get("ID_BANCO_DEPOSITO").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("ID_COPROPIETARIO"))) {
					reg.setIdCopropietario(archivo.get("ID_COPROPIETARIO").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("NOMBRE_APELLIDO_COPROPIETARIO"))) {
					reg.setNombreApellidoCopropietario(archivo.get("NOMBRE_APELLIDO_COPROPIETARIO").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("USUARIO_AUTORIZACION"))) {
					reg.setUsuarioAutorizacion(archivo.get("USUARIO_AUTORIZACION").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("NOMBRE_APELLIDO_USUARIO_AUTORIZACION"))) {
					reg.setNombreApellidoUsuarioAutorizacion(archivo.get("NOMBRE_APELLIDO_USUARIO_AUTORIZACION").toString());
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
				if (!Validaciones.isObjectNull(archivo.get("NOMBRE_APELLIDO_EJECUTIVO"))) {
					reg.setNombreApellidoEjecutivo(archivo.get("NOMBRE_APELLIDO_EJECUTIVO").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("ASISTENTE"))) {
					reg.setAsistente(archivo.get("ASISTENTE").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("NOMBRE_APELLIDO_ASISTENTE"))) {
					reg.setNombreApellidoAsistente(archivo.get("NOMBRE_APELLIDO_ASISTENTE").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("SALDO_DISPONIBLE"))) {
					reg.setSaldoDisponible((BigDecimal) archivo.get("SALDO_DISPONIBLE"));
				}
				if (!Validaciones.isObjectNull(archivo.get("FECHA_INGRESO_RECIBO"))) {
					reg.setFechaIngresoRecibo((Date) archivo.get("FECHA_INGRESO_RECIBO"));
				}
				if (!Validaciones.isObjectNull(archivo.get("FECHA_RETENCION"))) {
					reg.setFechaEmision(archivo.get("FECHA_RETENCION").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("FECHA_EMISION"))) {
					reg.setFechaEmisionCheque(String.valueOf(Utilidad.formatDatePicker((Date)archivo.get("FECHA_EMISION"))));
				}
				if (!Validaciones.isObjectNull(archivo.get("FECHA_VENCIMIENTO"))) {
					reg.setFechaVencimiento((Date) archivo.get("FECHA_VENCIMIENTO"));
				}
				if (!Validaciones.isObjectNull(archivo.get("FECHA_TRANSFERENCIA"))) {
					reg.setFechaTransferencia((Date) archivo.get("FECHA_TRANSFERENCIA"));
				}
				if (!Validaciones.isObjectNull(archivo.get("VALOR_PADRE"))) {
					reg.setValorPadre(archivo.get("VALOR_PADRE").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("ARCHIVO_DE_VALORES_A_CONCILIAR"))) {
					reg.setArchivoDeValoresAConciliar(archivo.get("ARCHIVO_DE_VALORES_A_CONCILIAR").toString());
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
				if (!Validaciones.isObjectNull(archivo.get("ID_ESTADO_VALOR"))) {
					reg.setIdEstadoValor(archivo.get("ID_ESTADO_VALOR").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("ID_TIPO_VALOR"))) {
					reg.setIdTipoValor(archivo.get("ID_TIPO_VALOR").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("ID_VALOR"))) {
					reg.setIdValor(((BigDecimal) archivo.get("ID_VALOR")).longValue());
				}
				if (!Validaciones.isObjectNull(archivo.get("ID_WORKFLOW"))) {
					reg.setIdWorkflow(((BigDecimal) archivo.get("ID_WORKFLOW")).longValue());
				}
				if (!Validaciones.isObjectNull(archivo.get("ID_ORIGEN"))) {
					reg.setIdOrigen(archivo.get("ID_ORIGEN").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("ID_EMPRESA"))) {
					reg.setIdEmpresa(archivo.get("ID_EMPRESA").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("ID_SEGMENTO"))) {
					reg.setIdSegmento(archivo.get("ID_SEGMENTO").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("FECHA_VALOR"))) {
					reg.setFechaValor((Date) archivo.get("FECHA_VALOR"));
				}
				if (!Validaciones.isObjectNull(archivo.get("ID_TIPO_RETENCION"))) {
					reg.setIdTipoRetencion(archivo.get("ID_TIPO_RETENCION").toString());
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
				if (!Validaciones.isObjectNull(archivo.get("ID_BANCO_ORIGEN"))) {
					reg.setIdBancoOrigen(archivo.get("ID_BANCO_ORIGEN").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("NRO_CUIT_RETENCION"))) {
					reg.setNroCuitRetencion(archivo.get("NRO_CUIT_RETENCION").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("PROVINCIA_RETENCION"))) {
					reg.setProvinciaRetencion(archivo.get("PROVINCIA_RETENCION").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("ID_PROVINCIA_RETENCION"))) {
					reg.setIdProvinciaRetencion(archivo.get("ID_PROVINCIA_RETENCION").toString());
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
				if (!Validaciones.isObjectNull(archivo.get("NOMBRE_ANALISTA_TEAM_COMERCIAL"))) {
					reg.setNombreAnalistaTeamComercial(archivo.get("NOMBRE_ANALISTA_TEAM_COMERCIAL").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("ID_SUPERVISOR_TEAM_COMERCIAL"))) {
					reg.setIdSupervisorTeamComercial(archivo.get("ID_SUPERVISOR_TEAM_COMERCIAL").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("NOMBRE_SUPERVIS_TEAM_COMERCIAL"))) {
					reg.setNombreSupervisorTeamComercial(archivo.get("NOMBRE_SUPERVIS_TEAM_COMERCIAL").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("ID_GERENTE_REG_TEAM_COMERCIAL"))) {
					reg.setIdGerenteRegionalTeamComercial(archivo.get("ID_GERENTE_REG_TEAM_COMERCIAL").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("NOMBRE_GERE_REG_TEAM_COMERCIAL"))) {
					reg.setNombreGerenteRegionalTeamComercial(archivo.get("NOMBRE_GERE_REG_TEAM_COMERCIAL").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("OBSERVACIONES"))) {
					reg.setObservaciones(archivo.get("OBSERVACIONES").toString());
				}
				//U562163
				if (Validaciones.isObjectNull(archivo.get("COBRO_FORMATEADO")) == false) {
					reg.setCobroFormateado(archivo.get("COBRO_FORMATEADO").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("ID_BOLETA"))) {
					reg.setIdBoleta(archivo.get("ID_BOLETA").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("FECHA_BUSQUEDA"))) {
					reg.setFechaBusqueda(String.valueOf(archivo.get("FECHA_BUSQUEDA")));
				}
				if (!Validaciones.isObjectNull(archivo.get("CUENTA_DEPOSITO"))) {
					reg.setCuentaDeposito(String.valueOf(archivo.get("CUENTA_DEPOSITO")));
				}
				if (!Validaciones.isObjectNull(archivo.get("EMAIL"))) {
					reg.setEmail(archivo.get("EMAIL").toString());
				}
				resultado.add(reg);
			}
			return resultado;
		} catch (Exception e) {
			throw new PersistenciaExcepcion(e.getMessage(), e);
		}

	}	
	
	/**
	 * 
	 * @param filtro
	 * @return
	 */
	private QueryParametrosUtil obtenerSQLBuscarValoresParametros(VistaSoporteBusquedaValoresFiltro filtro) {
		StringBuffer consulta = new StringBuffer();  
		StringBuffer condicion = new StringBuffer();
		QueryParametrosUtil parametros = new QueryParametrosUtil();
		BigDecimal importeLocal;
		consulta.append("SELECT * FROM SHV_SOP_BUSQUEDA_VALORES WHERE ");

		if (!Validaciones.isNullOrEmpty(filtro.getIdValor()) 
				&& Validaciones.isNullOrEmpty(filtro.getNroBoletaFiltro())
				&& Validaciones.isNullOrEmpty(filtro.getReferenciaDelValorFiltro())
				&& Validaciones.isNullOrEmpty(filtro.getIdCobroShivaFiltro())) {

			condicion.append(" ID_VALOR = ? ");
			parametros.addCampoAlQuery(filtro.getIdValor(), Types.NUMERIC);
		}
		 else {
			if(Validaciones.isCollectionNotEmpty(filtro.getIdValores())){
				String queryValores = " ID_VALOR in ";
				String valores = "(";
				for (Long idValores: filtro.getIdValores()) {
					valores += "?, ";
					parametros.addCampoAlQuery(idValores , Types.NUMERIC);
				}
				if (valores.length() > 3) {
					valores = valores.substring(0,
							valores.length() - 2) + ")";
				}
				queryValores += valores;
				condicion.append(queryValores);	
			}
			else{
			if (Validaciones.isCollectionNotEmpty(filtro.getListaBoletas())) {

				String queryBoletas = " ID_BOLETA in (";
				String boletas = "";
				for (String idBoleta: filtro.getListaBoletas()) {
					boletas += "?, ";
					parametros.addCampoAlQuery(idBoleta, Types.VARCHAR);
				}
				queryBoletas += boletas.substring(0, boletas.length()-2) + ")";
				condicion.append(queryBoletas);			

			} else {

				//Solo para filtros del cobroOnline creditos
				if (filtro.isCobroOnlineCreditosShiva() 
						&& Validaciones.isCollectionNotEmpty(filtro.getIdClientesLegado())) {

					condicion.append(" ID_ESTADO_VALOR <> ? ");
					parametros.addCampoAlQuery(Estado.VAL_NO_DISPONIBLE, Types.VARCHAR);

					String queryClientes = "  AND ID_CLIENTE_LEGADO in (";
					String clientes = "";
					for (String idCliente: filtro.getIdClientesLegado()) {
						clientes += "?, ";
						parametros.addCampoAlQuery(idCliente, Types.VARCHAR);
					}
					queryClientes += clientes.substring(0, clientes.length()-2) + ")";
					condicion.append(queryClientes);	

					String queryEstados = " AND ID_ESTADO_VALOR in (";
					String estados = "";
					for (String idEstado: filtro.getIdEstados()) {
						estados += "?, ";
						parametros.addCampoAlQuery(idEstado, Types.VARCHAR);
					}
					queryEstados += estados.substring(0, estados.length()-2) + ")";
					condicion.append(queryEstados);

					if (!Validaciones.isNullOrEmpty(filtro.getFechaDesde()) && !Validaciones.isNullOrEmpty(filtro.getFechaHasta())) {
						condicion.append(" AND TO_DATE(FECHA_BUSQUEDA, ?) BETWEEN TO_DATE(?,? )  AND TO_DATE(?,? ) ");
						parametros.addCampoAlQuery(Utilidad.ORACLE_DATE_TIME_FULL_FORMAT, Types.VARCHAR);

						parametros.addCampoAlQuery(filtro.getFechaDesde() + " 00:00:00", Types.VARCHAR);
						parametros.addCampoAlQuery(Utilidad.ORACLE_DATE_TIME_FULL_FORMAT, Types.VARCHAR);
						parametros.addCampoAlQuery(filtro.getFechaHasta() + " 23:59:59", Types.VARCHAR);
						parametros.addCampoAlQuery(Utilidad.ORACLE_DATE_TIME_FULL_FORMAT, Types.VARCHAR);
					} else if (!Validaciones.isNullOrEmpty(filtro.getFechaDesde()) && Validaciones.isNullOrEmpty(filtro.getFechaHasta())) {
						condicion.append(" AND TO_DATE(FECHA_BUSQUEDA, ?) >= TO_DATE(?,? ) ");	
						parametros.addCampoAlQuery(Utilidad.ORACLE_DATE_TIME_FULL_FORMAT, Types.VARCHAR);
						parametros.addCampoAlQuery(filtro.getFechaDesde() + " 00:00:00", Types.VARCHAR);
						parametros.addCampoAlQuery(Utilidad.ORACLE_DATE_TIME_FULL_FORMAT, Types.VARCHAR);
					} else if (Validaciones.isNullOrEmpty(filtro.getFechaDesde()) && !Validaciones.isNullOrEmpty(filtro.getFechaHasta())) {
						condicion.append(" AND TO_DATE(FECHA_BUSQUEDA, ?) <= TO_DATE(?,? ) ");	
						parametros.addCampoAlQuery(Utilidad.ORACLE_DATE_TIME_FULL_FORMAT, Types.VARCHAR);
						parametros.addCampoAlQuery(filtro.getFechaHasta() + " 23:59:59", Types.VARCHAR);
						parametros.addCampoAlQuery(Utilidad.ORACLE_DATE_TIME_FULL_FORMAT, Types.VARCHAR);
					}

					if(Validaciones.isCollectionNotEmpty(filtro.getListaTipoValor())) {
						String query = " AND ID_TIPO_VALOR in (";

						String datos = "";
						for (String id: filtro.getListaTipoValor()) {
							datos += "?, ";
							parametros.addCampoAlQuery(id, Types.VARCHAR);
						}
						query += datos.substring(0, datos.length()-2) + ")";
						condicion.append(query);	
					}
				} else {
					//Filtro por los valores en estado "NO DISPONIBLE"
					condicion.append(" ID_ESTADO_VALOR <> ? ");
					parametros.addCampoAlQuery(Estado.VAL_NO_DISPONIBLE, Types.VARCHAR);
					
//					condicion.append(" AND (ID_MOTIVO <> ? OR ID_MOTIVO IS NULL) ");
//					parametros.addCampoAlQuery(MotivoShivaEnum.VALOR_EN_GARANTIA.idMotivo(), Types.NUMERIC);

					if(!Validaciones.isNullEmptyOrDash(filtro.getNumeroDocumentoContable())){
						condicion.append(" AND NUMERO_DOCUMENTO_CONTABLE = ? ");
						parametros.addCampoAlQuery(filtro.getNumeroDocumentoContable(), Types.VARCHAR);
					}

					if(!Validaciones.isNullEmptyOrDash(filtro.getAcuerdo())){
						condicion.append(" AND ACUERDO = ? ");
						parametros.addCampoAlQuery(filtro.getAcuerdo(), Types.NUMERIC);
					}
					
					// Filtro para los valores que esten en un estado determinado
					if (Validaciones.isCollectionNotEmpty(filtro.getIdEstados())) {
						String queryEstados = " AND ID_ESTADO_VALOR in (";
						String estados = "";
						for (String idEstado: filtro.getIdEstados()) {
							estados += "?, ";
							parametros.addCampoAlQuery(idEstado, Types.VARCHAR);
						}
						queryEstados += estados.substring(0, estados.length()-2) + ")";
						condicion.append(queryEstados);
					}
					
					// Para buscar valores por clientes, ya sea uno o varios
					
					if (Validaciones.isCollectionNotEmpty(filtro.getIdClientesLegado())) {
						
						String queryClientes = " AND ID_CLIENTE_LEGADO in (";
						String clientes = "";
						for (String idCliente: filtro.getIdClientesLegado()) {
							clientes += "?, ";
							parametros.addCampoAlQuery(idCliente, Types.VARCHAR);
						}
						queryClientes += clientes.substring(0, clientes.length()-2) + ")";
						condicion.append(queryClientes);	
					}
					

					if (!Validaciones.isNullOrEmpty(filtro.getFechaDesde()) && !Validaciones.isNullOrEmpty(filtro.getFechaHasta())) {
						condicion.append(" AND TO_DATE(FECHA_ALTA, ?) BETWEEN TO_DATE(?,? )  AND TO_DATE(?,? ) ");
						parametros.addCampoAlQuery(Utilidad.ORACLE_DATE_TIME_FULL_FORMAT, Types.VARCHAR);

						parametros.addCampoAlQuery(filtro.getFechaDesde() + " 00:00:00", Types.VARCHAR);
						parametros.addCampoAlQuery(Utilidad.ORACLE_DATE_TIME_FULL_FORMAT, Types.VARCHAR);
						parametros.addCampoAlQuery(filtro.getFechaHasta() + " 23:59:59", Types.VARCHAR);
						parametros.addCampoAlQuery(Utilidad.ORACLE_DATE_TIME_FULL_FORMAT, Types.VARCHAR);
					} else if (!Validaciones.isNullOrEmpty(filtro.getFechaDesde()) && Validaciones.isNullOrEmpty(filtro.getFechaHasta())) {
						condicion.append(" AND TO_DATE(FECHA_ALTA, ?) >= TO_DATE(?,? ) ");	
						parametros.addCampoAlQuery(Utilidad.ORACLE_DATE_TIME_FULL_FORMAT, Types.VARCHAR);
						parametros.addCampoAlQuery(filtro.getFechaDesde() + " 00:00:00", Types.VARCHAR);
						parametros.addCampoAlQuery(Utilidad.ORACLE_DATE_TIME_FULL_FORMAT, Types.VARCHAR);
					} else if (Validaciones.isNullOrEmpty(filtro.getFechaDesde()) && !Validaciones.isNullOrEmpty(filtro.getFechaHasta())) {
						condicion.append(" AND TO_DATE(FECHA_ALTA, ?) <= TO_DATE(?,? ) ");	
						parametros.addCampoAlQuery(Utilidad.ORACLE_DATE_TIME_FULL_FORMAT, Types.VARCHAR);
						parametros.addCampoAlQuery(filtro.getFechaHasta() + " 23:59:59", Types.VARCHAR);
						parametros.addCampoAlQuery(Utilidad.ORACLE_DATE_TIME_FULL_FORMAT, Types.VARCHAR);
					}

					if (!Validaciones.isNullOrEmpty(filtro.getIdEmpresa())){
						condicion.append(" AND ID_EMPRESA = ? ");
						parametros.addCampoAlQuery(filtro.getIdEmpresa(), Types.VARCHAR);
					}

					//			Sprint 3 - Req. 12, Combo segmento ("Todos") unificar comportamiento	
					if (!Validaciones.isNullOrEmpty(filtro.getIdSegmento())){
						if(!filtro.getIdSegmento().equals(Constantes.TODOS_LOS_SEGMENTOS)){
							condicion.append(" AND ID_SEGMENTO = ? ");
							parametros.addCampoAlQuery(filtro.getIdSegmento(), Types.VARCHAR);
						}
					}

					if (!Validaciones.isNullOrEmpty(filtro.getIdEstado())) {
						condicion.append(" AND ID_ESTADO_VALOR = ? ");
						parametros.addCampoAlQuery(filtro.getIdEstado(), Types.VARCHAR);
					}
					//			if (!Validaciones.isNullOrEmpty(filtro.getIdClienteLegado())) {
					//				condicion.append(" AND ID_CLIENTE_LEGADO = ? ");
					//				parametros.addCampoAlQuery(filtro.getIdClienteLegado(), Types.VARCHAR);
					//			}

					if (!Validaciones.isNullOrEmpty(filtro.getImporteDesde()) && !Validaciones.isNullOrEmpty(filtro.getImporteHasta())) {
						condicion.append(" AND IMPORTE BETWEEN ? AND ? ");
						importeLocal = Utilidad.stringToBigDecimal(filtro.getImporteDesde());
						parametros.addCampoAlQuery(importeLocal, Types.DECIMAL);
						importeLocal = Utilidad.stringToBigDecimal(filtro.getImporteHasta());
						parametros.addCampoAlQuery(importeLocal, Types.DECIMAL);
					}else{
						if (!Validaciones.isNullOrEmpty(filtro.getImporteDesde())) {
							condicion.append(" AND IMPORTE >= ? ");
							importeLocal = Utilidad.stringToBigDecimal(filtro.getImporteDesde());
							parametros.addCampoAlQuery(importeLocal, Types.DECIMAL);
						}
						if(!Validaciones.isNullOrEmpty(filtro.getImporteHasta())){
							condicion.append(" AND IMPORTE <= ? ");
							importeLocal = Utilidad.stringToBigDecimal(filtro.getImporteHasta());
							parametros.addCampoAlQuery(importeLocal, Types.DECIMAL);
						}
					}		

					/* USUARIOS SESION */
					if(!Constantes.TODOS_LOS_SEGMENTOS.equals(filtro.getIdSegmento())){
						if (!Validaciones.isObjectNull(filtro.getUsuarioLogeado()) && filtro.getUsuarioLogeado().esAnalista()) {
							condicion.append(" AND (UPPER(ID_ANALISTA) = ? OR UPPER(ID_COPROPIETARIO) = ? ) ");
							parametros.addCampoAlQuery(filtro.getUsuarioLogeado().getIdUsuario().toUpperCase(), Types.VARCHAR);
							parametros.addCampoAlQuery(filtro.getUsuarioLogeado().getIdUsuario().toUpperCase(), Types.VARCHAR);
						}
					}

					/* ANALISTA FILTRO */
					if (!Validaciones.isNullOrEmpty(filtro.getAnalistaFiltro())) {
						condicion.append(" AND (UPPER(ID_ANALISTA) = ? OR UPPER(ID_COPROPIETARIO) = ? OR UPPER(ID_ANALISTA_TEAM_COMERCIAL) = ?) ");
						parametros.addCampoAlQuery(filtro.getAnalistaFiltro().toUpperCase(), Types.VARCHAR);
						parametros.addCampoAlQuery(filtro.getAnalistaFiltro().toUpperCase(), Types.VARCHAR);
						parametros.addCampoAlQuery(filtro.getAnalistaFiltro().toUpperCase(), Types.VARCHAR);
					}

					//Sprint 3 - Req. 11, Modificacion Busqueda de Valores
					if (!Validaciones.isNullOrEmpty(filtro.getNroBoletaFiltro())) {
						condicion.append(" AND NRO_BOLETA = ? ");
						parametros.addCampoAlQuery(filtro.getNroBoletaFiltro(), Types.NUMERIC);
					}

					if (!Validaciones.isNullOrEmpty(filtro.getReferenciaDelValorFiltro())) {
						condicion.append(" AND REFERENCIA_VALOR = ? ");
						parametros.addCampoAlQuery(filtro.getReferenciaDelValorFiltro(), Types.VARCHAR);
					}
					if (!Validaciones.isNullOrEmpty(filtro.getReferenciaDelValorFiltroLike())) {
						condicion.append(" AND REFERENCIA_VALOR LIKE ? ");
						parametros.addCampoAlQuery(filtro.getReferenciaDelValorFiltroLike(), Types.VARCHAR);
					}
					if (!Validaciones.isNullOrEmpty(filtro.getIdValor())) {
						condicion.append(" AND ID_VALOR = ? ");
						parametros.addCampoAlQuery(filtro.getIdValor(), Types.NUMERIC);
					}
					if (!Validaciones.isNullOrEmpty(filtro.getIdBanco())) {
						condicion.append(" AND ID_BANCO_ORIGEN = ? ");
						parametros.addCampoAlQuery(filtro.getIdBanco(), Types.VARCHAR);
					}  else if (
						!Validaciones.isNullEmptyOrDash(filtro.getIdBancos())
					) {
						StringBuffer query = new StringBuffer();
						if (!Validaciones.isObjectNull(condicion)) {
							query.append(" AND ID_BANCO_ORIGEN in (");
						} else {
							query.append(" ID_BANCO_ORIGEN in (");
						}
						StringBuffer datos = new StringBuffer();
						String vectorIdBancos[] = filtro.getIdBancos().split("-");
						for (String idBanco: vectorIdBancos) {
							datos.append("?, ");
							parametros.addCampoAlQuery(idBanco, Types.VARCHAR);
						}
						query.append(datos.toString().substring(0, datos.length()-2) + ")");
						condicion.append(query.toString());
					}

					//			if (!Validaciones.isNullOrEmpty(filtro.getIdCobroShivaFiltro())) {
					//				condicion.append(" AND COBRO_FORMATEADO LIKE ? ");
					//				parametros.addCampoAlQuery("%"+filtro.getIdCobroShivaFiltro()+"%", Types.VARCHAR);
					//			}		

					if (Validaciones.isCollectionNotEmpty(filtro.getIdValorDeBusqueraPorFiltroCobro())) {
						String query = "";
						if (!Validaciones.isObjectNull(condicion)) {
							query = " AND ID_VALOR in (";
						} else {
							query = " ID_VALOR in (";
						}

						String datos = "";
						for (Long idValorDeBusquedaPorFiltroCobro: filtro.getIdValorDeBusqueraPorFiltroCobro()) {
							datos += "?, ";
							parametros.addCampoAlQuery(idValorDeBusquedaPorFiltroCobro, Types.NUMERIC);
						}
						query += datos.substring(0, datos.length()-2) + ")";
						condicion.append(query);	
					}

					if(Validaciones.isCollectionNotEmpty(filtro.getListaTipoValor())) {
						String query = "";
						if(!Validaciones.isObjectNull(condicion)){
							query = " AND ID_TIPO_VALOR in (";
						}else{
							query = " ID_TIPO_VALOR in (";
						}

						String datos = "";
						for (String id: filtro.getListaTipoValor()) {
							datos += "?, ";
							parametros.addCampoAlQuery(id, Types.VARCHAR);
						}
						query += datos.substring(0, datos.length()-2) + ")";
						condicion.append(query);	
					}
					if (!Validaciones.isNullOrEmpty(filtro.getFechaVencimiento())) {
						condicion.append(" AND FECHA_VENCIMIENTO = TO_DATE(?, '"+Utilidad.ORACLE_DATE_TIME_FULL_FORMAT+"') ");
						parametros.addCampoAlQuery(filtro.getFechaVencimiento() + " 00:00:00", Types.VARCHAR);
					}
					
					if (!Validaciones.isNullOrEmpty(filtro.getSqlOrder())) {
						condicion.append(filtro.getSqlOrder());
					}
				}
			}
		}
	}
		//Solo para cobroOnline - Creditos /*Por ahora esta comentado pues no es necesario*/ 
		//if (filtro.isCobroOnlineCreditosShiva()) {
		//	condicion.append(" order by ID_VALOR asc");
		//}

		parametros.setSql(consulta.append(condicion).toString());
		return parametros;
	}	
	
	
	@Override
	public List<VistaSoporteResultadoBusquedaTalonarios> buscarTalonarios(
			TalonarioFiltro filtro) throws PersistenciaExcepcion {
		List<Map<String, Object>> listaResultadoQuery;

		try {
			QueryParametrosUtil qp = obtenerSQLBuscarTalonariosParametros(filtro);
			listaResultadoQuery = buscarUsandoSQL(qp);
			List<VistaSoporteResultadoBusquedaTalonarios> resultado = new ArrayList<>();
			for (Map<String, Object> archivo : listaResultadoQuery) {
				
				VistaSoporteResultadoBusquedaTalonarios reg = new VistaSoporteResultadoBusquedaTalonarios();

				if (!Validaciones.isObjectNull(archivo.get("ID_TALONARIO"))) {
					reg.setIdTalonario(((BigDecimal)(archivo.get("ID_TALONARIO"))).longValue());
				}
				if (!Validaciones.isObjectNull(archivo.get("ID_EMPRESA"))) {
					reg.setIdEmpresa(archivo.get("ID_EMPRESA").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("ID_SEGMENTO"))) {
				reg.setIdSegmento(archivo.get("ID_SEGMENTO").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("EMPRESA"))) {
					reg.setEmpresa(archivo.get("EMPRESA").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("SEGMENTO"))) {
				reg.setSegmento(archivo.get("SEGMENTO").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("ID_ESTADO"))) {
					reg.setIdEstado(archivo.get("ID_ESTADO").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("ESTADO"))) {
					reg.setEstado(archivo.get("ESTADO").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("NUMERO_SERIE"))) {
					reg.setNumeroSerie(((BigDecimal)(archivo.get("NUMERO_SERIE"))).longValue());
				}
				if (!Validaciones.isObjectNull(archivo.get("RANGO_DESDE"))) {
					reg.setRangoDesde(((BigDecimal)(archivo.get("RANGO_DESDE"))).longValue());
				}
				if (!Validaciones.isObjectNull(archivo.get("RANGO_HASTA"))) {
					reg.setRangoHasta(((BigDecimal)(archivo.get("RANGO_HASTA"))).longValue());
				}
				if (!Validaciones.isObjectNull(archivo.get("FECHA_ALTA"))) {
					reg.setFechaAlta((Date)archivo.get("FECHA_ALTA"));
				}
				if (!Validaciones.isObjectNull(archivo.get("USUARIO_ALTA"))) {
					reg.setUsuarioAlta(archivo.get("USUARIO_ALTA").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("FECHA_ASIGNACION"))) {
					reg.setFechaAsignacion(archivo.get("FECHA_ASIGNACION").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("USUARIO_ASIGNACION"))) {
					reg.setUsuarioAsignacion(archivo.get("USUARIO_ASIGNACION").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("GESTOR_ASIGNADO"))) {
					reg.setGestorAsignado(archivo.get("GESTOR_ASIGNADO").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("FECHA_RENDICION"))) {
					reg.setFechaRendicion(archivo.get("FECHA_RENDICION").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("USUARIO_RENDICION"))) {
					reg.setUsuarioRendicion(archivo.get("USUARIO_RENDICION").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("FECHA_APROBACION_RENDICION"))) {
					reg.setFechaAprobacionRendicion(archivo.get("FECHA_APROBACION_RENDICION").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("USUARIO_APROBACION_RENDICION"))) {
					reg.setUsuarioAprobacionRendicion(archivo.get("USUARIO_APROBACION_RENDICION").toString());
				}
				
				resultado.add(reg);
				
		}
			return resultado;
		} catch (Exception e) {
			throw new PersistenciaExcepcion(e.getMessage(), e);
		}
	}
	
	private QueryParametrosUtil obtenerSQLBuscarTalonariosParametros(TalonarioFiltro filtro) {
		StringBuffer consulta = new StringBuffer();  
		StringBuffer condicion = new StringBuffer();
		QueryParametrosUtil parametros = new QueryParametrosUtil();
		
		consulta.append("SELECT * FROM SHV_SOP_TALONARIOS_BUSQUEDA WHERE ");
		
		/*   EMPRESA   */
		if (!Validaciones.isNullOrEmpty(filtro.getEmpresa())){
			condicion.append(" ID_EMPRESA = ? ");
			parametros.addCampoAlQuery(filtro.getEmpresa(), Types.VARCHAR);
		}
		
		/*   SEGMENTO   */
		if (!Validaciones.isNullOrEmpty(filtro.getSegmento())){
			if(!filtro.getSegmento().equals(Constantes.TODOS_LOS_SEGMENTOS_ID)){
				if(!Validaciones.isObjectNull( filtro.getEmpresa())){
					condicion.append("AND ");
				}
				condicion.append(" ID_SEGMENTO = ? ");
				parametros.addCampoAlQuery(filtro.getSegmento(), Types.VARCHAR);
			}
		}
		/*   ESTADO   */
		if (!Validaciones.isNullOrEmpty(filtro.getIdEstado())) {
			if(!Validaciones.isObjectNull( filtro.getSegmento())){
				condicion.append("AND ");
			}
			condicion.append("ID_ESTADO = ? ");
			parametros.addCampoAlQuery(filtro.getIdEstado(), Types.VARCHAR);
		}
		
		if (!Validaciones.isNullOrEmpty(filtro.getIdTalonario())) {
			if(!Validaciones.isObjectNull( filtro.getIdEstado())){
				condicion.append("AND ");
			}
			condicion.append("ID_TALONARIO = ? ");
			parametros.addCampoAlQuery(filtro.getIdTalonario(), Types.VARCHAR);
		}
		
		/*   NRO SERIE   */
		if (!Validaciones.isNullOrEmpty(filtro.getNroSerie())) {
			condicion.append("AND NUMERO_SERIE = ? ");
			parametros.addCampoAlQuery(filtro.getNroSerie(), Types.VARCHAR);
		}
		
		/*   RANGO DESDE   */
		if (!Validaciones.isNullOrEmpty(filtro.getRangoDesde())) {
			condicion.append("AND RANGO_DESDE <=  ? ");
			parametros.addCampoAlQuery(filtro.getRangoDesde(), Types.VARCHAR);
		}
		
		/*   RANGO HASTA   */
		if (!Validaciones.isNullOrEmpty(filtro.getRangoHasta())) {
			condicion.append("AND RANGO_HASTA >=  ? ");
			parametros.addCampoAlQuery(filtro.getRangoHasta(), Types.VARCHAR);
		}
		
		parametros.setSql(consulta.append(condicion).toString());
		return parametros;
	}
	
		@Override
	public List<VistaSoporteResultadoBusquedaReciboPreimpreso> buscarRecibos(VistaSoporteBusquedaReciboPreImpresoFiltro filtro) throws PersistenciaExcepcion {
		String TIPO_RECIBO = "REC";
		String TIPO_VALOR = "VAL";
		String TIPO_COMPENSACION = "COM";
		
		List<Map<String, Object>> listaResultadoQuery;

		try {
			StringBuffer consulta = new StringBuffer();  
			QueryParametrosUtil qp = new QueryParametrosUtil();
			
			consulta.append("SELECT * FROM SHV_SOP_RECIBOS_BUSQUEDA ");
			consulta.append("WHERE ");
			if ( !Validaciones.isObjectNull( filtro.getIdTalonario() ) ) {
				consulta.append("ID_TALONARIO = ? ");
				qp.addCampoAlQuery(filtro.getIdTalonario(), Types.NUMERIC);
			}
			if ( !Validaciones.isObjectNull( filtro.getIdRecibo() ) ) {
				if(!Validaciones.isObjectNull( filtro.getIdTalonario() )){
					consulta.append("AND ");
				}
				consulta.append("ID_RECIBO_PREIMPRESO = ? ");
				qp.addCampoAlQuery(filtro.getIdRecibo(), Types.NUMERIC);
			}
			if ( !Validaciones.isObjectNull( filtro.getTipo() ) ) {
				if (Validaciones.isCollectionNotEmpty(filtro.getTipo())) {
					
					String queryTipo = " AND TIPO in ( ";
					
					for (String tipo: filtro.getTipo()) {
						queryTipo += " ? , ";
						qp.addCampoAlQuery(tipo, Types.VARCHAR);
					}
					consulta.append(queryTipo.substring(0, queryTipo.length()-2) + " ) ");
				}
			}
			consulta.append(" AND (ESTADO_VALOR != ? OR ESTADO_VALOR IS NULL) ");
			consulta.append("ORDER BY ? ");
			qp.addCampoAlQuery(Estado.VAL_ANULADO.descripcion(), Types.VARCHAR);
			qp.addCampoAlQuery(2, Types.NUMERIC);
			
			qp.setSql(consulta.toString());
			listaResultadoQuery = buscarUsandoSQL(qp);
			
			List<VistaSoporteResultadoBusquedaReciboPreimpreso> resultado = new ArrayList<>();
			VistaSoporteResultadoBusquedaReciboPreimpreso reg = new VistaSoporteResultadoBusquedaReciboPreimpreso();
			String idReciboActual = "";
			int contadorRecibos = 1;

			for (Map<String, Object> archivo : listaResultadoQuery) {
				if ( Validaciones.isNullOrEmpty(idReciboActual)){
					
					idReciboActual = archivo.get("ID_RECIBO_PREIMPRESO").toString();
					
				} else if (!Validaciones.isNullEmptyOrDash(idReciboActual) && 
						   !idReciboActual.equals( archivo.get("ID_RECIBO_PREIMPRESO").toString() ) ) {
					resultado.add(reg);
					reg = new VistaSoporteResultadoBusquedaReciboPreimpreso();
					idReciboActual = archivo.get("ID_RECIBO_PREIMPRESO").toString();
				}
			
				if(TIPO_RECIBO.equals(archivo.get("TIPO").toString())){
					if(!Validaciones.isObjectNull(archivo.get("FECHA_ULTIMA_MODIFICACION"))) {
						reg.setFechaUltimaModificacion( (Date) archivo.get("FECHA_ULTIMA_MODIFICACION") );
					}
					if(!Validaciones.isObjectNull(archivo.get("USUARIO_ANULACION"))) {
						reg.setUsuarioAnulacion( archivo.get("USUARIO_ANULACION").toString() );
					}
					if(!Validaciones.isObjectNull(archivo.get("GESTOR_ASIGNADO"))) {
						reg.setGestorAsignado( archivo.get("GESTOR_ASIGNADO").toString() );
					}
					if(!Validaciones.isObjectNull(archivo.get("ID_RECIBO_PREIMPRESO"))) {
						reg.setIdReciboPreimpreso( archivo.get("ID_RECIBO_PREIMPRESO").toString() );
					}
					if(!Validaciones.isObjectNull(archivo.get("IMPORTE_RECIBO"))) {
						reg.setImporteRecibo( (BigDecimal) archivo.get("IMPORTE_RECIBO") );
					}
					if(!Validaciones.isObjectNull(archivo.get("FECHA_ANULACION"))) {
						reg.setFechaAnulacion( (Date) archivo.get("FECHA_ANULACION") );
					}
					if(!Validaciones.isObjectNull(archivo.get("FECHA_INGRESO"))) {
						reg.setFechaIngreso( (Date) archivo.get("FECHA_INGRESO") );
					}
					if(!Validaciones.isObjectNull(archivo.get("EMPRESA"))) {
						reg.setEmpresa( archivo.get("EMPRESA").toString() );
					}
					if(!Validaciones.isObjectNull(archivo.get("SEGMENTO"))) {
						reg.setSegmento( archivo.get("SEGMENTO").toString() );
					}
					if(!Validaciones.isObjectNull(archivo.get("ID_TALONARIO"))) {
						reg.setIdTalonario( archivo.get("ID_TALONARIO").toString() );
					}
					if(!Validaciones.isObjectNull(archivo.get("NUMERO_RECIBO"))) {
						reg.setNumeroRecibo( archivo.get("NUMERO_RECIBO").toString() );
					}
					if(!Validaciones.isObjectNull(archivo.get("ESTADO"))) {
						reg.setEstado( archivo.get("ESTADO").toString() );
					}
				} else if(TIPO_VALOR.equals(archivo.get("TIPO").toString())){
					ValorDto valorDto = new ValorDto();
					
					/* Analista */
					if(!Validaciones.isObjectNull(archivo.get("ID_ANALISTA"))) {
						valorDto.setIdAnalista( archivo.get("ID_ANALISTA").toString() );
					}
					
					if(!Validaciones.isObjectNull(archivo.get("TIPO_VALOR"))) {
						valorDto.setTipoValor( archivo.get("TIPO_VALOR").toString() );
					}
					
					if(!Validaciones.isObjectNull(archivo.get("ID_CLIENTE_LEGADO"))) {
						valorDto.setCodCliente( archivo.get("ID_CLIENTE_LEGADO").toString() );
					}
					if(!Validaciones.isObjectNull(archivo.get("RAZON_SOCIAL_CLIENTE_PERFIL"))) {
						valorDto.setRazonSocialClienteAgrupador( archivo.get("RAZON_SOCIAL_CLIENTE_PERFIL").toString() );
					}
					if(!Validaciones.isObjectNull(archivo.get("ID_TIPO_VALOR"))) {
						valorDto.setIdTipoValor( archivo.get("ID_TIPO_VALOR").toString() );
					}
					String numeroValorConEnter = null;
					if(!Validaciones.isObjectNull(archivo.get("NRO_VALOR"))) {
						numeroValorConEnter = archivo.get("NRO_VALOR").toString().replace("|", "<br>");
					}
					valorDto.setNumeroValor(numeroValorConEnter);
					if(!Validaciones.isObjectNull(archivo.get("FECHA_DEPOSITO"))) {
						valorDto.setFechaDeposito(Utilidad.formatDateAndTimeYearDos((Date)archivo.get("FECHA_DEPOSITO")));
					}
					if(!Validaciones.isObjectNull(archivo.get("FECHA_INGRESO_RECIBO"))) {
						valorDto.setFechaIngresoRecibo(Utilidad.formatDatePicker((Date)archivo.get("FECHA_INGRESO_RECIBO")));
					}
					if(!Validaciones.isObjectNull(archivo.get("IMPORTE"))) {
						valorDto.setImporte(Utilidad.formatCurrency((BigDecimal)archivo.get("IMPORTE"),2));
					}
					if(!Validaciones.isObjectNull(archivo.get("ACUERDO"))) {
						valorDto.setIdAcuerdo( archivo.get("ACUERDO").toString() );
					}
					if(!Validaciones.isObjectNull(archivo.get("ID_RECIBO_PREIMPRESO"))) {
						valorDto.setReciboPreImpreso( archivo.get("ID_RECIBO_PREIMPRESO").toString() );
					}
					reg.getListaValores().add(valorDto);
				}else if (TIPO_COMPENSACION.equals(archivo.get("TIPO").toString())){
					ShvTalCompensacion compensacion = new ShvTalCompensacion();
					
					if(!Validaciones.isObjectNull(archivo.get("REFERENCIA"))) {
						compensacion.setReferencia( archivo.get("REFERENCIA").toString() );
					}
					if(!Validaciones.isObjectNull(archivo.get("IMPORTE_COMPENSACION"))) {
						compensacion.setImporte((BigDecimal) archivo.get("IMPORTE_COMPENSACION"));
					}
					if(!Validaciones.isObjectNull(archivo.get("ID_COMPENSACION"))){
						compensacion.setIdCompensacion(Integer.valueOf(archivo.get("ID_COMPENSACION").toString()));
					} 
					reg.getListaCompensaciones().add(compensacion);
				}
				

				if (listaResultadoQuery.size() == contadorRecibos ) {
					resultado.add(reg);
				}
				
				contadorRecibos++;
			}
			return resultado;
		} catch (Exception e) {
			throw new PersistenciaExcepcion(e.getMessage(), e);
		}
		
	}
	

	/**
	 * @author fabio.giaquinta.ruiz
	 * @throws PersistenciaExcepcion 
	 */
	@Override
	public List<VistaSoporteRegistrosAvcSinConciliar> listarRegistrosAVCSinConciliar(VistaSoporteRegistrosAvcSinConciliarFiltro filtro) throws PersistenciaExcepcion {

		List<Map<String, Object>> listaResultadoQuery;

		try {
			QueryParametrosUtil qp = obtenerSQLConsultaValoresDisponibles(filtro);
			listaResultadoQuery = buscarUsandoSQL(qp);
	
			List<VistaSoporteRegistrosAvcSinConciliar> resultado = new ArrayList<VistaSoporteRegistrosAvcSinConciliar>();
		
			for (Map<String, Object> archivo : listaResultadoQuery) {
				
				VistaSoporteRegistrosAvcSinConciliar reg = new VistaSoporteRegistrosAvcSinConciliar();
				 
				 if (Validaciones.isObjectNull(archivo.get("ESTADO")) == false) {
					 reg.setEstado(archivo.get("ESTADO").toString());
				 }
				 if (Validaciones.isObjectNull(archivo.get("ID_REGISTRO")) == false) {
					 reg.setIdRegistro(((BigDecimal) archivo.get("ID_REGISTRO")).longValue());
				 }
				 if (Validaciones.isObjectNull(archivo.get("ACUERDO_FORMATEADO")) == false) {
					 reg.setAcuerdoFormateado(archivo.get("ACUERDO_FORMATEADO").toString());
				 }
				 if (Validaciones.isObjectNull(archivo.get("CODIGO_CLIENTE")) == false) {
					 reg.setCodigoCliente(archivo.get("CODIGO_CLIENTE").toString());
				 }
				 if (Validaciones.isObjectNull(archivo.get("NUMERO_BOLETA")) == false) {
					 reg.setNumeroBoleta(((BigDecimal) archivo.get("NUMERO_BOLETA")).longValue());
				 }
				 if (Validaciones.isObjectNull(archivo.get("NUMERO_REFERENCIA")) == false) {
					 reg.setNumeroReferencia(((BigDecimal) archivo.get("NUMERO_REFERENCIA")).longValue());
				 }
				 if (Validaciones.isObjectNull(archivo.get("TIPO_VALOR_FORMATEADO")) == false) {
					 reg.setTipoValorFormateado(archivo.get("TIPO_VALOR_FORMATEADO").toString());
				 }
				 if (Validaciones.isObjectNull(archivo.get("IMPORTE")) == false) {
					 reg.setImporte((BigDecimal) archivo.get("IMPORTE"));
				 }				
				 if (Validaciones.isObjectNull(archivo.get("CUIT_FORMATEADO")) == false) {
					 reg.setCuitFormateado(archivo.get("CUIT_FORMATEADO").toString());
				 }
				 if (Validaciones.isObjectNull(archivo.get("ESTADO_FORMATEADO")) == false) {
					 reg.setEstadoFormateado(archivo.get("ESTADO_FORMATEADO").toString());
				 }
				 if (Validaciones.isObjectNull(archivo.get("OBSERVACION")) == false) {
					 reg.setObservacion(archivo.get("OBSERVACION").toString());
				 }
				 if (Validaciones.isObjectNull(archivo.get("NOMBRE_ARCHIVO")) == false) {
					 reg.setNombreArchivo(archivo.get("NOMBRE_ARCHIVO").toString());
				 }
				 if (Validaciones.isObjectNull(archivo.get("TIPO_DTO")) == false) {
					 reg.setTipoDto(archivo.get("TIPO_DTO").toString());
				 }
				 if (Validaciones.isObjectNull(archivo.get("BANCO_ORIGEN")) == false) {
					 reg.setBancoOrigenFormateado(archivo.get("BANCO_ORIGEN").toString());
				 }
				 if (Validaciones.isObjectNull(archivo.get("FECHA_VENCIMIENTO")) == false) {
					 reg.setFechaVencimientoFormateado(Utilidad.formatDatePicker((Date) archivo.get("FECHA_VENCIMIENTO")));
				 }
				 if (Validaciones.isObjectNull(archivo.get("CODIGO_ORGANISMO")) == false) {
					 reg.setCodigoOrganismo(archivo.get("CODIGO_ORGANISMO").toString());
				 }
				 if (Validaciones.isObjectNull(archivo.get("ERROR_ALTA_INTERDEPOSITO")) == false) {
					 reg.setErrorAltaInterdeposito(archivo.get("ERROR_ALTA_INTERDEPOSITO").toString());
				 }
				 if (Validaciones.isObjectNull(archivo.get("FECHA_ERROR")) == false) {
					 reg.setFechaErrorFormateado(Utilidad.formatDateAndTimeFull((Date) archivo.get("FECHA_ERROR")));
				 }
				 if (Validaciones.isObjectNull(archivo.get("razon_social")) == false) {
					 reg.setRazonSocial(archivo.get("razon_social").toString());
				 }
				 if (Validaciones.isObjectNull(archivo.get("analista_team_comercial")) == false) {
					 reg.setAnalistaTeamComercial(archivo.get("analista_team_comercial").toString());
				 }
				 if (Validaciones.isObjectNull(archivo.get("fecha_alta")) == false) {
						reg.setFechaAltaFormateada(Utilidad.formatDatePicker((Date) archivo.get("fecha_alta")));
				}
				 if (Validaciones.isObjectNull(archivo.get("fecha_deposito")) == false) {
						reg.setFechaDepositoFormateada(Utilidad.formatDatePicker((Date) archivo.get("fecha_deposito")));
				}
				 if (Validaciones.isObjectNull(archivo.get("fecha_derivacion")) == false) {
						reg.setFechaDerivacion(Utilidad.formatDatePicker((Date) archivo.get("fecha_derivacion")));
				}
				 if (Validaciones.isObjectNull(archivo.get("analista_derivacion")) == false) {
					 reg.setAnalistaDerivacion(archivo.get("analista_derivacion").toString());
				 }
				 resultado.add(reg);
			}
			return resultado;
		} catch (Exception e) {
			throw new PersistenciaExcepcion(e.getMessage(), e);
		}

	}

	/**
	 * 
	 * @param filtro
	 * @return
	 * @throws PersistenciaExcepcion 
	 */
	private QueryParametrosUtil obtenerSQLConsultaValoresDisponibles(VistaSoporteRegistrosAvcSinConciliarFiltro filtro) throws  PersistenciaExcepcion {

		StringBuffer consulta = new StringBuffer();
		StringBuffer consulta1 = new StringBuffer();
		QueryParametrosUtil parametros = new QueryParametrosUtil();
		consulta.append("select * from shv_sop_reg_avc_sin_conciliar ");
		String FechaDerivacion = "fecha_derivacion";
		String fechaAlta = "fecha_alta";
		String nroBoleta = "NUMERO_BOLETA";
		String referenciaDelValor = "NUMERO_REFERENCIA";
		String importe = "importe";

		if (!Validaciones.isNullOrEmpty(filtro.getEstado())) {
			consulta1.append("where estado=? ");
			parametros.addCampoAlQuery(filtro.getEstado(), Types.VARCHAR);
		}
		if (!Validaciones.isNullOrEmpty(filtro.getNroBoleta())) {
			if(consulta1.length()==0){
				consulta1.append("where ");
				consulta1.append(nroBoleta);
				consulta1.append(" = ? ");
			}else{
				consulta1.append("and ");
				consulta1.append(nroBoleta);
				consulta1.append(" = ? ");
			}
			parametros.addCampoAlQuery(Utilidad.stringToBigDecimal(filtro.getNroBoleta()), Types.DECIMAL);
		}

		if (!Validaciones.isNullOrEmpty(filtro.getReferenciaDelValor())) {
			if(consulta1.length()==0){
				consulta1.append("where ");
				consulta1.append(referenciaDelValor);
				consulta1.append(" = ? ");
			}else{
				consulta1.append("and ");
				consulta1.append(referenciaDelValor);
				consulta1.append(" = ? ");
			}
			parametros.addCampoAlQuery(Utilidad.stringToBigDecimal(filtro.getReferenciaDelValor()), Types.DECIMAL);
		}
		try{
			if (!Validaciones.isNullOrEmpty(filtro.getFechaAltaDesdeFormateada())) {

				if(!Validaciones.isNullOrEmpty(filtro.getFechaAltaHastaFormateada())){
					if(consulta1.length()==0){
						consulta1.append("where ");
						consulta1.append(fechaAlta);
						consulta1.append(" between ? and ? ");
					}else{
						consulta1.append("and ");
						consulta1.append(fechaAlta);
						consulta1.append(" between ? and ? ");
					}
					parametros.addCampoAlQuery(Utilidad.parseDatePickerString(filtro.getFechaAltaDesdeFormateada()), Types.DATE);
					parametros.addCampoAlQuery(sumarUnDia(Utilidad.parseDatePickerString(filtro.getFechaAltaHastaFormateada())), Types.DATE);
				}else{
					if(consulta1.length()==0){
						consulta1.append("where ");
						consulta1.append(fechaAlta);
						consulta1.append(" >= ? ");
					}else{
						consulta1.append("and ");
						consulta1.append(fechaAlta);
						consulta1.append(" >= ? ");
					}
					parametros.addCampoAlQuery(Utilidad.parseDatePickerString(filtro.getFechaAltaDesdeFormateada()), Types.DATE);
				}
			}else{
				if(!Validaciones.isNullOrEmpty(filtro.getFechaAltaHastaFormateada())){
					if(consulta1.length()==0){
						consulta1.append("where ");
						consulta1.append(fechaAlta);
						consulta1.append(" <= ? ");
					}else{
						consulta1.append("and ");
						consulta1.append(fechaAlta);
						consulta1.append(" <= ? ");
					}
					parametros.addCampoAlQuery(sumarUnDia(Utilidad.parseDatePickerString(filtro.getFechaAltaHastaFormateada())), Types.DATE);
				}

			}

			if (!Validaciones.isNullOrEmpty(filtro.getFechaDerivacionDesdeFormateada())) {

				if (!Validaciones.isNullOrEmpty(filtro.getFechaDerivacionHastaFormateada())) {
					if(consulta1.length()==0){
						consulta1.append("where ");
						consulta1.append(FechaDerivacion);
						consulta1.append(" between ? and ? ");
					}else{
						consulta1.append("and ");
						consulta1.append(FechaDerivacion);
						consulta1.append(" between ? and ? ");
					}
					parametros.addCampoAlQuery(Utilidad.parseDatePickerString(filtro.getFechaDerivacionDesdeFormateada()), Types.DATE);
					parametros.addCampoAlQuery(sumarUnDia(Utilidad.parseDatePickerString(filtro.getFechaDerivacionHastaFormateada())), Types.DATE);
				}else{
					if(consulta1.length()==0){
						consulta1.append("where ");
						consulta1.append(FechaDerivacion);
						consulta1.append(" >= ? ");
					}else{
						consulta1.append("and ");
						consulta1.append(FechaDerivacion);
						consulta1.append(" >= ? ");
					}
					parametros.addCampoAlQuery(Utilidad.parseDatePickerString(filtro.getFechaDerivacionDesdeFormateada()), Types.DATE);
				}

			}else{
				if (!Validaciones.isNullOrEmpty(filtro.getFechaDerivacionHastaFormateada())) {
					if(consulta1.length()==0){
						consulta1.append("where ");
						consulta1.append(FechaDerivacion);
						consulta1.append(" <= ? ");
					}else{
						consulta1.append("and ");
						consulta1.append(FechaDerivacion);
						consulta1.append(" <= ? ");
					}
					parametros.addCampoAlQuery(sumarUnDia(Utilidad.parseDatePickerString(filtro.getFechaDerivacionHastaFormateada())), Types.DATE);
				}
			}
			if(!Validaciones.isNullOrEmpty(filtro.getImporteHasta()) && !Validaciones.isNullOrEmpty(filtro.getImporteDesde())) {
				
					if(consulta1.length()==0){
						consulta1.append("where ");
						consulta1.append(importe);
						consulta1.append(" between ? and ? ");
					}else{
						consulta1.append("and ");
						consulta1.append(importe);
						consulta1.append(" between ? and ? ");
					}
					BigDecimal importeLocal = Utilidad.stringToBigDecimal(filtro.getImporteDesde());
					parametros.addCampoAlQuery(importeLocal, Types.DECIMAL);
					importeLocal = Utilidad.stringToBigDecimal(filtro.getImporteHasta());
					parametros.addCampoAlQuery(importeLocal, Types.DECIMAL);
					
			}else{
				if(!Validaciones.isNullOrEmpty(filtro.getImporteDesde())) {
					if(consulta1.length()==0){
						consulta1.append("where ");
						consulta1.append(importe);
						consulta1.append(" >= ? ");
					}else{
						consulta1.append("and ");
						consulta1.append(importe);
						consulta1.append(" >= ? ");
					}

					BigDecimal importeLocal = Utilidad.stringToBigDecimal(filtro.getImporteDesde());
					parametros.addCampoAlQuery(importeLocal, Types.DECIMAL);

				}else if(!Validaciones.isNullOrEmpty(filtro.getImporteHasta())) {
					if(consulta1.length()==0){
						consulta1.append("where ");
						consulta1.append(importe);
						consulta1.append(" <= ? ");
					}else{
						consulta1.append("and ");
						consulta1.append(importe);
						consulta1.append(" <= ? ");
					}

					BigDecimal importeLocal = Utilidad.stringToBigDecimal(filtro.getImporteHasta());
					parametros.addCampoAlQuery(importeLocal, Types.DECIMAL);
				}
			}


			consulta.append(consulta1);
			consulta.append(" order by 2");

			parametros.setSql(consulta.toString());
		} catch (ParseException e) {
			throw new PersistenciaExcepcion(e.getMessage(), e);
		}
		return parametros;
	}


	private Date sumarUnDia(Date fecha) throws ParseException {
		GregorianCalendar fechaAltaHasta = new GregorianCalendar();
		fechaAltaHasta.setTime(fecha);
		fechaAltaHasta.add(GregorianCalendar.DATE, 1);
		return fechaAltaHasta.getTime();
	}	


	/**
	 * Arma la consulta para la busqueda de cobros a partir del filtro
	 * @param filtro
	 * @return
	 */
	private QueryParametrosUtil obtenerSQLConsultaCobrosOnline(VistaSoporteCobroOnlineFiltro filtro) {
	
		QueryParametrosUtil parametros = new QueryParametrosUtil();
		String query = "select " 
				 + "cobro.id_cobro, "
				 + "cobro.id_cobro_padre, "
				 + "cobro.id_empresa, "
				 + "cobro.id_segmento, "
				 + "cobro.id_operacion, "
				 + "cobro.id_motivo_cobro, "
				 + "cobro.desc_motivo_cobro, "
				 + "cobro.cliente, "
				 + "cobro.estado, "
				 + "cobro.sub_estado, "
				 + "cobro.fecha_ultimo_estado, "
				 + "cobro.analista, "
				 + "cobro.copropietario, "
				 + "cobro.id_analista_team_comercial, "
				 + "descobro.ID_DESCOBRO_PADRE, "
				 + "descobro.id_operacion as ID_OPERACION_REVERSION, "
				 + "descobro.id_descobro, "
				 + "cobro.id_operacion_masiva, "
				 + "cobro.nombre_archivo, "
				 + "cobro.nombre_archivo_salida, "
				 + "cobro.nombre_archivo_entrada, "
				 + "cobro.importe_total, "
				 + "cobro.fecha_alta, "
				 + "cobro.fecha_derivacion, "
				 + "cobro.fecha_aprob_super_cob, "
				 + "cobro.fecha_aprob_refer_cob, "
				 + "cobro.fecha_aprob_oper_espe, "
				 + "cobro.fecha_imputacion, "
				 + "SWWE.ESTADO as estado_reversion, "
				 + "descobro.origen_descobro as ORIGEN_DESCOBRO, "
				 + "cobro.tiene_duc, "
				 + "cobro.moneda_importe, "
				 + "cobro.id_contradocumento_cap, "
				 + "cobro.id_tipo_medio_pago, "
				 + "cobro.TIPO_COBRO, "
				 + "cobro.codigo_operacion_externa, "
				 + "cobro.referente_aprobador, "
				 + "cobro.referente_caja, "
				 + "cobro.fecha_referente_caja, "
				 + "cobro.referente_oper_externa, "
				 + "cobro.fecha_referente_oper_externa, "
				 + "cobro.aprobador_super_oper_espe "
				 + "from SHV_SOP_COBROS_BUSQUEDA cobro "
				 + "left join SHV_COB_DESCOBRO descobro "
				 + "ON (cobro.id_cobro = descobro.id_cobro) "
				 + "left join SHV_WF_WORKFLOW_ESTADO swwe "
				 + "ON (descobro.id_workflow = swwe.id_workflow) "
				 + "WHERE (descobro.ID_WORKFLOW = "
				//U562163 - PM00600969 - IM02170059
				 + "(SELECT SWE.ID_WORKFLOW FROM SHV_WF_WORKFLOW_ESTADO SWE, SHV_COB_DESCOBRO DES "
				 + "WHERE DES.ID_WORKFLOW=SWE.ID_WORKFLOW AND DES.ID_COBRO= COBRO.ID_COBRO AND FECHA_MODIFICACION = "
				 + "(SELECT MAX(FECHA_MODIFICACION) FROM SHV_WF_WORKFLOW_ESTADO SWE2 WHERE ID_WORKFLOW IN "
				 + "( SELECT ID_WORKFLOW FROM SHV_COB_DESCOBRO WHERE ID_COBRO = cobro.ID_COBRO )))"
				 + " OR descobro.ID_WORKFLOW IS NULL) ";
			
		
		/* Filtro Empresa */
		query += " and cobro.ID_EMPRESA = ? ";
		parametros.addCampoAlQuery(filtro.getIdEmpresa(), Types.VARCHAR); 
		
		/*Por defecto no debe traer los cobros en estado COB_NO_DISPONIBLE*/
		query += " and cobro.estado <> ? ";
		parametros.addCampoAlQuery(Estado.COB_NO_DISPONIBLE, Types.VARCHAR); 
		
		/* Filtro Segmento */
		if (!Validaciones.isNullOrEmpty(filtro.getIdSegmento())) { 
			query += " and cobro.ID_SEGMENTO = ? ";
			parametros.addCampoAlQuery(filtro.getIdSegmento(), Types.VARCHAR); 
		}
		/* Busqueda por ClienteLegado. Filtro: ClienteLegado, Agencia Negocio, Holding, Cuit*/
		query = filtroConsultaCobrosClientesLegado(query, parametros, filtro);
		
		/* Filtro Estado */
		if (!Validaciones.isNullOrEmpty(filtro.getIdEstado())) {
			if(Validaciones.isCollectionNotEmpty(filtro.getListaIdEstados())) {
				List<Estado> listaEstados = filtro.getListaIdEstados();
				String queryEstados = " and cobro.ESTADO in (";
				String estados = "";
				for (Estado e : listaEstados) {
					String idEstado = e.name();
					estados += "?, ";
					parametros.addCampoAlQuery(idEstado, Types.VARCHAR);
				}
				queryEstados += estados.substring(0, estados.length()-2) + ")";
				query += queryEstados;
			}
		
		}
		/* Filtro Motivo Cobro */
		if (!Validaciones.isNullOrEmpty(filtro.getIdMotivo())) { 
			query += " and cobro.ID_MOTIVO_COBRO = ? ";
			parametros.addCampoAlQuery(filtro.getIdMotivo(), Types.VARCHAR); 
		}
		/* Filtro Id Operacion Cobro */
		if (!Validaciones.isNullOrEmpty(filtro.getIdOpCobro())) { 
			query += " and cobro.ID_OPERACION = ? ";
			parametros.addCampoAlQuery(filtro.getIdOpCobro(), Types.VARCHAR); 
		}
		/* Filtro Id Operacion Masiva */
		if (!Validaciones.isNullOrEmpty(filtro.getIdOperacionMasiva())) { 
			query += " and cobro.ID_OPERACION_MASIVA = ? ";
			parametros.addCampoAlQuery(filtro.getIdOperacionMasiva(), Types.VARCHAR); 
		}
		/* Filtro Id Reversion Cobro */

		if (!Validaciones.isNullOrEmpty(filtro.getIdOperacionReversion())) { 
			query += " and (descobro.ID_OPERACION = ? ";
			parametros.addCampoAlQuery(filtro.getIdOperacionReversion(), Types.VARCHAR); 
			query += " or descobro.ID_DESCOBRO = ? )";
			parametros.addCampoAlQuery(filtro.getIdOperacionReversion(), Types.VARCHAR);
		}
		/* Filtro Fecha Alta Desde y Fecha Alta Hasta*/
		if (!Validaciones.isNullOrEmpty(filtro.getFechaDesde())) {
			query += "and cobro.FECHA_ALTA>=to_timestamp( ? , ? ) ";
			parametros.addCampoAlQuery(filtro.getFechaDesde() + " 00:00:00", Types.VARCHAR);
			parametros.addCampoAlQuery(Utilidad.ORACLE_DATE_TIME_FULL_FORMAT, Types.VARCHAR);
		} 
		if (!Validaciones.isNullOrEmpty(filtro.getFechaHasta())) {
			query += "and cobro.FECHA_ALTA<=to_timestamp( ? , ? ) ";
			parametros.addCampoAlQuery(filtro.getFechaHasta() + " 23:59:59", Types.VARCHAR);
			parametros.addCampoAlQuery(Utilidad.ORACLE_DATE_TIME_FULL_FORMAT, Types.VARCHAR);
		}
		/* Filtro Analista*/
		if (!Validaciones.isNullOrEmpty(filtro.getIdAnalista())) {
			query += " and (UPPER(cobro.ANALISTA) = ? or UPPER(cobro.COPROPIETARIO) = ? ) ";
			parametros.addCampoAlQuery(filtro.getIdAnalista().toUpperCase(), Types.VARCHAR);
			parametros.addCampoAlQuery(filtro.getIdAnalista().toUpperCase(), Types.VARCHAR);
		}
		/* Filtro Codigo Operación Externa*/
		if (!Validaciones.isNullOrEmpty(filtro.getCodigoOperacionExterna())) {
			query += " and (cobro.id_cobro) In (Select id_cobro FROM shv_cob_ed_cod_oper_ext where CODIGO_OPERACION_EXTERNA = ?) ";
			parametros.addCampoAlQuery(filtro.getCodigoOperacionExterna(), Types.VARCHAR);
		}
		
		
		/* Filtro Nro Documento Y Nro Ref MIC */
		query = filtroConsultaCobrosNroDocumentoYNroRefMic(query, parametros, filtro);
		
		/* Filtro Tipo Medio de Pago */
		query = filtroConsultaCobrosTipoMedioPago(query, parametros, filtro);
		
		
		query = filtroConsultaCobrosTratamientoDiferencia(query, parametros, filtro);
		
		/* Filtros Aprobadores*/
		if(!Validaciones.isNullOrEmpty(filtro.getReferenteCobranza())){
			query += "and cobro.USUARIO_APROB_REFER_COB = ? ";
			parametros.addCampoAlQuery(filtro.getReferenteCobranza(), Types.VARCHAR); 
		}
		query = filtroConsultaAprobador(query, parametros, filtro);
		
		if(!Validaciones.isNullOrEmpty(filtro.getAprobadorOperacionesEspeciales())){
			query += "and cobro.USUARIO_APROB_SUPER_OPER_ESPE = ? ";
			parametros.addCampoAlQuery(filtro.getAprobadorOperacionesEspeciales(), Types.VARCHAR); 
		}
		
		
		parametros.setSql(query);
		return parametros;
	}
	
	
	@Override
	public List<VistaSoporteCobrosOnline> listarCobrosOnline(
			VistaSoporteCobroOnlineFiltro cobroFiltro) throws PersistenciaExcepcion {
		
		List<Map<String, Object>> listaResultadoQuery;
		String nombreArchivo = Constantes.EMPTY_STRING;

		try {
			QueryParametrosUtil qp = obtenerSQLConsultaCobrosOnline(cobroFiltro);
			listaResultadoQuery = buscarUsandoSQL(qp);
			
			
			List<VistaSoporteCobrosOnline> resultado = new ArrayList<>();
		
			for (Map<String, Object> archivo : listaResultadoQuery) { // recorro la lista de resultados
				
				VistaSoporteCobrosOnline reg = new VistaSoporteCobrosOnline();
				 
				if (!Validaciones.isObjectNull(archivo.get("ID_EMPRESA"))) {
					 reg.setIdEmpresa(archivo.get("ID_EMPRESA").toString());
				 }
				if (!Validaciones.isObjectNull(archivo.get("ID_SEGMENTO"))) {
					 reg.setIdSegmento(archivo.get("ID_SEGMENTO").toString());
				 }
				if (!Validaciones.isObjectNull(archivo.get("ID_OPERACION"))) {
					 reg.setIdOperacionCobro(((BigDecimal) archivo.get("ID_OPERACION")).longValue());
				 }
				if (!Validaciones.isObjectNull(archivo.get("ID_COBRO"))) {
					reg.setIdCobro(((BigDecimal) archivo.get("ID_COBRO")).longValue());
				}
				if (!Validaciones.isObjectNull(archivo.get("ID_COBRO_PADRE"))) {
					reg.setIdCobroPadre(((BigDecimal) archivo.get("ID_COBRO_PADRE")).longValue());
				}
				if (!Validaciones.isObjectNull(archivo.get("DESC_MOTIVO_COBRO"))) {
					 reg.setDescMotivoCobro(archivo.get("DESC_MOTIVO_COBRO").toString());
				 }
				if(!Validaciones.isObjectNull(archivo.get("CLIENTE"))){
					reg.setCliente(archivo.get("CLIENTE").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("ESTADO"))) {
					 reg.setEstado(archivo.get("ESTADO").toString());
				 }
				if (!Validaciones.isObjectNull(archivo.get("SUB_ESTADO"))) {
					 reg.setSubEstado(archivo.get("SUB_ESTADO").toString());
				 }
				if (!Validaciones.isObjectNull(archivo.get("FECHA_ULTIMO_ESTADO"))) {
					 reg.setFechaUltimoEstado(archivo.get("FECHA_ULTIMO_ESTADO").toString());
				 }
				if (!Validaciones.isObjectNull(archivo.get("ANALISTA"))) {
					 reg.setAnalista(archivo.get("ANALISTA").toString());
				 }
				if (!Validaciones.isObjectNull(archivo.get("COPROPIETARIO"))) {
					 reg.setCopropietario(archivo.get("COPROPIETARIO").toString());
				 }
				if (!Validaciones.isObjectNull(archivo.get("ID_ANALISTA_TEAM_COMERCIAL"))) {
					 reg.setAnalistaTeamComercial(archivo.get("ID_ANALISTA_TEAM_COMERCIAL").toString());
				 }
				if (!Validaciones.isObjectNull(archivo.get("ID_DESCOBRO_PADRE"))) {
					 reg.setIdReversionPadre(archivo.get("ID_DESCOBRO_PADRE").toString());
				 }
				if (!Validaciones.isObjectNull(archivo.get("ID_DESCOBRO"))) {
					 reg.setIdReversion(archivo.get("ID_DESCOBRO").toString());
				 }
				if (!Validaciones.isObjectNull(archivo.get("ID_OPERACION_REVERSION"))) {
					 reg.setIdOperacionReversion(archivo.get("ID_OPERACION_REVERSION").toString());
				 }
				if (!Validaciones.isObjectNull(archivo.get("IMPORTE_TOTAL"))) {
					reg.setImporteTotal(((BigDecimal) archivo.get("IMPORTE_TOTAL"))); 
				 }
				if (!Validaciones.isObjectNull(archivo.get("FECHA_ALTA"))) {
					 reg.setFechaAlta(archivo.get("FECHA_ALTA").toString());
				 }
				if (!Validaciones.isObjectNull(archivo.get("FECHA_DERIVACION"))) {
					 reg.setFechaDerivacion(archivo.get("FECHA_DERIVACION").toString());
				 }
				if (!Validaciones.isObjectNull(archivo.get("FECHA_APROB_SUPER_COB"))) {
					 reg.setFechaAprobSuperCob(archivo.get("FECHA_APROB_SUPER_COB").toString());
				 }
				if (!Validaciones.isObjectNull(archivo.get("FECHA_APROB_REFER_COB"))) {
					 reg.setFechaAprobReferCob(archivo.get("FECHA_APROB_REFER_COB").toString());
				 }
				if (!Validaciones.isObjectNull(archivo.get("FECHA_APROB_OPER_ESPE"))) {
					reg.setFechaAprobOperEsp(archivo.get("FECHA_APROB_OPER_ESPE").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("FECHA_IMPUTACION"))) {
					 reg.setFechaImputacion(archivo.get("FECHA_IMPUTACION").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("ESTADO_REVERSION"))) {
					 reg.setEstadoReversion(archivo.get("ESTADO_REVERSION").toString());
				}
				
				if (!Validaciones.isObjectNull(archivo.get("ID_OPERACION_MASIVA"))) {
					reg.setIdOperacionMasiva(archivo.get("ID_OPERACION_MASIVA").toString());
				}
				
				if (!Validaciones.isObjectNull(archivo.get("NOMBRE_ARCHIVO"))) {
					
					nombreArchivo = archivo.get("NOMBRE_ARCHIVO").toString();
					reg.setNombreArchivo(nombreArchivo);
					
				} else if (!Validaciones.isObjectNull(archivo.get("NOMBRE_ARCHIVO_SALIDA"))) {
					
					nombreArchivo = "Salida: " + archivo.get("NOMBRE_ARCHIVO_SALIDA").toString();
					
					if (!Validaciones.isObjectNull(archivo.get("NOMBRE_ARCHIVO_ENTRADA"))) {
						nombreArchivo += "-Entrada: " + archivo.get("NOMBRE_ARCHIVO_ENTRADA");
					}
					
					reg.setNombreArchivo(nombreArchivo);
				}
				
				if (!Validaciones.isObjectNull(archivo.get("ORIGEN_DESCOBRO"))) {
					reg.setOrigenDescobro(archivo.get("ORIGEN_DESCOBRO").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("TIENE_DUC"))) {
					 reg.setTieneDuc((boolean) SiNoEnum.SI.name().equals(archivo.get("TIENE_DUC").toString()) ? true : false);
				}
				if (!Validaciones.isObjectNull(archivo.get("MONEDA_IMPORTE"))) {
					reg.setMonedaImporte(archivo.get("MONEDA_IMPORTE").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("ID_CONTRADOCUMENTO_CAP"))) {
					reg.setIdContradocumentoCap(archivo.get("ID_CONTRADOCUMENTO_CAP").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("ID_TIPO_MEDIO_PAGO"))) {
					reg.setIdTipoMedioPago(archivo.get("ID_TIPO_MEDIO_PAGO").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("ID_TIPO_MEDIO_PAGO"))) {
					reg.setIdTipoMedioPago(archivo.get("ID_TIPO_MEDIO_PAGO").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("codigo_operacion_externa"))) {
					reg.setCodigoOperacionExterna(archivo.get("codigo_operacion_externa").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("TIPO_COBRO"))) {
					reg.setTipoCobro(TipoCobroEnum.getEnumByName(archivo.get("TIPO_COBRO").toString()).getDescripcion());
				}
				if (!Validaciones.isObjectNull(archivo.get("REFERENTE_APROBADOR"))) {
					reg.setReferenteAprobadorCobro(archivo.get("REFERENTE_APROBADOR").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("REFERENTE_CAJA"))) {
					reg.setReferenteCaja(archivo.get("REFERENTE_CAJA").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("FECHA_REFERENTE_CAJA"))) {
					reg.setFechaReferenteCaja(archivo.get("FECHA_REFERENTE_CAJA").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("REFERENTE_OPER_EXTERNA"))) {
					reg.setReferenteOperExterna(archivo.get("REFERENTE_OPER_EXTERNA").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("FECHA_REFERENTE_OPER_EXTERNA"))) {
					reg.setFechaReferenteOperExterna(archivo.get("FECHA_REFERENTE_OPER_EXTERNA").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("APROBADOR_SUPER_OPER_ESPE"))) {
					reg.setAprobadorSuperOperEspe(archivo.get("APROBADOR_SUPER_OPER_ESPE").toString());
				}
				 resultado.add(reg);
			}
			return resultado;
		} catch (Exception e) {
			throw new PersistenciaExcepcion(e.getMessage(), e);
		}

	}

	/**
	 * Arma la consulta para la busqueda de operaciones masivas a partir del filtro
	 * @param filtro
	 * @return
	 */
	private QueryParametrosUtil obtenerSQLConsultaOperacionesMasivas(VistaSoporteOperacionMasivaFiltro filtro) {
	
		
		
		QueryParametrosUtil parametros = new QueryParametrosUtil();
		String query = "select " 
				 + "opMas.nombre_archivo, "
				 + "opMas.estado, "
				 + "opMas.tipo_operacion, "
				 + "opMas.fecha_modificacion, "
				 + "opMas.id_motivo, "
				 + "opMas.descripcion_motivo, "
				 + "opMas.id_analista, "
				 + "opMas.id_copropietario, "
				 + "opMas.fecha_creacion, "
				 + "opMas.fecha_derivacion, "
				 + "opMas.fecha_autorizacion, "
				 + "opMas.fecha_proceso, "
				 + "opMas.registros_ingresados, "
				 + "opMas.registros_procesados_ok, "
				 + "opMas.registros_procesados_error, "
				 + "opMas.id_operacion_masiva, "
				 + "opMas.id_segmento "
				 + "from SHV_MAS_OPER_MASIV_BUSQUEDA opMas "
				 
				 + "where opMas.ID_EMPRESA = ?";
			  	 parametros.addCampoAlQuery(filtro.getIdEmpresa(), Types.VARCHAR);
				 
				 
			  	if (!Validaciones.isNullOrEmpty(filtro.getIdSegmento())) { 
				 query += " and opMas.ID_SEGMENTO = ?";
				 parametros.addCampoAlQuery(filtro.getIdSegmento(), Types.VARCHAR);
			  	}
			  	//TODO preguntar por el campo "Operacion Masiva Procesada Parcialmente" y "Operacion Masiva Procesada
			  	
			  	if (!Validaciones.isNullOrEmpty(filtro.getidEstado())) { 
			  		if(filtro.getidEstado().equals(EstadoOperacionMasivaEnum.EN_PROCESO.name())){
			  			query += " and (opMas.ESTADO = 'MAS_PENDIENTE_PROCESAR' or opMas.ESTADO = 'MAS_PROCESO_IMPUTACION')";
			  		} else if(filtro.getidEstado().equals(EstadoOperacionMasivaEnum.ANULADA.name())){
			  			query += " and opMas.ESTADO = 'MAS_ANULADA'";
			  		} else if(filtro.getidEstado().equals(EstadoOperacionMasivaEnum.PENDIENTE_APROBACION.name())){
			  			query += " and opMas.ESTADO = 'MAS_PENDIENTE_APROBACION'";
			  		} else if(filtro.getidEstado().equals(EstadoOperacionMasivaEnum.RECHAZADO.name())){
			  			query += " and opMas.ESTADO = 'MAS_RECHAZADA'";
			  		} else if(filtro.getidEstado().equals(EstadoOperacionMasivaEnum.EN_ERROR.name())){
			  			query += " and (opMas.ESTADO = 'MAS_ERROR' OR opMas.ESTADO = 'MAS_PARCIALMENTE_IMPUTADA')";
			  		} else if(filtro.getidEstado().equals(EstadoOperacionMasivaEnum.FINALIZADA.name())){
			  			query += " and opMas.ESTADO = 'MAS_IMPUTADA'";
			  		}
			  		
			  		
//					 query += " and opMas.ESTADO = ?";
//					 parametros.addCampoAlQuery(filtro.getidEstado(), Types.VARCHAR);
				  	}
			  	
			  	if (!Validaciones.isNullOrEmpty(filtro.getidMotivo())) { 
					 query += " and opMas.ID_MOTIVO = ?";
					 parametros.addCampoAlQuery(filtro.getidMotivo(), Types.VARCHAR);
				  	}
			  	
			  	if (!Validaciones.isNullOrEmpty(filtro.getidAnalista())) { 
					 query += " and opMas.ID_ANALISTA = ?";
					 parametros.addCampoAlQuery(filtro.getidAnalista(), Types.VARCHAR);
				  	}
			  	
			  	if (!Validaciones.isNullOrEmpty(filtro.getidTipoOperacionMasiva())) { 
					 query += " and opMas.TIPO_OPERACION = ?";
					 parametros.addCampoAlQuery(filtro.getidTipoOperacionMasiva(), Types.VARCHAR);
				  	}
			  	
			  	if (!Validaciones.isNullOrEmpty(filtro.getIdOperacionMasiva())) { 
					 query += " and opMas.ID_OPERACION_MASIVA = ?";
					 parametros.addCampoAlQuery(filtro.getIdOperacionMasiva(), Types.VARCHAR);
				  	}
			  	
			  	/* Filtro Fecha Alta Desde y Fecha Alta Hasta*/
				if (!Validaciones.isNullOrEmpty(filtro.getFechaDesde())) {
					query += "and opMas.FECHA_CREACION>=to_timestamp( ? , ? ) ";
					parametros.addCampoAlQuery(filtro.getFechaDesde() + " 00:00:00", Types.VARCHAR);
					parametros.addCampoAlQuery(Utilidad.ORACLE_DATE_TIME_FULL_FORMAT, Types.VARCHAR);
				} 
				if (!Validaciones.isNullOrEmpty(filtro.getFechaHasta())) {
					query += "and opMas.FECHA_CREACION<=to_timestamp( ? , ? ) ";
					parametros.addCampoAlQuery(filtro.getFechaHasta() + " 23:59:59", Types.VARCHAR);
					parametros.addCampoAlQuery(Utilidad.ORACLE_DATE_TIME_FULL_FORMAT, Types.VARCHAR);
				}
						
			  	
		
//				/* Filtro Empresa */
//			    query += " cobro.ID_EMPRESA = ? ";
//	         	parametros.addCampoAlQuery(filtro.getIdEmpresa(), Types.VARCHAR); 
//		
		
		parametros.setSql(query);
		return parametros;
	}
	
	
	@Override
	public List<VistaSoporteOperacionesMasivas> listarOperacionesMasivas(
			VistaSoporteOperacionMasivaFiltro operacionMasivaFiltro) throws PersistenciaExcepcion {
		
		List<Map<String, Object>> listaResultadoQuery;

		try {
			QueryParametrosUtil qp = obtenerSQLConsultaOperacionesMasivas(operacionMasivaFiltro);
			listaResultadoQuery = buscarUsandoSQL(qp);
			
			
			List<VistaSoporteOperacionesMasivas> resultado = new ArrayList<>();
		
			for (Map<String, Object> archivo : listaResultadoQuery) { // recorro la lista de resultados
				
				VistaSoporteOperacionesMasivas reg = new VistaSoporteOperacionesMasivas();
				 
				if (!Validaciones.isObjectNull(archivo.get("ID_OPERACION_MASIVA"))) {
					reg.setIdOperacionMasiva(((BigDecimal) archivo.get("ID_OPERACION_MASIVA")).longValue());
				}
				
				if (!Validaciones.isObjectNull(archivo.get("ID_SEGMENTO"))) {
					 reg.setSegmento(archivo.get("ID_SEGMENTO").toString());
				}
					
				if (!Validaciones.isObjectNull(archivo.get("NOMBRE_ARCHIVO"))) {
					 reg.setArchivoOriginal(archivo.get("NOMBRE_ARCHIVO").toString());
				 }
				if (!Validaciones.isObjectNull(archivo.get("ESTADO"))) {
					 reg.setEstadoOperacionMasiva(archivo.get("ESTADO").toString());
				 }
				if (!Validaciones.isObjectNull(archivo.get("TIPO_OPERACION"))) {
					 reg.setTipoDeOperacion(archivo.get("TIPO_OPERACION").toString());
				 }
				
				
				if (!Validaciones.isObjectNull(archivo.get("FECHA_MODIFICACION"))) {
					reg.setFechaUltimaModificacion(archivo.get("FECHA_MODIFICACION").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("ID_MOTIVO"))) {
					reg.setMotivoOm(((BigDecimal) archivo.get("ID_MOTIVO")).longValue());
				}
				
				if (!Validaciones.isObjectNull(archivo.get("DESCRIPCION_MOTIVO"))) {
					reg.setDescripcionMotivo(archivo.get("DESCRIPCION_MOTIVO").toString());
				}
				
				if (!Validaciones.isObjectNull(archivo.get("ID_ANALISTA"))) {
					reg.setAnalista(archivo.get("ID_ANALISTA").toString());
				}
				
				if (!Validaciones.isObjectNull(archivo.get("ID_COPROPIETARIO"))) {
					reg.setCopropietario(archivo.get("ID_COPROPIETARIO").toString());
				}
				
				if (!Validaciones.isObjectNull(archivo.get("FECHA_CREACION"))) {
					reg.setFechaAlta(archivo.get("FECHA_CREACION").toString());
				}
				
				if (!Validaciones.isObjectNull(archivo.get("FECHA_DERIVACION"))) {
					reg.setFechaDerivacion(archivo.get("FECHA_DERIVACION").toString());
				}
				
				if (!Validaciones.isObjectNull(archivo.get("FECHA_AUTORIZACION"))) {
					reg.setFechaAutorizacionReferente(archivo.get("FECHA_AUTORIZACION").toString());
				}
				
				if (!Validaciones.isObjectNull(archivo.get("FECHA_PROCESO"))) {
					reg.setFechaDeProcesamiento(archivo.get("FECHA_PROCESO").toString());
				}
				
				if (!Validaciones.isObjectNull(archivo.get("REGISTROS_INGRESADOS"))) {
					reg.setRegistrosIngresados(archivo.get("REGISTROS_INGRESADOS").toString());
				}

				if (!Validaciones.isObjectNull(archivo.get("REGISTROS_PROCESADOS_OK"))){
					reg.setRegistrosProcesadosCorrectamente(archivo.get("REGISTROS_PROCESADOS_OK").toString());
				}

				if (!Validaciones.isObjectNull(archivo.get("REGISTROS_PROCESADOS_ERROR"))){
					reg.setRegistrosProcesadosConError(archivo.get("REGISTROS_PROCESADOS_ERROR").toString());
				}
				
				 resultado.add(reg);
			}
			return resultado;
		} catch (Exception e) {
			throw new PersistenciaExcepcion(e.getMessage(), e);
		}

	}

	/**@author u579607
	 * Arma la consulta para la busqueda de historial de un cobro
	 * @param cobroDto
	 * @return
	 */
	private QueryParametrosUtil obtenerSQLConsultaCobroHistorial(VistaSoporteBusquedaCobroHistorialFiltro filtro) {
	
		
		StringBuffer consulta = new StringBuffer();
		QueryParametrosUtil parametros = new QueryParametrosUtil();
		consulta.append("SELECT  * FROM SHV_SOP_COBROS_HISTORIAL where ID_COBRO_PADRE = ?");
			//	+ "START WITH ID_COBRO= ?");

		parametros.addCampoAlQuery(filtro.getIdCobroPadre(), Types.VARCHAR); 
		
			//  consulta.append(" CONNECT BY PRIOR ID_COBRO_PADRE = ID_COBRO");
		
		parametros.setSql(consulta.toString());
		return parametros;
	}
	
	@Override
	public List<VistaSoporteResultadoBusquedaCobroHistorial> obtenerHistorialCobro(
			VistaSoporteBusquedaCobroHistorialFiltro filtro) throws PersistenciaExcepcion {
		
		List<Map<String, Object>> listaResultadoQuery;

		try {
			QueryParametrosUtil qp = obtenerSQLConsultaCobroHistorial(filtro);
			listaResultadoQuery = buscarUsandoSQL(qp);
	
			List<VistaSoporteResultadoBusquedaCobroHistorial> resultado = new ArrayList<>();
		
			for (Map<String, Object> archivoQuery : listaResultadoQuery) { // recorro la lista de resultados
				
				VistaSoporteResultadoBusquedaCobroHistorial reg = new VistaSoporteResultadoBusquedaCobroHistorial();
				
					 reg.setIdOperacion(((BigDecimal) archivoQuery.get("id_operacion")).longValue());
					 reg.setIdOperacionFormateado(archivoQuery.get("id_operacion").toString());
					 if (!Validaciones.isObjectNull(archivoQuery.get("estado"))) {
					 reg.setEstadoModificacion(archivoQuery.get("estado").toString());
					 }
					 if (!Validaciones.isObjectNull(archivoQuery.get("subestado"))) {
					 reg.setMarcaModificacion(archivoQuery.get("subestado").toString());
					 }
					 if (!Validaciones.isObjectNull(archivoQuery.get("usuario_modificacion"))) {
					 reg.setIdUsuarioModificacion(archivoQuery.get("usuario_modificacion").toString());
					 }
					 if (!Validaciones.isObjectNull(archivoQuery.get("fecha_modificacion"))) {
					 reg.setFechaModificacion(archivoQuery.get("fecha_modificacion").toString());
					 }
					 if (!Validaciones.isObjectNull(archivoQuery.get("observacion"))) {
					 reg.setObservaciones(archivoQuery.get("observacion").toString());
					 }
					 if (!Validaciones.isObjectNull(archivoQuery.get("mensaje_estado"))) {
					 reg.setMensajeError(archivoQuery.get("mensaje_estado").toString());
					 }
					 if (!Validaciones.isObjectNull(archivoQuery.get("numero_transaccion"))) {
					 reg.setNumeroTransaccion(archivoQuery.get("numero_transaccion").toString());
					 }
					 if (!Validaciones.isObjectNull(archivoQuery.get("numero_transaccion"))) {
						 reg.setNumeroTransaccion(archivoQuery.get("numero_transaccion").toString());
					 }
					 if (!Validaciones.isObjectNull(archivoQuery.get("numero_transaccion_ficticio"))) {
						 reg.setNumeroTransaccionFicticio(archivoQuery.get("numero_transaccion_ficticio").toString());
					 }
					 if (	!Validaciones.isObjectNull(archivoQuery.get("tipo_comprobante")) 
						 && !Validaciones.isObjectNull(archivoQuery.get("numero_documento"))) {
						 //Parseo del tipo de comprobante y numero de documento
						 TipoComprobanteEnum tipoComprobante = TipoComprobanteEnum.getEnumByName(archivoQuery.get("tipo_comprobante").toString());
						 String numeroDocumento = "";
						 if(!"--".equals(archivoQuery.get("numero_documento").toString())){
							 if(archivoQuery.get("numero_documento").toString().startsWith("-")){
								 numeroDocumento = " " + archivoQuery.get("numero_documento").toString().substring(1);
							 } else {
								 numeroDocumento = " " + archivoQuery.get("numero_documento").toString();
							 }						 
						 }
						 
						 reg.setNumeroDocumentoDebito((!Validaciones.isObjectNull(tipoComprobante)? tipoComprobante.getDescripcion() : archivoQuery.get("tipo_comprobante").toString()) +numeroDocumento);
					 }
					 if (!Validaciones.isObjectNull(archivoQuery.get("importe_cobrar"))) {
					 reg.setImporte(archivoQuery.get("importe_cobrar").toString());
					 }
					 if (!Validaciones.isObjectNull(archivoQuery.get("fecha_valor"))) {
					 reg.setFechaCobro(archivoQuery.get("fecha_valor").toString());
					 }
					 if (!Validaciones.isObjectNull(archivoQuery.get("referencia"))) {
					 reg.setRefMedioPago(archivoQuery.get("referencia").toString());
					 }
					 if (!Validaciones.isObjectNull(archivoQuery.get("acuerdo_traslado_cargo"))) {
					 reg.setAcuerdoTrasladoCargo(archivoQuery.get("acuerdo_traslado_cargo").toString());
					 }
					 if (!Validaciones.isObjectNull(archivoQuery.get("observacion_sist_soc"))) {
						 reg.setObservacionSistSoc(archivoQuery.get("observacion_sist_soc").toString());
					 }

				 resultado.add(reg);
			}
			return resultado;
		} catch (Exception e) {
			throw new PersistenciaExcepcion(e.getMessage(), e);
		}
	}

	/**
	 * 
	 * @param cobroTransaccionFiltro
	 * @return
	 */
	private QueryParametrosUtil obtenerSQLConsultaCobroTransaccion(VistaSoporteCobroTransaccionFiltro filtro) {
	
		StringBuffer consulta = new StringBuffer();
		QueryParametrosUtil parametros = new QueryParametrosUtil();

		consulta.append("SELECT * FROM SHV_SOP_COBROS_GRILLA_TRANSAC ");
		
		if (!Validaciones.isObjectNull(filtro.getIdCobro())) { 
			if (!Validaciones.isObjectNull(filtro.getSistema()) && !Validaciones.isObjectNull(filtro.getSociedad())){ 
				consulta.append(" where NUMERO_TRANSACCION_FORMATEADO in ( SELECT NUMERO_TRANSACCION_FORMATEADO FROM SHV_SOP_COBROS_GRILLA_TRANSAC ");
				
				consulta.append(" where ID_COBRO = ? ");
				parametros.addCampoAlQuery(filtro.getIdCobro(), Types.NUMERIC);
				
				consulta.append(" and SISTEMA = ? ");
				parametros.addCampoAlQuery(filtro.getSistema(), Types.VARCHAR);
			
				consulta.append(" and ID_SOCIEDAD = ? )");
				parametros.addCampoAlQuery(filtro.getSociedad(), Types.VARCHAR);
			} else {
				consulta.append(" where ID_COBRO = ? ");
				parametros.addCampoAlQuery(filtro.getIdCobro(), Types.NUMERIC); 
			}
		} else {
			if (!Validaciones.isObjectNull(filtro.getIdOperacion())) { 
				consulta.append(" where ID_OPERACION = ? ");
				parametros.addCampoAlQuery(filtro.getIdOperacion(), Types.NUMERIC);
			}
		}
		
		consulta.append(" ORDER BY NUMERO_TRANSACCION_FORMATEADO ASC, SISTEMA_ORIGEN_DOCUMENTO ASC, TIPO_COMPROBANTE ASC, SUBTIPO_DOCUMENTO ASC ");
		
		parametros.setSql(consulta.toString());
		return parametros;
	}

	@Override
	public List<VistaSoporteResultadoBusquedaCobroTransaccion> obtenerTransaccionesCobro(VistaSoporteCobroTransaccionFiltro cobroTransaccionFiltro) 
			throws PersistenciaExcepcion {

		List<Map<String, Object>> listaResultadoQuery;

		try {
			QueryParametrosUtil qp = obtenerSQLConsultaCobroTransaccion(cobroTransaccionFiltro);
			listaResultadoQuery = buscarUsandoSQL(qp);
	
			List<VistaSoporteResultadoBusquedaCobroTransaccion> resultado = new ArrayList<>();
		
			for (Map<String, Object> registroBD : listaResultadoQuery) { // recorro la lista de resultados
				
				VistaSoporteResultadoBusquedaCobroTransaccion transaccion = new VistaSoporteResultadoBusquedaCobroTransaccion();
				
				if (!Validaciones.isObjectNull(registroBD.get("NUMERO_TRANSACCION_FORMATEADO"))) {
					transaccion.setNumeroTransaccionFormateado((String)registroBD.get("NUMERO_TRANSACCION_FORMATEADO"));
				}
				if (!Validaciones.isObjectNull(registroBD.get("NRO_TRANS_FICTICIO_FORMATEADO"))) {
					transaccion.setNumeroTransaccionFicticioFormateado((String)registroBD.get("NRO_TRANS_FICTICIO_FORMATEADO"));
				}
				if (!Validaciones.isObjectNull(registroBD.get("ESTADO_TRANSACCION"))) {
					transaccion.setEstadoTransaccion(EstadoTransaccionEnum.getEnumByName((String)registroBD.get("ESTADO_TRANSACCION")));
				}
				if (!Validaciones.isObjectNull(registroBD.get("SISTEMA_ORIGEN_DOCUMENTO"))) {
					transaccion.setSistemaOrigenDocumento(SistemaEnum.getEnumByName((String)registroBD.get("SISTEMA_ORIGEN_DOCUMENTO")));
				}
				if (!Validaciones.isObjectNull(registroBD.get("TIPO_COMPROBANTE"))) {
					transaccion.setTipoComprobante(TipoComprobanteEnum.getEnumByName((String)registroBD.get("TIPO_COMPROBANTE")));
				}
				if (!Validaciones.isObjectNull(registroBD.get("SUBTIPO_DOCUMENTO"))) {
					
					String subTipoDocumento = (String) registroBD.get("SUBTIPO_DOCUMENTO");
					String descripcionSubTipoDocumento = "";

					if (TipoComprobanteEnum.REI.equals(transaccion.getTipoComprobante()) || TipoComprobanteEnum.GAN.equals(transaccion.getTipoComprobante())) {
						transaccion.setSubTipoDocumento(TipoTratamientoDiferenciaEnum.getEnumByName(subTipoDocumento).getSubTipoDocumento());
						descripcionSubTipoDocumento = TipoTratamientoDiferenciaEnum.getEnumByName(subTipoDocumento).getDescripcion();

					} else if (TipoComprobanteEnum.FAC.equals(transaccion.getTipoComprobante()) || TipoComprobanteEnum.DEB.equals(transaccion.getTipoComprobante())) {
						transaccion.setSubTipoDocumento(String.valueOf(TipoFacturaEnum.getEnumByCodigo(Integer.valueOf(subTipoDocumento)).codigo()));
						descripcionSubTipoDocumento = TipoFacturaEnum.getEnumByCodigo(Integer.valueOf(subTipoDocumento)).descripcion();
					
					} else if (TipoComprobanteEnum.DUC.equals(transaccion.getTipoComprobante())) {
						transaccion.setSubTipoDocumento(TipoDUCEnum.getEnumByCodigo(subTipoDocumento).codigo());
						descripcionSubTipoDocumento = TipoDUCEnum.getEnumByCodigo(subTipoDocumento).descripcion();
					}
					
					transaccion.setDescripcionSubTipoDocumento(descripcionSubTipoDocumento);
				}
				if (!Validaciones.isObjectNull(registroBD.get("ORIGEN_DOCUMENTO"))) {
					transaccion.setOrigenDocumento(OrigenDocumentoEnum.getEnumByName(registroBD.get("ORIGEN_DOCUMENTO").toString()));
				}
				if (!Validaciones.isObjectNull(registroBD.get("NUMERO_DOCUMENTO"))) {
					transaccion.setNumeroDocumento((String)registroBD.get("NUMERO_DOCUMENTO"));
				}
				if (!Validaciones.isObjectNull(registroBD.get("NUMERO_REFERENCIA"))) {
					transaccion.setNumeroReferencia((String)registroBD.get("NUMERO_REFERENCIA"));
				}
				if (!Validaciones.isObjectNull(registroBD.get("FECHA_VENCIMIENTO"))) {
					transaccion.setFechaVencimiento((Date)registroBD.get("FECHA_VENCIMIENTO"));
				}
				if (!Validaciones.isObjectNull(registroBD.get("FECHA_SEGUNDO_VENCIMIENTO"))) {
					transaccion.setFechaSegundoVencimiento((Date)registroBD.get("FECHA_SEGUNDO_VENCIMIENTO"));
				}
				if (!Validaciones.isObjectNull(registroBD.get("MONEDA"))) {
					transaccion.setMoneda(MonedaEnum.getEnumByName((String)registroBD.get("MONEDA")));
				}
				if (!Validaciones.isObjectNull(registroBD.get("FECHA_COBRO"))) {
					transaccion.setFechaCobro((Date)registroBD.get("FECHA_COBRO"));
				}
				if (!Validaciones.isObjectNull(registroBD.get("IMPORTE_A_COBRAR"))) {
					transaccion.setImporte((BigDecimal)registroBD.get("IMPORTE_A_COBRAR"));
				}
				if (!Validaciones.isObjectNull(registroBD.get("TIPO_DE_CAMBIO_FECHA_COBRO"))) {
					transaccion.setTipoDeCambioFechaCobro((BigDecimal)registroBD.get("TIPO_DE_CAMBIO_FECHA_COBRO"));
				}
				if (!Validaciones.isObjectNull(registroBD.get("TIPO_DE_CAMBIO_FECHA_EMISION"))) {
					transaccion.setTipoDeCambioFechaEmision((BigDecimal)registroBD.get("TIPO_DE_CAMBIO_FECHA_EMISION"));
				}
				if (!Validaciones.isObjectNull(registroBD.get("IMPORTE_APLIC_FEC_EMIS_MON_ORI"))) {
					transaccion.setImporteAplicadoFechaEmisionMonedaOrigen((BigDecimal)registroBD.get("IMPORTE_APLIC_FEC_EMIS_MON_ORI"));
				}
				if (!Validaciones.isObjectNull(registroBD.get("SISTEMA_ORIGEN_MEDIO_PAGO"))) {
					transaccion.setSistemaMedioPago(SistemaEnum.getEnumByName((String)registroBD.get("SISTEMA_ORIGEN_MEDIO_PAGO")));
				}
				if (!Validaciones.isObjectNull(registroBD.get("TIPO_MEDIO_PAGO"))) {
					transaccion.setTipoMedioPago(TipoMedioPagoEnum.getEnumByDescripcion((String)registroBD.get("TIPO_MEDIO_PAGO")));
				}
				if (!Validaciones.isObjectNull(registroBD.get("SUBTIPO_MEDIO_PAGO"))) {
					if (TipoMedioPagoEnum.REMANENTEACTUALIZADO.equals(transaccion.getTipoMedioPago()) || TipoMedioPagoEnum.REMANOACTUALIZABLE.equals(transaccion.getTipoMedioPago())) {
						transaccion.setSubTipoMedioPagoRemanente(TipoRemanenteEnum.getEnumByCodigo(new Long(registroBD.get("SUBTIPO_MEDIO_PAGO").toString())));
					} else { 
						transaccion.setSubTipoMedioPagoRetencion(TipoRetencionEnum.getEnumByIdTipoRetencion(new Long(registroBD.get("SUBTIPO_MEDIO_PAGO").toString())));
					}
				}
				if (!Validaciones.isObjectNull(registroBD.get("REFERENCIA_MEDIO_PAGO"))) {
					transaccion.setReferenciaMedioPago((String)registroBD.get("REFERENCIA_MEDIO_PAGO"));
				}
				if (!Validaciones.isObjectNull(registroBD.get("FECHA_MEDIO_PAGO"))) {
					transaccion.setFechaMedioPago((Date)registroBD.get("FECHA_MEDIO_PAGO"));
				}
				if (!Validaciones.isObjectNull(registroBD.get("MONEDA_MEDIO_PAGO"))) {
					transaccion.setMonedaMedioPago(MonedaEnum.getEnumByName((String)registroBD.get("MONEDA_MEDIO_PAGO")));
				}
				if (!Validaciones.isObjectNull(registroBD.get("IMPORTE_MEDIO_PAGO"))) {
					transaccion.setImporteMedioPago((BigDecimal)registroBD.get("IMPORTE_MEDIO_PAGO"));
				}
				if (!Validaciones.isObjectNull(registroBD.get("TIPO_CAMBIO_FECHA_COBRO_MP"))) {
					transaccion.setTipoDeCambioFechaCobroMedioPago((BigDecimal)registroBD.get("TIPO_CAMBIO_FECHA_COBRO_MP"));
				}
				if (!Validaciones.isObjectNull(registroBD.get("TIPO_CAMBIO_FECHA_EMISION_MP"))) {
					transaccion.setTipoDeCambioFechaEmisionMedioPago((BigDecimal)registroBD.get("TIPO_CAMBIO_FECHA_EMISION_MP"));
				}
				if (!Validaciones.isObjectNull(registroBD.get("IMPORTE_APL_FEC_EMI_MON_ORI_MP"))) {
					transaccion.setImporteAplicadoFechaEmisionMonedaOrigenMedioPago((BigDecimal)registroBD.get("IMPORTE_APL_FEC_EMI_MON_ORI_MP"));
				}
				if (!Validaciones.isObjectNull(registroBD.get("QUE_HACER_CON_INTERESES"))) {
					transaccion.setQueHacerConIntereses(TratamientoInteresesEnum.getEnumByName((String)registroBD.get("QUE_HACER_CON_INTERESES")));
				}
				if (!Validaciones.isObjectNull(registroBD.get("INTERESES_GENERADOS"))) {
					transaccion.setIntereses((BigDecimal)registroBD.get("INTERESES_GENERADOS"));
				} else {
					transaccion.setIntereses(BigDecimal.ZERO);
				}
				if (!Validaciones.isObjectNull(registroBD.get("CHECK_TRASLADAR_INTERESES"))) {
					transaccion.setTrasladarIntereses(SiNoEnum.getEnumByName((String)registroBD.get("CHECK_TRASLADAR_INTERESES")));
				}
				if (!Validaciones.isObjectNull(registroBD.get("GENERA_INTERESES_PARAM_USO"))) {
					transaccion.setGeneraInteresesParamUso(SiNoEnum.getEnumByName((String)registroBD.get("GENERA_INTERESES_PARAM_USO")));
				}
				if (!Validaciones.isObjectNull(registroBD.get("COBRADOR_INTERESES_TRASLADADOS"))) {
					transaccion.setCobradorInteresesTrasladados((BigDecimal)registroBD.get("COBRADOR_INTERESES_TRASLADADOS"));
				}
				if (!Validaciones.isObjectNull(registroBD.get("CHECK_COBRAR_SEGUNDO_VENCIMIEN"))) {
					transaccion.setCobrarSegundoVencimiento(SiNoEnum.getEnumByName((String)registroBD.get("CHECK_COBRAR_SEGUNDO_VENCIMIEN")));
				}
				if (!Validaciones.isObjectNull(registroBD.get("PORCENTAJE_A_BONIFICAR"))) {
					transaccion.setPorcentajeABonificar(new Integer(registroBD.get("PORCENTAJE_A_BONIFICAR").toString()));
				}
				if (!Validaciones.isObjectNull(registroBD.get("IMPORTE_A_BONIFICAR"))) {
					transaccion.setImporteABonificar((BigDecimal)registroBD.get("IMPORTE_A_BONIFICAR"));
				}
				if (!Validaciones.isObjectNull(registroBD.get("ACUERDO_TRASLADO_CARGO"))) {
					transaccion.setAcuerdoDestinoCargos(new Long(registroBD.get("ACUERDO_TRASLADO_CARGO").toString()));	
				}
				if (!Validaciones.isObjectNull(registroBD.get("ACUERDO_TRASLADO_CARGO_ORIGNAL"))) {
					transaccion.setAcuerdoDestinoCargosOriginal(new Long(registroBD.get("ACUERDO_TRASLADO_CARGO_ORIGNAL").toString()));	
				}
				if (!Validaciones.isObjectNull(registroBD.get("ESTADO_ACUERDO_TRASLADO_CARGO"))) {
					transaccion.setEstadoAcuerdoDestinoCargos(EstadoAcuerdoFacturacionEnum.getEnumByName((String)registroBD.get("ESTADO_ACUERDO_TRASLADO_CARGO")));
				}
				if (!Validaciones.isObjectNull(registroBD.get("ID_CLIENTE_ACUERDO_TRAS_CARGO"))) {
					transaccion.setIdClienteLegadoAcuerdoTrasladoCargo(new Long(registroBD.get("ID_CLIENTE_ACUERDO_TRAS_CARGO").toString()));	
				}
				if (!Validaciones.isObjectNull(registroBD.get("TIPO_MENSAJE_ESTADO_TRANSACCIO"))) {
					transaccion.setTipoMensajeTransaccion(TipoMensajeEstadoEnum.getEnumByName((String)registroBD.get("TIPO_MENSAJE_ESTADO_TRANSACCIO")));
				}
				if (!Validaciones.isObjectNull(registroBD.get("MENSAJE_ESTADO_TRANSACCION"))) {
					transaccion.setMensajeTransaccion((String)registroBD.get("MENSAJE_ESTADO_TRANSACCION"));
				}
				if (!Validaciones.isObjectNull(registroBD.get("ESTADO_TRANSACCION"))) {
					transaccion.setEstadoTransaccion(EstadoTransaccionEnum.getEnumByName((String)registroBD.get("ESTADO_TRANSACCION")));
				}
				if (!Validaciones.isObjectNull(registroBD.get("TIPO_MENSAJE_ESTADO_DEBITO"))) {
					transaccion.setTipoMensajeDebito(TipoMensajeEstadoEnum.getEnumByName((String)registroBD.get("TIPO_MENSAJE_ESTADO_DEBITO")));
				}
				if (!Validaciones.isObjectNull(registroBD.get("MENSAJE_ESTADO_DEBITO"))) {
					transaccion.setMensajeDebito((String)registroBD.get("MENSAJE_ESTADO_DEBITO"));
				}
				if (!Validaciones.isObjectNull(registroBD.get("ESTADO_DEBITO"))) {
					transaccion.setEstadoDebito(EstadoFacturaMedioPagoEnum.getEnumByName((String)registroBD.get("ESTADO_DEBITO")));
				}
				if (!Validaciones.isObjectNull(registroBD.get("TIPO_MENSAJE_ESTADO_CREDITO"))) {
					transaccion.setTipoMensajeCredito(TipoMensajeEstadoEnum.getEnumByName((String)registroBD.get("TIPO_MENSAJE_ESTADO_CREDITO")));
				}
				if (!Validaciones.isObjectNull(registroBD.get("MENSAJE_ESTADO_CREDITO"))) {
					transaccion.setMensajeCredito((String)registroBD.get("MENSAJE_ESTADO_CREDITO"));
				}
				if (!Validaciones.isObjectNull(registroBD.get("ESTADO_CREDITO"))) {
					transaccion.setEstadoCredito(EstadoFacturaMedioPagoEnum.getEnumByName((String)registroBD.get("ESTADO_CREDITO")));
				}
				if (!Validaciones.isObjectNull(registroBD.get("ID_COBRO"))) {
					transaccion.setIdCobro(new Long(registroBD.get("ID_COBRO").toString()));
				}
				if (!Validaciones.isObjectNull(registroBD.get("ID_OPERACION"))) {
					transaccion.setIdOperacion(new Long(registroBD.get("ID_OPERACION").toString()));
				}
				if (!Validaciones.isObjectNull(registroBD.get("ID_TRANSACCION"))) {
					transaccion.setIdTransaccion(new Long(registroBD.get("ID_TRANSACCION").toString()));
				}

				if (!Validaciones.isObjectNull(registroBD.get("NUMERO_TRANSACCION"))) {
					transaccion.setNumeroTransaccion(new Long(registroBD.get("NUMERO_TRANSACCION").toString()));
					transaccion.setNumeroTransaccionOriginal(new Long(registroBD.get("NUMERO_TRANSACCION").toString()));
				}

				if (!Validaciones.isObjectNull(registroBD.get("NUMERO_TRANSACCION_FICTICIO"))) {
					transaccion.setNumeroTransaccionFicticio(new Long(registroBD.get("NUMERO_TRANSACCION_FICTICIO").toString()));
				}
				
				if (!Validaciones.isObjectNull(registroBD.get("NUMERO_TRANSACCION_PADRE"))) {
					
					transaccion.setNumeroTransaccionPadre(new Long(registroBD.get("NUMERO_TRANSACCION_PADRE").toString()));
					transaccion.setNumeroTransaccion(new Long(registroBD.get("NUMERO_TRANSACCION_PADRE").toString()));

					if (!Validaciones.isObjectNull(registroBD.get("NUMERO_TRANSACCION_PADRE_FORMA"))) {
						transaccion.setNumeroTransaccionFormateado((String)registroBD.get("NUMERO_TRANSACCION_PADRE_FORMA"));
					}
				}

				if (!Validaciones.isObjectNull(registroBD.get("NRO_TRANSACCION_PADRE_FICTICIO"))) {
					
					transaccion.setNumeroTransaccionPadreFicticio(new Long(registroBD.get("NRO_TRANSACCION_PADRE_FICTICIO").toString()));
					transaccion.setNumeroTransaccionFicticio(new Long(registroBD.get("NRO_TRANSACCION_PADRE_FICTICIO").toString()));

					if (!Validaciones.isObjectNull(registroBD.get("NRO_TRANS_PADRE_FICTICIO_FORMA"))) {
						transaccion.setNumeroTransaccionFicticioFormateado((String)registroBD.get("NRO_TRANS_PADRE_FICTICIO_FORMA"));
					}
				}
				
				if (!Validaciones.isObjectNull(registroBD.get("ID_FACTURA"))) {
					transaccion.setIdFactura(new Long(registroBD.get("ID_FACTURA").toString()));
				}
				if (!Validaciones.isObjectNull(registroBD.get("ID_MEDIO_PAGO"))) {	
					transaccion.setIdMedioPago(new Long(registroBD.get("ID_MEDIO_PAGO").toString()));
				}
				if (!Validaciones.isObjectNull(registroBD.get("ID_TRATAMIENTO_DIFERENCIA"))) {	
					transaccion.setIdTratamientoDiferencia(new Long(registroBD.get("ID_TRATAMIENTO_DIFERENCIA").toString()));
				}
				if (!Validaciones.isObjectNull(registroBD.get("SEGMENTO_AGENCIA_NEGOCIO"))) {	
					transaccion.setSegmentoAgenciaNegocio(registroBD.get("SEGMENTO_AGENCIA_NEGOCIO").toString());
				}
				if (!Validaciones.isObjectNull(registroBD.get("ID_SOCIEDAD"))) {
					transaccion.setSociedad(SociedadEnum.getEnumByName(registroBD.get("ID_SOCIEDAD").toString()));
				}
				if (!Validaciones.isObjectNull(registroBD.get("GRUPO"))) {
					transaccion.setGrupo(registroBD.get("GRUPO").toString());
				}
				if (!Validaciones.isObjectNull(registroBD.get("APOCOPE"))) {
					transaccion.setApocope(registroBD.get("APOCOPE").toString());
				}
				resultado.add(transaccion);
			}
			return resultado;

		} catch (Exception e) {
			throw new PersistenciaExcepcion(e.getMessage(), e);
		}
	}


	@Override
	public List<VistaSoporteResultadoCobroCreditoDebito> obtenerDebitosPorReferencia(
			String idDebitoReferencia, String idCobro) throws PersistenciaExcepcion {

		List<Map<String, Object>> listaResultadoQuery;

		try {
			StringBuffer consulta = new StringBuffer();
			QueryParametrosUtil qp = new QueryParametrosUtil();
			consulta.append("select distinct sced.id_debito_referencia as ID_DEBITO_REFERENCIA,");
			consulta.append(" (SELECT LISTAGG(scec.id_operacion || ' / ' || scec.analista, ',') WITHIN GROUP(ORDER BY scec.id_operacion) ");
			consulta.append(" 	FROM SHV_COB_ED_COBRO SCEC LEFT JOIN SHV_COB_ED_DEBITO SCED2 ON SCEC.ID_COBRO = SCED2.ID_COBRO ");
			consulta.append("	INNER JOIN SHV_WF_WORKFLOW_ESTADO SWWE ON SCEC.ID_WORKFLOW = SWWE.ID_WORKFLOW ");
			
			//Si el idCobro es null no lo agrego a la query
			if (!Validaciones.isNullOrEmpty(idCobro)){
				consulta.append("		WHERE sced.ID_DEBITO_REFERENCIA = sced2.ID_DEBITO_REFERENCIA and scec.id_cobro not in(?) and swwe.estado in ");
				qp.addCampoAlQuery(idCobro, Types.VARCHAR);
			} else {
				consulta.append("		WHERE sced.ID_DEBITO_REFERENCIA = sced2.ID_DEBITO_REFERENCIA and swwe.estado in ");
			}
			
			consulta.append(" 			('COB_EN_EDICION', 'COB_EN_EDICION_VERIFCANDO_DEBITOS', 'COB_RECHAZADO', 'COB_PEND_REFERENTE_COBRANZA', 'COB_PEND_SUPERVISOR_OPERACIONES_ESPECIALES')) as OPERACION_ANALISTA ");
			consulta.append(" from SHV_COB_ED_DEBITO SCED ");
			// Se filtra por el RESULTADO_VALIDACION_ESTADO para los casos en que puede no estar completadop el campo ID_DEBITO_REFERNCIA
			// OTRA MANERA DE SOLUCIONAR ESTO SERIA "UPPER(sced.ID_DEBITO_REFERENCIA) NOT LIKE '%NULL%'"
			// Pero creo que esto tardaria mas
			consulta.append(" where sced.RESULTADO_VALIDACION_ESTADO NOT IN ('PENDIENTE','VALIDACION_ERROR') AND sced.ID_DEBITO_REFERENCIA = ?");

			qp.addCampoAlQuery(idDebitoReferencia, Types.VARCHAR); 
			qp.setSql(consulta.toString());
			listaResultadoQuery = buscarUsandoSQL(qp);
			
			List<VistaSoporteResultadoCobroCreditoDebito> resultado = new ArrayList<>();
			
			for (Map<String, Object> registroBD : listaResultadoQuery) { // recorro la lista de resultados
				
				VistaSoporteResultadoCobroCreditoDebito item = new VistaSoporteResultadoCobroCreditoDebito();
				
				if (!Validaciones.isObjectNull(registroBD.get("ID_DEBITO_REFERENCIA"))) {
					item.setIdReferencia((String)registroBD.get("ID_DEBITO_REFERENCIA"));
				}
				if (!Validaciones.isObjectNull(registroBD.get("OPERACION_ANALISTA"))) {
					item.setOperacionAnalista((String)registroBD.get("OPERACION_ANALISTA"));
				}
				resultado.add(item);
			}
			return resultado;

		} catch (Exception e) {
			throw new PersistenciaExcepcion(e.getMessage(), e);
		}
	}


	@Override
	public List<VistaSoporteResultadoCobroCreditoDebito> obtenerCreditosPorReferencia(
			String idCreditoReferencia, String idCobro) throws PersistenciaExcepcion {
		
		List<Map<String, Object>> listaResultadoQuery;

		try {
			StringBuffer consulta = new StringBuffer();
			QueryParametrosUtil qp = new QueryParametrosUtil();

			//consulta.append("SELECT  * FROM SHV_SOP_COBROS_CRED_REF ");
			//consulta.append(" where ID_CREDITO_REFERENCIA = ? ");
			
			consulta.append("select distinct scecc.id_credito_referencia as ID_DEBITO_REFERENCIA,");
			consulta.append(" (SELECT LISTAGG(scec.id_operacion || ' / ' || scec.analista, ',') WITHIN GROUP(ORDER BY scec.id_operacion)");
			consulta.append("  FROM SHV_COB_ED_COBRO SCEC LEFT JOIN SHV_COB_ED_CREDITO SCECC2 ON SCEC.ID_COBRO = SCECC2.ID_COBRO ");
			consulta.append("  INNER JOIN SHV_WF_WORKFLOW_ESTADO SWWE ON SCEC.ID_WORKFLOW = SWWE.ID_WORKFLOW ");
			
			if (!Validaciones.isNullOrEmpty(idCobro)){
				consulta.append("		WHERE scecc.ID_CREDITO_REFERENCIA = scecc2.ID_CREDITO_REFERENCIA and scec.id_cobro not in (?) and swwe.estado in ('COB_EN_EDICION', 'COB_EN_EDICION_VERIFCANDO_DEBITOS', 'COB_RECHAZADO', 'COB_PEND_REFERENTE_COBRANZA', 'COB_PEND_SUPERVISOR_OPERACIONES_ESPECIALES')) as OPERACION_ANALISTA");
				qp.addCampoAlQuery(idCobro, Types.VARCHAR); 
			
			} else {
				consulta.append("		WHERE scecc.ID_CREDITO_REFERENCIA = scecc2.ID_CREDITO_REFERENCIA and swwe.estado in ('COB_EN_EDICION', 'COB_EN_EDICION_VERIFCANDO_DEBITOS', 'COB_RECHAZADO', 'COB_PEND_REFERENTE_COBRANZA', 'COB_PEND_SUPERVISOR_OPERACIONES_ESPECIALES')) as OPERACION_ANALISTA");
				
			}
			consulta.append("	from SHV_COB_ED_CREDITO SCECC");
			consulta.append(" where scecc.ID_CREDITO_REFERENCIA = ? ");
			qp.addCampoAlQuery(idCreditoReferencia, Types.VARCHAR); 
			 
			qp.setSql(consulta.toString());
			listaResultadoQuery = buscarUsandoSQL(qp);
			
			List<VistaSoporteResultadoCobroCreditoDebito> resultado = new ArrayList<>();
			
			for (Map<String, Object> registroBD : listaResultadoQuery) { // recorro la lista de resultados
				
				VistaSoporteResultadoCobroCreditoDebito item = new VistaSoporteResultadoCobroCreditoDebito();
				
				if (!Validaciones.isObjectNull(registroBD.get("ID_CREDITO_REFERENCIA"))) {
					item.setIdReferencia((String)registroBD.get("ID_CREDITO_REFERENCIA"));
				}
				if (!Validaciones.isObjectNull(registroBD.get("OPERACION_ANALISTA"))) {
					item.setOperacionAnalista((String)registroBD.get("OPERACION_ANALISTA"));
				}
				resultado.add(item);
			}
			return resultado;

		} catch (Exception e) {
			throw new PersistenciaExcepcion(e.getMessage(), e);
		}
	}
	
	@Override
	public boolean obtenerMarcaCreditoEnCobrosPendienteProceso(String idCreditoReferencia) throws PersistenciaExcepcion {
		boolean salida = false;
		List<Map<String, Object>> listaResultadoQuery;

		try {
			StringBuffer consulta = new StringBuffer();
			QueryParametrosUtil qp = new QueryParametrosUtil();
	
			consulta.append("SELECT SCEC.ID_OPERACION FROM SHV_COB_ED_COBRO SCEC ");
			consulta.append("INNER JOIN SHV_WF_WORKFLOW_ESTADO SWWE ON SCEC.ID_WORKFLOW = SWWE.ID_WORKFLOW ");
			consulta.append("INNER JOIN SHV_COB_ED_CREDITO SCECC ON SCEC.ID_COBRO = SCECC.ID_COBRO ");
			consulta.append("WHERE SWWE.ESTADO IN ('COB_PENDIENTE', 'COB_PROCESO') ");
			consulta.append("AND SCECC.id_credito_referencia = ? ");
			
			qp.addCampoAlQuery(idCreditoReferencia, Types.VARCHAR);
			qp.setSql(consulta.toString());
			listaResultadoQuery = buscarUsandoSQL(qp);
			
			if (!Validaciones.isObjectNull(listaResultadoQuery)) {
				if (listaResultadoQuery.size() > 0) {
					salida = true;
				}
			} 
			return salida;
		} catch (Exception e) {
			throw new PersistenciaExcepcion(e.getMessage(), e);
		}
	}
	@Override
	public boolean obtenerMarcaDebitoEnCobrosPendienteProceso(String idDebitoReferencia) throws PersistenciaExcepcion {
		boolean salida = false;
		List<Map<String, Object>> listaResultadoQuery;

		try {
			StringBuffer consulta = new StringBuffer();
			QueryParametrosUtil qp = new QueryParametrosUtil();
	
			consulta.append("SELECT SCEC.ID_OPERACION FROM SHV_COB_ED_COBRO SCEC ");
			consulta.append("INNER JOIN SHV_WF_WORKFLOW_ESTADO SWWE ON SCEC.ID_WORKFLOW = SWWE.ID_WORKFLOW ");
			consulta.append("INNER JOIN SHV_COB_ED_DEBITO SCEDD ON SCEC.ID_COBRO = SCEDD.ID_COBRO ");
			consulta.append("WHERE SWWE.ESTADO IN ('COB_PENDIENTE', 'COB_PROCESO') ");
			consulta.append("AND SCEDD.id_debito_referencia = ? ");
			
			qp.addCampoAlQuery(idDebitoReferencia, Types.VARCHAR);
			qp.setSql(consulta.toString());
			listaResultadoQuery = buscarUsandoSQL(qp);
			
			if (!Validaciones.isObjectNull(listaResultadoQuery)) {
				if (listaResultadoQuery.size() > 0) {
					salida = true;
				}
			} 
			return salida;
		} catch (Exception e) {
			throw new PersistenciaExcepcion(e.getMessage(), e);
		}
	}
	/**
	 * Arma la consulta para la busqueda de descobros a partir del filtro
	 * @param filtro
	 * @return
	 */
	private QueryParametrosUtil obtenerSQLConsultaDescobros(VistaSoporteDescobroFiltro filtro) {
	
		QueryParametrosUtil parametros = new QueryParametrosUtil();
	
		//FECHA_MODIFICACION
		StringBuilder query = new StringBuilder("select  descobro.* , ");
			query.append("swwe.ID_WORKFLOW_ESTADO, swwe.ID_WORKFLOW, swwe.ESTADO, swwe.USUARIO_MODIFICACION, ")
					.append("NVL(")
						.append("(")
							.append("SELECT SWWM1.FECHA_CREACION ")
							.append("FROM SHV_WF_WORKFLOW_MARCA SWWM1 ")
							.append("WHERE SWWM1.ID_WORKFLOW_MARCA = ")
							.append("(SELECT MAX (SWWM.ID_WORKFLOW_MARCA) ")
								.append("FROM SHV_WF_WORKFLOW_MARCA SWWM ")
								.append("WHERE SWWM.ID_WORKFLOW_ESTADO = SWWE.ID_WORKFLOW_ESTADO")
							.append( ")")
					.append("),")
					.append("SWWE.FECHA_MODIFICACION ")
					.append(")" )
					.append(" AS FECHA_MODIFICACION")
				.append(", spmd.* , scc.ID_COBRO , scc.ID_OPERACION AS ID_OPERACION_COBRO, (SELECT SWWM1.ID_MARCA FROM SHV_WF_WORKFLOW_MARCA SWWM1 WHERE swwm1.ID_WORKFLOW_MARCA = (SELECT MAX (SWWM.ID_WORKFLOW_MARCA) FROM SHV_WF_WORKFLOW_MARCA SWWM WHERE SWWM.id_workflow_estado = SWWE.id_workflow_estado)) AS subEstado, ")
				.append(" (SELECT LISTAGG(SUBSTR(CEC.ID_CLIENTE_LEGADO || ' ' || CEC.RAZON_SOCIAL,0,30), '-') WITHIN GROUP(ORDER BY CEC.ID_CLIENTE_LEGADO) FROM SHV_COB_ED_CLIENTE CEC WHERE CEC.ID_COBRO = descobro.ID_COBRO) AS CLIENTE, ")
				.append(" (SELECT LISTAGG(team.USER_ANALISTA_COBRANZA_DATOS, '-') WITHIN GROUP(ORDER BY team.USER_ANALISTA_COBRANZA_DATOS) FROM SHV_COB_ED_CLIENTE CEC2 JOIN SHV_PARAM_TEAM_COMERCIAL TEAM ON (TEAM.NRO_DE_CLIENTE = CEC2.ID_CLIENTE_LEGADO) WHERE CEC2.ID_COBRO = descobro.ID_COBRO) AS ID_ANALISTA_TEAM_COMERCIAL ")
				.append(", ")

				.append(" CASE scerd.TIPO_TRATAMIENTO_DIFERENCIA when '")
				.append(TipoTratamientoDiferenciaEnum.APLICACION_MANUAL.name())
				.append("' THEN CASE (SELECT COUNT(*) FROM SHV_COB_ED_DEBITO WHERE ID_COBRO = scc.ID_COBRO) WHEN 0 THEN '")
				.append(TipoCobroEnum.APLICACION_MANUAL.name())
				.append("' ELSE '")
				.append(TipoCobroEnum.MIXTO.name())
				.append("' END ELSE '")
				.append(TipoCobroEnum.SHIVA.name())
				.append("' END AS TIPO_COBRO, ")
				
				.append("(SELECT LISTAGG(scecoe.CODIGO_OPERACION_EXTERNA, '-') WITHIN GROUP(ORDER BY scecoe.CODIGO_OPERACION_EXTERNA) ")
				
				.append("FROM  shv_cob_descobro_cod_oper_ext scecoe ")
				.append("WHERE scecoe.ID_DESCOBRO = descobro.ID_DESCOBRO ")
				.append(")                     AS CODIGO_OPERACION_EXTERNA ")
				
				.append(" from SHV_COB_DESCOBRO descobro")
				.append(" INNER JOIN SHV_WF_WORKFLOW_ESTADO swwe")
				.append(" ON descobro.ID_WORKFLOW = swwe.ID_WORKFLOW")
				.append(" INNER JOIN SHV_COB_COBRO scc")
				.append(" ON descobro.ID_COBRO = scc.ID_COBRO")
				.append(" LEFT JOIN SHV_PARAM_MOTIVO_DESCOBRO spmd")
				.append(" ON descobro.ID_MOTIVO_DESCOBRO = spmd.ID_MOTIVO_DESCOBRO")
				.append(" LEFT JOIN shv_cob_ed_tratamiento_dif scerd ")
				.append(" ON scc.ID_COBRO = scerd.ID_COBRO ")
//				+ " LEFT JOIN SHV_COB_TRANSACCION sct ")
//				+ " ON descobro.ID_OPERACION = sct.ID_OPERACION")
//				+ " LEFT JOIN SHV_COB_TRATAMIENTO_DIFERENCIA sctd")
//				+ " ON sct.ID_TRANSACCION = sctd.ID_TRANSACCION")
//				+ " LEFT JOIN SHV_COB_MED_PAGO scmp")
//				+ " ON sct.ID_TRANSACCION = scmp.ID_TRANSACCION")
				.append(" where descobro.ID_EMPRESA = ? ");
				parametros.addCampoAlQuery(filtro.getIdEmpresa(), Types.VARCHAR); 
				
				if (!Validaciones.isNullOrEmpty(filtro.getCodigoOperacionExterna())) {
					query.append(" and (descobro.id_descobro) In (Select ID_DESCOBRO FROM shv_cob_DESCOBRO_cod_oper_ext where CODIGO_OPERACION_EXTERNA = ?) ");
					parametros.addCampoAlQuery(filtro.getCodigoOperacionExterna(), Types.VARCHAR);
				}
				
				/* Filtro Segmento */
				if (!Validaciones.isNullOrEmpty(filtro.getIdSegmento())) { 
					query.append(" and descobro.ID_SEGMENTO = ? ");
					parametros.addCampoAlQuery(filtro.getIdSegmento(), Types.VARCHAR); 
				}
				
				/* Filtro Motivo Descobro */
				if (!Validaciones.isNullOrEmpty(filtro.getIdMotivo())) {
					query.append(" and descobro.ID_MOTIVO_DESCOBRO = ? ");
					parametros.addCampoAlQuery(filtro.getIdMotivo(), Types.VARCHAR); 
				}
				
				/* Filtro Operacion */
				if (!Validaciones.isNullOrEmpty(filtro.getIdOpCobro())) {
					query.append(" and scc.ID_OPERACION = ?");
					parametros.addCampoAlQuery(filtro.getIdOpCobro(), Types.NUMERIC); 
				}
				
				/* Filtro id Reversion */

				if (!Validaciones.isNullOrEmpty(filtro.getIdOperacionReversion())) {
					query.append(" and (descobro.ID_OPERACION = ? ");
					parametros.addCampoAlQuery(filtro.getIdOperacionReversion(), Types.NUMERIC); 
					if (Validaciones.isNullOrEmpty(filtro.getIdTarea())) {
						query.append(" or descobro.ID_DESCOBRO_PADRE = ? ");
						parametros.addCampoAlQuery(filtro.getIdOperacionReversion(), Types.NUMERIC); 
					}
					query.append(")");
				}	
				
				/* Filtro Estado */
				if(!Validaciones.isObjectNull(filtro.getListaIdEstados())) {
					
					Iterator<Estado> iterador = filtro.getListaIdEstados().iterator();
					
					query.append(" and (swwe.ESTADO = ? ");
					parametros.addCampoAlQuery(iterador.next(), Types.VARCHAR); 
					
					while(iterador.hasNext()) {
						query.append(" or swwe.ESTADO = ? ");
						parametros.addCampoAlQuery(iterador.next(), Types.VARCHAR);
					}
					
					query.append(") ");
					
//					if(Estado.DES_DESCOBRO_PROCESO.descripcion().equals(filtro.getIdEstado())) {
//						query += " or swwe.ESTADO = ? )";
//						parametros.addCampoAlQuery(Estado.DES_PENDIENTE, Types.VARCHAR);
//					}else if (Estado.DES_EN_ERROR.descripcion().equals(filtro.getIdEstado())) {
//						query += " or swwe.ESTADO = ? )";
//						parametros.addCampoAlQuery(Estado.DES_ERROR_EN_PRIMER_DESCOBRO, Types.VARCHAR);
//					}else {
//						
//					}
				}
				
				/* Filtro Fecha Alta Desde y Fecha Alta Hasta*/
				if (!Validaciones.isNullOrEmpty(filtro.getFechaDesde())) {
					query.append(" and descobro.FECHA_ALTA>=to_timestamp( ? , ? ) ");
					parametros.addCampoAlQuery(filtro.getFechaDesde() + " 00:00:00", Types.VARCHAR);
					parametros.addCampoAlQuery(Utilidad.ORACLE_DATE_TIME_FULL_FORMAT, Types.VARCHAR);
				} 
				if (!Validaciones.isNullOrEmpty(filtro.getFechaHasta())) {
					query.append(" and descobro.FECHA_ALTA<=to_timestamp( ? , ? ) ");
					parametros.addCampoAlQuery(filtro.getFechaHasta() + " 23:59:59", Types.VARCHAR);
					parametros.addCampoAlQuery(Utilidad.ORACLE_DATE_TIME_FULL_FORMAT, Types.VARCHAR);
				}
				
				/* Filtro Analista*/
				if (!Validaciones.isNullOrEmpty(filtro.getIdAnalista())) {
					query.append(" and (UPPER(descobro.ID_ANALISTA) = ? or UPPER(descobro.ID_COPROPIETARIO) = ? ) ");
					parametros.addCampoAlQuery(filtro.getIdAnalista().toUpperCase(), Types.VARCHAR);
					parametros.addCampoAlQuery(filtro.getIdAnalista().toUpperCase(), Types.VARCHAR);
				}
		
				if (!Validaciones.isNullOrEmpty(filtro.getIdTratamientoDiferencia())) {
					query.append(" and descobro.id_cobro in (");
					query.append("select scetd.id_cobro ");
					query.append("FROM SHV_COB_ED_TRATAMIENTO_DIF scetd ");
					query.append("WHERE scetd.TIPO_TRATAMIENTO_DIFERENCIA = ? ");
					parametros.addCampoAlQuery(TipoTratamientoDiferenciaEnum.getEnumByIdtipoMedioPagoAsociado(filtro.getIdTratamientoDiferencia()).name(), Types.VARCHAR);
					
					if (!Validaciones.isNullOrEmpty(filtro.getRefNroTramite())) {
						query.append(" and scetd.NUMERO_TRAMITE_REINTEGRO = ? ");
						parametros.addCampoAlQuery(filtro.getRefNroTramite(),Types.NUMERIC);
					}
					
					if (!Validaciones.isNullOrEmpty(filtro.getRefNroAcuerdoFact())) {
						query.append(" and scetd.ACUERDO_FACTURACION = ? ");
						parametros.addCampoAlQuery(filtro.getRefNroAcuerdoFact(),Types.NUMERIC);
					}
					
					if (!Validaciones.isNullOrEmpty(filtro.getIdSistemaDestino())) {
						query.append(" and scetd.SISTEMA_DESTINO = ? ");
						parametros.addCampoAlQuery(SistemaEnum.getEnumByDescripcionCorta(filtro.getIdSistemaDestino()).name(),Types.VARCHAR);
					}
					
					query.append(")");
					
				}
				
				/* Desde aca, el filtro iria a la vista de cobros busqueda */

				query.append(" and descobro.id_cobro in (");
				query.append("select cobro.id_cobro ");
				query.append("from SHV_SOP_COBROS_BUSQUEDA cobro ");
//						+ "where cobro.id_reversion IS NOT NULL ";

				/*Por defecto no debe traer los cobros en estado COB_NO_DISPONIBLE*/
				query.append(" where cobro.estado <> ? ");
				parametros.addCampoAlQuery(Estado.COB_NO_DISPONIBLE, Types.VARCHAR); 
				
				
				/* Busqueda por ClienteLegado. Filtro: ClienteLegado, Agencia Negocio, Holding, Cuit*/
				String sqlQuery = "";
				sqlQuery = filtroConsultaCobrosClientesLegado(query.toString(), parametros, filtro);
				/* Filtro Nro Documento Y Nro Ref MIC */
				
				sqlQuery = filtroConsultaCobrosNroDocumentoYNroRefMic(sqlQuery, parametros, filtro);
				
				if(!Validaciones.isNullOrEmpty(filtro.getIdTipoMedioPago())) {
					sqlQuery = filtroConsultaCobrosTipoMedioPago(sqlQuery,parametros,filtro);
//					query += ")";
				}
				sqlQuery += ")";
		
		parametros.setSql(sqlQuery);
		return parametros;
	}
	
	@Override
	public List<VistaSoporteResultadoBusquedaDescobro> listarDescobros(
			VistaSoporteDescobroFiltro descobroFiltro)
			throws PersistenciaExcepcion {
		
		List<Map<String, Object>> listaResultadoQuery;

		try {
			QueryParametrosUtil qp = obtenerSQLConsultaDescobros(descobroFiltro);
			listaResultadoQuery = buscarUsandoSQL(qp);
			
			
			List<VistaSoporteResultadoBusquedaDescobro> resultado = new ArrayList<>();
		
			for (Map<String, Object> archivo : listaResultadoQuery) { // recorro la lista de resultados
				
				VistaSoporteResultadoBusquedaDescobro reg = new VistaSoporteResultadoBusquedaDescobro();
				 
				if (!Validaciones.isObjectNull(archivo.get("ID_EMPRESA"))) {
					reg.setIdEmpresa(archivo.get("ID_EMPRESA").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("ID_SEGMENTO"))) {
					reg.setIdSegmento(archivo.get("ID_SEGMENTO").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("ID_OPERACION_COBRO"))) {
					reg.setIdOperacionCobro(((BigDecimal) archivo.get("ID_OPERACION_COBRO")).longValue());
				}
				if (!Validaciones.isObjectNull(archivo.get("ID_MOTIVO_DESCOBRO"))) {
					reg.setIdMotivo(((BigDecimal) archivo.get("ID_MOTIVO_DESCOBRO")).longValue());
				}
				if (!Validaciones.isObjectNull(archivo.get("DESCRIPCION"))) {
					reg.setDescripcionMotivo(archivo.get("DESCRIPCION").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("ID_DESCOBRO"))) {
					reg.setIdReversion(((BigDecimal) archivo.get("ID_DESCOBRO")).longValue());
				}
				if (!Validaciones.isObjectNull(archivo.get("ID_COBRO"))) {
					reg.setIdCobro(((BigDecimal) archivo.get("ID_COBRO")).longValue());
				}
				if (!Validaciones.isObjectNull(archivo.get("ID_DESCOBRO_PADRE"))) {
					reg.setIdReversionPadre(((BigDecimal) archivo.get("ID_DESCOBRO_PADRE")).longValue());
				}
				if (!Validaciones.isObjectNull(archivo.get("ID_OPERACION"))) {
					reg.setIdOperacionReversion(((BigDecimal) archivo.get("ID_OPERACION")).longValue());
				}
				if (!Validaciones.isObjectNull(archivo.get("FECHA_MODIFICACION"))) {
					reg.setFechaUltimoEstado(archivo.get("FECHA_MODIFICACION").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("ID_ANALISTA"))) {
					reg.setAnalista(archivo.get("ID_ANALISTA").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("ID_COPROPIETARIO"))) {
					reg.setCopropietario(archivo.get("ID_COPROPIETARIO").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("ID_ANALISTA_TEAM_COMERCIAL"))) {
					reg.setAnalistaTeamComercial(archivo.get("ID_ANALISTA_TEAM_COMERCIAL").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("IMPORTE_TOTAL"))) {
					reg.setImporteTotal(((BigDecimal) archivo.get("IMPORTE_TOTAL"))); 
				}
				if (!Validaciones.isObjectNull(archivo.get("FECHA_ALTA"))) {
					reg.setFechaAlta(archivo.get("FECHA_ALTA").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("FECHA_DERIVACION"))) {
					reg.setFechaDerivacion(archivo.get("FECHA_DERIVACION").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("FECHA_REVERSION"))) {
					reg.setFechaReversion(archivo.get("FECHA_REVERSION").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("ESTADO"))) {
					reg.setEstado(archivo.get("ESTADO").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("subEstado"))) {
					reg.setSubEstado(archivo.get("subEstado").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("DESCRIPCION"))) {
					reg.setDescripcionMotivo(archivo.get("DESCRIPCION").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("CLIENTE"))) {
					reg.setCliente(archivo.get("CLIENTE").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("MONEDA_OPERACION"))) {
					reg.setMonedaImporte(archivo.get("MONEDA_OPERACION").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("CODIGO_OPERACION_EXTERNA"))) {
					reg.setCodigoOperacionExterna(archivo.get("CODIGO_OPERACION_EXTERNA").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("TIPO_COBRO"))) {
					reg.setTipoCobro(TipoCobroEnum.getEnumByName(archivo.get("TIPO_COBRO").toString()).getDescripcion());
				}
				resultado.add(reg);
			}
			return resultado;
		} catch (Exception e) {
			throw new PersistenciaExcepcion(e.getMessage(), e);
		}

	}

	private String filtroConsultaCobrosTipoMedioPago(String query, QueryParametrosUtil parametros, VistaSoporteCobroOnlineFiltro filtro) {
				
		/* Filtro Tipo Medio de Pago */
		String idTipoMedioPago = "";
		if(!Validaciones.isNullOrEmpty(filtro.getIdTipoMedioPago())){
			TipoCreditoEnum enumByValor = TipoCreditoEnum.getEnumByValor(filtro.getIdTipoMedioPago());
			idTipoMedioPago = enumByValor.getIdTipoMedioPago();
		}
		if(!Validaciones.isNullOrEmpty(filtro.getIdTipoMedioPago()) && (TipoCreditoEnum.CHEQUE.getIdTipoMedioPago().equals(idTipoMedioPago) ||
				TipoCreditoEnum.CHEQUEDIF.getIdTipoMedioPago().equals(idTipoMedioPago) ||
				TipoCreditoEnum.EFECTIVO.getIdTipoMedioPago().equals(idTipoMedioPago) ||
				TipoCreditoEnum.TRANSFERENCIA.getIdTipoMedioPago().equals(idTipoMedioPago) ||
				TipoCreditoEnum.INTERDEPOSITO.getIdTipoMedioPago().equals(idTipoMedioPago))){
			if(Validaciones.isNullOrEmpty(filtro.getRefMP())){
				query += " and cobro.ID_COBRO in (select ID_COBRO from SHV_COB_ED_CREDITO "
						+ "where ID_TIPO_MEDIO_PAGO = ? ) ";
				parametros.addCampoAlQuery(idTipoMedioPago, Types.VARCHAR);
			}else{
				query += " and cobro.ID_COBRO in (select ID_COBRO from SHV_COB_ED_CREDITO "
						+ "where ID_TIPO_MEDIO_PAGO = ? and REFERENCIA_VALOR = ? ) ";
				parametros.addCampoAlQuery(idTipoMedioPago, Types.VARCHAR);
				parametros.addCampoAlQuery(filtro.getRefMP(), Types.VARCHAR);
			}
		}else if(TipoCreditoEnum.NOTACREDITO.getIdTipoMedioPago().equals(idTipoMedioPago) ||
				TipoCreditoEnum.PAGOACUENTA.getIdTipoMedioPago().equals(idTipoMedioPago)){
			if(Validaciones.isNullOrEmpty(filtro.getRefMP())){
				query += " and cobro.ID_COBRO in (select ID_COBRO from SHV_COB_ED_CREDITO "
						+ "where ID_TIPO_MEDIO_PAGO = ? ) ";
				parametros.addCampoAlQuery(idTipoMedioPago, Types.VARCHAR);
			}else if((TipoCreditoEnum.PAGOACUENTA.getIdTipoMedioPago().equals(idTipoMedioPago) || 
					TipoCreditoEnum.NOTACREDITO.getIdTipoMedioPago().equals(idTipoMedioPago)) && 
					!Validaciones.isNullOrEmpty(filtro.getRefMP())){
				query += " and cobro.ID_COBRO in (select ID_COBRO from SHV_COB_ED_CREDITO "
						+ "where ID_TIPO_MEDIO_PAGO = ? and (NRO_REFERENCIA_MIC = ? "
						+ " or CLASE_COMPROBANTE || '-'|| SUCURSAL_COMPROBANTE || '-' || NUMERO_COMPROBANTE = ? " 
						+ " or (SUCURSAL_COMPROBANTE || '-' || NUMERO_COMPROBANTE = ? "
						+ "and (CLASE_COMPROBANTE <> 'A'"
						+ " or CLASE_COMPROBANTE <> 'B'"
						+ " or CLASE_COMPROBANTE <> 'X' ))) "
						+ ")";
				parametros.addCampoAlQuery(idTipoMedioPago, Types.VARCHAR);
				if(Validaciones.isNumeric(filtro.getRefMP())){
					Long nroRefMic = Long.valueOf(filtro.getRefMP());
					parametros.addCampoAlQuery(nroRefMic, Types.NUMERIC);
				}else{
					parametros.addCampoAlQuery(null, Types.NUMERIC);
				}
				parametros.addCampoAlQuery(filtro.getRefMP(), Types.VARCHAR);
				parametros.addCampoAlQuery(filtro.getRefMP(), Types.VARCHAR);
			}
		}else if(TipoCreditoEnum.RETENCION.getValor().equals(filtro.getIdTipoMedioPago())){
			if(Validaciones.isNullOrEmpty(filtro.getRefMP())){
				query += " and cobro.ID_COBRO in (select ID_COBRO from SHV_COB_ED_CREDITO "
						+ "where ID_TIPO_MEDIO_PAGO in (?, ?, ?, ?, ?, ?, ?, ? )) ";
				parametros.addCampoAlQuery(TipoRetencionEnum.IMPUESTO_MUNICIPAL_SEGURIDAD_E_HIGIENGE.getIdTipoMedioPago(), Types.VARCHAR);
				parametros.addCampoAlQuery(TipoRetencionEnum.IMPUESTO_AL_SELLO.getIdTipoMedioPago(), Types.VARCHAR);
				parametros.addCampoAlQuery(TipoRetencionEnum.RETENCION_SEGURIDAD_SOCIAL.getIdTipoMedioPago(), Types.VARCHAR);
				parametros.addCampoAlQuery(TipoRetencionEnum.RETENCION_GANANCIA.getIdTipoMedioPago(), Types.VARCHAR);
				parametros.addCampoAlQuery(TipoRetencionEnum.RETENCION_IVA.getIdTipoMedioPago(), Types.VARCHAR);
				parametros.addCampoAlQuery(TipoRetencionEnum.RETENCION_IIBB.getIdTipoMedioPago(), Types.VARCHAR);
				parametros.addCampoAlQuery(TipoRetencionEnum.RETENCION_IVA_RG3349.getIdTipoMedioPago(), Types.VARCHAR);
				parametros.addCampoAlQuery(TipoRetencionEnum.IMPUESTO_TASAS_MUNICIPALES.getIdTipoMedioPago(), Types.VARCHAR);
			}else{
				query += " and cobro.ID_COBRO in (select ID_COBRO from SHV_COB_ED_CREDITO "
						+ "where ID_TIPO_MEDIO_PAGO in (?, ?, ?, ?, ?, ?, ?, ? ) and REFERENCIA_VALOR = ? ) ";
				parametros.addCampoAlQuery(TipoRetencionEnum.IMPUESTO_MUNICIPAL_SEGURIDAD_E_HIGIENGE.getIdTipoMedioPago(), Types.VARCHAR);
				parametros.addCampoAlQuery(TipoRetencionEnum.IMPUESTO_AL_SELLO.getIdTipoMedioPago(), Types.VARCHAR);
				parametros.addCampoAlQuery(TipoRetencionEnum.RETENCION_SEGURIDAD_SOCIAL.getIdTipoMedioPago(), Types.VARCHAR);
				parametros.addCampoAlQuery(TipoRetencionEnum.RETENCION_GANANCIA.getIdTipoMedioPago(), Types.VARCHAR);
				parametros.addCampoAlQuery(TipoRetencionEnum.RETENCION_IVA.getIdTipoMedioPago(), Types.VARCHAR);
				parametros.addCampoAlQuery(TipoRetencionEnum.RETENCION_IIBB.getIdTipoMedioPago(), Types.VARCHAR);
				parametros.addCampoAlQuery(TipoRetencionEnum.RETENCION_IVA_RG3349.getIdTipoMedioPago(), Types.VARCHAR);
				parametros.addCampoAlQuery(TipoRetencionEnum.IMPUESTO_TASAS_MUNICIPALES.getIdTipoMedioPago(), Types.VARCHAR);
				parametros.addCampoAlQuery(filtro.getRefMP(), Types.VARCHAR);
			}
		}else if(TipoCreditoEnum.REMANENTE.getValor().equals(filtro.getIdTipoMedioPago())){	
			if(Validaciones.isNullOrEmpty(filtro.getRefMP())){
				query += " and cobro.ID_COBRO in (select ID_COBRO from SHV_COB_ED_CREDITO "
						+ "where ID_TIPO_MEDIO_PAGO in (?, ?)"
						+  ")";
				parametros.addCampoAlQuery(TipoRemanenteEnum.idMedioPago_Remanente_Actualizable(), Types.NUMERIC);
				parametros.addCampoAlQuery(TipoRemanenteEnum.idMedioPago_Remanente_NO_Actualizable(), Types.NUMERIC);
			}else{
				query += " and cobro.ID_COBRO in (select ID_COBRO from SHV_COB_ED_CREDITO "
						+ "where ID_TIPO_MEDIO_PAGO in (?, ?) and ID_CLIENTE_LEGADO = ? and CUENTA = ? and CODIGO_TIPO_REMANENTE = ? "
						+ ")";
				parametros.addCampoAlQuery(TipoRemanenteEnum.idMedioPago_Remanente_Actualizable(), Types.NUMERIC);
				parametros.addCampoAlQuery(TipoRemanenteEnum.idMedioPago_Remanente_NO_Actualizable(), Types.NUMERIC);
				if(Validaciones.isNumeric(filtro.getRefMP())){
					Long nroRefMic = Long.valueOf(filtro.getRefMP());
					String nroRefMicString = nroRefMic.toString();
					String cliente = nroRefMicString.substring(0, nroRefMicString.length()-4);
					String cuenta = nroRefMicString.substring(nroRefMicString.length()-4, nroRefMicString.length()-1);
					String codigoTipoRemanente = nroRefMicString.substring(nroRefMicString.length()-1);
					parametros.addCampoAlQuery(cliente, Types.NUMERIC);
					parametros.addCampoAlQuery(cuenta, Types.NUMERIC);
					parametros.addCampoAlQuery(codigoTipoRemanente, Types.NUMERIC);
				}else{
					parametros.addCampoAlQuery(null, Types.NUMERIC);
					parametros.addCampoAlQuery(null, Types.NUMERIC);
					parametros.addCampoAlQuery(null, Types.NUMERIC);
				}
			}
		}else if(!Validaciones.isNullOrEmpty(filtro.getIdTipoMedioPago()) && 
				(TipoCreditoEnum.DESISTIMIENTO.getIdTipoMedioPago().equals(filtro.getIdTipoMedioPago()) ||
				TipoCreditoEnum.IIBB.getIdTipoMedioPago().equals(filtro.getIdTipoMedioPago()) ||
//				TipoCreditoEnum.PROVEEDORES.getIdTipoMedioPago().equals(filtro.getIdTipoMedioPago()) ||
				TipoCreditoEnum.CESION_CREDITOS.getIdTipoMedioPago().equals(filtro.getIdTipoMedioPago()) ||
				TipoCreditoEnum.OTRAS_COMPENSACIONES.getIdTipoMedioPago().equals(filtro.getIdTipoMedioPago()) ||
//				TipoCreditoEnum.LIQUIDOPRODUCTO.getIdTipoMedioPago().equals(filtro.getIdTipoMedioPago()) ||
				TipoCreditoEnum.INTERCOMPANY.getIdTipoMedioPago().equals(filtro.getIdTipoMedioPago()) ||
				TipoCreditoEnum.PLANPAGO.getIdTipoMedioPago().equals(filtro.getIdTipoMedioPago()))){
			if(Validaciones.isNullOrEmpty(filtro.getRefMP())){
				query += " and cobro.ID_COBRO in (select ID_COBRO from SHV_COB_ED_OTROS_MEDIO_PAGO "
						+ "where ID_TIPO_MEDIO_PAGO = ? ) ";
				TipoCreditoEnum enumByValor = TipoCreditoEnum.getEnumByValor(filtro.getIdTipoMedioPago());
				parametros.addCampoAlQuery(enumByValor.getIdTipoMedioPago(), Types.VARCHAR);
			}else if(TipoCreditoEnum.PLANPAGO.getIdTipoMedioPago().equals(filtro.getIdTipoMedioPago()) &&
					!Validaciones.isNullOrEmpty(filtro.getRefMP())){
				query += " and cobro.ID_COBRO in (select cl.id_cobro from shv_cob_ed_medio_pago_cliente cl, SHV_COB_ED_OTROS_MEDIO_PAGO mp "
						+ "where cl.ID_CLIENTE_LEGADO = ? " 
						+ "and mp.id_tipo_medio_pago = ? "
						+ "and mp.id_cobro=cl.id_cobro "
						+ "and mp.id_medio_pago = cl.id_medio_pago ) ";
				TipoCreditoEnum enumByValor = TipoCreditoEnum.getEnumByValor(filtro.getIdTipoMedioPago());
				if(Validaciones.isNumeric(filtro.getRefMP())){
					Long nroRefMic = Long.valueOf(filtro.getRefMP());
					parametros.addCampoAlQuery(nroRefMic, Types.NUMERIC);
				}else{
					parametros.addCampoAlQuery(null, Types.NUMERIC);
				}
				parametros.addCampoAlQuery(enumByValor.getIdTipoMedioPago(), Types.VARCHAR);

			}else if(!Validaciones.isNullOrEmpty(filtro.getRefMP())){
				query += " and cobro.ID_COBRO in (select ID_COBRO from SHV_COB_ED_OTROS_MEDIO_PAGO "
						+ "where ID_TIPO_MEDIO_PAGO = ? and NRO_ACTA = ? ) ";
				TipoCreditoEnum enumByValor = TipoCreditoEnum.getEnumByValor(filtro.getIdTipoMedioPago());
				parametros.addCampoAlQuery(enumByValor.getIdTipoMedioPago(), Types.VARCHAR);
				parametros.addCampoAlQuery(filtro.getRefMP(), Types.VARCHAR);
			}
		}else if(!Validaciones.isNullOrEmpty(filtro.getIdTipoMedioPago()) && 
				TipoCreditoEnum.PROVEEDORES.getIdTipoMedioPago().equals(filtro.getIdTipoMedioPago())){
			
			query += " and cobro.ID_COBRO in (select ID_COBRO from SHV_COB_ED_OTROS_MEDIO_PAGO "
					+ "where ID_TIPO_MEDIO_PAGO = ? ";
			parametros.addCampoAlQuery(filtro.getIdTipoMedioPago(), Types.VARCHAR);
			
			if(!Validaciones.isNullOrEmpty(filtro.getRefMP())) {
				query += " and NRO_ACTA = ? ";
				parametros.addCampoAlQuery(filtro.getRefMP(), Types.VARCHAR);
			}
			
			query += ")";
			
			if(!Validaciones.isNullOrEmpty(filtro.getNroSAP())) {
				query += " and cobro.ID_COBRO in (select ID_COBRO from SHV_COB_MED_PAG_DOC_CAP "
						+ "where ID_DOCUMENTO = ? )";
				parametros.addCampoAlQuery(filtro.getNroSAP(), Types.VARCHAR);
			}
			
//			if(!Validaciones.isNullOrEmpty(filtro.getTipoTratamientoDiferencia())) {
//				query += " and cobro.ID_COBRO in (select ID_COBRO from SHV_COB_ED_TRATAMIENTO_DIF "
//						+ "where TIPO_TRATAMIENTO_DIFERENCIA = ? )";
//				parametros.addCampoAlQuery(filtro.getTipoTratamientoDiferencia(), Types.VARCHAR);
//			}
			
		}else if(!Validaciones.isNullOrEmpty(filtro.getIdTipoMedioPago()) &&
				TipoCreditoEnum.LIQUIDOPRODUCTO.getIdTipoMedioPago().equals(filtro.getIdTipoMedioPago())) {
			
			query += " and cobro.ID_COBRO in (select ID_COBRO from SHV_COB_ED_OTROS_MEDIO_PAGO "
					+ "where ID_TIPO_MEDIO_PAGO = ? ";
			parametros.addCampoAlQuery(filtro.getIdTipoMedioPago(), Types.VARCHAR);
			
			if(!Validaciones.isNullOrEmpty(filtro.getIdSubtipoCompensacion())) {
				query += " and ID_SUBTIPO_OTROS_MEDIO_PAGO = ? ";
				parametros.addCampoAlQuery(filtro.getIdSubtipoCompensacion(), Types.VARCHAR);
			}
			
			if(!Validaciones.isNullOrEmpty(filtro.getRefMP())) {
				query += " and NRO_ACTA = ? ";
				parametros.addCampoAlQuery(filtro.getRefMP(), Types.VARCHAR);
			}
			
			query += ")";
			
			if(!Validaciones.isNullOrEmpty(filtro.getNroSAP())) {
				query += " and cobro.ID_COBRO in (select ID_COBRO from SHV_COB_MED_PAG_DOC_CAP "
						+ "where ID_DOCUMENTO = ? )";
				parametros.addCampoAlQuery(filtro.getNroSAP(), Types.VARCHAR);
			}
			
//			if(!Validaciones.isNullOrEmpty(filtro.getTipoTratamientoDiferencia())) {
//				query += " and cobro.ID_COBRO in (select ID_COBRO from SHV_COB_ED_TRATAMIENTO_DIF "
//						+ "where TIPO_TRATAMIENTO_DIFERENCIA = ? )";
//				parametros.addCampoAlQuery(filtro.getTipoTratamientoDiferencia(), Types.VARCHAR);
//			}
		}
		return query;
	}
	
	private String filtroConsultaCobrosTratamientoDiferencia(String query, QueryParametrosUtil parametros, VistaSoporteCobroOnlineFiltro filtro) {

		
		if(!Validaciones.isNullOrEmpty(filtro.getTipoTratamientoDiferencia())) {

			if(TipoTratamientoDiferenciaEnum.DEBITO_PROX_FAC.name().equalsIgnoreCase(filtro.getTipoTratamientoDiferencia())) {

				query += " and cobro.ID_COBRO in (select c.ID_COBRO from SHV_COB_ED_COBRO c "
													+ "INNER JOIN SHV_COB_TRANSACCION t ON c.ID_OPERACION = t.ID_OPERACION "
													+ "INNER JOIN SHV_COB_MED_PAGO mp ON t.ID_TRANSACCION = mp.ID_TRANSACCION "
													+ "LEFT JOIN SHV_COB_MED_PAG_DEB_PROX_CLP mpc ON mp.ID_MEDIO_PAGO = mpc.ID_MEDIO_PAGO "
													+ "LEFT JOIN SHV_COB_MED_PAG_DEB_PROX_MIC mpm ON mp.ID_MEDIO_PAGO = mpm.ID_MEDIO_PAGO ";
				
				if(!Validaciones.isNullOrEmpty(filtro.getSistemaDestino())) {
					if(SistemaEnum.CALIPSO.getDescripcionCorta().equalsIgnoreCase(filtro.getSistemaDestino())) {
						query += "where mp.ID_TIPO_MEDIO_PAGO = 28 ";
						
						if(!Validaciones.isNullOrEmpty(filtro.getNroAcuerdoFact())) {
							query += "and mpc.ACUERDO_TRASLADO_CARGO = "+filtro.getNroAcuerdoFact();
						}
						
					}else if(SistemaEnum.MIC.getDescripcionCorta().equalsIgnoreCase(filtro.getSistemaDestino())){
						query += "where mp.ID_TIPO_MEDIO_PAGO = 29 ";
						
						if(!Validaciones.isNullOrEmpty(filtro.getNroAcuerdoFact())) {
							query += "and mpm.ACUERDO_TRASLADO_CARGO = "+filtro.getNroAcuerdoFact();
						}
					}
				}else {
					query += "where mp.ID_TIPO_MEDIO_PAGO in (28,29) ";
					
					if(!Validaciones.isNullOrEmpty(filtro.getNroAcuerdoFact())) {
						query += "and (mpc.ACUERDO_TRASLADO_CARGO = "+filtro.getNroAcuerdoFact()+" or mpm.ACUERDO_TRASLADO_CARGO = "+filtro.getNroAcuerdoFact()+")";
					}
				}
				
				query += ") ";
				
				
				
				
			}else {

				query += " and cobro.ID_COBRO in (select ID_COBRO from SHV_COB_ED_TRATAMIENTO_DIF where TIPO_TRATAMIENTO_DIFERENCIA = ? ";

				parametros.addCampoAlQuery(filtro.getTipoTratamientoDiferencia(), Types.VARCHAR);

				if(!Validaciones.isNullOrEmpty(filtro.getSistemaDestino())) {
					query += " and SISTEMA_DESTINO = ? ";
					parametros.addCampoAlQuery(SistemaEnum.getEnumByDescripcionCorta(filtro.getSistemaDestino()), Types.VARCHAR);
				}
				if(!Validaciones.isNullOrEmpty(filtro.getNroAcuerdoFact())) {
					query += " and ACUERDO_FACTURACION = ? ";
					parametros.addCampoAlQuery(filtro.getNroAcuerdoFact(), Types.NUMERIC);
				}
				if(!Validaciones.isNullOrEmpty(filtro.getNroTramite())) {
					query += " and NUMERO_TRAMITE_REINTEGRO = ? ";
					parametros.addCampoAlQuery(filtro.getNroTramite(), Types.NUMERIC);
				}

				query += ")";

			}


		}
		
		return query;
	}
	/* MAR ACÁ */
	private String filtroConsultaCobrosNroDocumentoYNroRefMic(String query, QueryParametrosUtil parametros, VistaSoporteCobroOnlineFiltro filtro) {
		
		/* Filtro Numero Documento Debito*/
		if (!Validaciones.isNullOrEmpty(filtro.getNroDocumento())) {
			
			query += " and cobro.ID_COBRO in ("
					+ "select ID_COBRO from SHV_COB_ED_DEBITO " 
					+ "where "
					+ "(trim(numero_referencia_duc) = ?) "
					+ "or "
					+ " (SUCURSAL_COMPROBANTE || '-' || NUMERO_COMPROBANTE = ? "
					+ " and (CLASE_COMPROBANTE is null or (CLASE_COMPROBANTE <> 'A' or CLASE_COMPROBANTE <> 'B' or CLASE_COMPROBANTE <> 'E'))) "
					+ " or CLASE_COMPROBANTE || '-'|| SUCURSAL_COMPROBANTE || '-' || NUMERO_COMPROBANTE = ? "
					+ " UNION "
					+ " select ID_COBRO from SHV_COB_ED_OTROS_DEBITO odeb "
					+ " WHERE odeb.clase_comprobante || '-' || odeb.sucursal_comprobante || '-' || odeb.numero_comprobante = ? "
					+ " or odeb.clase_comprobante || odeb.sucursal_comprobante || '-' || odeb.numero_comprobante = ? "
					+ " or odeb.sucursal_comprobante || '-'|| odeb.numero_comprobante= ? "
					+ " )";
		
			parametros.addCampoAlQuery(filtro.getNroDocumento(), Types.VARCHAR);
			parametros.addCampoAlQuery(filtro.getNroDocumento(), Types.VARCHAR);
			parametros.addCampoAlQuery(filtro.getNroDocumento(), Types.VARCHAR);
		
			parametros.addCampoAlQuery(filtro.getNroDocumento(), Types.VARCHAR);
			parametros.addCampoAlQuery(filtro.getNroDocumento(), Types.VARCHAR);
			parametros.addCampoAlQuery(filtro.getNroDocumento(), Types.VARCHAR);
			
		}
		
		/* Filtro Nro Ref MAR */
		if (!Validaciones.isNullOrEmpty(filtro.getNroRef())) { 
			query += " and cobro.ID_COBRO in (select ID_COBRO from SHV_COB_ED_DEBITO "
					+ "where NUMERO_REFERENCIA_MIC = ?  "
					+ "UNION "
					+ "SELECT odeb.id_cobro FROM SHV_COB_ED_OTROS_DEBITO odeb "
					+ "WHERE odeb.REFERENCIA = ? ) ";
			parametros.addCampoAlQuery(filtro.getNroRef(), Types.NUMERIC);
			parametros.addCampoAlQuery(filtro.getNroRef(), Types.NUMERIC);
		}
		return query;
	}	
	
	private String filtroConsultaCobrosClientesLegado(String query, QueryParametrosUtil parametros, VistaSoporteCobroOnlineFiltro filtro) {

		if (Validaciones.isCollectionNotEmpty(filtro.getListaClientesSegunFiltros())) { 
			
			if(Validaciones.isCollectionNotEmpty(filtro.getListaClientesSegunFiltros())) {
				List<ClienteDto> listaClientes =  filtro.getListaClientesSegunFiltros();
				String queryClientes = " ID_CLIENTE_LEGADO in (";
				String clientes = "";
				for (ClienteDto cliente : listaClientes) {
					String idCliente = cliente.getIdClienteLegado();
					clientes += "?, ";
					parametros.addCampoAlQuery(idCliente, Types.VARCHAR);
				}
				queryClientes += clientes.substring(0, clientes.length()-2) + ")";
				query += " and cobro.ID_COBRO in (select ID_COBRO from SHV_COB_ED_CLIENTE where";
				query += queryClientes + ")";
			}
		}
		return query;
	}
	
	private String filtroConsultaAprobador(String query, QueryParametrosUtil parametros, VistaSoporteCobroOnlineFiltro filtro) {

		if (!Validaciones.isNullOrEmpty(filtro.getReferenteCaja())) { 
			query += "and (cobro.ID_REFERENTE_CAJA = ? ";
			parametros.addCampoAlQuery(filtro.getReferenteCaja(), Types.VARCHAR); 
			query += " or cobro.ID_COBRO in (select tareaCobro.ID_COBRO from shv_wf_tarea_cobro tareaCobro join shv_wf_tarea tarea on  tareaCobro.id_tarea = tarea.id_tarea where tarea.tipo_accion = 'CONFIRMADA' and tarea.usuario_ejecucion = ?)";
			parametros.addCampoAlQuery(filtro.getReferenteCaja(), Types.VARCHAR); 
			query += ")";
		}
		if(!Validaciones.isNullOrEmpty(filtro.getReferenteOperacionesExternas())){
			query += "and (cobro.ID_REFERENTE_OPER_EXTERNA = ? ";
			parametros.addCampoAlQuery(filtro.getReferenteOperacionesExternas(), Types.VARCHAR); 
			query += " or cobro.ID_COBRO in (select tareaCobro.ID_COBRO from shv_wf_tarea_cobro tareaCobro join shv_wf_tarea tarea on  tareaCobro.id_tarea = tarea.id_tarea where tarea.tipo_accion = 'CONFIRMADA' and tarea.usuario_ejecucion = ?)";
			parametros.addCampoAlQuery(filtro.getReferenteOperacionesExternas(), Types.VARCHAR); 
			query += ")";
		}
		
		return query;
	}

	
	
	
	/**@author u579607
	 * Arma la consulta para la busqueda de historial de un cobro
	 * @param cobroDto
	 * @return
	 */
	private QueryParametrosUtil obtenerSQLConsultaDescobroHistorial(VistaSoporteBusquedaDescobroHistorialFiltro filtro) {
	
		
		StringBuffer consulta = new StringBuffer();
		QueryParametrosUtil parametros = new QueryParametrosUtil();
		consulta.append("SELECT * FROM SHV_SOP_DESCOBROS_HISTORIAL where ID_DESCOBRO = ? ");

		parametros.addCampoAlQuery(filtro.getIdDescobro(), Types.VARCHAR); 
		
		
		parametros.setSql(consulta.toString());
		return parametros;
	}
	
	@Override
	public List<VistaSoporteResultadoBusquedaDescobroHistorial> obtenerHistorialDescobro(
			VistaSoporteBusquedaDescobroHistorialFiltro filtro) throws PersistenciaExcepcion {
		
		List<Map<String, Object>> listaResultadoQuery;

		try {
			QueryParametrosUtil qp = obtenerSQLConsultaDescobroHistorial(filtro);
			listaResultadoQuery = buscarUsandoSQL(qp);
	
			List<VistaSoporteResultadoBusquedaDescobroHistorial> resultado = new ArrayList<>();
		
			for (Map<String, Object> archivoQuery : listaResultadoQuery) { // recorro la lista de resultados
				
				VistaSoporteResultadoBusquedaDescobroHistorial reg = new VistaSoporteResultadoBusquedaDescobroHistorial();
				
					 reg.setIdReversion(((BigDecimal) archivoQuery.get("ID_DESCOBRO")).longValue());
					 reg.setIdOperacion(((BigDecimal) archivoQuery.get("ID_OPERACION")).longValue());
					 reg.setEstado(archivoQuery.get("ESTADO").toString());
					 
					 if (!Validaciones.isObjectNull(archivoQuery.get("ID_MARCA"))) {
					 reg.setSubEstado(archivoQuery.get("ID_MARCA").toString());
					 }
					 if (!Validaciones.isObjectNull(archivoQuery.get("USUARIO_ULTIMA_MODIFICACION"))) {
					 reg.setUsuarioUltimaModificacion(archivoQuery.get("USUARIO_ULTIMA_MODIFICACION").toString());
					 }
					 if (!Validaciones.isObjectNull(archivoQuery.get("FECHA_ULTIMA_MODIFICACION"))) {
					 reg.setFechaUltimaModificacion(archivoQuery.get("FECHA_ULTIMA_MODIFICACION").toString());
					 }
					 if (!Validaciones.isObjectNull(archivoQuery.get("MENSAJE_ESTADO"))) {
					 reg.setMensajeError(archivoQuery.get("MENSAJE_ESTADO").toString());
					 }
					 if (!Validaciones.isObjectNull(archivoQuery.get("NUMERO_TRANSACCION"))) {
						 reg.setNroTransaccion(archivoQuery.get("NUMERO_TRANSACCION").toString());
					 }
					 if (!Validaciones.isObjectNull(archivoQuery.get("NUMERO_TRANSACCION_FICTICIO"))) {
						 reg.setNroTransaccionFicticio(archivoQuery.get("NUMERO_TRANSACCION_FICTICIO").toString());
					 }
					 if (!Validaciones.isObjectNull(archivoQuery.get("TIPO_COMPROBANTE")) || !Validaciones.isObjectNull(archivoQuery.get("CLASE_COMPROBANTE")) || !Validaciones.isObjectNull(archivoQuery.get("SUCURSAL_COMPROBANTE")) || !Validaciones.isObjectNull(archivoQuery.get("NUMERO_COMPROBANTE"))) {
					 reg.setNroDocumentoDebito(archivoQuery.get("TIPO_COMPROBANTE").toString()+archivoQuery.get("CLASE_COMPROBANTE").toString()+archivoQuery.get("SUCURSAL_COMPROBANTE").toString()+archivoQuery.get("NUMERO_COMPROBANTE").toString());
					 }
					 if (!Validaciones.isObjectNull(archivoQuery.get("IMPORTE_COBRAR"))) {
					 reg.setImporteACobrar(archivoQuery.get("IMPORTE_COBRAR").toString());
					 }
					 if (!Validaciones.isObjectNull(archivoQuery.get("FECHA_VALOR"))) {
					 reg.setFechaCobro(archivoQuery.get("FECHA_VALOR").toString());
					 }
					 if (!Validaciones.isObjectNull(archivoQuery.get("REFERENCIA"))) {
					 reg.setRefMedioPago(archivoQuery.get("REFERENCIA").toString());
					 }
					 if (!Validaciones.isObjectNull(archivoQuery.get("ACUERDO_TRASLADO_CARGO"))) {
					 reg.setAcuerdoFactDestinoCargo(archivoQuery.get("ACUERDO_TRASLADO_CARGO").toString());
					 }

				 resultado.add(reg);
			}
			return resultado;
		} catch (Exception e) {
			throw new PersistenciaExcepcion(e.getMessage(), e);
		}
	}
	
	/**
	 * 
	 * @param cobroTransaccionFiltro
	 * @return
	 */
	private QueryParametrosUtil obtenerSQLConsultaDescobroTransaccion(VistaSoporteDescobroTransaccionFiltro filtro) {
	
		StringBuffer consulta = new StringBuffer();
		QueryParametrosUtil parametros = new QueryParametrosUtil();

		consulta.append("SELECT  * FROM SHV_SOP_DESCOBROS_GRILLA_TRANS ");
		
		if (!Validaciones.isObjectNull(filtro.getIdDescobro())) { 
			consulta.append(" where ID_DESCOBRO = ? ");
			parametros.addCampoAlQuery(filtro.getIdDescobro(), Types.NUMERIC); 
		} else { 
			if (!Validaciones.isObjectNull(filtro.getIdOperacion())) { 
				consulta.append(" where ID_OPERACION = ? ");
				parametros.addCampoAlQuery(filtro.getIdOperacion(), Types.NUMERIC);
			}
		}
		
		consulta.append(" ORDER BY NUMERO_TRANSACCION_FORMATEADO ASC, SISTEMA_ORIGEN_DOCUMENTO ASC, TIPO_COMPROBANTE ASC, SUBTIPO_DOCUMENTO ASC ");
		
		parametros.setSql(consulta.toString());
		return parametros;
	}

	
	@Override
	public List<VistaSoporteResultadoBusquedaDescobroTransaccion> obtenerTransaccionesDescobro(VistaSoporteDescobroTransaccionFiltro descobroTransaccionFiltro) 
			throws PersistenciaExcepcion {

		List<Map<String, Object>> listaResultadoQuery;

		try {
			QueryParametrosUtil qp = obtenerSQLConsultaDescobroTransaccion(descobroTransaccionFiltro);
			listaResultadoQuery = buscarUsandoSQL(qp);
	
			List<VistaSoporteResultadoBusquedaDescobroTransaccion> resultado = new ArrayList<>();
		
			for (Map<String, Object> registroBD : listaResultadoQuery) { // recorro la lista de resultados
				
				VistaSoporteResultadoBusquedaDescobroTransaccion transaccion = new VistaSoporteResultadoBusquedaDescobroTransaccion();
				
				if (!Validaciones.isObjectNull(registroBD.get("NUMERO_TRANSACCION_FORMATEADO"))) {
					transaccion.setNumeroTransaccionFormateado((String)registroBD.get("NUMERO_TRANSACCION_FORMATEADO"));
				}
				if (!Validaciones.isObjectNull(registroBD.get("NRO_TRANS_FICTICIO_FORMATEADO"))) {
					transaccion.setNumeroTransaccionFicticioFormateado((String)registroBD.get("NRO_TRANS_FICTICIO_FORMATEADO"));
				}
				if (!Validaciones.isObjectNull(registroBD.get("ESTADO_TRANSACCION"))) {
					transaccion.setEstadoTransaccion(EstadoTransaccionEnum.getEnumByName((String)registroBD.get("ESTADO_TRANSACCION")));
				}
				if (!Validaciones.isObjectNull(registroBD.get("SISTEMA_ORIGEN_DOCUMENTO"))) {
					transaccion.setSistemaOrigenDocumento(SistemaEnum.getEnumByName((String)registroBD.get("SISTEMA_ORIGEN_DOCUMENTO")));
				}
				if (!Validaciones.isObjectNull(registroBD.get("TIPO_COMPROBANTE"))) {
					transaccion.setTipoComprobante(TipoComprobanteEnum.getEnumByName((String)registroBD.get("TIPO_COMPROBANTE")));
				}
				if (!Validaciones.isObjectNull(registroBD.get("SUBTIPO_DOCUMENTO"))) {
					
					String subTipoDocumento = (String) registroBD.get("SUBTIPO_DOCUMENTO");
					String descripcionSubTipoDocumento = "";

					if (TipoComprobanteEnum.REI.equals(transaccion.getTipoComprobante()) || TipoComprobanteEnum.GAN.equals(transaccion.getTipoComprobante())) {
						transaccion.setSubTipoDocumento(TipoTratamientoDiferenciaEnum.getEnumByName(subTipoDocumento).getSubTipoDocumento());
						descripcionSubTipoDocumento = TipoTratamientoDiferenciaEnum.getEnumByName(subTipoDocumento).getDescripcion();

					} else if (TipoComprobanteEnum.FAC.equals(transaccion.getTipoComprobante()) || TipoComprobanteEnum.DEB.equals(transaccion.getTipoComprobante())) {
						transaccion.setSubTipoDocumento(String.valueOf(TipoFacturaEnum.getEnumByCodigo(Integer.valueOf(subTipoDocumento)).codigo()));
						descripcionSubTipoDocumento = TipoFacturaEnum.getEnumByCodigo(Integer.valueOf(subTipoDocumento)).descripcion();
					
					} else if (TipoComprobanteEnum.DUC.equals(transaccion.getTipoComprobante())) {
						transaccion.setSubTipoDocumento(TipoDUCEnum.getEnumByCodigo(subTipoDocumento).codigo());
						descripcionSubTipoDocumento = TipoDUCEnum.getEnumByCodigo(subTipoDocumento).descripcion();
					}
					
					transaccion.setDescripcionSubTipoDocumento(descripcionSubTipoDocumento);
				}
				if (!Validaciones.isObjectNull(registroBD.get("ORIGEN_DOCUMENTO"))) {
					transaccion.setOrigenDocumento(OrigenDocumentoEnum.getEnumByName(registroBD.get("ORIGEN_DOCUMENTO").toString()));
				}
				if (!Validaciones.isObjectNull(registroBD.get("NUMERO_DOCUMENTO"))) {
					transaccion.setNumeroDocumento((String)registroBD.get("NUMERO_DOCUMENTO"));
				}
				if (!Validaciones.isObjectNull(registroBD.get("NUMERO_REFERENCIA_MIC"))) {
					transaccion.setNumeroReferenciaMic((String)registroBD.get("NUMERO_REFERENCIA_MIC"));
				}
				if (!Validaciones.isObjectNull(registroBD.get("FECHA_VENCIMIENTO"))) {
					transaccion.setFechaVencimiento((Date)registroBD.get("FECHA_VENCIMIENTO"));
				}
				if (!Validaciones.isObjectNull(registroBD.get("FECHA_SEGUNDO_VENCIMIENTO"))) {
					transaccion.setFechaSegundoVencimiento((Date)registroBD.get("FECHA_SEGUNDO_VENCIMIENTO"));
				}
				if (!Validaciones.isObjectNull(registroBD.get("MONEDA"))) {
					transaccion.setMoneda(MonedaEnum.getEnumByName((String)registroBD.get("MONEDA")));
				}
				if (!Validaciones.isObjectNull(registroBD.get("FECHA_COBRO"))) {
					transaccion.setFechaCobro((Date)registroBD.get("FECHA_COBRO"));
				}
				if (!Validaciones.isObjectNull(registroBD.get("IMPORTE_A_COBRAR"))) {
					transaccion.setImporte((BigDecimal)registroBD.get("IMPORTE_A_COBRAR"));
				}
				if (!Validaciones.isObjectNull(registroBD.get("TIPO_DE_CAMBIO_FECHA_COBRO"))) {
					transaccion.setTipoDeCambioFechaCobro((BigDecimal)registroBD.get("TIPO_DE_CAMBIO_FECHA_COBRO"));
				}
				if (!Validaciones.isObjectNull(registroBD.get("TIPO_DE_CAMBIO_FECHA_EMISION"))) {
					transaccion.setTipoDeCambioFechaEmision((BigDecimal)registroBD.get("TIPO_DE_CAMBIO_FECHA_EMISION"));
				}
				if (!Validaciones.isObjectNull(registroBD.get("IMPORTE_APLIC_FEC_EMIS_MON_ORI"))) {
					transaccion.setImporteAplicadoFechaEmisionMonedaOrigen((BigDecimal)registroBD.get("IMPORTE_APLIC_FEC_EMIS_MON_ORI"));
				}
				if (!Validaciones.isObjectNull(registroBD.get("SISTEMA_ORIGEN_MEDIO_PAGO"))) {
					transaccion.setSistemaMedioPago(SistemaEnum.getEnumByName((String)registroBD.get("SISTEMA_ORIGEN_MEDIO_PAGO")));
				}
				if (!Validaciones.isObjectNull(registroBD.get("TIPO_MEDIO_PAGO"))) {
					transaccion.setTipoMedioPago(TipoMedioPagoEnum.getEnumByDescripcion((String)registroBD.get("TIPO_MEDIO_PAGO")));
				}
				if (!Validaciones.isObjectNull(registroBD.get("SUBTIPO_MEDIO_PAGO"))) {
					transaccion.setSubTipoMedioPago(TipoRetencionEnum.getEnumByIdTipoRetencion(new Long(registroBD.get("SUBTIPO_MEDIO_PAGO").toString())));
				}
				if (!Validaciones.isObjectNull(registroBD.get("SUBTIPO_MEDIO_PAGO"))) {
					if (TipoMedioPagoEnum.REMANENTEACTUALIZADO.equals(transaccion.getTipoMedioPago()) || TipoMedioPagoEnum.REMANOACTUALIZABLE.equals(transaccion.getTipoMedioPago())) {
						transaccion.setSubTipoMedioPagoRemanente(TipoRemanenteEnum.getEnumByCodigo(new Long(registroBD.get("SUBTIPO_MEDIO_PAGO").toString())));
					} else { 
						transaccion.setSubTipoMedioPago(TipoRetencionEnum.getEnumByIdTipoRetencion(new Long(registroBD.get("SUBTIPO_MEDIO_PAGO").toString())));
					}
				}
				if (!Validaciones.isObjectNull(registroBD.get("REFERENCIA_MEDIO_PAGO"))) {
					transaccion.setReferenciaMedioPago((String)registroBD.get("REFERENCIA_MEDIO_PAGO"));
				}
				if (!Validaciones.isObjectNull(registroBD.get("MONEDA_MEDIO_PAGO"))) {
					transaccion.setMonedaMedioPago(MonedaEnum.getEnumByName((String)registroBD.get("MONEDA_MEDIO_PAGO")));
				}
				if (!Validaciones.isObjectNull(registroBD.get("FECHA_MEDIO_PAGO"))) {
					transaccion.setFechaMedioPago((Date)registroBD.get("FECHA_MEDIO_PAGO"));
				}
				if (!Validaciones.isObjectNull(registroBD.get("IMPORTE_MEDIO_PAGO"))) {
					transaccion.setImporteMedioPago((BigDecimal)registroBD.get("IMPORTE_MEDIO_PAGO"));
				}
				if (!Validaciones.isObjectNull(registroBD.get("TIPO_CAMBIO_FECHA_COBRO_MP"))) {
					transaccion.setTipoDeCambioFechaCobroMedioPago((BigDecimal)registroBD.get("TIPO_CAMBIO_FECHA_COBRO_MP"));
				}
				if (!Validaciones.isObjectNull(registroBD.get("TIPO_CAMBIO_FECHA_EMISION_MP"))) {
					transaccion.setTipoDeCambioFechaEmisionMedioPago((BigDecimal)registroBD.get("TIPO_CAMBIO_FECHA_EMISION_MP"));
				}
				if (!Validaciones.isObjectNull(registroBD.get("IMPORTE_APL_FEC_EMI_MON_ORI_MP"))) {
					transaccion.setImporteAplicadoFechaEmisionMonedaOrigenMedioPago((BigDecimal)registroBD.get("IMPORTE_APL_FEC_EMI_MON_ORI_MP"));
				}
				if (!Validaciones.isObjectNull(registroBD.get("QUE_HACER_CON_INTERESES"))) {
					transaccion.setQueHacerConIntereses(TratamientoInteresesEnum.getEnumByName((String)registroBD.get("QUE_HACER_CON_INTERESES")));
				}
				if (!Validaciones.isObjectNull(registroBD.get("COBRADOR_INTERESES_TRASLADADOS"))) {
					transaccion.setCobradorInteresesTrasladados((BigDecimal)registroBD.get("COBRADOR_INTERESES_TRASLADADOS"));
				}
				if (!Validaciones.isObjectNull(registroBD.get("INTERESES_GENERADOS"))) {
					transaccion.setIntereses((BigDecimal)registroBD.get("INTERESES_GENERADOS"));
				} else {
					transaccion.setIntereses(BigDecimal.ZERO);
				}
				if (!Validaciones.isObjectNull(registroBD.get("CHECK_TRASLADAR_INTERESES"))) {
					transaccion.setTrasladarIntereses(SiNoEnum.getEnumByName((String)registroBD.get("CHECK_TRASLADAR_INTERESES")));
				}
				if (!Validaciones.isObjectNull(registroBD.get("CHECK_COBRAR_SEGUNDO_VENCIMIEN"))) {
					transaccion.setCobrarSegundoVencimiento(SiNoEnum.getEnumByName((String)registroBD.get("CHECK_COBRAR_SEGUNDO_VENCIMIEN")));
				}
				if (!Validaciones.isObjectNull(registroBD.get("PORCENTAJE_A_BONIFICAR"))) {
					transaccion.setPorcentajeABonificar(new Integer(registroBD.get("PORCENTAJE_A_BONIFICAR").toString()));
				}
				if (!Validaciones.isObjectNull(registroBD.get("IMPORTE_A_BONIFICAR"))) {
					transaccion.setImporteABonificar((BigDecimal)registroBD.get("IMPORTE_A_BONIFICAR"));
				}
				if (!Validaciones.isObjectNull(registroBD.get("SISTEMA_ACUERDO"))) {
					transaccion.setSistemaAcuerdo(SistemaEnum.getEnumByName(registroBD.get("SISTEMA_ACUERDO").toString()));	
				}
				if (!Validaciones.isObjectNull(registroBD.get("ACUERDO_TRASLADO_CARGO"))) {
					transaccion.setAcuerdoDestinoCargos(new Long(registroBD.get("ACUERDO_TRASLADO_CARGO").toString()));	
				}
				if (!Validaciones.isObjectNull(registroBD.get("ESTADO_ACUERDO_TRASLADO_CARGO"))) {
					transaccion.setEstadoAcuerdoDestinoCargos(EstadoAcuerdoFacturacionEnum.getEnumByName((String)registroBD.get("ESTADO_ACUERDO_TRASLADO_CARGO")));
				}
				if (!Validaciones.isObjectNull(registroBD.get("ID_CLIENTE_ACUERDO_TRAS_CARGO"))) {
					transaccion.setIdClienteLegadoAcuerdoTrasladoCargo(new Long(registroBD.get("ID_CLIENTE_ACUERDO_TRAS_CARGO").toString()));	
				}
				if (!Validaciones.isObjectNull(registroBD.get("ESTADO_CARGO_GENERADO"))) {
					transaccion.setEstadoCargoGenerado(EstadoCargoGeneradoEnum.getEnumByName(registroBD.get("ESTADO_CARGO_GENERADO").toString()));	
				}
				if (!Validaciones.isObjectNull(registroBD.get("SISTEMA_ACUERDO_CONTRACARGO"))) {
					transaccion.setSistemaAcuerdoContracargo(SistemaEnum.getEnumByName(registroBD.get("SISTEMA_ACUERDO_CONTRACARGO").toString()));	
				}
				if (!Validaciones.isObjectNull(registroBD.get("ACUERDO_CONTRACARGO"))) {
					transaccion.setAcuerdoContracargo(new Long (registroBD.get("ACUERDO_CONTRACARGO").toString()));
				}
				if (!Validaciones.isObjectNull(registroBD.get("ESTADO_ACUERDO_CONTRACARGO"))) {
					transaccion.setEstadoAcuerdoContracargo(EstadoAcuerdoFacturacionEnum.getEnumByName(registroBD.get("ESTADO_ACUERDO_CONTRACARGO").toString()));	
				}
				if (!Validaciones.isObjectNull(registroBD.get("ID_CLIENTE_ACUERDO_CONTRACARGO"))) {
					transaccion.setIdClienteAcuerdoContracargo(new Long (registroBD.get("ID_CLIENTE_ACUERDO_CONTRACARGO").toString()));
				}
				if (!Validaciones.isObjectNull(registroBD.get("TIPO_MENSAJE_ESTADO_TRANSACCIO"))) {
					transaccion.setTipoMensajeTransaccion(TipoMensajeEstadoEnum.getEnumByName((String)registroBD.get("TIPO_MENSAJE_ESTADO_TRANSACCIO")));
				}
				if (!Validaciones.isObjectNull(registroBD.get("MENSAJE_ESTADO_TRANSACCION"))) {
					transaccion.setMensajeTransaccion((String)registroBD.get("MENSAJE_ESTADO_TRANSACCION"));
				}
				if (!Validaciones.isObjectNull(registroBD.get("ESTADO_TRANSACCION"))) {
					transaccion.setEstadoTransaccion(EstadoTransaccionEnum.getEnumByName((String)registroBD.get("ESTADO_TRANSACCION")));
				}
				if (!Validaciones.isObjectNull(registroBD.get("TIPO_MENSAJE_ESTADO_DEBITO"))) {
					transaccion.setTipoMensajeDebito(TipoMensajeEstadoEnum.getEnumByName((String)registroBD.get("TIPO_MENSAJE_ESTADO_DEBITO")));
				}
				if (!Validaciones.isObjectNull(registroBD.get("MENSAJE_ESTADO_DEBITO"))) {
					transaccion.setMensajeDebito((String)registroBD.get("MENSAJE_ESTADO_DEBITO"));
				}
				if (!Validaciones.isObjectNull(registroBD.get("ESTADO_DEBITO"))) {
					transaccion.setEstadoDebito(EstadoFacturaMedioPagoEnum.getEnumByName((String)registroBD.get("ESTADO_DEBITO")));
				}
				if (!Validaciones.isObjectNull(registroBD.get("TIPO_MENSAJE_ESTADO_CREDITO"))) {
					transaccion.setTipoMensajeCredito(TipoMensajeEstadoEnum.getEnumByName((String)registroBD.get("TIPO_MENSAJE_ESTADO_CREDITO")));
				}
				if (!Validaciones.isObjectNull(registroBD.get("MENSAJE_ESTADO_CREDITO"))) {
					transaccion.setMensajeCredito((String)registroBD.get("MENSAJE_ESTADO_CREDITO"));
				}
				if (!Validaciones.isObjectNull(registroBD.get("ESTADO_CREDITO"))) {
					transaccion.setEstadoCredito(EstadoFacturaMedioPagoEnum.getEnumByName((String)registroBD.get("ESTADO_CREDITO")));
				}
				if (!Validaciones.isObjectNull(registroBD.get("ID_DESCOBRO"))) {
					transaccion.setIdDescobro(new Long(registroBD.get("ID_DESCOBRO").toString()));
				}
				if (!Validaciones.isObjectNull(registroBD.get("ID_OPERACION"))) {
					transaccion.setIdOperacion(new Long(registroBD.get("ID_OPERACION").toString()));
				}
				if (!Validaciones.isObjectNull(registroBD.get("ID_TRANSACCION"))) {
					transaccion.setIdTransaccion(new Long(registroBD.get("ID_TRANSACCION").toString()));
				}

				if (!Validaciones.isObjectNull(registroBD.get("NUMERO_TRANSACCION"))) {
					transaccion.setNumeroTransaccion(new Long(registroBD.get("NUMERO_TRANSACCION").toString()));
					transaccion.setNumeroTransaccionOriginal(new Long(registroBD.get("NUMERO_TRANSACCION").toString()));
				}

				if (!Validaciones.isObjectNull(registroBD.get("NUMERO_TRANSACCION_FICTICIO"))) {
					transaccion.setNumeroTransaccionFicticio(new Long(registroBD.get("NUMERO_TRANSACCION_FICTICIO").toString()));
				}
				
				if (!Validaciones.isObjectNull(registroBD.get("NUMERO_TRANSACCION_PADRE"))) {
					
					transaccion.setNumeroTransaccionPadre(new Long(registroBD.get("NUMERO_TRANSACCION_PADRE").toString()));
					transaccion.setNumeroTransaccion(new Long(registroBD.get("NUMERO_TRANSACCION_PADRE").toString()));

					if (!Validaciones.isObjectNull(registroBD.get("NUMERO_TRANSACCION_PADRE_FORMA"))) {
						transaccion.setNumeroTransaccionFormateado((String)registroBD.get("NUMERO_TRANSACCION_PADRE_FORMA"));
					}
				}
				
				if (!Validaciones.isObjectNull(registroBD.get("NRO_TRANSACCION_PADRE_FICTICIO"))) {
					
					transaccion.setNumeroTransaccionPadreFicticio(new Long(registroBD.get("NRO_TRANSACCION_PADRE_FICTICIO").toString()));
					transaccion.setNumeroTransaccionFicticio(new Long(registroBD.get("NRO_TRANSACCION_PADRE_FICTICIO").toString()));

					if (!Validaciones.isObjectNull(registroBD.get("NRO_TRANS_PADRE_FICTICIO_FORMA"))) {
						transaccion.setNumeroTransaccionFicticioFormateado((String)registroBD.get("NRO_TRANS_PADRE_FICTICIO_FORMA"));
					}
				}
				
				if (!Validaciones.isObjectNull(registroBD.get("ID_FACTURA"))) {
					transaccion.setIdFactura(new Long(registroBD.get("ID_FACTURA").toString()));
				}
				if (!Validaciones.isObjectNull(registroBD.get("ID_MEDIO_PAGO"))) {	
					transaccion.setIdMedioPago(new Long(registroBD.get("ID_MEDIO_PAGO").toString()));
				}
				if (!Validaciones.isObjectNull(registroBD.get("ID_TRATAMIENTO_DIFERENCIA"))) {	
					transaccion.setIdTratamientoDiferencia(new Long(registroBD.get("ID_TRATAMIENTO_DIFERENCIA").toString()));
				}
				
				if (!Validaciones.isObjectNull(registroBD.get("ID_SOCIEDAD"))) {
					transaccion.setSociedad(SociedadEnum.getEnumByName(registroBD.get("ID_SOCIEDAD").toString()));
				}
				if (!Validaciones.isObjectNull(registroBD.get("GRUPO"))) {
					transaccion.setGrupo(registroBD.get("GRUPO").toString());
				}
				
				resultado.add(transaccion);
			}
			return resultado;

		} catch (Exception e) {
			throw new PersistenciaExcepcion(e.getMessage(), e);
		}
	}

	/**
	 * 
	 * @param cobroTransaccionFiltro
	 * @return
	 */
	private QueryParametrosUtil obtenerSQLConsultaDescobroOperacionRelacionada(VistaSoporteDescobroOperacionRelacionadaFiltro filtro) {
	
		StringBuffer consulta = new StringBuffer();
		QueryParametrosUtil parametros = new QueryParametrosUtil();

		consulta.append("SELECT  * FROM SHV_SOP_DESCOBROS_OPER_RELAC ");
		
		if (!Validaciones.isObjectNull(filtro.getIdDescobro())) { 
			consulta.append(" where ID_DESCOBRO = ? ");
			parametros.addCampoAlQuery(filtro.getIdDescobro(), Types.NUMERIC); 
		}
		
//		consulta.append(" ORDER BY  ");
		
		parametros.setSql(consulta.toString());
		return parametros;
	}
	
	@Override
	public List<VistaSoporteResultadoBusquedaDescobroOperacionesRelacionadas> obtenerOperacionesRelacionadasDescobro (VistaSoporteDescobroOperacionRelacionadaFiltro filtro) 
			throws PersistenciaExcepcion {

		List<Map<String, Object>> listaResultadoQuery;

		try {
			QueryParametrosUtil qp = obtenerSQLConsultaDescobroOperacionRelacionada(filtro);
			listaResultadoQuery = buscarUsandoSQL(qp);
	
			List<VistaSoporteResultadoBusquedaDescobroOperacionesRelacionadas> resultado = new ArrayList<>();
		
			for (Map<String, Object> registroBD : listaResultadoQuery) { // recorro la lista de resultados
				
				VistaSoporteResultadoBusquedaDescobroOperacionesRelacionadas operacionRelacionada = new VistaSoporteResultadoBusquedaDescobroOperacionesRelacionadas();
				
				if (!Validaciones.isObjectNull(registroBD.get("SISTEMA"))) {
					operacionRelacionada.setSistema((String) registroBD.get("SISTEMA"));
				}
				if (!Validaciones.isObjectNull(registroBD.get("ID_OPERACION"))) {
					operacionRelacionada.setIdOperacionRelacionada(registroBD.get("ID_OPERACION").toString());
				}
				if (!Validaciones.isObjectNull(registroBD.get("ID_OPERACION_PADRE"))) {
					operacionRelacionada.setIdOperacionCobroPadre(registroBD.get("ID_OPERACION_PADRE").toString());
				}
				if (!Validaciones.isObjectNull(registroBD.get("NRO_TRANSACCION_PADRE"))) {
					operacionRelacionada.setIdTransaccionCobroPadre(registroBD.get("NRO_TRANSACCION_PADRE").toString());
				}
				if (!Validaciones.isObjectNull(registroBD.get("TIPO_COMPROBANTE"))) {
					operacionRelacionada.setTipoDocumentoRelacionado(registroBD.get("TIPO_COMPROBANTE").toString());
				}
				if (!Validaciones.isObjectNull(registroBD.get("NRO_DOCUMENTO_RELACIONADO"))) {
					operacionRelacionada.setNroDocumentoRelacionado((String)registroBD.get("NRO_DOCUMENTO_RELACIONADO"));
				}
				if (!Validaciones.isObjectNull(registroBD.get("DESC_MOTIVO_COBRO"))) {
					operacionRelacionada.setMotivoCobro(registroBD.get("DESC_MOTIVO_COBRO").toString());
				}
				if (!Validaciones.isObjectNull(registroBD.get("CLIENTE"))) {
					operacionRelacionada.setIdCliente(registroBD.get("CLIENTE").toString());
				}
				if (!Validaciones.isObjectNull(registroBD.get("ESTADO"))) {
					operacionRelacionada.setEstadoCobro((String)registroBD.get("ESTADO"));
				}
				if (!Validaciones.isObjectNull(registroBD.get("SUB_ESTADO"))) {
					operacionRelacionada.setSubEstadoCobro((String)registroBD.get("SUB_ESTADO"));
				}
				if (!Validaciones.isObjectNull(registroBD.get("FECHA_ULTIMO_ESTADO"))) {
					operacionRelacionada.setFechaUltimoEstado((String)registroBD.get("FECHA_ULTIMO_ESTADO"));
				}
				if (!Validaciones.isObjectNull(registroBD.get("ANALISTA"))) {
					operacionRelacionada.setAnalista((String)registroBD.get("ANALISTA"));
				}
				if (!Validaciones.isObjectNull(registroBD.get("COPROPIETARIO"))) {
					operacionRelacionada.setCopropietario((String)registroBD.get("COPROPIETARIO"));
				}
				if (!Validaciones.isObjectNull(registroBD.get("ANALISTA_TEAM_COMERCIAL"))) {
					operacionRelacionada.setAnalistaTeamComercial((String)registroBD.get("ANALISTA_TEAM_COMERCIAL"));
				}
				if (!Validaciones.isObjectNull(registroBD.get("IMPORTE_TOTAL"))) {
					operacionRelacionada.setImporteFormateado(registroBD.get("IMPORTE_TOTAL").toString());
				}
				if (!Validaciones.isObjectNull(registroBD.get("FECHA_ALTA"))) {
					operacionRelacionada.setFechaAlta(registroBD.get("FECHA_ALTA").toString());
				}
				if (!Validaciones.isObjectNull(registroBD.get("FECHA_DERIVACION"))) {
					operacionRelacionada.setFechaDerivacion(registroBD.get("FECHA_DERIVACION").toString());
				}
				if (!Validaciones.isObjectNull(registroBD.get("FECHA_AUTORIZACION_REFERENTE"))) {
					operacionRelacionada.setFechaAutorizacionReferente(registroBD.get("FECHA_AUTORIZACION_REFERENTE").toString());
				}
				if (!Validaciones.isObjectNull(registroBD.get("FECHA_IMPUTACION"))) {
					operacionRelacionada.setFechaImputacion(registroBD.get("FECHA_IMPUTACION").toString());
				}
				resultado.add(operacionRelacionada);
			}
			return resultado;

		} catch (Exception e) {
			throw new PersistenciaExcepcion(e.getMessage(), e);
		}
	}
	@Override
	public boolean obtenerMarca(Object valor, QueryMarcaEnum queryMarca) throws PersistenciaExcepcion {
		boolean salida = false;
		List<Map<String, Object>> listaResultadoQuery;

		try {
			StringBuffer consulta = new StringBuffer();
			QueryParametrosUtil qp = new QueryParametrosUtil();
			consulta.append(queryMarca.query);
			qp.addCampoAlQuery(valor, queryMarca.tipoDatoParametro);
			qp.setSql(consulta.toString());
			listaResultadoQuery = buscarUsandoSQL(qp);
			if (!Validaciones.isObjectNull(listaResultadoQuery)) {
				if (listaResultadoQuery.size() > 0) {
					salida = true;
				}
			} 
			return salida;
		} catch (Exception e) {
			throw new PersistenciaExcepcion(e.getMessage(), e);
		}
	}
	
	/**@author u579607
	 * Arma la consulta para la busqueda de historial de un cobro
	 * @param cobroDto
	 * @return
	 */
	private QueryParametrosUtil obtenerSQLConsultaOperacionMasivaHistorial(String idOperacioMasiva) {
	
		
		StringBuffer consulta = new StringBuffer();
		QueryParametrosUtil parametros = new QueryParametrosUtil();
		consulta.append("SELECT  * FROM SHV_SOP_OPER_MASIVA_HISTORIAL where ID_OPERACION_MASIVA = ? order by fecha_modificacion desc");
	
		parametros.addCampoAlQuery(idOperacioMasiva, Types.VARCHAR); 
		
		parametros.setSql(consulta.toString());
		return parametros;
	}
	
	@Override
	public List<VistaSoporteResultadoBusquedaOperacionMasivaHistorial> obtenerHistorialOperacionMasiva(String idOperacioMasiva) throws PersistenciaExcepcion {
		
		List<Map<String, Object>> listaResultadoQuery;

		try {
			QueryParametrosUtil qp = obtenerSQLConsultaOperacionMasivaHistorial(idOperacioMasiva);
			listaResultadoQuery = buscarUsandoSQL(qp);
	
			List<VistaSoporteResultadoBusquedaOperacionMasivaHistorial> resultado = new ArrayList<VistaSoporteResultadoBusquedaOperacionMasivaHistorial>();
		
			for (Map<String, Object> archivoQuery : listaResultadoQuery) { // recorro la lista de resultados
				
				VistaSoporteResultadoBusquedaOperacionMasivaHistorial reg = new VistaSoporteResultadoBusquedaOperacionMasivaHistorial();
				
				reg.setIdOperacionMasiva(((BigDecimal) archivoQuery.get("id_operacion_masiva")).longValue());

				if (!Validaciones.isObjectNull(archivoQuery.get("estado"))) {
					reg.setEstado(archivoQuery.get("estado").toString());
				}
				if (!Validaciones.isObjectNull(archivoQuery.get("usuario_modificacion"))) {
					reg.setIdUsuarioModificacion(archivoQuery.get("usuario_modificacion").toString());
				}
				if (!Validaciones.isObjectNull(archivoQuery.get("fecha_modificacion"))) {
					reg.setFechaModificacion(archivoQuery.get("fecha_modificacion").toString());
				}
				if (!Validaciones.isObjectNull(archivoQuery.get("observacion"))) {
					reg.setObservaciones(archivoQuery.get("observacion").toString());
				}
				resultado.add(reg);
			}
			return resultado;
		} catch (Exception e) {
			throw new PersistenciaExcepcion(e.getMessage(), e);
		}
	}
	/**************************************************************************/
	// SAP u578936
	@Override
	public List<VistaSoporteResultadoCobroCreditoDebito> obtenerDocumentoCapPorReferencia(String idCapReferencia, String idCobro) throws PersistenciaExcepcion {
		List<Map<String, Object>> listaResultadoQuery;

		try {
			StringBuffer consulta = new StringBuffer();
			QueryParametrosUtil qp = new QueryParametrosUtil();

			consulta.append("Select distinct SCEDC.id_cap_referencia as ID_CAP_REFERENCIA,");
			consulta.append(" (SELECT LISTAGG(scec.id_operacion || ' / ' || scec.analista, ',') WITHIN GROUP(ORDER BY scec.id_operacion)");
			consulta.append("  FROM SHV_COB_ED_COBRO SCEC LEFT JOIN SHV_COB_ED_DOCUMENTO_CAP SCEDC2 ON SCEC.ID_COBRO = SCEDC2.ID_COBRO ");
			consulta.append("  INNER JOIN SHV_WF_WORKFLOW_ESTADO SWWE ON SCEC.ID_WORKFLOW = SWWE.ID_WORKFLOW ");
			
			if (!Validaciones.isNullOrEmpty(idCobro)){
				consulta.append("		WHERE scedc.ID_CAP_REFERENCIA = scedc2.ID_CAP_REFERENCIA and scec.id_cobro not in (?) and swwe.estado in ('COB_EN_EDICION', 'COB_EN_EDICION_VERIFCANDO_DEBITOS', 'COB_RECHAZADO', 'COB_PEND_REFERENTE_COBRANZA', 'COB_PEND_SUPERVISOR_OPERACIONES_ESPECIALES')) as OPERACION_ANALISTA");
				qp.addCampoAlQuery(idCobro, Types.VARCHAR); 
			} else {
				consulta.append("		WHERE scedc.ID_CAP_REFERENCIA = scedc2.ID_CAP_REFERENCIA and swwe.estado in ('COB_EN_EDICION', 'COB_EN_EDICION_VERIFCANDO_DEBITOS', 'COB_RECHAZADO', 'COB_PEND_REFERENTE_COBRANZA', 'COB_PEND_SUPERVISOR_OPERACIONES_ESPECIALES')) as OPERACION_ANALISTA");
			}
			consulta.append("	from SHV_COB_ED_DOCUMENTO_CAP SCEDC");
			consulta.append(" where SCEDC.ID_CAP_REFERENCIA = ? ");
			qp.addCampoAlQuery(idCapReferencia, Types.VARCHAR); 
			 
			qp.setSql(consulta.toString());
			listaResultadoQuery = buscarUsandoSQL(qp);
			
			List<VistaSoporteResultadoCobroCreditoDebito> resultado = new ArrayList<>();
			
			for (Map<String, Object> registroBD : listaResultadoQuery) { // recorro la lista de resultados
				
				VistaSoporteResultadoCobroCreditoDebito item = new VistaSoporteResultadoCobroCreditoDebito();
				
				if (!Validaciones.isObjectNull(registroBD.get("ID_CAP_REFERENCIA"))) {
					item.setIdReferencia((String)registroBD.get("ID_CAP_REFERENCIA"));
				}
				if (!Validaciones.isObjectNull(registroBD.get("OPERACION_ANALISTA"))) {
					item.setOperacionAnalista((String)registroBD.get("OPERACION_ANALISTA"));
				}
				resultado.add(item);
			}
			return resultado;

		} catch (Exception e) {
			throw new PersistenciaExcepcion(e.getMessage(), e);
		}
	}
	
	@Override
	public boolean obtenerMarcaCapEnCobrosPendienteProceso(String idCapReferencia) throws PersistenciaExcepcion {
		boolean salida = false;
		List<Map<String, Object>> listaResultadoQuery;

		try {
			StringBuffer consulta = new StringBuffer();
			QueryParametrosUtil qp = new QueryParametrosUtil();
	
			consulta.append("SELECT SCEC.ID_OPERACION FROM SHV_COB_ED_COBRO SCEC ");
			consulta.append("INNER JOIN SHV_WF_WORKFLOW_ESTADO SWWE ON SCEC.ID_WORKFLOW = SWWE.ID_WORKFLOW ");
			consulta.append("INNER JOIN SHV_COB_ED_DOCUMENTO_CAP SCEDC ON SCEC.ID_COBRO = SCEDC.ID_COBRO ");
			consulta.append("WHERE SWWE.ESTADO IN ('COB_PENDIENTE', 'COB_PROCESO') ");
			consulta.append("AND SCEDC.id_CAP_referencia = ? ");
			
			qp.addCampoAlQuery(idCapReferencia, Types.VARCHAR);
			qp.setSql(consulta.toString());
			listaResultadoQuery = buscarUsandoSQL(qp);
			
			if (!Validaciones.isObjectNull(listaResultadoQuery)) {
				if (listaResultadoQuery.size() > 0) {
					salida = true;
				}
			} 
			return salida;
		} catch (Exception e) {
			throw new PersistenciaExcepcion(e.getMessage(), e);
		}
	}
	
	
	/**
	 * Retorno los resultados producto de la simulación (verificación) de documentos CAP contra SAP
	 * 
	 * @param idCobro
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public List<String> consultarResultadoVerificacionDocumentosCap(Long idCobro) throws PersistenciaExcepcion {
		
		StringBuffer consulta = new StringBuffer();
		consulta.append("select distinct scmpdcr.descripcion_resultado ");
		consulta.append("from ");
		consulta.append("  shv_cob_cobro scc, ");
		consulta.append("  shv_cob_transaccion sct, ");
		consulta.append("  shv_cob_med_pago scmp, ");
		consulta.append("  (select id_medio_pago, id_medio_pago_doc_cap from shv_cob_med_pag_comp_proveedor "); 
		consulta.append("   where id_medio_pago_doc_cap is not null ");
		consulta.append("  union ");
		consulta.append("  select id_medio_pago, id_medio_pago_doc_cap from shv_cob_med_pag_comp_liquido ");
		consulta.append("  where id_medio_pago_doc_cap is not null) scmpc, ");
		consulta.append("  shv_cob_med_pag_doc_cap_result scmpdcr ");
		consulta.append("where  ");
		consulta.append("  scc.id_cobro = ? ");
		consulta.append("  and scc.id_operacion = sct.id_operacion ");
		consulta.append("  and sct.id_transaccion = scmp.id_transaccion ");
		consulta.append("  and scmp.id_medio_pago = scmpc.id_medio_pago ");
		consulta.append("  and scmpc.id_medio_pago_doc_cap = scmpdcr.id_medio_pago_doc_cap ");
		
		QueryParametrosUtil queryParametros = new QueryParametrosUtil();
		queryParametros.addCampoAlQuery(idCobro, Types.NUMERIC);
		queryParametros.setSql(consulta.toString());
		
		List<String> resultadosDocumentosCap = new ArrayList<String>();
		
		List<Map<String, Object>> listaResultadoQuery = buscarUsandoSQL(queryParametros);
		
		for (Map<String, Object> resultado : listaResultadoQuery) {
			if (!Validaciones.isObjectNull(resultado.get("DESCRIPCION_RESULTADO"))) { 
				resultadosDocumentosCap.add(resultado.get("DESCRIPCION_RESULTADO").toString());
			}
		}
		
		return resultadosDocumentosCap;
	}
	@Override
	public List<VistaSoporteConsultaDeudaPdfCap> obtenerRegistrosDeudaPdf(Long idOperacion) throws PersistenciaExcepcion {
		try {
			List<Map<String, Object>> listaResultadoQuery = null;
			StringBuffer consulta = new StringBuffer();
			QueryParametrosUtil qp = new QueryParametrosUtil();
			List<VistaSoporteConsultaDeudaPdfCap> lista = new ArrayList<VistaSoporteConsultaDeudaPdfCap>();
	

			consulta.append(" select * from SHV_SOP_BUSQ_REGIS_DEUDA_PDF where id_operacion =? ");
			qp.setSql(consulta.toString());
			qp.addCampoAlQuery(idOperacion,  Types.NUMERIC);
			listaResultadoQuery = buscarUsandoSQL(qp);

			if (!Validaciones.isObjectNull(listaResultadoQuery)) {
				for (Map<String, Object> resultado : listaResultadoQuery) {
					VistaSoporteConsultaDeudaPdfCap registro = new VistaSoporteConsultaDeudaPdfCap();

					if (!Validaciones.isObjectNull(resultado.get("Sistema"))) {
						if (!Validaciones.isObjectNull((SistemaEnum.getEnumByDescripcionCorta(resultado.get("Sistema").toString())))){
							registro.setSistema(SistemaEnum.getEnumByDescripcionCorta(resultado.get("Sistema").toString()));
							}else{
								registro.setSistema(SistemaEnum.getEnumByName(resultado.get("Sistema").toString()));
							}
					}
					if (!Validaciones.isObjectNull(resultado.get("sociedad"))){
							registro.setSociedad(SociedadEnum.getEnumByName(resultado.get("sociedad").toString()));	
					}
					if (!Validaciones.isObjectNull(resultado.get("id_cliente_legado"))) {
						registro.setCliente(resultado.get("id_cliente_legado").toString());
					}
					if (!Validaciones.isObjectNull(resultado.get("tipo_comprobante"))) {
						registro.setTipo(resultado.get("tipo_comprobante").toString());
					}
					if (!Validaciones.isObjectNull(resultado.get("numero_documento"))) {
						registro.setNroDeComprobante(resultado.get("numero_documento").toString());
					}
					if(!Validaciones.isObjectNull(resultado.get("numero_referencia"))){
						registro.setNroReferencia(resultado.get("numero_referencia").toString());
					}
					if (!Validaciones.isObjectNull(resultado.get("razon_social"))) {
						registro.setRazonSocial(resultado.get("razon_social").toString());
					}
					if (!Validaciones.isObjectNull(resultado.get("fecha_vencimiento"))) {
						registro.setFechaDateVto((Date) resultado.get("fecha_vencimiento"));
					}
					if (!Validaciones.isObjectNull(resultado.get("fecha_emision"))) {
						registro.setFechaEmision((Date) resultado.get("fecha_emision"));
					}
					if (!Validaciones.isObjectNull(resultado.get("moneda"))) {
						registro.setMoneda(MonedaEnum.getEnumByName(resultado.get("moneda").toString()));
					}
					if (!Validaciones.isObjectNull(resultado.get("moneda_importe"))) {
						registro.setMonedaImporte(MonedaEnum.getEnumByName(resultado.get("moneda_importe").toString()));
					}
					
					
					if (!Validaciones.isObjectNull(resultado.get("importe_original"))) {
						registro.setImporteOrigen(new BigDecimal(resultado.get("importe_original").toString()));
					}
					if (!Validaciones.isObjectNull(resultado.get("importe_a_cobrar"))) {
				//TODO eliminar importe a cobrar 
						registro.setImporteACobrar(new BigDecimal(resultado.get("importe_a_cobrar").toString()));
				///----
						registro.setaCompensarEnPesos(new BigDecimal(resultado.get("importe_a_cobrar").toString()));
					}
					
					if (!Validaciones.isObjectNull(resultado.get("monto_reclamado"))) {
						registro.setMontoReclamado(new BigDecimal(resultado.get("monto_reclamado").toString()).setScale(2, BigDecimal.ROUND_HALF_UP));
					}
					
					if (registro.getMoneda().equals(registro.getMonedaImporte())) {
						
						if (!Validaciones.isObjectNull(resultado.get("tipo_de_cambio"))) {
							registro.setTipoCambio(null);
						}
						if (!Validaciones.isObjectNull(resultado.get("saldo"))) {
							registro.setSaldo(new BigDecimal(resultado.get("saldo").toString()));
						}
											
						
						
					} else {
						if (!Validaciones.isObjectNull(resultado.get("saldo_actual_origen"))) {
							registro.setSaldoActual(new BigDecimal(resultado.get("saldo_actual_origen").toString()));
						}
						if (!Validaciones.isObjectNull(resultado.get("tipo_de_cambio"))) {
							registro.setTipoCambio(new BigDecimal(resultado.get("tipo_de_cambio").toString()));
						}
						if (!Validaciones.isObjectNull(resultado.get("saldo_origen"))) {
							registro.setSaldo(new BigDecimal(resultado.get("saldo_origen").toString()));
						}
												
					}
					lista.add(registro);
				}
			} 
			return lista;
		} catch (Exception e) {
			throw new PersistenciaExcepcion(e.getMessage(), e);
		}
	}
	@Override
	public List<VistaSoporteConsultaCapPdfCap> obtenerRegistrosCapPdf(Long idCobro) throws PersistenciaExcepcion {
		try {
			List<Map<String, Object>> listaResultadoQuery = null;
			StringBuffer consulta = new StringBuffer();
			QueryParametrosUtil qp = new QueryParametrosUtil();
			List<VistaSoporteConsultaCapPdfCap> lista = new ArrayList<VistaSoporteConsultaCapPdfCap>();
	
			consulta.append("Select ");
			consulta.append("scmpdcd.id_sociedad AS COD_SOCIEDAD, ");	
			consulta.append("scmpdcd.numero_doc_sap    AS NUMERO_DOC_SAP, ");
			consulta.append("sum (scmpdcd.importe_reglon_mon_origen_emis)    AS monto_reclamado, ");
			consulta.append("scmpdcd.FECHA_EMISION    AS FECHA_EMISION, ");
			consulta.append("scmpdcd.numero_legal_doc_proveedor AS NUMERO_LEGAL, ");
			consulta.append("scmpdcd.moneda_doc_proveedor AS MONEDA, ");
			consulta.append("scmpdcd.IMPORTE_TOTAL_MON_ORIGEN_EMIS AS IMPORTE_ORIGEN, ");
			consulta.append("scmpdcd.importe_total_mon_origen_emis -  NVL(imp_tot_mon_ori_doc_blo_pesos,0) AS SALDO_ACTUAL, ");
			consulta.append("scmpdcd.tipo_cambio_registrado_sap AS TIPO_CAMBIO_EMISION, ");
			consulta.append("scmpdcd.TIPO_CAMBIO_A_FECH_TIPO_CAMBIO AS TIPO_CAMBIO_SHIVA, ");
			consulta.append("CASE ");
			consulta.append("WHEN scmpdcd.moneda_doc_proveedor = scmpdc.moneda ");
			consulta.append("THEN sum(scmpdcd.importe_reglon_mon_origen_emis) ");
			consulta.append("ELSE CASE ");
			consulta.append("WHEN scmpdcd.CHECK_SIN_DIFERENCIA_CAMBIO = 'NO' ");
			consulta.append("THEN sum(scmpdcd.IMPORTE_REGLON_PESOS_FEC_SHIVA) ");
			consulta.append("ELSE ");
			consulta.append("sum(scmpdcd.IMPORTE_REGLON_PESOS_FEC_EMIS) ");
			consulta.append("END ");
			consulta.append("END as A_COMPENSAR, ");
			consulta.append("scmpdcd.CHECK_SIN_DIFERENCIA_CAMBIO AS SIN_DIFERECIA_CAMBIO, ");
			consulta.append("scmpdcd.SALDO_PES_EMI_DOC_NO_PESOS AS SALDO, ");
			consulta.append("scmpdcd.SALDO_PES_COBRO_DOC_NO_PESOS AS SALDO_PES_COBRO, ");
			consulta.append("scmpdc.moneda AS MONEDA_COBRO ");
			consulta.append("From shv_cob_med_pag_doc_cap_detall scmpdcd left join ");
			consulta.append("shv_cob_med_pag_doc_cap scmpdc ON (scmpdcd.id_medio_pago_doc_cap = scmpdc.id_medio_pago_doc_cap) ");
			consulta.append("where scmpdc.id_cobro = ? "); 
			consulta.append("group by scmpdcd.id_sociedad, scmpdcd.numero_doc_sap, scmpdcd.FECHA_EMISION, scmpdcd.numero_legal_doc_proveedor, scmpdcd.moneda_doc_proveedor, ");
			consulta.append("scmpdcd.IMPORTE_TOTAL_MON_ORIGEN_EMIS, scmpdcd.tipo_cambio_registrado_sap, scmpdcd.TIPO_CAMBIO_A_FECH_TIPO_CAMBIO, ");
			consulta.append("scmpdcd.CHECK_SIN_DIFERENCIA_CAMBIO, ");
			consulta.append("scmpdcd.SALDO_PES_EMI_DOC_NO_PESOS, scmpdcd.SALDO_PES_COBRO_DOC_NO_PESOS, scmpdc.moneda, scmpdcd.importe_total_mon_origen_emis -  NVL(imp_tot_mon_ori_doc_blo_pesos,0) ");

			qp.addCampoAlQuery(idCobro, Types.VARCHAR);

			qp.setSql(consulta.toString());
			listaResultadoQuery = buscarUsandoSQL(qp);

			if (!Validaciones.isObjectNull(listaResultadoQuery)) {
				for (Map<String, Object> resultado : listaResultadoQuery) {
					VistaSoporteConsultaCapPdfCap registro = new VistaSoporteConsultaCapPdfCap();

					//COD_SOCIEDAD
					if (!Validaciones.isObjectNull(resultado.get("COD_SOCIEDAD"))) {
						registro.setCodigoSociedad(resultado.get("COD_SOCIEDAD").toString());
					}
					if (!Validaciones.isObjectNull(resultado.get("NUMERO_DOC_SAP"))) {
						registro.setNumeroDocumentoSap(resultado.get("NUMERO_DOC_SAP").toString());
					}
					if (!Validaciones.isObjectNull(resultado.get("NUMERO_LEGAL"))) {
						registro.setNroDeComprobante((resultado.get("NUMERO_LEGAL").toString()));
					}
					if (!Validaciones.isObjectNull(resultado.get("FECHA_EMISION"))) {
						registro.setFechaEmision(((Date) resultado.get("FECHA_EMISION")));
					}
					if (!Validaciones.isObjectNull(resultado.get("MONEDA"))) {
						registro.setMoneda(MonedaEnum.getEnumByName(resultado.get("MONEDA").toString()));
					}
					if (!Validaciones.isObjectNull(resultado.get("IMPORTE_ORIGEN"))) {
						registro.setImporteOrigen(new BigDecimal(resultado.get("IMPORTE_ORIGEN").toString()));
					}
					if (!Validaciones.isObjectNull(resultado.get("SALDO_ACTUAL"))) {
						registro.setSaldoActual(new BigDecimal(resultado.get("SALDO_ACTUAL").toString()));
					}
					if (!Validaciones.isObjectNull(resultado.get("A_COMPENSAR"))) {
						registro.setaCompensarEnPesos(new BigDecimal(resultado.get("A_COMPENSAR").toString()));
					}
					
					if (!Validaciones.isObjectNull(resultado.get("MONEDA_COBRO")) && !Validaciones.isObjectNull(resultado.get("MONEDA"))) {
						MonedaEnum monedaCobro = MonedaEnum.getEnumByName(resultado.get("MONEDA_COBRO").toString());
						registro.setMoneda(MonedaEnum.getEnumByName(resultado.get("MONEDA").toString()));
						if (registro.getMoneda().equals(monedaCobro)) {
							registro.setTipoCambio(null);
							if (!Validaciones.isObjectNull(resultado.get("SALDO"))) {
								registro.setMontoReclamado(new BigDecimal(resultado.get("SALDO").toString()).subtract(registro.getaCompensarEnPesos()));
							}
							
						} else {
							if (SiNoEnum.SI.name().equals(resultado.get("SIN_DIFERECIA_CAMBIO").toString())) {
								if (!Validaciones.isObjectNull(resultado.get("TIPO_CAMBIO_EMISION"))) {
									registro.setTipoCambio(new BigDecimal(resultado.get("TIPO_CAMBIO_EMISION").toString()));
								}
								if (!Validaciones.isObjectNull(resultado.get("SALDO"))) {
									if (Validaciones.isObjectNull(resultado.get("monto_reclamado").toString())) {
									registro.setMontoReclamado(new BigDecimal(resultado.get("SALDO").toString()).subtract(registro.getaCompensarEnPesos()));
									}else {
										registro.setMontoReclamado((new BigDecimal(resultado.get("SALDO").toString()).subtract(
												//registro.getaCompensarEnPesos().divide(registro.getTipoCambio() ,4, RoundingMode.FLOOR)).negate()).setScale(2, BigDecimal.ROUND_HALF_UP));
												new BigDecimal(resultado.get("monto_reclamado").toString())
												)));
									}
								}
							} else {
								if (!Validaciones.isObjectNull(resultado.get("TIPO_CAMBIO_SHIVA"))) {
									registro.setTipoCambio(new BigDecimal(resultado.get("TIPO_CAMBIO_SHIVA").toString()));
								}
								if (!Validaciones.isObjectNull(resultado.get("SALDO")) && !Validaciones.isObjectNull(resultado.get("Monto_reclamado"))) {
									registro.setMontoReclamado((new BigDecimal(resultado.get("SALDO").toString()).subtract(
											//registro.getaCompensarEnPesos()).divide(registro.getTipoCambio(),4, RoundingMode.FLOOR)).negate().setScale(2, BigDecimal.ROUND_HALF_UP));
											new BigDecimal(resultado.get("monto_reclamado").toString())
											)));
								}
							}
						}
					}
					lista.add(registro);
				}
			} 
			return lista;
		} catch (Exception e) {
			throw new PersistenciaExcepcion(e.getMessage(), e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ShvSopExcepcionMorosidad> obtenerRegistrosExcepcionMorosidad(ExcepcionMorosidadFiltro filtro) throws PersistenciaExcepcion {
		StringBuilder str = new StringBuilder("from ShvSopExcepcionMorosidad excepcionMorosidad");
				
		str.append(" where not exists (");
		str.append(" from ShvParamCliente As cli");  
		str.append(" where cli.idClienteLegado = excepcionMorosidad.cliente");
		str.append(" and cli.empresa.idEmpresa != ? )");

		str.append(" and excepcionMorosidad.fechaModificacionUltimoEstado BETWEEN ? AND ? ");
		str.append(" ORDER BY excepcionMorosidad.numeroTransaccionFormateado ASC, excepcionMorosidad.idFactura ASC, ");
		str.append(" excepcionMorosidad.sistemaOrigen DESC, excepcionMorosidad.tipoDocumento DESC, excepcionMorosidad.numeroLegalComprobante DESC, ");
		str.append(" excepcionMorosidad.numeroReferencia DESC ");
		
		
		//str.append(" ORDER BY excepcionMorosidad.NUMERO_TRANSACCION_FORMATEADO ASC, SISTEMA_ORIGEN_DOCUMENTO ASC, TIPO_COMPROBANTE ASC, SUBTIPO_DOCUMENTO ASC ");

		QueryParametrosUtil qp = new QueryParametrosUtil(str.toString());
		try {

			qp.addCampoAlQuery(EmpresaEnum.TA.toString(), Types.VARCHAR);
			
			qp.addParametro(Utilidad.deserializeAndFormatDate(filtro.getFechaDesde(), Utilidad.DATE_TIME_FULL_FORMAT));
			qp.addParametro(Utilidad.deserializeAndFormatDate(filtro.getFechaHasta(), Utilidad.DATE_TIME_FULL_FORMAT));


			return this.buscarUsandoQueryConParametros(qp);
		} catch (ParseException e) {
			throw new PersistenciaExcepcion("Error en formato de fecha", e);
		}
	}

	/**
	 * 
	 * @param filtro
	 * @return
	 */
	private QueryParametrosUtil obtenerSQLConsultaLegajoChequeRechazado(VistaSoporteLegajoChequeRechazadoFiltro filtro) {

		QueryParametrosUtil parametros = new QueryParametrosUtil();
		StringBuilder str = new StringBuilder();
		str.append("select ID_LEGAJO_CHEQUE_RECHAZADO, EMPRESA, SEGMENTO, CLIENTE, CUIT, ID_BANCO_ORIGEN, NUMERO_CHEQUE, ");
		str.append("ESTADO, ID_MOTIVO_LEGAJO, FECHA_ULTIMO_ESTADO, ID_ANALISTA, ID_COPROPIETARIO, ID_ANALISTA_TEAM_COMERCIAL ");
		str.append(", IMPORTE, FECHA_DEPOSITO, FECHA_NOTIFICACION, FECHA_RECHAZO, FECHA_ALTA_LEGAJO, FECHA_VENCIMIENTO, CODIGO_BANCO, DESCRIPCION_BANCO, FECHA_CIERRE, ID_VALOR ");
		str.append("FROM SHV_SOP_LGJ_BUSQUEDA ");
		
		StringBuilder strWhere = new StringBuilder();
		/*   EMPRESA   */
		if (!Validaciones.isNullOrEmpty(filtro.getIdEmpresa())) {
			strWhere.append(obtenerOperadorBusqueda(strWhere)).append(" ID_EMPRESA=? ");
			parametros.addCampoAlQuery(filtro.getIdEmpresa(),Types.VARCHAR);
		}
		/*   SEGMENTO   */
		if (!Validaciones.isNullOrEmpty(filtro.getIdSegmento())) {
			strWhere.append(obtenerOperadorBusqueda(strWhere)).append(" ID_SEGMENTO=? ");
			parametros.addCampoAlQuery(filtro.getIdSegmento(),Types.VARCHAR);
		}
		/* CODIGO DE CLIENTE */
		if (Validaciones.isCollectionNotEmpty(filtro.getIdClientesLegado())) {
			String queryClientes = "  ID_CLIENTE_LEGADO in (";
			String clientes = "";
			for (String idCliente: filtro.getIdClientesLegado()) {
				clientes += "?, ";
				parametros.addCampoAlQuery(Utilidad.removeStartingZeros(idCliente), Types.VARCHAR);
			}
			queryClientes += clientes.substring(0, clientes.length()-2) + ")";
			strWhere.append(obtenerOperadorBusqueda(strWhere)).append(queryClientes);
		}

		/* ESTADO */
		if (!Validaciones.isNullOrEmpty(filtro.getEstado())) {
			strWhere.append(obtenerOperadorBusqueda(strWhere)).append(" ESTADO=? ");
			parametros.addCampoAlQuery(filtro.getEstado(),Types.VARCHAR);
		}

		if (Validaciones.isCollectionNotEmpty(filtro.getEstados())) {
			StringBuilder queryCollection = new StringBuilder("  ESTADO in (");
			for (Estado estado: filtro.getEstados()) {
				queryCollection.append("?, ");
				parametros.addCampoAlQuery(estado.name(), Types.VARCHAR);
			}
			queryCollection.substring(0, queryCollection.toString().length() - 2);
			queryCollection.append(") ");
			strWhere.append(obtenerOperadorBusqueda(strWhere)).append(queryCollection);
		}

		if (Validaciones.isCollectionNotEmpty(filtro.getNotInEstados())) {
			StringBuilder queryCollection = new StringBuilder(" ESTADO NOT in (");
			for (Estado estado: filtro.getNotInEstados()) {
				queryCollection.append("?, ");
				parametros.addCampoAlQuery(estado.name(), Types.VARCHAR);
			}
			queryCollection.delete(queryCollection.toString().length() - 2, queryCollection.toString().length());
			queryCollection.append(") ");
			strWhere.append(obtenerOperadorBusqueda(strWhere)).append(queryCollection);
		}

		if (Validaciones.isCollectionNotEmpty(filtro.getIdValores())) {
			StringBuilder queryCollection = new StringBuilder(" ID_VALOR IN (");
			for (Long idValor : filtro.getIdValores()) {
				queryCollection.append("?, ");
				parametros.addCampoAlQuery(idValor, Types.NUMERIC);
			}
			queryCollection.delete(queryCollection.toString().length() - 2, queryCollection.toString().length());
			queryCollection.append(") ");
			strWhere.append(obtenerOperadorBusqueda(strWhere)).append(queryCollection);
		}
		/* ID LEGAJO */
		if (!Validaciones.isObjectNull(filtro.getIdLegajo())) {
			strWhere.append(obtenerOperadorBusqueda(strWhere)).append(" ID_LEGAJO_CHEQUE_RECHAZADO=? ");
			parametros.addCampoAlQuery(new Long(filtro.getIdLegajo()),Types.NUMERIC);
		}
		/* NUMERO DE CHEQUE */
		if (!Validaciones.isNullOrEmpty(filtro.getNumeroCheque())) {
			strWhere.append(obtenerOperadorBusqueda(strWhere)).append(" NUMERO_CHEQUE=? ");
			parametros.addCampoAlQuery(filtro.getNumeroCheque(),Types.VARCHAR);
		}
		/* ANALISTA */
		if (!Validaciones.isNullOrEmpty(filtro.getAnalista())) {
			strWhere.append(obtenerOperadorBusqueda(strWhere)).append(" ID_ANALISTA=? ");
			parametros.addCampoAlQuery(filtro.getAnalista(),Types.VARCHAR);
		}
		/* RANGO IMPORTE */
		if (!Validaciones.isNullOrEmpty(filtro.getImporteDesde())) {
			strWhere.append(obtenerOperadorBusqueda(strWhere)).append(" IMPORTE>=? " );
			BigDecimal importeDesde = Utilidad.stringToBigDecimal(filtro.getImporteDesde());
			parametros.addCampoAlQuery(importeDesde, Types.DECIMAL);
		}
		if (!Validaciones.isNullOrEmpty(filtro.getImporteHasta())) {
			strWhere.append(obtenerOperadorBusqueda(strWhere)).append(" IMPORTE<=? ");
			BigDecimal importeHasta = Utilidad.stringToBigDecimal(filtro.getImporteHasta());
			parametros.addCampoAlQuery(importeHasta, Types.DECIMAL);
		}
		/* RANGO FECHA ALTA LEGAJO*/
		if (!Validaciones.isNullOrEmpty(filtro.getFechaAltaLegajoDesde())) {
			strWhere.append(obtenerOperadorBusqueda(strWhere)).append(" FECHA_ALTA_LEGAJO>=to_timestamp('");
			strWhere.append(filtro.getFechaAltaLegajoDesde()).append(" 00:00:00','");
			strWhere.append(Utilidad.ORACLE_DATE_TIME_FULL_FORMAT).append("')");
		}
		if (!Validaciones.isNullOrEmpty(filtro.getFechaAltaLegajoHasta())) {
			strWhere.append(obtenerOperadorBusqueda(strWhere)).append(" FECHA_ALTA_LEGAJO<=to_timestamp('");
			strWhere.append(filtro.getFechaAltaLegajoHasta()).append(" 23:59:59','");
			strWhere.append(Utilidad.ORACLE_DATE_TIME_FULL_FORMAT).append("')");
		}
		/* RANGO FECHA ALTA CHEQUE*/
		if (!Validaciones.isNullOrEmpty(filtro.getFechaAltaChequeDesde())) {
			strWhere.append(obtenerOperadorBusqueda(strWhere)).append(" TO_DATE(FECHA_ALTA_CHEQUE,?)>=TO_DATE('");
			strWhere.append(filtro.getFechaAltaChequeDesde()).append(" 00:00:00','");
			strWhere.append(Utilidad.ORACLE_DATE_TIME_FULL_FORMAT).append("')");
			parametros.addCampoAlQuery(Utilidad.ORACLE_DATE_TIME_FULL_FORMAT, Types.VARCHAR);
		}
		if (!Validaciones.isNullOrEmpty(filtro.getFechaAltaChequeHasta())) {
			strWhere.append(obtenerOperadorBusqueda(strWhere)).append(" TO_DATE(FECHA_ALTA_CHEQUE,?)<=TO_DATE('");
			strWhere.append(filtro.getFechaAltaChequeHasta()).append(" 23:59:59','");
			strWhere.append(Utilidad.ORACLE_DATE_TIME_FULL_FORMAT).append("')");
			parametros.addCampoAlQuery(Utilidad.ORACLE_DATE_TIME_FULL_FORMAT, Types.VARCHAR);
		}
		/* FECHA VENCIMIENTO */
		if (!Validaciones.isNullOrEmpty(filtro.getFechaVencimiento())) {
			strWhere.append(obtenerOperadorBusqueda(strWhere)).append(" FECHA_VENCIMIENTO=to_timestamp('");
			strWhere.append(filtro.getFechaVencimiento()).append(" 00:00:00','");
			strWhere.append(Utilidad.ORACLE_DATE_TIME_FULL_FORMAT + "')");
		}
		/* CODIGO BANCO */
		if (Validaciones.isCollectionNotEmpty(filtro.getCodigosBancos())) {
			String queryBancos = "  ID_BANCO_ORIGEN IN (";
			String clientes = "";
			for (String codigo: filtro.getCodigosBancos()) {
				clientes += "?, ";
				parametros.addCampoAlQuery(codigo, Types.VARCHAR);
			}
			queryBancos += clientes.substring(0, clientes.length()-2) + ")";
			strWhere.append(obtenerOperadorBusqueda(strWhere)).append(queryBancos);
		}

		
		if (strWhere.toString().length() != 0) {
			str.append(strWhere);
		}
		
		//El orden por default de los registros de la grilla será por Fecha de alta descendente.
		str.append(" order by FECHA_ALTA_LEGAJO DESC");
		
		parametros.setSql(str.toString());
		return parametros;
	}
	
	public List<Bean> listarLegajosChequeRechazado(VistaSoporteLegajoChequeRechazadoFiltro legajoFiltro) throws PersistenciaExcepcion{

		List<Map<String, Object>> listaResultadoQuery;

		try {
			QueryParametrosUtil qp = obtenerSQLConsultaLegajoChequeRechazado(legajoFiltro);
			listaResultadoQuery = buscarUsandoSQL(qp);
			
			
			List<Bean> resultado = new ArrayList<Bean>();
		
			for (Map<String, Object> archivo : listaResultadoQuery) { // recorro la lista de resultados
				
				VistaSoporteResultadoBusquedaLegajoChequeRechazado reg = new VistaSoporteResultadoBusquedaLegajoChequeRechazado();
				 
				if (!Validaciones.isObjectNull(archivo.get("EMPRESA"))) {
					 reg.setEmpresa(archivo.get("EMPRESA").toString());
				 }
				if (!Validaciones.isObjectNull(archivo.get("SEGMENTO"))) {
					 reg.setSegmento(archivo.get("SEGMENTO").toString());
				 }
				if (!Validaciones.isObjectNull(archivo.get("ID_LEGAJO_CHEQUE_RECHAZADO"))) {
					 reg.setIdLegajo(new Long(archivo.get("ID_LEGAJO_CHEQUE_RECHAZADO").toString()));
				 }
				if (!Validaciones.isObjectNull(archivo.get("CLIENTE"))) {
					 reg.setCliente(archivo.get("CLIENTE").toString());
				 }
				if (!Validaciones.isObjectNull(archivo.get("CUIT"))) {
					 reg.setCuit(archivo.get("CUIT").toString());
				 }
				if (!Validaciones.isObjectNull(archivo.get("DESCRIPCION_BANCO"))) {
					 reg.setBancoOrigen(archivo.get("DESCRIPCION_BANCO").toString());
				 }
				if (!Validaciones.isObjectNull(archivo.get("NUMERO_CHEQUE"))) {
					 reg.setNroCheque(archivo.get("NUMERO_CHEQUE").toString());
				 }
				if (!Validaciones.isObjectNull(archivo.get("ESTADO"))) {
					 reg.setEstado(archivo.get("ESTADO").toString());
				 }
				if (!Validaciones.isObjectNull(archivo.get("ID_MOTIVO_LEGAJO"))) {
					 reg.setMotivo(archivo.get("ID_MOTIVO_LEGAJO").toString());
				 }
				if (!Validaciones.isObjectNull(archivo.get("FECHA_ULTIMO_ESTADO"))) {
					 reg.setFechaUltimoEstado(Utilidad.deserializeAndFormatDate(archivo.get("FECHA_ULTIMO_ESTADO").toString(),Utilidad.DATE_TIME_FULL_FORMAT_BASE_DATOS));
				 }
				if (!Validaciones.isObjectNull(archivo.get("ID_ANALISTA"))) {
					 reg.setAnalista(archivo.get("ID_ANALISTA").toString());
				 }
				if (!Validaciones.isObjectNull(archivo.get("ID_COPROPIETARIO"))) {
					 reg.setCopropietario(archivo.get("ID_COPROPIETARIO").toString());
				 }
				if (!Validaciones.isObjectNull(archivo.get("ID_ANALISTA_TEAM_COMERCIAL"))) {
					 reg.setAnalistaTeamComercial(archivo.get("ID_ANALISTA_TEAM_COMERCIAL").toString());
				 }
				if (!Validaciones.isObjectNull(archivo.get("IMPORTE"))) {
					 reg.setImporte(archivo.get("IMPORTE").toString());
				 }
				if (!Validaciones.isObjectNull(archivo.get("FECHA_ALTA_LEGAJO"))) {
					 reg.setFechaAlta(Utilidad.deserializeAndFormatDate(archivo.get("FECHA_ALTA_LEGAJO").toString(),Utilidad.DATE_TIME_FULL_FORMAT_BASE_DATOS));
				 }
				if (!Validaciones.isObjectNull(archivo.get("FECHA_NOTIFICACION"))) {
					 reg.setFechaNotificacion(Utilidad.deserializeAndFormatDate(archivo.get("FECHA_NOTIFICACION").toString(),Utilidad.DATE_TIME_FULL_FORMAT_BASE_DATOS));
				 }
				if (!Validaciones.isObjectNull(archivo.get("FECHA_CIERRE"))) {
					 reg.setFechaCierre(Utilidad.deserializeAndFormatDate(archivo.get("FECHA_CIERRE").toString(),Utilidad.DATE_TIME_FULL_FORMAT_BASE_DATOS));
				}
				if (!Validaciones.isObjectNull(archivo.get("FECHA_RECHAZO"))) {
					 reg.setFechaRechazo((Date) archivo.get("FECHA_RECHAZO"));
				}
				if (!Validaciones.isObjectNull(archivo.get("ID_VALOR"))) {
					 reg.setIdValor(Long.parseLong(archivo.get("ID_VALOR").toString()));
				}
				
				resultado.add(reg);
			}
			return resultado;
		} catch (Exception e) {
			throw new PersistenciaExcepcion(e.getMessage(), e);
		}
	}

	
	/**
	 * 
	 * @param filtro
	 * @return
	 */
	private QueryParametrosUtil obtenerSQLConsultaLegajoChequeRechazadoCobrosRelacionados(VistaSoporteLegajoChequeRechazadoCobroRelacionadoFiltro filtro) {
		
		QueryParametrosUtil parametros = new QueryParametrosUtil();
		String str = "select ID_LEGAJO_CHEQUE_RECHAZADO, ESTADO, SISTEMA_IMPUTACION, ID_OPERACION, TIPO_DOCUMENTO, NUMERO_LEGAL_DOCUMENTO, NUMERO_REFERENCIA_DOCUMENTO"+
					",CONVENIO_FINANCIACION, CUOTA_FINANCIACION, CLIENTE_LEGADO_DOCUMENTO, IMPORTE_TOTAL_DOCUMENTO, IMPORTE_TOTAL_CHEQUE, IMPORTE_TOTAL_EFECTIVO,"
					+ " IMPORTE_TOTAL_RETENCIONES, IMPORTE_TOTAL_BONOS,"+
					"IMPORTE_TOTAL_OTRAS_MONEDAS, FECHA_IMPUTACION, ID_REVERSION, ANALISTA, COPROPIETARIO, ANALISTA_TEAM_COMERCIAL, ID_CHEQUE_RECHAZADO_COBRO " +
					", FECHA_REVERSION, NOMBRE_ARCHIVO_REVERSION, ESTADO_REVERSION_SHIVA, ESTADO_REVERSION_ICE, SISTEMA_ORIGEN, ID_OPERACION_REVERSION " +
					"FROM SHV_SOP_LGJ_COBRO_RELACIONADO";
		
		String strWhere = "";
		/* ID LEGAJO */
		if (!Validaciones.isObjectNull(filtro.getIdLegajoChequeRechazado())) {
			strWhere += obtenerOperadorBusqueda(strWhere) + " ID_LEGAJO_CHEQUE_RECHAZADO=? ";
			parametros.addCampoAlQuery(filtro.getIdLegajoChequeRechazado(),Types.NUMERIC);
		}
		/* ESTADO */
		if (!Validaciones.isObjectNull(filtro.getEstado())) {
			strWhere += obtenerOperadorBusqueda(strWhere) + " ESTADO=? ";
			parametros.addCampoAlQuery(filtro.getEstado().name(),Types.VARCHAR);
		}
		
		if(!Validaciones.isNullOrEmpty(strWhere)){
			str += strWhere;
		}
		
		//El orden por default de los registros de la grilla será por Fecha de alta descendente.
		str += " order by FECHA_IMPUTACION DESC";
		
		parametros.setSql(str);
		return parametros;
	}
	
	/**
	 * 
	 */
	@Override
	public List<VistaSoporteResultadoBusquedaLegajoCobrosRelacionado> listarCobrosRelacionados(VistaSoporteLegajoChequeRechazadoCobroRelacionadoFiltro filtro) throws PersistenciaExcepcion {
		List<Map<String, Object>> listaResultadoQuery;

		try {
			QueryParametrosUtil qp = obtenerSQLConsultaLegajoChequeRechazadoCobrosRelacionados(filtro);
			listaResultadoQuery = buscarUsandoSQL(qp);
			
			
			List<VistaSoporteResultadoBusquedaLegajoCobrosRelacionado> resultado = new ArrayList<VistaSoporteResultadoBusquedaLegajoCobrosRelacionado>();
		
			for (Map<String, Object> archivo : listaResultadoQuery) { // recorro la lista de resultados
				
				VistaSoporteResultadoBusquedaLegajoCobrosRelacionado reg = new VistaSoporteResultadoBusquedaLegajoCobrosRelacionado();
				if (!Validaciones.isObjectNull(archivo.get("SISTEMA_IMPUTACION"))) {
					 reg.setSistema(archivo.get("SISTEMA_IMPUTACION").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("ID_OPERACION"))) {
					reg.setIdOperacion(archivo.get("ID_OPERACION").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("TIPO_DOCUMENTO"))) {
					reg.setTipoDocumento(archivo.get("TIPO_DOCUMENTO").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("NUMERO_LEGAL_DOCUMENTO"))) {
					reg.setNumeroLegal(archivo.get("NUMERO_LEGAL_DOCUMENTO").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("NUMERO_REFERENCIA_DOCUMENTO"))) {
					reg.setNumeroReferencia(archivo.get("NUMERO_REFERENCIA_DOCUMENTO").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("CONVENIO_FINANCIACION"))) {
					reg.setConvenioFinanciacion(archivo.get("CONVENIO_FINANCIACION").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("CUOTA_FINANCIACION"))) {
					reg.setCuotaFinanciacion(archivo.get("CUOTA_FINANCIACION").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("CLIENTE_LEGADO_DOCUMENTO"))) {
					reg.setClienteLegado(archivo.get("CLIENTE_LEGADO_DOCUMENTO").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("IMPORTE_TOTAL_DOCUMENTO"))) {
					reg.setImporteTotalDocumento(archivo.get("IMPORTE_TOTAL_DOCUMENTO").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("IMPORTE_TOTAL_CHEQUE"))) {
					reg.setImporteTotalCheque(archivo.get("IMPORTE_TOTAL_CHEQUE").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("IMPORTE_TOTAL_EFECTIVO"))) {
					reg.setImporteTotalEfectivo(archivo.get("IMPORTE_TOTAL_EFECTIVO").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("IMPORTE_TOTAL_RETENCIONES"))) {
					reg.setImporteTotalRetenciones(archivo.get("IMPORTE_TOTAL_RETENCIONES").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("IMPORTE_TOTAL_BONOS"))) {
					reg.setImporteTotalBonos(archivo.get("IMPORTE_TOTAL_BONOS").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("IMPORTE_TOTAL_OTRAS_MONEDAS"))) {
					reg.setImporteTotalOtrasMonedas(archivo.get("IMPORTE_TOTAL_OTRAS_MONEDAS").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("FECHA_IMPUTACION"))) {
					reg.setFechaImputacion(Utilidad.deserializeAndFormatDate(archivo.get("FECHA_IMPUTACION").toString(),Utilidad.DATE_TIME_FULL_FORMAT_BASE_DATOS));
				}
				if (!Validaciones.isObjectNull(archivo.get("ID_REVERSION"))) {
					reg.setIdDescobro(archivo.get("ID_REVERSION").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("ANALISTA"))) {
					reg.setAnalista(archivo.get("ANALISTA").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("COPROPIETARIO"))) {
					reg.setCopropietario(archivo.get("COPROPIETARIO").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("ANALISTA_TEAM_COMERCIAL"))) {
					reg.setIdAnalistaTeamComercial(archivo.get("ANALISTA_TEAM_COMERCIAL").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("ID_CHEQUE_RECHAZADO_COBRO"))) {
					reg.setIdChequeRechazadoCobro(archivo.get("ID_CHEQUE_RECHAZADO_COBRO").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("ID_CHEQUE_RECHAZADO_COBRO"))) {
					reg.setIdChequeRechazadoCobro(archivo.get("ID_CHEQUE_RECHAZADO_COBRO").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("FECHA_REVERSION"))) {
					reg.setFechaReversion(Utilidad.deserializeAndFormatDate(archivo.get("FECHA_REVERSION").toString(),Utilidad.DATE_TIME_FULL_FORMAT_BASE_DATOS));
				}
				if (!Validaciones.isObjectNull(archivo.get("NOMBRE_ARCHIVO_REVERSION"))) {
					reg.setNombreArchivoReversion(archivo.get("NOMBRE_ARCHIVO_REVERSION").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("ESTADO_REVERSION_SHIVA"))) {
					reg.setEstadoReversionShiva(archivo.get("ESTADO_REVERSION_SHIVA").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("ESTADO_REVERSION_ICE"))) {
					reg.setEstadoReversionIce(archivo.get("ESTADO_REVERSION_ICE").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("SISTEMA_ORIGEN"))) {
					reg.setSistemaOrigen(SistemaEnum.getEnumByName(archivo.get("SISTEMA_ORIGEN").toString()));
				}
				if (!Validaciones.isObjectNull(archivo.get("ID_OPERACION_REVERSION"))) {
					reg.setIdOperacionDescobro(archivo.get("ID_OPERACION_REVERSION").toString());
				}
				resultado.add(reg);
			}
			return resultado;
		} catch (Exception e) {
			throw new PersistenciaExcepcion(e.getMessage(), e);
		}
	}

	@Override
	public VistaSoporteResultadoBusquedaLegajoCobrosRelacionadoSimplificado obtenerLegajoChequeRechazadoCobrosRelacionadosEstadoReversionShivaBy(Long idOperacion) throws NegocioExcepcion {
		QueryParametrosUtil parametros = new QueryParametrosUtil();
		StringBuilder str = new StringBuilder();
		List<Map<String, Object>> listaResultadoQuery;
		VistaSoporteResultadoBusquedaLegajoCobrosRelacionadoSimplificado reg = null;
	
		str.append("select ESTADO_REVERSION_SHIVA, ID_OPERACION, ID_OPERACION_REVERSION ");
		str.append("FROM SHV_SOP_LGJ_COBRO_RELACIONADO where id_operacion = ? GROUP BY ESTADO_REVERSION_SHIVA, ID_OPERACION, ID_OPERACION_REVERSION");

		parametros.addCampoAlQuery(idOperacion, Types.NUMERIC);
		parametros.setSql(str.toString());
		try {
			listaResultadoQuery = buscarUsandoSQL(parametros);
		} catch (PersistenciaExcepcion e) {
			Traza.error(getClass(), e.getMessage());
			throw new NegocioExcepcion(e.getMessage(), e);
		}

		if (listaResultadoQuery.size() > 1) {
			Traza.error(getClass(), "Error hay mas una reversion por cobro activa");
			throw new NegocioExcepcion("Error hay mas una reversion por cobro activa");
		}

		for (Map<String, Object> archivo : listaResultadoQuery) { // recorro la lista de resultados
			reg = new VistaSoporteResultadoBusquedaLegajoCobrosRelacionadoSimplificado();

			
			if (!Validaciones.isObjectNull(archivo.get("ID_OPERACION_REVERSION"))) {
				reg.setIdOperacionDescobro(archivo.get("ID_OPERACION_REVERSION").toString());
			}
			
			if (!Validaciones.isObjectNull(archivo.get("ESTADO_REVERSION_SHIVA"))) {
				reg.setEstadoReversionShiva(archivo.get("ESTADO_REVERSION_SHIVA").toString());
			}
			if (!Validaciones.isObjectNull(archivo.get("ID_OPERACION"))) {
				reg.setIdOperacion(archivo.get("ID_OPERACION").toString());
			}

		}

		return reg;
	}
	
	/**
	 * Metodo que arma la query a lanzar en el metodo listarChequeRechazadoDetalleDocumento
	 * @param filtro
	 * @return
	 */
	private QueryParametrosUtil obtenerSQLConsultaChequeRechazadoDetalleDocumento(Long idLegajoChequeRechazado) {
		
		QueryParametrosUtil parametros = new QueryParametrosUtil();
		StringBuilder str = new StringBuilder("SELECT ");
					str.append("VISTA.ID_LEGAJO_CHEQUE_RECHAZADO AS ID_LEGAJO_CHEQUE_RECHAZADO ");
					str.append(",VISTA.ID_CHEQUE_RECHAZADO AS ID_CHEQUE_RECHAZADO ");
					str.append(",VISTA.SISTEMA_ORIGEN AS SISTEMA_ORIGEN ");
					str.append(",VISTA.SISTEMA_IMPUTACION AS SISTEMA_IMPUTACION ");
					str.append(",VISTA.TIPO_DOCUMENTO AS TIPO_DOCUMENTO ");
					str.append(",VISTA.NUMERO_LEGAL_DOCUMENTO AS NUMERO_LEGAL ");
					str.append(",VISTA.NUMERO_REFERENCIA_DOCUMENTO AS NUMERO_REFERENCIA_MIC ");
					str.append(",VISTA.CLIENTE_LEGADO_DOCUMENTO AS ID_CLIENTE_LEGADO ");
					str.append(",VISTA.IMPORTE_TOTAL_DOCUMENTO AS IMPORTE_TOTAL_DOCUMENTO ");
					str.append(",VISTA.SALDO_DOCU_LUEGO_APLICA AS SALDO_DOCU_LUEGO_APLICA ");
					str.append(",VISTA.IMPORTE_APLICADO_CHEQUE AS IMPORTE_APLICADO_CHEQUE ");
					str.append(",DOCUM.SALDO_TOTAL_DOCUMENTO AS SALDO_TOTAL_DOCUMENTO ");
					str.append(",DOCUM.FECHA_ACTUALIZACION AS FECHA_ACTUALIZACION ");
					str.append(",VISTA.MONEDA_DOCUMENTO AS MONEDA ");
					str.append(",VISTA.MONEDA_DOLAR AS MONEDA_DOLAR ");
					str.append(",VISTA.IMPORTE_DOLAR AS IMPORTE_DOLAR ");
					str.append("FROM SHV_SOP_LGJ_COBRO_RELACIONADO VISTA ");
					str.append("LEFT JOIN SHV_LGJ_CHEQUE_RECHA_DET_DOCUM DOCUM ON ");
					str.append("(VISTA.ID_CHEQUE_RECHAZADO = DOCUM.ID_CHEQUE_RECHAZADO AND ");
					str.append("(VISTA.NUMERO_REFERENCIA_DOCUMENTO=DOCUM.NUMERO_REFERENCIA_MIC OR ");
					str.append("DOCUM.NUMERO_LEGAL=VISTA.NUMERO_LEGAL_DOCUMENTO)) ");
					str.append("WHERE VISTA.ID_LEGAJO_CHEQUE_RECHAZADO=? ");
					str.append("ORDER BY VISTA.SISTEMA_ORIGEN, VISTA.TIPO_DOCUMENTO, VISTA.NUMERO_LEGAL_DOCUMENTO, VISTA.NUMERO_REFERENCIA_DOCUMENTO ");

		/* ID LEGAJO */
		parametros.addCampoAlQuery(idLegajoChequeRechazado,Types.NUMERIC);
		
		parametros.setSql(str.toString());
		return parametros;
	}
	
	/**
	 * Metodo que lista los registros de la tabla SHV_LGJ_CHEQUE_RECHA_DET_DOCUM con la vista SHV_SOP_LGJ_COBRO_RELACIONADO.
	 * los mismos se usan para la solapa de reembolso.
	 */
	@Override
	public List<VistaSoporteResultadoBusquedaLegajoDetalleDocumento> listarChequeRechazadoDetalleDocumento(Long idLegajoChequeRechazado)throws PersistenciaExcepcion {
		List<Map<String, Object>> listaResultadoQuery;

		try {
			QueryParametrosUtil qp = obtenerSQLConsultaChequeRechazadoDetalleDocumento(idLegajoChequeRechazado);
			listaResultadoQuery = buscarUsandoSQL(qp);
			
			
			List<VistaSoporteResultadoBusquedaLegajoDetalleDocumento> resultado = new ArrayList<VistaSoporteResultadoBusquedaLegajoDetalleDocumento>();
		
			for (Map<String, Object> archivo : listaResultadoQuery) { // recorro la lista de resultados
				
				VistaSoporteResultadoBusquedaLegajoDetalleDocumento reg = new VistaSoporteResultadoBusquedaLegajoDetalleDocumento();
				
				if (!Validaciones.isObjectNull(archivo.get("ID_CHEQUE_RECHAZADO"))) {
					 reg.setIdChequeRechazado(Long.parseLong(archivo.get("ID_CHEQUE_RECHAZADO").toString()));
				}

				if (!Validaciones.isObjectNull(archivo.get("sistema_origen"))) {
					reg.setSistemaOrigen(SistemaEnum.getEnumByName(archivo.get("sistema_origen").toString()));
				}

				if (SistemaEnum.ICE.equals(reg.getSistemaOrigen())) {
					if (!Validaciones.isObjectNull(archivo.get("sistema_imputacion"))) {
						TipoFacturacionIceEnum tipoFacturacionIceEnum = TipoFacturacionIceEnum.getEnumByCodigo(archivo.get("sistema_imputacion").toString());
						if (!Validaciones.isObjectNull(tipoFacturacionIceEnum)) {
							reg.setSistemaImputacion(SistemaEnum.getEnumByName(tipoFacturacionIceEnum.sistema().toUpperCase()));
						}
					}
				} else {
					if (!Validaciones.isObjectNull(archivo.get("sistema_imputacion"))) {
						reg.setSistemaImputacion(SistemaEnum.getEnumByName(archivo.get("sistema_imputacion").toString()));
					}
				}
			
				if (!Validaciones.isObjectNull(archivo.get("TIPO_DOCUMENTO"))) {
					String tipoDocumento = archivo.get("TIPO_DOCUMENTO").toString();
					TipoComprobanteEnum tipoComprobante = TipoComprobanteEnum.getEnumByName(tipoDocumento);
					if (
						!Validaciones.isObjectNull(tipoComprobante)
					) {
						reg.setTipoComprobante(Utilidad.capitalizarCadena(tipoComprobante.getDescripcion()));
					} else {
						// DUC y RAPIDUC Viene en Mayusculas
						reg.setTipoComprobante(Utilidad.capitalizarCadena(tipoDocumento));
					}
					
				}
				
				if (!Validaciones.isObjectNull(archivo.get("NUMERO_REFERENCIA_MIC"))) {
					reg.setNumeroReferenciaMic(archivo.get("NUMERO_REFERENCIA_MIC").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("ID_CLIENTE_LEGADO"))) {
					String str = archivo.get("ID_CLIENTE_LEGADO").toString();
					String [] lista = str.split(" ");
					if(lista.length>0){
						if(Validaciones.isNumeric(lista[0])){
							reg.setIdClienteLegado(Long.parseLong(lista[0]));
						}
					}
				}
				if (!Validaciones.isObjectNull(archivo.get("NUMERO_LEGAL"))) {
					reg.setNumeroLegal(archivo.get("NUMERO_LEGAL").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("MONEDA_DOLAR"))) {
					reg.setMonedaDocumento(MonedaEnum.getEnumByName(archivo.get("MONEDA_DOLAR").toString()));
				} else {
					if (!Validaciones.isObjectNull(archivo.get("MONEDA"))) {
						reg.setMonedaDocumento(MonedaEnum.getEnumByName(archivo.get("MONEDA").toString()));
					}
				}
				if (!Validaciones.isObjectNull(archivo.get("MONEDA_DOLAR")) && MonedaEnum.DOL.equals(MonedaEnum.getEnumByName(archivo.get("MONEDA_DOLAR").toString()))) {
					reg.setImporteTotalDocumento((BigDecimal)(archivo.get("IMPORTE_DOLAR")));
				} else {
					if (!Validaciones.isObjectNull(archivo.get("IMPORTE_TOTAL_DOCUMENTO"))) {
						reg.setImporteTotalDocumento((BigDecimal)(archivo.get("IMPORTE_TOTAL_DOCUMENTO")));
					}
				}
				if (!Validaciones.isObjectNull(archivo.get("SALDO_DOCU_LUEGO_APLICA"))) {
					reg.setSaldoDocumentoLuegoAplicarCheque((BigDecimal)(archivo.get("SALDO_DOCU_LUEGO_APLICA")));
				}
				if (!Validaciones.isObjectNull(archivo.get("IMPORTE_APLICADO_CHEQUE"))) {
					reg.setImporteAplicadoDelCheque((BigDecimal)(archivo.get("IMPORTE_APLICADO_CHEQUE")));
				}
				if (!Validaciones.isObjectNull(archivo.get("SALDO_TOTAL_DOCUMENTO"))) {
					reg.setSaldoActualDocumento((BigDecimal)(archivo.get("SALDO_TOTAL_DOCUMENTO")));
				}
				if (!Validaciones.isObjectNull(archivo.get("FECHA_ACTUALIZACION"))) {
					reg.setFechaConsultaSaldo(Utilidad.deserializeAndFormatDate(archivo.get("FECHA_ACTUALIZACION").toString(),Utilidad.DATE_TIME_FULL_FORMAT_BASE_DATOS));
				}
				resultado.add(reg);
			}
			return resultado;
		} catch (Exception e) {
			throw new PersistenciaExcepcion(e.getMessage(), e);
		}
	}
	

	@Override
	public List<VistaSoporteResultadoBusquedaLegajoDetalleDocumento> obtenerChequeRechazadoDetalleDocumentoBy(Long idLegajo) throws NegocioExcepcion {
		QueryParametrosUtil parametros = new QueryParametrosUtil();
		StringBuffer str = new StringBuffer();
		List<Map<String, Object>> listaResultadoQuery;
		VistaSoporteResultadoBusquedaLegajoDetalleDocumento reg = null;
		List<VistaSoporteResultadoBusquedaLegajoDetalleDocumento> lista = new ArrayList<VistaSoporteResultadoBusquedaLegajoDetalleDocumento>();
		
		
		legajoChequeRechazadoRepositorio.flush();
		str.append("select cobro.sistema_imputacion, cobro.sistema_origen, cobro.TIPO_DOCUMENTO, cobro.numero_legal_documento, ");
		str.append("cobro.numero_referencia_documento, cobro.cliente_legado_documento, moneda_documento ");
		str.append("FROM SHV_SOP_LGJ_COBRO_RELACIONADO cobro ");
		str.append("where cobro.id_legajo_cheque_rechazado = ? AND cobro.sistema_imputacion IN ('CALIPSO', 'MIC', '00', '97', '77', '96') and cobro.TIPO_DOCUMENTO != 'DUC' ");
		str.append("GROUP BY cobro.sistema_imputacion, cobro.sistema_origen, cobro.TIPO_DOCUMENTO, cobro.numero_legal_documento, cobro.numero_referencia_documento, ");
		str.append("cobro.cliente_legado_documento, moneda_documento ");

		// Donde cobro.sistema_imputacion 
//		FACTURACION_GENESIS ("00","Facturación Génesis", "MIC"),
//		FACTURACION_PERSONAL_TRAFICO ("16","Facturación Personal Tráfico" , "Negocio"),
//		RAPIDUCS_GENESIS ("75","RAPIDUCs Génesis", "MIC"),
//		DUCS_GENESIS ("76","DUCs Génesis", "MIC"),
//		FACTURACION_PERSEUS ("77","Facturación Perseus", "Calipso"),
//		MAYORISTAS_CALIPSO ("96","Mayoristas Calipso", "Calipso"),
//		DATOS_CALIPSO ("97","Datos Calipso", "Calipso"),
//		CUOTAS_DEIMOS ("98","Cuotas DEIMOs", "DEIMOs");
		
		parametros.addCampoAlQuery(idLegajo, Types.NUMERIC);
		parametros.setSql(str.toString());

		try {
			listaResultadoQuery = buscarUsandoSQL(parametros);
		} catch (PersistenciaExcepcion e) {
			Traza.error(getClass(), e.getMessage());
			throw new NegocioExcepcion(e.getMessage(), e);
		}

		if (listaResultadoQuery.isEmpty()) {
			Traza.error(getClass(), "Error al recuperar documentos relacionados");
			return lista;
		}

		for (Map<String, Object> archivo : listaResultadoQuery) { // recorro la lista de resultados
			reg = new VistaSoporteResultadoBusquedaLegajoDetalleDocumento();

			if (!Validaciones.isObjectNull(archivo.get("sistema_origen"))) {
				reg.setSistemaOrigen(SistemaEnum.getEnumByName(archivo.get("sistema_origen").toString()));
			}
			if (SistemaEnum.ICE.equals(reg.getSistemaOrigen())) {
				if (!Validaciones.isObjectNull(archivo.get("sistema_imputacion"))) {
					TipoFacturacionIceEnum tipoFacturacionIceEnum = TipoFacturacionIceEnum.getEnumByCodigo(archivo.get("sistema_imputacion").toString());
					if (!Validaciones.isObjectNull(tipoFacturacionIceEnum)) {
						reg.setSistemaImputacion(SistemaEnum.getEnumByName(tipoFacturacionIceEnum.sistema().toUpperCase()));
					}
				}
			} else {
				if (!Validaciones.isObjectNull(archivo.get("sistema_imputacion"))) {
					reg.setSistemaImputacion(SistemaEnum.getEnumByName(archivo.get("sistema_imputacion").toString()));
				}
			}

			if (!Validaciones.isObjectNull(archivo.get("TIPO_DOCUMENTO"))) {
				String tipoDocumento = archivo.get("TIPO_DOCUMENTO").toString();
				if (
					TipoComprobanteEnum.FAC.name().equals(tipoDocumento) ||
					TipoComprobanteEnum.DEB.name().equals(tipoDocumento)
				) {
					reg.setTipoComprobante(TipoComprobanteEnum.getEnumByName(tipoDocumento).getDescripcion());
				} else {
					reg.setTipoComprobante(Utilidad.capitalizarCadena(tipoDocumento));
				}
			}
			
			// cliente_legado_documento es un cadena de String [idClienteLegado] - [RazonSocila]
			// Cliente legados asi que tomo el primero para buscar en los cobrodores cuando se guarda SHV_LGJ_CHEQUE_RECHA_DET_COBRO.
			//	Puede que el cliente sea 0 o sea que no hay clientes
			if (!Validaciones.isObjectNull(archivo.get("cliente_legado_documento"))) {
				String clienteVector[] = archivo.get("cliente_legado_documento").toString().split(" ");
				if (clienteVector.length > 0) {
					if (Validaciones.isNumeric(clienteVector[0])) {
						Long numero = Long.parseLong(clienteVector[0]);
						if (numero > 0) {
							reg.setIdClienteLegado(numero);
						}
					}
				}
			}
			
			if (!Validaciones.isObjectNull(archivo.get("numero_legal_documento"))) {
				reg.setNumeroLegal(archivo.get("numero_legal_documento").toString());
			}

			if (!Validaciones.isObjectNull(archivo.get("numero_referencia_documento"))) {
				reg.setNumeroReferenciaMic(archivo.get("numero_referencia_documento").toString());
			}

			if (!Validaciones.isObjectNull(archivo.get("moneda_documento"))) {
				reg.setMonedaDocumento(MonedaEnum.getEnumByName(archivo.get("moneda_documento").toString()));
			}
			lista.add(reg);
		}

		return lista;
	}
	
	/**
	 * 
	 * @param filtro
	 * @return
	 */
	private QueryParametrosUtil obtenerSQLConsultaMotorConciliacion() {
	
		StringBuffer consulta = new StringBuffer();
		QueryParametrosUtil parametros = new QueryParametrosUtil();
//		consulta.append(" SELECT * FROM SHV_SOP_MOTOR_CONCILIACION ");
////		consulta.append(" where fechaModificacion>to_timestamp(?,'dd/mm/yyyy hh24:mi:ss')");
//		consulta.append(" ORDER BY REGLA");
		

		consulta.append(" SELECT * ");
		consulta.append("  FROM shv_sop_motor_conciliacion ");
		consulta.append(" where regla  =  (SELECT min(regla) FROM shv_sop_motor_conciliacion) ");
		
//		GregorianCalendar fechaPrimerDiaMesAnterior = new GregorianCalendar(); 
//		fechaPrimerDiaMesAnterior.add(GregorianCalendar.MONTH, -1);
//		fechaPrimerDiaMesAnterior.set(fechaPrimerDiaMesAnterior.get(GregorianCalendar.YEAR), fechaPrimerDiaMesAnterior.get(GregorianCalendar.MONTH), 1);
//		qp.addParametro(Utilidad.formatDatePicker(fechaPrimerDiaMesAnterior.getTime())+" 00:00:00");
		
		parametros.setSql(consulta.toString());
		return parametros;
	}	
	
	/**
	 * @author fabio.giaquinta.ruiz
	 * @throws PersistenciaExcepcion 
	 */
	@Override
	public List<VistaSoporteMotorConciliacion> listarRegistrosMotorConciliacion() throws PersistenciaExcepcion {
		
		List<Map<String, Object>> listaResultadoQuery;

		try {
			QueryParametrosUtil qp = obtenerSQLConsultaMotorConciliacion();
			listaResultadoQuery = buscarUsandoSQL(qp);
	
			List<VistaSoporteMotorConciliacion> resultado = mapearVistaSopporteMotorConciliacion(listaResultadoQuery);
			return resultado;
		} catch (Exception e) {
			throw new PersistenciaExcepcion(e.getMessage(), e);
		}

	}

	/**
	 * 
	 * @param filtro
	 * @return
	 */
	public List<VistaSoporteMotorConciliacion> listarRegistrosMotorConciliacionPorReglaMenor() throws PersistenciaExcepcion {
	
		List<Map<String, Object>> listaResultadoQuery;

		try {
			StringBuffer consulta = new StringBuffer();
			QueryParametrosUtil qp = new QueryParametrosUtil();

			consulta.append(" SELECT * ");
			consulta.append("  FROM shv_sop_motor_conciliacion ");
			consulta.append(" where regla  =  (SELECT min(regla) FROM shv_sop_motor_conciliacion) ");
			
			qp.setSql(consulta.toString());
			listaResultadoQuery = buscarUsandoSQL(qp);
	
			List<VistaSoporteMotorConciliacion> resultado = mapearVistaSopporteMotorConciliacion(listaResultadoQuery);
			return resultado;
		} catch (Exception e) {
			throw new PersistenciaExcepcion(e.getMessage(), e);
		}
	}	

	private List<VistaSoporteMotorConciliacion> mapearVistaSopporteMotorConciliacion(List<Map<String, Object>> listaResultadoQuery) throws PersistenciaExcepcion {
		List<VistaSoporteMotorConciliacion> resultado = new ArrayList<VistaSoporteMotorConciliacion>();

		for (Map<String, Object> archivo : listaResultadoQuery) {
			
			VistaSoporteMotorConciliacion reg = new VistaSoporteMotorConciliacion();
			
			try {
				if (!Validaciones.isObjectNull(archivo.get("REGLA"))) {
					reg.setRegla(archivo.get("REGLA").toString());
			 	}
				if (!Validaciones.isObjectNull(archivo.get("TIPO"))) {
					reg.setTipo(archivo.get("TIPO").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("ID_REGISTRO_AVC"))) {
					reg.setRavcIdRegistroAvc(Long.parseLong(archivo.get("ID_REGISTRO_AVC").toString()));
				}
				if (!Validaciones.isObjectNull(archivo.get("ID_ARCHIVO_AVC"))) {
					reg.setRavcIdArchivoAvc(Long.parseLong(archivo.get("ID_ARCHIVO_AVC").toString()));
				}
				if (!Validaciones.isObjectNull(archivo.get("RAVC_ID_WORKFLOW"))) {
					reg.setRavcIdWorkflow(Long.parseLong(archivo.get("RAVC_ID_WORKFLOW").toString()));
				}
				if (!Validaciones.isObjectNull(archivo.get("RAVC_FECHA_ULTIMA_MODIFI"))) {
					reg.setRavcFechaUltimaModificacion((Date)(archivo.get("RAVC_FECHA_ULTIMA_MODIFI")));
				}
				if (!Validaciones.isObjectNull(archivo.get("RAVC_ESTADO"))) {
					reg.setRavcEstadoWorkflow(archivo.get("RAVC_ESTADO").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("RAVC_IMPORTE"))) {
					reg.setRavcImporte((BigDecimal)archivo.get("RAVC_IMPORTE"));
				}
				if (!Validaciones.isObjectNull(archivo.get("RAVC_ID_ACUERDO"))) {
					reg.setRavcIdAcuerdo(archivo.get("RAVC_ID_ACUERDO").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("RAVC_CODIGO_CLIENTE"))) {
					reg.setRavcCodigoCliente(archivo.get("RAVC_CODIGO_CLIENTE").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("RAVC_TIPO_VALOR"))) {
					reg.setRavcTipoValor(archivo.get("RAVC_TIPO_VALOR").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("RAVC_NUMERO_BOLETA"))) {
					reg.setRavcNumeroBoleta(archivo.get("RAVC_NUMERO_BOLETA").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("RAVC_NUMERO_CHEQUE"))) {
					reg.setRavcNumeroCheque(archivo.get("RAVC_NUMERO_CHEQUE").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("RAVC_FECHA_DE_PAGO"))) {
					reg.setRavcFechaDePago((Date)(archivo.get("RAVC_FECHA_DE_PAGO")));
				}
				if (!Validaciones.isObjectNull(archivo.get("RAVC_CODIGO_BANCO"))) {
					reg.setRavcCodigoBanco(archivo.get("RAVC_CODIGO_BANCO").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("RAVC_FECHA_VENCIMIENTO"))) {
					reg.setRavcFechaVencimiento((Date)(archivo.get("RAVC_FECHA_VENCIMIENTO")));
				}
				if (!Validaciones.isObjectNull(archivo.get("ID_BOLETA"))) {
					reg.setBolIdBoleta(Long.parseLong(archivo.get("ID_BOLETA").toString()));
				}
				if (!Validaciones.isObjectNull(archivo.get("BOLETA_ID_WORKFLOW"))) {
					reg.setBolIdWorkflow(Long.parseLong(archivo.get("BOLETA_ID_WORKFLOW").toString()));
				}
				if (!Validaciones.isObjectNull(archivo.get("BOLETA_ESTADO"))) {
					reg.setBoletaEstadoWorkflow(archivo.get("BOLETA_ESTADO").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("BOLETA_FECHA_ULTIMA_MODIFI"))) {
					reg.setBoletaFechaUltimaModificacion((Date)(archivo.get("BOLETA_FECHA_ULTIMA_MODIFI")));
				}
				if (!Validaciones.isObjectNull(archivo.get("BOLETA_NUMERO_BOLETA"))) {
					reg.setBolNumeroBoleta(archivo.get("BOLETA_NUMERO_BOLETA").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("ID_VALOR"))) {
					reg.setValIdValor(Long.parseLong(archivo.get("ID_VALOR").toString()));
				}
				if (!Validaciones.isObjectNull(archivo.get("VALOR_ID_WORKFLOW"))) {
					reg.setValIdWorkflow(Long.parseLong(archivo.get("VALOR_ID_WORKFLOW").toString()));
				}
				if (!Validaciones.isObjectNull(archivo.get("VALOR_ESTADO"))) {
					reg.setValorEstadoWorkflow(archivo.get("VALOR_ESTADO").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("VALOR_CLIENTE_LEGADO"))) {
					reg.setValClienteLegado(archivo.get("VALOR_CLIENTE_LEGADO").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("VALOR_TIPO_VALOR"))) {
					reg.setValTipoValor(archivo.get("VALOR_TIPO_VALOR").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("VALOR_ACUERDO"))) {
					reg.setValAcuerdo(archivo.get("VALOR_ACUERDO").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("VALOR_IMPORTE"))) {
					reg.setValImporte((BigDecimal)archivo.get("VALOR_IMPORTE"));
				}
				if (!Validaciones.isObjectNull(archivo.get("VALOR_NUMERO_CHEQUE"))) {
					reg.setValNumeroCheque(archivo.get("VALOR_NUMERO_CHEQUE").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("VALOR_ID_EMPRESA"))) {
					reg.setValIdEmpresa(archivo.get("VALOR_ID_EMPRESA").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("VALOR_ID_SEGMENTO"))) {
					reg.setValIdSegmento(archivo.get("VALOR_ID_SEGMENTO").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("VALOR_CODIGO_CLIENTE_AGRUPADOR"))) {
					reg.setValCodigoClienteAgrupador(archivo.get("VALOR_CODIGO_CLIENTE_AGRUPADOR").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("VALOR_RAZON_SOCIAL_CLI_AGRUP"))) {
					reg.setValRazonSocialCliAgrup(archivo.get("VALOR_RAZON_SOCIAL_CLI_AGRUP").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("VALOR_ID_ANALISTA"))) {
					reg.setValIdAnalista(archivo.get("VALOR_ID_ANALISTA").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("VALOR_ID_COPROPIETARIO"))) {
					reg.setValIdCopropietario(archivo.get("VALOR_ID_COPROPIETARIO").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("VALOR_ID_MOTIVO"))) {
					reg.setValIdMotivo(archivo.get("VALOR_ID_MOTIVO").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("VALOR_OPERACION_ASOCIADA"))) {
					reg.setValOperacionAsociada(archivo.get("VALOR_OPERACION_ASOCIADA").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("VALOR_FECHA_ULTIMA_MODIFI"))) {
					reg.setValorFechaUltimaModificacion((Date)(archivo.get("VALOR_FECHA_ULTIMA_MODIFI")));
				}
				if (!Validaciones.isObjectNull(archivo.get("BOLETA_ID_ACUERDO"))) {
					reg.setBoletaIdAcuerdo(archivo.get("BOLETA_ID_ACUERDO").toString());
				}
			} catch (Exception e) {
				throw new PersistenciaExcepcion(e.getMessage(), e);
			}
			 resultado.add(reg);
		}
		return resultado;
	}
	
}
