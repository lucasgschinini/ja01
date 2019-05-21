/**
 * 
 */
package ar.com.telecom.shiva.base.ws.cliente.datos.entrada;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoDocumentoCapEnum;

/**
 * @author u586743
 *
 */
@SuppressWarnings("serial")
public class EntradaSapConsultaPartidasWS extends EntradaWS {
	
	protected List<String> listaIdProveedores = new ArrayList<String>();
	protected String idDocumentoCap ;
	protected MonedaEnum moneda ;
	protected TipoDocumentoCapEnum tipoDocumentoCap;
	protected Date fechaEmisionDesde;
	protected Date fechaEmisionHasta;
	protected Date fechaTipoCambio;
	protected String numeroDocumentoLegal;
	protected String bloqueoPago;

	/**
	 * @return the listaIdProveedores
	 */
	public List<String> getListaIdProveedores() {
		return listaIdProveedores;
	}

	/**
	 * @param listaIdProveedores the listaIdProveedores to set
	 */
	public void setListaIdProveedores(List<String> listaIdProveedores) {
		this.listaIdProveedores = listaIdProveedores;
	}

	/**
	 * @return the idDocumentoCap
	 */
	public String getIdDocumentoCap() {
		return idDocumentoCap;
	}

	/**
	 * @param idDocumentoCap the idDocumentoCap to set
	 */
	public void setIdDocumentoCap(String idDocCap) {
		this.idDocumentoCap = idDocCap;
	}

	/**
	 * @return the moneda
	 */
	public MonedaEnum getMoneda() {
		return moneda;
	}

	/**
	 * @param moneda the moneda to set
	 */
	public void setMoneda(MonedaEnum moneda) {
		this.moneda = moneda;
	}

	/**
	 * @return the tipoDocumentoCap
	 */
	public TipoDocumentoCapEnum getTipoDocumentoCap() {
		return tipoDocumentoCap;
	}

	/**
	 * @param tipoDocumentoCap the tipoDocumentoCap to set
	 */
	public void setTipoDocumentoCap(TipoDocumentoCapEnum tipoDocumentoCap) {
		this.tipoDocumentoCap = tipoDocumentoCap;
	}

	/**
	 * @return the fechaTipoCambio
	 */
	public Date getFechaTipoCambio() {
		return fechaTipoCambio;
	}

	/**
	 * @param fechaTipoCambio the fechaTipoCambio to set
	 */
	public void setFechaTipoCambio(Date fechaTipoCambio) {
		this.fechaTipoCambio = fechaTipoCambio;
	}

	/**
	 * @return the numeroDocumentoLegal
	 */
	public String getNumeroDocumentoLegal() {
		return numeroDocumentoLegal;
	}

	/**
	 * @param numeroDocumentoLegal the numeroDocumentoLegal to set
	 */
	public void setNumeroDocumentoLegal(String numeroDocumentoLegal) {
		this.numeroDocumentoLegal = numeroDocumentoLegal;
	}

	/**
	 * @return the fechaEmisionDesde
	 */
	public Date getFechaEmisionDesde() {
		return fechaEmisionDesde;
	}

	/**
	 * @param fechaEmisionDesde the fechaEmisionDesde to set
	 */
	public void setFechaEmisionDesde(Date fechaEmisionDesde) {
		this.fechaEmisionDesde = fechaEmisionDesde;
	}

	/**
	 * @return the fechaEmisionHasta
	 */
	public Date getFechaEmisionHasta() {
		return fechaEmisionHasta;
	}

	/**
	 * @param fechaEmisionHasta the fechaEmisionHasta to set
	 */
	public void setFechaEmisionHasta(Date fechaEmisionHasta) {
		this.fechaEmisionHasta = fechaEmisionHasta;
	}

	/**
	 * @return the bloqueoPago
	 */
	public String getBloqueoPago() {
		return bloqueoPago;
	}

	/**
	 * @param bloqueoPago the bloqueoPago to set
	 */
	public void setBloqueoPago(String bloqueoPago) {
		this.bloqueoPago = bloqueoPago;
	}
}