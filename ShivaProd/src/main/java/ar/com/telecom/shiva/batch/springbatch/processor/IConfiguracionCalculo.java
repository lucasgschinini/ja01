/**
 * 
 */
package ar.com.telecom.shiva.batch.springbatch.processor;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;

/**
 * @author u564030 Pablo M. Ibarrola
 *
 */
public interface IConfiguracionCalculo {

	/**
	 * Cada implementaci�n de esta interfaz debe sobrecargar este m�todo, ya que es el encargado
	 * de realizar el algoritmo de calculo propio de cada implementaci�n.
	 * 
	 * @param objOrigen
	 * @throws NegocioExcepcion 
	 */
	void calcular(Object objOrigen) throws NegocioExcepcion;
}
