package ar.com.telecom.shiva.persistencia.dao;

import java.util.List;
import java.util.Map;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.presentacion.bean.filtro.OperacionTagetikFiltro;

public interface IOperacionTagetikDao {
	
	List<Map<String,Object>> obtenerFacturasTagetik(OperacionTagetikFiltro filtro) throws PersistenciaExcepcion;

}
