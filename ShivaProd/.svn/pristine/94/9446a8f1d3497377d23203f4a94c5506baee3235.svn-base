package ar.com.telecom.shiva.batch;

import java.io.File;
import java.io.FilenameFilter;
import java.security.AccessControlException;
import java.util.List;

import ar.com.telecom.shiva.base.constantes.Configuracion;
import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Mensajes;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.ValidacionExcepcion;
import ar.com.telecom.shiva.base.utils.ControlArchivo;
import ar.com.telecom.shiva.base.utils.UnicaInstanciaFileKey;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.servicios.IDescobroOrigenCobradorServicio;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;

public class ReversionCobrosBatchRunner {

	private ReversionCobrosBatchRunner(){ 
	}
	
	/**
	 * Main
	 */
	public static void main(String[] args) {
		try {
			Traza.auditoria(ReversionCobrosBatchRunner.class, 
						"Se ha iniciado el Batch para la reversion de cobros");
			if (!UnicaInstanciaFileKey.setLock(Constantes.LOCK_KEY_REVERSION_BATCH)) {
    	    	throw new AccessControlException(Mensajes.LISTENER_UNICA_INSTANCIA);
    	    }
    	    
			//Verifico si la version es correcta
			IParametroServicio parametroServicio =  (IParametroServicio) Configuracion.getBeanBatch("parametroServicio");
			Utilidad.verificarVersion(parametroServicio);
			
			//Tarea 1
			if (!procesarTareaReversionCobros()){
				System.exit(Constantes.SH_PROCESAMIENTO_PARCIAL);
			}
			
		} catch (AccessControlException e) {
			System.err.println(Mensajes.LISTENER_UNICA_INSTANCIA);
			Traza.error(ReversionCobrosBatchRunner.class, 
					Mensajes.LISTENER_UNICA_INSTANCIA, e);
			
			System.exit(Constantes.SH_ERROR_INSTANCIA);
			
		} catch (ShivaExcepcion e) {
			Traza.error(ReversionCobrosBatchRunner.class, e.getMessage());
			System.err.println(Utilidad.getStackTrace(e));
			System.exit(Constantes.SH_ERROR);
			
		} finally{
			UnicaInstanciaFileKey.releaseLock();
			
			Traza.auditoria(ReversionCobrosBatchRunner.class, 
					"Se ha finalizado el Batch para la reversion de cobros");
		}
		
		System.exit(0);
	}
	
	/**
	 * Se encarga de levantar los archivos de reversion y procesarlos uno a la vez.
	 * 
	 * @throws ShivaExcepcion 
	 */
	private static boolean procesarTareaReversionCobros() throws ShivaExcepcion {
		try {
			IDescobroOrigenCobradorServicio descobroServicio = (IDescobroOrigenCobradorServicio) Configuracion.getBeanBatch("descobroOrigenCobradorServicio");
			
			boolean huboErrorArchivo = false;
			Exception excepcion = null;
			boolean resultadoProcesamiento = true;
			Traza.auditoria(ReversionCobrosBatchRunner.class, "Se ha iniciado el proceso de Reversion de Cobros");
			
			try {
				// Levanto el archivo del dia actual
				FilenameFilter filtroArchivo = filtrarArchivosDeReversion();
				File directorio = ControlArchivo.getDirectorio(Propiedades.SHIVA_PROPIEDADES.getString("batch.directorio.archivosReversiones"));
				if (ControlArchivo.existeArchivosEnUnDirectorio(directorio,filtroArchivo)) {
					
					// Ordeno las lineas
					List<File> archivos = ControlArchivo.listaArchivosEnUnDirectorio(directorio, filtroArchivo);
					for (File archivo : archivos) {
						try {
							//Se encarga de procesar un archivo de reversion de cobros
							if (!descobroServicio.procesarArchivoReversion(archivo)) {
								if(resultadoProcesamiento){
									resultadoProcesamiento = false;
								}
							}
							
							System.out.println("Se ha procesado el archivo " + archivo.getName() + " correctamente.");
							Traza.auditoria(ReversionCobrosBatchRunner.class, "Se ha procesado el archivo " + archivo.getName() + " correctamente.");
							
						} catch (Exception e) {
							huboErrorArchivo = true;
							excepcion = e;
							
							System.err.println("Se ha producido un error al procesar el archivo " + archivo.getName());
							Traza.error(ReversionCobrosBatchRunner.class, "Se ha producido un error al procesar el archivo " + archivo.getName(), e);
						}
					}
				} else {
					System.out.println("No se ha encontrado ningún archivo con el formato [Nombre Aplicación].ReversionOperaciones.[fecha].[cobrador Origen]");
					Traza.advertencia(ReversionCobrosBatchRunner.class, "No se ha encontrado ningún archivo con el formato [Nombre Aplicación].ReversionOperaciones.[fecha].[cobrador Origen]");
				}
			} catch (Exception e) {
				throw new NegocioExcepcion(e);
			}
			
			if (huboErrorArchivo) {
				throw new ShivaExcepcion(excepcion.getMessage(),excepcion);
			} else {
				System.out.println("Se ha finalizado exitosamente el proceso de Reversion de Cobros");
				Traza.auditoria(ReversionCobrosBatchRunner.class, "---- Se ha finalizado exitosamente el proceso de Reversion de Cobros");
			}
		
			return resultadoProcesamiento;
		} catch (Throwable e) {
			Traza.error(ReversionCobrosBatchRunner.class, "Se ha producido un error en el proceso batch", e);
			Traza.advertencia(ReversionCobrosBatchRunner.class, "---- Se ha finalizado con error en el proceso de Reversion de Cobros");
			
			throw new ShivaExcepcion(e);
		}
	}
	
	/**
	 * Filtro del formato de archivos de reversion en un directorio.
	 * El formato es [Nombre Aplicación].ReversionOperaciones.[fecha].[cobrador Origen].txt
	 * @param filtro
	 * @return FilenameFilter
	 */
	private static FilenameFilter filtrarArchivosDeReversion() {
		
		return new FilenameFilter() {
			public boolean accept(File dir, String name) {
				try {
					if (Validaciones.esNombreArchivoReversion(name)) {
						return true;
					}
				} catch (ValidacionExcepcion e) {
					return false;
				}
				return false;
			}
		};	
	}
}
