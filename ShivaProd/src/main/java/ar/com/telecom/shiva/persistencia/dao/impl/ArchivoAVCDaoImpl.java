package ar.com.telecom.shiva.persistencia.dao.impl;

import java.sql.Types;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.persistencia.bean.ResultadoBusquedaArchivoAVC;
import ar.com.telecom.shiva.persistencia.dao.Dao;
import ar.com.telecom.shiva.persistencia.dao.IArchivoAVCDao;
import ar.com.telecom.shiva.persistencia.dao.util.QueryParametrosUtil;
import ar.com.telecom.shiva.persistencia.modelo.ShvAvcArchivoAvc;
import ar.com.telecom.shiva.persistencia.repository.ArchivoAVCRepositorio;
import ar.com.telecom.shiva.presentacion.bean.filtro.ArchivoAVCFiltro;

public class ArchivoAVCDaoImpl extends Dao implements IArchivoAVCDao{

	@Autowired ArchivoAVCRepositorio archivoAVCRepositorio;
	
	/**
	 * Inserta el archivo
	 * @param ArchivosAVC
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public ShvAvcArchivoAvc crear(ShvAvcArchivoAvc archivo) throws PersistenciaExcepcion {
		try{
		
			ShvAvcArchivoAvc archivoAVCBD = archivoAVCRepositorio.save(archivo);
			archivoAVCRepositorio.flush();
			return archivoAVCBD;
		} catch(Exception e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	/**
	 * Inserta el archivo
	 * @param ArchivosAVC
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public ShvAvcArchivoAvc modificar(ShvAvcArchivoAvc archivo) throws PersistenciaExcepcion {
		try{
			ShvAvcArchivoAvc archivoAVCBD = archivoAVCRepositorio.save(archivo);
			archivoAVCRepositorio.flush();
			return archivoAVCBD;
		}catch(Exception e){
			throw new PersistenciaExcepcion(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public ShvAvcArchivoAvc buscarArchivoPorIdArchivo(Integer id) throws PersistenciaExcepcion {
		try {
			String query = "from ShvAvcArchivoAvc where idArchivosAvc=?";
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addParametro(new Long(id.toString()));
			
			List<ShvAvcArchivoAvc> archivo = (List<ShvAvcArchivoAvc>)buscarUsandoQueryConParametros(qp); 
			return archivo.get(0);
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	/**
	 * Retorna los archivos que contengan conciliaciones exitosas con una semana de antigüedad y 
	 * todos los archivos que aún posean registros pendientes de conciliar sin importar la antigüedad.
	 * Se consideran registros pendientes: “Pendiente de Conciliación”, “Conciliación Sugerida”,
	 * “Pendiente de Confirmar Alta de Valor” y “Alta de Valor Rechazada”.
	 */
	public Collection<ResultadoBusquedaArchivoAVC> buscarArchivosAvcParaResultadoConciliacion(ArchivoAVCFiltro archivoFiltro) throws PersistenciaExcepcion {
		List<ResultadoBusquedaArchivoAVC> resultado = (List<ResultadoBusquedaArchivoAVC>) buscarArchivosAvc(archivoFiltro);
		if(Validaciones.isCollectionNotEmpty(resultado)){
			Iterator<ResultadoBusquedaArchivoAVC> it = resultado.iterator();
			while (it.hasNext()) {
				ResultadoBusquedaArchivoAVC archivoAVC = it.next();
				if(archivoAVC.getRegistrosSinConciliar()==0l && archivoAVC.getRegistrosConciliacionSugerida()==0l
						&& (archivoAVC.getRegistrosConciliados()+archivoAVC.getRegistrosAnulados())==archivoAVC.getRegistrosProcesados()){
					if(Utilidad.diferenciaDias(new Date(),archivoAVC.getFechaProcesamiento())>7){
						it.remove();
					}
			  }
			}
		}
		return resultado;
	}
	
