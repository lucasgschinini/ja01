package ar.com.telecom.shiva.test.soa;

import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaTeamComercialClienteWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.agrupador.delta.Rol;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaTeamComercialClienteWS;
import ar.com.telecom.shiva.base.ws.cliente.servicios.IClienteDeltaServicio;
import ar.com.telecom.shiva.test.SoporteContextoSpringTest;

public class WSDeltaTest extends SoporteContextoSpringTest {

	@Autowired 
	IClienteDeltaServicio clienteDeltaServicio;
	
	@Test
	public void testTeamComercialCliente() throws NegocioExcepcion {
		
		System.out.println("--------------------------------------");
		EntradaTeamComercialClienteWS eEntrada = new EntradaTeamComercialClienteWS("0000097033");
		SalidaTeamComercialClienteWS datos = clienteDeltaServicio.consultarTeamComercialCliente(eEntrada);
		
		System.out.println("---WS TeamComercialCliente - Resultado=0000097033 --- ");
		if (datos != null) {
			System.out.println("resultado: ");
			if (datos.getListaRolesTeamComercialCliente().isEmpty()) {
				System.out.println("Sin Miembros");
			} else {
				for (Map.Entry<String, Rol> entry : datos.getListaRolesTeamComercialCliente().entrySet()) {
				    System.out.println(entry.getValue().toString());
				}
			}
		} else {
			System.out.println("resultado: sin datos");
		}
		System.out.println("--------------------------------------");
		
		eEntrada = new EntradaTeamComercialClienteWS("1401004125");
		datos = clienteDeltaServicio.consultarTeamComercialCliente(eEntrada);
		
		System.out.println("---WS TeamComercialCliente - Resultado=1401004125 --- ");
		if (datos != null) {
			System.out.println("resultado: ");
			if (datos.getListaRolesTeamComercialCliente().isEmpty()) {
				System.out.println("Sin Miembros");
			} else {
				for (Map.Entry<String, Rol> entry : datos.getListaRolesTeamComercialCliente().entrySet()) {
				    System.out.println(entry.getValue().toString());
				}
			}
		} else {
			System.out.println("resultado: sin datos");
		}
		System.out.println("--------------------------------------");

		eEntrada = new EntradaTeamComercialClienteWS("");
		datos = clienteDeltaServicio.consultarTeamComercialCliente(eEntrada);
		
		System.out.println("---WS TeamComercialCliente - Resultado= --- ");
		if (datos != null) {
			System.out.println("resultado: ");
			if (datos.getListaRolesTeamComercialCliente().isEmpty()) {
				System.out.println("Sin Miembros");
			} else {
				for (Map.Entry<String, Rol> entry : datos.getListaRolesTeamComercialCliente().entrySet()) {
				    System.out.println(entry.getValue().toString());
				}
			}
		} else {
			System.out.println("resultado: sin datos");
		}
		System.out.println("--------------------------------------");
	}
}
