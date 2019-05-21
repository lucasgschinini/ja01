package ar.com.telecom.shiva.batch.springbatch.listener;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;
import java.util.Map;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.utils.ControlArchivo;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.batch.springbatch.model.GenericDataFile;
import ar.com.telecom.shiva.negocio.servicios.IArchivoAVCSoporteServicio;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;
import ar.com.telecom.shiva.persistencia.dao.IAcuerdoDao;
import ar.com.telecom.shiva.persistencia.modelo.ShvAvcArchivoAvc;
import ar.com.telecom.shiva.persistencia.modelo.ShvDocDocumentoAdjunto;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamAcuerdo;

/**
 * @author u591368 F.N. Quispe
 *
 */
public class InitFinishJobListener implements JobExecutionListener {

	@Autowired
	private IParametroServicio parametroServicio;
	
	@Autowired
	private IArchivoAVCSoporteServicio archivoAVCSoporteServicio;
	
	private GenericDataFile archivoAvcInsertado;
	
	@Autowired
	private IAcuerdoDao acuerdoDao;
	
	@Autowired
	private IArchivoAVCSoporteServicio archivoAvcSoporteServicio;
	
	
	private static final String CARPETA_HISTORICO = "historico";

	
	@Override
	public void afterJob(JobExecution jobExecution) {
		// TODO Auto-generated method stub

		StepExecution stepExecution = jobExecution.getStepExecutions().iterator().next();

		int readCount = stepExecution.getReadCount();
		int totalCount = readCount + stepExecution.getReadSkipCount();
//		int writeCount = stepExecution.getWriteCount();


		if (jobExecution.getExitStatus().getExitCode().equals("ERROR_WRITER")) {
			System.out.println("ENTRO EN AFTER JOB. EXITSTATUS = ERROR_WRITER");
			Traza.error(InitFinishJobListener.class,"[ERROR WRITER FIN - " + getJobAndId(jobExecution) + "] " + getInfoJob(jobExecution) +
					"\n[STATISTICS" + getJobAndId(jobExecution) + "]" + getStatisticsJob(jobExecution));
		}
		
		else {
		Traza.auditoria(InitFinishJobListener.class,"[FIN - " + getJobAndId(jobExecution) + "] " + getInfoJob(jobExecution) +
				"\n[STATISTICS" + getJobAndId(jobExecution) + "]" + getStatisticsJob(jobExecution));
		Traza.auditoria(InitFinishJobListener.class, "");


		if (jobExecution.getAllFailureExceptions() != null && !jobExecution.getAllFailureExceptions().isEmpty()) {

			for (Throwable throwable : jobExecution.getAllFailureExceptions()) {
				Traza.error(InitFinishJobListener.class,"[ERROR FIN - " + getJobAndId(jobExecution) + "] " + getInfoJob(jobExecution) +
						"\n[STATISTICS" + getJobAndId(jobExecution) + "]" + getStatisticsJob(jobExecution), throwable);
			}
		}

		Long totalRegistros = (long) totalCount;
		String logProcesamiento = (String) jobExecution.getExecutionContext().get("logProcesamiento");
		archivoAvcInsertado.setLogProcesamiento(logProcesamiento);
		
		long cantidadLineasOk = Constantes.CERO;
		long cantidadLineasErroneas = Constantes.CERO;
		
		Object contadorObjectOk = jobExecution.getExecutionContext().get("contadorLineasProcesadasOk");
		Object contadorObjectError = jobExecution.getExecutionContext().get("contadorLineasErroneas"); 
		
		if (!Validaciones.isObjectNull(contadorObjectOk)) {
			cantidadLineasOk = (Long) contadorObjectOk;
		}
		
		if (!Validaciones.isObjectNull(contadorObjectError)) {
			cantidadLineasErroneas = (Long) contadorObjectError;
		}
		
		try {
			enviarMail(totalRegistros, cantidadLineasOk, cantidadLineasErroneas, jobExecution);
		} catch (NegocioExcepcion e) {
			Traza.error(this.getClass(), "Ocurrio un error al enviar mail.");
			throw new RuntimeException();
		}
		moverArchivoACarpetaHistorica(jobExecution.getJobParameters().getString("file.name"));
	
		}
		
		
	}

	
	@Override
	public void beforeJob(JobExecution jobExecution) {

		try {
			Traza.auditoria(InitFinishJobListener.class, " ");
			Traza.auditoria(InitFinishJobListener.class, " ");
			Traza.auditoria(InitFinishJobListener.class, "[INICIO del Job con archivo de configuración: " + getJobAndId(jobExecution) + "] " + getInfoJob(jobExecution));
			Traza.auditoria(InitFinishJobListener.class, " ");
			archivoAvcInsertado = createControlArchivo(jobExecution);
			jobExecution.getExecutionContext().put("tieneError", false);
			jobExecution.getExecutionContext().put("logProcesamiento", Constantes.EMPTY_STRING);
			
		} catch (PersistenciaExcepcion | NegocioExcepcion | IOException e) {
			System.out.println("ERROR EN BEFORE JOB: " + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param jobExecution
	 * @return
	 */
	private String getStatisticsJob(JobExecution jobExecution) {

		ExecutionContext jobContext = jobExecution.getExecutionContext();
		int readCount = (int) jobContext.get("readCount");
		int writeCount = (int) jobContext.get("writeCount");
		int commitCount = (int) jobContext.get("commitCount");
		int rollbackCount = (int) jobContext.get("rollbackCount");
		
		long cantidadLineasOk = Constantes.CERO;
		long cantidadLineasErroneas = Constantes.CERO;

		Object contadorObjectOk = jobContext.get("contadorLineasProcesadasOk");
		Object contadorObjectError = jobContext.get("contadorLineasErroneas"); 
		
		if (!Validaciones.isObjectNull(contadorObjectOk)) {
			cantidadLineasOk = (Long) contadorObjectOk;
		}
		
		if (!Validaciones.isObjectNull(contadorObjectError)) {
			cantidadLineasErroneas = (Long) contadorObjectError;
		}
		
		StringBuilder builder = new StringBuilder();
		
		builder.append(" Cantidad de registros leidos: " + readCount)
		.append(", Cantidad de registros escritos: " + writeCount)
		.append(", Cantidad de registros commiteados: " + commitCount)
		.append(", Cantidad de registros con rollback: " + rollbackCount)
		.append(", Cantidad de registros con error: " + cantidadLineasErroneas)
		.append(", Cantidad de registros ok: " + cantidadLineasOk);

		if (jobExecution.getExitStatus().compareTo(ExitStatus.FAILED) == 0) {

			builder.append(" Exit All Failure Exceptions : \n");

			for (Throwable e : jobExecution.getAllFailureExceptions())
				builder.append(e.getMessage() + "\n");

			for (StepExecution stepExecution : jobExecution.getStepExecutions()) {
				builder.append(stepExecution + "\n");
			}
		}

		return builder.toString();
	}
	
	/**
	 * 
	 * @param jobExecution
	 * @return
	 */
	private String getInfoJob(JobExecution jobExecution) {

		StringBuilder builder = new StringBuilder();
		builder.append("[" + getJobAndId(jobExecution) + "] ")
				.append("Status: " + jobExecution.getStatus())
				.append(" Job: " + jobExecution.getJobInstance().getJobName())
				.append(" Job Id:" + jobExecution.getJobId());

		JobParameters parameters = jobExecution.getJobParameters();
		Map<String, JobParameter> map = parameters.getParameters();

		for (Map.Entry<String, JobParameter> entry : map.entrySet()) {
			builder.append(" [" + entry.getKey() + "]: " + entry.getValue().toString());
		}

		return builder.toString();
	}


	/**
	 * 
	 * @param jobExecution
	 * @return
	 * @throws PersistenciaExcepcion
	 * @throws NegocioExcepcion
	 * @throws IOException
	 */
	private GenericDataFile createControlArchivo(JobExecution jobExecution) throws PersistenciaExcepcion, NegocioExcepcion, IOException {

		// traigo los job parameters que necesito
		String nombreArchivo=jobExecution.getJobParameters().getString("file.name");
		String pathArchivo = jobExecution.getJobParameters().getString("file.path");
		
		// metodo para extraer secuencial y numero de acuerdo, a partir del nombre de archivo de entrada
		
		
        // Creo el documento adjunto
        File archivoAdjunto = new File(pathArchivo + nombreArchivo);
        
        // Creo el Generic Data File   
		GenericDataFile genericDataFile = setGenericDataFileAttributes(nombreArchivo, archivoAdjunto);

       
        
        // Subo al contexto objetos que me serviran mas adelante
		jobExecution.getExecutionContext().put("genericDataFile", genericDataFile);
		jobExecution.getExecutionContext().put("nroDeAcuerdo", genericDataFile.getIdAcuerdo());
//		jobExecution.getExecutionContext().put("secuencialArchivo", secuencialArchivo);
		
		return genericDataFile;
	}

//	private void updateControlArchivo(ControlArchivo controlArchivo, Integer cantidadRegistros,
//			Integer cantidadRegistrosInd, ExitStatus exitStatus) {
//
////		controlArchivo.setCantidadRegistrosInd(cantidadRegistrosInd.longValue());
////		controlArchivo.setCantidadRegistros(cantidadRegistros.longValue());
////
////		if (exitStatus.compareTo(ExitStatus.COMPLETED) == 0) {
////			controlArchivo.setEstadoProcesamiento("OK");
////		} else if (exitStatus.compareTo(ExitStatus.FAILED) == 0) {
////			controlArchivo.setEstadoProcesamiento("ERROR");
////		} else if (exitStatus.compareTo(ExitStatus.EXECUTING) == 0) {
////			controlArchivo.setEstadoProcesamiento("EJECUTANDO");
////		}
////
////		controlArchivo.setFechaProceso(DateUtils.clear(new Date()));
////		// TODO controlArchivo.setTotalImporte(
////		// processBean.getTotalImporte().longValue() );
////		controlArchivo.setTimestamp(Timestamp.from(Instant.now()));
////
////		getControlArchivoRepository().save(controlArchivo);
//	}

	private String getJobAndId(JobExecution jobExecution) {
		return jobExecution.getJobInstance().getJobName() + " - id=" + jobExecution.getJobId();
	}

//	public ControlArchivoRepository getControlArchivoRepository() {
//		return controlArchivoRepository;
//	}
//
//	public void setControlArchivoRepository(ControlArchivoRepository controlArchivoRepository) {
//		this.controlArchivoRepository = controlArchivoRepository;
//	}
//
//	public List<ProcessBean> getProcessBeanList() {
//		return processBeanList;
//	}
//
//	public void setProcessBeanList(List<ProcessBean> processBeanList) {
//		this.processBeanList = processBeanList;
//	}
	
	
	private void enviarMail(Long totalRegistros, Long cantidadLineasOk, Long cantidadLineasErroneas, JobExecution jobExecution) throws NegocioExcepcion{


		
		System.out.println("Enviando mail de finalizacion");
		System.out.println("Cantidad Lineas totales: " + totalRegistros);
		System.out.println("Cantidad Lineas NOK: " + cantidadLineasErroneas);
		
		// Traigo un boolean del contexto para ver si el archivo contiene errores
        boolean hayErrorEnArchivo = false; 
        
        if(!Validaciones.isObjectNull(jobExecution.getExecutionContext().get("tieneError"))) {
        	hayErrorEnArchivo = (boolean) jobExecution.getExecutionContext().get("tieneError");
        };

			if (totalRegistros.equals(0L)) {
				try {
					crearAvcArchivoAvc(archivoAvcInsertado);
				} catch (PersistenciaExcepcion e) {
					throw new NegocioExcepcion("Ocurrió un error al guardar el archivo " + archivoAvcInsertado.getNombreArchivo() + " en base de datos",e);
				}
				archivoAVCSoporteServicio.enviarMailPorArchivoVacio(archivoAvcInsertado.getNombreArchivo());
//			} else if (totalRegistros.equals(cantidadLineasErroneas)) {
			} else if (hayErrorEnArchivo) {
				// Todos los registros erroneos, enviar mail RECHAZADO
				archivoAVCSoporteServicio.enviarMailRegistrosErroneosArchivoAVC(archivoAvcInsertado, totalRegistros, cantidadLineasOk, cantidadLineasErroneas, jobExecution);
				
			} else {
			//if (jobExecution.getAllFailureExceptions() == null && jobExecution.getAllFailureExceptions().isEmpty()) {
				archivoAVCSoporteServicio.enviarMailConProcesamientoArchivoAVC(archivoAvcInsertado, 
						 totalRegistros, cantidadLineasOk, cantidadLineasErroneas, jobExecution);
			}
				//}
			
			//else{
			//	System.out.println("hubo un error interno.. el proceso deberia terminar");
			//	jobExecution.setExitStatus(new ExitStatus("ERROR_FINALIZAR"));
			//}
			
		
	}
	
	
	private void crearAvcArchivoAvc(GenericDataFile genericFile) throws PersistenciaExcepcion, NegocioExcepcion {
	
		// Busco el acuerdo
        ShvParamAcuerdo acuerdo = acuerdoDao.buscarAcuerdo(genericFile.getIdAcuerdo());
        
        // Creo el documento adjunto
        ShvDocDocumentoAdjunto documentoAdjunto = new ShvDocDocumentoAdjunto();
        documentoAdjunto.setArchivoAdjunto(genericFile.getArchivoAdjunto());
        documentoAdjunto.setNombreArchivo(genericFile.getNombreArchivo());
        documentoAdjunto.setFechaCreacion(new Date());
        documentoAdjunto.setUsuarioCreacion(parametroServicio.getValorTexto(Constantes.USUARIO_BATCH));
        
        // Creo el Archivo AVC
	    ShvAvcArchivoAvc archivoAVC = new ShvAvcArchivoAvc();
	    archivoAVC.setDocumentoAdjunto(documentoAdjunto);
        archivoAVC.setLogProcesamiento(genericFile.getLogProcesamiento());
        archivoAVC.setFechaProcesamiento(genericFile.getFechaProcesamiento());
        archivoAVC.setNombreArchivo(genericFile.getNombreArchivo());
        archivoAVC.setUsuarioProcesamiento(genericFile.getUsuarioProcesamiento());
        archivoAVC.setAcuerdo(acuerdo);
		
        archivoAVC = archivoAvcSoporteServicio.crear(archivoAVC);
        Traza.auditoria(this.getClass(), "Se ha generado el archivo Avc con ID: " + archivoAVC.getIdArchivosAvc());
        Traza.auditoria(this.getClass(), " ");	
	
	}


	private void moverArchivoACarpetaHistorica(String archivoAMover){
		
		String pathOrigen = Propiedades.SHIVA_PROPIEDADES.getString("batch.directorio.archivosRegistrosAvcDinamico");

		// Muevo el archivo procesado a la carpeta historica
		try {
		if (!CARPETA_HISTORICO.equals(archivoAMover)){
			ControlArchivo.moverArchivoACarpetaHistorico(archivoAMover, pathOrigen);
		}
    } catch (NegocioExcepcion e) {
    	System.err.println(Utilidad.getStackTrace(e));
		Traza.error(this.getClass(), "No se ha podido mover el archivo " + archivoAMover + " a la carpeta de historicos", e);
    } 
	}
	
	private GenericDataFile setGenericDataFileAttributes(String nombreArchivo, File archivoAdjunto) throws IOException, NegocioExcepcion{
		
		String result[] = nombreArchivo.split("\\_");			
//	    String tipoDeProceso = result[0];
	    String numeroDeAcuerdo = result[1];
//	    String fechaProceso = result[2];
	    String secuencial = result[3];
	    
	    String result2[] = secuencial.split("\\.");
	    String secuencialArchivo = result2[0];
        GenericDataFile genericDataFile = new GenericDataFile();
	    genericDataFile.setFechaProcesamiento(new Date());
        genericDataFile.setNombreArchivo(nombreArchivo);
        genericDataFile.setSecuencialArchivo(secuencialArchivo);
        genericDataFile.setUsuarioProcesamiento(parametroServicio.getValorTexto(Constantes.USUARIO_BATCH));
        genericDataFile.setIdAcuerdo(numeroDeAcuerdo);
        genericDataFile.setArchivoAdjunto(Files.readAllBytes(archivoAdjunto.toPath()));
        
        return genericDataFile;
	}
	

}
