package ar.com.telecom.shiva.negocio.servicios.impl;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.ArchivoCotizacionesSapNoExisteExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.ArchivoCotizacionesSapNombreIncorrectoExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.ArchivoCotizacionesSapVacioExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.servicios.ICotizacionBatchServicio;
import ar.com.telecom.shiva.negocio.servicios.ICotizacionServicio;

/**
 * 
 * @author u564030
 *
 */
public class CotizacionBatchServicioImpl extends Servicio implements ICotizacionBatchServicio {
	
	private @Autowired ICotizacionServicio cotizacionServicio;
	
	@Override
	public int procesarArchivoCotizacionesSap() throws NegocioExcepcion {

		String pathOrigen = Propiedades.SHIVA_PROPIEDADES.getString("batch.directorio.cotizaciones.Sap").trim();
		File directorio = new File(pathOrigen);
		int exit = 0;
		
		// se aplica filtro a la busqueda en el directorio, para que solo traiga archivos .txt
		File[] listaArchivos = directorio.listFiles(new FileFilter() {
		    public boolean accept(File pathname) {
		        String name = pathname.getName().toLowerCase();
		        return name.endsWith(".txt") && pathname.isFile();
		    }
		});
		
		if (listaArchivos.length != 0) {
			Arrays.sort(listaArchivos);

		} else {
			String msgError = Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("procesoBatch.cotizacionesSap.archivoNoExiste"), pathOrigen);
			Traza.error(this.getClass(), msgError);
			throw new ArchivoCotizacionesSapNoExisteExcepcion(msgError);
		}

		// procesa todos los archivos q haya en la carpeta
		for (File file : listaArchivos) {
			try {
				exit = cotizacionServicio.procesarContenidoArchivoCotizacionesSap(file);
				file.renameTo(new File(file.getParent() + File.separator + Constantes.CARPETA_HISTORICO + File.separator + file.getName()));
				
			} catch(ArchivoCotizacionesSapVacioExcepcion e) {
				file.renameTo(new File(file.getParent() + File.separator + Constantes.CARPETA_HISTORICO + File.separator + file.getName()));
				exit = Constantes.PROCESO_BATCH_RETORNO_EXIT_WARNING;

			} catch(ArchivoCotizacionesSapNombreIncorrectoExcepcion e) {
				exit = Constantes.PROCESO_BATCH_RETORNO_EXIT_WARNING;
			}
		}
		
		return exit;
	} 
}
