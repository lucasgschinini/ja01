package ar.com.telecom.shiva.test.modulos;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.SimulacionCobroExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.WorkflowExcepcion;
import ar.com.telecom.shiva.negocio.servicios.ICobroBatchServicio;
import ar.com.telecom.shiva.negocio.servicios.ICobroBatchSimulacionServicio;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;
import ar.com.telecom.shiva.persistencia.dao.ICobroDao;
import ar.com.telecom.shiva.test.SoporteContextoSpringTest;

public class CobroBatchSimulacionTest extends SoporteContextoSpringTest {
	
	@Autowired ICobroBatchSimulacionServicio cobroBatchSimulacionServicio;
	@Autowired ICobroDao cobroDao;

	@Autowired ICobroBatchServicio cobroBatchServicio;
	@Autowired IParametroServicio parametroServicio; 
	
	/**
	 * @throws WorkflowExcepcion
	 */
	@Test
	public void simularCobro() {
		
		try {
//			cobroDao.buscarCobroSimulacionEnProceso();
			
//			cobroBatchSimulacionServicio.simularCobroOnline(new Long(9605));
//			cobroBatchSimulacionServicio.simularCobroOnline(new Long(10085));
			
			// Cobro de Pablo A para ver el tema de la nueva fecha valor de otros debitos
			// Operacion 8355440
//			cobroBatchSimulacionServicio.simularCobroOnline(new Long(5739));
			// Operacion 8355441 
			cobroBatchSimulacionServicio.simularCobroOnline(new Long(5740));
			
			// Prueba de simulacion batch
//			ShvCobCobro cobroPendienteSimulacionBatch = cobroBatchServicio.buscarCobroPendienteSimulacionBatch();
//
//			cobroBatchSimulacionServicio.simularCobroBatch(
//					cobroPendienteSimulacionBatch,
//					parametroServicio.getValorTexto(Constantes.USUARIO_BATCH));
			
			System.out.println("FINALIZADO");
		} catch (SimulacionCobroExcepcion e) {
			System.out.println("Error al simular el cobro: " + e.getMensajeAuxiliar());
		} catch (NegocioExcepcion e) {
			e.printStackTrace();
		}
		
	}
	
	//@Test
	public void buscarCobro() {
		
		try {
			cobroDao.buscarCobro(new Long(9222));
			System.out.println("Finalizado con éxito");
		
		} catch (PersistenciaExcepcion e) {
			System.out.println("Finalizado con errores");
			e.printStackTrace();
		}
	}
}
