package ar.com.telecom.shiva.batch.springbatch.processor;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.persistencia.dao.IGenericoDao;

/**
 * @author u564030 Pablo M. Ibarrola
 *
 */
public abstract class ConfiguracionCalculo {
	
	@Autowired IGenericoDao genericoDao;

	private int ordenProcesamiento;
	private String valorCalculadoDeRetorno;
	
	/**
	 * 
	 * @param claseOrigen
	 * @param nombreAtributo
	 * @return
	 * @throws NoSuchMethodException
	 */
	protected Method getGetterMethod(Object claseOrigen, String nombreAtributo) throws NoSuchMethodException {
		String firstGetLetterMethod = nombreAtributo.substring(0, 1).toUpperCase();
		String getMethodName = "get" + firstGetLetterMethod + nombreAtributo.substring(1);
		Method getterMethod = claseOrigen.getClass().getMethod(getMethodName, new Class[]{});
		
		return getterMethod;
	}
	
	/**
	 * 
	 * @param claseOrigen
	 * @param nombreAtributo
	 * @return
	 * @throws NoSuchMethodException
	 */
	protected Method getSetterMethod(Object claseOrigen, String nombreAtributo) throws NoSuchMethodException {

		Field atributoDestino = null;

		Class<? extends Object> tmpClass = claseOrigen.getClass();
		while (tmpClass.getName() != Object.class.getName()) {
 	    	for (Field atributo : tmpClass.getDeclaredFields()) {
 	    		if (atributo.getName().equals(nombreAtributo)) {
 	    			atributoDestino = atributo;
 	 	    		break;
 	    		}
 	    	}
 	        tmpClass = (Class<? extends Object>) tmpClass.getSuperclass();
 	    }
		
		String firstSetLetterMethod = nombreAtributo.substring(0, 1).toUpperCase();
		String setMethodName = "set" + firstSetLetterMethod + nombreAtributo.substring(1);
		Method setterMethod = claseOrigen.getClass().getMethod(setMethodName, new Class[]{atributoDestino.getType()});
		
		return setterMethod;
	}
	
	/**
	 * Permite dinamicamente obtener el valor de un atributo dado, de una instancia de clase especifica
	 * 
	 * @param claseOrigen
	 * @param nombreAtributo
	 * @return
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	protected Object getValorAtributo(Object claseOrigen, String nombreAtributo) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method getterMethod = getGetterMethod(claseOrigen, nombreAtributo);
		Object valorAtributo = getterMethod.invoke(claseOrigen, new Object[]{});
		
		return valorAtributo;
	}
	
	/**
	 * Permite dinamicamente setear el valor a un atributo dado, de una instancia de clase especifica
	 * 
	 * @param objetoDestino
	 * @param nombreAtributoCampoDestino
	 * @param valorAtributoCampoDestino
	 * @throws NoSuchMethodException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws ClassNotFoundException
	 */
	protected void setValorAtributo(Object objetoDestino, String nombreAtributoCampoDestino, Object valorAtributoCampoDestino) throws NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException  {

		// Obtengo el atributo de la clase destino
		Field atributoDestino = getCampoAtributo(objetoDestino, nombreAtributoCampoDestino);

		// Obtengo la clase del atributo origen y destino para poder luego hacer el seteo del valor calculado en el destino
	    Class<?> claseObjetoCampoDestino = Class.forName(atributoDestino.getType().getName());
		
		// Obtengo el metodo setter del destino
		Method setterMethod = getSetterMethod(objetoDestino, nombreAtributoCampoDestino);
		
		// Aqui estoy obteniendo un constructor del tipo de objeto destino que acepte un objeto de tipo origen.
		// Por ejemplo si el origen fuera "java.lang.String" y el destino fuera "java.lang.Long", el constructor que debiera
		// utilizar sería "public java.lang.Long(java.lang.String) throws java.lang.NumberFormatException"
		// Esto me permite transformar el tipo de dato del objeto a setear en el tipo de dato del objeto de destino
	    Constructor<?> constructorObjetoCampoDestino = claseObjetoCampoDestino.getConstructor(Class.forName(valorAtributoCampoDestino.getClass().getName()));
		
		// Creo una instancia del objeto destino utilizando el constructor, tomando como dato de entrada el valor a setear
	    Object instanciaValorCampoDestino = constructorObjetoCampoDestino.newInstance(valorAtributoCampoDestino);

	    // Invoco al 'setter', recibo como parámetros la clase dueña del del método 'setter' a invocar, y el valor a setear
		setterMethod.invoke(objetoDestino, instanciaValorCampoDestino);		
	}	
	
