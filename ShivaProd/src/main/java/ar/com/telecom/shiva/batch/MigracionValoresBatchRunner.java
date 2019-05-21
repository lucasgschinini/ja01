package ar.com.telecom.shiva.batch;

import java.security.AccessControlException;

import ar.com.telecom.shiva.base.constantes.Configuracion;
import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Mensajes;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.utils.UnicaInstanciaFileKey;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.servicios.IMigracionServicio;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;

public class MigracionValoresBatchRunner {

	private MigracionValoresBatchRunner(){ 
	}
	
	/**
	 * Main
	 */
	public static void main(String[] args) {
		try {
			Traza.auditoria(MigracionValoresBatchRunner.class, 
						"Se ha iniciado el Batch para la migracion de valores");
			if (!UnicaInstanciaFileKey.setLock(Constantes.LOCK_KEY_MIGRACION_BATCH)) {
    	    	throw new AccessControlException(Mensajes.LISTENER_UNICA_INSTANCIA);
    	    }
    	    
			//Verifico si la version es correcta
			IParametroServicio parametroServicio =  (IParametroServicio) Configuracion.getBeanBatch("parametroServicio");
			Utilidad.verificarVersion(parametroServicio);
			
			//Tarea 1
			procesarMigracionValores();
			
		} catch (AccessControlException e) {
			System.err.println(Mensajes.LISTENER_UNICA_INSTANCIA);
			Traza.error(MigracionValoresBatchRunner.class, 
    				Mensajes.LISTENER_UNICA_INSTANCIA, e);
			
			System.exit(Constantes.SH_ERROR_INSTANCIA);
			
		} catch (ShivaExcepcion e) {
			System.err.println(Utilidad.getStackTrace(e));
			System.exit(Constantes.SH_ERROR);
			
		} finally{
			UnicaInstanciaFileKey.releaseLock();
			Traza.auditoria(MigracionValoresBatchRunner.class, 
					"Se ha finalizado el Batch para la migracion de valores");
		}
		
		System.exit(0);
	}
	
	/**
	 * Proceso de migracion de valores
	 * @throws ShivaExcepcion 
	 */
	private static void procesarMigracionValores() throws ShivaExcepcion {
		try {
			IMigracionServicio migracionServicio = (IMigracionServicio) Configuracion.getBeanBatch("migracionServicio");
			
			Traza.auditoria(MigracionValoresBatchRunner.class, "Se ha iniciado el proceso de Migracion de valores");
			
			//Proceso que realiza la migracion de valores
			migracionServicio.migracionValores();
			
			System.out.println("Se ha finalizado exitosamente el proceso de Migracion de valores");
    		Traza.auditoria(MigracionValoresBatchRunner.class, "---- Se ha finalizado exitosamente el proceso de Migracion de valores");
    		
		} catch (Throwable e) {
			Traza.error(MigracionValoresBatchRunner.class, "Se ha producido un error en el proceso batch", e);
			Traza.advertencia(MigracionValoresBatchRunner.class, "---- Se ha finalizado con error en el proceso de Migracion de valores");
			
			throw new ShivaExcepcion(e);
		}
	}
}
