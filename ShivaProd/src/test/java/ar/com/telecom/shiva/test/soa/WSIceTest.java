package ar.com.telecom.shiva.test.soa;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.base.ws.cliente.IceConsultaChequesWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaIceConsultaChequesWS;
import ar.com.telecom.shiva.base.ws.cliente.servicios.IClienteIceServicio;
import ar.com.telecom.shiva.test.SoporteContextoSpringTest;

public class WSIceTest extends SoporteContextoSpringTest{

	@Autowired
	IceConsultaChequesWS iceConsultaChequesWS;
	
	@Autowired
	IClienteIceServicio clienteIceServicio;
	
	//@Test
	public void testWSConsultaChequeIce() {
		
		EntradaIceConsultaChequesWS entrada = new EntradaIceConsultaChequesWS();
		
		try {
			iceConsultaChequesWS.consultarChequesIce(entrada);

		} catch (NegocioExcepcion e) {
			Traza.error(this.getClass(), e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	public void testConsultarChequesIce() {
		
		EntradaIceConsultaChequesWS entrada = new EntradaIceConsultaChequesWS();
		
		try {
			clienteIceServicio.consultarChequesIce(entrada);

		} catch (NegocioExcepcion e) {
			Traza.error(this.getClass(), e.getMessage());
			e.printStackTrace();
		}
	}
	
//	@Test
	public void testConsultarDetalleCobrosChequesIce() {
		
		EntradaIceConsultaChequesWS entrada = new EntradaIceConsultaChequesWS();
		
		try {
			clienteIceServicio.consultarDetalleCobranzaChequeIce(entrada);

		} catch (NegocioExcepcion e) {
			Traza.error(this.getClass(), e.getMessage());
			e.printStackTrace();
		}
	}

	
	
}
