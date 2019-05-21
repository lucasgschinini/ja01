package ar.com.telecom.shiva.presentacion.bean.dto;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ClienteDto extends DTO {
	
	private static final long serialVersionUID = 1L;

	private String idCobroCliente;
	private String idClienteLegado;
	private String empresasAsociadas;
	private String razonSocial;
	private String origen;
	private String descripcionHolding;
	private String codigoHolding; 
    private String cuit;
    private String agenciaNegocio;
    private String descripcionAgenciaNegocio;
    private String segmentoAgencia;
    private String idClientePerfil;
    private String idProvincia;
    private String clienteOrigen;
    
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
	 * @return the idCliente
	 */
	public String getIdCobroCliente() {
		return idCobroCliente;
	}
	
	/**
	 * @param idCliente the idCliente to set
	 */
	public void setIdCobroCliente(String idCobroCliente) {
		this.idCobroCliente = idCobroCliente;
	}

	/**
	 * @return the razonSocial
	 */
	public String getRazonSocial() {
		return razonSocial;
	}

	/**
	 * @param razonSocial the razonSocial to set
	 */
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	/**
	 * @return the descripcionHolding
	 */
	public String getDescripcionHolding() {
		return descripcionHolding;
	}

	/**
	 * @param descripcionHolding the descripcionHolding to set
	 */
	public void setDescripcionHolding(String holding) {
		this.descripcionHolding = holding;
	}

	/**
	 * @return the cuit
	 */
	public String getCuit() {
		return cuit;
	}

	/**
	 * @param cuit the cuit to set
	 */
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	/**
	 * @return the agenciaNegocio
	 */
	public String getAgenciaNegocio() {
		return agenciaNegocio;
	}

	/**
	 * @param agenciaNegocio the agenciaNegocio to set
	 */
	public void setAgenciaNegocio(String agenciaNegocio) {
		this.agenciaNegocio = agenciaNegocio;
	}

	/**
	 * @return the segmentoAgencia
	 */
	public String getSegmentoAgencia() {
		return segmentoAgencia;
	}

	/**
	 * @param segmentoAgencia the segmentoAgencia to set
	 */
	public void setSegmentoAgencia(String segmentoAgencia) {
		this.segmentoAgencia = segmentoAgencia;
	}

	/**
	 * @return the codigoHolding
	 */
	public String getCodigoHolding() {
		return codigoHolding;
	}

	/**
	 * @param codigoHolding the codigoHolding to set
	 */
	public void setCodigoHolding(String codigoHolding) {
		this.codigoHolding = codigoHolding;
	}

	/**
	 * @return the descripcionAgenciaNegocio
	 */
	public String getDescripcionAgenciaNegocio() {
		return descripcionAgenciaNegocio;
	}

	/**
	 * @param descripcionAgenciaNegocio the descripcionAgenciaNegocio to set
	 */
	public void setDescripcionAgenciaNegocio(String descripcionAgenciaNegocio) {
		this.descripcionAgenciaNegocio = descripcionAgenciaNegocio;
	}

	public String getIdClientePerfil() {
		return idClientePerfil;
	}

	public void setIdClientePerfil(String idClientePerfil) {
		this.idClientePerfil = idClientePerfil;
	}

	public String getIdProvincia() {
		return idProvincia;
	}

	public void setIdProvincia(String idProvincia) {
		this.idProvincia = idProvincia;
	}
	public String getIdClienteLegadoString() {
		if(!Validaciones.isNullOrEmpty(this.getIdClienteLegado())){
			if (this.getIdClienteLegado().length() <= 10) {
				return Utilidad.rellenarCerosIzquierda(this.getIdClienteLegado(), 10);
			}
		}
		return this.getIdClienteLegado();
	}
	/**
	 * @return the clienteOrigen
	 */
	public String getClienteOrigen() {
		return clienteOrigen;
	}

	/**
	 * @param clienteOrigen the clienteOrigen to set
	 */
	public void setClienteOrigen(String clienteOrigen) {
		this.clienteOrigen = clienteOrigen;
	}

	/**
	 * @return the empresasAsociadas
	 */
	public String getEmpresasAsociadas() {
		return empresasAsociadas;
	}

	/**
	 * @param empresasAsociadas the empresasAsociadas to set
	 */
	public void setEmpresasAsociadas(String empresasAsociadas) {
		this.empresasAsociadas = empresasAsociadas;
	}

	/**
	 * @return the origen
	 */
	public String getOrigen() {
		return origen;
	}

	/**
	 * @param origen the origen to set
	 */
	public void setOrigen(String origen) {
		this.origen = origen;
	}

}
