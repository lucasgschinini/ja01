package ar.com.telecom.shiva.base.ws.cliente.servicios.impl.dummy;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaTeamComercialClienteWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.agrupador.delta.Rol;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaTeamComercialClienteWS;
import ar.com.telecom.shiva.base.ws.cliente.servicios.IClienteDeltaServicio;

public class DummyDeltaServicio implements IClienteDeltaServicio {

	
	@Override
	public SalidaTeamComercialClienteWS consultarTeamComercialCliente(EntradaTeamComercialClienteWS eTeamComercialClienteWS) 
			throws NegocioExcepcion {
		try {
			SalidaTeamComercialClienteWS datos = new SalidaTeamComercialClienteWS();
			
			Rol rol = new Rol("EJECUENT", "EJECUTIVO DE CUENTA", "U222222", "Diego Linares");
			datos.addRol(rol);
			
			rol = new Rol("ANALISTA", "ANALISTA DE CUENTA DEL CLIENTE", "N016947", "ADRIANA ANDUJAR");
			datos.addRol(rol);
			
			rol = new Rol("ASISDAT", "ASISTENTE DE DATOS", "U000050", "WALTER PERSELLO");
			datos.addRol(rol);
			
			rol = new Rol("ASISCOB", "ASISTENTE DE COBRANZA", "U000051", "LORENZO LAMAS");
			datos.addRol(rol);

			rol = new Rol("ASSCUENT", "ASISTENTE DE CUENTA", "U000052", "PABLO MARMOL");
			datos.addRol(rol);

			rol = new Rol("ASISVOZ", "ASISTENTE DE VOZ", "U000050", "RODRIGO PEREZ");
			datos.addRol(rol);
			
			rol = new Rol("INGCUENT", "INGENIERO DE CUENTA", "U193160", "Maximiliano De la Pena");
			datos.addRol(rol);
			
			return datos;
			
		} catch (Exception e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
}
