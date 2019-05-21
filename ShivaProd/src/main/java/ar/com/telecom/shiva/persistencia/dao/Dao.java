package ar.com.telecom.shiva.persistencia.dao;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameterValue;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.persistencia.dao.util.QueryParametrosUtil;

public class Dao {	
	private EntityManagerFactory entityManagerFactory;
	
	@Autowired
	@PersistenceUnit
	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}
	
	@Autowired 
	JdbcTemplate jdbcTemplate;
	
//	@Autowired
//	@PersistenceContext
//	public void setEngityManager(EntityManager entityManager) {
//	    this.em = entityManager;
//	}
	
	public Dao() {
	}
		
	/**
	 * JPA = consulta usando SQL (Sin paginado)
	 * Implementation with plain JPA
	 * @param query
	 * @return
	 * @throws DataAccessException
	 * @throws ParseException
	 */
	public List<Map<String, Object>> buscarUsandoSQL(QueryParametrosUtil qp) throws PersistenciaExcepcion {
		try {
			Traza.transaccionBBDD(getClass(), "Inicio Consulta: " + qp.getSql());
			
			Date start = new Date();
			List<Map<String, Object>> list = jdbcTemplate.queryForList(
					qp.getSql(), qp.getArrayValores(), qp.getArrayTiposDatos());
			
			Date end = new Date();
			
			Traza.auditoria_tiempo_consulta(getClass(), qp.getSql(), start, end, list.size());
			return list;
		} catch (Throwable e) {
			Traza.error(getClass(),e.getMessage(),e);
	        throw new PersistenciaExcepcion(e);
		}
	}

	
	
	
	
	public void execute (String query) throws PersistenciaExcepcion {
		try {
			Traza.transaccionBBDD(getClass(), "Inicio Execute: " + query);
			
			Date start = new Date();
			jdbcTemplate.execute(query);
			
			Date end = new Date();
			
			Traza.auditoria_tiempo_consulta(getClass(), query, start, end, 1);
		} catch (Throwable e) {
			Traza.error(getClass(),e.getMessage(),e);
			throw new PersistenciaExcepcion(e);
		}
	}
	
	
	
	
	
	/**
	 * @deprecated - Usar el metodo buscarUsandoQueryConParametros
	 * Consulta hibernate sin paginado 
	 * @param query
	 * @return
	 * @throws DataAccessException
	 * @throws ParseException
	 */
	@SuppressWarnings("rawtypes")
	public List buscarUsandoQuery(String hsql) throws PersistenciaExcepcion {
		
		return buscarUsandoQuery(hsql, -1, -1);
	}
	
	/**
	 * @deprecated - Usar el metodo buscarUsandoQueryConParametros 
	 * Consulta hibernate con/sin paginado 
	 * @param query
	 * @param firstResult
	 * @param maxResults
	 * @return
	 * @throws DataAccessException
	 * @throws ParseException
	 */
	@SuppressWarnings("rawtypes")
	public List buscarUsandoQuery(String hsql, final int firstResult, final int maxResults) 
		throws PersistenciaExcepcion {
		
		EntityManager em = entityManagerFactory.createEntityManager();
		try {
			Traza.transaccionBBDD(getClass(), "Inicio Consulta: " + hsql);
			Query query = em.createQuery(hsql);
			if (firstResult > -1 && maxResults > -1) {
				query.setFirstResult(firstResult);
				query.setMaxResults(maxResults);
			}
				
			Date start = new Date();
			List list = query.getResultList();
			Date end = new Date();
			
			Traza.auditoria_tiempo_consulta(getClass(), hsql, start, end, list.size());
			return list;
		} catch (Throwable e) {
			Traza.error(getClass(),e.getMessage(),e);
            throw new PersistenciaExcepcion(e);
		} finally {
			if (em.isOpen()) {
				em.close();
			}
		}
	}
	
	/**
	 * Consulta hibernate sin paginado 
	 * @param query
	 * @return
	 * @throws DataAccessException
	 * @throws ParseException
	 */
	@SuppressWarnings("rawtypes")
	public List buscarUsandoQueryConParametros(QueryParametrosUtil qp) throws PersistenciaExcepcion {
		
		return buscarUsandoQueryConParametros(qp, -1, -1);
	}
	
	/**
	 * Consulta hibernate con/sin paginado
	 * @param query
	 * @param firstResult
	 * @param maxResults
	 * @return
	 * @throws DataAccessException
	 * @throws ParseException
	 */
	@SuppressWarnings("rawtypes")
	public List buscarUsandoQueryConParametros(QueryParametrosUtil qp, final int firstResult, final int maxResults) 
		throws PersistenciaExcepcion {
		
		EntityManager em = entityManagerFactory.createEntityManager();
		
		try {
			Traza.transaccionBBDD(getClass(), "Inicio Consulta: " + qp.formatHQLtoJPQL());
			Query query = em.createQuery(qp.formatHQLtoJPQL());
			
			if (!Validaciones.isObjectNull(qp.getArrayValores())) {
				int contador = 1;
				for (Object parametro: qp.getArrayValores()) {
					query.setParameter(contador, parametro);
					contador++;
				}
			}
			
			//Para paginados
			if (firstResult > -1 && maxResults > -1) {
				query.setFirstResult(firstResult);
				query.setMaxResults(maxResults);
			}
				
			Date start = new Date();
			List list = query.getResultList();
			Date end = new Date();
			
			Traza.auditoria_tiempo_consulta(getClass(), qp.formatHQLtoJPQL(), start, end, list.size());
			return list;
		} catch (Throwable e) {
			Traza.error(getClass(),e.getMessage(),e);
            throw new PersistenciaExcepcion(e);
		} finally {
			if (em.isOpen()) {
				em.close();
			}
		}
	}
	
	
	/**
	 * Con este query podes consultar para que devuelva campos
	 * @param hsql
	 * @param firstResult
	 * @param maxResults
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	@SuppressWarnings("rawtypes")
	public List buscarUsandoNativeQuery(String hsql, final int firstResult, final int maxResults) 
			throws PersistenciaExcepcion {
			
		EntityManager em = entityManagerFactory.createEntityManager();
		try {
			Traza.transaccionBBDD(getClass(), "Inicio Consulta: " + hsql);
			Query query = em.createNativeQuery(hsql);
			if (firstResult > -1 && maxResults > -1) {
				query.setFirstResult(firstResult);
				query.setMaxResults(maxResults);
			}
			
			Date start = new Date();
			List list = query.getResultList();
			Date end = new Date();
			
			Traza.auditoria_tiempo_consulta(getClass(), hsql, start, end, list.size());
			return list;
		} catch (Throwable e) {
			Traza.error(getClass(),e.getMessage(),e);
            throw new PersistenciaExcepcion(e);
		} finally {
			if (em.isOpen()) {
				em.close();
			}
		}
	}
	
	/**
	 * @deprecated - Usar el metodo buscarUsandoQueryConParametros
	 * Consulta hibernate sin paginado 
	 * JPA = consulta usando SQL (Sin paginado)
	 * Implementation with plain JPA
	 * @param query
	 * @return
	 * @throws DataAccessException
	 * @throws ParseException
	 */
	public List<Map<String, Object>> buscarUsandoSQLParametros(Map<String, SqlParameterValue[]> consultaConParametos) throws PersistenciaExcepcion {
		
		try {
			String sql = null;
			SqlParameterValue[] parametros = null;
			for (Map.Entry<String, SqlParameterValue[]> entry : consultaConParametos.entrySet())
			{
			    sql = entry.getKey();
			    parametros = entry.getValue();
			}
			Traza.transaccionBBDD(getClass(), "Inicio Consulta: " + sql);
			
			Date start = new Date();
			List<Map<String, Object>> list = jdbcTemplate.queryForList(sql,(Object[]) parametros);
			Date end = new Date();
			
			Traza.auditoria_tiempo_consulta(getClass(), sql, start, end, list.size());
			return list;
		} catch (Throwable e) {
			Traza.error(getClass(),e.getMessage(),e);
	        throw new PersistenciaExcepcion(e);
		}
	}
    
	/**
     * Genero Estadisticas del hibernate
     * @return
     */
    public Statistics getEstadisticas() {
    	EntityManager em = entityManagerFactory.createEntityManager();
    	SessionFactory sessionFactory = ((Session)(em.getDelegate())).getSessionFactory();
    	Statistics stats = sessionFactory.getStatistics();
    	stats.setStatisticsEnabled(true);
    	if (em.isOpen()) {
    		em.close();
    	}
    	
    	return stats;
    }

	/**
	 * Utilidad para determinar el operador "where" o "and" de acuerdo al contenido previo de la sentencia HSQL
	 * 
	 * @param query
	 * @return
	 */
	protected String obtenerOperadorBusqueda(String query) {
		String operador = " where ";
		
		if (!Validaciones.isNullOrEmpty(query)) {
			operador = " and ";  
		}
		return operador;
	}
	protected String obtenerOperadorBusqueda(StringBuilder query) {
		String operador = " where ";
		
		if (!Validaciones.isObjectNull(query) && query.toString().length() != 0) {
			operador = " and ";  
		}
		return operador;
	}
    /**
     * SETTERS & GETTERS
     */
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}
}
