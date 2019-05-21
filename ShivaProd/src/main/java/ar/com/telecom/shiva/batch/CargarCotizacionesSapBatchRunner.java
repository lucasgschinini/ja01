package ar.com.telecom.shiva.batch;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.AccessControlException;

import ar.com.telecom.shiva.base.constantes.Configuracion;
import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Mensajes;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.ArchivoCotizacionesSapNoExisteExcepcion;
import ar.com.telecom.shiva.base.utils.UnicaInstanciaFileKey;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.servicios.ICotizacionBatchServicio;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;

/**
 * 
 * @author 
 * 
 */
public class CargarCotizacionesSapBatchRunner {
	
	
	public static void main(String[] args) {
		
		int exit = 0;
		
		try {
 			Traza.auditoria(CargarCotizacionesSapBatchRunner.class, "INICIO - BATCH CARGAR COTIZACIONES SAP");
			if (!UnicaInstanciaFileKey.setLock(Constantes.LOCK_KEY_CARGAR_COTIZACIONES_SAP_BATCH))
				throw new AccessControlException(Mensajes.LISTENER_UNICA_INSTANCIA);

			// Verifico si la version es correcta
			IParametroServicio parametroServicio =  (IParametroServicio) Configuracion.getBeanBatch("parametroServicio");
			Utilidad.verificarVersion(parametroServicio);
			
			// PROCESO
			exit = procesarArchivoCotizacionesSap();
		
		} catch (AccessControlException e) {
			Traza.error(CargarCotizacionesSapBatchRunner.class, Mensajes.LISTENER_UNICA_INSTANCIA, e);
			System.err.println(Mensajes.LISTENER_UNICA_INSTANCIA);
			System.exit(Constantes.SH_ERROR_INSTANCIA);

		} catch (ShivaExcepcion e) {
			Traza.error(CargarCotizacionesSapBatchRunner.class, e.getMessage());
			System.err.println(Utilidad.getStackTrace(e));
			System.exit(Constantes.SH_ERROR);

		} catch (ArchivoCotizacionesSapNoExisteExcepcion e) {
			System.err.println(Utilidad.getStackTrace(e));
			System.exit(Constantes.SH_PROCESAMIENTO_PARCIAL);

		} catch (NegocioExcepcion e) {
			Traza.error(CargarCotizacionesSapBatchRunner.class, e.getMessage());
			System.err.println(Utilidad.getStackTrace(e));
			System.exit(Constantes.SH_ERROR);

		} finally {
			UnicaInstanciaFileKey.releaseLock();
			Traza.auditoria(CargarCotizacionesSapBatchRunner.class, "Se ha finalizado el batch para cargar cotizaciones SAP");
		}
		
		System.exit(exit);
	}
	
	
	/**
	 * 
	 * @return
	 * @throws ShivaExcepcion
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws NegocioExcepcion
	 */
	private static int procesarArchivoCotizacionesSap() throws NegocioExcepcion {
		
		ICotizacionBatchServicio servicio = (ICotizacionBatchServicio) Configuracion.getBeanBatch("cotizacionBatchServicio");
		return servicio.procesarArchivoCotizacionesSap();
	}
}
