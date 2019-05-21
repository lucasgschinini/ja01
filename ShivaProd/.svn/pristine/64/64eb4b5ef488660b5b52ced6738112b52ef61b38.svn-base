package ar.com.telecom.shiva.base.ws.cliente.servicios.impl;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.ws.cliente.TeamComercialClienteWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaTeamComercialClienteWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaTeamComercialClienteWS;
import ar.com.telecom.shiva.base.ws.cliente.servicios.IClienteDeltaServicio;

public class ClienteDeltaServicio implements IClienteDeltaServicio {
	
	@Autowired
	TeamComercialClienteWS teamComercialClienteWS;
	
	@Override
	public SalidaTeamComercialClienteWS consultarTeamComercialCliente(EntradaTeamComercialClienteWS eTeamComercialClienteWS) 
			throws NegocioExcepcion {
		try {

			return teamComercialClienteWS.consultarTeamComercialCliente(eTeamComercialClienteWS);
			
		} catch (Exception e) {
			String mensajeAuxiliar = "Se ha producido un error en el servicio de Delta";
			throw new NegocioExcepcion(e.getMessage(), e, mensajeAuxiliar);
		}
	}

	public TeamComercialClienteWS getTeamComercialClienteWS() {
		return teamComercialClienteWS;
	}

	public void setTeamComercialClienteWS(
			TeamComercialClienteWS teamComercialClienteWS) {
		this.teamComercialClienteWS = teamComercialClienteWS;
	}
}
