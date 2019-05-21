/**
 * 
 */
package ar.com.telecom.shiva.presentacion.bean.filtro;

/**
 * @author pablo.m.ibarrola
 *
 */
public class VistaSoporteValoresDisponiblesFiltro extends Filtro {

	private static final long serialVersionUID = -2579427009550076872L;

	private String idClienteLegado;
	private String idTipoMedioPago;
	
	public VistaSoporteValoresDisponiblesFiltro(String idClienteLegado, String idTipoMedioPago) {
		this.idClienteLegado = idClienteLegado;
		this.idTipoMedioPago = idTipoMedioPago;
	}

	/**
	 * @return the idClienteLegado
	 */
	public String getIdClienteLegado() {
		return idClienteLegado;
	}
	/**
	 * @param idClienteLegado the idClienteLegado to set
	 */
	public void setIdClienteLegado(String idClienteLegado) {
		this.idClienteLegado = idClienteLegado;
	}
	/**
	 * @return the idTipoMedioPago
	 */
	public String getIdTipoMedioPago() {
		return idTipoMedioPago;
	}
	/**
	 * @param idTipoMedioPago the idTipoMedioPago to set
	 */
	public void setIdTipoMedioPago(String idTipoMedioPago) {
		this.idTipoMedioPago = idTipoMedioPago;
	}
}
