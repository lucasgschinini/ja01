/**
 * 
 */
package ar.com.telecom.shiva.negocio.dto.cobros.simulacion;

import ar.com.telecom.shiva.base.enumeradores.TipoClaseComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;

/**
 * @author u564027
 *
 */
public class DocumentoLegal {

	private TipoComprobanteEnum tipoComprobante;
	private TipoClaseComprobanteEnum claseComprobante;
	private String sucursalComprobante;
	private String numeroComprobante;

	/**
	 * @return the tipoComprobante
	 */
	public TipoComprobanteEnum getTipoComprobante() {
		return tipoComprobante;
	}
	/**
	 * @param tipoComprobante the tipoComprobante to set
	 */
	public void setTipoComprobante(TipoComprobanteEnum tipoComprobante) {
		this.tipoComprobante = tipoComprobante;
	}
	/**
	 * @return the claseComprobante
	 */
	public TipoClaseComprobanteEnum getClaseComprobante() {
		return claseComprobante;
	}
	/**
	 * @param claseComprobante the claseComprobante to set
	 */
	public void setClaseComprobante(TipoClaseComprobanteEnum claseComprobante) {
		this.claseComprobante = claseComprobante;
	}
	/**
	 * @return the sucursalComprobante
	 */
	public String getSucursalComprobante() {
		return sucursalComprobante;
	}
	/**
	 * @param sucursalComprobante the sucursalComprobante to set
	 */
	public void setSucursalComprobante(String sucursalComprobante) {
		this.sucursalComprobante = sucursalComprobante;
	}
	/**
	 * @return the numeroComprobante
	 */
	public String getNumeroComprobante() {
		return numeroComprobante;
	}
	/**
	 * @param numeroComprobante the numeroComprobante to set
	 */
	public void setNumeroComprobante(String numeroComprobante) {
		this.numeroComprobante = numeroComprobante;
	}
}
