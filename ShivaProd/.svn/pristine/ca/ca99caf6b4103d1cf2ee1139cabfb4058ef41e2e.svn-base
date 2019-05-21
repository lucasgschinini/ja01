package ar.com.telecom.shiva.base.ws.cliente.datos.entrada;

import ar.com.telecom.shiva.base.enumeradores.CriterioBusquedaClienteEnum;


public class EntradaSiebelConsultarClienteWS {
	
	protected String criterio;
	protected String legadoClienteID;
	protected String crmHoldingID;
	protected String crmAgenciaNegocio;
	protected String crmClienteCUIT;
				
	/**
	 * @param legadoClienteID
	 */
	public EntradaSiebelConsultarClienteWS (String legadoClienteID) {
		this.legadoClienteID = legadoClienteID;
	}

	/**
	 * 
	 * @param criterio
	 * @param valor
	 */
	public EntradaSiebelConsultarClienteWS (String criterio, String valor) {
		this.criterio = criterio;
		if (CriterioBusquedaClienteEnum.BUSQUEDA_POR_AGENCIA_NEGOCIO.getNombre().equals(criterio)
				|| CriterioBusquedaClienteEnum.BUSQUEDA_POR_CLIENTE_LEGADO.getNombre().equals(criterio)
				|| CriterioBusquedaClienteEnum.BUSQUEDA_POR_HOLDING.getNombre().equals(criterio)) 
		{
			this.legadoClienteID = valor;
		} 
		if (CriterioBusquedaClienteEnum.BUSQUEDA_POR_CUIT.getNombre().equals(criterio)) {
			this.crmClienteCUIT = valor.replace("-", "");
		}
	}
	
	/**
	 * @param legadoClienteID
	 * @param crmHoldingID
	 * @param crmAgenciaNegocio
	 * @param crmClienteCUIT
	 */
	public EntradaSiebelConsultarClienteWS (String criterio,
			String legadoClienteID,
			String crmHoldingID,
			String crmAgenciaNegocio,
			String crmClienteCUIT) {
		
		this.criterio = criterio;
		this.legadoClienteID = legadoClienteID;
		this.crmHoldingID = crmHoldingID;
		this.crmAgenciaNegocio = crmAgenciaNegocio;
		this.crmClienteCUIT = crmClienteCUIT;
	}
	
	/**
	 * Getters & Setters
	 */
	public String getLegadoClienteID() {
		return legadoClienteID;
	}
	public void setLegadoClienteID(String legadoClienteID) {
		this.legadoClienteID = legadoClienteID;
	}

	public String getCrmHoldingID() {
		return crmHoldingID;
	}
	public void setCrmHoldingID(String crmHoldingID) {
		this.crmHoldingID = crmHoldingID;
	}

	public String getCrmAgenciaNegocio() {
		return crmAgenciaNegocio;
	}
	public void setCrmAgenciaNegocio(String crmAgenciaNegocio) {
		this.crmAgenciaNegocio = crmAgenciaNegocio;
	}

	public String getCrmClienteCUIT() {
		return crmClienteCUIT;
	}
	public void setCrmClienteCUIT(String crmClienteCUIT) {
		this.crmClienteCUIT = crmClienteCUIT;
	}

	public String getCriterio() {
		return criterio;
	}

	public void setCriterio(String criterio) {
		this.criterio = criterio;
	}
	
}
