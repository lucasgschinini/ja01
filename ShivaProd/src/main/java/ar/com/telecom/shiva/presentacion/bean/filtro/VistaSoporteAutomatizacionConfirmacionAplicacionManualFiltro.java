/**
 * 
 */
package ar.com.telecom.shiva.presentacion.bean.filtro;

/**
 * @author fernando.formento
 *
 */
public class VistaSoporteAutomatizacionConfirmacionAplicacionManualFiltro extends Filtro {

	private static final long serialVersionUID = -2579427009550076872L;

	private String sistema;
	
	public VistaSoporteAutomatizacionConfirmacionAplicacionManualFiltro(String sistema) {
		this.sistema = sistema;
	}

	public String getSistema() {
		return sistema;
	}

	public void setSistema(String sistema) {
		this.sistema = sistema;
	}

}
