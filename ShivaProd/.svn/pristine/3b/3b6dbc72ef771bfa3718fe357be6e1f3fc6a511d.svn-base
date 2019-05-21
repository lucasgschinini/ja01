package ar.com.telecom.shiva.batch;

import java.security.AccessControlException;

import ar.com.telecom.shiva.base.constantes.Configuracion;
import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Mensajes;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.utils.UnicaInstanciaFileKey;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.servicios.IBoletaSinValorServicio;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;

public class AnularBoletasBatchRunner {

	private AnularBoletasBatchRunner(){ 
	}
	
	/**
	 * Main
	 */
	public static void main(String[] args) {
		try {
			Traza.auditoria(AnularBoletasBatchRunner.class, 
						"Se ha iniciado el Batch para la Anulacion de boletas");
			if (!UnicaInstanciaFileKey.setLock(Constantes.LOCK_KEY_ANULAR_BOLETA_BATCH)) {
    	    	throw new AccessControlException(Mensajes.LISTENER_UNICA_INSTANCIA);
    	    }
    	    
			//Verifico si la version es correcta
			IParametroServicio parametroServicio =  (IParametroServicio) Configuracion.getBeanBatch("parametroServicio");
			Utilidad.verificarVersion(parametroServicio);
			
			String fechaHasta = args[0];
			//Tarea 1
			procesarAnularBoletas(fechaHasta);
			
		} catch (AccessControlException e) {
			System.err.println(Mensajes.LISTENER_UNICA_INSTANCIA);
			Traza.error(AnularBoletasBatchRunner.class, 
					Mensajes.LISTENER_UNICA_INSTANCIA, e);
			
			System.exit(Constantes.SH_ERROR_INSTANCIA);
			
		} catch (ShivaExcepcion e) {
			System.err.println(Utilidad.getStackTrace(e));
			Traza.error(AnularBoletasBatchRunner.class, "Se ha producido un error en el proceso batch", e);
			Traza.advertencia(AnularBoletasBatchRunner.class, "---- Se ha finalizado con error en el proceso de Anulacion de boletas");
			
			System.exit(Constantes.SH_ERROR);
		} finally{
			UnicaInstanciaFileKey.releaseLock();
			
			Traza.auditoria(AnularBoletasBatchRunner.class, 
					"Se ha finalizado el Batch para la Anulacion de boletas");
		}
		
		System.exit(0);
	}
	
	
	/**
	 * Proceso que anula las boletas
	 * @param fechaHasta 
	 * @param servicio
	 * @throws ShivaExcepcion
	 */
	private static void procesarAnularBoletas(String fechaHasta) throws ShivaExcepcion {
		
		try {
			IBoletaSinValorServicio servicio =  (IBoletaSinValorServicio) Configuracion.getBeanBatch("boletaSinValorServicio");
			Traza.auditoria(AnularBoletasBatchRunner.class, "Se ha iniciado el proceso de Anulacion de boletas");
			
			//Servicio que anula las boletas pendientes
			servicio.anularBoletasPendientes(fechaHasta);
			
			System.out.println("Se ha finalizado exitosamente el proceso de Anulacion de boletas");
			Traza.auditoria(AnularBoletasBatchRunner.class, 
					"---- Se ha finalizado exitosamente el proceso de Anulacion de boletas");
			
		} catch (Throwable e) {
			throw new ShivaExcepcion(e);
		}
	}
}
