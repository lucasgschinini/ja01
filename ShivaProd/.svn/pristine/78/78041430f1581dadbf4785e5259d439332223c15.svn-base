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
import ar.com.telecom.shiva.negocio.servicios.IReporteRegistrosAVCPendienteConciliarServicio;

public class ReporteRegistrosAVCPendienteConciliarBatchRunner {

	/*
	 * Main
	 */
	
	public static void main(String[] args) {
		try {
			Traza.auditoria(ReporteRegistrosAVCPendienteConciliarBatchRunner.class, "Se ha iniciado el Batch Reporte de Registros AVC Pendientes de conciliar");
			if (!UnicaInstanciaFileKey.setLock(Constantes.LOCK_KEY_REPORTE_REGISTROS_AVC_PENDIENTE_CONCILIAR)) {
				throw new AccessControlException(Mensajes.LISTENER_UNICA_INSTANCIA);
			}
		
			//Verifico si la version es correcta
			IParametroServicio parametroServicio =  (IParametroServicio) Configuracion.getBeanBatch("parametroServicio");
			Utilidad.verificarVersion(parametroServicio);
			String fechaHasta = args[0];
			
			Traza.auditoria(ReporteRegistrosAVCPendienteConciliarBatchRunner.class, "Se recibio la siguiente fecha como parametro: " + fechaHasta);
			//Tarea 1
			procesarTareaReporteRegistrosAvcPendienteConciliar(fechaHasta);
			
		
		} catch (AccessControlException e) {
			
    		System.err.println(Mensajes.LISTENER_UNICA_INSTANCIA);
    		Traza.error(ReporteRegistrosAVCPendienteConciliarBatchRunner.class, 
    				Mensajes.LISTENER_UNICA_INSTANCIA, e);
    		
    		System.exit(Constantes.SH_ERROR_INSTANCIA);
    		
    	} catch (ShivaExcepcion e) {
    		Traza.error(ReporteRegistrosAVCPendienteConciliarBatchRunner.class, e.getMessage());
    		System.err.println(Utilidad.getStackTrace(e));
    		System.exit(Constantes.SH_ERROR);
    		
		} finally{
    		UnicaInstanciaFileKey.releaseLock();
    		Traza.auditoria(ReporteRegistrosAVCPendienteConciliarBatchRunner.class, 
					"Se ha finalizado el Batch Reporte de Registros AVC Pendientes de conciliar");
    	}
		
		System.exit(0);
	}
	
	/**
	 * Genero Reporte de Registros AVC Pendientes de conciliar
	 * @param fechaHasta 
	 * @throws ShivaExcepcion
	 */
	private static void procesarTareaReporteRegistrosAvcPendienteConciliar(String fechaHasta) throws ShivaExcepcion {
		try {
			IReporteRegistrosAVCPendienteConciliarServicio servicio = (IReporteRegistrosAVCPendienteConciliarServicio) Configuracion.getBeanBatch("reporteRegistrosAvcPendienteConciliarServicio");
			Traza.auditoria(ReporteRegistrosAVCPendienteConciliarBatchRunner.class, "Se ha iniciado el proceso Reporte de Registros AVC Pendientes de conciliar");
			servicio.generarArchivoReporteAvcPendienteConciliar(fechaHasta);
			
			System.out.println("Se ha finalizado exitosamente el proceso Reporte de Registros AVC Pendientes de conciliar");
    		Traza.auditoria(ReporteRegistrosAVCPendienteConciliarBatchRunner.class, "---- Se ha finalizado exitosamente el proceso Reporte de Registros AVC Pendientes de conciliar");
		
		} catch (Throwable e) {
			Traza.error(ReporteRegistrosAVCPendienteConciliarBatchRunner.class, "Se ha producido un error en el proceso batch", e);
			Traza.advertencia(ReporteRegistrosAVCPendienteConciliarBatchRunner.class, "---- Se ha finalizado con error en el proceso Reporte de Registros AVC Pendientes de conciliar");
			
			throw new ShivaExcepcion(e);
		}
		
	}

}
