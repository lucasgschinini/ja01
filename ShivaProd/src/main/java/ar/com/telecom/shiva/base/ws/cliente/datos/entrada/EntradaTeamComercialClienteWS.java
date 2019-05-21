package ar.com.telecom.shiva.base.ws.cliente.datos.entrada;


public class EntradaTeamComercialClienteWS {
	
	protected String legadoClienteID;
	
	/**
	 * 
	 * @param legadoClienteID
	 */
	public EntradaTeamComercialClienteWS (String legadoClienteID) {
		this.legadoClienteID = legadoClienteID;
	}

	/**
	 * 
	 * @return
	 */
	public String getLegadoClienteID() {
		return legadoClienteID;
	}

	/**
	 * 
	 * @param legadoClienteID
	 */
	public void setLegadoClienteID(String legadoClienteID) {
		this.legadoClienteID = legadoClienteID;
	}
	
}
