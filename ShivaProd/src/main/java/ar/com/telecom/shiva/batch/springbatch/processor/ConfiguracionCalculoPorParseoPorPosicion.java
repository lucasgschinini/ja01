/**
 * 
 */
package ar.com.telecom.shiva.batch.springbatch.processor;

import java.lang.reflect.InvocationTargetException;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.utils.logs.Traza;

/**
 * @author u564030 Pablo M. Ibarrola
 *
 * Aqui un ejemplo de como implementar dentro de la lista de campos calculados
 * 
 * <bean class="ar.com.telecom.shiva.batch.springbatch.processor.ConfiguracionCalculoPorParseoPorPosicion">
 *	<property name="ordenProcesamiento" value="1"/>
 *	<property name="idCampoOrigen" value="comprobante"/>
 *	<property name="posicionParseoDesde" value="0"/>
 *	<property name="posicionParseoHasta" value="3"/>
 *	<property name="idCampoDestino" value="codigoOrganismo"/>
 * </bean>
 *
 */
public class ConfiguracionCalculoPorParseoPorPosicion extends ConfiguracionCalculo implements IConfiguracionCalculo {

	private String idCampoOrigen;
	private int posicionParseoDesde;
	private int posicionParseoHasta;
	private String idCampoDestino;
	
	@Override
	public void calcular(Object objOrigen) throws NegocioExcepcion {
		
		try {

			// Obtengo el valor del campo indicado en "idCampoOrigen" para poder trabajar con él
			Object valorCampoOrigen = getValorAtributo(objOrigen, idCampoOrigen);
			
			// Ahora realizo el parseo por posición del valor del campo indicado en "idCampoOrigen" para obtener el valor del campo calculado
			Object valorCampoCalculadoParaDestino = ((String) valorCampoOrigen).substring(posicionParseoDesde, posicionParseoHasta);

			// Seteo el valor calculado en el campo destinado
			setValorAtributo(objOrigen, idCampoDestino, valorCampoCalculadoParaDestino);
		    
		    Traza.auditoria(this.getClass(), "Resultado del calculo realizado");
		    Traza.auditoria(this.getClass(), "Entrada: idCampoOrigen:  '" + idCampoOrigen + "', posicionParseoDesde: '" + posicionParseoDesde + "', posicionParseoHasta: '" + posicionParseoHasta + "'");
		    Traza.auditoria(this.getClass(), "Salida:  idCampoDestino: '" + idCampoDestino + "', valor asignado: '" + valorCampoCalculadoParaDestino + "'");
		    Traza.auditoria(this.getClass(), "");
			
		} catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | ClassNotFoundException | InstantiationException e) {
			e.printStackTrace();

			throw new NegocioExcepcion("Hubo un error desconocido al aplicar la logica de 'CalculoPorParseoPorPosicion': " + e.getMessage(), e);
		}
	}
	
	/**
	 * @return the idCampoOrigen
	 */
	public String getIdCampoOrigen() {
		return idCampoOrigen;
	}
	/**
	 * @param idCampoOrigen the idCampoOrigen to set
	 */
	public void setIdCampoOrigen(String idCampoOrigen) {
		this.idCampoOrigen = idCampoOrigen;
	}
	/**
	 * @return the posicionParseoDesde
	 */
	public int getPosicionParseoDesde() {
		return posicionParseoDesde;
	}
	/**
	 * @param posicionParseoDesde the posicionParseoDesde to set
	 */
	public void setPosicionParseoDesde(int posicionParseoDesde) {
		this.posicionParseoDesde = posicionParseoDesde;
	}
	/**
	 * @return the posicionParseoHasta
	 */
	public int getPosicionParseoHasta() {
		return posicionParseoHasta;
	}
	/**
	 * @param posicionParseoHasta the posicionParseoHasta to set
	 */
	public void setPosicionParseoHasta(int posicionParseoHasta) {
		this.posicionParseoHasta = posicionParseoHasta;
	}
	/**
	 * @return the idCampoDestino
	 */
	public String getIdCampoDestino() {
		return idCampoDestino;
	}
	/**
	 * @param idCampoDestino the idCampoDestino to set
	 */
	public void setIdCampoDestino(String idCampoDestino) {
		this.idCampoDestino = idCampoDestino;
	}
}
