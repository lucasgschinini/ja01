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
import ar.com.telecom.shiva.negocio.servicios.IValorServicio;

public class ReporteMorosidadBatchRunner {

	
	/**
	 * Main
	 */
	public static void main(String[] args) {
		try {
			Traza.auditoria(ReporteMorosidadBatchRunner.class, 
						"Se ha iniciado el Batch de Reporte de Morosidad");
			if (!UnicaInstanciaFileKey.setLock(Constantes.LOCK_KEY_REPORTE_MOROSIDAD_BATCH)) {
    	    	throw new AccessControlException(Mensajes.LISTENER_UNICA_INSTANCIA);
    	    }
    	    
			//Verifico si la version es correcta
			IParametroServicio parametroServicio =  (IParametroServicio) Configuracion.getBeanBatch("parametroServicio");
			Utilidad.verificarVersion(parametroServicio);
			String fechaHasta = args[0];
			//Tarea 1
			procesarTareaReporteMorosidad(fechaHasta);
			
		
		} catch (AccessControlException e) {
			
    		System.err.println(Mensajes.LISTENER_UNICA_INSTANCIA);
    		Traza.error(ReporteMorosidadBatchRunner.class, 
    				Mensajes.LISTENER_UNICA_INSTANCIA, e);
    		
    		System.exit(Constantes.SH_ERROR_INSTANCIA);
    		
    	} catch (ShivaExcepcion e) {
    		Traza.error(ReporteMorosidadBatchRunner.class, e.getMessage());
    		System.err.println(Utilidad.getStackTrace(e));
    		System.exit(Constantes.SH_ERROR);
    		
		} finally{
    		UnicaInstanciaFileKey.releaseLock();
    		Traza.auditoria(ReporteMorosidadBatchRunner.class, 
					"Se ha finalizado el Batch para el proceso de Reporte de Morosidad");
    	}
		
		System.exit(0);
	}
	
	/**
	 * Genero reporte de morosidad
	 * @param fechaHasta 
	 * @throws ShivaExcepcion
	 */
	private static void procesarTareaReporteMorosidad(String fechaHasta) throws ShivaExcepcion {
		try {
			IValorServicio valorServicio = (IValorServicio) Configuracion.getBeanBatch("valorServicio");
			Traza.auditoria(ReporteMorosidadBatchRunner.class, "Se ha iniciado el proceso de Reporte de Morosidad");
			
			valorServicio.generarReporteMorosidad(fechaHasta);
			
			System.out.println("Se ha finalizado exitosamente el proceso Reporte de Morosidad");
    		Traza.auditoria(ReporteMorosidadBatchRunner.class, "---- Se ha finalizado exitosamente el proceso Reporte de Morosidad");
		
		} catch (Throwable e) {
			Traza.error(ReporteMorosidadBatchRunner.class, "Se ha producido un error en el proceso batch", e);
			Traza.advertencia(ReporteMorosidadBatchRunner.class, "---- Se ha finalizado con error en el proceso de Reporte de Morosidad");
			
			throw new ShivaExcepcion(e);
		}
	}
	
}
