package ar.com.telecom.shiva.persistencia.dao.impl;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.enumeradores.MarcaEnum;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.negocio.workflow.definicion.TipoWorkflow;
import ar.com.telecom.shiva.persistencia.bean.ResultadoBusquedaDatosImputacion;
import ar.com.telecom.shiva.persistencia.bean.ResultadoBusquedaDatosSimulacion;
import ar.com.telecom.shiva.persistencia.dao.Dao;
import ar.com.telecom.shiva.persistencia.dao.IDescobroDao;
import ar.com.telecom.shiva.persistencia.dao.util.QueryParametrosUtil;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobDescobro;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobDescobroAdjunto;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobDescobroDocumentoRelacionado;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobDescobroOperacionRelacionada;
import ar.com.telecom.shiva.persistencia.modelo.ShvDocDocumentoAdjunto;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvCobDescobroSimpleSinWorkflow;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvCobDescobroSimplificadoCodigoExternoOperacion;
import ar.com.telecom.shiva.persistencia.repository.DescobroAdjuntoRepositorio;
import ar.com.telecom.shiva.persistencia.repository.DescobroDocumentoRelacionadoRepositorio;
import ar.com.telecom.shiva.persistencia.repository.DescobroOperacionRelacionadaRepositorio;
import ar.com.telecom.shiva.persistencia.repository.DescobroRepositorio;
import ar.com.telecom.shiva.persistencia.repository.DescobroSimplificadoCodigoOperacionExternaRepositorio;
import ar.com.telecom.shiva.persistencia.repository.DocumentoAdjuntoRepositorio;
import ar.com.telecom.shiva.presentacion.bean.filtro.Filtro;

public class DescobroDaoImpl extends Dao implements IDescobroDao {

	@Autowired 
	DescobroRepositorio descobroRepositorio;
	
	@Autowired 
	DescobroAdjuntoRepositorio descobroAdjuntoRepositorio;
	
	@Autowired 
	DocumentoAdjuntoRepositorio documentoAdjuntoRepositorio;
	
	@Autowired 
	DescobroOperacionRelacionadaRepositorio descobroOperacionRelacionadaRepositorio;
	
	@Autowired 
	DescobroDocumentoRelacionadoRepositorio descobroDocumentoRelacionadoRepositorio;
	@Autowired
	DescobroSimplificadoCodigoOperacionExternaRepositorio descobroSimplificadoCodigoOperacionExternaRepositorio;
	
