/**
 * 
 */
package ar.com.telecom.shiva.presentacion.bean.filtro;

/**
 * @author fernando.formento
 *
 */
public class VistaSoporteReporteAplicacionManualPendientesFiltro extends Filtro {

	private static final long serialVersionUID = -2579427009550076872L;

	private String sistema;
	private String fecha;
	
	public VistaSoporteReporteAplicacionManualPendientesFiltro(String sistema, String fecha) {
		this.sistema = sistema;
		this.fecha = fecha;
	}

	public String getSistema() {
		return sistema;
	}

	public void setSistema(String sistema) {
		this.sistema = sistema;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	
}