	public Collection<ResultadoBusquedaArchivoAVC> buscarArchivosAvc(ArchivoAVCFiltro archivoFiltro) throws PersistenciaExcepcion {
		List<ResultadoBusquedaArchivoAVC> resultado = new ArrayList<ResultadoBusquedaArchivoAVC>();
		
		QueryParametrosUtil qp = getQueryTablaResultadoConciliacion(archivoFiltro);
		List<Map<String,Object>> listaResultadoQuery = buscarUsandoSQL(qp);
	
		 for (Map<String, Object> archivo : listaResultadoQuery) {
			
			ResultadoBusquedaArchivoAVC resultadoBusquedaArchivo = new ResultadoBusquedaArchivoAVC();
			
			try {
				resultadoBusquedaArchivo.setFechaProcesamiento(Utilidad.deserializeAndFormatDate(archivo.get("fecha_procesamiento").toString(),Utilidad.DATE_TIME_FULL_FORMAT_BASE_DATOS));
			} catch (ParseException e) {
				throw new PersistenciaExcepcion(e.getMessage(),e);
			}
			
			resultadoBusquedaArchivo.setIdArchivosAvc(archivo.get("ID_ARCHIVO_AVC").toString());
			resultadoBusquedaArchivo.setNombreArchivo(archivo.get("NOMBRE_ARCHIVO").toString());
			resultadoBusquedaArchivo.setNroAcuerdo(archivo.get("ACUERDO").toString());
			resultadoBusquedaArchivo.setUsuarioProcesamiento(archivo.get("USUARIO_PROCESAMIENTO").toString());
			
			resultadoBusquedaArchivo.setRegistrosConciliados(getValorLong(archivo.get("CONCILIADOS")));
			resultadoBusquedaArchivo.setRegistrosConciliacionSugerida(getValorLong(archivo.get("SUGERIDOS")));
			resultadoBusquedaArchivo.setRegistrosSinConciliar(getValorLong(archivo.get("PENDIENTES")));
			resultadoBusquedaArchivo.setRegistrosAnulados(getValorLong(archivo.get("ANULADOS")));
			
			// Esto se hizo porque pincha cuando la cantidad de byte a traer supera los 4000 caracteres. 
			// Fije el corte por 3500 por si hay varios carateres especiales.
			String log = archivo.get("LOG_PROCESAMIENTO").toString();
			if(!Validaciones.isObjectNull(archivo.get("LOG_PROCESAMIENTO_1"))){
					log += archivo.get("LOG_PROCESAMIENTO_1").toString();
			}
			if(!Validaciones.isObjectNull(archivo.get("LOG_PROCESAMIENTO_2"))){
				log += archivo.get("LOG_PROCESAMIENTO_2").toString();
			}
			if(!Validaciones.isObjectNull(archivo.get("LOG_PROCESAMIENTO_3"))){
				log += archivo.get("LOG_PROCESAMIENTO_3").toString();
			}
			if(!Validaciones.isObjectNull(archivo.get("LOG_PROCESAMIENTO_4"))){
				log += archivo.get("LOG_PROCESAMIENTO_4").toString();
			}
			resultadoBusquedaArchivo.setLogProcesamiento(log);
			
			
			resultadoBusquedaArchivo.setRegistrosProcesados(resultadoBusquedaArchivo.getRegistrosConciliados() +
					resultadoBusquedaArchivo.getRegistrosConciliacionSugerida()+
					resultadoBusquedaArchivo.getRegistrosSinConciliar()+
					resultadoBusquedaArchivo.getRegistrosAnulados());
			
			resultado.add(resultadoBusquedaArchivo);
		}
		return resultado;
	}

	private Long getValorLong(Object archivo) {
		if(!Validaciones.isObjectNull(archivo)){
			return Long.valueOf(archivo.toString());
		}else{
			return 0L;
		}
	}
	
