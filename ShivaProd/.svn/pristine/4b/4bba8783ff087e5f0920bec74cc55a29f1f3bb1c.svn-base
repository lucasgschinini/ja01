package ar.com.telecom.shiva.persistencia.dao.impl;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.com.telecom.shiva.base.enumeradores.EmpresaEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoProcesamientoHilosEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoTransaccionEnum;
import ar.com.telecom.shiva.base.enumeradores.MarcaEnum;
import ar.com.telecom.shiva.base.enumeradores.MensajeServicioEnum;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.SociedadEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoImputacionEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTareaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTratamientoDiferenciaEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.WorkflowExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.negocio.bean.ControlDeHilosCobros;
import ar.com.telecom.shiva.negocio.dto.cobros.MicTransaccionADCRespuestaDto;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.persistencia.bean.ResultadoBusquedaCobrosPendientes;
import ar.com.telecom.shiva.persistencia.bean.ResultadoBusquedaDatosImputacion;
import ar.com.telecom.shiva.persistencia.bean.ResultadoBusquedaDatosImputacionSap;
import ar.com.telecom.shiva.persistencia.bean.ResultadoBusquedaDatosSimulacion;
import ar.com.telecom.shiva.persistencia.bean.ResultadoBusquedaDocumentoScard;
import ar.com.telecom.shiva.persistencia.bean.ResultadoBusquedaEstadoGrupos;
import ar.com.telecom.shiva.persistencia.dao.Dao;
import ar.com.telecom.shiva.persistencia.dao.ICobroDao;
import ar.com.telecom.shiva.persistencia.dao.util.QueryParametrosUtil;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobCobro;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobFactura;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPago;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoCTA;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobOperacion;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobTransaccion;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobTransaccionMensajeriaDetalle;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvCobCobroSimple;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvCobCobroSimpleSinWorkflow;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvCobTransaccionSimple;
import ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion.ShvCobMedioPagoCTASinOperacion;
import ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion.ShvCobTransaccionSinOperacion;
import ar.com.telecom.shiva.persistencia.repository.CobroRepositorio;
import ar.com.telecom.shiva.persistencia.repository.CobroSimpleRepositorio;
import ar.com.telecom.shiva.persistencia.repository.CobroSimpleSinWorkflowRepositorio;
import ar.com.telecom.shiva.persistencia.repository.MedioPagoRepositorio;
import ar.com.telecom.shiva.persistencia.repository.TransaccionCobroRepositorio;
import ar.com.telecom.shiva.persistencia.repository.TransaccionSinOperacionCobroRepositorio;

public class CobroDaoImpl extends Dao implements ICobroDao {

	@Autowired
	CobroRepositorio cobroRepositorio;

	@Autowired
	TransaccionCobroRepositorio transaccionRepositorio;

	@Autowired
	TransaccionSinOperacionCobroRepositorio transaccionSinOperacionRepositorio;

	@Autowired
	MedioPagoRepositorio medioPagoRepositorio;

	@Autowired
	CobroSimpleRepositorio cobroSimpleRepositorio;

	@Autowired
	CobroSimpleSinWorkflowRepositorio cobroSimpleSinWorkflowRepositorio;

