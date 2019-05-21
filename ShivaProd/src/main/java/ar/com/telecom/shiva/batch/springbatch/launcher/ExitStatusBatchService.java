/**
 * 
 */
package ar.com.telecom.shiva.batch.springbatch.launcher;

import org.springframework.batch.core.ExitStatus;

/**
 * @author u591368 F.N. Quispe
 *
 */
public class ExitStatusBatchService {

	private String		mensaje;
	private ExitStatus	exitStatus;
	private Throwable	throwable;

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String message) {
		this.mensaje = message;
	}

	public ExitStatus getExitStatus() {
		return exitStatus;
	}

	public void setExitStatus(ExitStatus exitStatus) {
		this.exitStatus = exitStatus;
	}

	public Throwable getThrowable() {
		return throwable;
	}

	public void setThrowable(Throwable e) {
		this.throwable = e;
	}
}
