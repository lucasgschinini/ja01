package ar.com.telecom.shiva.batch.springbatch.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class CustomJobListener implements JobExecutionListener{

    @Override
    public void beforeJob(JobExecution jobExecution) {
        System.out.println("JOBLISTENER: Se va a ejecutar el Job con ID: ".concat(jobExecution.getJobId().toString()));
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        System.out.println("JOBLISTENER: Se ha terminado de ejecutar el Job con ID: ".concat(jobExecution.getJobId().toString()));
    }

}
