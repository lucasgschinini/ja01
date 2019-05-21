package ar.com.telecom.shiva.batch.springbatch.launcher;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.AccessControlException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Mensajes;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.utils.ControlArchivo;
import ar.com.telecom.shiva.base.utils.UnicaInstanciaFileKey;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.batch.springbatch.configuration.SpringBatchConfiguracion;
import ar.com.telecom.shiva.negocio.servicios.IAcuerdoServicio;
import ar.com.telecom.shiva.negocio.servicios.IArchivoAVCSoporteServicio;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;
import ar.com.telecom.shiva.persistencia.modelo.ShvAvcArchivoAvc;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamAcuerdo;

public class ProcesarArchivosAvcDinamicoBatchRunner {

	private ProcesarArchivosAvcDinamicoBatchRunner(){ 
	}
	
	/**
	 * Main
	 */
	
	static int contadorArchivosAProcesar = 0;


	static int contadorArchivosProcesados = 0;
	static int contadorArchivosErroneos = 0;
	static int contadorArchivosOK = 0;
	static List<String> listaNumerosDeAcuerdoJobs = new ArrayList<String>();
    static Map<String, String> mapNumerosDeAcuerdoJobs = new HashMap<String, String>();
	static List<String> listaNumerosDeAcuerdoCSV = new ArrayList<String>();
	static List<String> listaArchivosValidosAProcesar = new ArrayList<String>();
	static String pathOrigen = Propiedades.SHIVA_PROPIEDADES.getString("batch.directorio.archivosRegistrosAvcDinamico");
    static Map<String, Integer> mapSystemExits = new HashMap<String, Integer>();

	
	public static void main(String[] args) {
		boolean resultado = true;
		
		try {
			if (!UnicaInstanciaFileKey.setLock(Constantes.LOCK_KEY_PROCESAR_ARCHIVO_AVC_DINAMICO_BATCH)) {
    	    	throw new AccessControlException(Mensajes.LISTENER_UNICA_INSTANCIA);
    	    }

			//ControlVariablesSingleton.permitirTraceoSQL();
			
			//Tarea 1
			resultado = procesarTareaProcesarArchivosAvcDinamico();
			
		} catch (AccessControlException e) {
			
    		System.err.println(Mensajes.LISTENER_UNICA_INSTANCIA);
    		Traza.error(ProcesarArchivosAvcDinamicoBatchRunner.class, 
    				Mensajes.LISTENER_UNICA_INSTANCIA, e); 
    		
    		System.exit(Constantes.SH_ERROR_INSTANCIA);
    	
    	} catch (ShivaExcepcion e) {
    		Traza.error(ProcesarArchivosAvcDinamicoBatchRunner.class, e.getMessage());
    		System.err.println(Utilidad.getStackTrace(e));
    		System.exit(Constantes.SH_ERROR);
    		
		} finally{
    		UnicaInstanciaFileKey.releaseLock();
    		Traza.auditoria(ProcesarArchivosAvcDinamicoBatchRunner.class, 
					"Se ha finalizado el Batch para el proceso de archivos Avc");
    	}
		
		if (resultado) {
			System.out.println("Se ha finalizado exitosamente, el Batch para el proceso de archivos Avc");
			Traza.auditoria(ProcesarArchivosAvcDinamicoBatchRunner.class, 
					"---- Se ha finalizado exitosamente, el Batch para el proceso de archivos Avc");
			
			System.exit(0);
		} else {
			
			System.out.println("Se ha finalizado con algun error, el Batch para el proceso de archivos Avc");
			Traza.advertencia(ProcesarArchivosAvcDinamicoBatchRunner.class, 
					"---- Se ha finalizado con algun error, el Batch para el proceso de archivos Avc");
			
			System.exit(Constantes.SH_ERROR);
		}
	}
	
