package ar.com.telecom.shiva.test.modulos;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.SimulacionCobroExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.WorkflowExcepcion;
import ar.com.telecom.shiva.negocio.servicios.ILegajoChequeRechazadoServicio;
import ar.com.telecom.shiva.persistencia.dao.ILegajoChequeRechazadoDao;
import ar.com.telecom.shiva.persistencia.modelo.ShvLgjLegajoChequeRechazado;
import ar.com.telecom.shiva.test.SoporteContextoSpringTest;

public class LegajoChequeRechazadoTest extends SoporteContextoSpringTest {
	
	@Autowired ILegajoChequeRechazadoServicio legajoChequeRechazadoServicio;
	@Autowired ILegajoChequeRechazadoDao 	  legajoChequeRechazadoDao;

	/**
	 * @throws WorkflowExcepcion
	 */
	@Test
	public void contabilizarReversionCobranzasLegajoChequeRechazadoCircuitoCobranzaBancariaIce() {
		
		try {
			
			ShvLgjLegajoChequeRechazado legajoChequeRechazado = legajoChequeRechazadoDao.buscar(new Long(102));
			String idUsuario = "SHV564030";
			
			legajoChequeRechazadoServicio.contabilizarReversionCobranzasLegajoChequeRechazadoCircuitoCobranzaBancariaIce(
					legajoChequeRechazado, idUsuario);
			
			System.out.println("FINALIZADO");
		} catch (SimulacionCobroExcepcion e) {
			System.out.println("Error al contabilizarReversionCobranzasLegajoChequeRechazadoCircuitoCobranzaBancariaIce: " + e.getMensajeAuxiliar());

		} catch (NegocioExcepcion e) {
			e.printStackTrace();
		
		} catch (PersistenciaExcepcion e) {
			e.printStackTrace();
		}
		
	}
}
	
