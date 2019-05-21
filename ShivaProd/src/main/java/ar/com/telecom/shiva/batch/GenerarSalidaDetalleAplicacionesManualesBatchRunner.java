package ar.com.telecom.shiva.batch;

import java.security.AccessControlException;

import ar.com.telecom.shiva.base.constantes.Configuracion;
import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Mensajes;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.ConcurrenciaExcepcion;
import ar.com.telecom.shiva.base.utils.UnicaInstanciaFileKey;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.servicios.IGenerarSalidaDetalleAplicacionesManualesServicio;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;

public class GenerarSalidaDetalleAplicacionesManualesBatchRunner {

	private GenerarSalidaDetalleAplicacionesManualesBatchRunner() {
	}

	/**
	 * Main
	 */
	public static void main(String[] args) {
		try {
			Traza.auditoria(GenerarSalidaDetalleAplicacionesManualesBatchRunner.class, 
						"Se ha iniciado el Batch para la generacion del archivo de salida de la automatizacion de la confirmacion de la aplicacion manual");
			if (!UnicaInstanciaFileKey.setLock(Constantes.LOCK_KEY_GENERAR_SALIDA_DETALLE_APLICACIONES_MANUALES_BATCH)) {
    	    	throw new AccessControlException(Mensajes.LISTENER_UNICA_INSTANCIA);
    	    }
    	    
			//Verifico si la version es correcta
			IParametroServicio parametroServicio =  (IParametroServicio) Configuracion.getBeanBatch("parametroServicio");
			Utilidad.verificarVersion(parametroServicio);
			String fechaHasta = args[0];
			
			Traza.auditoria(ReporteValoresPorEmpresaBatchRunner.class, "Se recibio la siguiente fecha como parametro: " + fechaHasta);
			
			procesarTareaGeneracionSalidaDetalleAplicacionesManuales(fechaHasta);
			
		} catch (AccessControlException e) {
			System.err.println(Mensajes.LISTENER_UNICA_INSTANCIA);
			Traza.error(GenerarSalidaDetalleAplicacionesManualesBatchRunner.class, 
    				Mensajes.LISTENER_UNICA_INSTANCIA, e);
			
			System.exit(Constantes.SH_ERROR_INSTANCIA);
			
		} catch (ShivaExcepcion e) {
			System.err.println(Utilidad.getStackTrace(e));
			Traza.error(GenerarSalidaDetalleAplicacionesManualesBatchRunner.class, 
    				"Se ha producido un error en la generacion del archivo de salida de la automatizacion de la confirmacion de la aplicacion manual", e);
			System.exit(Constantes.SH_ERROR);
			
		} catch (Exception e) {
			System.err.println(Utilidad.getStackTrace(e));
			Traza.error(GenerarSalidaDetalleAplicacionesManualesBatchRunner.class, 
    				"Se ha producido un error desconocido", e);
			System.exit(Constantes.SH_ERROR);
		}
		finally{
			UnicaInstanciaFileKey.releaseLock();
			Traza.auditoria(GenerarSalidaDetalleAplicacionesManualesBatchRunner.class, 
					"Se ha finalizado el Batch para la generacion del archivo de salida de la automatizacion de la confirmacion de la aplicacion manual");
		}
		
		System.exit(0);
	}
	
	/**
	 * Se encarga de levantar los registro avc sin conciliar y los compara uno a la vez contra las boletas pendientes de conciliar.
	 * @throws ShivaExcepcion 
	 */
	private static void procesarTareaGeneracionSalidaDetalleAplicacionesManuales(String fechaHasta) throws ShivaExcepcion {
		try {
			IGenerarSalidaDetalleAplicacionesManualesServicio generarSalidaDetalleAplicacionesManualesServicio = (IGenerarSalidaDetalleAplicacionesManualesServicio) Configuracion.getBeanBatch("generarSalidaDetalleAplicacionesManualesServicio");
			Traza.auditoria(GenerarSalidaDetalleAplicacionesManualesBatchRunner.class, "Se ha iniciado la generacion del archivo de salida de la automatizacion de la confirmacion de la aplicacion manual");
			try{
				generarSalidaDetalleAplicacionesManualesServicio.procesarTareaGeneracionSalidaDetalleAplicacionesManuales(fechaHasta);
			} catch (ConcurrenciaExcepcion e) {
				Traza.error(GenerarSalidaDetalleAplicacionesManualesBatchRunner.class, "Se ha producido un error de concurrencia - id: " + ((ConcurrenciaExcepcion)e).getIdInconcurrente());
			}
			
		} catch (Throwable e) {
			Traza.error(GenerarSalidaDetalleAplicacionesManualesBatchRunner.class, "Se ha producido un error en el proceso batch", e);
			throw new ShivaExcepcion(e);
		}
	}

}