	/**
	 * Se encarga de levantar los archivos de reversion y procesarlos uno a la vez.
	 * 
	 * @throws ShivaExcepcion
	 */
	private static boolean procesarTareaProcesarArchivosAvcDinamico() throws ShivaExcepcion {
		boolean salida = true;
		try {
			
			Traza.auditoria(ProcesarArchivosAvcDinamicoBatchRunner.class, "");
			Traza.auditoria(ProcesarArchivosAvcDinamicoBatchRunner.class, "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			Traza.auditoria(ProcesarArchivosAvcDinamicoBatchRunner.class, "Se ha iniciado la ejecucion del procesamiento de archivos Avc Dinamico");
					
			IParametroServicio parametroServicio =  (IParametroServicio) SpringBatchConfiguracion.getBeanBatch("parametroServicio");
			Utilidad.verificarVersion(parametroServicio);

			IArchivoAVCSoporteServicio archivoAVCSoporteServicio =  (IArchivoAVCSoporteServicio) SpringBatchConfiguracion.getBeanBatch("archivoAVCSoporteServicio");
			IAcuerdoServicio acuerdoServicio = (IAcuerdoServicio) SpringBatchConfiguracion.getBeanBatch("acuerdoServicio");

			//============ FEDE ============//
			List<String> listaJobs = listarJobs();	
			List<String> archivosCSV = listarCSV();	
			
			Traza.auditoria(ProcesarArchivosAvcDinamicoBatchRunner.class, "");
			Traza.auditoria(ProcesarArchivosAvcDinamicoBatchRunner.class, "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			Traza.auditoria(ProcesarArchivosAvcDinamicoBatchRunner.class, "Procedo a validar nomenclatura y acuerdo de los archivos de configuracion encontrados.");
			for (String job : listaJobs) {			
				validarJob(job, acuerdoServicio);
			}

			Traza.auditoria(ProcesarArchivosAvcDinamicoBatchRunner.class, "");
			Traza.auditoria(ProcesarArchivosAvcDinamicoBatchRunner.class, "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			Traza.auditoria(ProcesarArchivosAvcDinamicoBatchRunner.class, "Procedo a validar nomenclatura de los archivos a procesar encontrados.");
			// Cuento los archivos, los valido y los agrego a una lista
			for (String csv : archivosCSV) {
				validarArchivoCSV(csv, archivoAVCSoporteServicio);
			}

			Traza.auditoria(ProcesarArchivosAvcDinamicoBatchRunner.class, "");
			Traza.auditoria(ProcesarArchivosAvcDinamicoBatchRunner.class, "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			Traza.auditoria(ProcesarArchivosAvcDinamicoBatchRunner.class, "Listado de archivos (acuerdos) validos configurados en el directorio 'configuracion'.");
			for (String elemento : listaNumerosDeAcuerdoJobs) {
				Traza.auditoria(ProcesarArchivosAvcDinamicoBatchRunner.class, elemento);
			}
			
			Traza.auditoria(ProcesarArchivosAvcDinamicoBatchRunner.class, "");
			Traza.auditoria(ProcesarArchivosAvcDinamicoBatchRunner.class, "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			Traza.auditoria(ProcesarArchivosAvcDinamicoBatchRunner.class, "Listado de archivos a procesar validos en el directorio 'entrada'.");
			for (String elemento : listaArchivosValidosAProcesar) {
				Traza.auditoria(ProcesarArchivosAvcDinamicoBatchRunner.class, elemento);
			}			
			
			contadorArchivosAProcesar = listaArchivosValidosAProcesar.size();

			// VALIDACION FINAL DE LISTAS DE JOB Y CSV.
			if (listaNumerosDeAcuerdoCSV.isEmpty()) {
				Traza.auditoria(ProcesarArchivosAvcDinamicoBatchRunner.class, "");
				Traza.auditoria(ProcesarArchivosAvcDinamicoBatchRunner.class, "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				Traza.auditoria(ProcesarArchivosAvcDinamicoBatchRunner.class, "No se encontro ningun CSV valido en el directorio para procesar. Finalizando ejecucion.");
				salida = false;
				System.exit(0);
			}
			
			if (listaNumerosDeAcuerdoCSV.isEmpty() && listaNumerosDeAcuerdoJobs.isEmpty()) {
				Traza.auditoria(ProcesarArchivosAvcDinamicoBatchRunner.class, "");
				Traza.auditoria(ProcesarArchivosAvcDinamicoBatchRunner.class, "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				Traza.auditoria(ProcesarArchivosAvcDinamicoBatchRunner.class, "No se encontro ningun CSV ni jobs validos en los directorios. Finalizando ejecucion.");
				salida = false;
				System.exit(0);
			}

			if (listaNumerosDeAcuerdoJobs.isEmpty()) {
				Traza.auditoria(ProcesarArchivosAvcDinamicoBatchRunner.class, "");
				Traza.auditoria(ProcesarArchivosAvcDinamicoBatchRunner.class, "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				Traza.auditoria(ProcesarArchivosAvcDinamicoBatchRunner.class, "No se encontro ningun archivo de configuracion para procesar AVCs. Finalizando ejecucion.");
				salida = false;
				System.exit(0);
			}
			
			if (!Collections.disjoint(listaNumerosDeAcuerdoJobs, listaNumerosDeAcuerdoCSV)) {
				Set<String> ad = new HashSet<String>(listaNumerosDeAcuerdoCSV);
				Set<String> bd = new HashSet<String>(listaNumerosDeAcuerdoJobs);
				ad.removeAll(bd);
									
				Traza.auditoria(ProcesarArchivosAvcDinamicoBatchRunner.class, "");
				Traza.auditoria(ProcesarArchivosAvcDinamicoBatchRunner.class, "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				Traza.auditoria(ProcesarArchivosAvcDinamicoBatchRunner.class, "Se encontro al menos un archivo .CSV que no tiene job asociado para procesarlo.");
				Traza.auditoria(ProcesarArchivosAvcDinamicoBatchRunner.class, "Nro de acuerdo sin job asociado:" + ad);
				
				// Por cada CSV sin job asociado que encuentro, le seteo System Exit 1.
				for (String csvSinJob : ad) {
					mapSystemExits.put(csvSinJob, 1);
				}
				
				salida = false;
			}
			
			if (listaArchivosValidosAProcesar.isEmpty()) {
				Traza.auditoria(ProcesarArchivosAvcDinamicoBatchRunner.class, "");
				Traza.auditoria(ProcesarArchivosAvcDinamicoBatchRunner.class, "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				Traza.auditoria(ProcesarArchivosAvcDinamicoBatchRunner.class, "No se encontro ningun archivo valido para procesar. Finalizando ejecucion.");
				salida = false;
				System.exit(0);
			}

			Traza.auditoria(ProcesarArchivosAvcDinamicoBatchRunner.class, "");
			Traza.auditoria(ProcesarArchivosAvcDinamicoBatchRunner.class, "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			Traza.auditoria(ProcesarArchivosAvcDinamicoBatchRunner.class, "Comienzo de ejecucion de los Jobs");
			
			String pathJobs = Propiedades.SHIVA_PROPIEDADES.getString("batch.directorio.archivosRegistrosAvcDinamico.configuracion"); 
			
			// INICIO EJECUCION
			for (String csv : listaArchivosValidosAProcesar) {
				
				Traza.auditoria(ProcesarArchivosAvcDinamicoBatchRunner.class, "");
				Traza.auditoria(ProcesarArchivosAvcDinamicoBatchRunner.class, "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				Traza.auditoria(ProcesarArchivosAvcDinamicoBatchRunner.class, "-- Archivo a procesar: " + csv);
				
				// Descompongo la mascara del archivo a procesar
				
			    String result[] = csv.split("\\_");			
			    String tipoDeProceso = result[0];
			    String numeroDeAcuerdoCSV = result[1];
			    String fechaProceso = result[2];
			    String secuencial = result[3];
			    
			    String result2[] = secuencial.split("\\.");
			    secuencial = result2[0];
			    
			    Traza.auditoria(ProcesarArchivosAvcDinamicoBatchRunner.class, "-- Tipo de Proceso: " + tipoDeProceso);		    
			    Traza.auditoria(ProcesarArchivosAvcDinamicoBatchRunner.class, "-- Numero de Acuerdo: " + numeroDeAcuerdoCSV);
			    Traza.auditoria(ProcesarArchivosAvcDinamicoBatchRunner.class, "-- Fecha de Proceso: " + fechaProceso);
			    Traza.auditoria(ProcesarArchivosAvcDinamicoBatchRunner.class, "-- Secuencial del archivo: " + secuencial);
				Traza.auditoria(ProcesarArchivosAvcDinamicoBatchRunner.class, "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

				if (mapNumerosDeAcuerdoJobs.containsKey(numeroDeAcuerdoCSV)) {
					
					String nombreJob = (String) mapNumerosDeAcuerdoJobs.get(numeroDeAcuerdoCSV); 
					String jobAEjecutar = nombreJob + Constantes.PUNTO + numeroDeAcuerdoCSV;
					String pathJobAEjecutar = pathJobs + jobAEjecutar + Constantes.ARCHIVO_XML;
					System.setProperty("fileDefinicionProcesamientoArchivoAvcAcuerdo", pathJobAEjecutar);
					
					//
					// Aqui utilizo un XML de configuración de carga generico (definicionProcesamientoArchivoAvcAcuerdo.xml) para cualquier 
					// definicion de procesamiento de archivos AVC.
					// Internamente este XML posee un import de 'spring-batch-context.xml' y un import de un archivo externo definido de esta manera
					// 
					// <import resource="file:${fileDefinicionProcesamientoArchivoAvcAcuerdo}"/>
					// 
					// La variable '${fileDefinicionProcesamientoArchivoAvcAcuerdo}' es la que indica donde estaría ubicado el XML de definicion que finalmente
					// deseo utilizar. Este valor lo seteo en la linea anterior usando 'System.setProperty("fileDefinicionProcesamientoArchivoAvcAcuerdo", pathJobAEjecutar)'
					// 
					// Creo que no es la manera la limpia de hacer esto, pero es la unica que encontré hasta el momento para hacer que dinamicamente levante un contexto mixto,
					// es decir, mitad de la configuración dentro del paquete entregable, y mitad de la configuración fuera de este.
					//
					String pathJobGenericoAEjecutar = "springbatch/definicionProcesamientoArchivoAvc/definicionProcesamientoArchivoAvcAcuerdo.xml";
					
					Traza.auditoria(ProcesarArchivosAvcDinamicoBatchRunner.class, "-- Ejecutara el Job: " +  jobAEjecutar + " cuya definicion se levanta de: " + pathJobAEjecutar);  					
					CommandLineJobRunner commandLineJobRunner = new CommandLineJobRunner();
					String[] argumentosNuevos = new String[]{jobAEjecutar, pathJobGenericoAEjecutar, "file.name=" + csv, "file.path=" + pathOrigen, "numeroAcuerdo=" + numeroDeAcuerdoCSV.toString(), "secuencialArchivo=" + secuencial};
					CommandLineJobRunner.setContadorArchivosAProcesar(contadorArchivosAProcesar);
					commandLineJobRunner.run(argumentosNuevos);
					
				}
				salida = true;
			}
			
		} catch (Throwable e) {
			Traza.error(ProcesarArchivosAvcDinamicoBatchRunner.class, "Se ha producido un error en el proceso batch", e);
			Traza.advertencia(ProcesarArchivosAvcDinamicoBatchRunner.class, "-- Se ha finalizado con error en el proceso de archivos Avc");
			throw new ShivaExcepcion(e);
		}
		
		return salida;
	}
	
	
	// == Metodos Utiles == //
	
	/**
	 * 
	 * @return
	 * @throws FileNotFoundException
	 * @throws NegocioExcepcion
	 * @throws IOException
	 */
	private static List<String> listarCSV() throws FileNotFoundException, NegocioExcepcion, IOException{
		
		System.out.println("Directorio de Entrada: " + pathOrigen);
		
		File folder = new File(pathOrigen);
		if (!folder.exists()) {
			Traza.error(CommandLineJobRunner.class, "Error! La ruta de configuración de los archivos CSV  no existe. Ruta actual: " + folder);
			System.exit(1);
			return null;
		}
		else {
			List<String> listaDeArchivos = listFilesCSVForFolder(folder);
			
			Traza.auditoria(ProcesarArchivosAvcDinamicoBatchRunner.class, "");
			Traza.auditoria(ProcesarArchivosAvcDinamicoBatchRunner.class, "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			Traza.auditoria(ProcesarArchivosAvcDinamicoBatchRunner.class, "Listado de archivos encontrados en el directorio 'entrada' (" + folder + ").");
			for (String elemento : listaDeArchivos) {
				Traza.auditoria(ProcesarArchivosAvcDinamicoBatchRunner.class, elemento);
			}
			
			return listaDeArchivos;
		}
	}	
	
	/**
	 * 
	 * @return
	 * @throws FileNotFoundException
	 * @throws NegocioExcepcion
	 * @throws IOException
	 */
	private static List<String> listarJobs() throws FileNotFoundException, NegocioExcepcion, IOException{

		String pathJobs = Propiedades.SHIVA_PROPIEDADES.getString("batch.directorio.archivosRegistrosAvcDinamico.configuracion"); 
		File folder = new File(pathJobs);
		
		if (folder.exists()) {
			List<String> listaDeJobs = listFilesXMLForFolder(folder);
			
			Traza.auditoria(ProcesarArchivosAvcDinamicoBatchRunner.class, "");
			Traza.auditoria(ProcesarArchivosAvcDinamicoBatchRunner.class, "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			Traza.auditoria(ProcesarArchivosAvcDinamicoBatchRunner.class, "Listado de archivos encontrados en el directorio 'configuracion' (" + folder + ").");
			for (String elemento : listaDeJobs) {
				Traza.auditoria(ProcesarArchivosAvcDinamicoBatchRunner.class, elemento);
			}

			return listaDeJobs;
		}
		else {
			Traza.error(CommandLineJobRunner.class, "Error! La ruta de configuración de los archivos XML definición de procesamiento no existe. Ruta actual: " + folder);
			System.exit(1);
			return null;
		}	
	}

	/**
	 * 
	 * @param folder
	 * @return
	 * @throws IOException
	 */
	public static List<String> listFilesCSVForFolder(final File folder) throws IOException {
		
		List<String> filenames = new LinkedList<String>();
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				listFilesCSVForFolder(fileEntry);
			} else {
				
				if(fileEntry.getName().contains(Constantes.ARCHIVO_CSV)) { 
//					if(!ControlArchivo.isFileEmpty(fileEntry)) {
						
						String result[] = fileEntry.getName().split("\\_");			
						if (result.length == 4) {
							String tipo = result[0];
					    	String numeroDeAcuerdoCSV = result[1];
					    	String fechaProcesamiento = result[2];
					    	String secuencialYExtension = result[3];
					    	String result2[] = secuencialYExtension.split("\\.");
					    	String secuencial = result2[0];
						    String extension = result2[1];
							
							if(extension.equals("csv")){
								filenames.add(fileEntry.getName());
							}
						}
						else{
							System.out.println("El archivo " + fileEntry.getName() + " no tiene un formato correcto.");
							Traza.auditoria(CommandLineJobRunner.class, "El archivo " + fileEntry.getName() + " no tiene un formato correcto.");
						}
						
//					} else {
//						Traza.auditoria(CommandLineJobRunner.class, "El archivo " + fileEntry.getName() + " se encuentra vacío.");
//					}
				} 
			}
		}
		return filenames;
	}
	
	/**
	 * 
	 * @param folder
	 * @return
	 * @throws NegocioExcepcion
	 */
	public static List<String> listFilesXMLForFolder(final File folder) throws NegocioExcepcion {
		
		List<String> filenames = new LinkedList<String>();
		for (final File fileEntry : folder.listFiles()) {
			
			if (fileEntry.isDirectory()) {
				listFilesXMLForFolder(fileEntry);
			} else {
				if(fileEntry.getName().contains(Constantes.ARCHIVO_XML)){
					
					String result[] = fileEntry.getName().split("\\.");			
					if (result.length == 3) {
						String nombreJob = result[0];
				    	String numeroDeAcuerdoXML = result[1];
				    	String extension = result[2];
						
						if(extension.equals("xml")){
							filenames.add(fileEntry.getName());
						}
					}
					else {
						Traza.auditoria(CommandLineJobRunner.class, "El archivo " + fileEntry.getName() + " no tiene el formato adecuado.");
					}
				}
			}
		}
		return filenames;
	}
	
	/**
	 * 
	 * @param filename
	 * @param acuerdoServicioImpl
	 * @return
	 * @throws NegocioExcepcion
	 */
	public static boolean validarJob(String filename, IAcuerdoServicio acuerdoServicioImpl) throws NegocioExcepcion{
		
		String result[] = filename.split("\\.");		
	    String nombreJob = result[0];
	    String numeroDeAcuerdoJob = result[1];
	    
	    Traza.auditoria(ProcesarArchivosAvcDinamicoBatchRunner.class, "");
	    Traza.auditoria(ProcesarArchivosAvcDinamicoBatchRunner.class, "Archivo de configuracion a validar: " + filename);

	    try {
		    int numeroDeAcuerdoInt = Integer.parseInt(numeroDeAcuerdoJob);

		    Traza.auditoria(ProcesarArchivosAvcDinamicoBatchRunner.class, "Nombre Job: " + nombreJob);
		    Traza.auditoria(ProcesarArchivosAvcDinamicoBatchRunner.class, "Numero de Acuerdo Job: " + numeroDeAcuerdoJob);
		    
		    if (nombreJob.equals(Constantes.DEFINICION_PROCESAMIENTO_ARCHIVO_AVC_ACUERDO) && numeroDeAcuerdoInt >= 1 && numeroDeAcuerdoInt <= 999 ) {
		    	
		    	ShvParamAcuerdo acuerdo = acuerdoServicioImpl.buscar(numeroDeAcuerdoJob);
		    	
		    	if (Validaciones.isObjectNull(acuerdo)) {
					Traza.auditoria(ProcesarArchivosAvcDinamicoBatchRunner.class, "Warning!! El XML de definicion no posee un acuerdo configurado en base de datos.");
					mapSystemExits.put(nombreJob, 2);
					return false;
		    	} else {
					Traza.auditoria(ProcesarArchivosAvcDinamicoBatchRunner.class, "Archivo de configuracion validado correctamente.");
					mapNumerosDeAcuerdoJobs.put(result[1], result[0]);
					listaNumerosDeAcuerdoJobs.add(numeroDeAcuerdoJob);
					return true;
		    	}
			}
		    else {
		        Traza.advertencia(ProcesarArchivosAvcDinamicoBatchRunner.class, "Se encontro un job con nombre/formato erroneo. No se agregara a la lista de jobs disponibles.");
		    	
		        mapSystemExits.put(nombreJob, 2);
		        return false;
		    }
		} catch (NumberFormatException e) {
	        Traza.advertencia(ProcesarArchivosAvcDinamicoBatchRunner.class, "Se encontro un job con nombre/formato erroneo. No se agregara a la lista de jobs disponibles.");
			return false;
		}
	}
	
	/**
	 * 
	 * @param csv
	 * @param archivoAVCSoporteServicio
	 * @return
	 * @throws NegocioExcepcion
	 * @throws IOException
	 */
	public static boolean validarArchivoCSV(String csv, IArchivoAVCSoporteServicio archivoAVCSoporteServicio) throws NegocioExcepcion, IOException{

	    String result[] = csv.split("\\_");			
	    String numeroDeAcuerdoCSV = result[1];
	    
	    Traza.auditoria(ProcesarArchivosAvcDinamicoBatchRunner.class, "");
	    Traza.auditoria(ProcesarArchivosAvcDinamicoBatchRunner.class, "Archivo a procesar a validar: " + csv);
	    
		if (archivoAVCSoporteServicio.esNombreArchivoRegistroAVCDinamico(csv)) {
			ShvAvcArchivoAvc archivoAux = archivoAVCSoporteServicio.buscarArchivoAvcPorNombreArchivo(csv);
			
			if (!Validaciones.isObjectNull(archivoAux)) {
				archivoAVCSoporteServicio.enviarMailArchivoProcesadoAnteriormente(archivoAux);
				
				Traza.auditoria(ProcesarArchivosAvcDinamicoBatchRunner.class, "Ya fue procesado anteriormente el archivo: " + csv);
				return false;
			}
			
			listaNumerosDeAcuerdoCSV.add(numeroDeAcuerdoCSV);	
	
			if (mapNumerosDeAcuerdoJobs.containsKey(numeroDeAcuerdoCSV)) {
				Traza.auditoria(ProcesarArchivosAvcDinamicoBatchRunner.class, "Archivo a procesar validado correctamente.");
				listaArchivosValidosAProcesar.add(csv);
				return true;
			} else {
				Traza.auditoria(ProcesarArchivosAvcDinamicoBatchRunner.class, "Warning!! Este archivo a procesar no posee una definicion de procesamiento configurada.");
				return false;
			}
		} else {
			// El nombre del archivo es incorrecto,  envio mail.
			archivoAVCSoporteServicio.enviarMailPorNombreIncorrectoArchivoAVC(csv);
	        Traza.auditoria(ProcesarArchivosAvcDinamicoBatchRunner.class, "La nomenclatura del archivo '" + csv + "' no es la esperada, por lo que no sera procesado.");
		        
			return false;
		}
	}
}