	/**
	 * Inserta un cobro
	 * 
	 * @param cobro
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public ShvCobCobro insertarCobro(ShvCobCobro cobro) throws PersistenciaExcepcion {
		try {
			ShvCobCobro cobroBD = cobroRepositorio.save(cobro);
			cobroRepositorio.flush();
			return cobroBD;

		} catch (Exception e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	/**
	 * Busca un cobro en la base.
	 */
	public ShvCobCobro buscarCobro(Long id) throws PersistenciaExcepcion {
		try {
			return cobroRepositorio.findOne(id);

		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	/**
	 * Actualiza el Cobro en la base.
	 */
	public ShvCobCobro modificar(ShvCobCobro cobroModelo) throws PersistenciaExcepcion {
		try {
			ShvCobCobro cobroBD = cobroRepositorio.save(cobroModelo);
			cobroRepositorio.flush();
			return cobroBD;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	@Transactional
	public ShvCobCobroSimple modificar(ShvCobCobroSimple cobroModelo) throws PersistenciaExcepcion {
		try {
			ShvCobCobroSimple cobroBD = cobroSimpleRepositorio.save(cobroModelo);
			cobroSimpleRepositorio.flush();
			return cobroBD;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	@Transactional
	public ShvCobCobroSimpleSinWorkflow modificar(ShvCobCobroSimpleSinWorkflow cobroModelo) throws PersistenciaExcepcion {
		try {
			ShvCobCobroSimpleSinWorkflow cobroBD = cobroSimpleSinWorkflowRepositorio.save(cobroModelo);
			cobroSimpleSinWorkflowRepositorio.flush();
			return cobroBD;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	/**
	 * Lista todos los cobros en estado Pendiente / En Proceso
	 */
	public List<ResultadoBusquedaCobrosPendientes> listarCobrosImputacionManualPendientes(TipoImputacionEnum tipoImputacion) throws PersistenciaExcepcion {
		try {

			QueryParametrosUtil qp = null;

			if (TipoImputacionEnum.AUTOMATICA.equals(tipoImputacion)) {
				qp = getQueryCobrosPendientes();
			} else {
				if (TipoImputacionEnum.MANUAL.equals(tipoImputacion)) {
					qp = getQueryCobrosImputacionManualPendientes();
				}
			}

			List<Map<String, Object>> listaResultadoQuery = buscarUsandoSQL(qp);

			List<ResultadoBusquedaCobrosPendientes> listaCobrosPendientes = new ArrayList<ResultadoBusquedaCobrosPendientes>();

			for (Map<String, Object> archivo : listaResultadoQuery) {

				// Obtengo los datos del cobro
				ResultadoBusquedaCobrosPendientes cobroPendiente = new ResultadoBusquedaCobrosPendientes();

				cobroPendiente.setIdCobro(Long.valueOf(archivo.get("ID_COBRO").toString()));
				cobroPendiente.setIdOperacion(Long.valueOf(archivo.get("ID_OPERACION").toString()));
				cobroPendiente.setCantidadTransacciones(Integer.valueOf(archivo.get("CANTIDAD").toString()));
				cobroPendiente.setEstadoCobro(Estado.getEnumByName(archivo.get("ESTADO").toString()));
				cobroPendiente.setFechaUltimoProcesamiento(archivo.get("FECHA_ULT_PROCESAMIENTO").toString());

				if (archivo.get("CONTIENE_APLICACION_MANUAL") != null) {
					cobroPendiente.setContieneAplicacionManual(SiNoEnum.getEnumByName(archivo.get("CONTIENE_APLICACION_MANUAL").toString()));
				} else {
					cobroPendiente.setContieneAplicacionManual(SiNoEnum.NO);
				}
				listaCobrosPendientes.add(cobroPendiente);
			}

			return listaCobrosPendientes;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	/**
	 * Lista todos los cobros en estado Pendiente / En Proceso
	 */
	// public List<ShvCobProcHilosCobros> listarCobrosPendientes() throws
	// PersistenciaExcepcion {
	// try {
	//
	// QueryParametrosUtil qp = getQueryCobrosPendientes();
	// List<Map<String,Object>> listaResultadoQuery = buscarUsandoSQL(qp);
	//
	// List<ShvCobProcHilosCobros> listaCobrosPendientes = new
	// ArrayList<ShvCobProcHilosCobros>();
	//
	// for (Map<String, Object> archivo : listaResultadoQuery) {
	//
	// //Obtengo los datos del cobro
	// ShvCobProcHilosCobros cobroPendiente = new ShvCobProcHilosCobros();
	//
	// cobroPendiente.setIdCobro(Long.valueOf(archivo.get("ID_COBRO").toString()));
	// cobroPendiente.setIdOperacion(Long.valueOf(archivo.get("ID_OPERACION").toString()));
	// cobroPendiente.setCantTransacciones(Integer.valueOf(archivo.get("CANTIDAD").toString()));
	// listaCobrosPendientes.add(cobroPendiente);
	// }
	//
	// return listaCobrosPendientes;
	// } catch (Throwable e) {
	// throw new PersistenciaExcepcion(e);
	// }
	// }

	private QueryParametrosUtil getQueryCobrosPendientes() {
		QueryParametrosUtil parametros = new QueryParametrosUtil();

		String query = "select cob.Id_operacion, cob.Id_cobro, cob.fecha_ult_procesamiento, we.estado estado, cob.contiene_aplicacion_manual " + ", (select count(*) from shv_cob_transaccion where id_operacion=cob.id_operacion) cantidad " + "from shv_cob_cobro cob, shv_wf_workflow_estado we " + "where fecha_ult_procesamiento is not null and we.id_workflow=cob.id_workflow and we.estado in ('COB_PENDIENTE','COB_PROCESO') " + "and id_operacion not in (select id_operacion from shv_cob_proc_hilos_cobros where estado='EN_CURSO') " + "order by fecha_ult_procesamiento";

		parametros.setSql(query);
		return parametros;
	}

	private QueryParametrosUtil getQueryCobrosImputacionManualPendientes() {
		QueryParametrosUtil parametros = new QueryParametrosUtil();

		String query = "select cob.Id_operacion, cob.Id_cobro, cob.fecha_ult_procesamiento, we.estado estado, cob.contiene_aplicacion_manual " + ", (select count(*) from shv_cob_transaccion where id_operacion=cob.id_operacion) cantidad " + "from shv_cob_cobro cob, shv_wf_workflow_estado we " + "where fecha_ult_procesamiento is not null and we.id_workflow=cob.id_workflow and we.estado in ('COB_PROCESO_APLICACION_EXTERNA','COB_PENDIENTE_CONFIRMACION_MANUAL_Y_PROCESO_APLICACION_EXTERNA') " + "and id_operacion not in (select id_operacion from shv_cob_proc_hilos_cobros where estado='EN_CURSO') " + "order by fecha_ult_procesamiento";

		parametros.setSql(query);
		return parametros;
	}

	@Override
	public List<EstadoTransaccionEnum> listarEstadosTransaccionesAplicacionManualPendientesProcesar(Long idOperacion) throws PersistenciaExcepcion {

		QueryParametrosUtil parametros = new QueryParametrosUtil();
		List<EstadoTransaccionEnum> lista = new ArrayList<EstadoTransaccionEnum>();

		String query = "select distinct estado from shv_cob_transaccion where id_operacion=? and grupo > 0 ";

		parametros.addCampoAlQuery(idOperacion, Types.NUMERIC);
		parametros.setSql(query);

		List<Map<String, Object>> listaResultadoQuery = buscarUsandoSQL(parametros);

		for (Map<String, Object> archivo : listaResultadoQuery) {

			if (!Validaciones.isObjectNull(archivo.get("ESTADO"))) {
				lista.add(EstadoTransaccionEnum.getEnumByName(archivo.get("ESTADO").toString()));
			}
		}

		return lista;
	}

	/**
	 * Lista todos los cobros en estado Pendiente de MIC
	 */
	public List<Long> listarCobrosPendientesProcesarMIC() throws PersistenciaExcepcion {
		QueryParametrosUtil parametros = new QueryParametrosUtil();
		List<Long> lista = new ArrayList<Long>();
//		String query = "select distinct cob.id_operacion from shv_cob_cobro cob " + "inner join shv_wf_workflow_estado wf on wf.id_workflow = cob.id_workflow " + "inner join shv_cob_transaccion tr on tr.id_operacion = cob.id_operacion " + "where wf.ESTADO =? " + "and tr.grupo=0";
		String query = "select distinct cob.id_operacion from shv_cob_cobro cob " + "inner join shv_wf_workflow_estado wf on wf.id_workflow = cob.id_workflow " + "inner join shv_cob_transaccion tr on tr.id_operacion = cob.id_operacion " + "where wf.ESTADO =? ";

		parametros.addCampoAlQuery(Estado.COB_PEND_PROCESAR_MIC, Types.VARCHAR);
		parametros.setSql(query);
		List<Map<String, Object>> listaResultadoQuery = buscarUsandoSQL(parametros);

		for (Map<String, Object> archivo : listaResultadoQuery) {

			if (!Validaciones.isObjectNull(archivo.get("ID_OPERACION"))) {
				lista.add(Long.valueOf(archivo.get("ID_OPERACION").toString()));
			}
		}

		return lista;
	}

	/**
	 * Lista todos los cobros en estado Pendiente de MIC
	 */
	public List<Long> listarCobrosImpManualPendientesProcesarMIC() throws PersistenciaExcepcion {

		QueryParametrosUtil parametros = new QueryParametrosUtil();
		List<Long> lista = new ArrayList<Long>();
		String query = "select distinct cob.id_operacion from shv_cob_cobro cob " + "inner join shv_wf_workflow_estado wf on wf.id_workflow = cob.id_workflow " + "inner join shv_cob_transaccion tr on tr.id_operacion = cob.id_operacion " + "where wf.ESTADO in(?,?) " + "and tr.grupo > 0";

		parametros.addCampoAlQuery(Estado.COB_PEND_CONF_MANUAL_Y_PROCESO_APLICACION_EXTERNA_Y_PEND_PROC_MIC, Types.VARCHAR);
		parametros.addCampoAlQuery(Estado.COB_PROCESO_APLICACION_EXTERNA_Y_PENDIENTE_PROCESAR_MIC, Types.VARCHAR);
		parametros.setSql(query);
		List<Map<String, Object>> listaResultadoQuery = buscarUsandoSQL(parametros);

		for (Map<String, Object> archivo : listaResultadoQuery) {

			if (!Validaciones.isObjectNull(archivo.get("ID_OPERACION"))) {
				lista.add(Long.valueOf(archivo.get("ID_OPERACION").toString()));
			}
		}

		return lista;

	}

	/**
	 * Retorna un un cobro por el idTransaccion.
	 */
	public ShvCobCobro buscarCobroPorIdTransaccion(Integer idTransaccion) throws PersistenciaExcepcion {
		try {
			String query = "select c from ShvCobCobro as c join c.operacion.transacciones as t " + "where t.idTransaccion=?";
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addParametro(new Integer(idTransaccion));

			ShvCobCobro cobro = (ShvCobCobro) buscarUsandoQueryConParametros(qp).get(0);
			return cobro;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	/**
	 * Retorna un cobro por el id de operacion y el numero transaccion.
	 */
	@SuppressWarnings("unchecked")
	public ShvCobCobro buscarCobroPorIdOperacionYNroTransaccionParaReversion(String idOperacionTransaccion) throws PersistenciaExcepcion {
		try {

			String query = "select c from ShvCobCobro as c " + "join c.operacion.transacciones as t " + "join c.workflow.shvWfWorkflowEstado as we " + " where t.numeroTransaccion= ?" + " and c.operacion.idOperacion= ?" + " and we.estado in (?,?)";

			String idOperacion = Utilidad.removeStartingZeros(idOperacionTransaccion.split("\\.")[0]);
			String nroTransaccion = Utilidad.removeStartingZeros(idOperacionTransaccion.split("\\.")[1]);

			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addParametro(new Integer(nroTransaccion));
			qp.addParametro(new Long(idOperacion));
			qp.addParametro(Estado.COB_COBRADO);
			qp.addParametro(Estado.COB_CONFIRMADO_CON_ERROR);

			List<ShvCobCobro> lista = (List<ShvCobCobro>) buscarUsandoQueryConParametros(qp);
			if (Validaciones.isCollectionNotEmpty(lista)) {
				return lista.get(0);
			} else {
				return null;
			}
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	/**
	 * Retorna un un cobro por el idOperacion.
	 */
	@SuppressWarnings("unchecked")
	public ShvCobCobro buscarCobroPorIdOperacion(Long idOperacion) throws PersistenciaExcepcion {
		try {
			String query = "select c from ShvCobCobro as c join c.operacion as o where o.idOperacion=?";
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addParametro(idOperacion);

			List<ShvCobCobro> listaCobro = (List<ShvCobCobro>) buscarUsandoQueryConParametros(qp);
			if (Validaciones.isCollectionNotEmpty(listaCobro)) {
				return listaCobro.get(0);
			} else {
				return null;
			}
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	// public Long buscarIdOperacionPorIdDocumentoCap(Long idDocumento) throws
	// PersistenciaExcepcion {
	// try {
	//
	// List<Map<String, Object>> listaResultadoQuery = null;
	// Long idOperacion = null;
	//
	// String query = "select scc.id_operacion from shv_cob_cobro scc "
	// + "inner join shv_cob_transaccion sct "
	// + "ON scc.id_operacion = sct.id_operacion "
	// + "inner join shv_cob_med_pago scmp "
	// + "ON sct.id_transaccion = scmp.id_transaccion "
	// + "inner join (select id_medio_pago, id_medio_pago_doc_cap "
	// + "from shv_cob_med_pag_comp_proveedor "
	// + "where id_medio_pago_doc_cap is not null "
	// + "union "
	// + "select id_medio_pago, id_medio_pago_doc_cap "
	// + "from shv_cob_med_pag_comp_liquido "
	// + "where id_medio_pago_doc_cap is not null) scmpc "
	// + "ON scmp.id_medio_pago = scmpc.id_medio_pago "
	// + "inner join shv_cob_med_pag_doc_cap scmpdc "
	// + "ON scmpc.id_medio_pago_doc_cap = scmpdc.id_medio_pago_doc_cap "
	// + "where scmpdc.id_documento = ?;" ;
	// QueryParametrosUtil qp = new QueryParametrosUtil(query);
	// qp.addCampoAlQuery(idDocumento, Types.NUMERIC);
	// // qp.addParametro(idDocumento);
	//
	// listaResultadoQuery = buscarUsandoSQL(qp);
	//
	// if (!Validaciones.isObjectNull(listaResultadoQuery)) {
	// for (Map<String, Object> resultado : listaResultadoQuery){
	//
	// if (!Validaciones.isObjectNull(resultado.get("id_operacion"))) {
	// idOperacion = (Long) resultado.get("id_operacion");
	// }
	// }
	// }
	// return idOperacion;
	//
	// } catch (Throwable e) {
	// throw new PersistenciaExcepcion(e);
	// }
	// }

	/**
	 * Retorna un un cobro por el idOperacion.
	 */
	@SuppressWarnings("unchecked")
	public ShvCobCobroSimple buscarCobroSimplePorIdOperacion(Long idOperacion) throws PersistenciaExcepcion {
		try {
			String query = "select c from ShvCobCobroSimple as c where idOperacion=?";
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addParametro(idOperacion);

			List<ShvCobCobroSimple> listaCobro = (List<ShvCobCobroSimple>) buscarUsandoQueryConParametros(qp);
			return listaCobro.get(0);
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	/**
	 * Retorna un un cobro por el idOperacion.
	 */
	@SuppressWarnings("unchecked")
	public ShvCobCobroSimple buscarCobroSimplePorIdCobro(Long idCobro) throws PersistenciaExcepcion {
		try {
			String query = "select c from ShvCobCobroSimple as c where idCobro = ?";
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addParametro(idCobro);

			List<ShvCobCobroSimple> listaCobro = (List<ShvCobCobroSimple>) buscarUsandoQueryConParametros(qp);
			return listaCobro.get(0);
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	@SuppressWarnings("unchecked")
	public ShvCobCobroSimpleSinWorkflow buscarCobroSimpleSinWorkflowPorIdOperacion(Long idOperacion) throws PersistenciaExcepcion {
		try {
			String query = "select c from ShvCobCobroSimpleSinWorkflow as c where idOperacion=?";
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addParametro(idOperacion);

			List<ShvCobCobroSimpleSinWorkflow> listaCobro = (List<ShvCobCobroSimpleSinWorkflow>) buscarUsandoQueryConParametros(qp);
			return listaCobro.get(0);
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	/**
	 * Lista todos los cobros en estado Error en confirmacion
	 */
	@SuppressWarnings("unchecked")
	public List<ShvCobCobro> listarCobrosErrorConfirmacion(Long idOperacion) throws PersistenciaExcepcion {
		try {
			String query = "select cob from ShvCobCobro as cob join cob.workflow.shvWfWorkflowEstado as we " + "join cob.operacion as op " + "where we.estado = ? and op.idOperacion= ?";

			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addParametro(Estado.COB_ERROR_CONFIRMACION);
			qp.addParametro(new Long(idOperacion));

			List<ShvCobCobro> resultado = (List<ShvCobCobro>) buscarUsandoQueryConParametros(qp);
			return resultado;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ShvCobFactura> buscarFacturaParaReporteRetencion(List<String> listaIdRetencion) throws PersistenciaExcepcion {
		QueryParametrosUtil qp = new QueryParametrosUtil();

		String concatenacion = "";
		for (String id : listaIdRetencion) {
			concatenacion += "?, ";
			qp.addParametro(new Long(id));
		}
		if (!concatenacion.equals("")) {
			concatenacion = concatenacion.substring(0, concatenacion.length() - 2);
		}

		String query = "select fact from ShvCobTransaccion as trans join trans.shvCobFactura as fact " + " join trans.shvCobMedioPago as med where med.idMedioPago in " + " (select shiv.idMedioPago from ShvCobMedioPagoShiva as shiv " + " where shiv.idValor in (" + concatenacion + "))";
		qp.setSql(query);

		List<ShvCobFactura> lista = (List<ShvCobFactura>) buscarUsandoQueryConParametros(qp);
		return lista;
	}

	@Override
	public List<Map<String, Object>> buscarCobrosParaSubdiario(GregorianCalendar fechaFin) throws PersistenciaExcepcion {
		GregorianCalendar fechaInicio = new GregorianCalendar();
		BeanUtils.copyProperties(fechaFin, fechaInicio);
		fechaInicio.set(GregorianCalendar.DAY_OF_MONTH, 1);
		try {
			StringBuffer query = new StringBuffer();

			query.append("select * from shv_sop_subdiario ");
			query.append("where ");
			query.append("to_date(to_char(fechaprocesamiento, 'dd/mm/yyyy'), ?) >= to_date(?, ?) and to_date(to_char(fechaprocesamiento, 'dd/mm/yyyy'), ?) < to_date(?, ?)");

			QueryParametrosUtil parametros = new QueryParametrosUtil(query.toString());

			// Formato to_date de fecha de procesamiento
			parametros.addCampoAlQuery(Utilidad.ORACLE_DATE_TIME_FULL_FORMAT, Types.VARCHAR);

			// Fecha "desde" y su formato
			parametros.addCampoAlQuery(Utilidad.formatDatePicker(fechaInicio.getTime()) + " 00:00:00", Types.VARCHAR);
			parametros.addCampoAlQuery(Utilidad.ORACLE_DATE_TIME_FULL_FORMAT, Types.VARCHAR);

			// Formato to_date para fecha de procesamiento segunda condicion
			parametros.addCampoAlQuery(Utilidad.ORACLE_DATE_TIME_FULL_FORMAT, Types.VARCHAR);

			// Fecha "hasta" y su formato
			parametros.addCampoAlQuery(Utilidad.formatDatePicker(fechaFin.getTime()) + " 23:59:59", Types.VARCHAR);
			parametros.addCampoAlQuery(Utilidad.ORACLE_DATE_TIME_FULL_FORMAT, Types.VARCHAR);

			return buscarUsandoSQL(parametros);
		} catch (PersistenciaExcepcion e) {
			throw new PersistenciaExcepcion(e.getMessage(), e);
		}
	}

	@Override
	public List<ResultadoBusquedaDatosImputacion> buscarDatosImputacion(Long idOperacion) throws PersistenciaExcepcion {

		StringBuilder query = new StringBuilder("select cli.segmento_agencia_negocio as segmento, ed_cob.id_analista as ID_ANALISTA, ed_cob.analista as ANALISTA, ed_cob.id_empresa as empresa, ed_cob.id_copropietario as COPROPIETARIO,");
		query.append(" cli.id_cliente_legado as CLIENTE_LEGADO,");
		query.append(" atc.USER_ANALISTA_COBRANZA_DATOS as ID_ANALISTA_TC, cli.cuit as cuit,");
		query.append(" ed_cob.REFERENTE_RECHAZA_APLIC_MANUAL as USUARIO_RECHAZO, ");
		query.append(" ed_cob.observaciones_aplic_manual as OBSERVACIONES_APLIC_MANUAL,");
		query.append(" ed_cob.ID_SEGMENTO as segmento_cobro, ");
		query.append(" cli.razon_social as RAZON_SOCIAL, ed_cob.importe_total as IMPORTE from shv_cob_ed_cobro ed_cob");
		query.append(" left join shv_cob_ed_cliente cli on ed_cob.id_cobro = cli.id_cobro");
		query.append(" left join SHV_PARAM_TEAM_COMERCIAL atc on atc.NRO_DE_CLIENTE = cli.id_cliente_legado");
		query.append(" where ed_cob.id_operacion = ? ORDER BY CLI.ID_CLIENTE_LEGADO ");

		QueryParametrosUtil qp = new QueryParametrosUtil(query.toString());
		qp.addCampoAlQuery(idOperacion, Types.NUMERIC);

		List<Map<String, Object>> listaResultadoQuery;

		try {
			listaResultadoQuery = buscarUsandoSQL(qp);

			List<ResultadoBusquedaDatosImputacion> listaDatos = new ArrayList<ResultadoBusquedaDatosImputacion>();

			for (Map<String, Object> archivo : listaResultadoQuery) {

				ResultadoBusquedaDatosImputacion datos = new ResultadoBusquedaDatosImputacion();
				datos.setIdAnalista(archivo.get("ID_ANALISTA").toString());
				if (!Validaciones.isObjectNull(archivo.get("ANALISTA"))) {
					datos.setAnalista(archivo.get("ANALISTA").toString());
				}

				datos.setImporte((BigDecimal) archivo.get("IMPORTE"));

				if (!Validaciones.isObjectNull(archivo.get("segmento"))) {
					datos.setSegmento((String) archivo.get("segmento"));
				}

				if (!Validaciones.isObjectNull(archivo.get("CLIENTE_LEGADO"))) {
					datos.setNumCliente(Long.valueOf(archivo.get("CLIENTE_LEGADO").toString()));
				}

				if (!Validaciones.isObjectNull(archivo.get("RAZON_SOCIAL"))) {
					datos.setRazonSocial(archivo.get("RAZON_SOCIAL").toString());
				}

				if (!Validaciones.isObjectNull(archivo.get("COPROPIETARIO"))) {
					datos.setCopropietario(archivo.get("COPROPIETARIO").toString());
				}

				if (!Validaciones.isObjectNull(archivo.get("ID_ANALISTA_TC"))) {
					datos.setAnalistaTeamComercial(archivo.get("ID_ANALISTA_TC").toString());
				}

				if (!Validaciones.isObjectNull(archivo.get("EMPRESA"))) {
					datos.setEmpresa(EmpresaEnum.getEnumByName(archivo.get("EMPRESA").toString()).descripcion());
				}

				if (!Validaciones.isObjectNull(archivo.get("CUIT"))) {
					datos.setCuit(archivo.get("CUIT").toString());
				}

				if (!Validaciones.isObjectNull(archivo.get("USUARIO_RECHAZO"))) {
					datos.setUsuarioRechazoAplicacionManual(archivo.get("USUARIO_RECHAZO").toString());
				}

				if (!Validaciones.isObjectNull(archivo.get("OBSERVACIONES_APLIC_MANUAL"))) {
					datos.setObservacionesAplicacionManual(archivo.get("OBSERVACIONES_APLIC_MANUAL").toString());
				}
				
				if (!Validaciones.isObjectNull(archivo.get("segmento_cobro"))) {
					datos.setSegmentoCobro(archivo.get("segmento_cobro").toString());
				}
				listaDatos.add(datos);
				
			}
			if (listaDatos.isEmpty()){
				return null;
			} else {
				return listaDatos;
			}
		} catch (PersistenciaExcepcion e) {
			throw new PersistenciaExcepcion(e.getMessage(), e);
		}
	}

	@Override
	public List<ResultadoBusquedaDatosImputacion> buscarDatosImputacion(Long idOperacion, SociedadEnum sociedad, SistemaEnum sistema) throws PersistenciaExcepcion {
		
		StringBuilder query = new StringBuilder("select cli.segmento_agencia_negocio as segmento, ed_cob.id_analista as ID_ANALISTA, ed_cob.analista as ANALISTA, ed_cob.id_empresa as empresa, ed_cob.id_copropietario as COPROPIETARIO,");
		query.append(" cli.id_cliente_legado as CLIENTE_LEGADO,");
		query.append(" atc.USER_ANALISTA_COBRANZA_DATOS as ID_ANALISTA_TC, cli.cuit as cuit,");
		query.append(" ed_cob.REFERENTE_RECHAZA_APLIC_MANUAL as USUARIO_RECHAZO, ");
		query.append(" ed_cob.observaciones_aplic_manual as OBSERVACIONES_APLIC_MANUAL,");
		query.append(" ed_cob.ID_SEGMENTO as segmento_cobro, ");
		query.append(" cli.razon_social as RAZON_SOCIAL, ed_cob.importe_total as IMPORTE from shv_cob_ed_cobro ed_cob");
		query.append(" left join shv_cob_ed_cliente cli on ed_cob.id_cobro = cli.id_cobro");
		query.append(" left join SHV_PARAM_TEAM_COMERCIAL atc on atc.NRO_DE_CLIENTE = cli.id_cliente_legado");
		query.append(" left join shv_cob_ed_otros_debito otroDebito on (otroDebito.ID_COBRO = ed_cob.id_cobro)");
		query.append(" where ed_cob.id_operacion = ? and otroDebito.SOCIEDAD = ? and otroDebito.SISTEMA_ORIGEN = ? ORDER BY CLI.ID_CLIENTE_LEGADO ");

		QueryParametrosUtil qp = new QueryParametrosUtil(query.toString());
		qp.addCampoAlQuery(idOperacion,Types.NUMERIC);
		qp.addCampoAlQuery(sociedad.name(),Types.VARCHAR);
		qp.addCampoAlQuery(sistema.name(),Types.VARCHAR);

		List<Map<String, Object>> listaResultadoQuery;

		try {
			listaResultadoQuery = buscarUsandoSQL(qp);

			List<ResultadoBusquedaDatosImputacion> listaDatos = new ArrayList<ResultadoBusquedaDatosImputacion>();

			for (Map<String, Object> archivo : listaResultadoQuery) {

				ResultadoBusquedaDatosImputacion datos = new ResultadoBusquedaDatosImputacion();
				datos.setIdAnalista(archivo.get("ID_ANALISTA").toString());
				if (!Validaciones.isObjectNull(archivo.get("ANALISTA"))) {
					datos.setAnalista(archivo.get("ANALISTA").toString());
				}

				datos.setImporte((BigDecimal) archivo.get("IMPORTE"));

				if (!Validaciones.isObjectNull(archivo.get("segmento"))) {
					datos.setSegmento((String) archivo.get("segmento"));
				}

				if (!Validaciones.isObjectNull(archivo.get("CLIENTE_LEGADO"))) {
					datos.setNumCliente(Long.valueOf(archivo.get("CLIENTE_LEGADO").toString()));
				}

				if (!Validaciones.isObjectNull(archivo.get("RAZON_SOCIAL"))) {
					datos.setRazonSocial(archivo.get("RAZON_SOCIAL").toString());
				}

				if (!Validaciones.isObjectNull(archivo.get("COPROPIETARIO"))) {
					datos.setCopropietario(archivo.get("COPROPIETARIO").toString());
				}

				if (!Validaciones.isObjectNull(archivo.get("ID_ANALISTA_TC"))) {
					datos.setAnalistaTeamComercial(archivo.get("ID_ANALISTA_TC").toString());
				}

				if (!Validaciones.isObjectNull(archivo.get("EMPRESA"))) {
					datos.setEmpresa(EmpresaEnum.getEnumByName(archivo.get("EMPRESA").toString()).descripcion());
				}

				if (!Validaciones.isObjectNull(archivo.get("CUIT"))) {
					datos.setCuit(archivo.get("CUIT").toString());
				}

				if (!Validaciones.isObjectNull(archivo.get("USUARIO_RECHAZO"))) {
					datos.setUsuarioRechazoAplicacionManual(archivo.get("USUARIO_RECHAZO").toString());
				}

				if (!Validaciones.isObjectNull(archivo.get("OBSERVACIONES_APLIC_MANUAL"))) {
					datos.setObservacionesAplicacionManual(archivo.get("OBSERVACIONES_APLIC_MANUAL").toString());
				}
				
				if (!Validaciones.isObjectNull(archivo.get("segmento_cobro"))) {
					datos.setSegmentoCobro(archivo.get("segmento_cobro").toString());
				}
				listaDatos.add(datos);

			}
			if (listaDatos.isEmpty()) {
				return null;
			} else {
				return listaDatos;
			}
		} catch (PersistenciaExcepcion e) {
			throw new PersistenciaExcepcion(e.getMessage(), e);
		}
	}
	public List<ResultadoBusquedaDatosSimulacion> buscarDatosSimulacion(Long idOperacion) throws PersistenciaExcepcion {

		StringBuffer query = new StringBuffer("select distinct ed_cob.id_analista as ID_ANALISTA ,");
		query.append(" ed_cob.id_copropietario as ID_COPROPIETARIO, ");
		query.append(" atc.USER_ANALISTA_COBRANZA_DATOS as ID_ANALISTA_TC, ");
		query.append(" cli.id_cliente_legado as ID_ClIENTE_LEGADO,");
		query.append(" cli.razon_social as RAZON_SOCIAL_CLIENTE,");
		query.append(" ed_cob.importe_total as IMPORTE");
		query.append(" from shv_cob_ed_cobro ed_cob");
		query.append(" left join shv_cob_ed_cliente cli on ed_cob.id_cobro = cli.id_cobro");
		query.append(" left join SHV_PARAM_TEAM_COMERCIAL atc on atc.NRO_DE_CLIENTE = cli.id_cliente_legado");
		query.append(" where ed_cob.id_operacion = ?  ORDER BY CLI.ID_CLIENTE_LEGADO");

		QueryParametrosUtil qp = new QueryParametrosUtil(query.toString());
		qp.addCampoAlQuery(idOperacion, Types.NUMERIC);

		List<Map<String, Object>> listaResultadoQuery;

		try {
			listaResultadoQuery = buscarUsandoSQL(qp);

			List<ResultadoBusquedaDatosSimulacion> listaDatos = new ArrayList<ResultadoBusquedaDatosSimulacion>();

			for (Map<String, Object> archivo : listaResultadoQuery) {

				ResultadoBusquedaDatosSimulacion datos = new ResultadoBusquedaDatosSimulacion();
				datos.setAnalista(archivo.get("ID_ANALISTA").toString());
				datos.setIdClienteLegado(new Long(archivo.get("ID_ClIENTE_LEGADO").toString()));

				if (!Validaciones.isObjectNull(archivo.get("IMPORTE"))) {
					datos.setImporte((BigDecimal) archivo.get("IMPORTE"));
				}
				if (!Validaciones.isObjectNull(archivo.get("RAZON_SOCIAL_CLIENTE"))) {
					datos.setRazonSocial(archivo.get("RAZON_SOCIAL_CLIENTE").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("ID_COPROPIETARIO"))) {
					datos.setCopropietario(archivo.get("ID_COPROPIETARIO").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("ID_ANALISTA_TC"))) {
					datos.setAnalistaTeamComercial(archivo.get("ID_ANALISTA_TC").toString());
				}
				listaDatos.add(datos);
			}
			if (listaDatos.isEmpty()) {
				return null;
			} else {
				return listaDatos;
			}
		} catch (PersistenciaExcepcion e) {
			throw new PersistenciaExcepcion(e.getMessage(), e);
		}
	}

	@Override
	public List<ResultadoBusquedaDatosImputacionSap> buscarDatosImputacionSap(Long idOperacion, boolean hayMpProveedor) throws PersistenciaExcepcion {

		String queryProveedorOliqProd = "";
		if (hayMpProveedor) {
			queryProveedorOliqProd = " inner join shv_cob_med_pag_comp_proveedor sap on sap.id_medio_pago = mp.id_medio_pago";
		} else {
			queryProveedorOliqProd = " inner join shv_cob_med_pag_comp_liquido sap on sap.id_medio_pago = mp.id_medio_pago";
		}
		String query = "select distinct " + "cli.id_cliente_legado as CLIENTE_LEGADO, " + "cli.razon_social as RAZON_SOCIAL, " + "cap.id_documento as ID_DOCUMENTO, " + "cap.id_proveedor as ID_PROVEEDOR, " + "(CASE WHEN cli.id_cliente_legado = mpCli.id_cliente_legado THEN 'SI' ELSE 'NO' END) AS CLIENTE_COMPENSACION" + " from shv_cob_ed_cliente cli" + " inner join shv_cob_cobro cob on cob.id_cobro = cli.id_cobro" + " inner join shv_cob_transaccion tr on tr.id_operacion = cob.id_operacion" + " inner join shv_cob_med_pago mp on mp.id_transaccion = tr.id_transaccion" + " inner join shv_cob_med_pag_cliente mpCli on mpCli.id_medio_pago = mp.id_medio_pago" + queryProveedorOliqProd + " inner join shv_cob_med_pag_doc_cap cap on cap.id_medio_pago_doc_cap = sap.id_medio_pago_doc_cap" + " where cob.id_operacion=? order by CLIENTE_COMPENSACION desc";

		QueryParametrosUtil qp = new QueryParametrosUtil(query);
		qp.addCampoAlQuery(idOperacion, Types.NUMERIC);

		List<Map<String, Object>> listaResultadoQuery;

		try {
			listaResultadoQuery = buscarUsandoSQL(qp);

			List<ResultadoBusquedaDatosImputacionSap> listaDatos = new ArrayList<ResultadoBusquedaDatosImputacionSap>();

			for (Map<String, Object> archivo : listaResultadoQuery) {

				ResultadoBusquedaDatosImputacionSap datos = new ResultadoBusquedaDatosImputacionSap();

				if (!Validaciones.isObjectNull(archivo.get("CLIENTE_LEGADO"))) {
					datos.setNumCliente(Long.valueOf(archivo.get("CLIENTE_LEGADO").toString()));
				}

				if (!Validaciones.isObjectNull(archivo.get("RAZON_SOCIAL"))) {
					datos.setRazonSocial(archivo.get("RAZON_SOCIAL").toString());
				}

				if (!Validaciones.isObjectNull(archivo.get("ID_DOCUMENTO"))) {
					datos.setIdCompensacion(archivo.get("ID_DOCUMENTO").toString());
				}

				if (!Validaciones.isObjectNull(archivo.get("ID_PROVEEDOR"))) {
					datos.setNumeroProveedor(archivo.get("ID_PROVEEDOR").toString());
				}

				if (!Validaciones.isObjectNull(archivo.get("CLIENTE_COMPENSACION"))) {
					datos.setClienteCompensacion(archivo.get("CLIENTE_COMPENSACION").toString());
				}
				listaDatos.add(datos);

			}
			if (listaDatos.isEmpty()) {
				return null;
			} else {
				return listaDatos;
			}
		} catch (PersistenciaExcepcion e) {
			throw new PersistenciaExcepcion(e.getMessage(), e);
		}
	}

	@Override
	/**
	 * 
	 */
	public void eliminar(Long id) throws PersistenciaExcepcion {
		try {
			cobroRepositorio.delete(id);
			cobroRepositorio.flush();

		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<ShvCobCobro> buscarCobrosSimulacionEnProceso() throws PersistenciaExcepcion {

		StringBuffer query = new StringBuffer();

		query.append("SELECT cobro from ShvCobCobro as cobro WHERE cobro.workflow IN (");
		query.append("SELECT wf ");
		query.append("FROM ShvWfWorkflowMarca marca LEFT JOIN marca.workflowEstado LEFT JOIN marca.workflowEstado.workflow as wf ");
		query.append("WHERE marca.marca = ? AND marca.workflowEstado.estado in (?, ?) AND marca.fechaCreacion = ( ");
		query.append("SELECT max(marca2.fechaCreacion) ");
		query.append("FROM ShvWfWorkflowMarca marca2 left JOIN marca2.workflowEstado ");
		query.append("WHERE marca2.workflowEstado = marca.workflowEstado)) order by cobro.idCobro");

		QueryParametrosUtil qp = new QueryParametrosUtil(query.toString());
		qp.addParametro(MarcaEnum.SIMULACION_BATCH_EN_PROCESO);
		qp.addParametro(Estado.COB_EN_EDICION);
		qp.addParametro(Estado.COB_RECHAZADO);

		List<ShvCobCobro> listaCobros = buscarUsandoQueryConParametros(qp);

		return listaCobros;
	}

	@Override
	public List<Long> listarOperacionesDeCobroEnProcesoPorIdValor(Long idValor) throws PersistenciaExcepcion {

		StringBuffer query = new StringBuffer("");

		query.append(" select ");
		query.append("   sced.id_cobro, ");
		query.append("   sced.id_operacion, ");
		query.append("   swwe.estado, ");
		query.append("   scmps.id_valor ");
		query.append(" from ");
		query.append("   shv_cob_ed_cobro sced, ");
		query.append("   shv_wf_workflow_estado swwe, ");
		query.append("   shv_cob_transaccion sct, ");
		query.append("   shv_cob_med_pago scmp, ");
		query.append("   shv_cob_med_pag_shiva scmps ");
		query.append(" where ");
		query.append("   sced.id_workflow = swwe.id_workflow ");
		query.append("   and sced.id_operacion = sct.id_operacion ");
		query.append("   and sct.id_transaccion = scmp.id_transaccion ");
		query.append("   and scmp.id_medio_pago = scmps.id_medio_pago ");
		query.append("   and swwe.estado in ('COB_PENDIENTE', 'COB_PROCESO') ");
		query.append("   and scmps.id_valor = ? ");
		query.append(" order by 1 asc ");

		QueryParametrosUtil qp = new QueryParametrosUtil(query.toString());
		qp.addCampoAlQuery(idValor, Types.NUMERIC);

		List<Long> listaOperaciones = new ArrayList<Long>();

		try {
			List<Map<String, Object>> listaResultadoQuery = buscarUsandoSQL(qp);

			for (Map<String, Object> registro : listaResultadoQuery) {

				if (!Validaciones.isObjectNull(registro.get("ID_OPERACION"))) {
					listaOperaciones.add(new Long(registro.get("ID_OPERACION").toString()));
				}
			}
		} catch (PersistenciaExcepcion e) {
			throw new PersistenciaExcepcion(e.getMessage(), e);
		}

		return listaOperaciones;
	}

	@Override
	public BigDecimal importeDeAplicacionesPendientesYCobrosEnProcesos(Long idValor) throws PersistenciaExcepcion {

		StringBuffer query = new StringBuffer("");

		query.append("select sum (scmp.importe) ");
		query.append("	from   shv_cob_med_pag_shiva scmps, ");
		query.append("	       shv_cob_med_pago scmp, ");
		query.append(" 		   shv_cob_transaccion sct, ");
		query.append("		   shv_cob_cobro scc, ");
		query.append("	 	   shv_wf_workflow_estado swwe ");

		query.append("	where  swwe.id_workflow = scc.id_workflow ");
		query.append("		and  scc.id_operacion = sct.id_operacion ");
		query.append("		and  sct.id_transaccion = scmp.id_transaccion ");
		query.append("		and  scmp.id_medio_pago = scmps.id_medio_pago ");
		query.append("		and  swwe.estado in ('COB_PENDIENTE', 'COB_PROCESO') ");
		query.append("		and  scmp.estado = 'PENDIENTE' ");
		query.append("		and  scmps.id_valor = ? ");

		QueryParametrosUtil qp = new QueryParametrosUtil(query.toString());
		qp.addCampoAlQuery(idValor, Types.NUMERIC);

		BigDecimal saldoPendienteDeAplicar = BigDecimal.ZERO;

		try {
			List<Map<String, Object>> listaResultadoQuery = buscarUsandoSQL(qp);

			for (Map<String, Object> registro : listaResultadoQuery) {

				if (!Validaciones.isObjectNull(registro.get("SUM(SCMP.IMPORTE)"))) {
					saldoPendienteDeAplicar = (BigDecimal) registro.get("SUM(SCMP.IMPORTE)");
				}
			}
		} catch (PersistenciaExcepcion e) {
			throw new PersistenciaExcepcion(e.getMessage(), e);
		}

		return saldoPendienteDeAplicar;
	}

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<ShvCobTransaccionMensajeriaDetalle> buscarConfirmacionFallida(Long idOperacion) throws PersistenciaExcepcion {

		String query = "select trans from ShvCobTransaccionMensajeriaDetalle as trans " + " where trans.servicio like '%CONFIRMACION' and  trans.idOperacion=?";
		QueryParametrosUtil qp = new QueryParametrosUtil(query.toString());
		qp.addCampoAlQuery(idOperacion, Types.NUMERIC);

		List<ShvCobTransaccionMensajeriaDetalle> lista = (List<ShvCobTransaccionMensajeriaDetalle>) buscarUsandoQueryConParametros(qp);
		return lista;

	}

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public ShvCobOperacion buscarOperacionPorIdOperacion(Long idOperacion) throws PersistenciaExcepcion {
		String query = "select ope from ShvCobOperacion as ope " + " where ope.idOperacion=?";
		QueryParametrosUtil qp = new QueryParametrosUtil(query.toString());
		qp.addCampoAlQuery(idOperacion, Types.NUMERIC);

		List<ShvCobOperacion> lista = (List<ShvCobOperacion>) buscarUsandoQueryConParametros(qp);

		if (Validaciones.isCollectionNotEmpty(lista)) {
			return lista.get(0);
		}
		return null;
	}

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public ShvCobTransaccion buscarTransaccionPorIdTransaccion(Integer idTransaccion) throws PersistenciaExcepcion {
		String query = "select trans from ShvCobTransaccion as trans " + " where trans.idTransaccion=?";
		QueryParametrosUtil qp = new QueryParametrosUtil(query.toString());
		qp.addCampoAlQuery(idTransaccion, Types.NUMERIC);

		List<ShvCobTransaccion> lista = (List<ShvCobTransaccion>) buscarUsandoQueryConParametros(qp);

		if (Validaciones.isCollectionNotEmpty(lista)) {
			return lista.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<ShvCobTransaccionSinOperacion> buscarTransaccionesPorIdOperacionParaRechazoConfirmAplicacionManual(Long idOperacion) throws PersistenciaExcepcion {

		// String query = "select * from shv_cob_transaccion tr" +
		// " inner join shv_cob_tratamiento_diferencia tratamiento on tratamiento.id_transaccion = tr.id_transaccion"
		// +
		// " where" +
		// " tr.id_operacion=? and tratamiento.tipo_tratamiento_diferencia in(?,?,?,?,?)";
		//
		// QueryParametrosUtil qp = new QueryParametrosUtil(query);
		// qp.addCampoAlQuery(idOperacion,Types.NUMERIC);
		// qp.addCampoAlQuery(TipoTratamientoDiferenciaEnum.APLICACION_MANUAL_CABLEVISION.name(),Types.VARCHAR);
		// qp.addCampoAlQuery(TipoTratamientoDiferenciaEnum.APLICACION_MANUAL_FIBERTEL.name(),Types.VARCHAR);
		// qp.addCampoAlQuery(TipoTratamientoDiferenciaEnum.APLICACION_MANUAL_NEGOCIO_NET.name(),Types.VARCHAR);
		// qp.addCampoAlQuery(TipoTratamientoDiferenciaEnum.APLICACION_MANUAL_NEXTEL.name(),Types.VARCHAR);
		// qp.addCampoAlQuery(TipoTratamientoDiferenciaEnum.APLICACION_MANUAL_SAP.name(),Types.VARCHAR);
		//
		// List<Map<String,Object>> listaResultadoQuery = buscarUsandoSQL(qp);
		//
		// for (Map<String, Object> archivo : listaResultadoQuery) {
		//
		// if (!Validaciones.isObjectNull(archivo.get("hilos_vivos"))){
		// resultado = Integer.valueOf(archivo.get("hilos_vivos").toString());
		// }
		// }

		String query = "select trans from ShvCobTransaccionSinOperacion as trans " + "join trans.listaTratamientosDiferencias tratamiento" + " where trans.idOperacion=? and tratamiento.tipoTratamientoDiferencia in (?,?,?,?,?)";
		QueryParametrosUtil qp = new QueryParametrosUtil(query.toString());
		qp.addCampoAlQuery(idOperacion, Types.NUMERIC);
		qp.addCampoAlQuery(TipoTratamientoDiferenciaEnum.APLICACION_MANUAL_CABLEVISION, Types.VARCHAR);
		qp.addCampoAlQuery(TipoTratamientoDiferenciaEnum.APLICACION_MANUAL_FIBERTEL, Types.VARCHAR);
		qp.addCampoAlQuery(TipoTratamientoDiferenciaEnum.APLICACION_MANUAL_NEGOCIO_NET, Types.VARCHAR);
		qp.addCampoAlQuery(TipoTratamientoDiferenciaEnum.APLICACION_MANUAL_NEXTEL, Types.VARCHAR);
		qp.addCampoAlQuery(TipoTratamientoDiferenciaEnum.APLICACION_MANUAL_SAP, Types.VARCHAR);

		List<ShvCobTransaccionSinOperacion> lista = (List<ShvCobTransaccionSinOperacion>) buscarUsandoQueryConParametros(qp);

		if (Validaciones.isCollectionNotEmpty(lista)) {
			return lista;
		}
		return null;
	}
	
	
	  
	 
	@Override
	public List<ResultadoBusquedaEstadoGrupos> buscarEstadoGruposConAplicacionManual(Long idOperacion,Long grupoActual) throws PersistenciaExcepcion {
		
//		String query = "select distinct estado,grupo,id_sociedad,sistema from shv_cob_transaccion where id_operacion=? and grupo > 0 and estado not in(?) order by grupo asc";
//		String query="select distinct tr.estado,tr.grupo,tr.id_sociedad,tr.sistema,cob.id_workflow,tarea.id_workflow,tarea.sistema,tarea.sociedad"
//				+ " from shv_cob_transaccion tr"
//				+ "	inner join shv_cob_cobro cob on cob.id_operacion = tr.id_operacion"
//				+ "	full join shv_wf_tarea tarea on tarea.id_workflow = cob.id_workflow"
//				+ "	where cob.id_operacion=? and tr.grupo > 0 and tr.estado not in(?)"
//				+ "	and (tarea.id_workflow is nuLL or ((tarea.sistema != tr.sistema and tarea.sociedad != tr.id_sociedad)"
//				+ "	and tarea.tipo_tarea  in (?,?)))";
		
		/*String query=
				"select distinct tr.estado,tr.grupo,tr.id_sociedad,tr.sistema "
				+"from shv_cob_transaccion tr "
				+"inner join shv_cob_cobro cob on cob.id_operacion = tr.id_operacion "
				+"full join shv_wf_tarea tarea on tarea.id_workflow = cob.id_workflow "
				+"where cob.id_operacion=? and tr.grupo > 0 " 
				+"and tr.estado not in(?)"
				+"and (tarea.id_workflow is nuLL or ((tarea.sistema != tr.sistema and tarea.sociedad != tr.id_sociedad) "
				+"and tarea.tipo_tarea  in (?,?)) "
				+"and tr.id_transaccion not in ( "
				+"    select distinct tr2.id_transaccion "
				+"    from shv_cob_transaccion tr2 "
				+"    inner join shv_cob_cobro cob2 on cob2.id_operacion = tr2.id_operacion "
				+"    full join shv_wf_tarea tarea2 on tarea2.id_workflow = cob2.id_workflow "
				+"    where (tarea2.sistema = tr2.sistema and tarea2.sociedad = tr2.id_sociedad) "
				+")) ";*/
		
		
		String query=
				"select distinct tr.estado,tr.grupo,tr.id_sociedad,tr.sistema "
				+"from shv_cob_transaccion tr "
				+"inner join shv_cob_cobro cob on cob.id_operacion = tr.id_operacion "
				+"full join shv_wf_tarea tarea on tarea.id_workflow = cob.id_workflow "
				+"where cob.id_operacion=? and tr.grupo > 0 " 
				+"and tr.estado not in(?)"
				+"and (not exists (select id_workflow from shv_wf_tarea where id_workflow=tarea.id_workflow "
				+"and tipo_tarea  in ('COB_REV_ERR_APLIC_MANUAL','COB_CONF_APLIC_MANUAL')) or ((tarea.sistema != tr.sistema and tarea.sociedad != tr.id_sociedad) "
				+"and tarea.tipo_tarea  in (?,?)) "
				+"and tr.id_transaccion not in ( "
				+"    select distinct tr2.id_transaccion "
				+"    from shv_cob_transaccion tr2 "
				+"    inner join shv_cob_cobro cob2 on cob2.id_operacion = tr2.id_operacion "
				+"    full join shv_wf_tarea tarea2 on tarea2.id_workflow = cob2.id_workflow "
				+"    where (tarea2.sistema = tr2.sistema and tarea2.sociedad = tr2.id_sociedad) "
				+")) ";
		
		
		QueryParametrosUtil qp = new QueryParametrosUtil();
		qp.addCampoAlQuery(idOperacion, Types.NUMERIC);
		
		qp.addCampoAlQuery(EstadoTransaccionEnum.PENDIENTE.name(), Types.VARCHAR);
		qp.addCampoAlQuery(TipoTareaEnum.COB_REV_ERR_APLIC_MANUAL.name(), Types.VARCHAR);
		qp.addCampoAlQuery(TipoTareaEnum.COB_CONF_APLIC_MANUAL.name(), Types.VARCHAR);
		
		if(!Validaciones.isObjectNull(grupoActual)){
			query+=" and tr.grupo != ?";
			qp.addCampoAlQuery(grupoActual, Types.NUMERIC);
		}
		query+=" order by tr.grupo asc";
		
		qp.setSql(query);
		
		List<ResultadoBusquedaEstadoGrupos> lista = new ArrayList<ResultadoBusquedaEstadoGrupos>();
		
		List<Map<String, Object>> listaResultadoQuery = buscarUsandoSQL(qp);

		for (Map<String, Object> archivo : listaResultadoQuery) {

			ResultadoBusquedaEstadoGrupos resultado = new ResultadoBusquedaEstadoGrupos();
			
			if (!Validaciones.isObjectNull(archivo.get("ESTADO"))) {
				resultado.setEstadoTransaccion(EstadoTransaccionEnum.getEnumByName(archivo.get("ESTADO").toString()));
			}
			
			if (!Validaciones.isObjectNull(archivo.get("GRUPO"))) {
				resultado.setGrupo(Long.valueOf(archivo.get("GRUPO").toString()));
			}
			
			if (!Validaciones.isObjectNull(archivo.get("ID_SOCIEDAD"))) {
				resultado.setSociedad(SociedadEnum.getEnumByName(archivo.get("ID_SOCIEDAD").toString()));
			}
			
			if (!Validaciones.isObjectNull(archivo.get("SISTEMA"))) {
				resultado.setSistema(SistemaEnum.getEnumByName(archivo.get("SISTEMA").toString()));
			}
			
			lista.add(resultado);
		}

		if(Validaciones.isCollectionNotEmpty(lista)){
			return lista;
		}
		return null;
	}
//	
//	@Override
//	public List<ShvCobTransaccionSinOperacion> buscarTransaccionesApropiadasPorIdOperacionAplicacionManual(Long idOperacion) throws PersistenciaExcepcion {
//		
//		String query = "select trans from ShvCobTransaccionSinOperacion as trans "
//				+ " where trans.idOperacion=? and trans.estadoProcesamiento in (?,?) and trans.grupo > 0";
//		QueryParametrosUtil qp = new QueryParametrosUtil(query.toString());
//		qp.addCampoAlQuery(idOperacion, Types.NUMERIC);
//		qp.addCampoAlQuery(EstadoTransaccionEnum.APROPIADA,Types.VARCHAR);
//		qp.addCampoAlQuery(EstadoTransaccionEnum.APROPIADA_SIN_COMP_SAP,Types.VARCHAR);
//		
//		@SuppressWarnings("unchecked")
//		List<ShvCobTransaccionSinOperacion> lista = (List<ShvCobTransaccionSinOperacion>) buscarUsandoQueryConParametros(qp);
//		
//		if(Validaciones.isCollectionNotEmpty(lista)){
//			return lista;
//		}
//		return null;
//	}
	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public ShvCobTransaccionSinOperacion buscarTransaccionSinOperacionPorIdTransaccion(Integer idTransaccion) throws PersistenciaExcepcion {
		String query = "select trans from ShvCobTransaccionSinOperacion as trans " + " where trans.idTransaccion=?";
		QueryParametrosUtil qp = new QueryParametrosUtil(query.toString());
		qp.addCampoAlQuery(idTransaccion, Types.NUMERIC);

		List<ShvCobTransaccionSinOperacion> lista = (List<ShvCobTransaccionSinOperacion>) buscarUsandoQueryConParametros(qp);

		if (Validaciones.isCollectionNotEmpty(lista)) {
			return lista.get(0);
		}
		return null;
	}

	/**
	 * 
	 * @param mensajeRespuesta
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean mensajeAnteriormenteProcesado(MicTransaccionADCRespuestaDto mensajeRespuesta, MensajeServicioEnum servicio) throws PersistenciaExcepcion {

		String query = "select trans from ShvCobTransaccionMensajeriaDetalle as trans " + " where trans.servicio=? and trans.idOperacion=? and trans.idTransaccion=? and trans.estado='COMPLETADO'";
		QueryParametrosUtil qp = new QueryParametrosUtil(query.toString());
		qp.addCampoAlQuery(servicio, Types.VARCHAR);

		String idOperacion = Utilidad.removeStartingZeros(mensajeRespuesta.getIdOperacionTransaccion().split("\\.")[0]);
		qp.addCampoAlQuery(Long.valueOf(idOperacion), Types.NUMERIC);
		qp.addCampoAlQuery(mensajeRespuesta.getIdTransaccion(), Types.NUMERIC);

		List<ShvCobTransaccionMensajeriaDetalle> lista = (List<ShvCobTransaccionMensajeriaDetalle>) buscarUsandoQueryConParametros(qp);

		if (Validaciones.isCollectionNotEmpty(lista)) {
			return true;
		}
		return false;
	}

	@Override
	public ShvCobTransaccion guardarTransaccion(ShvCobTransaccion transaccion) throws PersistenciaExcepcion {
		try {
			ShvCobTransaccion transaccionBD = transaccionRepositorio.save(transaccion);
			transaccionRepositorio.flush();
			return transaccionBD;

		} catch (Exception e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	@Transactional(readOnly = false, rollbackFor = { Exception.class, WorkflowExcepcion.class,PersistenciaExcepcion.class }, propagation = Propagation.REQUIRED)
	public ShvCobTransaccionSinOperacion guardarTransaccionSinOperacion(ShvCobTransaccionSinOperacion transaccion) throws PersistenciaExcepcion {
		try {
			ShvCobTransaccionSinOperacion transaccionBD = transaccionSinOperacionRepositorio.save(transaccion);
			transaccionSinOperacionRepositorio.flush();
			return transaccionBD;

		} catch (Exception e) {
			throw new PersistenciaExcepcion(e);
		}
		
	}
	
	/**
	 * 
	 * @param transaccion
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	@Override
	public ShvCobTransaccionSinOperacion guardarTransaccionSinOperacionNoTransaccional(ShvCobTransaccionSinOperacion transaccion) throws PersistenciaExcepcion {
		try {
			ShvCobTransaccionSinOperacion transaccionBD = transaccionSinOperacionRepositorio.save(transaccion);
			transaccionSinOperacionRepositorio.flush();
			return transaccionBD;

		} catch (Exception e) {
			throw new PersistenciaExcepcion(e);
		}
		
	}

	@SuppressWarnings("unchecked")
	public ShvCobTransaccionSinOperacion buscarTransaccionAProcesarPorIdOperacion(Long idOperacion) throws PersistenciaExcepcion {
		
		/*String query = "select t from ShvCobTransaccionSinOperacion as t where t.idTransaccion in ( "+
				"select min(trans.idTransaccion ) "+
				"from ShvCobTransaccionSimple trans, ShvCobCobroSimple cob, ShvWfWorkflowEstado we "+  
				"where trans.idOperacion  in (?) "+
				"and cob.idOperacion=trans.idOperacion " +
				"and cob.workflow.idWorkflow=we.workflow.idWorkflow "+ 
				"and we.estado in ('COB_PENDIENTE','COB_PROCESO') "+
				"and trans.estadoProcesamiento in ('PENDIENTE','EN_PROCESO_APROPIACION','EN_PROCESO_DESAPROPIACION') "+
				"and trans.grupo = 0 "+
				"and cob.idOperacion not in ("
						+ "select trans2.idOperacion "
						+ "from ShvCobTransaccionSimple trans2 "
						+ "where trans2.estadoProcesamiento in ('ERROR_MEDIO_PAGO','ERROR_DEUDA','ERROR_MEDIO_PAGO_DEUDA','ERROR_TRATAMIENTO'))) ";*/
		
		String query = "select t from ShvCobTransaccionSinOperacion as t where t.idTransaccion in ( "+
		"select min(trans.idTransaccion ) "+
		"from ShvCobTransaccionSimple trans, ShvCobCobroSimple cob, ShvWfWorkflowEstado we "+  
		"where trans.idOperacion  in (?) "+
		"and cob.idOperacion=trans.idOperacion " +
		"and cob.workflow.idWorkflow=we.workflow.idWorkflow "+ 
		"and we.estado in ('COB_PENDIENTE','COB_PROCESO') "+
		"and ("+
		"(trans.estadoProcesamiento in ('EN_PROCESO_DESAPROPIACION') and trans.grupo = 0 and cob.contieneAplicacionManual='SI')"+
		"or (trans.estadoProcesamiento in ('PENDIENTE','EN_PROCESO_APROPIACION') "+
		"and trans.grupo = 0 "+
		"and cob.idOperacion not in ("
				+ "select trans2.idOperacion "
				+ "from ShvCobTransaccionSimple trans2 "
				+ "where trans2.estadoProcesamiento in ('ERROR_MEDIO_PAGO','ERROR_DEUDA','ERROR_MEDIO_PAGO_DEUDA','ERROR_TRATAMIENTO'))))) ";
		
		QueryParametrosUtil qp = new QueryParametrosUtil(query.toString());
		qp.addCampoAlQuery(idOperacion, Types.NUMERIC);

		List<ShvCobTransaccionSinOperacion> lista = (List<ShvCobTransaccionSinOperacion>) buscarUsandoQueryConParametros(qp);

		if (Validaciones.isCollectionNotEmpty(lista)) {
			return lista.get(0);
		}
		return null;
	}

	@Override
	public ShvCobTransaccionSimple buscarTransaccionSimplePorIdOperacionYNumeroTransaccion(Long idOperacion, Integer numeroTransaccion) throws PersistenciaExcepcion {

		String query = "select t from ShvCobTransaccionSimple as t where t.idOperacion=? and t.numeroTransaccion=?";

		QueryParametrosUtil qp = new QueryParametrosUtil(query.toString());
		qp.addCampoAlQuery(idOperacion, Types.NUMERIC);
		qp.addCampoAlQuery(numeroTransaccion, Types.NUMERIC);

		@SuppressWarnings("unchecked")
		List<ShvCobTransaccionSimple> lista = (List<ShvCobTransaccionSimple>) buscarUsandoQueryConParametros(qp);

		if (Validaciones.isCollectionNotEmpty(lista)) {
			return lista.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public ShvCobTransaccionSinOperacion buscarTransaccionAProcesarPorIdOperacionImpManual(Long idOperacion) throws PersistenciaExcepcion {
		
		String query = "select t from ShvCobTransaccionSinOperacion as t where t.idTransaccion in ( "+
				"select min(trans.idTransaccion ) "+
				"from ShvCobTransaccionSimple trans, ShvCobCobroSimple cob, ShvWfWorkflowEstado we "+  
				"where trans.idOperacion  in (?) "+
				"and cob.idOperacion=trans.idOperacion " +
				"and cob.workflow.idWorkflow=we.workflow.idWorkflow "+ 
				"and we.estado in ('COB_PROCESO_APLICACION_EXTERNA','COB_PENDIENTE_CONFIRMACION_MANUAL_Y_PROCESO_APLICACION_EXTERNA') "+
				"and (trans.estadoProcesamiento in ('PENDIENTE','EN_PROCESO_APROPIACION','EN_PROCESO_DESAPROPIACION','EN_PROCESO_DESAPROPIACION_APL_MANUAL_RECHAZADA') "+ 
				"or (trans.estadoProcesamiento in ('PENDIENTE_FINALIZACION_TRANSACCION') "+
				"and (select sct1.grupo from ShvCobTransaccionSinOperacion sct1 where sct1.idOperacion = trans.idOperacion and  sct1.numeroTransaccion = trans.numeroTransaccionDependencia ) = 0)) "+
				"and trans.grupo > 0 "+
				"and cob.idOperacion not in ("
						+ "select trans2.idOperacion "
						+ "from ShvCobTransaccionSimple trans2 "
						+ "where trans2.estadoProcesamiento in ('ERROR_MEDIO_PAGO','ERROR_DEUDA','ERROR_MEDIO_PAGO_DEUDA','ERROR_TRATAMIENTO')"
						+ "and trans2.grupo=trans.grupo)) order by t.grupo asc";
		
		QueryParametrosUtil qp = new QueryParametrosUtil(query.toString());
		qp.addCampoAlQuery(idOperacion, Types.NUMERIC);

		List<ShvCobTransaccionSinOperacion> lista = (List<ShvCobTransaccionSinOperacion>) buscarUsandoQueryConParametros(qp);

		if (Validaciones.isCollectionNotEmpty(lista)) {
			return lista.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<ShvCobTransaccionSinOperacion> buscarTransaccionPorIdOperacion(Long idOperacion) throws PersistenciaExcepcion {

		StringBuffer consulta = new StringBuffer("");
		QueryParametrosUtil qp = new QueryParametrosUtil();

		consulta.append("select trans from ShvCobTransaccionSinOperacion as trans");
		consulta.append(" where trans.idOperacion = ? ");

		qp.addCampoAlQuery(idOperacion, Types.NUMERIC);
		qp.setSql(consulta.toString());

		List<ShvCobTransaccionSinOperacion> lista = (List<ShvCobTransaccionSinOperacion>) buscarUsandoQueryConParametros(qp);

		if (Validaciones.isCollectionNotEmpty(lista)) {
			return lista;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<ShvCobTransaccionSinOperacion> buscarTransaccionPorIdOperacionYEstado(Long idOperacion, EstadoTransaccionEnum estado) throws PersistenciaExcepcion {

		StringBuffer consulta = new StringBuffer("");
		QueryParametrosUtil qp = new QueryParametrosUtil();

		consulta.append("select trans from ShvCobTransaccionSinOperacion as trans");
		consulta.append(" where trans.idOperacion = ? ");
		consulta.append(" and trans.estadoProcesamiento = ?");

		qp.addCampoAlQuery(idOperacion, Types.NUMERIC);
		qp.addCampoAlQuery(estado, Types.JAVA_OBJECT);
		qp.setSql(consulta.toString());
		

		List<ShvCobTransaccionSinOperacion> lista = (List<ShvCobTransaccionSinOperacion>) buscarUsandoQueryConParametros(qp);

		if (Validaciones.isCollectionNotEmpty(lista)) {
			return lista;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ShvCobTransaccionSinOperacion> buscarTransaccionSinOperacionPorIdOperacionYSistemaYSociedad(Long idOperacion, SistemaEnum sistema, SociedadEnum sociedad) throws PersistenciaExcepcion {

		StringBuffer consulta = new StringBuffer("");
		QueryParametrosUtil qp = new QueryParametrosUtil();

		consulta.append("select trans from ShvCobTransaccionSinOperacion as trans");
		consulta.append(" where trans.idOperacion = ? ");
		consulta.append(" and trans.sociedad = ? ");
		consulta.append(" and trans.sistema = ? ");

		qp.addCampoAlQuery(idOperacion, Types.NUMERIC);
		qp.addCampoAlQuery(sociedad, Types.JAVA_OBJECT);
		qp.addCampoAlQuery(sistema, Types.JAVA_OBJECT);
		qp.setSql(consulta.toString());

		List<ShvCobTransaccionSinOperacion> lista = (List<ShvCobTransaccionSinOperacion>) buscarUsandoQueryConParametros(qp);

		if (Validaciones.isCollectionNotEmpty(lista)) {
			return lista;
		}
		return null;
	}

//	@SuppressWarnings("unchecked")
//	@Override
//	public List<ShvCobTransaccionSinOperacion> buscarTransaccionSinOperacionPorIdOperacionYSistemaDependenciaYSociedadDependencia(Long idOperacion, SistemaEnum sistema, SociedadEnum sociedad) throws PersistenciaExcepcion {
//
//		StringBuffer consulta = new StringBuffer("");
//		QueryParametrosUtil qp = new QueryParametrosUtil();
//
//		consulta.append("select trans from ShvCobTransaccionSinOperacion as trans");
//		consulta.append(" where trans.idOperacion = ? ");
//		consulta.append(" and trans.sociedad = ? ");
//		consulta.append(" and trans.sistema = ? ");
//
//		qp.addCampoAlQuery(idOperacion, Types.NUMERIC);
//		qp.addCampoAlQuery(sociedad, Types.JAVA_OBJECT);
//		qp.addCampoAlQuery(sistema, Types.JAVA_OBJECT);
//		qp.setSql(consulta.toString());
//
//		List<ShvCobTransaccionSinOperacion> lista = (List<ShvCobTransaccionSinOperacion>) buscarUsandoQueryConParametros(qp);
//
//		if (Validaciones.isCollectionNotEmpty(lista)) {
//			return lista;
//		}
//		return null;
//	}
	@SuppressWarnings("unchecked")
	@Override
	public List<ShvCobTransaccionSinOperacion> buscarTransaccionSinOperacionPorIdOperacionYSistemaDependenciaYSociedadDependencia(Long idOperacion, SistemaEnum sistema, SociedadEnum sociedad) throws PersistenciaExcepcion {

		StringBuffer consulta = new StringBuffer("");
		QueryParametrosUtil qp = new QueryParametrosUtil();

		consulta.append("select trans from ShvCobTransaccionSinOperacion as trans");
		consulta.append(" where trans.idOperacion = ? ");
		consulta.append(" and trans.idSociedadDependencia = ? ");
		consulta.append(" and trans.sistemaDependencia = ? ");

		qp.addCampoAlQuery(idOperacion, Types.NUMERIC);
		qp.addCampoAlQuery(sociedad, Types.JAVA_OBJECT);
		qp.addCampoAlQuery(sistema, Types.JAVA_OBJECT);
		qp.setSql(consulta.toString());

		List<ShvCobTransaccionSinOperacion> lista = (List<ShvCobTransaccionSinOperacion>) buscarUsandoQueryConParametros(qp);

		if (Validaciones.isCollectionNotEmpty(lista)) {
			return lista;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public synchronized ShvWfWorkflow buscarWorkflowPorIdOperacion(Long idOperacion) throws PersistenciaExcepcion {

		String query = "select w " + " from ShvCobCobroSimple as cob " + " join cob.workflow as w " + " where cob.idOperacion = ? ";
		QueryParametrosUtil qp = new QueryParametrosUtil(query.toString());
		qp.addCampoAlQuery(idOperacion, Types.NUMERIC);

		List<ShvWfWorkflow> lista = (List<ShvWfWorkflow>) buscarUsandoQueryConParametros(qp);

		if (Validaciones.isCollectionNotEmpty(lista)) {
			return lista.get(0);
		}
		return null;
	}

	/**
	 * 
	 */
	public void guardarMedioPago(ShvCobMedioPago medioPago) throws PersistenciaExcepcion {
		try {
			@SuppressWarnings("unused")
			ShvCobMedioPago medioPagoBD = medioPagoRepositorio.save(medioPago);
			medioPagoRepositorio.flush();

		} catch (Exception e) {
			throw new PersistenciaExcepcion(e);
		}

	}

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<ShvCobMedioPagoCTA> buscarMedioPagoCtaIgualACta(Long idOperacion, Integer numeroTransaccion, ShvCobMedioPagoCTASinOperacion medPagoCta) throws PersistenciaExcepcion {

		String query = " from ShvCobMedioPagoCTA as cta " + " where cta.idMedioPago in (select med.idMedioPago " + " from ShvCobTransaccion as trans " + " join trans.shvCobMedioPago as med " + " join trans.operacion as op " + " where op.idOperacion = ? " + " and trans.numeroTransaccion > ?" + " and med.tipoMedioPago.idTipoMedioPago=21)" + " and cta.nroComprobante = ?" + " and cta.claseComprobante = ? " + " and cta.tipoComprobante = ? " + " and cta.sucursalComprobante = ?";

		QueryParametrosUtil qp = new QueryParametrosUtil(query.toString());
		qp.addCampoAlQuery(idOperacion, Types.NUMERIC);
		qp.addCampoAlQuery(numeroTransaccion, Types.NUMERIC);
		qp.addCampoAlQuery(medPagoCta.getNroComprobante(), Types.VARCHAR);
		qp.addCampoAlQuery(medPagoCta.getClaseComprobante(), Types.JAVA_OBJECT);
		qp.addCampoAlQuery(medPagoCta.getTipoComprobante(), Types.JAVA_OBJECT);
		qp.addCampoAlQuery(medPagoCta.getSucursalComprobante(), Types.VARCHAR);

		List<ShvCobMedioPagoCTA> lista = (List<ShvCobMedioPagoCTA>) buscarUsandoQueryConParametros(qp);

		return lista;
	}

	@Override
	public Integer hayHilosCobrosVivos(TipoImputacionEnum tipoImputacionEnum) throws PersistenciaExcepcion {

		QueryParametrosUtil parametros = new QueryParametrosUtil();
		Integer resultado = 0;
		String query = "select Count (*) as hilos_vivos from shv_cob_proc_hilos_cobros where estado ='EN_CURSO' and proceso_batch='" + tipoImputacionEnum.name() +"'";

		parametros.setSql(query);
		List<Map<String, Object>> listaResultadoQuery = buscarUsandoSQL(parametros);

		for (Map<String, Object> archivo : listaResultadoQuery) {

			if (!Validaciones.isObjectNull(archivo.get("hilos_vivos"))) {
				resultado = Integer.valueOf(archivo.get("hilos_vivos").toString());
			}
		}

		return resultado;
	}

	@Override
	public Integer obtenerEstadoDelHilo(Long idOperacion) throws PersistenciaExcepcion {

		QueryParametrosUtil parametros = new QueryParametrosUtil();
		Integer resultado = 0;
		String query = "select count(*) as hilos_en_curso from shv_cob_proc_hilos_cobros where estado='EN_CURSO' and " + "id_operacion =" + idOperacion;

		parametros.setSql(query);
		List<Map<String, Object>> listaResultadoQuery = buscarUsandoSQL(parametros);

		for (Map<String, Object> archivo : listaResultadoQuery) {

			if (!Validaciones.isObjectNull(archivo.get("hilos_en_curso"))) {
				resultado = Integer.valueOf(archivo.get("hilos_en_curso").toString());
			}
		}

		return resultado;
	}

	@Override
	public Long obtenerOrdenCap(String idProveedor) throws PersistenciaExcepcion {

		Long orden = new Long(0);

		String query = "select max (orden + 1) as orden  from shv_cob_med_pag_doc_cap where id_proveedor=? and orden is not null";

		QueryParametrosUtil qp = new QueryParametrosUtil(query);
		qp.addCampoAlQuery(idProveedor, Types.VARCHAR);

		List<Map<String, Object>> listaResultadoQuery;

		try {
			listaResultadoQuery = buscarUsandoSQL(qp);

			for (Map<String, Object> archivo : listaResultadoQuery) {

				if (!Validaciones.isObjectNull(archivo.get("ORDEN"))) {
					orden = Long.valueOf(archivo.get("ORDEN").toString());
				}
			}

		} catch (PersistenciaExcepcion e) {
			throw new PersistenciaExcepcion(e.getMessage(), e);
		}

		return orden;
	}

	@Override
	public String obtenerCuit(Long idCobro, Long idClienteLegado) throws PersistenciaExcepcion {

		String cuit = "";

		String query = "select cuit from shv_cob_ed_cliente where id_cobro=? and id_cliente_legado=?";

		QueryParametrosUtil qp = new QueryParametrosUtil(query);
		qp.addCampoAlQuery(idCobro, Types.NUMERIC);
		qp.addCampoAlQuery(idClienteLegado, Types.NUMERIC);

		List<Map<String, Object>> listaResultadoQuery;

		try {
			listaResultadoQuery = buscarUsandoSQL(qp);

			for (Map<String, Object> archivo : listaResultadoQuery) {

				if (!Validaciones.isObjectNull(archivo.get("CUIT"))) {
					cuit = archivo.get("CUIT").toString();
				}
			}

		} catch (PersistenciaExcepcion e) {
			throw new PersistenciaExcepcion(e.getMessage(), e);
		}

		return cuit;
	}

	@Override
	public ResultadoBusquedaDocumentoScard obtenerDocumentoScardCobroPorIdDocumento(Long idDocumento) throws PersistenciaExcepcion {

		try {
			ResultadoBusquedaDocumentoScard resultado = new ResultadoBusquedaDocumentoScard();

			StringBuffer consulta = new StringBuffer("");

			consulta.append(" select 'Modelo nuevo' as tipo_cobro ");
			consulta.append(" , cobro.id_analista as id_analista ");
			consulta.append(" , wh.fecha_modificacion as fecha_ultima_modificacion ");
			consulta.append(" , cobro.id_operacion_masiva as id_operacion_masiva ");
			consulta.append(" , cobro.moneda_operacion as moneda ");
			consulta.append(" , cliente.id_cliente_legado as id_cliente_legado ");
			consulta.append(" , cliente.razon_social as razon_social ");
			consulta.append(" , cliente.cuit as cuit ");
			consulta.append(" , wh.fecha_modificacion as fecha_estado ");
			consulta.append(" , wh.estado ");
			consulta.append(" from shv_cob_documento doc ");
			consulta.append(" left join shv_cob_ed_cobro cobro on doc.id_operacion = cobro.id_operacion ");
			consulta.append(" left join Shv_Cob_Ed_Cliente cliente on (cobro.id_cobro = cliente.id_cobro and doc.id_cliente_legado = cliente.id_cliente_legado) ");
			consulta.append(" inner join shv_wf_workflow_estado_hist wh on (cobro.id_workflow = wh.id_workflow ");
			consulta.append("   and wh.id_workflow_estado_hist = ( ");
			consulta.append("     select min (id_workflow_estado_hist) from shv_wf_workflow_estado_hist where id_workflow = cobro.id_workflow) ");
			consulta.append(" ) ");
			consulta.append(" where doc.id_documento = ? ");
			consulta.append(" union ");
			consulta.append(" select 'Modelo viejo' as tipo_cobro ");
			consulta.append(" , wh.usuario_modificacion as id_analista  ");
			consulta.append(" , we.fecha_modificacion as fecha_ultima_modificacion  ");
			consulta.append(" , null as id_operacion_masiva  ");
			consulta.append(" , null as moneda  ");
			consulta.append(" , doc.id_cliente_legado as id_cliente_legado  ");
			consulta.append(" , tabla.razon as razon_social  ");
			consulta.append(" , tabla.cuit as cuit  ");
			consulta.append(" , wh.fecha_modificacion as fecha_estado  ");
			consulta.append(" , wh.estado  ");
			consulta.append(" from  ");
			consulta.append(" (select trans.id_operacion as id_operacion, ");
			consulta.append(" fac.id_cliente_legado as cliente, ");
			consulta.append(" fac.cuit as cuit, ");
			consulta.append(" decode(fac.sistema_origen,'MIC',mic.razon_social_cliente_legado,'CALIPSO',clp.razon_social_cliente_legado) as Razon ");
			consulta.append(" from shv_cob_transaccion trans ");
			consulta.append(" join shv_cob_factura fac on (trans.id_transaccion=fac.id_transaccion) ");
			consulta.append(" left join shv_cob_factura_calipso clp on (clp.id_factura=fac.id_factura) ");
			consulta.append(" left join shv_cob_factura_mic mic on (mic.id_factura=fac.id_factura)) tabla, ");
			consulta.append(" shv_cob_documento doc ");
			consulta.append(" join shv_cob_cobro cobro on (doc.id_operacion = cobro.id_operacion  ");
			consulta.append(" and cobro.id_operacion not in (  ");
			consulta.append(" select id_operacion from shv_cob_ed_cobro where id_operacion = cobro.id_operacion) ) ");
			consulta.append(" inner join shv_wf_workflow_estado we on cobro.id_workflow=we.id_workflow  ");
			consulta.append(" inner join shv_wf_workflow_estado_hist wh on (cobro.id_workflow = wh.id_workflow  ");
			consulta.append(" and wh.id_workflow_estado_hist = (  ");
			consulta.append(" select min (id_workflow_estado_hist) from shv_wf_workflow_estado_hist where id_workflow = cobro.id_workflow)  ");
			consulta.append(" ) ");
			consulta.append(" where doc.id_documento  = ? and tabla.id_operacion=doc.id_operacion and tabla.cliente=doc.id_cliente_legado ");

			QueryParametrosUtil qp = new QueryParametrosUtil(consulta.toString());
			qp.addCampoAlQuery(idDocumento, Types.NUMERIC);
			qp.addCampoAlQuery(idDocumento, Types.NUMERIC);

			List<Map<String, Object>> listaResultadoQuery = buscarUsandoSQL(qp);

			Map<String, Object> archivo = listaResultadoQuery.get(0);

			if (!Validaciones.isObjectNull(archivo.get("ID_ANALISTA"))) {
				resultado.setIdAnalista(String.valueOf(archivo.get("ID_ANALISTA").toString()));
			}
			if (!Validaciones.isObjectNull(archivo.get("FECHA_ULTIMA_MODIFICACION"))) {
				resultado.setFechaUltimaModificacion((Date) archivo.get("FECHA_ULTIMA_MODIFICACION"));
			}
			if (!Validaciones.isObjectNull(archivo.get("ID_OPERACION_MASIVA"))) {
				resultado.setIdOperacionMasiva(Long.valueOf(archivo.get("ID_OPERACION_MASIVA").toString()));
			}
			if (!Validaciones.isObjectNull(archivo.get("MONEDA"))) {
				resultado.setMonedaOperacion(MonedaEnum.getEnumByName(String.valueOf(archivo.get("MONEDA").toString())));
			}
			if (!Validaciones.isObjectNull(archivo.get("ID_CLIENTE_LEGADO"))) {
				resultado.setIdClienteLegado(Long.valueOf(archivo.get("ID_CLIENTE_LEGADO").toString()));
			}
			if (!Validaciones.isObjectNull(archivo.get("RAZON_SOCIAL"))) {
				resultado.setRazonSocial(String.valueOf(archivo.get("RAZON_SOCIAL").toString()));
			}
			if (!Validaciones.isObjectNull(archivo.get("CUIT"))) {
				resultado.setCuit(String.valueOf(archivo.get("CUIT").toString()));
			}

			return resultado;

		} catch (PersistenciaExcepcion e) {
			throw new PersistenciaExcepcion(e.getMessage(), e);
		}

	}

	@Override
	public ResultadoBusquedaDocumentoScard obtenerDocumentoScardDescobroPorIdDocumento(Long idDocumento) throws PersistenciaExcepcion {

		try {
			ResultadoBusquedaDocumentoScard resultado = new ResultadoBusquedaDocumentoScard();

			StringBuffer consulta = new StringBuffer("");

			consulta.append(" select 'Modelo nuevo' as tipo_descobro ");
			consulta.append("   , descobro.id_analista as id_analista ");
			consulta.append("   , we.fecha_modificacion as fecha_ultima_modificacion ");
			consulta.append("   , cobro.id_operacion_masiva as id_operacion_masiva ");
			consulta.append("   , cobro.moneda_operacion as moneda ");
			consulta.append("   , cliente.id_cliente_legado as id_cliente_legado ");
			consulta.append("   , cliente.razon_social as razon_social ");
			consulta.append("   , cliente.cuit as cuit ");
			consulta.append("   , wh.fecha_modificacion as fecha_estado ");
			consulta.append("   , wh.estado ");
			consulta.append(" from ");
			consulta.append("   shv_cob_documento doc ");
			consulta.append("   inner join shv_cob_descobro descobro on doc.id_operacion = descobro.id_operacion ");
			consulta.append("   inner join shv_cob_ed_cobro cobro on cobro.id_cobro = descobro.id_cobro ");
			consulta.append("   inner join shv_cob_ed_cliente cliente on (cobro.id_cobro = cliente.id_cobro and doc.id_cliente_legado = cliente.id_cliente_legado) ");
			consulta.append("   inner join shv_wf_workflow_estado we on descobro.id_workflow = we.id_workflow ");
			consulta.append("   inner join shv_wf_workflow_estado_hist wh on (descobro.id_workflow = wh.id_workflow ");
			consulta.append("   and wh.id_workflow_estado_hist = ( ");
			consulta.append("     select min (id_workflow_estado_hist) from shv_wf_workflow_estado_hist where id_workflow = descobro.id_workflow) ");
			consulta.append("   ) ");
			consulta.append(" where ");
			consulta.append("   doc.id_documento = ? ");
			consulta.append(" union ");
			consulta.append(" select 'Modelo viejo' as tipo_descobro ");
			consulta.append(" , wh.usuario_modificacion as id_analista  ");
			consulta.append(" , we.fecha_modificacion as fecha_ultima_modificacion  ");
			consulta.append(" , null as id_operacion_masiva  ");
			consulta.append(" , null as moneda  ");
			consulta.append(" , doc.id_cliente_legado as id_cliente_legado  ");
			consulta.append(" , tabla.razon as razon_social  ");
			consulta.append(" , tabla.cuit as cuit  ");
			consulta.append(" , wh.fecha_modificacion as fecha_estado  ");
			consulta.append(" , wh.estado  ");
			consulta.append(" from  ");
			consulta.append(" (select trans.id_operacion as id_operacion, ");
			consulta.append(" fac.id_cliente_legado as cliente, ");
			consulta.append(" fac.cuit as cuit, ");
			consulta.append(" decode(fac.sistema_origen,'MIC',mic.razon_social_cliente_legado,'CALIPSO',clp.razon_social_cliente_legado) as Razon ");
			consulta.append(" from shv_cob_transaccion trans ");
			consulta.append(" join shv_cob_factura fac on (trans.id_transaccion=fac.id_transaccion) ");
			consulta.append(" left join shv_cob_factura_calipso clp on (clp.id_factura=fac.id_factura) ");
			consulta.append(" left join shv_cob_factura_mic mic on (mic.id_factura=fac.id_factura)) tabla, ");
			consulta.append(" shv_cob_documento doc  ");
			consulta.append(" inner join shv_cob_descobro descobro on doc.id_operacion = descobro.id_operacion  ");
			consulta.append(" join shv_cob_cobro cobro on (descobro.id_cobro = cobro.id_cobro  ");
			consulta.append(" and cobro.id_operacion not in (  ");
			consulta.append(" select id_operacion from shv_cob_ed_cobro where id_operacion = cobro.id_operacion) )  ");
			consulta.append(" inner join shv_wf_workflow_estado we on descobro.id_workflow = we.id_workflow  ");
			consulta.append(" inner join shv_wf_workflow_estado_hist wh on (descobro.id_workflow = wh.id_workflow  ");
			consulta.append(" and wh.id_workflow_estado_hist = (  ");
			consulta.append(" select min (id_workflow_estado_hist) from shv_wf_workflow_estado_hist where id_workflow = descobro.id_workflow) )  ");
			consulta.append(" where  ");
			consulta.append(" doc.id_documento = ? and ");
			consulta.append(" tabla.id_operacion=doc.id_operacion and tabla.cliente=doc.id_cliente_legado ");

			QueryParametrosUtil qp = new QueryParametrosUtil(consulta.toString());
			qp.addCampoAlQuery(idDocumento, Types.NUMERIC);
			qp.addCampoAlQuery(idDocumento, Types.NUMERIC);

			List<Map<String, Object>> listaResultadoQuery = buscarUsandoSQL(qp);

			if (Validaciones.isCollectionNotEmpty(listaResultadoQuery)) {
				Map<String, Object> archivo = listaResultadoQuery.get(0);

				if (!Validaciones.isObjectNull(archivo.get("ID_ANALISTA"))) {
					resultado.setIdAnalista(String.valueOf(archivo.get("ID_ANALISTA").toString()));
				}
				if (!Validaciones.isObjectNull(archivo.get("FECHA_ULTIMA_MODIFICACION"))) {
					resultado.setFechaUltimaModificacion((Date) archivo.get("FECHA_ULTIMA_MODIFICACION"));
				}
				if (!Validaciones.isObjectNull(archivo.get("ID_OPERACION_MASIVA"))) {
					resultado.setIdOperacionMasiva(Long.valueOf(archivo.get("ID_OPERACION_MASIVA").toString()));
				}
				if (!Validaciones.isObjectNull(archivo.get("MONEDA"))) {
					resultado.setMonedaOperacion(MonedaEnum.getEnumByName(String.valueOf(archivo.get("MONEDA").toString())));
				}
				if (!Validaciones.isObjectNull(archivo.get("ID_CLIENTE_LEGADO"))) {
					resultado.setIdClienteLegado(Long.valueOf(archivo.get("ID_CLIENTE_LEGADO").toString()));
				}
				if (!Validaciones.isObjectNull(archivo.get("RAZON_SOCIAL"))) {
					resultado.setRazonSocial(String.valueOf(archivo.get("RAZON_SOCIAL").toString()));
				}
				if (!Validaciones.isObjectNull(archivo.get("CUIT"))) {
					resultado.setCuit(String.valueOf(archivo.get("CUIT").toString()));
				}

				return resultado;
			} else {
				return null;
			}
		} catch (PersistenciaExcepcion e) {
			throw new PersistenciaExcepcion(e.getMessage(), e);
		}
	}

	@Override
	public String buscarUsuarioConfirmacionManual(Long idOperacion) throws PersistenciaExcepcion {

		String usuario = "";

		String query = "select ID_REFERENTE_OPER_EXTERNA, ID_REFERENTE_CAJA from shv_cob_ed_cobro where id_operacion=?";

		QueryParametrosUtil qp = new QueryParametrosUtil(query);
		qp.addCampoAlQuery(idOperacion, Types.NUMERIC);

		List<Map<String, Object>> listaResultadoQuery;

		try {
			listaResultadoQuery = buscarUsandoSQL(qp);

			for (Map<String, Object> archivo : listaResultadoQuery) {

				if (!Validaciones.isObjectNull(archivo.get("ID_REFERENTE_OPER_EXTERNA"))) {
					usuario = archivo.get("ID_REFERENTE_OPER_EXTERNA").toString();
				} else if (!Validaciones.isObjectNull(archivo.get("ID_REFERENTE_CAJA"))) {
					usuario = archivo.get("ID_REFERENTE_CAJA").toString();
				}
			}

		} catch (PersistenciaExcepcion e) {
			throw new PersistenciaExcepcion(e.getMessage(), e);
		}

		return usuario;

	}

	@Override
	public String buscarCuentaContablePorDefault(List<String> listaIdClienteLegado) throws PersistenciaExcepcion {

		String cuentaContable = "";

		String query = "select  distinct cuenta_contable from shv_param_cliente where id_cliente_legado in (";
		QueryParametrosUtil qp = new QueryParametrosUtil();

		for (String string : listaIdClienteLegado) {
			query += "?,";
			qp.addCampoAlQuery(string, Types.NUMERIC);
		}
		query = query.substring(0, query.length() - 1) + ")";
		qp.setSql(query);

		List<Map<String, Object>> listaResultadoQuery;

		try {
			listaResultadoQuery = buscarUsandoSQL(qp);

			if (Validaciones.isCollectionNotEmpty(listaResultadoQuery) && listaResultadoQuery.size() == 1) {

				for (Map<String, Object> archivo : listaResultadoQuery) {

					if (!Validaciones.isObjectNull(archivo.get("CUENTA_CONTABLE"))) {
						cuentaContable = archivo.get("CUENTA_CONTABLE").toString();
					}
				}
			}

		} catch (PersistenciaExcepcion e) {
			throw new PersistenciaExcepcion(e.getMessage(), e);
		}

		return cuentaContable;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ShvCobTransaccionSinOperacion> buscarTransaccionSinOperacionPorIdOperacionYGrupo(Long idOperacion, Long grupo) throws PersistenciaExcepcion {

		String query = "select t from ShvCobTransaccionSinOperacion as t where t.grupo = ? and t.idOperacion= ? ";

		QueryParametrosUtil qp = new QueryParametrosUtil(query.toString());
		qp.addCampoAlQuery(grupo, Types.NUMERIC);
		qp.addCampoAlQuery(idOperacion, Types.NUMERIC);

		List<ShvCobTransaccionSinOperacion> lista = (List<ShvCobTransaccionSinOperacion>) buscarUsandoQueryConParametros(qp);

		if (Validaciones.isCollectionNotEmpty(lista)) {
			return lista;
		}
		return null;

	}

	@Override
	public ShvCobTransaccionSinOperacion buscarTransaccionSinOperacionPorIdOperacionYNumeroTransaccion(Long idOperacion, Integer numeroTransaccion) throws PersistenciaExcepcion {

		String query = "select t from ShvCobTransaccionSinOperacion as t where t.numeroTransaccion = ? and t.idOperacion= ? ";

		QueryParametrosUtil qp = new QueryParametrosUtil(query.toString());
		qp.addCampoAlQuery(numeroTransaccion, Types.NUMERIC);
		qp.addCampoAlQuery(idOperacion, Types.NUMERIC);
		
		return (ShvCobTransaccionSinOperacion) buscarUsandoQueryConParametros(qp).get(0);

	}

	/**
	 * Busco los distintos estados de los grupos
	 * 
	 * @param idOperacion
	 * @return
	 * @throws NegocioExcepcion
	 */
	public List<EstadoTransaccionEnum> buscarEstadoDeGrupos(Long idOperacion) throws NegocioExcepcion {

		List<EstadoTransaccionEnum> lista = new ArrayList<EstadoTransaccionEnum>();

		try {

			String query = "select distinct estado from shv_cob_transaccion where id_operacion=?";

			QueryParametrosUtil qp = new QueryParametrosUtil();
			qp.addCampoAlQuery(idOperacion, Types.NUMERIC);
			qp.setSql(query);

			List<Map<String, Object>> listaResultadoQuery;

			listaResultadoQuery = buscarUsandoSQL(qp);

			for (Map<String, Object> archivo : listaResultadoQuery) {

				if (!Validaciones.isObjectNull(archivo.get("ESTADO"))) {
					lista.add(EstadoTransaccionEnum.getEnumByName(archivo.get("ESTADO").toString()));
				}
			}

		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}

		return lista;

	}
	/**
	 * 
	 */
	@Override
	public ShvCobTransaccionSinOperacion buscarTransaccionAProcesarPorErrorDesapropiacionPorIdOperacion(Long idOperacion) throws PersistenciaExcepcion{
		
		String query = "select t from ShvCobTransaccionSinOperacion as t where t.idTransaccion in ( "+
				"select min(trans.idTransaccion ) "+
				"from ShvCobTransaccionSimple trans, ShvCobCobroSimple cob, ShvWfWorkflowEstado we "+  
				"where trans.idOperacion  in (?) "+
				"and cob.idOperacion=trans.idOperacion " +
				"and cob.workflow.idWorkflow=we.workflow.idWorkflow "+ 
				"and we.estado in ('COB_PROCESO_APLICACION_EXTERNA','COB_PENDIENTE_CONFIRMACION_MANUAL_Y_PROCESO_APLICACION_EXTERNA') "+
				"and trans.estadoProcesamiento in ('EN_PROCESO_DESAPROPIACION_POR_ERROR_DESAPROPIACION') "+ 
				"and trans.grupo > 0 "+
				"and cob.idOperacion not in ("
						+ "select trans2.idOperacion "
						+ "from ShvCobTransaccionSimple trans2 "
						+ "where trans2.estadoProcesamiento in ('ERROR_MEDIO_PAGO','ERROR_DEUDA','ERROR_MEDIO_PAGO_DEUDA','ERROR_TRATAMIENTO')"
						+ "and trans2.grupo=trans.grupo)) order by t.grupo asc";
		
		QueryParametrosUtil qp = new QueryParametrosUtil(query.toString());
		qp.addCampoAlQuery(idOperacion, Types.NUMERIC);

		@SuppressWarnings("unchecked")
		List<ShvCobTransaccionSinOperacion> lista = (List<ShvCobTransaccionSinOperacion>) buscarUsandoQueryConParametros(qp);

		if (Validaciones.isCollectionNotEmpty(lista)) {
			return lista.get(0);
		}
		return null;
	}
	
	@Override
	public List<ControlDeHilosCobros> buscarHilosEnProceso(Integer tiempoLimite) throws PersistenciaExcepcion {
		
		Calendar fechaActual = Calendar.getInstance();
        fechaActual.setTimeZone(TimeZone.getTimeZone("GMT-3:00"));
        fechaActual.add(Calendar.MINUTE, -tiempoLimite);
        
		System.out.println(fechaActual.getTime());
//		String query = "select hilos from ShvCobProcHilosCobros as hilos where hilos.estado=? AND hilos.fechaFin IS NULL "
//				+ "AND hilos.fechaInicio < ?";
		
		
		String query ="select id_operacion, fecha_inicio from shv_cob_proc_hilos_cobros where estado=? AND fecha_fin IS NULL AND fecha_inicio < ?";
		
 		QueryParametrosUtil qp = new QueryParametrosUtil();
		qp.addCampoAlQuery(EstadoProcesamientoHilosEnum.EN_CURSO,Types.VARCHAR);
		qp.addCampoAlQuery(fechaActual.getTime(),Types.DATE);
		qp.setSql(query);
		
		
		
		List<Map<String, Object>> listaResultadoQuery = buscarUsandoSQL(qp);

		List<ControlDeHilosCobros> listaHilosEnProceso = new ArrayList<ControlDeHilosCobros>();

		for (Map<String, Object> archivo : listaResultadoQuery) {

			// Obtengo los datos del cobro
			ControlDeHilosCobros controlHilo = new ControlDeHilosCobros();

			if (!Validaciones.isObjectNull(archivo.get("ID_OPERACION"))){
				controlHilo.setIdOperacion(Long.valueOf(archivo.get("ID_OPERACION").toString()));
				
			}
			
			if (!Validaciones.isObjectNull(archivo.get("FECHA_INICIO"))){
				controlHilo.setFechaInicioHilo((Date) archivo.get("FECHA_INICIO"));
			}
			
			listaHilosEnProceso.add(controlHilo);
		}

		if (Validaciones.isCollectionNotEmpty(listaHilosEnProceso)) {
			return listaHilosEnProceso;
		}
		return null;
	}
	
	@Override
	public List<ControlDeHilosCobros> buscarCobrosEnProceso(List<Estado> listaEstadosEnum)throws PersistenciaExcepcion {
		
//		String query = "select cobro from ShvCobCobroSimple as cobro, ShvWfWorkflowEstado we where cobro.workflow.idWorkflow=we.workflow.idWorkflow and we.estado in(";
		
		String query ="select cob.id_operacion, wf.estado, wf.fecha_modificacion from shv_cob_cobro cob "
				+ " inner join shv_wf_workflow_estado wf on wf.id_workflow = cob.id_workflow where estado in (";
		
		for (int i=0; i < listaEstadosEnum.size();i++){
			query+="?,";
		}
		
		query = query.substring(0, query.length()-1);
		query +=")";
		
//		query +=" order by we.fechaModificacion desc";
				
		
 		QueryParametrosUtil qp = new QueryParametrosUtil();
		
		for (Estado estado : listaEstadosEnum) {
			qp.addCampoAlQuery(estado,Types.VARCHAR);
			
		}

		qp.setSql(query);
		
		List<Map<String, Object>> listaResultadoQuery = buscarUsandoSQL(qp);

		List<ControlDeHilosCobros> listaCobrosEnProceso = new ArrayList<ControlDeHilosCobros>();

		for (Map<String, Object> archivo : listaResultadoQuery) {

			// Obtengo los datos del cobro
			ControlDeHilosCobros controlHilo = new ControlDeHilosCobros();

			if (!Validaciones.isObjectNull(archivo.get("ID_OPERACION"))){
				controlHilo.setIdOperacion(Long.valueOf(archivo.get("ID_OPERACION").toString()));
				
			}
			
			if (!Validaciones.isObjectNull(archivo.get("FECHA_MODIFICACION"))){
				controlHilo.setFechaUltimaModificacionWF((Date) archivo.get("FECHA_MODIFICACION"));
			}
			
			if (!Validaciones.isObjectNull(archivo.get("ESTADO"))){
				controlHilo.setEstadoCobro(Estado.getEnumByName(archivo.get("ESTADO").toString()));
			}
			
			listaCobrosEnProceso.add(controlHilo);
		}

		if (Validaciones.isCollectionNotEmpty(listaCobrosEnProceso)) {
			return listaCobrosEnProceso;
		}
		return null;
		
		
	}

	/************************************************************************************
	 * Getters & Setters
	 ************************************************************************************/
	public CobroRepositorio getCobroRepositorio() {
		return cobroRepositorio;
	}

	public void setCobroRepositorio(CobroRepositorio cobroRepositorio) {
		this.cobroRepositorio = cobroRepositorio;
	}

}
