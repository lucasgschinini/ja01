package ar.com.telecom.shiva.test.modulos;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.excepciones.otros.WorkflowExcepcion;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.batch.ActualizarTeamComercialBatchRunner;
import ar.com.telecom.shiva.negocio.servicios.ITeamComercialServicio;
import ar.com.telecom.shiva.test.SoporteContextoSpringTest;

public class TeamComercialBatchTest extends SoporteContextoSpringTest {
	
	@Autowired ITeamComercialServicio teamComercialServicio;
	
	/**
	 * @throws WorkflowExcepcion
	 */
	@Test
	public void actualizarTeamComercialTest() {
		
		try {
			teamComercialServicio.actualizarTeamComercial("01/10/2017");
			System.out.println("Se ha finalizado exitosamente el proceso de actualización team comercial");
			Traza.auditoria(ActualizarTeamComercialBatchRunner.class, "---- Se ha finalizado exitosamente el proceso de actualizacion team comercial");
		} catch (Throwable e) {
			Traza.error(ActualizarTeamComercialBatchRunner.class, "Se ha producido un error en el proceso de actualizacion team comercial", e);
			Traza.advertencia(ActualizarTeamComercialBatchRunner.class, "---- Se ha producido un error en el proceso de actualizacion team comercial");
			e.printStackTrace();
		}

		System.out.println("FINALIZADO");
	}
}
