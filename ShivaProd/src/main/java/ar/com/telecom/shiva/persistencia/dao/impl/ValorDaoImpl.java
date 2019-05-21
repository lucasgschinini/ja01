package ar.com.telecom.shiva.persistencia.dao.impl;

import java.math.BigDecimal;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.enumeradores.EmpresaEnum;
import ar.com.telecom.shiva.base.enumeradores.MotivoSuspensionEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoValorEnum;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.persistencia.bean.ResultadoBusquedaBoletaDeposito;
import ar.com.telecom.shiva.persistencia.dao.Dao;
import ar.com.telecom.shiva.persistencia.dao.IValorDao;
import ar.com.telecom.shiva.persistencia.dao.util.QueryParametrosUtil;
import ar.com.telecom.shiva.persistencia.modelo.ShvAvcRegistroAvcValor;
import ar.com.telecom.shiva.persistencia.modelo.ShvBolBoleta;
import ar.com.telecom.shiva.persistencia.modelo.ShvValBoletaDepositoCheque;
import ar.com.telecom.shiva.persistencia.modelo.ShvValBoletaDepositoChequePagoDiferido;
import ar.com.telecom.shiva.persistencia.modelo.ShvValBoletaDepositoEfectivo;
import ar.com.telecom.shiva.persistencia.modelo.ShvValValor;
import ar.com.telecom.shiva.persistencia.modelo.ShvValValorCheque;
import ar.com.telecom.shiva.persistencia.modelo.ShvValValorChequePagoDiferido;
import ar.com.telecom.shiva.persistencia.modelo.ShvValValorInterdeposito;
import ar.com.telecom.shiva.persistencia.modelo.ShvValValorPorReversion;
import ar.com.telecom.shiva.persistencia.modelo.ShvValValorRetencion;
import ar.com.telecom.shiva.persistencia.modelo.ShvValValorTransferencia;
import ar.com.telecom.shiva.persistencia.modelo.ShvValValoresVista;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvValValorSimple;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvValValorSimplificado;
import ar.com.telecom.shiva.persistencia.repository.BoletaRepositorio;
import ar.com.telecom.shiva.persistencia.repository.ValorPorReversionRepositorio;
import ar.com.telecom.shiva.persistencia.repository.ValorRepositorio;
import ar.com.telecom.shiva.persistencia.repository.ValorRepositorioSimplificado;
import ar.com.telecom.shiva.presentacion.bean.UsuarioSesion;
import ar.com.telecom.shiva.presentacion.bean.dto.ValorDto;
import ar.com.telecom.shiva.presentacion.bean.filtro.BoletaConValorFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.Filtro;

public class ValorDaoImpl extends Dao implements IValorDao {

	private static final int ID_RET_IIBB = 1;
	@Autowired
	ValorRepositorio valorRepositorio;
	@Autowired
	ValorPorReversionRepositorio valorPorReversionRepositorio;
	@Autowired
	BoletaRepositorio boletaRepositorio;
	@Autowired
	ValorRepositorioSimplificado valorRepositorioSimplificado;

