package ar.com.telecom.shiva.persistencia.dao;

import java.util.List;

import ar.com.telecom.shiva.base.enumeradores.TipoPerfilEnum;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.bean.ResultadoBusquedaAcciones;
import ar.com.telecom.shiva.persistencia.modelo.ShvArcArchivo;
import ar.com.telecom.shiva.persistencia.modelo.ShvSegPerfil;
import ar.com.telecom.shiva.persistencia.modelo.ShvSegPerfilAplicativo;

public interface IReportePerfilesAuditoriaDao {
	
/**
 * 
 * @param archivo
 * @return
 * @throws PersistenciaExcepcion
 */
	public ShvArcArchivo insertarArcArchivo(ShvArcArchivo archivo) throws PersistenciaExcepcion;
	
	/** 
	 * 
	 * @return
	 */
	public List<ShvSegPerfil> buscarSegPerfil();
	
	/** 
	 * 
	 * @return
	 */
	public List<ShvSegPerfilAplicativo> buscarSegPerfilAplicativo();
	
	/** 
	 * 
	 * @return
	 */
	public List<ResultadoBusquedaAcciones> listarAcciones();

	/** 
	 * 
	 * @param segmento 
	 * @param empresa 
	 * @param tipoPerfil 
	 * @return
	 */
	public List<ResultadoBusquedaAcciones> buscarAccionesPorPerfil(TipoPerfilEnum tipoPerfil, String empresa, String segmento);

	/**
	 * 
	 * @param valor
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public ShvSegPerfil buscarPerfil(String perfil) throws PersistenciaExcepcion;
	/**
	 * 
	 * @param valor
	 * @return
	 * @throws PersistenciaExcepcion 
	 */
	public String buscarValor(String valor) throws PersistenciaExcepcion;

}
