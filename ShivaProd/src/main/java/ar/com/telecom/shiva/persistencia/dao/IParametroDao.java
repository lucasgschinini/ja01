package ar.com.telecom.shiva.persistencia.dao;

import org.hibernate.stat.Statistics;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;

public interface IParametroDao {
	
	String getValorTexto(String clave) throws PersistenciaExcepcion;
	
	Long getValorNumerico(String clave) throws PersistenciaExcepcion;
	
	Statistics getEstadisticas() throws PersistenciaExcepcion;

	void setValorTexto(String clave, String valorTexto) throws PersistenciaExcepcion;
	
}
