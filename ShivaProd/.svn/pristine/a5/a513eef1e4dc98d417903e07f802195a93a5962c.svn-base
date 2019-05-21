package ar.com.telecom.shiva.persistencia.dao.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class QueryParametrosUtil {
	String sql;
	Collection<Object> valores = new ArrayList<Object>();
	Collection<Integer> tiposDatos = new ArrayList<Integer>();
	
	public QueryParametrosUtil () {
	}
	
	public QueryParametrosUtil (String hsql) {
		this.sql = hsql;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getSql() {
		return sql;
	}
	
	/**
	 * Change JDBC positional style to JPA-style positional parameters style
	 * @return
	 */
	public String formatHQLtoJPQL() {
		StringBuffer buffer = new StringBuffer(sql);
		int start=0;
		int order=1;
		while ((start=buffer.indexOf("?", start+1)) > 0) {
			buffer.insert(start+1, order);
			order++;
		}
		
		return buffer.toString();
	}
	
	
	/**
	 * Seteo el SQL
	 * @param query
	 */
	public void setSql(String query) {
		this.sql = query;
	}
	
	/**
	 * Este método se ultiliza para obtener los valores previamente agregados, que se van a utilizar
	 * para inyectar en un query determinado. 
	 * @return Un array de <code>Object</code> con los valores a inyectar en un query. 
	 */
	public Object[] getArrayValores() {
		if (valores.size() > 0)
			return valores.toArray();
		else
			return null;
	}
	
	//Opcion 1 sin tipo de datos
	/**
	 * Recibe el parametro a injectar en el query.
	 * @param valor
	 */
	public void addParametro(Object valorCampo) {
		this.valores.add(valorCampo);
	}
		
	//Opcion 2 con tipo de datos
	/**
	 * Recibe el campo a injectar en el query y el tipo de dato del campo.
	 * Éstos datos se almacenan cada uno en una colección separada.
	 * @param valorCampo
	 * @param tipoDato del tipo <code>java.sql.Types</code> correspondiente al tipo del dato de valorCampo.
	 */
	public void addCampoAlQuery(Object valorCampo, int tipoDato) {
		this.valores.add(valorCampo);
		this.tiposDatos.add(new Integer(tipoDato));
	}

	/**
	 * Este método se ultiliza para obtener los tipos de datos correspondientes a los valores, previamente agregados, que se van a utilizar
	 * para inyectar en un query determinado. 
	 * @return Un array de <code>int</code> con los tipos de datos de los valores a ser reemplazados en un query.
	 */
	public int[] getArrayTiposDatos() {
		
		int[] result = new int[tiposDatos.size()];
		int i = 0;
		
		Iterator<Integer> it = tiposDatos.iterator();
		while (it.hasNext()) {
			result[i] = it.next();
			++i;
		}
		
		if (result.length > 0)
			return result;
		else
			return null;
	}

	

	
}
