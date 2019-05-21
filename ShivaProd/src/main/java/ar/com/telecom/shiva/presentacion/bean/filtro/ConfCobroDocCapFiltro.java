package ar.com.telecom.shiva.presentacion.bean.filtro;

import java.util.ArrayList;
import java.util.List;

import ar.com.telecom.shiva.base.enumeradores.TipoDocumentoCapEnum;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.presentacion.bean.dto.ClienteDto;

public class ConfCobroDocCapFiltro extends Filtro {
	private static final long serialVersionUID = 1L;

	private List<ClienteDto> clientes = new ArrayList<ClienteDto>();
	private String idDocCap = "";
	private TipoDocumentoCapEnum tipoComprobanteSap = null;
	private String moneda = null;
	private String monedaImporte = null;
	private String numeroLegal = "";
	private String _numeroLegalOld = "";
	private String fechaTipoDeCambio = "";
	private String tipoDeCambio = "";
	private String idOperacion = "";
	private String idCobro = "";

	private List proveedoresSap = null;

	public ConfCobroDocCapFiltro() {
	}

	/**
	 * @return the clientes
	 */
	public List<ClienteDto> getClientes() {
		return clientes;
	}


	/**
	 * @param clientes the clientes to set
	 */
	public void setClientes(List<ClienteDto> clientes) {
		this.clientes = clientes;
	}


	/**
	 * @return the idDocCap
	 */
	public String getIdDocCap() {
		return idDocCap;
	}
	/**
	 * @param idDocCap the idDocCap to set
	 */
	public void setIdDocCap(String idDocCap) {
		this.idDocCap = idDocCap;
	}
	/**
	 * @return the tipoComprobanteSap
	 */
	public TipoDocumentoCapEnum getTipoComprobanteSap() {
		return tipoComprobanteSap;
	}

	/**
	 * @param tipoComprobanteSap the tipoComprobanteSap to set
	 */
	public void setTipoComprobanteSap(TipoDocumentoCapEnum tipoComprobanteSap) {
		this.tipoComprobanteSap = tipoComprobanteSap;
	}
	/**
	 * @return the moneda
	 */
	public String getMoneda() {
		return moneda;
	}
	/**
	 * @param moneda the moneda to set
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}
	/**
	 * @return the numeroLegal
	 */
	public String getNumeroLegal() {
		return numeroLegal;
	}
	/**
	 * @param numeroLegal the numeroLegal to set
	 */
	public void setNumeroLegal(String numeroLegal) {
		this.numeroLegal = numeroLegal;
	}


	/**
	 * @return the proveedores
	 */
	public List getProveedoresSap() {
		return proveedoresSap;
	}


	/**
	 * @param proveedores the proveedores to set
	 */
	public void setProveedoresSap(List proveedores) {
		this.proveedoresSap = proveedores;
	}

	/**
	 * @return the monedaImporte
	 */
	public String getMonedaImporte() {
		return monedaImporte;
	}

	/**
	 * @param monedaImporte the monedaImporte to set
	 */
	public void setMonedaImporte(String monedaImporte) {
		this.monedaImporte = monedaImporte;
	}

	/**
	 * @return the fechaTipoDeCambio
	 */
	public String getFechaTipoDeCambio() {
		return fechaTipoDeCambio;
	}

	/**
	 * @param fechaTipoDeCambio the fechaTipoDeCambio to set
	 */
	public void setFechaTipoDeCambio(String fechaTipoDeCambio) {
		this.fechaTipoDeCambio = fechaTipoDeCambio;
	}

	/**
	 * @return the tipoDeCambio
	 */
	public String getTipoDeCambio() {
		return tipoDeCambio;
	}

	/**
	 * @param tipoDeCambio the tipoDeCambio to set
	 */
	public void setTipoDeCambio(String tipoDeCambio) {
		this.tipoDeCambio = tipoDeCambio;
	}

	/**
	 * @return the idOperacion
	 */
	public String getIdOperacion() {
		return idOperacion;
	}

	/**
	 * @param idOperacion the idOperacion to set
	 */
	public void setIdOperacion(String idOperacion) {
		this.idOperacion = idOperacion;
	}

	/**
	 * @return the idCobro
	 */
	public String getIdCobro() {
		return idCobro;
	}

	/**
	 * @param idCobro the idCobro to set
	 */
	public void setIdCobro(String idCobro) {
		this.idCobro = idCobro;
	}

	public void convertirNumeroLegalSap() {
		if (!Validaciones.isNullEmptyOrDash(this.numeroLegal)) {
			this._numeroLegalOld = this.numeroLegal;
			this.numeroLegal = Utilidad.parcearNroDocumentoLegalShiva(this.numeroLegal);
		}
	}

	/**
	 * @return the _numeroLegalOld
	 */
	public String get_numeroLegalOld() {
		return _numeroLegalOld;
	}

	/**
	 * @param _numeroLegalOld the _numeroLegalOld to set
	 */
	public void set_numeroLegalOld(String _numeroLegalOld) {
		this._numeroLegalOld = _numeroLegalOld;
	}
	
}
