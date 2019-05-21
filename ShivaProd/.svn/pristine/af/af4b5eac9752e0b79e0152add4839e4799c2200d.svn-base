package ar.com.telecom.shiva.persistencia.dao.impl;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.persistencia.dao.Dao;
import ar.com.telecom.shiva.persistencia.dao.IGenericoDao;
import ar.com.telecom.shiva.persistencia.dao.util.QueryParametrosUtil;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;
import ar.com.telecom.shiva.presentacion.bean.filtro.Filtro;

public class GenericoDaoImpl extends Dao implements IGenericoDao  {

	public static final  String VALORES 				= "valores";
	public static final  String CAMPOS_SQL 				= "camposSql";

	@SuppressWarnings("unchecked")
	public Long count(Class<?> clase) throws PersistenciaExcepcion {
		QueryParametrosUtil qp = new QueryParametrosUtil("SELECT COUNT(*) FROM "+clase.getSimpleName());
		List<Long> resultado = this.buscarUsandoQueryConParametros(qp);
		return (!resultado.isEmpty())?resultado.get(0):0;
	}
	
	/**
	 * Devuelve todos los registros de la tabla correspondiente a la clase enviada
	 */
	public Collection<?> listar(Class<?> clase) throws PersistenciaExcepcion {
		QueryParametrosUtil qp = new QueryParametrosUtil("FROM " + clase.getSimpleName());
		Collection<?> list = this.buscarUsandoQueryConParametros(qp);
		return list;
	}
	
	/**
	 * Genera la siguiente consulta: FROM clase as obj WHERE obj.campo = 'valor'
	 */
	public Collection<?> listarPorValor(Class<?> clase, String campo, String valor) throws PersistenciaExcepcion {
		QueryParametrosUtil qp = new QueryParametrosUtil();
		qp.setSql("FROM " + clase.getSimpleName()+ " as obj WHERE obj."+campo+" = '"+valor+"'");
		Collection<?> list = this.buscarUsandoQueryConParametros(qp);
		return list;
	}
	
	
	/**
	 * Genera la siguiente consulta: FROM clase as obj WHERE obj.campo = 'valor' AND...
	 */
	public Collection<?> listarPorValores(Class<?> clase, List<String> campos, List<String> valores) throws PersistenciaExcepcion {
		QueryParametrosUtil qp = new QueryParametrosUtil();
		StringBuilder query = new StringBuilder();
		query.append("FROM " + clase.getSimpleName()+ " as obj WHERE ");
		for (int i = 0; i < campos.size(); i++) {
			query.append("obj."+campos.get(i)+" = ?");
			if (i+1 < campos.size()){
				query.append(" AND" );
			}
			qp.addCampoAlQuery(valores.get(i), Types.VARCHAR);
		}
		qp.setSql(query.toString());
		Collection<?> list = this.buscarUsandoQueryConParametros(qp);
		return list;
	}
	/**
	 * 
	 * @param valores
	 * @param query
	 * @return Collection de la clase Object
	 * @throws PersistenciaExcepcion
	 * 
	 * Genera una consulta mediante una "query", setea los parametros que estan en la lista "valores"
	 */
	public Collection<Object> listarPorValoresUsuandoQuery(List<String> valores, String query) throws PersistenciaExcepcion {
		QueryParametrosUtil qp = new QueryParametrosUtil();
		StringBuilder querySB = new StringBuilder();
		querySB.append(query);
		for (String valor : valores) {
			qp.addParametro(valor);
		}
		qp.setSql(querySB.toString());
		Collection<Object> list = this.buscarUsandoQueryConParametros(qp);
		return list;
	}
	
	/**
	 * Genera la siguiente consulta: FROM clase as obj WHERE (obj.listaCondiciones[0] = 'listaCondiciones[1]' or ...)
	 */
	public Collection<?> listarCondicionalWfEstado(Class<?> clase, List<String> listaCondiciones) throws PersistenciaExcepcion {
		QueryParametrosUtil qp = new QueryParametrosUtil();
		String query = "SELECT obj FROM " + clase.getSimpleName()+ " as obj "
						+ "join obj.workflow as w  "
						+ "join w.shvWfWorkflowEstado as we  WHERE we.estado in (";
		for (String condicion : listaCondiciones) {
			query += "?, ";
			qp.addParametro(Estado.getEnumByName(condicion));
		}
		
		query = query.substring(0,query.length()-2)+")";
		qp.setSql(query);
		
		Collection<?> list = this.buscarUsandoQueryConParametros(qp);
		return list;
	}
	
