/**
 * 
 */
package ar.com.telecom.shiva.batch.springbatch.processor;

import org.springframework.batch.item.ExecutionContext;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;


/**
 * @author u564030 Pablo M. Ibarrola
 *
 */
public interface IConfiguracionCampo {

	/**
	 * Cada implementación de esta interfaz debe sobrecargar este método, ya que es el encargado
	 * de realizar el algoritmo de validacion propio de cada implementación.
	 * 
	 * @param objOrigen
	 * @return 
	 * @throws NegocioExcepcion 
	 */
	void validar(Object objOrigen, ExecutionContext executionContext) throws NegocioExcepcion;
	
	/**
	 * 
	 * @return
	 */
	String getId();

	/**
	 * 
	 * @return
	 */
	String getNombre();
	
	/**
	 * 
	 * @return
	 */
	String getPosicionDeCorrespondencia();
	
	/**
	 * 
	 * @return
	 */
	ConfiguracionObligatoriedad getConfiguracionObligatoriedad();
	
	/**
	 * 
	 * @return
	 */
	ConfiguracionTipoDato getConfiguracionTipoDato();
	
	/**
	 * 
	 * @return
	 */
	ConfiguracionMapeoAtributoModeloRelacionado getConfiguracionMapeoModeloRelacionado();

}

