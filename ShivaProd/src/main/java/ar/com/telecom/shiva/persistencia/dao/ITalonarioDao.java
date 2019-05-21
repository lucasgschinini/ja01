package ar.com.telecom.shiva.persistencia.dao;

import java.util.List;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.modelo.ShvTalReciboPreImpreso;
import ar.com.telecom.shiva.persistencia.modelo.ShvTalTalonario;
import ar.com.telecom.shiva.presentacion.bean.filtro.TalonarioFiltro;

public interface ITalonarioDao {
	
	ShvTalTalonario insertarTalonario(ShvTalTalonario talonario) throws PersistenciaExcepcion;

	List<ShvTalTalonario> listarTalonarios(TalonarioFiltro talonarioFiltro) throws PersistenciaExcepcion;
	
	ShvTalTalonario buscarTalonario(Integer id) throws PersistenciaExcepcion;

	Boolean verificacionRango(String desde, String hasta, String nroSerie) throws PersistenciaExcepcion;
	
	Boolean verificacionRango(String desde, String hasta, String idTalonario, String nroSerie) throws PersistenciaExcepcion;

	List<ShvTalReciboPreImpreso> listarRecibos(String talonarioId) throws PersistenciaExcepcion;

	ShvTalTalonario actualizarTalonario(ShvTalTalonario talonarioModelo) throws PersistenciaExcepcion;
 
}
