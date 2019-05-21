package ar.com.telecom.shiva.persistencia.dao;

import java.util.Collection;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.bean.ResultadoBusquedaArchivoAVC;
import ar.com.telecom.shiva.persistencia.modelo.ShvAvcArchivoAvc;
import ar.com.telecom.shiva.presentacion.bean.filtro.ArchivoAVCFiltro;

public interface IArchivoAVCDao {

	ShvAvcArchivoAvc crear(ShvAvcArchivoAvc archivo) throws PersistenciaExcepcion;

	Collection<ResultadoBusquedaArchivoAVC> buscarArchivosAvc(ArchivoAVCFiltro archivoFiltro) throws PersistenciaExcepcion;
	
	Collection<ResultadoBusquedaArchivoAVC> buscarArchivosAvcParaResultadoConciliacion(ArchivoAVCFiltro archivoFiltro) throws PersistenciaExcepcion;

	ShvAvcArchivoAvc buscarArchivoPorIdArchivo(Integer id) throws PersistenciaExcepcion;

	ShvAvcArchivoAvc buscarArchivoAvcPorNombreArchivo(String name) throws PersistenciaExcepcion;
	
}
