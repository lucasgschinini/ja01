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
import ar.com.telecom.shiva.negocio.servicios.IReporteValoresPorEmpresaServicio;

public class ReporteValoresPorEmpresaBatchRunner {

	/*
	 * Main
	 */
	
	public static void main(String[] args) {
		try {
			Traza.auditoria(ReporteValoresPorEmpresaBatchRunner.class, "Se ha iniciado el Batch Reporte de Valores Por Empresa");
			if (!UnicaInstanciaFileKey.setLock(Constantes.LOCK_KEY_REPORTE_HISTORICO_VALORES_OTRAS_EMPRESAS_BATCH)) { //TODO CAMBIAR LOCK KEY
				throw new AccessControlException(Mensajes.LISTENER_UNICA_INSTANCIA);
			}
		
			//Verifico si la version es correcta
			IParametroServicio parametroServicio =  (IParametroServicio) Configuracion.getBeanBatch("parametroServicio");
			Utilidad.verificarVersion(parametroServicio);
			String fechaHasta = args[0];
			
			Traza.auditoria(ReporteValoresPorEmpresaBatchRunner.class, "Se recibio la siguiente fecha como parametro: " + fechaHasta);
			//Tarea 1
			procesarTareaReporteValoresPorEmpresa(fechaHasta);
			
		
		} catch (AccessControlException e) {
			
    		System.err.println(Mensajes.LISTENER_UNICA_INSTANCIA);
    		Traza.error(ReporteValoresPorEmpresaBatchRunner.class, 
    				Mensajes.LISTENER_UNICA_INSTANCIA, e);
    		
    		System.exit(Constantes.SH_ERROR_INSTANCIA);
    		
    	} catch (ShivaExcepcion e) {
    		Traza.error(ReporteValoresPorEmpresaBatchRunner.class, e.getMessage());
    		System.err.println(Utilidad.getStackTrace(e));
    		System.exit(Constantes.SH_ERROR);
    		
		} finally{
    		UnicaInstanciaFileKey.releaseLock();
    		Traza.auditoria(ReporteValoresPorEmpresaBatchRunner.class, 
					"Se ha finalizado el Batch para el proceso Reporte de Valores Por Empresa");
    	}
		
		System.exit(0);
	}
	
	/**
	 * Genero Reporte de Valores Por Empresa
	 * @param fechaHasta 
	 * @throws ShivaExcepcion
	 */
	private static void procesarTareaReporteValoresPorEmpresa(String fechaHasta) throws ShivaExcepcion {
		try {
			IReporteValoresPorEmpresaServicio reporteValoresPorEmpresaServicio = (IReporteValoresPorEmpresaServicio) Configuracion.getBeanBatch("reporteValoresPorEmpresaServicio");
			Traza.auditoria(ReporteValoresPorEmpresaBatchRunner.class, "Se ha iniciado el proceso Reporte de Valores Por Empresa");
			
			reporteValoresPorEmpresaServicio.generarArchivoReporteValoresPorEmpresa(fechaHasta);
			
			System.out.println("Se ha finalizado exitosamente el proceso Reporte de Valores Por Empresa");
    		Traza.auditoria(ReporteValoresPorEmpresaBatchRunner.class, "---- Se ha finalizado exitosamente el proceso Reporte de Valores Por Empresa");
		
		} catch (Throwable e) {
			Traza.error(ReporteValoresPorEmpresaBatchRunner.class, "Se ha producido un error en el proceso batch", e);
			Traza.advertencia(ReporteValoresPorEmpresaBatchRunner.class, "---- Se ha finalizado con error en el proceso Reporte de Valores Por Empresa");
			
			throw new ShivaExcepcion(e);
		}
		
	}

}
