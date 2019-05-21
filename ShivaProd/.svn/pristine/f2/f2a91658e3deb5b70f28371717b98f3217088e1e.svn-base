package ar.com.telecom.shiva.negocio.servicios.bean;

import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.utils.Utilidad;



public class ClienteBean implements Bean {
	private static final long serialVersionUID = -2658260564662840968L;

	private Long idClienteLegado;
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
	private String idClienteAgrupador = "";
	private String razonSocialClienteAgrupador = "";
	private String idProvincia;
	private String clienteOrigen;
	private SiNoEnum permiteUsoTA;
	private SiNoEnum permiteUsoTP;
	private SiNoEnum permiteUsoCV;
	private SiNoEnum permiteUsoFT;
	private SiNoEnum permiteUsoNX;

	private DomicilioBean domicilio;
	
	public ClienteBean() {}

	
	/**
	 * @return the idClienteLegado
	 */
	public Long getIdClienteLegado() {
		return idClienteLegado;
	}

	/**
	 * @param idClienteLegado the idClienteLegado to set
	 */
	public void setIdClienteLegado(Long idClienteLegado) {
		this.idClienteLegado = idClienteLegado;
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
	public void setDescripcionHolding(String descripcionHolding) {
		this.descripcionHolding = descripcionHolding;
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
	 * @return the idClientePerfil
	 */
	public String getIdClientePerfil() {
		return idClientePerfil;
	}

	/**
	 * @param idClientePerfil the idClientePerfil to set
	 */
	public void setIdClientePerfil(String idClientePerfil) {
		this.idClientePerfil = idClientePerfil;
	}

	/**
	 * @return the idProvincia
	 */
	public String getIdProvincia() {
		return idProvincia;
	}

	/**
	 * @param idProvincia the idProvincia to set
	 */
	public void setIdProvincia(String idProvincia) {
		this.idProvincia = idProvincia;
	}


	/**
	 * @return the idClienteAgrupador
	 */
	public String getIdClienteAgrupador() {
		return idClienteAgrupador;
	}


	/**
	 * @param idClienteAgrupador the idClienteAgrupador to set
	 */
	public void setIdClienteAgrupador(String idClienteAgrupador) {
		this.idClienteAgrupador = idClienteAgrupador;
	}


	/**
	 * @return the razonSocialClienteAgrupador
	 */
	public String getRazonSocialClienteAgrupador() {
		return razonSocialClienteAgrupador;
	}

	/**
	 * @param razonSocialClienteAgrupador the razonSocialClienteAgrupador to set
	 */
	public void setRazonSocialClienteAgrupador(String razonSocialClienteAgrupador) {
		this.razonSocialClienteAgrupador = razonSocialClienteAgrupador;
	}
	/**
	 * 
	 * @return
	 */
	public String getIdClienteLegadoString() {
		String cliente = this.getIdClienteLegado().toString();
		if (cliente.length() <= 10) {
			return Utilidad.rellenarCerosIzquierda(cliente, 10);
		}
		return cliente;
	}


	/**
	 * @return the domicilio
	 */
	public DomicilioBean getDomicilio() {
		return domicilio;
	}


	/**
	 * @param domicilio the domicilio to set
	 */
	public void setDomicilio(DomicilioBean domicilio) {
		this.domicilio = domicilio;
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


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idClienteLegado == null) ? 0 : idClienteLegado.hashCode());
		return result;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClienteBean other = (ClienteBean) obj;
		if (idClienteLegado == null) {
			if (other.idClienteLegado != null)
				return false;
		} else if (!idClienteLegado.equals(other.idClienteLegado))
			return false;
		return true;
	}


	public SiNoEnum getPermiteUsoTA() {
		return permiteUsoTA;
	}


	public void setPermiteUsoTA(SiNoEnum permiteUsoTA) {
		this.permiteUsoTA = permiteUsoTA;
	}


	public SiNoEnum getPermiteUsoTP() {
		return permiteUsoTP;
	}


	public void setPermiteUsoTP(SiNoEnum permiteUsoTP) {
		this.permiteUsoTP = permiteUsoTP;
	}


	public SiNoEnum getPermiteUsoCV() {
		return permiteUsoCV;
	}


	public void setPermiteUsoCV(SiNoEnum permiteUsoCV) {
		this.permiteUsoCV = permiteUsoCV;
	}


	public SiNoEnum getPermiteUsoFT() {
		return permiteUsoFT;
	}


	public void setPermiteUsoFT(SiNoEnum permiteUsoFT) {
		this.permiteUsoFT = permiteUsoFT;
	}


	public SiNoEnum getPermiteUsoNX() {
		return permiteUsoNX;
	}


	public void setPermiteUsoNX(SiNoEnum permiteUsoNX) {
		this.permiteUsoNX = permiteUsoNX;
	}
	
}