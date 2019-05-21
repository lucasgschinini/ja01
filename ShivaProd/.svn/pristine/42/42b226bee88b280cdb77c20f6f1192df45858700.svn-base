package ar.com.telecom.shiva.test.modulos;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.negocio.servicios.IClienteServicio;
import ar.com.telecom.shiva.negocio.servicios.bean.ClienteBean;
import ar.com.telecom.shiva.test.SoporteContextoSpringTest;

public class ClienteTest extends SoporteContextoSpringTest {
	
	@Autowired private IClienteServicio clienteServicio;
	
	@Test
	public void consultaCliente() {
		
		try {
			ClienteBean clienteBean = clienteServicio.consultarCliente("2801175473");
		} catch (NegocioExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