	/**
	 * Inserta un Descobro
	 * @param cobro
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public ShvCobDescobro insertarDescobro(ShvCobDescobro descobro) throws PersistenciaExcepcion {
		try{
			if(Validaciones.isObjectNull(descobro.getOperacion().getIdOperacion())){
				
				// Si el IdOperacion es null lo obtengo de la secuencia de la 
				// base, porque se borro el mapeo de la secuencia del modelo
				QueryParametrosUtil qp = new QueryParametrosUtil();	
				String query = "select SEQ_SHV_COB_OPERACION.NEXTVAL from dual";
				qp.setSql(query);
				List<Map<String,Object>> listaResultadoQuery = buscarUsandoSQL(qp);
				if(Validaciones.isCollectionNotEmpty(listaResultadoQuery)){
					String idOperacion = listaResultadoQuery.get(0).get("NEXTVAL").toString();
					descobro.getOperacion().setIdOperacion(Long.valueOf(idOperacion));
				}
			}
			ShvCobDescobro descobroBD = descobroRepositorio.save(descobro);
			descobroRepositorio.flush();
			return descobroBD;
			
		}catch(Exception e){
			throw new PersistenciaExcepcion(e);
		}
	}
	
	/**
	 * @author u572487 Guido.Settecerze
	 * Guarda los adjuntos del cobro online
	 */
	public ShvCobDescobroAdjunto insertarDocumentoAdjunto(ShvCobDescobroAdjunto registroAdjunto) throws PersistenciaExcepcion {
		try {
			
			registroAdjunto = descobroAdjuntoRepositorio.save(registroAdjunto);
			descobroAdjuntoRepositorio.flush();
			return registroAdjunto;
			
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	/**
	 * Busca un descobro en la base.
	 */
	public ShvCobDescobro buscarDescobro(Long id) throws PersistenciaExcepcion {
		try {
			return descobroRepositorio.findOne(id);
			
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	/**
	 * Actualiza el Descobro en la base.
	 */
	public ShvCobDescobro modificar(ShvCobDescobro descobroModelo) throws PersistenciaExcepcion {
		try {
			if(Validaciones.isObjectNull(descobroModelo.getOperacion().getIdOperacion())){
				
				// Si el IdOperacion es null lo obtengo de la secuencia de la 
				// base, porque se borro el mapeo de la secuencia del modelo
				QueryParametrosUtil qp = new QueryParametrosUtil();	
				String query = "select SEQ_SHV_COB_OPERACION.NEXTVAL from dual";
				qp.setSql(query);
				List<Map<String,Object>> listaResultadoQuery = buscarUsandoSQL(qp);
				if(Validaciones.isCollectionNotEmpty(listaResultadoQuery)){
					String idOperacion = listaResultadoQuery.get(0).get("NEXTVAL").toString();
					descobroModelo.getOperacion().setIdOperacion(Long.valueOf(idOperacion));
				}
			}
			
			ShvCobDescobro descobroBD = descobroRepositorio.save(descobroModelo);
			descobroRepositorio.flush();
			return descobroBD;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	/**
	 * Lista todos los descobros en estado Pendiente/En Proceso
	 */
	@SuppressWarnings("unchecked")
	public List<Long> listarDescobrosPendientes() throws PersistenciaExcepcion {
		try {
			String query = "select des.operacion.idOperacion "
					+ " from ShvCobDescobro as des "
					+ " join des.workflow as w "
					+ " join w.shvWfWorkflowEstado as we "
//					+ " join des.operacion.transacciones as t "
					+ " where we.estado in (?,?) "
					+ " and w.tipoWorkflow = ? "
//					+ " group by des.operacion.idOperacion "
					+ " order by we.fechaModificacion asc";
			
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addParametro(Estado.DES_PENDIENTE);
			qp.addParametro(Estado.DES_DESCOBRO_PROCESO);
			qp.addParametro(TipoWorkflow.DESCOBRO);
			
			List<Long> resultado = (List<Long>) buscarUsandoQueryConParametros(qp);
			return resultado;
			
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	/**
	 * Lista todos los cobros en estado Pendiente de MIC
	 */
	@SuppressWarnings("unchecked")
	public List<Long> listarDescobrosPendientesProcesarMIC() throws PersistenciaExcepcion {
		try {
			String query = "select des.operacion.idOperacion "
					+ " from ShvCobDescobro as des "
					+ " join des.workflow as w "
					+ " join w.shvWfWorkflowEstado as we "
					+ " join des.operacion.transacciones as t "
					+ " where we.estado in (?) "
					+ " group by des.operacion.idOperacion "
					+ " order by count(t) asc";
			
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addParametro(Estado.DES_PEND_PROCESAR_MIC);
			
			List<Long> resultado = (List<Long>) buscarUsandoQueryConParametros(qp);
			return resultado;
			
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	/**
	 * Retorna un un descobro por el idTransaccion.
	 */
	public ShvCobDescobro buscarDescobroPorIdTransaccion(Integer idTransaccion) throws PersistenciaExcepcion {
		try {
			String query = "select c from ShvCobDescobro as c "
					+ "join c.operacion.transacciones as t where t.idTransaccion=? ";
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addParametro(idTransaccion);
			
			return (ShvCobDescobro) buscarUsandoQueryConParametros(qp).get(0);
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	/**
	 * Retorna un un descobro por el idOperacion.
	 */
	public ShvCobDescobro buscarDescobroPorIdOperacion(Long idOperacion) throws PersistenciaExcepcion {
		try {
			String query = "select c from ShvCobDescobro as c "
					+ "join c.operacion as o where o.idOperacion=? ";
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addParametro(idOperacion);
			Object object = null;
			if(Validaciones.isCollectionNotEmpty(buscarUsandoQueryConParametros(qp))){
				object = buscarUsandoQueryConParametros(qp).get(0);
			}
			return (ShvCobDescobro) object;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	/**
	 * @author u572487 Guido.Settecerze
	 * Retorna un descobro por el idOperacionCobro.
	 */
	@SuppressWarnings("unchecked")
	public List<ShvCobDescobroOperacionRelacionada> buscarOperacionRelacionadaDescobroPorIdOperacionCobro(Long idOperacionCobro) throws PersistenciaExcepcion {
		try {
			String query = "select c from ShvCobDescobroOperacionRelacionada as c "
					+ "where c.idOperacion=? ";
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addParametro(idOperacionCobro);
			
			return (List<ShvCobDescobroOperacionRelacionada>) buscarUsandoQueryConParametros(qp);
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> buscarCobrosParaSubdiario(Filtro subdiarioFiltro) throws PersistenciaExcepcion {
		List<Object[]> listaCobros = new ArrayList<Object[]>();
	
		GregorianCalendar fechaFin = new GregorianCalendar(); 
		GregorianCalendar fechaInicio = new GregorianCalendar();
		fechaInicio.set(GregorianCalendar.DAY_OF_MONTH, 1);
		BeanUtils.copyProperties(fechaInicio, fechaFin);
		fechaInicio.roll(GregorianCalendar.MONTH, -1);
		String consultaFechaInicio = "TO_DATE('"+Utilidad.formatDatePicker(fechaInicio.getTime())+"', 'dd/mm/yyyy')";
		String consultaFechaFin = "TO_DATE('"+Utilidad.formatDatePicker(fechaFin.getTime())+"', 'dd/mm/yyyy')";
		
		try {
//			TODO Finalizar modificacion fecha hasta fabio giaquinta
//			List<GregorianCalendar> listaFechas= Utilidad.fechasMesAnterior(subdiarioFiltro.getFechaHasta());
			
			String query = "select desWe.fechaModificacion as Fecha, fac as Factura " + 
			" from ShvCobCobro as cobro " + 
			" join cobro.operacion.transacciones as tran " +
			" join tran.shvCobFactura as fac " +
			" join cobro.workflow.shvWfWorkflowEstado as weCobro " +
			" join cobro.descobro.workflow.shvWfWorkflowEstado as desWe " + 
			" where (desWe.estado = ?)" +
			" and (desWe.fechaModificacion >= "+consultaFechaInicio+" and desWe.fechaModificacion < "+consultaFechaFin+")";
			
			
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addParametro(Estado.DES_DESCOBRADO);
//			TODO Finalizar modificacion fecha hasta fabio giaquinta
//			qp.addParametro(new String(Utilidad.formatDatePicker(listaFechas.get(0).getTime())));
//			qp.addParametro(new String(Utilidad.DATE_FORMAT_PICKER));
//			qp.addParametro(new String(Utilidad.formatDatePicker(listaFechas.get(1).getTime())));
//			qp.addParametro(new String(Utilidad.DATE_FORMAT_PICKER));
			
			listaCobros =  (List<Object[]>) buscarUsandoQueryConParametros(qp);
		} catch (PersistenciaExcepcion
//				| ParseException 
				e) {
			throw new PersistenciaExcepcion(e.getMessage(),e);
		}
		return listaCobros;
	}
	
	/**
	 * @author u573005, fabio.giaquinta.ruiz
	 * levanto los descobros que estan pendientes de simular
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ShvCobDescobro> buscarDescobrosSimulacionEnProceso()
			throws PersistenciaExcepcion {
		
		StringBuffer query = new StringBuffer();
		
		query.append("SELECT descobro from ShvCobDescobro as descobro WHERE descobro.workflow IN (");
		query.append("SELECT wf ");
		query.append("FROM ShvWfWorkflowMarca marca LEFT JOIN marca.workflowEstado LEFT JOIN marca.workflowEstado.workflow as wf ");
		query.append("WHERE marca.marca = ? AND marca.workflowEstado.estado in ( ? , ? ) AND marca.fechaCreacion = ( ");
		query.append("SELECT max(marca2.fechaCreacion) ");
		query.append("FROM ShvWfWorkflowMarca marca2 ");
		query.append("LEFT JOIN marca2.workflowEstado ");
		query.append("WHERE marca2.workflowEstado = marca.workflowEstado )) order by descobro.idDescobro");

		QueryParametrosUtil qp = new QueryParametrosUtil(query.toString());
		qp.addParametro(MarcaEnum.SIMULACION_BATCH_EN_PROCESO);
		qp.addParametro(Estado.DES_EN_EDICION);
		qp.addParametro(Estado.DES_ERROR_EN_PRIMER_DESCOBRO);

		List<ShvCobDescobro> listaDescobros = buscarUsandoQueryConParametros(qp);
		
		return listaDescobros;
	}
	
	public ShvCobDescobroOperacionRelacionada insertarOperacionRelacionada(ShvCobDescobroOperacionRelacionada registro) throws PersistenciaExcepcion {
		try {
			
			registro = descobroOperacionRelacionadaRepositorio.save(registro);
			descobroOperacionRelacionadaRepositorio.flush();
			return registro;
			
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	public void borrarOperacionesRelacionadas(Set<ShvCobDescobroOperacionRelacionada> registrosABorrar) throws PersistenciaExcepcion {
		try {
			
			descobroOperacionRelacionadaRepositorio.delete(registrosABorrar);
			descobroOperacionRelacionadaRepositorio.flush();
			
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	public void borrarDescobro(ShvCobDescobro registrosABorrar) throws PersistenciaExcepcion {
		try {
//			registrosABorrar.setOperacionesRelacionadas(null);
//			registrosABorrar.setOperacion(null);
//			descobroRepositorio.save(registrosABorrar);
//			descobroRepositorio.flush();
			descobroRepositorio.delete(registrosABorrar);
			descobroRepositorio.flush();
			
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	
	public List<ResultadoBusquedaDatosSimulacion> buscarDatosSimulacion(Long idOperacion) throws PersistenciaExcepcion {
		
		StringBuffer query = new StringBuffer("select distinct descobro.id_analista as ID_ANALISTA ,");
		query.append(" descobro.id_copropietario as ID_COPROPIETARIO, ");
		query.append(" atc.USER_ANALISTA_COBRANZA_DATOS as ID_ANALISTA_TC, ");
		query.append(" cli.id_cliente_legado as ID_CLIENTE_LEGADO,");
		query.append(" cli.razon_social as RAZON_SOCIAL_CLIENTE,");
		query.append(" descobro.importe_total as IMPORTE");
		query.append(" from shv_cob_descobro descobro ");
		query.append(" left join shv_cob_ed_cliente cli on descobro.id_cobro = cli.id_cobro");
		query.append(" left join SHV_PARAM_TEAM_COMERCIAL atc on atc.NRO_DE_CLIENTE = cli.id_cliente_legado");
		query.append(" where descobro.id_operacion = ? ORDER BY CLI.ID_CLIENTE_LEGADO");

		QueryParametrosUtil qp = new QueryParametrosUtil(query.toString());
		qp.addCampoAlQuery(idOperacion,Types.NUMERIC);
		
		List<Map<String, Object>> listaResultadoQuery;
		
		try {
			listaResultadoQuery = buscarUsandoSQL(qp);
	
			List<ResultadoBusquedaDatosSimulacion> listaDatos = new ArrayList<ResultadoBusquedaDatosSimulacion>();
		
			for (Map<String, Object> archivo : listaResultadoQuery) {

				ResultadoBusquedaDatosSimulacion datos = new ResultadoBusquedaDatosSimulacion();
				datos.setAnalista(archivo.get("ID_ANALISTA").toString());
				datos.setIdClienteLegado(new Long(archivo.get("ID_CLIENTE_LEGADO").toString()));
				
				if (!Validaciones.isObjectNull(archivo.get("IMPORTE"))) {
					datos.setImporte((BigDecimal)archivo.get("IMPORTE"));
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
	public List<ResultadoBusquedaDatosImputacion> buscarDatosImputacion(
			Long idOperacion) throws PersistenciaExcepcion {
		
		StringBuffer query = new StringBuffer("select distinct cli.segmento_agencia_negocio as SEG_DESCRI, descobro.id_analista as ID_ANALISTA , descobro.analista as ANALISTA, ");
		query.append(" descobro.id_copropietario as ID_COPROPIETARIO, ");
		query.append(" atc.USER_ANALISTA_COBRANZA_DATOS as ID_ANALISTA_TC, ");
		query.append(" cli.id_cliente_legado as ID_CLIENTE_LEGADO, ");
		query.append(" cli.razon_social as RAZON_SOCIAL_CLIENTE, ");
		query.append(" descobro.importe_total as IMPORTE, ");
		query.append(" cli.cuit As CUIT, ");
		query.append("(");
		query.append("SELECT LISTAGG(scecoe.CODIGO_OPERACION_EXTERNA, ', ') ");
		query.append("WITHIN GROUP(ORDER BY scecoe.CODIGO_OPERACION_EXTERNA) ");
		query.append("FROM  shv_cob_descobro_cod_oper_ext scecoe ");
		query.append("WHERE scecoe.ID_DESCOBRO = descobro.ID_DESCOBRO ");
		query.append(")                     AS CODIGO_OPERACION_EXTERNA ");
		query.append(" from shv_cob_descobro descobro ");
		query.append(" left join shv_cob_ed_cliente cli on descobro.id_cobro = cli.id_cobro");
		query.append(" left join SHV_PARAM_TEAM_COMERCIAL atc on atc.NRO_DE_CLIENTE = cli.id_cliente_legado");
		query.append(" where descobro.id_operacion = ? ");
		query.append(" ORDER BY CLI.ID_CLIENTE_LEGADO");

		QueryParametrosUtil qp = new QueryParametrosUtil(query.toString());
		qp.addCampoAlQuery(idOperacion,Types.NUMERIC);
		
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
				if (!Validaciones.isObjectNull(archivo.get("SEG_DESCRI"))) {
					datos.setSegmento((String) archivo.get("SEG_DESCRI"));
				}
				if (!Validaciones.isObjectNull(archivo.get("ID_CLIENTE_LEGADO"))) {
					datos.setNumCliente(new Long(archivo.get("ID_CLIENTE_LEGADO").toString()));
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
				if (!Validaciones.isObjectNull(archivo.get("IMPORTE"))) {
					datos.setImporte((BigDecimal)archivo.get("IMPORTE"));
				}
				if (!Validaciones.isObjectNull(archivo.get("CUIT"))) {
					datos.setCuit(archivo.get("CUIT").toString());
				}
				if (!Validaciones.isObjectNull(archivo.get("CODIGO_OPERACION_EXTERNA"))) {
					datos.setCodigosDeOperacionExterna(archivo.get("CODIGO_OPERACION_EXTERNA").toString());
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

	/**
	 * Lista los adjuntos del cobro online
	 * @author u572487 Guido.Settecerze
	 */
	public List<ShvDocDocumentoAdjunto> buscarAdjuntosDelDescobroOnline(Long idDescobro) throws PersistenciaExcepcion {
		try {
			List<ShvDocDocumentoAdjunto> list = new ArrayList<ShvDocDocumentoAdjunto>();
			
			List<ShvCobDescobroAdjunto> lista = descobroAdjuntoRepositorio.buscarAdjuntosDescobrosOnline(idDescobro);  
			if(Validaciones.isCollectionNotEmpty(lista)){
				for (ShvCobDescobroAdjunto descobroAdjunto : lista) {
					list.add(descobroAdjunto.getPk().getDocumentoAdjunto());
				}
			}
			return list;
	
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	public List<ShvCobDescobroDocumentoRelacionado> buscarDocumentosRelacionadosDescobro(Long idDescobro) throws PersistenciaExcepcion {
		try {
			
			List<ShvCobDescobroDocumentoRelacionado> lista = descobroDocumentoRelacionadoRepositorio.buscarDocumentosRelacionadosDescobro(idDescobro);  
			return lista;
	
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public synchronized ShvWfWorkflow buscarWorkflowPorIdOperacion(Long idOperacion) throws PersistenciaExcepcion {
		
		String query = "select w "
				+ " from ShvCobDescobro as des "
				+ " join des.workflow as w "
				+ " where des.operacion.idOperacion = ? ";
		QueryParametrosUtil qp = new QueryParametrosUtil(query.toString());
		qp.addCampoAlQuery(idOperacion, Types.NUMERIC);
		
		List<ShvWfWorkflow> lista = (List<ShvWfWorkflow>) buscarUsandoQueryConParametros(qp);
		
		if(Validaciones.isCollectionNotEmpty(lista)){
			return lista.get(0);
		}
		return null;
	}
	
	/**
	 * @author u572487 Guido.Settecerze
	 * 
	 */
	public List<ShvDocDocumentoAdjunto> buscarPorIdAdjuntoDescobrosOnline(Long idAdjunto) throws PersistenciaExcepcion {
		try {
			List<ShvDocDocumentoAdjunto> list = new ArrayList<ShvDocDocumentoAdjunto>();
			
			List<ShvCobDescobroAdjunto> lista = descobroAdjuntoRepositorio.buscarPorIdAdjuntoDescobrosOnline(idAdjunto);  
			if(Validaciones.isCollectionNotEmpty(lista)){
				for (ShvCobDescobroAdjunto descobroAdjunto : lista) {
					list.add(descobroAdjunto.getPk().getDocumentoAdjunto());
				}
			}
			return list;
			
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	

	/**
	 * @author u572487 Guido.Settecerze
	 * @param documentoAdjunto
	 * @throws PersistenciaExcepcion
	 */
	@Override
	public void eliminarDocumentoAdjuntoDelDescobro(ShvDocDocumentoAdjunto documentoAdjunto) throws PersistenciaExcepcion {
		
		List<ShvCobDescobroAdjunto> lista = 
				descobroAdjuntoRepositorio.buscarPorIdAdjuntoDescobrosOnline(documentoAdjunto.getIdValorAdjunto());
		
		if(Validaciones.isCollectionNotEmpty(lista)){
			ShvCobDescobroAdjunto regAdjunto = lista.get(0);
			descobroAdjuntoRepositorio.delete(regAdjunto);
			descobroAdjuntoRepositorio.flush();
			documentoAdjuntoRepositorio.delete(documentoAdjunto);
			documentoAdjuntoRepositorio.flush();
		}
	}
	
	/**
	 * Getters & Setters
	 */
	
	public DescobroRepositorio getDescobroRepositorio() {
		return descobroRepositorio;
	}

	public void setDescobroRepositorio(DescobroRepositorio descobroRepositorio) {
		this.descobroRepositorio = descobroRepositorio;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ShvCobDescobro buscarDescobroPorIdCobro(Long idCobro) throws PersistenciaExcepcion {
		
		
		String query = "select descobro "
				+ " from ShvCobDescobro as descobro "
				+ " join descobro.workflow as w "
				+ " join w.shvWfWorkflowEstado as we "
				+ " where descobro.idCobro = ? "
				+ " and we.estado != ? ";
		
		QueryParametrosUtil qp = new QueryParametrosUtil(query);
		qp.addParametro(idCobro);
		qp.addParametro(Estado.DES_ANULADO);
		
		List<ShvCobDescobro> resultado =   buscarUsandoQueryConParametros(qp);
		if(Validaciones.isCollectionNotEmpty(resultado)){
			return resultado.get(0);
		}
		
		return null;

	}
	
	@Override
	public Long buscarIdDescobroPorIdOperacionCobro(Long idOperacion) throws PersistenciaExcepcion {
		
		List<Long> resultado = new ArrayList<Long>();

		String query = "select des.id_operacion from shv_cob_descobro des "
		+ " inner join shv_cob_cobro cob on des.id_cobro = cob.id_cobro "
		+ " inner join shv_wf_workflow_estado wf on wf.id_workflow = des.id_workflow "
		+ " where "
		+ " cob.id_operacion = ? "
		+ " and wf.estado not in (?) ";
	
		QueryParametrosUtil qp = new QueryParametrosUtil(query);
		qp.addCampoAlQuery(idOperacion,Types.NUMERIC);
		qp.addCampoAlQuery(Estado.DES_ANULADO,Types.VARCHAR);
		
		List<Map<String, Object>> listaResultadoQuery;
		
			listaResultadoQuery = buscarUsandoSQL(qp);
	
			for (Map<String, Object> archivo : listaResultadoQuery) {
				resultado.add(((BigDecimal) archivo.get("ID_OPERACION")).longValue());
			}
		
		return resultado.get(0);

	}
	/**
	 * 
	 * @param idOperacion
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	@Override
	public ShvCobDescobroSimplificadoCodigoExternoOperacion buscarDescobroSimplificadoCodigoExternoOperacion(Long idOperacion) throws PersistenciaExcepcion {
		try {
			return descobroSimplificadoCodigoOperacionExternaRepositorio.find(idOperacion);
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	@SuppressWarnings("unchecked")
	public ShvCobDescobroSimpleSinWorkflow buscarDescobroSimpleSinWorkflowPorIdOperacion(Long idOperacion) throws PersistenciaExcepcion {
		try {
			String query = "select c from ShvCobDescobroSimpleSinWorkflow as c where idOperacion=?";
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addParametro(idOperacion);

			List<ShvCobDescobroSimpleSinWorkflow> listaCobro = (List<ShvCobDescobroSimpleSinWorkflow>) buscarUsandoQueryConParametros(qp);
			return listaCobro.get(0);
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
}
