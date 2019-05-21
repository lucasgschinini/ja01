package ar.com.telecom.shiva.base.soa.singleton;

import java.util.Date;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;

public class SoapMensajesSingleton {
	
	private static ConcurrentMap<String, SoapMensaje> listMensajesEntrada 
		= new ConcurrentHashMap<String, SoapMensaje> ();
	
	private static ConcurrentMap<String, SoapMensaje> listMensajesSalida 
	= new ConcurrentHashMap<String, SoapMensaje> ();
	
	//----------------- Singleton ------------------
	private static SoapMensajesSingleton SINGLETON = null;
	
	// Constructor privado
	private SoapMensajesSingleton() {}
	
    // creador sincronizado para protegerse de posibles problemas  
	// multi-hilo para evitar instanciación múltiple
    private synchronized static void createInstance() {
        if (SINGLETON == null) { 
            SINGLETON = new SoapMensajesSingleton();
        }
    }
    public static SoapMensajesSingleton getInstance() {
        if (SINGLETON == null) createInstance();
        return SINGLETON;
    }
    //----------------- fin - Singleton ------------------

    /**
     * Incorporo el mensaje de entrada
     * @param id
     * @param msg
     */
    public static synchronized void addMensajeEntrada(String id, String msg) {
    	limpiarMensajes();
    	
    	SoapMensaje mensaje = new SoapMensaje();
    	mensaje.setContenidoMensaje(msg);
		mensaje.setTimeStartSesion(new Date());
		listMensajesEntrada.put(id, mensaje);
	}
    
    /**
     * Incorporo el mensaje de salida
     * @param id
     * @param msg
     */
    public static synchronized void addMensajeSalida(String id, String msg) {
    	limpiarMensajes();
    	
    	SoapMensaje mensaje = new SoapMensaje();
    	mensaje.setContenidoMensaje(msg);
		mensaje.setTimeStartSesion(new Date());
		listMensajesSalida.put(id, mensaje);
	}
    
    /**
     * Tomo el mensaje de entrada
     * @param id
     * @param msg
     */
    public static String getMensajeEntrada(String id) {
    	limpiarMensajes();
    	if (Validaciones.isMapNotEmpty(listMensajesEntrada)) {
			SoapMensaje mensaje = listMensajesEntrada.get(id);
			if (mensaje != null) {
				return mensaje.getContenidoMensaje();
			}
		} 
		return null;
	}
    
    /**
     * Tomo el mensaje de salida 
     * @param id
     */
    public static String getMensajeSalida(String id) {
    	limpiarMensajes();
    	if (Validaciones.isMapNotEmpty(listMensajesSalida)) {
			SoapMensaje mensaje = listMensajesSalida.get(id);
			if (mensaje != null) {
				return mensaje.getContenidoMensaje();
			}
		} 
		return null;
	}
    
    
    
    /**
     * Limpio todos los mensajes expirados
     */
    private static void limpiarMensajes() {	
		borrarMensajesExpirados(listMensajesEntrada);
		borrarMensajesExpirados(listMensajesSalida);
	}
    
    /**
     * Metodo generico que borra todos los mensajes expirados
     * @param lista
     */
    private static void borrarMensajesExpirados(ConcurrentMap<String, SoapMensaje> lista) {	
		if (Validaciones.isMapNotEmpty(lista)) {
			for (Entry<String, SoapMensaje> e: lista.entrySet()) {
				String id = (String) e.getKey();
				SoapMensaje msg = (SoapMensaje) e.getValue();
				
				if (!Validaciones.isObjectNull(msg)) {	
					if (msg.getTimeStartSesion() != null) {
						long diferencia = Utilidad.calcularDiferenciaDeFechas(msg.getTimeStartSesion(), new Date());
						if (diferencia > Constantes.$Quince_MIN_MS) {
							lista.remove(id);
						}
					} else {
						//Si la fecha de comienzo de la sesion es nula, lo borro
						lista.remove(id);
					}
				} else {
					//Si el usuarioSesion es nulo, lo borro
					lista.remove(id);
				}
			}
		}
    }	
}
