/**
 * 
 */
package ar.com.telecom.shiva.batch.springbatch.processor;

import org.springframework.batch.core.StepExecution;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author u564030 Pablo M. Ibarrola
 *
 */
public abstract class ConfiguracionCalculoStepExecutionSupport extends ConfiguracionCalculo {

	@Value("#{stepExecution}")
	protected StepExecution stepExecution;

	/**
	 * @return the stepExecution
	 */
	public StepExecution getStepExecution() {
		return stepExecution;
	}

	/**
	 * @param stepExecution the stepExecution to set
	 */
	public void setStepExecution(StepExecution stepExecution) {
		this.stepExecution = stepExecution;
	}
}
