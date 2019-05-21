package ar.com.telecom.shiva.test.modulos;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.WorkflowExcepcion;
import ar.com.telecom.shiva.negocio.servicios.ICotizacionBatchServicio;
import ar.com.telecom.shiva.test.SoporteContextoSpringTest;

public class CargarCotizacionesSapTest extends SoporteContextoSpringTest {
	
	@Autowired ICotizacionBatchServicio cotizacionBatchServicio;
	
	/**
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws ShivaExcepcion 
	 * @throws WorkflowExcepcion
	 */
	@Test
	public void cargarCotizacionesSap() {
		
		try {
			
			cotizacionBatchServicio.procesarArchivoCotizacionesSap();
			
			System.out.println("FINALIZADO");
		} catch (NegocioExcepcion e) {
			e.printStackTrace();
//			throw new Exception(e);
		}
		
	}
}
