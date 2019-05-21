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
import ar.com.telecom.shiva.base.utils.ControlArchivo;
import ar.com.telecom.shiva.base.utils.UnicaInstanciaFileKey;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.base.utils.singleton.ValidarAuxiliarSingleton;
import ar.com.telecom.shiva.negocio.servicios.IOperacionMasivaServicio;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;

public class OperacionesMasivasLecturaArchivoMicBatchRunner {

	private OperacionesMasivasLecturaArchivoMicBatchRunner() { 
	}

	/**
	 * Main
	 */
	public static void main(String[] args) {
		boolean error = false;
		int salida = 0;
		try {
			Traza.auditoria(OperacionesMasivasLecturaArchivoMicBatchRunner.class, 
						"Se ha iniciado el Batch para operaciones masivas lectura Mic");
			if (!UnicaInstanciaFileKey.setLock(Constantes.LOCK_KEY_OPERACIONES_MASIVAS_BATCH_LECTURA_MIC)) {
				throw new AccessControlException(Mensajes.LISTENER_UNICA_INSTANCIA);
			}
			Traza.auditoria(OperacionesMasivasLecturaArchivoMicBatchRunner.class, "1.- ===================================================================================");
			
			//Verifico si la version es correcta
			IParametroServicio parametroServicio =  (IParametroServicio) Configuracion.getBeanBatch("parametroServicio");
			Utilidad.verificarVersion(parametroServicio);
			
			//Tarea 1
			error = procesarTareaOperacionesMasivas();
			if (!error) {
				salida = 2;
			}
			Traza.auditoria(OperacionesMasivasLecturaArchivoMicBatchRunner.class, "FIN ===================================================================================");
		} catch (AccessControlException e) {
			System.err.println(Mensajes.LISTENER_UNICA_INSTANCIA);
			Traza.error(OperacionesMasivasLecturaArchivoMicBatchRunner.class, 
					Mensajes.LISTENER_UNICA_INSTANCIA, e);
			Traza.auditoria(OperacionesMasivasLecturaArchivoMicBatchRunner.class, "===== Salida: " + Constantes.SH_ERROR_INSTANCIA);
			System.exit(Constantes.SH_ERROR_INSTANCIA);
			
		} catch (ShivaExcepcion e) {
			Traza.error(ProcesarArchivosAVCBatchRunner.class, e.getMessage());
			System.err.println(Utilidad.getStackTrace(e));
			Traza.auditoria(OperacionesMasivasLecturaArchivoMicBatchRunner.class, "===== Salida: " + Constantes.SH_ERROR);
			System.exit(Constantes.SH_ERROR);
			
		} finally {
			UnicaInstanciaFileKey.releaseLock();
			Traza.auditoria(OperacionesMasivasLecturaArchivoMicBatchRunner.class, "FIN PROCESO============================================================================");
			Traza.auditoria(OperacionesMasivasLecturaArchivoMicBatchRunner.class, 
					"Se ha finalizado el Batch para OPERACIONES MASIVAS lectura mic");
		}
		Traza.auditoria(OperacionesMasivasLecturaArchivoMicBatchRunner.class, "===== Salida: " + salida);
		System.exit(salida);
	}
	
	/**
	 * Se encarga de levantar los archivos de operaciones masivas y procesarlos uno a la vez.
	 * 
	 * @throws ShivaExcepcion 
	 */
	private static boolean  procesarTareaOperacionesMasivas() throws ShivaExcepcion {
		boolean error = true;
		try {
			IOperacionMasivaServicio operacionMasivaServicio =  (IOperacionMasivaServicio) Configuracion.getBeanBatch("operacionMasivaServicio");
			Traza.auditoria(OperacionesMasivasLecturaArchivoMicBatchRunner.class, "- Se ha comenzado a procesar los archivos mic del dia");
			error = procesarArchivosInterfazMicSalida();
			Traza.auditoria(OperacionesMasivasLecturaArchivoMicBatchRunner.class, "Se ha actualizan los contadores de las operacion masiva");
			operacionMasivaServicio.actualizarContadoresEnOperacionesMasivas();
			Traza.auditoria(OperacionesMasivasLecturaArchivoMicBatchRunner.class, "Se ha creado los cobros de las operacion masiva");
			
			operacionMasivaServicio.actualizarEstadoOperacionesMasivasSinRegistrosEnProceso();
			return error;
		} catch (Throwable e) {
			Traza.error(OperacionesMasivasLecturaArchivoMicBatchRunner.class, "Se ha producido un error en el proceso batch", e);
			Traza.advertencia(OperacionesMasivasLecturaArchivoMicBatchRunner.class, "---- Se ha finalizado con error en el proceso de Operaciones Masivas lectura mic");
			
			throw new ShivaExcepcion(e);
		}
	}

