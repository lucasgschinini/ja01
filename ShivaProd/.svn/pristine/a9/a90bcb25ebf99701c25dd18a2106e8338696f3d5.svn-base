package ar.com.telecom.shiva.persistencia.dao;

import java.math.BigDecimal;

import ar.com.telecom.shiva.base.enumeradores.TipoArchivoOperacionMasivaEnum;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.modelo.ShvMasOperacionMasivaArchivo;

public interface IArchivoOperacionMasivaDao {

	ShvMasOperacionMasivaArchivo crear(ShvMasOperacionMasivaArchivo archivo) throws PersistenciaExcepcion;

	ShvMasOperacionMasivaArchivo buscarArchivoPorIdArchivo(Integer id) throws PersistenciaExcepcion;

	ShvMasOperacionMasivaArchivo buscarArchivoAvcPorNombreArchivo(String name) throws PersistenciaExcepcion;

	ShvMasOperacionMasivaArchivo validarDuplicidadNombre(String nombreArchivo) throws PersistenciaExcepcion;
	
	ShvMasOperacionMasivaArchivo validarDuplicidadContenido(TipoArchivoOperacionMasivaEnum tipoArchivo, int cantRegistros, BigDecimal importeTotal) throws PersistenciaExcepcion;
	
	String ultimaSecuenciaDuplicidadNombre(String nombreArchivo) throws PersistenciaExcepcion;
	
	ShvMasOperacionMasivaArchivo buscarListaComprobantesPorIdArchivo(String idArchivo) throws PersistenciaExcepcion;
	
	ShvMasOperacionMasivaArchivo buscarArchivoPorIdOperacionMasiva(Long id) throws PersistenciaExcepcion;
	
	public void eliminar(ShvMasOperacionMasivaArchivo archivo) throws PersistenciaExcepcion;
}
