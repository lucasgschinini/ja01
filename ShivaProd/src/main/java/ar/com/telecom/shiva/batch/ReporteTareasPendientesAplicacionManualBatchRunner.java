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
import ar.com.telecom.shiva.negocio.servicios.IReporteTareasPendientesAplicacionManualServicio;

public class ReporteTareasPendientesAplicacionManualBatchRunner {
	public static void main(String[] args) {
		try {
			Traza.auditoria(ReporteTareasPendientesAplicacionManualBatchRunner.class, "Se ha iniciado el Batch Reporte de Tareas Pendientes de Aplicacion Manual");
			if (!UnicaInstanciaFileKey.setLock(Constantes.LOCK_KEY_REPORTE_TAREA_PENDIENTE_APLICACION_MANUAL_BATCH)) {
				throw new AccessControlException(Mensajes.LISTENER_UNICA_INSTANCIA);
			}
		
			//Verifico si la version es correcta
			IParametroServicio parametroServicio =  (IParametroServicio) Configuracion.getBeanBatch("parametroServicio");
			Utilidad.verificarVersion(parametroServicio);
			String fecha = args[0];
			
			Traza.auditoria(ReporteValoresPorEmpresaBatchRunner.class, "Se recibio la siguiente fecha como parametro: " + fecha);
			//Tarea 1
			procesarTareasPendientesAplicacionManual(fecha);
			
		
		} catch (AccessControlException e) {
			
    		System.err.println(Mensajes.LISTENER_UNICA_INSTANCIA);
    		Traza.error(ReporteTareasPendientesAplicacionManualBatchRunner.class, 
    				Mensajes.LISTENER_UNICA_INSTANCIA, e);
    		
    		System.exit(Constantes.SH_ERROR_INSTANCIA);
    		
    	} catch (ShivaExcepcion e) {
    		Traza.error(ReporteTareasPendientesAplicacionManualBatchRunner.class, e.getMessage());
    		System.err.println(Utilidad.getStackTrace(e));
    		System.exit(Constantes.SH_ERROR);
    		
		} finally{
    		UnicaInstanciaFileKey.releaseLock();
    		Traza.auditoria(ReporteTareasPendientesAplicacionManualBatchRunner.class, 
					"Se ha finalizado el Batch para el proceso Reporte de Tareas Pendientes de Aplicación Manual");
    	}
		
		System.exit(0);
	}
	

	private static void procesarTareasPendientesAplicacionManual(String fecha) throws ShivaExcepcion {
		try {
			IReporteTareasPendientesAplicacionManualServicio reporteTareasPendientesAplicacionManualServicio = (IReporteTareasPendientesAplicacionManualServicio) Configuracion.getBeanBatch("reporteTareasPendientesAplicacionManualServicio");
			Traza.auditoria(ReporteTareasPendientesAplicacionManualBatchRunner.class, "Se ha iniciado el proceso Reporte de Tareas Pendientes de Aplicación Manual");
			
			reporteTareasPendientesAplicacionManualServicio.buscarTareasPendientesAplicacionManual(fecha);
			
			System.out.println("Se ha finalizado exitosamente el proceso Reporte de Aplicación Manual Pendientes");
    		Traza.auditoria(ReporteTareasPendientesAplicacionManualBatchRunner.class, "---- Se ha finalizado exitosamente el proceso Reporte de Aplicación Manual Pendientes");
		
		} catch (Throwable e) {
			Traza.error(ReporteTareasPendientesAplicacionManualBatchRunner.class, "Se ha producido un error en el proceso batch", e);
			Traza.advertencia(ReporteTareasPendientesAplicacionManualBatchRunner.class, "---- Se ha finalizado con error en el proceso Reporte de Aplicación Manual Pendientes");
			
			throw new ShivaExcepcion(e);
		}
		
	}
}
