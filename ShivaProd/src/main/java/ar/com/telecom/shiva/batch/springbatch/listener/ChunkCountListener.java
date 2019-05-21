/**
 * 
 */
package ar.com.telecom.shiva.batch.springbatch.listener;

import java.text.MessageFormat;

import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.scope.context.ChunkContext;

import ar.com.telecom.shiva.base.utils.logs.Traza;

/**
 * @author u564030 Pablo M. Ibarrola
 *
 */
public class ChunkCountListener implements ChunkListener {


	private MessageFormat fmt = new MessageFormat("{0} Items Procesados");

	private int loggingInterval = 1000;
	
	
	/* (non-Javadoc)
	 * @see org.springframework.batch.core.ChunkListener#beforeChunk(org.springframework.batch.core.scope.context.ChunkContext)
	 */
	@Override
	public void beforeChunk(ChunkContext context) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.springframework.batch.core.ChunkListener#afterChunk(org.springframework.batch.core.scope.context.ChunkContext)
	 */
	@Override
	public void afterChunk(ChunkContext context) {
		int readCount = context.getStepContext().getStepExecution().getReadCount();
				
		// If the number of records processed so far is a multiple of the logging interval then output a log message.			
//		if (count > 0 && count % loggingInterval == 0) {
			Traza.auditoria(ChunkCountListener.class, fmt.format(new Object[] {new Integer(readCount) })) ;
//		}

	}

	/* (non-Javadoc)
	 * @see org.springframework.batch.core.ChunkListener#afterChunkError(org.springframework.batch.core.scope.context.ChunkContext)
	 */
	@Override
	public void afterChunkError(ChunkContext context) {
		// TODO Auto-generated method stub

	}
	
	public void setItemName(String itemName) {
		this.fmt = new MessageFormat("{0} " + itemName + " procesado");
	}

	public void setLoggingInterval(int loggingInterval) {
		this.loggingInterval = loggingInterval;
	}

}
