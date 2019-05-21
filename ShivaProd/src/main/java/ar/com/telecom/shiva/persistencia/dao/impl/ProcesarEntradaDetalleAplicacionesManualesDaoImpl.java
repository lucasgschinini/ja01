package ar.com.telecom.shiva.persistencia.dao.impl;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.enumeradores.MensajeEstadoEnum;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoOperacionEnum;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.persistencia.bean.ResultadoBusquedaDetalleAplicacionManual;
import ar.com.telecom.shiva.persistencia.bean.ResultadoBusquedaImporteTransaccionesAplicacionManual;
import ar.com.telecom.shiva.persistencia.dao.Dao;
import ar.com.telecom.shiva.persistencia.dao.IProcesarEntradaDetalleAplicacionesManualesDao;
import ar.com.telecom.shiva.persistencia.dao.util.QueryParametrosUtil;
import ar.com.telecom.shiva.persistencia.modelo.ShvArcArchivo;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobAplicacionManualBatchDetalle;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvCobDescobroCodigoOperacionExternaSimplificado;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvCobEdCodigoOperacionExternaSimplificado;
import ar.com.telecom.shiva.persistencia.repository.AplicacionManualBatchDetalleRepositorio;
import ar.com.telecom.shiva.persistencia.repository.ArchivoEntradaAplicacionManualRepositorio;
import ar.com.telecom.shiva.persistencia.repository.CodigoOperacionExternaSimplificadoDescobroRepositorio;
import ar.com.telecom.shiva.persistencia.repository.CobroCodigoOperacionExternaSimplificadoRepositorio;

public class ProcesarEntradaDetalleAplicacionesManualesDaoImpl extends Dao implements IProcesarEntradaDetalleAplicacionesManualesDao{

	@Autowired
	ArchivoEntradaAplicacionManualRepositorio archivoEntradaAplicacionManualRepositorio;
	
	@Autowired
	AplicacionManualBatchDetalleRepositorio aplicacionManualBatchDetalleRepositorio;
	
	@Autowired
	CobroCodigoOperacionExternaSimplificadoRepositorio cobroCodigoOperacionExternaSimplificadoRepositorio;
	
	@Autowired
	CodigoOperacionExternaSimplificadoDescobroRepositorio codigoOperacionExternaSimplificadoDescobroRepositorio;
	
