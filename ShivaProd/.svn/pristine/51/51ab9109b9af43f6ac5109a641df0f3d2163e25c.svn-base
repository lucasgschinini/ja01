package ar.com.telecom.shiva.batch;

import java.security.AccessControlException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ar.com.telecom.shiva.base.constantes.Configuracion;
import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Mensajes;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.ConcurrenciaExcepcion;
import ar.com.telecom.shiva.base.mail.Mail;
import ar.com.telecom.shiva.base.mail.MailServicio;
import ar.com.telecom.shiva.base.utils.UnicaInstanciaFileKey;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.servicios.IMotorProcesamientoConciliacionServicio;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteMotorConciliacion;

public class MotorProcesamientoConciliacionBatchRunner {

	private MotorProcesamientoConciliacionBatchRunner() {
	}

	/**
	 * Main
	 */
	public static void main(String[] args) {
		try {
			Traza.auditoria(MotorProcesamientoConciliacionBatchRunner.class, 
						"Se ha iniciado el Batch para el motor de Procesamiento Conciliacion");
			if (!UnicaInstanciaFileKey.setLock(Constantes.LOCK_KEY_MOTOR_CONCILIACION_BATCH)) {
    	    	throw new AccessControlException(Mensajes.LISTENER_UNICA_INSTANCIA);
    	    }
    	    
			//Verifico si la version es correcta
			IParametroServicio parametroServicio =  (IParametroServicio) Configuracion.getBeanBatch("parametroServicio");
			Utilidad.verificarVersion(parametroServicio);
			
			//Tarea 1
			procesarTareaProcesarArchivosAvc();
			
		} catch (AccessControlException e) {
			System.err.println(Mensajes.LISTENER_UNICA_INSTANCIA);
			Traza.error(MotorProcesamientoConciliacionBatchRunner.class, 
    				Mensajes.LISTENER_UNICA_INSTANCIA, e);
			
			System.exit(Constantes.SH_ERROR_INSTANCIA);
			
		} catch (ShivaExcepcion e) {
			System.err.println(Utilidad.getStackTrace(e));
			Traza.error(MotorProcesamientoConciliacionBatchRunner.class, 
    				"Se ha producido un error en el motor de procesamiento Conciliacion", e);
			System.exit(Constantes.SH_ERROR);
			
		} catch (Exception e) {
			System.err.println(Utilidad.getStackTrace(e));
			Traza.error(MotorProcesamientoConciliacionBatchRunner.class, 
    				"Se ha producido un error desconocido", e);
			System.exit(Constantes.SH_ERROR);
		}
		finally{
			UnicaInstanciaFileKey.releaseLock();
			Traza.auditoria(MotorProcesamientoConciliacionBatchRunner.class, 
					"Se ha finalizado el Batch para el motor de Procesamiento Conciliacion");
		}
		
		System.exit(0);
	}
	
	/**
	 * Se encarga de levantar los registro avc sin conciliar y los compara uno a la vez contra las boletas pendientes de conciliar.
	 * @throws ShivaExcepcion 
	 */
	private static void procesarTareaProcesarArchivosAvc() throws ShivaExcepcion {
		try {
			IMotorProcesamientoConciliacionServicio motorProcesamientoConciliacionServicio = (IMotorProcesamientoConciliacionServicio) Configuracion.getBeanBatch("motorProcesamientoConciliacionServicio");
			MailServicio mailServicio = (MailServicio) Configuracion.getBeanBatch("mailServicio");
			Traza.auditoria(MotorProcesamientoConciliacionBatchRunner.class, "Se ha iniciado el motor de Procesamiento Conciliacion");
			Boolean ocurrioConcurrenciaExcepcion = false;
			
			HashMap<String, List<String>> cuerpoMailMap = new HashMap<String, List<String>>();
			
			Mail mailAsuntoAux = new Mail();
			
			//Lista de conciliaciones
			List<VistaSoporteMotorConciliacion> listaDeConciliaciones = new ArrayList<VistaSoporteMotorConciliacion>();
			
			do {
				// Busco el listado de conciliaciones de la regla mas baja en la vista 
				listaDeConciliaciones = motorProcesamientoConciliacionServicio.listarRegistrosMotorConciliacionPorReglaMenor();
				
				if(Validaciones.isCollectionNotEmpty(listaDeConciliaciones)){
					Traza.auditoria(MotorProcesamientoConciliacionBatchRunner.class, "Se obtuvieron " +listaDeConciliaciones.size() 
							+ " conciliaciones con la regla " + listaDeConciliaciones.get(0).getRegla());
					
					
					// Recorro la lista de RAVC realizando las conciliaciones
					try{
						motorProcesamientoConciliacionServicio.ejecutarProcesoConciliacion(listaDeConciliaciones, cuerpoMailMap, mailAsuntoAux);
					} catch (ConcurrenciaExcepcion e) {
						ocurrioConcurrenciaExcepcion= true;
						Traza.error(MotorProcesamientoConciliacionBatchRunner.class, "Se ha producido un error de concurrencia - id: " + ((ConcurrenciaExcepcion)e).getIdInconcurrente());
					}
				}
			} while(Validaciones.isCollectionNotEmpty(listaDeConciliaciones));
			
			Traza.auditoria(MotorProcesamientoConciliacionBatchRunner.class, "Ya no se encontraron conciliaciones.");
			
			
			mailServicio.enviarMailMasivo(mailAsuntoAux.getAsunto(), cuerpoMailMap);
			
			
			if (!ocurrioConcurrenciaExcepcion){
				System.out.println("Se ha finalizado exitosamente el motor de Procesamiento Conciliacion");
				Traza.auditoria(MotorProcesamientoConciliacionBatchRunner.class, "---- Se ha finalizado exitosamente el motor de Procesamiento Conciliacion");
			} else { 
				System.out.println("Se ha finalizado con error en el motor de Procesamiento Conciliacion");
				Traza.advertencia(MotorProcesamientoConciliacionBatchRunner.class, "---- Se ha finalizado con error en el motor de Procesamiento Conciliacion");
			}
		
		} catch (Throwable e) {
			Traza.error(MotorProcesamientoConciliacionBatchRunner.class, "Se ha producido un error en el proceso batch", e);
			throw new ShivaExcepcion(e);
		}
	}


}