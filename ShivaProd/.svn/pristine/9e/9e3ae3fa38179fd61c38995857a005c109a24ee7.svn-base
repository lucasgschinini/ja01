package ar.com.telecom.shiva.persistencia.dao.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.enumeradores.TipoArchivoOperacionMasivaEnum;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.singleton.ControlVariablesSingleton;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.persistencia.dao.Dao;
import ar.com.telecom.shiva.persistencia.dao.IArchivoOperacionMasivaDao;
import ar.com.telecom.shiva.persistencia.dao.util.QueryParametrosUtil;
import ar.com.telecom.shiva.persistencia.modelo.ShvMasOperacionMasivaArchivo;
import ar.com.telecom.shiva.persistencia.repository.ArchivoOperacionMasivaRepositorio;

public class ArchivoOperacionMasivaDaoImpl extends Dao implements IArchivoOperacionMasivaDao{

	@Autowired ArchivoOperacionMasivaRepositorio archivoOperacionMasivaRepositorio;
	
	/**
	 * Inserta el archivo
	 * @param ArchivosAVC
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public ShvMasOperacionMasivaArchivo crear(ShvMasOperacionMasivaArchivo archivo) throws PersistenciaExcepcion {
		try{
			ShvMasOperacionMasivaArchivo archivoAVCBD = archivoOperacionMasivaRepositorio.save(archivo);
			archivoOperacionMasivaRepositorio.flush();
			return archivoAVCBD;
		}catch(Exception e){
			throw new PersistenciaExcepcion(e);
		}
	}
	
	/**
	 * Eliminar el archivo
	 * @param ArchivosOperacionMasiva
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public void eliminar(ShvMasOperacionMasivaArchivo archivo) throws PersistenciaExcepcion {
		try{
			archivoOperacionMasivaRepositorio.delete(archivo);
			archivoOperacionMasivaRepositorio.flush();
			
		}catch(Exception e){
			throw new PersistenciaExcepcion(e);
		}
	}

	
	@SuppressWarnings("unchecked")
	public ShvMasOperacionMasivaArchivo buscarArchivoPorIdArchivo(Integer id) throws PersistenciaExcepcion {
		try {
			String query = "from ShvMasOperacionMasivaArchivo where idArchivosAvc=?";
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addParametro(id);
			
			List<ShvMasOperacionMasivaArchivo> archivo = (List<ShvMasOperacionMasivaArchivo>)buscarUsandoQueryConParametros(qp); 
			return archivo.get(0);
			
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	@SuppressWarnings("unchecked")
	public ShvMasOperacionMasivaArchivo buscarArchivoAvcPorNombreArchivo(String name) throws PersistenciaExcepcion {
		try {
			String query = "from ShvMasOperacionMasivaArchivo as a where a.nombreArchivo=?";
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addParametro(name);
			
			List<ShvMasOperacionMasivaArchivo> lista = (List<ShvMasOperacionMasivaArchivo>) buscarUsandoQueryConParametros(qp);
			if(Validaciones.isCollectionNotEmpty(lista)){
				return lista.get(0);
			}else{
				return null;
			}
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public ShvMasOperacionMasivaArchivo buscarListaComprobantesPorIdArchivo(String idOperacion) throws PersistenciaExcepcion {
		try {
			String query = "select a from ShvMasOperacionMasivaArchivo as a join a.operacionMasiva as b where b.idOperacionMasiva=?";
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addParametro(Long.parseLong(idOperacion));
			
			List<ShvMasOperacionMasivaArchivo> lista = (List<ShvMasOperacionMasivaArchivo>) buscarUsandoQueryConParametros(qp);
			if(Validaciones.isCollectionNotEmpty(lista)){
				return lista.get(0);
			}else{
				return null;
			}
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	@SuppressWarnings("unchecked")
	public ShvMasOperacionMasivaArchivo validarDuplicidadNombre(String nombreArchivo) throws PersistenciaExcepcion {
		try{
			String query = "select a from ShvMasOperacionMasivaArchivo as a "
							+ " join a.operacionMasiva.workFlow as w "
							+ " join w.shvWfWorkflowEstado as we "
							+ " where a.nombreArchivo=? " 
							+ " and we.estado <> ?"; 
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addParametro(nombreArchivo);
			qp.addParametro(Estado.MAS_ANULADA);
			List<ShvMasOperacionMasivaArchivo> lista = (List<ShvMasOperacionMasivaArchivo>) buscarUsandoQueryConParametros(qp);
			if(Validaciones.isCollectionNotEmpty(lista)){
				return lista.get(0);
			}else{
				return null;
			}
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public String ultimaSecuenciaDuplicidadNombre(String nombreArchivo) throws PersistenciaExcepcion {
		try{
			String query = "Select max(substr(a.nombreArchivo, 16, 3)) from ShvMasOperacionMasivaArchivo as a "
					+ " where upper(a.nombreArchivo) like ? ";
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			
			String condicionNombre = nombreArchivo.toUpperCase().substring(0, 14)+"_%";
			qp.addParametro(condicionNombre);
			
			List<String> resultado = (List<String>) buscarUsandoQueryConParametros(qp);
			if(Validaciones.isCollectionNotEmpty(resultado)){
				return resultado.get(0);
			}else{
				return null;
			}
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public ShvMasOperacionMasivaArchivo validarDuplicidadContenido(TipoArchivoOperacionMasivaEnum tipoArchivo, int cantRegistros, BigDecimal importeTotal) throws PersistenciaExcepcion{
		try{
			//ControlVariablesSingleton.permitirTraceoSQL();
			String query = "select a from ShvMasOperacionMasivaArchivo as a "
							+ " join a.operacionMasiva as op "
							+ " join op.workFlow as w "
							+ "	join w.shvWfWorkflowEstado as we "
							+ " where a.importeTotal = ? and a.cantidadRegistros = ? and a.nombreArchivo like ? "
							+ " and we.estado <> ?";
			
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addParametro(importeTotal);
			qp.addParametro(Long.valueOf(String.valueOf(cantRegistros)));
			String condicionNombre = tipoArchivo.name()+"_%";
			qp.addParametro(condicionNombre);
			qp.addParametro(Estado.MAS_ANULADA);
			
			List<ShvMasOperacionMasivaArchivo> lista = (List<ShvMasOperacionMasivaArchivo>) buscarUsandoQueryConParametros(qp);

			ControlVariablesSingleton.quitarTraceoSQL();
			
			if(Validaciones.isCollectionNotEmpty(lista)){
				return lista.get(0);
			}else{
				return null;
			}

			
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public ShvMasOperacionMasivaArchivo buscarArchivoPorIdOperacionMasiva(Long id) throws PersistenciaExcepcion {
		try {
			String query = "from ShvMasOperacionMasivaArchivo where id_operacion_masiva=?";
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addParametro(id);
			
			List<ShvMasOperacionMasivaArchivo> archivo = (List<ShvMasOperacionMasivaArchivo>)buscarUsandoQueryConParametros(qp); 
			return archivo.get(0);
			
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
}
