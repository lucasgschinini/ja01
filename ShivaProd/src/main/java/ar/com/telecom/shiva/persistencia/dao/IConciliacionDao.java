package ar.com.telecom.shiva.persistencia.dao;

import java.util.List;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.modelo.ShvAvcRegistroAvc;
import ar.com.telecom.shiva.persistencia.modelo.ShvAvcRegistroAvcBoleta;
import ar.com.telecom.shiva.persistencia.modelo.ShvBolBoleta;

public interface IConciliacionDao {

	List<ShvAvcRegistroAvcBoleta> listarConciliacionesSugeridas() throws PersistenciaExcepcion;
	
	ShvAvcRegistroAvcBoleta insertarConciliacionEnRegistroAvcBoleta(ShvBolBoleta boleta, ShvAvcRegistroAvc registroAvc) throws PersistenciaExcepcion; 

	void eliminarConciliacionSugerida(String idRegistro, String idBoleta) throws PersistenciaExcepcion;
	
	ShvAvcRegistroAvcBoleta buscarConciliacionesSugeridas(String idRegistro, String idBoleta) throws PersistenciaExcepcion;
}
