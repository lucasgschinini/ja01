/**
 * 
 */
package ar.com.telecom.shiva.batch.springbatch.processor;


/**
 * @author u564030 Pablo M. Ibarrola
 *
 */
public class ConfiguracionTipoDato {

	private String tipoDato;
	private String formato;
	private String longitud;

	/**
	 * @return the tipoDato
	 */
	public String getTipoDato() {
		return tipoDato;
	}
	/**
	 * @param tipoDato the tipoDato to set
	 */
	public void setTipoDato(String tipoDato) {
		this.tipoDato = tipoDato;
	}
	/**
	 * @return the formato
	 */
	public String getFormato() {
		return formato;
	}
	/**
	 * @param formato the formato to set
	 */
	public void setFormato(String formato) {
		this.formato = formato;
	}
	/**
	 * @return the longitud
	 */
	public String getLongitud() {
		return longitud;
	}
	/**
	 * @param longitud the longitud to set
	 */
	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}
}