	@Override
	public List<ResultadoBusquedaDetalleAplicacionManual> obtenerDatosRegistro(Long idOperacion, TipoOperacionEnum tipoOperacion)throws PersistenciaExcepcion {
		
		String query = "";
		
		if(TipoOperacionEnum.COBRO.equals(tipoOperacion)){
			query = "select cob.id_operacion,cob.id_cobro,cob.moneda_operacion, wf.estado, atr.sistema_destino, "
					+"tr.id_transaccion, "
					+"lpad(tr.id_operacion, 7, '0') ||'.' ||lpad(tr.numero_transaccion, 5, '0') numero_transaccion_formateado, "
					+"tdif.id_tratamiento_diferencia, "
					+"mp.id_medio_pago, "
					+"mp.moneda_importe moneda_importetotal_medio_pago , "
					+"mp.importe importe_total_medio_pago "
					+"from shv_cob_cobro cob "
					+"inner join shv_wf_workflow_estado wf on wf.id_workflow = cob.id_workflow "
					+"inner join shv_cob_ed_tratamiento_dif atr on atr.id_cobro = cob.id_cobro "
					+"inner join shv_cob_transaccion tr on tr.id_operacion = cob.id_operacion "
					+"inner join shv_cob_tratamiento_diferencia tdif on tdif.id_transaccion = tr.id_transaccion "
					+"inner join shv_cob_med_pago mp on mp.id_transaccion = tr.id_transaccion "
					+"where cob.id_operacion=?";
		} else if (TipoOperacionEnum.DESCOBRO.equals(tipoOperacion)){
			query = "select cob.id_operacion,des.id_operacion des_idoperacion,des.id_descobro,cob.id_cobro ,des.moneda_operacion, wf.estado, atr.sistema_destino, "
					+"tr.id_transaccion, "
					+"lpad(tr.id_operacion, 7, '0') ||'.' ||lpad(tr.numero_transaccion, 5, '0') numero_transaccion_formateado, "
					+"tdif.id_tratamiento_diferencia, "
					+"mp.id_medio_pago, "
					+"mp.moneda_importe moneda_importetotal_medio_pago, "
					+"mp.importe importe_total_medio_pago  "
					+ "from shv_cob_descobro des "
					+"inner join shv_cob_cobro cob on des.id_cobro = cob.id_cobro "
					+"inner join shv_wf_workflow_estado wf on wf.id_workflow = des.id_workflow "
					+"inner join shv_cob_ed_tratamiento_dif atr on atr.id_cobro = cob.id_cobro "
					+"inner join shv_cob_transaccion tr on tr.id_operacion = des.id_operacion "
					+"inner join shv_cob_tratamiento_diferencia tdif on tdif.id_transaccion = tr.id_transaccion "
					+"inner join shv_cob_med_pago mp on mp.id_transaccion = tr.id_transaccion "
					+"where cob.id_operacion=? "
					+"and wf.estado<>?";
		}
		
		
		QueryParametrosUtil qp = new QueryParametrosUtil(query);
		qp.addCampoAlQuery(idOperacion,Types.NUMERIC);
		qp.addCampoAlQuery(Estado.DES_ANULADO.name(), Types.VARCHAR);
		
		List<ResultadoBusquedaDetalleAplicacionManual> lista= new ArrayList<ResultadoBusquedaDetalleAplicacionManual>();
		
		List<Map<String,Object>> listaResultadoQuery = buscarUsandoSQL(qp);
		for (Map<String, Object> archivo : listaResultadoQuery) {
			
			ResultadoBusquedaDetalleAplicacionManual resultado = new ResultadoBusquedaDetalleAplicacionManual();
			
			if (!Validaciones.isObjectNull(archivo.get("DES_IDOPERACION"))){
				resultado.setIdOperacionDescobro(Long.valueOf(archivo.get("DES_IDOPERACION").toString()));
			}
			
			if (!Validaciones.isObjectNull(archivo.get("ID_OPERACION"))){
				resultado.setIdOperacion(Long.valueOf(archivo.get("ID_OPERACION").toString()));
			}
			
			if (!Validaciones.isObjectNull(archivo.get("ESTADO"))){
				resultado.setEstadoCobro(Estado.getEnumByName((archivo.get("ESTADO").toString())));
			}
			
			if (!Validaciones.isObjectNull(archivo.get("SISTEMA_DESTINO"))){
				resultado.setSistema(SistemaEnum.getEnumByName(archivo.get("SISTEMA_DESTINO").toString()));
			}
			
			if (!Validaciones.isObjectNull(archivo.get("MONEDA_OPERACION"))){
				resultado.setMoneda(MonedaEnum.getEnumByName(archivo.get("MONEDA_OPERACION").toString()));
			}
			
			if (!Validaciones.isObjectNull(archivo.get("ID_COBRO"))){
				resultado.setIdCobro(Long.valueOf(archivo.get("ID_COBRO").toString()));
			}
			
			if (!Validaciones.isObjectNull(archivo.get("ID_DESCOBRO"))){
				resultado.setIdDescobro(Long.valueOf(archivo.get("ID_DESCOBRO").toString()));
			}
			
			if (!Validaciones.isObjectNull(archivo.get("ID_TRANSACCION"))){
				resultado.setIdTransaccion(Long.valueOf(archivo.get("ID_TRANSACCION").toString()));
			}
			
			if (!Validaciones.isObjectNull(archivo.get("NUMERO_TRANSACCION_FORMATEADO"))){
				resultado.setNumTransaccionFormateado(archivo.get("NUMERO_TRANSACCION_FORMATEADO").toString());
			}
			
			if (!Validaciones.isObjectNull(archivo.get("ID_TRATAMIENTO_DIFERENCIA"))){
				resultado.setIdTratamientoDiferencia(Long.valueOf(archivo.get("ID_TRATAMIENTO_DIFERENCIA").toString()));
			}
			
			if (!Validaciones.isObjectNull(archivo.get("ID_MEDIO_PAGO"))){
				resultado.setIdMedioPago(Long.valueOf(archivo.get("ID_MEDIO_PAGO").toString()));
			}
			
			if (!Validaciones.isObjectNull(archivo.get("MONEDA_IMPORTETOTAL_MEDIO_PAGO"))){
				resultado.setMonedaImporteTotalMedioPago(MonedaEnum.getEnumByName(archivo.get("MONEDA_IMPORTETOTAL_MEDIO_PAGO").toString()));
			}
		
			if (!Validaciones.isObjectNull(archivo.get("IMPORTE_TOTAL_MEDIO_PAGO"))){
				resultado.setImporteTotalMedioPago((BigDecimal) archivo.get("IMPORTE_TOTAL_MEDIO_PAGO"));
			}
			
			lista.add(resultado);
			
		}
		
		return lista;
	}
	
	@Override
	public List<ShvCobAplicacionManualBatchDetalle> obtenerListaShvCobAplicacionManualBatchDetalle(List<String> listaIdTransacciones, TipoOperacionEnum tipoOperacion) throws PersistenciaExcepcion {
		
		
		String query = "select det from ShvCobAplicacionManualBatchDetalle as det where det.tipoOperacion=? and det.estadoTarea=?";
		
		if (TipoOperacionEnum.COBRO.equals(tipoOperacion)){
			query += " and det.idTransaccionCobro in (";
		} else if (TipoOperacionEnum.DESCOBRO.equals(tipoOperacion)){
			query += " and det.idTransaccionDescobro in (";
		}	

		for (int i = 0; i < listaIdTransacciones.size(); i++) {
			query+="?,";
		}
		query = query.substring(0,query.length()-1);
		query+=")";
		
		QueryParametrosUtil qp = new QueryParametrosUtil(query);
		qp.addCampoAlQuery(tipoOperacion.tipoOperacionesExternas(),Types.VARCHAR);
		qp.addCampoAlQuery(MensajeEstadoEnum.PENDIENTE,Types.VARCHAR);
		for (String idTransaccion : listaIdTransacciones) {
			qp.addCampoAlQuery(idTransaccion,Types.VARCHAR);
		}
		
		@SuppressWarnings("unchecked")
		List<ShvCobAplicacionManualBatchDetalle> lista = (List<ShvCobAplicacionManualBatchDetalle>) buscarUsandoQueryConParametros(qp);
		
		return lista;
	}
	
