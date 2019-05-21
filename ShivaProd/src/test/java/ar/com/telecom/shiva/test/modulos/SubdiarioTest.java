package ar.com.telecom.shiva.test.modulos;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.WorkflowExcepcion;
import ar.com.telecom.shiva.negocio.servicios.IScardServicio;
import ar.com.telecom.shiva.negocio.servicios.ISubdiarioServicio;
import ar.com.telecom.shiva.persistencia.dao.ICobroDao;
import ar.com.telecom.shiva.test.SoporteContextoSpringTest;

public class SubdiarioTest extends SoporteContextoSpringTest {
	
	@Autowired ICobroDao cobroDao;
	@Autowired IScardServicio scardServicio;
	@Autowired ISubdiarioServicio subdiarioServicio;
	
	/**
	 * @throws WorkflowExcepcion
	 */
	@Test
	public void simularCobro() {
		
		try {
			
			// Genero el subdiario
			subdiarioServicio.generarSubdiario("12/16");
			
		} catch (NegocioExcepcion e) {
			System.out.println("Error al generar registros para Subdiario: " + e.getMensajeAuxiliar());
			e.printStackTrace();
		}
	}
}
