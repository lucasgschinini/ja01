/**
 * 
 */
package ar.com.telecom.shiva.batch.springbatch.listener;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.ItemReadListener;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.batch.item.file.transform.IncorrectTokenCountException;
import org.springframework.beans.factory.annotation.Value;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;

/**
 * @author u591368 F.N. Quispe
 *
 */
public class CustomReaderListener implements ItemReadListener<Object>, StepExecutionListener {

	
	private int contadorLineasLeidas = 2;
	private long contadorLineasErroneas = Constantes.CERO;
	
	@Value("#{stepExecution}")
	private StepExecution stepExecution;
	
	@Override
	public void afterRead(Object arg0) {
		Traza.auditoria(CustomReaderListener.class,"[READER] Se ha leido la linea: " + contadorLineasLeidas);
		contadorLineasLeidas++;
	}

	@Override
	public void beforeRead() {
		Traza.auditoria(CustomReaderListener.class, "[READER] leyendo linea: " + contadorLineasLeidas);
		
		Object contadorLineasErroneasObject = stepExecution.getJobExecution().getExecutionContext().get("contadorLineasErroneas"); 
		
		if (!Validaciones.isObjectNull(contadorLineasErroneasObject)) {
			contadorLineasErroneas = (Long) contadorLineasErroneasObject; 
		}
		
	}

	@Override
	public void onReadError(Exception exception) {
		StringBuilder message = new StringBuilder( "[ERROR-READER] Mensaje: ");
		StringBuilder messageException = new StringBuilder();
		String logProcesamiento = (String) stepExecution.getJobExecution().getExecutionContext().get("logProcesamiento");
		
		if (Validaciones.isNullEmptyOrDash(logProcesamiento)) {
			logProcesamiento = Constantes.EMPTY_STRING;
		}
		
		if (exception instanceof FlatFileParseException) {
			 logProcesamiento += "Línea número: " + ((FlatFileParseException)exception).getLineNumber() + " Causa:";
	        	
			 	if (exception.getCause() instanceof IncorrectTokenCountException) {
	        		messageException
	                       .append(" Número incorrecto de columnas: Se esperaban ")
	                       .append(((IncorrectTokenCountException)exception.getCause()).getExpectedCount())
	                       .append(", se recibió ")
	                       .append(((IncorrectTokenCountException)exception.getCause()).getActualCount())
	                       .append(" en la entrada: ")
	                       .append(((IncorrectTokenCountException)exception.getCause()).getInput());
	        	}
	        } else {
	        	message.append(exception.getMessage());
	        }
		if (!Validaciones.isNullEmptyOrDash(messageException.toString())) {
			logProcesamiento += messageException.toString() + System.lineSeparator();
		} else {
			logProcesamiento += message.toString() + System.lineSeparator();
		}
		 
		Traza.error(CustomReaderListener.class, "[ERROR-READER] Ocurrio un error de lectura en la linea: " + contadorLineasLeidas);		
		Traza.error(CustomReaderListener.class, message.append(messageException).toString());
		
		contadorLineasErroneas++;
		stepExecution.getJobExecution().getExecutionContext().put("contadorLineasErroneas", contadorLineasErroneas);
		stepExecution.getJobExecution().getExecutionContext().put("logProcesamiento", logProcesamiento);
		stepExecution.getJobExecution().getExecutionContext().put("tieneError", true);
		contadorLineasLeidas++;
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		ExecutionContext jobContext = stepExecution.getJobExecution().getExecutionContext();
		
		jobContext.put("readCount", stepExecution.getReadCount());
		jobContext.put("writeCount", stepExecution.getWriteCount());
		jobContext.put("commitCount", stepExecution.getCommitCount());
		jobContext.put("rollbackCount", stepExecution.getRollbackCount());
		
//		int cantidadLineasErroneas = (int) jobContext.get("contadorLineasErroneas"); 
		
		if (Constantes.CERO == contadorLineasErroneas && Constantes.CERO == stepExecution.getReadCount()) {  
			String logProcesamiento = "Archivo Vacío";
			stepExecution.getJobExecution().getExecutionContext().put("logProcesamiento", logProcesamiento);
       }  
		
		return stepExecution.getExitStatus();
	}

	@Override
	public void beforeStep(StepExecution arg0) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @return the contadorLineasErroneas
	 */
	public long getContadorLineasErroneas() {
		return contadorLineasErroneas;
	}

	/**
	 * @param contadorLineasErroneas the contadorLineasErroneas to set
	 */
	public void setContadorLineasErroneas(long contadorLineasErroneas) {
		this.contadorLineasErroneas = contadorLineasErroneas;
	}

	
}