	/**
	 * Actualiza en la base segun la clase recibida. Ignora el primer campo del Modelo recibido (modelo), SerialUIVersion.
	 * Si uno de los campos del modelo es un objeto busca y guarda el id del mismo utilizando el nombre de columna del mismo.
	 * Recibe valores de tipo Date y los guarda como TIMESTAMP o DATE.
	 */
	public void actualizar(Class<?> clase,Modelo modelo, String condicion) throws PersistenciaExcepcion {
		//TODO refactorizar para que el metodo arme la condicion por si mismo obteniendo la anotacion id del objeto
		String query = "UPDATE "+clase.getAnnotation(Table.class).name()+"  SET ";
		HashMap<String, List<String>> map =listarCamposYValores(clase,modelo, false);
		List<String> listaCampos = map.get(CAMPOS_SQL);
		List<String> listaValores = map.get(VALORES);
		for (int i = 0; i < listaCampos.size(); i++){
			query += listaCampos.get(i)+"= "+listaValores.get(i)+", ";
		}
		query = query.substring(0,query.length()-2)+" where "+condicion;
		this.getJdbcTemplate().execute(query);
		
	}
	
//	public void actualizarUltimo(Class<?> clase, String campoId,String campoModificacion, String nroBoleta){
//		String query = "UPDATE "+clase.getAnnotation(Table.class).name()+" SET "+campoModificacion+"='"+nroBoleta
//				+"' where "+campoId+"= (select max("+campoId+") from "+clase.getAnnotation(Table.class).name()+")";
//		this.getJdbcTemplate().execute(query);
//	}
	
	/**
	 * Inserta en la tabla según la clase recibida. Ignora el primer campo del Modelo recibido (modelo), SerialUIVersion. Para el segundo atributo busca el proximo valor de la secuencia,
	 * si no tiene secuencia inserta el valor que llega en el modelo. Si uno de los campos del modelo es un objeto busca y guarda el id del mismo utilizando el nombre de columna del mismo.
	 * Recibe valores de tipo Date y los guarda como TIMESTAMP o DATE.
	 */
	public String insertar(Class<?> clase,Modelo modelo) throws PersistenciaExcepcion {
		HashMap<String, List<String>> map =listarCamposYValores(clase,modelo,true);
		String campos = "";
		String valores = "";
		List<String> listaCampos = map.get(CAMPOS_SQL);
		List<String> listaValores = map.get(VALORES);
		for (int i = 0; i < listaCampos.size(); i++) {
			campos += listaCampos.get(i)+", ";
			valores += listaValores.get(i)+", ";
			
		}
		campos = campos.substring(0, campos.length()-2);
		valores = valores.substring(0, valores.length()-2);
		Table tabla = clase.getAnnotation(Table.class);
		this.getJdbcTemplate().execute("INSERT INTO " + tabla.name()+" ("+campos+") VALUES ("+valores+")");
		return listaValores.get(0);
	}
	
