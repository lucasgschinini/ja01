package ar.com.telecom.shiva.batch;

import java.security.AccessControlException;

import ar.com.telecom.shiva.base.constantes.Configuracion;
import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Mensajes;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.utils.UnicaInstanciaFileKey;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;
import ar.com.telecom.shiva.negocio.servicios.IRetencionServicio;

public class ReporteRetencionesIIBBBatchRunner {

	private ReporteRetencionesIIBBBatchRunner(){ 
	}
	
	/**
	 * Main
	 */
	public static void main(String[] args) {
		try {
			Traza.auditoria(ReporteRetencionesIIBBBatchRunner.class, 
						"Se ha iniciado el Batch para el reporte de retenciones IIBB");
			if (!UnicaInstanciaFileKey.setLock(Constantes.LOCK_KEY_REPORTE_RETENCIONES_IIBB_BATCH)) {
    	    	throw new AccessControlException(Mensajes.LISTENER_UNICA_INSTANCIA);
    	    }
    	    
			//Verifico si la version es correcta
			IParametroServicio parametroServicio =  (IParametroServicio) Configuracion.getBeanBatch("parametroServicio");
			Utilidad.verificarVersion(parametroServicio);
			
			String fechaHasta = args[0];
			//Tarea 1
			reporteRetenciones(fechaHasta);
			
		} catch (AccessControlException e) {
			System.err.println(Mensajes.LISTENER_UNICA_INSTANCIA);
			Traza.error(ReporteRetencionesIIBBBatchRunner.class, 
					Mensajes.LISTENER_UNICA_INSTANCIA, e);
			
			System.exit(Constantes.SH_ERROR_INSTANCIA);
			
		} catch (ShivaExcepcion e) {
			Traza.error(ReporteRetencionesIIBBBatchRunner.class, e.getMessage());
			System.err.println(Utilidad.getStackTrace(e));
			System.exit(Constantes.SH_ERROR);
			
		} finally{
			UnicaInstanciaFileKey.releaseLock();
			Traza.auditoria(ReporteRetencionesIIBBBatchRunner.class, 
					"Se ha finalizado el Batch para el reporte de retenciones IIBB");
		}
		
		System.exit(0);
	}
	
	/**
	 * Genera reporte de retenciones IIBB
	 * @param fechaHasta 
	 * 
	 * @throws ShivaExcepcion 
	 */
	private static void reporteRetenciones(String fechaHasta) throws ShivaExcepcion {
		try {
			IRetencionServicio retencionServicio = (IRetencionServicio) Configuracion.getBeanBatch("retencionServicio");
			
			Traza.auditoria(ReporteRetencionesIIBBBatchRunner.class, "Se ha iniciado el proceso para el reporte de retenciones IIBB");
			
			retencionServicio.generarReporte(fechaHasta);
			
			System.out.println("Se ha finalizado exitosamente el proceso para el reporte de retenciones IIBB");
			Traza.auditoria(ReporteRetencionesIIBBBatchRunner.class, "---- Se ha finalizado exitosamente el proceso para el reporte de retenciones IIBB");
		} catch (Throwable e) {
			
			Traza.error(ReporteRetencionesIIBBBatchRunner.class, "Se ha producido un error en el proceso batch", e);
			Traza.advertencia(ReporteRetencionesIIBBBatchRunner.class, "---- Se ha finalizado con error en el proceso para el reporte de retenciones IIBB");
			
			throw new ShivaExcepcion(e);
		}
	}
}
