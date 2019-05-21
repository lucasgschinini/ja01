package ar.com.telecom.shiva.negocio.servicios;

import org.springframework.batch.core.JobExecution;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.batch.springbatch.model.GenericDataFile;
import ar.com.telecom.shiva.persistencia.modelo.ShvAvcArchivoAvc;

public interface IArchivoAVCSoporteServicio {

	/**
	 * 
	 * @param name
	 * @return
	 * @throws NegocioExcepcion
	 */
	ShvAvcArchivoAvc buscarArchivoAvcPorNombreArchivo(String name) throws NegocioExcepcion;

	/**
	 * 
	 * @param archivo
	 * @throws NegocioExcepcion
	 */
	void enviarMailArchivoProcesadoAnteriormente(ShvAvcArchivoAvc archivo) throws NegocioExcepcion;

	/**
	 * 
	 * @param name
	 * @throws NegocioExcepcion
	 */
	void enviarMailPorNombreIncorrectoArchivoAVC(String name) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param name
	 * @throws NegocioExcepcion
	 */
	void enviarMailPorArchivoVacio(String name) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param nombreArchivoCompleto
	 * @return
	 */
	boolean esNombreArchivoRegistroAVCDinamico(String nombreArchivoCompleto);

	/**
	 * 
	 * @param archivoAvc
	 * @return
	 * @throws NegocioExcepcion
	 */
	ShvAvcArchivoAvc crear(ShvAvcArchivoAvc archivoAvc) throws NegocioExcepcion;
	
	void enviarMailConProcesamientoArchivoAVC(GenericDataFile archivoAvcInsertado, Long totalRegistroEnArchivo, long cantOK, long cantError, JobExecution jobExecution) throws NegocioExcepcion;

	//void enviarMailPorErrorProcesoArchivoAVC(String name) throws NegocioExcepcion;

	void enviarMailRegistrosErroneosArchivoAVC(GenericDataFile archivoAvcInsertado, Long totalRegistroEnArchivo, long cantOK, long cantError, JobExecution jobExecution) throws NegocioExcepcion;
}
