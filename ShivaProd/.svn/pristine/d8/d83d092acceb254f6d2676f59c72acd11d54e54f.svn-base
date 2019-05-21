package ar.com.telecom.shiva.batch.springbatch.launcher;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.batch.core.launch.support.ExitCodeMapper;
import org.springframework.batch.core.launch.support.SimpleJvmExitCodeMapper;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.utils.ControlArchivo;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.servicios.IAcuerdoServicio;
import ar.com.telecom.shiva.negocio.servicios.IArchivoAVCSoporteServicio;
import ar.com.telecom.shiva.persistencia.modelo.ShvAvcArchivoAvc;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamAcuerdo;

public class CommandLineJobRunner {
	
	static int contadorArchivosAProcesar=0;
	static int contadorArchivosProcesados=0;
	static int contadorArchivosErroneos=0;
	static int contadorArchivosOK=0;

	static List<String> listaNumerosDeAcuerdoJobs = new ArrayList<String>();
    static Map<String, String> mapNumerosDeAcuerdoJobs = new HashMap<String, String>();
	static List<String> listaNumerosDeAcuerdoCSV = new ArrayList<String>();
	static List<String> listaArchivosValidosAProcesar = new ArrayList<String>();


	static String pathOrigen = Propiedades.SHIVA_PROPIEDADES.getString("batch.directorio.archivosRegistrosAvcDinamico");


    static Map<String, Integer> mapSystemExits = new HashMap<String, Integer>();

    /**
     * 
     * @param arguments
     */
	public void run(String[] arguments) {

		ShivaCommandLineJobRunner runner = null;
		ExitStatusBatchService exitStatus = null;
		Traza.auditoria(CommandLineJobRunner.class, 
				"[SPRINGBATCH] Se ha iniciado el Batch para el procesamiento de archivos avc");
		try {
			init(arguments);

			runner = new ShivaCommandLineJobRunner();
			exitStatus = runner.start(arguments);

		} catch (Exception e) {
			Traza.error(CommandLineJobRunner.class, e.getMessage(), e);;
		}finally{
			finish(runner, exitStatus);
		}
	}
	
	/**
	 * 
	 * @param arguments
	 * @throws ShivaExcepcion
	 */
	private void init(String[] arguments) throws ShivaExcepcion {

		StringBuilder builder = new StringBuilder();
		for (String value : arguments)
			builder.append(" " + value);

		Traza.auditoria(CommandLineJobRunner.class, 
				"[INICIO - CommandLineJobRunner] Args:" + builder.toString());
		Traza.auditoria(this.getClass(), " ");

		if (arguments.length < 2)
			throw new ShivaExcepcion("La cantidad de parametros debe ser como minimo 2");
	}

	/**
	 * 
	 * @param runner
	 * @param exitStatus
	 */
	private void finish(ShivaCommandLineJobRunner runner, ExitStatusBatchService exitStatus) {

		if (exitStatus.getThrowable() != null){
			Traza.error(CommandLineJobRunner.class, "[SPRINGBATCH] Ha ocurrido un error. : " , 
					exitStatus.getThrowable());			
		}
		String message = "[FIN - CommandLineJobRunner] Exit Status: " + exitStatus.getExitStatus() + " Message: " + exitStatus.getMensaje();
		
		contadorArchivosProcesados++;

		Traza.auditoria(CommandLineJobRunner.class, message);
		Traza.auditoria(CommandLineJobRunner.class, "Contador Archivos A PROCESAR: " + contadorArchivosAProcesar);
		Traza.auditoria(CommandLineJobRunner.class, "Contador Archivos PROCESADOS: " + contadorArchivosProcesados);

		if (contadorArchivosAProcesar == contadorArchivosProcesados) {
			
			Traza.auditoria(CommandLineJobRunner.class, "Se procesaron todos los archivos.");

			ExitCodeMapper exitCodeMapper = new SimpleJvmExitCodeMapper();
			runner.exit(exitCodeMapper.intValue(exitStatus.getExitStatus().getExitCode()));
		}
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
		
		String pathOrigen = Propiedades.SHIVA_PROPIEDADES.getString("batch.directorio.archivosRegistrosAvcDinamico");

		Traza.auditoria(CommandLineJobRunner.class, "INPUT DIRECTORY: " + pathOrigen);
		
		File folder = new File(pathOrigen);
		if (!folder.exists()) {
			Traza.error(CommandLineJobRunner.class, "Error! La ruta de configuración de los archivos CSV  no existe. Ruta actual: " + folder);
			System.exit(1);
			return null;
		}
		else {
			List<String> listaDeArchivos = listFilesCSVForFolder(folder);
			return listaDeArchivos;
		}
		
	}	
	