	public ShvValValor insertarValor(ShvValValor valor)
			throws PersistenciaExcepcion {
		try {
			ShvValValor valorBD = valorRepositorio.save(valor);
			valorRepositorio.flush();
			return valorBD;
		} catch (Exception e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	public ShvValValorPorReversion insertarValorAux(
			ShvValValorPorReversion valor) throws PersistenciaExcepcion {
		try {
			ShvValValorPorReversion valorBD = valorPorReversionRepositorio
					.save(valor);
			valorPorReversionRepositorio.flush();
			return valorBD;
		} catch (Exception e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	@SuppressWarnings("unchecked")
	public ShvValValor buscarValorTipoDepositoPorIdBoleta(Long idBoleta)
			throws PersistenciaExcepcion {
		try {

			String query = "select che from ShvValBoletaDepositoCheque as che "
					+ " join che.boleta as bol where bol.idBoleta = ? ";
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addParametro(idBoleta);
			List<ShvValBoletaDepositoCheque> boletaDepositoCheque = buscarUsandoQueryConParametros(qp);

			if (Validaciones.isCollectionNotEmpty(boletaDepositoCheque)) {
				return boletaDepositoCheque.get(0).getValor();

			} else {
				query = "select chedf from ShvValBoletaDepositoChequePagoDiferido as chedf "
						+ " join chedf.boleta as bol where bol.idBoleta = ?";
				qp = new QueryParametrosUtil(query);
				qp.addParametro(idBoleta);

				List<ShvValBoletaDepositoChequePagoDiferido> boletaDepositoChequePagoDiferido = buscarUsandoQueryConParametros(qp);
				if (Validaciones
						.isCollectionNotEmpty(boletaDepositoChequePagoDiferido)) {
					return boletaDepositoChequePagoDiferido.get(0).getValor();
				} else {
					query = "select efv from ShvValBoletaDepositoEfectivo as efv "
							+ " join efv.boleta as bol where bol.idBoleta = ? ";
					qp = new QueryParametrosUtil(query);
					qp.addParametro(idBoleta);

					List<ShvValBoletaDepositoEfectivo> boletaDepositoEfectivo = buscarUsandoQueryConParametros(qp);
					if (Validaciones
							.isCollectionNotEmpty(boletaDepositoEfectivo)) {
						return boletaDepositoEfectivo.get(0).getValor();
					}
				}
			}

			return null;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	public Collection<ResultadoBusquedaBoletaDeposito> buscarValorBoleta()
			throws PersistenciaExcepcion {
		List<ResultadoBusquedaBoletaDeposito> resultado = new ArrayList<ResultadoBusquedaBoletaDeposito>();

		QueryParametrosUtil qp = new QueryParametrosUtil(
				getQueryResultadoBoletaDeposito());

		List<Map<String, Object>> listaResultadoQuery = buscarUsandoSQL(qp);

		for (Map<String, Object> archivo : listaResultadoQuery) {

			ResultadoBusquedaBoletaDeposito resultadoBusquedaArchivo = new ResultadoBusquedaBoletaDeposito();

			try {

				if (!Validaciones.isObjectNull(archivo.get("FECHA_DEPOSITO"))) {
					resultadoBusquedaArchivo.setFechaDeposito(Utilidad
							.deserializeAndFormatDate(
									archivo.get("FECHA_DEPOSITO").toString(),
									Utilidad.DATE_TIME_FULL_FORMAT_BASE_DATOS));
				} else {
					resultadoBusquedaArchivo.setFechaDeposito(null);
				}

				resultadoBusquedaArchivo.setIdBoleta(archivo.get("ID_BOLETA")
						.toString());
				resultadoBusquedaArchivo.setIdAcuerdo(archivo.get("ID_ACUERDO")
						.toString());
				resultadoBusquedaArchivo.setIdClienteLegado(archivo.get(
						"ID_CLIENTE_LEGADO").toString());
				resultadoBusquedaArchivo.setImporte(Utilidad
						.stringToBigDecimal(archivo.get("IMPORTE").toString()));
				resultadoBusquedaArchivo.setNroBoleta(Long.valueOf(archivo.get(
						"NUMERO_BOLETA").toString()));

				if (!Validaciones.isObjectNull(archivo.get("NUMERO_CHEQUE"))) {
					resultadoBusquedaArchivo.setNroCheque(getValorLong(archivo
							.get("NUMERO_CHEQUE")));
				} else {
					resultadoBusquedaArchivo.setNroCheque(null);
				}

			} catch (ParseException e) {
				throw new PersistenciaExcepcion(e.getMessage(), e);
			}

			resultadoBusquedaArchivo.setIdBancoOrigen(getValorLong(archivo
					.get("ID_BANCO_ORIGEN")));

			resultado.add(resultadoBusquedaArchivo);
		}
		return resultado;
	}

	private Long getValorLong(Object archivo) {
		if (!Validaciones.isObjectNull(archivo)) {
			return Long.valueOf(archivo.toString());
		} else {
			return 0L;
		}
	}

	/**
	 * Retorna el query que se debe ejecutar para poder cargar la tabla de
	 * Resultados Conciliacion.
	 * 
	 * @param filtro
	 * @return
	 */
	private String getQueryResultadoBoletaDeposito() {

		String query = "select "
				+ "shv_bol_boleta.id_boleta, "
				+ "shv_avc_registro_avc.id_acuerdo, "
				+ "shv_val_valor.id_cliente_legado, "
				+ "shv_avc_registro_avc.importe, "
				+ "shv_bol_boleta.numero_boleta, "
				+ "shv_val_boleta_dep_cheque.numero_cheque, "
				+ "shv_val_boleta_dep_cheque.id_banco_origen, "
				+ "shv_val_boleta_dep_cheque.fecha_deposito, "
				+ "null, "
				+ "null, "
				+ "null, "
				+ "null, "
				+ "null "
				+ "from shv_avc_reg_avc_boleta, "
				+ "shv_avc_registro_avc, shv_bol_boleta, "
				+ "shv_wf_workflow_estado, "
				+ "shv_val_valor, "
				+ "shv_val_boleta_dep_cheque "
				+ "where shv_avc_reg_avc_boleta.id_registro_avc = shv_avc_registro_avc.id_registro_avc "
				+ "and shv_bol_boleta.id_boleta = shv_avc_reg_avc_boleta.id_boleta "
				+ "and shv_bol_boleta.id_boleta = shv_val_boleta_dep_cheque.id_boleta "
				+ "and shv_val_valor.id_valor = shv_val_boleta_dep_cheque.id_valor "
				+ "and shv_avc_registro_avc.id_workflow = shv_wf_workflow_estado.id_workflow "
				+ "and shv_wf_workflow_estado.estado = 'AVC_CONCILIACION_SUGERIDA' "
				+ "union "
				+ "select "
				+ "shv_bol_boleta.id_boleta, "
				+ "shv_avc_registro_avc.id_acuerdo, "
				+ "shv_val_valor.id_cliente_legado, "
				+ "shv_avc_registro_avc.importe, "
				+ "shv_bol_boleta.numero_boleta, "
				+ "null, "
				+ "null, "
				+ "null, "
				+ "shv_val_boleta_dep_cheque_pd.numero_cheque, "
				+ "shv_val_boleta_dep_cheque_pd.id_banco_origen, "
				+ "shv_val_boleta_dep_cheque_pd.fecha_deposito, "
				+ "shv_val_boleta_dep_cheque_pd.fecha_vencimiento, "
				+ "null "
				+ "from shv_avc_reg_avc_boleta, shv_avc_registro_avc, shv_bol_boleta, shv_wf_workflow_estado, shv_val_valor, shv_val_boleta_dep_cheque_pd "
				+ "where shv_avc_reg_avc_boleta.id_registro_avc = shv_avc_registro_avc.id_registro_avc "
				+ "and shv_bol_boleta.id_boleta = shv_avc_reg_avc_boleta.id_boleta "
				+ "and shv_bol_boleta.id_boleta = SHV_VAL_BOLETA_DEP_CHEQUE_PD.id_boleta "
				+ "and shv_val_valor.id_valor = SHV_VAL_BOLETA_DEP_CHEQUE_PD.ID_VALOR "
				+ "and shv_avc_registro_avc.id_workflow = shv_wf_workflow_estado.id_workflow "
				+ "and shv_wf_workflow_estado.estado = 'AVC_CONCILIACION_SUGERIDA' "
				+ "union "
				+ "select "
				+ "shv_bol_boleta.id_boleta, "
				+ "shv_avc_registro_avc.id_acuerdo, "
				+ "shv_val_valor.id_cliente_legado, "
				+ "shv_avc_registro_avc.importe, "
				+ "shv_bol_boleta.numero_boleta, "
				+ "null, "
				+ "null, "
				+ "null, "
				+ "null, "
				+ "null, "
				+ "null, "
				+ "null, "
				+ "shv_val_boleta_dep_efectivo.fecha_deposito "
				+ "from shv_avc_reg_avc_boleta, shv_avc_registro_avc, shv_bol_boleta, shv_wf_workflow_estado, shv_val_valor, shv_val_boleta_dep_efectivo "
				+ "where shv_avc_reg_avc_boleta.id_registro_avc = shv_avc_registro_avc.id_registro_avc "
				+ "and shv_bol_boleta.id_boleta = shv_avc_reg_avc_boleta.id_boleta "
				+ "and shv_bol_boleta.id_boleta = shv_val_boleta_dep_efectivo.id_boleta "
				+ "and shv_val_valor.id_valor = shv_val_boleta_dep_efectivo.id_valor "
				+ "and shv_avc_registro_avc.id_workflow = shv_wf_workflow_estado.id_workflow "
				+ "and shv_wf_workflow_estado.estado = 'AVC_CONCILIACION_SUGERIDA' ";

		return query;
	}

	public ShvBolBoleta buscarBoletaPorId(Long id) throws PersistenciaExcepcion {
		try {

			return boletaRepositorio.findOne(id);

		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	public List<ShvValValor> listarTodasBoletas() throws PersistenciaExcepcion {
		try {
			return valorRepositorio.findAll();

		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	@Override
	public List<ShvValValorSimplificado> listarValoresSimplificados(
			List<Long> idValores) throws PersistenciaExcepcion {
		try {
			return (List<ShvValValorSimplificado>) valorRepositorioSimplificado
					.findAll(idValores);

		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	@Override
	public ShvValValorSimplificado buscarValorSimplificado(Long idValor)
			throws PersistenciaExcepcion {
		try {
			return (ShvValValorSimplificado) valorRepositorioSimplificado
					.findOne(idValor);

		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	public ShvValValorSimplificado actualizarValorSimplificado(
			ShvValValorSimplificado valor) throws PersistenciaExcepcion {
		try {
			ShvValValorSimplificado valorBD = valorRepositorioSimplificado
					.save(valor);
			valorRepositorioSimplificado.flush();
			return valorBD;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	public ShvValValor actualizarValor(ShvValValor valor)
			throws PersistenciaExcepcion {
		try {
			ShvValValor valorBD = valorRepositorio.save(valor);
			valorRepositorio.flush();
			return valorBD;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	public Long proximoNumeroValor() throws PersistenciaExcepcion {

		try {
			String query = "select seq_shv_val_valor.nextval from dual";
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			List<Map<String, Object>> lista = buscarUsandoSQL(qp);
			return Long.valueOf(lista.get(0).get("NEXTVAL").toString());
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	// METODOS AGREGADOS DE ValorDaoImpl

	/**
	 * Busca un ShvValValor a partir de idValor
	 */
	public ShvValValor buscarBoletaConValor(Long idValor)
			throws PersistenciaExcepcion {
		try {
			ShvValValor boletaConValor = valorRepositorio.findOne(idValor);
			return boletaConValor;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	@SuppressWarnings("unchecked")
	public ShvValValor buscarValorPadreAsociadoAlValorDelChequeAnulado(
			Long idValor) throws PersistenciaExcepcion {
		try {
			QueryParametrosUtil qp = new QueryParametrosUtil(
					"from ShvValValor where idValor=?");
			qp.addParametro(idValor);

			List<ShvValValor> lista = buscarUsandoQueryConParametros(qp);

			return (lista.size() > 0) ? lista.get(0) : null;

		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	public List<ShvValValor> listarTodasBoletasConValor()
			throws PersistenciaExcepcion {
		try {
			return valorRepositorio.findAll();

		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<ShvValValorSimple> listarCodigosClienteLegadoBoleta(
			String usuarioLogueado) throws PersistenciaExcepcion {
		try {
			QueryParametrosUtil qp = new QueryParametrosUtil(
					"select val from ShvValValorSimple as val where upper(val.idAnalista)=? "
							+ " and val.tipoValor.tipoTipoValor = 'BCV' order by val.idValor desc");
			qp.addParametro(usuarioLogueado.toUpperCase());
			List<ShvValValorSimple> lista = (List<ShvValValorSimple>) buscarUsandoQueryConParametros(
					qp, 0, 50);

			return lista;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<ShvValValorSimple> listarCodigosClienteLegadoAviso(
			String usuarioLogueado) throws PersistenciaExcepcion {
		try {
			QueryParametrosUtil qp = new QueryParametrosUtil(
					"select val from ShvValValorSimple as val where upper(val.idAnalista)=? "
							+ " and val.tipoValor.tipoTipoValor = 'AVS' order by val.idValor desc");
			qp.addParametro(usuarioLogueado.toUpperCase());

			List<ShvValValorSimple> lista = (List<ShvValValorSimple>) buscarUsandoQueryConParametros(
					qp, 0, 50);
			return lista;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<ShvValValorSimple> listarCodigosClienteLegadoUser(
			String usuarioLogueado) throws PersistenciaExcepcion {
		try {
			QueryParametrosUtil qp = new QueryParametrosUtil(
					"select val from ShvValValorSimple as val where upper(val.idAnalista)=? order by val.idValor desc");
			qp.addParametro(usuarioLogueado.toUpperCase());

			List<ShvValValorSimple> lista = (List<ShvValValorSimple>) buscarUsandoQueryConParametros(
					qp, 0, 50);

			return lista;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ShvValBoletaDepositoCheque> buscarValoresBoletaChequeDuplicados(
			String bancoOrigen, String numeroCheque, String importe)
			throws PersistenciaExcepcion {

		String query = "from ShvValBoletaDepositoCheque as che where che.idValor in "
				+ "(select valor.idValor from ShvValValor as valor, ShvValBoletaDepositoCheque  as cheque, "
				+ " ShvWfWorkflowEstado as workflow  where valor.idValor = cheque.idValor "
				+ " and valor.workFlow = workflow.workflow "
				+ " and workflow.estado!=? "
				+ " and valor.importe=? "
				+ " and cheque.numeroCheque=? "
				+ " and cheque.bancoOrigen.idBanco=? )";

		BigDecimal importeLocal = Utilidad.stringToBigDecimal(importe);

		QueryParametrosUtil qp = new QueryParametrosUtil(query);
		qp.addParametro(Estado.VAL_ANULADO);
		qp.addParametro(importeLocal);
		qp.addParametro(new Long(numeroCheque));
		qp.addParametro(bancoOrigen);

		List<ShvValBoletaDepositoCheque> listaUno = (List<ShvValBoletaDepositoCheque>) buscarUsandoQueryConParametros(qp);
		return listaUno;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ShvValBoletaDepositoChequePagoDiferido> buscarValoresBoletaChequeDiferidoDuplicados(
			String bancoOrigen, String numeroCheque, String importe,
			String fechaVencimiento) throws PersistenciaExcepcion {
		BigDecimal importeLocal = Utilidad.stringToBigDecimal(importe);
		String fechaVencimientoQuery = null;
		String dateStr = fechaVencimiento;
		SimpleDateFormat dateParser = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MMM-yy");
		Date date = null;
		try {
			if (!Validaciones.isNullOrEmpty(dateStr)) {
				date = dateParser.parse(dateStr);
				fechaVencimientoQuery = "\'" + dateFormatter.format(date)
						+ "\'";
			}
		} catch (ParseException e) {
			throw new PersistenciaExcepcion("Error de parseo", e);
		}

		String query = "from ShvValBoletaDepositoChequePagoDiferido as che where che.idValor in ( select valor.idValor from ShvValValor as valor, "
				+ " ShvValBoletaDepositoChequePagoDiferido  as cheque, ShvWfWorkflowEstado as workflow   where valor.idValor = cheque.idValor "
				+ " and valor.workFlow = workflow.workflow"
				+ " and workflow.estado != ? "
				+ " and valor.importe = ? "
				+ " and cheque.numeroCheque = ? "
				+ " and cheque.bancoOrigen.idBanco = ? "
				+ " and cheque.fechaVencimiento = "
				+ fechaVencimientoQuery
				+ ")";

		QueryParametrosUtil qp = new QueryParametrosUtil(query);
		qp.addParametro(Estado.VAL_ANULADO);
		qp.addParametro(importeLocal);
		qp.addParametro(new Long(numeroCheque));
		qp.addParametro(bancoOrigen);

		List<ShvValBoletaDepositoChequePagoDiferido> listaUno = (List<ShvValBoletaDepositoChequePagoDiferido>) buscarUsandoQueryConParametros(qp);
		return listaUno;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ShvValValorCheque> buscarValoresChequeDuplicados(
			String bancoOrigen, String numeroCheque, String importe,
			String fechaDeposito) throws PersistenciaExcepcion {
		BigDecimal importeLocal = Utilidad.stringToBigDecimal(importe);
		String dateStr = fechaDeposito;
		String fechaDepositoQuery = null;
		SimpleDateFormat dateParser = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MMM-yy");
		Date date = null;
		try {
			if (!Validaciones.isNullOrEmpty(dateStr)) {
				date = dateParser.parse(dateStr);
				fechaDepositoQuery = "\'" + dateFormatter.format(date) + "\'";
			} else {
				return new ArrayList<ShvValValorCheque>();
			}
		} catch (ParseException e) {
			throw new PersistenciaExcepcion(e.getMessage(), e);
		}

		String query = "from ShvValValorCheque as che where che.idValor in  "
				+ " (select valor.idValor from ShvValValor as valor, ShvValValorCheque  as cheque, ShvWfWorkflowEstado as workflow  "
				+ " where valor.idValor = cheque.idValor "
				+ " and valor.workFlow = workflow.workflow"
				+ " and workflow.estado != ? " + " and valor.importe = ? "
				+ " and cheque.numeroCheque = ? "
				+ " and cheque.bancoOrigen.idBanco  = ? "
				+ " and cheque.fechaDeposito = " + fechaDepositoQuery + ")";

		QueryParametrosUtil qp = new QueryParametrosUtil(query);
		qp.addParametro(Estado.VAL_ANULADO);
		qp.addParametro(importeLocal);
		qp.addParametro(new Long(numeroCheque));
		qp.addParametro(bancoOrigen);

		List<ShvValValorCheque> listaUno = (List<ShvValValorCheque>) buscarUsandoQueryConParametros(qp);
		return listaUno;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ShvValValorChequePagoDiferido> buscarValoresChequeDiferidoDuplicados(
			String bancoOrigen, String numeroCheque, String importe,
			String fechaDeposito, String fechaVencimiento)
			throws PersistenciaExcepcion {
		BigDecimal importeLocal = Utilidad.stringToBigDecimal(importe);
		String dateStr = fechaDeposito;
		String dateStrVencimiento = fechaVencimiento;
		String fechaVencimientoQuery = null;
		String fechaDepositoQuery = null;
		SimpleDateFormat dateParser = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MMM-yy");
		Date date = null;
		Date dateVencimiento = null;
		try {
			if (!Validaciones.isNullOrEmpty(dateStr)) {
				date = dateParser.parse(dateStr);
				fechaDepositoQuery = "\'" + dateFormatter.format(date) + "\'";
			}
			if (!Validaciones.isNullOrEmpty(dateStrVencimiento)) {
				dateVencimiento = dateParser.parse(dateStrVencimiento);
				fechaVencimientoQuery = "\'"
						+ dateFormatter.format(dateVencimiento) + "\'";
			}
		} catch (ParseException e) {
			throw new PersistenciaExcepcion(e.getMessage(), e);
		}

		String query = "from ShvValValorChequePagoDiferido as che where che.idValor in "
				+ " (select valor.idValor from ShvValValor as valor, ShvValValorChequePagoDiferido  as cheque, "
				+ " ShvWfWorkflowEstado as workflow   where valor.idValor = cheque.idValor "
				+ " and valor.workFlow = workflow.workflow"
				+ " and workflow.estado != ? "
				+ " and valor.importe = ? "
				+ " and cheque.numeroCheque = ? "
				+ " and cheque.bancoOrigen.idBanco = ? "
				+ " and cheque.fechaDeposito = "
				+ fechaDepositoQuery
				+ " and cheque.fechaVencimiento = "
				+ fechaVencimientoQuery
				+ ")";

		QueryParametrosUtil qp = new QueryParametrosUtil(query);
		qp.addParametro(Estado.VAL_ANULADO);
		qp.addParametro(importeLocal);
		qp.addParametro(new Long(numeroCheque));
		qp.addParametro(bancoOrigen);

		List<ShvValValorChequePagoDiferido> listaUno = (List<ShvValValorChequePagoDiferido>) buscarUsandoQueryConParametros(qp);
		return listaUno;
	}

	@SuppressWarnings("unchecked")
	public Long obtenerIdValorAsociadoABoleta(Long idBoleta)
			throws PersistenciaExcepcion {

		String query = "select vbc from ShvValBoletaDepositoCheque as vbc "
				+ "join vbc.boleta as b where b.idBoleta=? ";
		QueryParametrosUtil qp = new QueryParametrosUtil(query);
		qp.addParametro(idBoleta);
		List<ShvValBoletaDepositoCheque> resultado = (List<ShvValBoletaDepositoCheque>) buscarUsandoQueryConParametros(qp);
		if (Validaciones.isCollectionNotEmpty(resultado)) {
			return resultado.get(0).getValor().getIdValor();
		}

		query = "select vbc from ShvValBoletaDepositoChequePagoDiferido as vbc "
				+ "join vbc.boleta as b where b.idBoleta=? ";
		qp = new QueryParametrosUtil(query);
		qp.addParametro(idBoleta);
		List<ShvValBoletaDepositoChequePagoDiferido> resultado2 = (List<ShvValBoletaDepositoChequePagoDiferido>) buscarUsandoQueryConParametros(qp);
		if (Validaciones.isCollectionNotEmpty(resultado2)) {
			return resultado2.get(0).getValor().getIdValor();
		}

		query = "select vbe from ShvValBoletaDepositoEfectivo as vbe "
				+ "join vbe.boleta as b where b.idBoleta=? ";
		qp = new QueryParametrosUtil(query);
		qp.addParametro(idBoleta);
		List<ShvValBoletaDepositoEfectivo> resultado3 = (List<ShvValBoletaDepositoEfectivo>) buscarUsandoQueryConParametros(qp);
		if (Validaciones.isCollectionNotEmpty(resultado3)) {
			return resultado3.get(0).getValor().getIdValor();
		}
		return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ShvValValorTransferencia> buscarValoresTransferenciaDuplicados(
			String numeroReferencia, String fechaTransferencia, String importe,
			boolean es87, String acuerdo) throws PersistenciaExcepcion {
		BigDecimal importeLocal = Utilidad.stringToBigDecimal(importe);
		String dateStr = fechaTransferencia;
		// String fechaTransferenciaQuery = null;
		SimpleDateFormat dateParser = new SimpleDateFormat("dd/MM/yyyy");
		// SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MMM-yy");
		Date date = null;
		try {
			if (!Validaciones.isNullOrEmpty(dateStr)) {
				date = dateParser.parse(dateStr);
				// fechaTransferenciaQuery = dateFormatter.format(date);
			}
		} catch (ParseException e) {
			throw new PersistenciaExcepcion(e.getMessage(), e);
		}
		String query = "from ShvValValorTransferencia as transferencia where transferencia.idValor in "
				+ "(select valor.idValor from ShvValValor as valor, ShvValValorTransferencia  as transf, "
				+ " ShvWfWorkflowEstado as workflow   where valor.idValor = transf.idValor "
				+ " and valor.workFlow = workflow.workflow"
				+ " and workflow.estado != ?"
				+ " and transf.numeroReferencia = ? "
				+ " and valor.acuerdo.idAcuerdo = ? ";
		QueryParametrosUtil qp = new QueryParametrosUtil();

		qp.addParametro(Estado.VAL_ANULADO);
		qp.addParametro(new Long(numeroReferencia));
		qp.addParametro(new Integer(acuerdo));
		if (!es87) {
			query += " and valor.importe = ?"
					+ " and day(transf.fechaTransferencia) = day(?) "
					+ " and month(transf.fechaTransferencia) = month(?) "
					+ " and year(transf.fechaTransferencia) = year(?) ";
			qp.addParametro(importeLocal);
			qp.addParametro(date);
			qp.addParametro(date);
			qp.addParametro(date);
		}
		query += ")";
		qp.setSql(query);

		List<ShvValValorTransferencia> listaUno = (List<ShvValValorTransferencia>) buscarUsandoQueryConParametros(qp);
		return listaUno;

	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ShvValValorRetencion> buscarValoresRetencionImpuestoDuplicados(
			String tipoRetencion, String numeroConstancia, String CodigoCliente)
			throws PersistenciaExcepcion {
		BigDecimal tipoRetencionLocal = Utilidad
				.stringToBigDecimal(tipoRetencion);

		String query = "from ShvValValorRetencion as retencion, ShvValValor as valor, ShvWfWorkflowEstado as workflow "
				+ " where valor.idValor = retencion.idValor  "
				+ " and valor.workFlow = workflow.workflow"
				+ " and workflow.estado != ?"
				+ " and retencion.paramTipoRetencionImpuesto.idTipoRetencionImpuesto = ? "
				+ " and retencion.nroConstanciaRetencion = ? "
				+ " and retencion.valor.idClienteLegado = ? ";

		QueryParametrosUtil qp = new QueryParametrosUtil(query);
		qp.addParametro(Estado.VAL_ANULADO);
		qp.addParametro(new Long(tipoRetencionLocal.toString()));
		qp.addParametro(numeroConstancia);
		qp.addParametro(new Long(CodigoCliente));

		List<ShvValValorRetencion> listaUno = (List<ShvValValorRetencion>) buscarUsandoQueryConParametros(qp);
		return listaUno;
	}

	@SuppressWarnings("unchecked")
	public List<ShvValValorInterdeposito> buscarValoresInterdepositoDuplicados(
			String codCliente, String numeroInterdeposito,
			String fechaInterdeposito) throws PersistenciaExcepcion {
		SimpleDateFormat dateParser = new SimpleDateFormat("dd/MM/yyyy");
		Date date = null;
		try {
			if (!Validaciones.isNullOrEmpty(fechaInterdeposito)) {
				date = dateParser.parse(fechaInterdeposito);
			}
		} catch (ParseException e) {
			throw new PersistenciaExcepcion(e.getMessage(), e);
		}

		String query = "from ShvValValorInterdeposito as interdeposito, "
				+ " ShvValValor as valor, ShvWfWorkflowEstado as workflow "
				+ " where valor.idValor = interdeposito.idValor  "
				+ " and valor.workFlow = workflow.workflow"
				+ " and workflow.estado != ? "
				+ " and valor.idClienteLegado = ? "
				+ " and interdeposito.numeroInterdeposito = ? "
				+ " and day(interdeposito.fechaInterdeposito) = day(?) "
				+ " and month(interdeposito.fechaInterdeposito) = month(?) "
				+ " and year(interdeposito.fechaInterdeposito) = year(?) ";

		QueryParametrosUtil qp = new QueryParametrosUtil(query);
		qp.addParametro(Estado.VAL_ANULADO);
		qp.addParametro(new Long(codCliente));
		qp.addParametro(new Long(numeroInterdeposito));
		qp.addParametro(date);
		qp.addParametro(date);
		qp.addParametro(date);

		List<ShvValValorInterdeposito> listaUno = (List<ShvValValorInterdeposito>) buscarUsandoQueryConParametros(qp);
		return listaUno;

	}

	@SuppressWarnings("unchecked")
	public ArrayList<ShvValValor> buscarValores(UsuarioSesion userSesion,
			BoletaConValorFiltro boletaFiltro) throws PersistenciaExcepcion {
		try {
			QueryParametrosUtil qp = generarConsulta(userSesion, boletaFiltro);
			ArrayList<ShvValValor> list = (ArrayList<ShvValValor>) buscarUsandoQueryConParametros(qp);
			return list;

		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	@SuppressWarnings("unchecked")
	public ArrayList<ShvValValor> buscarValores(List<Long> idValores)
			throws PersistenciaExcepcion {
		try {
			QueryParametrosUtil qp = new QueryParametrosUtil();
			String query = "select val from ShvValValor as val where val.idValor in ";
			String listaValores = "(";
			for (Long idValor : idValores) {
				listaValores += "?, ";
				qp.addParametro(idValor);
			}
			if (listaValores.length() > 3) {
				listaValores = listaValores.substring(0,
						listaValores.length() - 2) + ")";
			}
			qp.setSql(query + listaValores);

			return (ArrayList<ShvValValor>) buscarUsandoQueryConParametros(qp);

		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	@SuppressWarnings("unchecked")
	public ShvValValorSimple buscarValorSimple(String id)
			throws PersistenciaExcepcion {
		try {
			ShvValValorSimple valor = null;

			String query = "from ShvValValorSimple where idValor=? ";
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addParametro(new Long(id));

			List<ShvValValorSimple> lista = buscarUsandoQueryConParametros(qp);
			if (Validaciones.isCollectionNotEmpty(lista)) {
				valor = lista.get(0);
			}
			return valor;

		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	@SuppressWarnings("unchecked")
	public ShvValValor buscarValor(String idValor) throws PersistenciaExcepcion {
		try {
			ShvValValor valor = null;

			String query = "select val from ShvValValor as val where val.idValor=? ";
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addParametro(new Long(idValor));

			ArrayList<ShvValValor> lista = (ArrayList<ShvValValor>) buscarUsandoQueryConParametros(qp);
			if (Validaciones.isCollectionNotEmpty(lista)) {
				valor = lista.get(0);
			}
			return valor;

		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	@SuppressWarnings("unchecked")
	public ArrayList<ShvValValor> buscarValoresParaAutorizar(
			UsuarioSesion userSesion, BoletaConValorFiltro boletaFiltro)
			throws PersistenciaExcepcion {
		try {
			QueryParametrosUtil qp = generarConsultaParaAutorizacion(
					userSesion, boletaFiltro);
			ArrayList<ShvValValor> list = (ArrayList<ShvValValor>) buscarUsandoQueryConParametros(qp);
			return list;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	private QueryParametrosUtil generarConsultaParaAutorizacion(
			UsuarioSesion userSesion, BoletaConValorFiltro filtro)
			throws PersistenciaExcepcion {

		QueryParametrosUtil qp = new QueryParametrosUtil();

		String str = "";

		/* ID Valor Bandeja Entrada */
		if (!Validaciones.isNullOrEmpty(filtro.getIdValorBandeja())) {
			str += obtenerOperadorBusqueda(str) + " val.idValor= ? ";
			qp.addParametro(new Long(filtro.getIdValorBandeja()));
		}

		/* EMPRESA */
		if (!Validaciones.isNullOrEmpty(filtro.getEmpresa())) {
			str += obtenerOperadorBusqueda(str) + " val.empresa.idEmpresa = ? ";
			qp.addParametro(filtro.getEmpresa());
		}

		/* SEGMENTO */
		if (!Validaciones.isNullOrEmpty(filtro.getSegmento())) {
			str += obtenerOperadorBusqueda(str)
					+ " val.segmento.idSegmento = ? ";
			qp.addParametro(filtro.getSegmento());
		}

		/* ESTADO */
		str += " and we.estado= ? ";
		qp.addParametro(Estado.VAL_BOLETA_PENDIENTE_AUTORIZAR);

		String query = "select val from ShvValValor as val "
				+ "join val.workFlow as w  "
				+ "join w.shvWfWorkflowEstado as we " + str;

		String orderBy = " order by val.fechaAlta desc";
		qp.setSql(query + orderBy);

		return qp;
	}

	private QueryParametrosUtil generarConsulta(UsuarioSesion userSesion,
			BoletaConValorFiltro filtro) throws PersistenciaExcepcion {

		String query = "select val from ShvValValor as val "
				+ "join val.workFlow as w  "
				+ "join w.shvWfWorkflowEstado as we ";

		QueryParametrosUtil qp = new QueryParametrosUtil();

		String str = "";

		/* EMPRESA */
		if (!Validaciones.isNullOrEmpty(filtro.getEmpresa())) {
			str += obtenerOperadorBusqueda(str) + " val.empresa.idEmpresa=? ";
			qp.addParametro(filtro.getEmpresa());
		}

		/* SEGMENTO */
		if (!Validaciones.isNullOrEmpty(filtro.getSegmento())) {
			str += obtenerOperadorBusqueda(str) + " val.segmento.idSegmento=? ";
			qp.addParametro(filtro.getSegmento());
		}

		/* ESTADO */
		if (!Validaciones.isNullOrEmpty(filtro.getSelectEstado())) {
			str += obtenerOperadorBusqueda(str) + " we.estado=? ";

			qp.addParametro(Estado.getEnumByName(filtro.getSelectEstado()));
		}

		/* CODIGO DE CLIENTE */
		if (!Validaciones.isNullOrEmpty(filtro.getCodCliente())) {
			str += obtenerOperadorBusqueda(str) + " val.idClienteLegado=? ";
			qp.addParametro(new Long(filtro.getCodCliente()));
		}

		/* RANGO */
		if (!Validaciones.isNullOrEmpty(filtro.getFechaDesde())) {
			str += obtenerOperadorBusqueda(str)
					+ " val.fechaAlta>=to_timestamp('" + filtro.getFechaDesde()
					+ " 00:00:00','" + Utilidad.ORACLE_DATE_TIME_FULL_FORMAT
					+ "')";

		}

		if (!Validaciones.isNullOrEmpty(filtro.getFechaHasta())) {
			str += obtenerOperadorBusqueda(str)
					+ " val.fechaAlta<=to_timestamp('" + filtro.getFechaHasta()
					+ " 23:59:59','" + Utilidad.ORACLE_DATE_TIME_FULL_FORMAT
					+ "')";

		}

		/* IMPORTE */
		if (!Validaciones.isNullOrEmpty(filtro.getImporteDesde())) {
			str += obtenerOperadorBusqueda(str)
					+ " val.importe>="
					+ filtro.getImporteDesde().replace(".", "")
							.replace(",", ".");
		}

		if (!Validaciones.isNullOrEmpty(filtro.getImporteHasta())) {
			str += obtenerOperadorBusqueda(str)
					+ " val.importe<="
					+ filtro.getImporteHasta().replace(".", "")
							.replace(",", ".");
		}

		/* USUARIOS SESION */
		if (userSesion.esAnalista()) {
			str += obtenerOperadorBusqueda(str)
					+ " (upper(val.idAnalista)=? or upper(val.idCopropietario)=?)";
			qp.addParametro(userSesion.getIdUsuario().toUpperCase());
			qp.addParametro(userSesion.getIdUsuario().toUpperCase());

		}

		// Filtro por los valores en estado "NO DISPONIBLE"
		str += obtenerOperadorBusqueda(str) + " we.estado <> ? ";
		qp.addParametro(Estado.VAL_NO_DISPONIBLE);

		qp.setSql(query + str);

		return qp;
	}

	public List<Map<String, Object>> consultaChequesReemplazar(
			String codigoCliente) throws PersistenciaExcepcion {

		String query = "select che.numero_cheque, valor.id_valor  from shv_val_valor valor, SHV_WF_WORKFLOW_ESTADO wf_estado, shv_val_valor_cheque che"
				+ " where valor.id_workflow = wf_estado.id_workflow"
				+ " and   valor.ID_VALOR = che.ID_VALOR"
				+ " and valor.id_cliente_legado = ? "
				+ " and wf_estado.estado = ? "
				+ " and valor.id_motivo_suspension = ? "
				+ " union "
				+ "select chedi.numero_cheque, valor.id_valor from shv_val_valor valor, SHV_WF_WORKFLOW_ESTADO wf_estado, shv_val_valor_cheque_pd chedi"
				+ " where valor.id_workflow = wf_estado.id_workflow"
				+ " and   valor.ID_VALOR = chedi.ID_VALOR"
				+ " and valor.id_cliente_legado = ? "
				+ " and wf_estado.estado = ? "
				+ " and valor.id_motivo_suspension = ? "
				+ " union "
				+ "select chebol.numero_cheque, valor.id_valor from shv_val_valor valor, SHV_WF_WORKFLOW_ESTADO wf_estado, SHV_VAL_BOLETA_DEP_CHEQUE chebol"
				+ " where valor.id_workflow = wf_estado.id_workflow"
				+ " and   valor.ID_VALOR = chebol.ID_VALOR"
				+ " and valor.id_cliente_legado = ? "
				+ " and wf_estado.estado = ? "
				+ " and valor.id_motivo_suspension = ? "
				+ " union "
				+ "select cheboldi.numero_cheque, valor.id_valor from shv_val_valor valor, SHV_WF_WORKFLOW_ESTADO wf_estado, SHV_VAL_BOLETA_DEP_CHEQUE_PD cheboldi"
				+ " where valor.id_workflow = wf_estado.id_workflow"
				+ " and   valor.ID_VALOR = cheboldi.ID_VALOR"
				+ " and valor.id_cliente_legado = ? "
				+ " and wf_estado.estado = ? "
				+ " and valor.id_motivo_suspension = ? ";

		QueryParametrosUtil qp = new QueryParametrosUtil(query);
		qp.addCampoAlQuery(codigoCliente, Types.NUMERIC);
		qp.addCampoAlQuery(Estado.VAL_ANULADO, Types.VARCHAR);
		qp.addCampoAlQuery(MotivoSuspensionEnum.CHEQUE_RECHAZADO.codigo(),
				Types.NUMERIC);
		qp.addCampoAlQuery(codigoCliente, Types.NUMERIC);
		qp.addCampoAlQuery(Estado.VAL_ANULADO, Types.VARCHAR);
		qp.addCampoAlQuery(MotivoSuspensionEnum.CHEQUE_RECHAZADO.codigo(),
				Types.NUMERIC);
		qp.addCampoAlQuery(codigoCliente, Types.NUMERIC);
		qp.addCampoAlQuery(Estado.VAL_ANULADO, Types.VARCHAR);
		qp.addCampoAlQuery(MotivoSuspensionEnum.CHEQUE_RECHAZADO.codigo(),
				Types.NUMERIC);
		qp.addCampoAlQuery(codigoCliente, Types.NUMERIC);
		qp.addCampoAlQuery(Estado.VAL_ANULADO, Types.VARCHAR);
		qp.addCampoAlQuery(MotivoSuspensionEnum.CHEQUE_RECHAZADO.codigo(),
				Types.NUMERIC);

		try {
			return buscarUsandoSQL(qp);
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ShvValValor> buscarValoresPorRecibo(String reciboPreImpreso,
			String atributoValor) throws PersistenciaExcepcion {
		try {
			String query = "select valor from ShvValValor as valor, "
					+ atributoValor + " as atr "
					+ " where valor.idValor = atr.idValor "
					+ " and atr.reciboPreImpreso.numeroRecibo = ?";

			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addParametro(reciboPreImpreso);
			List<ShvValValor> resultado = (List<ShvValValor>) buscarUsandoQueryConParametros(qp);
			return resultado;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	@SuppressWarnings("unchecked")
	public Long getIdValorAsociadoABoleta(String idBoleta, String tipoValor)
			throws PersistenciaExcepcion {
		if (String.valueOf(
				TipoValorEnum.BOLETA_DEPOSITO_CHEQUE.getIdTipoValor())
				.equalsIgnoreCase(tipoValor)) {
			String query = "select vbc from ShvValBoletaDepositoCheque as vbc "
					+ "join vbc.boleta as b where b.idBoleta= ? ";
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addParametro(new Long(idBoleta));

			List<ShvValBoletaDepositoCheque> resultado = (List<ShvValBoletaDepositoCheque>) buscarUsandoQueryConParametros(qp);
			if (Validaciones.isCollectionNotEmpty(resultado)) {
				return resultado.get(0).getValor().getIdValor();
			}
		} else {
			if (String.valueOf(
					TipoValorEnum.BOLETA_DEPOSITO_CHEQUE_DIFERIDO
							.getIdTipoValor()).equalsIgnoreCase(tipoValor)) {
				String query = "select vbc from ShvValBoletaDepositoChequePagoDiferido as vbc "
						+ "join vbc.boleta as b where b.idBoleta= ? ";
				QueryParametrosUtil qp = new QueryParametrosUtil(query);
				qp.addParametro(new Long(idBoleta));

				List<ShvValBoletaDepositoChequePagoDiferido> resultado = (List<ShvValBoletaDepositoChequePagoDiferido>) buscarUsandoQueryConParametros(qp);
				if (Validaciones.isCollectionNotEmpty(resultado)) {
					return resultado.get(0).getValor().getIdValor();
				}
			} else {
				if (String
						.valueOf(
								TipoValorEnum.BOLETA_DEPOSITO_EFECTIVO
										.getIdTipoValor()).equalsIgnoreCase(
								tipoValor)) {
					String query = "select vbe from ShvValBoletaDepositoEfectivo as vbe "
							+ "join vbe.boleta as b where b.idBoleta=?";
					QueryParametrosUtil qp = new QueryParametrosUtil(query);
					qp.addParametro(new Long(idBoleta));

					List<ShvValBoletaDepositoEfectivo> resultado = (List<ShvValBoletaDepositoEfectivo>) buscarUsandoQueryConParametros(qp);
					if (Validaciones.isCollectionNotEmpty(resultado)) {
						return resultado.get(0).getValor().getIdValor();
					}
				}
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public Long getIdValorAsociadoABoleta(String numeroBoleta)
			throws PersistenciaExcepcion {

		String query = "select vbc from ShvValBoletaDepositoCheque as vbc "
				+ "join vbc.boleta as b where b.numeroBoleta=?";
		QueryParametrosUtil qp = new QueryParametrosUtil(query);
		qp.addParametro(new Long(numeroBoleta));

		List<ShvValBoletaDepositoCheque> resultado = (List<ShvValBoletaDepositoCheque>) buscarUsandoQueryConParametros(qp);
		if (Validaciones.isCollectionNotEmpty(resultado)) {
			return resultado.get(0).getValor().getIdValor();
		}

		String query2 = "select vbc from ShvValBoletaDepositoChequePagoDiferido as vbc "
				+ "join vbc.boleta as b where b.numeroBoleta=?";
		QueryParametrosUtil qp2 = new QueryParametrosUtil(query2);
		qp2.addParametro(new Long(numeroBoleta));

		List<ShvValBoletaDepositoChequePagoDiferido> resultado2 = (List<ShvValBoletaDepositoChequePagoDiferido>) buscarUsandoQueryConParametros(qp2);
		if (Validaciones.isCollectionNotEmpty(resultado2)) {
			return resultado2.get(0).getValor().getIdValor();
		}

		String query3 = "select vbe from ShvValBoletaDepositoEfectivo as vbe "
				+ "join vbe.boleta as b where b.numeroBoleta=?";
		QueryParametrosUtil qp3 = new QueryParametrosUtil(query3);
		qp3.addParametro(new Long(numeroBoleta));

		List<ShvValBoletaDepositoEfectivo> resultado3 = (List<ShvValBoletaDepositoEfectivo>) buscarUsandoQueryConParametros(qp3);
		if (Validaciones.isCollectionNotEmpty(resultado3)) {
			return resultado3.get(0).getValor().getIdValor();
		}
		return null;
	}

	/**
	 * Valida si ya existe un valor con los datos del valorDto, segun su tipo.
	 */
	@SuppressWarnings("unchecked")
	public List<ShvValValor> validarUnicidadValor(ValorDto valorDto)
			throws PersistenciaExcepcion {
		String query = "";
		QueryParametrosUtil qp;

		List<ShvValValor> resultado = new ArrayList<ShvValValor>();
		List<ShvValValor> resultado2 = new ArrayList<ShvValValor>();

		switch (TipoValorEnum.getEnumByIdTipoValor(Long.valueOf(valorDto
				.getIdTipoValor()))) {
		case CHEQUE:
			query = "select v from ShvValValor as v join v.valValorCheque as vch "
					+ " join v.workFlow.shvWfWorkflowEstado as we "
					+ " where vch.bancoOrigen.idBanco=? "
					+ " and vch.numeroCheque= ? "
					+ " and v.importe= ? "
					+ " and vch.fechaDeposito=to_timestamp(?,'"
					+ Utilidad.DATE_FORMAT_PICKER
					+ "')"
					+ " and we.estado<> ? ";

			qp = new QueryParametrosUtil(query);
			qp.addParametro(valorDto.getIdBancoOrigen());
			qp.addParametro(new Long(valorDto.getNumeroCheque()));
			qp.addParametro(Utilidad.stringToBigDecimal(valorDto.getImporte()));
			qp.addParametro(valorDto.getFechaDeposito());
			qp.addParametro(Estado.VAL_ANULADO);
			resultado = (List<ShvValValor>) buscarUsandoQueryConParametros(qp);

			query = "select v from ShvValValor as v "
					+ " join v.valorBoletaDepositoCheque as vBch "
					+ " join v.workFlow.shvWfWorkflowEstado as we "
					+ " where vBch.bancoOrigen.idBanco=? "
					+ " and vBch.numeroCheque=? " + " and v.importe=? "
					+ " and vBch.fechaDeposito=to_timestamp(?,'"
					+ Utilidad.DATE_FORMAT_PICKER + "')"
					+ " and we.estado<> ? ";

			qp = new QueryParametrosUtil(query);
			qp.addParametro(valorDto.getIdBancoOrigen());
			qp.addParametro(new Long(valorDto.getNumeroCheque()));
			qp.addParametro(Utilidad.stringToBigDecimal(valorDto.getImporte()));
			qp.addParametro(valorDto.getFechaDeposito());
			qp.addParametro(Estado.VAL_ANULADO);
			resultado2 = (List<ShvValValor>) buscarUsandoQueryConParametros(qp);

			resultado.addAll(resultado2);
			break;
		case CHEQUE_DIFERIDO:
			query = "select v from ShvValValor as v  join v.valValorChequePD as vch"
					+ " join v.workFlow.shvWfWorkflowEstado as we "
					+ " where vch.bancoOrigen.idBanco= ?"
					+ " and vch.numeroCheque=? "
					+ " and v.importe=? "
					+ " and vch.fechaDeposito=to_timestamp(?,'"
					+ Utilidad.DATE_FORMAT_PICKER
					+ "')"
					+ " and vch.fechaVencimiento=to_timestamp(?,'"
					+ Utilidad.DATE_FORMAT_PICKER + "')" + " and we.estado<> ?";

			qp = new QueryParametrosUtil(query);
			qp.addParametro(valorDto.getIdBancoOrigen());
			qp.addParametro(new Long(valorDto.getNumeroCheque()));
			qp.addParametro(Utilidad.stringToBigDecimal(valorDto.getImporte()));
			qp.addParametro(valorDto.getFechaDeposito());
			qp.addParametro(valorDto.getFechaVencimiento());
			qp.addParametro(Estado.VAL_ANULADO);
			resultado = (List<ShvValValor>) buscarUsandoQueryConParametros(qp);

			query = "select v from ShvValValor as v join v.valorBoletaDepositoChequePD as vBch"
					+ " join v.workFlow.shvWfWorkflowEstado as we "
					+ " where vBch.bancoOrigen.idBanco=? "
					+ " and vBch.numeroCheque=? "
					+ " and v.importe=? "
					+ " and vBch.fechaDeposito=to_timestamp(?,'"
					+ Utilidad.DATE_FORMAT_PICKER
					+ "')"
					+ " and vBch.fechaVencimiento=to_timestamp(?,'"
					+ Utilidad.DATE_FORMAT_PICKER
					+ "')"
					+ " and we.estado <> ? ";

			qp = new QueryParametrosUtil(query);
			qp.addParametro(valorDto.getIdBancoOrigen());
			qp.addParametro(new Long(valorDto.getNumeroCheque()));
			qp.addParametro(Utilidad.stringToBigDecimal(valorDto.getImporte()));
			qp.addParametro(valorDto.getFechaDeposito());
			qp.addParametro(valorDto.getFechaVencimiento());
			qp.addParametro(Estado.VAL_ANULADO);
			resultado2 = (List<ShvValValor>) buscarUsandoQueryConParametros(qp);

			resultado.addAll(resultado2);
			break;
		case TRANSFERENCIA:
			query = "select v from ShvValValor as v "
					+ " join v.valValorTransferencia as vt"
					+ " join v.workFlow.shvWfWorkflowEstado as we "
					+ " join v.acuerdo as a"
					+ " where vt.numeroReferencia=? " + " and v.importe=? "
					+ " and vt.fechaTransferencia=to_timestamp(? ,'"
					+ Utilidad.DATE_FORMAT_PICKER + "')"
					+ " and we.estado <> ?"
					+ " and a.idAcuerdo = ?";

			qp = new QueryParametrosUtil(query);
			qp.addParametro(new Long(valorDto.getNumeroReferencia()));
			qp.addParametro(Utilidad.stringToBigDecimal(valorDto
					.getImporteParaComparacion()));
			qp.addParametro(valorDto.getFechaTransferencia());
			qp.addParametro(Estado.VAL_ANULADO);
			qp.addParametro(new Integer(valorDto.getIdAcuerdo()));
			resultado = (List<ShvValValor>) buscarUsandoQueryConParametros(qp);
			break;
		case INTERDEPÓSITO:
			query = "select v from ShvValValor as v "
					+ " join v.valValorInterdeposito as vi"
					+ " join v.workFlow.shvWfWorkflowEstado as we "
					+ " where v.idClienteLegado=? "
					+ " and vi.numeroInterdeposito=? "
					+ " and vi.fechaInterdeposito=to_timestamp(?,'"
					+ Utilidad.DATE_FORMAT_PICKER + "')" + " and we.estado<>? ";

			qp = new QueryParametrosUtil(query);
			qp.addParametro(new Long(valorDto.getCodCliente()));
			qp.addParametro(new Long(valorDto.getNumeroInterdepositoCdif()));
			qp.addParametro(valorDto.getFechaInterdeposito());
			qp.addParametro(Estado.VAL_ANULADO);
			resultado = (List<ShvValValor>) buscarUsandoQueryConParametros(qp);
			break;
		default:
		}
		;

		return resultado;

	}

	/**
	 * Valida si existe otro valor con los datos del valorDto, segun su tipo.
	 */
	@SuppressWarnings("unchecked")
	public boolean reValidarUnicidadValor(ValorDto valorDto)
			throws PersistenciaExcepcion {
		String query = "";
		QueryParametrosUtil qp;

		List<ShvValValor> resultado = new ArrayList<ShvValValor>();
		List<ShvValValor> resultado2 = new ArrayList<ShvValValor>();

		switch (TipoValorEnum.getEnumByIdTipoValor(Long.valueOf(valorDto
				.getIdTipoValor()))) {
		case CHEQUE:
			query = "select v from ShvValValor as v join v.valValorCheque as vch "
					+ " join v.workFlow.shvWfWorkflowEstado as we "
					+ " where vch.bancoOrigen.idBanco=? "
					+ " and vch.numeroCheque=? "
					+ " and v.importe=? "
					+ " and vch.fechaDeposito=to_timestamp(?,'"
					+ Utilidad.DATE_FORMAT_PICKER
					+ "')"
					+ " and we.estado<> ? " + " and v.idValor<> ?";

			qp = new QueryParametrosUtil(query);
			qp.addParametro(valorDto.getIdBancoOrigen());
			qp.addParametro(new Long(valorDto.getNumeroCheque()));
			qp.addParametro(Utilidad.stringToBigDecimal(valorDto.getImporte()));
			qp.addParametro(valorDto.getFechaDeposito());
			qp.addParametro(Estado.VAL_ANULADO);
			qp.addParametro(valorDto.getIdValor());
			resultado = (List<ShvValValor>) buscarUsandoQueryConParametros(qp);

			query = "select v from ShvValValor as v join v.valorBoletaDepositoCheque as vBch "
					+ " join v.workFlow.shvWfWorkflowEstado as we "
					+ " where vBch.bancoOrigen.idBanco=? "
					+ " and vBch.numeroCheque=? "
					+ " and v.importe=? "
					+ " and vBch.fechaDeposito=to_timestamp(?,'"
					+ Utilidad.DATE_FORMAT_PICKER
					+ "')"
					+ " and we.estado<> ? " + " and v.idValor<> ? ";

			qp = new QueryParametrosUtil(query);
			qp.addParametro(valorDto.getIdBancoOrigen());
			qp.addParametro(new Long(valorDto.getNumeroCheque()));
			qp.addParametro(Utilidad.stringToBigDecimal(valorDto.getImporte()));
			qp.addParametro(valorDto.getFechaDeposito());
			qp.addParametro(Estado.VAL_ANULADO);
			qp.addParametro(valorDto.getIdValor());
			resultado2 = (List<ShvValValor>) buscarUsandoQueryConParametros(qp);

			resultado.addAll(resultado2);
			break;
		case CHEQUE_DIFERIDO:
			query = "select v from ShvValValor as v join v.valValorChequePD as vch "
					+ " join v.workFlow.shvWfWorkflowEstado as we "
					+ " where vch.bancoOrigen.idBanco=? "
					+ " and vch.numeroCheque=? "
					+ " and v.importe= ? "
					+ " and vch.fechaDeposito=to_timestamp(? ,'"
					+ Utilidad.DATE_FORMAT_PICKER
					+ "')"
					+ " and vch.fechaVencimiento=to_timestamp(? ,'"
					+ Utilidad.DATE_FORMAT_PICKER
					+ "')"
					+ " and we.estado<> ? " + " and v.idValor<> ? ";

			qp = new QueryParametrosUtil(query);
			qp.addParametro(valorDto.getIdBancoOrigen());
			qp.addParametro(new Long(valorDto.getNumeroCheque()));
			qp.addParametro(Utilidad.stringToBigDecimal(valorDto.getImporte()));
			qp.addParametro(valorDto.getFechaDeposito());
			qp.addParametro(valorDto.getFechaVencimiento());
			qp.addParametro(Estado.VAL_ANULADO);
			qp.addParametro(valorDto.getIdValor());
			resultado = (List<ShvValValor>) buscarUsandoQueryConParametros(qp);

			query = "select v from ShvValValor as v join v.valorBoletaDepositoChequePD as vBch"
					+ " join v.workFlow.shvWfWorkflowEstado as we "
					+ " where vBch.bancoOrigen.idBanco=? "
					+ " and vBch.numeroCheque=? "
					+ " and v.importe=? "
					+ " and vBch.fechaDeposito=to_timestamp(?,'"
					+ Utilidad.DATE_FORMAT_PICKER
					+ "')"
					+ " and vBch.fechaVencimiento=to_timestamp(?,'"
					+ Utilidad.DATE_FORMAT_PICKER
					+ "')"
					+ " and we.estado<> ? " + " and v.idValor<> ? ";

			qp = new QueryParametrosUtil(query);
			qp.addParametro(valorDto.getIdBancoOrigen());
			qp.addParametro(new Long(valorDto.getNumeroCheque()));
			qp.addParametro(Utilidad.stringToBigDecimal(valorDto.getImporte()));
			qp.addParametro(valorDto.getFechaDeposito());
			qp.addParametro(valorDto.getFechaVencimiento());
			qp.addParametro(Estado.VAL_ANULADO);
			qp.addParametro(valorDto.getIdValor());
			resultado2 = (List<ShvValValor>) buscarUsandoQueryConParametros(qp);

			resultado.addAll(resultado2);
			break;
		case TRANSFERENCIA:
			query = "select v from ShvValValor as v "
					+ " join v.valValorTransferencia as vt"
					+ " join v.workFlow.shvWfWorkflowEstado as we "
					+ " join v.acuerdo as a "
					+ " where vt.numeroReferencia=? " + " and v.importe=? "
					+ " and vt.fechaTransferencia=to_timestamp(? ,'"
					+ Utilidad.DATE_FORMAT_PICKER + "')"
					+ " and we.estado<> ? " + " and v.idValor<> ? "
					+ " and a.idAcuerdo = ? ";

			qp = new QueryParametrosUtil(query);
			qp.addParametro(new Long(valorDto.getNumeroReferencia()));
			qp.addParametro(Utilidad.stringToBigDecimal(valorDto
					.getImporteParaComparacion()));
			qp.addParametro(valorDto.getFechaTransferencia());
			qp.addParametro(Estado.VAL_ANULADO);
			qp.addParametro(valorDto.getIdValor());
			qp.addParametro(new Integer(valorDto.getIdAcuerdo()));
			resultado = (List<ShvValValor>) buscarUsandoQueryConParametros(qp);
			break;
		case INTERDEPÓSITO:
			query = "select v from ShvValValor as v "
					+ " join v.valValorInterdeposito as vi"
					+ " join v.workFlow.shvWfWorkflowEstado as we "
					+ " where v.idClienteLegado=? "
					+ " and vi.numeroInterdeposito=? "
					+ " and vi.fechaInterdeposito=to_timestamp(?,'"
					+ Utilidad.DATE_FORMAT_PICKER + "')"
					+ " and we.estado<> ? " + " and v.idValor<> ?";

			qp = new QueryParametrosUtil(query);
			qp.addParametro(new Long(valorDto.getCodCliente()));
			qp.addParametro(new Long(valorDto.getNumeroInterdepositoCdif()));
			qp.addParametro(valorDto.getFechaInterdeposito());
			qp.addParametro(Estado.VAL_ANULADO);
			qp.addParametro(valorDto.getIdValor());
			resultado = (List<ShvValValor>) buscarUsandoQueryConParametros(qp);
			break;
		default:
		}
		;

		if (Validaciones.isCollectionNotEmpty(resultado)) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @param valorDto
	 * @param incluirValorActualEnComparacion
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public boolean validarUnicidadValor(ValorDto valorDto, boolean incluirValorActualEnComparacion) throws PersistenciaExcepcion {
		
		boolean esValorEsUnico = false;
		
		StringBuffer consulta = new StringBuffer();
		QueryParametrosUtil qp = new QueryParametrosUtil();
		
		consulta.append(" select count(*) as cantidad ");
		consulta.append(" from ");
		consulta.append(" 	shv_sop_busqueda_valores ssbv ");
		consulta.append(" where ");

		// Obtener valores en estado diferente a anulados
		consulta.append(" 	id_estado_valor != ? ");
		qp.addCampoAlQuery(Estado.VAL_ANULADO, Types.VARCHAR);
		
		Traza.auditoria(getClass(), " Tipo de Valor:                      " + TipoValorEnum.getEnumByIdTipoValor(Long.valueOf(valorDto.getIdTipoValor())).getDescripcion());
		
		if (incluirValorActualEnComparacion) {
			consulta.append(" 	and id_valor != ? ");
			qp.addCampoAlQuery(valorDto.getIdValor(), Types.NUMERIC);
			Traza.auditoria(getClass(), " Id Valor:                           " + valorDto.getIdValor());
		}

		TipoValorEnum tipoValor = TipoValorEnum.getEnumByIdTipoValor(Long.valueOf(valorDto.getIdTipoValor()));
			
		// --  Cheque / Boleta deposito cheque
		if (TipoValorEnum.CHEQUE.equals(tipoValor) || TipoValorEnum.BOLETA_DEPOSITO_CHEQUE.equals(tipoValor)) {
			consulta.append(" 	and ssbv.id_banco_origen = ? ");
			qp.addCampoAlQuery(valorDto.getIdBancoOrigen(), Types.VARCHAR);
			Traza.auditoria(getClass(), " Id Banco Origen:                    " + valorDto.getIdBancoOrigen());

			consulta.append(" 	and ssbv.referencia_valor = ? "); // -- numero de cheque 
			qp.addCampoAlQuery(new Long(valorDto.getNumeroCheque()), Types.NUMERIC);
			Traza.auditoria(getClass(), " Numero de Cheque:                   " + valorDto.getNumeroCheque());
			
			consulta.append(" 	and ssbv.importe = ? ");
			qp.addCampoAlQuery(Utilidad.stringToBigDecimal(valorDto.getImporte()), Types.DECIMAL);
			Traza.auditoria(getClass(), " Importe:                            " + Utilidad.stringToBigDecimal(valorDto.getImporte()));
			
			if (!Validaciones.isObjectNull(valorDto.getFechaDeposito())) {
				consulta.append(" 	and trunc(ssbv.fecha_deposito) = to_date(?, ?) ");
				qp.addCampoAlQuery(valorDto.getFechaDeposito(), Types.VARCHAR);
				qp.addCampoAlQuery(Utilidad.DATE_FORMAT_PICKER, Types.VARCHAR);
				Traza.auditoria(getClass(), " Fecha Depósito:                     " + valorDto.getFechaDeposito());
			} else {
				Traza.auditoria(getClass(), " Fecha Depósito:                     es nula.");
			}
		}

		// --  Cheque Diferido / Boleta deposito cheque diferido
		else if (TipoValorEnum.CHEQUE_DIFERIDO.equals(tipoValor) || TipoValorEnum.BOLETA_DEPOSITO_CHEQUE_DIFERIDO.equals(tipoValor)) {		
			consulta.append(" 	and ssbv.id_banco_origen = ? ");
			qp.addCampoAlQuery(valorDto.getIdBancoOrigen(), Types.VARCHAR);
			Traza.auditoria(getClass(), " Id Banco Origen:                    " + valorDto.getIdBancoOrigen());

			consulta.append(" 	and ssbv.referencia_valor =  ? "); // -- numero de cheque
			qp.addCampoAlQuery(new Long(valorDto.getNumeroCheque()), Types.NUMERIC);
			Traza.auditoria(getClass(), " Numero de Cheque:                   " + valorDto.getNumeroCheque());

			consulta.append(" 	and ssbv.importe = ? ");
			qp.addCampoAlQuery(Utilidad.stringToBigDecimal(valorDto.getImporte()), Types.DECIMAL);
			Traza.auditoria(getClass(), " Importe:                            " + Utilidad.stringToBigDecimal(valorDto.getImporte()));

			if (!Validaciones.isObjectNull(valorDto.getFechaDeposito())) {
				consulta.append(" 	and trunc(ssbv.fecha_deposito) = to_date(?, ?) ");
				qp.addCampoAlQuery(valorDto.getFechaDeposito(), Types.VARCHAR);
				qp.addCampoAlQuery(Utilidad.DATE_FORMAT_PICKER, Types.VARCHAR);
				Traza.auditoria(getClass(), " Fecha Depósito:                     " + valorDto.getFechaDeposito());
			} else {
				Traza.auditoria(getClass(), " Fecha Depósito:                     es nula.");
			}

			consulta.append(" 	and ssbv.fecha_vencimiento = to_date(?, ?) ");
			qp.addCampoAlQuery(valorDto.getFechaVencimiento(), Types.VARCHAR);
			qp.addCampoAlQuery(Utilidad.DATE_FORMAT_PICKER, Types.VARCHAR);
			Traza.auditoria(getClass(), " Fecha Vencimiento:                  " + valorDto.getFechaVencimiento());
		}

		// --  Transferencia
		else if (TipoValorEnum.TRANSFERENCIA.equals(tipoValor)) {
			consulta.append(" 	and ssbv.referencia_valor = ? "); //-- numero de referencia transferencia
			qp.addCampoAlQuery(new Long(valorDto.getNumeroReferencia()), Types.VARCHAR);
			Traza.auditoria(getClass(), " Numero de Referencia Transferencia: " + valorDto.getNumeroReferencia());

			consulta.append(" 	and ssbv.importe = ? ");
			qp.addCampoAlQuery(Utilidad.stringToBigDecimal(valorDto.getImporte()), Types.DECIMAL);
			Traza.auditoria(getClass(), " Importe:                            " + Utilidad.stringToBigDecimal(valorDto.getImporte()));

			consulta.append(" 	and ssbv.fecha_transferencia = to_date(?, ?) ");
			qp.addCampoAlQuery(valorDto.getFechaTransferencia(), Types.VARCHAR);
			qp.addCampoAlQuery(Utilidad.DATE_FORMAT_PICKER, Types.VARCHAR);
			Traza.auditoria(getClass(), " Fecha Transferencia :               " + valorDto.getFechaTransferencia());
		}

		// --  Interdepósito
		else if (TipoValorEnum.INTERDEPÓSITO.equals(tipoValor)) {
			consulta.append(" 	and ssbv.id_cliente_legado = ? ");
			qp.addCampoAlQuery(new Long(valorDto.getCodCliente()), Types.NUMERIC);
			Traza.auditoria(getClass(), " Id Cliente Legado:                  " + new Long(valorDto.getCodCliente()));
			
			consulta.append(" 	and ssbv.referencia_valor =  ? "); // -- numero de interdeposito
			qp.addCampoAlQuery(new Long(valorDto.getNumeroInterdepositoCdif()), Types.NUMERIC);
			Traza.auditoria(getClass(), " Numero de Intedepósito:             " + valorDto.getNumeroInterdepositoCdif());

			consulta.append(" 	and trunc(ssbv.fecha_deposito) = to_date(?, ?) ");
			qp.addCampoAlQuery(valorDto.getFechaDeposito(), Types.VARCHAR);
			qp.addCampoAlQuery(Utilidad.DATE_FORMAT_PICKER, Types.VARCHAR);
			Traza.auditoria(getClass(), " Fecha Depósito:                     " + valorDto.getFechaDeposito());
		}
		
		else if (TipoValorEnum.RETENCIÓN_IMPUESTO.equals(tipoValor)) {
			// --  Retencion e impuesto
			consulta.append(" 	and ssbv.id_tipo_retencion = ? ");
			qp.addCampoAlQuery(new Long(valorDto.getIdTipoImpuesto()), Types.NUMERIC);
			Traza.auditoria(getClass(), " Tipo de Retencion Impuesto:         " + valorDto.getIdTipoImpuesto());

			consulta.append(" 	and ssbv.referencia_valor =  ? "); // -- numero de constancia de retencion
			qp.addCampoAlQuery(new Long(valorDto.getNroConstancia()), Types.NUMERIC);
			Traza.auditoria(getClass(), " Número de Contancia de Retencion:   " + valorDto.getNroConstancia());

			consulta.append(" 	and ssbv.id_cliente_legado = ? ");
			qp.addCampoAlQuery(new Long(valorDto.getCodCliente()), Types.NUMERIC);
			Traza.auditoria(getClass(), " Id Cliente Legado:                  " + new Long(valorDto.getCodCliente()));
		}
		
		qp.setSql(consulta.toString());
		
		List<Map<String, Object>> listaResultadoQuery = buscarUsandoSQL(qp);

		for (Map<String, Object> registro : listaResultadoQuery) {
			if (!Validaciones.isObjectNull(registro.get("CANTIDAD"))) {
				long cantidadRegistrosExistentes = new Long(registro.get("CANTIDAD").toString());
				
				esValorEsUnico = cantidadRegistrosExistentes == 0;
				
				Traza.auditoria(getClass(), " ");
				if (esValorEsUnico) {
					Traza.auditoria(getClass(), " El resultado de la validacion es: El valor que se intenta dar de alta es unico");
				} else {
					Traza.auditoria(getClass(), " El resultado de la validacion es: El valor que se intenta dar de alta ya existe en Shiva");
				}
			}
		}
		return esValorEsUnico;
	}

	
	/**
	 * Busca si existe un valor creado anteriormente para ese id de registro y
	 * lo retorna
	 */
	@SuppressWarnings("unchecked")
	public ShvValValor buscarValorCreadoAPartirRegistroAvc(String idRegistro)
			throws PersistenciaExcepcion {

		String query = "select vreg from ShvAvcRegistroAvcValor as vreg "
				+ "where shvAvcRegistroAvcValorPK.registroAvc.idRegistroAvc=? ";
		QueryParametrosUtil qp = new QueryParametrosUtil(query);
		qp.addParametro(new Long(idRegistro));

		List<ShvAvcRegistroAvcValor> resultado = (List<ShvAvcRegistroAvcValor>) buscarUsandoQueryConParametros(qp);
		if (Validaciones.isCollectionNotEmpty(resultado)) {
			return resultado.get(0).getShvAvcRegistroAvcValorPK().getValor();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ShvValValor> buscarRetencionesParaReporte(Filtro retencionFiltro)
			throws PersistenciaExcepcion {
		List<ShvValValor> listaRetenciones = new ArrayList<ShvValValor>();

		String query = "SELECT valor FROM ShvValValor as valor join valor.valValorRetencion as ret"
				+ " join valor.workFlow.shvWfWorkflowEstado as valWe"
				+ " where (valWe.estado = ? or valWe.estado = ? )"
				+ " and (valor.fechaDisponible >= TO_DATE( ? , ?) and valor.fechaDisponible < TO_DATE( ? , ?) )"
				+ " and ret.paramTipoRetencionImpuesto.idTipoRetencionImpuesto = ? ";
		try {
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addParametro(Estado.VAL_DISPONIBLE);
			qp.addParametro(Estado.VAL_USADO);
			qp.addParametro(new String(retencionFiltro.getFechaDesde()
					+ " 00:00:00"));
			qp.addParametro(new String(Utilidad.ORACLE_DATE_TIME_FULL_FORMAT));
			qp.addParametro(new String(retencionFiltro.getFechaHasta()
					+ " 23:59:59"));
			qp.addParametro(new String(Utilidad.ORACLE_DATE_TIME_FULL_FORMAT));
			qp.addParametro(new Long(ID_RET_IIBB));
			listaRetenciones = (List<ShvValValor>) buscarUsandoQueryConParametros(qp);
		} catch (PersistenciaExcepcion e) {
			throw new PersistenciaExcepcion(e.getMessage(), e);
		}
		return listaRetenciones;
	}

	// TODO Este metodo no tiene que reestablecerce es un comentario aparte de
	// la vuelta atras
	// public List<ShvValValoresVista> listarMorosidadValores(Filtro
	// morosidadFiltro) throws PersistenciaExcepcion {
	// try {
	// // TODO
	// /** @author u573005
	// * Esto queda asi?
	// */
	// String query = "select vista from ShvValValoresVista vista "
	// + " join vista.shvValValor valor "
	// + " join valor.workFlow.shvWfWorkflowEstadoHist estadoHist "
	// + " where estadoHist.estado = ? "
	// + " and estadoHist.fechaModificacion <= TO_TIMESTAMP( ? , ?) ";
	// QueryParametrosUtil qp = new QueryParametrosUtil(query);
	// qp.addParametro(Estado.VAL_DISPONIBLE);
	// qp.addParametro(new String(morosidadFiltro.getFechaHasta() +
	// " 23:59:59"));
	// qp.addParametro(new String(Utilidad.ORACLE_DATE_TIME_FULL_FORMAT));
	//
	// List<ShvValValoresVista> lista = buscarUsandoQueryConParametros(qp);
	// return lista;
	// } catch (Throwable e) {
	// throw new PersistenciaExcepcion(e);
	// }
	// }

	// TODO se comenta este metodo para volver a la version anterior
	// @SuppressWarnings("unchecked")
	// @Override
	// public List<ShvValValor> listarMorosidadValores(Filtro morosidadFiltro)
	// throws PersistenciaExcepcion{
	// try {
	// String query =
	// "select valor from ShvValValor as valor  join valor.workFlow.shvWfWorkflowEstadoHist estadoHist  where estadoHist.estado = ?  and estadoHist.fechaModificacion <= TO_TIMESTAMP( ? , ?) ";
	// QueryParametrosUtil qp = new QueryParametrosUtil(query);
	// qp.addParametro(Estado.VAL_DISPONIBLE);
	// qp.addParametro(new String((new
	// StringBuilder()).append(morosidadFiltro.getFechaHasta()).append(" 23:59:59").toString()));
	// qp.addParametro(new String("dd/mm/yyyy hh24:mi:ss"));
	// List<ShvValValor> lista = (List<ShvValValor>)
	// buscarUsandoQueryConParametros(qp);
	// return lista;
	// } catch(Throwable e) {
	// throw new PersistenciaExcepcion(e);
	// }
	// }


	// TODO es la version anterior del metodo que se comento
	/**
	 * Tra
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ShvValValoresVista> listarMorosidadValores(
			Filtro morosidadFiltro) throws PersistenciaExcepcion {
		try {
			String query = "from ShvValValoresVista As vis "
								+ "	where not exists ( " 
                         				+ "from ShvParamCliente As cli "  
                         				+ "where cli.idClienteLegado = vis.codigoClienteLegado "
                         				+ "and cli.empresa.idEmpresa != ? "
                         			+ ") "
                         			+ "and vis.idEstadoValor= ? ";
			
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			
			qp.addCampoAlQuery(EmpresaEnum.TA.toString(), Types.VARCHAR);
			qp.addParametro(Estado.VAL_DISPONIBLE.name());

			List<ShvValValoresVista> lista = buscarUsandoQueryConParametros(qp);
			return lista;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	@SuppressWarnings("unchecked")
	public Date buscarFechaModificacionXIdValor(Long idValor)
			throws PersistenciaExcepcion {
		try {
			Date fechaModificacion = new Date();

			String query = "select estado.fechaModificacion from ShvValValor as valor ,ShvWfWorkflowEstado as estado "
					+ "where valor.workFlow.idWorkflow = estado.workflow.idWorkflow "
					+ "and valor.idValor = ?";
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addParametro(idValor);

			ArrayList<Date> lista = (ArrayList<Date>) buscarUsandoQueryConParametros(qp);
			if (Validaciones.isCollectionNotEmpty(lista)) {
				fechaModificacion = lista.get(0);
			}
			return fechaModificacion;

		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	/****************************
	 * Getters & Setters *
	 ****************************/

	public ValorRepositorio getValorRepositorio() {
		return valorRepositorio;
	}

	public void setValorRepositorio(ValorRepositorio valorRepositorio) {
		this.valorRepositorio = valorRepositorio;
	}

	public ValorPorReversionRepositorio getValorPorReversionRepositorio() {
		return valorPorReversionRepositorio;
	}

	public void setValorPorReversionRepositorio(
			ValorPorReversionRepositorio valorAuxRepositorio) {
		this.valorPorReversionRepositorio = valorAuxRepositorio;
	}

}
