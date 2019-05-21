package ar.com.telecom.shiva.test.modulos;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.SimulacionCobroExcepcion;
import ar.com.telecom.shiva.negocio.servicios.IImputacionAutomaticaValoresClientesPuroServicio;
import ar.com.telecom.shiva.test.SoporteContextoSpringTest;

public class ImputacionAutomaticaValoresClientesPurosTest extends SoporteContextoSpringTest {
	
	@Autowired IImputacionAutomaticaValoresClientesPuroServicio imputacionAutomaticaValoresClientesPuroServicio;
	
	@Test
	public void simularCobro() {
		
		try {
			
			imputacionAutomaticaValoresClientesPuroServicio.generarImputacionAutomaticaValor();

			System.out.println("FINALIZADO");
		
		} catch (SimulacionCobroExcepcion e) {
			System.out.println("Error: " + e.getMensajeAuxiliar());

		} catch (NegocioExcepcion e) {
			e.printStackTrace();
		}
		
	}
}
