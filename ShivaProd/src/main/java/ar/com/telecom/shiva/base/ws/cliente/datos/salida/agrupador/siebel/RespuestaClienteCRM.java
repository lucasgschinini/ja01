package ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.siebel;

public class RespuestaClienteCRM {
	private String codigoClienteLegado;
	private String razonSocialClienteLegado;
	private String codigoClienteAgrupador;
	private String razonSocialClienteAgrupador;
	private String numeroHolding;
	private String nombreHolding;
	private String agenciaNegocio;
	private String descripcionAgenciaNegocio;
	private String segmentoAgencia;
	private String cuit;
	private String idProvincia;
	private RespuestaDomicilioCRM domicilio;

	public RespuestaClienteCRM() {}

	public String getCodigoClienteLegado() {
		return codigoClienteLegado;
	}
	public void setCodigoClienteLegado(String codigoClienteLegado) {
		this.codigoClienteLegado = codigoClienteLegado;
	}
	public String getRazonSocialClienteLegado() {
		return razonSocialClienteLegado;
	}
	public void setRazonSocialClienteLegado(String razonSocialClienteLegado) {
		this.razonSocialClienteLegado = razonSocialClienteLegado;
	}
	public String getCodigoClienteAgrupador() {
		return codigoClienteAgrupador;
	}
	public void setCodigoClienteAgrupador(String codigoClienteAgrupador) {
		this.codigoClienteAgrupador = codigoClienteAgrupador;
	}
	public String getRazonSocialClienteAgrupador() {
		return razonSocialClienteAgrupador;
	}
	public void setRazonSocialClienteAgrupador(String razonSocialClienteAgrupador) {
		this.razonSocialClienteAgrupador = razonSocialClienteAgrupador;
	}
	public String getNumeroHolding() {
		return numeroHolding;
	}
	public void setNumeroHolding(String numeroHolding) {
		this.numeroHolding = numeroHolding;
	}
	public String getNombreHolding() {
		return nombreHolding;
	}
	public void setNombreHolding(String nombreHolding) {
		this.nombreHolding = nombreHolding;
	}
	public String getAgenciaNegocio() {
		return agenciaNegocio;
	}
	public void setAgenciaNegocio(String agenciaNegocio) {
		this.agenciaNegocio = agenciaNegocio;
	}
	public String getSegmentoAgencia() {
		return segmentoAgencia;
	}
	public void setSegmentoAgencia(String segmentoAgencia) {
		this.segmentoAgencia = segmentoAgencia;
	}
	public String getCuit() {
		return cuit;
	}
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}
	public String getDescripcionAgenciaNegocio() {
		return descripcionAgenciaNegocio;
	}
	public void setDescripcionAgenciaNegocio(String descripcionAgenciaNegocio) {
		this.descripcionAgenciaNegocio = descripcionAgenciaNegocio;
	}
	public String getIdProvincia() {
		return idProvincia;
	}
	public void setIdProvincia(String idProvincia) {
		this.idProvincia = idProvincia;
	}
	/**
	 * @return the domicilio
	 */
	public RespuestaDomicilioCRM getDomicilio() {
		return domicilio;
	}
	/**
	 * @param domicilio the domicilio to set
	 */
	public void setDomicilio(RespuestaDomicilioCRM domicilio) {
		this.domicilio = domicilio;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ClienteCRM [codigoClienteLegado=" + codigoClienteLegado
				+ ", razonSocialClienteLegado=" + razonSocialClienteLegado
				+ ", codigoClienteAgrupador=" + codigoClienteAgrupador
				+ ", razonSocialClienteAgrupador="
				+ razonSocialClienteAgrupador + ", numeroHolding="
				+ numeroHolding + ", nombreHolding=" + nombreHolding
				+ ", agenciaNegocio=" + agenciaNegocio
				+ ", descripcionAgenciaNegocio=" + descripcionAgenciaNegocio
				+ ", segmentoAgencia=" + segmentoAgencia + ", cuit=" + cuit
				+ ", idProvincia=" + idProvincia + ", domicilio=" + domicilio
				+ "]";
	}
	
}
