package ar.com.telecom.shiva.base.aspectos;

import java.text.DecimalFormat;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;

import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.logs.Traza;


public class ControllerAspecto {

	public Object timeController(ProceedingJoinPoint joinPoint) throws Throwable {
		Traza.aspectoDebug(this.getClass(), "timeController", "Clase: " + joinPoint.getTarget().getClass().getSimpleName());
		Traza.aspectoDebug(this.getClass(), "timeController", "Metodo: " + joinPoint.getSignature().getName());
		Traza.aspectoDebug(this.getClass(), "timeController", "Antes de ejecutar...");
	
		String usuario = "";
		if (joinPoint != null && joinPoint.getArgs() != null) {
			for (Object objeto: joinPoint.getArgs()) {
				if (objeto instanceof HttpServletRequest) {
					HttpServletRequest request = ((HttpServletRequest)objeto);
					usuario = Utilidad.getUsuarioSesion(request);
				}
			}
		}
	
		double startTime = System.nanoTime();
		//continue on the intercepted method
		Object object = joinPoint.proceed(); 
		double stopTime = System.nanoTime();
		double elapsedTime = (stopTime - startTime) / 1000000000.0;
		Traza.aspectoDebug(this.getClass(), "timeController", "Despues de ejecutar...");
		
		String metodo = joinPoint.getTarget().getClass().getSimpleName() + "." + joinPoint.getSignature().getName();
		String detalle = "Resultado: " + new DecimalFormat("#.########").format(elapsedTime);
		Traza.tiempo(this.getClass(), usuario, metodo , detalle);
		
		return object;
	}
}