	/**
	 * 
	 * @param objeto
	 * @param nombreAtributo
	 * @return
	 */
	protected Field getCampoAtributo(Object objeto, String nombreAtributo) {
		Field atributo = null;
		
 	    Class<? extends Object> tmpClass = objeto.getClass();
 	    
 	    while (tmpClass.getName() != Object.class.getName() && atributo == null) {
 	    	for (Field attr : tmpClass.getDeclaredFields()) {
 	    		if (attr.getName().equals(nombreAtributo)) {
 	    			atributo = attr;
 	 	    		break;
 	    		}
 	    	}
 	        tmpClass = (Class<? extends Object>) tmpClass.getSuperclass();
 	    }
 	    return atributo;
	}

	/**
	 * 
	 * @param objOrigen
	 * @param consultaHql
	 * @param listaAtributos
	 * @return Una lista con el resultado de la consulta dinamica
	 * @throws NegocioExcepcion
	 */
	protected Collection<Object> obtenerResultadoConsultaDinamica(Object objOrigen, String consultaHql, List<String> listaAtributos) throws NegocioExcepcion {
		Collection<Object> resultadoEjecucion = new ArrayList<Object>();
		List<String> listaAtributosFiltros = new ArrayList<String>();
		String parametroFiltro = "";
		
		if (validarAtributosObtenidos(listaAtributos, objOrigen) && Validaciones.isCollectionNotEmpty(listaAtributos)) {
			Traza.auditoria(this.getClass(), "Los {atributos} son validos");
			for (String atributo : listaAtributos) {
				consultaHql = consultaHql.replace(Constantes.OPEN_LLAVE + atributo + Constantes.CLOSE_LLAVE, Constantes.SIGNO_INTERROGACION);
				try {
					parametroFiltro = (String) getValorAtributo(objOrigen, atributo);
					Traza.auditoria(this.getClass(), "Atributo: " + atributo + " Valor: '" + parametroFiltro + "'");
					
					// Verifico si es un valor numérico, para eliminar los ceros delante, si y solo si es numerico
					if (parametroFiltro.startsWith("0")) {
						try {
							parametroFiltro = String.valueOf(new Long(parametroFiltro));
							Traza.auditoria(this.getClass(), "Atributo: " + atributo + " Valor recalculado: '" + parametroFiltro + "' (aplico logica de eliminacion de ceros a la izquierda).");
						} catch (NumberFormatException ex) {
							// Si da error entonces no era numérico, lo sigo dejando sin cambios
						}
					}
					
				} catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					throw new NegocioExcepcion(e);
				}
				listaAtributosFiltros.add(parametroFiltro);
			}
			
			try {
				 resultadoEjecucion = (Collection<Object>) genericoDao.listarPorValoresUsuandoQuery(listaAtributosFiltros, consultaHql);
			} catch (PersistenciaExcepcion e) {
				throw new NegocioExcepcion(e);
			}
		} else {
			if (!Validaciones.isCollectionNotEmpty(listaAtributos)) {
				Traza.error(this.getClass(), "No se encontraron {atributos}");
			} else {
				Traza.error(this.getClass(), "Error al validar los {atributos}: ");
				for (String atributo : listaAtributos) {
					if(atributo.isEmpty()) {
						Traza.error(this.getClass(), "Atributo vacio");
					}
					Traza.error(this.getClass(), atributo + " ");
				}
			}
		}
		
		return resultadoEjecucion;
	}
	
	/**
	 * 
	 * @param listaAtributos
	 * @param objeto
	 * @return
	 */
	protected boolean validarAtributosObtenidos(List<String> listaAtributos, Object objeto) {
		boolean hayAtributosValidos = true;
		for (String atributo : listaAtributos) {
			if (Validaciones.isObjectNull(getCampoAtributo(objeto, atributo))) {
				hayAtributosValidos = false;
				break;
			}
		}
		return hayAtributosValidos;
	}
	
	/**
	 * @return the ordenProcesamiento
	 */
	public int getOrdenProcesamiento() {
		return ordenProcesamiento;
	}
	/**
	 * @param ordenProcesamiento the ordenProcesamiento to set
	 */
	public void setOrdenProcesamiento(int ordenProcesamiento) {
		this.ordenProcesamiento = ordenProcesamiento;
	}
	/**
	 * @return the valorCalculadoDeRetorno
	 */
	public String getValorCalculadoDeRetorno() {
		return valorCalculadoDeRetorno;
	}
	/**
	 * @param valorCalculadoDeRetorno the valorCalculadoDeRetorno to set
	 */
	public void setValorCalculadoDeRetorno(String valorCalculadoDeRetorno) {
		this.valorCalculadoDeRetorno = valorCalculadoDeRetorno;
	}
}
