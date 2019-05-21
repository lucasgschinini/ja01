package ar.com.telecom.shiva.test.modulos;

import java.util.HashMap;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.mail.MailServicio;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.dto.RegistroAVCDto;
import ar.com.telecom.shiva.negocio.servicios.IMotorProcesamientoConciliacionAltaServicio;
import ar.com.telecom.shiva.negocio.servicios.IRegistroAVCServicio;
import ar.com.telecom.shiva.test.SoporteContextoSpringTest;

public class MotorConciliacionAltaTest extends SoporteContextoSpringTest {
	
	@Autowired IMotorProcesamientoConciliacionAltaServicio motorProcesamientoConciliacionAltaServicio;
	@Autowired IRegistroAVCServicio registroServicio;
	@Autowired MailServicio mailServicio;
	
	/**
	 * Se encarga de levantar los archivos de reversion y procesarlos uno a la vez.
	 * @throws ShivaExcepcion 
	 */
	@Test
	public void procesarTareaProcesarArchivosAvcTest() throws ShivaExcepcion {
		try {
			
			Traza.auditoria(MotorConciliacionAltaTest.class, "Se ha iniciado el motor de Procesamiento Conciliacion de Alta (Test)");
			
			// Busco los Registros AVC Interdeposito y Transferencias en estado Pendiente de conciliar
			List<RegistroAVCDto> listaRAVC = registroServicio.listarRegistrosInterdepositoYTransferenciasPendientesConciliar();
			
			// Recorro los RAVC
			HashMap<String, List<String>> cuerpoMailMap = new HashMap<String, List<String>>();
			for (RegistroAVCDto registroAvc : listaRAVC) {
				if (new Long("1746").equals(registroAvc.getIdRegistro())) {
					motorProcesamientoConciliacionAltaServicio.ejecutarProcesoConciliacion(registroAvc, cuerpoMailMap);
				}
			}
			
 			mailServicio.enviarMailMasivo("Valores disponibles||Valor disponible -", cuerpoMailMap);
			System.out.println("Se ha finalizado exitosamente el motor de Procesamiento Conciliacion de Alta (Test)");
			Traza.auditoria(MotorConciliacionAltaTest.class, "---- Se ha finalizado exitosamente el motor de Procesamiento Conciliacion de Alta (Test)");
		
		} catch (Throwable e) {
			Traza.error(MotorConciliacionAltaTest.class, "Se ha producido un error en el proceso batch (Test)", e);
			Traza.advertencia(MotorConciliacionAltaTest.class, "---- Se ha finalizado con error en el motor de Procesamiento Conciliacion de Alta (Test)");
			
			throw new ShivaExcepcion(e);
		}
	}
}
