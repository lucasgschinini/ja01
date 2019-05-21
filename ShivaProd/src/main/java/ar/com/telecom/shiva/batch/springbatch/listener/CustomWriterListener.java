package ar.com.telecom.shiva.batch.springbatch.listener;

import java.util.List;

import org.springframework.batch.core.ItemWriteListener;
import org.springframework.batch.core.StepExecution;
import org.springframework.beans.factory.annotation.Value;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.utils.logs.Traza;

public class CustomWriterListener implements ItemWriteListener<Object> {

	private long contadorLineasErroneasWriter = Constantes.CERO;
	
	@Value("#{stepExecution}")
	private StepExecution stepExecution;
	
	@Override
	public void afterWrite(List<? extends Object> arg0) {
		
	}

	@Override
	public void beforeWrite(List<? extends Object> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onWriteError(Exception arg0, List<? extends Object> arg1) {
		Traza.auditoria(CustomReaderListener.class,"[ERROR - WRITER] Linea: " + contadorLineasErroneasWriter);
		contadorLineasErroneasWriter++;
		stepExecution.getJobExecution().getExecutionContext().put("contadorLineasErroneasWriter", contadorLineasErroneasWriter);
		
	}

}
