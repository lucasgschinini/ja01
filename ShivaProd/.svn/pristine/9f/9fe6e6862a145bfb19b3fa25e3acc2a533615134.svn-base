package ar.com.telecom.shiva.batch;

import java.security.AccessControlException;
import java.util.HashMap;
import java.util.List;

import ar.com.telecom.shiva.base.constantes.Configuracion;
import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Mensajes;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.mail.MailServicio;
import ar.com.telecom.shiva.base.utils.UnicaInstanciaFileKey;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.dto.RegistroAVCDto;
import ar.com.telecom.shiva.negocio.servicios.IMotorProcesamientoConciliacionAltaServicio;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;
import ar.com.telecom.shiva.negocio.servicios.IRegistroAVCServicio;

public class MotorProcesamientoConciliacionAltaBatchRunner {

	private MotorProcesamientoConciliacionAltaBatchRunner(){ 
	}
	
	/**
	 * Main
	 */
	public static void main(String[] args) {
		try {
			Traza.auditoria(MotorProcesamientoConciliacionAltaBatchRunner.class, 
						"Se ha iniciado el Batch para el motor de Procesamiento Conciliacion de Alta");
			if (!UnicaInstanciaFileKey.setLock(Constantes.LOCK_KEY_MOTOR_CONCILIACION_BATCH_ALTA)) {
    	    	throw new AccessControlException(Mensajes.LISTENER_UNICA_INSTANCIA);
    	    }
    	    
			//Verifico si la version es correcta
			IParametroServicio parametroServicio =  (IParametroServicio) Configuracion.getBeanBatch("parametroServicio");
			Utilidad.verificarVersion(parametroServicio);
			
			//Tarea 1
			procesarTareaProcesarArchivosAvc();
			
		} catch (AccessControlException e) {
			System.err.println(Mensajes.LISTENER_UNICA_INSTANCIA);
			Traza.error(MotorProcesamientoConciliacionAltaBatchRunner.class, 
    				Mensajes.LISTENER_UNICA_INSTANCIA, e);
			
			System.exit(Constantes.SH_ERROR_INSTANCIA);
			
		} catch (ShivaExcepcion e) {
			System.err.println(Utilidad.getStackTrace(e));
			Traza.error(MotorProcesamientoConciliacionAltaBatchRunner.class, 
    				"Se ha producido un error en el motor de procesamiento Conciliacion de Alta", e);
			System.exit(Constantes.SH_ERROR);
			
		} catch (Exception e) {
			System.err.println(Utilidad.getStackTrace(e));
			Traza.error(MotorProcesamientoConciliacionAltaBatchRunner.class, 
    				"Se ha producido un error desconocido", e);
			System.exit(Constantes.SH_ERROR);
		}
		finally{
			UnicaInstanciaFileKey.releaseLock();
			Traza.auditoria(MotorProcesamientoConciliacionAltaBatchRunner.class, 
					"Se ha finalizado el Batch para el motor de Procesamiento Conciliacion de Alta");
		}
		
		System.exit(0);
	}
	
	/**
	 * Se encarga de levantar los archivos de reversion y procesarlos uno a la vez.
	 * @throws ShivaExcepcion 
	 */
	private static void procesarTareaProcesarArchivosAvc() throws ShivaExcepcion {
		try {
			
			IMotorProcesamientoConciliacionAltaServicio motorProcesamientoConciliacionAltaServicio = (IMotorProcesamientoConciliacionAltaServicio) Configuracion.getBeanBatch("motorProcesamientoConciliacionAltaServicio");
			IRegistroAVCServicio registroServicio = (IRegistroAVCServicio) Configuracion.getBeanBatch("registroAVCServicio");
			MailServicio mailServicio = (MailServicio) Configuracion.getBeanBatch("mailServicio");
			Traza.auditoria(MotorProcesamientoConciliacionAltaBatchRunner.class, "Se ha iniciado el motor de Procesamiento Conciliacion de Alta");
//			mostrarReglas();
			
			// Busco los Registros AVC Interdeposito y Transferencias en estado Pendiente de conciliar
			List<RegistroAVCDto> listaRAVC = registroServicio.listarRegistrosInterdepositoYTransferenciasPendientesConciliar();
			
			// Recorro los RAVC
			HashMap<String, List<String>> cuerpoMailMap = new HashMap<String, List<String>>();
			for (RegistroAVCDto registroAvc : listaRAVC) {
				motorProcesamientoConciliacionAltaServicio.ejecutarProcesoConciliacion(registroAvc, cuerpoMailMap);
			}
			
 			mailServicio.enviarMailMasivo("Valores disponibles||Valor disponible -", cuerpoMailMap);
			System.out.println("Se ha finalizado exitosamente el motor de Procesamiento Conciliacion de Alta");
			Traza.auditoria(MotorProcesamientoConciliacionAltaBatchRunner.class, "---- Se ha finalizado exitosamente el motor de Procesamiento Conciliacion de Alta");
		
		} catch (Throwable e) {
			Traza.error(MotorProcesamientoConciliacionAltaBatchRunner.class, "Se ha producido un error en el proceso batch", e);
			Traza.advertencia(MotorProcesamientoConciliacionAltaBatchRunner.class, "---- Se ha finalizado con error en el motor de Procesamiento Conciliacion de Alta");
			
			throw new ShivaExcepcion(e);
		}
	}
	
}