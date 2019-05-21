package ar.com.telecom.shiva.batch.springbatch.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.SkipListener;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.batch.item.file.transform.FlatFileFormatException;
import org.springframework.batch.item.file.transform.IncorrectTokenCountException;
import org.springframework.batch.item.validator.ValidationException;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.utils.logs.Traza;

public class CustomSkipListener implements SkipListener<Object, Object> {

    @Override
    public void onSkipInRead(Throwable t) {
        StringBuilder message = new StringBuilder("[ERROR en LECTURA] ");

        if (t instanceof FlatFileParseException) {
        	message
            .append("Línea número: ")
            .append(((FlatFileParseException)t).getLineNumber())
        	.append(" Causa: ");
        	
        	if(t.getCause() instanceof IncorrectTokenCountException) {
        		message
                       .append(" Número incorrecto de columnas: Se esperaban ")
                       .append(((IncorrectTokenCountException)t.getCause()).getExpectedCount())
                       .append(", se recibió ")
                       .append(((IncorrectTokenCountException)t.getCause()).getActualCount())
                       .append(" en la entrada: ")
                       .append(((IncorrectTokenCountException)t.getCause()).getInput());
        	}
        } else {
        	message.append(t.getMessage());
        }
        
        Traza.error(CustomSkipListener.class, message.toString());
    }

    @Override
    public void onSkipInWrite(Object item, Throwable t) {
        StringBuilder message = new StringBuilder("[ERROR en ESCRITURA] ");
        if(t instanceof FlatFileFormatException) {
        	message
		            .append(" Un campo se encuentra NULO : ")
		    		.append(((FlatFileFormatException)t).getInput());
        } else {
        	message.append(t.getMessage());
        	try {
        		System.out.println("Entro en ON SKIP IN WRITE. Lanzando Exception...");
				throw new NegocioExcepcion("Error en writer.. terminando aplicacion.");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

        }
        Traza.error(CustomSkipListener.class, message.toString());

    }

    @Override
    public void onSkipInProcess(Object item, Throwable t) {
        StringBuilder message = new StringBuilder("[ERROR en PROCESADO] ");
        if (t instanceof ValidationException) {
        	message
            .append(((ValidationException)t).getMessage());
        } else {
        	message.append(t.getMessage());
        }
        Traza.error(CustomSkipListener.class, message.toString());
    }
}