	private static boolean procesarArchivosInterfazMicSalida() throws NegocioExcepcion, ShivaExcepcion {
		try {
			Traza.auditoria(ImputacionCobrosBatchRunner.class, "- Se ha iniciado la busqueda de archivos de Salida Operacion Masiva Mic ");
			int contadorError = 0;
			try {
				IOperacionMasivaServicio operacionMasivaServicio =  (IOperacionMasivaServicio) Configuracion.getBeanBatch("operacionMasivaServicio");
				// Levanto el archivo del dia actual
				FilenameFilter filtroArchivo = operacionMasivaServicio.filtrarArchivosEntradaDeOperacionMasivaMic();
				File directorio = ControlArchivo.getDirectorio(Propiedades.SHIVA_PROPIEDADES.getString("batch.directorio.operacionesMasivas.salida"));
				if (ControlArchivo.existeArchivosEnUnDirectorio(directorio, filtroArchivo)) {
					// Ordeno las lineas
					List<File> archivos = ControlArchivo.listaArchivosEnUnDirectorio(directorio, filtroArchivo);
					Traza.auditoria(OperacionesMasivasLecturaArchivoMicBatchRunner.class, "- Se han encontrado " + (archivos != null ? archivos.size() : 0) + " archivos.");
					for (File archivo : archivos) {
						try {
							ValidarAuxiliarSingleton.getInstance().reIniciar();
							ValidarAuxiliarSingleton.getInstance().setCurrentfileName(archivo.getName());
							//Se encarga de procesar un archivo de reversion de cobros
							 operacionMasivaServicio.procesarArchivoInterfazMicSalida(archivo);

							ControlArchivo.moverArchivo(archivo.getName(),Propiedades.SHIVA_PROPIEDADES.getString("batch.directorio.operacionesMasivas.salida"), 
									Propiedades.SHIVA_PROPIEDADES.getString("batch.directorio.operacionesMasivas.historico"));
							System.out.println("Se ha procesado el archivo " + archivo.getName() + " correctamente.");
							Traza.auditoria(OperacionesMasivasLecturaArchivoMicBatchRunner.class, "Se ha procesado el archivo " + archivo.getName() + " correctamente.");

						} catch (Exception e) {
							System.err.println("Se ha producido un error al procesar el archivo " + archivo.getName() + " " + e.getMessage());
							Traza.error(OperacionesMasivasLecturaArchivoMicBatchRunner.class, "Se ha producido un error al procesar el archivo " + archivo.getName(), e);
							contadorError++;
						}
					}
					if (contadorError > 0) {
						return false;
					}
					return true;
				} else {
					System.out.println("No se ha encontrado ningún archivo con el formato [Nombre Aplicación].OperacionesMasivas.MIC.Salida.[fecha]");
					Traza.advertencia(OperacionesMasivasLecturaArchivoMicBatchRunner.class, "No se ha encontrado ningún archivo con el formato [Nombre Aplicación].OperacionesMasivas.MIC.Salida.[fecha]");
					return false;
				}
			} catch (Exception e) {
				throw new NegocioExcepcion(e);
			}
		} catch (Throwable e) {
			Traza.error(OperacionesMasivasLecturaArchivoMicBatchRunner.class, "Se ha producido un error en el proceso batch", e);
			Traza.advertencia(OperacionesMasivasLecturaArchivoMicBatchRunner.class, "---- Se ha finalizado con error en el proceso de lectura de operacion masivas");
			
			throw new ShivaExcepcion(e);
		}
	}

}
