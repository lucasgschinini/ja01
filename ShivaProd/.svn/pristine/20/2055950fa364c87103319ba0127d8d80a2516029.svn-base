package ar.com.telecom.shiva.persistencia.dao;

import java.util.Collection;
import java.util.List;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;
import ar.com.telecom.shiva.presentacion.bean.filtro.Filtro;

public interface IGenericoDao {
	
	Long count(Class<?> clase) throws PersistenciaExcepcion; 
	
	Collection<?> listar(Class<?> clase) throws PersistenciaExcepcion;
	
	Collection<?> listarPorValor(Class<?> clase, String campo, String valor) throws PersistenciaExcepcion;
	
	public Collection<?> listarCondicionalWfEstado(Class<?> clase, List<String> listaCondiciones) throws PersistenciaExcepcion;

	String insertar(Class<?> clase, Modelo obj) throws PersistenciaExcepcion;
	
	void insertarEnTabla(String tabla, Class<?> clase,Modelo modelo) throws PersistenciaExcepcion;
	
	Long proximoValorSecuencia(String secuencia) throws PersistenciaExcepcion;
	
	void actualizar(Class<?> clase, Modelo modelo, String condicion) throws PersistenciaExcepcion;

	List<?> listarMesAnterior(Class<?> clase, String fechaComparacion, Filtro subsidiarioFiltro) throws PersistenciaExcepcion;

	Collection<?> listarPorValoresUsuandoQuery(List<String> listaAtributosFiltros, String consultaHql) throws PersistenciaExcepcion;
	
//	public void actualizarUltimo(Class<?> clase, String id,String campoModificacion, String nroBoleta);
}
