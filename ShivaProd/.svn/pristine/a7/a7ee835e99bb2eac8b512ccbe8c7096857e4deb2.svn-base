package ar.com.telecom.shiva.persistencia.dao;

import java.util.List;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.persistencia.modelo.ShvMasOperacionMasiva;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvMasOperacionMasivaSimplificado;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvMasOperacionMasivaSimplificadoWorkFlow;

public interface IOperacionMasivaDao {

	ShvMasOperacionMasiva crear(ShvMasOperacionMasiva operacionMasiva) throws PersistenciaExcepcion;

	ShvMasOperacionMasiva modificar(ShvMasOperacionMasiva operacionMasiva) throws PersistenciaExcepcion;

	public ShvMasOperacionMasivaSimplificado modificar(ShvMasOperacionMasivaSimplificado operacionMasiva) throws PersistenciaExcepcion;

	public ShvMasOperacionMasiva buscarOperacionMasiva(Long id) throws PersistenciaExcepcion;

	public List<ShvMasOperacionMasiva> buscarOperacionesMasivasByEstado(Estado estado) throws PersistenciaExcepcion;

	public List<ShvMasOperacionMasivaSimplificadoWorkFlow> buscarOperacionesMasivasSinRegistroEnProcesoByEstado(List<Estado> estado) throws PersistenciaExcepcion;

	public List<ShvMasOperacionMasivaSimplificado> calcularCantidadesDeRegistros(List<Estado> estados) throws PersistenciaExcepcion;

	public List<ShvMasOperacionMasivaSimplificado> modificar(List<ShvMasOperacionMasivaSimplificado> list) throws PersistenciaExcepcion;
	
	
		
}