	/**
	 * devuelve un HashMap con los keys CAMPOS_SQL y VALORES y una lista de strings. La lista de "campos" contiene el name de la anotacion Column y "valores" el valor del atributo.
	 * @param clase
	 * @param modelo
	 * @param secuencial
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public HashMap<String,List<String>> listarCamposYValores(Class<?> clase,Modelo modelo,boolean secuencial) throws PersistenciaExcepcion{
		List<String> listaCampos = new ArrayList<String>();
		List<String> listaValores = new ArrayList<String>();
		Field[] fields = clase.getDeclaredFields();
		int contador = 1;
		try {
			if(secuencial){
				if (fields[1].getAnnotation(Id.class) != null){
					fields[1].setAccessible(true);
					listaCampos.add(fields[1].getAnnotation(Column.class).name());
//					listaValores.add((fields[1].getAnnotation(SequenceGenerator.class) != null)?fields[1].getAnnotation(SequenceGenerator.class).name()+".nextval":formatearCampo(fields[1].get(clase.cast(modelo))));
					listaValores.add((fields[1].getAnnotation(SequenceGenerator.class) != null)?String.valueOf(proximoValorSecuencia(fields[1].getAnnotation(SequenceGenerator.class).name())):formatearCampo(fields[1].get(clase.cast(modelo))));
					contador++;
				}
			}
			for (int i = contador; i < fields.length; i++) {
				fields[i].setAccessible(true);
				if (fields[i].getAnnotation(Column.class) != null){
					listaCampos.add(fields[i].getAnnotation(Column.class).name());
					listaValores.add((fields[i].get(clase.cast(modelo)) != null)?formatearCampo(fields[i].get(clase.cast(modelo))):null);
				} else {
					if (fields[i].getAnnotation(JoinColumn.class) != null){
						Field[] filedsObjeto = fields[i].get(modelo).getClass().getDeclaredFields();
						for (int j = 1; j < filedsObjeto.length; j++) {
							filedsObjeto[j].setAccessible(true);
							if (filedsObjeto[j].getAnnotation(Id.class) != null){
								listaCampos.add(filedsObjeto[j].getAnnotation(Column.class).name());
								listaValores.add((filedsObjeto[j].get(fields[i].get(clase.cast(modelo))) != null)?formatearCampo(filedsObjeto[j].get(fields[i].get(clase.cast(modelo)))):null);
								break;
							}
						}
					}
				}
			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new PersistenciaExcepcion(e.getMessage(),e);
		}
		
		if (listaCampos.size()!=listaValores.size()){
			throw new PersistenciaExcepcion();
		}
		HashMap<String,List<String>> map = new HashMap<String,List<String>>();
		map.put(CAMPOS_SQL, listaCampos);
		map.put(VALORES,listaValores);
		return map;
	}
	/**
	 * Idem Insertar pero utiliza la tabla recibida.
	 * @param tabla
	 * @param clase
	 * @param modelo
	 * @throws PersistenciaExcepcion
	 */
	public void insertarEnTabla(String tabla, Class<?> clase,Modelo modelo) throws PersistenciaExcepcion {
		List<String> listaCampos = new ArrayList<String>();
		List<String> listaValores = new ArrayList<String>();
		Field[] fields = clase.getDeclaredFields();
		try {
			for (int i = 1; i < fields.length; i++) {
				fields[i].setAccessible(true);
				if (fields[i].getAnnotation(Column.class) != null){
					listaCampos.add(fields[i].getAnnotation(Column.class).name());
					listaValores.add((fields[i].get(clase.cast(modelo)) != null)?formatearCampo(fields[i].get(clase.cast(modelo))):null);
				} else {
					if (fields[i].getAnnotation(JoinColumn.class) != null){
						Field[] filedsObjeto = fields[i].get(modelo).getClass().getDeclaredFields();
						for (int j = 1; j < filedsObjeto.length; j++) {
							filedsObjeto[j].setAccessible(true);
							if (filedsObjeto[j].getAnnotation(Id.class) != null){
								listaCampos.add(filedsObjeto[j].getAnnotation(Column.class).name());
								listaValores.add((filedsObjeto[j].get(fields[i].get(clase.cast(modelo))) != null)?formatearCampo(filedsObjeto[j].get(fields[i].get(clase.cast(modelo)))):null);
								break;
							}
						}
					}
				}
			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new PersistenciaExcepcion(e.getMessage(),e);
		}

		if (listaCampos.size()!=listaValores.size()){
			throw new PersistenciaExcepcion();
		}
		String campos = "";
		String valores = "";
		for (int i = 0; i < listaCampos.size(); i++) {
			campos += listaCampos.get(i)+", ";
			valores += listaValores.get(i)+", ";
		}
		campos = campos.substring(0, campos.length()-2);
		valores = valores.substring(0, valores.length()-2);
		this.getJdbcTemplate().execute("INSERT INTO " + tabla+" ("+campos+") VALUES ("+valores+")");
	}

	public String formatearCampo(Object obj){
		String resultado = "";
		if(obj.getClass().equals(Timestamp.class) || obj.getClass().equals(Date.class) ){
			resultado = "TO_DATE('"+Utilidad.formatDateAndTimeFull((Date)obj)+"', 'dd/mm/yyyy hh24:mi:ss')";
		} else {
			resultado = "'"+String.valueOf(obj)+"'";
		}
		return resultado;
	}
	
	/**
	 * Retorna el siguiente valor de la "secuencia" dada
	 * @param secuencia
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public Long proximoValorSecuencia(String secuencia) throws PersistenciaExcepcion {
		try {
			String query = "select "+secuencia+".nextval from dual";
			
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			List<Map<String, Object>> lista =  buscarUsandoSQL(qp);
			
			return Long.valueOf(lista.get(0).get("NEXTVAL").toString());
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	@Override
	public List<?> listarMesAnterior(Class<?> clase, String fechaComparacion, Filtro subdiarioFiltro) throws PersistenciaExcepcion {

		try {
			List<GregorianCalendar> listaFechas= Utilidad.fechasMesAnterior(subdiarioFiltro.getFechaHasta());
			
			String query = "FROM "+clase.getSimpleName()+" as obj"
					+ " where (obj."+fechaComparacion+">= TO_DATE( ? ,  ? ) and obj."+fechaComparacion+" < TO_DATE( ? ,  ? ) )";
			
			QueryParametrosUtil qp = new QueryParametrosUtil(query);			
			qp.addParametro(new String(Utilidad.formatDatePicker(listaFechas.get(0).getTime())));
			qp.addParametro(new String(Utilidad.DATE_FORMAT_PICKER));
			qp.addParametro(new String(Utilidad.formatDatePicker(listaFechas.get(1).getTime())));
			qp.addParametro(new String(Utilidad.DATE_FORMAT_PICKER));
			return buscarUsandoQueryConParametros(qp);
			
		} catch (PersistenciaExcepcion | ParseException e) {
			throw new PersistenciaExcepcion(e.getMessage(),e);
		}
	}
}