	/**
	 * Retorna el query que se debe ejecutar para poder cargar la tabla de Resultados Conciliacion.
	 * @param filtro
	 * @return
	 */
	private QueryParametrosUtil getQueryTablaResultadoConciliacion(ArchivoAVCFiltro filtro){
		QueryParametrosUtil parametros = new QueryParametrosUtil();
		
		String query = "select distinct " +
				 "arch.id_archivo_avc, " +
				 "arch.nombre_archivo, " +
				 "arch.fecha_procesamiento, " +
				 "to_char(substr(arch.log_procesamiento,0,3500)) log_procesamiento, " +
				 "to_char(substr(arch.log_procesamiento,3501,3500)) log_procesamiento_1, " +
				 "to_char(substr(arch.log_procesamiento,7001,3500)) log_procesamiento_2, " +
				 "to_char(substr(arch.log_procesamiento,10501,3500)) log_procesamiento_3, " +
				 "to_char(substr(arch.log_procesamiento,14001,3500)) log_procesamiento_4, " +
				 "arch.usuario_procesamiento " +
				 ",arch.id_acuerdo Acuerdo " +
				 ",sugeridos.suma Sugeridos " +
				 ",conciliacion.suma Conciliados " +
				 ",pendientes.suma Pendientes " +
				 ",anulados.suma  Anulados " +
				" from " +
				 " shv_avc_archivo_avc arch " 
				 +",(" +
				   "select  " +  //CONCILIADOS
				     "reg.id_archivo_avc, " +
				     "count(wfEst.estado) suma " +
				   "from " +
				     "shv_avc_registro_avc reg, " +
				     "shv_wf_workflow wf, " +
				     "shv_wf_workflow_estado wfEst " +
				   "where " +
				     "reg.id_workflow = wf.id_workflow " +
				     "and wf.id_workflow =  wfEst.id_workflow and wfEst.estado in ('AVC_CONCILIADO', 'AVC_CONCILIADO_SIN_BOLETA', 'AVC_DIFERENCIA') " +
				     //Las Conciliaciones Totales deben ser solo con una semana de antiguedad
//				     "and arch.fechaProcesamiento>=to_timestamp('" + getDateConUnaSemanaAntiguedad() 
//												+ " 00:00:00','" + Utilidad.ORACLE_DATE_TIME_FULL_FORMAT + "')"  +
				   "group by  reg.id_archivo_avc " +
				    ") conciliacion " +
				    ",( " +
				    "select  " + //SUGERIDAS
				      "reg.id_archivo_avc, " +
				      "count(wfEst.estado) suma " +
				    " from " +
				      "shv_avc_registro_avc reg, " +
				      "shv_wf_workflow wf, " +
				      "shv_wf_workflow_estado wfEst " +
				    " where " +
				      "reg.id_workflow = wf.id_workflow " +
				      "and wf.id_workflow =  wfEst.id_workflow and wfEst.estado = 'AVC_CONCILIACION_SUGERIDA' " +
				    "group by  reg.id_archivo_avc " +
				  ") sugeridos  "
				  +",(" +
				    "select  " + //PENDIENTES
				      "reg.id_archivo_avc, " +
				      "count(wfEst.estado) suma " +
				    "from " +
				      "shv_avc_registro_avc reg, " +
				      "shv_wf_workflow wf, " +
				      "shv_wf_workflow_estado wfEst " +
				    "where " +
				      "reg.id_workflow = wf.id_workflow " +
				      "and wf.id_workflow =  wfEst.id_workflow and wfEst.estado in ('AVC_PENDIENTE', 'AVC_PENDIENTE_CONFIRMAR_ALTA_VALOR', 'AVC_ALTA_VALOR_RECHAZADA' ) " +
				    "group by  reg.id_archivo_avc " +
				  ") pendientes, " +
				  "( " +
				    "select  " + // ANULADOS
				      "reg.id_archivo_avc, " +
				      "count(wfEst.estado) suma " +
				    "from " +
				      "shv_avc_registro_avc reg, " +
				      "shv_wf_workflow wf, " +
				      "shv_wf_workflow_estado wfEst " +
				    "where reg.id_workflow = wf.id_workflow " +
				      "and wf.id_workflow =  wfEst.id_workflow and wfEst.estado in ('AVC_ANULADO','AVC_PENDIENTE_ANULACION') " + 
				    "group by  reg.id_archivo_avc " +
				  ") anulados " +
				  "where " +
				    "arch.id_archivo_avc = sugeridos.id_archivo_avc (+) " +
				    "and arch.id_archivo_avc = conciliacion.id_archivo_avc (+) " +
				    "and arch.id_archivo_avc = pendientes.id_archivo_avc (+) " +
				    "and arch.id_archivo_avc = anulados.id_archivo_avc (+) ";
		
		/*   FECHA ALTA   */
		if (!Validaciones.isNullOrEmpty(filtro.getFechaDesde())) {
			query += "and arch.fecha_procesamiento>=to_timestamp( ? , ? ) ";
			
			parametros.addCampoAlQuery(filtro.getFechaDesde() + " 00:00:00", Types.VARCHAR);
			parametros.addCampoAlQuery(Utilidad.ORACLE_DATE_TIME_FULL_FORMAT, Types.VARCHAR);
		}
		if (!Validaciones.isNullOrEmpty(filtro.getFechaHasta())) {
			query += "and arch.fecha_procesamiento<=to_timestamp( ? , ? ) ";
			
			parametros.addCampoAlQuery(filtro.getFechaHasta() + " 23:59:59", Types.VARCHAR);
			parametros.addCampoAlQuery(Utilidad.ORACLE_DATE_TIME_FULL_FORMAT, Types.VARCHAR);
		}
		
		if (!Validaciones.isObjectNull(filtro.getIdArchivoAvc())) {
			query += "and arch.id_archivo_avc = ? ";
			parametros.addCampoAlQuery(filtro.getIdArchivoAvc(), Types.NUMERIC);
		}
		parametros.setSql(query);
		
		return parametros;
	}

	@SuppressWarnings("unchecked")
	public ShvAvcArchivoAvc buscarArchivoAvcPorNombreArchivo(String name) throws PersistenciaExcepcion {
		try {
			String query = "from ShvAvcArchivoAvc as a where a.nombreArchivo=?";
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addParametro(name);
			
			List<ShvAvcArchivoAvc> lista = (List<ShvAvcArchivoAvc>) buscarUsandoQueryConParametros(qp);
			if(Validaciones.isCollectionNotEmpty(lista)){
				return lista.get(0);
			}else{
				return null;
			}
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
}
