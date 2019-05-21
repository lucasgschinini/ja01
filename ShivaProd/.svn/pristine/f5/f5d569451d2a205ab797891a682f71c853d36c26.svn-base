package ar.com.telecom.shiva.test.seguridad;

import org.junit.Test;

import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.presentacion.controlador.GeneralController;
import ch.qos.logback.classic.Level;

public class TrazaTest {

	@Test
	public void testTraza() {
		Traza.debug(this.getClass(), "detalle");
		
		Traza.advertencia(this.getClass(), "detalle");
		
		Traza.auditoria(this.getClass(), null, "detalle");
		Traza.auditoria(this.getClass(), "accion", "detalle");
		
		Traza.evento(this.getClass(), "idEvento", "idTransaccion", "evento");
		
		Traza.error(this.getClass(), "mensaje ERROR");
		Traza.error(this.getClass(), null, new Exception("detalle error"));
		Traza.error(this.getClass(), "mensaje ERROR", new Exception("detalle error"));
		Traza.error(this.getClass(), "idTransaccion", "metodo", new Exception("detalle error"));
		
		//Con esto funciona para una clase pero para los demás no funciona como root
		//TODO: Solución implementar un singleton para que busque un valor boolean en comun y setee 
		//según lo configurado en el logback.xml
		//También poner un timer para recuperar el estado del nivel del logueo 
		//(por si el administrador se olvida de cambiar el estado)
		Traza.setLoggingLevel(Level.DEBUG);
		Traza.debug(GeneralController.class, "Desactiva los logs");
		Traza.debug(this.getClass(), "Desactiva los logs");
		
		Traza.setLoggingLevel(Level.OFF);
		Traza.debug(this.getClass(), "detalle 2");
		Traza.auditoria(this.getClass(), null, "detalle 2");
		Traza.auditoria(GeneralController.class, null, "detalle 2");
		
		Traza.setLoggingLevel(Level.DEBUG);
		Traza.debug(this.getClass(), "Se activa los logs");
		Traza.debug(GeneralController.class, "Se activa los logs");
		Traza.auditoria(GeneralController.class, null, "detalle 3");	
		Traza.auditoria(this.getClass(), null, "detalle 3");
	}
	
}
