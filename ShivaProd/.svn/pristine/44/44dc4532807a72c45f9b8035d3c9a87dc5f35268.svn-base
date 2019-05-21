package ar.com.telecom.shiva.batch;

import java.security.AccessControlException;

import ar.com.telecom.shiva.base.constantes.Configuracion;
import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Mensajes;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.utils.UnicaInstanciaFileKey;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.servicios.IInterfazExcepcionMorosidadServicio;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;

public class InterfazExcepcionMorosidadBatchRunner {

	private InterfazExcepcionMorosidadBatchRunner(){ 
	}

	/**
	 * Main
	 */
	public static void main(String[] args) {
		try {
			Traza.auditoria(InterfazExcepcionMorosidadBatchRunner.class, 
						"Se ha iniciado el Batch para la generación del archivo de excepciones a la morosidad");
			if (!UnicaInstanciaFileKey.setLock(Constantes.LOCK_KEY_EXCEPCION_MOROSIDAD_BATCH)) {
    	    	throw new AccessControlException(Mensajes.LISTENER_UNICA_INSTANCIA);
    	    }

			//Verifico si la version es correcta
			IParametroServicio parametroServicio =  (IParametroServicio) Configuracion.getBeanBatch("parametroServicio");
			Utilidad.verificarVersion(parametroServicio);

			generarArchivoExcepcionesMorosidad(args[0]);
			
			
		} catch (AccessControlException e) {
    		System.err.println(Mensajes.LISTENER_UNICA_INSTANCIA);
    		Traza.error(InterfazExcepcionMorosidadBatchRunner.class, Mensajes.LISTENER_UNICA_INSTANCIA, e);
			
			System.exit(Constantes.SH_ERROR_INSTANCIA);
		} catch (ShivaExcepcion e) {
			System.err.println(Utilidad.getStackTrace(e));
			Traza.error(ImputacionCobrosBatchRunner.class, "Se ha producido un error en el proceso batch de excepcion de morosidad", e);
			
			System.exit(Constantes.SH_ERROR);	
    	} finally{
    		UnicaInstanciaFileKey.releaseLock();
    		Traza.auditoria(InterfazExcepcionMorosidadBatchRunner.class, "Se ha finalizado el Batch para la generación del archivo de excepciones a la morosidad");
    	}
		
		System.exit(0);
	}
	
	/**
	 * 
	 * @param cobroServicio
	 * @throws ShivaExcepcion
	 */
	private static void generarArchivoExcepcionesMorosidad(String fecha) throws ShivaExcepcion {
		try {
			IInterfazExcepcionMorosidadServicio interfazExcepcionMorosidadServicio = (IInterfazExcepcionMorosidadServicio) Configuracion.getBeanBatch("interfazExcepcionMorosidadServicio");

			Traza.auditoria(InterfazExcepcionMorosidadBatchRunner.class, "Se ha iniciado el proceso de generación del archivo de excepciones a la morosidad");

			interfazExcepcionMorosidadServicio.generarArchivoExcepcionMorosidad(fecha);

			System.out.println("Se ha finalizado exitosamente el proceso de generación del archivo de excepciones a la morosidad");
			Traza.auditoria(InterfazExcepcionMorosidadBatchRunner.class, "---- Se ha finalizado exitosamente el proceso de generación del archivo de excepciones a la morosidad");

		} catch (Throwable e) {
			Traza.error(InterfazExcepcionMorosidadBatchRunner.class, "Se ha producido un error en el proceso batch de excepcion de morosidad", e);
			Traza.advertencia(InterfazExcepcionMorosidadBatchRunner.class, "---- Se ha finalizado con error en el proceso de generación del archivo de excepciones a la morosidad");
			throw new ShivaExcepcion(e);
		}
	}
	
}
