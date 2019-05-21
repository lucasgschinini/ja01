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
import ar.com.telecom.shiva.negocio.servicios.IReportePerfilesAuditoriaServicio;

public class ReportePerfilesAuditoriaBatchRunner {

	private ReportePerfilesAuditoriaBatchRunner() {
	}

	/**
	 * Main
	 */
	public static void main(String[] args) {
		int exit = 0;
		try {
			Traza.auditoria(ReportePerfilesAuditoriaBatchRunner.class, 
						"Se ha lanzado el Batch para procesar el archivo de entrada de tuId");
			if (!UnicaInstanciaFileKey.setLock(Constantes.LOCK_KEY_REPORTE_PERFILES_AUDITORIA)) {
    	    	throw new AccessControlException(Mensajes.LISTENER_UNICA_INSTANCIA);
    	    }
    	    
			//Verifico si la version es correcta
			IParametroServicio parametroServicio =  (IParametroServicio) Configuracion.getBeanBatch("parametroServicio");
			Utilidad.verificarVersion(parametroServicio);
			String[] parametro = args;
			
			exit = procesarReportePerfilesAuditoria(parametro);
			
		} catch (AccessControlException e) {
			System.err.println(Mensajes.LISTENER_UNICA_INSTANCIA);
			Traza.error(ReportePerfilesAuditoriaBatchRunner.class, 
    				Mensajes.LISTENER_UNICA_INSTANCIA, e);
			
			System.exit(Constantes.SH_ERROR_INSTANCIA);
			
		} catch (ShivaExcepcion e) {
			System.err.println(Utilidad.getStackTrace(e));
			Traza.error(ReportePerfilesAuditoriaBatchRunner.class, 
    				"Se ha producido un error al procesar el archivo de entrada", e);
			System.exit(Constantes.SH_ERROR);
			
		} catch (Exception e) {
			System.err.println(Utilidad.getStackTrace(e));
			Traza.error(ReportePerfilesAuditoriaBatchRunner.class, 
    				"Se ha producido un error desconocido", e);
			System.exit(Constantes.SH_ERROR);
		}
		finally{
			UnicaInstanciaFileKey.releaseLock();
			Traza.auditoria(ReportePerfilesAuditoriaBatchRunner.class, 
					"Se ha finalizado el Batch para procesar el archivo de entrada de TuID");
		}

		System.exit(exit);
	}
	
	
	private static int procesarReportePerfilesAuditoria(String[] parametro) throws ShivaExcepcion {
		try{
			Traza.auditoria(ReportePerfilesAuditoriaBatchRunner.class, "Se inicia el Batch para procesar el reporte de perfiles");
			IReportePerfilesAuditoriaServicio reportePefilesAuditoriaServicio = (IReportePerfilesAuditoriaServicio) Configuracion.getBeanBatch("reportePerfilesAuditoriaServicio");
			return reportePefilesAuditoriaServicio.generarReporte(parametro);
		} catch (Throwable e) {
			throw new ShivaExcepcion(e);
		}
	}

}