package ar.com.telecom.shiva.batch;

import java.security.AccessControlException;

import ar.com.telecom.shiva.base.constantes.Configuracion;
import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Mensajes;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.utils.ControlMemoriaCPU;
import ar.com.telecom.shiva.base.utils.UnicaInstanciaFileKey;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.servicios.IImputacionAutomaticaValoresClientesPuroServicio;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;

public class ImputacionValoresClientesPurosBatchRunner {

	private ImputacionValoresClientesPurosBatchRunner(){ 
	}
	
	/**
	 * Main
	 */
	public static void main(String[] args) {
		// Medicion de tiempo al inicio del procesamiento
		double fechaHoraInicioNanoTime = System.nanoTime();
					
		try {
			Traza.auditoria(ImputacionValoresClientesPurosBatchRunner.class, ControlMemoriaCPU.getInformacionGeneralSistema());
			
			Traza.auditoria(ImputacionValoresClientesPurosBatchRunner.class, 
						"Se ha iniciado el Batch para la imputacion de automatica de valores de Clientes Puros");
			if (!UnicaInstanciaFileKey.setLock(Constantes.LOCK_KEY_IMPUTACION_AUTOMATICA_VALORES_CLIENTES_PUROS)) {
    	    	throw new AccessControlException(Mensajes.LISTENER_UNICA_INSTANCIA);
    	    }
			
			//Verifico si la version es correcta
			IParametroServicio parametroServicio =  (IParametroServicio) Configuracion.getBeanBatch("parametroServicio");
			Utilidad.verificarVersion(parametroServicio);
			
			//Tarea 1
			procesarTareaImputacionAutomaticaCobros();
			
		} catch (AccessControlException e) {
			
			System.err.println(Mensajes.LISTENER_UNICA_INSTANCIA);
			Traza.error(ImputacionValoresClientesPurosBatchRunner.class, 
    				Mensajes.LISTENER_UNICA_INSTANCIA, e);
			
			System.exit(Constantes.SH_ERROR_INSTANCIA);
			
		} catch (ShivaExcepcion e) {
			Traza.error(ImputacionValoresClientesPurosBatchRunner.class, e.getMessage());
			System.err.println(Utilidad.getStackTrace(e));			
			System.exit(Constantes.SH_ERROR);
			
		} finally{
			UnicaInstanciaFileKey.releaseLock();
			Traza.auditoria(ImputacionValoresClientesPurosBatchRunner.class, 
					"Se ha finalizado el Batch para la imputacion de automatica de valores de Clientes Puros");
			
			//Tiempo del proceso batch
			double elapsedTime = System.nanoTime() - fechaHoraInicioNanoTime;
			String detalle = "Tiempo de procesamiento batch de la imputacion automatica de valores de Clientes Puros: " 
						+ ((double) elapsedTime / 1000000000.0);
			Traza.tiempo(ImputacionValoresClientesPurosBatchRunner.class, "main", detalle);
		}
		System.exit(0);
	}
	

	/**
	 * Genera un cobro por cada valor cuyo cliente pertenece a Cablevision o Nextel.
	 * @param  
	 * @throws ShivaExcepcion
	 */
	private static void procesarTareaImputacionAutomaticaCobros() throws ShivaExcepcion {
		try {
			IImputacionAutomaticaValoresClientesPuroServicio imputacionAutomaticaValoresClientesPuroServicio = (IImputacionAutomaticaValoresClientesPuroServicio) Configuracion.getBeanBatch("imputacionAutomaticaValoresClientesPuroServicio");
			Traza.auditoria(ImputacionValoresClientesPurosBatchRunner.class, "Se ha iniciado el proceso Imputacion Automatica de Valores pertenecientes a Clientes Puros");
			
			imputacionAutomaticaValoresClientesPuroServicio.generarImputacionAutomaticaValor();
			
			System.out.println("Se ha finalizado exitosamente el proceso Imputacion Automatica de Valores pertenecientes a Clientes Puros");
    		Traza.auditoria(ImputacionValoresClientesPurosBatchRunner.class, "---- Se ha finalizado exitosamente el proceso Imputacion Automatica de Valores pertenecientes a Clientes Puros");
		
		} catch (Throwable e) {
			Traza.error(ImputacionValoresClientesPurosBatchRunner.class, "Se ha producido un error en el proceso batch", e);
			Traza.advertencia(ImputacionValoresClientesPurosBatchRunner.class, "---- Se ha finalizado con error en el proceso Imputacion Automatica de Valores pertenecientes a Clientes Puros");
			
			throw new ShivaExcepcion(e);
		}
		
	}

}