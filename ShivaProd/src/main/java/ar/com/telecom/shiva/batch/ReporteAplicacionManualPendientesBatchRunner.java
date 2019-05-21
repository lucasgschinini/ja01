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
import ar.com.telecom.shiva.negocio.servicios.IReporteAplicacionManualPendientesServicio;

public class ReporteAplicacionManualPendientesBatchRunner {

	/*
	 * Main
	 */
	
	public static void main(String[] args) {
		try {
			Traza.auditoria(ReporteAplicacionManualPendientesBatchRunner.class, "Se ha iniciado el Batch Reporte Aplicacion Manual Pendientes");
			if (!UnicaInstanciaFileKey.setLock(Constantes.LOCK_KEY_REPORTE_APLICACION_MANUAL_PENDIENTE_BATCH)) {
				throw new AccessControlException(Mensajes.LISTENER_UNICA_INSTANCIA);
			}
		
			//Verifico si la version es correcta
			IParametroServicio parametroServicio =  (IParametroServicio) Configuracion.getBeanBatch("parametroServicio");
			Utilidad.verificarVersion(parametroServicio);
			String fecha = args[0];
			
			Traza.auditoria(ReporteAplicacionManualPendientesBatchRunner.class, "Se recibio la siguiente fecha como parametro: " + fecha);
			//Tarea 1
			procesarTareaReporteAplicacionManualPendientes(fecha);
			
		
		} catch (AccessControlException e) {
			
    		System.err.println(Mensajes.LISTENER_UNICA_INSTANCIA);
    		Traza.error(ReporteAplicacionManualPendientesBatchRunner.class, 
    				Mensajes.LISTENER_UNICA_INSTANCIA, e);
    		
    		System.exit(Constantes.SH_ERROR_INSTANCIA);
    		
    	} catch (ShivaExcepcion e) {
    		Traza.error(ReporteAplicacionManualPendientesBatchRunner.class, e.getMessage());
    		System.err.println(Utilidad.getStackTrace(e));
    		System.exit(Constantes.SH_ERROR);
    		
		} finally{
    		UnicaInstanciaFileKey.releaseLock();
    		Traza.auditoria(ReporteAplicacionManualPendientesBatchRunner.class, 
					"Se ha finalizado el Batch para el proceso Reporte de Aplicación Manual Pendientes");
    	}
		
		System.exit(0);
	}
	
	
	/**
	 * Genero Reporte de Aplicacion Manual en estado pendientes.
	 * 
	 * @author U587496 Guido.Settecerze
	 * @throws ShivaExcepcion
	 */
	private static void procesarTareaReporteAplicacionManualPendientes(String fecha) throws ShivaExcepcion {
		try {
			IReporteAplicacionManualPendientesServicio reporteAplicacionManualPendientesServicio = (IReporteAplicacionManualPendientesServicio) Configuracion.getBeanBatch("reporteAplicacionManualPendientesServicio");
			Traza.auditoria(ReporteAplicacionManualPendientesBatchRunner.class, "Se ha iniciado el proceso Reporte de Aplicación Manual Pendientes");
			
			reporteAplicacionManualPendientesServicio.generarArchivoReporteAplicacionManualPendientes(fecha);
			
			System.out.println("Se ha finalizado exitosamente el proceso Reporte de Aplicación Manual Pendientes");
    		Traza.auditoria(ReporteAplicacionManualPendientesBatchRunner.class, "---- Se ha finalizado exitosamente el proceso Reporte de Aplicación Manual Pendientes");
		
		} catch (Throwable e) {
			Traza.error(ReporteAplicacionManualPendientesBatchRunner.class, "Se ha producido un error en el proceso batch", e);
			Traza.advertencia(ReporteAplicacionManualPendientesBatchRunner.class, "---- Se ha finalizado con error en el proceso Reporte de Aplicación Manual Pendientes");
			
			throw new ShivaExcepcion(e);
		}
		
	}

}