	@Override
	public ShvArcArchivo insertarArcArchivo(ShvArcArchivo archivo) throws PersistenciaExcepcion {
		try{
			ShvArcArchivo modeloNuevo = archivoEntradaAplicacionManualRepositorio.save(archivo);
			archivoEntradaAplicacionManualRepositorio.flush();
			return modeloNuevo;
			
		}catch(Exception e){
			throw new PersistenciaExcepcion(e);
		}
		
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public ShvArcArchivo buscarArchivoEntradaPorNombreArchivo(String name) throws PersistenciaExcepcion {
		try {
			String query = "from ShvArcArchivo as archivo where archivo.nombreArchivo=?";
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addParametro(name);
			
			List<ShvArcArchivo> lista = (List<ShvArcArchivo>) buscarUsandoQueryConParametros(qp);
			if(Validaciones.isCollectionNotEmpty(lista)){
				return lista.get(0);
			}else{
				return null;
			}
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	@Override
	public ShvCobAplicacionManualBatchDetalle actualizarShvCobAplicacionManualBatchDetalle(ShvCobAplicacionManualBatchDetalle detModelo) throws PersistenciaExcepcion{
		try {
			ShvCobAplicacionManualBatchDetalle detBD = aplicacionManualBatchDetalleRepositorio.save(detModelo);
			aplicacionManualBatchDetalleRepositorio.flush();
			return detBD;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	@Override
	public List<ResultadoBusquedaImporteTransaccionesAplicacionManual> obtenerImporteTransaccionesAplicacionManual(Long idOperacion) throws PersistenciaExcepcion {
		
		String query =
				" select " +
				" lpad(tr.id_operacion, 7, '0') " +
				" ||'.' " +
				" ||lpad(tr.numero_transaccion, 5, '0') as numero_transaccion_formateado," +
				" td.importe  " +
				" from shv_cob_tratamiento_diferencia td " +
				" inner join shv_cob_transaccion tr on tr.id_transaccion = td.id_transaccion" +
				" where tipo_tratamiento_diferencia like '%APLICACION_MANUAL%' "+
				" and" +
				" tr.id_operacion=?";
		
		QueryParametrosUtil qp = new QueryParametrosUtil(query);
		qp.addCampoAlQuery(idOperacion,Types.NUMERIC);
		
		List<ResultadoBusquedaImporteTransaccionesAplicacionManual> lista = new ArrayList<ResultadoBusquedaImporteTransaccionesAplicacionManual>();

		List<Map<String,Object>> listaResultadoQuery = buscarUsandoSQL(qp);
		
		for (Map<String, Object> archivo : listaResultadoQuery) {
			
			ResultadoBusquedaImporteTransaccionesAplicacionManual resultado = new ResultadoBusquedaImporteTransaccionesAplicacionManual();
			
			if (!Validaciones.isObjectNull(archivo.get("NUMERO_TRANSACCION_FORMATEADO"))){
				resultado.setNumTransaccionFormateado(archivo.get("NUMERO_TRANSACCION_FORMATEADO").toString());
			}
			
			if (!Validaciones.isObjectNull(archivo.get("IMPORTE"))){
				resultado.setImporte((BigDecimal) archivo.get("IMPORTE"));
			}
			
			lista.add(resultado);
		}
		
		return lista;
	}

	@Override
	public ShvCobEdCodigoOperacionExternaSimplificado guardarShvCobEdCodigoOperacionExternaSimplificado(ShvCobEdCodigoOperacionExternaSimplificado codOpExterna) throws PersistenciaExcepcion {
		try {
			ShvCobEdCodigoOperacionExternaSimplificado codOpExternaBD = cobroCodigoOperacionExternaSimplificadoRepositorio.save(codOpExterna);
			cobroCodigoOperacionExternaSimplificadoRepositorio.flush();

			return codOpExternaBD;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	@Override
	public ShvCobDescobroCodigoOperacionExternaSimplificado guardarShvCobDescobroCodigoOperacionExternaSimplificado(ShvCobDescobroCodigoOperacionExternaSimplificado codOpExterna) throws PersistenciaExcepcion {
		try {
			ShvCobDescobroCodigoOperacionExternaSimplificado codOpExternaBD = codigoOperacionExternaSimplificadoDescobroRepositorio.save(codOpExterna);
			codigoOperacionExternaSimplificadoDescobroRepositorio.flush();

			return codOpExternaBD;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	

}
