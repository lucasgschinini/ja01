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
import ar.com.telecom.shiva.negocio.servicios.IReporteValoresPorEmpresaDisponiblesServicio;

public class ReporteValoresPorEmpresaDisponiblesBatchRunner {

	/*
	 * Main
	 */
	
	public static void main(String[] args) {
		try {
			Traza.auditoria(ReporteValoresPorEmpresaDisponiblesBatchRunner.class, "Se ha iniciado el Batch Reporte de Valores Por Empresa");
			if (!UnicaInstanciaFileKey.setLock(Constantes.LOCK_KEY_REPORTE_HISTORICO_VALORES_OTRAS_EMPRESAS_DISPONIBLES_BATCH)) { //TODO CAMBIAR LOCK KEY
				throw new AccessControlException(Mensajes.LISTENER_UNICA_INSTANCIA);
			}
		
			//Verifico si la version es correcta
			IParametroServicio parametroServicio =  (IParametroServicio) Configuracion.getBeanBatch("parametroServicio");
			Utilidad.verificarVersion(parametroServicio);
			String fechaHasta = args[0];
			
			Traza.auditoria(ReporteValoresPorEmpresaDisponiblesBatchRunner.class, "Se recibio la siguiente fecha como parametro: " + fechaHasta);
			//Tarea 1
			procesarTareaReporteValoresPorEmpresaDisponibles(fechaHasta);
			
		
		} catch (AccessControlException e) {
			
    		System.err.println(Mensajes.LISTENER_UNICA_INSTANCIA);
    		Traza.error(ReporteValoresPorEmpresaDisponiblesBatchRunner.class, 
    				Mensajes.LISTENER_UNICA_INSTANCIA, e);
    		
    		System.exit(Constantes.SH_ERROR_INSTANCIA);
    		
    	} catch (ShivaExcepcion e) {
    		Traza.error(ReporteValoresPorEmpresaDisponiblesBatchRunner.class, e.getMessage());
    		System.err.println(Utilidad.getStackTrace(e));
    		System.exit(Constantes.SH_ERROR);
    		
		} finally{
    		UnicaInstanciaFileKey.releaseLock();
    		Traza.auditoria(ReporteValoresPorEmpresaDisponiblesBatchRunner.class, 
					"Se ha finalizado el Batch para el proceso Reporte de Valores Por Empresa Disponibles");
    	}
		
		System.exit(0);
	}
	
	
	/**
	 * Genero Reporte de Valores Por Empresa Disponibles
	 * 
	 * @author U587496 Guido.Settecerze
	 * @param fechaHasta 
	 * @throws ShivaExcepcion
	 */
	private static void procesarTareaReporteValoresPorEmpresaDisponibles(String fechaHasta) throws ShivaExcepcion {
		try {
			IReporteValoresPorEmpresaDisponiblesServicio reporteValoresPorEmpresaDisponiblesServicio = (IReporteValoresPorEmpresaDisponiblesServicio) Configuracion.getBeanBatch("reporteValoresPorEmpresaDisponiblesServicio");
			Traza.auditoria(ReporteValoresPorEmpresaDisponiblesBatchRunner.class, "Se ha iniciado el proceso Reporte de Valores Por Empresa Disponibles");
			
			reporteValoresPorEmpresaDisponiblesServicio.generarArchivoReporteValoresPorEmpresaDisponibles(fechaHasta);
			
			System.out.println("Se ha finalizado exitosamente el proceso Reporte de Valores Por Empresa Disponibles");
    		Traza.auditoria(ReporteValoresPorEmpresaDisponiblesBatchRunner.class, "---- Se ha finalizado exitosamente el proceso Reporte de Valores Por Empresa Disponibles");
		
		} catch (Throwable e) {
			Traza.error(ReporteValoresPorEmpresaDisponiblesBatchRunner.class, "Se ha producido un error en el proceso batch", e);
			Traza.advertencia(ReporteValoresPorEmpresaDisponiblesBatchRunner.class, "---- Se ha finalizado con error en el proceso Reporte de Valores Por Empresa Disponibles");
			
			throw new ShivaExcepcion(e);
		}
		
	}

}
