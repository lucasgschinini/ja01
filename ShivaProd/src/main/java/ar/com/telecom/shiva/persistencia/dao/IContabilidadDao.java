package ar.com.telecom.shiva.persistencia.dao;

import java.util.List;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.modelo.ShvCntContabilidadDetalle;
import ar.com.telecom.shiva.presentacion.bean.filtro.Filtro;

public interface IContabilidadDao {

	ShvCntContabilidadDetalle insertarActualizarContabilidadDetalle(ShvCntContabilidadDetalle contabilidadModelo) throws PersistenciaExcepcion;
	
	List<ShvCntContabilidadDetalle> listarPendientes(Filtro contabilidadFiltro) throws PersistenciaExcepcion;
	
	List<ShvCntContabilidadDetalle> listarEnProceso() throws PersistenciaExcepcion;
	
	List<ShvCntContabilidadDetalle> listarPendientesAcreditacion(Filtro contabilidadFiltro) throws PersistenciaExcepcion;
	
	List<ShvCntContabilidadDetalle> listarPendientesSinAcreditacion(Filtro contabilidadFiltro) throws PersistenciaExcepcion;
	
	List<ShvCntContabilidadDetalle> buscarPorIdValor(Long idValor) throws PersistenciaExcepcion;
}
