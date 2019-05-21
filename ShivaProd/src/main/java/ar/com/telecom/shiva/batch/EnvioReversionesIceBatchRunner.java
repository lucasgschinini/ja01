package ar.com.telecom.shiva.batch;

import java.security.AccessControlException;

import ar.com.telecom.shiva.base.constantes.Configuracion;
import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Mensajes;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.utils.UnicaInstanciaFileKey;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.base.utils.singleton.ControlVariablesSingleton;
import ar.com.telecom.shiva.negocio.servicios.ILegajoChequeRechazadoReversionIceServicio;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;

public class EnvioReversionesIceBatchRunner {

	private EnvioReversionesIceBatchRunner(){ 
	}
	
	/**
	 * Main
	 */
	public static void main(String[] args) {
					
		boolean resultado = false;
		try {
			
			ControlVariablesSingleton.permitirTraceoSQL();
			
			Traza.auditoria(EnvioReversionesIceBatchRunner.class, 
						"Se ha iniciado el Batch para el envio de las reversiones a ICE");
			if (!UnicaInstanciaFileKey.setLock(Constantes.LOCK_KEY_ENVIO_REVERSIONES_ICE_BATCH)) {
    	    	throw new AccessControlException(Mensajes.LISTENER_UNICA_INSTANCIA);
    	    }
			
				//Verifico si la version es correcta
				IParametroServicio parametroServicio =  (IParametroServicio) Configuracion.getBeanBatch("parametroServicio");
				Utilidad.verificarVersion(parametroServicio);
				String fechaHasta = args[0];
				//Tarea 1
				resultado = procesarTareaArmadoYEnvioDeArchivo(fechaHasta);
				
			} catch (AccessControlException e) {
				
	    		System.err.println(Mensajes.LISTENER_UNICA_INSTANCIA);
	    		Traza.error(EnvioReversionesIceBatchRunner.class, Mensajes.LISTENER_UNICA_INSTANCIA, e);
	    		
	    		System.exit(Constantes.SH_ERROR_INSTANCIA);
	    	
	    	} catch (ShivaExcepcion e) {
	    		Traza.error(EnvioReversionesIceBatchRunner.class, e.getMessage());
	    		System.err.println(Utilidad.getStackTrace(e));
	    		System.exit(Constantes.SH_ERROR);
	    		
			} finally{
	    		UnicaInstanciaFileKey.releaseLock();
	    		Traza.auditoria(EnvioReversionesIceBatchRunner.class, "Se ha finalizado el Batch para el envio de las reversiones a ICE");
	    	}
			
			if (resultado) {
				System.out.println("Se ha finalizado exitosamente, el Batch para el envio de las reversiones a ICE");
				Traza.auditoria(EnvioReversionesIceBatchRunner.class, 
						"---- Se ha finalizado exitosamente, el Batch para el envio de las reversiones a ICE");
				
				System.exit(0);
			} else {
				
				System.out.println("Se ha finalizado con algun error, el Batch para el envio de las reversiones a ICE");
				Traza.advertencia(EnvioReversionesIceBatchRunner.class, 
						"---- Se ha finalizado con algun error, el Batch para el envio de las reversiones a ICE");
				
				System.exit(Constantes.SH_ERROR);
			}
		}
		
		/**
		 * Se encarga de armar el archivo con las reversiones a ICE.
		 * @throws ShivaExcepcion
		 */
		private static boolean procesarTareaArmadoYEnvioDeArchivo(String fechaHasta) throws ShivaExcepcion {
			try {
				ILegajoChequeRechazadoReversionIceServicio legajoChequeRechazadoReversionIceServicio = (ILegajoChequeRechazadoReversionIceServicio) Configuracion.getBeanBatch("legajoChequeRechazadoReversionIceServicio");
				
				Traza.auditoria(EnvioReversionesIceBatchRunner.class, "Se ha iniciado el proceso del metodo procesarTareaArmadoYEnvioDeArchivo");
				
				legajoChequeRechazadoReversionIceServicio.generarArchivoReversionesIce(fechaHasta);
				return true;
				
			} catch (Throwable e) {
				Traza.error(EnvioReversionesIceBatchRunner.class, "Se ha producido un error en el proceso batch", e);
				Traza.advertencia(EnvioReversionesIceBatchRunner.class, "---- Se ha finalizado con error en el proceso de envio de las reversiones a ICE");
				
				throw new ShivaExcepcion(e);
			}
		}
	
}