	/**
	 * 
	 * @param acuerdoServicioImpl
	 * @return
	 * @throws FileNotFoundException
	 * @throws NegocioExcepcion
	 * @throws IOException
	 */
	private static List<String> listarJobs(IAcuerdoServicio acuerdoServicioImpl) throws FileNotFoundException, NegocioExcepcion, IOException{

		String pathJobs = Propiedades.SHIVA_PROPIEDADES.getString("batch.directorio.archivosRegistrosAvcDinamico.configuracion"); 
		File folder = new File(pathJobs);
		if (folder.exists()) {
			List<String> listaDeJobs = listFilesXMLForFolder(folder);
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
					if(!ControlArchivo.isFileEmpty(fileEntry)) {
						filenames.add(fileEntry.getName());
					} else {
						Traza.auditoria(CommandLineJobRunner.class, "El archivo " + fileEntry.getName() + " se encuentra vacío.");
					}
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
				if(fileEntry.getName().contains(Constantes.ARCHIVO_XML))
					filenames.add(fileEntry.getName());
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
	    
	    try {
		    int numeroDeAcuerdoInt = Integer.parseInt(numeroDeAcuerdoJob);

		    Traza.auditoria(CommandLineJobRunner.class, "NOMBRE JOB: " + nombreJob);
		    Traza.auditoria(CommandLineJobRunner.class, "NUMERO DE ACUERDO JOB: " + numeroDeAcuerdoJob);

		    if (nombreJob.equals(Constantes.DEFINICION_PROCESAMIENTO_ARCHIVO_AVC_ACUERDO) && numeroDeAcuerdoInt >=1 && numeroDeAcuerdoInt<=999 ) {
		    	
		    	ShvParamAcuerdo acuerdo = acuerdoServicioImpl.buscar(numeroDeAcuerdoJob);
			    int numeroDeAcuerdoBuscado = acuerdo.getIdAcuerdo();
			    
			    Traza.auditoria(CommandLineJobRunner.class, "ACUERDO BUSCADO EN BASE: " + numeroDeAcuerdoBuscado);
			    Traza.auditoria(CommandLineJobRunner.class, "ACUERDO ARCHIVO DE ENTRADA: " + numeroDeAcuerdoInt);
		    
			    	if (numeroDeAcuerdoBuscado == numeroDeAcuerdoInt) {
						mapNumerosDeAcuerdoJobs.put(result[1], result[0]);
						listaNumerosDeAcuerdoJobs.add(numeroDeAcuerdoJob);
						return true;
					}
			    	else {
			    		
						Traza.auditoria(CommandLineJobRunner.class, "El XML de definicion no posee un acuerdo configurado. ");
						mapSystemExits.put(nombreJob, 2);
						return false;
					}
			    
			}
		    else{
		        Traza.advertencia(CommandLineJobRunner.class, "Se encontro un job con nombre/formato erroneo. No se agregara a la lista de jobs disponibles. Archivo: " + filename);
		    	
		        mapSystemExits.put(nombreJob, 2);
		        return false;
		    }
		} catch (NumberFormatException e) {
	        Traza.advertencia(CommandLineJobRunner.class, "Se encontro un job con nombre/formato erroneo. No se agregara a la lista de jobs disponibles. Archivo: " + filename);
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
		
	    File archivoCsv = new File(csv);
	    
		if (archivoAVCSoporteServicio.esNombreArchivoRegistroAVCDinamico(csv)) {
			ShvAvcArchivoAvc archivoAux = archivoAVCSoporteServicio.buscarArchivoAvcPorNombreArchivo(csv);
			
			if(!Validaciones.isObjectNull(archivoAux)){
				archivoAVCSoporteServicio.enviarMailArchivoProcesadoAnteriormente(archivoAux);
				
				Traza.auditoria(CommandLineJobRunner.class, "Ya fue procesado anteriormente el archivo " + csv);
				
				return false;
			}
			
			listaNumerosDeAcuerdoCSV.add(numeroDeAcuerdoCSV);	

			if (mapNumerosDeAcuerdoJobs.containsKey(numeroDeAcuerdoCSV)) {
				listaArchivosValidosAProcesar.add(csv);
				return true;
			}
			else {
				return false;
			}
		} else {
				// El nombre del archivo es incorrecto,  envio mail.
				archivoAVCSoporteServicio.enviarMailPorNombreIncorrectoArchivoAVC(csv);
		        Traza.auditoria(CommandLineJobRunner.class, "Se ha informado que el archivo " + csv + " es incorrecto y no lo puede procesar.");
		        mapSystemExits.put(csv, 2);
		        
			return false;
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public static int getContadorArchivosAProcesar() {
		return contadorArchivosAProcesar;
	}

	/**
	 * 
	 * @param contadorArchivosAProcesar
	 */
	public static void setContadorArchivosAProcesar(int contadorArchivosAProcesar) {
		CommandLineJobRunner.contadorArchivosAProcesar = contadorArchivosAProcesar;
	}
}
