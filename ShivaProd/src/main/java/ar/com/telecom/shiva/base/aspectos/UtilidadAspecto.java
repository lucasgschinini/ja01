package ar.com.telecom.shiva.base.aspectos;

import java.text.DecimalFormat;
import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.ldap.userdetails.LdapUserDetails;

import ar.com.telecom.shiva.base.utils.logs.Traza;

public class UtilidadAspecto {

	/**
	 * Para determinar detalles del login
	 * @param joinPoint
	 * @throws Throwable
	 */
	public void login(ProceedingJoinPoint joinPoint) throws Throwable {
		Traza.aspectoDebug(this.getClass(), "login", "Clase: " + joinPoint.getTarget().getClass().getSimpleName());
		Traza.aspectoDebug(this.getClass(), "login", "Metodo: " + joinPoint.getSignature().getName());
		Traza.aspectoDebug(this.getClass(), "login", "Argumentos del metodo: " + Arrays.toString(joinPoint.getArgs()));
		Traza.aspectoDebug(this.getClass(), "login", "Antes de ejecutar...");
		joinPoint.proceed(); 
		Traza.aspectoDebug(this.getClass(), "login", "Despues de ejecutar...");
		
		if (joinPoint != null && joinPoint.getArgs() != null) {
			for (Object objeto: joinPoint.getArgs()) {
				if (objeto instanceof UsernamePasswordAuthenticationToken) {
					String usuario = "";
					Object user = ((UsernamePasswordAuthenticationToken)objeto).getPrincipal();
					if (user instanceof LdapUserDetails) {
						LdapUserDetails ldapUser = (LdapUserDetails) user;
						usuario = ldapUser.getUsername().toUpperCase();
					}	
					String detalle = objeto.toString();
					Traza.auditoriaLogin(getClass(), detalle, usuario);
				}
			}
		}
	}
	
	/**
	 * Para medir tiempos de ejecuciones
	 * @param joinPoint
	 * @return
	 * @throws Throwable
	 */
	public Object time(ProceedingJoinPoint joinPoint) throws Throwable {
		Traza.aspectoDebug(this.getClass(), "time", "Clase: " + joinPoint.getTarget().getClass().getSimpleName());
		Traza.aspectoDebug(this.getClass(), "time", "Metodo: " + joinPoint.getSignature().getName());
		Traza.aspectoDebug(this.getClass(), "time", "Argumentos del metodo: " + Arrays.toString(joinPoint.getArgs()));
		Traza.aspectoDebug(this.getClass(), "time", "Antes de ejecutar...");
	
//		Runtime garbage = Runtime.getRuntime();
//		long bytesFreeMemoryAntes = garbage.freeMemory();
				
		double startTime = System.nanoTime();
		//continue on the intercepted method
		Object object = joinPoint.proceed(); 
		double stopTime = System.nanoTime();
		double elapsedTime = (stopTime - startTime) / 1000000000.0;
		Traza.aspectoDebug(this.getClass(), "time", "Despues de ejecutar...");
		
		String metodo = joinPoint.getTarget().getClass().getSimpleName() + "." + joinPoint.getSignature().getName();
		String detalle = "Resultado: " + new DecimalFormat("#.########").format(elapsedTime);
		Traza.tiempo(this.getClass(), metodo , detalle);
		
//		long bytesFreeMemoryDespues = garbage.freeMemory();
//		long bytesMaxMemoryDespues = garbage.maxMemory();
//		long bytesTotalMemoryDespues = garbage.totalMemory();
//		Traza.auditoria(this.getClass(), "CG Memory Free: Antes (" +(bytesFreeMemoryAntes/1000000) + "MB) "
//						+ " - Despues (" + (bytesFreeMemoryDespues/1000000) + "MB) - Total: "+ (bytesTotalMemoryDespues/1000000)+"MB "
//						+ " - Max:" + (bytesMaxMemoryDespues/1000000)+"MB ");
		
		return object;
	}
}
