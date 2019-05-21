package ar.com.telecom.shiva.persistencia.dao;

import java.util.List;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamGestor;

public interface IGestorDao {
	
	/**
	 * Devuelve la lista de gestores por empresa y gestor 
	 * a partir de la relacion de gestor y segmentos
	 */
	List<ShvParamGestor> listarGestoresActivos(String idEmpresa, String idSegmento) throws PersistenciaExcepcion;
	
}
