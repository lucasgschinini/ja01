package ar.com.telecom.shiva.batch;

import java.security.AccessControlException;

import ar.com.telecom.shiva.base.constantes.Configuracion;
import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Mensajes;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.utils.ControlMemoriaCPU;
import ar.com.telecom.shiva.base.utils.UnicaInstanciaFileKey;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.base.utils.singleton.ControlVariablesSingleton;
import ar.com.telecom.shiva.negocio.servicios.IControlHilosCobrosServicio;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;

public class ControlHilosCobrosBatchRunner {

	private ControlHilosCobrosBatchRunner(){ 
	}
	
	/**
	 * Main
	 */
	public static void main(String[] args) {
		// Medicion de tiempo al inicio del procesamiento
		double fechaHoraInicioNanoTime = System.nanoTime();
					
		try {
			Traza.auditoria(ControlHilosCobrosBatchRunner.class, ControlMemoriaCPU.getInformacionGeneralSistema());
			
			ControlVariablesSingleton.permitirTraceoSQL();
			
			Traza.auditoria(ControlHilosCobrosBatchRunner.class, 
						"Se ha iniciado el Batch para el control de hilos y estados de cobros");
			if (!UnicaInstanciaFileKey.setLock(Constantes.LOCK_KEY_CONTROL_HILOS_COBROS_BATCH)) {
    	    	throw new AccessControlException(Mensajes.LISTENER_UNICA_INSTANCIA);
    	    }
			
			//Verifico si la version es correcta
			IParametroServicio parametroServicio =  (IParametroServicio) Configuracion.getBeanBatch("parametroServicio");
			Utilidad.verificarVersion(parametroServicio);
			
			iniciarControlDeHilos(parametroServicio);
			
			//Libero la memoria
			System.gc();
			
			
		} catch (AccessControlException e) {
			
			System.err.println(Mensajes.LISTENER_UNICA_INSTANCIA);
			Traza.error(ControlHilosCobrosBatchRunner.class, 
    				Mensajes.LISTENER_UNICA_INSTANCIA, e);
			
			System.exit(Constantes.SH_ERROR_INSTANCIA);
			
		} catch (ShivaExcepcion e) {
			System.err.println(Utilidad.getStackTrace(e));
			Traza.error(ControlHilosCobrosBatchRunner.class, "Se ha producido un error en el proceso batch", e);
			
			System.exit(Constantes.SH_ERROR);
		} finally{
			UnicaInstanciaFileKey.releaseLock();
			Traza.auditoria(ControlHilosCobrosBatchRunner.class, 
					"Se ha finalizado el Batch para el control de hilos de cobros");
			
			/**Tiempo del proceso batch*/
			double elapsedTime = System.nanoTime() - fechaHoraInicioNanoTime;
			String detalle = "Tiempo de procesamiento batch para el control de hilos de cobros: " 
						+ ((double) elapsedTime / 1000000000.0);
			Traza.tiempo(ControlHilosCobrosBatchRunner.class, "main", detalle);
		}
		System.exit(0);
	}

	private static void iniciarControlDeHilos(IParametroServicio parametroServicio) throws ShivaExcepcion {
		
		IControlHilosCobrosServicio controlHilosCobrosServicio = (IControlHilosCobrosServicio) Configuracion.getBeanBatch("controlHilosCobrosServicio");
		
		Integer tiempoLimiteParametro = 0;
		
		try {
			
			tiempoLimiteParametro = Integer.valueOf(parametroServicio.getValorNumerico(Constantes.CONTROL_HILOS_COBROS_PARAMETRO_HORA_LIMITE).toString());
			String estadosCobro = parametroServicio.getValorTexto(Constantes.CONTROL_HILOS_COBROS_PARAMETRO_ESTADOS_COBRO).toString();
			String destinatario = parametroServicio.getValorTexto(Constantes.CONTROL_HILOS_COBROS_PARAMETRO_MAIL_DESTINATARIO).toString();
			String destinatarioCC = parametroServicio.getValorTexto(Constantes.CONTROL_HILOS_COBROS_PARAMETRO_MAIL_DESTINATARIO_CC).toString();
			String horaEnvioMailCobrosPendientes = parametroServicio.getValorTexto(Constantes.CONTROL_HILOS_COBROS_PARAMETRO_HORA_ENVIO_MAIL_ESTADOS_COBRO).toString();
			
			Traza.advertencia(ControlHilosCobrosBatchRunner.class, "---- Se inicia el proceso de control de hilos cobros");
			
			controlHilosCobrosServicio.iniciarProcesoDeControlDeHilos(tiempoLimiteParametro,estadosCobro,destinatario,destinatarioCC,horaEnvioMailCobrosPendientes);
			
			Traza.advertencia(ControlHilosCobrosBatchRunner.class, "---- Se ha finalizado el proceso de control de hilos cobros");
		
		} catch (NumberFormatException e) {
			Traza.advertencia(ControlHilosCobrosBatchRunner.class, "---- Se ha finalizado con error en el proceso de control de hilos cobros");
			throw new ShivaExcepcion(e);
		} catch (NegocioExcepcion e) {
			Traza.advertencia(ControlHilosCobrosBatchRunner.class, "---- Se ha finalizado con error en el proceso de control de hilos cobros");
			throw new ShivaExcepcion(e);
		}
		
	}
	
	
	
}
